package jadx.core.dex.nodes;

import com.android.dex.ClassData.Method;
import com.android.dex.Code;
import com.android.dex.Code.CatchHandler;
import com.android.dex.Code.Try;
import jadx.core.deobf.Deobfuscator;
import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.nodes.JumpInfo;
import jadx.core.dex.attributes.nodes.LineAttrNode;
import jadx.core.dex.attributes.nodes.LoopInfo;
import jadx.core.dex.info.AccessInfo;
import jadx.core.dex.info.AccessInfo.AFType;
import jadx.core.dex.info.ClassInfo;
import jadx.core.dex.info.MethodInfo;
import jadx.core.dex.instructions.GotoNode;
import jadx.core.dex.instructions.IfNode;
import jadx.core.dex.instructions.InsnDecoder;
import jadx.core.dex.instructions.SwitchNode;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.instructions.args.SSAVar;
import jadx.core.dex.instructions.args.TypeImmutableArg;
import jadx.core.dex.nodes.parser.SignatureParser;
import jadx.core.dex.regions.Region;
import jadx.core.dex.trycatch.ExcHandlerAttr;
import jadx.core.dex.trycatch.ExceptionHandler;
import jadx.core.dex.trycatch.TryCatchBlock;
import jadx.core.utils.Utils;
import jadx.core.utils.exceptions.DecodeException;
import jadx.core.utils.exceptions.JadxRuntimeException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MethodNode extends LineAttrNode implements ILoadable, IDexNode {
    private static final Logger LOG = LoggerFactory.getLogger(MethodNode.class);
    private final AccessInfo accFlags;
    private List<RegisterArg> argsList;
    private List<BlockNode> blocks;
    private int codeSize;
    private int debugInfoOffset;
    private BlockNode enterBlock;
    private List<ExceptionHandler> exceptionHandlers = Collections.emptyList();
    private List<BlockNode> exitBlocks;
    private Map<ArgType, List<ArgType>> genericMap;
    private InsnNode[] instructions;
    private List<LoopInfo> loops = Collections.emptyList();
    private final Method methodData;
    private boolean methodIsVirtual;
    private final MethodInfo mthInfo;
    private boolean noCode;
    private final ClassNode parentClass;
    private Region region;
    private int regsCount;
    private ArgType retType;
    private List<SSAVar> sVars = Collections.emptyList();
    private RegisterArg thisArg;

    public MethodNode(ClassNode classNode, Method mthData, boolean isVirtual) {
        this.mthInfo = MethodInfo.fromDex(classNode.dex(), mthData.getMethodIndex());
        this.parentClass = classNode;
        this.accFlags = new AccessInfo(mthData.getAccessFlags(), AFType.METHOD);
        this.noCode = mthData.getCodeOffset() == 0;
        if (this.noCode) {
            mthData = null;
        }
        this.methodData = mthData;
        this.methodIsVirtual = isVirtual;
    }

    public void load() throws DecodeException {
        try {
            if (this.noCode) {
                this.regsCount = 0;
                this.codeSize = 0;
                initMethodTypes();
                return;
            }
            Code mthCode = this.parentClass.dex().readCode(this.methodData);
            this.regsCount = mthCode.getRegistersSize();
            initMethodTypes();
            InsnDecoder decoder = new InsnDecoder(this);
            decoder.decodeInsns(mthCode);
            this.instructions = decoder.process();
            this.codeSize = this.instructions.length;
            initTryCatches(mthCode);
            initJumps();
            this.debugInfoOffset = mthCode.getDebugInfoOffset();
        } catch (Exception e) {
            if (!this.noCode) {
                this.noCode = true;
                load();
                this.noCode = false;
            }
            throw new DecodeException(this, "Load method exception", e);
        }
    }

    public void checkInstructions() {
        List<RegisterArg> list = new ArrayList();
        for (InsnNode insnNode : this.instructions) {
            if (insnNode != null) {
                list.clear();
                RegisterArg resultArg = insnNode.getResult();
                if (resultArg != null) {
                    list.add(resultArg);
                }
                insnNode.getRegisterArgs(list);
                int argsCount = list.size();
                for (int i = 0; i < argsCount; i++) {
                    if (((RegisterArg) list.get(i)).getRegNum() >= this.regsCount) {
                        throw new JadxRuntimeException("Incorrect register number in instruction: " + insnNode + ", expected to be less than " + this.regsCount);
                    }
                }
                continue;
            }
        }
    }

    private void initMethodTypes() {
        if (!parseSignature()) {
            this.retType = this.mthInfo.getReturnType();
            initArguments(this.mthInfo.getArgumentsTypes());
        }
    }

    public void unload() {
        if (!this.noCode) {
            this.instructions = null;
            this.blocks = null;
            this.exitBlocks = null;
            this.exceptionHandlers.clear();
        }
    }

    private boolean parseSignature() {
        SignatureParser sp = SignatureParser.fromNode(this);
        if (sp == null) {
            return false;
        }
        try {
            this.genericMap = sp.consumeGenericMap();
            List<ArgType> argsTypes = sp.consumeMethodArgs();
            this.retType = sp.consumeType();
            List<ArgType> mthArgs = this.mthInfo.getArgumentsTypes();
            if (argsTypes.size() != mthArgs.size()) {
                if (argsTypes.isEmpty()) {
                    return false;
                }
                if (this.mthInfo.isConstructor()) {
                    if (getParentClass().getAccessFlags().isEnum()) {
                        argsTypes.add(0, mthArgs.get(0));
                        argsTypes.add(1, mthArgs.get(1));
                    } else {
                        argsTypes.add(0, mthArgs.get(0));
                    }
                    if (argsTypes.size() != mthArgs.size()) {
                        return false;
                    }
                }
                LOG.warn("Wrong signature parse result: {} -> {}, not generic version: {}", new Object[]{sp, argsTypes, mthArgs});
                return false;
            }
            initArguments(argsTypes);
            return true;
        } catch (JadxRuntimeException e) {
            LOG.error("Method signature parse error: {}", this, e);
            return false;
        }
    }

    private void initArguments(List<ArgType> args) {
        int pos;
        if (this.noCode) {
            pos = 1;
        } else {
            pos = this.regsCount;
            for (ArgType arg : args) {
                pos -= arg.getRegCount();
            }
        }
        if (this.accFlags.isStatic()) {
            this.thisArg = null;
        } else {
            TypeImmutableArg arg2 = InsnArg.typeImmutableReg(pos - 1, this.parentClass.getClassInfo().getType());
            arg2.markAsThis();
            this.thisArg = arg2;
        }
        if (args.isEmpty()) {
            this.argsList = Collections.emptyList();
            return;
        }
        this.argsList = new ArrayList(args.size());
        for (ArgType arg3 : args) {
            this.argsList.add(InsnArg.typeImmutableReg(pos, arg3));
            pos += arg3.getRegCount();
        }
    }

    public List<RegisterArg> getArguments(boolean includeThis) {
        if (!includeThis || this.thisArg == null) {
            return this.argsList;
        }
        List<RegisterArg> list = new ArrayList(this.argsList.size() + 1);
        list.add(this.thisArg);
        list.addAll(this.argsList);
        return list;
    }

    public RegisterArg removeFirstArgument() {
        add(AFlag.SKIP_FIRST_ARG);
        return (RegisterArg) this.argsList.remove(0);
    }

    public RegisterArg getThisArg() {
        return this.thisArg;
    }

    public ArgType getReturnType() {
        return this.retType;
    }

    public Map<ArgType, List<ArgType>> getGenericMap() {
        return this.genericMap;
    }

    private void initTryCatches(Code mthCode) {
        InsnNode[] insnByOffset = this.instructions;
        CatchHandler[] catchBlocks = mthCode.getCatchHandlers();
        Try[] tries = mthCode.getTries();
        if (catchBlocks.length != 0 || tries.length != 0) {
            int hc = 0;
            Set<Integer> addrs = new HashSet();
            List<TryCatchBlock> catches = new ArrayList(catchBlocks.length);
            for (CatchHandler handler : catchBlocks) {
                int addr;
                TryCatchBlock tcBlock = new TryCatchBlock();
                catches.add(tcBlock);
                for (int i = 0; i < handler.getAddresses().length; i++) {
                    addr = handler.getAddresses()[i];
                    tcBlock.addHandler(this, addr, ClassInfo.fromDex(this.parentClass.dex(), handler.getTypeIndexes()[i]));
                    addrs.add(Integer.valueOf(addr));
                    hc++;
                }
                addr = handler.getCatchAllAddress();
                if (addr >= 0) {
                    tcBlock.addHandler(this, addr, null);
                    addrs.add(Integer.valueOf(addr));
                    hc++;
                }
            }
            if (hc > 0 && hc != addrs.size()) {
                for (TryCatchBlock ct1 : catches) {
                    for (TryCatchBlock ct2 : catches) {
                        if (ct1 != ct2 && ct2.containsAllHandlers(ct1)) {
                            for (ExceptionHandler h : ct1.getHandlers()) {
                                ct2.removeHandler(this, h);
                                h.setTryBlock(ct1);
                            }
                        }
                    }
                }
            }
            addrs.clear();
            for (TryCatchBlock ct : catches) {
                for (ExceptionHandler eh : ct.getHandlers()) {
                    insnByOffset[eh.getHandleOffset()].addAttr(new ExcHandlerAttr(ct, eh));
                }
            }
            for (Try aTry : tries) {
                TryCatchBlock catchBlock = (TryCatchBlock) catches.get(aTry.getCatchHandlerIndex());
                int offset = aTry.getStartAddress();
                int end = (aTry.getInstructionCount() + offset) - 1;
                InsnNode insn = insnByOffset[offset];
                insn.add(AFlag.TRY_ENTER);
                while (offset <= end && offset >= 0) {
                    insn = insnByOffset[offset];
                    catchBlock.addInsn(insn);
                    offset = InsnDecoder.getNextInsnOffset(insnByOffset, offset);
                }
                if (insnByOffset[end] != null) {
                    insnByOffset[end].add(AFlag.TRY_LEAVE);
                } else {
                    insn.add(AFlag.TRY_LEAVE);
                }
            }
        }
    }

    private void initJumps() {
        InsnNode[] insnByOffset = this.instructions;
        for (int offset = 0; offset < insnByOffset.length; offset++) {
            InsnNode insn = insnByOffset[offset];
            if (insn != null) {
                switch (insn.getType()) {
                    case SWITCH:
                        for (int target : ((SwitchNode) insn).getTargets()) {
                            addJump(insnByOffset, offset, target);
                        }
                        int nextInsnOffset = InsnDecoder.getNextInsnOffset(insnByOffset, offset);
                        if (nextInsnOffset == -1) {
                            break;
                        }
                        addJump(insnByOffset, offset, nextInsnOffset);
                        break;
                    case IF:
                        int next = InsnDecoder.getNextInsnOffset(insnByOffset, offset);
                        if (next != -1) {
                            addJump(insnByOffset, offset, next);
                        }
                        addJump(insnByOffset, offset, ((IfNode) insn).getTarget());
                        break;
                    case GOTO:
                        addJump(insnByOffset, offset, ((GotoNode) insn).getTarget());
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static void addJump(InsnNode[] insnByOffset, int offset, int target) {
        insnByOffset[target].addAttr(AType.JUMP, new JumpInfo(offset, target));
    }

    public String getName() {
        return this.mthInfo.getName();
    }

    public String getAlias() {
        return this.mthInfo.getAlias();
    }

    public ClassNode getParentClass() {
        return this.parentClass;
    }

    public boolean isNoCode() {
        return this.noCode;
    }

    public int getCodeSize() {
        return this.codeSize;
    }

    public InsnNode[] getInstructions() {
        return this.instructions;
    }

    public void unloadInsnArr() {
        this.instructions = null;
    }

    public void initBasicBlocks() {
        this.blocks = new ArrayList();
        this.exitBlocks = new ArrayList(1);
    }

    public void finishBasicBlocks() {
        ((ArrayList) this.blocks).trimToSize();
        ((ArrayList) this.exitBlocks).trimToSize();
        this.blocks = Collections.unmodifiableList(this.blocks);
        this.exitBlocks = Collections.unmodifiableList(this.exitBlocks);
        for (BlockNode block : this.blocks) {
            block.lock();
        }
    }

    public List<BlockNode> getBasicBlocks() {
        return this.blocks;
    }

    public BlockNode getEnterBlock() {
        return this.enterBlock;
    }

    public void setEnterBlock(BlockNode enterBlock) {
        this.enterBlock = enterBlock;
    }

    public List<BlockNode> getExitBlocks() {
        return this.exitBlocks;
    }

    public void addExitBlock(BlockNode exitBlock) {
        this.exitBlocks.add(exitBlock);
    }

    public void registerLoop(LoopInfo loop) {
        if (this.loops.isEmpty()) {
            this.loops = new ArrayList(5);
        }
        loop.setId(this.loops.size());
        this.loops.add(loop);
    }

    @Nullable
    public LoopInfo getLoopForBlock(BlockNode block) {
        if (this.loops.isEmpty()) {
            return null;
        }
        for (LoopInfo loop : this.loops) {
            if (loop.getLoopBlocks().contains(block)) {
                return loop;
            }
        }
        return null;
    }

    public List<LoopInfo> getAllLoopsForBlock(BlockNode block) {
        if (this.loops.isEmpty()) {
            return Collections.emptyList();
        }
        List<LoopInfo> list = new ArrayList(this.loops.size());
        for (LoopInfo loop : this.loops) {
            if (loop.getLoopBlocks().contains(block)) {
                list.add(loop);
            }
        }
        return list;
    }

    public int getLoopsCount() {
        return this.loops.size();
    }

    public Iterable<LoopInfo> getLoops() {
        return this.loops;
    }

    public ExceptionHandler addExceptionHandler(ExceptionHandler handler) {
        if (this.exceptionHandlers.isEmpty()) {
            this.exceptionHandlers = new ArrayList(2);
        } else {
            for (ExceptionHandler h : this.exceptionHandlers) {
                if (h == handler) {
                    return h;
                }
                if (h.getHandleOffset() == handler.getHandleOffset()) {
                    return h;
                }
            }
        }
        this.exceptionHandlers.add(handler);
        return handler;
    }

    public Iterable<ExceptionHandler> getExceptionHandlers() {
        return this.exceptionHandlers;
    }

    public boolean isNoExceptionHandlers() {
        return this.exceptionHandlers.isEmpty();
    }

    public int getExceptionHandlersCount() {
        return this.exceptionHandlers.size();
    }

    public boolean isArgsOverload() {
        int argsCount = this.mthInfo.getArgumentsTypes().size();
        if (argsCount == 0) {
            return false;
        }
        String name = getName();
        for (MethodNode method : this.parentClass.getMethods()) {
            MethodInfo otherMthInfo = method.mthInfo;
            if (this != method && otherMthInfo.getArgumentsTypes().size() == argsCount && otherMthInfo.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isDefaultConstructor() {
        if (!this.accFlags.isConstructor() || !this.mthInfo.isConstructor()) {
            return false;
        }
        int defaultArgCount = 0;
        if (this.parentClass.getClassInfo().isInner() && !this.parentClass.getAccessFlags().isStatic()) {
            ClassNode outerCls = this.parentClass.getParentClass();
            if (!(this.argsList == null || this.argsList.isEmpty() || !((RegisterArg) this.argsList.get(0)).getType().equals(outerCls.getClassInfo().getType()))) {
                defaultArgCount = 1;
            }
        }
        if (this.argsList == null || this.argsList.size() == defaultArgCount) {
            return true;
        }
        return false;
    }

    public boolean isVirtual() {
        return this.methodIsVirtual;
    }

    public int getRegsCount() {
        return this.regsCount;
    }

    public int getDebugInfoOffset() {
        return this.debugInfoOffset;
    }

    public SSAVar makeNewSVar(int regNum, int version, @NotNull RegisterArg assignArg) {
        SSAVar var = new SSAVar(regNum, version, assignArg);
        if (this.sVars.isEmpty()) {
            this.sVars = new ArrayList();
        }
        this.sVars.add(var);
        return var;
    }

    public int getNextSVarVersion(int regNum) {
        int v = -1;
        for (SSAVar sVar : this.sVars) {
            if (sVar.getRegNum() == regNum) {
                v = Math.max(v, sVar.getVersion());
            }
        }
        return v + 1;
    }

    public void removeSVar(SSAVar var) {
        this.sVars.remove(var);
    }

    public List<SSAVar> getSVars() {
        return this.sVars;
    }

    public AccessInfo getAccessFlags() {
        return this.accFlags;
    }

    public Region getRegion() {
        return this.region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public DexNode dex() {
        return this.parentClass.dex();
    }

    public RootNode root() {
        return dex().root();
    }

    public MethodInfo getMethodInfo() {
        return this.mthInfo;
    }

    public int hashCode() {
        return this.mthInfo.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.mthInfo.equals(((MethodNode) obj).mthInfo);
    }

    public String toString() {
        return this.parentClass + Deobfuscator.CLASS_NAME_SEPARATOR + this.mthInfo.getName() + "(" + Utils.listToString(this.mthInfo.getArgumentsTypes()) + "):" + this.retType;
    }
}

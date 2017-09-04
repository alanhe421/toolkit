package jadx.core.dex.nodes.parser;

import com.android.dex.Dex.Section;
import jadx.core.dex.attributes.nodes.SourceFileAttr;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.instructions.args.SSAVar;
import jadx.core.dex.nodes.DexNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.utils.exceptions.DecodeException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebugInfoParser {
    private static final int DBG_ADVANCE_LINE = 2;
    private static final int DBG_ADVANCE_PC = 1;
    private static final int DBG_END_LOCAL = 5;
    private static final int DBG_END_SEQUENCE = 0;
    private static final int DBG_FIRST_SPECIAL = 10;
    private static final int DBG_LINE_BASE = -4;
    private static final int DBG_LINE_RANGE = 15;
    private static final int DBG_RESTART_LOCAL = 6;
    private static final int DBG_SET_EPILOGUE_BEGIN = 8;
    private static final int DBG_SET_FILE = 9;
    private static final int DBG_SET_PROLOGUE_END = 7;
    private static final int DBG_START_LOCAL = 3;
    private static final int DBG_START_LOCAL_EXTENDED = 4;
    private static final Logger LOG = LoggerFactory.getLogger(DebugInfoParser.class);
    private final InsnArg[] activeRegisters;
    private final DexNode dex;
    private final InsnNode[] insnByOffset;
    private final LocalVar[] locals;
    private final MethodNode mth;
    private final Section section;

    public DebugInfoParser(MethodNode mth, int debugOffset, InsnNode[] insnByOffset) {
        this.mth = mth;
        this.dex = mth.dex();
        this.section = this.dex.openSection(debugOffset);
        int regsCount = mth.getRegsCount();
        this.locals = new LocalVar[regsCount];
        this.activeRegisters = new InsnArg[regsCount];
        this.insnByOffset = insnByOffset;
    }

    public void process() throws DecodeException {
        int addr = DBG_END_SEQUENCE;
        int line = this.section.readUleb128();
        int paramsCount = this.section.readUleb128();
        List<RegisterArg> mthArgs = this.mth.getArguments(false);
        for (int i = DBG_END_SEQUENCE; i < paramsCount; i += DBG_ADVANCE_PC) {
            int id = this.section.readUleb128() - 1;
            if (id != -1) {
                String name = this.dex.getString(id);
                if (i < mthArgs.size()) {
                    ((RegisterArg) mthArgs.get(i)).setName(name);
                }
            }
        }
        for (RegisterArg arg : mthArgs) {
            int rn = arg.getRegNum();
            this.locals[rn] = new LocalVar(arg);
            this.activeRegisters[rn] = arg;
        }
        addrChange(-1, DBG_ADVANCE_PC, line);
        setLine(DBG_END_SEQUENCE, line);
        int c = this.section.readByte() & 255;
        while (c != 0) {
            LocalVar var;
            switch (c) {
                case DBG_ADVANCE_PC /*1*/:
                    addr = addrChange(addr, this.section.readUleb128(), line);
                    setLine(addr, line);
                    break;
                case DBG_ADVANCE_LINE /*2*/:
                    line += this.section.readSleb128();
                    break;
                case DBG_START_LOCAL /*3*/:
                    startVar(new LocalVar(this.dex, this.section.readUleb128(), this.section.readUleb128() - 1, this.section.readUleb128() - 1, -1), addr, line);
                    break;
                case DBG_START_LOCAL_EXTENDED /*4*/:
                    startVar(new LocalVar(this.dex, this.section.readUleb128(), this.section.readUleb128() - 1, this.section.readUleb128() - 1, this.section.readUleb128() - 1), addr, line);
                    break;
                case DBG_END_LOCAL /*5*/:
                    var = this.locals[this.section.readUleb128()];
                    if (var == null) {
                        break;
                    }
                    var.end(addr, line);
                    setVar(var);
                    break;
                case DBG_RESTART_LOCAL /*6*/:
                    var = this.locals[this.section.readUleb128()];
                    if (var != null) {
                        if (var.end(addr, line)) {
                            setVar(var);
                        }
                        var.start(addr, line);
                        break;
                    }
                    break;
                case DBG_SET_PROLOGUE_END /*7*/:
                case DBG_SET_EPILOGUE_BEGIN /*8*/:
                    break;
                case DBG_SET_FILE /*9*/:
                    int idx = this.section.readUleb128() - 1;
                    if (idx == -1) {
                        break;
                    }
                    this.mth.addAttr(new SourceFileAttr(this.dex.getString(idx)));
                    break;
                default:
                    if (c >= DBG_FIRST_SPECIAL) {
                        int adjustedOpcode = c - 10;
                        addr = addrChange(addr, adjustedOpcode / DBG_LINE_RANGE, line);
                        line += (adjustedOpcode % DBG_LINE_RANGE) + DBG_LINE_BASE;
                        setLine(addr, line);
                        break;
                    }
                    throw new DecodeException("Unknown debug insn code: " + c);
            }
            c = this.section.readByte() & 255;
        }
        LocalVar[] arr$ = this.locals;
        int len$ = arr$.length;
        for (int i$ = DBG_END_SEQUENCE; i$ < len$; i$ += DBG_ADVANCE_PC) {
            var = arr$[i$];
            if (!(var == null || var.isEnd())) {
                var.end(this.mth.getCodeSize() - 1, line);
                setVar(var);
            }
        }
        setSourceLines(addr, this.insnByOffset.length, line);
    }

    private int addrChange(int addr, int addrInc, int line) {
        int newAddr = Math.min(addr + addrInc, this.insnByOffset.length - 1);
        for (int i = addr + DBG_ADVANCE_PC; i <= newAddr; i += DBG_ADVANCE_PC) {
            InsnNode insn = this.insnByOffset[i];
            if (insn != null) {
                for (InsnArg arg : insn.getArguments()) {
                    if (arg.isRegister()) {
                        this.activeRegisters[((RegisterArg) arg).getRegNum()] = arg;
                    }
                }
                RegisterArg res = insn.getResult();
                if (res != null) {
                    this.activeRegisters[res.getRegNum()] = res;
                }
            }
        }
        setSourceLines(addr, newAddr, line);
        return newAddr;
    }

    private void setSourceLines(int start, int end, int line) {
        for (int offset = start + DBG_ADVANCE_PC; offset < end; offset += DBG_ADVANCE_PC) {
            setLine(offset, line);
        }
    }

    private void setLine(int offset, int line) {
        InsnNode insn = this.insnByOffset[offset];
        if (insn != null) {
            insn.setSourceLine(line);
        }
    }

    private void startVar(LocalVar var, int addr, int line) {
        int regNum = var.getRegNum();
        LocalVar prev = this.locals[regNum];
        if (!(prev == null || prev.isEnd())) {
            prev.end(addr, line);
            setVar(prev);
        }
        InsnArg activeReg = this.activeRegisters[var.getRegNum()];
        if (activeReg instanceof RegisterArg) {
            SSAVar ssaVar = ((RegisterArg) activeReg).getSVar();
            if (!(ssaVar == null || ssaVar.getStartAddr() == -1)) {
                InsnNode parentInsn = ssaVar.getAssign().getParentInsn();
                if (parentInsn != null && parentInsn.getOffset() >= 0) {
                    addr = parentInsn.getOffset();
                }
            }
        }
        var.start(addr, line);
        this.locals[regNum] = var;
    }

    private void setVar(LocalVar var) {
        int start = var.getStartAddr();
        int end = var.getEndAddr();
        for (int i = start; i <= end; i += DBG_ADVANCE_PC) {
            InsnNode insn = this.insnByOffset[i];
            if (insn != null) {
                fillLocals(insn, var);
            }
        }
        merge(this.activeRegisters[var.getRegNum()], var);
    }

    private static void fillLocals(InsnNode insn, LocalVar var) {
        merge(insn.getResult(), var);
        for (InsnArg arg : insn.getArguments()) {
            merge(arg, var);
        }
    }

    private static void merge(InsnArg arg, LocalVar var) {
        if (arg != null && arg.isRegister()) {
            RegisterArg reg = (RegisterArg) arg;
            if (var.getRegNum() == reg.getRegNum()) {
                boolean mergeRequired = false;
                SSAVar ssaVar = reg.getSVar();
                if (ssaVar != null) {
                    int ssaEnd = ssaVar.getEndAddr();
                    int ssaStart = ssaVar.getStartAddr();
                    int localStart = var.getStartAddr();
                    int localEnd = var.getEndAddr();
                    boolean isIntersected = localEnd >= ssaStart && ssaEnd >= localStart;
                    if (isIntersected && ssaEnd <= localEnd) {
                        mergeRequired = true;
                    }
                } else {
                    mergeRequired = true;
                }
                if (mergeRequired) {
                    reg.mergeDebugInfo(var.getType(), var.getName());
                }
            }
        }
    }
}

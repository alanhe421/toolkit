package jadx.core.codegen;

import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.annotations.MethodParameters;
import jadx.core.dex.attributes.nodes.JadxErrorAttr;
import jadx.core.dex.info.AccessInfo;
import jadx.core.dex.instructions.InsnType;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.instructions.args.SSAVar;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.trycatch.CatchAttr;
import jadx.core.dex.visitors.DepthTraversal;
import jadx.core.dex.visitors.FallbackModeVisitor;
import jadx.core.utils.ErrorsCounter;
import jadx.core.utils.InsnUtils;
import jadx.core.utils.Utils;
import jadx.core.utils.exceptions.CodegenException;
import jadx.core.utils.exceptions.DecodeException;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MethodGen {
    private static final Logger LOG = LoggerFactory.getLogger(MethodGen.class);
    private final AnnotationGen annotationGen;
    private final ClassGen classGen;
    private final MethodNode mth;
    private final NameGen nameGen;

    public MethodGen(ClassGen classGen, MethodNode mth) {
        this.mth = mth;
        this.classGen = classGen;
        this.annotationGen = classGen.getAnnotationGen();
        this.nameGen = new NameGen(mth, classGen.isFallbackMode());
    }

    public ClassGen getClassGen() {
        return this.classGen;
    }

    public NameGen getNameGen() {
        return this.nameGen;
    }

    public MethodNode getMethodNode() {
        return this.mth;
    }

    public boolean addDefinition(CodeWriter code) {
        if (this.mth.getMethodInfo().isClassInit()) {
            code.attachDefinition(this.mth);
            code.startLine("static");
            return true;
        } else if (this.mth.contains(AFlag.ANONYMOUS_CONSTRUCTOR)) {
            code.startLine();
            code.attachDefinition(this.mth);
            return false;
        } else {
            this.annotationGen.addForMethod(code, this.mth);
            AccessInfo clsAccFlags = this.mth.getParentClass().getAccessFlags();
            AccessInfo ai = this.mth.getAccessFlags();
            if (clsAccFlags.isInterface()) {
                ai = ai.remove(1024).remove(1);
            }
            if (clsAccFlags.isAnnotation()) {
                ai = ai.remove(1);
            }
            code.startLineWithNum(this.mth.getSourceLine());
            code.add(ai.makeString());
            if (this.classGen.addGenericMap(code, this.mth.getGenericMap())) {
                code.add(' ');
            }
            if (this.mth.getAccessFlags().isConstructor()) {
                code.attachDefinition(this.mth);
                code.add(this.classGen.getClassNode().getShortName());
            } else {
                this.classGen.useType(code, this.mth.getReturnType());
                code.add(' ');
                code.attachDefinition(this.mth);
                code.add(this.mth.getAlias());
            }
            code.add('(');
            List<RegisterArg> args = this.mth.getArguments(false);
            if (this.mth.getMethodInfo().isConstructor() && this.mth.getParentClass().contains(AType.ENUM_CLASS)) {
                if (args.size() == 2) {
                    args.clear();
                } else if (args.size() > 2) {
                    args = args.subList(2, args.size());
                } else {
                    LOG.warn(ErrorsCounter.formatErrorMsg(this.mth, "Incorrect number of args for enum constructor: " + args.size() + " (expected >= 2)"));
                }
            }
            addMethodArguments(code, args);
            code.add(')');
            this.annotationGen.addThrows(this.mth, code);
            return true;
        }
    }

    private void addMethodArguments(CodeWriter argsCode, List<RegisterArg> args) {
        MethodParameters paramsAnnotation = (MethodParameters) this.mth.get(AType.ANNOTATION_MTH_PARAMETERS);
        int i = 0;
        Iterator<RegisterArg> it = args.iterator();
        while (it.hasNext()) {
            RegisterArg arg = (RegisterArg) it.next();
            if (paramsAnnotation != null) {
                this.annotationGen.addForParameter(argsCode, paramsAnnotation, i);
            }
            SSAVar argSVar = arg.getSVar();
            if (argSVar != null && argSVar.contains(AFlag.FINAL)) {
                argsCode.add("final ");
            }
            if (it.hasNext() || !this.mth.getAccessFlags().isVarArgs()) {
                this.classGen.useType(argsCode, arg.getType());
            } else {
                ArgType type = arg.getType();
                if (type.isArray()) {
                    this.classGen.useType(argsCode, type.getArrayElement());
                    argsCode.add("...");
                } else {
                    LOG.warn(ErrorsCounter.formatErrorMsg(this.mth, "Last argument in varargs method not array"));
                    this.classGen.useType(argsCode, arg.getType());
                }
            }
            argsCode.add(' ');
            argsCode.add(this.nameGen.assignArg(arg));
            i++;
            if (it.hasNext()) {
                argsCode.add(", ");
            }
        }
    }

    public void addInstructions(CodeWriter code) throws CodegenException {
        if (this.mth.contains(AType.JADX_ERROR) || this.mth.contains(AFlag.INCONSISTENT_CODE) || this.mth.getRegion() == null) {
            JadxErrorAttr err = (JadxErrorAttr) this.mth.get(AType.JADX_ERROR);
            if (err != null) {
                code.startLine("/* JADX: method processing error */");
                Throwable cause = err.getCause();
                if (cause != null) {
                    code.newLine();
                    code.add("/*");
                    code.newLine().add("Error: ").add(Utils.getStackTrace(cause));
                    code.add("*/");
                }
            }
            code.startLine("/*");
            addFallbackMethodCode(code);
            code.startLine("*/");
            code.startLine("throw new UnsupportedOperationException(\"Method not decompiled: ").add(this.mth.toString()).add("\");");
            return;
        }
        new RegionGen(this).makeRegion(code, this.mth.getRegion());
    }

    public void addFallbackMethodCode(CodeWriter code) {
        if (this.mth.getInstructions() == null) {
            JadxErrorAttr errorAttr = (JadxErrorAttr) this.mth.get(AType.JADX_ERROR);
            if (errorAttr == null || errorAttr.getCause() == null || !errorAttr.getCause().getClass().equals(DecodeException.class)) {
                try {
                    this.mth.load();
                    DepthTraversal.visit(new FallbackModeVisitor(), this.mth);
                } catch (DecodeException e) {
                    LOG.error("Error reload instructions in fallback mode:", e);
                    code.startLine("// Can't load method instructions: " + e.getMessage());
                    return;
                }
            }
        }
        InsnNode[] insnArr = this.mth.getInstructions();
        if (insnArr == null) {
            code.startLine("// Can't load method instructions.");
            return;
        }
        if (this.mth.getThisArg() != null) {
            code.startLine(this.nameGen.useArg(this.mth.getThisArg())).add(" = this;");
        }
        addFallbackInsns(code, this.mth, insnArr, true);
    }

    public static void addFallbackInsns(CodeWriter code, MethodNode mth, InsnNode[] insnArr, boolean addLabels) {
        InsnGen insnGen = new InsnGen(getFallbackMethodGen(mth), true);
        for (InsnNode insn : insnArr) {
            if (!(insn == null || insn.getType() == InsnType.NOP)) {
                if (addLabels && (insn.contains(AType.JUMP) || insn.contains(AType.EXC_HANDLER))) {
                    code.decIndent();
                    code.startLine(getLabelName(insn.getOffset()) + ":");
                    code.incIndent();
                }
                try {
                    if (insnGen.makeInsn(insn, code)) {
                        CatchAttr catchAttr = (CatchAttr) insn.get(AType.CATCH_BLOCK);
                        if (catchAttr != null) {
                            code.add("\t " + catchAttr);
                        }
                    }
                } catch (CodegenException e) {
                    LOG.debug("Error generate fallback instruction: ", e.getCause());
                    code.startLine("// error: " + insn);
                }
            }
        }
    }

    public static MethodGen getFallbackMethodGen(MethodNode mth) {
        return new MethodGen(new ClassGen(mth.getParentClass(), null, true, true), mth);
    }

    public static String getLabelName(int offset) {
        return "L_" + InsnUtils.formatOffset(offset);
    }
}

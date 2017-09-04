package jadx.core.codegen;

import jadx.api.IJadxArgs;
import jadx.core.deobf.Deobfuscator;
import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.AttrNode;
import jadx.core.dex.attributes.nodes.EnumClassAttr;
import jadx.core.dex.attributes.nodes.EnumClassAttr.EnumField;
import jadx.core.dex.attributes.nodes.SourceFileAttr;
import jadx.core.dex.info.AccessInfo;
import jadx.core.dex.info.ClassInfo;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.instructions.args.PrimitiveType;
import jadx.core.dex.instructions.mods.ConstructorInsn;
import jadx.core.dex.nodes.ClassNode;
import jadx.core.dex.nodes.DexNode;
import jadx.core.dex.nodes.FieldNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.nodes.parser.FieldInitAttr;
import jadx.core.dex.nodes.parser.FieldInitAttr.InitType;
import jadx.core.utils.ErrorsCounter;
import jadx.core.utils.Utils;
import jadx.core.utils.exceptions.CodegenException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassGen {
    private static final Logger LOG = LoggerFactory.getLogger(ClassGen.class);
    public static final Comparator<MethodNode> METHOD_LINE_COMPARATOR = new Comparator<MethodNode>() {
        public int compare(MethodNode a, MethodNode b) {
            return Utils.compare(a.getSourceLine(), b.getSourceLine());
        }
    };
    private final AnnotationGen annotationGen;
    private final ClassNode cls;
    private int clsDeclLine;
    private final boolean fallback;
    private final Set<ClassInfo> imports;
    private final ClassGen parentGen;
    private final boolean showInconsistentCode;

    public ClassGen(ClassNode cls, IJadxArgs jadxArgs) {
        this(cls, null, jadxArgs.isFallbackMode(), jadxArgs.isShowInconsistentCode());
    }

    public ClassGen(ClassNode cls, ClassGen parentClsGen) {
        this(cls, parentClsGen, parentClsGen.fallback, parentClsGen.showInconsistentCode);
    }

    public ClassGen(ClassNode cls, ClassGen parentClsGen, boolean fallback, boolean showBadCode) {
        this.imports = new HashSet();
        this.cls = cls;
        this.parentGen = parentClsGen;
        this.fallback = fallback;
        this.showInconsistentCode = showBadCode;
        this.annotationGen = new AnnotationGen(cls, this);
    }

    public ClassNode getClassNode() {
        return this.cls;
    }

    public CodeWriter makeClass() throws CodegenException {
        CodeWriter clsBody = new CodeWriter();
        addClassCode(clsBody);
        CodeWriter clsCode = new CodeWriter();
        if (!"".equals(this.cls.getPackage())) {
            clsCode.add("package ").add(this.cls.getPackage()).add(';');
            clsCode.newLine();
        }
        int importsCount = this.imports.size();
        if (importsCount != 0) {
            List<String> sortImports = new ArrayList(importsCount);
            for (ClassInfo ic : this.imports) {
                sortImports.add(ic.getAlias().getFullName());
            }
            Collections.sort(sortImports);
            for (String imp : sortImports) {
                clsCode.startLine("import ").add(imp).add(';');
            }
            clsCode.newLine();
            sortImports.clear();
            this.imports.clear();
        }
        clsCode.add(clsBody);
        return clsCode;
    }

    public void addClassCode(CodeWriter code) throws CodegenException {
        if (!this.cls.contains(AFlag.DONT_GENERATE)) {
            if (this.cls.contains(AFlag.INCONSISTENT_CODE)) {
                code.startLine("// jadx: inconsistent code");
            }
            addClassDeclaration(code);
            addClassBody(code);
        }
    }

    public void addClassDeclaration(CodeWriter clsCode) {
        AccessInfo af = this.cls.getAccessFlags();
        if (af.isInterface()) {
            af = af.remove(1024).remove(8);
        } else if (af.isEnum()) {
            af = af.remove(16).remove(1024).remove(8);
        }
        if (!this.cls.getAlias().isInner()) {
            af = af.remove(8).remove(2);
        }
        this.annotationGen.addForClass(clsCode);
        insertSourceFileInfo(clsCode, this.cls);
        insertRenameInfo(clsCode, this.cls);
        clsCode.startLine(af.makeString());
        if (af.isInterface()) {
            if (af.isAnnotation()) {
                clsCode.add('@');
            }
            clsCode.add("interface ");
        } else if (af.isEnum()) {
            clsCode.add("enum ");
        } else {
            clsCode.add("class ");
        }
        clsCode.attachDefinition(this.cls);
        clsCode.add(this.cls.getShortName());
        addGenericMap(clsCode, this.cls.getGenericMap());
        clsCode.add(' ');
        ArgType sup = this.cls.getSuperClass();
        if (!(sup == null || sup.equals(ArgType.OBJECT) || sup.getObject().equals(ArgType.ENUM.getObject()))) {
            clsCode.add("extends ");
            useClass(clsCode, sup);
            clsCode.add(' ');
        }
        if (!this.cls.getInterfaces().isEmpty() && !af.isAnnotation()) {
            if (this.cls.getAccessFlags().isInterface()) {
                clsCode.add("extends ");
            } else {
                clsCode.add("implements ");
            }
            Iterator<ArgType> it = this.cls.getInterfaces().iterator();
            while (it.hasNext()) {
                useClass(clsCode, (ArgType) it.next());
                if (it.hasNext()) {
                    clsCode.add(", ");
                }
            }
            if (!this.cls.getInterfaces().isEmpty()) {
                clsCode.add(' ');
            }
        }
    }

    public boolean addGenericMap(CodeWriter code, Map<ArgType, List<ArgType>> gmap) {
        if (gmap == null || gmap.isEmpty()) {
            return false;
        }
        code.add('<');
        int i = 0;
        for (Entry<ArgType, List<ArgType>> e : gmap.entrySet()) {
            ArgType type = (ArgType) e.getKey();
            List<ArgType> list = (List) e.getValue();
            if (i != 0) {
                code.add(", ");
            }
            if (type.isGenericType()) {
                code.add(type.getObject());
            } else {
                useClass(code, type);
            }
            if (!(list == null || list.isEmpty())) {
                code.add(" extends ");
                Iterator<ArgType> it = list.iterator();
                while (it.hasNext()) {
                    ArgType g = (ArgType) it.next();
                    if (g.isGenericType()) {
                        code.add(g.getObject());
                    } else {
                        useClass(code, g);
                    }
                    if (it.hasNext()) {
                        code.add(" & ");
                    }
                }
            }
            i++;
        }
        code.add('>');
        return true;
    }

    public void addClassBody(CodeWriter clsCode) throws CodegenException {
        clsCode.add('{');
        this.clsDeclLine = clsCode.getLine();
        clsCode.incIndent();
        addFields(clsCode);
        addInnerClasses(clsCode, this.cls);
        addMethods(clsCode);
        clsCode.decIndent();
        clsCode.startLine('}');
    }

    private void addInnerClasses(CodeWriter code, ClassNode cls) throws CodegenException {
        for (ClassNode innerCls : cls.getInnerClasses()) {
            if (!(innerCls.contains(AFlag.DONT_GENERATE) || innerCls.contains(AFlag.ANONYMOUS_CLASS))) {
                ClassGen inClGen = new ClassGen(innerCls, getParentGen());
                code.newLine();
                inClGen.addClassCode(code);
                this.imports.addAll(inClGen.getImports());
            }
        }
    }

    private boolean isInnerClassesPresents() {
        for (ClassNode innerCls : this.cls.getInnerClasses()) {
            if (!innerCls.contains(AFlag.ANONYMOUS_CLASS)) {
                return true;
            }
        }
        return false;
    }

    private void addMethods(CodeWriter code) {
        for (MethodNode mth : sortMethodsByLine(this.cls.getMethods())) {
            if (!mth.contains(AFlag.DONT_GENERATE)) {
                if (code.getLine() != this.clsDeclLine) {
                    code.newLine();
                }
                try {
                    addMethod(code, mth);
                } catch (Exception e) {
                    code.newLine().add("/*");
                    code.newLine().add(ErrorsCounter.methodError(mth, "Method generation error", e));
                    code.newLine().add(Utils.getStackTrace(e));
                    code.newLine().add("*/");
                }
            }
        }
    }

    private static List<MethodNode> sortMethodsByLine(List<MethodNode> methods) {
        List<MethodNode> out = new ArrayList(methods);
        Collections.sort(out, METHOD_LINE_COMPARATOR);
        return out;
    }

    private boolean isMethodsPresents() {
        for (MethodNode mth : this.cls.getMethods()) {
            if (!mth.contains(AFlag.DONT_GENERATE)) {
                return true;
            }
        }
        return false;
    }

    private void addMethod(CodeWriter code, MethodNode mth) throws CodegenException {
        if (mth.getAccessFlags().isAbstract() || mth.getAccessFlags().isNative()) {
            new MethodGen(this, mth).addDefinition(code);
            if (this.cls.getAccessFlags().isAnnotation()) {
                Object def = this.annotationGen.getAnnotationDefaultValue(mth.getName());
                if (def != null) {
                    code.add(" default ");
                    this.annotationGen.encodeValue(code, def);
                }
            }
            code.add(';');
            return;
        }
        MethodGen mthGen;
        boolean badCode = mth.contains(AFlag.INCONSISTENT_CODE);
        if (badCode) {
            code.startLine("/* JADX WARNING: inconsistent code. */");
            code.startLine("/* Code decompiled incorrectly, please refer to instructions dump. */");
            ErrorsCounter.methodError(mth, "Inconsistent code");
            if (this.showInconsistentCode) {
                mth.remove(AFlag.INCONSISTENT_CODE);
                badCode = false;
            }
        }
        if (badCode || mth.contains(AType.JADX_ERROR) || this.fallback) {
            mthGen = MethodGen.getFallbackMethodGen(mth);
        } else {
            mthGen = new MethodGen(this, mth);
        }
        if (mthGen.addDefinition(code)) {
            code.add(' ');
        }
        code.add('{');
        code.incIndent();
        insertSourceFileInfo(code, mth);
        if (this.fallback) {
            mthGen.addFallbackMethodCode(code);
        } else {
            mthGen.addInstructions(code);
        }
        code.decIndent();
        code.startLine('}');
    }

    private void addFields(CodeWriter code) throws CodegenException {
        addEnumFields(code);
        for (FieldNode f : this.cls.getFields()) {
            if (!f.contains(AFlag.DONT_GENERATE)) {
                this.annotationGen.addForField(code, f);
                code.startLine(f.getAccessFlags().makeString());
                useType(code, f.getType());
                code.add(' ');
                code.attachDefinition(f);
                code.add(f.getAlias());
                FieldInitAttr fv = (FieldInitAttr) f.get(AType.FIELD_INIT);
                if (fv != null) {
                    code.add(" = ");
                    if (fv.getValue() == null) {
                        code.add(TypeGen.literalToString(0, f.getType(), this.cls));
                    } else if (fv.getValueType() == InitType.CONST) {
                        this.annotationGen.encodeValue(code, fv.getValue());
                    } else if (fv.getValueType() == InitType.INSN) {
                        addInsnBody(makeInsnGen(fv.getInsnMth()), code, fv.getInsn());
                    }
                }
                code.add(';');
            }
        }
    }

    private boolean isFieldsPresents() {
        for (FieldNode field : this.cls.getFields()) {
            if (!field.contains(AFlag.DONT_GENERATE)) {
                return true;
            }
        }
        return false;
    }

    private void addEnumFields(CodeWriter code) throws CodegenException {
        EnumClassAttr enumFields = (EnumClassAttr) this.cls.get(AType.ENUM_CLASS);
        if (enumFields != null) {
            InsnGen igen = null;
            Iterator<EnumField> it = enumFields.getFields().iterator();
            while (it.hasNext()) {
                EnumField f = (EnumField) it.next();
                code.startLine(f.getField().getAlias());
                ConstructorInsn constrInsn = f.getConstrInsn();
                if (constrInsn.getArgsCount() > f.getStartArg()) {
                    if (igen == null) {
                        igen = makeInsnGen(enumFields.getStaticMethod());
                    }
                    igen.generateMethodArguments(code, constrInsn, f.getStartArg(), this.cls.dex().resolveMethod(constrInsn.getCallMth()));
                }
                if (f.getCls() != null) {
                    code.add(' ');
                    new ClassGen(f.getCls(), this).addClassBody(code);
                }
                if (it.hasNext()) {
                    code.add(',');
                }
            }
            if (isMethodsPresents() || isFieldsPresents() || isInnerClassesPresents()) {
                if (enumFields.getFields().isEmpty()) {
                    code.startLine();
                }
                code.add(';');
                if (isFieldsPresents()) {
                    code.startLine();
                }
            }
        }
    }

    private InsnGen makeInsnGen(MethodNode mth) {
        return new InsnGen(new MethodGen(this, mth), false);
    }

    private void addInsnBody(InsnGen insnGen, CodeWriter code, InsnNode insn) {
        try {
            insnGen.makeInsn(insn, code, Flags.BODY_ONLY_NOWRAP);
        } catch (Exception e) {
            ErrorsCounter.classError(this.cls, "Failed to generate init code", e);
        }
    }

    public void useType(CodeWriter code, ArgType type) {
        PrimitiveType stype = type.getPrimitiveType();
        if (stype == null) {
            code.add(type.toString());
        } else if (stype == PrimitiveType.OBJECT) {
            if (type.isGenericType()) {
                code.add(type.getObject());
            } else {
                useClass(code, type);
            }
        } else if (stype == PrimitiveType.ARRAY) {
            useType(code, type.getArrayElement());
            code.add("[]");
        } else {
            code.add(stype.getLongName());
        }
    }

    public void useClass(CodeWriter code, ArgType type) {
        useClass(code, ClassInfo.extCls(this.cls.dex(), type));
        ArgType[] generics = type.getGenericTypes();
        if (generics != null) {
            code.add('<');
            int len = generics.length;
            for (int i = 0; i < len; i++) {
                if (i != 0) {
                    code.add(", ");
                }
                ArgType gt = generics[i];
                ArgType wt = gt.getWildcardType();
                if (wt != null) {
                    code.add('?');
                    int bounds = gt.getWildcardBounds();
                    if (bounds != 0) {
                        code.add(bounds == -1 ? " super " : " extends ");
                        useType(code, wt);
                    }
                } else {
                    useType(code, gt);
                }
            }
            code.add('>');
        }
    }

    public void useClass(CodeWriter code, ClassInfo classInfo) {
        ClassNode classNode = this.cls.dex().resolveClass(classInfo);
        if (classNode != null) {
            code.attachAnnotation(classNode);
        }
        code.add(useClassInternal(this.cls.getAlias(), classInfo.getAlias()));
    }

    private String useClassInternal(ClassInfo useCls, ClassInfo extClsInfo) {
        String fullName = extClsInfo.getFullName();
        if (this.fallback) {
            return fullName;
        }
        String shortName = extClsInfo.getShortName();
        if ((extClsInfo.getPackage().equals("java.lang") && extClsInfo.getParentClass() == null) || isClassInnerFor(useCls, extClsInfo) || isBothClassesInOneTopClass(useCls, extClsInfo)) {
            return shortName;
        }
        if (extClsInfo.getPackage().equals(useCls.getPackage()) && !extClsInfo.isInner()) {
            return shortName;
        }
        ClassNode classNode = this.cls.dex().resolveClass(extClsInfo);
        if (classNode != null && !classNode.getAccessFlags().isPublic()) {
            return shortName;
        }
        if (searchCollision(this.cls.dex(), useCls, extClsInfo)) {
            return fullName;
        }
        if (extClsInfo.isDefaultPackage()) {
            return shortName;
        }
        if (extClsInfo.getPackage().equals(useCls.getPackage())) {
            fullName = extClsInfo.getNameWithoutPackage();
        }
        for (ClassInfo importCls : getImports()) {
            if (!importCls.equals(extClsInfo) && importCls.getShortName().equals(shortName)) {
                if (!extClsInfo.isInner()) {
                    return fullName;
                }
                return useClassInternal(useCls, extClsInfo.getParentClass().getAlias()) + Deobfuscator.CLASS_NAME_SEPARATOR + shortName;
            }
        }
        addImport(extClsInfo);
        return shortName;
    }

    private void addImport(ClassInfo classInfo) {
        if (this.parentGen != null) {
            this.parentGen.addImport(classInfo.getAlias());
        } else {
            this.imports.add(classInfo);
        }
    }

    private Set<ClassInfo> getImports() {
        if (this.parentGen != null) {
            return this.parentGen.getImports();
        }
        return this.imports;
    }

    private static boolean isBothClassesInOneTopClass(ClassInfo useCls, ClassInfo extClsInfo) {
        ClassInfo a = useCls.getTopParentClass();
        ClassInfo b = extClsInfo.getTopParentClass();
        if (a != null) {
            return a.equals(b);
        }
        return useCls.equals(b);
    }

    private static boolean isClassInnerFor(ClassInfo inner, ClassInfo parent) {
        if (!inner.isInner()) {
            return false;
        }
        ClassInfo p = inner.getParentClass();
        if (p.equals(parent) || isClassInnerFor(p, parent)) {
            return true;
        }
        return false;
    }

    private static boolean searchCollision(DexNode dex, ClassInfo useCls, ClassInfo searchCls) {
        if (useCls == null) {
            return false;
        }
        String shortName = searchCls.getShortName();
        if (useCls.getShortName().equals(shortName)) {
            return true;
        }
        ClassNode classNode = dex.resolveClass(useCls);
        if (classNode != null) {
            for (ClassNode inner : classNode.getInnerClasses()) {
                if (inner.getShortName().equals(shortName) && !inner.getAlias().equals(searchCls)) {
                    return true;
                }
            }
        }
        return searchCollision(dex, useCls.getParentClass(), searchCls);
    }

    private void insertSourceFileInfo(CodeWriter code, AttrNode node) {
        SourceFileAttr sourceFileAttr = (SourceFileAttr) node.get(AType.SOURCE_FILE);
        if (sourceFileAttr != null) {
            code.startLine("/* compiled from: ").add(sourceFileAttr.getFileName()).add(" */");
        }
    }

    private void insertRenameInfo(CodeWriter code, ClassNode cls) {
        ClassInfo classInfo = cls.getClassInfo();
        if (classInfo.isRenamed() && !cls.getShortName().equals(cls.getAlias().getShortName())) {
            code.startLine("/* renamed from: ").add(classInfo.getFullName()).add(" */");
        }
    }

    public ClassGen getParentGen() {
        return this.parentGen == null ? this : this.parentGen;
    }

    public AnnotationGen getAnnotationGen() {
        return this.annotationGen;
    }

    public boolean isFallbackMode() {
        return this.fallback;
    }
}

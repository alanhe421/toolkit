package jadx.core.deobf;

import jadx.api.IJadxArgs;
import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.nodes.SourceFileAttr;
import jadx.core.dex.info.ClassInfo;
import jadx.core.dex.info.FieldInfo;
import jadx.core.dex.info.MethodInfo;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.nodes.ClassNode;
import jadx.core.dex.nodes.DexNode;
import jadx.core.dex.nodes.FieldNode;
import jadx.core.dex.nodes.MethodNode;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Deobfuscator {
    public static final String CLASS_NAME_SEPARATOR = ".";
    private static final boolean DEBUG = false;
    public static final String INNER_CLASS_SEPARATOR = "$";
    private static final Logger LOG = LoggerFactory.getLogger(Deobfuscator.class);
    private final IJadxArgs args;
    private int clsIndex = 0;
    private final Map<ClassInfo, DeobfClsInfo> clsMap = new HashMap();
    private final DeobfPresets deobfPresets;
    @NotNull
    private final List<DexNode> dexNodes;
    private int fldIndex = 0;
    private final Map<FieldInfo, String> fldMap = new HashMap();
    private final int maxLength;
    private final int minLength;
    private int mthIndex = 0;
    private final Map<MethodInfo, String> mthMap = new HashMap();
    private final List<OverridedMethodsNode> ovrd = new ArrayList();
    private final Map<MethodInfo, OverridedMethodsNode> ovrdMap = new HashMap();
    private int pkgIndex = 0;
    private final Set<String> pkgSet = new TreeSet();
    private final PackageNode rootPackage = new PackageNode("");
    private final boolean useSourceNameAsAlias;

    public Deobfuscator(IJadxArgs args, @NotNull List<DexNode> dexNodes, File deobfMapFile) {
        this.args = args;
        this.dexNodes = dexNodes;
        this.minLength = args.getDeobfuscationMinLength();
        this.maxLength = args.getDeobfuscationMaxLength();
        this.useSourceNameAsAlias = args.useSourceNameAsClassAlias();
        this.deobfPresets = new DeobfPresets(this, deobfMapFile);
    }

    public void execute() {
        if (!this.args.isDeobfuscationForceSave()) {
            this.deobfPresets.load();
            initIndexes();
        }
        process();
        this.deobfPresets.save(this.args.isDeobfuscationForceSave());
        clear();
    }

    private void initIndexes() {
        this.pkgIndex = this.pkgSet.size();
        this.clsIndex = this.deobfPresets.getClsPresetMap().size();
        this.fldIndex = this.deobfPresets.getFldPresetMap().size();
        this.mthIndex = this.deobfPresets.getMthPresetMap().size();
    }

    private void preProcess() {
        for (DexNode dexNode : this.dexNodes) {
            for (ClassNode cls : dexNode.getClasses()) {
                doClass(cls);
            }
        }
    }

    private void process() {
        preProcess();
        for (DexNode dexNode : this.dexNodes) {
            for (ClassNode cls : dexNode.getClasses()) {
                processClass(dexNode, cls);
            }
        }
        postProcess();
    }

    private void postProcess() {
        int id = 1;
        for (OverridedMethodsNode o : this.ovrd) {
            Iterator<MethodInfo> it = o.getMethods().iterator();
            if (it.hasNext()) {
                MethodInfo mth = (MethodInfo) it.next();
                if (mth.isRenamed() && !mth.isAliasFromPreset()) {
                    mth.setAlias(String.format("mo%d%s", new Object[]{Integer.valueOf(id), makeName(mth.getName())}));
                }
                String firstMethodAlias = mth.getAlias();
                while (it.hasNext()) {
                    mth = (MethodInfo) it.next();
                    if (!mth.getAlias().equals(firstMethodAlias)) {
                        mth.setAlias(firstMethodAlias);
                    }
                }
            }
            id++;
        }
    }

    void clear() {
        this.deobfPresets.clear();
        this.clsMap.clear();
        this.fldMap.clear();
        this.mthMap.clear();
        this.ovrd.clear();
        this.ovrdMap.clear();
    }

    @Nullable
    private static ClassNode resolveOverridingInternal(DexNode dex, ClassNode cls, String signature, Set<MethodInfo> overrideSet, ClassNode rootClass) {
        ArgType superClass;
        ClassNode clsWithMth;
        ClassNode iFaceNode;
        ClassNode result = null;
        for (MethodNode m : cls.getMethods()) {
            ClassNode superNode;
            if (m.getMethodInfo().getShortId().startsWith(signature)) {
                result = cls;
                if (!overrideSet.contains(m.getMethodInfo())) {
                    overrideSet.add(m.getMethodInfo());
                }
                superClass = cls.getSuperClass();
                if (superClass != null) {
                    superNode = dex.resolveClass(superClass);
                    if (superNode != null) {
                        clsWithMth = resolveOverridingInternal(dex, superNode, signature, overrideSet, rootClass);
                        if (clsWithMth != null) {
                            if (result != null || result == cls) {
                                result = clsWithMth;
                            } else if (clsWithMth != result) {
                                LOG.warn(String.format("Multiple overriding '%s' from '%s' and '%s' in '%s'", new Object[]{signature, result.getFullName(), clsWithMth.getFullName(), rootClass.getFullName()}));
                            }
                        }
                    }
                }
                for (ArgType iFaceType : cls.getInterfaces()) {
                    iFaceNode = dex.resolveClass(iFaceType);
                    if (iFaceNode != null) {
                        clsWithMth = resolveOverridingInternal(dex, iFaceNode, signature, overrideSet, rootClass);
                        if (clsWithMth == null) {
                            if (result != null || result == cls) {
                                result = clsWithMth;
                            } else if (clsWithMth != result) {
                                LOG.warn(String.format("Multiple overriding '%s' from '%s' and '%s' in '%s'", new Object[]{signature, result.getFullName(), clsWithMth.getFullName(), rootClass.getFullName()}));
                            }
                        }
                    }
                }
                return result;
            }
        }
        superClass = cls.getSuperClass();
        if (superClass != null) {
            superNode = dex.resolveClass(superClass);
            if (superNode != null) {
                clsWithMth = resolveOverridingInternal(dex, superNode, signature, overrideSet, rootClass);
                if (clsWithMth != null) {
                    if (result != null) {
                    }
                    result = clsWithMth;
                }
            }
        }
        while (i$.hasNext()) {
            iFaceNode = dex.resolveClass(iFaceType);
            if (iFaceNode != null) {
                clsWithMth = resolveOverridingInternal(dex, iFaceNode, signature, overrideSet, rootClass);
                if (clsWithMth == null) {
                    if (result != null) {
                    }
                    result = clsWithMth;
                }
            }
        }
        return result;
    }

    private void resolveOverriding(DexNode dex, ClassNode cls, MethodNode mth) {
        Set<MethodInfo> overrideSet = new HashSet();
        resolveOverridingInternal(dex, cls, mth.getMethodInfo().makeSignature(false), overrideSet, cls);
        if (overrideSet.size() > 1) {
            OverridedMethodsNode overridedMethodsNode = null;
            for (MethodInfo _mth : overrideSet) {
                if (this.ovrdMap.containsKey(_mth)) {
                    overridedMethodsNode = (OverridedMethodsNode) this.ovrdMap.get(_mth);
                    break;
                }
            }
            if (overridedMethodsNode == null) {
                overridedMethodsNode = new OverridedMethodsNode(overrideSet);
                this.ovrd.add(overridedMethodsNode);
            }
            for (MethodInfo _mth2 : overrideSet) {
                if (!this.ovrdMap.containsKey(_mth2)) {
                    this.ovrdMap.put(_mth2, overridedMethodsNode);
                    if (!overridedMethodsNode.contains(_mth2)) {
                        overridedMethodsNode.add(_mth2);
                    }
                }
            }
            return;
        }
        overrideSet.clear();
    }

    private void processClass(DexNode dex, ClassNode cls) {
        ClassInfo clsInfo = cls.getClassInfo();
        String fullName = getClassFullName(clsInfo);
        if (!fullName.equals(clsInfo.getFullName())) {
            clsInfo.rename(dex, fullName);
        }
        for (FieldNode field : cls.getFields()) {
            FieldInfo fieldInfo = field.getFieldInfo();
            String alias = getFieldAlias(field);
            if (alias != null) {
                fieldInfo.setAlias(alias);
            }
        }
        for (MethodNode mth : cls.getMethods()) {
            MethodInfo methodInfo = mth.getMethodInfo();
            alias = getMethodAlias(mth);
            if (alias != null) {
                methodInfo.setAlias(alias);
            }
            if (mth.isVirtual()) {
                resolveOverriding(dex, cls, mth);
            }
        }
    }

    public void addPackagePreset(String origPkgName, String pkgAlias) {
        getPackageNode(origPkgName, true).setAlias(pkgAlias);
    }

    private PackageNode getPackageNode(String fullPkgName, boolean create) {
        if (fullPkgName.isEmpty() || fullPkgName.equals(CLASS_NAME_SEPARATOR)) {
            return this.rootPackage;
        }
        PackageNode result = this.rootPackage;
        do {
            String pkgName;
            int idx = fullPkgName.indexOf(CLASS_NAME_SEPARATOR);
            if (idx > -1) {
                pkgName = fullPkgName.substring(0, idx);
                fullPkgName = fullPkgName.substring(idx + 1);
            } else {
                pkgName = fullPkgName;
                fullPkgName = "";
            }
            PackageNode parentNode = result;
            result = result.getInnerPackageByName(pkgName);
            if (result == null && create) {
                result = new PackageNode(pkgName);
                parentNode.addInnerPackage(result);
            }
            if (fullPkgName.isEmpty()) {
                return result;
            }
        } while (result != null);
        return result;
    }

    String getNameWithoutPackage(ClassInfo clsInfo) {
        String prefix;
        ClassInfo parentClsInfo = clsInfo.getParentClass();
        if (parentClsInfo != null) {
            DeobfClsInfo parentDeobfClsInfo = (DeobfClsInfo) this.clsMap.get(parentClsInfo);
            if (parentDeobfClsInfo != null) {
                prefix = parentDeobfClsInfo.makeNameWithoutPkg();
            } else {
                prefix = getNameWithoutPackage(parentClsInfo);
            }
            prefix = prefix + INNER_CLASS_SEPARATOR;
        } else {
            prefix = "";
        }
        return prefix + clsInfo.getShortName();
    }

    private void doClass(ClassNode cls) {
        ClassInfo classInfo = cls.getClassInfo();
        String pkgFullName = classInfo.getPackage();
        PackageNode pkg = getPackageNode(pkgFullName, true);
        doPkg(pkg, pkgFullName);
        String alias = this.deobfPresets.getForCls(classInfo);
        if (alias != null) {
            this.clsMap.put(classInfo, new DeobfClsInfo(this, cls, pkg, alias));
        } else if (!this.clsMap.containsKey(classInfo) && shouldRename(classInfo.getShortName())) {
            makeClsAlias(cls);
        }
    }

    public String getClsAlias(ClassNode cls) {
        DeobfClsInfo deobfClsInfo = (DeobfClsInfo) this.clsMap.get(cls.getClassInfo());
        if (deobfClsInfo != null) {
            return deobfClsInfo.getAlias();
        }
        return makeClsAlias(cls);
    }

    private String makeClsAlias(ClassNode cls) {
        ClassInfo classInfo = cls.getClassInfo();
        String alias = null;
        if (this.useSourceNameAsAlias) {
            alias = getAliasFromSourceFile(cls);
        }
        if (alias == null) {
            String clsName = classInfo.getShortName();
            Object[] objArr = new Object[2];
            int i = this.clsIndex;
            this.clsIndex = i + 1;
            objArr[0] = Integer.valueOf(i);
            objArr[1] = makeName(clsName);
            alias = String.format("C%04d%s", objArr);
        }
        this.clsMap.put(classInfo, new DeobfClsInfo(this, cls, getPackageNode(classInfo.getPackage(), true), alias));
        return alias;
    }

    @Nullable
    private String getAliasFromSourceFile(ClassNode cls) {
        SourceFileAttr sourceFileAttr = (SourceFileAttr) cls.get(AType.SOURCE_FILE);
        if (sourceFileAttr == null) {
            return null;
        }
        String name = sourceFileAttr.getFileName();
        if (name.endsWith(".java")) {
            name = name.substring(0, name.length() - ".java".length());
        }
        if (!NameMapper.isValidIdentifier(name) || NameMapper.isReserved(name)) {
            return null;
        }
        cls.remove(AType.SOURCE_FILE);
        return name;
    }

    @Nullable
    public String getFieldAlias(FieldNode field) {
        FieldInfo fieldInfo = field.getFieldInfo();
        String alias = (String) this.fldMap.get(fieldInfo);
        if (alias != null) {
            return alias;
        }
        alias = this.deobfPresets.getForFld(fieldInfo);
        if (alias != null) {
            this.fldMap.put(fieldInfo, alias);
            return alias;
        } else if (shouldRename(field.getName())) {
            return makeFieldAlias(field);
        } else {
            return null;
        }
    }

    @Nullable
    public String getMethodAlias(MethodNode mth) {
        MethodInfo methodInfo = mth.getMethodInfo();
        String alias = (String) this.mthMap.get(methodInfo);
        if (alias != null) {
            return alias;
        }
        alias = this.deobfPresets.getForMth(methodInfo);
        if (alias != null) {
            this.mthMap.put(methodInfo, alias);
            methodInfo.setAliasFromPreset(true);
            return alias;
        } else if (shouldRename(mth.getName())) {
            return makeMethodAlias(mth);
        } else {
            return null;
        }
    }

    public String makeFieldAlias(FieldNode field) {
        Object[] objArr = new Object[2];
        int i = this.fldIndex;
        this.fldIndex = i + 1;
        objArr[0] = Integer.valueOf(i);
        objArr[1] = makeName(field.getName());
        String alias = String.format("f%d%s", objArr);
        this.fldMap.put(field.getFieldInfo(), alias);
        return alias;
    }

    public String makeMethodAlias(MethodNode mth) {
        Object[] objArr = new Object[2];
        int i = this.mthIndex;
        this.mthIndex = i + 1;
        objArr[0] = Integer.valueOf(i);
        objArr[1] = makeName(mth.getName());
        String alias = String.format("m%d%s", objArr);
        this.mthMap.put(mth.getMethodInfo(), alias);
        return alias;
    }

    private void doPkg(PackageNode pkg, String fullName) {
        if (!this.pkgSet.contains(fullName)) {
            this.pkgSet.add(fullName);
            for (PackageNode parentPkg = pkg.getParentPackage(); !parentPkg.getName().isEmpty(); parentPkg = parentPkg.getParentPackage()) {
                if (!parentPkg.hasAlias()) {
                    doPkg(parentPkg, parentPkg.getFullName());
                }
            }
            String pkgName = pkg.getName();
            if (!pkg.hasAlias() && shouldRename(pkgName)) {
                Object[] objArr = new Object[2];
                int i = this.pkgIndex;
                this.pkgIndex = i + 1;
                objArr[0] = Integer.valueOf(i);
                objArr[1] = makeName(pkgName);
                pkg.setAlias(String.format("p%03d%s", objArr));
            }
        }
    }

    private boolean shouldRename(String s) {
        return s.length() > this.maxLength || s.length() < this.minLength || NameMapper.isReserved(s) || !NameMapper.isAllCharsPrintable(s);
    }

    private String makeName(String name) {
        if (name.length() > this.maxLength) {
            return "x" + Integer.toHexString(name.hashCode());
        }
        if (NameMapper.isReserved(name) || NameMapper.isAllCharsPrintable(name)) {
            return name;
        }
        return removeInvalidChars(name);
    }

    private String removeInvalidChars(String name) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            int ch = name.charAt(i);
            if (NameMapper.isPrintableChar(ch)) {
                sb.append((char) ch);
            }
        }
        return sb.toString();
    }

    private void dumpClassAlias(ClassNode cls) {
        if (getPackageNode(cls.getPackage(), false) == null) {
            LOG.error("Can't find package node for '{}'", cls.getPackage());
        } else if (!cls.getFullName().equals(getClassFullName(cls))) {
            LOG.info("Alias name for class '{}' is '{}'", cls.getFullName(), getClassFullName(cls));
        }
    }

    private void dumpAlias() {
        for (DexNode dexNode : this.dexNodes) {
            for (ClassNode cls : dexNode.getClasses()) {
                dumpClassAlias(cls);
            }
        }
    }

    private String getPackageName(String packageName) {
        PackageNode pkg = getPackageNode(packageName, false);
        if (pkg != null) {
            return pkg.getFullAlias();
        }
        return packageName;
    }

    private String getClassName(ClassInfo clsInfo) {
        DeobfClsInfo deobfClsInfo = (DeobfClsInfo) this.clsMap.get(clsInfo);
        if (deobfClsInfo != null) {
            return deobfClsInfo.makeNameWithoutPkg();
        }
        return getNameWithoutPackage(clsInfo);
    }

    private String getClassFullName(ClassNode cls) {
        return getClassFullName(cls.getClassInfo());
    }

    private String getClassFullName(ClassInfo clsInfo) {
        DeobfClsInfo deobfClsInfo = (DeobfClsInfo) this.clsMap.get(clsInfo);
        if (deobfClsInfo != null) {
            return deobfClsInfo.getFullName();
        }
        return getPackageName(clsInfo.getPackage()) + CLASS_NAME_SEPARATOR + getClassName(clsInfo);
    }

    public Map<ClassInfo, DeobfClsInfo> getClsMap() {
        return this.clsMap;
    }

    public Map<FieldInfo, String> getFldMap() {
        return this.fldMap;
    }

    public Map<MethodInfo, String> getMthMap() {
        return this.mthMap;
    }

    public PackageNode getRootPackage() {
        return this.rootPackage;
    }
}

package jadx.core.deobf;

import jadx.core.dex.nodes.ClassNode;

class DeobfClsInfo {
    private final String alias;
    private final ClassNode cls;
    private final Deobfuscator deobfuscator;
    private final PackageNode pkg;

    public DeobfClsInfo(Deobfuscator deobfuscator, ClassNode cls, PackageNode pkg, String alias) {
        this.deobfuscator = deobfuscator;
        this.cls = cls;
        this.pkg = pkg;
        this.alias = alias;
    }

    public String makeNameWithoutPkg() {
        String prefix;
        String str;
        ClassNode parentClass = this.cls.getParentClass();
        if (parentClass != this.cls) {
            DeobfClsInfo parentDeobfClsInfo = (DeobfClsInfo) this.deobfuscator.getClsMap().get(parentClass.getClassInfo());
            if (parentDeobfClsInfo != null) {
                prefix = parentDeobfClsInfo.makeNameWithoutPkg();
            } else {
                prefix = this.deobfuscator.getNameWithoutPackage(parentClass.getClassInfo());
            }
            prefix = prefix + Deobfuscator.INNER_CLASS_SEPARATOR;
        } else {
            prefix = "";
        }
        StringBuilder append = new StringBuilder().append(prefix);
        if (this.alias != null) {
            str = this.alias;
        } else {
            str = this.cls.getShortName();
        }
        return append.append(str).toString();
    }

    public String getFullName() {
        return this.pkg.getFullAlias() + Deobfuscator.CLASS_NAME_SEPARATOR + makeNameWithoutPkg();
    }

    public ClassNode getCls() {
        return this.cls;
    }

    public PackageNode getPkg() {
        return this.pkg;
    }

    public String getAlias() {
        return this.alias;
    }
}

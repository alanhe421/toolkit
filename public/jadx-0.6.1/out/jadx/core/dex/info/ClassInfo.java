package jadx.core.dex.info;

import jadx.core.deobf.Deobfuscator;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.nodes.DexNode;
import jadx.core.utils.exceptions.JadxRuntimeException;
import java.io.File;

public final class ClassInfo {
    private ClassInfo alias;
    private String fullName;
    private String name;
    private ClassInfo parentClass;
    private String pkg;
    private final ArgType type;

    private ClassInfo(DexNode dex, ArgType type) {
        this(dex, type, true);
    }

    private ClassInfo(DexNode dex, ArgType type, boolean inner) {
        if (!type.isObject() || type.isGeneric()) {
            throw new JadxRuntimeException("Not class type: " + type);
        }
        this.type = type;
        this.alias = this;
        splitNames(dex, inner);
    }

    public static ClassInfo fromType(DexNode dex, ArgType type) {
        if (type.isArray()) {
            type = ArgType.OBJECT;
        }
        ClassInfo cls = dex.getInfoStorage().getCls(type);
        if (cls != null) {
            return cls;
        }
        return dex.getInfoStorage().putCls(new ClassInfo(dex, type));
    }

    public static ClassInfo fromDex(DexNode dex, int clsIndex) {
        if (clsIndex == -1) {
            return null;
        }
        return fromType(dex, dex.getType(clsIndex));
    }

    public static ClassInfo fromName(DexNode dex, String clsName) {
        return fromType(dex, ArgType.object(clsName));
    }

    public static ClassInfo extCls(DexNode dex, ArgType type) {
        return fromName(dex, type.getObject()).alias;
    }

    public void rename(DexNode dex, String fullName) {
        ClassInfo newAlias = new ClassInfo(dex, ArgType.object(fullName), isInner());
        if (!this.alias.getFullName().equals(newAlias.getFullName())) {
            this.alias = newAlias;
        }
    }

    public boolean isRenamed() {
        return this.alias != this;
    }

    public ClassInfo getAlias() {
        return this.alias;
    }

    private void splitNames(DexNode dex, boolean canBeInner) {
        String clsName;
        String fullObjectName = this.type.getObject();
        int dot = fullObjectName.lastIndexOf(46);
        if (dot == -1) {
            this.pkg = "";
            clsName = fullObjectName;
        } else {
            this.pkg = fullObjectName.substring(0, dot);
            clsName = fullObjectName.substring(dot + 1);
        }
        int sep = clsName.lastIndexOf(36);
        if (!canBeInner || sep <= 0 || sep == clsName.length() - 1) {
            this.parentClass = null;
        } else {
            this.parentClass = fromName(dex, this.pkg + Deobfuscator.CLASS_NAME_SEPARATOR + clsName.substring(0, sep));
            clsName = clsName.substring(sep + 1);
        }
        this.name = clsName;
        this.fullName = makeFullClsName(clsName, false);
    }

    public String makeFullClsName(String shortName, boolean raw) {
        if (this.parentClass == null) {
            return !this.pkg.isEmpty() ? this.pkg + Deobfuscator.CLASS_NAME_SEPARATOR + shortName : shortName;
        } else {
            return this.parentClass.makeFullClsName(this.parentClass.getShortName(), raw) + (raw ? Deobfuscator.INNER_CLASS_SEPARATOR : Deobfuscator.CLASS_NAME_SEPARATOR) + shortName;
        }
    }

    public String getFullPath() {
        ClassInfo alias = getAlias();
        return alias.getPackage().replace('.', File.separatorChar) + File.separatorChar + alias.getNameWithoutPackage().replace('.', '_');
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getShortName() {
        return this.name;
    }

    public String getPackage() {
        return this.pkg;
    }

    public boolean isDefaultPackage() {
        return this.pkg.isEmpty();
    }

    public String getRawName() {
        return this.type.getObject();
    }

    public String getNameWithoutPackage() {
        if (this.parentClass == null) {
            return this.name;
        }
        return this.parentClass.getNameWithoutPackage() + Deobfuscator.CLASS_NAME_SEPARATOR + this.name;
    }

    public ClassInfo getParentClass() {
        return this.parentClass;
    }

    public ClassInfo getTopParentClass() {
        if (this.parentClass == null) {
            return null;
        }
        ClassInfo topCls = this.parentClass.getTopParentClass();
        if (topCls != null) {
            return topCls;
        }
        return this.parentClass;
    }

    public boolean isInner() {
        return this.parentClass != null;
    }

    public void notInner(DexNode dex) {
        splitNames(dex, false);
    }

    public ArgType getType() {
        return this.type;
    }

    public String toString() {
        return this.fullName;
    }

    public int hashCode() {
        return this.type.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClassInfo)) {
            return false;
        }
        return this.type.equals(((ClassInfo) obj).type);
    }
}

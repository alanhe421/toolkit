package jadx.core.dex.info;

import com.android.dex.FieldId;
import jadx.core.codegen.TypeGen;
import jadx.core.deobf.Deobfuscator;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.nodes.DexNode;

public final class FieldInfo {
    private String alias;
    private final ClassInfo declClass;
    private final String name;
    private final ArgType type;

    private FieldInfo(ClassInfo declClass, String name, ArgType type) {
        this.declClass = declClass;
        this.name = name;
        this.type = type;
        this.alias = name;
    }

    public static FieldInfo from(DexNode dex, ClassInfo declClass, String name, ArgType type) {
        return dex.getInfoStorage().getField(new FieldInfo(declClass, name, type));
    }

    public static FieldInfo fromDex(DexNode dex, int index) {
        FieldId field = dex.getFieldId(index);
        return from(dex, ClassInfo.fromDex(dex, field.getDeclaringClassIndex()), dex.getString(field.getNameIndex()), dex.getType(field.getTypeIndex()));
    }

    public String getName() {
        return this.name;
    }

    public ArgType getType() {
        return this.type;
    }

    public ClassInfo getDeclClass() {
        return this.declClass;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFullId() {
        return this.declClass.getFullName() + Deobfuscator.CLASS_NAME_SEPARATOR + this.name + ":" + TypeGen.signature(this.type);
    }

    public boolean isRenamed() {
        return !this.name.equals(this.alias);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FieldInfo fieldInfo = (FieldInfo) o;
        if (this.name.equals(fieldInfo.name) && this.type.equals(fieldInfo.type) && this.declClass.equals(fieldInfo.declClass)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.type.hashCode()) * 31) + this.declClass.hashCode();
    }

    public String toString() {
        return this.declClass + Deobfuscator.CLASS_NAME_SEPARATOR + this.name + " " + this.type;
    }
}

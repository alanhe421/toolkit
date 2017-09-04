package jadx.core.dex.nodes;

import com.android.dex.ClassData.Field;
import jadx.core.deobf.Deobfuscator;
import jadx.core.dex.attributes.nodes.LineAttrNode;
import jadx.core.dex.info.AccessInfo;
import jadx.core.dex.info.AccessInfo.AFType;
import jadx.core.dex.info.FieldInfo;
import jadx.core.dex.instructions.args.ArgType;

public class FieldNode extends LineAttrNode {
    private final AccessInfo accFlags;
    private final FieldInfo fieldInfo;
    private final ClassNode parent;
    private ArgType type;

    public FieldNode(ClassNode cls, Field field) {
        this(cls, FieldInfo.fromDex(cls.dex(), field.getFieldIndex()), field.getAccessFlags());
    }

    public FieldNode(ClassNode cls, FieldInfo fieldInfo, int accessFlags) {
        this.parent = cls;
        this.fieldInfo = fieldInfo;
        this.type = fieldInfo.getType();
        this.accFlags = new AccessInfo(accessFlags, AFType.FIELD);
    }

    public FieldInfo getFieldInfo() {
        return this.fieldInfo;
    }

    public AccessInfo getAccessFlags() {
        return this.accFlags;
    }

    public String getName() {
        return this.fieldInfo.getName();
    }

    public String getAlias() {
        return this.fieldInfo.getAlias();
    }

    public ArgType getType() {
        return this.type;
    }

    public void setType(ArgType type) {
        this.type = type;
    }

    public ClassNode getParentClass() {
        return this.parent;
    }

    public int hashCode() {
        return this.fieldInfo.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.fieldInfo.equals(((FieldNode) obj).fieldInfo);
    }

    public String toString() {
        return this.fieldInfo.getDeclClass() + Deobfuscator.CLASS_NAME_SEPARATOR + this.fieldInfo.getName() + " :" + this.type;
    }
}

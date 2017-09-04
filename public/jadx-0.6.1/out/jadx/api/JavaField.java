package jadx.api;

import jadx.core.deobf.Deobfuscator;
import jadx.core.dex.info.AccessInfo;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.nodes.FieldNode;

public final class JavaField implements JavaNode {
    private final FieldNode field;
    private final JavaClass parent;

    JavaField(FieldNode f, JavaClass cls) {
        this.field = f;
        this.parent = cls;
    }

    public String getName() {
        return this.field.getAlias();
    }

    public String getFullName() {
        return this.parent.getFullName() + Deobfuscator.CLASS_NAME_SEPARATOR + getName();
    }

    public JavaClass getDeclaringClass() {
        return this.parent;
    }

    public JavaClass getTopParentClass() {
        return this.parent.getTopParentClass();
    }

    public AccessInfo getAccessFlags() {
        return this.field.getAccessFlags();
    }

    public ArgType getType() {
        return this.field.getType();
    }

    public int getDecompiledLine() {
        return this.field.getDecompiledLine();
    }

    public int hashCode() {
        return this.field.hashCode();
    }

    public boolean equals(Object o) {
        return this == o || ((o instanceof JavaField) && this.field.equals(((JavaField) o).field));
    }

    public String toString() {
        return this.field.toString();
    }
}

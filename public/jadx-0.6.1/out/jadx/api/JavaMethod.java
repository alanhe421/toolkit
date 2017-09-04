package jadx.api;

import jadx.core.dex.info.AccessInfo;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.nodes.MethodNode;
import java.util.List;

public final class JavaMethod implements JavaNode {
    private final MethodNode mth;
    private final JavaClass parent;

    JavaMethod(JavaClass cls, MethodNode m) {
        this.parent = cls;
        this.mth = m;
    }

    public String getName() {
        return this.mth.getAlias();
    }

    public String getFullName() {
        return this.mth.getMethodInfo().getFullName();
    }

    public JavaClass getDeclaringClass() {
        return this.parent;
    }

    public JavaClass getTopParentClass() {
        return this.parent.getTopParentClass();
    }

    public AccessInfo getAccessFlags() {
        return this.mth.getAccessFlags();
    }

    public List<ArgType> getArguments() {
        return this.mth.getMethodInfo().getArgumentsTypes();
    }

    public ArgType getReturnType() {
        return this.mth.getReturnType();
    }

    public boolean isConstructor() {
        return this.mth.getMethodInfo().isConstructor();
    }

    public boolean isClassInit() {
        return this.mth.getMethodInfo().isClassInit();
    }

    public int getDecompiledLine() {
        return this.mth.getDecompiledLine();
    }

    public int hashCode() {
        return this.mth.hashCode();
    }

    public boolean equals(Object o) {
        return this == o || ((o instanceof JavaMethod) && this.mth.equals(((JavaMethod) o).mth));
    }

    public String toString() {
        return this.mth.toString();
    }
}

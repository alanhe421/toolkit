package jadx.core.deobf;

import jadx.core.dex.info.MethodInfo;
import java.util.Set;

class OverridedMethodsNode {
    private Set<MethodInfo> methods;

    public OverridedMethodsNode(Set<MethodInfo> methodsSet) {
        this.methods = methodsSet;
    }

    public boolean contains(MethodInfo mth) {
        return this.methods.contains(mth);
    }

    public void add(MethodInfo mth) {
        this.methods.add(mth);
    }

    public Set<MethodInfo> getMethods() {
        return this.methods;
    }
}

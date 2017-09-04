package jadx.api;

public interface JavaNode {
    JavaClass getDeclaringClass();

    int getDecompiledLine();

    String getFullName();

    String getName();

    JavaClass getTopParentClass();
}

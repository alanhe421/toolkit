package jadx.api;

import java.util.List;
import org.jetbrains.annotations.NotNull;

public final class JavaPackage implements JavaNode, Comparable<JavaPackage> {
    private final List<JavaClass> classes;
    private final String name;

    JavaPackage(String name, List<JavaClass> classes) {
        this.name = name;
        this.classes = classes;
    }

    public String getName() {
        return this.name;
    }

    public String getFullName() {
        return this.name;
    }

    public List<JavaClass> getClasses() {
        return this.classes;
    }

    public JavaClass getDeclaringClass() {
        return null;
    }

    public JavaClass getTopParentClass() {
        return null;
    }

    public int getDecompiledLine() {
        return 0;
    }

    public int compareTo(@NotNull JavaPackage o) {
        return this.name.compareTo(o.name);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.name.equals(((JavaPackage) o).name);
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        return this.name;
    }
}

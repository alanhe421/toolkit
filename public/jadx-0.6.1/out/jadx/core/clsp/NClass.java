package jadx.core.clsp;

public class NClass {
    private int id;
    private final String name;
    private NClass[] parents;

    public NClass(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NClass[] getParents() {
        return this.parents;
    }

    public void setParents(NClass[] parents) {
        this.parents = parents;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.name.equals(((NClass) o).name);
    }

    public String toString() {
        return this.name;
    }
}

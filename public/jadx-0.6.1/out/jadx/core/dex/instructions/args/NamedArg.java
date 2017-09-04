package jadx.core.dex.instructions.args;

import org.jetbrains.annotations.NotNull;

public final class NamedArg extends InsnArg implements Named {
    @NotNull
    private String name;

    public NamedArg(@NotNull String name, @NotNull ArgType type) {
        this.name = name;
        this.type = type;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    public boolean isNamed() {
        return true;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof NamedArg) {
            return this.name.equals(((NamedArg) o).name);
        }
        return false;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        return "(" + this.name + " " + this.type + ")";
    }
}

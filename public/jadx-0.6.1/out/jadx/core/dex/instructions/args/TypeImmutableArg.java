package jadx.core.dex.instructions.args;

import org.jetbrains.annotations.NotNull;

public class TypeImmutableArg extends RegisterArg {
    private boolean isThis;

    public TypeImmutableArg(int rn, ArgType type) {
        super(rn, type);
    }

    public boolean isTypeImmutable() {
        return true;
    }

    public void setType(ArgType type) {
    }

    public void markAsThis() {
        this.isThis = true;
    }

    public boolean isThis() {
        return this.isThis;
    }

    public String getName() {
        if (this.isThis) {
            return "this";
        }
        return super.getName();
    }

    void setSVar(@NotNull SSAVar sVar) {
        if (this.isThis) {
            sVar.setName("this");
        }
        sVar.setTypeImmutable(this.type);
        super.setSVar(sVar);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TypeImmutableArg)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (this.isThis != ((TypeImmutableArg) obj).isThis) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.isThis ? 1 : 0) + (super.hashCode() * 31);
    }
}

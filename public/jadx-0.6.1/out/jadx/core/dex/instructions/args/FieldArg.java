package jadx.core.dex.instructions.args;

import jadx.core.dex.info.FieldInfo;
import org.jetbrains.annotations.Nullable;

public final class FieldArg extends RegisterArg {
    private final FieldInfo field;
    @Nullable
    private final InsnArg instArg;

    public FieldArg(FieldInfo field, @Nullable InsnArg reg) {
        super(-1);
        this.instArg = reg;
        this.field = field;
    }

    public FieldInfo getField() {
        return this.field;
    }

    public InsnArg getInstanceArg() {
        return this.instArg;
    }

    public boolean isStatic() {
        return this.instArg == null;
    }

    public boolean isField() {
        return true;
    }

    public boolean isRegister() {
        return false;
    }

    public void setType(ArgType type) {
        this.type = type;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FieldArg) || !super.equals(obj)) {
            return false;
        }
        FieldArg fieldArg = (FieldArg) obj;
        if (!this.field.equals(fieldArg.field)) {
            return false;
        }
        if (this.instArg != null) {
            if (this.instArg.equals(fieldArg.instArg)) {
                return true;
            }
        } else if (fieldArg.instArg == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((super.hashCode() * 31) + this.field.hashCode()) * 31) + (this.instArg != null ? this.instArg.hashCode() : 0);
    }

    public String toString() {
        return "(" + this.field + ")";
    }
}

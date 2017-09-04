package jadx.core.dex.instructions.args;

import jadx.core.codegen.TypeGen;
import jadx.core.utils.exceptions.JadxRuntimeException;

public final class LiteralArg extends InsnArg {
    public static final LiteralArg FALSE = new LiteralArg(0, ArgType.BOOLEAN);
    public static final LiteralArg TRUE = new LiteralArg(1, ArgType.BOOLEAN);
    private final long literal;

    public LiteralArg(long value, ArgType type) {
        if (value != 0) {
            if (type.isObject()) {
                throw new JadxRuntimeException("Wrong literal type: " + type + " for value: " + value);
            } else if (!(type.isTypeKnown() || type.contains(PrimitiveType.LONG) || type.contains(PrimitiveType.DOUBLE))) {
                ArgType m = ArgType.merge(null, type, ArgType.NARROW_NUMBERS);
                if (m != null) {
                    type = m;
                }
            }
        }
        this.literal = value;
        this.type = type;
    }

    public long getLiteral() {
        return this.literal;
    }

    public boolean isLiteral() {
        return true;
    }

    public boolean isInteger() {
        PrimitiveType type = this.type.getPrimitiveType();
        return type == PrimitiveType.INT || type == PrimitiveType.BYTE || type == PrimitiveType.CHAR || type == PrimitiveType.SHORT || type == PrimitiveType.LONG;
    }

    public int hashCode() {
        return ((int) (this.literal ^ (this.literal >>> 32))) + (getType().hashCode() * 31);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LiteralArg that = (LiteralArg) o;
        if (this.literal == that.literal && getType().equals(that.getType())) {
            return true;
        }
        return false;
    }

    public String toString() {
        try {
            String value = TypeGen.literalToString(this.literal, getType());
            if (getType().equals(ArgType.BOOLEAN) && (value.equals("true") || value.equals("false"))) {
                return value;
            }
            return "(" + value + " " + this.type + ")";
        } catch (JadxRuntimeException e) {
            return "(" + this.literal + " " + this.type + ")";
        }
    }
}

package jadx.core.xmlgen.entry;

public class RawNamedValue {
    private final int nameRef;
    private final RawValue rawValue;

    public RawNamedValue(int nameRef, RawValue rawValue) {
        this.nameRef = nameRef;
        this.rawValue = rawValue;
    }

    public int getNameRef() {
        return this.nameRef;
    }

    public RawValue getRawValue() {
        return this.rawValue;
    }

    public String toString() {
        return "RawNamedValue{nameRef=" + this.nameRef + ", rawValue=" + this.rawValue + '}';
    }
}

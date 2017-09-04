package jadx.core.xmlgen.entry;

public final class RawValue {
    private final int data;
    private final int dataType;

    public RawValue(int dataType, int data) {
        this.dataType = dataType;
        this.data = data;
    }

    public int getDataType() {
        return this.dataType;
    }

    public int getData() {
        return this.data;
    }

    public String toString() {
        return "RawValue: type=0x" + Integer.toHexString(this.dataType) + ", value=" + this.data;
    }
}

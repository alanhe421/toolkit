package com.tencent.mobileqq.pb;

import java.io.IOException;

public final class PBDoubleField extends PBPrimitiveField<Double> {
    public static final PBDoubleField __repeatHelper__ = new PBDoubleField(0.0d, false);
    private double value = 0.0d;

    public PBDoubleField(double d, boolean z) {
        set(d, z);
    }

    public double get() {
        return this.value;
    }

    public void set(double d, boolean z) {
        this.value = d;
        setHasFlag(z);
    }

    public void set(double d) {
        set(d, true);
    }

    public int computeSize(int i) {
        if (has()) {
            return CodedOutputStreamMicro.computeDoubleSize(i, this.value);
        }
        return 0;
    }

    protected int computeSizeDirectly(int i, Double d) {
        return CodedOutputStreamMicro.computeDoubleSize(i, d.doubleValue());
    }

    public void writeTo(CodedOutputStreamMicro codedOutputStreamMicro, int i) throws IOException {
        if (has()) {
            codedOutputStreamMicro.writeDouble(i, this.value);
        }
    }

    protected void writeToDirectly(CodedOutputStreamMicro codedOutputStreamMicro, int i, Double d) throws IOException {
        codedOutputStreamMicro.writeDouble(i, d.doubleValue());
    }

    public void readFrom(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        this.value = codedInputStreamMicro.readDouble();
        setHasFlag(true);
    }

    protected Double readFromDirectly(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        return Double.valueOf(codedInputStreamMicro.readDouble());
    }

    public void clear(Object obj) {
        if (obj instanceof Double) {
            this.value = ((Double) obj).doubleValue();
        } else {
            this.value = 0.0d;
        }
        setHasFlag(false);
    }

    protected void copyFrom(PBField<Double> pBField) {
        PBDoubleField pBDoubleField = (PBDoubleField) pBField;
        set(pBDoubleField.value, pBDoubleField.has());
    }
}

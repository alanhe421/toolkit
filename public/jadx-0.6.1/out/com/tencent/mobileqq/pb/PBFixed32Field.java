package com.tencent.mobileqq.pb;

import java.io.IOException;

public final class PBFixed32Field extends PBPrimitiveField<Integer> {
    public static final PBFixed32Field __repeatHelper__ = new PBFixed32Field(0, false);
    private int value = 0;

    public PBFixed32Field(int i, boolean z) {
        set(i, z);
    }

    public int get() {
        return this.value;
    }

    public void set(int i, boolean z) {
        this.value = i;
        setHasFlag(z);
    }

    public void set(int i) {
        set(i, true);
    }

    public int computeSize(int i) {
        if (has()) {
            return CodedOutputStreamMicro.computeFixed32Size(i, this.value);
        }
        return 0;
    }

    protected int computeSizeDirectly(int i, Integer num) {
        return CodedOutputStreamMicro.computeFixed32Size(i, num.intValue());
    }

    public void writeTo(CodedOutputStreamMicro codedOutputStreamMicro, int i) throws IOException {
        if (has()) {
            codedOutputStreamMicro.writeFixed32(i, this.value);
        }
    }

    protected void writeToDirectly(CodedOutputStreamMicro codedOutputStreamMicro, int i, Integer num) throws IOException {
        codedOutputStreamMicro.writeFixed32(i, num.intValue());
    }

    public void readFrom(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        this.value = codedInputStreamMicro.readFixed32();
        setHasFlag(true);
    }

    protected Integer readFromDirectly(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        return Integer.valueOf(codedInputStreamMicro.readFixed32());
    }

    public void clear(Object obj) {
        if (obj instanceof Integer) {
            this.value = ((Integer) obj).intValue();
        } else {
            this.value = 0;
        }
        setHasFlag(false);
    }

    protected void copyFrom(PBField<Integer> pBField) {
        PBFixed32Field pBFixed32Field = (PBFixed32Field) pBField;
        set(pBFixed32Field.value, pBFixed32Field.has());
    }
}

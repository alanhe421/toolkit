package com.tencent.imcore;

public class BytesVecFetcher {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected BytesVecFetcher(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public BytesVecFetcher(BytesVec bytesVec) {
        this(internalJNI.new_BytesVecFetcher(BytesVec.getCPtr(bytesVec), bytesVec), true);
    }

    protected static long getCPtr(BytesVecFetcher bytesVecFetcher) {
        return bytesVecFetcher == null ? 0 : bytesVecFetcher.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_BytesVecFetcher(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public byte[] getBytesByIndex(int i) {
        return internalJNI.BytesVecFetcher_getBytesByIndex(this.swigCPtr, this, i);
    }

    public BytesVec getValues() {
        long BytesVecFetcher_values_get = internalJNI.BytesVecFetcher_values_get(this.swigCPtr, this);
        return BytesVecFetcher_values_get == 0 ? null : new BytesVec(BytesVecFetcher_values_get, false);
    }

    public void setValues(BytesVec bytesVec) {
        internalJNI.BytesVecFetcher_values_set(this.swigCPtr, this, BytesVec.getCPtr(bytesVec), bytesVec);
    }
}

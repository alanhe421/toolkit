package com.tencent.imcore;

public class BytesVec {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public BytesVec() {
        this(internalJNI.new_BytesVec__SWIG_0(), true);
    }

    public BytesVec(long j) {
        this(internalJNI.new_BytesVec__SWIG_1(j), true);
    }

    protected BytesVec(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(BytesVec bytesVec) {
        return bytesVec == null ? 0 : bytesVec.swigCPtr;
    }

    public long capacity() {
        return internalJNI.BytesVec_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.BytesVec_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_BytesVec(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.BytesVec_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public byte[] get(int i) {
        return internalJNI.BytesVec_get(this.swigCPtr, this, i);
    }

    public void pushBack(byte[] bArr) {
        internalJNI.BytesVec_pushBack(this.swigCPtr, this, bArr);
    }

    public void reserve(long j) {
        internalJNI.BytesVec_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, byte[] bArr) {
        internalJNI.BytesVec_set(this.swigCPtr, this, i, bArr);
    }

    public long size() {
        return internalJNI.BytesVec_size(this.swigCPtr, this);
    }
}

package com.tencent.imcore;

public class BytesMap {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public BytesMap() {
        this(internalJNI.new_BytesMap__SWIG_0(), true);
    }

    protected BytesMap(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public BytesMap(BytesMap bytesMap) {
        this(internalJNI.new_BytesMap__SWIG_1(getCPtr(bytesMap), bytesMap), true);
    }

    protected static long getCPtr(BytesMap bytesMap) {
        return bytesMap == null ? 0 : bytesMap.swigCPtr;
    }

    public void clear() {
        internalJNI.BytesMap_clear(this.swigCPtr, this);
    }

    public void del(byte[] bArr) {
        internalJNI.BytesMap_del(this.swigCPtr, this, bArr);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_BytesMap(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.BytesMap_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public byte[] get(byte[] bArr) {
        return internalJNI.BytesMap_get(this.swigCPtr, this, bArr);
    }

    public boolean hasKey(byte[] bArr) {
        return internalJNI.BytesMap_hasKey(this.swigCPtr, this, bArr);
    }

    public void set(byte[] bArr, byte[] bArr2) {
        internalJNI.BytesMap_set(this.swigCPtr, this, bArr, bArr2);
    }

    public long size() {
        return internalJNI.BytesMap_size(this.swigCPtr, this);
    }
}

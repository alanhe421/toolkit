package com.tencent.imcore;

public class BytesProfileMap {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public BytesProfileMap() {
        this(internalJNI.new_BytesProfileMap__SWIG_0(), true);
    }

    protected BytesProfileMap(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public BytesProfileMap(BytesProfileMap bytesProfileMap) {
        this(internalJNI.new_BytesProfileMap__SWIG_1(getCPtr(bytesProfileMap), bytesProfileMap), true);
    }

    protected static long getCPtr(BytesProfileMap bytesProfileMap) {
        return bytesProfileMap == null ? 0 : bytesProfileMap.swigCPtr;
    }

    public void clear() {
        internalJNI.BytesProfileMap_clear(this.swigCPtr, this);
    }

    public void del(byte[] bArr) {
        internalJNI.BytesProfileMap_del(this.swigCPtr, this, bArr);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_BytesProfileMap(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.BytesProfileMap_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public FriendProfile get(byte[] bArr) {
        return new FriendProfile(internalJNI.BytesProfileMap_get(this.swigCPtr, this, bArr), false);
    }

    public boolean hasKey(byte[] bArr) {
        return internalJNI.BytesProfileMap_hasKey(this.swigCPtr, this, bArr);
    }

    public void set(byte[] bArr, FriendProfile friendProfile) {
        internalJNI.BytesProfileMap_set(this.swigCPtr, this, bArr, FriendProfile.getCPtr(friendProfile), friendProfile);
    }

    public long size() {
        return internalJNI.BytesProfileMap_size(this.swigCPtr, this);
    }
}

package com.tencent.imcore;

public class BytesMemberInfoMap {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public BytesMemberInfoMap() {
        this(internalJNI.new_BytesMemberInfoMap__SWIG_0(), true);
    }

    protected BytesMemberInfoMap(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public BytesMemberInfoMap(BytesMemberInfoMap bytesMemberInfoMap) {
        this(internalJNI.new_BytesMemberInfoMap__SWIG_1(getCPtr(bytesMemberInfoMap), bytesMemberInfoMap), true);
    }

    protected static long getCPtr(BytesMemberInfoMap bytesMemberInfoMap) {
        return bytesMemberInfoMap == null ? 0 : bytesMemberInfoMap.swigCPtr;
    }

    public void clear() {
        internalJNI.BytesMemberInfoMap_clear(this.swigCPtr, this);
    }

    public void del(byte[] bArr) {
        internalJNI.BytesMemberInfoMap_del(this.swigCPtr, this, bArr);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_BytesMemberInfoMap(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.BytesMemberInfoMap_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public NewGroupMemberInfo get(byte[] bArr) {
        return new NewGroupMemberInfo(internalJNI.BytesMemberInfoMap_get(this.swigCPtr, this, bArr), false);
    }

    public boolean hasKey(byte[] bArr) {
        return internalJNI.BytesMemberInfoMap_hasKey(this.swigCPtr, this, bArr);
    }

    public void set(byte[] bArr, NewGroupMemberInfo newGroupMemberInfo) {
        internalJNI.BytesMemberInfoMap_set(this.swigCPtr, this, bArr, NewGroupMemberInfo.getCPtr(newGroupMemberInfo), newGroupMemberInfo);
    }

    public long size() {
        return internalJNI.BytesMemberInfoMap_size(this.swigCPtr, this);
    }
}

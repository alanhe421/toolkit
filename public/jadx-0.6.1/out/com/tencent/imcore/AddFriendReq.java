package com.tencent.imcore;

public class AddFriendReq {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public AddFriendReq() {
        this(internalJNI.new_AddFriendReq(), true);
    }

    protected AddFriendReq(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(AddFriendReq addFriendReq) {
        return addFriendReq == null ? 0 : addFriendReq.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_AddFriendReq(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getIdentifier() {
        return internalJNI.AddFriendReq_identifier_get(this.swigCPtr, this);
    }

    public byte[] getNickname() {
        return internalJNI.AddFriendReq_nickname_get(this.swigCPtr, this);
    }

    public byte[] getSource() {
        return internalJNI.AddFriendReq_source_get(this.swigCPtr, this);
    }

    public byte[] getWording() {
        return internalJNI.AddFriendReq_wording_get(this.swigCPtr, this);
    }

    public void setIdentifier(String str) {
        internalJNI.AddFriendReq_identifier_set(this.swigCPtr, this, str);
    }

    public void setNickname(byte[] bArr) {
        internalJNI.AddFriendReq_nickname_set(this.swigCPtr, this, bArr);
    }

    public void setSource(byte[] bArr) {
        internalJNI.AddFriendReq_source_set(this.swigCPtr, this, bArr);
    }

    public void setWording(byte[] bArr) {
        internalJNI.AddFriendReq_wording_set(this.swigCPtr, this, bArr);
    }
}

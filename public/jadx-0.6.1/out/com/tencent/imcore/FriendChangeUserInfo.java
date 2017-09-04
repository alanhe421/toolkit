package com.tencent.imcore;

public class FriendChangeUserInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public FriendChangeUserInfo() {
        this(internalJNI.new_FriendChangeUserInfo(), true);
    }

    protected FriendChangeUserInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(FriendChangeUserInfo friendChangeUserInfo) {
        return friendChangeUserInfo == null ? 0 : friendChangeUserInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_FriendChangeUserInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public byte[] getAdd_source() {
        return internalJNI.FriendChangeUserInfo_add_source_get(this.swigCPtr, this);
    }

    public byte[] getAdd_wording() {
        return internalJNI.FriendChangeUserInfo_add_wording_get(this.swigCPtr, this);
    }

    public byte[] getGroup() {
        return internalJNI.FriendChangeUserInfo_group_get(this.swigCPtr, this);
    }

    public String getIdentifier() {
        return internalJNI.FriendChangeUserInfo_identifier_get(this.swigCPtr, this);
    }

    public byte[] getNick() {
        return internalJNI.FriendChangeUserInfo_nick_get(this.swigCPtr, this);
    }

    public byte[] getRemark() {
        return internalJNI.FriendChangeUserInfo_remark_get(this.swigCPtr, this);
    }

    public void setAdd_source(byte[] bArr) {
        internalJNI.FriendChangeUserInfo_add_source_set(this.swigCPtr, this, bArr);
    }

    public void setAdd_wording(byte[] bArr) {
        internalJNI.FriendChangeUserInfo_add_wording_set(this.swigCPtr, this, bArr);
    }

    public void setGroup(byte[] bArr) {
        internalJNI.FriendChangeUserInfo_group_set(this.swigCPtr, this, bArr);
    }

    public void setIdentifier(String str) {
        internalJNI.FriendChangeUserInfo_identifier_set(this.swigCPtr, this, str);
    }

    public void setNick(byte[] bArr) {
        internalJNI.FriendChangeUserInfo_nick_set(this.swigCPtr, this, bArr);
    }

    public void setRemark(byte[] bArr) {
        internalJNI.FriendChangeUserInfo_remark_set(this.swigCPtr, this, bArr);
    }
}

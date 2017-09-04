package com.tencent.imcore;

public class FriendPendencyItem {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public FriendPendencyItem() {
        this(internalJNI.new_FriendPendencyItem(), true);
    }

    protected FriendPendencyItem(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(FriendPendencyItem friendPendencyItem) {
        return friendPendencyItem == null ? 0 : friendPendencyItem.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_FriendPendencyItem(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getDdwAddTime() {
        return internalJNI.FriendPendencyItem_ddwAddTime_get(this.swigCPtr, this);
    }

    public int getIType() {
        return internalJNI.FriendPendencyItem_iType_get(this.swigCPtr, this);
    }

    public byte[] getSAddSource() {
        return internalJNI.FriendPendencyItem_sAddSource_get(this.swigCPtr, this);
    }

    public byte[] getSAddWording() {
        return internalJNI.FriendPendencyItem_sAddWording_get(this.swigCPtr, this);
    }

    public String getSIdentifier() {
        return internalJNI.FriendPendencyItem_sIdentifier_get(this.swigCPtr, this);
    }

    public byte[] getSNickname() {
        return internalJNI.FriendPendencyItem_sNickname_get(this.swigCPtr, this);
    }

    public void setDdwAddTime(long j) {
        internalJNI.FriendPendencyItem_ddwAddTime_set(this.swigCPtr, this, j);
    }

    public void setIType(int i) {
        internalJNI.FriendPendencyItem_iType_set(this.swigCPtr, this, i);
    }

    public void setSAddSource(byte[] bArr) {
        internalJNI.FriendPendencyItem_sAddSource_set(this.swigCPtr, this, bArr);
    }

    public void setSAddWording(byte[] bArr) {
        internalJNI.FriendPendencyItem_sAddWording_set(this.swigCPtr, this, bArr);
    }

    public void setSIdentifier(String str) {
        internalJNI.FriendPendencyItem_sIdentifier_set(this.swigCPtr, this, str);
    }

    public void setSNickname(byte[] bArr) {
        internalJNI.FriendPendencyItem_sNickname_set(this.swigCPtr, this, bArr);
    }
}

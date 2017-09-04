package com.tencent.imcore;

public class FriendProfileUpdateItem {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public FriendProfileUpdateItem() {
        this(internalJNI.new_FriendProfileUpdateItem(), true);
    }

    protected FriendProfileUpdateItem(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(FriendProfileUpdateItem friendProfileUpdateItem) {
        return friendProfileUpdateItem == null ? 0 : friendProfileUpdateItem.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_FriendProfileUpdateItem(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getBytes_value() {
        return internalJNI.FriendProfileUpdateItem_bytes_value_get(this.swigCPtr, this);
    }

    public String getTag() {
        return internalJNI.FriendProfileUpdateItem_tag_get(this.swigCPtr, this);
    }

    public long getUint64_value() {
        return internalJNI.FriendProfileUpdateItem_uint64_value_get(this.swigCPtr, this);
    }

    public StrVec getVec_bytes_value() {
        long FriendProfileUpdateItem_vec_bytes_value_get = internalJNI.FriendProfileUpdateItem_vec_bytes_value_get(this.swigCPtr, this);
        return FriendProfileUpdateItem_vec_bytes_value_get == 0 ? null : new StrVec(FriendProfileUpdateItem_vec_bytes_value_get, false);
    }

    public void setBytes_value(String str) {
        internalJNI.FriendProfileUpdateItem_bytes_value_set(this.swigCPtr, this, str);
    }

    public void setTag(String str) {
        internalJNI.FriendProfileUpdateItem_tag_set(this.swigCPtr, this, str);
    }

    public void setUint64_value(long j) {
        internalJNI.FriendProfileUpdateItem_uint64_value_set(this.swigCPtr, this, j);
    }

    public void setVec_bytes_value(StrVec strVec) {
        internalJNI.FriendProfileUpdateItem_vec_bytes_value_set(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec);
    }
}

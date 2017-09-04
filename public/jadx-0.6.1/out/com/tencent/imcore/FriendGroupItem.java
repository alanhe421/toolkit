package com.tencent.imcore;

public class FriendGroupItem {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public FriendGroupItem() {
        this(internalJNI.new_FriendGroupItem(), true);
    }

    protected FriendGroupItem(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(FriendGroupItem friendGroupItem) {
        return friendGroupItem == null ? 0 : friendGroupItem.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_FriendGroupItem(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public SWIGTYPE_p_std__setT_std__string_t getIdentifiers() {
        long FriendGroupItem_identifiers_get = internalJNI.FriendGroupItem_identifiers_get(this.swigCPtr, this);
        return FriendGroupItem_identifiers_get == 0 ? null : new SWIGTYPE_p_std__setT_std__string_t(FriendGroupItem_identifiers_get, false);
    }

    public byte[] getName() {
        return internalJNI.FriendGroupItem_name_get(this.swigCPtr, this);
    }

    public void setIdentifiers(SWIGTYPE_p_std__setT_std__string_t sWIGTYPE_p_std__setT_std__string_t) {
        internalJNI.FriendGroupItem_identifiers_set(this.swigCPtr, this, SWIGTYPE_p_std__setT_std__string_t.getCPtr(sWIGTYPE_p_std__setT_std__string_t));
    }

    public void setName(byte[] bArr) {
        internalJNI.FriendGroupItem_name_set(this.swigCPtr, this, bArr);
    }
}

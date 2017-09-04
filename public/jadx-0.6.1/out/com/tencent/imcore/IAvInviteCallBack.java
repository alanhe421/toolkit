package com.tencent.imcore;

public class IAvInviteCallBack {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IAvInviteCallBack() {
        this(internalJNI.new_IAvInviteCallBack(), true);
        internalJNI.IAvInviteCallBack_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IAvInviteCallBack(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IAvInviteCallBack iAvInviteCallBack) {
        return iAvInviteCallBack == null ? 0 : iAvInviteCallBack.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IAvInviteCallBack(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public void onAvInviteBuf(byte[] bArr) {
        if (getClass() == IAvInviteCallBack.class) {
            internalJNI.IAvInviteCallBack_onAvInviteBuf(this.swigCPtr, this, bArr);
        } else {
            internalJNI.IAvInviteCallBack_onAvInviteBufSwigExplicitIAvInviteCallBack(this.swigCPtr, this, bArr);
        }
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        internalJNI.IAvInviteCallBack_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IAvInviteCallBack_change_ownership(this, this.swigCPtr, true);
    }
}

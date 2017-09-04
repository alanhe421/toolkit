package com.tencent.imcore;

public class INotify {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public INotify() {
        this(internalJNI.new_INotify(), true);
        internalJNI.INotify_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected INotify(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(INotify iNotify) {
        return iNotify == null ? 0 : iNotify.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_INotify(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public void onMsgEvent(MsgVec msgVec) {
        if (getClass() == INotify.class) {
            internalJNI.INotify_onMsgEvent(this.swigCPtr, this, MsgVec.getCPtr(msgVec), msgVec);
        } else {
            internalJNI.INotify_onMsgEventSwigExplicitINotify(this.swigCPtr, this, MsgVec.getCPtr(msgVec), msgVec);
        }
    }

    public void onMsgUpdate(MsgVec msgVec) {
        if (getClass() == INotify.class) {
            internalJNI.INotify_onMsgUpdate(this.swigCPtr, this, MsgVec.getCPtr(msgVec), msgVec);
        } else {
            internalJNI.INotify_onMsgUpdateSwigExplicitINotify(this.swigCPtr, this, MsgVec.getCPtr(msgVec), msgVec);
        }
    }

    public void onRecvMsgReceipt(MsgReceiptVec msgReceiptVec) {
        if (getClass() == INotify.class) {
            internalJNI.INotify_onRecvMsgReceipt(this.swigCPtr, this, MsgReceiptVec.getCPtr(msgReceiptVec), msgReceiptVec);
        } else {
            internalJNI.INotify_onRecvMsgReceiptSwigExplicitINotify(this.swigCPtr, this, MsgReceiptVec.getCPtr(msgReceiptVec), msgReceiptVec);
        }
    }

    public void onRefresh() {
        if (getClass() == INotify.class) {
            internalJNI.INotify_onRefresh(this.swigCPtr, this);
        } else {
            internalJNI.INotify_onRefreshSwigExplicitINotify(this.swigCPtr, this);
        }
    }

    public void onRefreshConversation(SessionUUIDVec sessionUUIDVec) {
        if (getClass() == INotify.class) {
            internalJNI.INotify_onRefreshConversation(this.swigCPtr, this, SessionUUIDVec.getCPtr(sessionUUIDVec), sessionUUIDVec);
        } else {
            internalJNI.INotify_onRefreshConversationSwigExplicitINotify(this.swigCPtr, this, SessionUUIDVec.getCPtr(sessionUUIDVec), sessionUUIDVec);
        }
    }

    public void onUploadProgress(Msg msg, int i, int i2, int i3) {
        if (getClass() == INotify.class) {
            internalJNI.INotify_onUploadProgress(this.swigCPtr, this, Msg.getCPtr(msg), msg, i, i2, i3);
        } else {
            internalJNI.INotify_onUploadProgressSwigExplicitINotify(this.swigCPtr, this, Msg.getCPtr(msg), msg, i, i2, i3);
        }
    }

    public void onUserStatusChanged(UserStatus userStatus) {
        if (getClass() == INotify.class) {
            internalJNI.INotify_onUserStatusChanged(this.swigCPtr, this, UserStatus.getCPtr(userStatus), userStatus);
        } else {
            internalJNI.INotify_onUserStatusChangedSwigExplicitINotify(this.swigCPtr, this, UserStatus.getCPtr(userStatus), userStatus);
        }
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        internalJNI.INotify_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.INotify_change_ownership(this, this.swigCPtr, true);
    }
}

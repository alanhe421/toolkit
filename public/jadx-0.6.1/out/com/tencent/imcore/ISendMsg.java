package com.tencent.imcore;

public class ISendMsg {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public ISendMsg() {
        this(internalJNI.new_ISendMsg(), true);
        internalJNI.ISendMsg_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected ISendMsg(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(ISendMsg iSendMsg) {
        return iSendMsg == null ? 0 : iSendMsg.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_ISendMsg(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done() {
        if (getClass() == ISendMsg.class) {
            internalJNI.ISendMsg_done(this.swigCPtr, this);
        } else {
            internalJNI.ISendMsg_doneSwigExplicitISendMsg(this.swigCPtr, this);
        }
    }

    public void fail(int i, String str) {
        if (getClass() == ISendMsg.class) {
            internalJNI.ISendMsg_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.ISendMsg_failSwigExplicitISendMsg(this.swigCPtr, this, i, str);
        }
    }

    protected void finalize() {
        delete();
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        internalJNI.ISendMsg_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.ISendMsg_change_ownership(this, this.swigCPtr, true);
    }
}

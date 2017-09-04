package com.tencent.imcore;

public class ISetReadMsg {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public ISetReadMsg() {
        this(internalJNI.new_ISetReadMsg(), true);
        internalJNI.ISetReadMsg_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected ISetReadMsg(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(ISetReadMsg iSetReadMsg) {
        return iSetReadMsg == null ? 0 : iSetReadMsg.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_ISetReadMsg(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done() {
        if (getClass() == ISetReadMsg.class) {
            internalJNI.ISetReadMsg_done(this.swigCPtr, this);
        } else {
            internalJNI.ISetReadMsg_doneSwigExplicitISetReadMsg(this.swigCPtr, this);
        }
    }

    public void fail(int i, String str) {
        if (getClass() == ISetReadMsg.class) {
            internalJNI.ISetReadMsg_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.ISetReadMsg_failSwigExplicitISetReadMsg(this.swigCPtr, this, i, str);
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
        internalJNI.ISetReadMsg_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.ISetReadMsg_change_ownership(this, this.swigCPtr, true);
    }
}

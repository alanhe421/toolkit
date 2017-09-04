package com.tencent.imcore;

public class IDeleteLocalMsg {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IDeleteLocalMsg() {
        this(internalJNI.new_IDeleteLocalMsg(), true);
        internalJNI.IDeleteLocalMsg_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IDeleteLocalMsg(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IDeleteLocalMsg iDeleteLocalMsg) {
        return iDeleteLocalMsg == null ? 0 : iDeleteLocalMsg.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IDeleteLocalMsg(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done() {
        if (getClass() == IDeleteLocalMsg.class) {
            internalJNI.IDeleteLocalMsg_done(this.swigCPtr, this);
        } else {
            internalJNI.IDeleteLocalMsg_doneSwigExplicitIDeleteLocalMsg(this.swigCPtr, this);
        }
    }

    public void fail(int i, String str) {
        if (getClass() == IDeleteLocalMsg.class) {
            internalJNI.IDeleteLocalMsg_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IDeleteLocalMsg_failSwigExplicitIDeleteLocalMsg(this.swigCPtr, this, i, str);
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
        internalJNI.IDeleteLocalMsg_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IDeleteLocalMsg_change_ownership(this, this.swigCPtr, true);
    }
}

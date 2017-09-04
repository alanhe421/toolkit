package com.tencent.imcore;

public class IGetMsgs {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IGetMsgs() {
        this(internalJNI.new_IGetMsgs(), true);
        internalJNI.IGetMsgs_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IGetMsgs(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IGetMsgs iGetMsgs) {
        return iGetMsgs == null ? 0 : iGetMsgs.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IGetMsgs(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done(MsgVec msgVec) {
        if (getClass() == IGetMsgs.class) {
            internalJNI.IGetMsgs_done(this.swigCPtr, this, MsgVec.getCPtr(msgVec), msgVec);
        } else {
            internalJNI.IGetMsgs_doneSwigExplicitIGetMsgs(this.swigCPtr, this, MsgVec.getCPtr(msgVec), msgVec);
        }
    }

    public void fail(int i, String str) {
        if (getClass() == IGetMsgs.class) {
            internalJNI.IGetMsgs_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IGetMsgs_failSwigExplicitIGetMsgs(this.swigCPtr, this, i, str);
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
        internalJNI.IGetMsgs_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IGetMsgs_change_ownership(this, this.swigCPtr, true);
    }
}

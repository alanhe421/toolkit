package com.tencent.imcore;

public class IStatusSetUserDefCallback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IStatusSetUserDefCallback() {
        this(internalJNI.new_IStatusSetUserDefCallback(), true);
        internalJNI.IStatusSetUserDefCallback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IStatusSetUserDefCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IStatusSetUserDefCallback iStatusSetUserDefCallback) {
        return iStatusSetUserDefCallback == null ? 0 : iStatusSetUserDefCallback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IStatusSetUserDefCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done() {
        if (getClass() == IStatusSetUserDefCallback.class) {
            internalJNI.IStatusSetUserDefCallback_done(this.swigCPtr, this);
        } else {
            internalJNI.IStatusSetUserDefCallback_doneSwigExplicitIStatusSetUserDefCallback(this.swigCPtr, this);
        }
    }

    public void fail(int i, String str) {
        if (getClass() == IStatusSetUserDefCallback.class) {
            internalJNI.IStatusSetUserDefCallback_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IStatusSetUserDefCallback_failSwigExplicitIStatusSetUserDefCallback(this.swigCPtr, this, i, str);
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
        internalJNI.IStatusSetUserDefCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IStatusSetUserDefCallback_change_ownership(this, this.swigCPtr, true);
    }
}

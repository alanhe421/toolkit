package com.tencent.imcore;

public class ICreateGroupCallback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public ICreateGroupCallback() {
        this(internalJNI.new_ICreateGroupCallback(), true);
        internalJNI.ICreateGroupCallback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected ICreateGroupCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(ICreateGroupCallback iCreateGroupCallback) {
        return iCreateGroupCallback == null ? 0 : iCreateGroupCallback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_ICreateGroupCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done(String str) {
        if (getClass() == ICreateGroupCallback.class) {
            internalJNI.ICreateGroupCallback_done(this.swigCPtr, this, str);
        } else {
            internalJNI.ICreateGroupCallback_doneSwigExplicitICreateGroupCallback(this.swigCPtr, this, str);
        }
    }

    public void fail(int i, String str) {
        if (getClass() == ICreateGroupCallback.class) {
            internalJNI.ICreateGroupCallback_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.ICreateGroupCallback_failSwigExplicitICreateGroupCallback(this.swigCPtr, this, i, str);
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
        internalJNI.ICreateGroupCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.ICreateGroupCallback_change_ownership(this, this.swigCPtr, true);
    }
}

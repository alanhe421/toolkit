package com.tencent.imcore;

public class IInit {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IInit() {
        this(internalJNI.new_IInit(), true);
        internalJNI.IInit_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IInit(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IInit iInit) {
        return iInit == null ? 0 : iInit.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IInit(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done() {
        if (getClass() == IInit.class) {
            internalJNI.IInit_done(this.swigCPtr, this);
        } else {
            internalJNI.IInit_doneSwigExplicitIInit(this.swigCPtr, this);
        }
    }

    public void fail(int i, String str) {
        if (getClass() == IInit.class) {
            internalJNI.IInit_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IInit_failSwigExplicitIInit(this.swigCPtr, this, i, str);
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
        internalJNI.IInit_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IInit_change_ownership(this, this.swigCPtr, true);
    }
}

package com.tencent.imcore;

public class IFileTrans {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IFileTrans() {
        this(internalJNI.new_IFileTrans(), true);
        internalJNI.IFileTrans_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IFileTrans(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IFileTrans iFileTrans) {
        return iFileTrans == null ? 0 : iFileTrans.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IFileTrans(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done(FileTransSuccParam fileTransSuccParam) {
        if (getClass() == IFileTrans.class) {
            internalJNI.IFileTrans_done(this.swigCPtr, this, FileTransSuccParam.getCPtr(fileTransSuccParam), fileTransSuccParam);
        } else {
            internalJNI.IFileTrans_doneSwigExplicitIFileTrans(this.swigCPtr, this, FileTransSuccParam.getCPtr(fileTransSuccParam), fileTransSuccParam);
        }
    }

    public void fail(int i, String str) {
        if (getClass() == IFileTrans.class) {
            internalJNI.IFileTrans_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IFileTrans_failSwigExplicitIFileTrans(this.swigCPtr, this, i, str);
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
        internalJNI.IFileTrans_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IFileTrans_change_ownership(this, this.swigCPtr, true);
    }
}

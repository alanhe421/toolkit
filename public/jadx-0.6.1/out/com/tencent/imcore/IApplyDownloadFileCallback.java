package com.tencent.imcore;

public class IApplyDownloadFileCallback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IApplyDownloadFileCallback() {
        this(internalJNI.new_IApplyDownloadFileCallback(), true);
        internalJNI.IApplyDownloadFileCallback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IApplyDownloadFileCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IApplyDownloadFileCallback iApplyDownloadFileCallback) {
        return iApplyDownloadFileCallback == null ? 0 : iApplyDownloadFileCallback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IApplyDownloadFileCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done(StrVec strVec) {
        if (getClass() == IApplyDownloadFileCallback.class) {
            internalJNI.IApplyDownloadFileCallback_done(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec);
        } else {
            internalJNI.IApplyDownloadFileCallback_doneSwigExplicitIApplyDownloadFileCallback(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec);
        }
    }

    public void fail(int i, String str) {
        if (getClass() == IApplyDownloadFileCallback.class) {
            internalJNI.IApplyDownloadFileCallback_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IApplyDownloadFileCallback_failSwigExplicitIApplyDownloadFileCallback(this.swigCPtr, this, i, str);
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
        internalJNI.IApplyDownloadFileCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IApplyDownloadFileCallback_change_ownership(this, this.swigCPtr, true);
    }
}

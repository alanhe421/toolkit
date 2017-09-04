package com.tencent.imcore;

public class IImageUploadCallback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IImageUploadCallback() {
        this(internalJNI.new_IImageUploadCallback(), true);
        internalJNI.IImageUploadCallback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IImageUploadCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IImageUploadCallback iImageUploadCallback) {
        return iImageUploadCallback == null ? 0 : iImageUploadCallback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IImageUploadCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done(ImageElem imageElem) {
        if (getClass() == IImageUploadCallback.class) {
            internalJNI.IImageUploadCallback_done(this.swigCPtr, this, ImageElem.getCPtr(imageElem), imageElem);
        } else {
            internalJNI.IImageUploadCallback_doneSwigExplicitIImageUploadCallback(this.swigCPtr, this, ImageElem.getCPtr(imageElem), imageElem);
        }
    }

    public void fail(int i, String str) {
        if (getClass() == IImageUploadCallback.class) {
            internalJNI.IImageUploadCallback_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IImageUploadCallback_failSwigExplicitIImageUploadCallback(this.swigCPtr, this, i, str);
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
        internalJNI.IImageUploadCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IImageUploadCallback_change_ownership(this, this.swigCPtr, true);
    }
}

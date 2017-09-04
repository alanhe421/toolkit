package com.tencent.imcore;

public class EnvRequestClosure {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected EnvRequestClosure(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(EnvRequestClosure envRequestClosure) {
        return envRequestClosure == null ? 0 : envRequestClosure.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_EnvRequestClosure(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done(byte[] bArr) {
        internalJNI.EnvRequestClosure_done(this.swigCPtr, this, bArr);
    }

    public void fail(int i, String str) {
        internalJNI.EnvRequestClosure_fail(this.swigCPtr, this, i, str);
    }

    protected void finalize() {
        delete();
    }

    public void release() {
        internalJNI.EnvRequestClosure_release(this.swigCPtr, this);
    }
}

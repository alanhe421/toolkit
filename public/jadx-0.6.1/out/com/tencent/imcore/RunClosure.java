package com.tencent.imcore;

public class RunClosure {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected RunClosure(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(RunClosure runClosure) {
        return runClosure == null ? 0 : runClosure.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_RunClosure(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public void release() {
        internalJNI.RunClosure_release(this.swigCPtr, this);
    }

    public void run() {
        internalJNI.RunClosure_run(this.swigCPtr, this);
    }
}

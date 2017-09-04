package com.tencent.imcore;

public class IEnv {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IEnv() {
        this(internalJNI.new_IEnv(), true);
        internalJNI.IEnv_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IEnv(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IEnv iEnv) {
        return iEnv == null ? 0 : iEnv.swigCPtr;
    }

    public boolean createThread(ThreadEntry threadEntry) {
        return getClass() == IEnv.class ? internalJNI.IEnv_createThread(this.swigCPtr, this, ThreadEntry.getCPtr(threadEntry), threadEntry) : internalJNI.IEnv_createThreadSwigExplicitIEnv(this.swigCPtr, this, ThreadEntry.getCPtr(threadEntry), threadEntry);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IEnv(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public boolean httpRequest(String str, HttpMethod httpMethod, String str2, byte[] bArr, EnvRequestClosure envRequestClosure) {
        if (getClass() == IEnv.class) {
            return internalJNI.IEnv_httpRequest(this.swigCPtr, this, str, httpMethod.swigValue(), str2, bArr, EnvRequestClosure.getCPtr(envRequestClosure), envRequestClosure);
        }
        return internalJNI.IEnv_httpRequestSwigExplicitIEnv(this.swigCPtr, this, str, httpMethod.swigValue(), str2, bArr, EnvRequestClosure.getCPtr(envRequestClosure), envRequestClosure);
    }

    public boolean runOnMainThread(RunClosure runClosure) {
        return getClass() == IEnv.class ? internalJNI.IEnv_runOnMainThread(this.swigCPtr, this, RunClosure.getCPtr(runClosure), runClosure) : internalJNI.IEnv_runOnMainThreadSwigExplicitIEnv(this.swigCPtr, this, RunClosure.getCPtr(runClosure), runClosure);
    }

    public boolean sSORequest(String str, String str2, byte[] bArr, EnvRequestClosure envRequestClosure) {
        if (getClass() == IEnv.class) {
            return internalJNI.IEnv_sSORequest__SWIG_1(this.swigCPtr, this, str, str2, bArr, EnvRequestClosure.getCPtr(envRequestClosure), envRequestClosure);
        }
        return internalJNI.IEnv_sSORequestSwigExplicitIEnv__SWIG_1(this.swigCPtr, this, str, str2, bArr, EnvRequestClosure.getCPtr(envRequestClosure), envRequestClosure);
    }

    public boolean sSORequest(String str, String str2, byte[] bArr, EnvRequestClosure envRequestClosure, long j) {
        if (getClass() == IEnv.class) {
            return internalJNI.IEnv_sSORequest__SWIG_0(this.swigCPtr, this, str, str2, bArr, EnvRequestClosure.getCPtr(envRequestClosure), envRequestClosure, j);
        }
        return internalJNI.IEnv_sSORequestSwigExplicitIEnv__SWIG_0(this.swigCPtr, this, str, str2, bArr, EnvRequestClosure.getCPtr(envRequestClosure), envRequestClosure, j);
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        internalJNI.IEnv_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IEnv_change_ownership(this, this.swigCPtr, true);
    }

    public boolean uploadLogFile(String str, UploadLogFileOpt uploadLogFileOpt) {
        if (getClass() == IEnv.class) {
            return internalJNI.IEnv_uploadLogFile(this.swigCPtr, this, str, UploadLogFileOpt.getCPtr(uploadLogFileOpt), uploadLogFileOpt);
        }
        return internalJNI.IEnv_uploadLogFileSwigExplicitIEnv(this.swigCPtr, this, str, UploadLogFileOpt.getCPtr(uploadLogFileOpt), uploadLogFileOpt);
    }
}

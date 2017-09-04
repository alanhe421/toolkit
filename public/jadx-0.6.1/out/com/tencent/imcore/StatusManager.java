package com.tencent.imcore;

public class StatusManager {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected StatusManager(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public StatusManager(String str) {
        this(internalJNI.new_StatusManager(str), true);
    }

    protected static long getCPtr(StatusManager statusManager) {
        return statusManager == null ? 0 : statusManager.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_StatusManager(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public boolean getUserStatus(StrVec strVec, IStatusCallback iStatusCallback) {
        return internalJNI.StatusManager_getUserStatus(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec, IStatusCallback.getCPtr(iStatusCallback), iStatusCallback);
    }

    public void setUsedefinedData(byte[] bArr, IStatusSetUserDefCallback iStatusSetUserDefCallback) {
        internalJNI.StatusManager_setUsedefinedData(this.swigCPtr, this, bArr, IStatusSetUserDefCallback.getCPtr(iStatusSetUserDefCallback), iStatusSetUserDefCallback);
    }
}

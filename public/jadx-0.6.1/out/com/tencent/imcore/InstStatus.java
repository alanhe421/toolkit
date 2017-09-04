package com.tencent.imcore;

public class InstStatus {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public InstStatus() {
        this(internalJNI.new_InstStatus(), true);
    }

    protected InstStatus(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(InstStatus instStatus) {
        return instStatus == null ? 0 : instStatus.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_InstStatus(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getDdwClientType() {
        return internalJNI.InstStatus_ddwClientType_get(this.swigCPtr, this);
    }

    public byte[] getSUserDefine() {
        return internalJNI.InstStatus_sUserDefine_get(this.swigCPtr, this);
    }

    public void setDdwClientType(long j) {
        internalJNI.InstStatus_ddwClientType_set(this.swigCPtr, this, j);
    }

    public void setSUserDefine(byte[] bArr) {
        internalJNI.InstStatus_sUserDefine_set(this.swigCPtr, this, bArr);
    }
}

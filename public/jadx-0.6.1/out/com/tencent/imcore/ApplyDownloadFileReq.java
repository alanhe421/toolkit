package com.tencent.imcore;

public class ApplyDownloadFileReq {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public ApplyDownloadFileReq() {
        this(internalJNI.new_ApplyDownloadFileReq(), true);
    }

    protected ApplyDownloadFileReq(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(ApplyDownloadFileReq applyDownloadFileReq) {
        return applyDownloadFileReq == null ? 0 : applyDownloadFileReq.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_ApplyDownloadFileReq(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getBusiId() {
        return internalJNI.ApplyDownloadFileReq_busiId_get(this.swigCPtr, this);
    }

    public long getDownloadFlag() {
        return internalJNI.ApplyDownloadFileReq_downloadFlag_get(this.swigCPtr, this);
    }

    public long getType() {
        return internalJNI.ApplyDownloadFileReq_type_get(this.swigCPtr, this);
    }

    public String getUuid() {
        return internalJNI.ApplyDownloadFileReq_uuid_get(this.swigCPtr, this);
    }

    public void setBusiId(long j) {
        internalJNI.ApplyDownloadFileReq_busiId_set(this.swigCPtr, this, j);
    }

    public void setDownloadFlag(long j) {
        internalJNI.ApplyDownloadFileReq_downloadFlag_set(this.swigCPtr, this, j);
    }

    public void setType(long j) {
        internalJNI.ApplyDownloadFileReq_type_set(this.swigCPtr, this, j);
    }

    public void setUuid(String str) {
        internalJNI.ApplyDownloadFileReq_uuid_set(this.swigCPtr, this, str);
    }
}

package com.tencent.imcore;

public class UploadLogFileOpt {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public UploadLogFileOpt() {
        this(internalJNI.new_UploadLogFileOpt(), true);
    }

    protected UploadLogFileOpt(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(UploadLogFileOpt uploadLogFileOpt) {
        return uploadLogFileOpt == null ? 0 : uploadLogFileOpt.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_UploadLogFileOpt(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getFilePath() {
        return internalJNI.UploadLogFileOpt_filePath_get(this.swigCPtr, this);
    }

    public byte[] getLogId() {
        return internalJNI.UploadLogFileOpt_logId_get(this.swigCPtr, this);
    }

    public long getLogSize() {
        return internalJNI.UploadLogFileOpt_logSize_get(this.swigCPtr, this);
    }

    public byte[] getRelativePath() {
        return internalJNI.UploadLogFileOpt_relativePath_get(this.swigCPtr, this);
    }

    public String getTag() {
        return internalJNI.UploadLogFileOpt_tag_get(this.swigCPtr, this);
    }

    public void setFilePath(String str) {
        internalJNI.UploadLogFileOpt_filePath_set(this.swigCPtr, this, str);
    }

    public void setLogId(byte[] bArr) {
        internalJNI.UploadLogFileOpt_logId_set(this.swigCPtr, this, bArr);
    }

    public void setLogSize(long j) {
        internalJNI.UploadLogFileOpt_logSize_set(this.swigCPtr, this, j);
    }

    public void setRelativePath(byte[] bArr) {
        internalJNI.UploadLogFileOpt_relativePath_set(this.swigCPtr, this, bArr);
    }

    public void setTag(String str) {
        internalJNI.UploadLogFileOpt_tag_set(this.swigCPtr, this, str);
    }

    public String toString() {
        return internalJNI.UploadLogFileOpt_toString(this.swigCPtr, this);
    }
}

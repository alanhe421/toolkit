package com.tencent.imcore;

public class FileElem {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public FileElem() {
        this(internalJNI.new_FileElem(), true);
    }

    protected FileElem(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(FileElem fileElem) {
        return fileElem == null ? 0 : fileElem.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_FileElem(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public int getBusiness_id() {
        return internalJNI.FileElem_business_id_get(this.swigCPtr, this);
    }

    public int getDownload_flag() {
        return internalJNI.FileElem_download_flag_get(this.swigCPtr, this);
    }

    public byte[] getFile_name() {
        return internalJNI.FileElem_file_name_get(this.swigCPtr, this);
    }

    public byte[] getFile_path() {
        return internalJNI.FileElem_file_path_get(this.swigCPtr, this);
    }

    public long getFile_size() {
        return internalJNI.FileElem_file_size_get(this.swigCPtr, this);
    }

    public String getFileid() {
        return internalJNI.FileElem_fileid_get(this.swigCPtr, this);
    }

    public int getTaskid() {
        return internalJNI.FileElem_taskid_get(this.swigCPtr, this);
    }

    public StrVec getUrls() {
        long FileElem_urls_get = internalJNI.FileElem_urls_get(this.swigCPtr, this);
        return FileElem_urls_get == 0 ? null : new StrVec(FileElem_urls_get, false);
    }

    public void setBusiness_id(int i) {
        internalJNI.FileElem_business_id_set(this.swigCPtr, this, i);
    }

    public void setDownload_flag(int i) {
        internalJNI.FileElem_download_flag_set(this.swigCPtr, this, i);
    }

    public void setFile_name(byte[] bArr) {
        internalJNI.FileElem_file_name_set(this.swigCPtr, this, bArr);
    }

    public void setFile_path(byte[] bArr) {
        internalJNI.FileElem_file_path_set(this.swigCPtr, this, bArr);
    }

    public void setFile_size(long j) {
        internalJNI.FileElem_file_size_set(this.swigCPtr, this, j);
    }

    public void setFileid(String str) {
        internalJNI.FileElem_fileid_set(this.swigCPtr, this, str);
    }

    public void setTaskid(int i) {
        internalJNI.FileElem_taskid_set(this.swigCPtr, this, i);
    }

    public void setUrls(StrVec strVec) {
        internalJNI.FileElem_urls_set(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec);
    }
}

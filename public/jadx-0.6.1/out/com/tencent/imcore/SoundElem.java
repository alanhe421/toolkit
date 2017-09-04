package com.tencent.imcore;

public class SoundElem {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public SoundElem() {
        this(internalJNI.new_SoundElem(), true);
    }

    protected SoundElem(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(SoundElem soundElem) {
        return soundElem == null ? 0 : soundElem.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_SoundElem(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public int getBusiness_id() {
        return internalJNI.SoundElem_business_id_get(this.swigCPtr, this);
    }

    public int getDownload_flag() {
        return internalJNI.SoundElem_download_flag_get(this.swigCPtr, this);
    }

    public byte[] getFile_path() {
        return internalJNI.SoundElem_file_path_get(this.swigCPtr, this);
    }

    public int getFile_size() {
        return internalJNI.SoundElem_file_size_get(this.swigCPtr, this);
    }

    public int getFile_time() {
        return internalJNI.SoundElem_file_time_get(this.swigCPtr, this);
    }

    public String getFileid() {
        return internalJNI.SoundElem_fileid_get(this.swigCPtr, this);
    }

    public int getTaskid() {
        return internalJNI.SoundElem_taskid_get(this.swigCPtr, this);
    }

    public StrVec getUrls() {
        long SoundElem_urls_get = internalJNI.SoundElem_urls_get(this.swigCPtr, this);
        return SoundElem_urls_get == 0 ? null : new StrVec(SoundElem_urls_get, false);
    }

    public void setBusiness_id(int i) {
        internalJNI.SoundElem_business_id_set(this.swigCPtr, this, i);
    }

    public void setDownload_flag(int i) {
        internalJNI.SoundElem_download_flag_set(this.swigCPtr, this, i);
    }

    public void setFile_path(byte[] bArr) {
        internalJNI.SoundElem_file_path_set(this.swigCPtr, this, bArr);
    }

    public void setFile_size(int i) {
        internalJNI.SoundElem_file_size_set(this.swigCPtr, this, i);
    }

    public void setFile_time(int i) {
        internalJNI.SoundElem_file_time_set(this.swigCPtr, this, i);
    }

    public void setFileid(String str) {
        internalJNI.SoundElem_fileid_set(this.swigCPtr, this, str);
    }

    public void setTaskid(int i) {
        internalJNI.SoundElem_taskid_set(this.swigCPtr, this, i);
    }

    public void setUrls(StrVec strVec) {
        internalJNI.SoundElem_urls_set(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec);
    }
}

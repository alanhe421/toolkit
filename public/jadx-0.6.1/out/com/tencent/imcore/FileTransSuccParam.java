package com.tencent.imcore;

public class FileTransSuccParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public FileTransSuccParam() {
        this(internalJNI.new_FileTransSuccParam(), true);
    }

    protected FileTransSuccParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(FileTransSuccParam fileTransSuccParam) {
        return fileTransSuccParam == null ? 0 : fileTransSuccParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_FileTransSuccParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public byte[] getData() {
        return internalJNI.FileTransSuccParam_data_get(this.swigCPtr, this);
    }

    public SWIGTYPE_p_void getDlinfo() {
        long FileTransSuccParam_dlinfo_get = internalJNI.FileTransSuccParam_dlinfo_get(this.swigCPtr, this);
        return FileTransSuccParam_dlinfo_get == 0 ? null : new SWIGTYPE_p_void(FileTransSuccParam_dlinfo_get, false);
    }

    public String getFileid() {
        return internalJNI.FileTransSuccParam_fileid_get(this.swigCPtr, this);
    }

    public int getHeight() {
        return internalJNI.FileTransSuccParam_height_get(this.swigCPtr, this);
    }

    public long getSize() {
        return internalJNI.FileTransSuccParam_size_get(this.swigCPtr, this);
    }

    public int getWidth() {
        return internalJNI.FileTransSuccParam_width_get(this.swigCPtr, this);
    }

    public void setData(byte[] bArr) {
        internalJNI.FileTransSuccParam_data_set(this.swigCPtr, this, bArr);
    }

    public void setDlinfo(SWIGTYPE_p_void sWIGTYPE_p_void) {
        internalJNI.FileTransSuccParam_dlinfo_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void setFileid(String str) {
        internalJNI.FileTransSuccParam_fileid_set(this.swigCPtr, this, str);
    }

    public void setHeight(int i) {
        internalJNI.FileTransSuccParam_height_set(this.swigCPtr, this, i);
    }

    public void setSize(long j) {
        internalJNI.FileTransSuccParam_size_set(this.swigCPtr, this, j);
    }

    public void setWidth(int i) {
        internalJNI.FileTransSuccParam_width_set(this.swigCPtr, this, i);
    }
}

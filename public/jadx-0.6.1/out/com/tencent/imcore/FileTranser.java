package com.tencent.imcore;

public class FileTranser {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected FileTranser(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public FileTranser(SWIGTYPE_p_imcore__FileTranserImpl sWIGTYPE_p_imcore__FileTranserImpl) {
        this(internalJNI.new_FileTranser(SWIGTYPE_p_imcore__FileTranserImpl.getCPtr(sWIGTYPE_p_imcore__FileTranserImpl)), true);
    }

    protected static long getCPtr(FileTranser fileTranser) {
        return fileTranser == null ? 0 : fileTranser.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_FileTranser(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public SWIGTYPE_p_imcore__FileTranserImpl getImpl() {
        long FileTranser_getImpl = internalJNI.FileTranser_getImpl(this.swigCPtr, this);
        return FileTranser_getImpl == 0 ? null : new SWIGTYPE_p_imcore__FileTranserImpl(FileTranser_getImpl, false);
    }

    public void setCachePath(String str) {
        internalJNI.FileTranser_setCachePath(this.swigCPtr, this, str);
    }
}

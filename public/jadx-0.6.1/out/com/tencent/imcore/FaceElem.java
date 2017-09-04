package com.tencent.imcore;

public class FaceElem {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public FaceElem() {
        this(internalJNI.new_FaceElem(), true);
    }

    protected FaceElem(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(FaceElem faceElem) {
        return faceElem == null ? 0 : faceElem.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_FaceElem(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public byte[] getBuf() {
        return internalJNI.FaceElem_buf_get(this.swigCPtr, this);
    }

    public int getIndex() {
        return internalJNI.FaceElem_index_get(this.swigCPtr, this);
    }

    public void setBuf(byte[] bArr) {
        internalJNI.FaceElem_buf_set(this.swigCPtr, this, bArr);
    }

    public void setIndex(int i) {
        internalJNI.FaceElem_index_set(this.swigCPtr, this, i);
    }
}

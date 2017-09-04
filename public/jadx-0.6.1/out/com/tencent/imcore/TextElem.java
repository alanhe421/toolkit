package com.tencent.imcore;

public class TextElem {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public TextElem() {
        this(internalJNI.new_TextElem(), true);
    }

    protected TextElem(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(TextElem textElem) {
        return textElem == null ? 0 : textElem.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_TextElem(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public byte[] getContent() {
        return internalJNI.TextElem_content_get(this.swigCPtr, this);
    }

    public void setContent(byte[] bArr) {
        internalJNI.TextElem_content_set(this.swigCPtr, this, bArr);
    }
}

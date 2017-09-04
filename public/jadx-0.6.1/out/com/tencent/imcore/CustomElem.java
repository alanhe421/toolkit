package com.tencent.imcore;

public class CustomElem {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public CustomElem() {
        this(internalJNI.new_CustomElem(), true);
    }

    protected CustomElem(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(CustomElem customElem) {
        return customElem == null ? 0 : customElem.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_CustomElem(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public byte[] getData() {
        return internalJNI.CustomElem_data_get(this.swigCPtr, this);
    }

    public byte[] getDesc() {
        return internalJNI.CustomElem_desc_get(this.swigCPtr, this);
    }

    public byte[] getExt() {
        return internalJNI.CustomElem_ext_get(this.swigCPtr, this);
    }

    public byte[] getSound() {
        return internalJNI.CustomElem_sound_get(this.swigCPtr, this);
    }

    public void setData(byte[] bArr) {
        internalJNI.CustomElem_data_set(this.swigCPtr, this, bArr);
    }

    public void setDesc(byte[] bArr) {
        internalJNI.CustomElem_desc_set(this.swigCPtr, this, bArr);
    }

    public void setExt(byte[] bArr) {
        internalJNI.CustomElem_ext_set(this.swigCPtr, this, bArr);
    }

    public void setSound(byte[] bArr) {
        internalJNI.CustomElem_sound_set(this.swigCPtr, this, bArr);
    }
}

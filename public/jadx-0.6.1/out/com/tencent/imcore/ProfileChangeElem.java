package com.tencent.imcore;

public class ProfileChangeElem {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public ProfileChangeElem() {
        this(internalJNI.new_ProfileChangeElem(), true);
    }

    protected ProfileChangeElem(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(ProfileChangeElem profileChangeElem) {
        return profileChangeElem == null ? 0 : profileChangeElem.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_ProfileChangeElem(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getFrom() {
        return internalJNI.ProfileChangeElem_from_get(this.swigCPtr, this);
    }

    public byte[] getNick() {
        return internalJNI.ProfileChangeElem_nick_get(this.swigCPtr, this);
    }

    public long getType() {
        return internalJNI.ProfileChangeElem_type_get(this.swigCPtr, this);
    }

    public void setFrom(String str) {
        internalJNI.ProfileChangeElem_from_set(this.swigCPtr, this, str);
    }

    public void setNick(byte[] bArr) {
        internalJNI.ProfileChangeElem_nick_set(this.swigCPtr, this, bArr);
    }

    public void setType(long j) {
        internalJNI.ProfileChangeElem_type_set(this.swigCPtr, this, j);
    }
}

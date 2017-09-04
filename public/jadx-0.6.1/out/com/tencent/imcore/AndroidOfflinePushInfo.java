package com.tencent.imcore;

public class AndroidOfflinePushInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public AndroidOfflinePushInfo() {
        this(internalJNI.new_AndroidOfflinePushInfo(), true);
    }

    protected AndroidOfflinePushInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(AndroidOfflinePushInfo androidOfflinePushInfo) {
        return androidOfflinePushInfo == null ? 0 : androidOfflinePushInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_AndroidOfflinePushInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getNotifyMode() {
        return internalJNI.AndroidOfflinePushInfo_notifyMode_get(this.swigCPtr, this);
    }

    public byte[] getSound() {
        return internalJNI.AndroidOfflinePushInfo_sound_get(this.swigCPtr, this);
    }

    public byte[] getTitle() {
        return internalJNI.AndroidOfflinePushInfo_title_get(this.swigCPtr, this);
    }

    public void setNotifyMode(long j) {
        internalJNI.AndroidOfflinePushInfo_notifyMode_set(this.swigCPtr, this, j);
    }

    public void setSound(byte[] bArr) {
        internalJNI.AndroidOfflinePushInfo_sound_set(this.swigCPtr, this, bArr);
    }

    public void setTitle(byte[] bArr) {
        internalJNI.AndroidOfflinePushInfo_title_set(this.swigCPtr, this, bArr);
    }
}

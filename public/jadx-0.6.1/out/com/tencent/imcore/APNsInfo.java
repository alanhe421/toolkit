package com.tencent.imcore;

public class APNsInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public APNsInfo() {
        this(internalJNI.new_APNsInfo(), true);
    }

    protected APNsInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(APNsInfo aPNsInfo) {
        return aPNsInfo == null ? 0 : aPNsInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_APNsInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getBadgeMode() {
        return internalJNI.APNsInfo_badgeMode_get(this.swigCPtr, this);
    }

    public byte[] getSound() {
        return internalJNI.APNsInfo_sound_get(this.swigCPtr, this);
    }

    public void setBadgeMode(long j) {
        internalJNI.APNsInfo_badgeMode_set(this.swigCPtr, this, j);
    }

    public void setSound(byte[] bArr) {
        internalJNI.APNsInfo_sound_set(this.swigCPtr, this, bArr);
    }
}

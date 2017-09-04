package com.tencent.imcore;

public class OfflinePushInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public OfflinePushInfo() {
        this(internalJNI.new_OfflinePushInfo(), true);
    }

    protected OfflinePushInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OfflinePushInfo offlinePushInfo) {
        return offlinePushInfo == null ? 0 : offlinePushInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_OfflinePushInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public AndroidOfflinePushInfo getAndroidInfo() {
        long OfflinePushInfo_androidInfo_get = internalJNI.OfflinePushInfo_androidInfo_get(this.swigCPtr, this);
        return OfflinePushInfo_androidInfo_get == 0 ? null : new AndroidOfflinePushInfo(OfflinePushInfo_androidInfo_get, false);
    }

    public APNsInfo getApns() {
        long OfflinePushInfo_apns_get = internalJNI.OfflinePushInfo_apns_get(this.swigCPtr, this);
        return OfflinePushInfo_apns_get == 0 ? null : new APNsInfo(OfflinePushInfo_apns_get, false);
    }

    public byte[] getDesc() {
        return internalJNI.OfflinePushInfo_desc_get(this.swigCPtr, this);
    }

    public byte[] getExt() {
        return internalJNI.OfflinePushInfo_ext_get(this.swigCPtr, this);
    }

    public long getFlag() {
        return internalJNI.OfflinePushInfo_flag_get(this.swigCPtr, this);
    }

    public boolean getIsValid() {
        return internalJNI.OfflinePushInfo_isValid_get(this.swigCPtr, this);
    }

    public void setAndroidInfo(AndroidOfflinePushInfo androidOfflinePushInfo) {
        internalJNI.OfflinePushInfo_androidInfo_set(this.swigCPtr, this, AndroidOfflinePushInfo.getCPtr(androidOfflinePushInfo), androidOfflinePushInfo);
    }

    public void setApns(APNsInfo aPNsInfo) {
        internalJNI.OfflinePushInfo_apns_set(this.swigCPtr, this, APNsInfo.getCPtr(aPNsInfo), aPNsInfo);
    }

    public void setDesc(byte[] bArr) {
        internalJNI.OfflinePushInfo_desc_set(this.swigCPtr, this, bArr);
    }

    public void setExt(byte[] bArr) {
        internalJNI.OfflinePushInfo_ext_set(this.swigCPtr, this, bArr);
    }

    public void setFlag(long j) {
        internalJNI.OfflinePushInfo_flag_set(this.swigCPtr, this, j);
    }

    public void setIsValid(boolean z) {
        internalJNI.OfflinePushInfo_isValid_set(this.swigCPtr, this, z);
    }
}

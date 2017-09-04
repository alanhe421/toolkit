package com.tencent.imcore;

public class SNSProfileItem {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public SNSProfileItem() {
        this(internalJNI.new_SNSProfileItem(), true);
    }

    protected SNSProfileItem(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(SNSProfileItem sNSProfileItem) {
        return sNSProfileItem == null ? 0 : sNSProfileItem.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_SNSProfileItem(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public BytesMap getMpCustom() {
        long SNSProfileItem_mpCustom_get = internalJNI.SNSProfileItem_mpCustom_get(this.swigCPtr, this);
        return SNSProfileItem_mpCustom_get == 0 ? null : new BytesMap(SNSProfileItem_mpCustom_get, false);
    }

    public BytesMap getMpProfiles() {
        long SNSProfileItem_mpProfiles_get = internalJNI.SNSProfileItem_mpProfiles_get(this.swigCPtr, this);
        return SNSProfileItem_mpProfiles_get == 0 ? null : new BytesMap(SNSProfileItem_mpProfiles_get, false);
    }

    public String getSIdentifier() {
        return internalJNI.SNSProfileItem_sIdentifier_get(this.swigCPtr, this);
    }

    public void setMpCustom(BytesMap bytesMap) {
        internalJNI.SNSProfileItem_mpCustom_set(this.swigCPtr, this, BytesMap.getCPtr(bytesMap), bytesMap);
    }

    public void setMpProfiles(BytesMap bytesMap) {
        internalJNI.SNSProfileItem_mpProfiles_set(this.swigCPtr, this, BytesMap.getCPtr(bytesMap), bytesMap);
    }

    public void setSIdentifier(String str) {
        internalJNI.SNSProfileItem_sIdentifier_set(this.swigCPtr, this, str);
    }
}

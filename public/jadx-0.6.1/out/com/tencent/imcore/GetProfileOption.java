package com.tencent.imcore;

public class GetProfileOption {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GetProfileOption() {
        this(internalJNI.new_GetProfileOption(), true);
    }

    protected GetProfileOption(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GetProfileOption getProfileOption) {
        return getProfileOption == null ? 0 : getProfileOption.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GetProfileOption(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public BytesMap getCustom_info() {
        long GetProfileOption_custom_info_get = internalJNI.GetProfileOption_custom_info_get(this.swigCPtr, this);
        return GetProfileOption_custom_info_get == 0 ? null : new BytesMap(GetProfileOption_custom_info_get, false);
    }

    public long getFlag() {
        return internalJNI.GetProfileOption_flag_get(this.swigCPtr, this);
    }

    public void setCustom_info(BytesMap bytesMap) {
        internalJNI.GetProfileOption_custom_info_set(this.swigCPtr, this, BytesMap.getCPtr(bytesMap), bytesMap);
    }

    public void setFlag(long j) {
        internalJNI.GetProfileOption_flag_set(this.swigCPtr, this, j);
    }
}

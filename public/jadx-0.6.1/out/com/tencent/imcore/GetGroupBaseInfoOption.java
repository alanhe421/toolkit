package com.tencent.imcore;

public class GetGroupBaseInfoOption {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GetGroupBaseInfoOption() {
        this(internalJNI.new_GetGroupBaseInfoOption(), true);
    }

    protected GetGroupBaseInfoOption(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GetGroupBaseInfoOption getGroupBaseInfoOption) {
        return getGroupBaseInfoOption == null ? 0 : getGroupBaseInfoOption.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GetGroupBaseInfoOption(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public BytesMap getCustom_info() {
        long GetGroupBaseInfoOption_custom_info_get = internalJNI.GetGroupBaseInfoOption_custom_info_get(this.swigCPtr, this);
        return GetGroupBaseInfoOption_custom_info_get == 0 ? null : new BytesMap(GetGroupBaseInfoOption_custom_info_get, false);
    }

    public long getFlag() {
        return internalJNI.GetGroupBaseInfoOption_flag_get(this.swigCPtr, this);
    }

    public StrVec getGroups() {
        long GetGroupBaseInfoOption_groups_get = internalJNI.GetGroupBaseInfoOption_groups_get(this.swigCPtr, this);
        return GetGroupBaseInfoOption_groups_get == 0 ? null : new StrVec(GetGroupBaseInfoOption_groups_get, false);
    }

    public void setCustom_info(BytesMap bytesMap) {
        internalJNI.GetGroupBaseInfoOption_custom_info_set(this.swigCPtr, this, BytesMap.getCPtr(bytesMap), bytesMap);
    }

    public void setFlag(long j) {
        internalJNI.GetGroupBaseInfoOption_flag_set(this.swigCPtr, this, j);
    }

    public void setGroups(StrVec strVec) {
        internalJNI.GetGroupBaseInfoOption_groups_set(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec);
    }
}

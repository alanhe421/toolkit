package com.tencent.imcore;

public class GetGroupMemInfoOption {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GetGroupMemInfoOption() {
        this(internalJNI.new_GetGroupMemInfoOption(), true);
    }

    protected GetGroupMemInfoOption(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GetGroupMemInfoOption getGroupMemInfoOption) {
        return getGroupMemInfoOption == null ? 0 : getGroupMemInfoOption.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GetGroupMemInfoOption(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public BytesMap getCustom_info() {
        long GetGroupMemInfoOption_custom_info_get = internalJNI.GetGroupMemInfoOption_custom_info_get(this.swigCPtr, this);
        return GetGroupMemInfoOption_custom_info_get == 0 ? null : new BytesMap(GetGroupMemInfoOption_custom_info_get, false);
    }

    public GroupMemRoleFilter getFilter() {
        return GroupMemRoleFilter.swigToEnum(internalJNI.GetGroupMemInfoOption_filter_get(this.swigCPtr, this));
    }

    public long getFlag() {
        return internalJNI.GetGroupMemInfoOption_flag_get(this.swigCPtr, this);
    }

    public String getGroup_id() {
        return internalJNI.GetGroupMemInfoOption_group_id_get(this.swigCPtr, this);
    }

    public StrVec getMembers() {
        long GetGroupMemInfoOption_members_get = internalJNI.GetGroupMemInfoOption_members_get(this.swigCPtr, this);
        return GetGroupMemInfoOption_members_get == 0 ? null : new StrVec(GetGroupMemInfoOption_members_get, false);
    }

    public void setCustom_info(BytesMap bytesMap) {
        internalJNI.GetGroupMemInfoOption_custom_info_set(this.swigCPtr, this, BytesMap.getCPtr(bytesMap), bytesMap);
    }

    public void setFilter(GroupMemRoleFilter groupMemRoleFilter) {
        internalJNI.GetGroupMemInfoOption_filter_set(this.swigCPtr, this, groupMemRoleFilter.swigValue());
    }

    public void setFlag(long j) {
        internalJNI.GetGroupMemInfoOption_flag_set(this.swigCPtr, this, j);
    }

    public void setGroup_id(String str) {
        internalJNI.GetGroupMemInfoOption_group_id_set(this.swigCPtr, this, str);
    }

    public void setMembers(StrVec strVec) {
        internalJNI.GetGroupMemInfoOption_members_set(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec);
    }
}

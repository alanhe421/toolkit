package com.tencent.imcore;

public class ModifyGroupMemberInfoOption {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public ModifyGroupMemberInfoOption() {
        this(internalJNI.new_ModifyGroupMemberInfoOption(), true);
    }

    protected ModifyGroupMemberInfoOption(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(ModifyGroupMemberInfoOption modifyGroupMemberInfoOption) {
        return modifyGroupMemberInfoOption == null ? 0 : modifyGroupMemberInfoOption.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_ModifyGroupMemberInfoOption(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public BytesMap getCustom_info() {
        long ModifyGroupMemberInfoOption_custom_info_get = internalJNI.ModifyGroupMemberInfoOption_custom_info_get(this.swigCPtr, this);
        return ModifyGroupMemberInfoOption_custom_info_get == 0 ? null : new BytesMap(ModifyGroupMemberInfoOption_custom_info_get, false);
    }

    public long getFlag() {
        return internalJNI.ModifyGroupMemberInfoOption_flag_get(this.swigCPtr, this);
    }

    public String getGroup_id() {
        return internalJNI.ModifyGroupMemberInfoOption_group_id_get(this.swigCPtr, this);
    }

    public String getMember() {
        return internalJNI.ModifyGroupMemberInfoOption_member_get(this.swigCPtr, this);
    }

    public long getMsg_flag() {
        return internalJNI.ModifyGroupMemberInfoOption_msg_flag_get(this.swigCPtr, this);
    }

    public byte[] getName_card() {
        return internalJNI.ModifyGroupMemberInfoOption_name_card_get(this.swigCPtr, this);
    }

    public long getRole() {
        return internalJNI.ModifyGroupMemberInfoOption_role_get(this.swigCPtr, this);
    }

    public long getShutup_time() {
        return internalJNI.ModifyGroupMemberInfoOption_shutup_time_get(this.swigCPtr, this);
    }

    public void setCustom_info(BytesMap bytesMap) {
        internalJNI.ModifyGroupMemberInfoOption_custom_info_set(this.swigCPtr, this, BytesMap.getCPtr(bytesMap), bytesMap);
    }

    public void setFlag(long j) {
        internalJNI.ModifyGroupMemberInfoOption_flag_set(this.swigCPtr, this, j);
    }

    public void setGroup_id(String str) {
        internalJNI.ModifyGroupMemberInfoOption_group_id_set(this.swigCPtr, this, str);
    }

    public void setMember(String str) {
        internalJNI.ModifyGroupMemberInfoOption_member_set(this.swigCPtr, this, str);
    }

    public void setMsg_flag(long j) {
        internalJNI.ModifyGroupMemberInfoOption_msg_flag_set(this.swigCPtr, this, j);
    }

    public void setName_card(byte[] bArr) {
        internalJNI.ModifyGroupMemberInfoOption_name_card_set(this.swigCPtr, this, bArr);
    }

    public void setRole(long j) {
        internalJNI.ModifyGroupMemberInfoOption_role_set(this.swigCPtr, this, j);
    }

    public void setShutup_time(long j) {
        internalJNI.ModifyGroupMemberInfoOption_shutup_time_set(this.swigCPtr, this, j);
    }
}

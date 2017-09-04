package com.tencent.imcore;

public class NewGroupMemberInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public NewGroupMemberInfo() {
        this(internalJNI.new_NewGroupMemberInfo(), true);
    }

    protected NewGroupMemberInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(NewGroupMemberInfo newGroupMemberInfo) {
        return newGroupMemberInfo == null ? 0 : newGroupMemberInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_NewGroupMemberInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public BytesMap getCustom_info() {
        long NewGroupMemberInfo_custom_info_get = internalJNI.NewGroupMemberInfo_custom_info_get(this.swigCPtr, this);
        return NewGroupMemberInfo_custom_info_get == 0 ? null : new BytesMap(NewGroupMemberInfo_custom_info_get, false);
    }

    public String getIdentifier() {
        return internalJNI.NewGroupMemberInfo_identifier_get(this.swigCPtr, this);
    }

    public int getMember_role() {
        return internalJNI.NewGroupMemberInfo_member_role_get(this.swigCPtr, this);
    }

    public int getMsg_flag() {
        return internalJNI.NewGroupMemberInfo_msg_flag_get(this.swigCPtr, this);
    }

    public byte[] getName_card() {
        return internalJNI.NewGroupMemberInfo_name_card_get(this.swigCPtr, this);
    }

    public void setCustom_info(BytesMap bytesMap) {
        internalJNI.NewGroupMemberInfo_custom_info_set(this.swigCPtr, this, BytesMap.getCPtr(bytesMap), bytesMap);
    }

    public void setIdentifier(String str) {
        internalJNI.NewGroupMemberInfo_identifier_set(this.swigCPtr, this, str);
    }

    public void setMember_role(int i) {
        internalJNI.NewGroupMemberInfo_member_role_set(this.swigCPtr, this, i);
    }

    public void setMsg_flag(int i) {
        internalJNI.NewGroupMemberInfo_msg_flag_set(this.swigCPtr, this, i);
    }

    public void setName_card(byte[] bArr) {
        internalJNI.NewGroupMemberInfo_name_card_set(this.swigCPtr, this, bArr);
    }
}

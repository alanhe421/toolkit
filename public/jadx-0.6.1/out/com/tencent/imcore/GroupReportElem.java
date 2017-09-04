package com.tencent.imcore;

public class GroupReportElem {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GroupReportElem() {
        this(internalJNI.new_GroupReportElem(), true);
    }

    protected GroupReportElem(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GroupReportElem groupReportElem) {
        return groupReportElem == null ? 0 : groupReportElem.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GroupReportElem(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public byte[] getAuthentication() {
        return internalJNI.GroupReportElem_authentication_get(this.swigCPtr, this);
    }

    public String getGroup() {
        return internalJNI.GroupReportElem_group_get(this.swigCPtr, this);
    }

    public String getGroup_name() {
        return internalJNI.GroupReportElem_group_name_get(this.swigCPtr, this);
    }

    public byte[] getMsg() {
        return internalJNI.GroupReportElem_msg_get(this.swigCPtr, this);
    }

    public long getMsg_key() {
        return internalJNI.GroupReportElem_msg_key_get(this.swigCPtr, this);
    }

    public NewGroupMemberInfo getOperator_group_member_info() {
        long GroupReportElem_operator_group_member_info_get = internalJNI.GroupReportElem_operator_group_member_info_get(this.swigCPtr, this);
        return GroupReportElem_operator_group_member_info_get == 0 ? null : new NewGroupMemberInfo(GroupReportElem_operator_group_member_info_get, false);
    }

    public String getOperator_user() {
        return internalJNI.GroupReportElem_operator_user_get(this.swigCPtr, this);
    }

    public FriendProfile getOperator_user_info() {
        long GroupReportElem_operator_user_info_get = internalJNI.GroupReportElem_operator_user_info_get(this.swigCPtr, this);
        return GroupReportElem_operator_user_info_get == 0 ? null : new FriendProfile(GroupReportElem_operator_user_info_get, false);
    }

    public byte[] getPlatform() {
        return internalJNI.GroupReportElem_platform_get(this.swigCPtr, this);
    }

    public long getType() {
        return internalJNI.GroupReportElem_type_get(this.swigCPtr, this);
    }

    public byte[] getUser_data() {
        return internalJNI.GroupReportElem_user_data_get(this.swigCPtr, this);
    }

    public void setAuthentication(byte[] bArr) {
        internalJNI.GroupReportElem_authentication_set(this.swigCPtr, this, bArr);
    }

    public void setGroup(String str) {
        internalJNI.GroupReportElem_group_set(this.swigCPtr, this, str);
    }

    public void setGroup_name(String str) {
        internalJNI.GroupReportElem_group_name_set(this.swigCPtr, this, str);
    }

    public void setMsg(byte[] bArr) {
        internalJNI.GroupReportElem_msg_set(this.swigCPtr, this, bArr);
    }

    public void setMsg_key(long j) {
        internalJNI.GroupReportElem_msg_key_set(this.swigCPtr, this, j);
    }

    public void setOperator_group_member_info(NewGroupMemberInfo newGroupMemberInfo) {
        internalJNI.GroupReportElem_operator_group_member_info_set(this.swigCPtr, this, NewGroupMemberInfo.getCPtr(newGroupMemberInfo), newGroupMemberInfo);
    }

    public void setOperator_user(String str) {
        internalJNI.GroupReportElem_operator_user_set(this.swigCPtr, this, str);
    }

    public void setOperator_user_info(FriendProfile friendProfile) {
        internalJNI.GroupReportElem_operator_user_info_set(this.swigCPtr, this, FriendProfile.getCPtr(friendProfile), friendProfile);
    }

    public void setPlatform(byte[] bArr) {
        internalJNI.GroupReportElem_platform_set(this.swigCPtr, this, bArr);
    }

    public void setType(long j) {
        internalJNI.GroupReportElem_type_set(this.swigCPtr, this, j);
    }

    public void setUser_data(byte[] bArr) {
        internalJNI.GroupReportElem_user_data_set(this.swigCPtr, this, bArr);
    }
}

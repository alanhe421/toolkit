package com.tencent.imcore;

public class GroupTipsElem {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GroupTipsElem() {
        this(internalJNI.new_GroupTipsElem(), true);
    }

    protected GroupTipsElem(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GroupTipsElem groupTipsElem) {
        return groupTipsElem == null ? 0 : groupTipsElem.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GroupTipsElem(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public SWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t getChanged_group_member_info() {
        long GroupTipsElem_changed_group_member_info_get = internalJNI.GroupTipsElem_changed_group_member_info_get(this.swigCPtr, this);
        return GroupTipsElem_changed_group_member_info_get == 0 ? null : new SWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t(GroupTipsElem_changed_group_member_info_get, false);
    }

    public SWIGTYPE_p_std__mapT_std__string_imcore__FriendProfile_t getChanged_user_info() {
        long GroupTipsElem_changed_user_info_get = internalJNI.GroupTipsElem_changed_user_info_get(this.swigCPtr, this);
        return GroupTipsElem_changed_user_info_get == 0 ? null : new SWIGTYPE_p_std__mapT_std__string_imcore__FriendProfile_t(GroupTipsElem_changed_user_info_get, false);
    }

    public GroupTipsElem_GroupInfoVec getGroup_change_list() {
        long GroupTipsElem_group_change_list_get = internalJNI.GroupTipsElem_group_change_list_get(this.swigCPtr, this);
        return GroupTipsElem_group_change_list_get == 0 ? null : new GroupTipsElem_GroupInfoVec(GroupTipsElem_group_change_list_get, false);
    }

    public byte[] getGroup_id() {
        return internalJNI.GroupTipsElem_group_id_get(this.swigCPtr, this);
    }

    public byte[] getGroup_name() {
        return internalJNI.GroupTipsElem_group_name_get(this.swigCPtr, this);
    }

    public GroupTipsElem_MemberInfoVec getMember_change_list() {
        long GroupTipsElem_member_change_list_get = internalJNI.GroupTipsElem_member_change_list_get(this.swigCPtr, this);
        return GroupTipsElem_member_change_list_get == 0 ? null : new GroupTipsElem_MemberInfoVec(GroupTipsElem_member_change_list_get, false);
    }

    public long getMember_num() {
        return internalJNI.GroupTipsElem_member_num_get(this.swigCPtr, this);
    }

    public NewGroupMemberInfo getOp_group_member_info() {
        long GroupTipsElem_op_group_member_info_get = internalJNI.GroupTipsElem_op_group_member_info_get(this.swigCPtr, this);
        return GroupTipsElem_op_group_member_info_get == 0 ? null : new NewGroupMemberInfo(GroupTipsElem_op_group_member_info_get, false);
    }

    public String getOp_user() {
        return internalJNI.GroupTipsElem_op_user_get(this.swigCPtr, this);
    }

    public FriendProfile getOp_user_info() {
        long GroupTipsElem_op_user_info_get = internalJNI.GroupTipsElem_op_user_info_get(this.swigCPtr, this);
        return GroupTipsElem_op_user_info_get == 0 ? null : new FriendProfile(GroupTipsElem_op_user_info_get, false);
    }

    public byte[] getPlatform() {
        return internalJNI.GroupTipsElem_platform_get(this.swigCPtr, this);
    }

    public long getTime() {
        return internalJNI.GroupTipsElem_time_get(this.swigCPtr, this);
    }

    public int getType() {
        return internalJNI.GroupTipsElem_type_get(this.swigCPtr, this);
    }

    public StrVec getUser_list() {
        long GroupTipsElem_user_list_get = internalJNI.GroupTipsElem_user_list_get(this.swigCPtr, this);
        return GroupTipsElem_user_list_get == 0 ? null : new StrVec(GroupTipsElem_user_list_get, false);
    }

    public void setChanged_group_member_info(SWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t sWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t) {
        internalJNI.GroupTipsElem_changed_group_member_info_set(this.swigCPtr, this, SWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t.getCPtr(sWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t));
    }

    public void setChanged_user_info(SWIGTYPE_p_std__mapT_std__string_imcore__FriendProfile_t sWIGTYPE_p_std__mapT_std__string_imcore__FriendProfile_t) {
        internalJNI.GroupTipsElem_changed_user_info_set(this.swigCPtr, this, SWIGTYPE_p_std__mapT_std__string_imcore__FriendProfile_t.getCPtr(sWIGTYPE_p_std__mapT_std__string_imcore__FriendProfile_t));
    }

    public void setGroup_change_list(GroupTipsElem_GroupInfoVec groupTipsElem_GroupInfoVec) {
        internalJNI.GroupTipsElem_group_change_list_set(this.swigCPtr, this, GroupTipsElem_GroupInfoVec.getCPtr(groupTipsElem_GroupInfoVec), groupTipsElem_GroupInfoVec);
    }

    public void setGroup_id(byte[] bArr) {
        internalJNI.GroupTipsElem_group_id_set(this.swigCPtr, this, bArr);
    }

    public void setGroup_name(byte[] bArr) {
        internalJNI.GroupTipsElem_group_name_set(this.swigCPtr, this, bArr);
    }

    public void setMember_change_list(GroupTipsElem_MemberInfoVec groupTipsElem_MemberInfoVec) {
        internalJNI.GroupTipsElem_member_change_list_set(this.swigCPtr, this, GroupTipsElem_MemberInfoVec.getCPtr(groupTipsElem_MemberInfoVec), groupTipsElem_MemberInfoVec);
    }

    public void setMember_num(long j) {
        internalJNI.GroupTipsElem_member_num_set(this.swigCPtr, this, j);
    }

    public void setOp_group_member_info(NewGroupMemberInfo newGroupMemberInfo) {
        internalJNI.GroupTipsElem_op_group_member_info_set(this.swigCPtr, this, NewGroupMemberInfo.getCPtr(newGroupMemberInfo), newGroupMemberInfo);
    }

    public void setOp_user(String str) {
        internalJNI.GroupTipsElem_op_user_set(this.swigCPtr, this, str);
    }

    public void setOp_user_info(FriendProfile friendProfile) {
        internalJNI.GroupTipsElem_op_user_info_set(this.swigCPtr, this, FriendProfile.getCPtr(friendProfile), friendProfile);
    }

    public void setPlatform(byte[] bArr) {
        internalJNI.GroupTipsElem_platform_set(this.swigCPtr, this, bArr);
    }

    public void setTime(long j) {
        internalJNI.GroupTipsElem_time_set(this.swigCPtr, this, j);
    }

    public void setType(int i) {
        internalJNI.GroupTipsElem_type_set(this.swigCPtr, this, i);
    }

    public void setUser_list(StrVec strVec) {
        internalJNI.GroupTipsElem_user_list_set(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec);
    }
}

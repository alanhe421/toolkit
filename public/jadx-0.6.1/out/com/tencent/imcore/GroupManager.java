package com.tencent.imcore;

public class GroupManager {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected GroupManager(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public GroupManager(String str) {
        this(internalJNI.new_GroupManager(str), true);
    }

    protected static long getCPtr(GroupManager groupManager) {
        return groupManager == null ? 0 : groupManager.swigCPtr;
    }

    public boolean applyJoinGroup(String str, String str2, IGroupNotifyCallback iGroupNotifyCallback) {
        return internalJNI.GroupManager_applyJoinGroup(this.swigCPtr, this, str, str2, IGroupNotifyCallback.getCPtr(iGroupNotifyCallback), iGroupNotifyCallback);
    }

    public boolean createGroup(NewGroupInfo newGroupInfo, ICreateGroupCallback iCreateGroupCallback) {
        return internalJNI.GroupManager_createGroup__SWIG_1(this.swigCPtr, this, NewGroupInfo.getCPtr(newGroupInfo), newGroupInfo, ICreateGroupCallback.getCPtr(iCreateGroupCallback), iCreateGroupCallback);
    }

    public boolean createGroup(String str, StrVec strVec, byte[] bArr, ICreateGroupCallback iCreateGroupCallback) {
        return internalJNI.GroupManager_createGroup__SWIG_0(this.swigCPtr, this, str, StrVec.getCPtr(strVec), strVec, bArr, ICreateGroupCallback.getCPtr(iCreateGroupCallback), iCreateGroupCallback);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GroupManager(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean deleteGroup(String str, IGroupNotifyCallback iGroupNotifyCallback) {
        return internalJNI.GroupManager_deleteGroup(this.swigCPtr, this, str, IGroupNotifyCallback.getCPtr(iGroupNotifyCallback), iGroupNotifyCallback);
    }

    public boolean deleteGroupMember(String str, StrVec strVec, IGroupMemberResultCallback iGroupMemberResultCallback) {
        return internalJNI.GroupManager_deleteGroupMember__SWIG_1(this.swigCPtr, this, str, StrVec.getCPtr(strVec), strVec, IGroupMemberResultCallback.getCPtr(iGroupMemberResultCallback), iGroupMemberResultCallback);
    }

    public boolean deleteGroupMember(String str, StrVec strVec, IGroupMemberResultCallback iGroupMemberResultCallback, byte[] bArr) {
        return internalJNI.GroupManager_deleteGroupMember__SWIG_0(this.swigCPtr, this, str, StrVec.getCPtr(strVec), strVec, IGroupMemberResultCallback.getCPtr(iGroupMemberResultCallback), iGroupMemberResultCallback, bArr);
    }

    protected void finalize() {
        delete();
    }

    public boolean getGroupBaseInfo(GetGroupBaseInfoOption getGroupBaseInfoOption, IGroupInfoListCallback iGroupInfoListCallback) {
        return internalJNI.GroupManager_getGroupBaseInfo__SWIG_1(this.swigCPtr, this, GetGroupBaseInfoOption.getCPtr(getGroupBaseInfoOption), getGroupBaseInfoOption, IGroupInfoListCallback.getCPtr(iGroupInfoListCallback), iGroupInfoListCallback);
    }

    public boolean getGroupBaseInfo(StrVec strVec, IGroupInfoListCallback iGroupInfoListCallback) {
        return internalJNI.GroupManager_getGroupBaseInfo__SWIG_0(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec, IGroupInfoListCallback.getCPtr(iGroupInfoListCallback), iGroupInfoListCallback);
    }

    public boolean getGroupList(boolean z, IGroupListCallback iGroupListCallback) {
        return internalJNI.GroupManager_getGroupList(this.swigCPtr, this, z, IGroupListCallback.getCPtr(iGroupListCallback), iGroupListCallback);
    }

    public boolean getGroupMembers(String str, IGroupMemberCallback iGroupMemberCallback) {
        return internalJNI.GroupManager_getGroupMembers(this.swigCPtr, this, str, IGroupMemberCallback.getCPtr(iGroupMemberCallback), iGroupMemberCallback);
    }

    public boolean getGroupMembersByFilter(String str, long j, GroupMemRoleFilter groupMemRoleFilter, BytesVec bytesVec, long j2, IGroupMemberCallbackV2 iGroupMemberCallbackV2) {
        return internalJNI.GroupManager_getGroupMembersByFilter(this.swigCPtr, this, str, j, groupMemRoleFilter.swigValue(), BytesVec.getCPtr(bytesVec), bytesVec, j2, IGroupMemberCallbackV2.getCPtr(iGroupMemberCallbackV2), iGroupMemberCallbackV2);
    }

    public boolean getGroupMembersInfo(GetGroupMemInfoOption getGroupMemInfoOption, IGroupMemberCallback iGroupMemberCallback) {
        return internalJNI.GroupManager_getGroupMembersInfo__SWIG_1(this.swigCPtr, this, GetGroupMemInfoOption.getCPtr(getGroupMemInfoOption), getGroupMemInfoOption, IGroupMemberCallback.getCPtr(iGroupMemberCallback), iGroupMemberCallback);
    }

    public boolean getGroupMembersInfo(String str, StrVec strVec, IGroupMemberCallback iGroupMemberCallback) {
        return internalJNI.GroupManager_getGroupMembersInfo__SWIG_0(this.swigCPtr, this, str, StrVec.getCPtr(strVec), strVec, IGroupMemberCallback.getCPtr(iGroupMemberCallback), iGroupMemberCallback);
    }

    public boolean getGroupMembersV2(String str, long j, BytesVec bytesVec, long j2, IGroupMemberCallbackV2 iGroupMemberCallbackV2) {
        return internalJNI.GroupManager_getGroupMembersV2(this.swigCPtr, this, str, j, BytesVec.getCPtr(bytesVec), bytesVec, j2, IGroupMemberCallbackV2.getCPtr(iGroupMemberCallbackV2), iGroupMemberCallbackV2);
    }

    public boolean getGroupPublicInfo(StrVec strVec, IGroupInfoListCallback iGroupInfoListCallback) {
        return internalJNI.GroupManager_getGroupPublicInfo(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec, IGroupInfoListCallback.getCPtr(iGroupInfoListCallback), iGroupInfoListCallback);
    }

    public boolean getGroupPublicInfoV2(StrVec strVec, long j, BytesVec bytesVec, IGroupInfoListCallback iGroupInfoListCallback) {
        return internalJNI.GroupManager_getGroupPublicInfoV2(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec, j, BytesVec.getCPtr(bytesVec), bytesVec, IGroupInfoListCallback.getCPtr(iGroupInfoListCallback), iGroupInfoListCallback);
    }

    public boolean getMsgSeq(String str, SWIGTYPE_p_std__functionT_void_fF_t sWIGTYPE_p_std__functionT_void_fF_t, SWIGTYPE_p_std__functionT_void_fint_std__string_const_RF_t sWIGTYPE_p_std__functionT_void_fint_std__string_const_RF_t) {
        return internalJNI.GroupManager_getMsgSeq(this.swigCPtr, this, str, SWIGTYPE_p_std__functionT_void_fF_t.getCPtr(sWIGTYPE_p_std__functionT_void_fF_t), SWIGTYPE_p_std__functionT_void_fint_std__string_const_RF_t.getCPtr(sWIGTYPE_p_std__functionT_void_fint_std__string_const_RF_t));
    }

    public boolean getPendency(GetGroupPendencyOption getGroupPendencyOption, IGroupGetPendencyCallback iGroupGetPendencyCallback) {
        return internalJNI.GroupManager_getPendency(this.swigCPtr, this, GetGroupPendencyOption.getCPtr(getGroupPendencyOption), getGroupPendencyOption, IGroupGetPendencyCallback.getCPtr(iGroupGetPendencyCallback), iGroupGetPendencyCallback);
    }

    public boolean getSelfInfo(String str, IGroupMemberCallback iGroupMemberCallback) {
        return internalJNI.GroupManager_getSelfInfo(this.swigCPtr, this, str, IGroupMemberCallback.getCPtr(iGroupMemberCallback), iGroupMemberCallback);
    }

    public boolean handleInviteRequest(String str, String str2, byte[] bArr, byte[] bArr2, long j, long j2, byte[] bArr3, IGroupNotifyCallback iGroupNotifyCallback) {
        return internalJNI.GroupManager_handleInviteRequest(this.swigCPtr, this, str, str2, bArr, bArr2, j, j2, bArr3, IGroupNotifyCallback.getCPtr(iGroupNotifyCallback), iGroupNotifyCallback);
    }

    public boolean handleJoinRequest(String str, String str2, byte[] bArr, byte[] bArr2, long j, long j2, byte[] bArr3, IGroupNotifyCallback iGroupNotifyCallback) {
        return internalJNI.GroupManager_handleJoinRequest(this.swigCPtr, this, str, str2, bArr, bArr2, j, j2, bArr3, IGroupNotifyCallback.getCPtr(iGroupNotifyCallback), iGroupNotifyCallback);
    }

    public boolean inviteGroupMember(String str, StrVec strVec, IGroupMemberResultCallback iGroupMemberResultCallback) {
        return internalJNI.GroupManager_inviteGroupMember(this.swigCPtr, this, str, StrVec.getCPtr(strVec), strVec, IGroupMemberResultCallback.getCPtr(iGroupMemberResultCallback), iGroupMemberResultCallback);
    }

    public boolean modifyGroupBaseInfo(ModifyGroupBaseInfoOption modifyGroupBaseInfoOption, IGroupNotifyCallback iGroupNotifyCallback) {
        return internalJNI.GroupManager_modifyGroupBaseInfo__SWIG_1(this.swigCPtr, this, ModifyGroupBaseInfoOption.getCPtr(modifyGroupBaseInfoOption), modifyGroupBaseInfoOption, IGroupNotifyCallback.getCPtr(iGroupNotifyCallback), iGroupNotifyCallback);
    }

    public boolean modifyGroupBaseInfo(String str, long j, byte[] bArr, long j2, IGroupNotifyCallback iGroupNotifyCallback) {
        return internalJNI.GroupManager_modifyGroupBaseInfo__SWIG_0(this.swigCPtr, this, str, j, bArr, j2, IGroupNotifyCallback.getCPtr(iGroupNotifyCallback), iGroupNotifyCallback);
    }

    public boolean modifyGroupMemberInfo(ModifyGroupMemberInfoOption modifyGroupMemberInfoOption, IGroupNotifyCallback iGroupNotifyCallback) {
        return internalJNI.GroupManager_modifyGroupMemberInfo__SWIG_1(this.swigCPtr, this, ModifyGroupMemberInfoOption.getCPtr(modifyGroupMemberInfoOption), modifyGroupMemberInfoOption, IGroupNotifyCallback.getCPtr(iGroupNotifyCallback), iGroupNotifyCallback);
    }

    public boolean modifyGroupMemberInfo(String str, String str2, long j, long j2, IGroupNotifyCallback iGroupNotifyCallback) {
        return internalJNI.GroupManager_modifyGroupMemberInfo__SWIG_0(this.swigCPtr, this, str, str2, j, j2, IGroupNotifyCallback.getCPtr(iGroupNotifyCallback), iGroupNotifyCallback);
    }

    public boolean modifyGroupOwner(String str, String str2, IGroupNotifyCallback iGroupNotifyCallback) {
        return internalJNI.GroupManager_modifyGroupOwner(this.swigCPtr, this, str, str2, IGroupNotifyCallback.getCPtr(iGroupNotifyCallback), iGroupNotifyCallback);
    }

    public boolean pendencyReport(long j, IGroupNotifyCallback iGroupNotifyCallback) {
        return internalJNI.GroupManager_pendencyReport(this.swigCPtr, this, j, IGroupNotifyCallback.getCPtr(iGroupNotifyCallback), iGroupNotifyCallback);
    }

    public boolean quitGroup(String str, IGroupNotifyCallback iGroupNotifyCallback) {
        return internalJNI.GroupManager_quitGroup(this.swigCPtr, this, str, IGroupNotifyCallback.getCPtr(iGroupNotifyCallback), iGroupNotifyCallback);
    }

    public boolean searchGroupByName(String str, long j, BytesVec bytesVec, long j2, long j3, IGroupInfoListCallbackV2 iGroupInfoListCallbackV2) {
        return internalJNI.GroupManager_searchGroupByName(this.swigCPtr, this, str, j, BytesVec.getCPtr(bytesVec), bytesVec, j2, j3, IGroupInfoListCallbackV2.getCPtr(iGroupInfoListCallbackV2), iGroupInfoListCallbackV2);
    }
}

package com.tencent.imcore;

public class GroupAssistant {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected GroupAssistant(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public GroupAssistant(String str) {
        this(internalJNI.new_GroupAssistant(str), true);
    }

    protected static long getCPtr(GroupAssistant groupAssistant) {
        return groupAssistant == null ? 0 : groupAssistant.swigCPtr;
    }

    public boolean checkGroupModifyOption(ModifyGroupBaseInfoOption modifyGroupBaseInfoOption) {
        return internalJNI.GroupAssistant_checkGroupModifyOption(this.swigCPtr, this, ModifyGroupBaseInfoOption.getCPtr(modifyGroupBaseInfoOption), modifyGroupBaseInfoOption);
    }

    public boolean checkMemberModifyOption(ModifyGroupMemberInfoOption modifyGroupMemberInfoOption) {
        return internalJNI.GroupAssistant_checkMemberModifyOption(this.swigCPtr, this, ModifyGroupMemberInfoOption.getCPtr(modifyGroupMemberInfoOption), modifyGroupMemberInfoOption);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GroupAssistant(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public SWIGTYPE_p_std__shared_ptrT_imcore__GroupInfoCache_t getGroupInfoCache() {
        return new SWIGTYPE_p_std__shared_ptrT_imcore__GroupInfoCache_t(internalJNI.GroupAssistant_getGroupInfoCache(this.swigCPtr, this), true);
    }

    public GroupSettings getGroupSettings() {
        return new GroupSettings(internalJNI.GroupAssistant_getGroupSettings(this.swigCPtr, this), false);
    }

    public int getGroups(StrVec strVec, GroupCacheInfoVec groupCacheInfoVec) {
        return internalJNI.GroupAssistant_getGroups(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec, GroupCacheInfoVec.getCPtr(groupCacheInfoVec), groupCacheInfoVec);
    }

    public void init(IGroupNotifyCallback iGroupNotifyCallback) {
        internalJNI.GroupAssistant_init(this.swigCPtr, this, IGroupNotifyCallback.getCPtr(iGroupNotifyCallback), iGroupNotifyCallback);
    }

    public void initGroupSettings(GroupSettings groupSettings) {
        internalJNI.GroupAssistant_initGroupSettings(this.swigCPtr, this, GroupSettings.getCPtr(groupSettings), groupSettings);
    }

    public boolean isGroupExist(String str) {
        return internalJNI.GroupAssistant_isGroupExist(this.swigCPtr, this, str);
    }

    public void onGroupAdd(String str) {
        internalJNI.GroupAssistant_onGroupAdd(this.swigCPtr, this, str);
    }

    public void onGroupDelete(String str) {
        internalJNI.GroupAssistant_onGroupDelete(this.swigCPtr, this, str);
    }

    public void onGroupSystemEvent(String str, long j) {
        internalJNI.GroupAssistant_onGroupSystemEvent(this.swigCPtr, this, str, j);
    }

    public void onGroupTipsEvent(String str, GroupTipsElem groupTipsElem) {
        internalJNI.GroupAssistant_onGroupTipsEvent(this.swigCPtr, this, str, GroupTipsElem.getCPtr(groupTipsElem), groupTipsElem);
    }

    public void onGroupUpdate(ModifyGroupBaseInfoOption modifyGroupBaseInfoOption) {
        internalJNI.GroupAssistant_onGroupUpdate__SWIG_0(this.swigCPtr, this, ModifyGroupBaseInfoOption.getCPtr(modifyGroupBaseInfoOption), modifyGroupBaseInfoOption);
    }

    public void onGroupUpdate(String str, GroupTipsElem_GroupInfoVec groupTipsElem_GroupInfoVec) {
        internalJNI.GroupAssistant_onGroupUpdate__SWIG_1(this.swigCPtr, this, str, GroupTipsElem_GroupInfoVec.getCPtr(groupTipsElem_GroupInfoVec), groupTipsElem_GroupInfoVec);
    }

    public void onGroupUpdateOwner(String str, String str2) {
        internalJNI.GroupAssistant_onGroupUpdateOwner(this.swigCPtr, this, str, str2);
    }

    public void onGroupUpdateSelfInfo(String str, ModifyGroupMemberInfoOption modifyGroupMemberInfoOption) {
        internalJNI.GroupAssistant_onGroupUpdateSelfInfo(this.swigCPtr, this, str, ModifyGroupMemberInfoOption.getCPtr(modifyGroupMemberInfoOption), modifyGroupMemberInfoOption);
    }

    public void onMemberJoin(String str, SWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t sWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t) {
        internalJNI.GroupAssistant_onMemberJoin(this.swigCPtr, this, str, SWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t.getCPtr(sWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t));
    }

    public void onMemberQuit(String str, SWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t sWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t) {
        internalJNI.GroupAssistant_onMemberQuit(this.swigCPtr, this, str, SWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t.getCPtr(sWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t));
    }

    public void onMemberUpdate(String str, SWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t sWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t) {
        internalJNI.GroupAssistant_onMemberUpdate(this.swigCPtr, this, str, SWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t.getCPtr(sWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t));
    }

    public GetGroupBaseInfoOption prepareDefaultGetGroupOption(StrVec strVec) {
        return new GetGroupBaseInfoOption(internalJNI.GroupAssistant_prepareDefaultGetGroupOption(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec), true);
    }

    public GetGroupMemInfoOption prepareDefaultGetMemOption(String str, StrVec strVec) {
        return new GetGroupMemInfoOption(internalJNI.GroupAssistant_prepareDefaultGetMemOption(this.swigCPtr, this, str, StrVec.getCPtr(strVec), strVec), true);
    }

    public void setCallback(IGroupAssistantCallback iGroupAssistantCallback) {
        internalJNI.GroupAssistant_setCallback(this.swigCPtr, this, IGroupAssistantCallback.getCPtr(iGroupAssistantCallback), iGroupAssistantCallback);
    }

    public void sync(IGroupNotifyCallback iGroupNotifyCallback) {
        internalJNI.GroupAssistant_sync(this.swigCPtr, this, IGroupNotifyCallback.getCPtr(iGroupNotifyCallback), iGroupNotifyCallback);
    }
}

package com.tencent.imcore;

public class IGroupAssistantCallback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IGroupAssistantCallback() {
        this(internalJNI.new_IGroupAssistantCallback(), true);
        internalJNI.IGroupAssistantCallback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IGroupAssistantCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IGroupAssistantCallback iGroupAssistantCallback) {
        return iGroupAssistantCallback == null ? 0 : iGroupAssistantCallback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IGroupAssistantCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public void onGroupAdd(GroupCacheInfo groupCacheInfo) {
        if (getClass() == IGroupAssistantCallback.class) {
            internalJNI.IGroupAssistantCallback_onGroupAdd(this.swigCPtr, this, GroupCacheInfo.getCPtr(groupCacheInfo), groupCacheInfo);
        } else {
            internalJNI.IGroupAssistantCallback_onGroupAddSwigExplicitIGroupAssistantCallback(this.swigCPtr, this, GroupCacheInfo.getCPtr(groupCacheInfo), groupCacheInfo);
        }
    }

    public void onGroupDelete(String str) {
        if (getClass() == IGroupAssistantCallback.class) {
            internalJNI.IGroupAssistantCallback_onGroupDelete(this.swigCPtr, this, str);
        } else {
            internalJNI.IGroupAssistantCallback_onGroupDeleteSwigExplicitIGroupAssistantCallback(this.swigCPtr, this, str);
        }
    }

    public void onGroupUpdate(GroupCacheInfo groupCacheInfo) {
        if (getClass() == IGroupAssistantCallback.class) {
            internalJNI.IGroupAssistantCallback_onGroupUpdate(this.swigCPtr, this, GroupCacheInfo.getCPtr(groupCacheInfo), groupCacheInfo);
        } else {
            internalJNI.IGroupAssistantCallback_onGroupUpdateSwigExplicitIGroupAssistantCallback(this.swigCPtr, this, GroupCacheInfo.getCPtr(groupCacheInfo), groupCacheInfo);
        }
    }

    public void onMemberJoin(String str, GroupMemberInfoVec groupMemberInfoVec) {
        if (getClass() == IGroupAssistantCallback.class) {
            internalJNI.IGroupAssistantCallback_onMemberJoin(this.swigCPtr, this, str, GroupMemberInfoVec.getCPtr(groupMemberInfoVec), groupMemberInfoVec);
            return;
        }
        internalJNI.IGroupAssistantCallback_onMemberJoinSwigExplicitIGroupAssistantCallback(this.swigCPtr, this, str, GroupMemberInfoVec.getCPtr(groupMemberInfoVec), groupMemberInfoVec);
    }

    public void onMemberQuit(String str, StrVec strVec) {
        if (getClass() == IGroupAssistantCallback.class) {
            internalJNI.IGroupAssistantCallback_onMemberQuit(this.swigCPtr, this, str, StrVec.getCPtr(strVec), strVec);
            return;
        }
        internalJNI.IGroupAssistantCallback_onMemberQuitSwigExplicitIGroupAssistantCallback(this.swigCPtr, this, str, StrVec.getCPtr(strVec), strVec);
    }

    public void onMemberUpdate(String str, GroupMemberInfoVec groupMemberInfoVec) {
        if (getClass() == IGroupAssistantCallback.class) {
            internalJNI.IGroupAssistantCallback_onMemberUpdate(this.swigCPtr, this, str, GroupMemberInfoVec.getCPtr(groupMemberInfoVec), groupMemberInfoVec);
            return;
        }
        internalJNI.IGroupAssistantCallback_onMemberUpdateSwigExplicitIGroupAssistantCallback(this.swigCPtr, this, str, GroupMemberInfoVec.getCPtr(groupMemberInfoVec), groupMemberInfoVec);
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        internalJNI.IGroupAssistantCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IGroupAssistantCallback_change_ownership(this, this.swigCPtr, true);
    }
}

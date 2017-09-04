package com.tencent.imcore;

public class GroupCacheInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GroupCacheInfo() {
        this(internalJNI.new_GroupCacheInfo(), true);
    }

    protected GroupCacheInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GroupCacheInfo groupCacheInfo) {
        return groupCacheInfo == null ? 0 : groupCacheInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GroupCacheInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public GroupDetailInfo getGroupInfo() {
        long GroupCacheInfo_groupInfo_get = internalJNI.GroupCacheInfo_groupInfo_get(this.swigCPtr, this);
        return GroupCacheInfo_groupInfo_get == 0 ? null : new GroupDetailInfo(GroupCacheInfo_groupInfo_get, false);
    }

    public GroupSelfInfo getSelfInfo() {
        long GroupCacheInfo_selfInfo_get = internalJNI.GroupCacheInfo_selfInfo_get(this.swigCPtr, this);
        return GroupCacheInfo_selfInfo_get == 0 ? null : new GroupSelfInfo(GroupCacheInfo_selfInfo_get, false);
    }

    public void setGroupInfo(GroupDetailInfo groupDetailInfo) {
        internalJNI.GroupCacheInfo_groupInfo_set(this.swigCPtr, this, GroupDetailInfo.getCPtr(groupDetailInfo), groupDetailInfo);
    }

    public void setSelfInfo(GroupSelfInfo groupSelfInfo) {
        internalJNI.GroupCacheInfo_selfInfo_set(this.swigCPtr, this, GroupSelfInfo.getCPtr(groupSelfInfo), groupSelfInfo);
    }
}

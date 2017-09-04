package com.tencent.imcore;

public class GroupSelfInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GroupSelfInfo() {
        this(internalJNI.new_GroupSelfInfo__SWIG_0(), true);
    }

    protected GroupSelfInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public GroupSelfInfo(MemberInfo memberInfo) {
        this(internalJNI.new_GroupSelfInfo__SWIG_1(MemberInfo.getCPtr(memberInfo), memberInfo), true);
    }

    protected static long getCPtr(GroupSelfInfo groupSelfInfo) {
        return groupSelfInfo == null ? 0 : groupSelfInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GroupSelfInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getDwMsgFalg() {
        return internalJNI.GroupSelfInfo_dwMsgFalg_get(this.swigCPtr, this);
    }

    public long getJoinTime() {
        return internalJNI.GroupSelfInfo_joinTime_get(this.swigCPtr, this);
    }

    public long getRole() {
        return internalJNI.GroupSelfInfo_role_get(this.swigCPtr, this);
    }

    public long getUnreadNum() {
        return internalJNI.GroupSelfInfo_unreadNum_get(this.swigCPtr, this);
    }

    public void setDwMsgFalg(long j) {
        internalJNI.GroupSelfInfo_dwMsgFalg_set(this.swigCPtr, this, j);
    }

    public void setJoinTime(long j) {
        internalJNI.GroupSelfInfo_joinTime_set(this.swigCPtr, this, j);
    }

    public void setRole(long j) {
        internalJNI.GroupSelfInfo_role_set(this.swigCPtr, this, j);
    }

    public void setUnreadNum(long j) {
        internalJNI.GroupSelfInfo_unreadNum_set(this.swigCPtr, this, j);
    }
}

package com.tencent.imcore;

public class GroupTipsElem_MemberInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GroupTipsElem_MemberInfo() {
        this(internalJNI.new_GroupTipsElem_MemberInfo(), true);
    }

    protected GroupTipsElem_MemberInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GroupTipsElem_MemberInfo groupTipsElem_MemberInfo) {
        return groupTipsElem_MemberInfo == null ? 0 : groupTipsElem_MemberInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GroupTipsElem_MemberInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getIdentifier() {
        return internalJNI.GroupTipsElem_MemberInfo_identifier_get(this.swigCPtr, this);
    }

    public long getShutup_time() {
        return internalJNI.GroupTipsElem_MemberInfo_shutup_time_get(this.swigCPtr, this);
    }

    public void setIdentifier(String str) {
        internalJNI.GroupTipsElem_MemberInfo_identifier_set(this.swigCPtr, this, str);
    }

    public void setShutup_time(long j) {
        internalJNI.GroupTipsElem_MemberInfo_shutup_time_set(this.swigCPtr, this, j);
    }
}

package com.tencent.imcore;

public class GroupTipsElem_MemberInfoVec {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GroupTipsElem_MemberInfoVec() {
        this(internalJNI.new_GroupTipsElem_MemberInfoVec__SWIG_0(), true);
    }

    public GroupTipsElem_MemberInfoVec(long j) {
        this(internalJNI.new_GroupTipsElem_MemberInfoVec__SWIG_1(j), true);
    }

    protected GroupTipsElem_MemberInfoVec(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GroupTipsElem_MemberInfoVec groupTipsElem_MemberInfoVec) {
        return groupTipsElem_MemberInfoVec == null ? 0 : groupTipsElem_MemberInfoVec.swigCPtr;
    }

    public long capacity() {
        return internalJNI.GroupTipsElem_MemberInfoVec_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.GroupTipsElem_MemberInfoVec_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GroupTipsElem_MemberInfoVec(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.GroupTipsElem_MemberInfoVec_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public GroupTipsElem_MemberInfo get(int i) {
        return new GroupTipsElem_MemberInfo(internalJNI.GroupTipsElem_MemberInfoVec_get(this.swigCPtr, this, i), false);
    }

    public void pushBack(GroupTipsElem_MemberInfo groupTipsElem_MemberInfo) {
        internalJNI.GroupTipsElem_MemberInfoVec_pushBack(this.swigCPtr, this, GroupTipsElem_MemberInfo.getCPtr(groupTipsElem_MemberInfo), groupTipsElem_MemberInfo);
    }

    public void reserve(long j) {
        internalJNI.GroupTipsElem_MemberInfoVec_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, GroupTipsElem_MemberInfo groupTipsElem_MemberInfo) {
        internalJNI.GroupTipsElem_MemberInfoVec_set(this.swigCPtr, this, i, GroupTipsElem_MemberInfo.getCPtr(groupTipsElem_MemberInfo), groupTipsElem_MemberInfo);
    }

    public long size() {
        return internalJNI.GroupTipsElem_MemberInfoVec_size(this.swigCPtr, this);
    }
}

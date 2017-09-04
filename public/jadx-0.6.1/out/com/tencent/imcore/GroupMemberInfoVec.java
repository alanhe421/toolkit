package com.tencent.imcore;

public class GroupMemberInfoVec {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GroupMemberInfoVec() {
        this(internalJNI.new_GroupMemberInfoVec__SWIG_0(), true);
    }

    public GroupMemberInfoVec(long j) {
        this(internalJNI.new_GroupMemberInfoVec__SWIG_1(j), true);
    }

    protected GroupMemberInfoVec(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GroupMemberInfoVec groupMemberInfoVec) {
        return groupMemberInfoVec == null ? 0 : groupMemberInfoVec.swigCPtr;
    }

    public long capacity() {
        return internalJNI.GroupMemberInfoVec_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.GroupMemberInfoVec_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GroupMemberInfoVec(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.GroupMemberInfoVec_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public MemberInfo get(int i) {
        return new MemberInfo(internalJNI.GroupMemberInfoVec_get(this.swigCPtr, this, i), false);
    }

    public void pushBack(MemberInfo memberInfo) {
        internalJNI.GroupMemberInfoVec_pushBack(this.swigCPtr, this, MemberInfo.getCPtr(memberInfo), memberInfo);
    }

    public void reserve(long j) {
        internalJNI.GroupMemberInfoVec_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, MemberInfo memberInfo) {
        internalJNI.GroupMemberInfoVec_set(this.swigCPtr, this, i, MemberInfo.getCPtr(memberInfo), memberInfo);
    }

    public long size() {
        return internalJNI.GroupMemberInfoVec_size(this.swigCPtr, this);
    }
}

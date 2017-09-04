package com.tencent.imcore;

public class GroupMemberResultVec {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GroupMemberResultVec() {
        this(internalJNI.new_GroupMemberResultVec__SWIG_0(), true);
    }

    public GroupMemberResultVec(long j) {
        this(internalJNI.new_GroupMemberResultVec__SWIG_1(j), true);
    }

    protected GroupMemberResultVec(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GroupMemberResultVec groupMemberResultVec) {
        return groupMemberResultVec == null ? 0 : groupMemberResultVec.swigCPtr;
    }

    public long capacity() {
        return internalJNI.GroupMemberResultVec_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.GroupMemberResultVec_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GroupMemberResultVec(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.GroupMemberResultVec_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public MemberResult get(int i) {
        return new MemberResult(internalJNI.GroupMemberResultVec_get(this.swigCPtr, this, i), false);
    }

    public void pushBack(MemberResult memberResult) {
        internalJNI.GroupMemberResultVec_pushBack(this.swigCPtr, this, MemberResult.getCPtr(memberResult), memberResult);
    }

    public void reserve(long j) {
        internalJNI.GroupMemberResultVec_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, MemberResult memberResult) {
        internalJNI.GroupMemberResultVec_set(this.swigCPtr, this, i, MemberResult.getCPtr(memberResult), memberResult);
    }

    public long size() {
        return internalJNI.GroupMemberResultVec_size(this.swigCPtr, this);
    }
}

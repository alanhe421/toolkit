package com.tencent.imcore;

public class GroupDetailInfoVec {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GroupDetailInfoVec() {
        this(internalJNI.new_GroupDetailInfoVec__SWIG_0(), true);
    }

    public GroupDetailInfoVec(long j) {
        this(internalJNI.new_GroupDetailInfoVec__SWIG_1(j), true);
    }

    protected GroupDetailInfoVec(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GroupDetailInfoVec groupDetailInfoVec) {
        return groupDetailInfoVec == null ? 0 : groupDetailInfoVec.swigCPtr;
    }

    public long capacity() {
        return internalJNI.GroupDetailInfoVec_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.GroupDetailInfoVec_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GroupDetailInfoVec(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.GroupDetailInfoVec_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public GroupDetailInfo get(int i) {
        return new GroupDetailInfo(internalJNI.GroupDetailInfoVec_get(this.swigCPtr, this, i), false);
    }

    public void pushBack(GroupDetailInfo groupDetailInfo) {
        internalJNI.GroupDetailInfoVec_pushBack(this.swigCPtr, this, GroupDetailInfo.getCPtr(groupDetailInfo), groupDetailInfo);
    }

    public void reserve(long j) {
        internalJNI.GroupDetailInfoVec_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, GroupDetailInfo groupDetailInfo) {
        internalJNI.GroupDetailInfoVec_set(this.swigCPtr, this, i, GroupDetailInfo.getCPtr(groupDetailInfo), groupDetailInfo);
    }

    public long size() {
        return internalJNI.GroupDetailInfoVec_size(this.swigCPtr, this);
    }
}

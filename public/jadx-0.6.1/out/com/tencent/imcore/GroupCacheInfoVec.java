package com.tencent.imcore;

public class GroupCacheInfoVec {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GroupCacheInfoVec() {
        this(internalJNI.new_GroupCacheInfoVec__SWIG_0(), true);
    }

    public GroupCacheInfoVec(long j) {
        this(internalJNI.new_GroupCacheInfoVec__SWIG_1(j), true);
    }

    protected GroupCacheInfoVec(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GroupCacheInfoVec groupCacheInfoVec) {
        return groupCacheInfoVec == null ? 0 : groupCacheInfoVec.swigCPtr;
    }

    public long capacity() {
        return internalJNI.GroupCacheInfoVec_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.GroupCacheInfoVec_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GroupCacheInfoVec(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.GroupCacheInfoVec_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public GroupCacheInfo get(int i) {
        return new GroupCacheInfo(internalJNI.GroupCacheInfoVec_get(this.swigCPtr, this, i), false);
    }

    public void pushBack(GroupCacheInfo groupCacheInfo) {
        internalJNI.GroupCacheInfoVec_pushBack(this.swigCPtr, this, GroupCacheInfo.getCPtr(groupCacheInfo), groupCacheInfo);
    }

    public void reserve(long j) {
        internalJNI.GroupCacheInfoVec_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, GroupCacheInfo groupCacheInfo) {
        internalJNI.GroupCacheInfoVec_set(this.swigCPtr, this, i, GroupCacheInfo.getCPtr(groupCacheInfo), groupCacheInfo);
    }

    public long size() {
        return internalJNI.GroupCacheInfoVec_size(this.swigCPtr, this);
    }
}

package com.tencent.imcore;

public class GroupPendencyItemVec {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GroupPendencyItemVec() {
        this(internalJNI.new_GroupPendencyItemVec__SWIG_0(), true);
    }

    public GroupPendencyItemVec(long j) {
        this(internalJNI.new_GroupPendencyItemVec__SWIG_1(j), true);
    }

    protected GroupPendencyItemVec(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GroupPendencyItemVec groupPendencyItemVec) {
        return groupPendencyItemVec == null ? 0 : groupPendencyItemVec.swigCPtr;
    }

    public long capacity() {
        return internalJNI.GroupPendencyItemVec_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.GroupPendencyItemVec_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GroupPendencyItemVec(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.GroupPendencyItemVec_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public GroupPendencyItem get(int i) {
        return new GroupPendencyItem(internalJNI.GroupPendencyItemVec_get(this.swigCPtr, this, i), false);
    }

    public void pushBack(GroupPendencyItem groupPendencyItem) {
        internalJNI.GroupPendencyItemVec_pushBack(this.swigCPtr, this, GroupPendencyItem.getCPtr(groupPendencyItem), groupPendencyItem);
    }

    public void reserve(long j) {
        internalJNI.GroupPendencyItemVec_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, GroupPendencyItem groupPendencyItem) {
        internalJNI.GroupPendencyItemVec_set(this.swigCPtr, this, i, GroupPendencyItem.getCPtr(groupPendencyItem), groupPendencyItem);
    }

    public long size() {
        return internalJNI.GroupPendencyItemVec_size(this.swigCPtr, this);
    }
}

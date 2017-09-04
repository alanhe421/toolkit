package com.tencent.imcore;

public class GroupBaseInfoVec {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GroupBaseInfoVec() {
        this(internalJNI.new_GroupBaseInfoVec__SWIG_0(), true);
    }

    public GroupBaseInfoVec(long j) {
        this(internalJNI.new_GroupBaseInfoVec__SWIG_1(j), true);
    }

    protected GroupBaseInfoVec(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GroupBaseInfoVec groupBaseInfoVec) {
        return groupBaseInfoVec == null ? 0 : groupBaseInfoVec.swigCPtr;
    }

    public long capacity() {
        return internalJNI.GroupBaseInfoVec_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.GroupBaseInfoVec_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GroupBaseInfoVec(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.GroupBaseInfoVec_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public GroupBaseInfo get(int i) {
        return new GroupBaseInfo(internalJNI.GroupBaseInfoVec_get(this.swigCPtr, this, i), false);
    }

    public void pushBack(GroupBaseInfo groupBaseInfo) {
        internalJNI.GroupBaseInfoVec_pushBack(this.swigCPtr, this, GroupBaseInfo.getCPtr(groupBaseInfo), groupBaseInfo);
    }

    public void reserve(long j) {
        internalJNI.GroupBaseInfoVec_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, GroupBaseInfo groupBaseInfo) {
        internalJNI.GroupBaseInfoVec_set(this.swigCPtr, this, i, GroupBaseInfo.getCPtr(groupBaseInfo), groupBaseInfo);
    }

    public long size() {
        return internalJNI.GroupBaseInfoVec_size(this.swigCPtr, this);
    }
}

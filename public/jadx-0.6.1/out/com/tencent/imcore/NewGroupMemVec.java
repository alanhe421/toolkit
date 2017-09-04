package com.tencent.imcore;

public class NewGroupMemVec {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public NewGroupMemVec() {
        this(internalJNI.new_NewGroupMemVec__SWIG_0(), true);
    }

    public NewGroupMemVec(long j) {
        this(internalJNI.new_NewGroupMemVec__SWIG_1(j), true);
    }

    protected NewGroupMemVec(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(NewGroupMemVec newGroupMemVec) {
        return newGroupMemVec == null ? 0 : newGroupMemVec.swigCPtr;
    }

    public long capacity() {
        return internalJNI.NewGroupMemVec_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.NewGroupMemVec_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_NewGroupMemVec(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.NewGroupMemVec_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public NewGroupMemberInfo get(int i) {
        return new NewGroupMemberInfo(internalJNI.NewGroupMemVec_get(this.swigCPtr, this, i), false);
    }

    public void pushBack(NewGroupMemberInfo newGroupMemberInfo) {
        internalJNI.NewGroupMemVec_pushBack(this.swigCPtr, this, NewGroupMemberInfo.getCPtr(newGroupMemberInfo), newGroupMemberInfo);
    }

    public void reserve(long j) {
        internalJNI.NewGroupMemVec_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, NewGroupMemberInfo newGroupMemberInfo) {
        internalJNI.NewGroupMemVec_set(this.swigCPtr, this, i, NewGroupMemberInfo.getCPtr(newGroupMemberInfo), newGroupMemberInfo);
    }

    public long size() {
        return internalJNI.NewGroupMemVec_size(this.swigCPtr, this);
    }
}

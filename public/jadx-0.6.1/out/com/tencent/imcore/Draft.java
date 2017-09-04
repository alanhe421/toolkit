package com.tencent.imcore;

public class Draft {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public Draft() {
        this(internalJNI.new_Draft(), true);
    }

    protected Draft(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(Draft draft) {
        return draft == null ? 0 : draft.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_Draft(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public ElemVec getElems() {
        long Draft_elems_get = internalJNI.Draft_elems_get(this.swigCPtr, this);
        return Draft_elems_get == 0 ? null : new ElemVec(Draft_elems_get, false);
    }

    public long getUint64_edit_time() {
        return internalJNI.Draft_uint64_edit_time_get(this.swigCPtr, this);
    }

    public byte[] getUser_define() {
        return internalJNI.Draft_user_define_get(this.swigCPtr, this);
    }

    public void setElems(ElemVec elemVec) {
        internalJNI.Draft_elems_set(this.swigCPtr, this, ElemVec.getCPtr(elemVec), elemVec);
    }

    public void setUint64_edit_time(long j) {
        internalJNI.Draft_uint64_edit_time_set(this.swigCPtr, this, j);
    }

    public void setUser_define(byte[] bArr) {
        internalJNI.Draft_user_define_set(this.swigCPtr, this, bArr);
    }
}

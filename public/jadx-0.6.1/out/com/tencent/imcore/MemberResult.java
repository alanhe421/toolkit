package com.tencent.imcore;

public class MemberResult {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public MemberResult() {
        this(internalJNI.new_MemberResult(), true);
    }

    protected MemberResult(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(MemberResult memberResult) {
        return memberResult == null ? 0 : memberResult.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_MemberResult(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getStatus() {
        return internalJNI.MemberResult_status_get(this.swigCPtr, this);
    }

    public String getUser() {
        return internalJNI.MemberResult_user_get(this.swigCPtr, this);
    }

    public void setStatus(long j) {
        internalJNI.MemberResult_status_set(this.swigCPtr, this, j);
    }

    public void setUser(String str) {
        internalJNI.MemberResult_user_set(this.swigCPtr, this, str);
    }
}

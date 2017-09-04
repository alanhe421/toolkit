package com.tencent.imcore;

public class UserStatus {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public UserStatus() {
        this(internalJNI.new_UserStatus(), true);
    }

    protected UserStatus(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(UserStatus userStatus) {
        return userStatus == null ? 0 : userStatus.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_UserStatus(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getDdwTinyId() {
        return internalJNI.UserStatus_ddwTinyId_get(this.swigCPtr, this);
    }

    public String getSUserId() {
        return internalJNI.UserStatus_sUserId_get(this.swigCPtr, this);
    }

    public InstStatusVec getStInstStatus() {
        long UserStatus_stInstStatus_get = internalJNI.UserStatus_stInstStatus_get(this.swigCPtr, this);
        return UserStatus_stInstStatus_get == 0 ? null : new InstStatusVec(UserStatus_stInstStatus_get, false);
    }

    public void setDdwTinyId(long j) {
        internalJNI.UserStatus_ddwTinyId_set(this.swigCPtr, this, j);
    }

    public void setSUserId(String str) {
        internalJNI.UserStatus_sUserId_set(this.swigCPtr, this, str);
    }

    public void setStInstStatus(InstStatusVec instStatusVec) {
        internalJNI.UserStatus_stInstStatus_set(this.swigCPtr, this, InstStatusVec.getCPtr(instStatusVec), instStatusVec);
    }
}

package com.tencent.imcore;

public class FriendChangeElem {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public FriendChangeElem() {
        this(internalJNI.new_FriendChangeElem(), true);
    }

    protected FriendChangeElem(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(FriendChangeElem friendChangeElem) {
        return friendChangeElem == null ? 0 : friendChangeElem.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_FriendChangeElem(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getDdwDecideReportTimestamp() {
        return internalJNI.FriendChangeElem_ddwDecideReportTimestamp_get(this.swigCPtr, this);
    }

    public long getDdwPendencyReportTimestamp() {
        return internalJNI.FriendChangeElem_ddwPendencyReportTimestamp_get(this.swigCPtr, this);
    }

    public long getDdwRecommendReportTimestamp() {
        return internalJNI.FriendChangeElem_ddwRecommendReportTimestamp_get(this.swigCPtr, this);
    }

    public long getType() {
        return internalJNI.FriendChangeElem_type_get(this.swigCPtr, this);
    }

    public FriendChangeInfoVec getUsers() {
        long FriendChangeElem_users_get = internalJNI.FriendChangeElem_users_get(this.swigCPtr, this);
        return FriendChangeElem_users_get == 0 ? null : new FriendChangeInfoVec(FriendChangeElem_users_get, false);
    }

    public void setDdwDecideReportTimestamp(long j) {
        internalJNI.FriendChangeElem_ddwDecideReportTimestamp_set(this.swigCPtr, this, j);
    }

    public void setDdwPendencyReportTimestamp(long j) {
        internalJNI.FriendChangeElem_ddwPendencyReportTimestamp_set(this.swigCPtr, this, j);
    }

    public void setDdwRecommendReportTimestamp(long j) {
        internalJNI.FriendChangeElem_ddwRecommendReportTimestamp_set(this.swigCPtr, this, j);
    }

    public void setType(long j) {
        internalJNI.FriendChangeElem_type_set(this.swigCPtr, this, j);
    }

    public void setUsers(FriendChangeInfoVec friendChangeInfoVec) {
        internalJNI.FriendChangeElem_users_set(this.swigCPtr, this, FriendChangeInfoVec.getCPtr(friendChangeInfoVec), friendChangeInfoVec);
    }
}

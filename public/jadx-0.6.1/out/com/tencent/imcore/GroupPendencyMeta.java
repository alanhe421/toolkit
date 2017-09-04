package com.tencent.imcore;

public class GroupPendencyMeta {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GroupPendencyMeta() {
        this(internalJNI.new_GroupPendencyMeta(), true);
    }

    protected GroupPendencyMeta(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GroupPendencyMeta groupPendencyMeta) {
        return groupPendencyMeta == null ? 0 : groupPendencyMeta.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GroupPendencyMeta(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getNext_start_time() {
        return internalJNI.GroupPendencyMeta_next_start_time_get(this.swigCPtr, this);
    }

    public long getRead_time_seq() {
        return internalJNI.GroupPendencyMeta_read_time_seq_get(this.swigCPtr, this);
    }

    public long getUnread_num() {
        return internalJNI.GroupPendencyMeta_unread_num_get(this.swigCPtr, this);
    }

    public void setNext_start_time(long j) {
        internalJNI.GroupPendencyMeta_next_start_time_set(this.swigCPtr, this, j);
    }

    public void setRead_time_seq(long j) {
        internalJNI.GroupPendencyMeta_read_time_seq_set(this.swigCPtr, this, j);
    }

    public void setUnread_num(long j) {
        internalJNI.GroupPendencyMeta_unread_num_set(this.swigCPtr, this, j);
    }
}

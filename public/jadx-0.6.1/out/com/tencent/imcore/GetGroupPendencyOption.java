package com.tencent.imcore;

public class GetGroupPendencyOption {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GetGroupPendencyOption() {
        this(internalJNI.new_GetGroupPendencyOption(), true);
    }

    protected GetGroupPendencyOption(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GetGroupPendencyOption getGroupPendencyOption) {
        return getGroupPendencyOption == null ? 0 : getGroupPendencyOption.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GetGroupPendencyOption(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getMax_limited() {
        return internalJNI.GetGroupPendencyOption_max_limited_get(this.swigCPtr, this);
    }

    public long getStart_time() {
        return internalJNI.GetGroupPendencyOption_start_time_get(this.swigCPtr, this);
    }

    public void setMax_limited(long j) {
        internalJNI.GetGroupPendencyOption_max_limited_set(this.swigCPtr, this, j);
    }

    public void setStart_time(long j) {
        internalJNI.GetGroupPendencyOption_start_time_set(this.swigCPtr, this, j);
    }
}

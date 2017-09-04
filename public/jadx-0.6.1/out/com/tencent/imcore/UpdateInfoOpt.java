package com.tencent.imcore;

public class UpdateInfoOpt {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public UpdateInfoOpt(long j) {
        this(internalJNI.new_UpdateInfoOpt(j), true);
    }

    protected UpdateInfoOpt(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(UpdateInfoOpt updateInfoOpt) {
        return updateInfoOpt == null ? 0 : updateInfoOpt.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_UpdateInfoOpt(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public BytesVec getCustomInfoTags() {
        long UpdateInfoOpt_customInfoTags_get = internalJNI.UpdateInfoOpt_customInfoTags_get(this.swigCPtr, this);
        return UpdateInfoOpt_customInfoTags_get == 0 ? null : new BytesVec(UpdateInfoOpt_customInfoTags_get, false);
    }

    public long getFlag() {
        return internalJNI.UpdateInfoOpt_flag_get(this.swigCPtr, this);
    }

    public void setCustomInfoTags(BytesVec bytesVec) {
        internalJNI.UpdateInfoOpt_customInfoTags_set(this.swigCPtr, this, BytesVec.getCPtr(bytesVec), bytesVec);
    }

    public void setFlag(long j) {
        internalJNI.UpdateInfoOpt_flag_set(this.swigCPtr, this, j);
    }
}

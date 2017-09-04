package com.tencent.imcore;

public class GroupSettings {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GroupSettings() {
        this(internalJNI.new_GroupSettings(), true);
    }

    protected GroupSettings(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GroupSettings groupSettings) {
        return groupSettings == null ? 0 : groupSettings.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GroupSettings(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public UpdateInfoOpt getGroupInfoOpt() {
        long GroupSettings_groupInfoOpt_get = internalJNI.GroupSettings_groupInfoOpt_get(this.swigCPtr, this);
        return GroupSettings_groupInfoOpt_get == 0 ? null : new UpdateInfoOpt(GroupSettings_groupInfoOpt_get, false);
    }

    public UpdateInfoOpt getMemberInfoOpt() {
        long GroupSettings_memberInfoOpt_get = internalJNI.GroupSettings_memberInfoOpt_get(this.swigCPtr, this);
        return GroupSettings_memberInfoOpt_get == 0 ? null : new UpdateInfoOpt(GroupSettings_memberInfoOpt_get, false);
    }

    public boolean getStorageEnabled() {
        return internalJNI.GroupSettings_storageEnabled_get(this.swigCPtr, this);
    }

    public void setGroupInfoOpt(UpdateInfoOpt updateInfoOpt) {
        internalJNI.GroupSettings_groupInfoOpt_set(this.swigCPtr, this, UpdateInfoOpt.getCPtr(updateInfoOpt), updateInfoOpt);
    }

    public void setMemberInfoOpt(UpdateInfoOpt updateInfoOpt) {
        internalJNI.GroupSettings_memberInfoOpt_set(this.swigCPtr, this, UpdateInfoOpt.getCPtr(updateInfoOpt), updateInfoOpt);
    }

    public void setStorageEnabled(boolean z) {
        internalJNI.GroupSettings_storageEnabled_set(this.swigCPtr, this, z);
    }
}

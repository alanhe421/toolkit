package com.tencent.imcore;

public class GroupAssistantConfig {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GroupAssistantConfig() {
        this(internalJNI.new_GroupAssistantConfig(), true);
    }

    protected GroupAssistantConfig(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GroupAssistantConfig groupAssistantConfig) {
        return groupAssistantConfig == null ? 0 : groupAssistantConfig.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GroupAssistantConfig(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public IGroupAssistantCallback getCallback() {
        long GroupAssistantConfig_callback_get = internalJNI.GroupAssistantConfig_callback_get(this.swigCPtr, this);
        return GroupAssistantConfig_callback_get == 0 ? null : new IGroupAssistantCallback(GroupAssistantConfig_callback_get, false);
    }

    public GroupSettings getSettings() {
        long GroupAssistantConfig_settings_get = internalJNI.GroupAssistantConfig_settings_get(this.swigCPtr, this);
        return GroupAssistantConfig_settings_get == 0 ? null : new GroupSettings(GroupAssistantConfig_settings_get, false);
    }

    public void setCallback(IGroupAssistantCallback iGroupAssistantCallback) {
        internalJNI.GroupAssistantConfig_callback_set(this.swigCPtr, this, IGroupAssistantCallback.getCPtr(iGroupAssistantCallback), iGroupAssistantCallback);
    }

    public void setSettings(GroupSettings groupSettings) {
        internalJNI.GroupAssistantConfig_settings_set(this.swigCPtr, this, GroupSettings.getCPtr(groupSettings), groupSettings);
    }
}

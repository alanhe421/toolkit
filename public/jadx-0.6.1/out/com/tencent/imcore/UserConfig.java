package com.tencent.imcore;

public class UserConfig {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public UserConfig() {
        this(internalJNI.new_UserConfig(), true);
    }

    protected UserConfig(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(UserConfig userConfig) {
        return userConfig == null ? 0 : userConfig.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_UserConfig(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public boolean getAuto_report() {
        return internalJNI.UserConfig_auto_report_get(this.swigCPtr, this);
    }

    public FriendshipProxyConfig getFrd_prxy_config() {
        long UserConfig_frd_prxy_config_get = internalJNI.UserConfig_frd_prxy_config_get(this.swigCPtr, this);
        return UserConfig_frd_prxy_config_get == 0 ? null : new FriendshipProxyConfig(UserConfig_frd_prxy_config_get, false);
    }

    public GroupAssistantConfig getGrp_ass_config() {
        long UserConfig_grp_ass_config_get = internalJNI.UserConfig_grp_ass_config_get(this.swigCPtr, this);
        return UserConfig_grp_ass_config_get == 0 ? null : new GroupAssistantConfig(UserConfig_grp_ass_config_get, false);
    }

    public INotify getNotify() {
        long UserConfig_notify_get = internalJNI.UserConfig_notify_get(this.swigCPtr, this);
        return UserConfig_notify_get == 0 ? null : new INotify(UserConfig_notify_get, false);
    }

    public boolean getRead_receipt() {
        return internalJNI.UserConfig_read_receipt_get(this.swigCPtr, this);
    }

    public boolean getRecent_contact() {
        return internalJNI.UserConfig_recent_contact_get(this.swigCPtr, this);
    }

    public boolean getRecent_contact_notify() {
        return internalJNI.UserConfig_recent_contact_notify_get(this.swigCPtr, this);
    }

    public boolean getStorage() {
        return internalJNI.UserConfig_storage_get(this.swigCPtr, this);
    }

    public void setAuto_report(boolean z) {
        internalJNI.UserConfig_auto_report_set(this.swigCPtr, this, z);
    }

    public void setFrd_prxy_config(FriendshipProxyConfig friendshipProxyConfig) {
        internalJNI.UserConfig_frd_prxy_config_set(this.swigCPtr, this, FriendshipProxyConfig.getCPtr(friendshipProxyConfig), friendshipProxyConfig);
    }

    public void setGrp_ass_config(GroupAssistantConfig groupAssistantConfig) {
        internalJNI.UserConfig_grp_ass_config_set(this.swigCPtr, this, GroupAssistantConfig.getCPtr(groupAssistantConfig), groupAssistantConfig);
    }

    public void setNotify(INotify iNotify) {
        internalJNI.UserConfig_notify_set(this.swigCPtr, this, INotify.getCPtr(iNotify), iNotify);
    }

    public void setRead_receipt(boolean z) {
        internalJNI.UserConfig_read_receipt_set(this.swigCPtr, this, z);
    }

    public void setRecent_contact(boolean z) {
        internalJNI.UserConfig_recent_contact_set(this.swigCPtr, this, z);
    }

    public void setRecent_contact_notify(boolean z) {
        internalJNI.UserConfig_recent_contact_notify_set(this.swigCPtr, this, z);
    }

    public void setStorage(boolean z) {
        internalJNI.UserConfig_storage_set(this.swigCPtr, this, z);
    }
}

package com.tencent;

import com.tencent.imcore.FriendshipProxyConfig;
import com.tencent.imcore.GroupAssistantConfig;
import com.tencent.imcore.GroupSettings;
import com.tencent.imcore.UserConfig;

public class IMCoreUserConfig {
    private TIMFriendshipSettings friendshipSettings = new TIMFriendshipSettings();
    private TIMGroupSettings groupSettings = new TIMGroupSettings();
    private boolean isAutoReportEnabled = true;
    private boolean isFriendshipStorageEnabled = false;
    private boolean isGroupStorageEnabled = false;
    private boolean isReadReceiptEnabled = false;
    private boolean isRecentContactEnabled = true;
    private boolean isRecentContactNotifyEnabled = true;
    private boolean isStorageEnabled = true;
    private IMCoreNotify notifyCallback;

    public UserConfig convertTo(String str) {
        UserConfig userConfig = new UserConfig();
        userConfig.setAuto_report(this.isAutoReportEnabled);
        userConfig.setRead_receipt(this.isReadReceiptEnabled);
        userConfig.setRecent_contact(this.isRecentContactEnabled);
        userConfig.setRecent_contact_notify(this.isRecentContactNotifyEnabled);
        userConfig.setStorage(this.isStorageEnabled);
        userConfig.setNotify(this.notifyCallback);
        GroupSettings groupSettings = new GroupSettings();
        groupSettings.setStorageEnabled(this.isGroupStorageEnabled);
        this.groupSettings.convertTo(groupSettings);
        GroupAssistantConfig groupAssistantConfig = new GroupAssistantConfig();
        groupAssistantConfig.setSettings(groupSettings);
        groupAssistantConfig.setCallback(new IMCoreGroupAssistantCallback(str));
        userConfig.setGrp_ass_config(groupAssistantConfig);
        FriendshipProxyConfig friendshipProxyConfig = new FriendshipProxyConfig();
        friendshipProxyConfig.setEnable(this.isFriendshipStorageEnabled);
        this.friendshipSettings.convertTo(friendshipProxyConfig);
        friendshipProxyConfig.setListener(new IMCoreFriendshipProxyCallback(str));
        userConfig.setFrd_prxy_config(friendshipProxyConfig);
        return userConfig;
    }

    public TIMFriendshipSettings getFriendshipSettings() {
        return this.friendshipSettings;
    }

    public TIMGroupSettings getGroupSettings() {
        return this.groupSettings;
    }

    public IMCoreNotify getNotifyCallback() {
        return this.notifyCallback;
    }

    public boolean isAutoReportEnabled() {
        return this.isAutoReportEnabled;
    }

    public boolean isFriendshipStorageEnabled() {
        return this.isFriendshipStorageEnabled;
    }

    public boolean isGroupStorageEnabled() {
        return this.isGroupStorageEnabled;
    }

    public boolean isReadReceiptEnabled() {
        return this.isReadReceiptEnabled;
    }

    public boolean isRecentContactEnabled() {
        return this.isRecentContactEnabled;
    }

    public boolean isRecentContactNotifyEnabled() {
        return this.isRecentContactNotifyEnabled;
    }

    public boolean isStorageEnabled() {
        return this.isStorageEnabled;
    }

    public void setAutoReportEnabled(boolean z) {
        this.isAutoReportEnabled = z;
    }

    public void setFriendshipSettings(TIMFriendshipSettings tIMFriendshipSettings) {
        this.friendshipSettings = tIMFriendshipSettings;
    }

    public void setFriendshipStorageEnabled(boolean z) {
        this.isFriendshipStorageEnabled = z;
    }

    public void setGroupSettings(TIMGroupSettings tIMGroupSettings) {
        this.groupSettings = tIMGroupSettings;
    }

    public void setGroupStorageEnabled(boolean z) {
        this.isGroupStorageEnabled = z;
    }

    public void setNotifyCallback(IMCoreNotify iMCoreNotify) {
        this.notifyCallback = iMCoreNotify;
    }

    public void setReadReceiptEnabled(boolean z) {
        this.isReadReceiptEnabled = z;
    }

    public void setRecentContactEnabled(boolean z) {
        this.isRecentContactEnabled = z;
    }

    public void setRecentContactNotifyEnabled(boolean z) {
        this.isRecentContactNotifyEnabled = z;
    }

    public void setStorageEnabled(boolean z) {
        this.isStorageEnabled = z;
    }
}

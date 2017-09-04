package com.tencent;

import android.net.Uri;

public class TIMOfflinePushSettings {
    private Uri c2cMsgRemindSound;
    private boolean enabled = true;
    private Uri groupMsgRemindSound;

    public Uri getC2cMsgRemindSound() {
        return this.c2cMsgRemindSound;
    }

    public Uri getGroupMsgRemindSound() {
        return this.groupMsgRemindSound;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setC2cMsgRemindSound(Uri uri) {
        this.c2cMsgRemindSound = uri;
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
    }

    public void setGroupMsgRemindSound(Uri uri) {
        this.groupMsgRemindSound = uri;
    }
}

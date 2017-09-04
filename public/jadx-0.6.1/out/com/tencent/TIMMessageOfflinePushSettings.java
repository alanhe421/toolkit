package com.tencent;

import android.net.Uri;
import android.text.TextUtils;
import com.tencent.imcore.APNsInfo;
import com.tencent.imcore.AndroidOfflinePushInfo;
import com.tencent.imcore.OfflinePushInfo;

public class TIMMessageOfflinePushSettings {
    private AndroidSettings androidSettings;
    private String descr;
    private byte[] ext;
    private IOSSettings iosSettings;
    private boolean isEnabled = true;

    public class AndroidSettings {
        private NotifyMode notifyMode = NotifyMode.Normal;
        private Uri sound;
        private String title = "";

        public NotifyMode getNotifyMode() {
            return this.notifyMode;
        }

        public Uri getSound() {
            return this.sound;
        }

        public String getTitle() {
            return this.title;
        }

        public void setNotifyMode(NotifyMode notifyMode) {
            if (notifyMode != null) {
                this.notifyMode = notifyMode;
            }
        }

        public void setSound(Uri uri) {
            this.sound = uri;
        }

        public void setTitle(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.title = str;
            }
        }
    }

    public class IOSSettings {
        private boolean isBadgeEnabled = true;
        private String sound = "";

        public String getSound() {
            return this.sound;
        }

        public boolean isBadgeEnabled() {
            return this.isBadgeEnabled;
        }

        public void setBadgeEnabled(boolean z) {
            this.isBadgeEnabled = z;
        }

        public void setSound(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.sound = str;
            }
        }
    }

    public enum NotifyMode {
        Normal(0),
        Custom(1);
        
        private int value;

        private NotifyMode(int i) {
            this.value = 0;
            this.value = i;
        }

        static NotifyMode convertFrom(long j) {
            for (NotifyMode notifyMode : values()) {
                if (((long) notifyMode.getValue()) == j) {
                    return notifyMode;
                }
            }
            return Normal;
        }

        final int getValue() {
            return this.value;
        }
    }

    protected static TIMMessageOfflinePushSettings convertFrom(OfflinePushInfo offlinePushInfo) {
        boolean z = true;
        TIMMessageOfflinePushSettings tIMMessageOfflinePushSettings = new TIMMessageOfflinePushSettings();
        if (offlinePushInfo != null && offlinePushInfo.getIsValid()) {
            try {
                tIMMessageOfflinePushSettings.setEnabled(offlinePushInfo.getFlag() == 0);
                tIMMessageOfflinePushSettings.setDescr(new String(offlinePushInfo.getDesc(), "utf-8"));
                tIMMessageOfflinePushSettings.setExt(offlinePushInfo.getExt());
                tIMMessageOfflinePushSettings.androidSettings.setTitle(new String(offlinePushInfo.getAndroidInfo().getTitle(), "utf-8"));
                tIMMessageOfflinePushSettings.androidSettings.setSound(Uri.parse(new String(offlinePushInfo.getAndroidInfo().getSound(), "utf-8")));
                tIMMessageOfflinePushSettings.androidSettings.setNotifyMode(NotifyMode.convertFrom(offlinePushInfo.getAndroidInfo().getNotifyMode()));
                tIMMessageOfflinePushSettings.iosSettings.setSound(new String(offlinePushInfo.getApns().getSound(), "utf-8"));
                IOSSettings iOSSettings = tIMMessageOfflinePushSettings.iosSettings;
                if (offlinePushInfo.getApns().getBadgeMode() != 0) {
                    z = false;
                }
                iOSSettings.setBadgeEnabled(z);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return tIMMessageOfflinePushSettings;
    }

    protected OfflinePushInfo convertTo() {
        long j = 0;
        OfflinePushInfo offlinePushInfo = new OfflinePushInfo();
        offlinePushInfo.setIsValid(true);
        offlinePushInfo.setFlag(this.isEnabled ? 0 : 1);
        if (!TextUtils.isEmpty(this.descr)) {
            try {
                offlinePushInfo.setDesc(this.descr.getBytes("utf-8"));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        if (this.ext != null) {
            offlinePushInfo.setExt(this.ext);
        }
        if (this.androidSettings != null) {
            AndroidOfflinePushInfo androidOfflinePushInfo = new AndroidOfflinePushInfo();
            try {
                if (!TextUtils.isEmpty(this.androidSettings.getTitle())) {
                    androidOfflinePushInfo.setTitle(this.androidSettings.getTitle().getBytes("utf-8"));
                }
                if (this.androidSettings.getSound() != null) {
                    androidOfflinePushInfo.setSound(this.androidSettings.getSound().toString().getBytes("utf-8"));
                }
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
            androidOfflinePushInfo.setNotifyMode((long) this.androidSettings.getNotifyMode().getValue());
            offlinePushInfo.setAndroidInfo(androidOfflinePushInfo);
        }
        if (this.iosSettings != null) {
            APNsInfo aPNsInfo = new APNsInfo();
            if (this.iosSettings.getSound() != null) {
                try {
                    aPNsInfo.setSound(this.iosSettings.getSound().getBytes("utf-8"));
                    if (!this.iosSettings.isBadgeEnabled) {
                        j = 1;
                    }
                    aPNsInfo.setBadgeMode(j);
                } catch (Throwable th22) {
                    th22.printStackTrace();
                }
            }
            offlinePushInfo.setApns(aPNsInfo);
        }
        return offlinePushInfo;
    }

    public AndroidSettings getAndroidSettings() {
        return this.androidSettings;
    }

    public String getDescr() {
        return this.descr;
    }

    public byte[] getExt() {
        return this.ext;
    }

    public IOSSettings getIosSettings() {
        return this.iosSettings;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setAndroidSettings(AndroidSettings androidSettings) {
        this.androidSettings = androidSettings;
    }

    public void setDescr(String str) {
        this.descr = str;
    }

    public void setEnabled(boolean z) {
        this.isEnabled = z;
    }

    public void setExt(byte[] bArr) {
        this.ext = bArr;
    }

    public void setIosSettings(IOSSettings iOSSettings) {
        this.iosSettings = iOSSettings;
    }
}

package com.tencent;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.app.t.d;
import android.text.TextUtils;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.openqq.protocol.imsdk.im_common;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class TIMOfflinePushNotification {
    private String content;
    private String conversationId;
    private TIMConversationType conversationType;
    private byte[] ext;
    private String groupName;
    private String identifier = "";
    private boolean isValid = false;
    private long opt = 0;
    private String senderIdentifier;
    private String senderNickName;
    private Uri sound;
    private String tag;
    private String title;

    protected TIMOfflinePushNotification() {
    }

    protected TIMOfflinePushNotification(Context context, TIMMessage tIMMessage) {
        String str = "";
        TIMConversationType type = tIMMessage.getConversation().getType();
        if (type != TIMConversationType.C2C && type != TIMConversationType.Group) {
            this.isValid = false;
        } else if (tIMMessage.getMsg().lifetime() == 0) {
            this.isValid = false;
        } else {
            Object identifier;
            String str2;
            Object obj = "";
            TIMMessageOfflinePushSettings offlinePushSettings = tIMMessage.getOfflinePushSettings();
            if (offlinePushSettings != null) {
                if (offlinePushSettings.isEnabled()) {
                    setSound(offlinePushSettings.getAndroidSettings().getSound());
                    setExt(offlinePushSettings.getExt());
                    obj = offlinePushSettings.getDescr();
                    this.title = offlinePushSettings.getAndroidSettings().getTitle();
                } else {
                    this.isValid = false;
                    return;
                }
            }
            this.opt = tIMMessage.getRecvFlag().getValue();
            setTag(tIMMessage.getConversation().getPeer());
            setConversationType(tIMMessage.getConversation().getType());
            setConversationId(tIMMessage.getConversation().getPeer());
            if (tIMMessage.getSenderProfile() != null) {
                identifier = tIMMessage.getSenderProfile().getIdentifier();
                if (!TextUtils.isEmpty(identifier)) {
                    setSenderIdentifier(identifier);
                }
                identifier = tIMMessage.getSenderProfile().getNickName();
                if (!TextUtils.isEmpty(identifier)) {
                    setSenderNickName(identifier);
                }
            }
            if (tIMMessage.getSenderGroupMemberProfile() != null) {
                identifier = tIMMessage.getSenderGroupMemberProfile().getNameCard();
                if (!TextUtils.isEmpty(identifier)) {
                    setSenderNickName(identifier);
                }
            }
            setGroupName(tIMMessage.getGroupName());
            if (this.conversationType != TIMConversationType.C2C) {
                if (TextUtils.isEmpty(this.title)) {
                    setTitle(this.groupName);
                }
                str2 = this.senderNickName;
                if (TextUtils.isEmpty(str2)) {
                    str2 = this.senderIdentifier;
                }
                str2 = str + str2 + ": ";
            } else if (TextUtils.isEmpty(this.title)) {
                setTitle(this.senderNickName);
                str2 = str;
            } else {
                str2 = str;
            }
            if (TextUtils.isEmpty(this.title)) {
                setTitle(this.conversationId);
            }
            if (TextUtils.isEmpty(obj)) {
                for (int i = 0; ((long) i) < tIMMessage.getElementCount(); i++) {
                    TIMElem element = tIMMessage.getElement(i);
                    if (element.getType() == TIMElemType.Sound) {
                        str2 = str2 + "[语音]";
                    } else if (element.getType() == TIMElemType.File) {
                        str2 = str2 + "[文件]";
                    } else if (element.getType() == TIMElemType.Text) {
                        str2 = str2 + ((TIMTextElem) element).getText();
                    } else if (element.getType() == TIMElemType.Image) {
                        str2 = str2 + "[图片]";
                    } else if (element.getType() == TIMElemType.Face) {
                        str2 = str2 + "[表情]";
                    } else if (element.getType() == TIMElemType.Custom) {
                        TIMCustomElem tIMCustomElem = (TIMCustomElem) element;
                        if (!TextUtils.isEmpty(tIMCustomElem.getDesc())) {
                            str2 = str2 + "[" + tIMCustomElem.getDesc() + "]";
                        }
                        if (this.ext == null) {
                            setExt(tIMCustomElem.getExt());
                        }
                    } else if (element.getType() == TIMElemType.Location) {
                        str2 = str2 + "[位置信息]" + ((TIMLocationElem) element).getDesc();
                    } else if (element.getType() == TIMElemType.Video) {
                        str2 = str2 + "[视频]";
                    }
                }
            } else {
                str2 = str2 + obj;
            }
            setContent(str2);
            if (getSound() == null) {
                TIMOfflinePushSettings tIMOfflinePushSettings = new TIMOfflinePushSettings();
                if (IMMsfCoreProxy.get().getOfflinePushSettingsFromLocal(context, this.identifier, tIMOfflinePushSettings)) {
                    if (this.conversationType == TIMConversationType.C2C && tIMOfflinePushSettings.getC2cMsgRemindSound() != null) {
                        setSound(tIMOfflinePushSettings.getC2cMsgRemindSound());
                    } else if (this.conversationType == TIMConversationType.Group && tIMOfflinePushSettings.getGroupMsgRemindSound() != null) {
                        setSound(tIMOfflinePushSettings.getGroupMsgRemindSound());
                    }
                }
            }
            setIsValid(true);
        }
    }

    public void doNotify(Context context, int i) {
        d dVar = new d(context.getApplicationContext());
        dVar.a(getTitle());
        dVar.b(getContent());
        dVar.a(i);
        dVar.c("收到一条新消息");
        dVar.c(true);
        dVar.b(-1);
        if (this.sound != null) {
            dVar.b(6);
            dVar.a(this.sound);
        }
        dVar.a(PendingIntent.getActivity(context, (int) SystemClock.uptimeMillis(), context.getApplicationContext().getPackageManager().getLaunchIntentForPackage(context.getPackageName()), SigType.WLOGIN_PT4Token));
        ((NotificationManager) context.getApplicationContext().getSystemService("notification")).notify(this.tag, im_common.BU_FRIEND, dVar.a());
    }

    public String getContent() {
        return this.content == null ? "" : this.content;
    }

    public String getConversationId() {
        return this.conversationId;
    }

    public TIMConversationType getConversationType() {
        return this.conversationType;
    }

    public byte[] getExt() {
        return this.ext == null ? "".getBytes() : this.ext;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public TIMGroupReceiveMessageOpt getGroupReceiveMsgOpt() {
        for (TIMGroupReceiveMessageOpt tIMGroupReceiveMessageOpt : TIMGroupReceiveMessageOpt.values()) {
            if (tIMGroupReceiveMessageOpt.getValue() == this.opt) {
                return tIMGroupReceiveMessageOpt;
            }
        }
        return TIMGroupReceiveMessageOpt.ReceiveAndNotify;
    }

    public String getSenderIdentifier() {
        return this.senderIdentifier;
    }

    public String getSenderNickName() {
        return this.senderNickName;
    }

    public Uri getSound() {
        return this.sound;
    }

    public String getTitle() {
        return this.title == null ? "" : this.title;
    }

    public boolean isValid() {
        return this.isValid;
    }

    public void setContent(String str) {
        this.content = str;
    }

    void setConversationId(String str) {
        this.conversationId = str;
    }

    void setConversationType(TIMConversationType tIMConversationType) {
        this.conversationType = tIMConversationType;
    }

    public void setExt(byte[] bArr) {
        if (bArr != null) {
            this.ext = bArr;
        }
    }

    void setGroupName(String str) {
        this.groupName = str;
    }

    void setGroupReceiveMsgOpt(long j) {
        this.opt = j;
    }

    protected void setIdentifier(String str) {
        if (str != null) {
            this.identifier = str;
        }
    }

    void setIsValid(boolean z) {
        this.isValid = z;
    }

    void setSenderIdentifier(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.senderIdentifier = str;
        }
    }

    void setSenderNickName(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.senderNickName = str;
        }
    }

    public void setSound(Uri uri) {
        if (uri != null) {
            this.sound = uri;
        }
    }

    void setTag(String str) {
        this.tag = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}

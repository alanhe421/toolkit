package com.tencent;

import android.text.TextUtils;
import com.tencent.imcore.Elem;
import com.tencent.imcore.Msg;
import com.tencent.imcore.MsgPriority;
import com.tencent.imsdk.QLog;
import java.io.UnsupportedEncodingException;

public class TIMMessage {
    private static final TIMElem defaultElem = new hg();
    private static final String tag = "imsdk.TIMMessage";
    private TIMConversation conversation;
    public Msg msg;

    public TIMMessage() {
        try {
            this.msg = Msg.newMsg();
            this.conversation = new TIMConversation();
        } catch (Throwable e) {
            QLog.e(tag, 1, IMFunc.getExceptionInfo(e));
            throw new OutOfMemoryError();
        }
    }

    TIMMessage(Msg msg) {
        try {
            this.msg = Msg.newMsg(msg);
            this.conversation = new TIMConversation();
            this.conversation.fromSession(this.msg.session());
        } catch (Throwable e) {
            QLog.e(tag, 1, IMFunc.getExceptionInfo(e));
            throw new OutOfMemoryError();
        }
    }

    public boolean DeleteFromStorage() {
        if (this.msg != null) {
            return this.msg.deleteFromStorage();
        }
        QLog.i(tag, 1, "msg is null");
        return false;
    }

    public int addElement(TIMElem tIMElem) {
        if (tIMElem == null) {
            return 1;
        }
        Elem convertTo = tIMElem.convertTo();
        if (convertTo == null) {
            return 1;
        }
        this.msg.addElem(convertTo);
        return 0;
    }

    public boolean convertToImportedMsg() {
        return this.msg == null ? false : this.msg.convertToImportedmsg();
    }

    public boolean copyFrom(TIMMessage tIMMessage) {
        return (this.msg == null || tIMMessage == null) ? false : this.msg.copyFrom(tIMMessage.getMsg());
    }

    public TIMConversation getConversation() {
        return this.conversation;
    }

    public int getCustomInt() {
        return this.msg == null ? 0 : this.msg.customInt();
    }

    public String getCustomStr() {
        return this.msg == null ? "" : this.msg.customStr();
    }

    public TIMElem getElement(int i) {
        if (this.msg == null || i < 0) {
            return defaultElem;
        }
        try {
            this.msg.elemType((long) i);
            return TIMElem.convertFrom(this.msg.getElem((long) i));
        } catch (Throwable th) {
            String exceptionInfo = IMFunc.getExceptionInfo(th);
            QLog.e(tag, 1, exceptionInfo);
            TIMExceptionListener exceptionListener = TIMManager.getInstance().getExceptionListener();
            if (exceptionListener != null) {
                exceptionListener.onException(exceptionInfo);
            }
            return defaultElem;
        }
    }

    public long getElementCount() {
        return this.msg == null ? 0 : this.msg.elemSize();
    }

    String getGroupName() {
        Object str;
        try {
            str = new String(this.msg.getGroupName(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            str = null;
        } catch (Throwable th) {
            String exceptionInfo = IMFunc.getExceptionInfo(th);
            QLog.e(tag, 1, exceptionInfo);
            TIMExceptionListener exceptionListener = TIMManager.getInstance().getExceptionListener();
            if (exceptionListener != null) {
                exceptionListener.onException(exceptionInfo);
            }
            str = null;
        }
        return TextUtils.isEmpty(str) ? "" : str;
    }

    Msg getMsg() {
        return this.msg;
    }

    public String getMsgId() {
        String str;
        if (this.msg == null) {
            QLog.i(tag, 1, "msg is null");
            str = "";
        } else {
            try {
                str = new String(this.msg.msgid(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "";
            }
        }
        QLog.i(tag, 1, "msgid " + str);
        return str;
    }

    public long getMsgUniqueId() {
        return this.msg != null ? this.msg.uniqueid() : 0;
    }

    public TIMMessageOfflinePushSettings getOfflinePushSettings() {
        return (this.msg == null || !this.msg.getOfflinePushInfo().getIsValid()) ? null : TIMMessageOfflinePushSettings.convertFrom(this.msg.getOfflinePushInfo());
    }

    public TIMMessagePriority getPriority() {
        if (this.msg == null) {
            return TIMMessagePriority.Normal;
        }
        for (TIMMessagePriority tIMMessagePriority : TIMMessagePriority.values()) {
            if (tIMMessagePriority.getValue() == this.msg.priority().swigValue()) {
                return tIMMessagePriority;
            }
        }
        return TIMMessagePriority.Normal;
    }

    public TIMGroupReceiveMessageOpt getRecvFlag() {
        return this.msg != null ? TIMGroupReceiveMessageOpt.values()[this.msg.getRecvFlag()] : null;
    }

    public String getSender() {
        if (this.msg != null) {
            return this.msg.getSender();
        }
        QLog.i(tag, 1, "msg is null");
        return null;
    }

    public TIMGroupMemberInfo getSenderGroupMemberProfile() {
        if (this.msg == null || getConversation().getType() != TIMConversationType.Group) {
            return null;
        }
        TIMGroupMemberInfo tIMGroupMemberInfo = new TIMGroupMemberInfo(this.msg.getSenderGroupMemberProfile());
        return tIMGroupMemberInfo.getUser().length() != 0 ? tIMGroupMemberInfo : null;
    }

    public TIMUserProfile getSenderProfile() {
        if (this.msg == null) {
            return null;
        }
        TIMUserProfile tIMUserProfile = new TIMUserProfile(this.msg.getSenderProfile());
        return tIMUserProfile.getIdentifier().length() != 0 ? tIMUserProfile : null;
    }

    public boolean hasGap() {
        return this.msg == null ? false : this.msg.hasGap();
    }

    public boolean isPeerReaded() {
        return this.msg == null ? false : this.msg.isPeerRead();
    }

    public boolean isRead() {
        return this.msg != null ? this.msg.isRead() : true;
    }

    public boolean isSelf() {
        return this.msg != null ? this.msg.isSelf() : true;
    }

    public boolean remove() {
        return this.msg == null ? false : this.msg.remove();
    }

    void setConversation(TIMConversation tIMConversation) {
        this.conversation = tIMConversation;
    }

    public void setCustomInt(int i) {
        if (this.msg != null) {
            this.msg.setCustomInt(i);
        }
    }

    public void setCustomStr(String str) {
        if (this.msg != null) {
            Msg msg = this.msg;
            if (str == null) {
                str = "";
            }
            msg.setCustomStr(str);
        }
    }

    public void setOfflinePushSettings(TIMMessageOfflinePushSettings tIMMessageOfflinePushSettings) {
        if (this.msg != null && tIMMessageOfflinePushSettings != null) {
            this.msg.setOfflinePushInfo(tIMMessageOfflinePushSettings.convertTo());
        }
    }

    public void setPriority(TIMMessagePriority tIMMessagePriority) {
        if (this.msg != null) {
            this.msg.setPriority(MsgPriority.swigToEnum(tIMMessagePriority.getValue()));
        }
    }

    public boolean setSender(String str) {
        if (this.msg == null) {
            return false;
        }
        Msg msg = this.msg;
        if (str == null) {
            str = "";
        }
        return msg.setSender(str);
    }

    public boolean setTimestamp(long j) {
        return this.msg == null ? false : this.msg.setTime(j);
    }

    public TIMMessageStatus status() {
        if (this.msg == null) {
            return TIMMessageStatus.SendSucc;
        }
        switch (this.msg.status()) {
            case 1:
                return TIMMessageStatus.Sending;
            case 2:
                return TIMMessageStatus.SendSucc;
            case 3:
                return TIMMessageStatus.SendFail;
            case 4:
                return TIMMessageStatus.HasDeleted;
            default:
                return TIMMessageStatus.SendSucc;
        }
    }

    public long timestamp() {
        return this.msg != null ? this.msg.time() : 0;
    }
}

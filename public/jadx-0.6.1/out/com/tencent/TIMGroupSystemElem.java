package com.tencent;

import com.tencent.imcore.GroupReportElem;
import com.tencent.imcore.IGroupNotifyCallback;
import java.io.UnsupportedEncodingException;

public class TIMGroupSystemElem extends TIMElem {
    private byte[] authKey;
    private String groupId;
    private long msgKey;
    private TIMGroupMemberInfo opGroupMemberInfo;
    private String opReason;
    private String opUser;
    private TIMUserProfile opUserInfo;
    private String platform;
    private long subtype;
    private byte[] userData;

    abstract class aa extends IGroupNotifyCallback {
        public TIMCallBack a;

        public aa(TIMGroupSystemElem tIMGroupSystemElem, TIMCallBack tIMCallBack) {
            swigReleaseOwnership();
            this.a = tIMCallBack;
        }

        public abstract void a();

        public abstract void a(int i, String str);

        public void done() {
            a();
            swigTakeOwnership();
        }

        public void fail(int i, String str) {
            a(i, str);
            swigTakeOwnership();
        }
    }

    public TIMGroupSystemElem() {
        this.groupId = "";
        this.opUser = "";
        this.opReason = "";
        this.userData = null;
        this.platform = "";
        this.type = TIMElemType.GroupSystem;
    }

    public TIMGroupSystemElem(GroupReportElem groupReportElem) {
        this.groupId = "";
        this.opUser = "";
        this.opReason = "";
        this.userData = null;
        this.platform = "";
        this.type = TIMElemType.GroupSystem;
        setAuthKey(groupReportElem.getAuthentication());
        setGroupId(groupReportElem.getGroup());
        setMsgKey(groupReportElem.getMsg_key());
        try {
            setOpReason(new String(groupReportElem.getMsg(), "utf-8"));
            setPlatform(new String(groupReportElem.getPlatform(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        setSubtype(groupReportElem.getType());
        setOpUser(groupReportElem.getOperator_user());
        setUserData(groupReportElem.getUser_data());
        setOpUserInfo(new TIMUserProfile(groupReportElem.getOperator_user_info()));
        setOpGroupMemberInfo(new TIMGroupMemberInfo(groupReportElem.getOperator_group_member_info()));
    }

    public void accept(String str, TIMCallBack tIMCallBack) {
        IGroupNotifyCallback guVar = new gu(this, tIMCallBack);
        String str2 = "";
        if (str == null) {
            str = str2;
        }
        try {
            if (getSubtype() == TIMGroupSystemElemType.TIM_GROUP_SYSTEM_ADD_GROUP_REQUEST_TYPE) {
                TIMManager.getInstance().getCoreUser().getGroupMgr().handleJoinRequest(this.groupId, this.opUser, str.getBytes("utf-8"), "".getBytes("utf-8"), 1, this.msgKey, this.authKey, guVar);
            } else if (getSubtype() == TIMGroupSystemElemType.TIM_GROUP_SYSTEM_INVITE_TO_GROUP_REQUEST_TYPE) {
                TIMManager.getInstance().getCoreUser().getGroupMgr().handleInviteRequest(this.groupId, this.opUser, str.getBytes("utf-8"), "".getBytes("utf-8"), 1, this.msgKey, this.authKey, guVar);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getGroupId() {
        return this.groupId;
    }

    public TIMGroupMemberInfo getOpGroupMemberInfo() {
        return this.opGroupMemberInfo;
    }

    public String getOpReason() {
        return this.opReason;
    }

    public String getOpUser() {
        return this.opUser;
    }

    public TIMUserProfile getOpUserInfo() {
        return this.opUserInfo;
    }

    public String getPlatform() {
        return this.platform;
    }

    public TIMGroupSystemElemType getSubtype() {
        for (TIMGroupSystemElemType tIMGroupSystemElemType : TIMGroupSystemElemType.values()) {
            if (this.subtype == tIMGroupSystemElemType.getValue()) {
                return tIMGroupSystemElemType;
            }
        }
        return TIMGroupSystemElemType.INVALID;
    }

    public byte[] getUserData() {
        return this.userData;
    }

    public void refuse(String str, TIMCallBack tIMCallBack) {
        IGroupNotifyCallback gvVar = new gv(this, tIMCallBack);
        String str2 = "";
        if (str == null) {
            str = str2;
        }
        try {
            if (getSubtype() == TIMGroupSystemElemType.TIM_GROUP_SYSTEM_ADD_GROUP_REQUEST_TYPE) {
                TIMManager.getInstance().getCoreUser().getGroupMgr().handleJoinRequest(this.groupId, this.opUser, str.getBytes("utf-8"), "".getBytes("utf-8"), 0, this.msgKey, this.authKey, gvVar);
            } else if (getSubtype() == TIMGroupSystemElemType.TIM_GROUP_SYSTEM_INVITE_TO_GROUP_REQUEST_TYPE) {
                TIMManager.getInstance().getCoreUser().getGroupMgr().handleInviteRequest(this.groupId, this.opUser, str.getBytes("utf-8"), "".getBytes("utf-8"), 0, this.msgKey, this.authKey, gvVar);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    void setAuthKey(byte[] bArr) {
        this.authKey = bArr;
    }

    void setGroupId(String str) {
        this.groupId = str;
    }

    void setMsgKey(long j) {
        this.msgKey = j;
    }

    void setOpGroupMemberInfo(TIMGroupMemberInfo tIMGroupMemberInfo) {
        this.opGroupMemberInfo = tIMGroupMemberInfo;
    }

    void setOpReason(String str) {
        this.opReason = str;
    }

    void setOpUser(String str) {
        this.opUser = str;
    }

    void setOpUserInfo(TIMUserProfile tIMUserProfile) {
        this.opUserInfo = tIMUserProfile;
    }

    void setPlatform(String str) {
        this.platform = str;
    }

    void setSubtype(long j) {
        this.subtype = j;
    }

    void setUserData(byte[] bArr) {
        this.userData = bArr;
    }
}

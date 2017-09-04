package com.tencent.av.Message;

import com.tencent.TIMCallBack;
import com.tencent.TIMUser;
import com.tencent.av.Message.AvMsg.Type;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.IMMsfUserInfo;
import com.tencent.mobileqq.pb.ByteStringMicro;
import com.tencent.mobileqq.pb.MessageMicro;
import com.tencent.openqq.protocol.imsdk.msg;
import com.tencent.openqq.protocol.imsdk.videoinvitation.MsgBody;
import com.tencent.openqq.protocol.imsdk.videoinvitation.UserInfo;
import java.util.ArrayList;
import java.util.List;

public class AvMsg0x32 extends AvMsg {
    private String identifer = "";

    public AvMsg0x32(String str) {
        this.identifer = str;
    }

    private int get0x32MsgType() {
        switch (ac.a[getMsgType().ordinal()]) {
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            default:
                return 0;
        }
    }

    private void set0x32MsgType(int i) {
        switch (i) {
            case 0:
                setMsgType(Type.MutiAvInvitation);
                return;
            case 1:
                setMsgType(Type.MutiAvCancel);
                return;
            case 2:
                setMsgType(Type.MutiAvAccept);
                return;
            case 3:
                setMsgType(Type.MutiAvReject);
                return;
            default:
                return;
        }
    }

    public void receive(byte[] bArr) {
        MsgBody msgBody = new MsgBody();
        try {
            msgBody.mergeFrom(bArr);
            set0x32MsgType(msgBody.int32_request_type.get());
            TIMUser tIMUser = new TIMUser();
            tIMUser.setAppIdAt3rd(msgBody.msg_sender.bytes_appid.get().toStringUtf8());
            tIMUser.setIdentifier(msgBody.msg_sender.bytes_openid.get().toStringUtf8());
            tIMUser.setAccountType(msgBody.msg_sender.bytes_acounttype.get().toStringUtf8());
            setSender(tIMUser);
            this.bussType = msgBody.int32_buss_type.get();
            this.authType = msgBody.int32_auth_type.get();
            this.auth_id = msgBody.uint32_auth_id.get();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void response(TIMCallBack tIMCallBack) {
        IMMsfUserInfo msfUserInfo = IMMsfCoreProxy.get().getMsfUserInfo(this.identifer);
        IMMsfUserInfo iMMsfUserInfo = msfUserInfo == null ? new IMMsfUserInfo() : msfUserInfo;
        MsgBody msgBody = new MsgBody();
        msgBody.int32_buss_type.set(this.bussType);
        msgBody.int32_auth_type.set(this.authType);
        msgBody.uint32_auth_id.set(this.auth_id);
        addReceivers(getSender());
        msgBody.uint32_sdk_appid.set(IMMsfCoreProxy.get().getSdkAppId());
        msgBody.int32_request_type.set(get0x32MsgType());
        MessageMicro userInfo = new UserInfo();
        userInfo.bytes_appid.set(ByteStringMicro.copyFromUtf8(iMMsfUserInfo.getsUerAppId()));
        userInfo.bytes_openid.set(ByteStringMicro.copyFromUtf8(iMMsfUserInfo.getUserId()));
        userInfo.bytes_acounttype.set(ByteStringMicro.copyFromUtf8(IMMsfCoreProxy.get().getUidType()));
        msgBody.msg_sender.set(userInfo);
        List arrayList = new ArrayList();
        if (getReceivers() != null) {
            for (TIMUser tIMUser : getReceivers()) {
                UserInfo userInfo2 = new UserInfo();
                userInfo2.bytes_acounttype.set(ByteStringMicro.copyFromUtf8(tIMUser.getAccountType()));
                userInfo2.bytes_appid.set(ByteStringMicro.copyFromUtf8(tIMUser.getAppIdAt3rd()));
                userInfo2.bytes_openid.set(ByteStringMicro.copyFromUtf8(tIMUser.getIdentifier()));
                arrayList.add(userInfo2);
            }
            msgBody.rpt_msg_receiver_list.set(arrayList);
        }
        msg.MsgBody msgBody2 = new msg.MsgBody();
        msgBody2.msg_content.set(ByteStringMicro.copyFrom(msgBody.toByteArray()));
        IMMsfCoreProxy.get().request(iMMsfUserInfo.getUserId(), "openim.videoinvitaion", msgBody2.toByteArray(), new ab(this, tIMCallBack));
    }

    public void send(TIMCallBack tIMCallBack) {
        IMMsfUserInfo msfUserInfo = IMMsfCoreProxy.get().getMsfUserInfo(this.identifer);
        IMMsfUserInfo iMMsfUserInfo = msfUserInfo == null ? new IMMsfUserInfo() : msfUserInfo;
        MsgBody msgBody = new MsgBody();
        msgBody.int32_buss_type.set(this.bussType);
        msgBody.int32_auth_type.set(this.authType);
        msgBody.uint32_auth_id.set(this.auth_id);
        msgBody.uint32_sdk_appid.set(IMMsfCoreProxy.get().getSdkAppId());
        msgBody.int32_request_type.set(get0x32MsgType());
        MessageMicro userInfo = new UserInfo();
        userInfo.bytes_appid.set(ByteStringMicro.copyFromUtf8(iMMsfUserInfo.getsUerAppId()));
        userInfo.bytes_openid.set(ByteStringMicro.copyFromUtf8(iMMsfUserInfo.getUserId()));
        userInfo.bytes_acounttype.set(ByteStringMicro.copyFromUtf8(IMMsfCoreProxy.get().getUidType()));
        msgBody.msg_sender.set(userInfo);
        List arrayList = new ArrayList();
        if (getReceivers() != null) {
            for (TIMUser tIMUser : getReceivers()) {
                UserInfo userInfo2 = new UserInfo();
                userInfo2.bytes_acounttype.set(ByteStringMicro.copyFromUtf8(tIMUser.getAccountType()));
                userInfo2.bytes_appid.set(ByteStringMicro.copyFromUtf8(tIMUser.getAppIdAt3rd()));
                userInfo2.bytes_openid.set(ByteStringMicro.copyFromUtf8(tIMUser.getIdentifier()));
                arrayList.add(userInfo2);
            }
            msgBody.rpt_msg_receiver_list.set(arrayList);
        }
        msg.MsgBody msgBody2 = new msg.MsgBody();
        msgBody2.msg_content.set(ByteStringMicro.copyFrom(msgBody.toByteArray()));
        IMMsfCoreProxy.get().request(iMMsfUserInfo.getUserId(), "openim.videoinvitaion", msgBody2.toByteArray(), new aa(this, tIMCallBack));
    }
}

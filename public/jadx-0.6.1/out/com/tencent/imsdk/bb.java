package com.tencent.imsdk;

import android.text.TextUtils;
import com.dynamicload.Lib.DLConstants;
import com.tencent.IMCoreUploadLogFileOpt;
import com.tencent.IMCoreWrapper;
import com.tencent.IMFunc;
import com.tencent.ProtobufParser;
import com.tencent.TIMExceptionListener;
import com.tencent.TIMManager;
import com.tencent.TIMOfflinePushListener;
import com.tencent.TIMOfflinePushNotification;
import com.tencent.TIMValueCallBack;
import com.tencent.imcore.IMCore;
import com.tencent.mobileqq.pb.ByteStringMicro;
import com.tencent.openqq.IMPushListener;
import com.tencent.openqq.protocol.imsdk.log_upload_pb.MsgBody;
import com.tencent.openqq.protocol.imsdk.msg_push.ReqBody;
import com.tencent.openqq.protocol.imsdk.msg_push.RspBody;

final class bb implements Runnable {
    private /* synthetic */ String a;
    private /* synthetic */ byte[] b;
    private /* synthetic */ ba c;

    bb(ba baVar, String str, byte[] bArr) {
        this.c = baVar;
        this.a = str;
        this.b = bArr;
    }

    public final void run() {
        String identification = TextUtils.isEmpty(this.a) ? TIMManager.getInstance().getIdentification() : this.a;
        QLog.i("imsdk.IMMsfCoreProxy", 1, "RecvMsg|1-OnlinePush|Succ|cmd=im_open_push.msg_push, " + this.a + DLConstants.DEPENDENCY_PACKAGE_DIV + identification);
        boolean z = this.c.a.mode == 1;
        if (z) {
            try {
                int manualPush = IMCore.get().getUser(identification).manualPush(this.b);
            } catch (Throwable th) {
                identification = IMFunc.getExceptionInfo(th);
                QLog.e("imsdk.IMMsfCoreProxy", 1, identification);
                TIMExceptionListener exceptionListener = TIMManager.getInstance().getExceptionListener();
                if (exceptionListener != null) {
                    exceptionListener.onException(identification);
                    return;
                }
                return;
            }
        }
        manualPush = 0;
        if (!z || r1 == 1) {
            ReqBody reqBody = new ReqBody();
            try {
                reqBody.mergeFrom(this.b);
                int i = reqBody.msg_msg.msg_msg_head.msg_content_head.uint32_type.get();
                int i2 = reqBody.msg_msg.msg_msg_head.msg_content_head.uint32_subtype.get();
                long j = reqBody.msg_msg.msg_msg_head.msg_routine_head.msg_c2c_info.uint64_to_uin.get();
                IMMsfUserInfo msfUserInfo = IMMsfCoreProxy.get().getMsfUserInfo(identification);
                if (msfUserInfo == null || !msfUserInfo.isLoggedIn()) {
                    QLog.w("imsdk.IMMsfCoreProxy", 1, "RecvMsg|1-OnlinePush|Fail| user not found or not online. id: " + this.a + DLConstants.DEPENDENCY_PACKAGE_DIV + identification);
                    QLog.d("imsdk.IMMsfCoreProxy", 1, "OfflinePush|1-Begin|succ|recv offline push, id: " + this.a + DLConstants.DEPENDENCY_PACKAGE_DIV + identification);
                    RspBody rspBody = new RspBody();
                    rspBody.bytes_session_data.setHasFlag(true);
                    rspBody.bytes_session_data.set(ByteStringMicro.copyFrom(reqBody.bytes_session_data.get().toByteArray()));
                    IMMsfCoreProxy.get().request(identification, "im_open_push.msg_push", rspBody.toByteArray(), null);
                    if (this.c.a.isOfflinePushEnabled(this.c.a.context, identification)) {
                        TIMOfflinePushListener offlinePushListener = TIMManager.getInstance().getOfflinePushListener();
                        if (offlinePushListener == null) {
                            QLog.d("imsdk.IMMsfCoreProxy", 1, "OfflinePush|2-callback|failed|no callback ");
                            return;
                        }
                        TIMOfflinePushNotification offlinePush2PushNotification = ProtobufParser.offlinePush2PushNotification(identification, this.c.a.context, this.b);
                        if (offlinePush2PushNotification == null || !offlinePush2PushNotification.isValid()) {
                            QLog.d("imsdk.IMMsfCoreProxy", 1, "OfflinePush|2-callback|failed|invalid msg");
                            return;
                        }
                        offlinePushListener.handleNotification(offlinePush2PushNotification);
                        QLog.d("imsdk.IMMsfCoreProxy", 1, "OfflinePush|2-callback|succ|callback");
                        return;
                    }
                    QLog.d("imsdk.IMMsfCoreProxy", 1, "OfflinePush|2-callback|failed|offline push disabled");
                } else if ((i == 561 || i == 734) && j != msfUserInfo.getTinyid()) {
                    QLog.d("imsdk.IMMsfCoreProxy", 1, "discard other's push: " + j);
                } else {
                    RspBody rspBody2 = new RspBody();
                    rspBody2.bytes_session_data.setHasFlag(true);
                    rspBody2.bytes_session_data.set(ByteStringMicro.copyFrom(reqBody.bytes_session_data.get().toByteArray()));
                    IMMsfCoreProxy.get().request(identification, "im_open_push.msg_push", rspBody2.toByteArray(), null);
                    QLog.e("imsdk.IMMsfCoreProxy", 1, "msg type: " + i + " subtype: " + i2 + "/" + identification);
                    if (i == 561 && i2 == 34) {
                        MsgBody msgBody = new MsgBody();
                        try {
                            msgBody.mergeFrom(reqBody.msg_msg.msg_msg_body.msg_content.get().toByteArray());
                            IMCoreUploadLogFileOpt iMCoreUploadLogFileOpt = new IMCoreUploadLogFileOpt();
                            String str = msgBody.rpt_string_tag.get();
                            iMCoreUploadLogFileOpt.setFilePath(IMCoreWrapper.get().getLogPath() + str + "_" + ((String) msgBody.rpt_string_logtime.get(0)) + ".log");
                            iMCoreUploadLogFileOpt.setTag(str);
                            iMCoreUploadLogFileOpt.setLogId(msgBody.bytes_log_id.get().toStringUtf8());
                            iMCoreUploadLogFileOpt.setLogSize((long) msgBody.uint32_log_size.get());
                            iMCoreUploadLogFileOpt.setRelativePath(msgBody.bytes_log_path.get().toStringUtf8());
                            this.c.a.uploadLogFile(this.a, iMCoreUploadLogFileOpt);
                            return;
                        } catch (Throwable th2) {
                            QLog.e("imsdk.IMMsfCoreProxy", 1, IMFunc.getExceptionInfo(th2));
                            QLog.e("imsdk.IMMsfCoreProxy", 1, "parse upload log pb failed");
                            return;
                        }
                    }
                    String str2 = "im_open_push.msg_push" + "_" + i;
                    if (msfUserInfo.getCmd2PushListener().containsKey(str2)) {
                        IMPushListener iMPushListener = (IMPushListener) msfUserInfo.getCmd2PushListener().get(str2);
                        if (i == 734) {
                            IMMsfCoreProxy.mainHandler.post(new bc(this, iMPushListener, reqBody));
                        } else {
                            iMPushListener.onRecv(this.b);
                        }
                    }
                    if (msfUserInfo.getCmd2PushCallBack().containsKey(str2)) {
                        TIMValueCallBack tIMValueCallBack = (TIMValueCallBack) msfUserInfo.getCmd2PushCallBack().get(str2);
                        if (i == 734) {
                            IMMsfCoreProxy.mainHandler.post(new bd(this, tIMValueCallBack, reqBody));
                        } else if (i2 == 50 && this.c.a.getMode() == 0) {
                            IMMsfCoreProxy.mainHandler.post(new be(this, reqBody));
                        } else {
                            tIMValueCallBack.onSuccess(this.b);
                        }
                    }
                }
            } catch (Throwable th22) {
                QLog.e("imsdk.IMMsfCoreProxy", 1, IMFunc.getExceptionInfo(th22));
            }
        }
    }
}

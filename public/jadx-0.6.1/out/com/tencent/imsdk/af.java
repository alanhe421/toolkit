package com.tencent.imsdk;

import android.net.Uri;
import android.text.TextUtils;
import com.tencent.IMFunc;
import com.tencent.TIMOfflinePushSettings;
import com.tencent.TIMValueCallBack;
import com.tencent.openqq.protocol.imsdk.common.CmdErrorCode;
import com.tencent.openqq.protocol.imsdk.stat_get_pushsound.RspBody;

final class af implements TIMValueCallBack<byte[]> {
    private /* synthetic */ TIMValueCallBack a;
    private /* synthetic */ String b;
    private /* synthetic */ IMMsfCoreProxy c;

    af(IMMsfCoreProxy iMMsfCoreProxy, TIMValueCallBack tIMValueCallBack, String str) {
        this.c = iMMsfCoreProxy;
        this.a = tIMValueCallBack;
        this.b = str;
    }

    public final void onError(int i, String str) {
        QLog.e("imsdk.IMMsfCoreProxy", 1, "getOfflinePushSettings request failed, code: " + i + "|desc: " + str);
        this.a.onError(i, str);
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        byte[] bArr = (byte[]) obj;
        RspBody rspBody = new RspBody();
        try {
            rspBody.mergeFrom(bArr);
            if (!rspBody.enum_cmd_error_code.has() || ((CmdErrorCode) rspBody.enum_cmd_error_code.get()).uint32_code.get() == 0) {
                TIMOfflinePushSettings tIMOfflinePushSettings = new TIMOfflinePushSettings();
                if (rspBody.msg_config.has()) {
                    tIMOfflinePushSettings.setEnabled(rspBody.msg_config.uint32_openpush.get() == 1);
                    Object toStringUtf8 = rspBody.msg_config.bytes_c2c_sound.get().toStringUtf8();
                    if (!TextUtils.isEmpty(toStringUtf8)) {
                        tIMOfflinePushSettings.setC2cMsgRemindSound(Uri.parse(toStringUtf8));
                    }
                    toStringUtf8 = rspBody.msg_config.bytes_grp_sound.get().toStringUtf8();
                    if (!TextUtils.isEmpty(toStringUtf8)) {
                        tIMOfflinePushSettings.setGroupMsgRemindSound(Uri.parse(toStringUtf8));
                    }
                }
                QLog.i("imsdk.IMMsfCoreProxy", 1, "getOfflinePushSettings succ");
                this.a.onSuccess(tIMOfflinePushSettings);
                this.c.saveOfflinePushSettingsToLocal(this.b, tIMOfflinePushSettings);
                return;
            }
            QLog.e("imsdk.IMMsfCoreProxy", 1, "getOfflinePushSettings failed, code: " + ((CmdErrorCode) rspBody.enum_cmd_error_code.get()).uint32_code.get() + "|desc: " + ((CmdErrorCode) rspBody.enum_cmd_error_code.get()).bytes_err_msg.get().toStringUtf8());
            this.a.onError(((CmdErrorCode) rspBody.enum_cmd_error_code.get()).uint32_code.get(), ((CmdErrorCode) rspBody.enum_cmd_error_code.get()).bytes_err_msg.get().toStringUtf8());
        } catch (Throwable th) {
            QLog.e("imsdk.IMMsfCoreProxy", 1, IMFunc.getExceptionInfo(th));
            QLog.e("imsdk.IMMsfCoreProxy", 1, "getOfflinePushSettings failed, parse rsp failed");
        }
    }
}

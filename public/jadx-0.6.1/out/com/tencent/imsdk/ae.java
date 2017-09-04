package com.tencent.imsdk;

import com.tencent.IMFunc;
import com.tencent.TIMOfflinePushSettings;
import com.tencent.TIMValueCallBack;
import com.tencent.openqq.protocol.imsdk.common.CmdErrorCode;
import com.tencent.openqq.protocol.imsdk.stat_set_pushsound.RspBody;

final class ae implements TIMValueCallBack<byte[]> {
    private /* synthetic */ String a;
    private /* synthetic */ TIMOfflinePushSettings b;
    private /* synthetic */ IMMsfCoreProxy c;

    ae(IMMsfCoreProxy iMMsfCoreProxy, String str, TIMOfflinePushSettings tIMOfflinePushSettings) {
        this.c = iMMsfCoreProxy;
        this.a = str;
        this.b = tIMOfflinePushSettings;
    }

    public final void onError(int i, String str) {
        QLog.e("imsdk.IMMsfCoreProxy", 1, "initOfflinePushSettings request failed, code: " + i + "|desc: " + str);
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        byte[] bArr = (byte[]) obj;
        RspBody rspBody = new RspBody();
        try {
            rspBody.mergeFrom(bArr);
            if (!rspBody.enum_cmd_error_code.has() || ((CmdErrorCode) rspBody.enum_cmd_error_code.get()).uint32_code.get() == 0) {
                QLog.i("imsdk.IMMsfCoreProxy", 1, "initOfflinePushSettings succ");
                this.c.saveOfflinePushSettingsToLocal(this.a, this.b);
                return;
            }
            QLog.e("imsdk.IMMsfCoreProxy", 1, "initOfflinePushSettings failed, code: " + ((CmdErrorCode) rspBody.enum_cmd_error_code.get()).uint32_code.get() + "|desc: " + ((CmdErrorCode) rspBody.enum_cmd_error_code.get()).bytes_err_msg.get().toStringUtf8());
        } catch (Throwable th) {
            QLog.e("imsdk.IMMsfCoreProxy", 1, IMFunc.getExceptionInfo(th));
            QLog.e("imsdk.IMMsfCoreProxy", 1, "initOfflinePushSettings failed, parse rsp failed");
        }
    }
}

package com.qq.reader.module.dicovery.a;

import android.os.Message;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.login.define.a;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.H5GameGetOpenidTask;
import com.tencent.util.WeakReferenceHandler;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: H5GameGetOpenidHandler */
public class c implements com.qq.reader.common.readertask.ordinal.c {
    public WeakReferenceHandler a;
    private final String b = "H5GameGetOpenidHandler";

    public c(WeakReferenceHandler weakReferenceHandler) {
        this.a = weakReferenceHandler;
    }

    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("code", 0);
            if (optInt == 0) {
                a.l(ReaderApplication.getApplicationImp(), jSONObject.optString("openid"));
            } else {
                f.a("H5GameGetOpenidHandler", "get openid error  code" + optInt);
            }
            a(readerProtocolTask, optInt);
        } catch (JSONException e) {
            a(readerProtocolTask, -100);
            e.printStackTrace();
        }
    }

    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
        a(readerProtocolTask, DLConstants.LOAD_ERR_SIGNATURES);
    }

    private void a(ReaderProtocolTask readerProtocolTask, int i) {
        if ((readerProtocolTask instanceof H5GameGetOpenidTask) && this.a != null) {
            String webJSCallBack = ((H5GameGetOpenidTask) readerProtocolTask).getWebJSCallBack();
            Message obtainMessage = this.a.obtainMessage();
            obtainMessage.obj = webJSCallBack;
            obtainMessage.what = 102;
            obtainMessage.arg1 = i;
            this.a.sendMessage(obtainMessage);
        }
    }
}

package com.qq.reader.module.dicovery.a;

import android.os.Bundle;
import android.os.Message;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.H5GameGrantTicketTask;
import com.tencent.open.SocialConstants;
import com.tencent.util.WeakReferenceHandler;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: H5GameGrantTicketHandler */
public class d implements c {
    public WeakReferenceHandler a;
    public int b = 0;
    public int c;

    public d(WeakReferenceHandler weakReferenceHandler) {
        this.a = weakReferenceHandler;
    }

    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            f.d("H5GameGrantTicketHandler", jSONObject.toString());
            a(readerProtocolTask, jSONObject.optInt("code", 0), jSONObject.getString(SocialConstants.PARAM_SEND_MSG));
        } catch (JSONException e) {
            a(readerProtocolTask, -100, "服务器抽风啦，请稍候再试");
            e.printStackTrace();
        }
    }

    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
        a(readerProtocolTask, DLConstants.LOAD_ERR_SIGNATURES, "网络出错，请检测网络");
    }

    private void a(ReaderProtocolTask readerProtocolTask, int i, String str) {
        if (readerProtocolTask instanceof H5GameGrantTicketTask) {
            String webJSCallBack = ((H5GameGrantTicketTask) readerProtocolTask).getWebJSCallBack();
            Message obtainMessage = this.a.obtainMessage();
            obtainMessage.obj = webJSCallBack;
            obtainMessage.what = 103;
            obtainMessage.arg1 = i;
            obtainMessage.arg2 = this.c;
            if (str != null) {
                Bundle bundle = new Bundle();
                bundle.putString("H5GAME_GRANTTICKET_MESSAGE", str);
                bundle.putInt("H5GAME_GRANTTICKET_COUNT", this.b);
                obtainMessage.setData(bundle);
            }
            this.a.sendMessage(obtainMessage);
        }
    }
}

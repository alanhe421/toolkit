package com.qq.reader.module.dicovery.a;

import android.os.Bundle;
import android.os.Message;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.H5GameGetBalanceTask;
import com.tencent.open.SocialConstants;
import com.tencent.util.WeakReferenceHandler;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: H5GameGetBalanceHandler */
public class b implements c {
    public WeakReferenceHandler a;

    public b(WeakReferenceHandler weakReferenceHandler) {
        this.a = weakReferenceHandler;
    }

    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("code", -1) == 0) {
                a(readerProtocolTask, 0, jSONObject.optInt("gameCoin"), jSONObject.optInt("bookCoin"), jSONObject.optString(SocialConstants.PARAM_SEND_MSG));
                return;
            }
            a(readerProtocolTask, -100, 0, 0, "请求失败");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
        a(readerProtocolTask, DLConstants.LOAD_ERR_SIGNATURES, 0, 0, "网络出错，请检测网络");
    }

    private void a(ReaderProtocolTask readerProtocolTask, int i, int i2, int i3, String str) {
        if (readerProtocolTask instanceof H5GameGetBalanceTask) {
            String webJSCallBack = ((H5GameGetBalanceTask) readerProtocolTask).getWebJSCallBack();
            Message obtainMessage = this.a.obtainMessage();
            obtainMessage.obj = webJSCallBack;
            obtainMessage.what = 105;
            obtainMessage.arg1 = i;
            Bundle bundle = new Bundle();
            bundle.putInt("H5GEME_GET_BALANCE_BOOKCOIN", i3);
            bundle.putInt("H5GEME_GET_BALANCE_GAMECOIN", i2);
            bundle.putString("H5GAME_GET_BALANCE_MSG", str);
            obtainMessage.setData(bundle);
            this.a.sendMessage(obtainMessage);
        }
    }
}

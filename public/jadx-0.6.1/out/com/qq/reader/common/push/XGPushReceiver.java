package com.qq.reader.common.push;

import android.content.Context;
import com.qq.reader.appconfig.b;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.j;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;
import org.json.JSONException;
import org.json.JSONObject;

public class XGPushReceiver extends XGPushBaseReceiver {
    private void a(Context context, String str) {
    }

    public void onRegisterResult(Context context, int i, XGPushRegisterResult xGPushRegisterResult) {
        if (context != null && xGPushRegisterResult != null) {
            String str;
            if (i == 0) {
                str = xGPushRegisterResult + "注册成功";
                xGPushRegisterResult.getToken();
            } else {
                str = xGPushRegisterResult + "注册失败，错误码：" + i;
            }
            f.d("TPushReceiver", str);
            a(context, str);
        }
    }

    public void onUnregisterResult(Context context, int i) {
        if (context != null) {
            String str;
            if (i == 0) {
                str = "反注册成功";
            } else {
                str = "反注册失败" + i;
            }
            f.d("TPushReceiver", str);
            a(context, str);
        }
    }

    public void onSetTagResult(Context context, int i, String str) {
        if (context != null) {
            String str2;
            if (i == 0) {
                str2 = "\"" + str + "\"设置成功";
            } else {
                str2 = "\"" + str + "\"设置失败,错误码：" + i;
            }
            f.d("TPushReceiver", str2);
            a(context, str2);
        }
    }

    public void onDeleteTagResult(Context context, int i, String str) {
        if (context != null) {
            String str2;
            if (i == 0) {
                str2 = "\"" + str + "\"删除成功";
            } else {
                str2 = "\"" + str + "\"删除失败,错误码：" + i;
            }
            f.d("TPushReceiver", str2);
            a(context, str2);
        }
    }

    public void onTextMessage(Context context, XGPushTextMessage xGPushTextMessage) {
        if (context != null && xGPushTextMessage != null) {
            String str = "收到消息:" + xGPushTextMessage.toString();
            if (b.a) {
                j.c("onTextMessage： XG message :" + str);
            }
            a.a(context, xGPushTextMessage.getContent(), a.a);
        }
    }

    public void onNotifactionClickedResult(Context context, XGPushClickedResult xGPushClickedResult) {
        if (context != null && xGPushClickedResult != null) {
            String str = "通知被打开 :" + xGPushClickedResult;
            String customContent = xGPushClickedResult.getCustomContent();
            if (!(customContent == null || customContent.length() == 0)) {
                try {
                    JSONObject jSONObject = new JSONObject(customContent);
                    if (!jSONObject.isNull("key")) {
                        f.d("TPushReceiver", "get custom value:" + jSONObject.getString("key"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            f.d("TPushReceiver", str);
        }
    }

    public void onNotifactionShowedResult(Context context, XGPushShowedResult xGPushShowedResult) {
        if (context != null && xGPushShowedResult != null) {
            String str = "通知被展示 ，title:" + xGPushShowedResult.getTitle() + ",content:" + xGPushShowedResult.getContent() + ",custom_content:" + xGPushShowedResult.getCustomContent();
            f.d("TPushReceiver", str);
            a(context, str);
        }
    }
}

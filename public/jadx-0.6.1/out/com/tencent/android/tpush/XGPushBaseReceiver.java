package com.tencent.android.tpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.b.c;
import com.tencent.android.tpush.b.i;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.p;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.d.f;
import java.util.List;

/* compiled from: ProGuard */
public abstract class XGPushBaseReceiver extends BroadcastReceiver {
    public static final int SUCCESS = 0;

    public abstract void onDeleteTagResult(Context context, int i, String str);

    public abstract void onNotifactionClickedResult(Context context, XGPushClickedResult xGPushClickedResult);

    public abstract void onNotifactionShowedResult(Context context, XGPushShowedResult xGPushShowedResult);

    public abstract void onRegisterResult(Context context, int i, XGPushRegisterResult xGPushRegisterResult);

    public abstract void onSetTagResult(Context context, int i, String str);

    public abstract void onTextMessage(Context context, XGPushTextMessage xGPushTextMessage);

    public abstract void onUnregisterResult(Context context, int i);

    public final void onReceive(Context context, Intent intent) {
        if (context != null && intent != null) {
            try {
                if (p.a(context) <= 0) {
                    String action = intent.getAction();
                    if (Constants.ACTION_PUSH_MESSAGE.equals(action)) {
                        a(context, intent);
                    } else if (Constants.ACTION_FEEDBACK.equals(action)) {
                        b(context, intent);
                    } else {
                        a.h(Constants.PushMessageLogTag, "未知的action:" + action);
                    }
                }
            } catch (Throwable th) {
                a.c(Constants.PushMessageLogTag, "onReceive handle error.", th);
            }
        }
    }

    private void a(Context context, Intent intent) {
        i a = i.a(context, intent);
        if (a.g().c() == 2) {
            long longExtra = intent.getLongExtra(MessageKey.MSG_BUSI_MSG_ID, 0);
            long longExtra2 = intent.getLongExtra(MessageKey.MSG_CREATE_TIMESTAMPS, 0);
            long longExtra3 = intent.getLongExtra(MessageKey.MSG_ID, -1);
            c cVar = new c(context, intent);
            XGPushManager.msgAck(context, a);
            if (cVar.a(a, longExtra2, longExtra, longExtra3)) {
                XGPushTextMessage xGPushTextMessage = new XGPushTextMessage();
                xGPushTextMessage.title = a.g().e();
                xGPushTextMessage.content = a.g().f();
                xGPushTextMessage.customContent = a.g().g();
                xGPushTextMessage.a(intent);
                onTextMessage(context, xGPushTextMessage);
            }
        }
    }

    private void b(Context context, Intent intent) {
        int intExtra = intent.getIntExtra(Constants.FEEDBACK_TAG, -1);
        int intExtra2 = intent.getIntExtra(Constants.FEEDBACK_ERROR_CODE, -1);
        switch (intExtra) {
            case 1:
                XGPushRegisterResult xGPushRegisterResult = new XGPushRegisterResult();
                xGPushRegisterResult.parseIntent(intent);
                onRegisterResult(context, intExtra2, xGPushRegisterResult);
                return;
            case 2:
                onUnregisterResult(context, intExtra2);
                return;
            case 3:
                String decrypt = Rijndael.decrypt(intent.getStringExtra(Constants.FLAG_TAG_NAME));
                if (!f.a(decrypt)) {
                    int intExtra3 = intent.getIntExtra(Constants.FLAG_TAG_TYPE, -1);
                    if (intExtra3 == 1) {
                        onSetTagResult(context, intExtra2, decrypt);
                        return;
                    } else if (intExtra3 == 2) {
                        onDeleteTagResult(context, intExtra2, decrypt);
                        return;
                    } else {
                        a.h(Constants.PushMessageLogTag, "错误的标签处理类型：" + intExtra3 + " ,标签名：" + decrypt);
                        return;
                    }
                }
                return;
            case 4:
                intent.getIntExtra("action", 2);
                long longExtra = intent.getLongExtra("accId", 0);
                List accessidList = XGPushConfig.getAccessidList(context);
                if (accessidList != null && accessidList.size() > 0 && accessidList.contains(Long.valueOf(longExtra))) {
                    XGPushClickedResult xGPushClickedResult = new XGPushClickedResult();
                    xGPushClickedResult.parseIntent(intent);
                    onNotifactionClickedResult(context, xGPushClickedResult);
                    return;
                }
                return;
            case 5:
                XGPushShowedResult xGPushShowedResult = new XGPushShowedResult();
                xGPushShowedResult.parseIntent(intent);
                onNotifactionShowedResult(context, xGPushShowedResult);
                return;
            default:
                a.h(Constants.PushMessageLogTag, "未知的feedbackType:" + intExtra);
                return;
        }
    }
}

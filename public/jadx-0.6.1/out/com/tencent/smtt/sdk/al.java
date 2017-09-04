package com.tencent.smtt.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.smtt.utils.Apn;
import com.tencent.smtt.utils.TbsLog;

class al extends BroadcastReceiver {
    final /* synthetic */ ak a;

    al(ak akVar) {
        this.a = akVar;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            try {
                if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                    int apnType = Apn.getApnType(context);
                    long currentTimeMillis = System.currentTimeMillis();
                    synchronized (QbSdk.h) {
                        if (QbSdk.e) {
                            QbSdk.g += currentTimeMillis - QbSdk.f;
                        }
                        QbSdk.f = currentTimeMillis;
                    }
                    TbsLog.d("sdkreport", "pv report, SdkEngine.registerConnectivityChangedReceiver QbSdk.sWifiConnectTime=" + QbSdk.g + " apnType=" + apnType);
                    QbSdk.e = apnType == 3;
                }
            } catch (Throwable th) {
            }
        }
    }
}

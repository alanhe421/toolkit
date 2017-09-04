package com.qq.reader.cservice.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.j;
import tencent.tls.platform.SigType;

public class NewUserAlarmReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (d.n(context)) {
            j.a(53, 0);
            d.g(context, false);
            Intent intent2 = new Intent(context, NewUserAlarmActivity.class);
            intent2.setFlags(SigType.TLS);
            context.startActivity(intent2);
        }
    }
}

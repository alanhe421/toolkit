package com.qq.reader.common.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.qq.reader.common.c.a;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.framework.mark.Mark;

public class DBMarkReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (a.cD.equals(intent.getAction())) {
                i.c().a((Mark) intent.getSerializableExtra("mark"), true);
            }
        }
    }
}

package com.qq.reader.liveshow.views.customviews;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.qq.reader.liveshow.model.c;
import com.qq.reader.liveshow.utils.LogConstants;
import com.qq.reader.liveshow.utils.SxbLog;

public class BaseActivity extends Activity {
    private BroadcastReceiver a;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = new BroadcastReceiver(this) {
            final /* synthetic */ BaseActivity a;

            {
                this.a = r1;
            }

            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("bd_sxb_exit")) {
                    SxbLog.c("BaseActivity", LogConstants.h + LogConstants.a + c.a().b() + LogConstants.a + "on force off line");
                    this.a.r();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("bd_sxb_exit");
        registerReceiver(this.a, intentFilter);
    }

    protected void r() {
    }

    protected void onDestroy() {
        try {
            unregisterReceiver(this.a);
        } catch (Exception e) {
        }
        super.onDestroy();
    }
}

package com.qq.reader.liveshow.views.customviews;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class BaseFragmentActivity extends FragmentActivity {
    private BroadcastReceiver a;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = new BroadcastReceiver(this) {
            final /* synthetic */ BaseFragmentActivity a;

            {
                this.a = r1;
            }

            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("bd_sxb_exit")) {
                    this.a.a();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("bd_sxb_exit");
        registerReceiver(this.a, intentFilter);
    }

    private void a() {
    }

    protected void onDestroy() {
        try {
            unregisterReceiver(this.a);
        } catch (Exception e) {
        }
        super.onDestroy();
    }
}

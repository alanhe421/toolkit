package com.qq.reader.module.qmessage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.i;

class MessageActivity$1 extends BroadcastReceiver {
    final /* synthetic */ MessageActivity a;

    MessageActivity$1(MessageActivity messageActivity) {
        this.a = messageActivity;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (a.cF.equals(action)) {
            try {
                if (intent.getIntExtra("TYPE", 1) == 1) {
                    ((MessageFragment) this.a.c.e(0)).reRefresh();
                    MessageActivity.a(this.a).a(0);
                } else {
                    ((MessageFragment) this.a.c.e(1)).reRefresh();
                    MessageActivity.b(this.a).a(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            i.a("event_C158", null, this.a);
        } else if (a.cG.equals(action)) {
            try {
                MessageActivity.c(this.a).postDelayed(new Runnable(this) {
                    final /* synthetic */ MessageActivity$1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        try {
                            ((MessageFragment) this.a.a.c.e(0)).reRefreshFristTime();
                            ((MessageFragment) this.a.a.c.e(1)).reRefreshFristTime();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 500);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}

package com.qq.reader.common.monitor;

import android.content.Context;
import android.content.Intent;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.protocol.a;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.CommonAllTask;
import com.qq.reader.common.utils.ao;
import com.tencent.beacon.event.UserAction;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ServerLogUpLoader */
public class k {
    private static volatile boolean b = false;
    private Context a;

    public k(Context context) {
        this.a = context;
    }

    public boolean a() {
        b();
        if (!ao.d(this.a) || !j.b(this.a) || b) {
            return false;
        }
        b = true;
        g.a().a(new CommonAllTask(new c(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                j.e();
                d.W(this.a.a.getApplicationContext());
                a.a(readerProtocolTask, str, this.a.a);
                this.a.a.sendBroadcast(new Intent(com.qq.reader.common.c.a.cj));
                if (com.qq.reader.module.rookie.presenter.a.a().c) {
                    com.qq.reader.module.rookie.presenter.a.a().b();
                }
                k.b = false;
                if (this.a.c() && !d.bf(this.a.a.getApplicationContext())) {
                    d.E(this.a.a.getApplicationContext(), true);
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                j.f();
                k.b = false;
            }
        }));
        if (c() && !d.bc(this.a.getApplicationContext())) {
            d.D(this.a.getApplicationContext(), true);
        }
        new Thread(new Runnable(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    UserAction.doUploadRecords();
                } catch (Throwable th) {
                }
            }
        }).start();
        return true;
    }

    private void b() {
        Context applicationContext = this.a.getApplicationContext();
        int i = Calendar.getInstance().get(6);
        int bb = d.bb(applicationContext);
        if (bb == 0 || i < bb) {
            d.ba(applicationContext);
        } else if (i != bb && i != bb) {
            boolean bc = d.bc(applicationContext);
            boolean bf = d.bf(applicationContext);
            Map hashMap = new HashMap();
            if (bc && bf) {
                bf = true;
            } else {
                hashMap.put("isUsed", bc ? "1" : "0");
                hashMap.put("isSuccess", bf ? "1" : "0");
                bf = false;
            }
            i.a("event_report_status", bf, 0, 0, hashMap, false, true, applicationContext);
            d.ba(applicationContext);
            d.D(applicationContext, false);
            d.E(applicationContext, false);
            g.b(this.a);
        }
    }

    private boolean c() {
        if (Calendar.getInstance().get(6) == d.bb(this.a.getApplicationContext())) {
            return true;
        }
        return false;
    }
}

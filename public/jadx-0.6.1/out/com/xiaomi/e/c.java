package com.xiaomi.e;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.d.d;
import com.xiaomi.push.service.y;
import com.xiaomi.xmpush.thrift.e;
import java.util.Arrays;

public class c implements Runnable {
    d a;
    e b;
    String c;

    public c(d dVar) {
        this.a = dVar;
    }

    public void run() {
        int i;
        if (this.b.f) {
            this.b.a("push_sdk_channel");
        }
        if (TextUtils.isEmpty(this.b.l())) {
            this.b.f(y.a());
        }
        this.b.b(System.currentTimeMillis());
        e b = this.a.b();
        String str = null;
        if (d.d(this.a.a())) {
            i = 0;
        } else {
            str = "No network";
            i = 1;
        }
        if (i == 0 && b == null) {
            str = "mUploader is null";
            i = 1;
        }
        if (i == 0 && !b.a(this.b, this.c)) {
            str = "mUploader refuse upload";
            i = 1;
        }
        if (i != 0) {
            com.xiaomi.channel.commonutils.b.c.c("A tinyData is added to pending list. Pending Reason is " + str + ". " + this.b.toString());
            this.a.a(this.b, this.c);
            return;
        }
        com.xiaomi.channel.commonutils.b.c.c("A tinyData is uploaded immediately." + this.b.toString());
        String j = this.b.j();
        if (TextUtils.isEmpty(j)) {
            j = this.c;
        }
        b.a(Arrays.asList(new e[]{this.b}), this.c, j);
    }
}

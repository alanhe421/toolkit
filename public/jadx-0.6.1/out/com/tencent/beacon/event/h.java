package com.tencent.beacon.event;

import android.content.Context;
import com.tencent.beacon.b.b;
import com.tencent.beacon.b.f;
import com.tencent.beacon.e.a;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ProGuard */
public final class h implements Runnable {
    private Context a;
    private int b;
    private int c = -1;
    private Map<String, String> d = new HashMap(2);
    private int e = 0;

    public h(Context context, int i) {
        this.a = context;
        this.b = i;
        this.e = Calendar.getInstance().get(5);
        this.d.put("A63", "N");
        this.d.put("A66", "F");
    }

    public final void run() {
        if (this.b > 0) {
            boolean j = b.j(this.a);
            int i = Calendar.getInstance().get(5);
            if (i != this.e) {
                this.e = i;
                new i(this.a, j).a(true);
            }
            if (this.c != -1) {
                if (this.c == 0 && j) {
                    f.a(this.a);
                    this.d.put("A33", f.l(this.a));
                    this.d.put("A85", b.b ? "Y" : "N");
                    o.a("rqd_applaunched", true, 0, this.d);
                    a.a(" create a applaunched event", new Object[0]);
                    this.c = 1;
                    o.d(true);
                    return;
                } else if (j || this.c != 1) {
                    return;
                }
            } else if (j) {
                this.c = 1;
                return;
            }
            this.c = 0;
        }
    }
}

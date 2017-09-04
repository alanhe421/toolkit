package com.tencent.beacon.runinfo;

import android.content.Context;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.beacon.b.b;
import com.tencent.beacon.b.c;
import com.tencent.beacon.b.d;
import com.tencent.beacon.b.f;
import com.tencent.beacon.e.a;
import com.tencent.beacon.event.g;
import com.tencent.beacon.event.o;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ProGuard */
public class AppRunInfoTask implements Runnable {
    private Context a;
    private int b;
    private int c;
    private Runnable d;
    private boolean e;
    private boolean f;
    private boolean g;
    private int h = 0;

    private AppRunInfoTask(Context context, int i, int i2, Runnable runnable, boolean z, boolean z2, boolean z3) {
        this.a = context;
        this.b = i;
        this.c = i2;
        this.d = runnable;
        this.e = z;
        this.f = z2;
        this.g = z3;
    }

    public static void startAppRunMonitor(Context context, Object obj) {
        if (context != null && (obj instanceof g)) {
            a.e("startAppRunMonitor.", new Object[0]);
            g gVar = (g) obj;
            if (gVar.i() || gVar.s()) {
                int h = gVar.h();
                int g = gVar.g();
                if (h > 0 && g > 0) {
                    AppRunInfoTask appRunInfoTask = new AppRunInfoTask(context, h, g, new b(context), gVar.i(), gVar.s(), gVar.r());
                    long j = (long) (h * 1000);
                    c.a().a(104, appRunInfoTask, (long) (h * 1000), j);
                }
            }
        }
    }

    public void run() {
        long h;
        String str = null;
        boolean j = b.j(this.a);
        String str2 = j ? "F" : "B";
        f a = f.a(this.a);
        if (this.b > 0 && this.f) {
            String g;
            try {
                h = (d.m().h() + new Date().getTime()) / 1000;
            } catch (Exception e) {
                h = 0;
            }
            if (this.f) {
                g = a.g();
                if (!(g == null || g.equals(""))) {
                    g = str2 + "," + g + "," + h;
                }
            } else {
                g = null;
            }
            if (this.g) {
                str = f.h();
                if (!(str == null || str.equals(""))) {
                    str = str2 + "," + str + "," + h;
                }
            }
            try {
                str2 = b.b(this.a, "app_mem_info", "");
                String b = b.b(this.a, "app_cpu_info", "");
                if ((this.h != 0 || "".equals(str2)) && this.h < this.c / this.b) {
                    if ("".equals(str2)) {
                        b.a(this.a, "app_mem_info", g);
                    } else {
                        b.a(this.a, "app_mem_info", str2 + VoiceWakeuperAidl.PARAMS_SEPARATE + g);
                    }
                    if (str != null) {
                        if ("".equals(b)) {
                            b.a(this.a, "app_cpu_info", str);
                        } else {
                            b.a(this.a, "app_cpu_info", b + VoiceWakeuperAidl.PARAMS_SEPARATE + str);
                        }
                    }
                    this.h++;
                } else {
                    Map hashMap = new HashMap();
                    f.a(this.a);
                    hashMap.put("A33", f.l(this.a));
                    hashMap.put("A78", str2 + VoiceWakeuperAidl.PARAMS_SEPARATE + g);
                    if (str != null) {
                        hashMap.put("A77", b + VoiceWakeuperAidl.PARAMS_SEPARATE + str);
                    }
                    if (o.a("rqd_res_occ", true, 0, hashMap)) {
                        b.a(this.a, "app_mem_info", "");
                        if (str != null) {
                            b.a(this.a, "app_cpu_info", "");
                        }
                        this.h = 0;
                    } else {
                        b.a(this.a, "app_mem_info", str2 + VoiceWakeuperAidl.PARAMS_SEPARATE + g);
                        if (str != null) {
                            b.a(this.a, "app_cpu_info", b + VoiceWakeuperAidl.PARAMS_SEPARATE + str);
                        }
                        this.h++;
                    }
                }
            } catch (Exception e2) {
                a.c("get resinfo from sp failed! ", new Object[0]);
            }
        }
        if (this.b > 0 && this.e) {
            h = new Date().getTime();
            a f = com.tencent.beacon.net.a.f(this.a);
            if (f == null) {
                f = new a();
                f.c(h);
                f.d(h);
                f.b(0);
                f.a(0);
            }
            f.a(f.a() + ((long) (this.b / 60)));
            if (j) {
                f.b(f.b() + ((long) (this.b / 60)));
            }
            f.d(h);
            Context context = this.a;
            if (!(context == null || f == null)) {
                List arrayList = new ArrayList();
                com.tencent.beacon.b.a.a aVar = new com.tencent.beacon.b.a.a(8, 0, f.d(), com.tencent.beacon.net.a.a(f));
                aVar.a(f.e());
                arrayList.add(aVar);
                com.tencent.beacon.b.a.a.b(context, arrayList);
            }
            a.e(" used:%d  seen:%d  next:%d", new Object[]{Long.valueOf(f.a()), Long.valueOf(f.b()), Integer.valueOf(this.b)});
            if (f.a() >= ((long) (this.c / 60))) {
                c.a().a(this.d);
            }
        }
    }
}

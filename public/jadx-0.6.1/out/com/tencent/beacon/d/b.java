package com.tencent.beacon.d;

import android.content.Context;
import android.content.SharedPreferences;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.beacon.b.c;
import com.tencent.beacon.e.a;
import com.tencent.beacon.event.UserAction;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: ProGuard */
public final class b {
    private static int d;
    private static String m = "rqd_up_qua";
    private static int o = 10;
    public boolean a = false;
    private final Context b;
    private final ConcurrentHashMap<Integer, c> c = new ConcurrentHashMap();
    private a<String> e = new a();
    private a<Long> f = new a();
    private a<Long> g = new a();
    private a<Long> h = new a();
    private a<Long> i = new a();
    private a<Long> j = new a();
    private a<Byte> k = new a();
    private a<SharedPreferences> l = new a();
    private int n = 5;
    private Runnable p = new Runnable(this) {
        private /* synthetic */ b a;

        {
            this.a = r1;
        }

        public final void run() {
            try {
                ((SharedPreferences) this.a.l.a()).edit().putString("on_qua_date", (String) this.a.e.a()).putLong("common_event_calls", ((Long) this.a.f.a()).longValue()).putLong("real_time_event_calls", ((Long) this.a.g.a()).longValue()).putLong("common_event_write_succ", ((Long) this.a.h.a()).longValue()).putLong("real_time_event_write_succ", ((Long) this.a.i.a()).longValue()).putLong("real_time_event_upload_succ", ((Long) this.a.j.a()).longValue()).commit();
            } catch (Throwable e) {
                a.a(e);
            }
        }
    };
    private Runnable q = new Runnable(this) {
        private /* synthetic */ b a;

        {
            this.a = r1;
        }

        public final void run() {
            this.a.b.getSharedPreferences("DENGTA_META", 0);
            this.a.l.a(this.a.b.getSharedPreferences("DENGTA_META", 0));
            this.a.e.a(((SharedPreferences) this.a.l.b()).getString("on_qua_date", ""));
            this.a.f.a(Long.valueOf(((SharedPreferences) this.a.l.b()).getLong("common_event_calls", 0)));
            this.a.g.a(Long.valueOf(((SharedPreferences) this.a.l.b()).getLong("real_time_event_calls", 0)));
            this.a.h.a(Long.valueOf(((SharedPreferences) this.a.l.b()).getLong("common_event_write_succ", 0)));
            this.a.i.a(Long.valueOf(((SharedPreferences) this.a.l.b()).getLong("real_time_event_write_succ", 0)));
            this.a.j.a(Long.valueOf(((SharedPreferences) this.a.l.b()).getLong("real_time_event_upload_succ", 0)));
            this.a.f();
        }
    };

    public b(Context context) {
        this.b = context;
        this.k.a(Byte.valueOf((byte) 0));
        c.a().a(this.q);
    }

    public final synchronized void a(int i, boolean z, long j, long j2, int i2, int i3, String str, String str2, int i4, String str3) {
        if (this.a && i != 0) {
            c cVar = (c) this.c.get(Integer.valueOf(i));
            d dVar;
            if (cVar != null) {
                dVar = new d();
                if (z) {
                    cVar.b(cVar.a() + 1);
                } else {
                    cVar.c(cVar.b() + 1);
                }
                dVar.b = i;
                dVar.a = j;
                if (j2 > 0 && i4 > 0) {
                    dVar.d = ((int) j2) / i4;
                }
                dVar.e = i2;
                dVar.f = i3;
                dVar.g = str;
                dVar.h = str2;
                dVar.c = z;
                dVar.i = i4;
                dVar.j = str3;
                cVar.c().add(dVar);
            } else {
                cVar = new c();
                cVar.a(i);
                if (z) {
                    cVar.b(1);
                } else {
                    cVar.c(1);
                }
                dVar = new d();
                dVar.b = i;
                dVar.a = j;
                if (j2 > 0 && i4 > 0) {
                    dVar.d = ((int) j2) / i4;
                }
                dVar.e = i2;
                dVar.f = i3;
                dVar.g = str;
                dVar.h = str2;
                dVar.c = z;
                dVar.i = i4;
                dVar.j = str3;
                cVar.c().add(dVar);
                this.c.put(Integer.valueOf(i), cVar);
            }
            int i5 = d + 1;
            d = i5;
            if (i5 >= this.n) {
                Map hashMap = new HashMap();
                hashMap.put("B1", d);
                hashMap.put("B2", c());
                hashMap.put("B3", d());
                try {
                    hashMap.put("B4", String.valueOf(this.f.a()));
                    hashMap.put("B5", String.valueOf(this.g.a()));
                    hashMap.put("B6", String.valueOf(com.tencent.beacon.net.a.e(this.b)));
                    hashMap.put("B7", String.valueOf(this.j.a()));
                    hashMap.put("B8", String.valueOf(this.i.a()));
                    hashMap.put("B9", String.valueOf(this.h.a()));
                    hashMap.put("B10", String.valueOf(((String) this.e.a()).replace("-", "")));
                } catch (Throwable e) {
                    a.a(e);
                }
                if (UserAction.onUserAction(m, true, 0, 0, hashMap, true)) {
                    this.c.clear();
                    d = 0;
                    this.n = 10;
                }
            }
        }
    }

    public final void a() {
        if (this.a) {
            try {
                f();
                this.f.a(Long.valueOf(((Long) this.f.a()).longValue() + 1));
                e();
            } catch (Throwable e) {
                a.a(e);
            }
        }
    }

    public final void b() {
        if (this.a) {
            try {
                f();
                this.g.a(Long.valueOf(((Long) this.g.a()).longValue() + 1));
                e();
            } catch (Throwable e) {
                a.a(e);
            }
        }
    }

    public final void a(int i) {
        if (this.a) {
            try {
                f();
                this.h.a(Long.valueOf(((Long) this.h.a()).longValue() + ((long) i)));
                if (i > 1) {
                    c.a().a(this.p);
                }
            } catch (Throwable e) {
                a.a(e);
            }
        }
    }

    public final void b(int i) {
        if (this.a) {
            try {
                f();
                this.i.a(Long.valueOf(((Long) this.i.a()).longValue() + ((long) i)));
                if (i > 1) {
                    c.a().a(this.p);
                }
            } catch (Throwable e) {
                a.a(e);
            }
        }
    }

    public final void c(int i) {
        if (this.a) {
            try {
                f();
                this.j.a(Long.valueOf(((Long) this.j.a()).longValue() + ((long) i)));
                if (i > 1) {
                    c.a().a(this.p);
                }
            } catch (Throwable e) {
                a.a(e);
            }
        }
    }

    private String c() {
        if (this.c == null || this.c.size() <= 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : this.c.entrySet()) {
            c cVar = (c) entry.getValue();
            stringBuilder.append(entry.getKey()).append(",").append(cVar.a()).append(",").append(cVar.b()).append(VoiceWakeuperAidl.PARAMS_SEPARATE);
        }
        return stringBuilder.toString();
    }

    private String d() {
        if (this.c == null || this.c.size() <= 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry value : this.c.entrySet()) {
            for (d dVar : ((c) value.getValue()).c()) {
                stringBuilder.append(dVar.a).append(",").append(dVar.b).append(",").append(dVar.c ? "Y" : "N").append(",").append(dVar.d).append(",").append(dVar.e).append(",").append(dVar.f).append(",").append(dVar.g).append(",").append(dVar.h).append(",").append(dVar.i).append(",").append(dVar.j).append(VoiceWakeuperAidl.PARAMS_SEPARATE);
            }
        }
        return stringBuilder.toString();
    }

    private void e() {
        if (this.a) {
            synchronized (this.k) {
                try {
                    byte byteValue = (byte) (((Byte) this.k.a()).byteValue() + 1);
                    if (byteValue >= o) {
                        c.a().a(this.p);
                        byteValue = (byte) 0;
                    }
                    this.k.a(Byte.valueOf(byteValue));
                } catch (Throwable e) {
                    a.a(e);
                }
            }
        }
    }

    private void f() {
        try {
            if (!com.tencent.beacon.net.a.d().equals(this.e.a())) {
                this.e.a(com.tencent.beacon.net.a.d());
                this.f.a(Long.valueOf(0));
                this.g.a(Long.valueOf(0));
                this.h.a(Long.valueOf(0));
                this.i.a(Long.valueOf(0));
                this.j.a(Long.valueOf(0));
                c.a().a(this.p);
            }
        } catch (Throwable e) {
            a.a(e);
        }
    }
}

package com.tencent.beacon.event;

import android.os.Build.VERSION;
import com.tencent.beacon.e.a;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/* compiled from: ProGuard */
public final class g implements Cloneable {
    private boolean A = true;
    private boolean B = false;
    private String C;
    private boolean D = true;
    private String E;
    private boolean F = false;
    private int G = 1;
    private int a = 12;
    private int b = 60;
    private int c = 12;
    private int d = 60;
    private int e = 20;
    private int f = 10485760;
    private boolean g = false;
    private int h = 60;
    private int i = ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE;
    private boolean j = true;
    private boolean k = false;
    private boolean l = false;
    private int m = 30;
    private boolean n = true;
    private boolean o = true;
    private Set<String> p = null;
    private Map<String, Float> q = null;
    private boolean r = false;
    private float s = 1.0f;
    private boolean t = false;
    private int u = ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE;
    private boolean v = true;
    private boolean w = false;
    private boolean x = false;
    private boolean y = false;
    private int z = 180;

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return F();
    }

    public final synchronized void a(Map<String, String> map) {
        if (map != null) {
            try {
                int intValue;
                String str = (String) map.get("realNumUp");
                if (str != null) {
                    intValue = Integer.valueOf(str).intValue();
                    if (intValue > 0 && intValue <= 50) {
                        this.a = intValue;
                    }
                }
                str = (String) map.get("realDelayUp");
                if (str != null) {
                    intValue = Integer.valueOf(str).intValue();
                    if (intValue >= 10 && intValue <= ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE) {
                        this.b = intValue;
                    }
                }
                str = (String) map.get("comNumDB");
                if (str != null) {
                    intValue = Integer.valueOf(str).intValue();
                    if (intValue > 0 && intValue <= 50) {
                        this.c = intValue;
                    }
                }
                str = (String) map.get("comDelayDB");
                if (str != null) {
                    intValue = Integer.valueOf(str).intValue();
                    if (intValue >= 30 && intValue <= ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE) {
                        this.d = intValue;
                    }
                }
                str = (String) map.get("comNumUp");
                if (str != null) {
                    intValue = Integer.valueOf(str).intValue();
                    if (intValue > 0 && intValue <= 100) {
                        this.e = intValue;
                    }
                }
                str = (String) map.get("dailyNetFlowLimit");
                if (str != null) {
                    intValue = Integer.valueOf(str).intValue();
                    if (intValue >= 204800 && intValue <= 10485760) {
                        this.f = intValue;
                    }
                }
                str = (String) map.get("runInfoPeriod");
                if (str != null) {
                    intValue = Integer.valueOf(str).intValue();
                    if (intValue >= 30 && intValue <= 300) {
                        this.h = intValue;
                    }
                }
                str = (String) map.get("useTimeUpPeriod");
                if (str != null) {
                    intValue = Integer.valueOf(str).intValue();
                    if (intValue >= 300 && intValue <= 1800) {
                        this.i = intValue;
                    }
                }
                str = (String) map.get("useTimeOnOff");
                if (str != null) {
                    if (str.toLowerCase().equals("y")) {
                        this.j = true;
                    } else if (str.toLowerCase().equals("n")) {
                        this.j = false;
                    }
                }
                str = (String) map.get("proChangePeriod");
                if (str != null) {
                    intValue = Integer.valueOf(str).intValue();
                    if (intValue >= 10 && intValue <= 300) {
                        this.m = intValue;
                    }
                }
                str = (String) map.get("proChangeOnOff");
                if (str != null) {
                    if (str.toLowerCase().equals("y")) {
                        this.n = true;
                    } else if (str.toLowerCase().equals("n")) {
                        this.n = false;
                    }
                }
                str = (String) map.get("heartOnOff");
                if (str != null) {
                    if (str.toLowerCase().equals("y")) {
                        this.o = true;
                    } else if (str.toLowerCase().equals("n")) {
                        this.o = false;
                    }
                }
                str = (String) map.get("appLogUploadOnOff");
                if (str != null) {
                    if (str.toLowerCase().equals("y")) {
                        str = (String) map.get("appLogRealTimeUpload");
                        if (!(str == null || str.toLowerCase().equals("y"))) {
                            str.toLowerCase().equals("n");
                        }
                        str = (String) map.get("appLogOutDay");
                        if (str != null) {
                            Long.valueOf(str).longValue();
                        }
                        if (((String) map.get("logDailyConsumeLimit")) != null) {
                            Integer.valueOf(str).intValue();
                        }
                        str = (String) map.get("appLogSizeLimit");
                        if (str != null) {
                            Integer.valueOf(str).intValue();
                        }
                        str = (String) map.get("appLogRecordMax");
                        if (str != null) {
                            Integer.valueOf(str).intValue();
                        }
                        str = (String) map.get("appLogFileTotalMaxSize");
                        if (str != null) {
                            Integer.valueOf(str).intValue();
                        }
                    } else {
                        str.toLowerCase().equals("n");
                    }
                }
                str = (String) map.get("appNetConOnOff");
                if (str != null) {
                    if (str.toLowerCase().equals("y")) {
                        this.t = true;
                    } else if (str.toLowerCase().equals("n")) {
                        this.t = false;
                    }
                }
                str = (String) map.get("netConQuePeriod");
                if (str != null) {
                    intValue = Integer.valueOf(str).intValue();
                    if (intValue >= 60 && intValue <= 1200) {
                        this.u = intValue;
                    }
                }
                str = (String) map.get("sdkNetConOnOff");
                if (str != null) {
                    if (str.toLowerCase().equals("y")) {
                        this.v = true;
                    } else if (str.toLowerCase().equals("n")) {
                        this.v = false;
                    }
                }
                str = (String) map.get("memOnOff");
                if (str != null) {
                    if (str.toLowerCase().equals("y")) {
                        this.k = true;
                    } else if (str.toLowerCase().equals("n")) {
                        this.k = false;
                    }
                }
                str = (String) map.get("cpuOnOff");
                if (str != null) {
                    if (str.toLowerCase().equals("y")) {
                        this.l = true;
                    } else if (str.toLowerCase().equals("n")) {
                        this.l = false;
                    }
                }
                str = (String) map.get("heatmapOnOff");
                if (!(str == null || str.toLowerCase().equals("y"))) {
                    str.toLowerCase().equals("n");
                }
                str = (String) map.get("heatmapUpMax");
                if (str != null) {
                    Integer.valueOf(str).intValue();
                }
                str = (String) map.get("heatmapPeriod");
                if (str != null) {
                    Integer.valueOf(str).intValue();
                }
                str = (String) map.get("installOnOff");
                if (str != null) {
                    if (str.toLowerCase().equals("y")) {
                        this.w = true;
                    } else if (str.toLowerCase().equals("n")) {
                        this.w = false;
                    }
                }
                str = (String) map.get("useAppOnOff");
                if (str != null) {
                    if (str.toLowerCase().equals("y")) {
                        this.x = true;
                    } else if (str.toLowerCase().equals("n")) {
                        this.x = false;
                    }
                }
                str = (String) map.get("isAllApp");
                if (str != null) {
                    if (str.toLowerCase().equals("y")) {
                        this.y = true;
                    } else if (str.toLowerCase().equals("n")) {
                        this.y = false;
                    }
                }
                if (this.x) {
                    str = (String) map.get("useAppCollPer");
                    if (str != null) {
                        intValue = Integer.valueOf(str).intValue();
                        if (intValue >= 60 && intValue <= 3600) {
                            this.z = intValue;
                        }
                    }
                }
                str = (String) map.get("upQa");
                if (str != null) {
                    if (str.toLowerCase().equals("y")) {
                        this.A = true;
                    } else if (str.toLowerCase().equals("n")) {
                        this.A = false;
                    }
                }
                str = (String) map.get("upAc");
                if (str != null) {
                    if (str.toLowerCase().equals("y")) {
                        this.B = true;
                    } else if (str.toLowerCase().equals("n")) {
                        this.B = false;
                    }
                }
                str = (String) map.get("apmUIOnOff");
                if (!(str == null || str.toLowerCase().equals("y"))) {
                    str.toLowerCase().equals("n");
                }
                str = (String) map.get("comPollUp");
                if (str != null) {
                    if (str.toLowerCase().equals("y")) {
                        this.g = true;
                    } else if (str.toLowerCase().equals("n")) {
                        this.g = false;
                    }
                }
                str = (String) map.get("accessTestOnOff");
                if (str != null) {
                    if (str.toLowerCase().equals("y")) {
                        a.b = true;
                    } else if (str.toLowerCase().equals("n")) {
                        a.b = false;
                    }
                }
                this.C = (String) map.get("appendXMeths");
                str = (String) map.get("esnOnOff");
                if (str != null) {
                    if (str.toLowerCase().equals("y")) {
                        this.D = true;
                    } else if (str.toLowerCase().equals("n")) {
                        this.D = false;
                    }
                }
                this.E = (String) map.get("speSerEnt");
                str = (String) map.get("zeroPeakOnOff");
                if (str != null) {
                    if (str.equalsIgnoreCase("y")) {
                        this.r = true;
                    } else if (str.equalsIgnoreCase("n")) {
                        this.r = false;
                    }
                }
                str = (String) map.get("zeroPeakRate");
                if (str != null) {
                    str = str.trim();
                    if (str.length() > 0) {
                        String[] split = str.split(",");
                        if (split.length == 2) {
                            try {
                                this.s = Float.valueOf(split[0]).floatValue() / Float.valueOf(split[1]).floatValue();
                            } catch (Exception e) {
                            }
                        }
                    }
                }
                str = (String) map.get("straOnOff");
                if (str != null) {
                    if (str.toLowerCase().equals("y")) {
                        this.F = true;
                    } else if (str.toLowerCase().equals("n")) {
                        this.F = false;
                    }
                }
                str = (String) map.get("straDayMaxCount");
                if (str != null) {
                    intValue = Integer.valueOf(str).intValue();
                    if (intValue > 0) {
                        this.G = intValue;
                    }
                }
            } catch (Throwable e2) {
                a.a(e2);
            }
        }
    }

    public final synchronized int a() {
        return this.a;
    }

    private synchronized void a(int i) {
        if (i > 0) {
            this.a = i;
        }
    }

    public final synchronized int b() {
        return this.b;
    }

    private synchronized void b(int i) {
        if (i > 0) {
            this.b = i;
        }
    }

    public final synchronized int c() {
        return this.c;
    }

    private synchronized void c(int i) {
        if (i > 0) {
            this.c = i;
        }
    }

    public final synchronized int d() {
        return this.d;
    }

    private synchronized void d(int i) {
        if (i > 0) {
            this.d = i;
        }
    }

    public final synchronized int e() {
        return this.e;
    }

    private synchronized void e(int i) {
        if (i > 0) {
            this.e = i;
        }
    }

    public final synchronized int f() {
        return this.f;
    }

    private synchronized void f(int i) {
        if (i > 0) {
            this.f = i;
        }
    }

    public final synchronized int g() {
        return this.i;
    }

    public final int h() {
        return this.h;
    }

    public final boolean i() {
        return this.j;
    }

    public final void j() {
        this.j = false;
    }

    public final int k() {
        return this.m;
    }

    public final boolean l() {
        return this.n;
    }

    public final boolean m() {
        return this.o;
    }

    public final boolean n() {
        return this.B;
    }

    public final synchronized void a(Set<String> set) {
        this.p = set;
    }

    public final synchronized boolean a(String str) {
        boolean z;
        z = false;
        if (this.p != null && this.p.size() > 0) {
            z = this.p.contains(str);
        }
        return z;
    }

    public final synchronized void b(Set<String> set) {
        if (this.q == null) {
            this.q = new HashMap();
        }
        for (String split : set) {
            String[] split2 = split.split(",");
            if (split2.length == 3) {
                try {
                    this.q.put(split2[0].toLowerCase(), Float.valueOf(Float.valueOf(split2[1]).floatValue() / Float.valueOf(split2[2]).floatValue()));
                } catch (Exception e) {
                }
            }
        }
    }

    public final synchronized boolean b(String str) {
        boolean z;
        if (this.q == null || this.q.get(str) == null) {
            z = true;
        } else if (new Random().nextInt(1000) + 1 > ((int) (((Float) this.q.get(str.toLowerCase())).floatValue() * 1000.0f))) {
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    public final synchronized boolean o() {
        boolean z;
        z = true;
        if (this.r) {
            Calendar instance = Calendar.getInstance();
            int i = instance.get(11);
            int i2 = instance.get(12);
            if (i == 0 && i2 >= 0 && 30 >= i2 && new Random().nextInt(1000) + 1 > ((int) (this.s * 1000.0f))) {
                z = false;
            }
        }
        return z;
    }

    public final boolean p() {
        if (Integer.parseInt(VERSION.SDK) < 8) {
            return false;
        }
        return this.t;
    }

    public final int q() {
        return this.u;
    }

    public final boolean r() {
        return this.l;
    }

    public final boolean s() {
        return this.k;
    }

    public final boolean t() {
        return this.w;
    }

    public final boolean u() {
        return this.x;
    }

    public final boolean v() {
        return this.y;
    }

    public final int w() {
        return this.z;
    }

    public final boolean x() {
        return this.A;
    }

    public final boolean y() {
        return this.g;
    }

    public final String z() {
        return this.C;
    }

    public final boolean A() {
        return this.D;
    }

    public final String B() {
        return this.E;
    }

    public final int C() {
        return this.G;
    }

    public final boolean D() {
        return this.F;
    }

    public final boolean E() {
        return this.v;
    }

    private synchronized g F() {
        g gVar;
        gVar = new g();
        gVar.d(this.d);
        gVar.c(this.c);
        gVar.e(this.e);
        gVar.f(this.f);
        gVar.b(this.b);
        gVar.a(this.a);
        return gVar;
    }
}

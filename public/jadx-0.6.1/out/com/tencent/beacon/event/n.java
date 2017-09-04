package com.tencent.beacon.event;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.etrump.jni.ETConverter;
import com.tencent.beacon.b.f;
import com.tencent.beacon.e.a;
import com.tencent.beacon.e.c;
import com.tencent.qalsdk.sdk.v;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/* compiled from: ProGuard */
public final class n {
    private static n a = null;
    private byte[] b = new byte[]{(byte) 57, (byte) -16, (byte) 34, (byte) -64, (byte) -79, (byte) -74, (byte) 14, (byte) -78, (byte) 99, (byte) -60, (byte) -123, (byte) -111, (byte) -19, (byte) -95, (byte) -113, (byte) 96, (byte) -7, (byte) -35, (byte) -112, (byte) -117, (byte) 91, (byte) 86, (byte) 10, (byte) 68};
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;

    public static synchronized n a(Context context) {
        n nVar;
        synchronized (n.class) {
            if (a == null && context != null) {
                a = new n(context);
            }
            nVar = a;
        }
        return nVar;
    }

    public static synchronized n a() {
        n nVar;
        synchronized (n.class) {
            nVar = a;
        }
        return nVar;
    }

    public final synchronized String b() {
        return this.c;
    }

    public final synchronized String c() {
        return this.d;
    }

    public final synchronized String d() {
        return this.e;
    }

    public final synchronized String e() {
        return this.f;
    }

    public final synchronized String f() {
        return this.g;
    }

    public final synchronized String g() {
        return this.h;
    }

    public final synchronized String h() {
        return this.i;
    }

    public final synchronized String i() {
        return this.j;
    }

    public final synchronized String j() {
        return this.k;
    }

    public final synchronized String k() {
        return this.l;
    }

    public final String l() {
        return this.m;
    }

    public final String m() {
        return this.n;
    }

    public final String n() {
        return this.o;
    }

    public final String o() {
        return this.p;
    }

    public final String p() {
        return this.q;
    }

    public final String q() {
        return this.r;
    }

    public final String r() {
        return this.s;
    }

    private n(Context context) {
        String str;
        byte[] bArr = new byte[]{(byte) -98, (byte) 122, (byte) 88, (byte) 37, (byte) -121, (byte) 18, (byte) 93, (byte) 117};
        bArr = new byte[]{(byte) 57, (byte) -16, (byte) 34, (byte) -64, (byte) -79, (byte) -74, (byte) 14, (byte) -78, (byte) -83, (byte) 124, (byte) 86, (byte) -95, (byte) 76, (byte) 41, (byte) 9, (byte) -59, (byte) -107, (byte) 115, (byte) -60, (byte) -115, (byte) 106, (byte) 108, (byte) -74, (byte) 37};
        bArr = this.b;
        bArr = this.b;
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = "";
        this.k = "";
        this.l = "";
        this.m = "";
        this.n = "";
        this.o = "";
        this.p = "";
        this.q = "";
        this.r = "";
        this.s = "";
        if (context == null) {
            a.d(" UADeviceInfo context == null? pls check!", new Object[0]);
        }
        a.b(" start to create UADeviceInfo", new Object[0]);
        long currentTimeMillis = System.currentTimeMillis();
        f.a(context);
        this.c = f.b();
        f.c();
        DisplayMetrics h = f.h(context);
        if (h == null) {
            str = "";
        } else {
            str = h.widthPixels + v.n + h.heightPixels;
        }
        this.d = str;
        this.e = f.e();
        this.f = "";
        this.g = f.f() + "m";
        this.h = f.i() + "m";
        this.i = f.k();
        this.j = f.l();
        this.k = f.j(context);
        this.l = f.m();
        this.m = c(context);
        this.n = t() + "m";
        this.o = v();
        this.p = s();
        this.q = u() ? "Y" : "N";
        this.r = d(context);
        this.s = c.a().b() ? "Y" : "N";
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        a.b(" detail create cost： %d  values:\n %s", Long.valueOf(currentTimeMillis2), toString());
    }

    private static String s() {
        Throwable e;
        Throwable th;
        String str = "";
        BufferedReader bufferedReader;
        try {
            String readLine;
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(new String[]{"/system/bin/cat", "/proc/cpuinfo"}).getInputStream()));
            do {
                try {
                    readLine = bufferedReader.readLine();
                    if (readLine != null) {
                    }
                    break;
                } catch (IOException e2) {
                    e = e2;
                } catch (Throwable th2) {
                    e = th2;
                }
            } while (!readLine.contains("Processor"));
            str = readLine.substring(readLine.indexOf(":") + 1).trim();
            try {
                bufferedReader.close();
            } catch (Throwable e3) {
                a.a(e3);
            }
        } catch (IOException e4) {
            e3 = e4;
            bufferedReader = null;
            try {
                a.a(e3);
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable e32) {
                        a.a(e32);
                    }
                }
                return str;
            } catch (Throwable th3) {
                th = th3;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable e322) {
                        a.a(e322);
                    }
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
        return str;
    }

    private static int t() {
        Throwable e;
        Throwable th;
        int i = 0;
        BufferedReader bufferedReader = null;
        BufferedReader bufferedReader2;
        try {
            bufferedReader2 = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(new String[]{"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"}).getInputStream()));
            try {
                String readLine = bufferedReader2.readLine();
                if (readLine != null) {
                    i = Integer.parseInt(readLine.trim()) / 1000;
                }
                try {
                    bufferedReader2.close();
                } catch (Throwable e2) {
                    a.a(e2);
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    a.a(e2);
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Throwable e22) {
                            a.a(e22);
                        }
                    }
                    return i;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable e222) {
                            a.a(e222);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                e222 = th3;
                bufferedReader = bufferedReader2;
                try {
                    a.a(e222);
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable e2222) {
                            a.a(e2222);
                        }
                    }
                    return i;
                } catch (Throwable th4) {
                    th = th4;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            }
        } catch (IOException e4) {
            e2222 = e4;
            bufferedReader2 = null;
            a.a(e2222);
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            return i;
        } catch (Throwable th5) {
            e2222 = th5;
            a.a(e2222);
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return i;
        }
        return i;
    }

    private static boolean u() {
        Throwable e;
        Throwable th;
        boolean z = false;
        BufferedReader bufferedReader = null;
        BufferedReader bufferedReader2;
        try {
            bufferedReader2 = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(new String[]{"/system/bin/cat", "/proc/cpuinfo"}).getInputStream()));
            String readLine;
            do {
                try {
                    readLine = bufferedReader2.readLine();
                    if (readLine != null) {
                    }
                    break;
                } catch (IOException e2) {
                    e = e2;
                } catch (Throwable th2) {
                    e = th2;
                    bufferedReader = bufferedReader2;
                }
            } while (-1 == readLine.toLowerCase().indexOf("armv7"));
            z = true;
            try {
                bufferedReader2.close();
            } catch (Throwable e3) {
                a.a(e3);
            }
        } catch (IOException e4) {
            e3 = e4;
            bufferedReader2 = null;
            try {
                a.a(e3);
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (Throwable e32) {
                        a.a(e32);
                    }
                }
                return z;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = bufferedReader2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable e322) {
                        a.a(e322);
                    }
                }
                throw th;
            }
        } catch (Throwable th4) {
            e322 = th4;
            try {
                a.a(e322);
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable e3222) {
                        a.a(e3222);
                    }
                }
                return z;
            } catch (Throwable th5) {
                th = th5;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
        }
        return z;
    }

    private static int c(Context context) {
        if (context == null) {
            return 160;
        }
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            if (displayMetrics.density == 1.0f) {
                return 160;
            }
            if (((double) displayMetrics.density) <= 0.75d) {
                return 120;
            }
            if (((double) displayMetrics.density) == 1.5d) {
                return ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK;
            }
            if (((double) displayMetrics.density) == 2.0d) {
                return ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE;
            }
            int i;
            if (((double) displayMetrics.density) == 3.0d) {
                i = 480;
            } else {
                i = 160;
            }
            return i;
        } catch (Throwable th) {
            a.a(th);
            return 160;
        }
    }

    public static String b(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        if (locationManager == null) {
            return "N";
        }
        List allProviders = locationManager.getAllProviders();
        if (allProviders == null) {
            return "N";
        }
        return allProviders.contains("gps") ? "Y" : "N";
    }

    private static int v() {
        int i = 1;
        try {
            File[] listFiles = new File("/sys/devices/system/cpu/").listFiles(new a());
            if (listFiles != null) {
                i = listFiles.length;
            }
        } catch (Throwable e) {
            a.d("CPU Count: Failed.", new Object[0]);
            a.a(e);
        }
        return i;
    }

    private static String d(Context context) {
        String str;
        if (context == null) {
            a.d("getSensor2 but context == null!", new Object[0]);
            return null;
        }
        String str2;
        a.a("getSensor2 start", new Object[0]);
        String str3 = "X";
        String str4 = "X";
        String str5 = "X";
        String str6 = "X";
        if ((((WifiManager) context.getSystemService("wifi")) == null ? 0 : 1) != 0) {
            str2 = "Y";
        } else {
            str2 = "N";
        }
        if (Integer.parseInt(VERSION.SDK) >= 10) {
            try {
                SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
                if (sensorManager.getDefaultSensor(1) != null) {
                    str3 = "Y";
                } else {
                    str3 = "N";
                }
                if (sensorManager.getDefaultSensor(5) != null) {
                    str4 = "Y";
                } else {
                    str4 = "N";
                }
                if (BluetoothAdapter.getDefaultAdapter() == null) {
                    str5 = "N";
                } else {
                    str5 = "Y";
                }
                if (context.getPackageManager().hasSystemFeature("android.hardware.nfc")) {
                    str = "Y";
                    str6 = str5;
                    str5 = str4;
                    str4 = str3;
                } else {
                    str = "N";
                    str6 = str5;
                    str5 = str4;
                    str4 = str3;
                }
            } catch (Throwable th) {
                str = str5;
                str5 = str4;
                str4 = str3;
                a.d("getSensor2 error!", new Object[0]);
                String str7 = str6;
                str6 = str;
                str = str7;
            }
        } else {
            str = str6;
            str6 = str5;
            str5 = str4;
            str4 = str3;
        }
        return str2 + str4 + str5 + str6 + str;
    }
}

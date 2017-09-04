package com.qq.reader.plugin;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.t.d;
import com.etrump.jni.ETConverter;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.MainActivity;
import com.qq.reader.common.c.a;
import com.qq.reader.common.utils.ao;
import java.io.File;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

/* compiled from: EpubFontPluginManager */
public class c implements e {
    public static boolean b;
    public static int c;
    public static boolean d;
    public static int e;
    public static final String[] f = new String[]{"36"};
    public static final String[] g = new String[]{"31", "32", "33", "34", "38"};
    private static Handler h;
    protected Context a;

    public c(Context context, Handler handler) {
        this.a = context;
        h = handler;
    }

    public static synchronized void a() {
        synchronized (c.class) {
            int length = f.length;
            int i = 0;
            while (i < length) {
                l b = k.b().b(f[i]);
                if (b != null) {
                    f fVar = (f) m.c().b(ReaderApplication.getApplicationImp(), b);
                    if (!fVar.i() && ao.a(ReaderApplication.getApplicationImp(), f[i] + ".ftf", fVar.i + ".c", "fonts")) {
                        b(fVar);
                        k.b().a(b.i(), 0, 4, null, 2);
                    }
                }
                i++;
            }
        }
    }

    public boolean b() {
        int length = g.length;
        int i = 0;
        boolean z = false;
        while (i < length) {
            boolean z2;
            l b = k.b().b(g[i]);
            if (b == null || !a((f) m.c().b(this.a, b))) {
                z2 = z;
            } else {
                z2 = true;
            }
            i++;
            z = z2;
        }
        return z;
    }

    public void c() {
        for (String b : g) {
            l b2 = k.b().b(b);
            if (b2 != null) {
                f fVar = (f) m.c().a(this.a, b2);
                File file = new File(fVar.i + ".c");
                if (!(fVar.l() || file.exists())) {
                    fVar.a((e) this);
                    fVar.r();
                    e++;
                    b = true;
                }
            }
        }
    }

    public boolean a(f fVar) {
        b(fVar);
        return (fVar.l() || b || new File(fVar.i + ".c").exists()) ? false : true;
    }

    private static void b(f fVar) {
        String str = fVar.i;
        String str2 = fVar.i + ".c";
        File file = new File(str);
        File file2 = new File(str2);
        if (file.exists()) {
            if (file2.exists()) {
                file2.delete();
            }
        } else if (file2.exists() && a(str2, str) && h != null) {
            h.sendEmptyMessage(1200);
        }
    }

    protected static boolean d() {
        for (String str : g) {
            if (!((f) m.b().get(a.k + "_" + str)).l()) {
                return false;
            }
        }
        return true;
    }

    public void a(int i, String str) {
        switch (i) {
            case 6106:
                if (!d) {
                    a(this.a, (byte) 0, str);
                    d = true;
                    break;
                }
                break;
            case 6108:
                c++;
                if (d()) {
                    a(this.a, (byte) 1, str);
                    if (h != null) {
                        h.sendEmptyMessage(1200);
                        break;
                    }
                }
                break;
            case 6109:
                c++;
                a(this.a, (byte) 2, str);
                break;
        }
        if (c == e) {
            c = 0;
            e = 0;
            b = false;
            d = false;
        }
    }

    private static void a(Context context, byte b, String str) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        Intent intent = new Intent();
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence stringBuffer2;
        PendingIntent activity;
        d y;
        switch (b) {
            case (byte) 0:
                notificationManager.cancel(2);
                notificationManager.cancel(1);
                stringBuffer.append("精排版字体开始下载");
                CharSequence stringBuffer3 = stringBuffer.toString();
                d y2 = ao.y(context);
                y2.c(stringBuffer3);
                y2.a((CharSequence) "下载开始");
                y2.b(stringBuffer3);
                y2.a(null);
                notificationManager.notify(b, y2.a());
                notificationManager.cancel(0);
                return;
            case (byte) 1:
                notificationManager.cancel(2);
                stringBuffer.append("精排版字体下载完成");
                stringBuffer2 = stringBuffer.toString();
                intent.setClass(context, MainActivity.class);
                intent.setFlags(SigType.WLOGIN_QRPUSH);
                activity = PendingIntent.getActivity(context, 0, intent, 0);
                y = ao.y(context);
                y.c(stringBuffer2);
                y.a((CharSequence) "下载完成");
                y.b(stringBuffer2);
                y.a(activity);
                notificationManager.notify(b, y.a());
                return;
            case (byte) 2:
                notificationManager.cancel(1);
                stringBuffer.append(str);
                stringBuffer2 = stringBuffer.toString();
                activity = PendingIntent.getActivity(context, 0, intent, 0);
                y = ao.y(context);
                y.c(stringBuffer2);
                y.a((CharSequence) "精排版字体下载失败");
                y.b(stringBuffer2);
                y.a(activity);
                notificationManager.notify(b, y.a());
                return;
            default:
                return;
        }
    }

    protected static boolean a(String str, String str2) {
        try {
            if (new ETConverter().native_ftf2ttf(str, str2, null, 242)) {
                new File(str).delete();
                return true;
            }
            File file = new File(str2);
            if (file.exists()) {
                file.delete();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void e() {
        h = null;
    }
}

package com.liveshow.b;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.ab;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.l;
import com.qq.reader.cservice.download.app.ReaderDownloadAppTask;
import com.qq.reader.liveshow.b.d;
import java.io.File;

/* compiled from: ReaderLiveLibLoader */
public class f {
    public static final String a = b;
    private static final String b = (ReaderApplication.getApplicationImp().getApplicationInfo().dataDir + "/txav" + "/");
    private static final String c = (a.m + "qqreader_6.5.3.0888_android" + "so.zip");
    private static final String[] d = new String[]{"lib_imcore_jni_gyp.so", "libBugly.so", "libhwcodec.so", "libqalcodecwrapper.so", "libqalmsfboot.so", "libqav_graphics.so", "libqavsdk.so", "libstlport_shared.so", "libTcVpxDec.so", "libTcVpxEnc.so", "libUDT.so", "libtraeimp-armeabi-v7a.so", "libwtcrypto.so", "libxplatform.so"};
    private Handler e = new Handler(Looper.getMainLooper());

    public void a(final Context context, final d dVar) {
        boolean z;
        if (dVar != null) {
            dVar.a();
        }
        for (String file : d) {
            if (!new File(a, file).exists()) {
                z = false;
                break;
            }
        }
        z = true;
        if (z) {
            c.e("ReaderLiveLibLoader", "all so exist");
            if (a(context)) {
                if (dVar != null) {
                    dVar.a(true);
                    return;
                }
                return;
            }
        }
        c.e("ReaderLiveLibLoader", "not all so exist");
        ab.a(new File(a), false);
        new File(a).mkdirs();
        c.e("ReaderLiveLibLoader", "start download so lib zip entry");
        ReaderTask readerDownloadAppTask = new ReaderDownloadAppTask(context, c, "http://mag.reader.3g.qq.com/plugin/qqreader_6.5.0.0888_android.zip");
        readerDownloadAppTask.setListener(new com.qq.reader.cservice.download.app.a(this) {
            final /* synthetic */ f c;

            public void a(boolean z, String str) {
                if (z) {
                    c.e("ReaderLiveLibLoader", "download success");
                    try {
                        c.e("ReaderLiveLibLoader", "start unzip");
                        File file = new File(f.a);
                        if (file.exists()) {
                            file.delete();
                        }
                        file.setWritable(true);
                        ao.f(f.c, f.a);
                        c.e("ReaderLiveLibLoader", "unzip success");
                    } catch (Exception e) {
                        c.e("ReaderLiveLibLoader", "unzip failed");
                        e.printStackTrace();
                        z = false;
                    }
                }
                new File(f.c).delete();
                this.c.e.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void run() {
                        if (z) {
                            boolean a = f.a(context);
                            if (dVar != null) {
                                dVar.a(a);
                            }
                        } else if (dVar != null) {
                            dVar.a(false);
                        }
                    }
                });
            }

            public void a(final int i) {
                this.c.e.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void run() {
                        if (dVar != null) {
                            dVar.a(i);
                        }
                    }
                });
            }
        });
        g.a().a(readerDownloadAppTask);
    }

    public static boolean a(Context context) {
        boolean a;
        int i = 0;
        c.e("ReaderLiveLibLoader", "inject forlder = " + a);
        do {
            c.e("ReaderLiveLibLoader", "start inject times = " + i);
            a = l.a(context, context.getClassLoader(), a);
            i++;
            if (a) {
                break;
            }
        } while (i < 3);
        c.e("ReaderLiveLibLoader", "inject " + (a ? "success" : "failed"));
        return a;
    }
}

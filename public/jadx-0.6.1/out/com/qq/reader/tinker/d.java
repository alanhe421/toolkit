package com.qq.reader.tinker;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderDownloadTask;
import com.qq.reader.common.readertask.ordinal.b;
import com.qq.reader.common.utils.StatisticsManager;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PatchUtil */
public class d {
    public static String a = (a.bj + "q.patch");
    public static String b = "";

    public static void a(final Handler handler, final String str, String str2) {
        c.e("Tinker.PatchUtil", "add download task ");
        ReaderTask readerDownloadTask = new ReaderDownloadTask(ReaderApplication.getApplicationImp(), a, str2);
        readerDownloadTask.setStrongRefListener(new b() {
            public void a(boolean z) {
                c.e("Tinker.PatchUtil", "download patch : " + (z ? "success" : "faild"));
                j.b("Tinker -- download patch : " + (z ? "success" : "faild"));
                Message obtainMessage = handler.obtainMessage(11000601);
                if (z) {
                    obtainMessage.arg1 = 1;
                    obtainMessage.obj = d.a;
                    d.b = str;
                    i.a("event_patch_download_success", null, ReaderApplication.getApplicationImp());
                } else {
                    obtainMessage.arg1 = 0;
                }
                handler.sendMessage(obtainMessage);
            }
        });
        g.a().a(readerDownloadTask);
    }

    private static void b() {
        Tinker with = Tinker.with(ReaderApplication.getApplicationImp());
        if (with.isTinkerLoaded()) {
            with.cleanPatch();
        }
    }

    public static void a() {
        g.a().a(new PatchUtil$2());
    }

    private static void c(Context context) {
        c.d("Tinker.PatchUtil", "restart process");
        ShareTinkerInternals.killAllOtherProcess(context);
        Process.killProcess(Process.myPid());
    }

    public static void a(Context context) {
        if (ShareTinkerInternals.isInMainProcess(context)) {
            boolean b = com.qq.reader.appconfig.a.a.b(context);
            c.e("Tinker.PatchUtil", "check tinker status ,need start : " + b);
            if (b) {
                Object c = com.qq.reader.appconfig.a.a.c(context);
                if (TextUtils.isEmpty(c)) {
                    c.e("Tinker.PatchUtil", "need clean Patch : false");
                } else {
                    c.e("Tinker.PatchUtil", "need clean Patch : true");
                    if (Tinker.with(context).isTinkerLoaded() && c.equals(a.d)) {
                        b();
                    }
                    com.qq.reader.appconfig.a.a.b(context, "");
                }
                c(context);
            }
        }
    }

    public static void b(Context context) {
        try {
            if (ShareTinkerInternals.isInMainProcess(context)) {
                if (com.qq.reader.appconfig.a.a.b(ReaderApplication.getApplicationImp())) {
                    com.qq.reader.appconfig.a.a.a(ReaderApplication.getApplicationImp(), false);
                }
                if (Tinker.with(ReaderApplication.getApplicationImp()).isTinkerLoaded()) {
                    j.b("Tinker -- patch load success with tinkerid : " + a.d);
                    Object a = com.qq.reader.appconfig.a.a.a(ReaderApplication.getApplicationImp());
                    if (TextUtils.isEmpty(a) || !a.equalsIgnoreCase(a.d)) {
                        c.e("Tinker.ReaderApplication", "got a unreport tinkerid :" + a.d);
                        Map hashMap = new HashMap();
                        hashMap.put("frompatch", BaseBuildInfo.b);
                        hashMap.put("topatch", a.d);
                        i.a("PATCH_SUCCESS_EVENT", hashMap, ReaderApplication.getApplicationImp());
                        StatisticsManager.a().a("PATCH_SUCCESS_EVENT", hashMap, 110, true);
                        j.b("Tinker -- report patch success with tinkerid from: " + BaseBuildInfo.b + " to : " + a.d);
                        com.qq.reader.appconfig.a.a.a(context, a.d);
                    }
                } else if (!TextUtils.isEmpty(com.qq.reader.appconfig.a.a.a(ReaderApplication.getApplicationImp()))) {
                    com.qq.reader.appconfig.a.a.a(context, "");
                }
            }
        } catch (Exception e) {
        }
    }
}

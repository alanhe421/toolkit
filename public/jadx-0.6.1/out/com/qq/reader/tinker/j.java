package com.qq.reader.tinker;

import com.tencent.tinker.lib.listener.PatchListener;
import com.tencent.tinker.lib.patch.AbstractPatch;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.reporter.LoadReporter;
import com.tencent.tinker.lib.reporter.PatchReporter;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.app.ApplicationLike;

/* compiled from: TinkerManager */
public class j {
    private static ApplicationLike a;
    private static i b;
    private static boolean c = false;

    public static void a(ApplicationLike applicationLike) {
        a = applicationLike;
    }

    public static ApplicationLike a() {
        return a;
    }

    public static void b() {
        if (b == null) {
            b = new i();
            Thread.setDefaultUncaughtExceptionHandler(b);
        }
    }

    public static void a(boolean z) {
        l.a(a.getApplication()).a(z);
    }

    public static void b(ApplicationLike applicationLike) {
        if (c) {
            TinkerLog.w("Tinker.TinkerManager", "install tinker, but has installed, ignore", new Object[0]);
            return;
        }
        LoadReporter eVar = new e(applicationLike.getApplication());
        PatchReporter gVar = new g(applicationLike.getApplication());
        PatchListener fVar = new f(applicationLike.getApplication());
        AbstractPatch upgradePatch = new UpgradePatch();
        h.a(new k());
        TinkerInstaller.install(applicationLike, eVar, gVar, fVar, SampleResultService.class, upgradePatch);
        c = true;
    }
}

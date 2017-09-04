package com.qq.reader.tinker;

import android.content.SharedPreferences;
import android.os.SystemClock;
import com.tencent.tinker.lib.tinker.TinkerApplicationHelper;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import com.tencent.upload.log.trace.TracerConfig;
import java.lang.Thread.UncaughtExceptionHandler;

/* compiled from: SampleUncaughtExceptionHandler */
public class i implements UncaughtExceptionHandler {
    private final UncaughtExceptionHandler a = Thread.getDefaultUncaughtExceptionHandler();

    public void uncaughtException(Thread thread, Throwable th) {
        TinkerLog.e("Tinker.SampleUncaughtExHandler", "uncaughtException:" + th.getMessage(), new Object[0]);
        a();
        a(th);
        this.a.uncaughtException(thread, th);
    }

    private void a(Throwable th) {
        boolean z = false;
        while (th != null) {
            boolean z2;
            if (z) {
                z2 = z;
            } else {
                z2 = m.a(th);
            }
            if (z2) {
                ApplicationLike a = j.a();
                if (a != null && a.getApplication() != null && TinkerApplicationHelper.isTinkerLoadSuccess(a)) {
                    int i;
                    if ((th instanceof IllegalAccessError) && th.getMessage().contains("Class ref in pre-verified class resolved to unexpected implementation")) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if (i != 0) {
                        h.f();
                        TinkerLog.e("Tinker.SampleUncaughtExHandler", "have xposed: just clean tinker", new Object[0]);
                        ShareTinkerInternals.killAllOtherProcess(a.getApplication());
                        TinkerApplicationHelper.cleanPatch(a);
                        ShareTinkerInternals.setTinkerDisableWithSharedPreferences(a.getApplication());
                        return;
                    }
                } else {
                    return;
                }
            }
            th = th.getCause();
            z = z2;
        }
    }

    private boolean a() {
        ApplicationLike a = j.a();
        if (a == null || a.getApplication() == null || !TinkerApplicationHelper.isTinkerLoadSuccess(a) || SystemClock.elapsedRealtime() - a.getApplicationStartElapsedTime() >= TracerConfig.LOG_FLUSH_DURATION) {
            return false;
        }
        String currentVersion = TinkerApplicationHelper.getCurrentVersion(a);
        if (ShareTinkerInternals.isNullOrNil(currentVersion)) {
            return false;
        }
        SharedPreferences sharedPreferences = a.getApplication().getSharedPreferences(ShareConstants.TINKER_SHARE_PREFERENCE_CONFIG, 4);
        int i = sharedPreferences.getInt(currentVersion, 0) + 1;
        if (i >= 3) {
            h.e();
            TinkerApplicationHelper.cleanPatch(a);
            TinkerLog.e("Tinker.SampleUncaughtExHandler", "tinker has fast crash more than %d, we just clean patch!", Integer.valueOf(i));
            return true;
        }
        sharedPreferences.edit().putInt(currentVersion, i).commit();
        TinkerLog.e("Tinker.SampleUncaughtExHandler", "tinker has fast crash %d times", Integer.valueOf(i));
        return false;
    }
}

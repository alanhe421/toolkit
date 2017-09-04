package com.qq.reader.tinker;

import android.content.Context;
import android.os.Looper;
import android.os.MessageQueue.IdleHandler;
import com.tencent.tinker.lib.reporter.DefaultLoadReporter;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;

/* compiled from: SampleLoadReporter */
public class e extends DefaultLoadReporter {
    public e(Context context) {
        super(context);
    }

    public void onLoadPatchListenerReceiveFail(File file, int i) {
        super.onLoadPatchListenerReceiveFail(file, i);
        h.a(i);
    }

    public void onLoadResult(File file, int i, long j) {
        super.onLoadResult(file, i, j);
        switch (i) {
            case 0:
                h.a(j);
                break;
        }
        Looper.getMainLooper();
        Looper.myQueue().addIdleHandler(new IdleHandler(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public boolean queueIdle() {
                l.a(this.a.context).a();
                return false;
            }
        });
    }

    public void onLoadException(Throwable th, int i) {
        super.onLoadException(th, i);
        switch (i) {
            case -4:
                String checkTinkerLastUncaughtCrash = SharePatchFileUtil.checkTinkerLastUncaughtCrash(this.context);
                if (!ShareTinkerInternals.isNullOrNil(checkTinkerLastUncaughtCrash)) {
                    SharePatchFileUtil.safeDeleteFile(SharePatchFileUtil.getPatchLastCrashFile(this.context));
                    TinkerLog.e("Tinker.SampleLoadReporter", "tinker uncaught real exception:" + checkTinkerLastUncaughtCrash, new Object[0]);
                    break;
                }
                break;
        }
        h.a(th, i);
    }

    public void onLoadFileMd5Mismatch(File file, int i) {
        super.onLoadFileMd5Mismatch(file, i);
        h.d(i);
    }

    public void onLoadFileNotFound(File file, int i, boolean z) {
        TinkerLog.i("Tinker.SampleLoadReporter", "patch loadReporter onLoadFileNotFound: patch file not found: %s, fileType:%d, isDirectory:%b", file.getAbsolutePath(), Integer.valueOf(i), Boolean.valueOf(z));
        if (i == 4) {
            Tinker with = Tinker.with(this.context);
            if (with.isMainProcess()) {
                File file2 = with.getTinkerLoadResultIfPresent().patchVersionFile;
                if (file2 != null) {
                    if (l.a(this.context).a(SharePatchFileUtil.getMD5(file2))) {
                        TinkerLog.i("Tinker.SampleLoadReporter", "try to repair oat file on patch process", new Object[0]);
                        TinkerInstaller.onReceiveUpgradePatch(this.context, file2.getAbsolutePath());
                    } else {
                        TinkerLog.i("Tinker.SampleLoadReporter", "repair retry exceed must max time, just clean", new Object[0]);
                        checkAndCleanPatch();
                    }
                }
            }
        } else {
            checkAndCleanPatch();
        }
        h.c(i);
    }

    public void onLoadPackageCheckFail(File file, int i) {
        super.onLoadPackageCheckFail(file, i);
        h.b(i);
    }

    public void onLoadPatchInfoCorrupted(String str, String str2, File file) {
        super.onLoadPatchInfoCorrupted(str, str2, file);
        h.a();
    }

    public void onLoadPatchVersionChanged(String str, String str2, File file, String str3) {
        super.onLoadPatchVersionChanged(str, str2, file, str3);
    }
}

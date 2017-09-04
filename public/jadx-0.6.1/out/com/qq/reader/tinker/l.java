package com.qq.reader.tinker;

import android.content.Context;
import android.content.Intent;
import com.tencent.tinker.lib.service.TinkerPatchService;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.lib.util.TinkerServiceInternals;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/* compiled from: UpgradePatchRetry */
public class l {
    private static l e;
    private boolean a = false;
    private File b = null;
    private File c = null;
    private Context d = null;

    /* compiled from: UpgradePatchRetry */
    static class a {
        String a;
        String b;

        a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        static a a(File file) {
            Closeable fileInputStream;
            String property;
            Object e;
            Throwable th;
            String str = null;
            Properties properties = new Properties();
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    properties.load(fileInputStream);
                    property = properties.getProperty("md5");
                    try {
                        str = properties.getProperty("times");
                        SharePatchFileUtil.closeQuietly(fileInputStream);
                    } catch (IOException e2) {
                        e = e2;
                        try {
                            TinkerLog.e("Tinker.UpgradePatchRetry", "fail to readRetryProperty:" + e, new Object[0]);
                            SharePatchFileUtil.closeQuietly(fileInputStream);
                            return new a(property, str);
                        } catch (Throwable th2) {
                            th = th2;
                            SharePatchFileUtil.closeQuietly(fileInputStream);
                            throw th;
                        }
                    }
                } catch (IOException e3) {
                    IOException iOException = e3;
                    property = str;
                    TinkerLog.e("Tinker.UpgradePatchRetry", "fail to readRetryProperty:" + e, new Object[0]);
                    SharePatchFileUtil.closeQuietly(fileInputStream);
                    return new a(property, str);
                }
            } catch (IOException e32) {
                e = e32;
                fileInputStream = str;
                property = str;
                TinkerLog.e("Tinker.UpgradePatchRetry", "fail to readRetryProperty:" + e, new Object[0]);
                SharePatchFileUtil.closeQuietly(fileInputStream);
                return new a(property, str);
            } catch (Throwable th3) {
                fileInputStream = str;
                th = th3;
                SharePatchFileUtil.closeQuietly(fileInputStream);
                throw th;
            }
            return new a(property, str);
        }

        static void a(File file, a aVar) {
            Closeable fileOutputStream;
            Throwable e;
            if (aVar != null) {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                Properties properties = new Properties();
                properties.put("md5", aVar.a);
                properties.put("times", aVar.b);
                try {
                    fileOutputStream = new FileOutputStream(file, false);
                    try {
                        properties.store(fileOutputStream, null);
                        SharePatchFileUtil.closeQuietly(fileOutputStream);
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            TinkerLog.printErrStackTrace("Tinker.UpgradePatchRetry", e, "retry write property fail", new Object[0]);
                            SharePatchFileUtil.closeQuietly(fileOutputStream);
                        } catch (Throwable th) {
                            e = th;
                            SharePatchFileUtil.closeQuietly(fileOutputStream);
                            throw e;
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    fileOutputStream = null;
                    TinkerLog.printErrStackTrace("Tinker.UpgradePatchRetry", e, "retry write property fail", new Object[0]);
                    SharePatchFileUtil.closeQuietly(fileOutputStream);
                } catch (Throwable th2) {
                    e = th2;
                    fileOutputStream = null;
                    SharePatchFileUtil.closeQuietly(fileOutputStream);
                    throw e;
                }
            }
        }
    }

    public l(Context context) {
        this.d = context;
        this.b = new File(SharePatchFileUtil.getPatchTempDirectory(context), "patch.retry");
        this.c = new File(SharePatchFileUtil.getPatchTempDirectory(context), "temp.apk");
    }

    public static l a(Context context) {
        if (e == null) {
            e = new l(context);
        }
        return e;
    }

    public void a() {
        if (!this.a) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchRetryLoad retry disabled, just return", new Object[0]);
        } else if (!Tinker.with(this.d).isMainProcess()) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchRetryLoad retry is not main process, just return", new Object[0]);
        } else if (!this.b.exists()) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchRetryLoad retry info not exist, just return", new Object[0]);
        } else if (TinkerServiceInternals.isTinkerPatchServiceRunning(this.d)) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchRetryLoad tinker service is running, just return", new Object[0]);
        } else {
            String absolutePath = this.c.getAbsolutePath();
            if (absolutePath == null || !new File(absolutePath).exists()) {
                TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchRetryLoad patch file: %s is not exist, just return", absolutePath);
                return;
            }
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchRetryLoad patch file: %s is exist, retry to patch", absolutePath);
            TinkerInstaller.onReceiveUpgradePatch(this.d, absolutePath);
            h.g();
        }
    }

    public void a(Intent intent) {
        if (!this.a) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchServiceStart retry disabled, just return", new Object[0]);
        } else if (intent == null) {
            TinkerLog.e("Tinker.UpgradePatchRetry", "onPatchServiceStart intent is null, just return", new Object[0]);
        } else {
            String patchPathExtra = TinkerPatchService.getPatchPathExtra(intent);
            if (patchPathExtra == null) {
                TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchServiceStart patch path is null, just return", new Object[0]);
                return;
            }
            File file = new File(patchPathExtra);
            String md5 = SharePatchFileUtil.getMD5(file);
            if (md5 == null) {
                TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchServiceStart patch md5 is null, just return", new Object[0]);
                return;
            }
            a a;
            if (this.b.exists()) {
                a = a.a(this.b);
                if (a.a == null || a.b == null || !md5.equals(a.a)) {
                    a(file);
                    a.a = md5;
                    a.b = "1";
                } else {
                    int parseInt = Integer.parseInt(a.b);
                    if (parseInt >= 4) {
                        SharePatchFileUtil.safeDeleteFile(this.c);
                        TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchServiceStart retry more than max count, delete retry info file!", new Object[0]);
                        return;
                    }
                    a.b = String.valueOf(parseInt + 1);
                }
            } else {
                a(file);
                a = new a(md5, "1");
            }
            a.a(this.b, a);
        }
    }

    public boolean a(String str) {
        if (!this.a) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchListenerCheck retry disabled, just return", new Object[0]);
            return true;
        } else if (!this.b.exists()) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchListenerCheck retry file is not exist, just return", new Object[0]);
            return true;
        } else if (str == null) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchListenerCheck md5 is null, just return", new Object[0]);
            return true;
        } else {
            a a = a.a(this.b);
            if (!str.equals(a.a) || Integer.parseInt(a.b) < 4) {
                return true;
            }
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchListenerCheck, retry count %d must exceed than max retry count", Integer.valueOf(Integer.parseInt(a.b)));
            SharePatchFileUtil.safeDeleteFile(this.c);
            return false;
        }
    }

    public void b() {
        if (!this.a) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "onPatchServiceResult retry disabled, just return", new Object[0]);
        } else if (this.c.exists()) {
            SharePatchFileUtil.safeDeleteFile(this.c);
        }
    }

    public void a(boolean z) {
        this.a = z;
    }

    private void a(File file) {
        if (!file.getAbsolutePath().equals(this.c.getAbsolutePath())) {
            TinkerLog.w("Tinker.UpgradePatchRetry", "try copy file: %s to %s", file.getAbsolutePath(), this.c.getAbsolutePath());
            try {
                SharePatchFileUtil.copyFileUsingStream(file, this.c);
            } catch (IOException e) {
                TinkerLog.e("Tinker.UpgradePatchRetry", "fail to copy file: %s to %s", file.getAbsolutePath(), this.c.getAbsolutePath());
            }
        }
    }
}

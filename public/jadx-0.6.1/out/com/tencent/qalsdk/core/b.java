package com.tencent.qalsdk.core;

import android.content.Context;
import android.os.Environment;
import com.tencent.qalsdk.util.QLog;
import com.tencent.smtt.sdk.WebView;
import java.io.File;

/* compiled from: CoreUtil */
public class b {
    static int a = -1;
    private static final String b = "MSF.C.CoreUtil";
    private static String c = (Environment.getExternalStorageDirectory().getPath() + "/qalsdk/files");

    public static synchronized String a(Context context) {
        String str = null;
        synchronized (b.class) {
            try {
                String absolutePath;
                File filesDir = context.getFilesDir();
                if (filesDir == null) {
                    filesDir = context.getCacheDir();
                    if (filesDir != null) {
                        absolutePath = filesDir.getAbsolutePath();
                        int lastIndexOf = absolutePath.lastIndexOf(47);
                        if (lastIndexOf != -1) {
                            absolutePath = absolutePath.substring(0, lastIndexOf) + "/files/";
                        }
                    } else if (QLog.isColorLevel()) {
                        QLog.w(b, 2, "load cache dir is null");
                        absolutePath = null;
                    } else {
                        absolutePath = null;
                    }
                } else {
                    absolutePath = filesDir.getAbsolutePath();
                }
                File file;
                if (absolutePath == null) {
                    absolutePath = c;
                    file = new File(absolutePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                } else {
                    file = new File(absolutePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    if (!(file.exists() && file.canWrite())) {
                        absolutePath = c;
                        new File(absolutePath).mkdirs();
                    }
                }
                if (QLog.isColorLevel()) {
                    QLog.d(b, 2, "load save root dir is " + absolutePath);
                }
                str = absolutePath;
            } catch (Throwable th) {
                if (QLog.isColorLevel()) {
                    QLog.w(b, 2, "getSaveRootPath error ", th);
                }
            }
        }
        return str;
    }

    public static int b(Context context) {
        if (a == -1 && context != null) {
            try {
                a = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return a;
    }

    public static byte[] a(int i) {
        return new byte[]{(byte) ((i >>> 24) & 255), (byte) ((i >>> 16) & 255), (byte) ((i >>> 8) & 255), (byte) (i & 255)};
    }

    public static int a(byte[] bArr, int i) {
        return (((bArr[i + 3] & 255) | ((bArr[i + 2] << 8) & 65280)) | ((bArr[i + 1] << 16) & 16711680)) | ((bArr[i + 0] << 24) & WebView.NIGHT_MODE_COLOR);
    }
}

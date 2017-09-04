package com.qq.reader.common.imageloader.d;

import android.content.Context;
import android.os.Environment;
import com.qq.reader.common.monitor.f;
import java.io.File;

/* compiled from: StorageUtils */
public final class b {
    public static File a(Context context, String str) {
        File file = null;
        if ("mounted".equals(Environment.getExternalStorageState()) && a(context)) {
            file = b(context, str);
        }
        if (file == null) {
            file = context.getCacheDir();
        }
        if (file == null) {
            return new File("/data/data/" + context.getPackageName() + "/cache/");
        }
        return file;
    }

    private static File b(Context context, String str) {
        File file = new File(str);
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        f.c("ImageLoader", "Unable to create external cache directory");
        return null;
    }

    private static boolean a(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }
}

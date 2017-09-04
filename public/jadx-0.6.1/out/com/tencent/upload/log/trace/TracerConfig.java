package com.tencent.upload.log.trace;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.tencent.upload.log.a.b;
import java.io.File;

public class TracerConfig {
    public static final String DEF_TRACE_FILEEXT = ".upload.log";
    private static String DEF_TRACE_FILEPRE = null;
    public static final long LOG_FLUSH_DURATION = 10000;
    public static final long LOG_MAX_KEEP_DAY = 7;
    public static final int MAX_BLOCK_COUNT = 36;
    public static final int MAX_BLOCK_SIZE = 524288;
    public static final long MEMORY_SIZE = 32768;

    public static final String getLogDir(Context context) {
        return Environment.getExternalStorageDirectory() + File.separator + "android" + File.separator + "data" + File.separator + context.getPackageName() + File.separator + "logs";
    }

    public static final String getLogFilePre(Context context) {
        if (!TextUtils.isEmpty(DEF_TRACE_FILEPRE)) {
            return DEF_TRACE_FILEPRE;
        }
        String str = "";
        if (b.b(context)) {
            return "";
        }
        String a = b.a(context);
        int indexOf = a.indexOf(":");
        return indexOf >= 0 ? a.substring(indexOf + 1) + "." : str;
    }
}

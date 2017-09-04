package com.tencent.mm.performance.util;

import android.os.Debug;
import android.os.Environment;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.f;
import java.io.File;
import java.io.IOException;

public class Util {
    public static final String DUMP_FILE_PATH = a.Q;
    public static final String HPROF_FORMAT = ".hprof";
    private static final String TAG = "MicroMsg.Util";

    public static String getThreadStack(Thread thread, boolean z) {
        StackTraceElement[] stackTrace = thread.getStackTrace();
        if (stackTrace == null || stackTrace.length < 4) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < stackTrace.length; i++) {
            if (stackTrace[i].getClassName().contains("com.qq.reader")) {
                stringBuilder.append("[");
                stringBuilder.append(stackTrace[i].getClassName().substring("com.qq.reader.".length()));
                stringBuilder.append(":");
                stringBuilder.append(stackTrace[i].getMethodName());
                if (z) {
                    stringBuilder.append("(" + stackTrace[i].getLineNumber() + ")]");
                } else {
                    stringBuilder.append("]");
                }
            }
        }
        return stringBuilder.toString();
    }

    private static boolean checkSdcard() {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            File file = new File(DUMP_FILE_PATH);
            if (!file.exists()) {
                file.mkdirs();
            }
            return true;
        }
        f.b(TAG, "Hprof sdcard is invalid");
        return false;
    }

    public static String dumpHprofFileForName(String str) {
        if (!checkSdcard()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DUMP_FILE_PATH).append(str).append(HPROF_FORMAT);
        String stringBuilder2 = stringBuilder.toString();
        try {
            System.gc();
            System.gc();
            Debug.dumpHprofData(stringBuilder2);
            return stringBuilder2;
        } catch (IOException e) {
            f.a(TAG, " dumpHprofFile IOException");
            return null;
        }
    }
}

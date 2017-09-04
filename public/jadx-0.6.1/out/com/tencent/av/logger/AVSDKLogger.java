package com.tencent.av.logger;

import android.util.Log;
import com.tencent.av.sdk.LogListener;
import java.io.File;

public class AVSDKLogger {
    public static final int DEFAULT_MAX_LOG_FILE_SIZE = 52428800;
    private static final String LOGTAG = "AVSDKLogger";
    private static boolean s_IsEnablePrintLog;
    private static boolean s_IsEnableWriteLog;
    private static String s_LogDir;
    private static LogListener s_LogListener;
    private static int s_MaxFileSize;

    public static void setIsEnablePrintLog(boolean z) {
        s_IsEnablePrintLog = z;
    }

    public static boolean isEnablePrintLog() {
        return s_IsEnablePrintLog;
    }

    public static void setIsEnableWriteLog(boolean z) {
        s_IsEnableWriteLog = z;
    }

    public static boolean isEnableWriteLog() {
        return s_IsEnableWriteLog;
    }

    public static String getLogDir() {
        return s_LogDir;
    }

    public static void setLogDir(String str) {
        File file = new File(str);
        if (file.exists()) {
            Log.i(LOGTAG, "log dir exist");
        } else {
            Log.e(LOGTAG, "log dir not exist");
            file.mkdir();
        }
        s_LogDir = str;
    }

    public static void setMaxFileSize(int i) {
        s_MaxFileSize = i;
    }

    public static int getMaxFileSize() {
        return s_MaxFileSize;
    }

    public static void setLogListener(LogListener logListener) {
        s_LogListener = logListener;
    }

    public static LogListener getLogListener() {
        return s_LogListener;
    }
}

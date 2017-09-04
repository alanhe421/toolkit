package com.tencent.av.utils;

import android.util.Log;
import com.tencent.av.logger.AVSDKLogger;

public class QLog {
    public static final int CLR = 0;
    public static final String SDK = "AVSDK";
    public static final String SPLIT = "          ";
    public static final int USR = 1;

    public static native void writeLog(String str);

    public static boolean isColorLevel() {
        return true;
    }

    public static void d(String str, int i, String str2) {
        if (AVSDKLogger.isEnablePrintLog()) {
            Log.d(str, str2);
        }
        if (AVSDKLogger.isEnableWriteLog()) {
            writeLog("AVSDK          " + str + SPLIT + str2 + "\n");
        }
    }

    public static void d(String str, int i, String str2, Exception exception) {
        if (AVSDKLogger.isEnablePrintLog()) {
            Log.d(str, str2);
        }
        if (AVSDKLogger.isEnableWriteLog()) {
            writeLog("AVSDK          " + str + SPLIT + str2 + "\n");
        }
    }

    public static void w(String str, int i, String str2) {
        if (AVSDKLogger.isEnablePrintLog()) {
            Log.w(str, str2);
        }
        if (AVSDKLogger.isEnableWriteLog()) {
            writeLog("AVSDK          " + str + SPLIT + str2 + "\n");
        }
    }

    public static void w(String str, int i, String str2, Exception exception) {
        if (AVSDKLogger.isEnablePrintLog()) {
            Log.w(str, str2);
        }
        if (AVSDKLogger.isEnableWriteLog()) {
            writeLog("AVSDK          " + str + SPLIT + str2 + "\n");
        }
    }

    public static void e(String str, int i, String str2) {
        if (AVSDKLogger.isEnablePrintLog()) {
            Log.e(str, str2);
        }
        if (AVSDKLogger.isEnableWriteLog()) {
            writeLog("AVSDK          " + str + SPLIT + str2 + "\n");
        }
    }

    public static void e(String str, int i, String str2, Exception exception) {
        if (AVSDKLogger.isEnablePrintLog()) {
            Log.e(str, str2, exception);
        }
        if (AVSDKLogger.isEnableWriteLog()) {
            writeLog("AVSDK          " + str + SPLIT + str2 + SPLIT + Log.getStackTraceString(exception) + "\n");
        }
    }

    public static void i(String str, int i, String str2) {
        if (AVSDKLogger.isEnablePrintLog()) {
            Log.i(str, str2);
        }
        if (AVSDKLogger.isEnableWriteLog()) {
            writeLog("AVSDK          " + str + SPLIT + str2 + "\n");
        }
    }

    public static void i(String str, int i, String str2, Exception exception) {
        if (AVSDKLogger.isEnablePrintLog()) {
            Log.i(str, str2);
        }
        if (AVSDKLogger.isEnableWriteLog()) {
            writeLog("AVSDK          " + str + SPLIT + str2 + "\n");
        }
    }
}

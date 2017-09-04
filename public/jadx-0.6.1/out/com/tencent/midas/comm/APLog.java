package com.tencent.midas.comm;

import android.util.Log;
import java.lang.reflect.Method;

public class APLog {
    private static APLogInfo logInfo = new APLogInfo();
    private static APLogWriter mLogWriter = null;

    private static class Inner {
        static APLog apLog = new APLog();

        private Inner() {
        }
    }

    private APLog() {
    }

    public static void init(APLogInfo aPLogInfo) {
        logInfo = aPLogInfo;
    }

    public static void i(String str, String str2) {
        if (logInfo.isLogEnable()) {
            Log.i(logInfo.getLogTag(), str + " | " + str2);
            printLog(str, str2);
        }
    }

    public static void d(String str, String str2) {
        if (logInfo.isLogEnable()) {
            Log.d(logInfo.getLogTag(), str + " | " + str2);
            printLog(str, str2);
        }
    }

    public static void v(String str, String str2) {
        if (logInfo.isLogEnable()) {
            Log.v(logInfo.getLogTag(), str + " | " + str2);
            printLog(str, str2);
        }
    }

    public static void w(String str, String str2) {
        Log.w(logInfo.getLogTag(), str + " | " + str2);
        printLog(str, str2);
    }

    public static void e(String str, String str2) {
        Log.e(logInfo.getLogTag(), str + " | " + str2);
        printLog(str, str2);
    }

    private static void writer(String str, String str2) {
        try {
            mLogWriter = APLogWriter.open();
            mLogWriter.print(str, str2);
        } catch (Exception e) {
            Log.e(APLogInfo.INNER_LOG_TAG, "APLog writer" + e.toString());
        }
    }

    private static void printLog(String str, String str2) {
        try {
            if (logInfo.getContext() != null && isWritePermission() && getPrintState()) {
                writer(str, str2);
            }
        } catch (Exception e) {
            Log.i(APLogInfo.INNER_LOG_TAG, e.toString());
        }
    }

    private static boolean getPrintState() {
        Method method = null;
        String str = "1";
        try {
            Class cls = Class.forName("com.pay.tool.APDataInterface");
            if (cls != null) {
                try {
                    method = cls.getDeclaredMethod("singleton", new Class[0]);
                } catch (NoSuchMethodException e) {
                }
                try {
                    method.invoke(null, new Object[0]);
                } catch (Exception e2) {
                }
                try {
                    cls.getMethod("getUploadLog", new Class[0]);
                } catch (NoSuchMethodException e3) {
                }
            }
        } catch (Exception e4) {
        }
        return str.equals("1") || str.equals("2");
    }

    public static APLogInfo getLogInfo() {
        return logInfo;
    }

    public static void closeLog() {
        try {
            mLogWriter.close();
        } catch (Exception e) {
            Log.i(APLogInfo.INNER_LOG_TAG, e.toString());
        }
    }

    public String getLine() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        int lineNumber = stackTraceElement.getLineNumber();
        return stackTraceElement.getFileName() + " Line:" + String.valueOf(lineNumber) + " ";
    }

    private static boolean isWritePermission() {
        boolean z;
        if (logInfo.getContext().getPackageManager().checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", APLogWriter.getPackage(logInfo.getContext())) == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return true;
        }
        return false;
    }
}

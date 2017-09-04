package com.tencent.imsdk;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.dynamicload.Lib.DLConstants;
import com.tencent.TIMLogListener;
import com.tencent.TIMManager;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class QLogImpl {
    private static final int[] INTERVAL_RETRY_INIT = new int[]{1, 2, 4, 8, 16, 29};
    private static final String MEMTag = "appMemory";
    public static final String TAG_REPORTLEVEL_COLORUSER = "W";
    public static final String TAG_REPORTLEVEL_DEVELOPER = "D";
    public static final String TAG_REPORTLEVEL_USER = "E";
    private static int UIN_REPORTLOG_LEVEL = 4;
    protected static int _DEFAULT_REPORTLOG_LEVEL = 4;
    public static Runnable acutualInitRunnable = new bp();
    static long colorLogTime = 0;
    static HashSet<String> colorTags = new HashSet();
    protected static Object formatterLock = new Object();
    private static AtomicBoolean isInitLogFileDone = new AtomicBoolean(false);
    protected static boolean isLogToFile = true;
    private static AtomicBoolean isPreExceptionEnospc = new AtomicBoolean(false);
    private static long lastPrintMemeoryTime = 0;
    static long lastWriterErrorTime = 0;
    static final ReentrantLock lock = new ReentrantLock();
    static MyLinkedBlockingDeque<String> logDeque = new MyLinkedBlockingDeque(15000);
    private static String logPath = "";
    private static String logTime = "";
    private static int myProcessId = 0;
    private static long nextHourTime = 0;
    private static long nextSecondMinuteTime = 0;
    static String nowUsedFile = "";
    private static String packageName = "";
    private static String processName = "";
    private static Handler retryInitHandler = new Handler(Looper.getMainLooper());
    private static AtomicInteger retryInitTimes = new AtomicInteger(0);
    private static volatile Context sContext = null;
    private static TIMLogListener sdkLogLister = null;
    static Thread t = new br();
    private static final String tag = "MSF.D.QLogImpl";
    private static FileWriter writer;

    public static class LogFile extends File {
        public LogFile(File file, String str) {
            super(file, str);
        }

        public LogFile(String str) {
            super(str);
        }
    }

    protected static class QLogItem {
        String level;
        String msg;
        String tag;
        Throwable tr;

        public QLogItem(String str, String str2, String str3, Throwable th) {
            this.tag = str;
            this.level = str2;
            this.msg = str3;
            this.tr = th;
        }
    }

    private static void addLogItem(String str, int i, String str2, Throwable th) {
        if (IMMsfCoreProxy.get().getMode() != 1 && isLogToFile) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis >= nextSecondMinuteTime) {
                checkNextMinuteTime(currentTimeMillis);
            }
            if (colorLogTime != 0 && currentTimeMillis - colorLogTime > 1800000) {
                colorLogTime = 0;
                colorTags.clear();
            }
            String str3 = logTime + DLConstants.DEPENDENCY_PACKAGE_DIV + processName + "[" + myProcessId + "]|" + String.valueOf(Thread.currentThread().getId()) + DLConstants.DEPENDENCY_PACKAGE_DIV + getReportLevel(i) + DLConstants.DEPENDENCY_PACKAGE_DIV + str + DLConstants.DEPENDENCY_PACKAGE_DIV + str2 + "\n";
            if (th != null) {
                str3 = str3 + "\n" + Log.getStackTraceString(th) + "\n";
            }
            if (addLogToCache(str3) && IMMsfCoreProxy.get().getContext() != null && System.currentTimeMillis() - lastPrintMemeoryTime > 180000) {
                lastPrintMemeoryTime = System.currentTimeMillis();
                printMemStatus();
            }
        }
    }

    private static boolean addLogToCache(String str) {
        try {
            logDeque.add(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static synchronized void checkNextMinuteTime(long j) {
        synchronized (QLogImpl.class) {
            if (j > nextSecondMinuteTime) {
                synchronized (formatterLock) {
                    logTime = MsfSdkUtils.timeFormatter.format(Long.valueOf(j));
                    nextSecondMinuteTime += 1000;
                }
            }
        }
    }

    public static void d(String str, int i, String str2) {
        d(str, i, str2, null);
    }

    public static void d(String str, int i, String str2, Throwable th) {
        if (UIN_REPORTLOG_LEVEL >= i || colorTags.contains(str)) {
            if (TIMManager.getInstance().getIsLogPrintEnabled()) {
                if (th == null) {
                    Log.d(str, "[" + getReportLevel(i) + "]" + str2);
                } else {
                    Log.d(str, "[" + getReportLevel(i) + "]" + str2, th);
                }
            }
            addLogItem(str, i, str2, th);
            if (sdkLogLister != null) {
                sdkLogLister.log(4, str, str2);
            }
        }
    }

    public static void dfile(String str, int i, String str2) {
        if (UIN_REPORTLOG_LEVEL >= i || colorTags.contains(str)) {
            addLogItem(str, i, str2, null);
            if (sdkLogLister != null) {
                sdkLogLister.log(4, str, str2);
            }
        }
    }

    public static void e(String str, int i, String str2) {
        e(str, i, str2, null);
    }

    public static void e(String str, int i, String str2, Throwable th) {
        if (UIN_REPORTLOG_LEVEL >= i || colorTags.contains(str)) {
            if (TIMManager.getInstance().getIsLogPrintEnabled()) {
                if (th == null) {
                    Log.e(str, "[" + getReportLevel(i) + "]" + str2);
                } else {
                    Log.e(str, "[" + getReportLevel(i) + "]" + str2, th);
                }
            }
            addLogItem(str, i, str2, th);
            if (sdkLogLister != null) {
                sdkLogLister.log(1, str, str2);
            }
        }
    }

    public static void efile(String str, int i, String str2) {
        if (UIN_REPORTLOG_LEVEL >= i || colorTags.contains(str)) {
            addLogItem(str, i, str2, null);
            if (sdkLogLister != null) {
                sdkLogLister.log(1, str, str2);
            }
        }
    }

    public static String getLogFileName(String str) {
        return processName.replace(":", "_") + "." + str + ".log";
    }

    public static String getLogPath() {
        return logPath;
    }

    public static String getReportLevel(int i) {
        switch (i) {
            case 1:
                return TAG_REPORTLEVEL_USER;
            case 2:
                return TAG_REPORTLEVEL_COLORUSER;
            case 4:
                return TAG_REPORTLEVEL_DEVELOPER;
            default:
                return TAG_REPORTLEVEL_USER;
        }
    }

    private static String getThisHour(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy.MM.dd.HH");
        logTime = new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(Long.valueOf(j));
        String format = simpleDateFormat.format(instance.getTime());
        setNextSecond(instance);
        setNextHour(instance);
        return format;
    }

    public static void i(String str, int i, String str2) {
        i(str, i, str2, null);
    }

    public static void i(String str, int i, String str2, Throwable th) {
        if (UIN_REPORTLOG_LEVEL >= i || colorTags.contains(str)) {
            if (TIMManager.getInstance().getIsLogPrintEnabled()) {
                if (th == null) {
                    Log.i(str, "[" + getReportLevel(i) + "]" + str2);
                } else {
                    Log.i(str, "[" + getReportLevel(i) + "]" + str2, th);
                }
            }
            addLogItem(str, i, str2, th);
            if (sdkLogLister != null) {
                sdkLogLister.log(3, str, str2);
            }
        }
    }

    public static void ifile(String str, int i, String str2) {
        if (UIN_REPORTLOG_LEVEL >= i || colorTags.contains(str)) {
            addLogItem(str, i, str2, null);
            if (sdkLogLister != null) {
                sdkLogLister.log(3, str, str2);
            }
        }
    }

    public static void init(Context context) {
        sContext = context.getApplicationContext();
        acutualInitRunnable.run();
    }

    static synchronized void initLogFile(long j) throws IOException {
        Throwable th;
        synchronized (QLogImpl.class) {
            File file;
            logPath = Environment.getExternalStorageDirectory().getPath() + "/tencent/imsdklogs/" + packageName.replace(".", "/") + "/";
            File file2 = new File(logPath);
            if (!(file2.exists() || file2.mkdirs())) {
                QLog.d(tag, 1, "create imsdklogs folder failed");
            }
            nowUsedFile = logPath + getLogFileName(getThisHour(j));
            try {
                file = new File(nowUsedFile);
                try {
                    if (file.exists()) {
                        writeAppVersion();
                        if (writer != null) {
                            writer.write(logTime + DLConstants.DEPENDENCY_PACKAGE_DIV + processName + "|E|MSF.D.QLogImpl" + DLConstants.DEPENDENCY_PACKAGE_DIV + Build.MODEL + " " + VERSION.RELEASE + "|newLogFile " + file.getName() + " is existed.\n");
                            writer.flush();
                        }
                    } else {
                        boolean createNewFile = file.createNewFile();
                        writeAppVersion();
                        if (writer != null) {
                            writer.write(logTime + DLConstants.DEPENDENCY_PACKAGE_DIV + processName + "|D|MSF.D.QLogImpl" + DLConstants.DEPENDENCY_PACKAGE_DIV + Build.MODEL + " " + VERSION.RELEASE + " create newLogFile " + file.getName() + " " + createNewFile + "\n");
                            writer.flush();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    th.printStackTrace();
                    writer = new FileWriter(file, true);
                    writeAppVersion();
                }
            } catch (Throwable th3) {
                th = th3;
                file = file2;
                th.printStackTrace();
                writer = new FileWriter(file, true);
                writeAppVersion();
            }
            writer = new FileWriter(file, true);
            writeAppVersion();
        }
    }

    private static boolean insertLogToCacheHead(String str) {
        try {
            logDeque.addFirst(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isColorUser() {
        return UIN_REPORTLOG_LEVEL > 1;
    }

    public static boolean isDEVELOPER() {
        return UIN_REPORTLOG_LEVEL >= 4;
    }

    public static void p(String str, String str2) {
        if (TIMManager.getInstance().getIsLogPrintEnabled()) {
            Log.d(tag, "[s]" + str2);
        }
    }

    private static void printMemStatus() {
        try {
            ActivityManager activityManager = (ActivityManager) IMMsfCoreProxy.get().getContext().getSystemService(Constants.FLAG_ACTIVITY_NAME);
            MemoryInfo memoryInfo = new MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            if (QLog.isColorLevel()) {
                d(MEMTag, 2, "availMem:" + ((memoryInfo.availMem / 1024) / 1024) + "M lowThreshold:" + ((memoryInfo.threshold / 1024) / 1024) + "M");
            }
        } catch (Throwable e) {
            if (QLog.isColorLevel()) {
                d(MEMTag, 2, "printMemError " + e, e);
            }
        }
    }

    private static void setNextHour(Calendar calendar) {
        calendar.add(11, 1);
        calendar.set(12, 0);
        calendar.set(13, 0);
        nextHourTime = calendar.getTimeInMillis();
    }

    private static void setNextSecond(Calendar calendar) {
        calendar.set(14, 0);
        nextSecondMinuteTime = calendar.getTimeInMillis() + 1000;
    }

    public static void setSdkLogListener(TIMLogListener tIMLogListener) {
        sdkLogLister = tIMLogListener;
    }

    public static void w(String str, int i, String str2) {
        w(str, i, str2, null);
    }

    public static void w(String str, int i, String str2, Throwable th) {
        if (UIN_REPORTLOG_LEVEL >= i || colorTags.contains(str)) {
            if (TIMManager.getInstance().getIsLogPrintEnabled()) {
                if (th == null) {
                    Log.w(str, "[" + getReportLevel(i) + "]" + str2);
                } else {
                    Log.w(str, "[" + getReportLevel(i) + "]" + str2, th);
                }
            }
            addLogItem(str, i, str2, th);
            if (sdkLogLister != null) {
                sdkLogLister.log(2, str, str2);
            }
        }
    }

    public static void wfile(String str, int i, String str2) {
        if (UIN_REPORTLOG_LEVEL >= i || colorTags.contains(str)) {
            addLogItem(str, i, str2, null);
            if (sdkLogLister != null) {
                sdkLogLister.log(2, str, str2);
            }
        }
    }

    private static void writeAppVersion() throws IOException {
        if (writer != null && !"".equals(QLog.sBuildNumber)) {
            writer.write(logTime + DLConstants.DEPENDENCY_PACKAGE_DIV + processName + "|D||QQ_Version: " + QLog.sBuildNumber + "\r\n");
            writer.flush();
        }
    }

    private static void writeLogToFile(String str) {
        try {
            if (isLogToFile && "mounted".equals(Environment.getExternalStorageState())) {
                if (writer == null) {
                    System.out.println("can not write log.");
                    long currentTimeMillis = System.currentTimeMillis();
                    if (lastWriterErrorTime == 0) {
                        lastWriterErrorTime = currentTimeMillis;
                    } else if (currentTimeMillis - lastWriterErrorTime > BuglyBroadcastRecevier.UPLOADLIMITED) {
                        try {
                            initLogFile(System.currentTimeMillis());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        lastWriterErrorTime = currentTimeMillis;
                    }
                } else {
                    long currentTimeMillis2 = System.currentTimeMillis();
                    if (currentTimeMillis2 > nextHourTime) {
                        initLogFile(currentTimeMillis2);
                    }
                    if (lock.tryLock()) {
                        writer.write(str);
                        writer.flush();
                        lock.unlock();
                    } else if (!insertLogToCacheHead(str)) {
                        Log.d("QLogImpl", "insertLogToCacheHead failed!");
                    }
                }
                isPreExceptionEnospc.compareAndSet(true, false);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}

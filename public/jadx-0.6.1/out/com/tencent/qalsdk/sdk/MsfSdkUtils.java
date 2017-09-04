package com.tencent.qalsdk.sdk;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import com.tencent.android.tpush.common.Constants;
import com.tencent.av.config.ConfigBaseParser;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.util.QLog;
import com.tencent.smtt.sdk.WebView;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MsfSdkUtils {
    private static final String[] PRIVILEGE_CMDS = new String[]{"openim.pbvideoinfo", "openim.pbvideoapp", "openim.pbtinyidtouserid", "openim.pbuseridtotinyid"};
    private static final AtomicInteger seqFactory = new AtomicInteger(new Random().nextInt(100000));
    private static final String tag = "MsfSdkUtils";
    public static final SimpleDateFormat timeFormatter = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

    public static boolean isTopActivity(Context context) {
        if (context == null) {
            return false;
        }
        try {
            if (((RunningTaskInfo) ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningTasks(1).get(0)).topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isScreenOn(Context context) {
        if (context == null) {
            return false;
        }
        try {
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            return ((Boolean) powerManager.getClass().getMethod("isScreenOn", new Class[0]).invoke(powerManager, (Object[]) null)).booleanValue();
        } catch (Exception e) {
            QLog.d(tag, 1, "e = " + e.toString());
            return true;
        }
    }

    public static String getProcessName(Context context) {
        Object obj;
        if (context == null) {
            return ConfigBaseParser.DEFAULT_VALUE;
        }
        Object obj2 = null;
        try {
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses();
            try {
                for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo != null && runningAppProcessInfo.pid == Process.myPid()) {
                        return runningAppProcessInfo.processName;
                    }
                }
            } catch (Exception e) {
                List list = runningAppProcesses;
                QLog.d(tag, 1, "getProcessName error " + obj);
                return ConfigBaseParser.DEFAULT_VALUE;
            }
        } catch (Exception e2) {
            obj = obj2;
            QLog.d(tag, 1, "getProcessName error " + obj);
            return ConfigBaseParser.DEFAULT_VALUE;
        }
        return ConfigBaseParser.DEFAULT_VALUE;
    }

    public static boolean isMainProcess(Context context) {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses();
        String packageName = context.getPackageName();
        int myPid = Process.myPid();
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == myPid && packageName.equals(runningAppProcessInfo.processName)) {
                return true;
            }
        }
        return false;
    }

    public static synchronized int getNextAppSeq() {
        int incrementAndGet;
        synchronized (MsfSdkUtils.class) {
            incrementAndGet = seqFactory.incrementAndGet();
            if (incrementAndGet > 1000000) {
                seqFactory.set(new Random().nextInt(100000));
            }
            if (incrementAndGet == 50000) {
                incrementAndGet += Constants.ERRORCODE_UNKNOWN;
            }
        }
        return incrementAndGet;
    }

    public static boolean killProcess(Context context, String str) {
        if (context == null || str == null) {
            return false;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.equals(str)) {
                Process.killProcess(runningAppProcessInfo.pid);
                return true;
            }
        }
        return false;
    }

    public static FromServiceMsg constructResponse(String str, String str2, int i, int i2, String str3, Object obj, int i3) {
        FromServiceMsg fromServiceMsg = new FromServiceMsg(str, str2);
        fromServiceMsg.setAppId(i);
        if (i2 != 1000) {
            fromServiceMsg.setBusinessFail(i2, str3);
        } else {
            fromServiceMsg.setMsgSuccess();
        }
        if (obj != null) {
            fromServiceMsg.addAttribute(str2, obj);
        }
        fromServiceMsg.setRequestSsoSeq(i3);
        return fromServiceMsg;
    }

    public static void addFromMsgProcessName(String str, FromServiceMsg fromServiceMsg) {
        if (fromServiceMsg != null) {
            fromServiceMsg.getAttributes().put(v.c, str);
        }
    }

    public static void addToMsgProcessName(String str, ToServiceMsg toServiceMsg) {
        if (toServiceMsg != null) {
            toServiceMsg.getAttributes().put(v.c, str);
        }
    }

    public static byte[] convertInt2Bytes(int i) {
        return new byte[]{(byte) ((i >>> 24) & 255), (byte) ((i >>> 16) & 255), (byte) ((i >>> 8) & 255), (byte) (i & 255)};
    }

    public static int convertBytes2Int(byte[] bArr) {
        return ((((bArr[0] << 24) & WebView.NIGHT_MODE_COLOR) | ((bArr[1] << 16) & 16711680)) | ((bArr[2] << 8) & 65280)) | ((bArr[3] << 0) & 255);
    }

    public static int convertBytes2Int(byte[] bArr, int i) {
        return ((((bArr[i + 0] << 24) & WebView.NIGHT_MODE_COLOR) | ((bArr[i + 1] << 16) & 16711680)) | ((bArr[i + 2] << 8) & 65280)) | ((bArr[i + 3] << 0) & 255);
    }

    public static void saveConfig(String str, Properties properties) throws Exception {
        Throwable th;
        if (str != null && properties != null) {
            FileOutputStream fileOutputStream;
            try {
                fileOutputStream = new FileOutputStream(str, false);
                try {
                    properties.store(fileOutputStream, "");
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        }
    }

    public static Properties loadConfig(String str) throws Exception {
        Throwable th;
        Properties properties = new Properties();
        if (str != null) {
            FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(str);
                try {
                    properties.load(fileInputStream);
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        }
        return properties;
    }

    public static FromServiceMsg constructResponse(ToServiceMsg toServiceMsg, int i, String str, Object obj) {
        if (toServiceMsg == null) {
            return new FromServiceMsg();
        }
        FromServiceMsg constructResponse = constructResponse(toServiceMsg.getUin(), toServiceMsg.getServiceCmd(), toServiceMsg.getAppId(), i, str, obj, toServiceMsg.getRequestSsoSeq());
        constructResponse.setAppSeq(toServiceMsg.getAppSeq());
        return constructResponse;
    }

    public static String getShortUin(String str) {
        return (TextUtils.isEmpty(str) || str.length() < 4) ? str : v.n + str.substring(str.length() - 4, str.length());
    }

    public static String insertMtype(String str, String str2) {
        if (str2.contains("mType=") || str == null) {
            return str2;
        }
        int indexOf = str2.indexOf("?");
        if (indexOf > 0) {
            if (str2.length() == indexOf + 1) {
                return str2.substring(0, indexOf + 1) + "mType=" + str;
            }
            String str3;
            indexOf = str2.indexOf("#", indexOf);
            if (indexOf > -1) {
                str3 = str2.substring(0, indexOf) + "&mType=" + str + str2.substring(indexOf);
            } else {
                str3 = str2 + "&mType=" + str;
            }
            return str3;
        } else if (str2.length() > 0) {
            return str2 + "?mType=" + str;
        } else {
            return str2;
        }
    }

    public static boolean isPrivilegeCMD(String str) {
        if (str == null) {
            return false;
        }
        for (Object equals : PRIVILEGE_CMDS) {
            if (str.equals(equals)) {
                return true;
            }
        }
        return false;
    }
}

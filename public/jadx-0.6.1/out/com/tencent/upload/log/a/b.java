package com.tencent.upload.log.a;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Process;
import com.tencent.android.tpush.common.Constants;
import java.util.List;

public final class b {
    private static volatile String a;
    private static final Object b = new Object();
    private static volatile Boolean c;
    private static final Object d = new Object();

    public static String a(Context context) {
        if (a != null) {
            return a;
        }
        synchronized (b) {
            if (a != null) {
                String str = a;
                return str;
            }
            int myPid = Process.myPid();
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses();
            if (runningAppProcesses != null && runningAppProcesses.size() > 0) {
                for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo != null && runningAppProcessInfo.pid == myPid) {
                        str = runningAppProcessInfo.processName;
                        break;
                    }
                }
            }
            str = null;
            a = str;
            return str;
        }
    }

    public static boolean b(Context context) {
        if (c != null) {
            return c.booleanValue();
        }
        synchronized (d) {
            if (c != null) {
                boolean booleanValue = c.booleanValue();
                return booleanValue;
            }
            String a = a(context);
            if (a == null) {
                return false;
            }
            Boolean valueOf = Boolean.valueOf(a.equals(context.getApplicationInfo().processName));
            c = valueOf;
            booleanValue = valueOf.booleanValue();
            return booleanValue;
        }
    }
}

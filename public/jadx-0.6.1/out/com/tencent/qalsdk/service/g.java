package com.tencent.qalsdk.service;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.common.Constants;
import com.tencent.qalsdk.base.a;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.core.o;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.tencent.qalsdk.sdk.v;
import com.tencent.qalsdk.util.QLog;
import java.util.List;
import tencent.tls.platform.SigType;

/* compiled from: MsfServiceUtil */
public class g {
    public static final String a = "MSF.S.Util";

    public static void a(Context context, String str, String str2, String str3, int i) {
        Intent intent = new Intent(str3);
        intent.putExtra("uin", str2);
        intent.putExtra("istatus", i);
        intent.putExtra("gatewayip", o.m());
        try {
            if (str3.toLowerCase().indexOf(Constants.FLAG_ACTIVITY_NAME, 0) != -1) {
                intent.setFlags(SigType.TLS);
                context.startActivity(intent);
            }
            intent.setFlags(32);
            context.sendBroadcast(intent);
        } catch (Exception e) {
            QLog.d(a, 1, "sendBootAction " + e);
        }
        QLog.d(a, 1, "send bootAction " + str3 + " for " + str + " uin:" + MsfSdkUtils.getShortUin(str2) + " istatus:" + i);
    }

    public static void a(Context context, String str, String str2, String str3, int i, String str4, byte[] bArr) {
        Intent intent = new Intent(str3);
        intent.putExtra(a.ck, str2);
        intent.putExtra("istatus", i);
        intent.putExtra("gatewayip", o.m());
        intent.putExtra(a.cl, str4);
        intent.putExtra(a.cm, bArr);
        try {
            if (str3.toLowerCase().indexOf(Constants.FLAG_ACTIVITY_NAME, 0) != -1) {
                intent.setFlags(SigType.TLS);
                context.startActivity(intent);
            }
            intent.setFlags(32);
            context.sendBroadcast(intent);
        } catch (Exception e) {
            QLog.d(a, 1, "sendBootAction " + e);
        }
        QLog.d(a, 1, "send bootAction " + str3 + " for " + str + " uin:" + str2 + " istatus:" + i);
    }

    public static FromServiceMsg a(ToServiceMsg toServiceMsg) {
        FromServiceMsg fromServiceMsg = new FromServiceMsg(toServiceMsg.getUin(), toServiceMsg.getServiceCmd());
        fromServiceMsg.setAppId(toServiceMsg.getAppId());
        fromServiceMsg.setAppSeq(toServiceMsg.getAppSeq());
        fromServiceMsg.setRequestSsoSeq(toServiceMsg.getRequestSsoSeq());
        fromServiceMsg.setMsfCommand(toServiceMsg.getMsfCommand());
        MsfSdkUtils.addFromMsgProcessName(b(toServiceMsg), fromServiceMsg);
        return fromServiceMsg;
    }

    public static String b(ToServiceMsg toServiceMsg) {
        if (toServiceMsg == null || toServiceMsg.getAttributes().get(v.c) == null) {
            return "";
        }
        return (String) toServiceMsg.getAttributes().get(v.c);
    }

    public static String a(FromServiceMsg fromServiceMsg) {
        if (fromServiceMsg.getAttributes().get(v.c) != null) {
            return (String) fromServiceMsg.getAttributes().get(v.c);
        }
        return "";
    }

    public static boolean a() {
        try {
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) QalService.context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses();
            if (runningAppProcesses != null) {
                Object substring;
                String processName = MsfSdkUtils.getProcessName(QalService.context);
                if (processName == null || processName.indexOf(":") <= 0) {
                    String str = processName;
                } else {
                    substring = processName.substring(0, processName.indexOf(":"));
                }
                for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo.processName.equals(substring)) {
                        return true;
                    }
                }
            } else if (QLog.isColorLevel()) {
                QLog.d(a, 2, "can not load appProcesses.");
            }
            return false;
        } catch (Exception e) {
            QLog.d(a, 1, "check isMainProcessRunning error " + e);
            return false;
        }
    }

    public static String b() {
        b a = c.a(QalService.context.getPackageName());
        String str = "com.tencent.qalsdk.broadcast.qal";
        if (a != null) {
            return a.b();
        }
        return str;
    }
}

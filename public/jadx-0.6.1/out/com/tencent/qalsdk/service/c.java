package com.tencent.qalsdk.service;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import com.dynamicload.Lib.DLConstants;
import com.tencent.android.tpush.common.Constants;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.IMsfServiceCallbacker;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.core.j;
import com.tencent.qalsdk.core.l;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.tencent.qalsdk.sdk.v;
import com.tencent.qalsdk.sdk.w;
import com.tencent.qalsdk.util.QLog;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: AppProcessManager */
public class c {
    static final String a = "MSF.S.AppProcessManager";
    public static final String b = "app_process_info_";
    static ConcurrentHashMap<String, b> c = new ConcurrentHashMap();
    static a d = new a();
    static j e;

    public static void a(Context context, j jVar) {
        e = jVar;
        d.setName("MsfServiceAppMsgHandler");
        d.start();
        try {
            a(context);
        } catch (UnsatisfiedLinkError e) {
            QLog.e(a, "so init error:" + e.getMessage());
        }
    }

    public static void a(String str, String str2, IMsfServiceCallbacker iMsfServiceCallbacker, int i) {
        if (str.equals(QalService.getProccessName())) {
            QLog.e(a, "WARNING, bind self:" + str);
        }
        if (!c.containsKey(str)) {
            c.putIfAbsent(str, new b());
            if (QLog.isColorLevel()) {
                QLog.d(a, 2, "add bootName " + str);
            }
        }
        ((b) c.get(str)).a(str, str2, iMsfServiceCallbacker);
        try {
            l.a().setConfig(b + str, ((b) c.get(str)).a());
        } catch (UnsatisfiedLinkError e) {
            QLog.e(a, "onRegisterApp exception: setConfig not find" + e.getMessage());
        }
        QLog.i(a, "onRegisterApp: " + str);
        d.a();
    }

    public static b a(String str) {
        return (b) c.get(str);
    }

    public static boolean b(String str) {
        b bVar = (b) c.get(str);
        if (bVar == null || bVar.c() == null) {
            return true;
        }
        if (QLog.isColorLevel()) {
            QLog.d(a, 2, "process " + str + " also registed,can not unregister by proxy.");
        }
        return false;
    }

    public static void a(String str, Boolean bool) {
        b bVar = (b) c.get(str);
        if (bVar != null) {
            bVar.d();
        }
        if (bool.booleanValue()) {
            bVar = (b) c.remove(str);
            l.a().removeConfig(b + str);
            if (QLog.isColorLevel()) {
                QLog.d(a, 2, "remove process :" + str + " " + (bVar == null ? "failed" : "succ"));
            }
        } else if (QLog.isColorLevel()) {
            QLog.d(a, 2, "unregister process :" + str + " " + (bVar == null ? "failed" : "succ"));
        }
    }

    public static void a(Context context) {
        String[] configList = l.a().getConfigList(b);
        QLog.d(a, "start loadAppProcessInfos:" + configList.length);
        for (String str : configList) {
            QLog.d(a, "loadAppProcessInfo proInfo:" + str);
            String[] split = str.split(",");
            String str2 = split[0];
            String str3 = "";
            if (split.length > 1) {
                str3 = split[1];
            }
            a(str2, str3, null, 0);
        }
    }

    public static void a(HashMap<String, String> hashMap) {
        ActivityManager activityManager = (ActivityManager) QalService.context.getSystemService(Constants.FLAG_ACTIVITY_NAME);
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        hashMap.put("availMem", String.valueOf(memoryInfo.availMem));
        hashMap.put("lowMemory", String.valueOf(memoryInfo.lowMemory));
    }

    public static void a(String str, ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) {
        if (str == null || str.length() == 0) {
            if (QLog.isColorLevel()) {
                QLog.e(a, 2, "find null processName msg to app " + toServiceMsg + " " + fromServiceMsg);
            }
            if (fromServiceMsg != null && fromServiceMsg.getServiceCmd() != null && !fromServiceMsg.getServiceCmd().equals("SharpSvr.s2c")) {
                return;
            }
            return;
        }
        if (str.equals(v.n)) {
            for (Entry entry : c.entrySet()) {
                MsfSdkUtils.addFromMsgProcessName((String) entry.getKey(), fromServiceMsg);
                ((b) entry.getValue()).g.add(new w(toServiceMsg, fromServiceMsg));
            }
        } else {
            b bVar = (b) c.get(str);
            if (bVar != null) {
                if (MsfSdkUtils.isPrivilegeCMD(fromServiceMsg.getServiceCmd())) {
                    bVar.g.a(new w(toServiceMsg, fromServiceMsg));
                    QLog.d(a, "service add queue first:" + System.currentTimeMillis() + DLConstants.DEPENDENCY_PACKAGE_DIV + fromServiceMsg.getServiceCmd() + DLConstants.DEPENDENCY_PACKAGE_DIV + fromServiceMsg.getRequestSsoSeq());
                } else {
                    bVar.g.b(new w(toServiceMsg, fromServiceMsg));
                    QLog.d(a, "service add queue last:" + System.currentTimeMillis() + DLConstants.DEPENDENCY_PACKAGE_DIV + fromServiceMsg.getServiceCmd() + DLConstants.DEPENDENCY_PACKAGE_DIV + fromServiceMsg.getRequestSsoSeq());
                }
            } else if (fromServiceMsg == null || fromServiceMsg.getServiceCmd() == null || !fromServiceMsg.getServiceCmd().equals("SharpSvr.s2c")) {
                QLog.e(a, 1, "can not find " + str + " to receive msg to:" + toServiceMsg + " from:" + fromServiceMsg);
            } else {
                QLog.e(a, 1, "can not find " + str + " to receive msg to:" + toServiceMsg + " from:" + fromServiceMsg);
            }
        }
        d.a();
    }
}

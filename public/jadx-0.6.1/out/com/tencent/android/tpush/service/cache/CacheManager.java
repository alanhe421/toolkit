package com.tencent.android.tpush.service.cache;

import android.content.Context;
import com.dynamicload.Lib.DLConstants;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.k;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.horse.data.OptStrategyList;
import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.service.channel.exception.NullReturnException;
import com.tencent.android.tpush.service.channel.protocol.AppInfo;
import com.tencent.android.tpush.service.channel.protocol.UnregInfo;
import com.tencent.android.tpush.service.d.f;
import com.tencent.android.tpush.service.m;
import com.tencent.android.tpush.stat.b.c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* compiled from: ProGuard */
public class CacheManager {
    private CacheManager() {
    }

    public static void addRegisterInfos(String str) {
        try {
            Context e = m.e();
            String a = f.a(e, c("tpush_reginfos", ".reg"), true);
            if (f.a(a)) {
                a = Rijndael.encrypt(str + ",");
            } else {
                String decrypt = Rijndael.decrypt(a);
                if (!decrypt.contains(str)) {
                    a = Rijndael.encrypt(decrypt + str + ",");
                }
            }
            f.a(e, c("tpush_reginfos", ".reg"), a, true);
        } catch (Throwable e2) {
            a.c(Constants.ServiceLogTag, "", e2);
        }
    }

    public static void removeRegisterInfos(String str) {
        try {
            Context e = m.e();
            String a = f.a(e, c("tpush_reginfos", ".reg"), true);
            if (!f.a(a)) {
                String decrypt = Rijndael.decrypt(a);
                if (decrypt.contains(str + ",")) {
                    a = Rijndael.encrypt(decrypt.replaceAll(str + ",", ""));
                }
            }
            f.a(e, c("tpush_reginfos", ".reg"), a, true);
        } catch (Throwable e2) {
            a.c(Constants.ServiceLogTag, "", e2);
        }
    }

    public static List getRegisterInfos(Context context) {
        List arrayList;
        try {
            String decrypt = Rijndael.decrypt(f.a(context, c("tpush_reginfos", ".reg"), true));
            if (f.a(decrypt)) {
                arrayList = new ArrayList();
            } else {
                arrayList = new ArrayList(Arrays.asList(decrypt.split(",")));
            }
        } catch (Throwable e) {
            a.c(Constants.ServiceLogTag, "", e);
            arrayList = new ArrayList();
        }
        if (!arrayList.contains(context.getPackageName())) {
            arrayList.add(context.getPackageName());
        }
        return arrayList;
    }

    public static boolean addRegisterInfo(com.tencent.android.tpush.data.a aVar) {
        return a(aVar, c(aVar.d, ".reg"), 0);
    }

    public static List getRegisterInfo(Context context) {
        if (context == null) {
            return null;
        }
        List<String> registerInfos = getRegisterInfos(context);
        if (registerInfos == null || registerInfos.size() <= 0) {
            return null;
        }
        List arrayList = new ArrayList();
        for (String registerInfoByPkgName : registerInfos) {
            com.tencent.android.tpush.data.a registerInfoByPkgName2 = getRegisterInfoByPkgName(registerInfoByPkgName);
            if (registerInfoByPkgName2 != null && registerInfoByPkgName2.e < 4) {
                arrayList.add(registerInfoByPkgName2);
            }
        }
        return arrayList;
    }

    public static List getUnregisterInfo(Context context) {
        if (context == null) {
            return null;
        }
        List<String> registerInfos = getRegisterInfos(context);
        if (registerInfos == null || registerInfos.size() <= 0) {
            return null;
        }
        List arrayList = new ArrayList();
        for (String registerInfoByPkgName : registerInfos) {
            com.tencent.android.tpush.data.a registerInfoByPkgName2 = getRegisterInfoByPkgName(registerInfoByPkgName);
            if (registerInfoByPkgName2 != null && registerInfoByPkgName2.e > 0 && registerInfoByPkgName2.e < 2) {
                arrayList.add(registerInfoByPkgName2);
            }
        }
        return arrayList;
    }

    public static List getUninstallInfo(Context context) {
        if (context == null) {
            return null;
        }
        List<String> registerInfos = getRegisterInfos(context);
        if (registerInfos == null || registerInfos.size() <= 0) {
            return null;
        }
        List arrayList = new ArrayList();
        for (String registerInfoByPkgName : registerInfos) {
            com.tencent.android.tpush.data.a registerInfoByPkgName2 = getRegisterInfoByPkgName(registerInfoByPkgName);
            if (registerInfoByPkgName2 != null && registerInfoByPkgName2.e > 1 && registerInfoByPkgName2.e < 3) {
                arrayList.add(registerInfoByPkgName2);
            }
        }
        return arrayList;
    }

    public static ArrayList getUninstallAndUnregisterInfo(Context context) {
        if (context == null) {
            return null;
        }
        List<String> registerInfos = getRegisterInfos(context);
        if (registerInfos == null || registerInfos.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String registerInfoByPkgName : registerInfos) {
            com.tencent.android.tpush.data.a registerInfoByPkgName2 = getRegisterInfoByPkgName(registerInfoByPkgName);
            if (registerInfoByPkgName2 != null && registerInfoByPkgName2.e > 0 && registerInfoByPkgName2.e < 3) {
                UnregInfo unregInfo = new UnregInfo();
                unregInfo.appInfo = new AppInfo(registerInfoByPkgName2.a, registerInfoByPkgName2.b, f.c(registerInfoByPkgName2.d), (byte) 0);
                unregInfo.isUninstall = (byte) registerInfoByPkgName2.e;
                unregInfo.timestamp = registerInfoByPkgName2.f;
                arrayList.add(unregInfo);
            }
        }
        return arrayList;
    }

    public static com.tencent.android.tpush.data.a getRegisterInfoByPkgName(String str) {
        return a(str, c(str, ".reg"));
    }

    public static boolean UnregisterInfoByPkgName(String str) {
        if (m.e() == null || f.a(str)) {
            a.h(Constants.ServiceLogTag, ">>> unregister registerInfo : " + str + " failed!");
            return false;
        }
        a(str, c(str, ".reg"), 1);
        return true;
    }

    public static boolean UnregisterInfoSuccessByPkgName(String str) {
        if (m.e() == null || f.a(str)) {
            a.h(Constants.ServiceLogTag, ">>> unregister registerInfo : " + str + " failed!");
            return false;
        }
        a(str, c(str, ".reg"), 3);
        return true;
    }

    public static boolean UninstallInfoByPkgName(String str) {
        if (m.e() == null || f.a(str)) {
            a.h(Constants.ServiceLogTag, ">>> uninstall registerInfo : " + str + " failed!");
            return false;
        }
        a(str, c(str, ".reg"), 2);
        return true;
    }

    public static boolean UninstallInfoSuccessByPkgName(String str) {
        if (m.e() == null || f.a(str)) {
            a.h(Constants.ServiceLogTag, ">>> uninstall registerInfo : " + str + " failed!");
            return false;
        }
        a(str, c(str, ".reg"), 4);
        return true;
    }

    public static void removeRegisterInfoByPkgName(String str) {
        a(str);
    }

    private static boolean a(com.tencent.android.tpush.data.a aVar, String str, int i) {
        if (!(m.e() == null || aVar == null)) {
            String encrypt = Rijndael.encrypt(aVar.a + DLConstants.DEPENDENCY_PACKAGE_DIV + aVar.b + DLConstants.DEPENDENCY_PACKAGE_DIV + aVar.c + DLConstants.DEPENDENCY_PACKAGE_DIV + i + DLConstants.DEPENDENCY_PACKAGE_DIV + System.currentTimeMillis());
            if (!f.a(encrypt)) {
                f.a(m.e(), str, encrypt, true);
                return true;
            }
        }
        a.h(Constants.ServiceLogTag, ">> add registerInfo failed!");
        return false;
    }

    private static com.tencent.android.tpush.data.a a(String str, String str2) {
        if (m.e() == null || f.a(str)) {
            return null;
        }
        return b(f.a(m.e(), str2, true), str);
    }

    private static void a(String str, String str2, int i) {
        com.tencent.android.tpush.data.a a = a(str, c(str, ".reg"));
        if (a != null) {
            a(a, str2, i);
        }
    }

    private static void a(String str) {
        if (m.e() != null) {
            f.a(m.e(), c(str, ".reg"), "", true);
        }
    }

    private static com.tencent.android.tpush.data.a b(String str, String str2) {
        try {
            String decrypt = Rijndael.decrypt(str);
            if (f.a(decrypt)) {
                return null;
            }
            String[] split = decrypt.split("\\|");
            if (split.length < 5) {
                return null;
            }
            com.tencent.android.tpush.data.a aVar = new com.tencent.android.tpush.data.a();
            aVar.a = Long.parseLong(split[0]);
            aVar.b = split[1];
            aVar.c = split[2];
            aVar.e = Integer.parseInt(split[3]);
            aVar.f = Long.parseLong(split[4]);
            aVar.d = str2;
            return aVar;
        } catch (Throwable e) {
            a.c(Constants.ServiceLogTag, "", e);
            return null;
        }
    }

    public static void updateUnregUninList(Context context, ArrayList arrayList) {
        if (context != null && arrayList != null && arrayList.size() > 0) {
            List unregisterInfo = getUnregisterInfo(context);
            List<com.tencent.android.tpush.data.a> uninstallInfo = getUninstallInfo(context);
            if (unregisterInfo != null) {
                for (int i = 0; i < arrayList.size(); i++) {
                    com.tencent.android.tpush.data.a aVar;
                    UnregInfo unregInfo = (UnregInfo) arrayList.get(i);
                    if (unregInfo.isUninstall == (byte) 1) {
                        for (int i2 = 0; i2 < unregisterInfo.size(); i2++) {
                            aVar = (com.tencent.android.tpush.data.a) unregisterInfo.get(i2);
                            if (aVar.a == unregInfo.appInfo.accessId) {
                                a(aVar.d, c(aVar.d, ".reg"), 3);
                            }
                        }
                    }
                    if (unregInfo.isUninstall == (byte) 2) {
                        for (com.tencent.android.tpush.data.a aVar2 : uninstallInfo) {
                            if (aVar2.a == unregInfo.appInfo.accessId) {
                                a(aVar2.d, c(aVar2.d, ".reg"), 4);
                            }
                        }
                    }
                }
            }
        }
    }

    public static String getToken(Context context) {
        return c.a(context);
    }

    public static boolean setToken(Context context, String str) {
        if (context == null || f.a(str) || str.equals(getToken(context))) {
            return false;
        }
        c.a(context, str);
        return true;
    }

    public static String getQua(Context context, long j) {
        String str = "";
        if (context != null) {
            return Rijndael.decrypt(f.a(context, ".com.tencent.tpush.cache.qua." + j, true));
        }
        return str;
    }

    public static boolean setQua(Context context, long j, String str) {
        if (context == null || f.a(str) || j <= 0) {
            return false;
        }
        return f.a(context, ".com.tencent.tpush.cache.qua." + j, Rijndael.encrypt(str), true);
    }

    public static synchronized boolean addOptStrategyList(Context context, String str, OptStrategyList optStrategyList) {
        boolean z = false;
        synchronized (CacheManager.class) {
            if (!(context == null || str == null)) {
                addOptKey(context, str);
                String str2 = str + ".com.tencent.tpush.cache.redirect";
                try {
                    optStrategyList.a(System.currentTimeMillis());
                    z = f.a(context, str2, Rijndael.encrypt(k.a(optStrategyList)), true);
                } catch (Throwable e) {
                    a.c(Constants.ServiceLogTag, "", e);
                }
            }
        }
        return z;
    }

    public static synchronized void removeOptStrategyList(Context context, String str) {
        synchronized (CacheManager.class) {
            addOptStrategyList(context, str, new OptStrategyList());
        }
    }

    public static OptStrategyList getOptStrategyList(Context context, String str) {
        boolean z = true;
        if (context == null || str == null) {
            try {
                StringBuffer stringBuffer = new StringBuffer("getStrategy return null,contex is null(");
                if (context != null) {
                    z = false;
                }
                throw new NullReturnException(stringBuffer.append(z).append(") and key=").append(str).toString());
            } catch (Exception e) {
                throw new NullReturnException("getOptStrategyList return null,deserialize err", e);
            }
        }
        Object a = k.a(Rijndael.decrypt(f.a(context, str + ".com.tencent.tpush.cache.redirect", true)));
        if (a instanceof OptStrategyList) {
            return (OptStrategyList) a;
        }
        throw new NullReturnException("getStrategy return null, because serializer object is not instanceof OptStrategyList");
    }

    public static synchronized boolean addOptStrategy(StrategyItem strategyItem) {
        boolean addOptStrategyList;
        synchronized (CacheManager.class) {
            OptStrategyList optStrategyList;
            String h = f.h(m.e());
            try {
                optStrategyList = getOptStrategyList(m.e(), h);
            } catch (Throwable e) {
                a.c(Constants.ServiceLogTag, ">> Can not get OptStrategyList from local", e);
                optStrategyList = new OptStrategyList();
            }
            if (strategyItem.d() == 1) {
                if (strategyItem.f() == 0) {
                    optStrategyList.d(strategyItem);
                } else {
                    optStrategyList.c(strategyItem);
                }
            } else if (strategyItem.f() == 0) {
                optStrategyList.b(strategyItem);
            } else {
                optStrategyList.a(strategyItem);
            }
            addOptStrategyList = addOptStrategyList(m.e(), h, optStrategyList);
        }
        return addOptStrategyList;
    }

    public static boolean addServerItems(Context context, String str, ArrayList arrayList) {
        boolean z = false;
        if (!(context == null || str == null)) {
            saveDomainKey(context, str);
            try {
                z = f.a(context, str + ".com.tencent.tpush.cache.server", Rijndael.encrypt(k.a(arrayList)), true);
            } catch (Throwable e) {
                a.c(Constants.ServiceLogTag, "", e);
            }
        }
        return z;
    }

    public static ArrayList getServerItems(Context context, String str) {
        if (str == null) {
            throw new NullReturnException("getServerItems return null,because key is null");
        }
        try {
            Object a = k.a(Rijndael.decrypt(f.a(context, str + ".com.tencent.tpush.cache.server", true)));
            if (a instanceof ArrayList) {
                return (ArrayList) a;
            }
            throw new NullReturnException("getServerItems return null,because object not instance of Arraylist<?>");
        } catch (Exception e) {
            throw new NullReturnException("getServerItem return null,deseriallize err", e);
        }
    }

    public static void addOptKeyList(Context context, HashSet hashSet) {
        if (context != null) {
            try {
                f.a(context, ".com.tencent.tpush.cache.keylist", Rijndael.encrypt(k.a(hashSet)), true);
            } catch (Throwable e) {
                a.c(Constants.ServiceLogTag, "", e);
            }
        }
    }

    public static void addOptKey(Context context, String str) {
        HashSet optKeyList;
        try {
            optKeyList = getOptKeyList(context);
        } catch (Exception e) {
            optKeyList = new HashSet();
        }
        optKeyList.add(str);
        addOptKeyList(context, optKeyList);
    }

    public static HashSet getOptKeyList(Context context) {
        if (context == null) {
            throw new NullReturnException("getOptKeyList return null,because ctx is null");
        }
        try {
            Object a = k.a(Rijndael.decrypt(f.a(context, ".com.tencent.tpush.cache.keylist", true)));
            if (a instanceof HashSet) {
                return (HashSet) a;
            }
            throw new NullReturnException("getOptKeyList return null,because object not instance of ArrayList<?>");
        } catch (Exception e) {
            throw new NullReturnException("getOptKeyList return null，deseriallize err", e);
        }
    }

    public static void clearOptKeyList(Context context) {
        if (context != null) {
            f.a(context, ".com.tencent.tpush.cache.keylist", "", true);
        }
    }

    public static boolean saveLoadIpTime(Context context, long j) {
        if (context == null || j <= 0) {
            return false;
        }
        return f.b(context, ".com.tencent.tpush.cache.load.ip.last.time", j);
    }

    public static long getLastLoadIpTime(Context context) {
        if (context != null) {
            return f.c(context, ".com.tencent.tpush.cache.load.ip.last.time", 0);
        }
        return 0;
    }

    public static void saveSpeedTestList(Context context, ArrayList arrayList) {
        if (context != null) {
            try {
                f.a(context, ".com.tencent.tpush.cache.speed.test", Rijndael.encrypt(k.a(arrayList)), true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList getSpeedTestList(Context context) {
        if (context == null) {
            throw new NullReturnException("getSpeedTestList return null ,because ctx is null");
        }
        try {
            Object a = k.a(Rijndael.decrypt(f.a(context, ".com.tencent.tpush.cache.speed.test", true)));
            if (a instanceof ArrayList) {
                return (ArrayList) a;
            }
            throw new NullReturnException("getSpeedTestList return null ,because instanceof err");
        } catch (Exception e) {
            throw new NullReturnException("getSpeedTestList return null ,because deserialize err", e);
        }
    }

    public static boolean setTestSpeedTime(Context context, long j) {
        if (context == null || j <= 0) {
            return false;
        }
        return f.b(context, "Channel.SpeedDetector.LastActivationTimestamp", j);
    }

    public static long getTestSpeedTime(Context context) {
        if (context != null) {
            return f.c(context, "Channel.SpeedDetector.LastActivationTimestamp", 0);
        }
        return 0;
    }

    public static void saveDomain(Context context, String str) {
        if (context != null) {
            f.a(context, ".com.tencent.tpush.cache.domain", str, true);
        }
    }

    public static String getDomain(Context context) {
        if (context != null) {
            return f.a(context, ".com.tencent.tpush.cache.domain", true);
        }
        return "";
    }

    public static void saveDomainKeyList(Context context, ArrayList arrayList) {
        if (context != null) {
            try {
                String str = "";
                if (arrayList != null) {
                    str = k.a(arrayList);
                }
                f.a(context, ".com.tencent.tpush.cache.domain.key", Rijndael.encrypt(str), true);
            } catch (Throwable e) {
                a.c(Constants.ServiceLogTag, "", e);
            }
        }
    }

    public static void saveDomainKey(Context context, String str) {
        if (context != null) {
            ArrayList domainKeyList;
            try {
                domainKeyList = getDomainKeyList(context);
            } catch (Exception e) {
                domainKeyList = new ArrayList();
            }
            domainKeyList.add(str);
            saveDomainKeyList(context, domainKeyList);
        }
    }

    public static ArrayList getDomainKeyList(Context context) {
        if (context == null) {
            throw new NullReturnException("getDomainKeyList return null,because ctx is null");
        }
        try {
            Object a = k.a(Rijndael.decrypt(f.a(context, ".com.tencent.tpush.cache.domain.key", true)));
            if (a instanceof ArrayList) {
                return (ArrayList) a;
            }
            throw new NullReturnException("getDomainKeyList return null,because object not instance of ArrayList<?>");
        } catch (Exception e) {
            throw new NullReturnException("getDomainKeyList return null，deseriallize err", e);
        }
    }

    public static void clearDomainServerItem(Context context) {
        ArrayList domainKeyList;
        try {
            domainKeyList = getDomainKeyList(context);
        } catch (NullReturnException e) {
            domainKeyList = new ArrayList();
        }
        domainKeyList.add(String.valueOf(3));
        domainKeyList.add(String.valueOf(1));
        domainKeyList.add(String.valueOf(2));
        Iterator it = domainKeyList.iterator();
        while (it.hasNext()) {
            try {
                f.a(context, ((String) it.next()) + ".com.tencent.tpush.cache.server", "", true);
            } catch (Throwable e2) {
                a.c(Constants.ServiceLogTag, "", e2);
            }
        }
    }

    private static String c(String str, String str2) {
        return str + ".com.tencent.tpush.cache" + str2;
    }
}

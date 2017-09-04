package com.tencent.android.tpush.service;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.Settings.System;
import com.dynamicload.Lib.DLConstants;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.pay.http.APPluginErrorCode;
import com.tencent.android.tpush.XGPush4Msdk;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.p;
import com.tencent.android.tpush.encrypt.a;
import com.tencent.android.tpush.service.a.c;
import com.tencent.android.tpush.service.a.d;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.d.f;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/* compiled from: ProGuard */
public class XGWatchdog {
    public static Integer CURRENT_WD_VERSION = Integer.valueOf(2);
    private static final String LIB_FULL_NAME = "libxguardian.so";
    private static final String LIB_NAME = "xguardian";
    public static final String TAG = "xguardian";
    private static String WatchdogPath = "";
    private static int defaultWatchdogPort = 55550;
    private static Handler handler = null;
    private static volatile XGWatchdog instance = null;
    private static Random random = new Random();
    private static final String watchdogPortName = a.a("com.tencent.tpnsWatchdogPort");
    private Context context = null;
    volatile boolean isStarted = false;

    private XGWatchdog(Context context) {
        try {
            this.context = context.getApplicationContext();
            m.c(this.context);
            HandlerThread handlerThread = new HandlerThread("XGWatchdog.thread");
            handlerThread.start();
            handler = new Handler(handlerThread.getLooper());
        } catch (Throwable th) {
            com.tencent.android.tpush.a.a.c("xguardian", "init XGWatchdog error", th);
        }
    }

    public static XGWatchdog getInstance(Context context) {
        if (instance == null) {
            synchronized (XGWatchdog.class) {
                if (instance == null) {
                    instance = new XGWatchdog(context);
                }
            }
        }
        return instance;
    }

    public static int getRandomInt(int i) {
        return random.nextInt(i);
    }

    public static int getRandomPort() {
        return getRandomInt(1000) + 55000;
    }

    public int getWatchdogPort() {
        Throwable th;
        ServerSocket serverSocket;
        try {
            return System.getInt(this.context.getContentResolver(), watchdogPortName);
        } catch (Throwable th2) {
            th = th2;
        }
        throw th;
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (Exception e) {
            }
        }
        throw th;
    }

    public void sendHeartbeat2Watchdog(String str) {
        sendHeartbeat2Watchdog(str, null);
    }

    private String directSendContent(String str) {
        Socket socket;
        DataOutputStream dataOutputStream;
        Throwable th;
        Socket socket2;
        DataOutputStream dataOutputStream2;
        ByteArrayOutputStream byteArrayOutputStream;
        String str2 = null;
        if (com.tencent.android.tpush.service.a.a.a(m.e()).y == 0 || !p.h(this.context)) {
            return null;
        }
        BufferedReader bufferedReader = null;
        ByteArrayOutputStream byteArrayOutputStream2;
        try {
            socket = new Socket("127.0.0.1", getWatchdogPort());
            try {
                socket.setSoTimeout(APPluginErrorCode.ERROR_APP_SYSTEM);
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                if (str == null) {
                    try {
                        str = "xgapplist:" + getLocalXGApps();
                    } catch (Throwable th2) {
                        th = th2;
                        socket2 = socket;
                        dataOutputStream2 = dataOutputStream;
                        byteArrayOutputStream = null;
                        if (socket2 != null) {
                            try {
                                socket2.close();
                            } catch (Exception e) {
                                com.tencent.android.tpush.a.a.h("xguardian", "close socket failed " + e.getMessage());
                            }
                        }
                        if (null != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e2) {
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e3) {
                            }
                        }
                        if (dataOutputStream2 != null) {
                            try {
                                dataOutputStream2.close();
                            } catch (Exception e4) {
                            }
                        }
                        throw th;
                    }
                }
                dataOutputStream.write(TpnsSecurity.oiSymmetryEncrypt2Byte(str));
                dataOutputStream.flush();
                byteArrayOutputStream2 = new ByteArrayOutputStream();
            } catch (Throwable th3) {
                th = th3;
                byteArrayOutputStream = null;
                socket2 = socket;
                dataOutputStream2 = null;
                if (socket2 != null) {
                    socket2.close();
                }
                if (null != null) {
                    bufferedReader.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (dataOutputStream2 != null) {
                    dataOutputStream2.close();
                }
                throw th;
            }
            try {
                String str3;
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = socket.getInputStream().read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream2.write(bArr, 0, read);
                }
                if (byteArrayOutputStream2.toByteArray().length > 0) {
                    str3 = new String(TpnsSecurity.oiSymmetryDecrypt2Byte(byteArrayOutputStream2.toByteArray()));
                } else {
                    str3 = null;
                }
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e5) {
                        com.tencent.android.tpush.a.a.h("xguardian", "close socket failed " + e5.getMessage());
                    }
                }
                if (null != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e6) {
                    }
                }
                if (byteArrayOutputStream2 != null) {
                    try {
                        byteArrayOutputStream2.close();
                    } catch (IOException e7) {
                    }
                }
                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                        str2 = str3;
                    } catch (Exception e8) {
                        str2 = str3;
                    }
                } else {
                    str2 = str3;
                }
            } catch (Throwable th4) {
                Throwable th5 = th4;
                socket2 = socket;
                dataOutputStream2 = dataOutputStream;
                byteArrayOutputStream = byteArrayOutputStream2;
                th = th5;
                if (socket2 != null) {
                    socket2.close();
                }
                if (null != null) {
                    bufferedReader.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (dataOutputStream2 != null) {
                    dataOutputStream2.close();
                }
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            byteArrayOutputStream = null;
            dataOutputStream2 = null;
            socket2 = null;
            if (socket2 != null) {
                socket2.close();
            }
            if (null != null) {
                bufferedReader.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (dataOutputStream2 != null) {
                dataOutputStream2.close();
            }
            throw th;
        }
        if (str2 == null) {
            return "";
        }
        return str2.replace(DLConstants.DEPENDENCY_PACKAGE_DIV, "").replace("/", "").replace("&", "").replace(" ", "");
    }

    public void sendHeartbeat2Watchdog(String str, ab abVar) {
        if (handler != null) {
            handler.post(new z(this, str, abVar));
        }
    }

    public void sendXGApp(String str, long j) {
        sendHeartbeat2Watchdog(str + "," + j + VoiceWakeuperAidl.PARAMS_SEPARATE);
    }

    public void sendAllLocalXGAppList() {
        sendHeartbeat2Watchdog(null);
    }

    public void sendDebugMode(boolean z) {
        sendHeartbeat2Watchdog("debug:" + (z ? "1" : "0"));
    }

    public String getLocalXGApps() {
        if (m.e() == null) {
            m.c(this.context);
        }
        List<ResolveInfo> a = f.a(this.context);
        List<ac> arrayList = new ArrayList(10);
        if (a != null) {
            for (ResolveInfo resolveInfo : a) {
                String str = resolveInfo.activityInfo.packageName;
                if (!f.a(str)) {
                    com.tencent.android.tpush.data.a registerInfoByPkgName = CacheManager.getRegisterInfoByPkgName(str);
                    if (registerInfoByPkgName != null) {
                        d a2 = c.a(this.context, str);
                        float f = 1.0f;
                        if (a2 != null) {
                            f = a2.a;
                        }
                        ac acVar = new ac();
                        acVar.a = str;
                        acVar.c = registerInfoByPkgName.a;
                        acVar.b = f;
                        arrayList.add(acVar);
                    }
                }
            }
        }
        Collections.sort(arrayList);
        long accessId = XGPushConfig.getAccessId(this.context);
        if (accessId <= 0) {
            accessId = XGPush4Msdk.getQQAccessId(this.context);
        }
        ac acVar2 = new ac();
        acVar2.a = this.context.getPackageName();
        acVar2.c = accessId;
        acVar2.b = Constants.PUSH_SDK_VERSION;
        arrayList.add(0, acVar2);
        StringBuilder stringBuilder = new StringBuilder(1024);
        for (ac acVar3 : arrayList) {
            stringBuilder.append(acVar3.a).append(",").append(acVar3.c).append(VoiceWakeuperAidl.PARAMS_SEPARATE);
        }
        return stringBuilder.toString();
    }

    private String domainToIp() {
        try {
            return InetAddress.getByName(Constants.UNSTALL_DOMAIN).getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return "14.18.245.161";
        }
    }

    private boolean oldloadWatchdog(String str) {
        if (!f.a(WatchdogPath)) {
            return true;
        }
        WatchdogPath = "";
        try {
            File file = new File(new StringBuffer(this.context.getFilesDir().getParentFile().getAbsolutePath()).append(File.separator).append(ShareConstants.SO_PATH).append(File.separator).append(LIB_FULL_NAME).toString());
            boolean exists = file.exists();
            if (!exists) {
                return exists;
            }
            WatchdogPath = file.getAbsolutePath();
            return exists;
        } catch (Exception e) {
            com.tencent.android.tpush.a.a.h("xguardian", "jniStartWatchdog loadWatchdog error:" + e.getMessage());
            return false;
        }
    }

    private void directStartWatchdog() {
        if (com.tencent.android.tpush.service.a.a.a(m.e()).y != 0 && p.h(this.context)) {
            try {
                if (!loadWatchdog(this.context.getPackageName())) {
                    return;
                }
                if (p.h(this.context)) {
                    int watchdogPort = getWatchdogPort();
                    List<com.tencent.android.tpush.data.a> registerInfo = CacheManager.getRegisterInfo(this.context);
                    StringBuffer stringBuffer = new StringBuffer();
                    StringBuffer stringBuffer2 = new StringBuffer();
                    StringBuffer stringBuffer3 = new StringBuffer();
                    for (com.tencent.android.tpush.data.a aVar : registerInfo) {
                        stringBuffer.append(aVar.a).append(",");
                        stringBuffer2.append(aVar.b).append(",");
                        stringBuffer3.append(aVar.d).append(",");
                    }
                    Object obj = new String[7];
                    obj[0] = WatchdogPath;
                    obj[1] = getLocalXGApps();
                    obj[2] = String.valueOf(watchdogPort);
                    obj[3] = domainToIp();
                    obj[4] = new com.tencent.android.tpush.d.a(this.context).a();
                    obj[5] = "" + (XGPushConfig.isEnableDebug(this.context) ? "1" : "0");
                    obj[6] = "" + VERSION.SDK_INT;
                    com.tencent.android.tpush.a.a.c("xguardian", "exec " + obj);
                    Process exec = Runtime.getRuntime().exec(obj);
                    ad adVar = new ad(this, exec.getErrorStream(), "Error");
                    ad adVar2 = new ad(this, exec.getInputStream(), "Output");
                    adVar.start();
                    adVar2.start();
                    com.tencent.android.tpush.a.a.c("xguardian", "proc.exitValue = " + exec.waitFor());
                    return;
                }
                com.tencent.android.tpush.a.a.h("xguardian", "xg is disable.");
            } catch (Throwable th) {
                com.tencent.android.tpush.a.a.c("xguardian", "directStartWatchdog", th);
            }
        }
    }

    public void startWatchdog() {
        if (handler != null) {
            handler.post(new aa(this));
        }
    }

    private boolean loadWatchdog(String str) {
        if (!f.a(WatchdogPath)) {
            return true;
        }
        boolean exists;
        WatchdogPath = "";
        try {
            File file = new File(new StringBuffer(File.separator).append("data").append(File.separator).append("data").append(File.separator).append(str).append(File.separator).append(ShareConstants.SO_PATH).append(File.separator).append(LIB_FULL_NAME).toString());
            exists = file.exists();
            if (exists) {
                WatchdogPath = file.getAbsolutePath();
                boolean isInstalledOnSdCard = isInstalledOnSdCard(this.context);
                com.tencent.android.tpush.a.a.d("xguardian", "Application is install in SD Card: " + isInstalledOnSdCard);
                if (isInstalledOnSdCard) {
                    String stringBuffer = new StringBuffer(this.context.getDir("watchdog", 0).getAbsolutePath()).append(File.separator).append(LIB_FULL_NAME).toString();
                    File file2 = new File(stringBuffer);
                    if (file2.exists()) {
                        com.tencent.android.tpush.a.a.d("xguardian", "exeWatchDog exists!");
                    } else {
                        try {
                            file2.createNewFile();
                            if (!p.a(file, file2)) {
                                file2.delete();
                                return false;
                            } else if (VERSION.SDK_INT >= 9) {
                                file2.getClass().getMethod("setExecutable", new Class[]{Boolean.TYPE}).invoke(file2, new Object[]{Boolean.valueOf(true)});
                            } else {
                                String str2 = "chmod 700 " + stringBuffer;
                                com.tencent.android.tpush.a.a.d("xguardian", " exec command: " + str2 + ",  exit:" + Runtime.getRuntime().exec(str2).waitFor());
                            }
                        } catch (IOException e) {
                            file2.delete();
                            com.tencent.android.tpush.a.a.h("xguardian", "exeWatchDog create error!");
                            return false;
                        }
                    }
                    WatchdogPath = stringBuffer;
                }
            }
        } catch (Exception e2) {
            com.tencent.android.tpush.a.a.h("xguardian", "jniStartWatchdog loadWatchdog error:" + e2.getMessage());
            exists = false;
        }
        return exists;
    }

    private static boolean isInstalledOnSdCard(Context context) {
        if (VERSION.SDK_INT >= 8) {
            try {
                if ((context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.flags & 262144) == 262144) {
                    return true;
                }
                return false;
            } catch (NameNotFoundException e) {
                com.tencent.android.tpush.a.a.h("xguardian", "check install location err, maybe api level < 8");
            }
        }
        try {
            String absolutePath = context.getFilesDir().getAbsolutePath();
            if (absolutePath.startsWith("/data/")) {
                return false;
            }
            if (absolutePath.contains("/mnt/") || absolutePath.contains("/sdcard/")) {
                return true;
            }
            return false;
        } catch (Throwable th) {
        }
    }
}

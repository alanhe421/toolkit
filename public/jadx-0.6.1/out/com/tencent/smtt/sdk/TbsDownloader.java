package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.dynamicload.Lib.DLConstants;
import com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.a;
import com.tencent.smtt.utils.i;
import com.tencent.smtt.utils.k;
import com.tencent.smtt.utils.t;
import java.io.File;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class TbsDownloader {
    public static final boolean DEBUG_DISABLE_DOWNLOAD = false;
    public static boolean DOWNLOAD_OVERSEA_TBS = false;
    public static final String LOGTAG = "TbsDownload";
    static boolean a;
    private static String b;
    private static Context c;
    private static Handler d;
    private static String e;
    private static Object f = new byte[0];
    private static HandlerThread g;
    private static boolean h = false;
    private static boolean i = false;
    private static boolean j = false;

    static String a(Context context) {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        String str;
        Locale locale = Locale.getDefault();
        StringBuffer stringBuffer = new StringBuffer();
        String str2 = VERSION.RELEASE;
        try {
            str = new String(str2.getBytes("UTF-8"), "ISO8859-1");
        } catch (Exception e) {
            str = str2;
        }
        if (str.length() > 0) {
            stringBuffer.append(str);
        } else {
            stringBuffer.append("1.0");
        }
        stringBuffer.append("; ");
        str = locale.getLanguage();
        if (str != null) {
            stringBuffer.append(str.toLowerCase());
            str = locale.getCountry();
            if (str != null) {
                stringBuffer.append("-");
                stringBuffer.append(str.toLowerCase());
            }
        } else {
            stringBuffer.append("en");
        }
        if ("REL".equals(VERSION.CODENAME)) {
            str2 = Build.MODEL;
            try {
                str = new String(str2.getBytes("UTF-8"), "ISO8859-1");
            } catch (Exception e2) {
                str = str2;
            }
            if (str.length() > 0) {
                stringBuffer.append("; ");
                stringBuffer.append(str);
            }
        }
        str = Build.ID.replaceAll("[一-龥]", "");
        if (str.length() > 0) {
            stringBuffer.append(" Build/");
            stringBuffer.append(str);
        }
        str = String.format("Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 Mobile Safari/533.1", new Object[]{stringBuffer});
        b = str;
        return str;
    }

    private static String a(String str) {
        return str == null ? "" : str;
    }

    private static void a(QbSdk$a qbSdk$a) {
        TbsLog.i(LOGTAG, "[TbsDownloader.queryConfig]");
        d.removeMessages(100);
        Message obtain = Message.obtain(d, 100);
        if (qbSdk$a != null) {
            obtain.obj = qbSdk$a;
        }
        obtain.sendToTarget();
    }

    private static boolean a() {
        try {
            for (String sharedTbsCoreVersion : TbsShareManager.getCoreProviderAppList()) {
                if (TbsShareManager.getSharedTbsCoreVersion(c, sharedTbsCoreVersion) > 0) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    static boolean a(Context context, boolean z, QbSdk$a qbSdk$a) {
        boolean z2 = false;
        TbsLog.initIfNeed(context);
        if (!z.b) {
            TbsLog.app_extra(LOGTAG, context);
            Object obj = null;
            c = context.getApplicationContext();
            TbsDownloadConfig instance = TbsDownloadConfig.getInstance(c);
            instance.setDownloadInterruptCode(-100);
            if (VERSION.SDK_INT < 8) {
                instance.setDownloadInterruptCode(DLConstants.LOAD_ERR_NAME_NOT_FOUND);
            } else if (QbSdk.c || !TbsShareManager.isThirdPartyApp(c) || a()) {
                if (!instance.mPreferences.contains(TbsConfigKey.KEY_IS_OVERSEA)) {
                    if (z && !"com.tencent.mm".equals(context.getApplicationInfo().packageName)) {
                        z = false;
                        TbsLog.i(LOGTAG, "needDownload-oversea is true, but not WX");
                    }
                    instance.a.put(TbsConfigKey.KEY_IS_OVERSEA, Boolean.valueOf(z));
                    instance.commit();
                    i = z;
                    TbsLog.i(LOGTAG, "needDownload-first-called--isoversea = " + z);
                }
                if (!getOverSea(context) || VERSION.SDK_INT == 16 || VERSION.SDK_INT == 17 || VERSION.SDK_INT == 18) {
                    e = instance.mPreferences.getString(TbsConfigKey.KEY_DEVICE_CPUABI, null);
                    if (!TextUtils.isEmpty(e)) {
                        Matcher matcher = null;
                        try {
                            matcher = Pattern.compile("i686|mips|x86_64").matcher(e);
                        } catch (Exception e) {
                        }
                        if (matcher != null && matcher.find()) {
                            if (qbSdk$a != null) {
                                qbSdk$a.a();
                            }
                            instance.setDownloadInterruptCode(DLConstants.LOAD_ERR_FILE_NOT_EXIST);
                            return false;
                        }
                    }
                    b();
                    if (h) {
                        if (qbSdk$a != null) {
                            qbSdk$a.a();
                        }
                        instance.setDownloadInterruptCode(DLConstants.LOAD_ERR_SO_LOAD_FAIL);
                        return false;
                    }
                    String string = instance.mPreferences.getString(TbsConfigKey.KEY_APP_VERSIONNAME, null);
                    int i = instance.mPreferences.getInt(TbsConfigKey.KEY_APP_VERSIONCODE, 0);
                    String string2 = instance.mPreferences.getString(TbsConfigKey.KEY_APP_METADATA, null);
                    String a = a.a(c);
                    int b = a.b(c);
                    String a2 = a.a(c, "com.tencent.mm.BuildInfo.CLIENT_VERSION");
                    long currentTimeMillis = System.currentTimeMillis();
                    long j = instance.mPreferences.getLong(TbsConfigKey.KEY_LAST_CHECK, 0);
                    long retryInterval = instance.getRetryInterval();
                    TbsLog.i(LOGTAG, "retryInterval = " + retryInterval + " s");
                    if (currentTimeMillis - j > 1000 * retryInterval) {
                        obj = 1;
                    } else if (!(a == null || b == 0 || a2 == null || (a.equals(string) && b == i && a2.equals(string2)))) {
                        obj = 1;
                    }
                    if (obj != null) {
                        a(qbSdk$a);
                    }
                    d.removeMessages(102);
                    Message.obtain(d, 102).sendToTarget();
                    if (QbSdk.c || !TbsShareManager.isThirdPartyApp(context)) {
                        z2 = instance.mPreferences.contains(TbsConfigKey.KEY_NEEDDOWNLOAD);
                        TbsLog.i(LOGTAG, "[TbsDownloader.needDownload] hasNeedDownloadKey=" + z2);
                        z2 = (z2 || TbsShareManager.isThirdPartyApp(context)) ? instance.mPreferences.getBoolean(TbsConfigKey.KEY_NEEDDOWNLOAD, false) : true;
                    }
                    if (z2) {
                        if (!c()) {
                            z2 = false;
                        }
                    } else if (obj != null) {
                        d.removeMessages(103);
                        Message.obtain(d, 103, c).sendToTarget();
                    }
                } else {
                    TbsLog.i(LOGTAG, "needDownload- return false,  because of  version is " + VERSION.SDK_INT + ", and overea");
                    if (qbSdk$a != null) {
                        qbSdk$a.a();
                    }
                    instance.setDownloadInterruptCode(DLConstants.LOAD_ERR_IO_FAIL);
                    return false;
                }
            } else {
                if (qbSdk$a != null) {
                    qbSdk$a.a();
                }
                return false;
            }
            if (obj == null && qbSdk$a != null) {
                qbSdk$a.a();
            }
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload] needDownload=" + z2);
            return z2;
        } else if (qbSdk$a == null) {
            return false;
        } else {
            qbSdk$a.a();
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.TargetApi(11)
    private static boolean a(java.lang.String r25, int r26, boolean r27) {
        /*
        r2 = "TbsDownload";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "[TbsDownloader.readResponse] response=";
        r3 = r3.append(r4);
        r0 = r25;
        r3 = r3.append(r0);
        r3 = r3.toString();
        com.tencent.smtt.utils.TbsLog.i(r2, r3);
        r2 = c;
        r10 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2);
        r2 = android.text.TextUtils.isEmpty(r25);
        if (r2 == 0) goto L_0x0037;
    L_0x0028:
        if (r27 == 0) goto L_0x0031;
    L_0x002a:
        r2 = -108; // 0xffffffffffffff94 float:NaN double:NaN;
        r10.setDownloadInterruptCode(r2);
    L_0x002f:
        r2 = 0;
    L_0x0030:
        return r2;
    L_0x0031:
        r2 = -208; // 0xffffffffffffff30 float:NaN double:NaN;
        r10.setDownloadInterruptCode(r2);
        goto L_0x002f;
    L_0x0037:
        r7 = new org.json.JSONObject;
        r0 = r25;
        r7.<init>(r0);
        r2 = "RET";
        r2 = r7.getInt(r2);
        if (r2 == 0) goto L_0x0056;
    L_0x0047:
        if (r27 == 0) goto L_0x0050;
    L_0x0049:
        r2 = -109; // 0xffffffffffffff93 float:NaN double:NaN;
        r10.setDownloadInterruptCode(r2);
    L_0x004e:
        r2 = 0;
        goto L_0x0030;
    L_0x0050:
        r2 = -209; // 0xffffffffffffff2f float:NaN double:NaN;
        r10.setDownloadInterruptCode(r2);
        goto L_0x004e;
    L_0x0056:
        r2 = "RESPONSECODE";
        r11 = r7.getInt(r2);
        r2 = "DOWNLOADURL";
        r12 = r7.getString(r2);
        r2 = "TBSAPKSERVERVERSION";
        r13 = r7.getInt(r2);
        r2 = "DOWNLOADMAXFLOW";
        r14 = r7.getInt(r2);
        r2 = "DOWNLOAD_MIN_FREE_SPACE";
        r15 = r7.getInt(r2);
        r2 = "DOWNLOAD_SUCCESS_MAX_RETRYTIMES";
        r16 = r7.getInt(r2);
        r2 = "DOWNLOAD_FAILED_MAX_RETRYTIMES";
        r17 = r7.getInt(r2);
        r2 = "DOWNLOAD_SINGLE_TIMEOUT";
        r18 = r7.getLong(r2);
        r2 = "TBSAPKFILESIZE";
        r20 = r7.getLong(r2);
        r2 = "RETRY_INTERVAL";
        r4 = 0;
        r8 = r7.optLong(r2, r4);
        r2 = "FLOWCTR";
        r3 = -1;
        r22 = r7.optInt(r2, r3);
        r6 = 0;
        r5 = 0;
        r4 = 0;
        r3 = "";
        r2 = "PKGMD5";
        r6 = r7.getString(r2);	 Catch:{ Exception -> 0x0197 }
        r2 = "RESETX5";
        r7.getInt(r2);	 Catch:{ Exception -> 0x02f9 }
        r2 = "UPLOADLOG";
        r5 = r7.getInt(r2);	 Catch:{ Exception -> 0x02f9 }
        r2 = "RESETTOKEN";
        r2 = r7.has(r2);	 Catch:{ Exception -> 0x02f9 }
        if (r2 == 0) goto L_0x00d4;
    L_0x00c9:
        r2 = "RESETTOKEN";
        r2 = r7.getInt(r2);	 Catch:{ Exception -> 0x02f9 }
        if (r2 == 0) goto L_0x0194;
    L_0x00d2:
        r2 = 1;
    L_0x00d3:
        r4 = r2;
    L_0x00d4:
        r2 = "SETTOKEN";
        r2 = r7.has(r2);	 Catch:{ Exception -> 0x02f9 }
        if (r2 == 0) goto L_0x0305;
    L_0x00dd:
        r2 = "SETTOKEN";
        r2 = r7.getString(r2);	 Catch:{ Exception -> 0x02f9 }
    L_0x00e4:
        r3 = r4;
        r4 = r5;
        r5 = r6;
    L_0x00e7:
        r6 = f;
        monitor-enter(r6);
        if (r3 == 0) goto L_0x00f9;
    L_0x00ec:
        r3 = r10.a;	 Catch:{ all -> 0x01a2 }
        r7 = "tbs_deskey_token";
        r23 = "";
        r0 = r23;
        r3.put(r7, r0);	 Catch:{ all -> 0x01a2 }
    L_0x00f9:
        r3 = android.text.TextUtils.isEmpty(r2);	 Catch:{ all -> 0x01a2 }
        if (r3 != 0) goto L_0x012b;
    L_0x00ff:
        r3 = r2.length();	 Catch:{ all -> 0x01a2 }
        r7 = 96;
        if (r3 != r7) goto L_0x012b;
    L_0x0107:
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01a2 }
        r3.<init>();	 Catch:{ all -> 0x01a2 }
        r2 = r3.append(r2);	 Catch:{ all -> 0x01a2 }
        r3 = "&";
        r2 = r2.append(r3);	 Catch:{ all -> 0x01a2 }
        r3 = com.tencent.smtt.utils.m.c();	 Catch:{ all -> 0x01a2 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x01a2 }
        r2 = r2.toString();	 Catch:{ all -> 0x01a2 }
        r3 = r10.a;	 Catch:{ all -> 0x01a2 }
        r7 = "tbs_deskey_token";
        r3.put(r7, r2);	 Catch:{ all -> 0x01a2 }
    L_0x012b:
        monitor-exit(r6);	 Catch:{ all -> 0x01a2 }
        r2 = 1;
        if (r4 != r2) goto L_0x0141;
    L_0x012f:
        r2 = d;
        r3 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        r2.removeMessages(r3);
        r2 = d;
        r3 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        r2 = android.os.Message.obtain(r2, r3);
        r2.sendToTarget();
    L_0x0141:
        r2 = -1;
        r0 = r22;
        if (r0 == r2) goto L_0x016a;
    L_0x0146:
        r6 = 86400; // 0x15180 float:1.21072E-40 double:4.26873E-319;
        r2 = 1;
        r0 = r22;
        if (r0 != r2) goto L_0x02ff;
    L_0x014e:
        r2 = 604800; // 0x93a80 float:8.47505E-40 double:2.98811E-318;
        r2 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x0302;
    L_0x0155:
        r2 = 604800; // 0x93a80 float:8.47505E-40 double:2.98811E-318;
    L_0x0158:
        r8 = 0;
        r4 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
        if (r4 <= 0) goto L_0x02ff;
    L_0x015e:
        r4 = r10.a;
        r6 = "retry_interval";
        r2 = java.lang.Long.valueOf(r2);
        r4.put(r6, r2);
    L_0x016a:
        r2 = c;
        r2 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r2);
        if (r2 == 0) goto L_0x01a5;
    L_0x0172:
        r2 = r10.a;
        r3 = "tbs_needdownload";
        r4 = 0;
        r4 = java.lang.Boolean.valueOf(r4);
        r2.put(r3, r4);
        r10.commit();
        r2 = "TbsDownload";
        r3 = "downloadUrl is empty --> disable current tbs!";
        com.tencent.smtt.utils.TbsLog.e(r2, r3);
        r2 = c;
        r3 = 0;
        com.tencent.smtt.sdk.TbsShareManager.writeCoreInfoForThirdPartyApp(r2, r13, r3);
        r2 = 0;
        goto L_0x0030;
    L_0x0194:
        r2 = 0;
        goto L_0x00d3;
    L_0x0197:
        r2 = move-exception;
        r2 = r4;
        r4 = r5;
        r5 = r6;
    L_0x019b:
        r24 = r3;
        r3 = r2;
        r2 = r24;
        goto L_0x00e7;
    L_0x01a2:
        r2 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x01a2 }
        throw r2;
    L_0x01a5:
        if (r11 != 0) goto L_0x01c7;
    L_0x01a7:
        r2 = r10.a;
        r3 = "tbs_needdownload";
        r4 = 0;
        r4 = java.lang.Boolean.valueOf(r4);
        r2.put(r3, r4);
        r10.commit();
        if (r27 == 0) goto L_0x01c1;
    L_0x01b9:
        r2 = -111; // 0xffffffffffffff91 float:NaN double:NaN;
        r10.setDownloadInterruptCode(r2);
    L_0x01be:
        r2 = 0;
        goto L_0x0030;
    L_0x01c1:
        r2 = -211; // 0xffffffffffffff2d float:NaN double:NaN;
        r10.setDownloadInterruptCode(r2);
        goto L_0x01be;
    L_0x01c7:
        r2 = c;
        r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2);
        r2 = r2.mPreferences;
        r3 = "tbs_download_version";
        r4 = 0;
        r3 = r2.getInt(r3, r4);
        r2 = android.os.Build.VERSION.SDK_INT;
        r4 = 11;
        if (r2 < r4) goto L_0x023c;
    L_0x01dd:
        r2 = c;
        r4 = "tbs_preloadx5_check_cfg_file";
        r6 = 4;
        r2 = r2.getSharedPreferences(r4, r6);
    L_0x01e7:
        r4 = "tbs_precheck_disable_version";
        r6 = -1;
        r2 = r2.getInt(r4, r6);
        if (r2 == r13) goto L_0x01fd;
    L_0x01f1:
        r0 = r26;
        if (r0 >= r13) goto L_0x01fd;
    L_0x01f5:
        if (r3 > r13) goto L_0x01fd;
    L_0x01f7:
        r3 = android.text.TextUtils.isEmpty(r12);
        if (r3 == 0) goto L_0x024d;
    L_0x01fd:
        if (r2 != r13) goto L_0x0219;
    L_0x01ff:
        r2 = "TbsDownload";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Download is disabled by preload_x5_check; tbs_version:";
        r3 = r3.append(r4);
        r3 = r3.append(r13);
        r3 = r3.toString();
        com.tencent.smtt.utils.TbsLog.e(r2, r3);
    L_0x0219:
        r2 = r10.a;
        r3 = "tbs_needdownload";
        r4 = 0;
        r4 = java.lang.Boolean.valueOf(r4);
        r2.put(r3, r4);
        r10.commit();
        if (r27 == 0) goto L_0x0247;
    L_0x022b:
        r2 = -112; // 0xffffffffffffff90 float:NaN double:NaN;
        r10.setDownloadInterruptCode(r2);
    L_0x0230:
        r2 = "TbsDownload";
        r3 = "version error or downloadUrl empty ,return ahead";
        com.tencent.smtt.utils.TbsLog.i(r2, r3);
        r2 = 0;
        goto L_0x0030;
    L_0x023c:
        r2 = c;
        r4 = "tbs_preloadx5_check_cfg_file";
        r6 = 0;
        r2 = r2.getSharedPreferences(r4, r6);
        goto L_0x01e7;
    L_0x0247:
        r2 = -212; // 0xffffffffffffff2c float:NaN double:NaN;
        r10.setDownloadInterruptCode(r2);
        goto L_0x0230;
    L_0x024d:
        r2 = r10.a;
        r3 = "tbs_download_version";
        r4 = java.lang.Integer.valueOf(r13);
        r2.put(r3, r4);
        r2 = r10.a;
        r3 = "tbs_downloadurl";
        r2.put(r3, r12);
        r2 = r10.a;
        r3 = "tbs_responsecode";
        r4 = java.lang.Integer.valueOf(r11);
        r2.put(r3, r4);
        r2 = r10.a;
        r3 = "tbs_download_maxflow";
        r4 = java.lang.Integer.valueOf(r14);
        r2.put(r3, r4);
        r2 = r10.a;
        r3 = "tbs_download_min_free_space";
        r4 = java.lang.Integer.valueOf(r15);
        r2.put(r3, r4);
        r2 = r10.a;
        r3 = "tbs_download_success_max_retrytimes";
        r4 = java.lang.Integer.valueOf(r16);
        r2.put(r3, r4);
        r2 = r10.a;
        r3 = "tbs_download_failed_max_retrytimes";
        r4 = java.lang.Integer.valueOf(r17);
        r2.put(r3, r4);
        r2 = r10.a;
        r3 = "tbs_single_timeout";
        r4 = java.lang.Long.valueOf(r18);
        r2.put(r3, r4);
        r2 = r10.a;
        r3 = "tbs_apkfilesize";
        r4 = java.lang.Long.valueOf(r20);
        r2.put(r3, r4);
        if (r5 == 0) goto L_0x02bf;
    L_0x02b7:
        r2 = r10.a;
        r3 = "tbs_apk_md5";
        r2.put(r3, r5);
    L_0x02bf:
        r2 = com.tencent.smtt.sdk.z.a();
        r3 = c;
        r2 = r2.a(r3, r13);
        if (r2 == 0) goto L_0x02eb;
    L_0x02cb:
        if (r27 == 0) goto L_0x02e5;
    L_0x02cd:
        r2 = -113; // 0xffffffffffffff8f float:NaN double:NaN;
        r10.setDownloadInterruptCode(r2);
    L_0x02d2:
        r2 = r10.a;
        r3 = "tbs_needdownload";
        r4 = 0;
        r4 = java.lang.Boolean.valueOf(r4);
        r2.put(r3, r4);
    L_0x02df:
        r10.commit();
        r2 = 1;
        goto L_0x0030;
    L_0x02e5:
        r2 = -213; // 0xffffffffffffff2b float:NaN double:NaN;
        r10.setDownloadInterruptCode(r2);
        goto L_0x02d2;
    L_0x02eb:
        r2 = r10.a;
        r3 = "tbs_needdownload";
        r4 = 1;
        r4 = java.lang.Boolean.valueOf(r4);
        r2.put(r3, r4);
        goto L_0x02df;
    L_0x02f9:
        r2 = move-exception;
        r2 = r4;
        r4 = r5;
        r5 = r6;
        goto L_0x019b;
    L_0x02ff:
        r2 = r6;
        goto L_0x015e;
    L_0x0302:
        r2 = r8;
        goto L_0x0158;
    L_0x0305:
        r2 = r3;
        goto L_0x00e4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.a(java.lang.String, int, boolean):boolean");
    }

    private static JSONObject b(boolean z) {
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(c);
        String a = a(c);
        String d = a.d(c);
        String c = a.c(c);
        String f = a.f(c);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("PROTOCOLVERSION", 1);
            int a2 = TbsShareManager.isThirdPartyApp(c) ? QbSdk.c ? TbsShareManager.a(c, false) : TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0) : 0;
            if (z) {
                jSONObject.put("FUNCTION", 2);
            } else {
                jSONObject.put("FUNCTION", a2 == 0 ? 0 : 1);
            }
            if (TbsShareManager.isThirdPartyApp(c)) {
                Object obj;
                JSONArray jSONArray = new JSONArray();
                Object coreProviderAppList = TbsShareManager.getCoreProviderAppList();
                String packageName = c.getApplicationContext().getPackageName();
                Object obj2;
                if (packageName.equals(TbsShareManager.d(c))) {
                    int length = coreProviderAppList.length;
                    obj = new String[(length + 1)];
                    System.arraycopy(coreProviderAppList, 0, obj, 0, length);
                    obj[length] = packageName;
                    obj2 = obj;
                } else {
                    obj2 = coreProviderAppList;
                }
                for (String sharedTbsCoreVersion : r3) {
                    int sharedTbsCoreVersion2 = TbsShareManager.getSharedTbsCoreVersion(c, sharedTbsCoreVersion);
                    if (sharedTbsCoreVersion2 > 0) {
                        obj = null;
                        for (int i = 0; i < jSONArray.length(); i++) {
                            if (jSONArray.optInt(i) == sharedTbsCoreVersion2) {
                                obj = 1;
                                break;
                            }
                        }
                        if (obj == null) {
                            jSONArray.put(sharedTbsCoreVersion2);
                        }
                    }
                }
                jSONObject.put("TBSVLARR", jSONArray);
                if (QbSdk.c) {
                    jSONObject.put("THIRDREQ", 1);
                }
            }
            jSONObject.put("APPN", c.getPackageName());
            jSONObject.put("APPVN", a(instance.mPreferences.getString(TbsConfigKey.KEY_APP_VERSIONNAME, null)));
            jSONObject.put("APPVC", instance.mPreferences.getInt(TbsConfigKey.KEY_APP_VERSIONCODE, 0));
            jSONObject.put("APPMETA", a(instance.mPreferences.getString(TbsConfigKey.KEY_APP_METADATA, null)));
            jSONObject.put("TBSSDKV", 36801);
            jSONObject.put("TBSV", a2);
            jSONObject.put("CPU", e);
            jSONObject.put("UA", a);
            jSONObject.put("IMSI", a(d));
            jSONObject.put("IMEI", a(c));
            jSONObject.put("ANDROID_ID", a(f));
            if (getOverSea(c)) {
                jSONObject.put("OVERSEA", 1);
            }
        } catch (Exception e) {
        }
        TbsLog.i(LOGTAG, "[TbsDownloader.postJsonData] jsonData=" + jSONObject.toString());
        return jSONObject;
    }

    private static synchronized void b() {
        synchronized (TbsDownloader.class) {
            if (g == null) {
                g = x.a();
                d = new v(g.getLooper());
            }
        }
    }

    private static boolean c() {
        return true;
    }

    private static boolean c(boolean z) {
        TbsLog.i(LOGTAG, "[TbsDownloader.sendRequest]isQuery: " + z);
        if (z.a().b(c)) {
            TbsLog.i(LOGTAG, "[TbsDownloader.sendRequest] -- isTbsLocalInstalled!");
            return false;
        }
        int i;
        boolean a;
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(c);
        File file = new File(i.a(c, 1), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
        File file2 = new File(i.a(c, 2), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
        File file3 = new File(i.a(c, 3), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
        File file4 = new File(i.a(c, 4), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
        if (!file4.exists()) {
            if (file3.exists()) {
                file3.renameTo(file4);
            } else if (file2.exists()) {
                file2.renameTo(file4);
            } else if (file.exists()) {
                file.renameTo(file4);
            }
        }
        instance.a.put(TbsConfigKey.KEY_LAST_CHECK, Long.valueOf(System.currentTimeMillis()));
        instance.a.put(TbsConfigKey.KEY_APP_VERSIONNAME, a.a(c));
        instance.a.put(TbsConfigKey.KEY_APP_VERSIONCODE, Integer.valueOf(a.b(c)));
        instance.a.put(TbsConfigKey.KEY_APP_METADATA, a.a(c, "com.tencent.mm.BuildInfo.CLIENT_VERSION"));
        instance.commit();
        if (e == null) {
            e = a.a();
            instance.a.put(TbsConfigKey.KEY_DEVICE_CPUABI, e);
            instance.commit();
        }
        if (!TextUtils.isEmpty(e)) {
            Matcher matcher = null;
            try {
                matcher = Pattern.compile("i686|mips|x86_64").matcher(e);
            } catch (Exception e) {
            }
            if (matcher != null && matcher.find()) {
                if (z) {
                    instance.setDownloadInterruptCode(DLConstants.LOAD_ERR_FILE_NOT_EXIST);
                    return false;
                }
                instance.setDownloadInterruptCode(-205);
                return false;
            }
        }
        JSONObject b = b(z);
        try {
            i = b.getInt("TBSV");
        } catch (Exception e2) {
            i = -1;
        }
        if (i != -1) {
            try {
                String d = t.a(c).d();
                TbsLog.i(LOGTAG, "[TbsDownloader.sendRequest] postUrl=" + d);
                a = a(k.a(d, b.toString().getBytes("utf-8"), new w(z, instance), false), i, z);
            } catch (Throwable th) {
                th.printStackTrace();
                if (z) {
                    instance.setDownloadInterruptCode(DLConstants.LOAD_ERR_APK_DATE);
                    a = false;
                } else {
                    instance.setDownloadInterruptCode(-206);
                }
            }
            return a;
        }
        a = false;
        return a;
    }

    public static synchronized boolean getOverSea(Context context) {
        boolean z;
        synchronized (TbsDownloader.class) {
            if (!j) {
                j = true;
                TbsDownloadConfig instance = TbsDownloadConfig.getInstance(context);
                if (instance.mPreferences.contains(TbsConfigKey.KEY_IS_OVERSEA)) {
                    i = instance.mPreferences.getBoolean(TbsConfigKey.KEY_IS_OVERSEA, false);
                    TbsLog.i(LOGTAG, "[TbsDownloader.getOverSea]  first called. sOverSea = " + i);
                }
                TbsLog.i(LOGTAG, "[TbsDownloader.getOverSea]  sOverSea = " + i);
            }
            z = i;
        }
        return z;
    }

    public static HandlerThread getsTbsHandlerThread() {
        return g;
    }

    public static synchronized boolean isDownloading() {
        boolean z;
        synchronized (TbsDownloader.class) {
            TbsLog.i(LOGTAG, "[TbsDownloader.isDownloading]");
            z = a;
        }
        return z;
    }

    public static boolean needDownload(Context context, boolean z) {
        return a(context, z, null);
    }

    public static synchronized void startDownload(Context context) {
        synchronized (TbsDownloader.class) {
        }
    }

    public static void stopDownload() {
    }
}

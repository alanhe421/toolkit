package com.tencent.beacon.net;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Proxy;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Base64;
import android.util.Log;
import android.util.SparseArray;
import com.dynamicload.Lib.DLConstants;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.av.config.ConfigBaseParser;
import com.tencent.beacon.b.a.e;
import com.tencent.beacon.b.a.f;
import com.tencent.beacon.b.b.h;
import com.tencent.beacon.b.d;
import com.tencent.beacon.b.j;
import com.tencent.beacon.event.UserAction;
import com.tencent.beacon.event.g;
import com.tencent.beacon.event.k;
import com.tencent.beacon.event.n;
import com.tencent.beacon.event.o;
import com.tencent.qalsdk.im_open.http;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.util.EncodingUtils;
import tencent.tls.tools.util.APNName;

/* compiled from: ProGuard */
public final class a {
    private final Context a;

    /* compiled from: ProGuard */
    static class a {
        public String a = "";
        public int b = -1;
    }

    /* compiled from: ProGuard */
    public static class b {
        public long a = -1;
        public long b = -1;
        public long c = -1;
        public long d = -1;
        public long e = -1;
        public String f = "";
    }

    public static Object a(String str, String str2, Class[] clsArr, Object[] objArr) {
        Object obj = null;
        try {
            obj = Class.forName(str).getMethod(str2, clsArr).invoke(null, objArr);
        } catch (Exception e) {
            com.tencent.beacon.e.a.a("ref call %s#%s failed.", str, str2);
        }
        return obj;
    }

    public static int a(Context context, f[] fVarArr) {
        if (context == null || fVarArr == null || fVarArr.length <= 0) {
            return -1;
        }
        List arrayList = new ArrayList(fVarArr.length);
        for (Object obj : fVarArr) {
            byte[] a = a(obj);
            if (a != null) {
                com.tencent.beacon.b.a.a aVar = new com.tencent.beacon.b.a.a(6, 0, 0, a);
                aVar.a(obj.a());
                arrayList.add(aVar);
            }
        }
        if (arrayList.size() > 0) {
            return com.tencent.beacon.b.a.a.b(context, arrayList) ? arrayList.size() : -1;
        } else {
            return 0;
        }
    }

    public static int e(Context context) {
        com.tencent.beacon.e.a.a(" RecordDAO.countRecordNum() start", new Object[0]);
        if (context != null) {
            return com.tencent.beacon.b.a.a.a(context, new int[]{1, 2, 3, 4});
        }
        com.tencent.beacon.e.a.d(" countRecordNum() have null args!", new Object[0]);
        return -1;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object[] a(android.content.Context r10, java.lang.String r11) {
        /*
        r8 = 0;
        r9 = com.tencent.beacon.b.a.e.a;
        monitor-enter(r9);
        if (r10 != 0) goto L_0x0012;
    L_0x0006:
        r0 = "context == null ";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x0095 }
        com.tencent.beacon.e.a.c(r0, r1);	 Catch:{ all -> 0x0095 }
        monitor-exit(r9);	 Catch:{ all -> 0x0095 }
        r0 = r8;
    L_0x0011:
        return r0;
    L_0x0012:
        if (r11 != 0) goto L_0x0020;
    L_0x0014:
        r0 = "key == null ";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x0095 }
        com.tencent.beacon.e.a.c(r0, r1);	 Catch:{ all -> 0x0095 }
        monitor-exit(r9);	 Catch:{ all -> 0x0095 }
        r0 = r8;
        goto L_0x0011;
    L_0x0020:
        r0 = com.tencent.beacon.b.a.e.a(r10);	 Catch:{ Exception -> 0x00a7, all -> 0x00b5 }
        r0 = r0.getWritableDatabase();	 Catch:{ Exception -> 0x00a7, all -> 0x00b5 }
        if (r0 != 0) goto L_0x0036;
    L_0x002a:
        r0 = "getWritableDatabase fail! ";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Exception -> 0x00a7, all -> 0x00b5 }
        com.tencent.beacon.e.a.c(r0, r1);	 Catch:{ Exception -> 0x00a7, all -> 0x00b5 }
        monitor-exit(r9);	 Catch:{ all -> 0x0095 }
        r0 = r8;
        goto L_0x0011;
    L_0x0036:
        r1 = "t_conf";
        r2 = 0;
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00a7, all -> 0x00b5 }
        r4 = "_key = '";
        r3.<init>(r4);	 Catch:{ Exception -> 0x00a7, all -> 0x00b5 }
        r3 = r3.append(r11);	 Catch:{ Exception -> 0x00a7, all -> 0x00b5 }
        r4 = "'";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x00a7, all -> 0x00b5 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x00a7, all -> 0x00b5 }
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x00a7, all -> 0x00b5 }
        if (r1 == 0) goto L_0x0098;
    L_0x005b:
        r0 = r1.moveToNext();	 Catch:{ Exception -> 0x00c5, all -> 0x00c2 }
        if (r0 == 0) goto L_0x0098;
    L_0x0061:
        r0 = 3;
        r0 = new java.lang.Object[r0];	 Catch:{ Exception -> 0x00c5, all -> 0x00c2 }
        r2 = 0;
        r0[r2] = r11;	 Catch:{ Exception -> 0x00c5, all -> 0x00c2 }
        r2 = 1;
        r3 = "_value";
        r3 = r1.getColumnIndex(r3);	 Catch:{ Exception -> 0x00c5, all -> 0x00c2 }
        r3 = r1.getString(r3);	 Catch:{ Exception -> 0x00c5, all -> 0x00c2 }
        r0[r2] = r3;	 Catch:{ Exception -> 0x00c5, all -> 0x00c2 }
        r2 = 2;
        r3 = "_vdate";
        r3 = r1.getColumnIndex(r3);	 Catch:{ Exception -> 0x00c5, all -> 0x00c2 }
        r4 = r1.getLong(r3);	 Catch:{ Exception -> 0x00c5, all -> 0x00c2 }
        r3 = java.lang.Long.valueOf(r4);	 Catch:{ Exception -> 0x00c5, all -> 0x00c2 }
        r0[r2] = r3;	 Catch:{ Exception -> 0x00c5, all -> 0x00c2 }
        if (r1 == 0) goto L_0x0092;
    L_0x0089:
        r2 = r1.isClosed();	 Catch:{ all -> 0x0095 }
        if (r2 != 0) goto L_0x0092;
    L_0x008f:
        r1.close();	 Catch:{ all -> 0x0095 }
    L_0x0092:
        monitor-exit(r9);	 Catch:{ all -> 0x0095 }
        goto L_0x0011;
    L_0x0095:
        r0 = move-exception;
        monitor-exit(r9);
        throw r0;
    L_0x0098:
        if (r1 == 0) goto L_0x00a3;
    L_0x009a:
        r0 = r1.isClosed();	 Catch:{ all -> 0x0095 }
        if (r0 != 0) goto L_0x00a3;
    L_0x00a0:
        r1.close();	 Catch:{ all -> 0x0095 }
    L_0x00a3:
        monitor-exit(r9);	 Catch:{ all -> 0x0095 }
        r0 = r8;
        goto L_0x0011;
    L_0x00a7:
        r0 = move-exception;
        r0 = r8;
    L_0x00a9:
        if (r0 == 0) goto L_0x00a3;
    L_0x00ab:
        r1 = r0.isClosed();	 Catch:{ all -> 0x0095 }
        if (r1 != 0) goto L_0x00a3;
    L_0x00b1:
        r0.close();	 Catch:{ all -> 0x0095 }
        goto L_0x00a3;
    L_0x00b5:
        r0 = move-exception;
    L_0x00b6:
        if (r8 == 0) goto L_0x00c1;
    L_0x00b8:
        r1 = r8.isClosed();	 Catch:{ all -> 0x0095 }
        if (r1 != 0) goto L_0x00c1;
    L_0x00be:
        r8.close();	 Catch:{ all -> 0x0095 }
    L_0x00c1:
        throw r0;	 Catch:{ all -> 0x0095 }
    L_0x00c2:
        r0 = move-exception;
        r8 = r1;
        goto L_0x00b6;
    L_0x00c5:
        r0 = move-exception;
        r0 = r1;
        goto L_0x00a9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.beacon.net.a.a(android.content.Context, java.lang.String):java.lang.Object[]");
    }

    public static com.tencent.beacon.runinfo.a f(Context context) {
        com.tencent.beacon.runinfo.a aVar = null;
        if (context != null) {
            List b = com.tencent.beacon.b.a.a.b(context, new int[]{8});
            if (b != null && b.size() > 0) {
                com.tencent.beacon.b.a.a aVar2 = (com.tencent.beacon.b.a.a) b.get(0);
                if (aVar2 != null) {
                    Object b2 = b(aVar2.b());
                    if (b2 != null && com.tencent.beacon.runinfo.a.class.isInstance(b2)) {
                        aVar = (com.tencent.beacon.runinfo.a) com.tencent.beacon.runinfo.a.class.cast(b2);
                        if (aVar != null) {
                            aVar.e(aVar2.a());
                        }
                    }
                }
            }
        }
        return aVar;
    }

    public static Object a(String str, String str2, Object obj, Class[] clsArr, Object[] objArr) {
        try {
            return Class.forName(str).getMethod(str2, clsArr).invoke(obj, objArr);
        } catch (Exception e) {
            com.tencent.beacon.e.a.a("ref call %s#%s failed.", str, str2);
            return null;
        }
    }

    public a(Context context) {
        this.a = context;
    }

    public static boolean a(Context context, k kVar) {
        int i = 3;
        com.tencent.beacon.e.a.a(" RecordDAO.insert() start", new Object[0]);
        if (context == null || kVar == null || kVar.b() == null) {
            com.tencent.beacon.e.a.d(" insert() have null args!", new Object[0]);
            return false;
        }
        int i2;
        if (kVar.b().equals("UA")) {
            i2 = 1;
        } else if (kVar.b().equals("IP")) {
            i2 = 2;
            i = 0;
        } else if (kVar.b().equals("DN")) {
            i2 = 3;
            i = 0;
        } else if (kVar.b().equals("HO")) {
            i2 = 4;
            i = 0;
        } else {
            com.tencent.beacon.e.a.d(" bean's type is error!", new Object[0]);
            return false;
        }
        try {
            com.tencent.beacon.b.a.a aVar = new com.tencent.beacon.b.a.a(i2, i, kVar.c(), a((Object) kVar));
            boolean a = com.tencent.beacon.b.a.a.a(context, aVar);
            if (a) {
                kVar.a(aVar.a());
            }
            com.tencent.beacon.e.a.a(" RecordDAO.insert() end", new Object[0]);
            return a;
        } catch (Throwable th) {
            com.tencent.beacon.e.a.a(" RecordDAO.insert() end", new Object[0]);
        }
    }

    public final void a() {
        if (this.a != null) {
            com.tencent.beacon.e.a.b("start upload ac event", new Object[0]);
            n a = n.a(this.a);
            com.tencent.beacon.a.a a2 = com.tencent.beacon.a.a.a(this.a);
            if (a == null) {
                com.tencent.beacon.e.a.c("uadeviceInfo == null,return.", new Object[0]);
                return;
            }
            com.tencent.beacon.b.f.a(this.a);
            Map hashMap = new HashMap();
            hashMap.put("A33", com.tencent.beacon.b.f.l(this.a));
            hashMap.put("A58", a.r());
            hashMap.put("A82", Build.FINGERPRINT);
            hashMap.put("A85", com.tencent.beacon.b.b.b ? "Y" : "N");
            hashMap.put("A88", com.tencent.beacon.b.b.b(this.a));
            hashMap.put("A89", com.tencent.beacon.b.b.a(this.a));
            hashMap.put("A90", a2.b().booleanValue() ? "Y" : "N");
            hashMap.put("A91", String.valueOf(a2.a()));
            Context context = this.a;
            int intValue = Integer.valueOf(VERSION.SDK).intValue();
            Activity e = e();
            String str = "";
            g j = o.d().j();
            if (!(j == null || j.z() == null)) {
                str = j.z();
            }
            hashMap.put("B13", i(com.tencent.beacon.nativeimpl.a.b(context, intValue, e, str)));
            UserAction.onUserAction("rqd_appresumed", true, 0, 0, hashMap, true);
        }
    }

    public static long a(String str, int i) {
        Throwable th;
        if (str == null || i <= 0) {
            return -1;
        }
        Socket socket = null;
        long currentTimeMillis = System.currentTimeMillis();
        SocketAddress inetSocketAddress = new InetSocketAddress(str, i);
        try {
            Socket socket2 = new Socket();
            try {
                socket2.connect(inetSocketAddress, qalsdk.n.f);
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                com.tencent.beacon.e.a.a(" s conn:" + currentTimeMillis2, new Object[0]);
                try {
                    socket2.close();
                    return currentTimeMillis2;
                } catch (Throwable e) {
                    com.tencent.beacon.e.a.a(e);
                    com.tencent.beacon.e.a.d(e.getMessage(), new Object[0]);
                    return currentTimeMillis2;
                }
            } catch (Throwable th2) {
                th = th2;
                socket = socket2;
                if (socket != null) {
                    socket.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            com.tencent.beacon.e.a.d(th.getMessage(), new Object[0]);
            com.tencent.beacon.e.a.b(" exception:" + str + ":" + i, new Object[0]);
            if (socket != null) {
                socket.close();
            }
            return -1;
        }
    }

    public static int a(String str, Object obj, String str2) {
        try {
            Field declaredField = Class.forName(str).getDeclaredField(str2);
            declaredField.setAccessible(true);
            return declaredField.getInt(obj);
        } catch (Exception e) {
            com.tencent.beacon.e.a.a("ref get %s.%s failed.", str, str2);
            return -1;
        }
    }

    public static List<f> a(Context context) {
        if (context == null) {
            return null;
        }
        List<com.tencent.beacon.b.a.a> a = com.tencent.beacon.b.a.a.a(context, new int[]{6}, -1, -1, 5, 0);
        if (a == null || a.size() <= 0) {
            return null;
        }
        List<f> arrayList = new ArrayList(a.size());
        for (com.tencent.beacon.b.a.a aVar : a) {
            try {
                f fVar = (f) f.class.cast(b(aVar.b()));
                if (fVar != null) {
                    fVar.a(aVar.a());
                    arrayList.add(fVar);
                }
            } catch (Throwable th) {
                com.tencent.beacon.e.a.a(th);
                com.tencent.beacon.e.a.d("netconsume error %s", th.toString());
            }
        }
        return arrayList;
    }

    public static Date d(String str) {
        Date date = null;
        if (str != null && str.trim().length() > 0) {
            try {
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(str);
            } catch (Throwable e) {
                com.tencent.beacon.e.a.a(e);
            }
        }
        return date;
    }

    public static String b(String str) {
        String str2 = ConfigBaseParser.DEFAULT_VALUE;
        if (str == null || str.trim().length() == 0) {
            return str2;
        }
        String trim = str.trim();
        int length = trim.length();
        char charAt;
        do {
            length--;
            if (length < 0) {
                length = 1;
                break;
            }
            charAt = trim.charAt(length);
            if (charAt < '0') {
                break;
            }
        } while (charAt <= '9');
        length = 0;
        if (length != 0) {
            str2 = str.trim();
            if (str2.length() > 16) {
                return str2.substring(0, 16);
            }
            return str2;
        }
        com.tencent.beacon.e.a.c("channelID is invalid!! channelID should be Numeric! channelID:" + str, new Object[0]);
        return str2;
    }

    public final void b() {
        if (this.a != null) {
            Map hashMap = new HashMap();
            hashMap.put("B15", i(com.tencent.beacon.nativeimpl.a.a(this.a, Integer.valueOf(VERSION.SDK).intValue(), e())));
            UserAction.onUserAction("rqd_apppaused", true, 0, 0, hashMap, true);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.content.Context r8, java.lang.String r9, java.lang.Object[] r10) {
        /*
        r2 = 1;
        r1 = 0;
        r3 = com.tencent.beacon.b.a.e.a;
        monitor-enter(r3);
        if (r8 == 0) goto L_0x000e;
    L_0x0007:
        if (r9 == 0) goto L_0x000e;
    L_0x0009:
        if (r10 == 0) goto L_0x000e;
    L_0x000b:
        r0 = r10.length;	 Catch:{ all -> 0x0085 }
        if (r0 != 0) goto L_0x0011;
    L_0x000e:
        monitor-exit(r3);	 Catch:{ all -> 0x0085 }
        r0 = r1;
    L_0x0010:
        return r0;
    L_0x0011:
        r0 = com.tencent.beacon.b.a.e.a(r8);	 Catch:{ Exception -> 0x008a }
        r4 = r0.getWritableDatabase();	 Catch:{ Exception -> 0x008a }
        r0 = "t_conf";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x008a }
        r6 = "_key = '";
        r5.<init>(r6);	 Catch:{ Exception -> 0x008a }
        r5 = r5.append(r9);	 Catch:{ Exception -> 0x008a }
        r6 = "'";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x008a }
        r5 = r5.toString();	 Catch:{ Exception -> 0x008a }
        r6 = 0;
        r4.delete(r0, r5, r6);	 Catch:{ Exception -> 0x008a }
        r5 = new android.content.ContentValues;	 Catch:{ Exception -> 0x008a }
        r5.<init>();	 Catch:{ Exception -> 0x008a }
        r0 = "_key";
        r5.put(r0, r9);	 Catch:{ Exception -> 0x008a }
        r6 = "_value";
        r0 = 0;
        r0 = r10[r0];	 Catch:{ Exception -> 0x008a }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x008a }
        r5.put(r6, r0);	 Catch:{ Exception -> 0x008a }
        r6 = "_vdate";
        r0 = 1;
        r0 = r10[r0];	 Catch:{ Exception -> 0x008a }
        r0 = (java.lang.Long) r0;	 Catch:{ Exception -> 0x008a }
        r5.put(r6, r0);	 Catch:{ Exception -> 0x008a }
        r0 = "_time";
        r6 = new java.util.Date;	 Catch:{ Exception -> 0x008a }
        r6.<init>();	 Catch:{ Exception -> 0x008a }
        r6 = r6.getTime();	 Catch:{ Exception -> 0x008a }
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x008a }
        r5.put(r0, r6);	 Catch:{ Exception -> 0x008a }
        r0 = "t_conf";
        r6 = 0;
        r4 = r4.insert(r0, r6, r5);	 Catch:{ Exception -> 0x008a }
        r6 = 0;
        r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r0 >= 0) goto L_0x0088;
    L_0x0079:
        r0 = " insertOrUpdateByKey failure! return false!";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x008a }
        com.tencent.beacon.e.a.d(r0, r2);	 Catch:{ Exception -> 0x008a }
        r0 = r1;
    L_0x0083:
        monitor-exit(r3);	 Catch:{ all -> 0x0085 }
        goto L_0x0010;
    L_0x0085:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
    L_0x0088:
        r0 = r2;
        goto L_0x0083;
    L_0x008a:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0083;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.beacon.net.a.a(android.content.Context, java.lang.String, java.lang.Object[]):boolean");
    }

    public static b a(String str, boolean z) {
        OutputStream outputStream;
        Throwable th;
        InputStream inputStream;
        Throwable th2;
        Throwable th3;
        InputStream inputStream2 = null;
        if (str == null || str.equals("")) {
            return null;
        }
        b bVar = new b();
        Socket socket;
        try {
            URL url = new URL(str);
            String host = url.getHost();
            long currentTimeMillis = System.currentTimeMillis();
            InetAddress byName = InetAddress.getByName(host);
            bVar.a = System.currentTimeMillis() - currentTimeMillis;
            com.tencent.beacon.e.a.a(" dns: " + bVar.a, new Object[0]);
            StringBuffer stringBuffer = new StringBuffer();
            byte[] address = byName.getAddress();
            int i = 0;
            while (i < address.length) {
                stringBuffer.append("." + (address[i] < (byte) 0 ? address[i] + 256 : address[i]));
                i++;
            }
            bVar.f = stringBuffer.substring(1);
            stringBuffer.setLength(0);
            if (z) {
                return bVar;
            }
            String str2;
            int port = url.getPort();
            if (port < 0) {
                port = 80;
            }
            SocketAddress inetSocketAddress = new InetSocketAddress(byName, port);
            long currentTimeMillis2 = System.currentTimeMillis();
            socket = new Socket();
            try {
                socket.connect(inetSocketAddress, qalsdk.n.f);
                bVar.b = System.currentTimeMillis() - currentTimeMillis2;
                com.tencent.beacon.e.a.a(" conn: " + bVar.b, new Object[0]);
                str2 = "HEAD " + (url.getPath() + (url.getQuery() != null ? "?" + url.getQuery() : "")) + " HTTP/1.1 \r\nHost: " + host + "\r\nConnection: close\r\n\r\n";
                outputStream = socket.getOutputStream();
            } catch (Throwable th4) {
                th2 = th4;
                outputStream = null;
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                if (socket != null) {
                    socket.close();
                }
                throw th2;
            }
            try {
                inputStream = socket.getInputStream();
                try {
                    currentTimeMillis2 = System.currentTimeMillis();
                    outputStream.write(str2.getBytes("UTF-8"));
                    outputStream.flush();
                    bVar.c = System.currentTimeMillis() - currentTimeMillis2;
                    com.tencent.beacon.e.a.a(" re: " + bVar.c, new Object[0]);
                    long currentTimeMillis3 = System.currentTimeMillis();
                    inputStream.read();
                    bVar.d = System.currentTimeMillis() - currentTimeMillis3;
                    com.tencent.beacon.e.a.a(" wait: " + bVar.d, new Object[0]);
                    byte[] bArr = new byte[http.Internal_Server_Error];
                    currentTimeMillis3 = System.currentTimeMillis();
                    int read = inputStream.read(bArr);
                    bVar.e = System.currentTimeMillis() - currentTimeMillis3;
                    com.tencent.beacon.e.a.a(" readp: " + bVar.e, new Object[0]);
                    com.tencent.beacon.e.a.a(" datasize: " + read, new Object[0]);
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Throwable th5) {
                            com.tencent.beacon.e.a.a(th5);
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th52) {
                            com.tencent.beacon.e.a.a(th52);
                        }
                    }
                    try {
                        socket.close();
                        return bVar;
                    } catch (Throwable th522) {
                        com.tencent.beacon.e.a.a(th522);
                        com.tencent.beacon.e.a.d(th522.getMessage(), new Object[0]);
                        return bVar;
                    }
                } catch (Throwable th6) {
                    th522 = th6;
                    com.tencent.beacon.e.a.a(th522);
                    com.tencent.beacon.e.a.d(th522.getMessage(), new Object[0]);
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (socket == null) {
                        return bVar;
                    }
                    socket.close();
                    return bVar;
                }
            } catch (Throwable th7) {
                th2 = th7;
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                if (socket != null) {
                    socket.close();
                }
                throw th2;
            }
        } catch (Throwable th8) {
            th2 = th8;
            outputStream = null;
            socket = null;
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream2 != null) {
                inputStream2.close();
            }
            if (socket != null) {
                socket.close();
            }
            throw th2;
        }
    }

    private static byte[] a(String str, byte[] bArr) throws Exception {
        if (str == null || bArr == null) {
            return null;
        }
        for (int length = str.length(); length < 16; length++) {
            str = new StringBuilder(String.valueOf(str)).append("0").toString();
        }
        String substring = str.substring(0, 16);
        Key secretKeySpec = new SecretKeySpec(substring.getBytes(), "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(2, secretKeySpec, new IvParameterSpec(substring.getBytes()));
        return instance.doFinal(bArr);
    }

    private static String i(String str) {
        String str2 = "";
        if (str == null) {
            return str2;
        }
        str2 = str.replace("=", "%3D").replace("/", "%2F").replace("+", "%2B");
        if (str2.length() > 1024) {
            return str2 + VoiceWakeuperAidl.PARAMS_SEPARATE;
        }
        return str2;
    }

    public static boolean a(Context context, h hVar) {
        boolean z = true;
        boolean z2 = false;
        synchronized (e.a) {
            if (context == null || hVar == null) {
                com.tencent.beacon.e.a.c("context == null || bean == null}", new Object[0]);
            } else {
                try {
                    SQLiteDatabase writableDatabase = e.a(context).getWritableDatabase();
                    if (writableDatabase == null) {
                        com.tencent.beacon.e.a.d("get db fail!,return false ", new Object[0]);
                    } else {
                        ContentValues contentValues;
                        if (hVar == null) {
                            contentValues = null;
                        } else {
                            contentValues = new ContentValues();
                            if (hVar.a() >= 0) {
                                contentValues.put("_id", Long.valueOf(hVar.a()));
                            }
                            contentValues.put("_key", Integer.valueOf(hVar.b()));
                            contentValues.put("_datas", hVar.c());
                        }
                        if (contentValues != null) {
                            long replace = writableDatabase.replace("t_strategy", "_id", contentValues);
                            if (replace < 0) {
                                com.tencent.beacon.e.a.c("insert failure! return false ", new Object[0]);
                            } else {
                                hVar.a(replace);
                                com.tencent.beacon.e.a.e("update strategy  %d true ", Integer.valueOf(hVar.b()));
                            }
                        } else {
                            z = false;
                        }
                        z2 = z;
                    }
                } catch (Throwable th) {
                    com.tencent.beacon.e.a.d("Error strategy update!  %s", th.toString());
                }
            }
        }
        return z2;
    }

    private static byte[] a(byte[] bArr) throws Exception {
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
        byte[] bArr2 = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = gZIPInputStream.read(bArr2, 0, bArr2.length);
            if (read == -1) {
                bArr2 = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                gZIPInputStream.close();
                byteArrayInputStream.close();
                return bArr2;
            }
            byteArrayOutputStream.write(bArr2, 0, read);
        }
    }

    public static String a(Map<String, String> map) {
        com.tencent.beacon.e.a.b("map 2 str", new Object[0]);
        if (map == null) {
            return "";
        }
        Set<String> keySet = map.keySet();
        if (keySet == null) {
            return "";
        }
        String str;
        if (keySet.size() > 50) {
            com.tencent.beacon.e.a.c("The Map<String, String> params size is more than 50, effective size is <= 50!", new Object[0]);
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String str2 : keySet) {
            int length = str2.trim().length();
            if (length <= 0 || !c(str2)) {
                com.tencent.beacon.e.a.c("The Map<String, String> params key is invalid!! key should be ASCII code in 32-126! key:" + str2, new Object[0]);
            } else {
                String trim = str2.trim();
                if (length > 64) {
                    trim = trim.substring(0, 64);
                }
                stringBuffer.append("&");
                stringBuffer.append(trim.replace(DLConstants.DEPENDENCY_PACKAGE_DIV, "%7C").replace("&", "%26").replace("=", "%3D"));
                stringBuffer.append("=");
                str2 = (String) map.get(str2);
                if (str2 != null) {
                    str2 = str2.trim();
                    if (str2.contains(VoiceWakeuperAidl.PARAMS_SEPARATE)) {
                        if (str2.length() > 10240) {
                            str2 = str2.substring(0, 10240);
                            str2 = str2.substring(0, str2.lastIndexOf(VoiceWakeuperAidl.PARAMS_SEPARATE));
                        }
                    } else if (str2.length() > 1024) {
                        str2 = str2.substring(0, 1024);
                    }
                    stringBuffer.append(str2.replace('\n', ' ').replace('\r', ' ').replace(DLConstants.DEPENDENCY_PACKAGE_DIV, "%7C").replace("&", "%26").replace("=", "%3D"));
                }
            }
        }
        str2 = stringBuffer.substring(1);
        stringBuffer.setLength(0);
        return str2;
    }

    private Activity e() {
        if (Integer.valueOf(VERSION.SDK).intValue() < 16) {
            return null;
        }
        try {
            Object className;
            Intent launchIntentForPackage = this.a.getPackageManager().getLaunchIntentForPackage(this.a.getPackageName());
            if (launchIntentForPackage != null) {
                className = launchIntentForPackage.getComponent().getClassName();
            } else {
                className = null;
            }
            if (className == null) {
                return null;
            }
            SparseArray a = com.tencent.beacon.b.g.a();
            if (a == null) {
                return null;
            }
            for (int i = 0; i < a.size(); i++) {
                WeakReference weakReference = (WeakReference) a.get(a.keyAt(i));
                if (!(weakReference == null || weakReference.get() == null)) {
                    Activity activity = (Activity) weakReference.get();
                    if (activity.getClass().getName().equals(className)) {
                        return activity;
                    }
                }
            }
            return null;
        } catch (Throwable e) {
            com.tencent.beacon.e.a.a(e);
        }
    }

    public static int b(Context context, f[] fVarArr) {
        if (context == null) {
            return -1;
        }
        if (fVarArr == null) {
            return com.tencent.beacon.b.a.a.a(context, new int[]{6}, Long.MAX_VALUE);
        }
        ArrayList arrayList = new ArrayList();
        for (f fVar : fVarArr) {
            if (fVar.a() >= 0) {
                arrayList.add(Long.valueOf(fVar.a()));
            }
        }
        if (arrayList.size() > 0) {
            return com.tencent.beacon.b.a.a.a(context, (Long[]) arrayList.toArray(new Long[0]));
        }
        return 0;
    }

    public static Long[] a(Context context, List<k> list) {
        com.tencent.beacon.e.a.a(" RecordDAO.insertList() start", new Object[0]);
        if (context == null || list == null) {
            com.tencent.beacon.e.a.d(" insertList() have null args!", new Object[0]);
            return null;
        }
        int size = list.size();
        if (size == 0) {
            com.tencent.beacon.e.a.b(" list siez == 0 , return true!", new Object[0]);
            return null;
        }
        Long[] lArr = new Long[size];
        List<com.tencent.beacon.b.a.a> arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            int i2;
            int i3;
            Object obj = (k) list.get(i);
            if (obj.b().equals("UA")) {
                i2 = 1;
                i3 = 3;
            } else if (obj.b().equals("IP")) {
                i2 = 2;
                i3 = 0;
            } else if (obj.b().equals("DN")) {
                i3 = 0;
                i2 = 3;
            } else if (obj.b().equals("HO")) {
                i2 = 4;
                i3 = 0;
            } else {
                com.tencent.beacon.e.a.d(" bean's type is error!", new Object[0]);
            }
            try {
                arrayList.add(new com.tencent.beacon.b.a.a(i2, i3, obj.c(), a(obj)));
            } catch (Throwable th) {
                com.tencent.beacon.e.a.a(th);
            }
        }
        if (!com.tencent.beacon.b.a.a.a(context, (List) arrayList)) {
            return null;
        }
        int i4 = 0;
        for (com.tencent.beacon.b.a.a aVar : arrayList) {
            if (i4 < size) {
                lArr[i4] = Long.valueOf(aVar.a());
            }
            i4++;
        }
        return lArr;
    }

    private static byte[] b(String str, byte[] bArr) throws Exception, NoSuchAlgorithmException {
        if (str == null || bArr == null) {
            return null;
        }
        for (int length = str.length(); length < 16; length++) {
            str = new StringBuilder(String.valueOf(str)).append("0").toString();
        }
        String substring = str.substring(0, 16);
        Key secretKeySpec = new SecretKeySpec(substring.getBytes(), "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(1, secretKeySpec, new IvParameterSpec(substring.getBytes()));
        return instance.doFinal(bArr);
    }

    public static int c(Context context, f[] fVarArr) {
        if (context == null || fVarArr == null || fVarArr.length <= 0) {
            return -1;
        }
        List arrayList = new ArrayList(fVarArr.length);
        for (Object obj : fVarArr) {
            byte[] a = a(obj);
            if (a != null) {
                com.tencent.beacon.b.a.a aVar = new com.tencent.beacon.b.a.a(7, 0, 0, a);
                aVar.a(obj.a());
                arrayList.add(aVar);
            }
        }
        if (arrayList.size() > 0) {
            return com.tencent.beacon.b.a.a.b(context, arrayList) ? arrayList.size() : -1;
        } else {
            return 0;
        }
    }

    public static byte[] a(Object obj) {
        ObjectOutputStream objectOutputStream;
        Throwable e;
        Throwable th;
        byte[] bArr = null;
        com.tencent.beacon.e.a.b("en obj 2 bytes ", new Object[0]);
        if (obj == null || !Serializable.class.isInstance(obj)) {
            com.tencent.beacon.e.a.c("not serial obj ", new Object[0]);
        } else {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                try {
                    objectOutputStream.writeObject(obj);
                    objectOutputStream.flush();
                    bArr = byteArrayOutputStream.toByteArray();
                    try {
                        objectOutputStream.close();
                    } catch (Throwable e2) {
                        com.tencent.beacon.e.a.a(e2);
                    }
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable e22) {
                        com.tencent.beacon.e.a.a(e22);
                    }
                } catch (Throwable th2) {
                    e22 = th2;
                    try {
                        com.tencent.beacon.e.a.a(e22);
                        com.tencent.beacon.e.a.d(e22.getMessage(), new Object[0]);
                        if (objectOutputStream != null) {
                            try {
                                objectOutputStream.close();
                            } catch (Throwable e222) {
                                com.tencent.beacon.e.a.a(e222);
                            }
                        }
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable e2222) {
                            com.tencent.beacon.e.a.a(e2222);
                        }
                        return bArr;
                    } catch (Throwable th3) {
                        th = th3;
                        if (objectOutputStream != null) {
                            try {
                                objectOutputStream.close();
                            } catch (Throwable e22222) {
                                com.tencent.beacon.e.a.a(e22222);
                            }
                        }
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable e222222) {
                            com.tencent.beacon.e.a.a(e222222);
                        }
                        throw th;
                    }
                }
            } catch (Throwable e2222222) {
                objectOutputStream = bArr;
                th = e2222222;
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                byteArrayOutputStream.close();
                throw th;
            }
        }
        return bArr;
    }

    public static byte[] a(String str) {
        if (str == null) {
            return null;
        }
        byte[] doFinal;
        try {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA", "BC").generatePublic(new X509EncodedKeySpec(Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsAxNCSLyNUCOP1QqYStE8ZeiU\nv4afaMqEmoLCKb0mUZYvYOoVN7LPMi2IVY2MRaFJvuND3glVw1RDm2VJJtjQkwUd\n3kpR9TrHAf7UQOVTpNo3Vi7pXTOqZ6bh3ZA/fs56jDCCKV6+wT/pCeu8N6vVnPrD\nz3SdHIeNeWb/woazCwIDAQAB", 0)));
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, rSAPublicKey);
            doFinal = instance.doFinal(str.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            doFinal = null;
        }
        return doFinal;
    }

    public static h c(Context context) {
        Throwable th;
        h hVar = null;
        synchronized (e.a) {
            if (context == null) {
                com.tencent.beacon.e.a.c("context == null}", new Object[0]);
                return null;
            }
            Cursor query;
            try {
                SQLiteDatabase writableDatabase = e.a(context).getWritableDatabase();
                if (writableDatabase == null) {
                    com.tencent.beacon.e.a.c("getWritableDatabase fail! ", new Object[0]);
                    return null;
                }
                query = writableDatabase.query("t_strategy", null, String.format(Locale.US, " %s = %d ", new Object[]{"_key", Integer.valueOf(101)}), null, null, null, null);
                if (query != null) {
                    try {
                        if (query.moveToNext()) {
                            if (!(query == null || query.isBeforeFirst() || query.isAfterLast())) {
                                com.tencent.beacon.e.a.b("parse bean}", new Object[0]);
                                r0 = new h();
                                r0.a(query.getLong(query.getColumnIndex("_id")));
                                r0.a(query.getInt(query.getColumnIndex("_key")));
                                r0.a(query.getBlob(query.getColumnIndex("_datas")));
                                hVar = r0;
                            }
                            if (hVar != null) {
                                com.tencent.beacon.e.a.g("read strategy key: %d", Integer.valueOf(hVar.b()));
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            com.tencent.beacon.e.a.a(th);
                            if (query != null) {
                                if (!query.isClosed()) {
                                    query.close();
                                    r0 = null;
                                    return r0;
                                }
                            }
                            r0 = hVar;
                            return r0;
                        } catch (Throwable th3) {
                            th = th3;
                            query.close();
                            throw th;
                        }
                    }
                }
                if (query != null) {
                    if (!query.isClosed()) {
                        query.close();
                        r0 = hVar;
                    }
                }
                r0 = hVar;
            } catch (Throwable th4) {
                th = th4;
                query = null;
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
                throw th;
            }
        }
    }

    public static List<f> b(Context context) {
        if (context == null) {
            return null;
        }
        List<com.tencent.beacon.b.a.a> a = com.tencent.beacon.b.a.a.a(context, new int[]{7}, -1, -1, 5, 0);
        if (a == null || a.size() <= 0) {
            return null;
        }
        List<f> arrayList = new ArrayList(a.size());
        for (com.tencent.beacon.b.a.a aVar : a) {
            try {
                f fVar = (f) f.class.cast(b(aVar.b()));
                fVar.a(aVar.a());
                arrayList.add(fVar);
            } catch (Throwable th) {
                com.tencent.beacon.e.a.a(th);
                com.tencent.beacon.e.a.d(" netconsume error:%s", th.toString());
            }
        }
        return arrayList;
    }

    public static List<k> a(Context context, int i) {
        com.tencent.beacon.e.a.a(" RecordDAO.queryRecentRecord() start", new Object[0]);
        if (context == null) {
            com.tencent.beacon.e.a.d(" queryRecentRecord() have null args!", new Object[0]);
            return null;
        }
        List a = com.tencent.beacon.b.a.a.a(context, new int[]{1, 2, 3, 4}, i);
        if (a == null || a.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = a.iterator();
        while (it.hasNext()) {
            com.tencent.beacon.b.a.a aVar = (com.tencent.beacon.b.a.a) it.next();
            try {
                Object b = b(aVar.b());
                if (b != null && k.class.isInstance(b)) {
                    k kVar = (k) k.class.cast(b);
                    kVar.a(aVar.a());
                    arrayList.add(kVar);
                    it.remove();
                }
            } catch (Throwable th) {
                com.tencent.beacon.e.a.a(th);
                com.tencent.beacon.e.a.d(" query have error!", new Object[0]);
            }
        }
        if (a.size() > 0) {
            com.tencent.beacon.e.a.a(" there are error datas ,should be remove " + a.size(), new Object[0]);
            Long[] lArr = new Long[a.size()];
            for (int i2 = 0; i2 < a.size(); i2++) {
                lArr[i2] = Long.valueOf(((com.tencent.beacon.b.a.a) a.get(i2)).a());
            }
            com.tencent.beacon.b.a.a.a(context, lArr);
        }
        com.tencent.beacon.e.a.a(" RecordDAO.queryRecentRecord() end", new Object[0]);
        return arrayList;
    }

    private static Object b(byte[] bArr) {
        ObjectInputStream objectInputStream;
        Object readObject;
        Throwable e;
        Throwable th;
        ObjectInputStream objectInputStream2 = null;
        com.tencent.beacon.e.a.b("de byte 2 obj ", new Object[0]);
        if (bArr != null && bArr.length >= 0) {
            InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                objectInputStream = new ObjectInputStream(byteArrayInputStream);
                try {
                    readObject = objectInputStream.readObject();
                    try {
                        objectInputStream.close();
                    } catch (Throwable e2) {
                        com.tencent.beacon.e.a.a(e2);
                    }
                    try {
                        byteArrayInputStream.close();
                    } catch (Throwable e22) {
                        com.tencent.beacon.e.a.a(e22);
                    }
                } catch (Throwable th2) {
                    e22 = th2;
                    try {
                        com.tencent.beacon.e.a.a(e22);
                        com.tencent.beacon.e.a.d(e22.getMessage(), new Object[0]);
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (Throwable e222) {
                                com.tencent.beacon.e.a.a(e222);
                            }
                        }
                        try {
                            byteArrayInputStream.close();
                        } catch (Throwable e2222) {
                            com.tencent.beacon.e.a.a(e2222);
                        }
                        return readObject;
                    } catch (Throwable th3) {
                        th = th3;
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (Throwable e22222) {
                                com.tencent.beacon.e.a.a(e22222);
                            }
                        }
                        try {
                            byteArrayInputStream.close();
                        } catch (Throwable e222222) {
                            com.tencent.beacon.e.a.a(e222222);
                        }
                        throw th;
                    }
                }
            } catch (Throwable e2222222) {
                objectInputStream = objectInputStream2;
                th = e2222222;
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                byteArrayInputStream.close();
                throw th;
            }
        }
        return readObject;
    }

    public static b a(String str, String str2, String str3) {
        InetAddress byName;
        byte[] address;
        int i;
        String[] split;
        Socket socket;
        String str4;
        OutputStream outputStream;
        InputStream inputStream;
        long currentTimeMillis;
        byte[] bArr;
        Throwable e;
        Throwable th;
        Throwable th2;
        InputStream inputStream2 = null;
        if (str == null || str.equals("") || str2 == null || str2.equals("") || str3 == null || str3.equals("")) {
            return null;
        }
        b bVar = new b();
        try {
            StringBuffer stringBuffer;
            SocketAddress inetSocketAddress;
            URL url = new URL("http://" + str2 + str3);
            long currentTimeMillis2 = System.currentTimeMillis();
            try {
                byName = InetAddress.getByName(str2);
                try {
                    bVar.a = System.currentTimeMillis() - currentTimeMillis2;
                } catch (Exception e2) {
                    bVar.a = -1;
                    com.tencent.beacon.e.a.a(" dns: }" + bVar.a, new Object[0]);
                    stringBuffer = new StringBuffer();
                    address = byName.getAddress();
                    i = 0;
                    while (i < address.length) {
                        stringBuffer.append("." + (address[i] < (byte) 0 ? address[i] : address[i] + 256));
                        i++;
                    }
                    bVar.f = stringBuffer.substring(1);
                    stringBuffer.setLength(0);
                    split = str.split(":");
                    inetSocketAddress = new InetSocketAddress(split[0], Integer.parseInt(split[1]));
                    currentTimeMillis2 = System.currentTimeMillis();
                    socket = new Socket();
                    socket.connect(inetSocketAddress, qalsdk.n.f);
                    bVar.b = System.currentTimeMillis() - currentTimeMillis2;
                    com.tencent.beacon.e.a.a(" conn: " + bVar.b, new Object[0]);
                    str4 = "HEAD " + (url.getPath() + (url.getQuery() != null ? "" : "?" + url.getQuery())) + " HTTP/1.1 \r\nHost: " + str2 + "\r\nConnection: close\r\n\r\n";
                    Log.i("IP", "host test: " + str4);
                    outputStream = socket.getOutputStream();
                    try {
                        inputStream = socket.getInputStream();
                        try {
                            currentTimeMillis2 = System.currentTimeMillis();
                            outputStream.write(str4.getBytes("UTF-8"));
                            outputStream.flush();
                            bVar.c = System.currentTimeMillis() - currentTimeMillis2;
                            com.tencent.beacon.e.a.a(" re: " + bVar.c, new Object[0]);
                            currentTimeMillis = System.currentTimeMillis();
                            inputStream.read();
                            bVar.d = System.currentTimeMillis() - currentTimeMillis;
                            com.tencent.beacon.e.a.a(" wait: " + bVar.d, new Object[0]);
                            bArr = new byte[http.Internal_Server_Error];
                            currentTimeMillis = System.currentTimeMillis();
                            inputStream.read(bArr);
                            bVar.e = System.currentTimeMillis() - currentTimeMillis;
                            com.tencent.beacon.e.a.a(" readp: " + bVar.e, new Object[0]);
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Throwable e3) {
                                    com.tencent.beacon.e.a.a(e3);
                                }
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Throwable e32) {
                                    com.tencent.beacon.e.a.a(e32);
                                }
                            }
                            try {
                                socket.close();
                                return bVar;
                            } catch (Throwable e322) {
                                com.tencent.beacon.e.a.a(e322);
                                com.tencent.beacon.e.a.d(e322.getMessage(), new Object[0]);
                                return bVar;
                            }
                        } catch (Throwable th3) {
                            e322 = th3;
                            try {
                                com.tencent.beacon.e.a.a(e322);
                                com.tencent.beacon.e.a.d(e322.getMessage(), new Object[0]);
                                if (outputStream != null) {
                                    try {
                                        outputStream.close();
                                    } catch (Throwable e3222) {
                                        com.tencent.beacon.e.a.a(e3222);
                                    }
                                }
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (Throwable e32222) {
                                        com.tencent.beacon.e.a.a(e32222);
                                    }
                                }
                                if (socket != null) {
                                    return bVar;
                                }
                                try {
                                    socket.close();
                                    return bVar;
                                } catch (Throwable e322222) {
                                    com.tencent.beacon.e.a.a(e322222);
                                    com.tencent.beacon.e.a.d(e322222.getMessage(), new Object[0]);
                                    return bVar;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                inputStream2 = inputStream;
                                if (outputStream != null) {
                                    try {
                                        outputStream.close();
                                    } catch (Throwable e4) {
                                        com.tencent.beacon.e.a.a(e4);
                                    }
                                }
                                if (inputStream2 != null) {
                                    try {
                                        inputStream2.close();
                                    } catch (Throwable e3222222) {
                                        com.tencent.beacon.e.a.a(e3222222);
                                    }
                                }
                                if (socket != null) {
                                    try {
                                        socket.close();
                                    } catch (Throwable e32222222) {
                                        com.tencent.beacon.e.a.a(e32222222);
                                        com.tencent.beacon.e.a.d(e32222222.getMessage(), new Object[0]);
                                    }
                                }
                                throw th;
                            }
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (inputStream2 != null) {
                            inputStream2.close();
                        }
                        if (socket != null) {
                            socket.close();
                        }
                        throw th;
                    }
                }
            } catch (Exception e5) {
                byName = null;
                bVar.a = -1;
                com.tencent.beacon.e.a.a(" dns: }" + bVar.a, new Object[0]);
                stringBuffer = new StringBuffer();
                address = byName.getAddress();
                i = 0;
                while (i < address.length) {
                    if (address[i] < (byte) 0) {
                    }
                    stringBuffer.append("." + (address[i] < (byte) 0 ? address[i] : address[i] + 256));
                    i++;
                }
                bVar.f = stringBuffer.substring(1);
                stringBuffer.setLength(0);
                split = str.split(":");
                inetSocketAddress = new InetSocketAddress(split[0], Integer.parseInt(split[1]));
                currentTimeMillis2 = System.currentTimeMillis();
                socket = new Socket();
                socket.connect(inetSocketAddress, qalsdk.n.f);
                bVar.b = System.currentTimeMillis() - currentTimeMillis2;
                com.tencent.beacon.e.a.a(" conn: " + bVar.b, new Object[0]);
                if (url.getQuery() != null) {
                }
                str4 = "HEAD " + (url.getPath() + (url.getQuery() != null ? "" : "?" + url.getQuery())) + " HTTP/1.1 \r\nHost: " + str2 + "\r\nConnection: close\r\n\r\n";
                Log.i("IP", "host test: " + str4);
                outputStream = socket.getOutputStream();
                inputStream = socket.getInputStream();
                currentTimeMillis2 = System.currentTimeMillis();
                outputStream.write(str4.getBytes("UTF-8"));
                outputStream.flush();
                bVar.c = System.currentTimeMillis() - currentTimeMillis2;
                com.tencent.beacon.e.a.a(" re: " + bVar.c, new Object[0]);
                currentTimeMillis = System.currentTimeMillis();
                inputStream.read();
                bVar.d = System.currentTimeMillis() - currentTimeMillis;
                com.tencent.beacon.e.a.a(" wait: " + bVar.d, new Object[0]);
                bArr = new byte[http.Internal_Server_Error];
                currentTimeMillis = System.currentTimeMillis();
                inputStream.read(bArr);
                bVar.e = System.currentTimeMillis() - currentTimeMillis;
                com.tencent.beacon.e.a.a(" readp: " + bVar.e, new Object[0]);
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                socket.close();
                return bVar;
            }
            com.tencent.beacon.e.a.a(" dns: }" + bVar.a, new Object[0]);
            stringBuffer = new StringBuffer();
            address = byName.getAddress();
            i = 0;
            while (i < address.length) {
                if (address[i] < (byte) 0) {
                }
                stringBuffer.append("." + (address[i] < (byte) 0 ? address[i] : address[i] + 256));
                i++;
            }
            bVar.f = stringBuffer.substring(1);
            stringBuffer.setLength(0);
            split = str.split(":");
            inetSocketAddress = new InetSocketAddress(split[0], Integer.parseInt(split[1]));
            currentTimeMillis2 = System.currentTimeMillis();
            socket = new Socket();
            try {
                socket.connect(inetSocketAddress, qalsdk.n.f);
                bVar.b = System.currentTimeMillis() - currentTimeMillis2;
                com.tencent.beacon.e.a.a(" conn: " + bVar.b, new Object[0]);
                if (url.getQuery() != null) {
                }
                str4 = "HEAD " + (url.getPath() + (url.getQuery() != null ? "" : "?" + url.getQuery())) + " HTTP/1.1 \r\nHost: " + str2 + "\r\nConnection: close\r\n\r\n";
                Log.i("IP", "host test: " + str4);
                outputStream = socket.getOutputStream();
                inputStream = socket.getInputStream();
                currentTimeMillis2 = System.currentTimeMillis();
                outputStream.write(str4.getBytes("UTF-8"));
                outputStream.flush();
                bVar.c = System.currentTimeMillis() - currentTimeMillis2;
                com.tencent.beacon.e.a.a(" re: " + bVar.c, new Object[0]);
                currentTimeMillis = System.currentTimeMillis();
                inputStream.read();
                bVar.d = System.currentTimeMillis() - currentTimeMillis;
                com.tencent.beacon.e.a.a(" wait: " + bVar.d, new Object[0]);
                bArr = new byte[http.Internal_Server_Error];
                currentTimeMillis = System.currentTimeMillis();
                inputStream.read(bArr);
                bVar.e = System.currentTimeMillis() - currentTimeMillis;
                com.tencent.beacon.e.a.a(" readp: " + bVar.e, new Object[0]);
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                socket.close();
                return bVar;
            } catch (Throwable th6) {
                th = th6;
                outputStream = null;
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                if (socket != null) {
                    socket.close();
                }
                throw th;
            }
        } catch (Throwable th7) {
            th = th7;
            outputStream = null;
            socket = null;
        }
    }

    public static boolean c(String str) {
        boolean z = true;
        int length = str.length();
        while (true) {
            length--;
            if (length < 0) {
                return z;
            }
            char charAt = str.charAt(length);
            if (charAt < ' ' || charAt >= '') {
                z = false;
            }
        }
    }

    public static byte[] a(byte[] bArr, int i, String str) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        com.tencent.beacon.e.a.b("enD: %d %d", Integer.valueOf(bArr.length), Integer.valueOf(i));
        if (i != 1) {
            return i == 3 ? b(str, bArr) : null;
        } else {
            if (str == null || bArr == null) {
                return null;
            }
            try {
                Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
                instance.init(1, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes("UTF-8"))), new IvParameterSpec(str.getBytes("UTF-8")));
                return instance.doFinal(bArr);
            } catch (Throwable th) {
                com.tencent.beacon.e.a.a(th);
                com.tencent.beacon.e.a.d("err enD: %s", th.toString());
                return null;
            }
        }
    }

    public static int d(Context context) {
        Throwable th;
        int i = 0;
        synchronized (e.a) {
            if (context == null) {
                com.tencent.beacon.e.a.c("context == null ||key < -1}", new Object[0]);
            } else {
                int delete;
                try {
                    SQLiteDatabase writableDatabase = e.a(context).getWritableDatabase();
                    if (writableDatabase == null) {
                        com.tencent.beacon.e.a.d("get db fail!,return ", new Object[0]);
                    } else {
                        delete = writableDatabase.delete("t_strategy", String.format("%s = %d", new Object[]{"_key", Integer.valueOf(101)}), null);
                        try {
                            com.tencent.beacon.e.a.g("delete Strategy key} %d , num} %d", Integer.valueOf(101), Integer.valueOf(delete));
                            i = delete;
                        } catch (Throwable th2) {
                            th = th2;
                            com.tencent.beacon.e.a.a(th);
                            i = delete;
                            return i;
                        }
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    delete = 0;
                    th = th4;
                    com.tencent.beacon.e.a.a(th);
                    i = delete;
                    return i;
                }
            }
        }
        return i;
    }

    public static byte[] b(byte[] bArr, int i, String str) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        if (i != 1) {
            return i == 3 ? a(str, bArr) : null;
        } else {
            if (str == null || bArr == null) {
                return null;
            }
            try {
                Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
                instance.init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes("UTF-8"))), new IvParameterSpec(str.getBytes("UTF-8")));
                return instance.doFinal(bArr);
            } catch (Throwable th) {
                com.tencent.beacon.e.a.a(th);
                com.tencent.beacon.e.a.d("err unD: %s", th.toString());
                return null;
            }
        }
    }

    private static byte[] a(byte[] bArr, int i) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        com.tencent.beacon.e.a.b("zp: %s len: %s", Integer.valueOf(i), Integer.valueOf(bArr.length));
        OutputStream byteArrayOutputStream;
        if (i == 1) {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
                ZipEntry zipEntry = new ZipEntry("zip");
                zipEntry.setSize((long) bArr.length);
                zipOutputStream.putNextEntry(zipEntry);
                zipOutputStream.write(bArr);
                zipOutputStream.closeEntry();
                zipOutputStream.close();
                bArr = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return bArr;
            } catch (Throwable th) {
                com.tencent.beacon.e.a.a(th);
                com.tencent.beacon.e.a.d("err zp : %s", th.toString());
                return null;
            }
        } else if (i != 2) {
            return null;
        } else {
            byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.finish();
            gZIPOutputStream.close();
            bArr = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return bArr;
        }
    }

    private static byte[] b(byte[] bArr, int i) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        com.tencent.beacon.e.a.b("unzp: %s len: %s", Integer.valueOf(i), Integer.valueOf(bArr.length));
        if (i != 1) {
            return i == 2 ? a(bArr) : null;
        } else {
            try {
                InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
                ZipInputStream zipInputStream = new ZipInputStream(byteArrayInputStream);
                bArr = null;
                while (zipInputStream.getNextEntry() != null) {
                    byte[] bArr2 = new byte[1024];
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    while (true) {
                        int read = zipInputStream.read(bArr2, 0, bArr2.length);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr2, 0, read);
                    }
                    bArr = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                }
                zipInputStream.close();
                byteArrayInputStream.close();
                return bArr;
            } catch (Throwable th) {
                com.tencent.beacon.e.a.a(th);
                com.tencent.beacon.e.a.d("err unzp}" + th.toString(), new Object[0]);
                return null;
            }
        }
    }

    public static b a(String str, String str2) {
        if (str != null) {
            if (!str.equals("")) {
                b bVar = new b();
                HttpURLConnection httpURLConnection = null;
                InputStream inputStream = null;
                OutputStream outputStream = null;
                try {
                    long currentTimeMillis;
                    long currentTimeMillis2;
                    long currentTimeMillis3;
                    long currentTimeMillis4;
                    long currentTimeMillis5;
                    URL url = new URL(str);
                    SocketAddress socketAddress = null;
                    String defaultHost = Proxy.getDefaultHost();
                    int defaultPort = Proxy.getDefaultPort();
                    if (defaultHost == null || defaultHost.trim().length() == 0 || defaultPort <= 0) {
                        a aVar;
                        com.tencent.beacon.e.a.a(" no default proxy!", new Object[0]);
                        if (str2 == null || str2.trim().length() <= 0) {
                            aVar = null;
                        } else {
                            aVar = new a();
                            defaultHost = str2.toLowerCase();
                            if (defaultHost.contains(APNName.NAME_CTWAP)) {
                                com.tencent.beacon.e.a.a(" search ctwap", new Object[0]);
                                aVar.a = "10.0.0.200";
                                aVar.b = 80;
                            } else if (defaultHost.contains(APNName.NAME_CMWAP)) {
                                com.tencent.beacon.e.a.a(" search cmwap", new Object[0]);
                                aVar.a = "10.0.0.172";
                                aVar.b = 80;
                            } else if (defaultHost.contains(APNName.NAME_UNIWAP)) {
                                com.tencent.beacon.e.a.a(" search uniwap", new Object[0]);
                                aVar.a = "10.0.0.172";
                                aVar.b = 80;
                            }
                        }
                        if (aVar == null) {
                            socketAddress = null;
                            if (socketAddress != null) {
                                httpURLConnection = (HttpURLConnection) url.openConnection();
                            } else {
                                httpURLConnection = (HttpURLConnection) url.openConnection(new java.net.Proxy(Type.HTTP, socketAddress));
                            }
                            httpURLConnection.setDoInput(true);
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.setConnectTimeout(qalsdk.n.f);
                            httpURLConnection.setReadTimeout(qalsdk.n.f);
                            currentTimeMillis = System.currentTimeMillis();
                            httpURLConnection.connect();
                            currentTimeMillis2 = System.currentTimeMillis();
                            outputStream = httpURLConnection.getOutputStream();
                            currentTimeMillis3 = System.currentTimeMillis();
                            inputStream = httpURLConnection.getInputStream();
                            currentTimeMillis4 = System.currentTimeMillis();
                            do {
                            } while (inputStream.read() != -1);
                            currentTimeMillis5 = System.currentTimeMillis();
                            bVar.a = -1;
                            bVar.f = "";
                            bVar.b = currentTimeMillis2 - currentTimeMillis;
                            bVar.c = currentTimeMillis3 - currentTimeMillis2;
                            bVar.d = currentTimeMillis4 - currentTimeMillis3;
                            bVar.e = currentTimeMillis5 - currentTimeMillis4;
                            com.tencent.beacon.e.a.a(" dns:" + bVar.a, new Object[0]);
                            com.tencent.beacon.e.a.a(" connetionElapse:" + bVar.b, new Object[0]);
                            com.tencent.beacon.e.a.a(" requestElapse:" + bVar.c, new Object[0]);
                            com.tencent.beacon.e.a.a(" waitElapse:" + bVar.d, new Object[0]);
                            com.tencent.beacon.e.a.a(" readResponeElapse:" + bVar.e, new Object[0]);
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Throwable e) {
                                    com.tencent.beacon.e.a.a(e);
                                }
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Throwable e2) {
                                    com.tencent.beacon.e.a.a(e2);
                                }
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            return bVar;
                        }
                        defaultHost = aVar.a;
                        defaultPort = aVar.b;
                    }
                    if (defaultHost != null && defaultHost.trim().length() > 0) {
                        socketAddress = new InetSocketAddress(defaultHost, defaultPort);
                        com.tencent.beacon.e.a.a(" apn }" + str2 + "  find proxy [}" + defaultHost + ":" + defaultPort + "]", new Object[0]);
                    }
                    if (socketAddress != null) {
                        httpURLConnection = (HttpURLConnection) url.openConnection(new java.net.Proxy(Type.HTTP, socketAddress));
                    } else {
                        httpURLConnection = (HttpURLConnection) url.openConnection();
                    }
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setConnectTimeout(qalsdk.n.f);
                    httpURLConnection.setReadTimeout(qalsdk.n.f);
                    currentTimeMillis = System.currentTimeMillis();
                    httpURLConnection.connect();
                    currentTimeMillis2 = System.currentTimeMillis();
                    outputStream = httpURLConnection.getOutputStream();
                    currentTimeMillis3 = System.currentTimeMillis();
                    inputStream = httpURLConnection.getInputStream();
                    currentTimeMillis4 = System.currentTimeMillis();
                    do {
                    } while (inputStream.read() != -1);
                    currentTimeMillis5 = System.currentTimeMillis();
                    bVar.a = -1;
                    bVar.f = "";
                    bVar.b = currentTimeMillis2 - currentTimeMillis;
                    bVar.c = currentTimeMillis3 - currentTimeMillis2;
                    bVar.d = currentTimeMillis4 - currentTimeMillis3;
                    bVar.e = currentTimeMillis5 - currentTimeMillis4;
                    com.tencent.beacon.e.a.a(" dns:" + bVar.a, new Object[0]);
                    com.tencent.beacon.e.a.a(" connetionElapse:" + bVar.b, new Object[0]);
                    com.tencent.beacon.e.a.a(" requestElapse:" + bVar.c, new Object[0]);
                    com.tencent.beacon.e.a.a(" waitElapse:" + bVar.d, new Object[0]);
                    com.tencent.beacon.e.a.a(" readResponeElapse:" + bVar.e, new Object[0]);
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                } catch (Throwable th) {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Throwable e3) {
                            com.tencent.beacon.e.a.a(e3);
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable e32) {
                            com.tencent.beacon.e.a.a(e32);
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
                return bVar;
            }
        }
        return null;
    }

    public static com.tencent.beacon.c.a.b a(int i, d dVar, byte[] bArr, int i2, int i3) {
        com.tencent.beacon.e.a.b("en2Req", new Object[0]);
        if (dVar == null) {
            com.tencent.beacon.e.a.d("error no com info! ", new Object[0]);
            return null;
        }
        try {
            com.tencent.beacon.c.a.b bVar = new com.tencent.beacon.c.a.b();
            synchronized (dVar) {
                bVar.j = dVar.a();
                bVar.k = dVar.b();
                bVar.a = dVar.c();
                bVar.b = dVar.j();
                bVar.c = dVar.d();
                bVar.d = dVar.e();
                bVar.e = dVar.f();
                bVar.l = "";
                if (i == 100) {
                    Map hashMap = new HashMap();
                    hashMap.put("A1", UserAction.getUserID());
                    hashMap.put("A2", dVar.i());
                    com.tencent.beacon.event.d a = com.tencent.beacon.event.d.a(dVar.l());
                    hashMap.put("A4", a.d());
                    hashMap.put("A6", a.c());
                    hashMap.put("A7", a.e());
                    hashMap.put("A3", j.a(dVar.l()).a());
                    hashMap.put("A23", dVar.k());
                    com.tencent.beacon.b.f.a(dVar.l());
                    hashMap.put("A33", com.tencent.beacon.b.f.l(dVar.l()));
                    if (com.tencent.beacon.b.b.j(dVar.l())) {
                        hashMap.put("A66", "F");
                    } else {
                        hashMap.put("A66", "B");
                    }
                    hashMap.put("A67", com.tencent.beacon.b.b.l(dVar.l()));
                    hashMap.put("A68", com.tencent.beacon.b.b.k(dVar.l()));
                    hashMap.put("A85", com.tencent.beacon.b.b.b ? "Y" : "N");
                    bVar.l = a(hashMap);
                }
            }
            bVar.f = i;
            bVar.h = (byte) i3;
            bVar.i = (byte) i2;
            if (bArr == null) {
                bArr = "".getBytes();
            }
            bVar.g = bArr;
            return bVar;
        } catch (Throwable th) {
            com.tencent.beacon.e.a.a(th);
            return null;
        }
    }

    public static int a(Context context, Long[] lArr) {
        com.tencent.beacon.e.a.a(" RecordDAO.deleteRecordList() start", new Object[0]);
        if (context == null) {
            com.tencent.beacon.e.a.d(" deleteRecordList() have null args!", new Object[0]);
            return -1;
        }
        com.tencent.beacon.e.a.a(" RecordDAO.deleteRecordList() end", new Object[0]);
        return com.tencent.beacon.b.a.a.a(context, lArr);
    }

    public static byte[] a(byte[] bArr, int i, int i2, String str) {
        byte[] bArr2 = null;
        if (bArr != null) {
            try {
                bArr2 = a(a(bArr, i), i2, str);
            } catch (Throwable th) {
                com.tencent.beacon.e.a.a(th);
            }
        }
        return bArr2;
    }

    public static byte[] b(byte[] bArr, int i, int i2, String str) {
        try {
            return b(b(bArr, i2, str), i);
        } catch (Throwable e) {
            com.tencent.beacon.e.a.a(e);
            return null;
        }
    }

    public static long c() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            return simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime();
        } catch (Throwable th) {
            com.tencent.beacon.e.a.a(th);
            return -1;
        }
    }

    public static String d() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date());
        } catch (Throwable th) {
            com.tencent.beacon.e.a.a(th);
            return "";
        }
    }

    public static String e(String str) {
        String str2 = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            str2 = EncodingUtils.getString(bArr, "UTF-8");
            fileInputStream.close();
            return str2;
        } catch (Exception e) {
            com.tencent.beacon.e.a.d("Read file %s failed.", str);
            return str2;
        }
    }

    public static String f(String str) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(str, "r");
            String readLine = randomAccessFile.readLine();
            if (readLine != null) {
                randomAccessFile.close();
                return readLine;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static String g(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            str = stringBuffer.toString();
        } catch (Throwable e) {
            com.tencent.beacon.e.a.a(e);
        }
        return str;
    }

    public static String h(String str) {
        String g = g(str);
        if (g != null) {
            try {
                g = g.substring(8, 24);
            } catch (Exception e) {
            }
        }
        return g;
    }

    public static String b(Context context, int i) {
        try {
            com.tencent.beacon.event.d a = com.tencent.beacon.event.d.a(context);
            String b = a.b();
            return h(b + "_" + a.c() + "_" + new Date().getTime() + "_" + i);
        } catch (Exception e) {
            return null;
        }
    }

    public static HashSet<String> a(ArrayList<String> arrayList) {
        int size = arrayList.size();
        if (arrayList == null || size <= 0) {
            return null;
        }
        HashSet<String> hashSet = new HashSet(size);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            hashSet.add((String) it.next());
        }
        return hashSet;
    }

    public static String g(Context context) {
        try {
            String e = com.tencent.beacon.event.d.a(context).e();
            String str = "";
            d m = d.m();
            if (m != null) {
                str = m.j();
            }
            return h(str + "_" + e + "_" + new Date().getTime() + "_" + ((int) (Math.random() * 2.147483647E9d)));
        } catch (Exception e2) {
            return null;
        }
    }
}

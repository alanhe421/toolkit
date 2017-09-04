package com.qq.reader.common.monitor;

import android.content.Context;
import android.text.format.Formatter;
import android.webkit.WebView;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.b;
import com.qq.reader.common.c.a;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.db.handle.s;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.ao;
import com.qq.reader.framework.mark.Mark;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.tencent.imsdk.QLogImpl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: ServerLog */
public class j {
    public static int[][] a = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{4, 200}));
    public static int b = 0;
    public static int c = 0;
    public static String d = "";
    public static int e = 0;
    public static int f = 0;
    public static int g = 0;
    public static int h = -1;
    public static int i = -1;
    public static int j = -1;
    public static int k = 0;
    public static int l = 0;
    public static Context m = null;
    public static boolean n = false;
    public static final String[] o = new String[]{"A", "B", "C", QLogImpl.TAG_REPORTLEVEL_DEVELOPER};
    public static long p = -1;
    public static int q = -1;
    public static HashMap<String, Set<Integer>> r = null;
    public static HashMap<String, String> s = null;
    public static ArrayList<String> t = null;
    private static boolean u = false;
    private static int v = 512000;
    private static int w = 10240;
    private static int x = 1024;
    private static String y = null;

    public static void a(Context context) {
        m = context;
        u = false;
        y = ao.c("-", ":");
        if (m != null) {
            b = d.X(m);
        }
        b = b + 1 >= Integer.MAX_VALUE ? b : b + 1;
        if (m != null) {
            d.k(m, b);
        }
    }

    public static void a(int i, int i2) {
        c.a("serverlog", "stat " + o[i2] + "" + (i + 1));
        try {
            int[] iArr = a[i2];
            iArr[i] = iArr[i] + 1;
        } catch (Exception e) {
            f.a("stat Exception :", e.toString());
        }
    }

    public static void a(Mark[] markArr) {
        try {
            if (t == null) {
                t = new ArrayList();
            } else {
                t.clear();
            }
            for (Mark mark : markArr) {
                if (mark != null) {
                    t.add(mark.getBookName());
                }
            }
        } catch (Exception e) {
        }
    }

    public static String a() {
        if (t == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        Object obj = null;
        try {
            Iterator it = t.iterator();
            while (it.hasNext()) {
                Object obj2;
                String str = (String) it.next();
                if (str != null) {
                    stringBuffer.append(str);
                    stringBuffer.append(",");
                    obj2 = 1;
                } else {
                    obj2 = obj;
                }
                obj = obj2;
            }
            if (obj != null) {
                stringBuffer.setLength(stringBuffer.length() - 1);
            } else {
                stringBuffer.append(" ");
            }
        } catch (Exception e) {
        }
        return stringBuffer.toString();
    }

    public static void a(String str, int i) {
        try {
            if (r == null) {
                r = new HashMap();
            }
            if (i == -1) {
                i = 0;
            }
            Set set = (Set) r.get(str);
            if (set == null) {
                set = new HashSet();
                r.put(str, set);
            }
            set.add(Integer.valueOf(i));
        } catch (Throwable th) {
        }
    }

    public static void a(int i) {
        if (s == null) {
            s = new HashMap();
        }
        try {
            String str = (String) s.get("" + i);
            if (str == null) {
                s.put("" + i, "1");
                return;
            }
            s.put("" + i, "" + (Integer.parseInt(str) + 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String b() {
        String str = " ";
        String stringBuffer;
        try {
            if (s == null || s.size() == 0) {
                return str;
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            for (Entry entry : s.entrySet()) {
                stringBuffer2.append((String) entry.getKey());
                stringBuffer2.append(":");
                stringBuffer2.append((String) entry.getValue());
                stringBuffer2.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
            }
            if (stringBuffer2.length() > 1) {
                stringBuffer2.setLength(stringBuffer2.length() - 1);
            }
            s.clear();
            stringBuffer = stringBuffer2.toString();
            return stringBuffer;
        } catch (Error e) {
            stringBuffer = str;
        }
    }

    public static String c() {
        String str = " ";
        String stringBuffer;
        try {
            if (r == null || r.size() == 0) {
                return str;
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            for (Entry entry : r.entrySet()) {
                stringBuffer2.append((String) entry.getKey());
                stringBuffer2.append(":");
                Iterator it = ((HashSet) entry.getValue()).iterator();
                while (it.hasNext()) {
                    stringBuffer2.append(((Integer) it.next()).intValue());
                    stringBuffer2.append(",");
                }
                stringBuffer2.replace(stringBuffer2.length() - 1, stringBuffer2.length(), VoiceWakeuperAidl.PARAMS_SEPARATE);
            }
            if (stringBuffer2.length() > 1) {
                stringBuffer2.setLength(stringBuffer2.length() - 1);
            }
            r.clear();
            stringBuffer = stringBuffer2.toString();
            return stringBuffer;
        } catch (Error e) {
            stringBuffer = str;
        }
    }

    private static String h() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return Formatter.formatIpAddress(inetAddress.hashCode());
                    }
                }
            }
        } catch (SocketException e) {
            f.a("WifiPreference IpAddress", e.toString());
        }
        return "error ip";
    }

    public static String d() {
        IOException e;
        Throwable th;
        Exception e2;
        g();
        File c = ao.c(a.az);
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(c, "r");
            try {
                byte[] bArr = new byte[((int) randomAccessFile.length())];
                randomAccessFile.read(bArr);
                String str = new String(bArr);
                if (randomAccessFile == null) {
                    return str;
                }
                try {
                    randomAccessFile.close();
                    return str;
                } catch (IOException e3) {
                    f.a("getUploadFile  finally Exception :", e3.toString());
                    return str;
                }
            } catch (IOException e4) {
                e = e4;
                try {
                    f.a("getUploadFile Exception :", e.toString());
                    if (c != null) {
                        c.delete();
                    }
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e5) {
                            f.a("getUploadFile  finally Exception :", e5.toString());
                        }
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e32) {
                            f.a("getUploadFile  finally Exception :", e32.toString());
                        }
                    }
                    throw th;
                }
            } catch (Exception e6) {
                e2 = e6;
                f.a("getUploadFile Exception :", e2.toString());
                if (c != null) {
                    c.delete();
                }
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e52) {
                        f.a("getUploadFile  finally Exception :", e52.toString());
                    }
                }
                return null;
            }
        } catch (IOException e7) {
            e52 = e7;
            randomAccessFile = null;
            f.a("getUploadFile Exception :", e52.toString());
            if (c != null) {
                c.delete();
            }
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            return null;
        } catch (Exception e8) {
            e2 = e8;
            randomAccessFile = null;
            f.a("getUploadFile Exception :", e2.toString());
            if (c != null) {
                c.delete();
            }
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            randomAccessFile = null;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw th;
        }
    }

    public static boolean b(Context context) {
        if (Calendar.getInstance().getTimeInMillis() > d.V(context.getApplicationContext())) {
            return true;
        }
        return false;
    }

    public static void e() {
        File c = ao.c(a.az);
        if (!(c == null || !c.delete() || m == null)) {
            d.av(m);
        }
        c = new File(a.aA);
        if (c.exists()) {
            c.delete();
        }
    }

    public static void f() {
        u = false;
    }

    public static void g() {
        RandomAccessFile randomAccessFile;
        Exception exception;
        Throwable th;
        File c = ao.c(a.az);
        String c2 = ao.c("-", ":");
        RandomAccessFile randomAccessFile2 = null;
        if (c != null) {
            try {
                randomAccessFile = new RandomAccessFile(c, "rw");
                try {
                    if (randomAccessFile.length() < ((long) w) && y != null) {
                        int i;
                        randomAccessFile.seek(randomAccessFile.length());
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("START=");
                        stringBuffer.append(y);
                        stringBuffer.append("\n");
                        stringBuffer.append("PROPERTY=");
                        stringBuffer.append("SEQ:");
                        stringBuffer.append(b);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("G1:");
                        stringBuffer.append(d.u(m));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("G2:");
                        stringBuffer.append(d.v(m));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        d.h(m, null);
                        stringBuffer.append("G3:");
                        stringBuffer.append(d.t(m));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        d.e(m, -1);
                        stringBuffer.append("N7:");
                        stringBuffer.append(i.c().d());
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N68:");
                        if (d.length() > 0) {
                            stringBuffer.append(d);
                        } else {
                            stringBuffer.append("none");
                        }
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        int e = i.c().e();
                        stringBuffer.append("N6:");
                        stringBuffer.append(e - i.c().d());
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N13:");
                        stringBuffer.append(e);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N5:");
                        stringBuffer.append(0);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("M9:");
                        stringBuffer.append(e);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("M22:");
                        stringBuffer.append(k);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        if (d.aY(m) != -1) {
                            stringBuffer.append("M12:");
                            stringBuffer.append(d.aY(m));
                            stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        }
                        stringBuffer.append("N4:");
                        String o = d.o(m);
                        if (d.B.equals(o)) {
                            o = "0";
                        }
                        stringBuffer.append(o);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N55:");
                        stringBuffer.append(c);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N1:");
                        stringBuffer.append(d.I(m));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N2:");
                        stringBuffer.append(d.ac(m));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N3:");
                        stringBuffer.append(d.ab(m));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N20:");
                        stringBuffer.append(d.D(m) + 1.0f);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N21:");
                        stringBuffer.append(d.E(m) + 1.0f);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N10:");
                        stringBuffer.append(d.af(m));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N65:");
                        stringBuffer.append(d.ao(m) ? 1 : 0);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N66:");
                        if (d.am(m)) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        stringBuffer.append(i);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N67:");
                        if (d.an(m)) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        stringBuffer.append(i);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N11:");
                        if (d.ah(m)) {
                            i = 1;
                        } else {
                            i = 2;
                        }
                        stringBuffer.append(i);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N12:");
                        stringBuffer.append(d.ai(m));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N16:");
                        stringBuffer.append(d.L(m));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N70:");
                        stringBuffer.append(d.C(m));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N72:");
                        o = d.R(m);
                        if (o == null) {
                            o = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
                        }
                        stringBuffer.append(o);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        int[] c3 = s.a().c();
                        stringBuffer.append("N17:");
                        stringBuffer.append(c3[1]);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N18:");
                        stringBuffer.append(c3[0]);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N84:");
                        stringBuffer.append(d.aw(m) ? 1 : 0);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N80:");
                        stringBuffer.append(h);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N81:");
                        stringBuffer.append(i);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N82:");
                        stringBuffer.append(j);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N85:");
                        stringBuffer.append(d.n ? 1 : 0);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N88:");
                        if (d.aC(m)) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        stringBuffer.append(i);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N89:");
                        stringBuffer.append(d.aJ(m));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N90:");
                        if (d.aa(m)) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        stringBuffer.append(i);
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N96:");
                        stringBuffer.append(d.M(m));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N91:");
                        stringBuffer.append(d.q(m));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N92:");
                        stringBuffer.append(a());
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        if (q > 0) {
                            stringBuffer.append("N93:");
                            stringBuffer.append(q);
                            stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        }
                        stringBuffer.append("N94:");
                        stringBuffer.append(d.aS(m));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("N96:");
                        stringBuffer.append(d.M(m));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("M78:");
                        stringBuffer.append(f);
                        f = 0;
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("M79:");
                        stringBuffer.append(g);
                        g = 0;
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("M76:");
                        stringBuffer.append(d.at(m));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append(i());
                        d.a();
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("M83:");
                        stringBuffer.append(m.f());
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("M84:");
                        stringBuffer.append(m.g());
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        m.e();
                        stringBuffer.append("M85:");
                        stringBuffer.append(b());
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("M29:");
                        stringBuffer.append(h.a().c());
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("M85:");
                        stringBuffer.append(d.bA(ReaderApplication.getApplicationImp()));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("XT1:");
                        stringBuffer.append(h.a().d(2));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("XT2:");
                        stringBuffer.append(h.a().b(2));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("XT3:");
                        stringBuffer.append(h.a().c(2));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("XT4:");
                        stringBuffer.append(h.a().e(2));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("XE1:");
                        stringBuffer.append(h.a().d(1));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("XE2:");
                        stringBuffer.append(h.a().b(1));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("XE3:");
                        stringBuffer.append(h.a().c(1));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("XE4:");
                        stringBuffer.append(h.a().e(1));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("XP1:");
                        stringBuffer.append(h.a().d(3));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("XP2:");
                        stringBuffer.append(h.a().b(3));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("XP3:");
                        stringBuffer.append(h.a().c(3));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("XP4:");
                        stringBuffer.append(h.a().e(3));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("XC1:");
                        stringBuffer.append(h.a().d(5));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("XC2:");
                        stringBuffer.append(h.a().b(5));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("XC3:");
                        stringBuffer.append(h.a().c(5));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("XC4:");
                        stringBuffer.append(h.a().e(5));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("XU1:");
                        stringBuffer.append(h.a().d(4));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("XU2:");
                        stringBuffer.append(h.a().b(4));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("XU3:");
                        stringBuffer.append(h.a().c(4));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("XU4:");
                        stringBuffer.append(h.a().e(4));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("XO1:");
                        stringBuffer.append(h.a().e(6));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        h.a().b();
                        stringBuffer.append("\n");
                        stringBuffer.append("OFFLINEREAD=");
                        stringBuffer.append(c());
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("\n");
                        stringBuffer.append("IP=");
                        stringBuffer.append(h());
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("\n");
                        stringBuffer.append("QQ=");
                        stringBuffer.append(d.R(m));
                        stringBuffer.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        stringBuffer.append("\n");
                        stringBuffer.append("END=");
                        stringBuffer.append(c2);
                        stringBuffer.append("\n");
                        randomAccessFile.write(stringBuffer.toString().getBytes());
                    }
                } catch (Exception e2) {
                    Exception exception2 = e2;
                    randomAccessFile2 = randomAccessFile;
                    exception = exception2;
                    try {
                        f.a("writeAccount Exception :", exception.toString(), exception);
                        if (c != null) {
                            c.delete();
                        }
                        n = false;
                        e = 0;
                        k = 0;
                        if (randomAccessFile2 != null) {
                            try {
                                randomAccessFile2.close();
                                return;
                            } catch (IOException e3) {
                                f.a("getUserID endLog finally Exception :", e3.toString());
                                return;
                            }
                        }
                        return;
                    } catch (Throwable th2) {
                        th = th2;
                        n = false;
                        e = 0;
                        k = 0;
                        if (randomAccessFile2 != null) {
                            try {
                                randomAccessFile2.close();
                            } catch (IOException e4) {
                                f.a("getUserID endLog finally Exception :", e4.toString());
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    randomAccessFile2 = randomAccessFile;
                    th = th4;
                    n = false;
                    e = 0;
                    k = 0;
                    if (randomAccessFile2 != null) {
                        randomAccessFile2.close();
                    }
                    throw th;
                }
            } catch (Exception e5) {
                exception = e5;
                f.a("writeAccount Exception :", exception.toString(), exception);
                if (c != null) {
                    c.delete();
                }
                n = false;
                e = 0;
                k = 0;
                if (randomAccessFile2 != null) {
                    randomAccessFile2.close();
                    return;
                }
                return;
            }
        }
        randomAccessFile = null;
        n = false;
        e = 0;
        k = 0;
        if (randomAccessFile != null) {
            try {
                randomAccessFile.close();
            } catch (IOException e32) {
                f.a("getUserID endLog finally Exception :", e32.toString());
            }
        }
    }

    private static String i() {
        String str = "";
        String str2 = "";
        for (int i = 0; i < a.length; i++) {
            String str3 = o[i];
            for (int i2 = 0; i2 < a[i].length; i2++) {
                if (a[i][i2] > 0) {
                    str = str + str3 + (i2 + 1) + ":" + a[i][i2] + VoiceWakeuperAidl.PARAMS_SEPARATE;
                    a[i][i2] = 0;
                }
            }
        }
        return str;
    }

    public static void a(String str) {
        RandomAccessFile randomAccessFile;
        UnsupportedEncodingException e;
        Throwable th;
        FileNotFoundException e2;
        Exception e3;
        if (b.d) {
            File c = ao.c(a.l + "cloudtask.log");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            if (c != null && c.length() > ((long) v)) {
                c.delete();
                c = ao.c(a.l + "qqreader.log");
            }
            RandomAccessFile randomAccessFile2 = null;
            try {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("[" + simpleDateFormat.format(new Date(System.currentTimeMillis())) + "]----");
                stringBuffer.append(str);
                stringBuffer.append("\n");
                randomAccessFile = new RandomAccessFile(c, "rw");
                try {
                    randomAccessFile.seek(randomAccessFile.length());
                    randomAccessFile.write(stringBuffer.toString().getBytes("UTF-8"));
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                } catch (UnsupportedEncodingException e5) {
                    e = e5;
                    try {
                        e.printStackTrace();
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e42) {
                                e42.printStackTrace();
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        randomAccessFile2 = randomAccessFile;
                        if (randomAccessFile2 != null) {
                            try {
                                randomAccessFile2.close();
                            } catch (IOException e6) {
                                e6.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e7) {
                    e2 = e7;
                    randomAccessFile2 = randomAccessFile;
                    try {
                        e2.printStackTrace();
                        if (randomAccessFile2 != null) {
                            try {
                                randomAccessFile2.close();
                            } catch (IOException e422) {
                                e422.printStackTrace();
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (randomAccessFile2 != null) {
                            randomAccessFile2.close();
                        }
                        throw th;
                    }
                } catch (Exception e8) {
                    e3 = e8;
                    randomAccessFile2 = randomAccessFile;
                    e3.printStackTrace();
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e4222) {
                            e4222.printStackTrace();
                        }
                    }
                }
            } catch (UnsupportedEncodingException e9) {
                e = e9;
                randomAccessFile = null;
                e.printStackTrace();
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (FileNotFoundException e10) {
                e2 = e10;
                e2.printStackTrace();
                if (randomAccessFile2 != null) {
                    randomAccessFile2.close();
                }
            } catch (Exception e11) {
                e3 = e11;
                e3.printStackTrace();
                if (randomAccessFile2 != null) {
                    randomAccessFile2.close();
                }
            }
        }
    }

    public static void b(String str) {
        g.a().a(new ServerLog$1(str));
    }

    public static void c(String str) {
        RandomAccessFile randomAccessFile;
        UnsupportedEncodingException e;
        Throwable th;
        FileNotFoundException e2;
        Exception e3;
        File c = ao.c(a.l + "qqreader.log");
        if (c != null && c.length() > ((long) v)) {
            c.delete();
            c = ao.c(a.l + "qqreader.log");
        }
        RandomAccessFile randomAccessFile2 = null;
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.sql.Date(System.currentTimeMillis())) + "]----");
            stringBuffer.append(str);
            stringBuffer.append("\n");
            randomAccessFile = new RandomAccessFile(c, "rw");
            try {
                randomAccessFile.seek(randomAccessFile.length());
                c.a("addDebugLog", stringBuffer.toString());
                randomAccessFile.write(stringBuffer.toString().getBytes("UTF-8"));
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            } catch (UnsupportedEncodingException e5) {
                e = e5;
                try {
                    e.printStackTrace();
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e42) {
                            e42.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    randomAccessFile2 = randomAccessFile;
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e7) {
                e2 = e7;
                randomAccessFile2 = randomAccessFile;
                try {
                    e2.printStackTrace();
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e422) {
                            e422.printStackTrace();
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (randomAccessFile2 != null) {
                        randomAccessFile2.close();
                    }
                    throw th;
                }
            } catch (Exception e8) {
                e3 = e8;
                randomAccessFile2 = randomAccessFile;
                e3.printStackTrace();
                if (randomAccessFile2 != null) {
                    try {
                        randomAccessFile2.close();
                    } catch (IOException e4222) {
                        e4222.printStackTrace();
                    }
                }
            }
        } catch (UnsupportedEncodingException e9) {
            e = e9;
            randomAccessFile = null;
            e.printStackTrace();
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        } catch (FileNotFoundException e10) {
            e2 = e10;
            e2.printStackTrace();
            if (randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
        } catch (Exception e11) {
            e3 = e11;
            e3.printStackTrace();
            if (randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
        }
    }

    public static void a(WebView webView, int i, int i2) {
        switch (i) {
            case 0:
                a(webView, i2);
                return;
            default:
                return;
        }
    }

    private static void a(WebView webView, int i) {
        webView.loadUrl("javascript:BookDetail.reportlog(" + i + ")");
    }
}

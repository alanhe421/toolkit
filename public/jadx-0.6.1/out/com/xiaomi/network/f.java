package com.xiaomi.network;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.Uri.Builder;
import android.net.wifi.WifiManager;
import android.os.Process;
import android.text.TextUtils;
import com.etrump.jni.ETConverter;
import com.qq.reader.module.question.fragment.FamousAuthorSayFragment;
import com.tencent.android.tpush.common.Constants;
import com.tencent.av.config.ConfigBaseParser;
import com.tencent.mid.api.MidEntity;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.channel.commonutils.d.d;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class f {
    protected static Map<String, b> b = new HashMap();
    protected static Context c;
    protected static boolean e = false;
    private static f l;
    private static a m;
    private static String n;
    private static String o;
    protected Map<String, c> a;
    protected b d;
    private e f;
    private String g;
    private long h;
    private final long i;
    private long j;
    private String k;

    public interface a {
        f a(Context context, e eVar, b bVar, String str);
    }

    public interface b {
        String a(String str);
    }

    protected f(Context context, e eVar, b bVar, String str) {
        this(context, eVar, bVar, str, null, null);
    }

    protected f(Context context, e eVar, b bVar, String str, String str2, String str3) {
        this.a = new HashMap();
        this.g = "0";
        this.h = 0;
        this.i = 15;
        this.j = 0;
        this.k = "isp_prov_city_country_ip";
        this.d = bVar;
        if (eVar == null) {
            this.f = new i(this);
        } else {
            this.f = eVar;
        }
        this.g = str;
        if (str2 == null) {
            str2 = context.getPackageName();
        }
        n = str2;
        if (str3 == null) {
            str3 = l();
        }
        o = str3;
    }

    public static synchronized f a() {
        f fVar;
        synchronized (f.class) {
            if (l == null) {
                throw new IllegalStateException("the host manager is not initialized yet.");
            }
            fVar = l;
        }
        return fVar;
    }

    private ArrayList<b> a(ArrayList<String> arrayList) {
        String str;
        int i;
        j();
        synchronized (this.a) {
            g();
            for (String str2 : this.a.keySet()) {
                if (!arrayList.contains(str2)) {
                    arrayList.add(str2);
                }
            }
        }
        boolean isEmpty = b.isEmpty();
        synchronized (b) {
            Object[] toArray = b.values().toArray();
            int length = toArray.length;
            int i2 = 0;
            while (i2 < length) {
                b bVar = (b) toArray[i2];
                if (!bVar.b()) {
                    isEmpty = true;
                    b.remove(bVar.b);
                }
                i2++;
                isEmpty = isEmpty;
            }
        }
        if (!arrayList.contains(c())) {
            arrayList.add(c());
        }
        ArrayList<b> arrayList2 = new ArrayList(arrayList.size());
        for (i = 0; i < arrayList.size(); i++) {
            arrayList2.add(null);
        }
        try {
            str2 = d.f(c) ? "wifi" : "wap";
            String a = a(arrayList, str2, this.g, isEmpty);
            if (!TextUtils.isEmpty(a)) {
                JSONObject jSONObject = new JSONObject(a);
                c.b(a);
                if ("OK".equalsIgnoreCase(jSONObject.getString("S"))) {
                    int i3;
                    jSONObject = jSONObject.getJSONObject("R");
                    String string = jSONObject.getString("province");
                    String string2 = jSONObject.getString("city");
                    String string3 = jSONObject.getString("isp");
                    String string4 = jSONObject.getString("ip");
                    String string5 = jSONObject.getString("country");
                    JSONObject jSONObject2 = jSONObject.getJSONObject(str2);
                    if (str2.equals("wap")) {
                        str2 = b();
                    }
                    c.a("get bucket: ip = " + string4 + " net = " + string3 + str2 + " hosts = " + jSONObject2.toString());
                    for (i3 = 0; i3 < arrayList.size(); i3++) {
                        str2 = (String) arrayList.get(i3);
                        JSONArray optJSONArray = jSONObject2.optJSONArray(str2);
                        if (optJSONArray == null) {
                            c.a("no bucket found for " + str2);
                        } else {
                            b bVar2 = new b(str2);
                            for (i = 0; i < optJSONArray.length(); i++) {
                                Object string6 = optJSONArray.getString(i);
                                if (!TextUtils.isEmpty(string6)) {
                                    bVar2.a(new k(string6, optJSONArray.length() - i));
                                }
                            }
                            arrayList2.set(i3, bVar2);
                            bVar2.g = string5;
                            bVar2.c = string;
                            bVar2.e = string3;
                            bVar2.f = string4;
                            bVar2.d = string2;
                            if (jSONObject.has("stat-percent")) {
                                bVar2.a(jSONObject.getDouble("stat-percent"));
                            }
                            if (jSONObject.has("stat-domain")) {
                                bVar2.c(jSONObject.getString("stat-domain"));
                            }
                            if (jSONObject.has("ttl")) {
                                bVar2.a(((long) jSONObject.getInt("ttl")) * 1000);
                            }
                            f(bVar2.e());
                        }
                    }
                    JSONObject optJSONObject = jSONObject.optJSONObject("reserved");
                    if (optJSONObject != null) {
                        long j = jSONObject.has("reserved-ttl") ? ((long) jSONObject.getInt("reserved-ttl")) * 1000 : FamousAuthorSayFragment.DATA_EXPIREDTIME_WEEK;
                        Iterator keys = optJSONObject.keys();
                        while (keys.hasNext()) {
                            str2 = (String) keys.next();
                            JSONArray optJSONArray2 = optJSONObject.optJSONArray(str2);
                            if (optJSONArray2 == null) {
                                c.a("no bucket found for " + str2);
                            } else {
                                b bVar3 = new b(str2);
                                bVar3.a(j);
                                for (i3 = 0; i3 < optJSONArray2.length(); i3++) {
                                    Object string7 = optJSONArray2.getString(i3);
                                    if (!TextUtils.isEmpty(string7)) {
                                        bVar3.a(new k(string7, optJSONArray2.length() - i3));
                                    }
                                }
                                synchronized (b) {
                                    if (this.f.a(str2)) {
                                        b.put(str2, bVar3);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            c.a("failed to get bucket " + e.getMessage());
        }
        for (i2 = 0; i2 < arrayList.size(); i2++) {
            bVar = (b) arrayList2.get(i2);
            if (bVar != null) {
                a((String) arrayList.get(i2), bVar);
            }
        }
        h();
        return arrayList2;
    }

    public static synchronized void a(Context context, e eVar, b bVar, String str, String str2, String str3) {
        synchronized (f.class) {
            c = context.getApplicationContext();
            if (c == null) {
                c = context;
            }
            if (l == null) {
                if (m == null) {
                    l = new f(context, eVar, bVar, str, str2, str3);
                } else {
                    l = m.a(context, eVar, bVar, str);
                }
            }
        }
    }

    public static synchronized void a(a aVar) {
        synchronized (f.class) {
            m = aVar;
            l = null;
        }
    }

    public static void a(String str, String str2) {
        b bVar = (b) b.get(str);
        synchronized (b) {
            if (bVar == null) {
                bVar = new b(str);
                bVar.a((long) FamousAuthorSayFragment.DATA_EXPIREDTIME_WEEK);
                bVar.b(str2);
                b.put(str, bVar);
            } else {
                bVar.b(str2);
            }
        }
    }

    static String b() {
        if (c == null) {
            return ConfigBaseParser.DEFAULT_VALUE;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) c.getSystemService("connectivity");
            if (connectivityManager == null) {
                return ConfigBaseParser.DEFAULT_VALUE;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return ConfigBaseParser.DEFAULT_VALUE;
            }
            if (activeNetworkInfo.getType() != 1) {
                return activeNetworkInfo.getTypeName() + "-" + activeNetworkInfo.getSubtypeName();
            }
            WifiManager wifiManager = (WifiManager) c.getSystemService("wifi");
            if (!(wifiManager == null || wifiManager.getConnectionInfo() == null)) {
                return "WIFI-" + wifiManager.getConnectionInfo().getSSID();
            }
            return ConfigBaseParser.DEFAULT_VALUE;
        } catch (Throwable th) {
        }
    }

    static String e(String str) {
        try {
            int length = str.length();
            byte[] bytes = str.getBytes("UTF-8");
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                if ((b & ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK) != ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK) {
                    bytes[i] = (byte) (((b & 15) ^ ((byte) (((b >> 4) + length) & 15))) | (b & ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK));
                }
            }
            return new String(bytes);
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    private String l() {
        try {
            PackageInfo packageInfo = c.getPackageManager().getPackageInfo(c.getPackageName(), 16384);
            if (packageInfo != null) {
                return packageInfo.versionName;
            }
        } catch (Exception e) {
        }
        return "0";
    }

    public b a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return a(new URL(str).getHost(), true);
        }
        throw new IllegalArgumentException("the url is empty");
    }

    public b a(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        } else if (!this.f.a(str)) {
            return null;
        } else {
            b c = c(str);
            if (c != null && c.b()) {
                return c;
            }
            if (z && d.d(c)) {
                b d = d(str);
                if (d != null) {
                    return d;
                }
            }
            return new j(this, str, c);
        }
    }

    protected String a(ArrayList<String> arrayList, String str, String str2, boolean z) {
        Iterator it;
        ArrayList arrayList2;
        ArrayList arrayList3 = new ArrayList();
        List<com.xiaomi.channel.commonutils.d.c> arrayList4 = new ArrayList();
        arrayList4.add(new com.xiaomi.channel.commonutils.d.a("type", str));
        if (str.equals("wap")) {
            arrayList4.add(new com.xiaomi.channel.commonutils.d.a("conpt", e(d.l(c))));
        }
        if (z) {
            arrayList4.add(new com.xiaomi.channel.commonutils.d.a("reserved", "1"));
        }
        arrayList4.add(new com.xiaomi.channel.commonutils.d.a("uuid", str2));
        arrayList4.add(new com.xiaomi.channel.commonutils.d.a("list", com.xiaomi.channel.commonutils.g.d.a((Collection) arrayList, ",")));
        b c = c("resolver.msg.xiaomi.net");
        String format = String.format(Locale.US, "http://%1$s/gslb/?ver=4.0", new Object[]{"resolver.msg.xiaomi.net"});
        if (c == null) {
            arrayList3.add(format);
            synchronized (b) {
                c = (b) b.get("resolver.msg.xiaomi.net");
                if (c != null) {
                    it = c.a(true).iterator();
                    while (it.hasNext()) {
                        String str3 = (String) it.next();
                        arrayList3.add(String.format(Locale.US, "http://%1$s/gslb/?ver=4.0", new Object[]{str3}));
                    }
                }
            }
            arrayList2 = arrayList3;
        } else {
            arrayList2 = c.a(format);
        }
        Iterator it2 = arrayList2.iterator();
        IOException iOException = null;
        while (it2.hasNext()) {
            Builder buildUpon = Uri.parse((String) it2.next()).buildUpon();
            for (com.xiaomi.channel.commonutils.d.c cVar : arrayList4) {
                buildUpon.appendQueryParameter(cVar.a(), cVar.b());
            }
            try {
                return this.d == null ? d.a(c, new URL(buildUpon.toString())) : this.d.a(buildUpon.toString());
            } catch (IOException e) {
                iOException = e;
            }
        }
        if (iOException == null) {
            return null;
        }
        c.a("network exception: " + iOException.getMessage());
        throw iOException;
    }

    public void a(String str, b bVar) {
        if (TextUtils.isEmpty(str) || bVar == null) {
            throw new IllegalArgumentException("the argument is invalid " + str + ", " + bVar);
        } else if (this.f.a(str)) {
            synchronized (this.a) {
                g();
                if (this.a.containsKey(str)) {
                    ((c) this.a.get(str)).a(bVar);
                } else {
                    c cVar = new c(str);
                    cVar.a(bVar);
                    this.a.put(str, cVar);
                }
            }
        }
    }

    public b b(String str) {
        return a(str, true);
    }

    protected b c(String str) {
        synchronized (this.a) {
            g();
            c cVar = (c) this.a.get(str);
        }
        if (cVar != null) {
            b a = cVar.a();
            if (a != null) {
                return a;
            }
        }
        return null;
    }

    protected String c() {
        return "resolver.msg.xiaomi.net";
    }

    protected b d(String str) {
        if (System.currentTimeMillis() - this.j > (this.h * 60) * 1000) {
            this.j = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            b bVar = (b) a(arrayList).get(0);
            if (bVar != null) {
                this.h = 0;
                return bVar;
            } else if (this.h < 15) {
                this.h++;
            }
        }
        return null;
    }

    public void d() {
        synchronized (this.a) {
            this.a.clear();
        }
    }

    public void e() {
        ArrayList arrayList;
        synchronized (this.a) {
            g();
            arrayList = new ArrayList(this.a.keySet());
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                c cVar = (c) this.a.get(arrayList.get(size));
                if (!(cVar == null || cVar.a() == null)) {
                    arrayList.remove(size);
                }
            }
        }
        ArrayList a = a(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            if (a.get(i) != null) {
                a((String) arrayList.get(i), (b) a.get(i));
            }
        }
    }

    protected String f() {
        String stringBuilder;
        Throwable th;
        Throwable th2;
        Reader reader = null;
        Reader bufferedReader;
        try {
            File file = new File(c.getFilesDir(), i());
            if (file.isFile()) {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                try {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder2.append(readLine);
                    }
                    stringBuilder = stringBuilder2.toString();
                    com.xiaomi.channel.commonutils.a.a.a(bufferedReader);
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        c.a("load host exception " + th.getMessage());
                        com.xiaomi.channel.commonutils.a.a.a(bufferedReader);
                        return stringBuilder;
                    } catch (Throwable th4) {
                        th2 = th4;
                        com.xiaomi.channel.commonutils.a.a.a(bufferedReader);
                        throw th2;
                    }
                }
            }
            com.xiaomi.channel.commonutils.a.a.a(reader);
        } catch (Throwable th5) {
            bufferedReader = reader;
            th2 = th5;
            com.xiaomi.channel.commonutils.a.a.a(bufferedReader);
            throw th2;
        }
        return stringBuilder;
    }

    public void f(String str) {
        this.k = str;
    }

    protected void g(String str) {
        int i = 0;
        synchronized (this.a) {
            this.a.clear();
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt(MidEntity.TAG_VER) != 2) {
                throw new JSONException("Bad version");
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                c a = new c().a(optJSONArray.getJSONObject(i2));
                this.a.put(a.c(), a);
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("reserved");
            while (i < optJSONArray2.length()) {
                b a2 = new b("").a(optJSONArray2.getJSONObject(i));
                b.put(a2.b, a2);
                i++;
            }
        }
    }

    protected boolean g() {
        synchronized (this.a) {
            if (e) {
                return true;
            }
            e = true;
            this.a.clear();
            try {
                Object f = f();
                if (!TextUtils.isEmpty(f)) {
                    g(f);
                    c.b("loading the new hosts succeed");
                    return true;
                }
            } catch (Throwable th) {
                c.a("load bucket failure: " + th.getMessage());
            }
        }
        return false;
    }

    public void h() {
        synchronized (this.a) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(c.openFileOutput(i(), 0)));
                Object jSONObject = k().toString();
                if (!TextUtils.isEmpty(jSONObject)) {
                    bufferedWriter.write(jSONObject);
                }
                bufferedWriter.close();
            } catch (Exception e) {
                c.a("persist bucket failure: " + e.getMessage());
            }
        }
    }

    protected String i() {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) c.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == Process.myPid()) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return "com.xiaomi";
    }

    public void j() {
        synchronized (this.a) {
            for (c a : this.a.values()) {
                a.a(true);
            }
            Object obj = null;
            while (obj == null) {
                for (String str : this.a.keySet()) {
                    if (((c) this.a.get(str)).b().isEmpty()) {
                        this.a.remove(str);
                        obj = null;
                        break;
                    }
                }
                obj = 1;
            }
        }
    }

    protected JSONObject k() {
        JSONObject jSONObject;
        synchronized (this.a) {
            jSONObject = new JSONObject();
            jSONObject.put(MidEntity.TAG_VER, 2);
            JSONArray jSONArray = new JSONArray();
            for (c d : this.a.values()) {
                jSONArray.put(d.d());
            }
            jSONObject.put("data", jSONArray);
            jSONArray = new JSONArray();
            for (b f : b.values()) {
                jSONArray.put(f.f());
            }
            jSONObject.put("reserved", jSONArray);
        }
        return jSONObject;
    }
}

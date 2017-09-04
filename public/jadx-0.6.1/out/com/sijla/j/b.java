package com.sijla.j;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.text.TextUtils;
import com.sijla.an.Order;
import com.sijla.bean.Info;
import com.sijla.bean.TruthInfo;
import com.sijla.j.a.a;
import com.sijla.j.a.c;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONObject;

@SuppressLint({"SimpleDateFormat", "DefaultLocale"})
public class b {
    public static String a(Context context, Info info) {
        StringBuffer stringBuffer = new StringBuffer();
        Class cls = info.getClass();
        if (cls.isAnnotationPresent(Order.class)) {
            String[] order = ((Order) cls.getAnnotation(Order.class)).order();
            if (order.length > 0) {
                String property = System.getProperty("line.separator", "\n");
                for (int i = 0; i < order.length; i++) {
                    try {
                        stringBuffer.append(e.a((Object) info, order[i]));
                        if (i == order.length - 1) {
                            stringBuffer.append(property);
                        } else {
                            stringBuffer.append("\t");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return stringBuffer.toString();
    }

    public static double a(double d, double d2, double d3, double d4) {
        double d5 = (3.141592653589793d * d2) / 180.0d;
        double d6 = (3.141592653589793d * d4) / 180.0d;
        double d7 = ((d - d3) * 3.141592653589793d) / 180.0d;
        double sin = Math.sin((d5 - d6) / 2.0d);
        d7 = Math.sin(d7 / 2.0d);
        double asin = (6378137.0d * 2.0d) * Math.asin(Math.sqrt((((Math.cos(d5) * Math.cos(d6)) * d7) * d7) + (sin * sin)));
        f.a("Distance=" + asin + " m");
        return asin;
    }

    public static boolean a(String str) {
        if (str == null || "".equals(str) || "null".equalsIgnoreCase(str)) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt != ' ' && charAt != '\t' && charAt != '\r' && charAt != '\n') {
                return false;
            }
        }
        return true;
    }

    public static String a() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static String b() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String c() {
        try {
            return new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            return "";
        }
    }

    public static String a(long j) {
        return new SimpleDateFormat("yyyyMMdd").format(Long.valueOf(j));
    }

    @SuppressLint({"SimpleDateFormat"})
    public static String b(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(j));
    }

    @SuppressLint({"SimpleDateFormat"})
    public static String c(long j) {
        return new SimpleDateFormat("HH:mm:ss").format(Long.valueOf(j));
    }

    @SuppressLint({"SimpleDateFormat"})
    public static String d() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public static void a(String str, String str2) {
    }

    public static String e() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static StringBuilder b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        stringBuilder.append(File.separator);
        stringBuilder.append(str);
        stringBuilder.append(File.separator);
        return stringBuilder;
    }

    public static List<String> f() {
        List arrayList = new ArrayList();
        arrayList.add(b("sitemp").toString() + "uuid");
        arrayList.add(b("at").toString() + "uuid");
        arrayList.add(b("MQ").toString() + "uuid");
        return arrayList;
    }

    public static List<String> a(Context context) {
        List arrayList = new ArrayList();
        arrayList.add(b(".Android").toString() + c.a(context.getPackageName() + ".Android"));
        arrayList.add(b("Android").toString() + c.a(context.getPackageName() + "Android"));
        arrayList.add(b("qmt").toString() + c.a(context.getPackageName() + "qmt"));
        arrayList.add(b("setup").toString() + c.a(context.getPackageName() + "setup"));
        return arrayList;
    }

    public static long g() {
        return System.currentTimeMillis() / 1000;
    }

    @SuppressLint({"SimpleDateFormat"})
    public static String h() {
        try {
            return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            return "";
        }
    }

    public static int i() {
        return 60 - Calendar.getInstance().get(12);
    }

    public static String j() {
        return b(".qmt" + File.separator + "Qt").toString();
    }

    public static String k() {
        return b(".qmt" + File.separator + "QD").toString();
    }

    public static String l() {
        return b(".qmt" + File.separator + ".mdau").toString();
    }

    public static String b(Context context) {
        if (a.a()) {
            return b(".qmt").toString();
        }
        return context.getFilesDir().getAbsolutePath() + File.separator;
    }

    public static String c(Context context) {
        return context.getFilesDir().getAbsolutePath() + File.separator + ".qmt";
    }

    public static String d(Context context) {
        return b(context) + ".lg/lg2";
    }

    public static String[] m() {
        String str = g() + "";
        return new String[]{c.a(str).substring(0, 8), str};
    }

    public static String a(JSONArray jSONArray) {
        try {
            String a = a(8);
            return a + com.sijla.f.b.a(a, jSONArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject c(String str) {
        if (!a(str)) {
            try {
                return new JSONObject(com.sijla.f.b.b(str.substring(0, 8), str.substring(8, str.length())));
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static JSONArray d(String str) {
        if (!a(str)) {
            try {
                return new JSONArray(com.sijla.f.b.b(str.substring(0, 8), str.substring(8, str.length())));
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static String a(int i) {
        String str = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(str.charAt(random.nextInt(str.length())));
        }
        return stringBuffer.toString();
    }

    public static int a(int i, int i2) {
        return (int) Math.round((Math.random() * ((double) (i2 - i))) + ((double) i));
    }

    public static long a(long j, long j2) {
        return Math.round((Math.random() * ((double) (j2 - j))) + ((double) j));
    }

    public static String b(String str, String str2) {
        try {
            return com.sijla.f.b.a(str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    public static byte[] e(String str) {
        ByteArrayOutputStream byteArrayOutputStream;
        IOException e;
        Throwable th;
        GZIPOutputStream gZIPOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream2.write(str.getBytes());
                    if (gZIPOutputStream2 != null) {
                        try {
                            gZIPOutputStream2.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (IOException e3) {
                    e2 = e3;
                    gZIPOutputStream = gZIPOutputStream2;
                    try {
                        e2.printStackTrace();
                        if (gZIPOutputStream != null) {
                            try {
                                gZIPOutputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        return byteArrayOutputStream.toByteArray();
                    } catch (Throwable th2) {
                        th = th2;
                        if (gZIPOutputStream != null) {
                            try {
                                gZIPOutputStream.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    gZIPOutputStream = gZIPOutputStream2;
                    if (gZIPOutputStream != null) {
                        gZIPOutputStream.close();
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e222 = e4;
                e222.printStackTrace();
                if (gZIPOutputStream != null) {
                    gZIPOutputStream.close();
                }
                return byteArrayOutputStream.toByteArray();
            }
        } catch (IOException e5) {
            e222 = e5;
            byteArrayOutputStream = null;
            e222.printStackTrace();
            if (gZIPOutputStream != null) {
                gZIPOutputStream.close();
            }
            return byteArrayOutputStream.toByteArray();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static String a(byte[] bArr) {
        GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(bArr));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr2 = new byte[1024];
        while (true) {
            int read = gZIPInputStream.read(bArr2);
            if (read != -1) {
                byteArrayOutputStream.write(bArr2, 0, read);
            } else {
                gZIPInputStream.close();
                byteArrayOutputStream.flush();
                return new String(byteArrayOutputStream.toByteArray(), "utf-8");
            }
        }
    }

    public static String f(String str) {
        return str + ".csv-" + h();
    }

    public static com.sijla.common.c a(Context context, String str, String str2) {
        boolean z = false;
        com.sijla.common.c cVar = new com.sijla.common.c();
        CharSequence j = j();
        File file = new File(j);
        if (!TextUtils.isEmpty(j)) {
            for (File file2 : file.listFiles()) {
                String name = file2.getName();
                if (file2.isFile() && name.startsWith(str) && !name.endsWith(".lock")) {
                    Object absolutePath = file2.getAbsolutePath();
                    if (str.equals("AK") || str.equals("AS")) {
                        z = name.contains(str2);
                    } else if (str.equals("UA")) {
                        z = c().equals(a(file2.lastModified()));
                    }
                    if (z) {
                        cVar.a(absolutePath);
                        f.a("CHKDATA:" + str + " NAME:" + name + " status:" + z);
                    }
                }
            }
        }
        cVar.a(z);
        return cVar;
    }

    public static void a(List<String> list) {
        for (String b : f()) {
            com.sijla.common.b.e().b(b, list);
        }
    }

    public static void a(Context context, TruthInfo truthInfo) {
        byte[] e = e(a(context, (Info) truthInfo).replace(System.getProperty("line.separator", "\n"), ""));
        for (String a : a(context)) {
            com.sijla.j.a.b.a(a, e);
        }
    }

    public static boolean e(Context context) {
        return a.c(context, "com.baidu.location.f");
    }

    public static boolean f(Context context) {
        try {
            ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 2).receivers;
            if (activityInfoArr == null) {
                return false;
            }
            for (ActivityInfo activityInfo : activityInfoArr) {
                if (activityInfo.name.equals("com.sijla.common.HBL")) {
                    return true;
                }
            }
            return false;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean g(Context context) {
        return !a(b(context, "com.amap.api.v2.apikey"));
    }

    public static String h(Context context) {
        return context.getSharedPreferences("arch", 0).getString("loc", "");
    }

    public static String i(Context context) {
        return context.getSharedPreferences("arch", 0).getString("sadr", "emt");
    }

    public static String j(Context context) {
        return a(context, g() + "");
    }

    public static String a(Context context, String str) {
        context.getSharedPreferences("arch", 0).edit().putString("loc", str).commit();
        return str;
    }

    public static void k(Context context) {
        try {
            ArrayList arrayList = new ArrayList();
            List<String> n = n();
            if (n != null) {
                String p = p(context);
                for (String str : n) {
                    String b = a.b(context, str);
                    if (!a(b)) {
                        ArrayList arrayList2 = new ArrayList();
                        f.a(str + "  = " + b);
                        arrayList2.add(p);
                        arrayList2.add(b);
                        arrayList2.add(str);
                        arrayList.add(arrayList2);
                    }
                }
            }
            com.sijla.common.b.e().a(f("AN"), arrayList);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static String l(Context context) {
        return "";
    }

    public static String m(Context context) {
        String str = (String) j.b(context, "QTChannel", "cnull");
        if (a(str)) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (applicationInfo != null) {
                    str = applicationInfo.metaData.getString("QTChannel");
                }
            } catch (Exception e) {
            }
        }
        return str;
    }

    public static String n(Context context) {
        return (String) j.b(context, "firstChannel", "null");
    }

    public static boolean a(Context context, String str, long j) {
        boolean z = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("arch", 0);
        long j2 = sharedPreferences.getLong(str, 0);
        long g = g();
        if (g - j2 > j) {
            z = true;
        }
        if (z) {
            sharedPreferences.edit().putLong(str, g).commit();
        }
        return z;
    }

    public static boolean g(String str) {
        if (!str.startsWith("u0_") || !str.contains(".") || str.contains("<") || str.contains("=") || str.contains("/")) {
            return false;
        }
        return true;
    }

    public static List<String> h(String str) {
        return a(str, Runtime.getRuntime());
    }

    public static List<String> a(String str, Runtime runtime) {
        List arrayList = new ArrayList();
        Process exec = runtime.exec(str);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        String str2 = "";
        while (true) {
            str2 = bufferedReader.readLine();
            if (str2 == null) {
                try {
                    break;
                } catch (InterruptedException e) {
                }
            } else if (g(str2)) {
                arrayList.add(str2);
            }
        }
        return exec.waitFor() != 0 ? arrayList : arrayList;
    }

    public static void o(Context context) {
        try {
            List<String> n = n();
            ArrayList arrayList = new ArrayList();
            String p = p(context);
            String str = g() + "";
            for (String str2 : n) {
                String a = a.a(str2, context);
                Object obj = a.a(context, str2) ? "1" : "0";
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(p);
                arrayList2.add("3");
                arrayList2.add(str2);
                arrayList2.add(obj);
                arrayList2.add(a);
                arrayList2.add(str);
                arrayList.add(arrayList2);
            }
            File file = new File(j());
            if (file != null) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (File file2 : listFiles) {
                        if (file2 != null && file2.exists() && file2.getName().startsWith("UA")) {
                            f.a("delete old ua file:" + file2.getName());
                            file2.delete();
                        }
                    }
                }
            }
            com.sijla.common.b.e().a(f("UA"), arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<String> n() {
        try {
            List<String> arrayList = new ArrayList();
            File file = new File(Environment.getExternalStorageDirectory() + "/Android/data/");
            f.a("androidDir = " + file);
            if (file != null && file.isDirectory()) {
                File[] listFiles = file.listFiles(new FileFilter() {
                    public boolean accept(File file) {
                        return file != null && file.isDirectory() && file.getName().contains(".") && !file.getName().startsWith(".");
                    }
                });
                if (listFiles != null) {
                    f.a("fs.length = " + listFiles.length);
                    for (File name : listFiles) {
                        arrayList.add(name.getName());
                    }
                    return arrayList;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }

    public static String b(Context context, String str) {
        String str2 = "";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString(str);
                if (string != null) {
                    return string;
                }
            }
        } catch (Exception e) {
        }
        return str2;
    }

    public static String p(Context context) {
        String packageName = context.getPackageName();
        return (packageName + "-" + a.a(packageName, context)).replace(" ", "");
    }

    public static void a(Closeable... closeableArr) {
        for (Closeable closeable : closeableArr) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void a(HttpURLConnection... httpURLConnectionArr) {
        for (HttpURLConnection httpURLConnection : httpURLConnectionArr) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }
}

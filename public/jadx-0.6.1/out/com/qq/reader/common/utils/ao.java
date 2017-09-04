package com.qq.reader.common.utils;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.StatFs;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.widget.ImageView;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.activity.SwitchViewActivity;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.db.handle.j;
import com.qq.reader.common.db.handle.o;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.download.task.l;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.protocol.ReadOnline.ReadOnlineResult;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.ProfileNetTask;
import com.qq.reader.common.receiver.AlarmReceiver;
import com.qq.reader.common.utils.StatisticsManager.Node;
import com.qq.reader.cservice.download.book.DownloadBookTask;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigDetailActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeSkinManageActivity;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.search.CommonBookSearchParamCollection;
import com.qq.reader.module.bookstore.search.ISearchParamCollection;
import com.qq.reader.module.comic.mark.ComicBookMark;
import com.qq.reader.plugin.audiobook.MusicOnlineTag;
import com.qq.reader.plugin.k;
import com.qq.reader.plugin.x;
import com.qq.reader.qplugin.local.TingBookMark;
import com.qq.reader.view.AudioFloatingWindowView;
import com.qq.reader.view.web.h;
import com.tencent.connect.common.Constants;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.WebView;
import com.tencent.theme.SkinnableBitmapDrawable;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import com.tencent.upload.impl.TaskManager;
import com.tencent.util.TimeFormatterUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import oicq.wlogin_sdk.request.WtloginHelper;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tencent.tls.platform.SigType;

/* compiled from: Utility */
public class ao {
    public static SharedPreferences a;
    public static String b = "";
    public static double c = 1048576.0d;
    public static double d = 1024.0d;
    public static String e = "项";
    public static final Map<Float, Float> f = new HashMap();
    public static final String[] g = new String[]{".txt"};
    public static final String[] h = new String[]{".epub"};
    public static final String[] i = new String[]{".pdf"};
    public static final String[] j = new String[]{".xls", ".xlsx", ".doc", ".docx", ".ppt", ".pptx"};
    public static final String[] k = new String[]{".umd", ".chm", ".teb", ".rar", ".zip", ".trial"};
    private static final char[] l = new char[]{'/', ':', '?', '*', '|', '<', '>', '\\', '\"'};
    private static float m;
    private static float n;
    private static String[] o = null;
    private static long p = -1;
    private static h q;
    private static String r = null;

    /* compiled from: Utility */
    public static final class a {
        public static boolean a(Context context, String str) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 1);
                if (packageInfo == null || packageInfo.activities == null || packageInfo.activities.length <= 0) {
                    return false;
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        public static void a(Context context, File file) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                intent.setFlags(SigType.TLS);
                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static boolean a(Activity activity, String str) {
            if (str == null || activity == null) {
                return false;
            }
            Intent launchIntentForPackage = activity.getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage == null) {
                return false;
            }
            c.a(R.anim.slide_in_right, R.anim.slide_out_left);
            activity.startActivity(launchIntentForPackage);
            return true;
        }
    }

    /* compiled from: Utility */
    public interface b {
        void a();
    }

    /* compiled from: Utility */
    public static final class c {
        public static boolean a(SQLiteDatabase sQLiteDatabase, String str, String str2) {
            Cursor cursor = null;
            try {
                boolean z;
                cursor = sQLiteDatabase.rawQuery("SELECT * FROM " + str2, null);
                if (cursor.getColumnIndex(str) < 0) {
                    z = false;
                } else {
                    z = true;
                }
                if (cursor != null) {
                    cursor.close();
                }
                return z;
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
    }

    /* compiled from: Utility */
    public static class d {
        public static String a(String str) {
            String string;
            Closeable closeable = null;
            try {
                closeable = ReaderApplication.getApplicationImp().getAssets().open(str);
                byte[] bArr = new byte[closeable.available()];
                closeable.read(bArr);
                string = EncodingUtils.getString(bArr, "UTF-8");
                return string;
            } catch (Exception e) {
                string = e;
                string.printStackTrace();
                return "";
            } finally {
                a(closeable);
            }
        }

        public static void a(Closeable closeable) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    /* compiled from: Utility */
    public static class e {
        public static boolean a(View view, Context context) {
            try {
                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
                if (inputMethodManager != null) {
                    return inputMethodManager.showSoftInput(view, 0);
                }
                return false;
            } catch (Exception e) {
                return false;
            }
        }

        public static boolean a(IBinder iBinder, Context context) {
            try {
                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
                if (inputMethodManager == null || !inputMethodManager.isActive()) {
                    return false;
                }
                return inputMethodManager.hideSoftInputFromWindow(iBinder, 0);
            } catch (Exception e) {
                return false;
            }
        }

        public static int a(Context context) {
            return context.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        }

        public static void a(Activity activity) {
            if (activity != null && !activity.isFinishing()) {
                try {
                    AccessibilityManager accessibilityManager = (AccessibilityManager) activity.getSystemService("accessibility");
                    if (accessibilityManager.isEnabled()) {
                        Method declaredMethod = accessibilityManager.getClass().getDeclaredMethod("setState", new Class[]{Integer.TYPE});
                        declaredMethod.setAccessible(true);
                        declaredMethod.invoke(accessibilityManager, new Object[]{Integer.valueOf(0)});
                    }
                } catch (Throwable th) {
                    com.qq.reader.common.monitor.debug.c.e("Webbrowserforcontents", th.getMessage());
                }
            }
        }
    }

    public static String a(double d) {
        return String.format("%.1f", new Object[]{Double.valueOf(100.0d * d)}) + "%";
    }

    public static String a(List<Integer> list) {
        String str = "";
        if (list == null || list.size() <= 0) {
            return str;
        }
        Collections.sort(list);
        StringBuffer stringBuffer = new StringBuffer();
        String str2 = ",";
        String str3 = "-";
        int intValue = ((Integer) list.get(0)).intValue();
        stringBuffer.append(intValue);
        int i = 0;
        int i2 = -1;
        int i3 = intValue;
        int i4 = 1;
        while (i4 < list.size()) {
            intValue = ((Integer) list.get(i4)).intValue();
            if (intValue != i3 + 1) {
                if (i != 0) {
                    stringBuffer.append(i3);
                    i = 0;
                }
                stringBuffer.append(str2);
                stringBuffer.append(intValue);
            } else if (i == 0) {
                stringBuffer.append(str3);
                i = 1;
            }
            i4++;
            i3 = intValue;
            i2 = intValue;
        }
        if (i != 0) {
            stringBuffer.append(i2);
        }
        return stringBuffer.toString();
    }

    public static ArrayList<Integer> a(String str) {
        ArrayList<Integer> arrayList = new ArrayList();
        if (str != null) {
            try {
                if (!str.trim().equals("")) {
                    String[] split = str.split(",");
                    if (split.length > 0) {
                        for (String str2 : split) {
                            if (str2.indexOf("-") > 0) {
                                String[] split2 = str2.split("\\-");
                                if (split2.length == 2) {
                                    short shortValue = Short.valueOf(split2[1]).shortValue();
                                    for (int shortValue2 = Short.valueOf(split2[0]).shortValue(); shortValue2 < shortValue + 1; shortValue2++) {
                                        arrayList.add(Integer.valueOf(shortValue2));
                                    }
                                }
                            } else {
                                arrayList.add(Integer.valueOf(str2));
                            }
                        }
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return null;
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public static boolean a() {
        try {
            StatFs statFs = new StatFs(am.b());
            if (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize()) < 2097152) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public static String a(long j, long j2) {
        double d = 0.0d;
        if (j2 != 0) {
            d = ((double) j) / ((double) j2);
        }
        if (d > 1.0d) {
            d = 1.0d;
        }
        if (d == 1.0d) {
            return "正在安装...";
        }
        return String.format("%.1f", new Object[]{Double.valueOf(d * 100.0d)}) + "%";
    }

    private static void z(final Context context) {
        new Thread(new Runnable() {
            public void run() {
                TypedArray obtainTypedArray = context.getResources().obtainTypedArray(R.array.prePluginRes);
                ao.a(context, obtainTypedArray, com.qq.reader.common.c.a.an, "pluginIcon/");
                obtainTypedArray.recycle();
            }
        }).start();
    }

    public static void a(Context context) {
        if (c()) {
            k.b().a(context);
            z(context);
            c(context);
        }
    }

    public static void b(Context context) {
        if (d() || com.qq.reader.common.c.a.P) {
            com.qq.reader.module.bookstore.qweb.channel.d.a().a(context);
            if (com.qq.reader.common.c.a.P) {
                com.qq.reader.module.bookstore.qweb.channel.a.a(context);
            }
            com.qq.reader.common.c.a.P = false;
        }
    }

    public static String a(long j) {
        long j2 = j / 60;
        long j3 = j % 60;
        StringBuffer stringBuffer = new StringBuffer();
        if (j2 < 10) {
            stringBuffer.append("0");
        }
        stringBuffer.append(j2);
        stringBuffer.append(":");
        if (j3 < 10) {
            stringBuffer.append("0");
        }
        stringBuffer.append(j3);
        return stringBuffer.toString();
    }

    public static long a(LocalMark localMark) {
        long j = 0;
        List a = ((com.qq.reader.cservice.download.book.e) l.d(1001)).a();
        if (a != null) {
            for (int i = 0; i < a.size(); i++) {
                DownloadBookTask downloadBookTask = (DownloadBookTask) a.get(i);
                if (downloadBookTask.getFilePath().equals(localMark.getId())) {
                    j = downloadBookTask.getId();
                }
            }
        }
        return j;
    }

    public static boolean a(Mark mark) {
        if (!(mark instanceof LocalMark) || a((LocalMark) mark) == 0) {
            return false;
        }
        return true;
    }

    public static void a(File file, List<File> list, boolean z) {
        if (file != null) {
            try {
                if (file.exists()) {
                    if (z) {
                        list.add(file);
                    } else if (file.isFile()) {
                        list.add(file);
                    }
                    if (!file.isFile()) {
                        File[] listFiles = file.listFiles();
                        if (listFiles != null) {
                            for (File a : listFiles) {
                                a(a, (List) list, z);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static List<File> b() {
        List<File> arrayList = new ArrayList();
        List<File> arrayList2 = new ArrayList();
        File file;
        try {
            List arrayList3 = new ArrayList();
            String str = com.qq.reader.common.c.a.l + ".*((Online" + File.separator + "online.db.*)|(bkd" + File.separator + ".*\\S+.db.*))";
            final String str2 = com.qq.reader.common.c.a.l + "((\\d+)|(Online)|(bkd))";
            File file2 = new File(com.qq.reader.common.c.a.l);
            Object[] listFiles = file2.exists() ? file2.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    return file.getAbsolutePath().matches(str2) && file.isDirectory();
                }
            }) : null;
            if (listFiles != null && listFiles.length > 0) {
                com.qq.reader.common.monitor.debug.c.a("ACCOUNT", " dirfile = " + Arrays.toString(listFiles));
                for (File file3 : listFiles) {
                    arrayList3.clear();
                    a(file3, arrayList3, true);
                    for (int i = 0; i < arrayList3.size(); i++) {
                        file3 = (File) arrayList3.get(i);
                        if (file3.isFile() && file3.getAbsolutePath().matches(str)) {
                            File file4 = new File(file3.getAbsolutePath().replace(com.qq.reader.common.c.a.l, com.qq.reader.common.c.a.n));
                            if (a(file3, file4, true)) {
                                com.qq.reader.common.monitor.debug.c.a("ACCOUNT", "copy file " + file3.getAbsolutePath() + " ---> " + file4.getAbsolutePath());
                                arrayList.add(file3);
                                arrayList2.add(file4);
                            }
                        }
                    }
                }
            }
            com.qq.reader.appconfig.a.d.a(true, ReaderApplication.getApplicationImp());
        } catch (Exception e) {
            e.printStackTrace();
            for (File file32 : arrayList2) {
                if (file32 != null && file32.exists()) {
                    com.qq.reader.common.monitor.debug.c.a("ACCOUNT", "recover file " + file32.getAbsolutePath() + "  " + file32.delete());
                }
            }
        }
        com.qq.reader.common.monitor.debug.c.a("ACCOUNT", "should delete list " + arrayList.toString());
        return arrayList;
    }

    public static boolean a(File file, File file2) {
        FileInputStream fileInputStream;
        Exception e;
        Throwable th;
        FileInputStream fileInputStream2 = null;
        try {
            if (file2.exists()) {
                return true;
            }
            if (ab.a(file2.getParentFile())) {
                file2.createNewFile();
            }
            FileOutputStream fileOutputStream;
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    fileOutputStream = new FileOutputStream(file2);
                } catch (Exception e2) {
                    e = e2;
                    fileOutputStream = null;
                    fileInputStream2 = fileInputStream;
                    try {
                        e.printStackTrace();
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (Exception e3) {
                                return false;
                            }
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e4) {
                                throw th;
                            }
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
                try {
                    byte[] bArr = new byte[51200];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.flush();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e5) {
                            return true;
                        }
                    }
                    if (fileOutputStream == null) {
                        return true;
                    }
                    fileOutputStream.close();
                    return true;
                } catch (Exception e6) {
                    e = e6;
                    fileInputStream2 = fileInputStream;
                    e.printStackTrace();
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    return false;
                } catch (Throwable th4) {
                    th = th4;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e7) {
                e = e7;
                fileOutputStream = null;
                e.printStackTrace();
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return false;
            } catch (Throwable th5) {
                th = th5;
                fileOutputStream = null;
                fileInputStream = null;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (IOException e8) {
            e8.printStackTrace();
            return false;
        }
    }

    public static boolean a(File file, File file2, boolean z) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        Exception e;
        Throwable th;
        FileInputStream fileInputStream2 = null;
        try {
            if (!file.exists()) {
                return false;
            }
            if (file2.exists()) {
                if (!z) {
                    return true;
                }
                file2.delete();
            } else if (ab.a(file2.getParentFile())) {
                file2.createNewFile();
            }
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    fileOutputStream = new FileOutputStream(file2);
                } catch (Exception e2) {
                    e = e2;
                    fileOutputStream = null;
                    fileInputStream2 = fileInputStream;
                    try {
                        e.printStackTrace();
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (Exception e3) {
                                return false;
                            }
                        }
                        if (fileOutputStream != null) {
                            return false;
                        }
                        fileOutputStream.close();
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e4) {
                                throw th;
                            }
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
                try {
                    byte[] bArr = new byte[51200];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.flush();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e5) {
                        }
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    return true;
                } catch (Exception e6) {
                    e = e6;
                    fileInputStream2 = fileInputStream;
                    e.printStackTrace();
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    if (fileOutputStream != null) {
                        return false;
                    }
                    fileOutputStream.close();
                    return false;
                } catch (Throwable th4) {
                    th = th4;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e7) {
                e = e7;
                fileOutputStream = null;
                e.printStackTrace();
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (fileOutputStream != null) {
                    return false;
                }
                fileOutputStream.close();
                return false;
            } catch (Throwable th5) {
                th = th5;
                fileOutputStream = null;
                fileInputStream = null;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (IOException e8) {
            e8.printStackTrace();
            return false;
        }
    }

    public static Object[] b(File file, File file2) {
        FileOutputStream fileOutputStream;
        Exception e;
        Throwable th;
        FileInputStream fileInputStream = null;
        Object[] objArr = new Object[2];
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (file2.exists()) {
                objArr[0] = Boolean.valueOf(true);
            } else {
                if (ab.a(file2.getParentFile())) {
                    file2.createNewFile();
                }
                try {
                    FileInputStream fileInputStream2 = new FileInputStream(file);
                    try {
                        fileOutputStream = new FileOutputStream(file2);
                    } catch (Exception e2) {
                        e = e2;
                        fileOutputStream = null;
                        fileInputStream = fileInputStream2;
                        try {
                            e.printStackTrace();
                            stringBuilder.append(e.getMessage());
                            objArr[0] = Boolean.valueOf(false);
                            objArr[1] = stringBuilder.toString();
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e3) {
                                }
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            return objArr;
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e4) {
                                    throw th;
                                }
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        fileOutputStream = null;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                    try {
                        byte[] bArr = new byte[51200];
                        while (true) {
                            int read = fileInputStream2.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        fileOutputStream.flush();
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (Exception e5) {
                            }
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        objArr[0] = Boolean.valueOf(true);
                    } catch (Exception e6) {
                        e = e6;
                        fileInputStream = fileInputStream2;
                        e.printStackTrace();
                        stringBuilder.append(e.getMessage());
                        objArr[0] = Boolean.valueOf(false);
                        objArr[1] = stringBuilder.toString();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        return objArr;
                    } catch (Throwable th4) {
                        th = th4;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Exception e7) {
                    e = e7;
                    fileOutputStream = null;
                    e.printStackTrace();
                    stringBuilder.append(e.getMessage());
                    objArr[0] = Boolean.valueOf(false);
                    objArr[1] = stringBuilder.toString();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    return objArr;
                } catch (Throwable th5) {
                    th = th5;
                    fileOutputStream = null;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            }
        } catch (IOException e8) {
            e8.printStackTrace();
            stringBuilder.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
            stringBuilder.append(e8.getMessage());
            objArr[0] = Boolean.valueOf(false);
            objArr[1] = stringBuilder.toString();
        }
        return objArr;
    }

    public static boolean c(Context context) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        Throwable th;
        FileOutputStream fileOutputStream = null;
        String str = context.getApplicationContext().getFilesDir().getAbsolutePath() + "/PlugIn/";
        File file = new File(str + "PdfAddon.apk");
        if (file == null || !file.exists()) {
            boolean z = false;
        } else {
            int i = 1;
        }
        File file2 = new File(str + "libpdf.so");
        if (file2 == null || !file2.exists()) {
            boolean z2 = false;
        } else {
            int i2 = 1;
        }
        if (!(i2 == 0 || r0 == 0)) {
            String str2 = context.getApplicationContext().getFilesDir().getAbsolutePath() + "/PlugIn/1/";
            File file3 = new File(str2);
            ab.b(file3);
            File[] fileArr = new File[]{file, file2};
            if (file3.exists() && fileArr != null && fileArr.length == 2) {
                i = 0;
                while (i < fileArr.length) {
                    String str3 = str2 + fileArr[i].getPath().substring(fileArr[i].getPath().lastIndexOf("/") + 1);
                    file2 = new File(str3);
                    if (file2.exists()) {
                        file2.delete();
                    }
                    c(str3);
                    try {
                        fileInputStream = new FileInputStream(fileArr[i].getPath());
                        try {
                            FileOutputStream fileOutputStream2 = new FileOutputStream(file2.getPath());
                            try {
                                byte[] bArr = new byte[51200];
                                while (true) {
                                    int read = fileInputStream.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    fileOutputStream2.write(bArr, 0, read);
                                }
                                fileOutputStream2.flush();
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (Exception e) {
                                    }
                                }
                                if (fileOutputStream2 != null) {
                                    fileOutputStream2.close();
                                }
                                fileArr[i].delete();
                                i++;
                            } catch (Exception e2) {
                                fileOutputStream = fileOutputStream2;
                                fileInputStream2 = fileInputStream;
                            } catch (Throwable th2) {
                                th = th2;
                                fileOutputStream = fileOutputStream2;
                            }
                        } catch (Exception e3) {
                            fileInputStream2 = fileInputStream;
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (Exception e4) {
                        fileInputStream2 = null;
                    } catch (Throwable th4) {
                        th = th4;
                        fileInputStream = null;
                    }
                }
            }
            file3 = new File(str + "Plugin");
            if (file3 != null && file3.exists()) {
                file3.delete();
            }
            file3 = new File(str + "PdfAddon.dex");
            if (file3 != null && file3.exists()) {
                file3.delete();
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(com.qq.reader.common.c.a.aJ);
            stringBuffer.append("1");
            stringBuffer.append("/");
            stringBuffer.append("1");
            stringBuffer.append(".zip");
            c(stringBuffer.toString());
            k.b().a("1", "android_plugin_pdf_1.0", "android_plugin_pdf_1.0", "android_plugin_pdf_1.0");
            k.b().a("1", 0, 4, null, 2);
        }
        return true;
        if (fileInputStream2 != null) {
            try {
                fileInputStream2.close();
            } catch (Exception e5) {
                return false;
            }
        }
        if (fileOutputStream == null) {
            return false;
        }
        fileOutputStream.close();
        return false;
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (Exception e6) {
                throw th;
            }
        }
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
        throw th;
    }

    public static boolean c() {
        File file = new File(com.qq.reader.common.c.a.ap);
        if (!file.exists()) {
            return true;
        }
        if (k.b().e().size() > 0) {
            return false;
        }
        try {
            file.delete();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        } catch (Throwable th) {
            return true;
        }
    }

    public static boolean d() {
        if (new File(com.qq.reader.common.c.a.aB).exists()) {
            return false;
        }
        return true;
    }

    public static boolean a(Activity activity, String str) {
        int i;
        if (a == null) {
            a = activity.getSharedPreferences("config", 0);
        }
        try {
            i = a.getInt(str, 0);
        } catch (Exception e) {
            i = 0;
        }
        if (10077 <= i) {
            return false;
        }
        Editor edit = a.edit();
        edit.putInt(str, 10077);
        edit.commit();
        return true;
    }

    public static void a(Activity activity) {
        try {
            int i;
            if (a == null) {
                a = activity.getSharedPreferences("config", 0);
            }
            int i2 = System.getInt(activity.getContentResolver(), "screen_brightness");
            int as = com.qq.reader.appconfig.a.d.as(activity);
            if (as <= 0) {
                com.qq.reader.appconfig.a.d.u((Context) activity, 25);
                i = 25;
            } else {
                i = as;
            }
            if (i2 < i && i2 > 0) {
                com.qq.reader.appconfig.a.d.u((Context) activity, i2);
            }
            int as2 = com.qq.reader.appconfig.a.d.as(activity);
            if (!com.qq.reader.appconfig.a.d.aa(activity)) {
                as = com.qq.reader.appconfig.a.d.ac(activity);
                if (i > as2) {
                    as -= i - as2;
                    com.qq.reader.appconfig.a.d.o((Context) activity, as);
                }
                i2 = as;
                com.qq.reader.appconfig.a.d.q = i2;
                if (com.qq.reader.appconfig.a.d.q == -1) {
                    com.qq.reader.appconfig.a.d.q = System.getInt(activity.getContentResolver(), "screen_brightness");
                }
                as = com.qq.reader.appconfig.a.d.ab(activity);
                if (i > as2) {
                    as -= i - as2;
                    com.qq.reader.appconfig.a.d.n((Context) activity, i2);
                }
                com.qq.reader.appconfig.a.d.s = as;
                if (com.qq.reader.appconfig.a.d.q < as2) {
                    com.qq.reader.appconfig.a.d.q = as2;
                }
                if (com.qq.reader.appconfig.a.d.s < as2) {
                    com.qq.reader.appconfig.a.d.s = as2;
                }
            } else if (System.getInt(activity.getContentResolver(), "screen_brightness_mode") == 0) {
                com.qq.reader.appconfig.a.d.q = i2;
                com.qq.reader.appconfig.a.d.s = i2;
            } else {
                com.qq.reader.appconfig.a.d.q = com.qq.reader.appconfig.a.d.ac(activity);
                com.qq.reader.appconfig.a.d.s = com.qq.reader.appconfig.a.d.ab(activity);
            }
        } catch (SettingNotFoundException e) {
            f.a("YT", "SettingNotFoundException " + e.toString());
            com.qq.reader.appconfig.a.d.u((Context) activity, 25);
        }
    }

    public static void b(Activity activity) {
        if (com.qq.reader.appconfig.a.d.aa(activity)) {
            c(activity);
        } else {
            a(activity, 0);
        }
    }

    public static void c(Activity activity) {
        LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.screenBrightness = -1.0f;
        activity.getWindow().setAttributes(attributes);
    }

    public static void a(Activity activity, int i) {
        int i2;
        if (a == null) {
            a = activity.getSharedPreferences("config", 0);
        }
        if (com.qq.reader.appconfig.a.d.n) {
            com.qq.reader.appconfig.a.d.s = Math.max(com.qq.reader.appconfig.a.d.s + i, com.qq.reader.appconfig.a.d.as(activity));
            com.qq.reader.appconfig.a.d.s = Math.min(com.qq.reader.appconfig.a.d.s, 255);
            i2 = com.qq.reader.appconfig.a.d.s;
        } else {
            com.qq.reader.appconfig.a.d.q = Math.max(com.qq.reader.appconfig.a.d.q + i, com.qq.reader.appconfig.a.d.as(activity));
            com.qq.reader.appconfig.a.d.q = Math.min(com.qq.reader.appconfig.a.d.q, 255);
            i2 = com.qq.reader.appconfig.a.d.q;
        }
        if (i2 <= 0) {
            i2 = 25;
        }
        LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.screenBrightness = ((float) i2) / 255.0f;
        if (w((Context) activity) && Build.MODEL.contains("LT29i")) {
            com.qq.reader.appconfig.a.d.G((Context) activity, true);
            f((Context) activity, 0);
        }
        activity.getWindow().setAttributes(attributes);
    }

    public static int a(Context context, boolean z) {
        if (z) {
            return 3600000;
        }
        int ai = com.qq.reader.appconfig.a.d.ai(context);
        if (ai > 30) {
            return -1;
        }
        return (ai * 1000) * 60;
    }

    public static void a(Window window, boolean z) {
        if (z) {
            window.addFlags(128);
            window.addFlags(WtloginHelper.SigType.WLOGIN_LHSIG);
            return;
        }
        window.clearFlags(128);
        window.clearFlags(WtloginHelper.SigType.WLOGIN_LHSIG);
    }

    public static String[] a(String str, String str2) {
        int i = 0;
        if (str == null || str2 == null) {
            return null;
        }
        Vector vector = new Vector();
        int indexOf = str.indexOf(str2);
        if (indexOf == -1) {
            vector.addElement(str);
        } else {
            int i2 = indexOf;
            indexOf = 0;
            while (i2 < str.length() && i2 != -1) {
                vector.addElement(str.substring(indexOf, i2));
                indexOf = str2.length() + i2;
                i2 = str.indexOf(str2, i2 + str2.length());
            }
            vector.addElement(str.substring(indexOf));
        }
        String[] strArr = new String[vector.size()];
        while (i < vector.size()) {
            strArr[i] = (String) vector.elementAt(i);
            i++;
        }
        return strArr;
    }

    public static String b(long j) {
        double d;
        String str = "M";
        if (((double) j) >= c) {
            d = ((double) j) / c;
        } else if (0.0d <= 11.0d) {
            d = ((double) j) / d;
            str = "K";
        } else {
            d = ((double) j) / d;
            str = "K";
        }
        return String.format("%.2f", new Object[]{Double.valueOf(d)}) + str;
    }

    public static String a(int i) {
        return i + e;
    }

    public static String a(File[] fileArr, Context context) {
        int i = 0;
        if (fileArr == null) {
            return "";
        }
        for (File file : fileArr) {
            if (file.isDirectory()) {
                i++;
            } else {
                String name = file.getName();
                if (!(name == null || name.startsWith(".") || !b(name, context))) {
                    i++;
                }
            }
        }
        return i + e;
    }

    public static void e() {
        c(new File(com.qq.reader.common.c.a.l));
    }

    public static boolean d(Context context) {
        if (context == null) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean e(Context context) {
        if (context == null) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean f(Context context) {
        if (context == null) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean g(Context context) {
        if (context == null) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean f() {
        boolean z = false;
        if ("mounted".equals(Environment.getExternalStorageState()) && new File(com.qq.reader.common.c.a.l, "Debug.txt").exists()) {
            z = true;
        }
        com.qq.reader.common.monitor.debug.c.a("debug", "file exist " + z);
        return z;
    }

    private static String b(InputStream inputStream) {
        String str = com.tencent.qalsdk.core.c.c;
        try {
            byte[] bArr = new byte[inputStream.available()];
            inputStream.read(bArr);
            str = EncodingUtils.getString(bArr, "GBK").substring("CHANNEL=".length());
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e32) {
                    e32.printStackTrace();
                }
            }
        }
        return str.replaceAll("\r|\n", "");
    }

    private static InputStream A(Context context) {
        InputStream inputStream = null;
        try {
            inputStream = context.getResources().getAssets().open("channel.ini");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    public static synchronized String h(Context context) {
        String p;
        synchronized (ao.class) {
            if (com.qq.reader.appconfig.f.a == null) {
                p = com.qq.reader.appconfig.a.c.p(context);
                if (p == null || p.length() <= 0) {
                    ReaderApplication.getInstance();
                    if (ReaderApplication.isDebugApplication && f()) {
                        try {
                            FileInputStream fileInputStream = new FileInputStream(new File(com.qq.reader.common.c.a.l, "Debug.txt"));
                            byte[] bArr = new byte[fileInputStream.available()];
                            fileInputStream.read(bArr);
                            String str = new String(bArr);
                            com.qq.reader.appconfig.a.c.f(context, str);
                            com.qq.reader.appconfig.f.a = str;
                            com.qq.reader.common.monitor.debug.c.a("debug", "channel is" + str);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Object a = com.qq.reader.common.b.a.a();
                        InputStream inputStream = null;
                        if (TextUtils.isEmpty(a)) {
                            inputStream = A(context);
                        } else {
                            try {
                                File file = new File(a);
                                if (file.exists()) {
                                    inputStream = new FileInputStream(file);
                                }
                            } catch (FileNotFoundException e2) {
                                e2.printStackTrace();
                                inputStream = A(context);
                            } catch (Exception e3) {
                                e3.printStackTrace();
                                inputStream = A(context);
                            }
                        }
                        p = b(inputStream);
                        com.qq.reader.appconfig.a.c.f(context, p);
                        com.qq.reader.appconfig.f.a = p;
                    }
                } else {
                    com.qq.reader.appconfig.f.a = p;
                }
            }
            p = com.qq.reader.appconfig.f.a;
        }
        return p;
    }

    public static boolean i(Context context) {
        if ("10000635".equals(h(context))) {
            return true;
        }
        return false;
    }

    public static boolean a(Context context, String str, String str2, String str3) {
        Exception e;
        Throwable th;
        InputStream inputStream = null;
        File file;
        BufferedOutputStream bufferedOutputStream;
        try {
            file = new File(str2 + com.qq.reader.common.download.task.f.DOWNLOAD_FILE_TMP);
            try {
                File file2 = new File(str2);
                if (file.exists()) {
                    file.delete();
                    file.createNewFile();
                } else if (ab.a(file.getParentFile())) {
                    file.createNewFile();
                }
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                try {
                    String str4 = "";
                    if (!TextUtils.isEmpty(str3)) {
                        str4 = str4 + str3 + File.separator;
                    }
                    inputStream = context.getAssets().open(str4 + str);
                    byte[] bArr = new byte[20480];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        bufferedOutputStream.write(bArr, 0, read);
                    }
                    file.renameTo(file2);
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e2) {
                            f.a("copyAssetsToSDcard Stream close", e2.toString());
                        }
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (file != null) {
                        file.delete();
                    }
                    return true;
                } catch (Exception e3) {
                    e = e3;
                    try {
                        f.a("copyAssetsToSDcard", e.toString());
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e4) {
                                f.a("copyAssetsToSDcard Stream close", e4.toString());
                                return false;
                            }
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (file != null) {
                            return false;
                        }
                        file.delete();
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e42) {
                                f.a("copyAssetsToSDcard Stream close", e42.toString());
                                throw th;
                            }
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (file != null) {
                            file.delete();
                        }
                        throw th;
                    }
                }
            } catch (Exception e5) {
                e = e5;
                bufferedOutputStream = null;
                f.a("copyAssetsToSDcard", e.toString());
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (file != null) {
                    return false;
                }
                file.delete();
                return false;
            } catch (Throwable th3) {
                th = th3;
                bufferedOutputStream = null;
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (file != null) {
                    file.delete();
                }
                throw th;
            }
        } catch (Exception e6) {
            e = e6;
            file = null;
            bufferedOutputStream = null;
            f.a("copyAssetsToSDcard", e.toString());
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (file != null) {
                return false;
            }
            file.delete();
            return false;
        } catch (Throwable th4) {
            th = th4;
            file = null;
            bufferedOutputStream = null;
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (file != null) {
                file.delete();
            }
            throw th;
        }
    }

    public static boolean a(Context context, TypedArray typedArray, String str, String str2) {
        InputStream open;
        Exception e;
        BufferedOutputStream bufferedOutputStream;
        Throwable th;
        InputStream inputStream = null;
        int i = 0;
        while (i < typedArray.length()) {
            BufferedOutputStream bufferedOutputStream2;
            String string = typedArray.getString(i);
            BufferedOutputStream bufferedOutputStream3 = null;
            InputStream inputStream2 = null;
            try {
                File file = new File(str + string);
                if (file.exists()) {
                    if (null != null) {
                        try {
                            bufferedOutputStream3.close();
                        } catch (IOException e2) {
                            f.a("copyAssetsToSDcard Stream close", e2.toString());
                        }
                    }
                    if (null != null) {
                        inputStream2.close();
                    }
                } else {
                    if (ab.a(file.getParentFile())) {
                        file.createNewFile();
                    }
                    bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file));
                    try {
                        open = context.getAssets().open(str2 + string);
                        try {
                            byte[] bArr = new byte[20480];
                            while (true) {
                                int read = open.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                bufferedOutputStream2.write(bArr, 0, read);
                            }
                            if (bufferedOutputStream2 != null) {
                                try {
                                    bufferedOutputStream2.close();
                                } catch (IOException e22) {
                                    f.a("copyAssetsToSDcard Stream close", e22.toString());
                                }
                            }
                            if (open != null) {
                                open.close();
                            }
                        } catch (Exception e3) {
                            e = e3;
                            bufferedOutputStream = bufferedOutputStream2;
                        } catch (Throwable th2) {
                            th = th2;
                            inputStream = open;
                        }
                    } catch (Exception e4) {
                        e = e4;
                        open = null;
                        bufferedOutputStream = bufferedOutputStream2;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
                i++;
            } catch (Exception e5) {
                e = e5;
                open = null;
            } catch (Throwable th4) {
                th = th4;
                bufferedOutputStream2 = null;
            }
        }
        return true;
        try {
            f.a("copyAssetsToSDcard", e.toString());
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e6) {
                    f.a("copyAssetsToSDcard Stream close", e6.toString());
                    return false;
                }
            }
            if (open == null) {
                return false;
            }
            open.close();
            return false;
        } catch (Throwable th5) {
            th = th5;
            bufferedOutputStream2 = bufferedOutputStream;
            inputStream = open;
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e7) {
                    f.a("copyAssetsToSDcard Stream close", e7.toString());
                    throw th;
                }
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public static void a(int i, Context context) {
        com.qq.reader.common.c.a.df = true;
        String str = null;
        switch (i) {
            case 0:
                str = "all.txt";
                break;
            case 1:
                str = "boy.txt";
                break;
            case 2:
                str = "girl.txt";
                break;
            case 3:
                str = "publish.txt";
                break;
        }
        d(str, context);
        com.qq.reader.common.c.a.df = false;
    }

    private static void d(String str, Context context) {
        InputStream open;
        Exception e;
        Throwable th;
        InputStream inputStream = null;
        o c = o.c();
        StringBuffer stringBuffer = new StringBuffer();
        String substring = str.substring(0, str.indexOf("."));
        try {
            open = context.getResources().getAssets().open(str);
            try {
                byte[] bArr = new byte[open.available()];
                open.read(bArr);
                JSONArray jSONArray = new JSONArray(new String(bArr, "UTF-8"));
                OnlineTag onlineTag = null;
                Mark mark = null;
                for (int i = 0; i < jSONArray.length(); i++) {
                    String str2 = substring + i;
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    String optString = jSONObject.optString("bookid");
                    if (optString.length() > 0) {
                        stringBuffer.append(optString);
                        stringBuffer.append(",");
                    }
                    String string = jSONObject.getString("bookname");
                    String string2 = jSONObject.getString("author");
                    if (i.c().f(optString) == null) {
                        if (c.c(Long.parseLong(optString)) != null) {
                            continue;
                        } else {
                            String h;
                            int optInt = jSONObject.optInt("resType");
                            String str3 = "";
                            if (optInt == 3) {
                                h = h(Long.parseLong(optString));
                            } else if (optInt == 2) {
                                h = a(Long.parseLong(optString), false, (int) Opcodes.OR_INT);
                            } else {
                                h = g(Long.valueOf(optString).longValue());
                            }
                            int optInt2 = jSONObject.optInt("chaptercount");
                            String optString2 = jSONObject.optString("bookformat");
                            int optInt3 = jSONObject.optInt("drm");
                            int optInt4 = jSONObject.optInt("finished", 1);
                            String optString3 = jSONObject.optString("lastChapterName");
                            String optString4 = jSONObject.optString("downloadinfo");
                            com.qq.reader.module.bookstore.qnative.b.b bVar = new com.qq.reader.module.bookstore.qnative.b.b();
                            bVar.a(optString4);
                            if (bVar.a()) {
                                mark = com.qq.reader.framework.mark.a.a(Long.parseLong(optString), string, string2, optString4);
                            } else {
                                OnlineTag onlineTag2 = new OnlineTag(optString, "", 0);
                                onlineTag2.a(string).e(string2).c(1).d(optInt2).h(h).k(optString2).i(optInt3).h(optInt4).j(optInt).c(System.currentTimeMillis()).l(optString3);
                                onlineTag2.a(0);
                                onlineTag2.b(System.currentTimeMillis());
                                if (optInt == 3) {
                                    v.b().b(onlineTag2);
                                    mark = new ComicBookMark(Long.parseLong(optString), string);
                                } else if (optInt == 2) {
                                    v.b().b(onlineTag2);
                                    mark = new TingBookMark(Long.parseLong(optString), string);
                                } else if (a(context, onlineTag2, str2, false, optInt3)) {
                                    v.b().b(onlineTag2);
                                    mark = new LocalMark(string, onlineTag2.f(), 0, 4, false);
                                }
                                if (mark != null) {
                                    mark.setPercentStr("0.0%").setAuthor(string2).setDescriptionStr("");
                                    mark.setStarPointStr(Mark.HEADPAGE_FLAG);
                                    mark.setHasNewContent(false);
                                    mark.setId(onlineTag2.k());
                                    mark.setBookId(Long.valueOf(onlineTag2.k()).longValue());
                                    mark.setFinished(optInt4);
                                }
                                onlineTag = onlineTag2;
                            }
                            a(context, onlineTag, str2, true, optInt3);
                            mark.setReadTime(0);
                            mark.setNetChannel("1353");
                            j.a().a(new com.qq.reader.common.monitor.a.a(onlineTag.k(), mark.getNetChannelId()));
                            if (optInt4 == 0) {
                                mark.setLastUpdateTime((System.currentTimeMillis() - 4320000) / 1000);
                                mark.setLastUpdateChapter(optString3);
                            }
                            mark.setCoverUrl(h);
                            i.c().a(mark, true);
                        }
                    }
                }
                com.qq.reader.appconfig.a.d.q(context.getApplicationContext(), stringBuffer.toString());
                if (open != null) {
                    try {
                        open.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                e = e3;
                inputStream = open;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e4) {
            e = e4;
            try {
                e.printStackTrace();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                open = inputStream;
                if (open != null) {
                    try {
                        open.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            open = null;
            if (open != null) {
                open.close();
            }
            throw th;
        }
    }

    private static boolean a(Context context, OnlineTag onlineTag, String str, boolean z, int i) {
        Exception e;
        Throwable th;
        InputStream inputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        InputStream inputStream2 = null;
        try {
            String str2 = "";
            if (z) {
                com.qq.reader.common.imageloader.c.a(context).a(onlineTag.u(), com.qq.reader.common.imageloader.a.a().b(str + ".p"));
                if (inputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e2) {
                        f.a("copyOnlineAssetsToSDcard Stream close", e2.toString());
                        return true;
                    }
                }
                if (inputStream == null) {
                    return true;
                }
                inputStream2.close();
                return true;
            }
            File file = new File(onlineTag.f());
            if (file.exists()) {
                if (inputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e22) {
                        f.a("copyOnlineAssetsToSDcard Stream close", e22.toString());
                        return true;
                    }
                }
                if (inputStream == null) {
                    return true;
                }
                inputStream2.close();
                return true;
            }
            if (ab.a(file.getParentFile())) {
                file.createNewFile();
            }
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            if (z) {
                try {
                    inputStream = context.getResources().getAssets().open(str + ".p");
                } catch (Exception e3) {
                    e = e3;
                    try {
                        f.a("copyOnlineAssetsToSDcard", e.toString());
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e4) {
                                f.a("copyOnlineAssetsToSDcard Stream close", e4.toString());
                                return false;
                            }
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e222) {
                                f.a("copyOnlineAssetsToSDcard Stream close", e222.toString());
                                throw th;
                            }
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        throw th;
                    }
                }
            } else if (i == 0) {
                inputStream = context.getResources().getAssets().open(str);
            } else if (i == 1) {
                inputStream = context.getResources().getAssets().open(str);
            } else {
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e42) {
                        f.a("copyOnlineAssetsToSDcard Stream close", e42.toString());
                    }
                }
                if (inputStream != null) {
                    inputStream2.close();
                }
                return false;
            }
            byte[] bArr = new byte[20480];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                bufferedOutputStream.write(bArr, 0, read);
            }
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e2222) {
                    f.a("copyOnlineAssetsToSDcard Stream close", e2222.toString());
                    return true;
                }
            }
            if (inputStream == null) {
                return true;
            }
            inputStream.close();
            return true;
        } catch (Exception e5) {
            e = e5;
            Object obj = inputStream;
            f.a("copyOnlineAssetsToSDcard", e.toString());
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            return false;
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream = inputStream;
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public static File b(String str) {
        File file = new File(str);
        return file.exists() ? file : null;
    }

    public static File c(String str) {
        try {
            File file = new File(str);
            if (file.exists() || !ab.a(file.getParentFile())) {
                return file;
            }
            file.createNewFile();
            return file;
        } catch (IOException e) {
            f.a("Utility getAccessFile", e.toString());
            return null;
        }
    }

    public static boolean a(File file) {
        boolean z = false;
        if (!file.exists()) {
            return true;
        }
        int i = 0;
        while (!z) {
            int i2 = i + 1;
            if (i >= 10) {
                return z;
            }
            z = file.delete();
            if (z) {
                i = i2;
            } else {
                try {
                    Thread.sleep(200);
                    i = i2;
                } catch (InterruptedException e) {
                    f.a("FileUtil.forceDeleteFile", e.getMessage());
                    i = i2;
                }
            }
        }
        return z;
    }

    public static void b(File file) {
        g.a().a(new Utility$3(file));
    }

    public static boolean c(File file) {
        boolean z = true;
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return a(file);
        }
        try {
            File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                Thread.sleep(1);
                if (listFiles[i].isDirectory()) {
                    if (!c(listFiles[i])) {
                        return false;
                    }
                } else if (!a(listFiles[i])) {
                    z = false;
                    break;
                }
            }
            File file2 = new File(file.getAbsolutePath() + System.currentTimeMillis());
            file.renameTo(file2);
            a(file2);
            return z;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void a(String str, String str2, boolean z) throws IOException {
        File file;
        Throwable th;
        BufferedInputStream bufferedInputStream;
        DataOutputStream dataOutputStream;
        GZIPInputStream gZIPInputStream;
        OutputStream outputStream;
        DataOutputStream dataOutputStream2;
        BufferedInputStream bufferedInputStream2;
        OutputStream outputStream2;
        GZIPInputStream gZIPInputStream2;
        InputStream inputStream;
        DataOutputStream dataOutputStream3;
        BufferedInputStream bufferedInputStream3 = null;
        byte[] bArr = new byte[1050];
        FileOutputStream fileOutputStream;
        try {
            InputStream bufferedInputStream4;
            File file2 = new File(str);
            if (z) {
                file = new File(str + com.qq.reader.common.download.task.f.DOWNLOAD_FILE_TMP);
                try {
                    a(file);
                    file.createNewFile();
                    BufferedInputStream bufferedInputStream5 = new BufferedInputStream(new FileInputStream(file2));
                    try {
                        OutputStream fileOutputStream2 = new FileOutputStream(file);
                        try {
                            DataOutputStream dataOutputStream4 = new DataOutputStream(fileOutputStream2);
                            while (true) {
                                try {
                                    int read = bufferedInputStream5.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    dataOutputStream4.write(com.qq.reader.common.utils.a.b.b(bArr), 0, read);
                                } catch (Throwable th2) {
                                    th = th2;
                                    bufferedInputStream = bufferedInputStream5;
                                    dataOutputStream = dataOutputStream4;
                                    fileOutputStream = fileOutputStream2;
                                    gZIPInputStream = null;
                                    bufferedInputStream3 = bufferedInputStream;
                                }
                            }
                            dataOutputStream4.close();
                            fileOutputStream2.close();
                            bufferedInputStream5.close();
                            outputStream = fileOutputStream2;
                            dataOutputStream2 = dataOutputStream4;
                            bufferedInputStream2 = bufferedInputStream5;
                        } catch (Throwable th3) {
                            th = th3;
                            outputStream2 = fileOutputStream2;
                            gZIPInputStream = null;
                            bufferedInputStream = bufferedInputStream5;
                            dataOutputStream = null;
                            bufferedInputStream3 = bufferedInputStream;
                            if (bufferedInputStream3 != null) {
                                bufferedInputStream3.close();
                            }
                            if (dataOutputStream != null) {
                                dataOutputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            if (gZIPInputStream != null) {
                                gZIPInputStream.close();
                            }
                            if (file != null) {
                                a(file);
                            }
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        fileOutputStream = null;
                        gZIPInputStream = null;
                        bufferedInputStream = bufferedInputStream5;
                        dataOutputStream = null;
                        bufferedInputStream3 = bufferedInputStream;
                        if (bufferedInputStream3 != null) {
                            bufferedInputStream3.close();
                        }
                        if (dataOutputStream != null) {
                            dataOutputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        if (gZIPInputStream != null) {
                            gZIPInputStream.close();
                        }
                        if (file != null) {
                            a(file);
                        }
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    dataOutputStream = null;
                    fileOutputStream = null;
                    gZIPInputStream = null;
                    if (bufferedInputStream3 != null) {
                        bufferedInputStream3.close();
                    }
                    if (dataOutputStream != null) {
                        dataOutputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (gZIPInputStream != null) {
                        gZIPInputStream.close();
                    }
                    if (file != null) {
                        a(file);
                    }
                    throw th;
                }
            }
            file = file2;
            bufferedInputStream2 = null;
            Object obj = null;
            Object obj2 = null;
            try {
                file2 = new File(str2);
                bufferedInputStream4 = new BufferedInputStream(new FileInputStream(file));
            } catch (Throwable th6) {
                th = th6;
                dataOutputStream = dataOutputStream2;
                gZIPInputStream = null;
                bufferedInputStream3 = bufferedInputStream2;
                outputStream2 = outputStream;
                if (bufferedInputStream3 != null) {
                    bufferedInputStream3.close();
                }
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (gZIPInputStream != null) {
                    gZIPInputStream.close();
                }
                if (file != null) {
                    a(file);
                }
                throw th;
            }
            try {
                gZIPInputStream2 = new GZIPInputStream(bufferedInputStream4);
                try {
                    fileOutputStream = new FileOutputStream(file2);
                } catch (Throwable th7) {
                    th = th7;
                    inputStream = bufferedInputStream4;
                    outputStream2 = outputStream;
                    dataOutputStream = dataOutputStream2;
                    gZIPInputStream = gZIPInputStream2;
                    if (bufferedInputStream3 != null) {
                        bufferedInputStream3.close();
                    }
                    if (dataOutputStream != null) {
                        dataOutputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (gZIPInputStream != null) {
                        gZIPInputStream.close();
                    }
                    if (file != null) {
                        a(file);
                    }
                    throw th;
                }
            } catch (Throwable th8) {
                th = th8;
                outputStream2 = outputStream;
                dataOutputStream3 = dataOutputStream2;
                gZIPInputStream = null;
                inputStream = bufferedInputStream4;
                dataOutputStream = dataOutputStream3;
                if (bufferedInputStream3 != null) {
                    bufferedInputStream3.close();
                }
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (gZIPInputStream != null) {
                    gZIPInputStream.close();
                }
                if (file != null) {
                    a(file);
                }
                throw th;
            }
            try {
                DataOutputStream dataOutputStream5 = new DataOutputStream(fileOutputStream);
                try {
                    byte[] bArr2 = new byte[1024];
                    while (true) {
                        int read2 = gZIPInputStream2.read(bArr2);
                        if (read2 <= 0) {
                            break;
                        }
                        dataOutputStream5.write(bArr2, 0, read2);
                    }
                    if (bufferedInputStream4 != null) {
                        bufferedInputStream4.close();
                    }
                    if (dataOutputStream5 != null) {
                        dataOutputStream5.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (gZIPInputStream2 != null) {
                        gZIPInputStream2.close();
                    }
                    if (file != null) {
                        a(file);
                    }
                } catch (Throwable th9) {
                    th = th9;
                    gZIPInputStream = gZIPInputStream2;
                    dataOutputStream3 = dataOutputStream5;
                    inputStream = bufferedInputStream4;
                    dataOutputStream = dataOutputStream3;
                    if (bufferedInputStream3 != null) {
                        bufferedInputStream3.close();
                    }
                    if (dataOutputStream != null) {
                        dataOutputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (gZIPInputStream != null) {
                        gZIPInputStream.close();
                    }
                    if (file != null) {
                        a(file);
                    }
                    throw th;
                }
            } catch (Throwable th10) {
                th = th10;
                inputStream = bufferedInputStream4;
                dataOutputStream = dataOutputStream2;
                gZIPInputStream = gZIPInputStream2;
                if (bufferedInputStream3 != null) {
                    bufferedInputStream3.close();
                }
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (gZIPInputStream != null) {
                    gZIPInputStream.close();
                }
                if (file != null) {
                    a(file);
                }
                throw th;
            }
        } catch (Throwable th11) {
            th = th11;
            file = null;
            dataOutputStream = null;
            fileOutputStream = null;
            gZIPInputStream = null;
            if (bufferedInputStream3 != null) {
                bufferedInputStream3.close();
            }
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (gZIPInputStream != null) {
                gZIPInputStream.close();
            }
            if (file != null) {
                a(file);
            }
            throw th;
        }
    }

    public static List<File> a(InputStream inputStream, String str) throws FileNotFoundException, IOException, ArchiveException {
        List<File> linkedList = new LinkedList();
        org.apache.commons.compress.archivers.d.b bVar = (org.apache.commons.compress.archivers.d.b) new org.apache.commons.compress.archivers.c().a("tar", inputStream);
        while (true) {
            org.apache.commons.compress.archivers.d.a aVar = (org.apache.commons.compress.archivers.d.a) bVar.c();
            if (aVar != null) {
                File file = new File(str, aVar.a());
                if (!aVar.g()) {
                    if (file != null && file.exists()) {
                        file.delete();
                    }
                    OutputStream fileOutputStream = new FileOutputStream(file);
                    org.apache.commons.compress.a.b.a(bVar, fileOutputStream);
                    fileOutputStream.close();
                } else if (!ab.a(file.getParentFile())) {
                    throw new IllegalStateException(String.format("Couldn't create directory %s.", new Object[]{file.getAbsolutePath()}));
                }
                linkedList.add(file);
            } else {
                bVar.close();
                return linkedList;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b(java.lang.String r11, java.lang.String r12) throws java.io.IOException {
        /*
        r2 = 0;
        r0 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r3 = new java.io.File;	 Catch:{ Exception -> 0x0125, all -> 0x010b }
        r3.<init>(r11);	 Catch:{ Exception -> 0x0125, all -> 0x010b }
        r1 = new java.io.File;	 Catch:{ Exception -> 0x012d, all -> 0x010b }
        r1.<init>(r12);	 Catch:{ Exception -> 0x012d, all -> 0x010b }
        if (r1 == 0) goto L_0x0035;
    L_0x0011:
        r7 = r1.exists();	 Catch:{ Exception -> 0x0137, all -> 0x010b }
        if (r7 == 0) goto L_0x0035;
    L_0x0017:
        r7 = r3.exists();	 Catch:{ Exception -> 0x0137, all -> 0x010b }
        if (r7 != 0) goto L_0x0032;
    L_0x001d:
        if (r2 == 0) goto L_0x0022;
    L_0x001f:
        r6.close();	 Catch:{ Exception -> 0x015b }
    L_0x0022:
        if (r2 == 0) goto L_0x0027;
    L_0x0024:
        r5.close();	 Catch:{ Exception -> 0x015b }
    L_0x0027:
        if (r2 == 0) goto L_0x002c;
    L_0x0029:
        r4.close();	 Catch:{ Exception -> 0x015b }
    L_0x002c:
        if (r2 == 0) goto L_0x0031;
    L_0x002e:
        r0.close();	 Catch:{ Exception -> 0x015b }
    L_0x0031:
        return;
    L_0x0032:
        r1.delete();	 Catch:{ Exception -> 0x0137, all -> 0x010b }
    L_0x0035:
        r4 = r3.length();	 Catch:{ Exception -> 0x0137, all -> 0x010b }
        r0 = (int) r4;	 Catch:{ Exception -> 0x0137, all -> 0x010b }
        r0 = new byte[r0];	 Catch:{ Exception -> 0x0137, all -> 0x010b }
        r4 = new java.io.DataInputStream;	 Catch:{ Exception -> 0x0137, all -> 0x010b }
        r5 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x0137, all -> 0x010b }
        r5.<init>(r3);	 Catch:{ Exception -> 0x0137, all -> 0x010b }
        r4.<init>(r5);	 Catch:{ Exception -> 0x0137, all -> 0x010b }
        r4.readFully(r0);	 Catch:{ Exception -> 0x0140, all -> 0x0110 }
        r0 = com.qq.reader.common.utils.a.b.b(r0);	 Catch:{ Exception -> 0x0140, all -> 0x0110 }
        r7 = new java.util.zip.GZIPInputStream;	 Catch:{ Exception -> 0x0140, all -> 0x0110 }
        r5 = new java.io.ByteArrayInputStream;	 Catch:{ Exception -> 0x0140, all -> 0x0110 }
        r5.<init>(r0);	 Catch:{ Exception -> 0x0140, all -> 0x0110 }
        r7.<init>(r5);	 Catch:{ Exception -> 0x0140, all -> 0x0110 }
        r0 = new java.io.ByteArrayOutputStream;	 Catch:{ Exception -> 0x0149, all -> 0x0115 }
        r0.<init>();	 Catch:{ Exception -> 0x0149, all -> 0x0115 }
        r6 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x0149, all -> 0x0115 }
        r6.<init>(r1);	 Catch:{ Exception -> 0x0149, all -> 0x0115 }
        r5 = new java.io.BufferedOutputStream;	 Catch:{ Exception -> 0x0152, all -> 0x011a }
        r5.<init>(r6);	 Catch:{ Exception -> 0x0152, all -> 0x011a }
        r2 = 1050; // 0x41a float:1.471E-42 double:5.19E-321;
        r2 = new byte[r2];	 Catch:{ Exception -> 0x0076, all -> 0x011f }
    L_0x006a:
        r8 = r7.read(r2);	 Catch:{ Exception -> 0x0076, all -> 0x011f }
        r9 = -1;
        if (r8 == r9) goto L_0x00e5;
    L_0x0071:
        r9 = 0;
        r0.write(r2, r9, r8);	 Catch:{ Exception -> 0x0076, all -> 0x011f }
        goto L_0x006a;
    L_0x0076:
        r0 = move-exception;
        r2 = r3;
        r3 = r4;
        r4 = r5;
        r5 = r6;
        r6 = r7;
    L_0x007c:
        if (r2 == 0) goto L_0x0087;
    L_0x007e:
        r7 = r2.exists();	 Catch:{ all -> 0x00ce }
        if (r7 == 0) goto L_0x0087;
    L_0x0084:
        r2.delete();	 Catch:{ all -> 0x00ce }
    L_0x0087:
        if (r1 == 0) goto L_0x0092;
    L_0x0089:
        r2 = r1.exists();	 Catch:{ all -> 0x00ce }
        if (r2 == 0) goto L_0x0092;
    L_0x008f:
        r1.delete();	 Catch:{ all -> 0x00ce }
    L_0x0092:
        r1 = "Utility";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ce }
        r2.<init>();	 Catch:{ all -> 0x00ce }
        r7 = "onlineUnZip failed ";
        r2 = r2.append(r7);	 Catch:{ all -> 0x00ce }
        r7 = r0.getMessage();	 Catch:{ all -> 0x00ce }
        r2 = r2.append(r7);	 Catch:{ all -> 0x00ce }
        r2 = r2.toString();	 Catch:{ all -> 0x00ce }
        com.qq.reader.common.monitor.f.a(r1, r2);	 Catch:{ all -> 0x00ce }
        r1 = new java.io.IOException;	 Catch:{ all -> 0x00ce }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ce }
        r2.<init>();	 Catch:{ all -> 0x00ce }
        r7 = "onlineUnZip failed ";
        r2 = r2.append(r7);	 Catch:{ all -> 0x00ce }
        r0 = r0.getMessage();	 Catch:{ all -> 0x00ce }
        r0 = r2.append(r0);	 Catch:{ all -> 0x00ce }
        r0 = r0.toString();	 Catch:{ all -> 0x00ce }
        r1.<init>(r0);	 Catch:{ all -> 0x00ce }
        throw r1;	 Catch:{ all -> 0x00ce }
    L_0x00ce:
        r0 = move-exception;
        r2 = r4;
    L_0x00d0:
        if (r3 == 0) goto L_0x00d5;
    L_0x00d2:
        r3.close();	 Catch:{ Exception -> 0x0109 }
    L_0x00d5:
        if (r2 == 0) goto L_0x00da;
    L_0x00d7:
        r2.close();	 Catch:{ Exception -> 0x0109 }
    L_0x00da:
        if (r5 == 0) goto L_0x00df;
    L_0x00dc:
        r5.close();	 Catch:{ Exception -> 0x0109 }
    L_0x00df:
        if (r6 == 0) goto L_0x00e4;
    L_0x00e1:
        r6.close();	 Catch:{ Exception -> 0x0109 }
    L_0x00e4:
        throw r0;
    L_0x00e5:
        r0 = r0.toByteArray();	 Catch:{ Exception -> 0x0076, all -> 0x011f }
        r0 = com.qq.reader.common.utils.a.b.a(r0);	 Catch:{ Exception -> 0x0076, all -> 0x011f }
        r5.write(r0);	 Catch:{ Exception -> 0x0076, all -> 0x011f }
        if (r4 == 0) goto L_0x00f5;
    L_0x00f2:
        r4.close();	 Catch:{ Exception -> 0x0106 }
    L_0x00f5:
        if (r5 == 0) goto L_0x00fa;
    L_0x00f7:
        r5.close();	 Catch:{ Exception -> 0x0106 }
    L_0x00fa:
        if (r6 == 0) goto L_0x00ff;
    L_0x00fc:
        r6.close();	 Catch:{ Exception -> 0x0106 }
    L_0x00ff:
        if (r7 == 0) goto L_0x0031;
    L_0x0101:
        r7.close();	 Catch:{ Exception -> 0x0106 }
        goto L_0x0031;
    L_0x0106:
        r0 = move-exception;
        goto L_0x0031;
    L_0x0109:
        r1 = move-exception;
        goto L_0x00e4;
    L_0x010b:
        r0 = move-exception;
        r3 = r2;
        r5 = r2;
        r6 = r2;
        goto L_0x00d0;
    L_0x0110:
        r0 = move-exception;
        r3 = r4;
        r5 = r2;
        r6 = r2;
        goto L_0x00d0;
    L_0x0115:
        r0 = move-exception;
        r3 = r4;
        r5 = r2;
        r6 = r7;
        goto L_0x00d0;
    L_0x011a:
        r0 = move-exception;
        r3 = r4;
        r5 = r6;
        r6 = r7;
        goto L_0x00d0;
    L_0x011f:
        r0 = move-exception;
        r3 = r4;
        r2 = r5;
        r5 = r6;
        r6 = r7;
        goto L_0x00d0;
    L_0x0125:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
        r4 = r2;
        r5 = r2;
        r6 = r2;
        goto L_0x007c;
    L_0x012d:
        r0 = move-exception;
        r1 = r2;
        r4 = r2;
        r5 = r2;
        r6 = r2;
        r10 = r2;
        r2 = r3;
        r3 = r10;
        goto L_0x007c;
    L_0x0137:
        r0 = move-exception;
        r4 = r2;
        r5 = r2;
        r6 = r2;
        r10 = r2;
        r2 = r3;
        r3 = r10;
        goto L_0x007c;
    L_0x0140:
        r0 = move-exception;
        r5 = r2;
        r6 = r2;
        r10 = r2;
        r2 = r3;
        r3 = r4;
        r4 = r10;
        goto L_0x007c;
    L_0x0149:
        r0 = move-exception;
        r5 = r2;
        r6 = r7;
        r10 = r2;
        r2 = r3;
        r3 = r4;
        r4 = r10;
        goto L_0x007c;
    L_0x0152:
        r0 = move-exception;
        r5 = r6;
        r6 = r7;
        r10 = r2;
        r2 = r3;
        r3 = r4;
        r4 = r10;
        goto L_0x007c;
    L_0x015b:
        r0 = move-exception;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.utils.ao.b(java.lang.String, java.lang.String):void");
    }

    public static final void a(ByteArrayOutputStream byteArrayOutputStream, int i) throws IOException {
        byteArrayOutputStream.write((i >>> 24) & 255);
        byteArrayOutputStream.write((i >>> 16) & 255);
        byteArrayOutputStream.write((i >>> 8) & 255);
        byteArrayOutputStream.write((i >>> 0) & 255);
    }

    public static final void a(ByteArrayOutputStream byteArrayOutputStream, long j) throws IOException {
        byteArrayOutputStream.write(new byte[]{(byte) ((int) (j >>> 56)), (byte) ((int) (j >>> 48)), (byte) ((int) (j >>> 40)), (byte) ((int) (j >>> 32)), (byte) ((int) (j >>> 24)), (byte) ((int) (j >>> 16)), (byte) ((int) (j >>> 8)), (byte) ((int) (j >>> 0))}, 0, 8);
    }

    public static String g() {
        return com.qq.reader.common.c.a.bk;
    }

    public static String c(long j) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(com.qq.reader.common.c.a.aO);
        stringBuffer.append("/");
        stringBuffer.append(j);
        return stringBuffer.toString();
    }

    public static String d(String str) {
        return g() + File.separator + str;
    }

    public static boolean j(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (connectivityManager == null || activeNetworkInfo == null || activeNetworkInfo.getState() == null || telephonyManager == null) {
                return false;
            }
            boolean z = activeNetworkInfo.getState() == State.CONNECTED || telephonyManager.getNetworkType() == 3;
            return z;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static void a(Context context, byte b, OnlineTag onlineTag, List<Integer> list) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("《");
        stringBuilder.append(e(onlineTag.b()));
        stringBuilder.append("》");
        CharSequence stringBuilder2 = stringBuilder.toString();
        android.support.v4.app.t.d y = y(context);
        PendingIntent activity;
        switch (b) {
            case (byte) 25:
                notificationManager.cancel(25);
                notificationManager.cancel(26);
                intent.setFlags(335544320);
                intent.setClass(context, SwitchViewActivity.class);
                intent.putExtra("notification_tag", (byte) 25);
                intent.putExtra("com.qq.reader.fromonline", true);
                intent.putExtra("com.qq.reader.OnlineTag", onlineTag);
                activity = PendingIntent.getActivity(context, 25, intent, WtloginHelper.SigType.WLOGIN_PT4Token);
                CharSequence charSequence = "您选择的章节已下载完成";
                y.c(charSequence);
                y.a(charSequence);
                y.b(charSequence);
                y.a(activity);
                notificationManager.notify(b, y.a());
                return;
            case (byte) 26:
                notificationManager.cancel(25);
                notificationManager.cancel(26);
                intent.setAction("com.qq.reader.chapter.download");
                bundle.putBoolean("com.qq.reader.chapter.downloadresult", false);
                bundle.putParcelable("com.qq.reader.OnlineTag", onlineTag);
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(list);
                bundle.putIntegerArrayList("com.qq.reader.chapter.DownloadResult", arrayList);
                intent.putExtras(bundle);
                activity = PendingIntent.getBroadcast(context, 26, intent, WtloginHelper.SigType.WLOGIN_PT4Token);
                y.c((CharSequence) "章节下载失败");
                y.a((int) R.drawable.download_plugin_icon_fail);
                y.a(stringBuilder2);
                y.b((CharSequence) "您批量选择的章节下载失败,点击重试");
                y.a(activity);
                notificationManager.notify(b, y.a());
                return;
            default:
                return;
        }
    }

    public static void a(Context context, byte b, int i, com.qq.reader.common.download.task.g gVar) {
        if (i != 1 || gVar != null) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            StringBuffer stringBuffer = new StringBuffer();
            android.support.v4.app.t.d y = y(context);
            CharSequence stringBuffer2;
            PendingIntent activity;
            switch (b) {
                case (byte) 0:
                    if (i == 1 && gVar != null) {
                        notificationManager.cancel(0);
                        stringBuffer.append("《");
                        stringBuffer.append(e(gVar.getName()));
                        stringBuffer.append("》开始下载");
                        stringBuffer2 = stringBuffer.toString();
                        activity = PendingIntent.getActivity(context, 0, intent, 0);
                        y.c(stringBuffer2);
                        y.a(stringBuffer2);
                        y.b((CharSequence) "开始下载");
                        y.a(activity);
                        notificationManager.notify(b, y.a());
                        notificationManager.cancel(0);
                        return;
                    }
                    return;
                case (byte) 1:
                    notificationManager.cancel(1);
                    if (i != 1 || gVar == null) {
                        stringBuffer.append("《");
                        stringBuffer.append(e(gVar.getName()));
                        stringBuffer.append("》等");
                        stringBuffer.append(i);
                        stringBuffer.append("本书");
                        stringBuffer2 = stringBuffer.toString();
                        intent.setClass(context, SwitchViewActivity.class);
                        intent.putExtra("notification_tag", (byte) 3);
                    } else {
                        String str = "《" + e(gVar.getName()) + "》";
                        intent.setClass(context, SwitchViewActivity.class);
                        if (gVar instanceof DownloadBookTask) {
                            bundle.putString("filepath", ((DownloadBookTask) gVar).getFilePath());
                            bundle.putString("filename", ((DownloadBookTask) gVar).getFullName());
                            bundle.putString("fileauthor", ((DownloadBookTask) gVar).getAuthor());
                        }
                        intent.putExtras(bundle);
                        intent.putExtra("notification_tag", (byte) 2);
                        stringBuffer2 = str;
                    }
                    SwitchViewActivity.a = intent;
                    intent.setFlags(335544320);
                    activity = PendingIntent.getActivity(context, 1, intent, WtloginHelper.SigType.WLOGIN_PT4Token);
                    y.c(stringBuffer2);
                    y.a(stringBuffer2);
                    y.b((CharSequence) "下载完成");
                    y.a(activity);
                    Notification a = y.a();
                    a.number = i;
                    notificationManager.notify(b, a);
                    return;
                case (byte) 25:
                    notificationManager.cancel(25);
                    intent.setFlags(335544320);
                    intent.setClass(context, SwitchViewActivity.class);
                    intent.putExtra("notification_tag", (byte) 25);
                    PendingIntent activity2 = PendingIntent.getActivity(context, 25, intent, WtloginHelper.SigType.WLOGIN_PT4Token);
                    CharSequence charSequence = "您选择的章节已下载完成";
                    y.c(charSequence);
                    y.a(charSequence);
                    y.a(activity2);
                    notificationManager.notify(b, y.a());
                    return;
                default:
                    return;
            }
        }
    }

    public static String e(String str) {
        if (str.length() > 13) {
            return str.substring(0, 12) + "…";
        }
        return str;
    }

    public static String h() {
        return new SimpleDateFormat("yyyy年MM月dd日").format(new Date(System.currentTimeMillis()));
    }

    public static String d(long j) {
        return new SimpleDateFormat("yyyy年MM月dd日").format(new Date(j));
    }

    public static String f(String str) {
        Time time = new Time();
        time.setToNow();
        StringBuffer stringBuffer = new StringBuffer(10);
        stringBuffer.append(time.year);
        stringBuffer.append(str);
        stringBuffer.append(time.month + 1);
        stringBuffer.append(str);
        stringBuffer.append(time.monthDay);
        return stringBuffer.toString();
    }

    public static long g(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").parse(str).getTime();
        } catch (Exception e) {
            return 0;
        }
    }

    public static Long h(String str) {
        long time;
        System.currentTimeMillis();
        try {
            time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str).getTime();
        } catch (Exception e) {
            time = System.currentTimeMillis();
        }
        return Long.valueOf(time);
    }

    public static String i(String str) {
        long longValue = h(str).longValue();
        StringBuilder stringBuilder = new StringBuilder();
        longValue -= System.currentTimeMillis();
        if (longValue > 0) {
            stringBuilder.append("还剩");
            int i = (int) (longValue / TimeFormatterUtils.ONE_DAY);
            if (i > 0) {
                stringBuilder.append(i);
                stringBuilder.append("天");
                longValue -= ((long) i) * TimeFormatterUtils.ONE_DAY;
            }
            i = (int) (longValue / 3600000);
            if (i > 0) {
                stringBuilder.append(i);
                stringBuilder.append("小时");
                longValue -= ((long) i) * 3600000;
            }
            int i2 = (int) (longValue / BuglyBroadcastRecevier.UPLOADLIMITED);
            if (i2 > 0) {
                stringBuilder.append(i2);
                stringBuilder.append("分钟");
            }
        }
        return stringBuilder.toString();
    }

    public static String c(String str, String str2) {
        Time time = new Time();
        time.setToNow();
        StringBuffer stringBuffer = new StringBuffer(10);
        stringBuffer.append(time.year);
        stringBuffer.append(str);
        if (time.month + 1 >= 10) {
            stringBuffer.append(time.month + 1);
        } else {
            stringBuffer.append(0);
            stringBuffer.append(time.month + 1);
        }
        stringBuffer.append(str);
        if (time.monthDay >= 10) {
            stringBuffer.append(time.monthDay);
        } else {
            stringBuffer.append(0);
            stringBuffer.append(time.monthDay);
        }
        stringBuffer.append(" ");
        if (time.hour >= 10) {
            stringBuffer.append(time.hour);
        } else {
            stringBuffer.append(0);
            stringBuffer.append(time.hour);
        }
        stringBuffer.append(str2);
        if (time.minute >= 10) {
            stringBuffer.append(time.minute);
        } else {
            stringBuffer.append(0);
            stringBuffer.append(time.minute);
        }
        stringBuffer.append(str2);
        if (time.second >= 10) {
            stringBuffer.append(time.second);
        } else {
            stringBuffer.append(0);
            stringBuffer.append(time.second);
        }
        return stringBuffer.toString();
    }

    public static void a(Throwable th) {
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printWriter);
        }
        String obj = stringWriter.toString();
        printWriter.close();
        com.qq.reader.common.monitor.j.c("Reader--------> error : " + th.toString() + "  \n trace : " + obj);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String r5, boolean r6) {
        /*
        if (r5 == 0) goto L_0x000c;
    L_0x0002:
        r0 = r5.trim();
        r0 = r0.length();
        if (r0 != 0) goto L_0x0010;
    L_0x000c:
        r0 = "";
    L_0x000f:
        return r0;
    L_0x0010:
        r0 = new java.lang.String;
        r0.<init>();
        r1 = 0;
        r0 = r5.charAt(r1);
        r4 = r0;
        r0 = r1;
        r1 = r4;
    L_0x001d:
        r2 = 32;
        if (r1 == r2) goto L_0x0025;
    L_0x0021:
        r2 = 12288; // 0x3000 float:1.7219E-41 double:6.071E-320;
        if (r1 != r2) goto L_0x0067;
    L_0x0025:
        r0 = r0 + 1;
        r1 = r5.length();
        if (r0 < r1) goto L_0x004a;
    L_0x002d:
        r2 = r0;
    L_0x002e:
        if (r6 == 0) goto L_0x0062;
    L_0x0030:
        r0 = r5.length();
        r0 = r0 + -1;
        r1 = r5.charAt(r0);
    L_0x003a:
        r3 = 13;
        if (r1 == r3) goto L_0x0055;
    L_0x003e:
        r1 = r0 + -1;
        if (r0 <= 0) goto L_0x004f;
    L_0x0042:
        r0 = r5.charAt(r1);
        r4 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x003a;
    L_0x004a:
        r1 = r5.charAt(r0);
        goto L_0x001d;
    L_0x004f:
        r0 = r5.length();
        r0 = r0 + -1;
    L_0x0055:
        r0 = r5.substring(r2, r0);
        r0 = r0.trim();
        r0 = z(r0);
        goto L_0x000f;
    L_0x0062:
        r0 = r5.length();
        goto L_0x0055;
    L_0x0067:
        r2 = r0;
        goto L_0x002e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.utils.ao.a(java.lang.String, boolean):java.lang.String");
    }

    private static String z(String str) {
        if (str.startsWith("[(") && str.endsWith(")]")) {
            return str.substring(2, str.length() - 2);
        }
        return str;
    }

    public static void a(int i, float[] fArr) {
        float f = 360.0f;
        float f2 = 1.0f;
        float red = ((float) Color.red(i)) / 255.0f;
        float green = ((float) Color.green(i)) / 255.0f;
        float blue = ((float) Color.blue(i)) / 255.0f;
        float min = Math.min(red, Math.min(blue, green));
        float max = Math.max(red, Math.max(blue, green));
        if (max == min) {
            blue = 0.0f;
        } else if (max == red && green >= blue) {
            blue = ((green - blue) * 60.0f) / (max - min);
        } else if (max == red && green < blue) {
            blue = (((green - blue) * 60.0f) / (max - min)) + 360.0f;
        } else if (max == green) {
            blue = (((blue - red) * 60.0f) / (max - min)) + 120.0f;
        } else if (max == blue) {
            blue = (((red - green) * 60.0f) / (max - min)) + 240.0f;
        } else {
            blue = 0.0f;
        }
        red = (max + min) / 2.0f;
        if (red == 0.0f || max == min) {
            green = 0.0f;
        } else if (0.0f < red && red <= 0.5f) {
            green = (max - min) / (min + max);
        } else if (red > 0.5f) {
            green = (max - min) / (2.0f - (min + max));
        } else {
            green = 0.0f;
        }
        if (blue <= 360.0f) {
            f = blue < 0.0f ? 0.0f : blue;
        }
        if (green > 1.0f) {
            green = 1.0f;
        } else if (green < 0.0f) {
            green = 0.0f;
        }
        if (red <= 1.0f) {
            f2 = red < 0.0f ? 0.0f : red;
        }
        fArr[0] = f;
        fArr[1] = green;
        fArr[2] = f2;
    }

    public static int a(float[] fArr) {
        float f;
        float f2 = fArr[0];
        float f3 = fArr[1];
        float f4 = fArr[2];
        if (f3 == 0.0f) {
            f3 = f4 * 255.0f;
            f2 = f3;
            f = f3;
        } else {
            if (f4 < 0.5f) {
                f3 = (f3 + 1.0f) * f4;
            } else {
                f3 = (f4 + f3) - (f3 * f4);
            }
            f4 = (f4 * 2.0f) - f3;
            f2 /= 360.0f;
            float[] fArr2 = new float[]{0.3333333f + f2, f2, f2 - 0.3333333f};
            for (int i = 0; i < 3; i++) {
                if (fArr2[i] < 0.0f) {
                    fArr2[i] = fArr2[i] + 1.0f;
                }
                if (fArr2[i] > 1.0f) {
                    fArr2[i] = fArr2[i] - 1.0f;
                }
                if (fArr2[i] * 6.0f < 1.0f) {
                    fArr2[i] = (((f3 - f4) * 6.0f) * fArr2[i]) + f4;
                } else if (fArr2[i] * 2.0f < 1.0f) {
                    fArr2[i] = f3;
                } else if (fArr2[i] * 3.0f < 2.0f) {
                    fArr2[i] = (((f3 - f4) * (0.6666667f - fArr2[i])) * 6.0f) + f4;
                } else {
                    fArr2[i] = f4;
                }
            }
            f = fArr2[0] * 255.0f;
            f2 = fArr2[1] * 255.0f;
            f3 = fArr2[2] * 255.0f;
        }
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 255.0f) {
            f = 255.0f;
        }
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > 255.0f) {
            f2 = 255.0f;
        }
        if (f3 < 0.0f) {
            f3 = 0.0f;
        }
        if (f3 > 255.0f) {
            f3 = 255.0f;
        }
        return Color.rgb(Math.round(f), Math.round(f2), Math.round(f3));
    }

    public static Bitmap j(String str) {
        FileInputStream fileInputStream;
        Throwable th;
        Bitmap bitmap = null;
        File file = new File(str);
        if (file != null && file.exists()) {
            Options options = new Options();
            options.inPurgeable = true;
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    bitmap = BitmapFactory.decodeStream(fileInputStream, null, options);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e) {
                            f.a("Utility", "decodeWithOptions error : " + e.toString());
                        }
                    }
                } catch (IOException e2) {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e3) {
                            f.a("Utility", "decodeWithOptions error : " + e3.toString());
                        }
                    }
                    return bitmap;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e32) {
                            f.a("Utility", "decodeWithOptions error : " + e32.toString());
                        }
                    }
                    throw th;
                }
            } catch (IOException e4) {
                fileInputStream = bitmap;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return bitmap;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                fileInputStream = bitmap;
                th = th4;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        }
        return bitmap;
    }

    public static ArrayList<String> d(String str, String str2) throws Exception {
        Throwable th;
        BufferedOutputStream bufferedOutputStream = null;
        ArrayList<String> arrayList = new ArrayList();
        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(str)));
        while (true) {
            BufferedOutputStream bufferedOutputStream2;
            try {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    break;
                }
                byte[] bArr = new byte[4096];
                File file = new File(str2 + nextEntry.getName());
                ab.a(new File(file.getParent()));
                bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file), 4096);
                while (true) {
                    try {
                        int read = zipInputStream.read(bArr, 0, 4096);
                        if (read == -1) {
                            break;
                        }
                        bufferedOutputStream2.write(bArr, 0, read);
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                bufferedOutputStream2.flush();
                bufferedOutputStream2.close();
                arrayList.add(file.getPath());
            } catch (Throwable th3) {
                th = th3;
                bufferedOutputStream2 = bufferedOutputStream;
            }
        }
        if (zipInputStream != null) {
            zipInputStream.close();
        }
        if (bufferedOutputStream != null) {
            bufferedOutputStream.close();
        }
        return arrayList;
        if (zipInputStream != null) {
            zipInputStream.close();
        }
        if (bufferedOutputStream2 != null) {
            bufferedOutputStream2.close();
        }
        throw th;
    }

    public static String a(StringBuffer stringBuffer) {
        for (int i = 0; i < stringBuffer.length(); i++) {
            for (char c : l) {
                if (stringBuffer.charAt(i) == c) {
                    stringBuffer.deleteCharAt(i);
                    break;
                }
            }
        }
        return stringBuffer.toString();
    }

    public static boolean d(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        float f = displayMetrics.density;
        int i3 = displayMetrics.densityDpi;
        if (((float) Math.sqrt(Math.pow((double) i2, 2.0d) + Math.pow((double) i, 2.0d))) / ((float) i3) >= 6.5f) {
            return true;
        }
        return false;
    }

    public static List<char[]> a(String str, TextPaint textPaint, float f) {
        List<char[]> arrayList = new ArrayList(5);
        int i = 0;
        Object toCharArray = str.toCharArray();
        int length = toCharArray.length;
        int i2 = 0;
        float f2 = 0.0f;
        while (i2 < length) {
            int i3;
            float f3;
            Object obj;
            if (toCharArray[i2] == '\n') {
                i3 = i2 - i;
                obj = new char[i3];
                System.arraycopy(toCharArray, i, obj, 0, i3);
                arrayList.add(obj);
                i3 = i2 + 1;
                f3 = 0.0f;
            } else {
                char c = toCharArray[i2];
                float b = b(c, textPaint);
                if (f2 + b > f && c > ' ' && c < '') {
                    i3 = i2 - 1;
                    while (i3 > i) {
                        if (toCharArray[i3] <= ' ' || toCharArray[i3] >= '') {
                            i3++;
                            break;
                        }
                        i3--;
                    }
                }
                i3 = i2;
                float f4 = f2 + b;
                if (f4 > f && i3 < length - 1) {
                    i2 = i3 - i;
                    obj = new char[i2];
                    System.arraycopy(toCharArray, i, obj, 0, i2);
                    arrayList.add(obj);
                    i2 = i3 - 1;
                    f3 = 0.0f;
                } else if (i3 == length - 1) {
                    i2 = (i3 + 1) - i;
                    Object obj2 = new char[i2];
                    System.arraycopy(toCharArray, i, obj2, 0, i2);
                    arrayList.add(obj2);
                    break;
                } else {
                    int i4 = i3;
                    i3 = i;
                    f3 = f4;
                    i2 = i4;
                }
            }
            i2++;
            f2 = f3;
            i = i3;
        }
        return arrayList;
    }

    public static void i() {
        m = -1.0f;
    }

    public static float a(char c, TextPaint textPaint) {
        if (c <= 'ÿ' || c == '“' || c == '”' || c == '‘' || c == '’' || c == '…') {
            return textPaint.measureText(new char[]{c}, 0, 1);
        }
        if (n <= 0.0f) {
            n = textPaint.measureText("中");
        }
        return n;
    }

    public static void j() {
        n = -1.0f;
    }

    public static float b(char c, TextPaint textPaint) {
        if (c > 'ÿ' && c != '“' && c != '”' && c != '‘' && c != '’' && c != '…') {
            return textPaint.measureText("中");
        }
        return textPaint.measureText(new char[]{c}, 0, 1);
    }

    public static String k(String str) {
        if (str == null) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf != -1) {
            return str.substring(0, lastIndexOf);
        }
        return str;
    }

    public static String l(String str) {
        String str2 = null;
        try {
            String substring = str.substring(str.lastIndexOf("/") + 1, str.length());
            if (substring != null) {
                str2 = k(substring);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2;
    }

    public static long a(int i, int i2, int i3) {
        return ((((long) i) << 32) | (((long) i2) << 8)) | (((long) i3) & 255);
    }

    public static String m(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(com.qq.reader.common.c.a.cR);
        stringBuffer.append(str);
        stringBuffer.append(".ttf");
        return stringBuffer.toString();
    }

    public static File n(String str) {
        if (str != null) {
            try {
                if (str.length() > 0) {
                    String c = k.b().c(str);
                    if (c != null) {
                        File file = new File(m(c));
                        if (file.exists()) {
                            return file;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static File k() {
        try {
            if (b != null && b.length() > 0) {
                String c = k.b().c(b);
                if (c != null) {
                    File file = new File(m(c));
                    if (file.exists()) {
                        return file;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[] k(Context context) {
        if (o == null) {
            o = context.getResources().getStringArray(R.array.fileEnding);
        }
        return o;
    }

    public static String[] a(Context context, int i) {
        switch (i) {
            case 0:
                return context.getResources().getStringArray(R.array.fileEnding);
            case 1:
                return g;
            case 2:
                return h;
            case 3:
                return i;
            case 4:
                return j;
            case 5:
                return k;
            default:
                return null;
        }
    }

    public static boolean a(String str, Context context) {
        for (String endsWith : new ArrayList(Arrays.asList(k(context)))) {
            if (str.toLowerCase().endsWith(endsWith)) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(String str, Context context, int i) {
        for (String endsWith : new ArrayList(Arrays.asList(a(context, i)))) {
            if (str.toLowerCase().endsWith(endsWith)) {
                return true;
            }
        }
        return false;
    }

    public static boolean b(String str, Context context) {
        for (String endsWith : new ArrayList(Arrays.asList(k(context)))) {
            if (str.toLowerCase().endsWith(endsWith)) {
                return true;
            }
        }
        return false;
    }

    public static File[] a(String str, final String[] strArr) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            return file.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    if (file.isDirectory()) {
                        return false;
                    }
                    String toLowerCase = file.getName().toLowerCase();
                    for (String endsWith : strArr) {
                        if (toLowerCase.endsWith(endsWith)) {
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
        return null;
    }

    public static String a(MusicOnlineTag musicOnlineTag) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(com.qq.reader.common.c.a.aO);
        stringBuffer.append("/");
        stringBuffer.append(musicOnlineTag.getBid());
        stringBuffer.append("/");
        stringBuffer.append(musicOnlineTag.getBid());
        stringBuffer.append("_");
        stringBuffer.append(musicOnlineTag.getCid());
        stringBuffer.append(".");
        stringBuffer.append(musicOnlineTag.getFileFormat());
        return stringBuffer.toString();
    }

    public static void l(Context context) {
        ((AlarmManager) context.getSystemService("alarm")).cancel(PendingIntent.getBroadcast(context, 0, new Intent(context, AlarmReceiver.class), 0));
    }

    public static void m(Context context) {
        ((AlarmManager) context.getSystemService("alarm")).set(1, com.qq.reader.appconfig.a.d.b().getTimeInMillis(), PendingIntent.getBroadcast(context, 0, new Intent(context, AlarmReceiver.class), 0));
    }

    public static int o(String str) {
        if (str == null || str.length() == 0) {
            return com.qq.reader.common.c.a.cT[0];
        }
        return com.qq.reader.common.c.a.cT[Math.abs(str.hashCode()) % com.qq.reader.common.c.a.cT.length];
    }

    public static void a(Activity activity, boolean z) {
        try {
            LayoutParams attributes = activity.getWindow().getAttributes();
            Field field = attributes.getClass().getField("buttonBrightness");
            if (field != null && "float".equals(field.getType().toString())) {
                field.setFloat(attributes, z ? -1.0f : 0.0f);
                activity.getWindow().setAttributes(attributes);
            }
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e2) {
        }
    }

    public static String e(long j) {
        long currentTimeMillis = (System.currentTimeMillis() / 1000) - (j / 1000);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("上次更新:");
        if (currentTimeMillis > TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC) {
            stringBuffer.append(currentTimeMillis / TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC);
            stringBuffer.append("天前");
        } else if (currentTimeMillis > 3600) {
            stringBuffer.append(currentTimeMillis / 3600);
            stringBuffer.append("小时前");
        } else if (currentTimeMillis > 60) {
            stringBuffer.append(currentTimeMillis / 60);
            stringBuffer.append("分钟前");
        } else {
            stringBuffer.append("刚刚");
        }
        return stringBuffer.toString();
    }

    public static String n(Context context) {
        if (!com.qq.reader.common.login.c.b()) {
            return com.qq.reader.common.c.a.aF + "default_chapter_payed.db";
        }
        return p(com.qq.reader.appconfig.a.d.R(context)) + "chapter_payed.db";
    }

    public static String p(String str) {
        return com.qq.reader.common.c.a.aF + str + "/";
    }

    public static int l() {
        if (ReaderApplication.getApplicationImp().getResources().getConfiguration().orientation == 1) {
            return 0;
        }
        return 1;
    }

    public static int a(Bitmap bitmap, int i, int i2) {
        if (bitmap == null || bitmap.isRecycled()) {
            return 0;
        }
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                int pixel = bitmap.getPixel(((bitmap.getWidth() - i) / 2) + i3, ((bitmap.getHeight() - i2) / 2) + i4);
                j += (long) (16711680 & pixel);
                j2 += (long) (65280 & pixel);
                j3 += (long) (pixel & 255);
            }
        }
        return Color.rgb((int) (((j / ((long) (i * i2))) >> 16) & 255), (int) (((j2 / ((long) (i * i2))) >> 8) & 255), (int) ((j3 / ((long) (i * i2))) & 255));
    }

    public static int a(Drawable drawable) {
        Bitmap createBitmap = Bitmap.createBitmap(10, 10, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, 10, 10);
        drawable.draw(canvas);
        int a = a(createBitmap, 10, 10);
        createBitmap.recycle();
        System.gc();
        return a;
    }

    public static String f(long j) {
        if (j <= 0) {
            return "";
        }
        float f = com.qq.reader.common.c.a.bZ;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("http://wfqqreader.3g.qq.com/cover/");
        stringBuffer.append("" + (j % 1000));
        stringBuffer.append("/");
        stringBuffer.append(j);
        stringBuffer.append("/t9_");
        stringBuffer.append(j);
        stringBuffer.append(".jpg");
        return stringBuffer.toString();
    }

    public static String g(long j) {
        if (j <= 0) {
            return "";
        }
        float f = com.qq.reader.common.c.a.bZ;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("http://wfqqreader.3g.qq.com/cover/");
        stringBuffer.append("" + (j % 1000));
        stringBuffer.append("/");
        stringBuffer.append(j);
        stringBuffer.append("/");
        if (((double) f) <= 1.5d) {
            stringBuffer.append("t3_");
        } else if (f <= 2.0f) {
            stringBuffer.append("t5_");
        } else {
            stringBuffer.append("t5_");
        }
        stringBuffer.append(j);
        stringBuffer.append(".jpg");
        return stringBuffer.toString();
    }

    public static String q(String str) {
        String substring;
        String str2 = null;
        float f = com.qq.reader.common.c.a.bZ;
        int lastIndexOf = str.lastIndexOf(File.separatorChar);
        if (lastIndexOf >= 0) {
            String substring2 = str.substring(lastIndexOf);
            substring = str.substring(0, lastIndexOf + 1);
            int indexOf = substring2.indexOf("_");
            String str3;
            if (indexOf >= 0) {
                str3 = substring;
                substring = substring2.substring(indexOf);
                str2 = str3;
            } else {
                str3 = substring;
                substring = null;
                str2 = str3;
            }
        } else {
            substring = null;
        }
        if (substring == null) {
            return str;
        }
        if (f <= 1.0f) {
            substring = "t3" + substring;
        } else if (((double) f) <= 1.5d) {
            substring = "t5" + substring;
        } else if (f <= 2.0f) {
            substring = "t5" + substring;
        } else {
            substring = "t5" + substring;
        }
        return str2 + substring;
    }

    public static String a(long j, int i, int i2) {
        if (j <= 0) {
            return "";
        }
        if (i <= 0) {
            i = a(93.0f);
        }
        if (i2 <= 0) {
            i2 = a(142.0f);
        }
        return com.qq.reader.module.comic.a.a().a(j, i, i2);
    }

    public static String h(long j) {
        if (j <= 0) {
            return "";
        }
        return a(j, a(94.0f), a(124.0f));
    }

    public static String a(long j, boolean z, int i) {
        if (j <= 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://qidian.qpic.cn/qdbimg/349573");
        if (z) {
            stringBuilder.append("/s_");
        } else {
            stringBuilder.append("/a_");
        }
        stringBuilder.append(j);
        stringBuilder.append("/");
        stringBuilder.append(i);
        return stringBuilder.toString();
    }

    public static Bitmap b(Context context, int i) {
        try {
            Drawable drawable = context.getResources().getDrawable(i);
            if (drawable != null) {
                if (drawable instanceof BitmapDrawable) {
                    return ((BitmapDrawable) drawable).getBitmap();
                }
                if (drawable instanceof SkinnableBitmapDrawable) {
                    return ((SkinnableBitmapDrawable) drawable).getBitmap();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap c(Context context, int i) {
        InputStream openRawResource;
        Throwable th;
        Bitmap bitmap = null;
        Options options = new Options();
        options.inPreferredConfig = Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        try {
            openRawResource = context.getResources().openRawResource(i);
            try {
                bitmap = BitmapFactory.decodeStream(openRawResource, null, options);
                if (openRawResource != null) {
                    try {
                        openRawResource.close();
                    } catch (IOException e) {
                    }
                }
            } catch (Exception e2) {
                if (openRawResource != null) {
                    try {
                        openRawResource.close();
                    } catch (IOException e3) {
                    }
                }
                return bitmap;
            } catch (Throwable th2) {
                th = th2;
                if (openRawResource != null) {
                    try {
                        openRawResource.close();
                    } catch (IOException e4) {
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            openRawResource = bitmap;
            if (openRawResource != null) {
                openRawResource.close();
            }
            return bitmap;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            openRawResource = bitmap;
            th = th4;
            if (openRawResource != null) {
                openRawResource.close();
            }
            throw th;
        }
        return bitmap;
    }

    public static Bitmap d(Context context, int i) {
        InputStream openRawResource;
        Throwable th;
        Bitmap bitmap = null;
        Options options = new Options();
        options.inPreferredConfig = Config.ARGB_8888;
        options.inPurgeable = true;
        options.inInputShareable = true;
        try {
            openRawResource = context.getResources().openRawResource(i);
            try {
                bitmap = BitmapFactory.decodeStream(openRawResource, null, options);
                if (openRawResource != null) {
                    try {
                        openRawResource.close();
                    } catch (IOException e) {
                    }
                }
            } catch (Exception e2) {
                if (openRawResource != null) {
                    try {
                        openRawResource.close();
                    } catch (IOException e3) {
                    }
                }
                return bitmap;
            } catch (Throwable th2) {
                th = th2;
                if (openRawResource != null) {
                    try {
                        openRawResource.close();
                    } catch (IOException e4) {
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            openRawResource = bitmap;
            if (openRawResource != null) {
                openRawResource.close();
            }
            return bitmap;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            openRawResource = bitmap;
            th = th4;
            if (openRawResource != null) {
                openRawResource.close();
            }
            throw th;
        }
        return bitmap;
    }

    public static void a(Context context, WebSettings webSettings) {
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheMaxSize(8388608);
        webSettings.setCacheMode(1);
        webSettings.setAppCachePath(context.getDir("cache", 0).getPath());
        webSettings.setDatabaseEnabled(true);
        webSettings.setDatabasePath(context.getDir("storage", 0).getPath());
        if (VERSION.SDK_INT >= 16) {
            webSettings.setAllowFileAccess(true);
            webSettings.setAllowContentAccess(true);
            webSettings.setAllowFileAccessFromFileURLs(true);
            webSettings.setAllowUniversalAccessFromFileURLs(true);
        }
    }

    public static void a(Context context, WebSettings webSettings, int i) {
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheMaxSize(8388608);
        webSettings.setCacheMode(i);
        webSettings.setAppCachePath(context.getDir("cache", 0).getPath());
        webSettings.setDatabaseEnabled(true);
        webSettings.setDatabasePath(context.getDir("storage", 0).getPath());
    }

    public static float e(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.density;
    }

    public static void m() {
        int i = 0;
        String[] strArr = new String[]{com.qq.reader.common.c.a.bw, com.qq.reader.common.c.a.bx, com.qq.reader.common.c.a.by, com.qq.reader.common.c.a.bz, com.qq.reader.common.c.a.bA, com.qq.reader.common.c.a.bB, com.qq.reader.common.c.a.bD, com.qq.reader.common.c.a.bC, com.qq.reader.common.c.a.bF, com.qq.reader.common.c.a.bG, com.qq.reader.common.c.a.bH, com.qq.reader.common.c.a.aa, com.qq.reader.common.c.a.ab, com.qq.reader.common.c.a.ad, com.qq.reader.common.c.a.ac};
        String[] strArr2 = new String[]{com.qq.reader.common.c.a.bd, com.qq.reader.common.c.a.be, com.qq.reader.common.c.a.bK, com.qq.reader.common.c.a.bh, com.qq.reader.common.c.a.bi, com.qq.reader.common.c.a.bm, com.qq.reader.common.c.a.bo, com.qq.reader.common.c.a.bp, com.qq.reader.common.c.a.br, com.qq.reader.common.c.a.bs, com.qq.reader.common.c.a.bu, com.qq.reader.common.c.a.ae, com.qq.reader.common.c.a.ah, com.qq.reader.common.c.a.aj, com.qq.reader.common.c.a.ai};
        if (strArr.length != strArr2.length) {
            com.qq.reader.common.monitor.debug.c.e("cloud", "unAccountFiles.length != newAccountFiles.length");
            return;
        }
        File file = new File(com.qq.reader.common.c.a.p);
        if (!file.exists()) {
            file.mkdirs();
        }
        String[] strArr3 = new String[]{com.qq.reader.common.c.a.aa, com.qq.reader.common.c.a.ab, com.qq.reader.common.c.a.ad, com.qq.reader.common.c.a.ac};
        String[] strArr4 = new String[]{com.qq.reader.common.c.a.ae, com.qq.reader.common.c.a.ah, com.qq.reader.common.c.a.aj, com.qq.reader.common.c.a.ai};
        for (int i2 = 0; i2 < strArr3.length; i2++) {
            File file2 = new File(strArr3[i2]);
            File file3 = new File(strArr4[i2]);
            if (file2.exists() && !file3.exists()) {
                file3.mkdirs();
            }
        }
        while (i < strArr.length) {
            if (new File(strArr[i]).exists()) {
                String str;
                com.qq.reader.common.monitor.debug.c.e("cloud", "rename : " + strArr[i] + " ---- " + strArr2[i]);
                String str2 = "cloud";
                if (e(strArr[i], strArr2[i])) {
                    str = "OK";
                } else {
                    str = "ERROR";
                }
                com.qq.reader.common.monitor.debug.c.e(str2, str);
            }
            i++;
        }
    }

    public static boolean e(String str, String str2) {
        File file = new File(str);
        File file2 = new File(str2);
        if (!file.exists()) {
            return false;
        }
        if (!file2.getParentFile().exists()) {
            file2.mkdirs();
        }
        boolean renameTo = file.renameTo(file2);
        com.qq.reader.common.monitor.debug.c.a("ACCOUNT", "rename file " + file.getAbsolutePath() + " ---> " + file2.getAbsolutePath() + "   result = " + renameTo);
        return renameTo;
    }

    public static void a(Context context, String str, String str2) throws IOException {
        InputStream open;
        IOException e;
        Object obj;
        Throwable th;
        OutputStream outputStream = null;
        int i = 0;
        OutputStream fileOutputStream;
        if (am.a() && a()) {
            try {
                InputStream inputStream;
                OutputStream outputStream2;
                String str3;
                File file;
                byte[] bArr;
                int read;
                String[] list = context.getResources().getAssets().list(str);
                File file2 = new File(str2);
                if (file2.exists() || !file2.mkdirs()) {
                    while (i < list.length) {
                        inputStream = null;
                        outputStream2 = null;
                        try {
                            str3 = list[i];
                            if (str3.contains(".")) {
                                file = new File(file2, str3);
                                if (file.exists()) {
                                    file.delete();
                                }
                                if (str.length() != 0) {
                                    open = context.getAssets().open(str + "/" + str3);
                                } else {
                                    open = context.getAssets().open(str3);
                                }
                                try {
                                    fileOutputStream = new FileOutputStream(file);
                                    try {
                                        bArr = new byte[4096];
                                        while (true) {
                                            read = open.read(bArr);
                                            if (read > 0) {
                                                break;
                                            }
                                            fileOutputStream.write(bArr, 0, read);
                                        }
                                        if (open != null) {
                                            try {
                                                open.close();
                                            } catch (IOException e2) {
                                                e2.printStackTrace();
                                            }
                                        }
                                        if (fileOutputStream == null) {
                                            fileOutputStream.close();
                                        }
                                    } catch (IOException e3) {
                                        e = e3;
                                        obj = open;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        outputStream = fileOutputStream;
                                    }
                                } catch (IOException e4) {
                                    e = e4;
                                    fileOutputStream = null;
                                    obj = open;
                                } catch (Throwable th3) {
                                    th = th3;
                                }
                            } else {
                                if (str.length() == 0) {
                                    a(context, str3, str2 + File.separator + str3 + File.separator);
                                } else {
                                    a(context, str + File.separator + str3, str2 + File.separator + str3 + File.separator);
                                }
                                if (null != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e22) {
                                        e22.printStackTrace();
                                    }
                                }
                                if (null == null) {
                                    outputStream2.close();
                                }
                            }
                            i++;
                        } catch (IOException e5) {
                            e = e5;
                            fileOutputStream = null;
                        } catch (Throwable th4) {
                            th = th4;
                            open = null;
                        }
                    }
                    return;
                }
                while (i < list.length) {
                    inputStream = null;
                    outputStream2 = null;
                    str3 = list[i];
                    if (str3.contains(".")) {
                        file = new File(file2, str3);
                        if (file.exists()) {
                            file.delete();
                        }
                        if (str.length() != 0) {
                            open = context.getAssets().open(str3);
                        } else {
                            open = context.getAssets().open(str + "/" + str3);
                        }
                        fileOutputStream = new FileOutputStream(file);
                        bArr = new byte[4096];
                        while (true) {
                            read = open.read(bArr);
                            if (read > 0) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        if (open != null) {
                            open.close();
                        }
                        if (fileOutputStream == null) {
                            fileOutputStream.close();
                        }
                    } else {
                        if (str.length() == 0) {
                            a(context, str + File.separator + str3, str2 + File.separator + str3 + File.separator);
                        } else {
                            a(context, str3, str2 + File.separator + str3 + File.separator);
                        }
                        if (null != null) {
                            inputStream.close();
                        }
                        if (null == null) {
                            outputStream2.close();
                        }
                    }
                    i++;
                }
                return;
                if (i < list.length) {
                    inputStream = null;
                    outputStream2 = null;
                    str3 = list[i];
                    if (str3.contains(".")) {
                        if (str.length() == 0) {
                            a(context, str3, str2 + File.separator + str3 + File.separator);
                        } else {
                            a(context, str + File.separator + str3, str2 + File.separator + str3 + File.separator);
                        }
                        if (null != null) {
                            inputStream.close();
                        }
                        if (null == null) {
                            outputStream2.close();
                        }
                    } else {
                        file = new File(file2, str3);
                        if (file.exists()) {
                            file.delete();
                        }
                        if (str.length() != 0) {
                            open = context.getAssets().open(str + "/" + str3);
                        } else {
                            open = context.getAssets().open(str3);
                        }
                        fileOutputStream = new FileOutputStream(file);
                        bArr = new byte[4096];
                        while (true) {
                            read = open.read(bArr);
                            if (read > 0) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        if (open != null) {
                            open.close();
                        }
                        if (fileOutputStream == null) {
                            fileOutputStream.close();
                        }
                    }
                    i++;
                } else {
                    return;
                }
            } catch (IOException e6) {
                return;
            }
        }
        return;
        try {
            throw e;
        } catch (Throwable th5) {
            th = th5;
            open = outputStream;
            outputStream = fileOutputStream;
        }
        if (open != null) {
            try {
                open.close();
            } catch (IOException e222) {
                e222.printStackTrace();
                throw th;
            }
        }
        if (outputStream != null) {
            outputStream.close();
        }
        throw th;
    }

    public static void f(String str, String str2) throws ZipException, IOException {
        ZipFile zipFile = new ZipFile(new File(str));
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        Enumeration entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            String name = zipEntry.getName();
            InputStream inputStream = zipFile.getInputStream(zipEntry);
            name = (str2 + name).replaceAll("\\*", "/");
            File file2 = new File(name.substring(0, name.lastIndexOf(47)));
            if (!file2.exists()) {
                file2.mkdirs();
            }
            if (!new File(name).isDirectory()) {
                OutputStream fileOutputStream = new FileOutputStream(name);
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                inputStream.close();
                fileOutputStream.close();
            }
        }
    }

    public static Bitmap e(Context context, int i) {
        Resources resources = context.getResources();
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        int i4 = context.getResources().getDisplayMetrics().widthPixels;
        if (i2 <= i4) {
            i4 = i2;
        }
        i2 = context.getResources().getDisplayMetrics().heightPixels;
        if (i3 <= i2) {
            i2 = i3;
        }
        options.inSampleSize = a(options, i4, i2);
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        return BitmapFactory.decodeResource(resources, i, a(options));
    }

    public static Bitmap a(Context context, int i, int i2, int i3) {
        Resources resources = context.getResources();
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        options.inSampleSize = a(options, i2, i3);
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        return BitmapFactory.decodeResource(resources, i, a(options));
    }

    public static int a(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        if (i3 <= i2 && i4 <= i) {
            return 1;
        }
        int ceil = (int) Math.ceil((double) (((float) i3) / ((float) i2)));
        i3 = (int) Math.ceil((double) (((float) i4) / ((float) i)));
        if (ceil < i3) {
            return ceil;
        }
        return i3;
    }

    public static Options a(Options options) {
        long maxMemory = Runtime.getRuntime().maxMemory();
        if (maxMemory > 0) {
            maxMemory = (long) (((double) (maxMemory - (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()))) * 0.7d);
            while (maxMemory < b(options)) {
                options.inSampleSize++;
            }
        }
        return options;
    }

    public static long b(Options options) {
        long j = 0;
        if (options.inSampleSize <= 0) {
            return 0;
        }
        float f;
        if (options.inPreferredConfig == Config.ALPHA_8) {
            j = 1;
        } else if (options.inPreferredConfig == Config.RGB_565) {
            j = 2;
        } else if (options.inPreferredConfig == Config.ARGB_4444) {
            j = 4;
        } else if (options.inPreferredConfig == Config.ARGB_8888) {
            j = 8;
        }
        if (options.inTargetDensity == 0 || options.inDensity == 0) {
            f = 1.0f;
        } else {
            f = ((float) options.inTargetDensity) / ((float) options.inDensity);
        }
        return j * ((long) (((int) (((float) ((int) ((f * ((float) options.outWidth)) + 0.5f))) * (1.0f / ((float) options.inSampleSize)))) * ((int) (((float) ((int) ((((float) options.outHeight) * f) + 0.5f))) * (1.0f / ((float) options.inSampleSize))))));
    }

    public static Bitmap a(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = ((float) com.qq.reader.common.c.a.bU) / ((float) width);
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static String r(String str) {
        try {
            return a(MessageDigest.getInstance("MD5").digest(str.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(byte[] bArr) {
        String str = "";
        int i = 0;
        while (i < bArr.length) {
            String toHexString = Integer.toHexString(bArr[i] & 255);
            if (toHexString.length() == 1) {
                str = str + "0";
            }
            i++;
            str = str + toHexString;
        }
        return str;
    }

    public static boolean o(Context context) {
        if (Calendar.getInstance().getTimeInMillis() > com.qq.reader.appconfig.a.d.w(context)) {
            return true;
        }
        return false;
    }

    public static boolean b(int i) {
        if (i <= 1 || i > 6) {
            return true;
        }
        return false;
    }

    public static boolean p(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
        int i = 0;
        while (i < allNetworkInfo.length && State.CONNECTED != allNetworkInfo[i].getState()) {
            i++;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || activeNetworkInfo.getType() != 0) {
            return false;
        }
        if (activeNetworkInfo.getSubtype() == 2 || activeNetworkInfo.getSubtype() == 4 || activeNetworkInfo.getSubtype() == 1) {
            return true;
        }
        return false;
    }

    public static String i(long j) {
        if (j <= 0) {
            return "未读";
        }
        long currentTimeMillis = (System.currentTimeMillis() / 1000) - (j / 1000);
        StringBuffer stringBuffer = new StringBuffer();
        if (currentTimeMillis > TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC) {
            stringBuffer.append(currentTimeMillis / TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC);
            stringBuffer.append("天前");
        } else if (currentTimeMillis > 3600) {
            stringBuffer.append(currentTimeMillis / 3600);
            stringBuffer.append("小时前");
        } else if (currentTimeMillis > 60) {
            stringBuffer.append(currentTimeMillis / 60);
            stringBuffer.append("分钟前");
        } else {
            stringBuffer.append("刚刚");
        }
        return stringBuffer.toString();
    }

    public static String j(long j) {
        String str = "";
        try {
            long currentTimeMillis = (System.currentTimeMillis() / 1000) - j;
            long j2 = 60 * 60;
            long j3 = 24 * j2;
            long j4 = 30 * j3;
            long j5 = 12 * j4;
            if (currentTimeMillis <= 60) {
                return "刚刚";
            }
            if (currentTimeMillis < j2) {
                return ((int) (currentTimeMillis / 60)) + "分钟前";
            } else if (currentTimeMillis < j3) {
                return ((int) (currentTimeMillis / j2)) + "小时前";
            } else if (currentTimeMillis < j4) {
                return ((int) (currentTimeMillis / j3)) + "天前";
            } else if (currentTimeMillis < j5) {
                return ((int) (currentTimeMillis / j4)) + "个月前";
            } else {
                return ((int) (currentTimeMillis / j5)) + "年前";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String b(Mark mark) {
        int type = mark.getType();
        long lastUpdateTime = mark.getLastUpdateTime();
        mark.getReadTime();
        long operateTime = mark.getOperateTime();
        boolean hasNewContent = mark.hasNewContent();
        String str = "";
        if (type == 3) {
            return "下载中";
        }
        if (!hasNewContent) {
            return j(operateTime / 1000);
        }
        if (lastUpdateTime <= 0) {
            return "连载中";
        }
        return j(operateTime / 1000);
    }

    public static boolean q(Context context) {
        if (p == -1) {
            p = com.qq.reader.appconfig.a.d.aG(context);
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (p != -1 && currentTimeMillis - p <= TaskManager.IDLE_PROTECT_TIME) {
            return false;
        }
        com.qq.reader.appconfig.a.d.f(context, currentTimeMillis);
        p = currentTimeMillis;
        return true;
    }

    public static boolean r(Context context) {
        if (!com.qq.reader.common.login.c.b()) {
            return false;
        }
        long aH = com.qq.reader.appconfig.a.d.aH(context);
        long currentTimeMillis = System.currentTimeMillis();
        if (aH != -1 && currentTimeMillis - aH <= TaskManager.IDLE_PROTECT_TIME) {
            return false;
        }
        com.qq.reader.appconfig.a.d.g(context, currentTimeMillis);
        return true;
    }

    public static boolean s(Context context) {
        long aI = com.qq.reader.appconfig.a.d.aI(context);
        long currentTimeMillis = System.currentTimeMillis();
        if (aI != -1 && currentTimeMillis - aI <= 600000) {
            return false;
        }
        com.qq.reader.appconfig.a.d.h(context, currentTimeMillis);
        return true;
    }

    public static boolean t(Context context) {
        int aL = com.qq.reader.appconfig.a.d.aL(context);
        if (aL != 0 && (aL == Calendar.getInstance().get(6) || Calendar.getInstance().get(11) <= 6)) {
            return false;
        }
        com.qq.reader.appconfig.a.d.B(context, Calendar.getInstance().get(6));
        return true;
    }

    public static String u(Context context) {
        String str = "";
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            return str;
        }
    }

    public static int v(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return i;
        }
    }

    public static boolean s(String str) {
        if (str == null || str.length() == 0 || str.equals("") || str.equals("0")) {
            return true;
        }
        return false;
    }

    public static SpannableString a(CharSequence charSequence, int[] iArr, ArrayList<Drawable> arrayList) {
        int i = 0;
        if (charSequence == null || iArr == null || arrayList == null || iArr.length != arrayList.size()) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(charSequence);
        for (int i2 = 0; i2 < iArr.length; i2++) {
            stringBuffer.insert(iArr[i2] + i2, '!');
        }
        SpannableString spannableString = new SpannableString(stringBuffer.toString());
        while (i < arrayList.size()) {
            spannableString.setSpan(new com.qq.reader.common.emotion.b.b((Drawable) arrayList.get(i)), iArr[i] + i, (iArr[i] + i) + 1, 33);
            i++;
        }
        return spannableString;
    }

    public static void g(String str, String str2) {
        File file = new File(str2);
        if (ab.a(file.getParentFile())) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String str3 = str + "\n";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsolutePath(), true);
            fileOutputStream.write(str3.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(android.graphics.Bitmap r5, float r6) {
        /*
        r0 = 100;
        r1 = 1;
        r2 = new java.io.ByteArrayOutputStream;
        r2.<init>();
        r3 = android.graphics.Bitmap.CompressFormat.JPEG;	 Catch:{ Exception -> 0x0038 }
        r4 = 100;
        r5.compress(r3, r4, r2);	 Catch:{ Exception -> 0x0038 }
    L_0x000f:
        r3 = r2.toByteArray();	 Catch:{ Exception -> 0x0038 }
        r3 = r3.length;	 Catch:{ Exception -> 0x0038 }
        r3 = r3 / 1024;
        r3 = (float) r3;	 Catch:{ Exception -> 0x0038 }
        r3 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1));
        if (r3 < 0) goto L_0x0025;
    L_0x001b:
        r2.reset();	 Catch:{ Exception -> 0x0038 }
        r3 = android.graphics.Bitmap.CompressFormat.JPEG;	 Catch:{ Exception -> 0x0038 }
        r5.compress(r3, r0, r2);	 Catch:{ Exception -> 0x0038 }
        if (r0 != r1) goto L_0x002d;
    L_0x0025:
        r0 = r2.toByteArray();	 Catch:{ Exception -> 0x0038 }
        r2.close();	 Catch:{ IOException -> 0x0033 }
    L_0x002c:
        return r0;
    L_0x002d:
        r0 = r0 + -10;
        if (r0 > 0) goto L_0x000f;
    L_0x0031:
        r0 = r1;
        goto L_0x000f;
    L_0x0033:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x002c;
    L_0x0038:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0046 }
        r0 = 0;
        r2.close();	 Catch:{ IOException -> 0x0041 }
        goto L_0x002c;
    L_0x0041:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x002c;
    L_0x0046:
        r0 = move-exception;
        r2.close();	 Catch:{ IOException -> 0x004b }
    L_0x004a:
        throw r0;
    L_0x004b:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x004a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.utils.ao.a(android.graphics.Bitmap, float):byte[]");
    }

    public static Bitmap a(String str, int i, int i2, boolean z) {
        Options options = new Options();
        try {
            int i3;
            int i4;
            options.inJustDecodeBounds = true;
            Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
            if (decodeFile != null) {
                decodeFile.recycle();
            }
            double d = (((double) options.outHeight) * 1.0d) / ((double) i);
            double d2 = (((double) options.outWidth) * 1.0d) / ((double) i2);
            double d3 = z ? d > d2 ? d2 : d : d < d2 ? d2 : d;
            options.inSampleSize = (int) d3;
            if (options.inSampleSize <= 1) {
                options.inSampleSize = 1;
            }
            while ((options.outHeight * options.outWidth) / options.inSampleSize > 2764800) {
                options.inSampleSize++;
            }
            if (z) {
                if (d > d2) {
                    i3 = (int) (((((double) i2) * 1.0d) * ((double) options.outHeight)) / ((double) options.outWidth));
                    i4 = i2;
                } else {
                    i4 = (int) (((((double) i) * 1.0d) * ((double) options.outWidth)) / ((double) options.outHeight));
                    i3 = i;
                }
            } else if (d < d2) {
                i3 = (int) (((((double) i2) * 1.0d) * ((double) options.outHeight)) / ((double) options.outWidth));
                i4 = i2;
            } else {
                i4 = (int) (((((double) i) * 1.0d) * ((double) options.outWidth)) / ((double) options.outHeight));
                i3 = i;
            }
            options.inJustDecodeBounds = false;
            f.b("Utility", "bitmap required size=" + i4 + "x" + i3 + ", orig=" + options.outWidth + "x" + options.outHeight + ", sample=" + options.inSampleSize);
            Bitmap decodeFile2 = BitmapFactory.decodeFile(str, options);
            if (decodeFile2 == null) {
                f.a("Utility", "bitmap decode failed");
                return null;
            }
            f.b("Utility", "bitmap decoded size=" + decodeFile2.getWidth() + "x" + decodeFile2.getHeight());
            decodeFile = Bitmap.createScaledBitmap(decodeFile2, i4, i3, true);
            if (!(decodeFile == null || decodeFile2 == null || (decodeFile2.getWidth() == decodeFile.getWidth() && decodeFile2.getHeight() == decodeFile.getHeight()))) {
                decodeFile2.recycle();
                decodeFile2 = decodeFile;
            }
            if (z) {
                decodeFile = Bitmap.createBitmap(decodeFile2, (decodeFile2.getWidth() - i2) >> 1, (decodeFile2.getHeight() - i) >> 1, i2, i);
                if (decodeFile == null) {
                    return decodeFile2;
                }
                decodeFile2.recycle();
                f.b("Utility", "bitmap croped size=" + decodeFile.getWidth() + "x" + decodeFile.getHeight());
            } else {
                decodeFile = decodeFile2;
            }
            return decodeFile;
        } catch (OutOfMemoryError e) {
            f.a("Utility", "decode bitmap failed: " + e.getMessage());
            return null;
        }
    }

    public static int a(float f) {
        return (int) TypedValue.applyDimension(1, f, ReaderApplication.getApplicationImp().getResources().getDisplayMetrics());
    }

    public static byte[] b(byte[] bArr) throws IOException {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(bArr);
        gZIPOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static int a(Paint paint, float f, String str) {
        if (paint.measureText(str) < f) {
            return str.length();
        }
        int length = str.length();
        while (length > 0) {
            length--;
            if (paint.measureText(str.subSequence(0, length).toString()) < f) {
                return length;
            }
        }
        return 0;
    }

    public static float a(Paint paint, String str) {
        return paint.measureText(str);
    }

    public static boolean w(Context context) {
        int i;
        try {
            i = System.getInt(context.getContentResolver(), "screen_brightness_mode");
        } catch (Exception e) {
            i = 0;
        }
        if (i == 1) {
            return true;
        }
        return false;
    }

    public static void f(Context context, int i) {
        try {
            System.putInt(context.getContentResolver(), "screen_brightness_mode", i);
        } catch (Exception e) {
        }
    }

    public static void a(Context context, long j) {
        Intent intent = new Intent();
        intent.putExtra("KEY_JUMP_PAGENAME", "DetailPage");
        intent.putExtra("URL_BUILD_PERE_BOOK_ID", j);
        intent.setClass(context, NativeBookStoreConfigDetailActivity.class);
        context.startActivity(intent);
    }

    public static void a(Context context, long j, Bundle bundle) {
        Intent intent = new Intent();
        if (bundle == null) {
            intent.putExtra("KEY_JUMP_PAGENAME", "DetailPage");
            intent.putExtra("URL_BUILD_PERE_BOOK_ID", j);
        } else if (bundle.getString("LOCAL_STORE_IN_TITLE").equals("精华书评")) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("KEY_JUMP_PAGENAME", "DetailPage");
            bundle2.putLong("URL_BUILD_PERE_BOOK_ID", j);
            bundle2.putString(s.ALG, bundle.getString(s.ALG));
            bundle2.putString("itemid", bundle.getString("itemid"));
            intent.putExtras(bundle2);
        } else {
            bundle.putString("KEY_JUMP_PAGENAME", "DetailPage");
            bundle.putLong("URL_BUILD_PERE_BOOK_ID", j);
            intent.putExtras(bundle);
        }
        intent.setClass(context, NativeBookStoreConfigDetailActivity.class);
        context.startActivity(intent);
    }

    public static StateListDrawable a(Context context, int i, int i2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable drawable = context.getResources().getDrawable(i);
        Drawable drawable2 = context.getResources().getDrawable(i2);
        stateListDrawable.addState(new int[]{16842919}, drawable2);
        stateListDrawable.addState(new int[]{16842913}, drawable2);
        stateListDrawable.addState(new int[]{16842910}, drawable);
        stateListDrawable.addState(new int[0], drawable);
        return stateListDrawable;
    }

    public static Bitmap b(Bitmap bitmap) {
        int max = Math.max(bitmap.getWidth(), bitmap.getHeight());
        try {
            Bitmap createBitmap = Bitmap.createBitmap(max, max, Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, max, max);
            paint.setAntiAlias(true);
            paint.setColor(-1);
            canvas.drawCircle((float) (max / 2), (float) (max / 2), (float) (max / 2), paint);
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            return createBitmap;
        } catch (Error e) {
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    public static int x(Context context) {
        int i = 0;
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            i = resources.getDimensionPixelSize(identifier);
        }
        if (i <= 0) {
            return a(25.0f);
        }
        return i;
    }

    public static int f(Activity activity) {
        try {
            Resources resources = activity.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
            f.e("dbw", "Navi height:" + dimensionPixelSize);
            return dimensionPixelSize;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public static void a(Context context, OnlineTag onlineTag) {
        int i = 0;
        try {
            String[] split = com.qq.reader.appconfig.a.d.cl(context).split("#");
            StringBuilder stringBuilder = new StringBuilder();
            Arrays.sort(split);
            int i2 = 0;
            while (i2 < split.length) {
                if (split[i2].contains(VoiceWakeuperAidl.PARAMS_SEPARATE + onlineTag.k() + VoiceWakeuperAidl.PARAMS_SEPARATE)) {
                    break;
                }
                i2++;
            }
            i2 = -1;
            if (i2 == -1) {
                stringBuilder.append(onlineTag.m()).append(VoiceWakeuperAidl.PARAMS_SEPARATE).append(onlineTag.k()).append(VoiceWakeuperAidl.PARAMS_SEPARATE).append(onlineTag.g()).append(VoiceWakeuperAidl.PARAMS_SEPARATE).append(onlineTag.i()).append("#");
                Arrays.sort(split);
                switch (split.length) {
                    case 1:
                        stringBuilder.append(split[0]);
                        break;
                    case 2:
                        stringBuilder.append(split[0]).append("#");
                        stringBuilder.append(split[1]);
                        break;
                    case 3:
                        stringBuilder.append(split[1]).append("#");
                        stringBuilder.append(split[2]);
                        break;
                }
            }
            split[i2] = onlineTag.m() + VoiceWakeuperAidl.PARAMS_SEPARATE + onlineTag.k() + VoiceWakeuperAidl.PARAMS_SEPARATE + onlineTag.g() + VoiceWakeuperAidl.PARAMS_SEPARATE + onlineTag.i();
            i2 = split.length;
            while (i < i2) {
                stringBuilder.append(split[i]).append("#");
                i++;
            }
            String stringBuilder2 = stringBuilder.toString();
            if (stringBuilder2.endsWith("#")) {
                stringBuilder2 = stringBuilder2.substring(0, stringBuilder2.length() - 1);
            }
            com.qq.reader.appconfig.a.d.P(context, stringBuilder2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(OnlineTag onlineTag) {
        if (onlineTag != null) {
            try {
                String[] split = com.qq.reader.appconfig.a.d.cl(ReaderApplication.getApplicationImp()).split("#");
                Arrays.sort(split);
                String[] strArr = null;
                for (int length = split.length; length > 0; length--) {
                    if (split[length - 1].contains(VoiceWakeuperAidl.PARAMS_SEPARATE + onlineTag.k() + VoiceWakeuperAidl.PARAMS_SEPARATE)) {
                        strArr = split[length - 1].split(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        break;
                    }
                }
                if (strArr != null && strArr.length > 3 && (onlineTag.m() + "").compareTo(strArr[0]) < 0) {
                    onlineTag.c(Integer.valueOf(strArr[2]).intValue());
                    onlineTag.a((long) Integer.valueOf(strArr[3]).intValue());
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    public static void a(View view, float f) {
        if (view != null) {
            view.setAlpha(f);
        }
    }

    public static com.qq.reader.common.login.a a(int i, ReaderBaseActivity readerBaseActivity, final b bVar) {
        if (com.qq.reader.common.login.c.b()) {
            a(String.valueOf(i), (Activity) readerBaseActivity, bVar);
            return null;
        }
        com.qq.reader.common.login.a anonymousClass5 = new com.qq.reader.common.login.a() {
            public void a(int i) {
                switch (i) {
                    case 1:
                        g.a().a(new ProfileNetTask(new com.qq.reader.common.readertask.ordinal.c(this) {
                            final /* synthetic */ AnonymousClass5 a;

                            {
                                this.a = r1;
                            }

                            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                                try {
                                    JSONObject jSONObject = new JSONObject(str);
                                    if (jSONObject != null) {
                                        com.qq.reader.common.login.b.a.a(com.qq.reader.common.login.c.c(), jSONObject);
                                        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                                            final /* synthetic */ AnonymousClass1 a;

                                            {
                                                this.a = r1;
                                            }

                                            public void run() {
                                                if (com.qq.reader.common.login.c.c() != null && bVar != null) {
                                                    bVar.a();
                                                }
                                            }
                                        });
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                            }
                        }));
                        return;
                    default:
                        return;
                }
            }
        };
        readerBaseActivity.startLogin();
        return anonymousClass5;
    }

    private static void a(String str, Activity activity, final b bVar) {
        String str2 = "buyPack.html?pid=" + str;
        if (q == null) {
            q = new h(activity);
            q.a(activity);
            q.a(new com.qq.reader.view.web.h.a() {
                public void a(String str, boolean z) {
                    try {
                        Log.d("readerpage", "OnDialogClose " + str);
                        ao.q.dismiss();
                        ao.q = null;
                        if (z && bVar != null) {
                            bVar.a();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        q.a(str2);
        q.f_();
    }

    public static Bitmap a(Bitmap bitmap, int i) {
        return a(bitmap, i, true);
    }

    public static Bitmap a(Bitmap bitmap, int i, boolean z) {
        try {
            int height = bitmap.getHeight() / 5;
            int width = bitmap.getWidth();
            int height2 = (bitmap.getHeight() * 3) / 5;
            if (!z) {
                height = bitmap.getHeight() > 200 ? 40 : bitmap.getHeight() / 5;
                width = bitmap.getWidth() > 200 ? 200 : bitmap.getWidth();
                if (bitmap.getHeight() > 200) {
                    height2 = 120;
                } else {
                    height2 = (bitmap.getHeight() * 3) / 5;
                }
            }
            Bitmap b = b(Bitmap.createBitmap(bitmap, 0, height, width, height2), 25, true);
            if (i == -1) {
                return b;
            }
            Canvas canvas = new Canvas(b);
            Paint paint = new Paint();
            paint.setColor(i);
            canvas.drawRect(new Rect(0, 0, b.getWidth(), b.getHeight()), paint);
            return b;
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("Err", e.getMessage());
            return bitmap;
        } catch (OutOfMemoryError e2) {
            com.qq.reader.common.monitor.debug.c.e("Err", e2.getMessage());
            return bitmap;
        }
    }

    public static Bitmap b(Bitmap bitmap, int i, boolean z) {
        Bitmap bitmap2;
        if (z) {
            bitmap2 = bitmap;
        } else {
            bitmap2 = bitmap.copy(bitmap.getConfig(), true);
        }
        if (i < 1) {
            return null;
        }
        int i2;
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        int[] iArr = new int[(width * height)];
        bitmap2.getPixels(iArr, 0, width, 0, 0, width, height);
        int i3 = width - 1;
        int i4 = height - 1;
        int i5 = width * height;
        int i6 = (i + i) + 1;
        int[] iArr2 = new int[i5];
        int[] iArr3 = new int[i5];
        int[] iArr4 = new int[i5];
        int[] iArr5 = new int[Math.max(width, height)];
        i5 = (i6 + 1) >> 1;
        int i7 = i5 * i5;
        int[] iArr6 = new int[(i7 * 256)];
        for (i5 = 0; i5 < i7 * 256; i5++) {
            iArr6[i5] = i5 / i7;
        }
        int[][] iArr7 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{i6, 3});
        int i8 = i + 1;
        int i9 = 0;
        int i10 = 0;
        for (i2 = 0; i2 < height; i2++) {
            int i11;
            int i12;
            i7 = 0;
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            int i16 = 0;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            for (i11 = -i; i11 <= i; i11++) {
                i12 = iArr[Math.min(i3, Math.max(i11, 0)) + i10];
                int[] iArr8 = iArr7[i11 + i];
                iArr8[0] = (16711680 & i12) >> 16;
                iArr8[1] = (65280 & i12) >> 8;
                iArr8[2] = i12 & 255;
                i12 = i8 - Math.abs(i11);
                i19 += iArr8[0] * i12;
                i18 += iArr8[1] * i12;
                i17 += i12 * iArr8[2];
                if (i11 > 0) {
                    i13 += iArr8[0];
                    i20 += iArr8[1];
                    i7 += iArr8[2];
                } else {
                    i16 += iArr8[0];
                    i15 += iArr8[1];
                    i14 += iArr8[2];
                }
            }
            i12 = i19;
            i19 = i18;
            i18 = i17;
            i11 = i10;
            i10 = i;
            for (i17 = 0; i17 < width; i17++) {
                iArr2[i11] = iArr6[i12];
                iArr3[i11] = iArr6[i19];
                iArr4[i11] = iArr6[i18];
                i12 -= i16;
                i19 -= i15;
                i18 -= i14;
                iArr8 = iArr7[((i10 - i) + i6) % i6];
                i16 -= iArr8[0];
                i15 -= iArr8[1];
                i14 -= iArr8[2];
                if (i2 == 0) {
                    iArr5[i17] = Math.min((i17 + i) + 1, i3);
                }
                int i21 = iArr[iArr5[i17] + i9];
                iArr8[0] = (16711680 & i21) >> 16;
                iArr8[1] = (65280 & i21) >> 8;
                iArr8[2] = i21 & 255;
                i13 += iArr8[0];
                i20 += iArr8[1];
                i7 += iArr8[2];
                i12 += i13;
                i19 += i20;
                i18 += i7;
                i10 = (i10 + 1) % i6;
                iArr8 = iArr7[i10 % i6];
                i16 += iArr8[0];
                i15 += iArr8[1];
                i14 += iArr8[2];
                i13 -= iArr8[0];
                i20 -= iArr8[1];
                i7 -= iArr8[2];
                i11++;
            }
            i9 += width;
            i10 = i11;
        }
        for (i17 = 0; i17 < width; i17++) {
            i20 = 0;
            i7 = (-i) * width;
            i14 = 0;
            i15 = 0;
            i16 = 0;
            i10 = 0;
            i12 = -i;
            i11 = 0;
            i18 = 0;
            i19 = 0;
            i13 = 0;
            while (i12 <= i) {
                i2 = Math.max(0, i7) + i17;
                int[] iArr9 = iArr7[i12 + i];
                iArr9[0] = iArr2[i2];
                iArr9[1] = iArr3[i2];
                iArr9[2] = iArr4[i2];
                int abs = i8 - Math.abs(i12);
                i9 = (iArr2[i2] * abs) + i19;
                i19 = (iArr3[i2] * abs) + i18;
                i18 = (iArr4[i2] * abs) + i11;
                if (i12 > 0) {
                    i14 += iArr9[0];
                    i13 += iArr9[1];
                    i20 += iArr9[2];
                } else {
                    i10 += iArr9[0];
                    i16 += iArr9[1];
                    i15 += iArr9[2];
                }
                if (i12 < i4) {
                    i7 += width;
                }
                i12++;
                i11 = i18;
                i18 = i19;
                i19 = i9;
            }
            i12 = i18;
            i9 = i19;
            i19 = i11;
            i11 = i17;
            i7 = i20;
            i20 = i13;
            i13 = i14;
            i14 = i15;
            i15 = i16;
            i16 = i10;
            i10 = i;
            for (i18 = 0; i18 < height; i18++) {
                iArr[i11] = (((WebView.NIGHT_MODE_COLOR & iArr[i11]) | (iArr6[i9] << 16)) | (iArr6[i12] << 8)) | iArr6[i19];
                i9 -= i16;
                i12 -= i15;
                i19 -= i14;
                int[] iArr10 = iArr7[((i10 - i) + i6) % i6];
                i16 -= iArr10[0];
                i15 -= iArr10[1];
                i14 -= iArr10[2];
                if (i17 == 0) {
                    iArr5[i18] = Math.min(i18 + i8, i4) * width;
                }
                i3 = iArr5[i18] + i17;
                iArr10[0] = iArr2[i3];
                iArr10[1] = iArr3[i3];
                iArr10[2] = iArr4[i3];
                i13 += iArr10[0];
                i20 += iArr10[1];
                i7 += iArr10[2];
                i9 += i13;
                i12 += i20;
                i19 += i7;
                i10 = (i10 + 1) % i6;
                iArr10 = iArr7[i10];
                i16 += iArr10[0];
                i15 += iArr10[1];
                i14 += iArr10[2];
                i13 -= iArr10[0];
                i20 -= iArr10[1];
                i7 -= iArr10[2];
                i11 += width;
            }
        }
        bitmap2.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitmap2;
    }

    public static String t(String str) {
        if (TextUtils.isEmpty(str)) {
            return "JSON_TYPE_ERROR";
        }
        char c = str.substring(0, 1).toCharArray()[0];
        if (c == '{') {
            return "JSON_TYPE_OBJECT";
        }
        if (c == '[') {
            return "JSON_TYPE_ARRAY";
        }
        return "JSON_TYPE_ERROR";
    }

    public static String a(CharSequence charSequence) {
        return Pattern.compile("\\[img.*?].*?\\[/img]").matcher(Pattern.compile("\\[url.*?].*?\\[/url.*?]").matcher(charSequence.toString()).replaceAll("")).replaceAll("");
    }

    public static String u(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (str.contains(".png")) {
            return str.replace(".png", "_315.png");
        }
        if (str.contains(".jpg")) {
            return str.replace(".jpg", "_315.jpg");
        }
        if (str.contains(".gif")) {
            return str.replace(".gif", "_315.gif");
        }
        return str;
    }

    public static String k(long j) {
        switch ((int) j) {
            case 1:
                return "书荒互助";
            case 2:
                return "原创空间";
            case 3:
                return "大神沙龙";
            default:
                return "";
        }
    }

    public static String l(long j) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("http://wfqqreader.3g.qq.com/cover/ad/");
        switch ((int) j) {
            case 1:
                stringBuffer.append("shqz");
                break;
            case 2:
                stringBuffer.append("ycjl");
                break;
            case 3:
                stringBuffer.append("dssl");
                break;
        }
        stringBuffer.append(".png");
        return stringBuffer.toString();
    }

    public static boolean m(long j) {
        return j <= com.qq.reader.common.c.a.R || j >= com.qq.reader.common.c.a.S;
    }

    public static String a(String str, int i, String str2) {
        if (str2 == null || str == null || str2.length() + i > str.length()) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.replace(i, str2.length() + i, str2);
        return stringBuilder.toString();
    }

    public static String h(String str, String str2) {
        int i = 0;
        if (!(str == null || str.length() == 0 || str2 == null || str2.length() == 0)) {
            boolean exists = new File(str + str2).exists();
            if (exists) {
                int lastIndexOf = str2.lastIndexOf(".");
                String substring = lastIndexOf == -1 ? str2 : str2.substring(0, lastIndexOf);
                String substring2 = lastIndexOf == -1 ? "" : str2.substring(lastIndexOf);
                while (exists) {
                    i++;
                    str2 = substring + "(" + i + ")" + substring2;
                    exists = new File(str + str2).exists();
                }
            }
        }
        return str2;
    }

    public static void c(String str, Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (!TextUtils.isEmpty(str)) {
            CharSequence charSequence = "当前版本无法使用\"" + str + "\",试试其它主题";
            android.support.v4.app.t.d y = y(context);
            y.c(charSequence);
            y.b(charSequence);
            Intent intent = new Intent();
            intent.setFlags(SigType.TLS);
            intent.setClass(context, NativeSkinManageActivity.class);
            y.a(PendingIntent.getActivity(context, 80, intent, WtloginHelper.SigType.WLOGIN_PT4Token));
            notificationManager.notify(80, y.a());
        }
    }

    public static void a(Runnable runnable) {
        new Handler(Looper.myLooper()).post(runnable);
    }

    public static long n() {
        long j = 0;
        try {
            List b = StatisticsManager.a().b(101);
            int size = b.size();
            int i = 0;
            while (i < size) {
                long optLong;
                try {
                    optLong = ((Node) b.get(i)).other.optLong("readTime", 0) + j;
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e("ReaderPageActivity", e.getMessage());
                    optLong = j;
                }
                i++;
                j = optLong;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return j;
    }

    public static void v(String str) {
        int L = com.qq.reader.appconfig.a.d.L(ReaderApplication.getApplicationImp());
        if (!TextUtils.isEmpty(str) && Constants.DEFAULT_UIN.equals(str) && L == 7) {
            com.qq.reader.appconfig.a.d.j(ReaderApplication.getApplicationImp(), 0);
        }
    }

    public static boolean o() {
        return com.qq.reader.appconfig.a.d.L(ReaderApplication.getApplicationImp()) == 7;
    }

    public static boolean a(x xVar) {
        if (xVar == null || TextUtils.isEmpty(xVar.i())) {
            return false;
        }
        return xVar.i().equals(com.qq.reader.appconfig.a.d.bS(ReaderApplication.getApplicationImp()));
    }

    public static int c(int i) {
        if (i < 0) {
            return com.qq.reader.common.c.a.dh[0];
        }
        return i < com.qq.reader.common.c.a.dh.length ? com.qq.reader.common.c.a.dh[i] : com.qq.reader.common.c.a.dh[com.qq.reader.common.c.a.dh.length - 1];
    }

    public static android.support.v4.app.t.d y(Context context) {
        android.support.v4.app.t.d dVar = new android.support.v4.app.t.d(context);
        dVar.a(System.currentTimeMillis());
        dVar.c(true);
        dVar.a(d(context.getApplicationContext(), (int) R.drawable.icon_notify_large));
        if (VERSION.SDK_INT >= 21) {
            dVar.a((int) R.drawable.icon_notify_small_5_0_plus);
        } else {
            dVar.a((int) R.drawable.icon_notify_small);
        }
        dVar.a(context.getResources().getString(R.string.app_name));
        return dVar;
    }

    public static int d(int i) {
        return new int[]{R.drawable.rank_list_item_bg1, R.drawable.rank_list_item_bg2, R.drawable.rank_list_item_bg3, R.drawable.rank_list_item_bg4, R.drawable.rank_list_item_bg5, R.drawable.rank_list_item_bg6, R.drawable.rank_list_item_bg7, R.drawable.rank_list_item_bg8, R.drawable.rank_list_item_bg9, R.drawable.rank_list_item_bg10}[i];
    }

    public static void a(View view, int i) {
        if (view != null) {
            Drawable background = view.getBackground();
            if (background instanceof GradientDrawable) {
                ((GradientDrawable) background).setColor(i);
            }
        }
    }

    public static String w(String str) {
        try {
            ah ahVar = new ah();
            Iterable<com.qq.reader.common.utils.ah.b> a = ah.a(str);
            StringBuilder stringBuilder = new StringBuilder();
            for (com.qq.reader.common.utils.ah.b bVar : a) {
                switch (bVar.f) {
                    case 0:
                        stringBuilder.append(bVar.d);
                        break;
                    default:
                        break;
                }
            }
            str = stringBuilder.toString();
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("Err", "filterCommentToNormalString");
        }
        return str;
    }

    public static int e(int i) {
        switch (i) {
            case 4:
                return R.drawable.card_platinum;
            case 5:
                return R.drawable.card_god;
            case 6:
                return R.drawable.card_star;
            case 7:
                return R.drawable.card_auther;
            default:
                return 0;
        }
    }

    public static void a(int i, ImageView imageView, boolean z) {
        imageView.setVisibility(8);
        switch (i) {
            case 0:
                if (z) {
                    imageView.setImageResource(R.drawable.bookvip_none);
                    imageView.setVisibility(0);
                    return;
                }
                return;
            case 1:
                imageView.setVisibility(0);
                imageView.setImageResource(R.drawable.bookvip_month);
                return;
            case 2:
                imageView.setVisibility(0);
                imageView.setImageResource(R.drawable.bookvip_year);
                return;
            default:
                return;
        }
    }

    public static String a(String str, String str2, String str3) {
        String str4 = "";
        if (str.contains("/")) {
            if (str.contains("?") && !str.contains("=")) {
                str4 = "";
            } else if (str.contains("?") && str.contains("=")) {
                str4 = "&";
            } else {
                str4 = "?";
            }
        } else if (str.contains("%3F") && !str.contains("%3D")) {
            str4 = "";
        } else if (str.contains("%3F") && str.contains("%3D")) {
            str4 = "%26";
        } else {
            str4 = "%3F";
        }
        return str + str4 + str2 + "=" + str3;
    }

    public static String x(String str) {
        BufferedReader bufferedReader;
        Exception e;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), 1024);
            try {
                String readLine = bufferedReader.readLine();
                bufferedReader.close();
                if (bufferedReader == null) {
                    return readLine;
                }
                try {
                    bufferedReader.close();
                    return readLine;
                } catch (IOException e2) {
                    com.qq.reader.common.monitor.debug.c.e("getSystemPropertyError", e2.getMessage());
                    return readLine;
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    com.qq.reader.common.monitor.debug.c.e("getSystemPropertyError", e.getMessage());
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                            com.qq.reader.common.monitor.debug.c.e("getSystemPropertyError", e4.getMessage());
                        }
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e22) {
                            com.qq.reader.common.monitor.debug.c.e("getSystemPropertyError", e22.getMessage());
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e5) {
            e = e5;
            bufferedReader = null;
            com.qq.reader.common.monitor.debug.c.e("getSystemPropertyError", e.getMessage());
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            throw th;
        }
    }

    public static boolean a(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (NameNotFoundException e) {
            com.qq.reader.common.monitor.debug.c.e("isAppExist", e.getMessage());
            return false;
        }
    }

    public static boolean a(Activity activity, String str, String str2) {
        if (!a(activity.getApplicationContext(), "com.tencent.mobileqq")) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("mqqwpa://im/chat?chat_type=crm&uin=" + str));
        intent.putExtra("android.intent.extra.TEXT", str2);
        activity.startActivity(intent);
        return true;
    }

    public static ISearchParamCollection a(ISearchParamCollection iSearchParamCollection) {
        if (iSearchParamCollection == null) {
            return new CommonBookSearchParamCollection();
        }
        return iSearchParamCollection;
    }

    public static String i(String str, String str2) {
        try {
            for (String str3 : new URL(str).getQuery().split("&")) {
                int indexOf = str3.indexOf("=");
                if (str2 != null && str3 != null && str2.equalsIgnoreCase(URLDecoder.decode(str3.substring(0, indexOf)))) {
                    return URLDecoder.decode(str3.substring(indexOf + 1), "UTF-8");
                }
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
        }
        return "";
    }

    public static String a(String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split(",");
            if (split != null && i < split.length) {
                return split[i];
            }
        }
        return "";
    }

    public static int y(String str) {
        int i = 0;
        if (!TextUtils.isEmpty(str)) {
            try {
                if (!str.startsWith("#")) {
                    str = "#" + str;
                }
                i = Color.parseColor(str);
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
            }
        }
        return i;
    }

    public static Bundle a(Map<String, String> map) {
        Bundle bundle = new Bundle();
        if (map != null) {
            String str = (String) map.get("bids");
            String str2 = (String) map.get("bidsincid");
            String str3 = (String) map.get("cidincate");
            if (!TextUtils.isEmpty(str)) {
                bundle.putString("bids", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                bundle.putString("bidsincid", str2);
            }
            if (!TextUtils.isEmpty(str3)) {
                bundle.putString("cidincate", str3);
            }
        }
        return bundle;
    }

    public static String a(Uri uri) {
        Cursor query = ReaderApplication.getApplicationImp().getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
        if (query == null) {
            return null;
        }
        int columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
        query.moveToFirst();
        String string = query.getString(columnIndexOrThrow);
        query.close();
        return string;
    }

    public static String p() {
        if (r == null) {
            if (com.qq.reader.common.utils.EmulatorCheck.b.a()) {
                r = "1";
            } else {
                r = "0";
            }
        }
        return r;
    }

    public static boolean q() {
        try {
            File parentFile = Environment.getExternalStorageDirectory().getParentFile();
            if (parentFile != null && (parentFile.listFiles() == null || parentFile.listFiles().length == 0)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean r() {
        try {
            Iterator it = al.a(ReaderApplication.getApplicationImp()).iterator();
            while (it.hasNext()) {
                StorageBean storageBean = (StorageBean) it.next();
                if (storageBean != null && storageBean.c() && storageBean.b().equalsIgnoreCase("mounted")) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String s() {
        try {
            Iterator it = al.a(ReaderApplication.getApplicationImp()).iterator();
            while (it.hasNext()) {
                StorageBean storageBean = (StorageBean) it.next();
                if (storageBean != null && storageBean.c() && storageBean.b().equalsIgnoreCase("mounted")) {
                    return storageBean.a();
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    public static String a(InputStream inputStream) throws Exception {
        InputStream bufferedInputStream = new BufferedInputStream(inputStream);
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return stringBuilder.toString();
            }
            stringBuilder.append(readLine);
        }
    }

    public static void j(String str, String str2) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, false);
            fileOutputStream.write(str2.getBytes("UTF-8"));
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
        }
    }

    public static void a(long j, long j2, long j3, String str) {
        if (j3 <= 0) {
            j3 = 1000;
        }
        if (j < 0) {
            j = 0;
        }
        StatisticsManager.a().a("mediaId", Long.valueOf(j)).a("audioTime", Long.valueOf(j3)).d(str).a(108).c();
    }

    public static void a(long j, long j2, long j3) {
        if (j <= 0) {
            j = 0;
        }
        StatisticsManager.a().a("mediaId", Long.valueOf(j)).a("chapterId", Long.valueOf(j2)).a("listenTimeIndex", Long.valueOf(j3)).a(109).c();
    }

    public static void a(int i, final Activity activity, final AudioFloatingWindowView audioFloatingWindowView, long j, final OnlineTag onlineTag, boolean z, final OnClickListener onClickListener) {
        if (audioFloatingWindowView != null) {
            if (!z || onlineTag == null || j == e.a) {
                audioFloatingWindowView.setOnClickListener(null);
                audioFloatingWindowView.setVisibility(8);
                Drawable drawable = audioFloatingWindowView.getDrawable();
                if (drawable != null && (drawable instanceof AnimationDrawable)) {
                    ((AnimationDrawable) drawable).stop();
                    return;
                }
                return;
            }
            audioFloatingWindowView.setVisibility(0);
            audioFloatingWindowView.setShadowDrawable(ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.audio_play_floating_window_bg_shadow));
            audioFloatingWindowView.setShadowWidthAndHeight(a(6.0f), a(6.0f));
            audioFloatingWindowView.setBorderWidth(0.0f);
            audioFloatingWindowView.setBorderColor(0);
            final AnimationDrawable animationDrawable = (AnimationDrawable) ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.audio_playing_floating_window_animation);
            animationDrawable.start();
            audioFloatingWindowView.setImageDrawable(animationDrawable);
            audioFloatingWindowView.setAdid(j);
            String g = g(j);
            audioFloatingWindowView.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c() {
                public void a(View view) {
                    if (onClickListener != null) {
                        onClickListener.onClick(view);
                    }
                    o.a(activity, onlineTag, null);
                }
            });
            com.qq.reader.common.imageloader.c.a(ReaderApplication.getApplicationImp()).a(g, (ImageView) audioFloatingWindowView, com.qq.reader.common.imageloader.a.a().d(), new com.bumptech.glide.request.e<String, com.bumptech.glide.load.resource.a.b>() {
                public boolean a(Exception exception, String str, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                    return false;
                }

                public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                    audioFloatingWindowView.setBackgroundDrawable(bVar);
                    audioFloatingWindowView.setImageDrawable(animationDrawable);
                    return true;
                }
            });
        }
    }

    public static void a(int i, final Activity activity, final AudioFloatingWindowView audioFloatingWindowView, final long j, boolean z, final String str) {
        if (audioFloatingWindowView != null) {
            if (!z || j == e.a) {
                audioFloatingWindowView.setOnClickListener(null);
                audioFloatingWindowView.setVisibility(8);
                Drawable drawable = audioFloatingWindowView.getDrawable();
                if (drawable != null && (drawable instanceof AnimationDrawable)) {
                    ((AnimationDrawable) drawable).stop();
                    return;
                }
                return;
            }
            com.qq.reader.common.monitor.i.a("event_B258", null, ReaderApplication.getApplicationImp());
            audioFloatingWindowView.setVisibility(0);
            audioFloatingWindowView.setShadowDrawable(ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.audio_play_floating_window_bg_shadow));
            audioFloatingWindowView.setShadowWidthAndHeight(a(6.0f), a(6.0f));
            audioFloatingWindowView.setBorderWidth(0.0f);
            audioFloatingWindowView.setBorderColor(0);
            final AnimationDrawable animationDrawable = (AnimationDrawable) ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.audio_playing_floating_window_animation);
            animationDrawable.start();
            audioFloatingWindowView.setImageDrawable(animationDrawable);
            audioFloatingWindowView.setAdid(j);
            String a = a(j, true, 180);
            audioFloatingWindowView.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c() {
                public void a(View view) {
                    com.qq.reader.common.monitor.i.a("event_B259", null, ReaderApplication.getApplicationImp());
                    o.c(activity, String.valueOf(j), str, null, null);
                }
            });
            com.qq.reader.common.imageloader.c.a(ReaderApplication.getApplicationImp()).a(a, (ImageView) audioFloatingWindowView, com.qq.reader.common.imageloader.a.a().d(), new com.bumptech.glide.request.e<String, com.bumptech.glide.load.resource.a.b>() {
                public boolean a(Exception exception, String str, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                    return false;
                }

                public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                    audioFloatingWindowView.setBackgroundDrawable(bVar);
                    audioFloatingWindowView.setImageDrawable(animationDrawable);
                    return true;
                }
            });
        }
    }

    public static void a(int i, long j, boolean z, String str) {
        e.a().a(i, j, z, str);
    }

    public static String a(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String[] split = str.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        if (split == null || split.length <= 0) {
            stringBuilder.append(str);
        } else {
            stringBuilder.append("第").append(i).append("集 ").append(split[split.length - 1].replace(".mp3", ""));
        }
        return stringBuilder.toString();
    }

    private static void i(int i) {
        if (i == 2 || i == 4) {
            e.a().a(1, e.a, true, null);
        } else {
            e.a().a(1, e.a, false, null);
        }
    }

    public static void f(int i) {
        Intent intent = new Intent("BROADCAST_ACTION_TTS_STATE_CHANGE");
        intent.putExtra("play_state", i);
        android.support.v4.content.d.a(ReaderApplication.getApplicationImp()).a(intent);
        i(i);
    }

    public static void t() {
        android.support.v4.content.d.a(ReaderApplication.getApplicationImp()).a(new Intent("BROADCAST_ACTION_TTS_START"));
    }

    public static void u() {
        android.support.v4.content.d.a(ReaderApplication.getApplicationImp()).a(new Intent("BROADCAST_ACTION_TTS_STOP"));
    }

    public static void v() {
        android.support.v4.content.d.a(ReaderApplication.getApplicationImp()).a(new Intent("BROADCAST_ACTION_TTS_RESUME"));
    }

    public static void w() {
        android.support.v4.content.d.a(ReaderApplication.getApplicationImp()).a(new Intent("BROADCAST_ACTION_TTS_PAUSE"));
    }

    public static void g(int i) {
        Intent intent = new Intent("BROADCAST_ACTION_TTS_SWITCH_TTS");
        intent.putExtra("BROADCAST_EXTRA_TTS_TYPE", i);
        android.support.v4.content.d.a(ReaderApplication.getApplicationImp()).a(intent);
    }

    public static void b(OnlineTag onlineTag) {
        Intent intent = new Intent("BROADCAST_ACTION_TTS_SWITCH_CHAPTER");
        intent.putExtra("resultOnlinetag", onlineTag);
        android.support.v4.content.d.a(ReaderApplication.getApplicationImp()).a(intent);
    }

    public static void c(OnlineTag onlineTag) {
        Intent intent = new Intent("BROADCAST_ACTION_TTS_SWITCH_CHAPTER_SUCEESS");
        intent.putExtra("onlinetag", onlineTag);
        android.support.v4.content.d.a(ReaderApplication.getApplicationImp()).a(intent);
    }

    public static void h(int i) {
        Intent intent = new Intent("BROADCAST_ACTION_TTS_CHAPTER_END");
        intent.putExtra("book_chapterid", i);
        android.support.v4.content.d.a(ReaderApplication.getApplicationImp()).a(intent);
    }

    public static void a(ReadOnlineResult readOnlineResult, int i) {
        Intent intent = new Intent(com.qq.reader.plugin.audiobook.core.e.m);
        intent.putExtra("onlinetag", readOnlineResult);
        intent.putExtra("download_type", i);
        android.support.v4.content.d.a(ReaderApplication.getApplicationImp()).a(intent);
    }

    public static void x() {
        android.support.v4.content.d.a(ReaderApplication.getApplicationImp()).a(new Intent(com.qq.reader.plugin.audiobook.core.e.o));
    }

    public static void a(boolean z) {
        Intent intent = new Intent(com.qq.reader.plugin.audiobook.core.e.E);
        intent.putExtra("auto_pay", z);
        android.support.v4.content.d.a(ReaderApplication.getApplicationImp()).a(intent);
    }

    public static boolean a(int i, int i2) {
        return i == -1 || i2 == -1;
    }
}

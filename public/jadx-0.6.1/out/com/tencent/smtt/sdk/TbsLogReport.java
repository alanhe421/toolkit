package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import com.dynamicload.Lib.DLConstants;
import com.tencent.av.config.ConfigBaseParser;
import com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.a;
import com.tencent.smtt.utils.k;
import com.tencent.smtt.utils.s;
import com.tencent.smtt.utils.t;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.json.JSONArray;

class TbsLogReport {
    private static TbsLogReport b;
    int a;
    private Context c;
    private long d;
    private String e;
    private String f;
    private int g;
    private int h;
    private int i;
    private int j;
    private String k;
    private int l;
    private int m;
    private long n;
    private long o;
    private int p;
    private String q;
    private String r;
    private long s;

    public enum EventType {
        TYPE_DOWNLOAD(0),
        TYPE_INSTALL(1),
        TYPE_LOAD(2);
        
        int a;

        private EventType(int i) {
            this.a = i;
        }
    }

    public static class ZipHelper {
        private final String a;
        private final String b;

        public ZipHelper(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        private static void a(File file) {
            RandomAccessFile randomAccessFile;
            Exception e;
            Throwable th;
            try {
                randomAccessFile = new RandomAccessFile(file, "rw");
                if (randomAccessFile != null) {
                    try {
                        int parseInt = Integer.parseInt("00001000", 2);
                        randomAccessFile.seek(7);
                        int read = randomAccessFile.read();
                        if ((read & parseInt) > 0) {
                            randomAccessFile.seek(7);
                            randomAccessFile.write(((parseInt ^ -1) & 255) & read);
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            e.printStackTrace();
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                    return;
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                }
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    }
                }
            } catch (Exception e5) {
                e = e5;
                randomAccessFile = null;
                e.printStackTrace();
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (Throwable th3) {
                th = th3;
                randomAccessFile = null;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                throw th;
            }
        }

        public void Zip() {
            FileInputStream fileInputStream;
            BufferedInputStream bufferedInputStream;
            Exception e;
            FileInputStream fileInputStream2;
            Throwable th;
            FileOutputStream fileOutputStream;
            ZipOutputStream zipOutputStream = null;
            FileOutputStream fileOutputStream2;
            ZipOutputStream zipOutputStream2;
            try {
                fileOutputStream2 = new FileOutputStream(this.b);
                try {
                    zipOutputStream2 = new ZipOutputStream(new BufferedOutputStream(fileOutputStream2));
                    try {
                        byte[] bArr = new byte[2048];
                        String str = this.a;
                        try {
                            fileInputStream = new FileInputStream(str);
                            try {
                                bufferedInputStream = new BufferedInputStream(fileInputStream, 2048);
                            } catch (Exception e2) {
                                e = e2;
                                bufferedInputStream = null;
                                fileInputStream2 = fileInputStream;
                                try {
                                    e.printStackTrace();
                                    if (bufferedInputStream != null) {
                                        try {
                                            bufferedInputStream.close();
                                        } catch (IOException e3) {
                                            e3.printStackTrace();
                                        }
                                    }
                                    if (fileInputStream2 != null) {
                                        try {
                                            fileInputStream2.close();
                                        } catch (IOException e32) {
                                            e32.printStackTrace();
                                        }
                                    }
                                    a(new File(this.b));
                                    if (zipOutputStream2 != null) {
                                        try {
                                            zipOutputStream2.close();
                                        } catch (IOException e322) {
                                            e322.printStackTrace();
                                        }
                                    }
                                    if (fileOutputStream2 == null) {
                                        try {
                                            fileOutputStream2.close();
                                        } catch (IOException e3222) {
                                            e3222.printStackTrace();
                                            return;
                                        }
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    fileInputStream = fileInputStream2;
                                    if (bufferedInputStream != null) {
                                        try {
                                            bufferedInputStream.close();
                                        } catch (IOException e4) {
                                            e4.printStackTrace();
                                        }
                                    }
                                    if (fileInputStream != null) {
                                        try {
                                            fileInputStream.close();
                                        } catch (IOException e42) {
                                            e42.printStackTrace();
                                        }
                                    }
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                bufferedInputStream = null;
                                if (bufferedInputStream != null) {
                                    bufferedInputStream.close();
                                }
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                throw th;
                            }
                            try {
                                zipOutputStream2.putNextEntry(new ZipEntry(str.substring(str.lastIndexOf("/") + 1)));
                                while (true) {
                                    int read = bufferedInputStream.read(bArr, 0, 2048);
                                    if (read == -1) {
                                        break;
                                    }
                                    zipOutputStream2.write(bArr, 0, read);
                                }
                                zipOutputStream2.flush();
                                zipOutputStream2.closeEntry();
                                if (bufferedInputStream != null) {
                                    try {
                                        bufferedInputStream.close();
                                    } catch (IOException e32222) {
                                        e32222.printStackTrace();
                                    }
                                }
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e322222) {
                                        e322222.printStackTrace();
                                    }
                                }
                            } catch (Exception e5) {
                                e = e5;
                                fileInputStream2 = fileInputStream;
                                e.printStackTrace();
                                if (bufferedInputStream != null) {
                                    bufferedInputStream.close();
                                }
                                if (fileInputStream2 != null) {
                                    fileInputStream2.close();
                                }
                                a(new File(this.b));
                                if (zipOutputStream2 != null) {
                                    zipOutputStream2.close();
                                }
                                if (fileOutputStream2 == null) {
                                    fileOutputStream2.close();
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                if (bufferedInputStream != null) {
                                    bufferedInputStream.close();
                                }
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                throw th;
                            }
                        } catch (Exception e6) {
                            e = e6;
                            bufferedInputStream = null;
                            e.printStackTrace();
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                            if (fileInputStream2 != null) {
                                fileInputStream2.close();
                            }
                            a(new File(this.b));
                            if (zipOutputStream2 != null) {
                                zipOutputStream2.close();
                            }
                            if (fileOutputStream2 == null) {
                                fileOutputStream2.close();
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            bufferedInputStream = null;
                            fileInputStream = null;
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            throw th;
                        }
                        a(new File(this.b));
                        if (zipOutputStream2 != null) {
                            zipOutputStream2.close();
                        }
                        if (fileOutputStream2 == null) {
                            fileOutputStream2.close();
                        }
                    } catch (Exception e7) {
                        e = e7;
                        zipOutputStream = zipOutputStream2;
                        fileOutputStream = fileOutputStream2;
                        try {
                            e.printStackTrace();
                            if (zipOutputStream != null) {
                                try {
                                    zipOutputStream.close();
                                } catch (IOException e3222222) {
                                    e3222222.printStackTrace();
                                }
                            }
                            if (fileOutputStream == null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e32222222) {
                                    e32222222.printStackTrace();
                                }
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            zipOutputStream2 = zipOutputStream;
                            fileOutputStream2 = fileOutputStream;
                            if (zipOutputStream2 != null) {
                                try {
                                    zipOutputStream2.close();
                                } catch (IOException e422) {
                                    e422.printStackTrace();
                                }
                            }
                            if (fileOutputStream2 != null) {
                                try {
                                    fileOutputStream2.close();
                                } catch (IOException e4222) {
                                    e4222.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th7) {
                        th = th7;
                        if (zipOutputStream2 != null) {
                            zipOutputStream2.close();
                        }
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        throw th;
                    }
                } catch (Exception e8) {
                    e = e8;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    if (zipOutputStream != null) {
                        zipOutputStream.close();
                    }
                    if (fileOutputStream == null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th8) {
                    th = th8;
                    zipOutputStream2 = null;
                    if (zipOutputStream2 != null) {
                        zipOutputStream2.close();
                    }
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    throw th;
                }
            } catch (Exception e9) {
                e = e9;
                fileOutputStream = null;
                e.printStackTrace();
                if (zipOutputStream != null) {
                    zipOutputStream.close();
                }
                if (fileOutputStream == null) {
                    fileOutputStream.close();
                }
            } catch (Throwable th9) {
                th = th9;
                zipOutputStream2 = null;
                fileOutputStream2 = null;
                if (zipOutputStream2 != null) {
                    zipOutputStream2.close();
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                throw th;
            }
        }
    }

    private TbsLogReport(Context context) {
        this.c = context.getApplicationContext();
        b();
    }

    public static synchronized TbsLogReport a(Context context) {
        TbsLogReport tbsLogReport;
        synchronized (TbsLogReport.class) {
            if (b == null) {
                b = new TbsLogReport(context);
            }
            tbsLogReport = b;
        }
        return tbsLogReport;
    }

    private String b(int i) {
        return i + DLConstants.DEPENDENCY_PACKAGE_DIV;
    }

    private String b(long j) {
        String str = null;
        try {
            str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(j));
        } catch (Exception e) {
        }
        return str;
    }

    private String b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (str == null) {
            str = "";
        }
        return stringBuilder.append(str).append(DLConstants.DEPENDENCY_PACKAGE_DIV).toString();
    }

    private void b() {
        this.d = 0;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 2;
        this.k = ConfigBaseParser.DEFAULT_VALUE;
        this.l = 0;
        this.m = 2;
        this.n = 0;
        this.o = 0;
        this.p = 1;
        this.a = 0;
        this.q = null;
        this.r = null;
        this.s = 0;
    }

    private String c(long j) {
        return j + DLConstants.DEPENDENCY_PACKAGE_DIV;
    }

    private JSONArray c() {
        String string = e().getString("tbs_download_upload", null);
        if (string == null) {
            return new JSONArray();
        }
        try {
            return new JSONArray(string);
        } catch (Exception e) {
            return new JSONArray();
        }
    }

    private void d() {
        Editor edit = e().edit();
        edit.remove("tbs_download_upload");
        edit.commit();
    }

    private SharedPreferences e() {
        return this.c.getSharedPreferences("tbs_download_stat", 4);
    }

    public void a() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] Run in UIThread, Report delay");
            return;
        }
        synchronized (this) {
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat]");
            JSONArray c = c();
            if (c == null || c.length() == 0) {
                TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] no data");
                return;
            }
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] jsonArray:" + c);
            try {
                TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] response:" + k.a(t.a(this.c).c(), c.toString().getBytes("utf-8"), new af(this), true) + " testcase: " + -1);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void a(int i) {
        if (!(i == 100 || i == 110 || i == 120 || i == 111 || i >= 400)) {
            TbsLog.i(TbsDownloader.LOGTAG, "error occured, errorCode:" + i, true);
        }
        if (i == 111) {
            TbsLog.i(TbsDownloader.LOGTAG, "you are not in wifi, downloading stoped", true);
        }
        this.a = i;
    }

    public void a(int i, String str) {
        a(i);
        a(System.currentTimeMillis());
        a(str);
        a(EventType.TYPE_LOAD);
    }

    public void a(int i, Throwable th) {
        if (th != null) {
            String str = "msg: " + th.getMessage() + "; err: " + th + "; cause: " + Log.getStackTraceString(th.getCause());
            if (str.length() > 1024) {
                str = str.substring(0, 1024);
            }
            this.r = str;
        } else {
            this.r = "NULL";
        }
        a(i, this.r);
    }

    public void a(long j) {
        this.d = j;
    }

    public void a(EventType eventType) {
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(b(eventType.a));
        stringBuilder.append(b(a.c(this.c)));
        stringBuilder.append(b(s.a(this.c)));
        stringBuilder.append(b(z.a().d(this.c)));
        String str2 = Build.MODEL;
        try {
            str = new String(str2.getBytes("UTF-8"), "ISO8859-1");
        } catch (Exception e) {
            str = str2;
        }
        stringBuilder.append(b(str));
        str = this.c.getPackageName();
        stringBuilder.append(b(str));
        if ("com.tencent.mm".equals(str)) {
            stringBuilder.append(b(a.a(this.c, "com.tencent.mm.BuildInfo.CLIENT_VERSION")));
        } else {
            stringBuilder.append(b(a.b(this.c)));
        }
        stringBuilder.append(b(b(this.d)));
        stringBuilder.append(b(this.e));
        stringBuilder.append(b(this.f));
        stringBuilder.append(b(this.g));
        stringBuilder.append(b(this.h));
        stringBuilder.append(b(this.i));
        stringBuilder.append(b(this.j));
        stringBuilder.append(b(this.k));
        stringBuilder.append(b(this.l));
        stringBuilder.append(b(this.m));
        stringBuilder.append(c(this.s));
        stringBuilder.append(c(this.n));
        stringBuilder.append(c(this.o));
        stringBuilder.append(b(this.p));
        stringBuilder.append(b(this.a));
        stringBuilder.append(b(this.q));
        stringBuilder.append(b(this.r));
        stringBuilder.append(b(TbsDownloadConfig.getInstance(this.c).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0)));
        stringBuilder.append(b(a.f(this.c)));
        stringBuilder.append(b("2.3.0.1048_36801"));
        stringBuilder.append(false);
        SharedPreferences e2 = e();
        JSONArray c = c();
        c.put(stringBuilder.toString());
        Editor edit = e2.edit();
        edit.putString("tbs_download_upload", c.toString());
        edit.commit();
        b();
        new Thread(new ae(this)).start();
    }

    public void a(String str) {
        if (str != null) {
            if (str.length() > 1024) {
                str = str.substring(0, 1024);
            }
            this.r = str;
        }
    }
}

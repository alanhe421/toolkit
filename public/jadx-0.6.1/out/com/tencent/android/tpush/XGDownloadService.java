package com.tencent.android.tpush;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.g;
import com.tencent.android.tpush.common.m;
import com.tencent.android.tpush.service.d.f;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* compiled from: ProGuard */
public class XGDownloadService extends Service {
    private static final String c = XGDownloadService.class.getSimpleName();
    private int a = 0;
    private String b = "";
    private File d = null;
    private File e = null;
    private NotificationManager f = null;
    private Notification g = null;
    private Intent h = null;
    private PendingIntent i = null;
    private Handler j = new a(this);

    public long a(String str, File file, int i) {
        FileOutputStream fileOutputStream;
        HttpURLConnection httpURLConnection;
        Throwable th;
        FileOutputStream fileOutputStream2;
        InputStream inputStream;
        long j = 0;
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection2.setRequestProperty("User-Agent", "PacificHttpClient");
                httpURLConnection2.setConnectTimeout(Constants.ERRORCODE_UNKNOWN);
                httpURLConnection2.setReadTimeout(20000);
                int contentLength = httpURLConnection2.getContentLength();
                if (httpURLConnection2.getResponseCode() == 404) {
                    throw new Exception("fail!");
                }
                InputStream inputStream2 = httpURLConnection2.getInputStream();
                try {
                    fileOutputStream = new FileOutputStream(file, false);
                } catch (Throwable th2) {
                    httpURLConnection = httpURLConnection2;
                    th = th2;
                    fileOutputStream2 = null;
                    inputStream = inputStream2;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    throw th;
                }
                try {
                    byte[] bArr = new byte[4096];
                    int i2 = 0;
                    while (true) {
                        int read = inputStream2.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                        j += (long) read;
                        if (i2 == 0 || ((int) ((100 * j) / ((long) contentLength))) - 10 > i2) {
                            i2 += 10;
                            this.g.setLatestEventInfo(this, "正在下载", ((((int) j) * 100) / contentLength) + "%", this.i);
                            this.f.notify(i, this.g);
                        }
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    return j;
                } catch (Throwable th22) {
                    inputStream = inputStream2;
                    FileOutputStream fileOutputStream3 = fileOutputStream;
                    httpURLConnection = httpURLConnection2;
                    th = th22;
                    fileOutputStream2 = fileOutputStream3;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    throw th;
                }
            } catch (Throwable th222) {
                Throwable th3 = th222;
                fileOutputStream2 = null;
                inputStream = null;
                httpURLConnection = httpURLConnection2;
                th = th3;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            inputStream = null;
            httpURLConnection = null;
            fileOutputStream2 = null;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        int i3;
        Throwable th;
        this.b = intent.getStringExtra(Constants.FLAG_PACKAGE_DOWNLOAD_URL);
        int a;
        try {
            String str = "NOTIFY_ID";
            a = m.a(this, str, 0);
            if (a >= 2147483646) {
                a = 0;
            }
            try {
                m.b(this, str, a + 1);
                i3 = a;
            } catch (Throwable th2) {
                th = th2;
                a.c(c, "", th);
                i3 = a;
                if (f.b()) {
                    this.d = new File(Environment.getExternalStorageDirectory(), "app/download/");
                    this.e = new File(this.d.getPath(), "downloadApp" + i3 + ShareConstants.PATCH_SUFFIX);
                }
                this.f = (NotificationManager) getSystemService("notification");
                this.g = new Notification();
                this.g.icon = getApplicationInfo().icon;
                this.g.tickerText = "开始下载";
                this.g.setLatestEventInfo(this, "下载应用", "0%", this.i);
                this.f.notify(i3, this.g);
                g.a().a(new b(this, intent, i3));
                return super.onStartCommand(intent, i, i2);
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            a = 0;
            th = th4;
            a.c(c, "", th);
            i3 = a;
            if (f.b()) {
                this.d = new File(Environment.getExternalStorageDirectory(), "app/download/");
                this.e = new File(this.d.getPath(), "downloadApp" + i3 + ShareConstants.PATCH_SUFFIX);
            }
            this.f = (NotificationManager) getSystemService("notification");
            this.g = new Notification();
            this.g.icon = getApplicationInfo().icon;
            this.g.tickerText = "开始下载";
            this.g.setLatestEventInfo(this, "下载应用", "0%", this.i);
            this.f.notify(i3, this.g);
            g.a().a(new b(this, intent, i3));
            return super.onStartCommand(intent, i, i2);
        }
        if (f.b()) {
            this.d = new File(Environment.getExternalStorageDirectory(), "app/download/");
            this.e = new File(this.d.getPath(), "downloadApp" + i3 + ShareConstants.PATCH_SUFFIX);
        }
        this.f = (NotificationManager) getSystemService("notification");
        this.g = new Notification();
        this.g.icon = getApplicationInfo().icon;
        this.g.tickerText = "开始下载";
        this.g.setLatestEventInfo(this, "下载应用", "0%", this.i);
        this.f.notify(i3, this.g);
        g.a().a(new b(this, intent, i3));
        return super.onStartCommand(intent, i, i2);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}

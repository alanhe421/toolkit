package com.qq.reader.cservice.download.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import com.qq.reader.appconfig.a.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.ao;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import tencent.tls.platform.SigType;

/* compiled from: ReaderUpdateHandler */
public class b {
    ReaderDownloadAppTask a;
    private Context b;
    private String c = null;
    private String d;
    private volatile boolean e = false;
    private boolean f = true;
    private int g = 0;
    private final int h = 3;
    private a i;

    /* compiled from: ReaderUpdateHandler */
    public interface a {
        void createNotification();

        void updateNotificationProgress(int i);
    }

    public void a(Context context, boolean z) {
        this.b = context;
        if (!c(context)) {
            f.a("isWifiAvailable", "false");
        } else if (!ReaderDownloadAppTask.isDownloading) {
            this.e = z;
            if (!this.e) {
                this.i = (a) this.b;
            }
            this.d = c.b;
            if (this.d != null && this.d.length() > 0) {
                if (!this.d.startsWith("http://")) {
                    this.d = "http://" + this.d;
                }
                this.c = com.qq.reader.common.imageloader.a.a.a.f + c.c + ShareConstants.PATCH_SUFFIX;
                if (c.c == null) {
                    f.a("Config.ServerConfig.update_version", "null");
                } else if (!a()) {
                    c();
                } else if (this.e) {
                    f.a("isFileExists", "isAutoUpdate");
                } else {
                    d();
                }
            }
        } else if (this.e && !z) {
            this.e = z;
            this.i = (a) this.b;
            this.i.createNotification();
        }
    }

    private void c() {
        this.a = new ReaderDownloadAppTask(this.b, this.c, this.d);
        this.a.setListener(new a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(boolean z, String str) {
                if (z) {
                    this.a.f = false;
                    if (!this.a.e) {
                        this.a.d();
                        return;
                    }
                    return;
                }
                this.a.g = this.a.g + 1;
                if (this.a.g <= 3 && this.a.c(this.a.b)) {
                    g.a().a(this.a.a);
                }
            }

            public void a(int i) {
                if (!this.a.e) {
                    this.a.i.updateNotificationProgress(i);
                }
            }
        });
        g.a().a(this.a);
        if (!this.e) {
            this.i.createNotification();
        }
    }

    private boolean c(Context context) {
        return ao.f(context) && ao.e(context);
    }

    public boolean a() {
        if (this.c == null) {
            this.c = com.qq.reader.common.imageloader.a.a.a.f + c.c + ShareConstants.PATCH_SUFFIX;
        }
        if (new File(this.c).exists()) {
            this.f = false;
            return true;
        }
        String str = c.c + ShareConstants.PATCH_SUFFIX;
        String str2 = str + ".temp";
        File[] listFiles = new File(com.qq.reader.common.imageloader.a.a.a.f).listFiles();
        if (listFiles == null) {
            return false;
        }
        for (File file : listFiles) {
            if (!(file.getName().equals(str) || file.getName().equals(str2))) {
                file.delete();
            }
        }
        this.f = true;
        return false;
    }

    private void d() {
        if (this.c != null && this.c.length() > 0 && this.c.endsWith(ShareConstants.PATCH_SUFFIX)) {
            File file = new File(this.c);
            Intent intent = new Intent();
            intent.addFlags(SigType.TLS);
            intent.setAction("android.intent.action.VIEW");
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            this.b.getApplicationContext().startActivity(intent);
        }
    }

    public void a(Context context) {
        this.b = context;
        d();
    }

    public boolean b() {
        return this.f;
    }

    public boolean b(Context context) {
        int parseInt = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        SharedPreferences sharedPreferences = context.getSharedPreferences("update_remind", 0);
        if (parseInt == sharedPreferences.getInt("UPDATE_REMIND_DATE", 0)) {
            return true;
        }
        Editor edit = sharedPreferences.edit();
        edit.clear();
        edit.putInt("UPDATE_REMIND_DATE", parseInt);
        edit.commit();
        return false;
    }
}

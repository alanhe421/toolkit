package com.qq.reader.activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.t.d;
import android.widget.RemoteViews;
import com.qq.reader.appconfig.a.c;
import com.qq.reader.common.c.a;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.download.app.ReaderDownloadAppTask;
import com.qq.reader.view.w;
import com.tencent.feedback.proguard.R;

public class AppUpdateActivity extends Activity {
    private w a;
    private int b = 0;
    private String c;
    private String d;
    private ReaderDownloadAppTask e;
    private NotificationManager f;
    private Notification g;
    private final int h = 3;
    private int i = 0;
    private Handler j;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.app_update);
        this.d = getIntent().getExtras().getString(a.cO);
        this.j = new Handler(this) {
            final /* synthetic */ AppUpdateActivity a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                switch (message.what) {
                    case 4012:
                        int i = message.arg1;
                        if (this.a.a != null && this.a.a.f()) {
                            this.a.a.b(i);
                        }
                        this.a.a(i);
                        return;
                    default:
                        return;
                }
            }
        };
        String str = "升级提醒\n\n新特性：\n1.精选信息.精选信息.精选信息..精选信息.精选信息精选信息\n2.发现\n\n新版本：V5.0.0 ";
        if (c.d != null && c.d.length() > 0) {
            str = "升级提醒\n\n" + c.d;
        }
        this.a = new w(this, str);
        this.a.c(true);
        this.a.a(new OnCancelListener(this) {
            final /* synthetic */ AppUpdateActivity a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                if (this.a.a != null && this.a.a.f()) {
                    this.a.a.cancel();
                }
                this.a.getWindow().closeAllPanels();
                this.a.finish();
            }
        });
        this.a.b(false);
        this.a.f_();
        this.c = com.qq.reader.common.imageloader.a.a.a.b + "qqreader.apk";
        str = "http://misc.wcd.qq.com/app?packageName=com.qq.reader&channelId=10000382";
        String str2 = "http://apk.r1.market.hiapk.com/data/upload/2015/03_24/16/com.tencent.qqmusic_165953.apk";
        str2 = this.d;
        if (str2 != null) {
            str = str2;
        }
        this.e = new ReaderDownloadAppTask(getApplicationContext(), this.c, str);
        this.f = (NotificationManager) getSystemService("notification");
        CharSequence charSequence = "开始升级";
        d y = ao.y(getApplicationContext());
        y.c(charSequence);
        this.g = y.a();
        this.g.contentView = new RemoteViews(getPackageName(), R.layout.downloading_notification_item);
        this.g.contentView.setTextViewText(R.id.download_progress_tip, getResources().getString(R.string.app_name) + "升级");
        this.g.contentView.setTextViewText(R.id.download_progress_percent, "0%");
        this.g.tickerText = charSequence;
        this.f.notify(1000, this.g);
    }

    private void a(int i) {
        if (i != 100) {
            this.g.contentView.setProgressBar(R.id.download_progress_progressbar, 100, i, false);
            this.g.contentView.setTextViewText(R.id.download_progress_percent, i + "%");
            this.f.notify(1000, this.g);
            return;
        }
        this.f.cancel(1000);
    }
}

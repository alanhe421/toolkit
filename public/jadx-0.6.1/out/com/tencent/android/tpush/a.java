package com.tencent.android.tpush;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;

/* compiled from: ProGuard */
class a extends Handler {
    final /* synthetic */ XGDownloadService a;

    a(XGDownloadService xGDownloadService) {
        this.a = xGDownloadService;
    }

    public void handleMessage(Message message) {
        int i = message.arg1;
        message.getData();
        switch (message.what) {
            case 0:
                Uri fromFile = Uri.fromFile(this.a.e);
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setDataAndType(fromFile, "application/vnd.android.package-archive");
                this.a.i = PendingIntent.getActivity(this.a, i, intent, 0);
                this.a.g.flags = 16;
                this.a.g.defaults = 1;
                this.a.g.setLatestEventInfo(this.a, "下载应用", "下载完成,点击安装!", this.a.i);
                this.a.f.notify(i, this.a.g);
                this.a.stopSelf();
                return;
            case 1:
                this.a.g.flags = 16;
                this.a.g.setLatestEventInfo(this.a, "下载应用", "下载失败!", this.a.i);
                this.a.f.notify(i, this.a.g);
                return;
            default:
                this.a.stopSelf();
                return;
        }
    }
}

package com.qq.reader.cservice.download.book;

import android.content.Context;
import com.qq.reader.common.download.task.g;
import com.qq.reader.common.download.task.l;
import com.qq.reader.common.utils.ao;

/* compiled from: DownLoadNotifacation */
public class a {
    private Context a;
    private String b;

    public a(Context context) {
        this.a = context;
    }

    public void a(DownloadBookTask downloadBookTask) {
        if (downloadBookTask.getFromType() != 1) {
            ao.a(this.a, (byte) 0, 1, (g) downloadBookTask);
        }
    }

    public void b(DownloadBookTask downloadBookTask) {
        if (downloadBookTask.getFromType() != 1) {
            this.b = downloadBookTask.getFilePath();
            l.a(1001, downloadBookTask.getName());
            ao.a(this.a, (byte) 1, l.f(1000), (g) downloadBookTask);
        }
    }

    public void a() {
        l.e(1001);
    }
}

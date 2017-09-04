package com.qq.reader.cservice.download.audio;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.t.d;
import com.qq.reader.activity.AudioBookDownloadActivity;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.download.a.b;
import com.qq.reader.module.bookstore.qnative.activity.NativeAudioBookPlayerActivity;
import java.util.ArrayList;
import tencent.tls.platform.SigType;

/* compiled from: AudioBookDownloadManager */
public class a extends com.qq.reader.cservice.download.a.a {
    private static a i;
    protected ArrayList<Integer> a = new ArrayList();
    private Handler j;

    private a() {
        super(false);
    }

    public void a(Handler handler) {
        this.j = handler;
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (i == null) {
                i = new a();
                i.f();
            }
            aVar = i;
        }
        return aVar;
    }

    protected void a(b bVar) {
        if (bVar.j() == 40) {
            this.a.add(Integer.valueOf(((b) bVar).b));
        }
    }

    public void b(b bVar) {
        Message obtain = Message.obtain(this.j, 1500001);
        if (bVar != null) {
            obtain.arg1 = bVar.k();
        }
        obtain.sendToTarget();
    }

    protected void a(b bVar, int i) {
        c.b().a((b) bVar);
    }

    protected void b() {
        i = null;
        d();
    }

    protected void c() {
        Message obtain = Message.obtain(this.j, 21007);
        ArrayList arrayList = new ArrayList();
        if (this.a.size() > 0) {
            arrayList.addAll(this.a);
            obtain.obj = arrayList;
            this.a.clear();
            obtain.sendToTarget();
        }
    }

    public boolean a(String str) {
        for (int i = 0; i < this.c.size(); i++) {
            if (((b) this.c.get(i)).a == Long.valueOf(str).longValue()) {
                return true;
            }
        }
        return false;
    }

    public boolean b(String str) {
        for (int i = 0; i < this.d.size(); i++) {
            if (((b) this.d.get(i)).a == Long.valueOf(str).longValue()) {
                return true;
            }
        }
        return false;
    }

    protected void c(b bVar) {
    }

    protected void d(b bVar) {
        CharSequence charSequence = "暂停下载:" + bVar.b();
        b bVar2 = (b) bVar;
        Intent intent = new Intent();
        intent.putExtra("com.qq.reader.OnlineTag", bVar2.d);
        intent.setClass(this.f, AudioBookDownloadActivity.class);
        PendingIntent activity = PendingIntent.getActivity(this.f, 0, intent, SigType.TLS);
        d y = ao.y(this.f);
        y.c(charSequence);
        y.a(bVar.b());
        y.b((CharSequence) "暂停下载");
        y.a(activity);
        y.c(charSequence);
        NotificationManager notificationManager = (NotificationManager) this.f.getSystemService("notification");
        notificationManager.notify(402, y.a());
        notificationManager.cancel(401);
    }

    protected void e(b bVar) {
        CharSequence charSequence = "正在下载:" + bVar.b();
        b bVar2 = (b) bVar;
        Intent intent = new Intent();
        intent.putExtra("com.qq.reader.OnlineTag", bVar2.d);
        intent.setClass(this.f, AudioBookDownloadActivity.class);
        PendingIntent activity = PendingIntent.getActivity(this.f, 0, intent, SigType.TLS);
        d y = ao.y(this.f);
        y.c(charSequence);
        y.a(bVar.b());
        y.b((CharSequence) "正在下载");
        y.a(activity);
        y.c(charSequence);
        ((NotificationManager) this.f.getSystemService("notification")).notify(401, y.a());
    }

    protected void a(b bVar, boolean z) {
        if (bVar.j() != 30) {
            CharSequence charSequence;
            if (z) {
                charSequence = "下载完成";
            } else {
                Object obj = "下载失败:" + bVar.b();
            }
            Intent intent = new Intent();
            b bVar2 = (b) bVar;
            if (z) {
                Bundle bundle = new Bundle();
                bundle.putString("filepath", bVar2.a + "");
                intent.putExtras(bundle);
                intent.setClass(this.f, NativeAudioBookPlayerActivity.class);
                intent.setFlags(335544320);
                i.a("event_C240", null, this.f);
            } else {
                intent.putExtra("com.qq.reader.OnlineTag", bVar2.d);
                intent.setClass(this.f, AudioBookDownloadActivity.class);
            }
            PendingIntent activity = PendingIntent.getActivity(this.f, 0, intent, SigType.TLS);
            d y = ao.y(this.f);
            y.c(charSequence);
            y.a(bVar.b());
            y.b(z ? "下载完成" : "下载失败");
            y.a(activity);
            y.c(charSequence);
            Notification a = y.a();
            NotificationManager notificationManager = (NotificationManager) this.f.getSystemService("notification");
            notificationManager.cancel(401);
            notificationManager.notify(403, a);
        }
    }

    public void d() {
        super.d();
        this.j = null;
    }

    public static void e() {
        i = null;
    }

    protected void f() {
        this.b = c.b().c();
        for (int size = this.b.size() - 1; size >= 0; size--) {
            if (((b) this.b.elementAt(size)).j() != 40) {
                this.c.add(this.b.elementAt(size));
            }
        }
    }
}

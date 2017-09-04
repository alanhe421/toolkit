package com.qq.reader.cservice.download.game;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.t.d;
import android.widget.RemoteViews;
import com.bumptech.glide.load.resource.bitmap.j;
import com.bumptech.glide.request.b.g;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.H5GameActivity;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import tencent.tls.platform.SigType;

/* compiled from: DownloadGameNotification */
public class b {
    public static final String a = b.class.getSimpleName();
    private NotificationManager b = ((NotificationManager) ReaderApplication.getApplicationImp().getSystemService("notification"));
    private Map<Long, Notification> c = new HashMap();

    public void a(int i) {
        Resources resources = ReaderApplication.getApplicationImp().getResources();
        d y = ao.y(ReaderApplication.getApplicationImp());
        y.a(resources.getString(R.string.you_have) + i + resources.getString(R.string.uncompleted_game_download_task)).b(resources.getString(R.string.click_to_download));
        Intent intent = new Intent(ReaderApplication.getApplicationImp(), DownloadGameBroadcastReceiver.class);
        intent.putExtra("action", 1);
        y.a(PendingIntent.getBroadcast(ReaderApplication.getApplicationImp(), (int) System.currentTimeMillis(), intent, 0));
        y.c(true);
        this.b.notify(a.c, 0, y.a());
    }

    public void a(d dVar) {
        if (dVar != null) {
            Notification e = e(dVar);
            if (e != null) {
                b(e, dVar);
                e.contentView.setProgressBar(R.id.game_download_progress, 100, dVar.getProgress(), false);
                e.contentView.setTextViewText(R.id.game_download_speed, a(dVar.getDownloadSpeed() * 1000.0f) + "/S");
                e.contentView.setTextViewText(R.id.game_downloaded_size, a((float) dVar.getCurrentSize()));
                e.contentView.setTextViewText(R.id.game_size, "/" + a((float) dVar.getSize()));
                c(e, dVar);
                a(e, true);
                this.b.notify(dVar.getName(), (int) dVar.getId(), e);
            }
        }
    }

    public void b(d dVar) {
        if (dVar != null) {
            Notification e = e(dVar);
            if (e != null) {
                a(e, dVar);
                e.contentView.setProgressBar(R.id.game_download_progress, 100, dVar.getProgress(), false);
                e.contentView.setTextViewText(R.id.game_download_speed, ReaderApplication.getApplicationImp().getResources().getString(R.string.download_game_pause));
                c(e, dVar);
                a(e, false);
                this.b.notify(dVar.getName(), (int) dVar.getId(), e);
            }
        }
    }

    public void a(d dVar, int i) {
        if (dVar != null) {
            Notification e = e(dVar);
            if (e != null) {
                b(e, dVar);
                e.contentView.setProgressBar(R.id.game_download_progress, 100, dVar.getProgress(), false);
                e.contentView.setTextViewText(R.id.game_download_speed, ReaderApplication.getApplicationImp().getResources().getString(R.string.download_game_wait));
                c(e, dVar);
                a(e, true);
                this.b.notify(dVar.getName(), (int) dVar.getId(), e);
                CharSequence charSequence = ReaderApplication.getApplicationImp().getResources().getString(i) + dVar.getFullName();
                d y = ao.y(ReaderApplication.getApplicationImp());
                y.c(charSequence);
                y.a(dVar.getName());
                y.b((CharSequence) "正在下载");
                this.b.notify(401, y.a());
            }
        }
    }

    public void c(d dVar) {
        if (dVar != null) {
            Notification e = e(dVar);
            if (e != null) {
                a(e, dVar);
                e.contentView.setProgressBar(R.id.game_download_progress, 100, dVar.getProgress(), false);
                e.contentView.setTextViewText(R.id.game_download_speed, ReaderApplication.getApplicationImp().getResources().getString(R.string.download_game_failed));
                c(e, dVar);
                a(e, false);
                this.b.notify(dVar.getName(), (int) dVar.getId(), e);
            }
        }
    }

    public void d(d dVar) {
        if (dVar != null) {
            this.b.cancel(dVar.getName(), (int) dVar.getId());
            this.c.remove(Long.valueOf(dVar.getId()));
        }
    }

    private String a(float f) {
        double d = (double) f;
        if (d < 1024.0d) {
            return a(d) + "B";
        }
        d /= 1024.0d;
        if (d < 1024.0d) {
            return a(d) + "KB";
        }
        d /= 1024.0d;
        if (d < 1024.0d) {
            return a(d) + "MB";
        }
        d /= 1024.0d;
        if (d < 1024.0d) {
            return a(d) + "GB";
        }
        return null;
    }

    private String a(double d) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(d);
        int indexOf = stringBuilder.indexOf(".");
        if (indexOf <= 0 || stringBuilder.length() - indexOf <= 2) {
            return stringBuilder.toString();
        }
        return stringBuilder.substring(0, indexOf + 3);
    }

    private Notification e(d dVar) {
        Notification notification = (Notification) this.c.get(Long.valueOf(dVar.getId()));
        if (notification != null) {
            return notification;
        }
        notification = f(dVar);
        this.c.put(Long.valueOf(dVar.getId()), notification);
        return notification;
    }

    private void a(Notification notification, d dVar) {
        Intent intent = new Intent(ReaderApplication.getApplicationImp(), DownloadGameBroadcastReceiver.class);
        intent.putExtra("action", 4);
        intent.putExtra("gameId", dVar.getId());
        intent.putExtra("gameName", dVar.getName());
        notification.contentView.setOnClickPendingIntent(R.id.game_pause_resume, PendingIntent.getBroadcast(ReaderApplication.getApplicationImp(), (int) System.currentTimeMillis(), intent, 0));
        notification.contentView.setImageViewResource(R.id.game_pause_resume, R.drawable.download_game_resume_icon);
    }

    private void b(Notification notification, d dVar) {
        Intent intent = new Intent(ReaderApplication.getApplicationImp(), DownloadGameBroadcastReceiver.class);
        intent.putExtra("action", 3);
        intent.putExtra("gameId", dVar.getId());
        intent.putExtra("gameName", dVar.getName());
        notification.contentView.setOnClickPendingIntent(R.id.game_pause_resume, PendingIntent.getBroadcast(ReaderApplication.getApplicationImp(), (int) System.currentTimeMillis(), intent, 0));
        notification.contentView.setImageViewResource(R.id.game_pause_resume, R.drawable.download_game_pause_icon);
    }

    private void c(final Notification notification, final d dVar) {
        if (dVar.b() == null) {
            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ b c;

                public void run() {
                    c.a(ReaderApplication.getApplicationImp().getApplicationContext()).a(dVar.c(), new g(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void a(Object obj, com.bumptech.glide.request.a.c cVar) {
                            if (obj instanceof j) {
                                j jVar = (j) obj;
                                dVar.a(jVar.b());
                                notification.contentView.setImageViewBitmap(R.id.game_icon, jVar.b());
                                this.a.c.b.notify(dVar.getName(), (int) dVar.getId(), notification);
                            }
                        }
                    });
                }
            });
        }
    }

    private Notification f(d dVar) {
        if (dVar == null) {
            return null;
        }
        RemoteViews remoteViews = new RemoteViews(ReaderApplication.getApplicationImp().getPackageName(), R.layout.download_game_notification_pregress);
        d y = ao.y(ReaderApplication.getApplicationImp());
        y.a(remoteViews);
        y.c(false);
        y.a(true);
        y.b(true);
        remoteViews.setTextViewText(R.id.game_title, dVar.getName());
        Intent intent = new Intent(ReaderApplication.getApplicationImp(), DownloadGameBroadcastReceiver.class);
        intent.putExtra("action", 5);
        intent.putExtra("gameId", dVar.getId());
        intent.putExtra("gameName", dVar.getName());
        remoteViews.setOnClickPendingIntent(R.id.game_cancel, PendingIntent.getBroadcast(ReaderApplication.getApplicationImp(), (int) System.currentTimeMillis(), intent, 0));
        String a = dVar.a();
        if (com.qq.reader.qurl.c.b(a)) {
            a = a.replace("uniteqqreader://webpage/game/", "");
            if (com.qq.reader.qurl.c.b(a)) {
                a = a.substring(a.indexOf(com.tencent.qalsdk.core.c.d));
            }
        }
        intent = new Intent();
        intent.putExtra("com.qq.reader.WebContent", a);
        intent.setClass(ReaderApplication.getApplicationImp(), H5GameActivity.class);
        intent.setFlags(536870912);
        intent.setAction(dVar.a());
        y.a(PendingIntent.getActivity(ReaderApplication.getApplicationImp(), 0, intent, SigType.TLS));
        return y.a();
    }

    public void a() {
        this.c.clear();
    }

    private void a(Notification notification, boolean z) {
        if (z) {
            notification.flags = 2 | notification.flags;
        } else {
            notification.flags &= -3;
        }
    }
}

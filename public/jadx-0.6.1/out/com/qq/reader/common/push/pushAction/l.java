package com.qq.reader.common.push.pushAction;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.SystemClock;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.push.pushAction.a.b;
import org.json.JSONObject;

/* compiled from: RichMediaAction */
public class l extends i {

    /* compiled from: RichMediaAction */
    public class a {
        final /* synthetic */ l c;

        public a(l lVar) {
            this.c = lVar;
        }

        public void a(Bitmap[] bitmapArr) {
        }
    }

    public l(Context context) {
        super(context);
    }

    public void a(JSONObject jSONObject) {
        if (com.qq.reader.common.push.a.a.equals(this.b)) {
            i.a("event_A205", null, ReaderApplication.getApplicationImp());
            final com.qq.reader.common.push.pushAction.a.a a = b.a(jSONObject, a());
            if (a == null) {
                c.e(a, "notify card is null");
                return;
            }
            c.e(a, "start download image");
            a.a(new a(this) {
                final /* synthetic */ l b;

                public void a(Bitmap[] bitmapArr) {
                    c.e(i.a, "load image success");
                    if (bitmapArr != null) {
                        Notification a = a.a(bitmapArr);
                        c.e(i.a, "notification build success");
                        if (a != null) {
                            i.a("event_A206", null, ReaderApplication.getApplicationImp());
                            NotificationManager notificationManager = (NotificationManager) this.b.a().getSystemService("notification");
                            c.e(i.a, "show notification");
                            notificationManager.notify(String.valueOf(SystemClock.currentThreadTimeMillis()), 1001, a);
                        }
                    }
                }
            });
        }
    }
}

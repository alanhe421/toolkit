package com.tencent.android.tpush;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.t.c;
import android.support.v4.app.t.d;
import android.support.v4.app.t.p;
import android.widget.RemoteViews;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.e;
import org.json.JSONObject;

/* compiled from: ProGuard */
public abstract class XGPushNotificationBuilder {
    public static final String BASIC_NOTIFICATION_BUILDER_TYPE = "basic";
    public static final String CUSTOM_NOTIFICATION_BUILDER_TYPE = "custom";
    protected Integer a = null;
    protected PendingIntent b = null;
    protected RemoteViews c = null;
    protected Integer d = null;
    protected PendingIntent e = null;
    protected Integer f = null;
    protected Integer g = null;
    protected Integer h = null;
    protected Integer i = Integer.valueOf(-16711936);
    protected Integer j = Integer.valueOf(100);
    protected Integer k = Integer.valueOf(100);
    protected Integer l = null;
    protected Uri m = null;
    protected CharSequence n = null;
    protected long[] o = null;
    protected Long p = null;
    protected Integer q = null;
    protected Bitmap r = null;
    protected Integer s = null;
    protected String t;
    protected Integer u = null;

    protected abstract void a(JSONObject jSONObject);

    protected abstract void b(JSONObject jSONObject);

    public abstract Notification buildNotification(Context context);

    public abstract String getType();

    public void encode(JSONObject jSONObject) {
        a(jSONObject);
        e.a(jSONObject, "audioStringType", this.a);
        e.a(jSONObject, "defaults", this.d);
        e.a(jSONObject, "flags", this.f);
        e.a(jSONObject, MessageKey.MSG_ICON, this.g);
        e.a(jSONObject, "iconLevel", this.h);
        e.a(jSONObject, "ledARGB", this.i);
        e.a(jSONObject, "ledOffMS", this.j);
        e.a(jSONObject, "ledOnMS", this.k);
        e.a(jSONObject, "number", this.l);
        e.a(jSONObject, "sound", this.m);
        e.a(jSONObject, "smallIcon", this.q);
        e.a(jSONObject, "notificationLargeIcon", this.s);
        if (this.o != null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < this.o.length; i++) {
                stringBuilder.append(String.valueOf(this.o[i]));
                if (i != this.o.length - 1) {
                    stringBuilder.append(",");
                }
            }
            e.a(jSONObject, MessageKey.MSG_VIBRATE, stringBuilder.toString());
        }
        e.a(jSONObject, "notificationId", this.u);
    }

    public void decode(String str) {
        JSONObject jSONObject = new JSONObject(str);
        b(jSONObject);
        this.a = (Integer) e.b(jSONObject, "audioStringType", null);
        this.d = (Integer) e.b(jSONObject, "defaults", null);
        this.f = (Integer) e.b(jSONObject, "flags", null);
        this.g = (Integer) e.b(jSONObject, MessageKey.MSG_ICON, null);
        this.h = (Integer) e.b(jSONObject, "iconLevel", null);
        this.i = (Integer) e.b(jSONObject, "ledARGB", null);
        this.j = (Integer) e.b(jSONObject, "ledOffMS", null);
        this.k = (Integer) e.b(jSONObject, "ledOnMS", null);
        this.l = (Integer) e.b(jSONObject, "number", null);
        String str2 = (String) e.b(jSONObject, "sound", null);
        this.q = (Integer) e.b(jSONObject, "smallIcon", null);
        this.s = (Integer) e.b(jSONObject, "notificationLargeIcon", null);
        if (str2 != null) {
            this.m = Uri.parse(str2);
        }
        str2 = (String) e.b(jSONObject, MessageKey.MSG_VIBRATE, null);
        if (str2 != null) {
            String[] split = str2.split(",");
            int length = split.length;
            this.o = new long[length];
            for (int i = 0; i < length; i++) {
                try {
                    this.o[i] = Long.valueOf(split[i]).longValue();
                } catch (NumberFormatException e) {
                }
            }
        }
        this.u = (Integer) e.b(jSONObject, "notificationId", null);
    }

    public String getTitle(Context context) {
        if (this.t == null) {
            this.t = (String) context.getApplicationContext().getPackageManager().getApplicationLabel(context.getApplicationInfo());
        }
        return this.t;
    }

    public void setTitle(String str) {
        this.t = str;
    }

    public int getApplicationIcon(Context context) {
        return context.getApplicationInfo().icon;
    }

    protected Notification a(Context context) {
        Notification notification = new Notification();
        if (this.u == null) {
            this.u = Integer.valueOf(0);
        }
        d dVar = new d(context);
        p cVar = new c();
        if (this.q != null) {
            dVar.a(this.q.intValue());
        }
        if (this.s != null) {
            dVar.a(BitmapFactory.decodeResource(context.getResources(), this.s.intValue()));
        }
        if (this.r != null) {
            dVar.a(this.r);
        }
        if (this.t == null) {
            this.t = getTitle(context);
        } else {
            dVar.a(this.t);
        }
        if (this.n == null || this.c != null) {
            dVar.b(this.n);
            dVar.c(this.n);
        } else {
            cVar.a(this.n);
            dVar.a(cVar);
            dVar.b(this.n);
            dVar.c(this.n);
        }
        notification = dVar.a();
        if (this.a != null) {
            notification.audioStreamType = this.a.intValue();
        }
        if (this.b != null) {
            notification.contentIntent = this.b;
        }
        if (this.c != null) {
            notification.contentView = this.c;
        }
        if (this.d != null) {
            notification.defaults = this.d.intValue();
        }
        if (this.g != null) {
            notification.icon = this.g.intValue();
        }
        if (this.e != null) {
            notification.deleteIntent = this.e;
        }
        if (this.f != null) {
            notification.flags = this.f.intValue();
        } else {
            notification.flags = 16;
        }
        if (this.h != null) {
            notification.iconLevel = this.h.intValue();
        }
        if (this.i != null) {
            notification.ledARGB = this.i.intValue();
        }
        if (this.j != null) {
            notification.ledOffMS = this.j.intValue();
        }
        if (this.k != null) {
            notification.ledOnMS = this.k.intValue();
        }
        if (this.l != null) {
            notification.number = this.l.intValue();
        }
        if (this.m != null) {
            notification.sound = this.m;
        }
        if (this.o != null) {
            notification.vibrate = this.o;
        }
        if (this.p != null) {
            notification.when = this.p.longValue();
        } else {
            notification.when = System.currentTimeMillis();
        }
        return notification;
    }

    public int getAudioStringType() {
        return this.a.intValue();
    }

    public XGPushNotificationBuilder setAudioStringType(int i) {
        this.a = Integer.valueOf(i);
        return this;
    }

    public PendingIntent getContentIntent() {
        return this.b;
    }

    public XGPushNotificationBuilder setContentIntent(PendingIntent pendingIntent) {
        this.b = pendingIntent;
        return this;
    }

    public XGPushNotificationBuilder setContentView(RemoteViews remoteViews) {
        this.c = remoteViews;
        return this;
    }

    public int getDefaults() {
        return this.d.intValue();
    }

    public XGPushNotificationBuilder setDefaults(int i) {
        if (this.d == null) {
            this.d = Integer.valueOf(i);
        } else {
            this.d = Integer.valueOf(this.d.intValue() | i);
        }
        return this;
    }

    public int getFlags() {
        return this.f.intValue();
    }

    public XGPushNotificationBuilder setFlags(int i) {
        if (this.f == null) {
            this.f = Integer.valueOf(i);
        } else {
            this.f = Integer.valueOf(this.f.intValue() | i);
        }
        return this;
    }

    public Integer getIcon() {
        return this.g;
    }

    public XGPushNotificationBuilder setIcon(Integer num) {
        this.g = num;
        return this;
    }

    public Integer getSmallIcon() {
        return this.q;
    }

    public XGPushNotificationBuilder setSmallIcon(Integer num) {
        this.q = num;
        return this;
    }

    public Bitmap getLargeIcon() {
        return this.r;
    }

    public XGPushNotificationBuilder setLargeIcon(Bitmap bitmap) {
        this.r = bitmap;
        return this;
    }

    public XGPushNotificationBuilder setNotificationLargeIcon(int i) {
        this.s = Integer.valueOf(i);
        return this;
    }

    public Integer getNotificationLargeIcon() {
        return this.s;
    }

    public int getIconLevel() {
        return this.h.intValue();
    }

    public XGPushNotificationBuilder setIconLevel(int i) {
        this.h = Integer.valueOf(i);
        return this;
    }

    public int getLedARGB() {
        return this.i.intValue();
    }

    public XGPushNotificationBuilder setLedARGB(int i) {
        this.i = Integer.valueOf(i);
        return this;
    }

    public int getLedOffMS() {
        return this.j.intValue();
    }

    public XGPushNotificationBuilder setLedOffMS(int i) {
        this.j = Integer.valueOf(i);
        return this;
    }

    public int getLedOnMS() {
        return this.k.intValue();
    }

    public XGPushNotificationBuilder setLedOnMS(int i) {
        this.k = Integer.valueOf(i);
        return this;
    }

    public int getNumber() {
        return this.l.intValue();
    }

    public XGPushNotificationBuilder setNumber(int i) {
        this.l = Integer.valueOf(i);
        return this;
    }

    public Uri getSound() {
        return this.m;
    }

    public XGPushNotificationBuilder setSound(Uri uri) {
        this.m = uri;
        return this;
    }

    public CharSequence getTickerText() {
        return this.n;
    }

    public XGPushNotificationBuilder setTickerText(CharSequence charSequence) {
        this.n = charSequence;
        return this;
    }

    public long[] getVibrate() {
        return this.o;
    }

    public XGPushNotificationBuilder setVibrate(long[] jArr) {
        this.o = jArr;
        return this;
    }

    public long getWhen() {
        return this.p.longValue();
    }

    public XGPushNotificationBuilder setWhen(long j) {
        this.p = Long.valueOf(j);
        return this;
    }
}

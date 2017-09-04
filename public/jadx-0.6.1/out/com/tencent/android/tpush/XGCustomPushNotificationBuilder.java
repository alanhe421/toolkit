package com.tencent.android.tpush;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.tencent.android.tpush.common.e;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class XGCustomPushNotificationBuilder extends XGPushNotificationBuilder {
    private Integer A = null;
    private Integer B = null;
    private Bitmap C = null;
    private Integer v = null;
    private Integer w = null;
    private Integer x = null;
    private Integer y = null;
    private Integer z = null;

    protected void a(JSONObject jSONObject) {
        e.a(jSONObject, "layoutId", this.v);
        e.a(jSONObject, "layoutIconId", this.w);
        e.a(jSONObject, "layoutTitleId", this.x);
        e.a(jSONObject, "layoutTextId", this.y);
        e.a(jSONObject, "layoutIconDrawableId", this.z);
        e.a(jSONObject, "statusBarIconDrawableId", this.A);
        e.a(jSONObject, "layoutTimeId", this.B);
    }

    protected void b(JSONObject jSONObject) {
        this.v = (Integer) e.b(jSONObject, "layoutId", null);
        this.w = (Integer) e.b(jSONObject, "layoutIconId", null);
        this.x = (Integer) e.b(jSONObject, "layoutTitleId", null);
        this.y = (Integer) e.b(jSONObject, "layoutTextId", null);
        this.z = (Integer) e.b(jSONObject, "layoutIconDrawableId", null);
        this.A = (Integer) e.b(jSONObject, "statusBarIconDrawableId", null);
        this.B = (Integer) e.b(jSONObject, "layoutTimeId", null);
    }

    public Notification buildNotification(Context context) {
        if (this.v == null) {
            return a(context);
        }
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), this.v.intValue());
        if (this.x != null) {
            remoteViews.setTextViewText(this.x.intValue(), getTitle(context));
        }
        if (this.y != null) {
            remoteViews.setTextViewText(this.y.intValue(), this.n);
        }
        if (this.w != null) {
            remoteViews.setImageViewResource(this.w.intValue(), this.z.intValue());
        }
        if (this.C != null) {
            remoteViews.setImageViewBitmap(this.w.intValue(), this.C);
        }
        if (this.A != null) {
            remoteViews.setTextViewText(this.A.intValue(), getTitle(context));
        }
        if (this.B != null) {
            remoteViews.setTextViewText(this.B.intValue(), String.valueOf(new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis()))));
        }
        this.c = remoteViews;
        return a(context);
    }

    public int getLayoutId() {
        return this.v.intValue();
    }

    public XGCustomPushNotificationBuilder setLayoutId(int i) {
        this.v = Integer.valueOf(i);
        return this;
    }

    public Integer getLayoutIconId() {
        return this.w;
    }

    public XGCustomPushNotificationBuilder setLayoutIconId(int i) {
        this.w = Integer.valueOf(i);
        return this;
    }

    public int getLayoutTitleId() {
        return this.x.intValue();
    }

    public XGCustomPushNotificationBuilder setLayoutTitleId(int i) {
        this.x = Integer.valueOf(i);
        return this;
    }

    public int getLayoutTextId() {
        return this.y.intValue();
    }

    public int getLayoutTimeId() {
        return this.B.intValue();
    }

    public XGCustomPushNotificationBuilder setLayoutTextId(int i) {
        this.y = Integer.valueOf(i);
        return this;
    }

    public XGCustomPushNotificationBuilder setLayoutTimeId(int i) {
        this.B = Integer.valueOf(i);
        return this;
    }

    public int getLayoutIconDrawableId() {
        return this.z.intValue();
    }

    public XGCustomPushNotificationBuilder setLayoutIconDrawableId(int i) {
        this.z = Integer.valueOf(i);
        return this;
    }

    public XGCustomPushNotificationBuilder setLayoutIconDrawableBmp(Bitmap bitmap) {
        this.C = bitmap;
        return this;
    }

    public String getType() {
        return XGPushNotificationBuilder.CUSTOM_NOTIFICATION_BUILDER_TYPE;
    }
}

package com.qq.reader.common.push.pushAction.a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.RemoteViews;
import com.tencent.feedback.proguard.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONObject;

/* compiled from: RichMediaNotificationType0 */
public class c extends a {
    protected int e;
    protected int f;
    private String g;
    private String h;
    private String i;

    public c(Context context, JSONObject jSONObject) {
        super(context, jSONObject);
    }

    protected void a() {
        try {
            this.g = this.b.optString("contentTitle");
            this.h = this.b.optString("contentDes");
            this.a.add(this.b.optString("bigImageUrl"));
            this.a.add(this.b.optString("smallImageUrl"));
            this.e = Color.parseColor(this.b.optString("bigBgColor"));
            this.f = Color.parseColor(this.b.optString("smallBgColor"));
        } catch (Exception e) {
        }
    }

    private String c() {
        if (this.i == null) {
            this.i = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        }
        return this.i;
    }

    protected RemoteViews b(Bitmap[] bitmapArr) {
        RemoteViews remoteViews = new RemoteViews(b().getPackageName(), R.layout.xiaomi_push_rich_media_a_small);
        if (bitmapArr[1] == null) {
            return null;
        }
        remoteViews.setImageViewBitmap(R.id.push_small_image, bitmapArr[1]);
        if (this.f != 0) {
            remoteViews.setInt(R.id.push_small_bg, "setBackgroundColor", this.f);
        }
        remoteViews.setTextViewText(R.id.push_small_time, c());
        return remoteViews;
    }

    protected RemoteViews c(Bitmap[] bitmapArr) {
        if (bitmapArr[0] == null) {
            return null;
        }
        RemoteViews remoteViews = new RemoteViews(b().getPackageName(), R.layout.xiaomi_push_rich_media_a);
        remoteViews.setImageViewBitmap(R.id.push_big_image, bitmapArr[0]);
        remoteViews.setTextViewText(R.id.push_big_title, this.g);
        remoteViews.setTextViewText(R.id.push_big_des, this.h);
        remoteViews.setTextViewText(R.id.push_big_time, c());
        if (this.e == 0) {
            return remoteViews;
        }
        remoteViews.setInt(R.id.push_big_bg, "setBackgroundColor", this.e);
        return remoteViews;
    }
}

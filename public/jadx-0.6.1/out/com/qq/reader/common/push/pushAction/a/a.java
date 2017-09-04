package com.qq.reader.common.push.pushAction.a;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.app.t.d;
import android.widget.RemoteViews;
import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.request.b.g;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.e;
import com.qq.reader.ReaderApplication;
import com.qq.reader.TypeContext;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* compiled from: BaseRichMediaNotification */
public abstract class a {
    protected List<String> a = new ArrayList();
    protected JSONObject b;
    protected String c;
    protected String d;
    private Context e;

    protected abstract void a();

    protected abstract RemoteViews b(Bitmap[] bitmapArr);

    protected abstract RemoteViews c(Bitmap[] bitmapArr);

    public a(Context context, JSONObject jSONObject) {
        this.e = context;
        this.b = jSONObject;
        this.c = jSONObject.optString("qurl");
        this.d = jSONObject.optString("title");
        a();
    }

    public Context b() {
        return this.e;
    }

    public Notification a(Bitmap[] bitmapArr) {
        d a = new d(b()).c(this.d).a(System.currentTimeMillis());
        a.a(ao.d(ReaderApplication.getApplicationImp(), (int) R.drawable.icon_notify_large));
        if (VERSION.SDK_INT >= 21) {
            a.a((int) R.drawable.icon_notify_small_5_0_plus);
        } else {
            a.a((int) R.drawable.icon_notify_small);
        }
        Notification a2 = a.a();
        a2.flags = 16;
        Intent intent = new Intent();
        intent.setClass(b(), TypeContext.class);
        intent.setFlags(335544320);
        intent.putExtra(s.ORIGIN, "event_D204");
        intent.setData(Uri.parse(this.c));
        RemoteViews c = c(bitmapArr);
        if (VERSION.SDK_INT >= 16 && c != null) {
            a2.bigContentView = c;
            a2.priority = 2;
        }
        a2.contentView = b(bitmapArr);
        if (a2.contentView == null) {
            return null;
        }
        a2.contentIntent = PendingIntent.getActivity(b(), 0, intent, 1073741824);
        return a2;
    }

    public final void a(com.qq.reader.common.push.pushAction.l.a aVar) {
        final Bitmap[] bitmapArr = new Bitmap[this.a.size()];
        if (this.a.size() == 0) {
            aVar.a(bitmapArr);
            return;
        }
        final int[] iArr = new int[this.a.size()];
        for (int i = 0; i < this.a.size(); i++) {
            final com.qq.reader.common.push.pushAction.l.a aVar2 = aVar;
            c.a(b()).a((String) this.a.get(i), new g(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a(Object obj, com.bumptech.glide.request.a.c cVar) {
                }
            }, new e<String, b>(this) {
                final /* synthetic */ a e;

                public boolean a(Exception exception, String str, j<b> jVar, boolean z) {
                    iArr[i] = 1;
                    this.e.a(bitmapArr, iArr, aVar2);
                    return true;
                }

                public boolean a(b bVar, String str, j<b> jVar, boolean z, boolean z2) {
                    if (bVar instanceof com.bumptech.glide.load.resource.bitmap.j) {
                        bitmapArr[i] = ((com.bumptech.glide.load.resource.bitmap.j) bVar).b();
                        iArr[i] = 1;
                        this.e.a(bitmapArr, iArr, aVar2);
                    }
                    return true;
                }
            });
        }
    }

    private void a(Bitmap[] bitmapArr, int[] iArr, com.qq.reader.common.push.pushAction.l.a aVar) {
        int i = 0;
        while (i < iArr.length) {
            if (iArr[i] == 1) {
                i++;
            } else {
                return;
            }
        }
        aVar.a(bitmapArr);
    }
}

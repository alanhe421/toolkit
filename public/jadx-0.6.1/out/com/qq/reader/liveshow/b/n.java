package com.qq.reader.liveshow.b;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.qq.reader.liveshow.a.h;
import com.qq.reader.liveshow.model.a;
import com.qq.reader.liveshow.utils.e;
import com.qq.reader.liveshow.utils.g;
import com.qq.reader.liveshow.views.LiveActivity;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

/* compiled from: QavsdkInitializer */
public class n {
    private static String a = "QavsdkInitializer";
    private static n b;
    private b c;
    private e d;
    private g e;
    private Context f;
    private k g = new k();

    public static synchronized n a() {
        n nVar;
        synchronized (n.class) {
            if (b == null) {
                b = new n();
            }
            nVar = b;
        }
        return nVar;
    }

    private n() {
    }

    public void a(b bVar, e eVar, g gVar, k kVar, Context context) {
        if (kVar != null) {
            this.g = kVar;
        }
        this.e = gVar;
        this.d = eVar;
        this.c = bVar;
        this.f = context;
        m.a(this.g.g());
        l.a(this.g.h());
        e.a();
    }

    public b b() {
        if (this.c == null) {
            g();
        }
        return this.c;
    }

    private void g() {
        if (f() != null) {
            Intent intent = new Intent("com.qq.reader.liveshow.ACTION_START_CONTEXT_COMPLETE");
            intent.putExtra("av_error_result", -1);
            intent.putExtra("av_error_result_code", 1004);
            f().sendBroadcast(intent);
        }
    }

    public e c() {
        if (this.d == null) {
            g();
        }
        return this.d;
    }

    public g d() {
        if (this.e == null) {
            g();
        }
        return this.e;
    }

    public k e() {
        if (this.g == null) {
            g();
        }
        return this.g;
    }

    public void a(Activity activity, int i) {
        if (g.a(activity)) {
            a.w();
            a.e(i);
            a(activity);
            return;
        }
        m.a((Context) activity, activity.getResources().getString(h.live_net_error), 0);
    }

    private void a(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LiveActivity.class);
        intent.addFlags(SigType.WLOGIN_QRPUSH);
        context.startActivity(intent);
    }

    public Context f() {
        return this.f;
    }
}

package com.qq.reader.liveshow.avcontrollers;

import android.content.Context;
import android.content.Intent;
import com.qq.reader.liveshow.utils.SxbLog;
import com.tencent.av.sdk.AVCallback;
import com.tencent.av.sdk.AVContext;
import com.tencent.av.sdk.AVContext.StartParam;
import com.tencent.openqq.IMSdkInt;

/* compiled from: AVContextControl */
class a {
    private static boolean i = false;
    private boolean a = false;
    private boolean b = false;
    private Context c;
    private AVContext d = null;
    private String e = "";
    private String f = "";
    private StartParam g = null;
    private String h = "";
    private AVCallback j = new AVCallback(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onComplete(int i, String str) {
            this.a.a = false;
            SxbLog.b("AvContextControl", "keypath AVSDK startContext  result " + i);
            if (i == 0) {
                a.i = true;
                this.a.c.sendBroadcast(new Intent("com.qq.reader.liveshow.ACTION_START_CONTEXT_COMPLETE").putExtra("av_error_result", i));
            }
            if (i != 0) {
                this.a.d = null;
                this.a.c.sendBroadcast(new Intent("com.qq.reader.liveshow.ACTION_START_CONTEXT_COMPLETE").putExtra("av_error_result", i).putExtra("av_error_result_code", 1006));
            }
        }
    };

    a(Context context) {
        this.c = context;
    }

    int a() {
        if (c()) {
            return 1;
        }
        SxbLog.b("AvContextControl", "AVSDKLogin startContext hasAVContext ");
        a(true, IMSdkInt.get().getTinyId(), 0);
        return 0;
    }

    public void a(int i, String str, String str2, String str3) {
        this.g = new StartParam();
        this.g.sdkAppId = i;
        this.g.accountType = str;
        this.g.appIdAt3rd = Integer.toString(i);
        this.g.identifier = str2;
        this.h = str3;
        this.e = str2;
    }

    void b() {
        if (c()) {
            SxbLog.c("AvContextControl", "WL_DEBUG stopContext");
            this.d.stop();
            this.b = true;
            b(true);
        }
    }

    boolean c() {
        return this.d != null;
    }

    AVContext d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    private void a(boolean z, long j, int i) {
        if (z) {
            this.d = AVContext.createInstance(this.c, false);
            if (this.d == null) {
                this.j.onComplete(i, "");
                return;
            }
            this.d.start(this.g, this.j);
            this.a = true;
            return;
        }
        this.j.onComplete(i, "");
    }

    private void b(boolean z) {
        this.d.destroy();
        this.d = null;
        this.b = false;
        i = false;
        this.c.sendBroadcast(new Intent("com.qq.reader.liveshow.ACTION_CLOSE_CONTEXT_COMPLETE"));
    }

    public static boolean f() {
        return i;
    }
}

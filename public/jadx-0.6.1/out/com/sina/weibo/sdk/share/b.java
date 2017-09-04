package com.sina.weibo.sdk.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.a.g;
import com.sina.weibo.sdk.api.a;
import com.sina.weibo.sdk.b.e;
import com.sina.weibo.sdk.b.k;
import com.sina.weibo.sdk.c;
import com.sina.weibo.sdk.web.WebRequestType;
import com.sina.weibo.sdk.web.b.d;

/* compiled from: WbShareHandler */
public class b {
    private boolean a = false;
    private Activity b;

    public b(Activity activity) {
        this.b = activity;
    }

    public boolean a() {
        a(this.b, "com.sina.weibo.sdk.Intent.ACTION_WEIBO_REGISTER", com.sina.weibo.sdk.b.b().a(), null, null);
        this.a = true;
        return true;
    }

    private void a(Context context, String str, String str2, String str3, Bundle bundle) {
        Intent intent = new Intent(str);
        String packageName = context.getPackageName();
        intent.putExtra("_weibo_sdkVersion", "0031405000");
        intent.putExtra("_weibo_appPackage", packageName);
        intent.putExtra("_weibo_appKey", str2);
        intent.putExtra("_weibo_flag", 538116905);
        intent.putExtra("_weibo_sign", e.a(k.a(context, packageName)));
        if (!TextUtils.isEmpty(str3)) {
            intent.setPackage(str3);
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.sendBroadcast(intent, "com.sina.weibo.permission.WEIBO_SDK_PERMISSION");
    }

    public void a(a aVar, boolean z) {
        if (!this.a) {
            throw new RuntimeException("please call WbShareHandler.registerApp(),before use share function");
        } else if (!b() && z) {
        } else {
            if (z || b()) {
                g.a(this.b, com.sina.weibo.sdk.b.b().a()).a();
                a(aVar);
                return;
            }
            b(aVar);
        }
    }

    private void a(a aVar) {
        Bundle bundle = new Bundle();
        bundle.putInt("_weibo_command_type", 1);
        bundle.putString("_weibo_transaction", String.valueOf(System.currentTimeMillis()));
        bundle.putLong("callbackId", 0);
        bundle.putAll(aVar.a(bundle));
        Intent intent = new Intent();
        intent.setClass(this.b, WbShareTransActivity.class);
        intent.putExtra("startPackage", c.a(this.b).a().a());
        intent.putExtra("startAction", "com.sina.weibo.sdk.action.ACTION_WEIBO_ACTIVITY");
        intent.putExtra("startFlag", 0);
        intent.putExtra("startActivity", this.b.getClass().getName());
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        try {
            this.b.startActivity(intent);
        } catch (Exception e) {
        }
    }

    private void b(a aVar) {
        g.a(this.b, com.sina.weibo.sdk.b.b().a()).a();
        Intent intent = new Intent(this.b, WbShareTransActivity.class);
        String packageName = this.b.getPackageName();
        d dVar = new d(com.sina.weibo.sdk.b.b(), WebRequestType.SHARE, "", 1, "微博分享", null, this.b);
        dVar.a(this.b);
        dVar.b("");
        dVar.c(packageName);
        com.sina.weibo.sdk.auth.b a = com.sina.weibo.sdk.auth.a.a(this.b);
        if (!(a == null || TextUtils.isEmpty(a.c()))) {
            dVar.a(a.c());
        }
        dVar.a(aVar);
        Bundle bundle = new Bundle();
        dVar.c(bundle);
        intent.putExtras(bundle);
        intent.putExtra("startFlag", 0);
        intent.putExtra("startActivity", this.b.getClass().getName());
        intent.putExtra("startAction", "com.sina.weibo.sdk.action.ACTION_WEIBO_ACTIVITY");
        intent.putExtra("gotoActivity", "com.sina.weibo.sdk.web.WeiboSdkWebActivity");
        this.b.startActivity(intent);
    }

    private boolean b() {
        com.sina.weibo.sdk.auth.c a = c.a(this.b).a();
        return a != null && a.d();
    }

    public void a(Intent intent, a aVar) {
        if (aVar != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                switch (extras.getInt("_weibo_resp_errcode")) {
                    case 0:
                        aVar.b();
                        return;
                    case 1:
                        aVar.c();
                        return;
                    case 2:
                        aVar.d();
                        return;
                    default:
                        return;
                }
            }
        }
    }
}

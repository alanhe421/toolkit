package com.sina.weibo.sdk.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.sina.weibo.sdk.a.g;
import com.sina.weibo.sdk.b.b;
import com.sina.weibo.sdk.b.d;
import com.sina.weibo.sdk.b.i;
import com.sina.weibo.sdk.b.j;
import com.sina.weibo.sdk.b.k;
import com.sina.weibo.sdk.c;
import com.sina.weibo.sdk.net.f;
import com.sina.weibo.sdk.web.WebRequestType;
import com.sina.weibo.sdk.web.WeiboSdkWebActivity;
import com.sina.weibo.sdk.web.b.a;
import com.tencent.connect.common.Constants;

public class BaseSsoHandler {
    protected Activity a;
    protected d b;
    protected final int c = 3;
    protected int d = -1;
    protected int e = 3;

    protected enum AuthType {
        ALL,
        SsoOnly,
        WebOnly
    }

    public BaseSsoHandler(Activity activity) {
        this.a = activity;
        b.a(this.a).a(com.sina.weibo.sdk.b.b().a());
    }

    public void a(d dVar) {
        a(32973, dVar, AuthType.SsoOnly);
        g.a(this.a, com.sina.weibo.sdk.b.b().a()).a();
    }

    private void a(int i, d dVar, AuthType authType) {
        a();
        if (dVar == null) {
            throw new RuntimeException("please set auth listener");
        }
        this.b = dVar;
        if (authType != AuthType.WebOnly) {
            Object obj = null;
            if (authType == AuthType.SsoOnly) {
                obj = 1;
            }
            if (c()) {
                a(i);
            } else if (obj != null) {
                this.b.a(new e());
            } else {
                b();
            }
        } else if (dVar != null) {
            b();
        }
    }

    protected void a(int i) {
        try {
            c a = c.a(this.a).a();
            Intent intent = new Intent();
            intent.setClassName(a.a(), a.b());
            intent.putExtras(com.sina.weibo.sdk.b.b().f());
            intent.putExtra("_weibo_command_type", 3);
            intent.putExtra("_weibo_transaction", String.valueOf(System.currentTimeMillis()));
            intent.putExtra("aid", k.b(this.a, com.sina.weibo.sdk.b.b().a()));
            if (i.a(this.a, intent)) {
                a(intent, i);
                try {
                    this.a.startActivityForResult(intent, this.d);
                } catch (Exception e) {
                    if (this.b != null) {
                        this.b.a(new e());
                    }
                    d();
                }
            }
        } catch (Exception e2) {
        }
    }

    protected void a(Intent intent, int i) {
    }

    protected void a() {
        this.d = 32973;
    }

    protected void b() {
        AuthInfo b = com.sina.weibo.sdk.b.b();
        f fVar = new f(b.a());
        fVar.a(Constants.PARAM_CLIENT_ID, b.a());
        fVar.a("redirect_uri", b.b());
        fVar.a(Constants.PARAM_SCOPE, b.c());
        fVar.a("response_type", "code");
        fVar.a(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION, "0031405000");
        fVar.a("luicode", "10000360");
        b a = a.a(this.a);
        if (!(a == null || TextUtils.isEmpty(a.c()))) {
            fVar.a("trans_token", a.c());
            fVar.a("trans_access_token", a.c());
        }
        fVar.a("lfid", "OP_" + b.a());
        Object b2 = k.b(this.a, b.a());
        if (!TextUtils.isEmpty(b2)) {
            fVar.a("aid", b2);
        }
        fVar.a("packagename", b.d());
        fVar.a("key_hash", b.e());
        String str = "https://open.weibo.cn/oauth2/authorize?" + fVar.c();
        if (com.sina.weibo.sdk.b.f.a(this.a)) {
            String str2 = null;
            if (this.b != null) {
                com.sina.weibo.sdk.web.c a2 = com.sina.weibo.sdk.web.c.a();
                str2 = a2.b();
                a2.a(str2, this.b);
            }
            a aVar = new a(b, WebRequestType.AUTH, str2, "微博登录", str, this.a);
            Intent intent = new Intent(this.a, WeiboSdkWebActivity.class);
            Bundle bundle = new Bundle();
            aVar.c(bundle);
            intent.putExtras(bundle);
            this.a.startActivity(intent);
            return;
        }
        j.a(this.a, "Error", "Application requires permission to access the Internet");
    }

    public void a(int i, int i2, Intent intent) {
        if (32973 != i) {
            return;
        }
        if (i2 == -1) {
            if (i.a(this.a, c.a(this.a).a(), intent)) {
                Object c = k.c(intent.getStringExtra("error"));
                Object c2 = k.c(intent.getStringExtra("error_type"));
                Object c3 = k.c(intent.getStringExtra("error_description"));
                d.a("WBAgent", "error: " + c + ", error_type: " + c2 + ", error_description: " + c3);
                if (TextUtils.isEmpty(c) && TextUtils.isEmpty(c2) && TextUtils.isEmpty(c3)) {
                    b a = b.a(intent.getExtras());
                    if (a != null && a.a()) {
                        d.a("WBAgent", "Login Success! " + a.toString());
                        a.a(this.a, a);
                        this.b.a(a);
                        return;
                    }
                    return;
                } else if ("access_denied".equals(c) || "OAuthAccessDeniedException".equals(c)) {
                    d.a("WBAgent", "Login canceled by user.");
                    this.b.a();
                    return;
                } else {
                    d.a("WBAgent", "Login failed: " + c);
                    this.b.a(new e(c2, c3));
                    return;
                }
            }
            this.b.a(new e("your install weibo app is counterfeit", "8001"));
        } else if (i2 != 0) {
        } else {
            if (intent != null) {
                this.b.a();
            } else {
                this.b.a();
            }
        }
    }

    protected boolean c() {
        c a = c.a(this.a).a();
        return a != null && a.d();
    }

    protected void d() {
    }
}

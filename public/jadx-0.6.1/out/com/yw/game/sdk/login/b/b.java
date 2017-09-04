package com.yw.game.sdk.login.b;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.yw.game.sdk.login.BaseGameLoginActivity;
import com.yw.game.sdk.login.a.c;

/* compiled from: UriParser */
class b {

    /* compiled from: UriParser */
    private static class a {
        private static final b a = new b();
    }

    b() {
    }

    public static b a() {
        return a.a;
    }

    void a(BaseGameLoginActivity baseGameLoginActivity, Uri uri, int i, c cVar) {
        try {
            if (TextUtils.equals("game_login", uri.getAuthority())) {
                String queryParameter = uri.getQueryParameter("gameId");
                if (com.yw.game.sdk.login.util.c.a((Context) baseGameLoginActivity)) {
                    c.a().a(baseGameLoginActivity, i, queryParameter, cVar);
                    return;
                }
                com.yw.game.sdk.login.util.c.a((Context) baseGameLoginActivity, "无网络连接，请检查您的网络设置");
                d.a().a(baseGameLoginActivity, null);
            }
        } catch (Exception e) {
            com.yw.game.sdk.login.util.c.a("UriParser", "Uri 解析报错" + e.toString());
            com.yw.game.sdk.login.util.c.a((Context) baseGameLoginActivity, e.toString());
            d.a().a(baseGameLoginActivity, null);
        }
    }
}

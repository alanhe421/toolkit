package com.yw.game.sdk.login.b;

import android.content.Intent;
import android.net.Uri;
import com.yw.game.sdk.login.BaseGameLoginActivity;
import com.yw.game.sdk.login.a.c;

/* compiled from: UriDispatcher */
public class a {
    static boolean a = false;

    /* compiled from: UriDispatcher */
    private static class a {
        private static final a a = new a();
    }

    public static a a() {
        return a.a;
    }

    public void a(boolean z, BaseGameLoginActivity baseGameLoginActivity, Intent intent, c cVar) {
        com.yw.game.sdk.login.util.c.a = z;
        a = true;
        a(baseGameLoginActivity, intent, 1, cVar);
    }

    public void a(BaseGameLoginActivity baseGameLoginActivity, Intent intent, int i, c cVar) {
        Uri data = intent.getData();
        if (data != null) {
            com.yw.game.sdk.login.util.c.a("尝试分发uri");
            b.a().a(baseGameLoginActivity, data, i, cVar);
            return;
        }
        com.yw.game.sdk.login.util.c.a("Uri is null");
        d.a().a(baseGameLoginActivity, null);
    }
}

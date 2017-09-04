package com.yw.game.sdk.login.util.task;

import android.app.Activity;
import com.yw.game.sdk.login.util.c;
import com.yw.game.sdk.login.util.network.Http;
import com.yw.game.sdk.login.util.network.Http.a;
import com.yw.game.sdk.login.util.network.e;
import com.yw.game.sdk.login.util.network.h;

public class GetTokenTask extends Http {
    public GetTokenTask(Activity activity, String str, String str2, int i, int i2, String str3, String str4, h hVar) {
        super(new a().a(c.d(com.yw.game.sdk.login.util.a.b)).a().a("ywguid", str).a("ywkey", str2).a("platformid", String.valueOf(i)).a("logintype", String.valueOf(i2)).a("gameid", str3).a("time", str4).a("sign", c.e(str4)).a(activity.getApplicationContext()).a(new e(hVar)));
    }
}

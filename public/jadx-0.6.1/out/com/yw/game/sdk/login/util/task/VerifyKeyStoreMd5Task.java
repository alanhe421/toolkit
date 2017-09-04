package com.yw.game.sdk.login.util.task;

import android.app.Activity;
import com.yw.game.sdk.login.util.c;
import com.yw.game.sdk.login.util.network.Http;
import com.yw.game.sdk.login.util.network.Http.a;
import com.yw.game.sdk.login.util.network.e;
import com.yw.game.sdk.login.util.network.h;

public class VerifyKeyStoreMd5Task extends Http {
    public VerifyKeyStoreMd5Task(Activity activity, String str, int i, String str2, String str3, String str4, h hVar) {
        super(new a().a(activity.getApplicationContext()).a(com.yw.game.sdk.login.util.a.a).a("gameid", str).a("platformid", String.valueOf(i)).a("packagename", str2).a("time", str4).a("sign", c.c(str4, str3)).a().a(new e(hVar)));
    }
}

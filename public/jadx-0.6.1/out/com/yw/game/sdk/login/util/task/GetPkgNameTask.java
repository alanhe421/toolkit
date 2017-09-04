package com.yw.game.sdk.login.util.task;

import android.app.Activity;
import com.yw.game.sdk.login.util.c;
import com.yw.game.sdk.login.util.network.Http;
import com.yw.game.sdk.login.util.network.Http.a;
import com.yw.game.sdk.login.util.network.e;
import com.yw.game.sdk.login.util.network.h;

public class GetPkgNameTask extends Http {
    public GetPkgNameTask(Activity activity, String str, String str2, h hVar) {
        super(new a().a(com.yw.game.sdk.login.util.a.c).a("gameid", str).a("time", str2).a("sign", c.e(str2)).a().a(activity.getApplicationContext()).a(new e(hVar)));
    }
}

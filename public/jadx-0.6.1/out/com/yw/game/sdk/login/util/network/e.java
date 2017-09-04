package com.yw.game.sdk.login.util.network;

import com.yw.game.sdk.login.util.NetResult;
import com.yw.game.sdk.login.util.c;

/* compiled from: NetResultCallback */
public class e implements g<String> {
    private h a;

    public e(h hVar) {
        this.a = hVar;
    }

    public void a(String str) {
        NetResult b = c.b(str);
        if (b.isSuccessed()) {
            this.a.a(b);
        } else {
            this.a.a(b.getReturnCode(), b.getReturnMessage());
        }
    }

    public void a(Exception exception) {
        this.a.a(exception);
    }
}

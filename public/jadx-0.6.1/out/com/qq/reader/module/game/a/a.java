package com.qq.reader.module.game.a;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.login.b.d;
import com.qq.reader.common.login.b.e;
import com.qq.reader.common.login.b.f;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.module.game.activity.GameLoginActivity;
import com.tencent.midas.api.APMidasPayAPI;
import com.yw.game.sdk.login.a.b;
import org.json.JSONObject;

/* compiled from: GameLoginImp */
public class a implements com.yw.game.sdk.login.a.a {
    GameLoginActivity a;

    public a(GameLoginActivity gameLoginActivity) {
        this.a = gameLoginActivity;
    }

    public void a(b bVar) {
        c.e(APMidasPayAPI.ENV_TEST, "!!!!!!!!!!!111111");
        if (com.qq.reader.common.login.c.b()) {
            b(bVar);
        } else {
            com.qq.reader.common.login.c.a(this.a, 7);
        }
    }

    public static void b(b bVar) {
        Exception e;
        int i;
        try {
            com.qq.reader.common.login.b.a c = com.qq.reader.common.login.c.c();
            if (c != null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("nickName", c.a() == null ? "" : c.a());
                switch (c.d()) {
                    case 1:
                        jSONObject.put("loginType", 1);
                        jSONObject.put("keyType", 0);
                        jSONObject.put("uin", ((d) c).c());
                        jSONObject.put("skey", ((d) c).a(ReaderApplication.getApplicationImp()));
                        break;
                    case 2:
                        jSONObject.put("loginType", 2);
                        jSONObject.put("openId", ((f) c).b(ReaderApplication.getApplicationImp()));
                        jSONObject.put("accessToken", ((f) c).a(ReaderApplication.getApplicationImp()));
                        break;
                    case 50:
                        jSONObject.put("loginType", 3);
                        jSONObject.put("ywGuid", ((e) c).c());
                        jSONObject.put("ywKey", ((e) c).a(ReaderApplication.getApplicationImp()));
                        break;
                }
                try {
                    bVar.a(0, jSONObject);
                } catch (Exception e2) {
                    e = e2;
                    i = 0;
                }
            } else {
                bVar.a(1, null);
            }
        } catch (Exception e3) {
            e = e3;
            i = 1;
            e.printStackTrace();
            bVar.a(i, null);
        }
    }
}

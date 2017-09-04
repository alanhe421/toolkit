package com.qq.reader.common.login.a;

import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.login.b.e;
import com.qq.reader.common.utils.ao;
import com.yuewen.ywlogin.b;
import org.json.JSONObject;

/* compiled from: QidianLoginHelper */
public class d extends a {
    private final int e;
    private final int f;
    private boolean g;

    /* compiled from: QidianLoginHelper */
    private static class a {
        private static final d a = new d();
    }

    public static d g() {
        return a.a;
    }

    private d() {
        this.e = 1450000219;
        this.f = 1;
        this.g = false;
        this.a = ReaderApplication.getApplicationImp().getApplicationContext();
    }

    public com.qq.reader.common.login.b.a e() {
        if (d == null || com.qq.reader.appconfig.a.d.z) {
            com.qq.reader.appconfig.a.d.z = false;
            d = new e();
        }
        return d;
    }

    public int d() {
        return 50;
    }

    public void f() {
        super.f();
    }

    public void h() {
        if (!this.g) {
            b.a(this.a, 1450000219, 1, ao.h(this.a), com.qq.reader.appconfig.a.d.h(this.a), com.qq.reader.appconfig.a.d.h(this.a), ao.u(this.a), false);
            this.g = true;
        }
    }

    public void a(Bundle bundle, com.yuewen.ywlogin.b.b bVar) {
        b.a(bundle.getString("login_qq"), bundle.getString("login_password"), bVar);
    }

    public void b(Bundle bundle, com.yuewen.ywlogin.b.b bVar) {
        b.a(bundle.getString("login_qq"), bundle.getString("login_password"), bundle.getString("login_sessionkey"), bundle.getString("login_imgValidateCode"), bVar);
    }

    public boolean b() {
        if (com.qq.reader.common.login.define.a.h(this.a) != 50 || com.qq.reader.common.login.define.a.d(this.a).length() <= 0) {
            return false;
        }
        return true;
    }

    public void c() {
        super.c();
    }

    public String i() {
        return com.qq.reader.common.login.define.a.k(this.a);
    }

    public void a() {
        super.a();
        com.qq.reader.common.login.define.a.h(this.a, null);
    }

    public void j() {
        h();
        com.yuewen.ywlogin.b.b anonymousClass1 = new com.yuewen.ywlogin.b.b(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(JSONObject jSONObject) {
            }

            public void a(int i, String str) {
            }

            public void a(String str, String str2) {
            }

            public void b(JSONObject jSONObject) {
                if (jSONObject != null && jSONObject.optInt("code", -1) == 0) {
                    JSONObject optJSONObject = jSONObject.optJSONObject("data");
                    if (optJSONObject != null) {
                        String optString = optJSONObject.optString("ywGuid");
                        String optString2 = optJSONObject.optString("ywKey");
                        com.qq.reader.common.login.define.a.a(this.a.a, 50);
                        com.qq.reader.common.login.define.a.b(this.a.a, optString);
                        com.qq.reader.common.login.define.a.a(this.a.a, optString2);
                        com.qq.reader.common.login.define.a.h(this.a.a, optString);
                    }
                }
            }
        };
        String e = com.qq.reader.common.login.define.a.e(this.a);
        b.a(Long.parseLong(e), com.qq.reader.common.login.define.a.d(this.a), anonymousClass1);
    }
}

package com.yw.game.sdk.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.widget.Toast;
import com.yw.game.sdk.login.a.a;
import com.yw.game.sdk.login.a.b;
import com.yw.game.sdk.login.a.c;
import com.yw.game.sdk.login.b.d;

public abstract class BaseGameLoginActivity extends Activity implements c {
    protected boolean a = false;
    protected b b;
    protected ProgressDialog c;

    protected abstract a a();

    public b d() {
        return this.b;
    }

    protected void onCreate(Bundle bundle) {
        getWindow().requestFeature(1);
        super.onCreate(bundle);
        com.yw.game.sdk.login.util.c.a("onCreate");
        if (VERSION.SDK_INT < 23) {
            g();
        } else if (VERSION.SDK_INT < 23 || (com.yw.game.sdk.login.util.c.a((Activity) this) && com.yw.game.sdk.login.util.c.b((Activity) this))) {
            g();
        } else {
            android.support.v4.app.a.a(this, new String[]{"android.permission.READ_PHONE_STATE", "android.permission.ACCESS_NETWORK_STATE"}, 291);
        }
    }

    private void g() {
        Intent intent = getIntent();
        boolean b = b();
        this.a = c();
        if (b) {
            com.yw.game.sdk.login.b.a.a().a(this.a, this, intent, (c) this);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        switch (i) {
            case 291:
                if (iArr.length > 0 && iArr.length == 2 && iArr[0] == 0 && iArr[1] == 0) {
                    g();
                    return;
                }
                com.yw.game.sdk.login.util.c.a("权限被拒绝，无法登录");
                d.a().a(this, null);
                return;
            default:
                return;
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        com.yw.game.sdk.login.util.c.a("onNewIntent");
        setIntent(intent);
        if (VERSION.SDK_INT < 23 || com.yw.game.sdk.login.util.c.a((Activity) this) || com.yw.game.sdk.login.util.c.b((Activity) this)) {
            g();
            return;
        }
        android.support.v4.app.a.a(this, new String[]{"android.permission.READ_PHONE_STATE", "android.permission.ACCESS_NETWORK_STATE"}, 291);
    }

    protected boolean c() {
        return false;
    }

    protected boolean b() {
        return false;
    }

    public void a(b bVar) {
        this.b = bVar;
        a a = a();
        if (a == null) {
            Toast.makeText(this, "ILogin 实例未被注册，登录取消", 0).show();
            d.a().a(this, null);
            return;
        }
        try {
            a.a(this.b);
        } catch (Exception e) {
            e.printStackTrace();
            d.a().a(this, null);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        d.a().a(this, null);
    }

    public void e() {
        a("提示", "校验中，请稍等...");
    }

    public void a(String str, String str2) {
        if (this.c == null) {
            this.c = new ProgressDialog(this);
        }
        this.c.setTitle(str);
        this.c.setMessage(str2);
        this.c.show();
    }

    public void f() {
        if (this.c != null) {
            try {
                this.c.cancel();
            } catch (Exception e) {
                e.printStackTrace();
                com.yw.game.sdk.login.util.c.a("BaseGameLoginActivity", "登录进度条" + e.toString());
            }
        }
    }
}

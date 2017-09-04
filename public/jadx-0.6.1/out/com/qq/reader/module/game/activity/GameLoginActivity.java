package com.qq.reader.module.game.activity;

import android.content.Intent;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.utils.ao;
import com.yuewen.ywlogin.b;
import com.yw.game.sdk.login.BaseGameLoginActivity;
import com.yw.game.sdk.login.a.a;

public class GameLoginActivity extends BaseGameLoginActivity {
    private final int d = 1450000219;
    private final int e = 1;

    public a a() {
        return new com.qq.reader.module.game.a.a(this);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 4098) {
            switch (i2) {
                case -1:
                    com.qq.reader.module.game.a.a.b(d());
                    return;
                case 0:
                    d().a(1, null);
                    return;
                default:
                    return;
            }
        }
    }

    protected boolean b() {
        b.a(ReaderApplication.getApplicationImp(), 1450000219, 1, ao.h(ReaderApplication.getApplicationImp()), d.h(ReaderApplication.getApplicationImp()), d.h(ReaderApplication.getApplicationImp()), ao.u(ReaderApplication.getApplicationImp()), false);
        return true;
    }

    protected boolean c() {
        return false;
    }
}

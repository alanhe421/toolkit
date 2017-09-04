package com.qq.reader.common.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.login.a.b;
import com.qq.reader.common.login.a.c;
import com.qq.reader.common.login.a.d;
import com.qq.reader.common.login.a.e;
import com.qq.reader.common.monitor.j;

public class LoginReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("type", -1);
        if (c.b()) {
            switch (intExtra) {
                case 1:
                    c.a(ReaderApplication.getApplicationImp()).k();
                    break;
                case 2:
                case 3:
                    e.a(ReaderApplication.getApplicationImp()).a(true);
                    break;
                case 10:
                    b.a(ReaderApplication.getApplicationImp()).g();
                    break;
                case 50:
                    d.g().j();
                    break;
            }
            try {
                j.b("LoginReceiver : " + intExtra);
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.a("TESTLOGIN", e.toString());
            }
        }
    }
}

package com.qq.reader.plugin.tts;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.plugin.audiobook.core.h;

/* compiled from: TtsSysControlHelper */
public class q {
    private h a;
    private boolean b = false;
    private PhoneStateListener c = new PhoneStateListener(this) {
        final /* synthetic */ q a;

        {
            this.a = r1;
        }

        public void onCallStateChanged(int i, String str) {
            switch (i) {
                case 0:
                    if (this.a.b) {
                        n.f().s();
                        this.a.b = false;
                        return;
                    }
                    return;
                case 1:
                    if (n.f().i()) {
                        n.f().r();
                        this.a.b = true;
                        return;
                    }
                    return;
                case 2:
                    if (n.f().i()) {
                        n.f().r();
                        this.a.b = true;
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    public void a() {
        try {
            ((TelephonyManager) ReaderApplication.getApplicationImp().getSystemService("phone")).listen(this.c, 32);
        } catch (Exception e) {
            if (e != null) {
                c.a("permission", e.toString());
            }
        }
        c();
        d();
    }

    public void b() {
        e();
    }

    private void c() {
        this.a = new h(ReaderApplication.getApplicationImp());
    }

    private void d() {
        if (this.a != null) {
            this.a.c();
        }
    }

    private void e() {
        if (this.a != null) {
            this.a.d();
        }
    }
}

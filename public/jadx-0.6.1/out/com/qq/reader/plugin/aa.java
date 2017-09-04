package com.qq.reader.plugin;

import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;

/* compiled from: SkinSilentUpdateCallBack */
public class aa implements g {
    private boolean a = false;
    private boolean b = false;

    public aa(boolean z, boolean z2) {
        this.a = z;
        this.b = z2;
    }

    public void a(String str, boolean z) {
        x c = w.b().c(str);
        if (c != null && c.d() == 4 && this.a) {
            w.b().e(str);
            d.C(ReaderApplication.getApplicationImp(), str);
            if (this.b) {
                d.j(ReaderApplication.getApplicationImp(), 7);
            }
        }
    }

    public void a(String str, String str2) {
    }

    public void a(l lVar, String str) {
    }

    public void a(l lVar) {
    }

    public void a(l lVar, Bundle bundle) {
    }
}

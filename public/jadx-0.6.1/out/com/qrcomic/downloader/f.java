package com.qrcomic.downloader;

import android.content.SharedPreferences.Editor;
import com.qrcomic.a.h;
import com.qrcomic.manager.b;

/* compiled from: QRComicNotification */
public class f {
    public boolean a = false;
    public int b = 0;
    public int c = 0;

    public boolean equals(Object obj) {
        if (obj instanceof f) {
            f fVar = (f) obj;
            if (fVar.a == this.a && fVar.c == this.b && fVar.c == this.c) {
                return true;
            }
        }
        return false;
    }

    public void a(String str) {
        this.b++;
    }

    public void b(String str) {
        this.c++;
    }

    public static void a(boolean z, int i, int i2) {
        h b = b.a().b();
        Editor edit = b.b().getSharedPreferences("VipComicRedDotInfo", 0).edit();
        String a = b.a();
        edit.putBoolean("needShowRedDot_" + a, z);
        edit.putInt("currentDownloadCount_" + a, i);
        edit.putInt("currentUnfinishedCount_" + a, i2);
        edit.commit();
    }
}

package com.qq.reader.common.exception;

import android.content.Context;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.readertask.g;

/* compiled from: DBErrorHandler */
public class a {
    public static final String a = (ReaderApplication.getApplicationImp().getExternalFilesDir(null) + "/" + com.qq.reader.common.c.a.bf + "backup.b");
    private static a b = null;
    private Context c = ReaderApplication.getApplicationImp();

    private a() {
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                b = new a();
            }
            aVar = b;
        }
        return aVar;
    }

    public void a(int i) {
        try {
            d.bi(this.c.getApplicationContext());
            g.a().a(new DBErrorHandler$1(this, i));
        } catch (Exception e) {
        }
    }

    public void b(int i) {
        try {
            if (d.bk(this.c.getApplicationContext()) >= 3) {
                g.a().a(new DBErrorHandler$2(this));
                return;
            }
            d.bj(this.c.getApplicationContext());
            g.a().a(new DBErrorHandler$3(this, i));
        } catch (Exception e) {
        }
    }

    private String c(int i) {
        return a;
    }

    private String d(int i) {
        return com.qq.reader.common.c.a.bg;
    }
}

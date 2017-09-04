package com.qq.reader.module.bookstore.qnative;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.b;
import com.qq.reader.common.imageloader.a.a.b.a;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/* compiled from: PageDataLoader */
public class d extends b {
    private static volatile d a = null;
    private com.qq.reader.common.imageloader.a.a.b b;

    private d() {
        c();
    }

    public static d b() {
        if (a == null) {
            synchronized (d.class) {
                if (a == null) {
                    a = new d();
                }
            }
        }
        return a;
    }

    public boolean a(Context context, com.qq.reader.module.bookstore.qnative.page.b bVar, Handler handler, boolean z) {
        if (bVar == null) {
            return false;
        }
        if (bVar.p() == 1002 && !bVar.b()) {
            return true;
        }
        ReaderTask f = bVar.i().f();
        f.setIsUseCache(z);
        f.setHandler(handler);
        g.a().a(f);
        return false;
    }

    public boolean a(Context context, com.qq.reader.module.bookstore.qnative.page.b bVar, Handler handler, boolean z, int i) {
        if (bVar == null) {
            return false;
        }
        if (bVar.p() == 1002 && !bVar.b()) {
            return true;
        }
        ReaderTask f = bVar.i().f();
        f.setIsUseCache(z);
        f.setHandler(handler);
        f.setPriority(i);
        g.a().a(f);
        return false;
    }

    public void a(com.qq.reader.module.bookstore.qnative.page.b bVar) {
        if (bVar != null) {
            ReaderShortTask f = bVar.f();
            if (f != null) {
                f.setHandler(null);
            }
        }
    }

    private void c() {
        try {
            this.b = a.a(ReaderApplication.getApplicationImp(), a.a(), 52428800, 0, new File(com.qq.reader.common.c.a.ae).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void a(String str, ByteArrayInputStream byteArrayInputStream, com.qq.reader.common.imageloader.d.a.a aVar) {
        try {
            if (!TextUtils.isEmpty(str) && str.contains("common/recmybooklist?")) {
                str = c(str);
            }
            this.b.a(str, byteArrayInputStream, aVar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File a(String str) {
        if (!TextUtils.isEmpty(str) && str.contains("common/recmybooklist?")) {
            str = c(str);
        }
        return this.b.a(str);
    }

    private String c(String str) {
        String[] split = str.split("&");
        String str2 = null;
        int i = 0;
        while (i < split.length) {
            if (!TextUtils.isEmpty(split[i]) && split[i].contains("bids=")) {
                str2 = split[i];
            }
            i++;
        }
        if (TextUtils.isEmpty(str2)) {
            return str2;
        }
        return str.replace(str2, "");
    }

    public boolean b(String str) {
        return this.b.b(str);
    }

    public void a() {
        synchronized (d.class) {
            a = null;
        }
    }
}

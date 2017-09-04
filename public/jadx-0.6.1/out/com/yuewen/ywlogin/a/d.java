package com.yuewen.ywlogin.a;

import android.content.ContentValues;
import com.yuewen.ywlogin.a.h.b;
import java.io.IOException;
import java.util.Map.Entry;
import okhttp3.p.a;
import okhttp3.w;

public class d implements b, Runnable {
    private String a;
    private a b;
    private ContentValues c;

    public void a() {
    }

    public void run() {
        if (this.b != null) {
            h.c.post(new e(this));
        }
        if (this.b != null) {
            this.b.a();
        }
        j a = a(this.a, this.c);
        if (this.b != null) {
            if (a.a()) {
                this.b.b(a);
            }
            h.c.post(new f(this, a));
        }
    }

    public j a(String str, ContentValues contentValues) {
        a aVar = new a();
        if (contentValues != null) {
            for (Entry entry : contentValues.valueSet()) {
                aVar.a((String) entry.getKey(), entry.getValue().toString());
            }
        }
        w.a aVar2 = new w.a();
        aVar2.a(str);
        aVar2.a(aVar.a());
        h.a(aVar2);
        try {
            return c.a(h.b.a(aVar2.b()).a());
        } catch (IOException e) {
            e.printStackTrace();
            return c.a(e);
        } catch (Exception e2) {
            e2.printStackTrace();
            return new j(false, -20001);
        }
    }
}

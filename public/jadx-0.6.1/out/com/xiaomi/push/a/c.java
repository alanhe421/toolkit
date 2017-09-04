package com.xiaomi.push.a;

import java.io.File;
import java.util.Date;

class c extends b$b {
    File a;
    final /* synthetic */ int d;
    final /* synthetic */ Date e;
    final /* synthetic */ Date f;
    final /* synthetic */ String g;
    final /* synthetic */ String h;
    final /* synthetic */ boolean i;
    final /* synthetic */ b j;

    c(b bVar, int i, Date date, Date date2, String str, String str2, boolean z) {
        this.j = bVar;
        this.d = i;
        this.e = date;
        this.f = date2;
        this.g = str;
        this.h = str2;
        this.i = z;
        super(bVar);
    }

    public void b() {
        if (com.xiaomi.channel.commonutils.a.c.d()) {
            try {
                File file = new File(b.a(this.j).getExternalFilesDir(null) + "/.logcache");
                file.mkdirs();
                if (file.isDirectory()) {
                    a aVar = new a();
                    aVar.a(this.d);
                    this.a = aVar.a(b.a(this.j), this.e, this.f, file);
                }
            } catch (NullPointerException e) {
            }
        }
    }

    public void c() {
        if (this.a != null && this.a.exists()) {
            b.b(this.j).add(new b$c(this.j, this.g, this.h, this.a, this.i));
        }
        b.a(this.j, 0);
    }
}

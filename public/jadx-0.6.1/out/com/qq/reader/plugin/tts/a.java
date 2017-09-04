package com.qq.reader.plugin.tts;

import com.qq.reader.plugin.tts.model.c;
import com.qq.reader.readengine.kernel.b;
import com.qq.reader.readengine.kernel.d;
import com.qq.reader.readengine.kernel.g;
import format.epub.view.s;
import format.epub.view.y;

/* compiled from: EpubComposer */
public class a extends m {
    private int d = 0;

    public a(d dVar) {
        super(dVar);
    }

    public boolean a() {
        if (this.b != null) {
            g c = this.a.c();
            int c2 = c.c();
            s a = s.a(((com.qq.reader.readengine.kernel.a.a) this.c).a(), c.b());
            int e = this.a.e();
            int i = c2;
            s sVar = a;
            while (true) {
                int f = sVar.f();
                StringBuffer stringBuffer = new StringBuffer();
                for (int i2 = i; i2 < f; i2++) {
                    format.epub.view.g c3 = sVar.c(i2);
                    if (c3 instanceof y) {
                        stringBuffer.append(((y) c3).c());
                    }
                }
                c = new g();
                c.a(((((long) i) << 8) | (((long) sVar.a) << 32)) | 0);
                g gVar = new g();
                gVar.a((((long) sVar.a) + 1) << 32);
                this.b.a(new c(stringBuffer.toString(), c, gVar));
                i = 0;
                this.d = sVar.a;
                a = sVar.h();
                if (a == null || a.a > e) {
                    break;
                }
                sVar = a;
            }
        }
        return true;
    }

    public boolean b() {
        if (this.b != null) {
            s a = s.a(((com.qq.reader.readengine.kernel.a.a) this.c).a(), this.d + 1);
            int e = this.a.e();
            s sVar = a;
            while (sVar != null && sVar.a <= e) {
                int f = sVar.f();
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < f; i++) {
                    format.epub.view.g c = sVar.c(i);
                    if (c instanceof y) {
                        stringBuffer.append(((y) c).c());
                    }
                }
                g gVar = new g();
                gVar.a(((long) sVar.a) << 32);
                g gVar2 = new g();
                gVar2.a((((long) sVar.a) + 1) << 32);
                this.b.a(new c(stringBuffer.toString(), gVar, gVar2));
                this.d = sVar.a;
                sVar = sVar.h();
            }
        }
        return true;
    }

    public void a(b bVar) {
        this.b = new j();
        this.b.a(1);
        this.b.a(bVar);
        this.b.start();
    }
}

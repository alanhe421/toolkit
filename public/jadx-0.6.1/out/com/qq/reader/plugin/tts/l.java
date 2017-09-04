package com.qq.reader.plugin.tts;

import com.qq.reader.readengine.fileparse.f;
import com.qq.reader.readengine.fileparse.h;
import com.qq.reader.readengine.kernel.b;
import com.qq.reader.readengine.kernel.d;

/* compiled from: TextComposer */
public class l extends m {
    public l(d dVar) {
        super(dVar);
    }

    public boolean a() {
        if (!(this.b == null || this.c == null)) {
            this.b.a(((com.qq.reader.readengine.fileparse.d) this.c).c(this.a.c()));
        }
        return true;
    }

    public boolean b() {
        if (!(this.b == null || this.c == null)) {
            this.b.a(((com.qq.reader.readengine.fileparse.d) this.c).r());
        }
        return true;
    }

    public void a(b bVar) {
        this.b = new j();
        if (this.c instanceof h) {
            this.b.a(2);
        } else if (this.c instanceof f) {
            this.b.a(4);
        } else {
            this.b.a(3);
        }
        this.b.a(bVar);
        this.b.start();
    }
}

package com.qq.reader.plugin.tts;

import com.qq.reader.readengine.fileparse.e;
import com.qq.reader.readengine.kernel.b;
import com.qq.reader.readengine.kernel.d;

/* compiled from: TtsBuffComposer */
public abstract class m {
    protected d a;
    protected c b;
    protected e c;

    public abstract void a(b bVar);

    public abstract boolean a();

    public abstract boolean b();

    public m(d dVar) {
        this.a = dVar;
    }

    public void a(e eVar) {
        this.c = eVar;
    }

    public void a(int i) {
        if (this.b != null) {
            this.b.b(i);
        }
    }

    public c c() {
        return this.b;
    }

    public void d() {
        if (this.b != null) {
            this.b.c();
            this.b = null;
        }
    }
}

package com.qq.reader.plugin.tts.a;

import com.qq.reader.plugin.tts.h;
import com.qq.reader.plugin.tts.model.e;

/* compiled from: TtsState */
public abstract class k {
    protected e a;
    private int b;

    protected abstract k a(h hVar, int i);

    public abstract void a(h hVar);

    public k(int i) {
        this.b = i;
    }

    public int b() {
        return this.b;
    }

    public e c() {
        return this.a;
    }

    public void a(e eVar) {
        this.a = eVar;
    }

    public k b(h hVar, int i) {
        return a(hVar, i);
    }
}

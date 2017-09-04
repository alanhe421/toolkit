package com.qq.reader.plugin.tts.a;

import com.qq.reader.plugin.tts.h;

/* compiled from: TtsBufferDataState */
public class c extends k {
    public c() {
        super(7);
    }

    public void a(h hVar) {
        hVar.getDataSource().d();
    }

    protected k a(h hVar, int i) {
        switch (i) {
            case 2:
                return new h();
            case 3:
                return new g();
            case 4:
                return new j();
            case 5:
                return new l();
            default:
                return new d();
        }
    }
}

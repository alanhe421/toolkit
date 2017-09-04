package com.qq.reader.plugin.tts.a;

import com.qq.reader.plugin.tts.h;

/* compiled from: TtsInitEngineState */
public class f extends k implements a {
    public f() {
        super(9);
    }

    public void a(h hVar) {
        hVar.initEngine(this);
    }

    protected k a(h hVar, int i) {
        switch (i) {
            case 2:
                if (hVar.isDataSatisfied()) {
                    return new h();
                }
                return new i();
            case 5:
                return new l();
            default:
                return new d();
        }
    }

    public void a() {
    }
}

package com.qq.reader.plugin.tts.a;

import com.qq.reader.common.monitor.m;
import com.qq.reader.plugin.tts.h;

/* compiled from: TtsPauseState */
public class g extends k {
    public g() {
        super(3);
    }

    protected k a(h hVar, int i) {
        switch (i) {
            case 2:
                return new h();
            case 3:
                return this;
            case 4:
                return new j();
            case 5:
                return new l();
            default:
                return new d();
        }
    }

    public void a(h hVar) {
        hVar.pause();
        if (hVar.getDataSource().e()) {
            m.b();
        } else {
            m.d();
        }
    }
}

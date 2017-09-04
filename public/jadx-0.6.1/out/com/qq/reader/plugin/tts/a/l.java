package com.qq.reader.plugin.tts.a;

import com.qq.reader.common.monitor.m;
import com.qq.reader.plugin.tts.h;

/* compiled from: TtsStopState */
public class l extends k {
    public l() {
        super(5);
    }

    protected k a(h hVar, int i) {
        switch (i) {
            case 1:
                return new e();
            case 2:
                return new h();
            case 3:
                return new g();
            case 4:
                return new h();
            case 5:
                return this;
            default:
                return new d();
        }
    }

    public void a(h hVar) {
        hVar.stop();
        if (hVar.getDataSource().e()) {
            m.b();
        } else {
            m.d();
        }
    }
}

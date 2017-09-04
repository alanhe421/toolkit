package com.qq.reader.plugin.tts.a;

import com.qq.reader.common.monitor.m;
import com.qq.reader.plugin.tts.h;

/* compiled from: TtsResumeState */
public class j extends k {
    public j() {
        super(4);
    }

    protected k a(h hVar, int i) {
        switch (i) {
            case 2:
                return new h();
            case 3:
                return new g();
            case 4:
                return this;
            case 5:
                return new l();
            case 7:
                return new c();
            default:
                return new d();
        }
    }

    public void a(h hVar) {
        if (hVar.getDataSource().e()) {
            m.a();
        } else {
            m.c();
        }
        hVar.resume();
    }
}

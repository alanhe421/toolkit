package com.qq.reader.plugin.tts.a;

import com.qq.reader.common.monitor.m;

/* compiled from: TtsPlayState */
public class h extends k {
    public h() {
        super(2);
    }

    protected k a(com.qq.reader.plugin.tts.h hVar, int i) {
        switch (i) {
            case 2:
            case 4:
                return this;
            case 3:
                return new g();
            case 5:
                return new l();
            case 7:
                return new c();
            default:
                return new d();
        }
    }

    public void a(com.qq.reader.plugin.tts.h hVar) {
        if (hVar.getDataSource().e()) {
            m.a();
        } else {
            m.c();
        }
        hVar.play();
    }
}

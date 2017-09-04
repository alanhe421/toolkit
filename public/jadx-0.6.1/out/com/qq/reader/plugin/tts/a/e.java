package com.qq.reader.plugin.tts.a;

import com.qq.reader.plugin.tts.h;

/* compiled from: TtsIdleState */
public class e extends k {
    public e() {
        super(1);
    }

    public void a(h hVar) {
    }

    public k a(h hVar, int i) {
        switch (i) {
            case 2:
                if (!hVar.isApkInstalled()) {
                    return new b();
                }
                if (!hVar.isEngineInited()) {
                    return new f();
                }
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
}

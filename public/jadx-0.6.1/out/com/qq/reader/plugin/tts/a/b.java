package com.qq.reader.plugin.tts.a;

import com.qq.reader.plugin.tts.h;

/* compiled from: TtsApkInstallState */
public class b extends k {
    public b() {
        super(8);
    }

    public void a(h hVar) {
        hVar.installApk();
    }

    protected k a(h hVar, int i) {
        switch (i) {
            case 5:
                return new l();
            default:
                return new d();
        }
    }
}

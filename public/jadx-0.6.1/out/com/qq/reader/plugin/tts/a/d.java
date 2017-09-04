package com.qq.reader.plugin.tts.a;

import com.qq.reader.plugin.tts.h;

/* compiled from: TtsErrorState */
public class d extends k {
    private int b = -1;

    public d() {
        super(0);
    }

    public void a(h hVar) {
        if (hVar != null && hVar.getListener() != null) {
            hVar.getListener().a(this.b);
        }
    }

    protected k a(h hVar, int i) {
        switch (i) {
            case 1:
                return this;
            default:
                return this;
        }
    }
}

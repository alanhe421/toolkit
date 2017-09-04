package com.qq.reader.plugin.tts;

import android.media.AudioManager.OnAudioFocusChangeListener;

/* compiled from: TtsFacade */
class n$1 implements OnAudioFocusChangeListener {
    final /* synthetic */ n a;

    n$1(n nVar) {
        this.a = nVar;
    }

    public void onAudioFocusChange(int i) {
        switch (i) {
            case -1:
                try {
                    this.a.c();
                    return;
                } catch (Exception e) {
                    return;
                }
            case 1:
                this.a.p();
                return;
            default:
                return;
        }
    }
}

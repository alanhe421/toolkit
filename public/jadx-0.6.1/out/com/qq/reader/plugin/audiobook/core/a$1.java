package com.qq.reader.plugin.audiobook.core;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;

/* compiled from: APlayer */
class a$1 implements OnErrorListener {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        switch (i) {
            case 100:
                this.a.a(2, 0, this.a.b);
                return true;
            default:
                return false;
        }
    }
}

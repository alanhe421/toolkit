package com.qq.reader.plugin.audiobook.core;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

/* compiled from: APlayer */
class a$2 implements OnCompletionListener {
    final /* synthetic */ a a;

    a$2(a aVar) {
        this.a = aVar;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        this.a.a(mediaPlayer);
    }
}

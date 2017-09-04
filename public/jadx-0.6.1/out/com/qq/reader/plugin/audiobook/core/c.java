package com.qq.reader.plugin.audiobook.core;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;

/* compiled from: AudioFocusHelper */
public class c {
    private f a;
    private boolean b;
    private AudioManager c;
    private OnAudioFocusChangeListener d = new 1(this);

    public c(Context context, f fVar) {
        this.c = (AudioManager) context.getSystemService("audio");
        this.a = fVar;
    }

    public boolean a() {
        try {
            if (1 == this.c.requestAudioFocus(this.d, 3, 1)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean b() {
        try {
            if (1 == this.c.abandonAudioFocus(this.d)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}

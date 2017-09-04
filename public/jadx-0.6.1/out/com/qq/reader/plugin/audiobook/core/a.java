package com.qq.reader.plugin.audiobook.core;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;

/* compiled from: APlayer */
public abstract class a extends Thread {
    protected MediaPlayer a;
    protected SongInfo b;
    protected boolean c;
    protected Context d;
    public final int e;
    protected int f = 3;
    private final k g;
    private OnErrorListener h = new 1(this);
    private OnCompletionListener i = new 2(this);

    protected abstract void a(MediaPlayer mediaPlayer);

    protected abstract long b(int i);

    protected abstract boolean b();

    protected abstract void c();

    protected abstract void d();

    protected abstract void e();

    protected abstract void f();

    protected abstract void g();

    protected abstract void h();

    protected abstract long i();

    protected abstract long j();

    protected abstract long k();

    protected abstract long l();

    protected abstract boolean m();

    protected abstract int n();

    public a(Context context, SongInfo songInfo, k kVar, int i) {
        this.b = songInfo;
        this.g = kVar;
        this.d = context;
        this.e = i;
        this.a = new MediaPlayer();
        this.a.setWakeMode(context, 1);
        this.a.setOnErrorListener(this.h);
        this.a.setOnCompletionListener(this.i);
    }

    protected final void a(int i, int i2, Object obj) {
        if (this.g != null) {
            this.g.a(i, i2, obj);
        }
    }

    protected void a(float f) {
        try {
            this.a.setVolume(f, f);
        } catch (Exception e) {
        }
    }

    protected void a(int i) {
        this.f = i;
    }

    protected int a() {
        return this.f;
    }
}

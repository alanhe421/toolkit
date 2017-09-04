package com.qq.reader.plugin.audiobook.core;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.download.audio.RequestMsg;
import com.qq.reader.cservice.download.audio.SplitDownloadTask;
import com.qq.reader.cservice.download.audio.d;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

/* compiled from: OnlinePlayer */
public class i extends a {
    private static final Object z = new Object();
    private String g;
    private boolean h = false;
    private boolean i = false;
    private int j = 0;
    private File k;
    private RandomAccessFile l;
    private boolean m;
    private int n;
    private int o;
    private long p;
    private long q;
    private int r = 0;
    private long s;
    private boolean t;
    private SplitDownloadTask u;
    private long v;
    private long w;
    private boolean x = false;
    private d y = new 1(this);

    public i(Context context, SongInfo songInfo, String str, k kVar) {
        super(context, songInfo, kVar, 1);
        if (str == null) {
            this.g = songInfo.c();
        } else {
            this.g = str;
        }
        this.i = true;
    }

    protected long k() {
        return this.p;
    }

    public boolean m() {
        return this.f == 0;
    }

    protected long l() {
        return this.q;
    }

    protected long j() {
        if (this.a != null) {
            return (long) this.a.getCurrentPosition();
        }
        return 0;
    }

    protected long i() {
        return (long) this.o;
    }

    protected boolean b() {
        this.m = false;
        this.f = 4;
        this.k = new File(p());
        if (this.k.exists()) {
            this.k.delete();
        }
        try {
            this.k.createNewFile();
            a(this.g);
            return true;
        } catch (IOException e) {
            return false;
        } catch (Exception e2) {
            Log.e("Error", e2.getMessage());
            return false;
        }
    }

    private void a(String str) {
        if (a.d.aD(this.d)) {
            str = e.cp + "adid=" + this.b.e() + "&acids=" + this.b.a.getChapterId();
        }
        this.u = new SplitDownloadTask(p(), new RequestMsg(str), this.y, this.b.c, String.valueOf(this.b.e()), String.valueOf(this.b.g()), true);
        g.a().a(this.u);
        start();
        com.qq.reader.common.monitor.i.a("event_C235", null, this.d);
    }

    public void run() {
        synchronized (z) {
            while (this.i) {
                long j;
                switch (this.f) {
                    case 0:
                        this.r = 100;
                        a(false);
                        if (!this.m && this.q > 0) {
                            j = this.p;
                            if (this.n + 1000 >= ((int) ((((long) (this.o < 0 ? 0 : this.o)) * j) / this.q))) {
                                try {
                                    this.a.pause();
                                } catch (Exception e) {
                                    this.i = false;
                                    h();
                                    c.e("OnlinePlayer", e.getMessage());
                                }
                                try {
                                    this.f = 5;
                                    this.s = j;
                                    this.t = true;
                                    a(4, 0, null);
                                } catch (Exception e2) {
                                    c.e("OnlinePlayer", e2.getMessage());
                                    break;
                                }
                            }
                        }
                        try {
                            z.wait(100);
                            break;
                        } catch (InterruptedException e3) {
                            c.e("OnlinePlayer", e3.getMessage());
                            break;
                        }
                    case 2:
                        this.i = false;
                        break;
                    case 4:
                    case 5:
                        this.c = false;
                        try {
                            Object obj;
                            if (this.m) {
                                a(this.p, this.p);
                                this.x = false;
                                obj = 1;
                            } else {
                                j = this.p - this.s;
                                a(j, 102400);
                                if (this.x) {
                                    obj = null;
                                } else if (j >= 102400) {
                                    int i = 1;
                                } else {
                                    obj = null;
                                }
                            }
                            if (obj != null) {
                                this.s = this.p;
                                this.t = false;
                                this.r = 100;
                                o();
                                if (!this.x) {
                                    c();
                                }
                            }
                        } catch (Exception e22) {
                            c.e("OnlinePlayer", e22.getMessage());
                        }
                        try {
                            z.wait(100);
                            break;
                        } catch (Exception e222) {
                            c.e("OnlinePlayer", e222.getMessage());
                            break;
                        }
                    default:
                        try {
                            z.wait(500);
                            break;
                        } catch (InterruptedException e32) {
                            c.e("OnlinePlayer", e32.getMessage());
                            break;
                        }
                }
            }
        }
    }

    private void o() {
        try {
            if (this.a != null) {
                this.a.reset();
                if (this.l == null) {
                    this.l = new RandomAccessFile(this.k, "rw");
                }
                this.l.setLength(this.q);
                try {
                    this.a.setDataSource(this.l.getFD());
                    this.a.setAudioStreamType(3);
                    this.a.prepare();
                    this.a.seekTo(this.n);
                    this.c = true;
                    int duration = this.a.getDuration();
                    if (this.v <= 0 || duration <= 0 || ((double) ((1.0f * ((float) Math.abs(((long) duration) - (this.v * 1000)))) / ((float) duration))) <= 0.1d) {
                        this.o = duration;
                        this.x = false;
                    } else {
                        if (!this.x) {
                            Map hashMap = new HashMap();
                            hashMap.put("device", Build.MODEL);
                            com.qq.reader.common.monitor.i.a("event_B390", hashMap, ReaderApplication.getApplicationImp());
                        }
                        this.x = true;
                    }
                    this.h = true;
                } catch (Exception e) {
                    this.f = 2;
                }
            }
        } catch (Exception e2) {
        }
    }

    private String p() {
        return com.qq.reader.common.c.a.ak;
    }

    private synchronized void a(boolean z) {
        int i = this.n;
        try {
            int currentPosition = this.a.getCurrentPosition();
            if (z || currentPosition != 0 || this.n == 0) {
                this.n = currentPosition;
            }
        } catch (Exception e) {
            this.n = i;
        }
    }

    private void a(long j, long j2) {
        int i = 1000;
        if (j < 0) {
            j = 0;
        }
        int i2 = j2 == 0 ? 0 : (int) ((1000 * j) / j2);
        if (i2 <= 1000) {
            i = i2;
        }
        this.r = i / 10;
    }

    protected void d() {
        if (this.a != null && this.c) {
            this.a.pause();
            a(1);
            a(4, 0, null);
        }
    }

    protected void e() {
        if (this.a != null) {
            this.f = 6;
        }
    }

    protected void c() {
        if (this.a == null || !this.h) {
            c.e("OnlinePlayer", "prepare not ready");
        } else if (ao.g(this.d) && b.a == 0) {
            this.d.sendBroadcast(new Intent(com.qq.reader.common.c.a.cJ));
            h();
            this.i = false;
        } else {
            if (this.b != null) {
                ao.a(2, this.b.e(), true, this.b.i());
            }
            this.a.start();
            c.e("OnlinePlayer", "startplay");
            this.f = 0;
            a(4, 0, null);
        }
    }

    protected void g() {
        if (this.a != null) {
            this.f = 0;
        }
    }

    protected void f() {
        if (this.a != null && this.h) {
            if (!(this.u.isRunning() || this.m)) {
                this.u = new SplitDownloadTask(p(), new RequestMsg(this.g), this.y);
                g.a().a(this.u);
            }
            this.a.start();
            this.f = 0;
        }
    }

    protected void h() {
        this.f = 2;
        a(4, 0, null);
        if (this.b != null) {
            ao.a(2, this.b.e(), false, this.b.i());
        }
        if (this.c) {
            this.a.release();
            this.a = null;
            this.c = false;
        }
        if (this.u != null) {
            this.u.terminate();
        }
        if (this.l != null) {
            try {
                this.l.close();
            } catch (IOException e) {
            }
            this.l = null;
        }
        this.o = 0;
        this.n = 0;
        this.p = 0;
        this.q = 0;
    }

    protected long b(int i) {
        int i2 = 0;
        if (i >= 0 && this.q > 0) {
            boolean z = this.f == 5 && this.n > 0;
            if (this.f == 0 || this.f == 6 || this.f == 1 || z) {
                long j = this.p;
                int i3 = (int) ((((long) this.o) * j) / this.q);
                if (i + 1000 < i3 || this.m) {
                    this.a.seekTo(i);
                    a(true);
                    this.c = true;
                    this.s = j;
                    i2 = i;
                } else {
                    i = i3 - 1000;
                    this.a.seekTo(i);
                    a(false);
                    try {
                        this.a.pause();
                        this.c = false;
                    } catch (Exception e) {
                        this.i = false;
                        h();
                    }
                    this.f = 5;
                    this.s = j;
                    this.t = true;
                    a(4, 0, null);
                    i2 = i;
                }
            }
        }
        return (long) i2;
    }

    protected int n() {
        return this.j;
    }

    protected void a(MediaPlayer mediaPlayer) {
        synchronized (z) {
            c.e("OnlinePlayer", "call onCompletionLogic:" + this.n + "/" + this.o);
            if (this.o > 0 && this.n + 1000 >= this.o) {
                h();
                a(1, 0, null);
            } else if (this.f != 2) {
                this.f = 5;
            }
        }
    }
}

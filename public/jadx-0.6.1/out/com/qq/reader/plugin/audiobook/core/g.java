package com.qq.reader.plugin.audiobook.core;

import android.content.Context;
import android.media.MediaPlayer;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.a.e;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: LocalPlayer */
public class g extends a {
    private final String g;

    public g(Context context, SongInfo songInfo, String str, k kVar) {
        super(context, songInfo, kVar, 1);
        if (str == null) {
            this.g = songInfo.c();
        } else {
            this.g = str;
        }
    }

    protected long k() {
        return 100;
    }

    public boolean m() {
        return this.f == 0;
    }

    protected long l() {
        return 100;
    }

    protected long j() {
        if (this.a != null) {
            return (long) this.a.getCurrentPosition();
        }
        return 0;
    }

    protected long i() {
        if (this.a != null) {
            return (long) this.a.getDuration();
        }
        return 0;
    }

    protected boolean b() {
        try {
            if (this.a != null) {
                this.a.reset();
                File file = new File(a.ak);
                if (file.exists()) {
                    file.delete();
                }
                try {
                    file.createNewFile();
                    if (this.b.c) {
                        a(this.g);
                        this.a.setDataSource(a.ak);
                    } else {
                        this.a.setDataSource(this.g);
                    }
                    this.a.setAudioStreamType(3);
                    this.a.prepare();
                    this.c = true;
                } catch (IOException e) {
                    return false;
                }
            }
        } catch (Exception e2) {
            this.c = false;
            e2.printStackTrace();
        }
        i.a("event_C236", null, this.d);
        return this.c;
    }

    protected void d() {
        if (this.a != null && this.c) {
            this.a.pause();
            a(1);
        }
    }

    protected void e() {
        if (this.a != null) {
            this.f = 6;
        }
    }

    protected void c() {
        if (this.a != null) {
            this.a.start();
            this.f = 0;
        }
    }

    protected void g() {
        if (this.a != null) {
            this.f = 0;
        }
    }

    protected void f() {
        if (this.a != null) {
            this.a.start();
            this.f = 0;
        }
    }

    protected void h() {
        this.f = 2;
        if (this.c) {
            this.a.release();
            this.a = null;
            this.c = false;
        }
        this.d = null;
    }

    protected long b(int i) {
        if (this.a == null) {
            return 0;
        }
        this.a.seekTo(i);
        return (long) i;
    }

    protected int n() {
        return 100;
    }

    protected void a(MediaPlayer mediaPlayer) {
        if (this.b.d() >= 0) {
            a(1, 0, null);
        }
    }

    public boolean a(String str) {
        try {
            byte[] bArr = new byte[51200];
            FileInputStream fileInputStream = new FileInputStream(str);
            FileOutputStream fileOutputStream = new FileOutputStream(a.ak);
            int i = 0;
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    byte[] a = e.a(bArr, 0, read, e.a(String.valueOf(this.b.e()), String.valueOf(this.b.g())));
                    fileOutputStream.write(a, 0, a.length);
                    i += a.length;
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

package com.qq.reader.plugin.audiobook.core;

/* compiled from: PlayList */
public class j {
    private int a = 0;
    private SongInfo[] b = null;
    private int c = -1;
    private int d = -1;
    private int[] e = null;
    private boolean f = false;
    private boolean g = true;
    private boolean h = false;
    private k i = null;

    public void a(k kVar) {
        this.i = kVar;
    }

    private void d(int i) {
        if (this.i != null) {
            this.i.a(i, 0, null);
        }
    }

    public void a(int i) {
        if (i >= 0 && i < this.a) {
            if (this.f) {
                for (int i2 = 0; i2 < this.a; i2++) {
                    if (this.e[i2] == i) {
                        b(i2);
                        return;
                    }
                }
                return;
            }
            b(i);
        }
    }

    public void b(int i) {
        this.d = this.c;
        this.c = i;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public boolean a() {
        return this.f;
    }

    public void b(boolean z) {
        if ((!this.h || this.f) && z != this.f) {
            if (z) {
                boolean f = f();
                int b = b();
                this.f = z;
                i();
                if (f) {
                    for (int i = 0; i < this.a; i++) {
                        if (this.e[i] == b) {
                            b(i);
                            break;
                        }
                    }
                }
            } else if (f()) {
                this.c = this.e[this.c];
            }
            this.f = z;
        }
    }

    public void c(boolean z) {
        this.h = z;
    }

    public int b() {
        if (f()) {
            return this.f ? this.e[this.c] : this.c;
        } else {
            return -1;
        }
    }

    public int c() {
        return this.a;
    }

    public SongInfo d() {
        if (f()) {
            return this.b[b()];
        }
        if (this.b == null || this.a <= 0) {
            return null;
        }
        return this.b[this.a - 1];
    }

    public SongInfo e() {
        if (f()) {
            return this.b[b()];
        }
        return null;
    }

    public boolean f() {
        return this.c >= 0 && this.c < this.a;
    }

    public void d(boolean z) {
        if (f()) {
            this.d = this.c;
            if (!this.h) {
                this.c++;
                if (this.c < this.a) {
                    return;
                }
                if (this.g) {
                    if (this.f) {
                        i();
                    }
                    this.c = 0;
                    return;
                }
                this.c = -1;
            } else if (!this.g) {
                this.c = -1;
            } else if (!z) {
                this.c++;
                if (this.c >= this.a) {
                    this.c = 0;
                }
            }
        }
    }

    public void g() {
        if (f()) {
            this.d = this.c;
            if (!this.h) {
                this.c--;
                if (this.c >= 0) {
                    return;
                }
                if (this.g) {
                    if (this.f) {
                        i();
                    }
                    this.c = this.a - 1;
                    return;
                }
                this.c = -1;
            } else if (this.g) {
                this.c--;
                if (this.c < 0) {
                    this.c = this.a - 1;
                }
            } else {
                this.c = -1;
            }
        }
    }

    private void i() {
        this.e = m.a(this.a);
    }

    private void e(int i) {
        if (this.b == null || i > this.b.length) {
            SongInfo[] songInfoArr = new SongInfo[i];
            int i2 = this.a;
            for (int i3 = 0; i3 < i2; i3++) {
                songInfoArr[i3] = this.b[i3];
            }
            this.b = songInfoArr;
        }
    }

    public SongInfo[] h() {
        SongInfo[] songInfoArr;
        synchronized (this) {
            songInfoArr = this.b;
        }
        return songInfoArr;
    }

    public boolean a(SongInfo[] songInfoArr) {
        boolean z = true;
        if (songInfoArr == null) {
            this.a = 0;
            this.b = null;
            d(8);
            return true;
        }
        int length = songInfoArr.length;
        if (this.a == length) {
            for (int i = 0; i < length; i++) {
                if (songInfoArr[i] != this.b[i]) {
                    break;
                }
            }
            z = false;
        }
        if (!z) {
            return false;
        }
        SongInfo j = j();
        a(songInfoArr, -1);
        if (this.f) {
            i();
        }
        boolean b = b(j);
        d(8);
        return b;
    }

    private SongInfo j() {
        if (f()) {
            return e();
        }
        return null;
    }

    private boolean b(SongInfo songInfo) {
        b(0);
        if (songInfo == null) {
            return false;
        }
        boolean z = true;
        for (int i = 0; i < this.a; i++) {
            if (songInfo.equals(this.b[i])) {
                a(i);
                z = false;
            }
        }
        if (!z) {
            return z;
        }
        if (!z || this.h) {
            return false;
        }
        return true;
    }

    public void a(SongInfo[] songInfoArr, int i) {
        int i2 = 0;
        int length = songInfoArr.length;
        if (i < 0) {
            this.a = 0;
            i = 0;
        }
        e(this.a + length);
        if (i > this.a) {
            i = this.a;
        }
        for (int i3 = this.a - i; i3 > 0; i3--) {
            this.b[i + i3] = this.b[(i + i3) - length];
        }
        while (i2 < length) {
            this.b[i + i2] = songInfoArr[i2];
            i2++;
        }
        this.a += length;
        for (i2 = this.a; i2 < this.b.length; i2++) {
            this.b[i2] = null;
        }
        if (this.c >= i) {
            this.c++;
        }
        if (this.f) {
            i();
        }
    }

    public boolean c(int i) {
        boolean b;
        int i2 = false;
        if (i >= 0 && i <= this.a) {
            synchronized (this) {
                SongInfo j = j();
                int i3 = (this.a - i) - 1;
                while (i2 < i3) {
                    this.b[i + i2] = this.b[(i + i2) + 1];
                    i2++;
                }
                this.a--;
                if (this.f) {
                    i();
                }
                b = b(j);
                d(8);
            }
        }
        return b;
    }

    public int a(SongInfo songInfo) {
        int i;
        synchronized (this) {
            if (this.b != null && this.b.length > 0) {
                i = 0;
                while (i < this.b.length) {
                    if (songInfo.equals(this.b[i])) {
                        break;
                    }
                    i++;
                }
            }
            i = -1;
        }
        return i;
    }
}

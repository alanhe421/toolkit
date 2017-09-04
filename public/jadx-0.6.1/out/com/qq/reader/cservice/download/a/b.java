package com.qq.reader.cservice.download.a;

import android.os.Bundle;
import com.qq.reader.common.download.task.f;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.download.audio.RequestMsg;
import com.qq.reader.cservice.download.audio.SplitDownloadTask;
import com.qq.reader.cservice.download.audio.d;
import java.io.File;

/* compiled from: DownloadTask */
public abstract class b {
    private int a;
    private int b;
    private long c;
    private long d;
    private String e;
    public final int f;
    protected String g;
    public final String h;
    private int i;
    private final Object j;
    private boolean k;
    private boolean l;
    private SplitDownloadTask m;
    private d n;

    protected abstract void a(b bVar, boolean z);

    public abstract String b();

    protected abstract String c();

    protected abstract void d();

    protected abstract void e();

    public abstract boolean equals(Object obj);

    protected abstract void f();

    public b(int i, String str, String str2, String str3) {
        this(i, str, str2, str3, 0, -3230);
    }

    public b(int i, String str, String str2, String str3, int i2, int i3) {
        boolean z = false;
        this.a = 0;
        this.b = -3230;
        this.c = 0;
        this.d = 0;
        this.i = -1;
        this.j = new Object();
        this.k = false;
        this.l = false;
        this.n = new d(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(long j, long j2) {
            }

            public void a(int i) {
                this.a.i = i;
            }

            public boolean a(Bundle bundle, long j, long j2) {
                this.a.a(j);
                this.a.c = j2;
                if (this.a.b(j2)) {
                    this.a.e();
                }
                return true;
            }

            public void a(int i, Bundle bundle) {
                if (i != 0) {
                    this.a.a(i);
                } else {
                    this.a.a();
                }
            }

            public void b(int i, Bundle bundle) {
                if (i == -5) {
                    return;
                }
                if (i == -3 || i == -4) {
                    this.a.a(i);
                    return;
                }
                this.a.a = 30;
                this.a.e();
                this.a.a(this.a, false);
            }
        };
        this.f = i;
        this.g = str3;
        if (str == null || str.length() == 0) {
            this.h = c();
        } else if (str.endsWith("/")) {
            this.h = str;
        } else {
            this.h = str + "/";
        }
        if (str2 == null || str2.length() == 0) {
            this.e = "error";
        } else {
            this.e = str2;
        }
        this.b = i3;
        this.a = i2;
        if (this.a == 10) {
            this.a = 30;
        }
        if (this.a == 40 && !new File(this.h + this.e).exists()) {
            z = true;
        }
        this.l = z;
    }

    public String g() {
        return this.g;
    }

    public boolean h() {
        return this.k;
    }

    public void a(boolean z) {
        if (this.k != z) {
            this.k = z;
            if (this.k && this.a == 50) {
                this.a = 30;
                e();
            }
        }
    }

    public String i() {
        return this.e;
    }

    public int j() {
        return this.a;
    }

    public int k() {
        return this.b;
    }

    public long l() {
        return this.d;
    }

    private void a(long j) {
        if (j != this.d) {
            if (this.d == 0) {
                d();
            }
            this.d = j;
        }
    }

    public long m() {
        return this.c;
    }

    private boolean b(long j) {
        if (j == this.c) {
            return false;
        }
        this.c = j;
        return true;
    }

    public void n() {
        if (this.a != 10) {
            synchronized (this.j) {
                Object obj = (this.a == 30 || (this.a == 50 && this.b != -3231)) ? 1 : null;
                this.a = 10;
                if (obj == null) {
                    String h = ao.h(this.h, this.e);
                    if (h == null || h.length() == 0) {
                        a(-3233);
                        return;
                    }
                    this.e = h;
                    f();
                }
                e();
                t();
            }
        }
    }

    private void t() {
        synchronized (this.j) {
            if (this.g == null || this.g.length() == 0 || this.g.endsWith("/")) {
                a(-3231);
                return;
            }
            this.m = new SplitDownloadTask(this.h + this.e + f.DOWNLOAD_FILE_TMP, new RequestMsg(this.g), this.n);
            g.a().a(this.m);
        }
    }

    public void o() {
        if (this.m != null) {
            this.m.terminate();
        }
    }

    public void p() {
        synchronized (this.j) {
            if (q()) {
                this.a = 30;
                e();
                c.e("DM", "call stop");
                this.m.terminate();
            }
        }
    }

    public boolean q() {
        return this.a == 10 && (this.i == 10 || this.i == 11 || this.i == 12 || this.i == 13);
    }

    protected void a() {
        try {
            new File(this.h + this.e + f.DOWNLOAD_FILE_TMP).renameTo(new File(this.h + this.e));
            this.a = 40;
            e();
            a(this, false);
        } catch (Exception e) {
            a(-3231);
            s();
        }
    }

    private void a(int i) {
        this.a = 50;
        this.b = i;
        a(this, this.b == -6);
        e();
    }

    public void r() {
        this.a = 50;
        this.b = -6;
        e();
    }

    public void s() {
        File file = new File(this.h + this.e + (this.a == 40 ? "" : f.DOWNLOAD_FILE_TMP));
        if (file.exists()) {
            file.delete();
        }
    }
}

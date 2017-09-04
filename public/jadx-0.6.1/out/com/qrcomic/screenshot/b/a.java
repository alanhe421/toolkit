package com.qrcomic.screenshot.b;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.MotionEvent;
import com.qrcomic.screenshot.c.e;
import com.qrcomic.screenshot.ui.QRComicDoodleView;
import java.util.List;

/* compiled from: QRBaseLayer */
public abstract class a implements c {
    protected boolean a;
    protected long b;
    protected QRComicDoodleView c;
    protected RectF d;
    protected Matrix e;
    protected Matrix f;
    protected Matrix g;
    protected List<a> h;

    /* compiled from: QRBaseLayer */
    public interface a {
        void a(a aVar, boolean z);

        void b(a aVar, boolean z);

        void c(a aVar, boolean z);

        void d(a aVar, boolean z);

        void e(a aVar, boolean z);

        void f(a aVar, boolean z);

        void g(a aVar, boolean z);
    }

    public boolean a() {
        return this.a;
    }

    public void a(boolean z) {
        if (this.a != z) {
            this.a = z;
            d(this, this.a);
        }
    }

    public long b() {
        return this.b;
    }

    public boolean a(MotionEvent motionEvent) {
        return false;
    }

    public void c() {
    }

    public e d() {
        return null;
    }

    protected final void a(a aVar, boolean z) {
        if (this.h != null && this.h.size() > 0) {
            for (a a : this.h) {
                a.a(aVar, z);
            }
        }
    }

    protected final void b(a aVar, boolean z) {
        if (this.h != null && this.h.size() > 0) {
            for (a b : this.h) {
                b.b(aVar, z);
            }
        }
    }

    protected final void c(a aVar, boolean z) {
        if (this.h != null && this.h.size() > 0) {
            for (a c : this.h) {
                c.c(aVar, z);
            }
        }
    }

    protected final void d(a aVar, boolean z) {
        if (this.h != null && this.h.size() > 0) {
            for (a d : this.h) {
                d.d(aVar, z);
            }
        }
    }

    protected final void e(a aVar, boolean z) {
        if (this.h != null && this.h.size() > 0) {
            for (a e : this.h) {
                e.e(aVar, z);
            }
        }
    }

    protected final void f(a aVar, boolean z) {
        if (this.h != null && this.h.size() > 0) {
            for (a f : this.h) {
                f.f(aVar, z);
            }
        }
    }

    protected final void g(a aVar, boolean z) {
        if (this.h != null && this.h.size() > 0) {
            for (a g : this.h) {
                g.g(aVar, z);
            }
        }
    }
}

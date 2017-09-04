package com.qq.reader.module.readpage;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.qq.reader.readengine.kernel.c.b;
import com.qq.reader.readengine.kernel.c.d;
import com.qq.reader.readengine.kernel.e;

/* compiled from: ReaderPageLayer */
public class s implements com.qq.reader.module.readpage.p.a, com.qq.reader.readengine.kernel.d.a {
    protected View a;
    protected boolean b = false;
    protected boolean c = false;
    protected boolean d = false;
    protected a e;
    private Activity f;
    private Handler g;
    private volatile boolean h = false;

    /* compiled from: ReaderPageLayer */
    public interface a {
        void a(s sVar);
    }

    public void a(boolean z) {
        this.c = z;
        if (this.c) {
            this.a.setVisibility(4);
        }
    }

    public void b(boolean z) {
        this.d = z;
        if (this.d) {
            this.a.setVisibility(4);
        } else {
            d();
        }
    }

    public View c() {
        return this.a;
    }

    public boolean a(Canvas canvas, e eVar) {
        if (this.a == null) {
            return false;
        }
        if (a(eVar.a())) {
            this.a.draw(canvas);
        }
        return true;
    }

    public void a() {
        this.a.setVisibility(4);
        this.h = true;
    }

    public void b() {
        d();
        this.h = false;
    }

    public void d() {
        if (!this.b || this.c || this.d) {
            this.a.setVisibility(4);
        } else {
            this.a.setVisibility(0);
        }
    }

    public void a(b bVar) {
        this.b = false;
        for (int i = 0; i < bVar.f(); i++) {
            d b = bVar.b(i);
            if (a(b.h().a())) {
                this.b = true;
                a(b);
                break;
            }
            this.b = false;
        }
        if (!this.h) {
            d();
        }
    }

    protected boolean a(int i) {
        return false;
    }

    protected void a(d dVar) {
    }

    public void a(a aVar) {
        this.e = aVar;
    }

    public Handler e() {
        return this.g;
    }

    public void a(Handler handler) {
        this.g = handler;
    }

    public boolean a(Message message) {
        return false;
    }

    public void a(Activity activity) {
        this.f = activity;
    }

    public Activity f() {
        return this.f;
    }
}

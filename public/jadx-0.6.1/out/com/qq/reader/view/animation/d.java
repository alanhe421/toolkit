package com.qq.reader.view.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.qq.reader.module.readpage.ab;
import com.qq.reader.module.readpage.i;
import com.qq.reader.readengine.kernel.PageIndex;
import com.qq.reader.readengine.kernel.b;
import com.qq.reader.view.animation.AnimationProvider.Mode;
import com.qq.reader.view.animation.AnimationProvider.a;

/* compiled from: NoneAnimationProvider */
public class d extends AnimationProvider {
    private Paint p;

    public d(i iVar, Context context, a aVar) {
        super(iVar, context, aVar);
        this.p = null;
        this.p = new Paint();
        iVar.a().e(0);
        this.c = 0.0f;
        this.b = 0.0f;
    }

    public void b(int i, int i2) {
        this.b = 0.0f;
        this.c = 0.0f;
    }

    public void a(int i, int i2, int i3, int i4, Mode mode, int i5) {
        if (this.o != null) {
            this.o.a();
        }
        if (this.h == null) {
            switch (ab.a((float) this.e.x, (float) this.e.y, this.l, this.m)) {
                case 0:
                    this.h = PageIndex.next;
                    break;
                case 1:
                    this.h = PageIndex.previous;
                    break;
            }
        }
        switch (this.h) {
            case previous:
                this.k.a(PageIndex.previous);
                break;
            case next:
                this.k.a(PageIndex.next);
                break;
        }
        i();
        if (mode == Mode.ForceScrolling) {
            this.k.f(PageIndex.previous);
            this.k.f(PageIndex.next);
        }
    }

    public void i() {
        super.i();
        if (this.o != null) {
            this.o.b();
        }
    }

    public PageIndex a(float f, float f2) {
        this.h = PageIndex.current;
        if (f > ((float) this.e.x)) {
            this.h = PageIndex.previous;
        } else if (f < ((float) this.e.x)) {
            this.h = PageIndex.next;
        }
        return this.h;
    }

    public void b(float f, float f2) {
        this.e.set((int) f, (int) f2);
        this.c = 0.0f;
        this.b = 0.0f;
        this.h = null;
    }

    public boolean e() {
        return false;
    }

    public boolean f() {
        return false;
    }

    public boolean g() {
        this.c = 0.0f;
        this.b = 0.0f;
        return true;
    }

    public boolean a(Canvas canvas) {
        canvas.drawBitmap(this.k.a(PageIndex.current), this.b, this.c, this.p);
        return true;
    }

    public int a(b bVar) {
        this.h = PageIndex.previous;
        return bVar.f();
    }

    public int b(b bVar) {
        this.h = PageIndex.next;
        return bVar.e();
    }
}

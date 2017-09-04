package com.qq.reader.view.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.readpage.i;
import com.qq.reader.readengine.kernel.PageIndex;
import com.qq.reader.readengine.kernel.b;
import com.qq.reader.view.animation.AnimationProvider.AnimState;
import com.qq.reader.view.animation.AnimationProvider.Mode;
import com.qq.reader.view.animation.AnimationProvider.a;
import com.tencent.feedback.proguard.R;

/* compiled from: SlideAnimationProvider */
public class g extends AnimationProvider {
    private Scroller p = null;
    private Paint q = null;
    private boolean r = false;
    private Drawable s;
    private int t;

    public g(i iVar, Context context, a aVar) {
        super(iVar, context, aVar);
        this.p = new Scroller(context, new LinearInterpolator());
        this.s = context.getResources().getDrawable(R.drawable.pageshadow);
        this.q = new Paint();
        iVar.a().e(0);
        this.t = ao.a(8.0f);
    }

    public void b(int i, int i2) {
        if (!this.r) {
            this.e.x = i;
        }
        int i3 = this.l;
        if (this.h == PageIndex.previous) {
            this.b = (float) (((-i3) + (i - this.e.x)) - this.t);
        } else if (this.h == PageIndex.next) {
            this.b = (float) (i - this.e.x);
        }
        this.c = 0.0f;
        this.d = AnimState.DRAGING;
        this.r = true;
        if (this.o != null) {
            this.o.c();
        }
    }

    public void a(int i, int i2, int i3, int i4, Mode mode, int i5) {
        int max = Math.max(i5 - 300, 300);
        if (this.d == AnimState.ANIMATING) {
            i();
        }
        this.d = AnimState.ANIMATING;
        this.a = mode;
        int i6 = this.l;
        int i7;
        int i8;
        if (this.h == PageIndex.previous) {
            i7 = this.r ? (int) this.b : -i6;
            i8 = this.r ? mode == Mode.AutoScrollingBackward ? (-i6) - i7 : (int) (0.0f - this.b) : i6;
            this.p.startScroll(i7, 0, i8, 0, (int) Math.abs((((float) max) / ((float) i6)) * ((float) i8)));
        } else if (this.h == PageIndex.next) {
            if (this.r) {
                i7 = (int) this.b;
            } else {
                i7 = 0;
            }
            i8 = this.r ? mode == Mode.AutoScrollingBackward ? -i7 : (int) (((float) (-i6)) - this.b) : -i6;
            this.p.startScroll(i7, 0, i8, 0, (int) Math.abs((((float) max) / ((float) i6)) * ((float) i8)));
        }
        if (this.o != null) {
            this.o.a();
        }
    }

    public PageIndex a(float f, float f2) {
        if (f > ((float) this.e.x)) {
            a(PageIndex.previous);
        } else if (f < ((float) this.e.x)) {
            a(PageIndex.next);
        }
        return this.h;
    }

    public boolean g() {
        if (this.d == AnimState.ANIMATING) {
            if (this.p.computeScrollOffset()) {
                this.b = (float) this.p.getCurrX();
                this.c = (float) this.p.getCurrY();
                return true;
            }
            i();
        }
        return false;
    }

    public void i() {
        super.i();
        this.d = AnimState.READY;
        this.c = -1.0f;
        this.b = -1.0f;
        this.p.abortAnimation();
        if (!this.p.isFinished()) {
            this.p.forceFinished(true);
        }
        this.r = false;
        if (this.o != null) {
            this.o.b();
        }
    }

    public boolean a(Canvas canvas) {
        if (f() || e()) {
            if (this.h == PageIndex.previous) {
                canvas.drawBitmap(this.k.a(PageIndex.current, 1), 0.0f, 0.0f, this.q);
                canvas.drawBitmap(this.k.a(PageIndex.previous), this.b, this.c, this.q);
            } else if (this.h == PageIndex.next) {
                canvas.drawBitmap(this.k.a(PageIndex.next), 0.0f, 0.0f, this.q);
                canvas.drawBitmap(this.k.a(PageIndex.current, 1), this.b, this.c, this.q);
            }
            this.s.setBounds(((int) this.b) + this.l, (int) this.c, (((int) this.b) + this.l) + 40, this.m);
            this.s.draw(canvas);
        } else {
            canvas.drawBitmap(this.k.a(PageIndex.current), 0.0f, 0.0f, this.q);
        }
        return true;
    }

    public boolean e() {
        return this.d == AnimState.DRAGING;
    }

    public boolean f() {
        return this.d == AnimState.ANIMATING;
    }

    public void b(float f, float f2) {
        this.e.set((int) f, (int) f2);
        this.c = -1.0f;
        this.b = -1.0f;
        this.r = false;
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

package com.qq.reader.view.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import com.qq.reader.module.readpage.i;
import com.qq.reader.readengine.kernel.PageIndex;
import com.qq.reader.readengine.kernel.b;

public abstract class AnimationProvider {
    public static int n = -15584170;
    protected Mode a = Mode.NoScrolling;
    protected float b = -1.0f;
    protected float c = -1.0f;
    protected AnimState d;
    protected Point e = new Point();
    protected Point f = new Point();
    protected Direction g;
    protected PageIndex h = PageIndex.next;
    protected int i = -1;
    protected boolean j = false;
    protected i k;
    protected int l;
    protected int m;
    protected a o;
    private Context p;

    public interface a {
        void a();

        void b();

        void c();
    }

    public enum AnimState {
        ANIMATING,
        ANIMATE_END,
        READY,
        DRAGING
    }

    public enum Direction {
        leftToRight(true),
        rightToLeft(true),
        up(false),
        down(false);
        
        public final boolean IsHorizontal;

        private Direction(boolean z) {
            this.IsHorizontal = z;
        }
    }

    public enum Mode {
        NoScrolling(false),
        ForceScrolling(false),
        AutoScrollingForward(true),
        AutoScrollingBackward(true);
        
        final boolean Auto;

        private Mode(boolean z) {
            this.Auto = z;
        }
    }

    public abstract int a(b bVar);

    public abstract PageIndex a(float f, float f2);

    public abstract void a(int i, int i2, int i3, int i4, Mode mode, int i5);

    public abstract boolean a(Canvas canvas);

    public abstract int b(b bVar);

    public abstract void b(float f, float f2);

    public abstract void b(int i, int i2);

    public abstract boolean e();

    public abstract boolean f();

    public abstract boolean g();

    public PageIndex a() {
        return this.h;
    }

    public void a(PageIndex pageIndex) {
        if (this.d == AnimState.ANIMATING) {
            i();
        }
        this.h = pageIndex;
    }

    public void a(int i, int i2) {
        this.l = i;
        this.m = i2;
    }

    public AnimationProvider(i iVar, Context context) {
        this.k = iVar;
        this.k.g();
        this.p = context;
    }

    public AnimationProvider(i iVar, Context context, a aVar) {
        this.k = iVar;
        this.k.g();
        this.p = context;
        this.o = aVar;
    }

    protected Context b() {
        return this.p;
    }

    public int c() {
        return 0;
    }

    public int d() {
        return 0;
    }

    public boolean h() {
        return this.a != Mode.NoScrolling;
    }

    public void i() {
        j();
    }

    private void j() {
        boolean z = a() == PageIndex.next;
        switch (this.a) {
            case AutoScrollingForward:
            case NoScrolling:
                this.k.a(z);
                break;
            case ForceScrolling:
                this.k.g();
                break;
        }
        this.a = Mode.NoScrolling;
    }

    public void a(int i) {
        n = i;
    }
}

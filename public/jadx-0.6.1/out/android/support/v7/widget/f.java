package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.view.View;

/* compiled from: OrientationHelper */
public abstract class f {
    protected final LayoutManager a;
    private int b;

    public abstract int a(View view);

    public abstract void a(int i);

    public abstract int b(View view);

    public abstract int c();

    public abstract int c(View view);

    public abstract int d();

    public abstract int d(View view);

    public abstract int e();

    public abstract int f();

    public abstract int g();

    private f(LayoutManager layoutManager) {
        this.b = Integer.MIN_VALUE;
        this.a = layoutManager;
    }

    public void a() {
        this.b = f();
    }

    public int b() {
        return Integer.MIN_VALUE == this.b ? 0 : f() - this.b;
    }

    public static f a(LayoutManager layoutManager, int i) {
        switch (i) {
            case 0:
                return a(layoutManager);
            case 1:
                return b(layoutManager);
            default:
                throw new IllegalArgumentException("invalid orientation");
        }
    }

    public static f a(LayoutManager layoutManager) {
        return new f(layoutManager) {
            public int d() {
                return this.a.s() - this.a.w();
            }

            public int e() {
                return this.a.s();
            }

            public void a(int i) {
                this.a.g(i);
            }

            public int c() {
                return this.a.u();
            }

            public int c(View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                return layoutParams.rightMargin + (this.a.e(view) + layoutParams.leftMargin);
            }

            public int d(View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                return layoutParams.bottomMargin + (this.a.f(view) + layoutParams.topMargin);
            }

            public int b(View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                return layoutParams.rightMargin + this.a.i(view);
            }

            public int a(View view) {
                return this.a.g(view) - ((LayoutParams) view.getLayoutParams()).leftMargin;
            }

            public int f() {
                return (this.a.s() - this.a.u()) - this.a.w();
            }

            public int g() {
                return this.a.w();
            }
        };
    }

    public static f b(LayoutManager layoutManager) {
        return new f(layoutManager) {
            public int d() {
                return this.a.t() - this.a.x();
            }

            public int e() {
                return this.a.t();
            }

            public void a(int i) {
                this.a.h(i);
            }

            public int c() {
                return this.a.v();
            }

            public int c(View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                return layoutParams.bottomMargin + (this.a.f(view) + layoutParams.topMargin);
            }

            public int d(View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                return layoutParams.rightMargin + (this.a.e(view) + layoutParams.leftMargin);
            }

            public int b(View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                return layoutParams.bottomMargin + this.a.j(view);
            }

            public int a(View view) {
                return this.a.h(view) - ((LayoutParams) view.getLayoutParams()).topMargin;
            }

            public int f() {
                return (this.a.t() - this.a.v()) - this.a.x();
            }

            public int g() {
                return this.a.x();
            }
        };
    }
}

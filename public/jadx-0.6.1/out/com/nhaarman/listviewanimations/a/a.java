package com.nhaarman.listviewanimations.a;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.nhaarman.listviewanimations.b;
import com.nhaarman.listviewanimations.b.c;
import com.nhaarman.listviewanimations.b.e;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

/* compiled from: AnimationAdapter */
public abstract class a extends b {
    static final /* synthetic */ boolean a = (!a.class.desiredAssertionStatus());
    private b b;
    private boolean c = true;
    private boolean d = true;
    private int e = -1;

    public abstract Animator[] a(ViewGroup viewGroup, View view);

    protected a(BaseAdapter baseAdapter) {
        super(baseAdapter);
        if (baseAdapter instanceof a) {
            ((a) baseAdapter).d();
        }
    }

    public void a(e eVar) {
        super.a(eVar);
        this.b = new b(eVar);
    }

    private void d() {
        this.c = false;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (this.c) {
            if (c() == null) {
                throw new IllegalStateException("Call setAbsListView() on this AnimationAdapter first!");
            } else if (!a && this.b == null) {
                throw new AssertionError();
            } else if (view != null) {
                this.b.a(view);
            }
        }
        View view2 = super.getView(i, view, viewGroup);
        if (this.c) {
            a(i, view2, viewGroup);
        }
        return view2;
    }

    private void a(int i, View view, ViewGroup viewGroup) {
        if (a || this.b != null) {
            Animator[] a;
            boolean z = this.d && (this.e == -1 || this.e == i);
            this.d = z;
            if (this.d) {
                this.e = i;
                this.b.a(-1);
            }
            if (a() instanceof a) {
                a = ((a) a()).a(viewGroup, view);
            } else {
                a = new Animator[0];
            }
            this.b.a(i, view, c.a(a, a(viewGroup, view), ObjectAnimator.ofFloat((Object) view, "alpha", 0.0f, 1.0f)));
            return;
        }
        throw new AssertionError();
    }
}

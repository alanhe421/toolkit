package com.nhaarman.listviewanimations.itemmanipulation.dragdrop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;
import com.nhaarman.listviewanimations.b.g;

@TargetApi(14)
/* compiled from: DragAndDropHandler */
public class b implements com.nhaarman.listviewanimations.itemmanipulation.a {
    static final /* synthetic */ boolean a = (!b.class.desiredAssertionStatus());
    private final c b;
    private final a c;
    private final c d;
    private final int e;
    private ListAdapter f;
    private e g;
    private View h;
    private long i;
    private float j;
    private int k;
    private d l;
    private f m;
    private float n;
    private float o;
    private boolean p;

    /* compiled from: DragAndDropHandler */
    private class a implements OnScrollListener {
        static final /* synthetic */ boolean b = (!b.class.desiredAssertionStatus());
        final /* synthetic */ b a;
        private final int c;
        private float d;
        private int e;
        private int f;
        private int g;
        private int h;

        void a(float f) {
            this.d = f;
        }

        void a() {
            if (this.a.g != null && !this.a.p) {
                Rect bounds = this.a.g.getBounds();
                int a = this.a.b.a();
                int height = this.a.b.h().getHeight();
                int b = this.a.b.b();
                int c = this.a.b.c();
                int i = bounds.top;
                int height2 = bounds.height();
                int max = (int) Math.max(1.0f, ((float) this.c) * this.d);
                if (i <= 0 && a > 0) {
                    this.a.b.a_(-max, 0);
                } else if (height2 + i >= height && a + b < c) {
                    this.a.b.a_(max, 0);
                }
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            this.g = i;
            this.h = i + i2;
            this.e = this.e == -1 ? this.g : this.e;
            this.f = this.f == -1 ? this.h : this.f;
            if (this.a.g != null) {
                if (b || this.a.h != null) {
                    this.a.g.a(this.a.h.getY());
                } else {
                    throw new AssertionError();
                }
            }
            if (!this.a.p) {
                b();
                c();
            }
            this.e = this.g;
            this.f = this.h;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i == 0 && this.a.g != null) {
                a();
            }
        }

        private void b() {
            if (this.a.g != null && this.a.f != null && this.g < this.e) {
                int b = this.a.a(this.a.i);
                if (b != -1) {
                    long itemId = (b + -1) - this.a.b.f() >= 0 ? this.a.f.getItemId((b - 1) - this.a.b.f()) : -1;
                    View a = this.a.b(itemId);
                    if (a != null) {
                        this.a.a(a, itemId, (float) (-a.getHeight()));
                    }
                }
            }
        }

        private void c() {
            if (this.a.g != null && this.a.f != null && this.h > this.f) {
                int b = this.a.a(this.a.i);
                if (b != -1) {
                    long itemId;
                    if ((b + 1) - this.a.b.f() < this.a.f.getCount()) {
                        itemId = this.a.f.getItemId((b + 1) - this.a.b.f());
                    } else {
                        itemId = -1;
                    }
                    View a = this.a.b(itemId);
                    if (a != null) {
                        this.a.a(a, itemId, (float) a.getHeight());
                    }
                }
            }
        }
    }

    /* compiled from: DragAndDropHandler */
    private class b extends AnimatorListenerAdapter implements AnimatorUpdateListener {
        final /* synthetic */ b a;
        private final e b;
        private final View c;

        private b(b bVar, e eVar, View view) {
            this.a = bVar;
            this.b = eVar;
            this.c = view;
        }

        public void onAnimationStart(Animator animator) {
            this.a.p = true;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.b.a(((Integer) valueAnimator.getAnimatedValue()).intValue());
            this.a.b.h().postInvalidate();
        }

        public void onAnimationEnd(Animator animator) {
            this.c.setVisibility(0);
            this.a.g = null;
            this.a.h = null;
            this.a.i = -1;
            this.a.k = -1;
            this.a.p = false;
        }
    }

    /* compiled from: DragAndDropHandler */
    private interface c {
        void a(long j, float f);
    }

    public void a(ListAdapter listAdapter) {
        b(listAdapter);
    }

    private void b(ListAdapter listAdapter) {
        if (listAdapter instanceof WrapperListAdapter) {
            listAdapter = ((WrapperListAdapter) listAdapter).getWrappedAdapter();
        }
        if (!listAdapter.hasStableIds()) {
            throw new IllegalStateException("Adapter doesn't have stable ids! Make sure your adapter has stable ids, and override hasStableIds() to return true.");
        } else if (listAdapter instanceof g) {
            this.f = listAdapter;
        } else {
            throw new IllegalArgumentException("Adapter should implement Swappable!");
        }
    }

    public void a(float f) {
        this.c.a(f);
    }

    public void a(int i) {
        if (this.i == -1) {
            if (this.j < 0.0f) {
                throw new IllegalStateException("User must be touching the DynamicListView!");
            } else if (this.f == null) {
                throw new IllegalStateException("This DynamicListView has no adapter set!");
            } else if (i >= 0 && i < this.f.getCount()) {
                this.h = this.b.a((i - this.b.c_()) + this.b.f());
                if (this.h != null) {
                    this.k = i;
                    this.i = this.f.getItemId(i);
                    this.g = new e(this.h, this.j);
                    this.h.setVisibility(4);
                }
            }
        }
    }

    public void a(d dVar) {
        this.l = dVar;
    }

    public void a(f fVar) {
        this.m = fVar;
    }

    public boolean a() {
        return this.i != -1;
    }

    public boolean a(MotionEvent motionEvent) {
        if (this.p) {
            return false;
        }
        boolean c;
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.j = motionEvent.getY();
                return b(motionEvent);
            case 1:
                c = c();
                this.j = -1.0f;
                return c;
            case 2:
                this.j = motionEvent.getY();
                return c(motionEvent);
            case 3:
                c = d();
                this.j = -1.0f;
                return c;
            default:
                return false;
        }
    }

    private boolean b(MotionEvent motionEvent) {
        this.n = motionEvent.getRawX();
        this.o = motionEvent.getRawY();
        return true;
    }

    private int a(long j) {
        View b = b(j);
        if (b == null) {
            return -1;
        }
        return this.b.a(b);
    }

    private View b(long j) {
        ListAdapter listAdapter = this.f;
        if (j == -1 || listAdapter == null) {
            return null;
        }
        int c_ = this.b.c_();
        View view = null;
        for (int i = 0; i < this.b.e() && view == null; i++) {
            int i2 = c_ + i;
            if (i2 - this.b.f() >= 0 && listAdapter.getItemId(i2 - this.b.f()) == j) {
                view = this.b.a(i);
            }
        }
        return view;
    }

    private boolean c(MotionEvent motionEvent) {
        float rawX = motionEvent.getRawX() - this.n;
        float rawY = motionEvent.getRawY() - this.o;
        if (this.g == null && Math.abs(rawY) > ((float) this.e) && Math.abs(rawY) > Math.abs(rawX)) {
            int a = this.b.a((int) motionEvent.getX(), (int) motionEvent.getY());
            if (a != -1) {
                View a2 = this.b.a(a - this.b.c_());
                if (!a && a2 == null) {
                    throw new AssertionError();
                } else if (this.l.a(a2, a - this.b.f(), motionEvent.getX() - a2.getX(), motionEvent.getY() - a2.getY())) {
                    a(a - this.b.f());
                    return true;
                }
            }
        } else if (this.g != null) {
            this.g.a(motionEvent);
            b();
            this.b.h().invalidate();
            return true;
        }
        return false;
    }

    private void b() {
        long j = -1;
        if (this.g != null && this.f != null) {
            int a = a(this.i);
            long itemId = (a + -1) - this.b.f() >= 0 ? this.f.getItemId((a - 1) - this.b.f()) : -1;
            if ((a + 1) - this.b.f() < this.f.getCount()) {
                j = this.f.getItemId((a + 1) - this.b.f());
            }
            if (!this.g.a()) {
                itemId = j;
            }
            View b = b(itemId);
            int b2 = this.g.b();
            if (b != null && Math.abs(b2) > this.g.getIntrinsicHeight()) {
                a(b, itemId, (float) ((b2 < 0 ? -1 : 1) * this.g.getIntrinsicHeight()));
            }
            this.c.a();
            this.b.h().invalidate();
        }
    }

    private void a(View view, long j, float f) {
        if (!a && this.g == null) {
            throw new AssertionError();
        } else if (!a && this.f == null) {
            throw new AssertionError();
        } else if (a || this.h != null) {
            ((g) this.f).a(this.b.a(view) - this.b.f(), this.b.a(this.h) - this.b.f());
            ((BaseAdapter) this.f).notifyDataSetChanged();
            this.g.b(view.getHeight());
            this.d.a(j, f);
        } else {
            throw new AssertionError();
        }
    }

    private boolean c() {
        if (this.h == null) {
            return false;
        }
        if (a || this.g != null) {
            ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{this.g.c(), (int) this.h.getY()});
            Object bVar = new b(this.g, this.h);
            ofInt.addUpdateListener(bVar);
            ofInt.addListener(bVar);
            ofInt.start();
            int a = a(this.i) - this.b.f();
            if (!(this.k == a || this.m == null)) {
                this.m.a(this.k, a);
            }
            return true;
        }
        throw new AssertionError();
    }

    private boolean d() {
        return c();
    }

    public void a(Canvas canvas) {
        if (this.g != null) {
            this.g.draw(canvas);
        }
    }
}

package com.qq.reader.liveshow.views.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.n;
import android.support.v4.view.z;
import android.support.v4.widget.k;
import android.support.v4.widget.k.a;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.qq.reader.liveshow.utils.m;

public class SlideView extends RelativeLayout {
    private Context a;
    private k b;
    private int c;
    private int d;
    private ViewGroup e;
    private int f;
    private boolean g;
    private OnShowChildViewListener h;
    private OnClickChildViewListener i;
    private int j;
    private boolean k;

    public interface OnClickChildViewListener {
        void a();
    }

    public interface OnShowChildViewListener {
        void a();

        void b();
    }

    public SlideView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = true;
        this.a = context;
        c();
    }

    public SlideView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlideView(Context context) {
        this(context, null);
    }

    private void c() {
        this.b = k.a((ViewGroup) this, 1.0f, new a(this) {
            final /* synthetic */ SlideView a;

            {
                this.a = r1;
            }

            public boolean a(View view, int i) {
                return true;
            }

            public int a(View view, int i, int i2) {
                return Math.max(0, i);
            }

            public void a(View view, float f, float f2) {
                if (view == this.a.e) {
                    if (this.a.e.getLeft() > m.a(this.a.getContext(), 100.0f)) {
                        this.a.a();
                    } else {
                        this.a.b();
                    }
                }
                this.a.invalidate();
            }
        });
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int a = n.a(motionEvent);
        if (a != 3 && a != 1) {
            return this.b.a(motionEvent);
        }
        this.b.e();
        return false;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.f = (int) motionEvent.getX();
                break;
            case 1:
            case 3:
                if (this.k && this.i != null) {
                    this.i.a();
                }
                if (this.g && this.e.getLeft() < this.d - m.a(getContext(), 100.0f)) {
                    b();
                    this.g = false;
                } else if (this.e.getLeft() >= this.d - m.a(getContext(), 100.0f)) {
                    a();
                }
                this.k = true;
                break;
            case 2:
                int x = ((int) motionEvent.getX()) - this.f;
                if (Math.abs(x) > m.a(getContext(), 5.0f)) {
                    this.k = false;
                }
                if (this.g) {
                    this.e.layout(this.d + x, 0, x + (this.d * 2), this.c);
                }
                invalidate();
                break;
        }
        this.b.b(motionEvent);
        return true;
    }

    public void computeScroll() {
        if (this.b.a(true)) {
            z.d(this);
        } else {
            this.j = this.e.getLeft();
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.e.offsetLeftAndRight(this.j);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.e = (ViewGroup) getChildAt(0);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.c = getMeasuredHeight();
        this.d = getMeasuredWidth();
    }

    public void a() {
        a(true);
    }

    public void b() {
        b(true);
    }

    public void a(boolean z) {
        int i = this.d;
        if (!z) {
            this.e.layout(i, 0, i * 2, this.c);
        } else if (this.b.a(this.e, i, 0)) {
            z.d(this);
            if (this.h != null) {
                this.h.b();
            }
            this.g = true;
        }
    }

    public void b(boolean z) {
        if (!z) {
            this.e.layout(0, 0, this.d, this.c);
        } else if (this.b.a(this.e, 0, 0)) {
            z.d(this);
            if (this.h != null) {
                this.h.a();
            }
            this.g = false;
        }
    }

    public void setOnShowChildViewListener(OnShowChildViewListener onShowChildViewListener) {
        this.h = onShowChildViewListener;
    }

    public void setOnClickChildViewListener(OnClickChildViewListener onClickChildViewListener) {
        this.i = onClickChildViewListener;
    }
}

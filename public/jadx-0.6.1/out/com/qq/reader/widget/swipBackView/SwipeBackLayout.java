package com.qq.reader.widget.swipBackView;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.z;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import com.qq.reader.c.b;
import com.tencent.feedback.proguard.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class SwipeBackLayout extends FrameLayout implements c {
    private static final int[] a = new int[]{1, 2, 8, 11};
    private int b;
    private float c;
    private Activity d;
    private boolean e;
    private View f;
    private e g;
    private float h;
    private int i;
    private int j;
    private List<a> k;
    private Drawable l;
    private Drawable m;
    private Drawable n;
    private float o;
    private int p;
    private boolean q;
    private Rect r;
    private int s;
    private boolean t;

    public SwipeBackLayout(Context context) {
        this(context, null);
    }

    public SwipeBackLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.SwipeBackLayoutStyle);
    }

    public SwipeBackLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.c = 0.3f;
        this.e = true;
        this.p = -1728053248;
        this.r = new Rect();
        this.g = e.a(this, new b(this, null));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.SwipeBackLayout, i, R.style.SwipeBackLayout);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        if (dimensionPixelSize > 0) {
            setEdgeSize(dimensionPixelSize);
        }
        setEdgeTrackingEnabled(a[obtainStyledAttributes.getInt(1, 0)]);
        dimensionPixelSize = obtainStyledAttributes.getResourceId(2, R.drawable.shadow_left);
        int resourceId = obtainStyledAttributes.getResourceId(3, R.drawable.shadow_right);
        int resourceId2 = obtainStyledAttributes.getResourceId(4, R.drawable.shadow_bottom);
        setShadow(dimensionPixelSize, 1);
        setShadow(resourceId, 2);
        setShadow(resourceId2, 8);
        obtainStyledAttributes.recycle();
        float f = getResources().getDisplayMetrics().density * 400.0f;
        this.g.a(f);
        this.g.b(f * 2.0f);
    }

    public void setSensitivity(Context context, float f) {
        this.g.a(context, f);
    }

    private void setContentView(View view) {
        this.f = view;
    }

    public void setEnableGesture(boolean z) {
        this.e = z;
    }

    public void setEdgeTrackingEnabled(int i) {
        this.b = i;
        this.g.a(this.b);
    }

    public void setScrimColor(int i) {
        this.p = i;
        invalidate();
    }

    public void setEdgeSize(int i) {
        this.g.b(i);
    }

    @Deprecated
    public void setSwipeListener(a aVar) {
        a(aVar);
    }

    public void a(a aVar) {
        if (this.k == null) {
            this.k = new ArrayList();
        }
        this.k.add(aVar);
    }

    public void setparentActivity(WeakReference<Activity> weakReference) {
        this.g.a(weakReference);
    }

    public void a(boolean z) {
        this.g.a(z);
    }

    public void setScrollThresHold(float f) {
        if (f >= 1.0f || f <= 0.0f) {
            throw new IllegalArgumentException("Threshold value should be between 0 and 1.0");
        }
        this.c = f;
    }

    public int getLastViewOffset() {
        return this.g.b();
    }

    public int getViewDragState() {
        return this.g.c();
    }

    public void setShadow(Drawable drawable, int i) {
        if ((i & 1) != 0) {
            this.l = drawable;
        } else if ((i & 2) != 0) {
            this.m = drawable;
        } else if ((i & 8) != 0) {
            this.n = drawable;
        }
        invalidate();
    }

    public void setShadow(int i, int i2) {
        setShadow(getResources().getDrawable(i), i2);
    }

    public void a() {
        int i = 0;
        int width = this.f.getWidth();
        int height = this.f.getHeight();
        if ((this.b & 1) != 0) {
            width = (width + this.l.getIntrinsicWidth()) + 10;
            this.s = 1;
        } else if ((this.b & 2) != 0) {
            width = ((-width) - this.m.getIntrinsicWidth()) - 10;
            this.s = 2;
        } else if ((this.b & 8) != 0) {
            width = ((-height) - this.n.getIntrinsicHeight()) - 10;
            this.s = 8;
            int i2 = width;
            width = 0;
            i = i2;
        } else {
            width = 0;
        }
        this.g.a(this.f, width, i);
        invalidate();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.e) {
            try {
                z = this.g.a(motionEvent);
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        return z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.e) {
            return false;
        }
        this.g.b(motionEvent);
        return true;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.q = true;
        if (this.f != null) {
            this.f.layout(this.i, this.j, this.i + this.f.getMeasuredWidth(), this.j + this.f.getMeasuredHeight());
        }
        this.q = false;
    }

    public void requestLayout() {
        if (!this.q) {
            super.requestLayout();
        }
    }

    protected boolean drawChild(Canvas canvas, View view, long j) {
        Object obj = view == this.f ? 1 : null;
        boolean drawChild = super.drawChild(canvas, view, j);
        if (!(this.o <= 0.0f || obj == null || this.g.c() == 0)) {
            a(canvas, view);
        }
        return drawChild;
    }

    private void a(Canvas canvas, View view) {
        Rect rect = this.r;
        view.getHitRect(rect);
        if ((this.b & 1) != 0) {
            this.l.setBounds(rect.left - this.l.getIntrinsicWidth(), rect.top, rect.left, rect.bottom);
            this.l.setAlpha((int) (this.o * 255.0f));
            this.l.draw(canvas);
        }
        if ((this.b & 2) != 0) {
            this.m.setBounds(rect.right, rect.top, rect.right + this.m.getIntrinsicWidth(), rect.bottom);
            this.m.setAlpha((int) (this.o * 255.0f));
            this.m.draw(canvas);
        }
        if ((this.b & 8) != 0) {
            this.n.setBounds(rect.left, rect.bottom, rect.right, rect.bottom + this.n.getIntrinsicHeight());
            this.n.setAlpha((int) (this.o * 255.0f));
            this.n.draw(canvas);
        }
    }

    public void a(Activity activity) {
        this.d = activity;
        TypedArray obtainStyledAttributes = activity.getTheme().obtainStyledAttributes(new int[]{16842836});
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290).getParent();
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.getParent();
        viewGroup.setBackgroundResource(resourceId);
        viewGroup2.removeView(viewGroup);
        addView(viewGroup, new LayoutParams(-1, -1));
        setContentView(viewGroup);
        try {
            viewGroup2.addView(this, new LayoutParams(-1, -1));
        } catch (IllegalArgumentException e) {
            viewGroup2.addView(this, new MarginLayoutParams(-1, -1));
        }
    }

    public void computeScroll() {
        this.o = 1.0f - this.h;
        if (this.g.b(true)) {
            z.d(this);
        }
    }

    public boolean b() {
        return this.t;
    }
}

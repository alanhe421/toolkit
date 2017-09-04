package com.qq.reader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.qq.reader.common.monitor.debug.c;
import com.tencent.feedback.proguard.R;
import java.lang.reflect.InvocationTargetException;

public class FlipContainerLayout extends ViewGroup implements com.qq.reader.view.FlipLayout.a {
    public boolean a;
    private Rect b;
    private Rect c;
    private Rect d;
    private int e;
    private FlipLayout f;
    private a g;
    private FrameLayout h;
    private View i;
    private int j;
    private int k;
    private com.qq.reader.view.FlipLayout.a l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private boolean q;

    private class a extends FrameLayout {
        final /* synthetic */ FlipContainerLayout a;
        private int b = 30;
        private int c = 0;
        private int d = 0;
        private int e = 0;
        private boolean f = true;

        public a(FlipContainerLayout flipContainerLayout, Context context) {
            this.a = flipContainerLayout;
            super(context);
            this.b = (int) (context.getResources().getDisplayMetrics().density * 30.0f);
        }

        public void a(boolean z) {
            this.f = z;
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (this.f) {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                switch (motionEvent.getAction() & 255) {
                    case 0:
                        this.c = x;
                        this.d = y;
                        this.e = x;
                        this.a.f.a(x, y);
                        return false;
                    case 2:
                        if (((float) Math.abs(x - this.c)) <= ((float) Math.abs(y - this.d)) * 2.0f || Math.abs(x - this.e) <= this.b) {
                            return false;
                        }
                        return true;
                }
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            return this.a.f.onTouchEvent(motionEvent);
        }
    }

    public interface b {
        boolean a();

        boolean b();

        View c();
    }

    public FlipContainerLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public FlipContainerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new Rect();
        this.c = new Rect();
        this.d = new Rect();
        this.e = 0;
        this.m = 0;
        this.n = 0;
        this.o = 3;
        this.a = true;
        this.p = true;
        this.q = true;
        i();
    }

    public FlipContainerLayout(Context context) {
        this(context, null);
    }

    private void i() {
        Context context = getContext();
        this.f = new FlipLayout(context);
        this.f.setFlipListener(this);
        this.h = new FrameLayout(context);
        this.g = new a(this, context);
        this.i = new View(context);
        this.i.setBackgroundResource(R.drawable.sidebar_shadow);
        addView(this.h, new LayoutParams(-1, -1));
        addView(this.g, new LayoutParams(-1, -1));
        addView(this.f);
        addView(this.i);
        this.q = false;
        this.p = false;
        j();
    }

    private void j() {
        try {
            if (!((Boolean) getClass().getMethod("isHardwareAccelerated", new Class[0]).invoke(this, (Object[]) null)).booleanValue()) {
                buildDrawingCache();
            }
        } catch (NoSuchMethodException e) {
            buildDrawingCache();
        } catch (InvocationTargetException e2) {
        } catch (IllegalAccessException e3) {
        }
    }

    public void setHeadView(View view) {
        if (view != null) {
            this.f.setHeadView(view);
        }
    }

    public void setHeadViewWithoutAdd(View view) {
        if (view != null) {
            this.f.setHeadViewWithoutAdd(view);
        }
    }

    public void setBottomViewWithoutAdd(View view) {
        if (view != null) {
            this.f.setBottomViewWithoutAdd(view);
        }
    }

    public void setIsSideFlip(boolean z) {
        this.a = z;
    }

    public void setCurrentView(b bVar) {
        if (bVar != null) {
            this.f.a(bVar);
        }
    }

    public void setLeftView(View view) {
        if (view != null) {
            this.h.addView(view);
        }
    }

    public void setRightView(View view) {
        if (view != null) {
            this.g.addView(view);
        }
    }

    public void setRightViewFilpEnable(boolean z) {
        if (this.g != null) {
            this.g.a(z);
        }
    }

    public void setRightSize(int i) {
        this.j = i;
        this.f.setRightWidth(this.j);
    }

    public void setLeftSize(int i) {
        this.k = i;
        this.f.setLeftWidth(this.k);
    }

    public void g() {
        this.q = true;
        this.f.a();
    }

    public void h() {
        this.f.c();
    }

    public int getCurrentState() {
        return this.f.getCurrentState();
    }

    public void setFlipListener(com.qq.reader.view.FlipLayout.a aVar) {
        this.l = aVar;
    }

    public void setOnTapListener(com.qq.reader.view.FlipLayout.b bVar) {
        this.f.setOnTapListener(bVar);
    }

    public View getLeftView() {
        return this.h;
    }

    public View getRightView() {
        return this.g;
    }

    public View getFlipView() {
        return this.f;
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        measureChild(this.f, size + 1073741824, size2 + 1073741824);
        measureChild(this.g, this.j + 1073741824, size2 + 1073741824);
        measureChild(this.h, this.k + 1073741824, size2 + 1073741824);
        measureChild(this.i, 1073741845, size2 + 1073741824);
        setMeasuredDimension(size, size2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        if (VERSION.SDK_INT < 11) {
            i2 = 0;
        }
        if (this.a) {
            this.m = this.h.getMeasuredWidth() / this.o;
            this.n = this.g.getMeasuredWidth() / this.o;
        }
        this.h.layout(i, i2, this.k + i, this.h.getMeasuredHeight());
        this.g.layout((i + i5) - this.j, i2, i5 + i, i6 + i2);
        this.f.layout(i, i2, this.f.getMeasuredWidth() + i, this.f.getMeasuredHeight() + i2);
        this.i.layout(this.f.getMeasuredWidth() + i, i2, (this.f.getMeasuredWidth() + i) + this.i.getMeasuredWidth(), this.i.getMeasuredHeight() + i2);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int currentState = this.f.getCurrentState();
        if (currentState == 101) {
            return this.f.dispatchTouchEvent(motionEvent);
        }
        if (currentState == 103) {
            switch (action) {
                case 0:
                    this.f.getHitRect(this.b);
                    this.h.getHitRect(this.d);
                    if (this.d.contains(x, y)) {
                        this.e = 20001;
                        return this.h.dispatchTouchEvent(motionEvent);
                    } else if (this.b.contains(x, y)) {
                        this.e = 20002;
                        return this.f.dispatchTouchEvent(motionEvent);
                    }
                    break;
                default:
                    if (this.e == 20001) {
                        return this.h.dispatchTouchEvent(motionEvent);
                    }
                    if (this.e == 20002) {
                        return this.f.dispatchTouchEvent(motionEvent);
                    }
                    break;
            }
            return this.h.dispatchTouchEvent(motionEvent);
        } else if (currentState != 102) {
            return this.f.dispatchTouchEvent(motionEvent);
        } else {
            motionEvent.offsetLocation((float) (this.j - getMeasuredWidth()), 0.0f);
            switch (action) {
                case 0:
                    this.f.getHitRect(this.b);
                    this.g.getHitRect(this.c);
                    if (this.c.contains(x, y)) {
                        this.e = 20003;
                        return this.g.dispatchTouchEvent(motionEvent);
                    } else if (this.b.contains(x, y)) {
                        this.e = 20002;
                        return this.f.dispatchTouchEvent(motionEvent);
                    }
                    break;
            }
            if (this.e == 20003) {
                return this.g.dispatchTouchEvent(motionEvent);
            }
            if (this.e == 20002) {
                return this.f.dispatchTouchEvent(motionEvent);
            }
            return this.g.dispatchTouchEvent(motionEvent);
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        long drawingTime = getDrawingTime();
        if (this.p) {
            if (this.a) {
                canvas.save();
                canvas.translate((float) ((this.f.getPosition() / this.o) - this.m), 0.0f);
                drawChild(canvas, this.h, drawingTime);
                canvas.restore();
            } else {
                drawChild(canvas, this.h, drawingTime);
            }
        }
        if (this.q) {
            c.a("FlipContainerLayout", "drawRight Child ");
            if (this.a) {
                canvas.save();
                canvas.translate((float) ((this.f.getPosition() / this.o) + this.n), 0.0f);
                drawChild(canvas, this.g, drawingTime);
                canvas.restore();
            } else {
                drawChild(canvas, this.g, drawingTime);
            }
        }
        if (this.f == null) {
            try {
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        drawChild(canvas, this.f, drawingTime);
    }

    public void b() {
        if (this.l != null) {
            this.l.b();
        }
        if (!this.a) {
        }
    }

    public void a() {
        if (this.l != null) {
            this.l.a();
        }
        if (!this.a) {
        }
    }

    public void c() {
        if (this.l != null) {
            this.l.c();
        }
    }

    public void d() {
        this.p = false;
        this.q = false;
        if (this.l != null) {
            this.l.d();
        }
    }

    public void b(float f) {
        this.p = false;
        this.q = true;
        if (this.l != null) {
            this.l.b(f);
        }
    }

    public void a(float f) {
        this.q = false;
        this.p = true;
        if (this.l != null) {
            this.l.a(f);
        }
    }

    public void c(float f) {
        if (this.l != null) {
            this.l.c(f);
        }
    }

    public void e() {
        this.p = false;
        this.q = true;
        if (this.l != null) {
            this.l.e();
        }
    }

    public void f() {
        this.q = false;
        this.p = true;
        if (this.l != null) {
            this.l.f();
        }
    }
}

package com.qq.reader.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Handler.Callback;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.qq.reader.common.monitor.f;
import com.tencent.android.tpush.common.Constants;
import com.tencent.util.WeakReferenceHandler;
import java.util.ArrayList;

public class CircledViewPager extends ViewGroup implements Callback {
    private float a;
    private int b;
    private ax c;
    private ArrayList<a> d;
    private int e;
    private int f;
    private LayoutParams g;
    private long h;
    private long i;
    private float j;
    private View k;
    private VelocityTracker l;
    private boolean m;
    private c n;
    private DataSetObserver o;
    private boolean p;
    private WeakReferenceHandler q;
    private int r;
    private int s;

    protected class a {
        View a;
        int b;
        int c;
        final /* synthetic */ CircledViewPager d;

        public a(CircledViewPager circledViewPager, View view, int i, int i2) {
            this.d = circledViewPager;
            this.a = view;
            this.b = i;
            this.c = i2;
        }
    }

    private class b extends FrameLayout {
        final /* synthetic */ CircledViewPager a;
        private int b = 0;
        private Camera c = new Camera();
        private Matrix d = new Matrix();

        public b(CircledViewPager circledViewPager, Context context) {
            this.a = circledViewPager;
            super(context);
            setDrawingCacheEnabled(false);
            buildDrawingCache(false);
        }

        private void a(Canvas canvas, int i) {
            float abs;
            int abs2 = Math.abs(i);
            canvas.save();
            this.c.save();
            if (abs2 > (getMeasuredWidth() * 3) / 4) {
                abs = Math.abs(((float) (getMeasuredWidth() - abs2)) / (((float) getMeasuredWidth()) / 4.0f));
            } else if (abs2 < getMeasuredWidth() / 4) {
                abs = Math.abs(((float) abs2) / (((float) getMeasuredWidth()) / 4.0f));
            } else {
                abs = 1.0f;
            }
            this.c.translate(0.0f, 0.0f, abs * 100.0f);
            this.c.getMatrix(this.d);
            this.d.preTranslate((float) (-(getMeasuredWidth() / 2)), (float) (-(getMeasuredHeight() / 2)));
            this.d.postTranslate((float) (getMeasuredWidth() / 2), (float) (getMeasuredHeight() / 2));
            this.c.restore();
            canvas.concat(this.d);
        }

        protected void a(int i) {
            this.b = i;
            invalidate();
        }

        protected void dispatchDraw(Canvas canvas) {
            a(canvas, this.b);
            super.dispatchDraw(canvas);
            canvas.restore();
        }
    }

    public CircledViewPager(Context context) {
        this(context, null);
    }

    public CircledViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 4.0f;
        this.b = 0;
        this.d = new ArrayList();
        this.e = 0;
        this.f = 0;
        this.h = 0;
        this.i = 0;
        this.m = true;
        this.p = true;
        this.q = null;
        this.r = -1;
        this.s = -1;
        e();
    }

    public CircledViewPager(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    private void e() {
        this.a = getContext().getResources().getDisplayMetrics().density * 4.0f;
        this.g = new LayoutParams(-1, -1);
        this.q = new WeakReferenceHandler(this);
        setDrawingCacheEnabled(false);
        buildDrawingCache(false);
    }

    public void setScaleEnable(boolean z) {
        if (this.c != null) {
            throw new IllegalAccessError(" setScaleEnable() must be called before setAdpter ");
        }
        this.p = z;
    }

    public void setAdapter(ax axVar) {
        this.c = axVar;
        if (this.c != null) {
            if (this.o != null) {
                this.c.b(this.o);
            }
            this.o = new DataSetObserver(this) {
                final /* synthetic */ CircledViewPager a;

                {
                    this.a = r1;
                }

                public void onChanged() {
                    this.a.f = this.a.c.a();
                    this.a.l();
                }

                public void onInvalidated() {
                    this.a.invalidate();
                }
            };
            this.c.a(this.o);
        }
        this.f = axVar.a();
        this.e = 0;
        l();
    }

    public ax getAdapter() {
        return this.c;
    }

    public void setCurrentItem(int i, int i2) {
        this.e = i2;
        l();
        b(i, this.e);
    }

    public void setCurrentItem(int i, boolean z) {
        if (i != this.e) {
            if (Math.abs(i - this.e) == 1) {
                if (i > this.e) {
                    a();
                } else if (i < this.e) {
                    b();
                }
            } else if (this.e == 0 && i == this.d.size() - 1) {
                b();
            } else if (this.e == this.d.size() - 1 && i == 0) {
                a();
            } else if (Math.abs(i - this.e) <= 1) {
            }
        }
    }

    public int getCurrentItem() {
        return this.e;
    }

    public void setPagerChangedListener(c cVar) {
        this.n = cVar;
    }

    public void requestLayout() {
        super.requestLayout();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.r = x;
                this.s = y;
                d();
                return false;
            case 2:
                if (y - this.s == 0 || ((float) Math.abs(x - this.r)) / ((float) Math.abs(y - this.s)) <= 2.0f) {
                    return false;
                }
                return true;
            default:
                return super.onInterceptTouchEvent(motionEvent);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        f.d("event", "onTouchEvent " + motionEvent.toString());
        if (!this.m) {
            return true;
        }
        if (this.l == null) {
            this.l = VelocityTracker.obtain();
        }
        int action = motionEvent.getAction();
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (this.k == null) {
            return false;
        }
        this.b = this.k.getLeft();
        switch (action & 255) {
            case 1:
                this.r = -1;
                this.s = -1;
                this.b = this.k.getLeft();
                f();
                c();
                return true;
            case 2:
                this.l.addMovement(motionEvent);
                this.l.computeCurrentVelocity(1000);
                a(x - this.r);
                this.r = x;
                return true;
            case 3:
                this.r = -1;
                this.s = -1;
                f();
                this.b = this.k.getLeft();
                return true;
            default:
                return true;
        }
    }

    private void a(int i) {
        int i2 = 0;
        if (i > 0 && this.d.get(0) == null) {
            return;
        }
        if (i >= 0 || this.d.get(2) != null) {
            if (this.b == 0 && i != 0) {
                int i3 = this.e;
                int i4 = i < 0 ? this.e + 1 >= this.f ? (this.e + 1) - this.f : this.e + 1 : this.e + -1 < 0 ? (this.e + this.f) - 1 : this.e - 1;
                a(i3, i4);
            }
            this.b += i;
            a(((float) this.b) / ((float) getMeasuredWidth()), ((float) (this.b * (this.e + 1))) / ((float) (getMeasuredWidth() * this.c.a())));
            this.k.offsetLeftAndRight(this.b - this.k.getLeft());
            if (this.p) {
                while (i2 < this.d.size()) {
                    a aVar = (a) this.d.get(i2);
                    if (aVar != null) {
                        ((b) aVar.a).a(this.b);
                    }
                    i2++;
                }
            }
            invalidate();
        }
    }

    private void f() {
        long uptimeMillis = SystemClock.uptimeMillis();
        this.m = false;
        this.h = uptimeMillis;
        this.i = uptimeMillis;
        if (this.l != null) {
            float xVelocity = this.l.getXVelocity();
            this.j = xVelocity / 1000.0f;
            if (this.j < this.a) {
                this.j = this.a;
            }
            if (xVelocity > 0.0f) {
                this.j = Math.abs(this.j);
                g();
                return;
            }
            this.j = -1.0f * Math.abs(this.j);
            h();
        }
    }

    public void a() {
        this.b = this.k.getLeft();
        a(-1);
        long uptimeMillis = SystemClock.uptimeMillis();
        this.m = false;
        this.h = uptimeMillis;
        this.i = uptimeMillis;
        this.j = -this.a;
        h();
    }

    public void b() {
        this.b = this.k.getLeft();
        a(1);
        long uptimeMillis = SystemClock.uptimeMillis();
        this.m = false;
        this.h = uptimeMillis;
        this.i = uptimeMillis;
        this.j = this.a;
        g();
    }

    private void g() {
        int i = (int) (this.j * 12.0f);
        if (this.k.getRight() > getMeasuredWidth()) {
            if (this.k.getLeft() == getMeasuredWidth()) {
                this.q.removeMessages(Constants.CODE_LOGIC_REGISTER_IN_PROCESS);
                j();
                return;
            } else if (this.k.getLeft() + i > getMeasuredWidth()) {
                i = getMeasuredWidth() - this.k.getLeft();
            }
        } else if (this.k.getLeft() >= 0) {
            this.q.removeMessages(Constants.CODE_LOGIC_REGISTER_IN_PROCESS);
            i();
            return;
        } else if (this.k.getLeft() + i > 0) {
            i = -this.k.getLeft();
        }
        a(i);
        this.i += 12;
        this.q.removeMessages(10001);
        this.q.removeMessages(Constants.CODE_LOGIC_REGISTER_IN_PROCESS);
        this.q.sendMessageDelayed(this.q.obtainMessage(Constants.CODE_LOGIC_REGISTER_IN_PROCESS), 12);
    }

    private void h() {
        int i = (int) (this.j * 12.0f);
        if (this.k.getRight() < getMeasuredWidth()) {
            if (this.k.getRight() == 0) {
                this.q.removeMessages(10001);
                k();
                return;
            } else if (this.k.getRight() + i < 0) {
                i = -this.k.getRight();
            }
        } else if (this.k.getLeft() == 0) {
            this.q.removeMessages(10001);
            i();
            return;
        } else if (this.k.getLeft() + i < 0) {
            i = -this.k.getLeft();
        }
        a(i);
        this.i += 12;
        this.q.removeMessages(10001);
        this.q.removeMessages(Constants.CODE_LOGIC_REGISTER_IN_PROCESS);
        this.q.sendMessageDelayed(this.q.obtainMessage(10001), 12);
    }

    private void i() {
        this.m = true;
    }

    private void j() {
        int a = this.e % this.c.a();
        int i = this.e - 1;
        if (i < 0) {
            i += this.c.a();
        }
        setCurrentItem(a, i);
        this.m = true;
    }

    private void k() {
        setCurrentItem(this.e % this.c.a(), (this.e + 1) % this.c.a());
        this.m = true;
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        for (int i3 = 0; i3 < this.d.size(); i3++) {
            a aVar = (a) this.d.get(i3);
            if (aVar != null) {
                measureChild(aVar.a, size + 1073741824, size2 + 1073741824);
            }
        }
        setMeasuredDimension(MeasureSpec.getMode(i) + size, MeasureSpec.getMode(i2) + size2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        for (int i7 = 0; i7 < this.d.size(); i7++) {
            a aVar = (a) this.d.get(i7);
            if (aVar != null) {
                View view = aVar.a;
                int i8 = aVar.b - this.e;
                int paddingLeft = view.getPaddingLeft() + ((i5 - view.getMeasuredWidth()) / 2);
                int paddingTop = getPaddingTop() + ((i6 - view.getMeasuredHeight()) / 2);
                view.layout((i8 * i5) + paddingLeft, paddingTop, ((i8 * i5) + paddingLeft) + view.getMeasuredWidth(), view.getMeasuredHeight() + paddingTop);
            }
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        long drawingTime = getDrawingTime();
        for (int i = 0; i < this.d.size(); i++) {
            a aVar = (a) this.d.get(i);
            if (aVar != null) {
                View view = aVar.a;
                if (view.equals(this.k)) {
                    drawChild(canvas, view, drawingTime);
                } else {
                    canvas.save();
                    canvas.translate((float) this.k.getLeft(), 0.0f);
                    drawChild(canvas, view, drawingTime);
                    canvas.restore();
                }
            }
        }
    }

    private void l() {
        if (this.c == null) {
            return;
        }
        if (this.e >= 0 && this.e < this.c.a()) {
            m();
            b(this.e);
            for (int i = 0; i < this.d.size(); i++) {
                a aVar = (a) this.d.get(i);
                if (aVar != null) {
                    View view = aVar.a;
                    if (aVar.b == this.e) {
                        this.k = view;
                    }
                    addView(view, this.g);
                }
            }
        } else if (this.c.a() != 0 || this.e != 0) {
            throw new IndexOutOfBoundsException("current index is " + this.e + "  size is  " + this.c.a());
        }
    }

    private void m() {
        for (int i = 0; i < this.d.size(); i++) {
            a aVar = (a) this.d.get(i);
            if (aVar != null) {
                View view = aVar.a;
                int i2 = aVar.b;
                if (this.p) {
                    ((ViewGroup) view).removeAllViews();
                }
                removeView(view);
                this.c.a(i2, this);
            }
        }
    }

    private void b(int i) {
        if (this.c != null) {
            this.d.clear();
            a c = c(i);
            a e = e(i);
            a d = d(i);
            this.d.add(c);
            this.d.add(e);
            this.d.add(d);
        }
    }

    private a c(int i) {
        int i2 = i - 1;
        if (this.f <= 2) {
            return null;
        }
        return e(i2);
    }

    private a d(int i) {
        int i2 = i + 1;
        if (this.f <= 1) {
            return null;
        }
        return e(i2);
    }

    private a e(int i) {
        if (this.c == null) {
            throw new NullPointerException("adapter is null");
        }
        int i2;
        if (i >= this.f) {
            i2 = 0;
        } else {
            i2 = i;
        }
        if (i2 < 0) {
            i2 += this.f;
        }
        View b = this.c.b(i2, this);
        if (this.p) {
            View bVar = new b(this, getContext());
            bVar.addView(b);
            b.requestLayout();
            return new a(this, bVar, i, i2);
        }
        b.requestLayout();
        return new a(this, b, i, i2);
    }

    private void a(int i, int i2) {
        if (this.n != null) {
            this.n.a(i, i2);
        }
    }

    private void a(float f, float f2) {
        if (this.n != null) {
            this.n.a(-1.0f * f, -1.0f * f2);
        }
    }

    private void b(int i, int i2) {
        if (this.n != null) {
            this.n.b(i, i2);
        }
    }

    public void c() {
        d();
        if (this.q != null) {
            this.q.sendEmptyMessageDelayed(10004, 5000);
        }
    }

    public void d() {
        if (this.q != null) {
            this.q.removeMessages(10004);
        }
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 10001:
                h();
                break;
            case Constants.CODE_LOGIC_REGISTER_IN_PROCESS /*10002*/:
                g();
                break;
            case 10004:
                if (this.c.a() >= 2) {
                    a();
                    this.q.sendEmptyMessageDelayed(10004, 5000);
                    break;
                }
                return true;
        }
        return false;
    }
}

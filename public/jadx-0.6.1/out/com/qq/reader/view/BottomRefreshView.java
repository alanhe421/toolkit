package com.qq.reader.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.qq.reader.common.monitor.f;

public class BottomRefreshView extends ViewGroup {
    int a = 0;
    int b = 0;
    private final int c = 101;
    private final int d = 102;
    private final int e = 103;
    private final int f = 100;
    private final int g = 99;
    private int h = 99;
    private int i = 0;
    private View j;
    private View k;
    private int l = 0;
    private Handler m;
    private float n = 2.0f;
    private a o;
    private b p;
    private boolean q = true;
    private int r;
    private boolean s = false;
    private float t = 0.0f;

    public interface a {
        void a();

        void a(float f);

        void b();

        void b(float f);

        void c();

        void d();
    }

    public interface b {
        View a();

        boolean b();
    }

    private class c extends AsyncTask<Void, Integer, Void> {
        final /* synthetic */ BottomRefreshView a;

        private c(BottomRefreshView bottomRefreshView) {
            this.a = bottomRefreshView;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Void) obj);
        }

        protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
            a((Integer[]) objArr);
        }

        protected Void a(Void... voidArr) {
            com.qq.reader.common.monitor.debug.c.a("BottomRefreshView", "before doInBack ");
            this.a.i();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            com.qq.reader.common.monitor.debug.c.a("BottomRefreshView", "after doinBack ");
            return null;
        }

        protected void a(Integer... numArr) {
        }

        protected void a(Void voidR) {
            this.a.s = false;
            com.qq.reader.common.monitor.debug.c.a("BottomRefreshView", "onPostExecute ");
            this.a.f();
        }
    }

    public BottomRefreshView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public BottomRefreshView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public BottomRefreshView(Context context) {
        super(context);
        a();
    }

    @SuppressLint({"NewApi"})
    private void a() {
        this.n = 2.0f * getResources().getDisplayMetrics().density;
        getContext();
        try {
            if (isHardwareAccelerated()) {
                setDrawingCacheEnabled(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.m = new Handler(this) {
            final /* synthetic */ BottomRefreshView a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                if (message.what == 101) {
                    this.a.f();
                } else if (message.what == 102) {
                    this.a.e();
                } else if (message.what == 103) {
                    this.a.g();
                } else if (message.what == 100) {
                    this.a.d();
                }
            }
        };
    }

    public void setRefreshListener(a aVar) {
        this.o = aVar;
    }

    public void setRefreshView(b bVar) {
        this.p = bVar;
        this.k = this.p.a();
        if (this.k != null) {
            addView(this.k);
            requestLayout();
        }
    }

    public void setSpeed(float f) {
        this.n = f;
    }

    public void setBottomHeight(int i) {
        this.r = i;
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (this.j != null) {
            measureChild(this.j, size + 1073741824, this.r + 1073741824);
        }
        if (this.k != null) {
            measureChild(this.k, size + MeasureSpec.getMode(i), size2 + MeasureSpec.getMode(i2));
        }
        setMeasuredDimension(i, i2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.k != null) {
            this.k.layout(i, 0, i3, this.k.getMeasuredHeight());
        }
        if (this.j != null) {
            this.j.layout(i, this.k.getMeasuredHeight(), i3, this.j.getMeasuredHeight() + this.k.getMeasuredHeight());
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (this.p == null) {
            this.a = x;
            this.b = y;
            return true;
        }
        switch (action) {
            case 0:
                this.a = x;
                this.b = y;
                b();
                return false;
            case 2:
                if (!this.p.b() || Math.abs(y - this.b) <= Math.abs(x - this.a) || this.b <= y) {
                    f.d("BRV", "return false");
                    return false;
                }
                f.d("BRV", "return true 1");
                return true;
            default:
                return super.onInterceptTouchEvent(motionEvent);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.q) {
            motionEvent.offsetLocation(0.0f, (float) (-this.l));
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        com.qq.reader.common.monitor.debug.c.a("BottomRefreshView", "ontouchEvent  " + this.j);
        if (this.j == null) {
            return super.onTouchEvent(motionEvent);
        }
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0:
                this.a = x;
                this.b = y;
                break;
            case 1:
                this.a = 0;
                this.b = 0;
                c();
                break;
            case 2:
                a((y - this.b) / 2);
                this.a = x;
                this.b = y;
                break;
        }
        return true;
    }

    private void a(int i) {
        if (this.l == 0 && i < 0) {
            h();
        }
        if (this.l + i < this.i) {
            i = this.i - this.l;
        }
        if (i <= 0) {
            a(((float) this.l) / ((float) this.j.getMeasuredHeight()));
        } else {
            b(((float) this.l) / ((float) this.j.getMeasuredHeight()));
        }
        if (this.l <= this.i) {
            if (i > 0) {
                this.l += i;
            }
        } else if (this.l < 0) {
            this.l += i;
        } else if (i < 0) {
            this.l += i;
        }
        if (this.l == 0 && i > 0) {
            j();
        }
        invalidate();
    }

    private void b() {
        this.i = ((int) (((float) (this.j.getMeasuredHeight() * 3)) / 1.3f)) * -1;
    }

    private void c() {
        this.q = false;
        b();
        if (this.l <= (-this.j.getMeasuredHeight())) {
            e();
        } else {
            f();
        }
    }

    private void d() {
        if (this.s) {
            invalidate();
            this.m.removeMessages(101);
            this.m.removeMessages(102);
            this.m.removeMessages(103);
            this.m.removeMessages(100);
            this.m.sendMessageDelayed(this.m.obtainMessage(100), 5);
        }
    }

    private void e() {
        if (this.j == null) {
            this.q = true;
            f();
            return;
        }
        float f;
        long currentTimeMillis = System.currentTimeMillis();
        if (this.t == 0.0f) {
            f = 5.0f;
        } else {
            f = (((float) currentTimeMillis) - this.t) / 1000.0f;
        }
        int i = (int) (f * this.n);
        if (this.l == (-this.j.getMeasuredHeight())) {
            if (this.o != null) {
                this.o.b();
            }
            this.s = true;
            d();
            this.t = 0.0f;
            new c().execute(new Void[0]);
            return;
        }
        if (this.l + i > (-this.j.getMeasuredHeight())) {
            i = (-this.l) - this.j.getMeasuredHeight();
        }
        a(i);
        this.m.removeMessages(101);
        this.m.removeMessages(102);
        this.m.removeMessages(103);
        this.m.removeMessages(100);
        this.m.sendMessageDelayed(this.m.obtainMessage(102), 5);
    }

    private void f() {
        float f;
        com.qq.reader.common.monitor.debug.c.a("BottomRefreshView", "backAimation " + this.l);
        long currentTimeMillis = System.currentTimeMillis();
        if (this.t == 0.0f) {
            f = 5.0f;
        } else {
            f = (((float) currentTimeMillis) - this.t) / 1000.0f;
        }
        int i = (int) (f * this.n);
        if (this.l == 0) {
            this.t = 0.0f;
            this.q = true;
            return;
        }
        if (this.l + i > 0) {
            i = -this.l;
        }
        a(i);
        this.m.removeMessages(100);
        this.m.removeMessages(101);
        this.m.removeMessages(102);
        this.m.removeMessages(103);
        this.m.sendMessageDelayed(this.m.obtainMessage(101), 5);
    }

    private void g() {
        float f;
        long currentTimeMillis = System.currentTimeMillis();
        if (this.t == 0.0f) {
            f = 5.0f;
        } else {
            f = (((float) currentTimeMillis) - this.t) / 1000.0f;
        }
        int i = (int) (f * this.n);
        if (this.l == this.j.getMeasuredHeight()) {
            this.t = 0.0f;
            c();
            return;
        }
        if (this.l + i > this.j.getMeasuredHeight()) {
            i = this.j.getMeasuredHeight() - this.l;
        }
        a(i);
        this.m.removeMessages(100);
        this.m.removeMessages(101);
        this.m.removeMessages(102);
        this.m.removeMessages(103);
        this.m.sendMessageDelayed(this.m.obtainMessage(103), 5);
    }

    protected void dispatchDraw(Canvas canvas) {
        long drawingTime = getDrawingTime();
        canvas.save();
        canvas.translate(0.0f, (float) this.l);
        if (this.k != null) {
            drawChild(canvas, this.k, drawingTime);
        }
        if (this.j != null) {
            drawChild(canvas, this.j, drawingTime);
        }
        canvas.restore();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void h() {
        if (this.o != null) {
            this.o.a();
        }
    }

    private void a(float f) {
        if (this.o != null) {
            this.o.a(f);
        }
    }

    private void i() {
        if (this.o != null) {
            this.o.c();
        }
    }

    private void b(float f) {
        if (this.o != null) {
            this.o.b(f);
        }
    }

    private void j() {
        if (this.o != null) {
            this.o.d();
        }
    }
}

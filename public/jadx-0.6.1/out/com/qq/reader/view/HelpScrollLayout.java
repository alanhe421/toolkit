package com.qq.reader.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Scroller;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;

public class HelpScrollLayout extends ViewGroup {
    int a;
    private Scroller b;
    private VelocityTracker c;
    private int d;
    private int e;
    private final int f;
    private final int g;
    private int h;
    private int i;
    private float j;
    private float k;
    private a l;
    private Handler m;
    private final int n;

    public interface a {
        void a();

        void a(View view, float f, float f2);
    }

    public HelpScrollLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HelpScrollLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 0;
        this.f = ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE;
        this.g = 2;
        this.h = 0;
        this.n = 1;
        this.b = new Scroller(context);
        this.d = this.e;
        this.i = ViewConfiguration.get(getContext().getApplicationContext()).getScaledTouchSlop();
        this.m = new Handler(this) {
            final /* synthetic */ HelpScrollLayout a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                if (message.what == 1 && this.a.l != null) {
                    this.a.l.a();
                }
            }
        };
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z || this.a != getChildCount()) {
            this.a = getChildCount();
            int i5 = 0;
            for (int i6 = 0; i6 < this.a; i6++) {
                View childAt = getChildAt(i6);
                if (childAt.getVisibility() != 8) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    childAt.layout(i5, 0, i5 + measuredWidth, childAt.getMeasuredHeight());
                    i5 += measuredWidth;
                }
            }
        }
    }

    public void addView(View view, LayoutParams layoutParams) {
        super.addView(view, layoutParams);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = MeasureSpec.getSize(i);
        if (MeasureSpec.getMode(i) != 1073741824) {
            throw new IllegalStateException("ScrollLayout only canmCurScreen run at EXACTLY mode!");
        } else if (MeasureSpec.getMode(i2) != 1073741824) {
            throw new IllegalStateException("ScrollLayout only can run at EXACTLY mode!");
        } else {
            int childCount = getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                getChildAt(i3).measure(i, i2);
            }
            scrollTo(this.d * size, 0);
        }
    }

    public void a() {
        int width = getWidth();
        a((getScrollX() + (width / 2)) / width);
    }

    public void a(int i) {
        if (i == getChildCount()) {
            c();
            return;
        }
        int max = Math.max(0, Math.min(i, getChildCount() - 1));
        if (getScrollX() != getWidth() * max) {
            int width = (getWidth() * max) - getScrollX();
            this.b.startScroll(getScrollX(), 0, width, 0, Math.abs(width) * 2);
            this.d = max;
            invalidate();
        }
    }

    public int getCurScreenIndex() {
        return this.d;
    }

    public void computeScroll() {
        if (this.b.computeScrollOffset()) {
            scrollTo(this.b.getCurrX(), this.b.getCurrY());
            postInvalidate();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (getChildCount() != 1 || this.l == null) {
            if (this.c == null) {
                this.c = VelocityTracker.obtain();
            }
            this.c.addMovement(motionEvent);
            switch (motionEvent.getAction()) {
                case 0:
                    if (!this.b.isFinished()) {
                        this.b.abortAnimation();
                    }
                    this.j = x;
                    this.k = y;
                    this.h = 2;
                    break;
                case 1:
                    if (this.h == 2) {
                        a(this.d + 1);
                    } else if (this.h == 1) {
                        VelocityTracker velocityTracker = this.c;
                        velocityTracker.computeCurrentVelocity(1000);
                        int xVelocity = (int) velocityTracker.getXVelocity();
                        if (xVelocity > ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE && this.d > 0) {
                            a(this.d - 1);
                        } else if (xVelocity < -600 && this.d < getChildCount() - 1) {
                            a(this.d + 1);
                        } else if (xVelocity >= -600 || this.d != getChildCount() - 1) {
                            a();
                        } else {
                            c();
                        }
                    }
                    this.h = 0;
                    break;
                case 2:
                    int i = (int) (y - this.k);
                    if (Math.abs((int) (x - this.j)) >= this.i || Math.abs(i) >= this.i) {
                        this.h = 1;
                        i = (int) (this.j - x);
                        if (getScrollX() >= 0 && ((getScrollX() != 0 || i >= 0) && getScrollX() + i >= 0)) {
                            this.j = x;
                            scrollBy(i, 0);
                            break;
                        }
                    }
                case 3:
                    this.h = 0;
                    break;
                default:
                    break;
            }
        }
        this.l.a(this, x, y);
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 2 && this.h != 0) {
            return true;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (action) {
            case 0:
                this.j = x;
                this.k = y;
                this.h = this.b.isFinished() ? 0 : 1;
                break;
            case 1:
            case 3:
                this.h = 0;
                break;
            case 2:
                if (((int) Math.abs(this.j - x)) > this.i) {
                    this.h = 1;
                    break;
                }
                break;
        }
        if (this.h == 0) {
            return false;
        }
        return true;
    }

    public void a(a aVar) {
        this.l = aVar;
    }

    public void b() {
        this.l = null;
    }

    public void c() {
        if (getScrollX() != getChildCount() * getWidth()) {
            int childCount = (getChildCount() * getWidth()) - getScrollX();
            this.b.startScroll(getScrollX(), 0, childCount, 0, Math.abs(childCount) * 2);
            this.d = getChildCount();
            invalidate();
            this.m.sendEmptyMessageDelayed(1, (long) (Math.abs(childCount) * 3));
        }
    }
}

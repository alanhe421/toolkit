package com.qq.reader.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Scroller;
import android.widget.TextView;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;

public class PullDownView extends FrameLayout implements OnGestureListener, AnimationListener {
    public static int a;
    private ImageView b;
    private FrameLayout c;
    private TextView d;
    private TextView e;
    private GestureDetector f = new GestureDetector(this);
    private Animation g;
    private Animation h;
    private Animation i;
    private boolean j;
    private a k = new a(this);
    private int l;
    private String m;
    private long n = 0;
    private int o = 0;
    private int p = 1;
    private b q;
    private View r;
    private boolean s = true;
    private Drawable t;
    private Drawable u;
    private boolean v = true;
    private boolean w = true;
    private Rect x;
    private boolean y = false;

    private class a implements Runnable {
        final /* synthetic */ PullDownView a;
        private Scroller b;
        private int c;

        public a(PullDownView pullDownView) {
            this.a = pullDownView;
            this.b = new Scroller(pullDownView.getContext());
        }

        private void a() {
            this.a.removeCallbacks(this);
        }

        public void a(int i, int i2) {
            if (i == 0) {
                i--;
            }
            a();
            this.c = 0;
            this.b.startScroll(0, 0, -i, 0, i2);
            this.a.j = true;
            this.a.post(this);
        }

        public void run() {
            Scroller scroller = this.b;
            boolean computeScrollOffset = scroller.computeScrollOffset();
            int currX = scroller.getCurrX();
            this.a.a((float) (this.c - currX), false);
            this.a.f();
            if (computeScrollOffset) {
                this.c = currX;
                this.a.post(this);
                return;
            }
            this.a.j = false;
            this.a.removeCallbacks(this);
        }
    }

    public interface b {
        void a();

        void b();

        void c();
    }

    public PullDownView(Context context) {
        super(context);
        a();
        b();
    }

    private void a() {
        a = getResources().getDimensionPixelSize(R.dimen.updatebar_height);
        setDrawingCacheEnabled(false);
        setBackgroundDrawable(null);
        setClipChildren(false);
        this.f.setIsLongpressEnabled(true);
        this.t = getResources().getDrawable(R.drawable.z_arrow_up);
        this.u = getResources().getDrawable(R.drawable.z_arrow_down);
    }

    public void setPullDownloadReload(boolean z) {
        this.w = z;
    }

    public void setCanPullDown(boolean z) {
        this.v = z;
    }

    public PullDownView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
        b();
    }

    public void setListHeaderHeigh(int i) {
        this.o = i;
    }

    public void setUpdateHandle(b bVar) {
        this.q = bVar;
    }

    private void b() {
        this.i = AnimationUtils.loadAnimation(getContext(), R.anim.rotaterepeate);
        this.g = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_up);
        this.g.setAnimationListener(this);
        this.h = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_down);
        this.h.setAnimationListener(this);
        this.r = LayoutInflater.from(getContext()).inflate(R.layout.new_vw_update_bar, null);
        this.r.setVisibility(4);
        addView(this.r);
        this.b = new ImageView(getContext());
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.b.setScaleType(ScaleType.CENTER);
        this.b.setLayoutParams(layoutParams);
        this.b.setImageResource(R.drawable.pulldown_loading_80x80);
        this.c = (FrameLayout) this.r.findViewById(R.id.iv_content);
        this.c.addView(this.b);
        new FrameLayout.LayoutParams(-2, -2).gravity = 17;
        this.d = (TextView) findViewById(R.id.tv_title);
        this.e = (TextView) findViewById(R.id.tv_updata_date);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.s) {
            return super.dispatchTouchEvent(motionEvent);
        }
        if (this.j) {
            return true;
        }
        boolean onTouchEvent = this.f.onTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        if (action == 1) {
            onTouchEvent = c();
        } else if (action == 3) {
            onTouchEvent = c();
        }
        if (this.p == 6 || this.p == 7) {
            f();
            return super.dispatchTouchEvent(motionEvent);
        } else if ((onTouchEvent || this.p == 2 || this.p == 4 || this.p == 5 || this.p == 3) && getChildAt(1).getTop() != 0) {
            if (this.x != null) {
                if (!this.x.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                    motionEvent.setAction(3);
                }
            } else {
                motionEvent.setAction(3);
            }
            super.dispatchTouchEvent(motionEvent);
            f();
            return true;
        } else {
            f();
            return super.dispatchTouchEvent(motionEvent);
        }
    }

    private View a(View view) {
        if (view instanceof ViewPager) {
            return view;
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View a = a(((ViewGroup) view).getChildAt(i));
                if (a != null) {
                    return a;
                }
            }
        }
        return null;
    }

    private boolean c() {
        if (this.l >= 0) {
            return false;
        }
        switch (this.p) {
            case 2:
            case 3:
                if (Math.abs(this.l) < a) {
                    this.p = 3;
                }
                e();
                break;
            case 4:
            case 5:
                this.p = 5;
                if (!this.w) {
                    e();
                    break;
                }
                d();
                break;
        }
        return true;
    }

    private void d() {
        this.k.a((-this.l) - a, 300);
    }

    private void e() {
        this.k.a(-this.l, 300);
    }

    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (!this.v) {
            return true;
        }
        float f3 = (float) (((double) f2) * 0.5d);
        AdapterView adapterView = (AdapterView) getChildAt(1);
        if (adapterView == null || adapterView.getCount() == 0 || adapterView.getChildCount() == 0) {
            return false;
        }
        boolean z = adapterView.getFirstVisiblePosition() == 0;
        boolean z2;
        if (z) {
            if (adapterView.getChildAt(0).getTop() == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (this.q != null && this.w) {
                this.q.a();
            }
        } else {
            z2 = z;
        }
        if ((f3 >= 0.0f || !r0) && this.l >= 0) {
            return false;
        }
        return a(f3, true);
    }

    private boolean a(float f, boolean z) {
        if (this.p == 6) {
            if (f >= 0.0f) {
                if (z) {
                    this.p = 7;
                }
            }
            return true;
        }
        if (this.p != 7 || f >= 0.0f || (-this.l) < a) {
            this.l = (int) (((float) this.l) + f);
            if (this.l > 0) {
                this.l = 0;
            }
            if (z) {
                switch (this.p) {
                    case 1:
                        if (this.l < 0) {
                            this.p = 2;
                            break;
                        }
                        break;
                    case 2:
                        if (Math.abs(this.l) < a) {
                            if (this.l == 0) {
                                this.p = 1;
                                break;
                            }
                        }
                        this.p = 4;
                        break;
                        break;
                    case 3:
                    case 5:
                        if (z) {
                            if (Math.abs(this.l) >= a) {
                                this.p = 4;
                            } else if (Math.abs(this.l) < a) {
                                this.p = 2;
                            } else if (this.l == 0) {
                                this.p = 1;
                            }
                        } else if (this.l == 0) {
                            this.p = 1;
                        }
                        invalidate();
                        break;
                    case 4:
                        if (Math.abs(this.l) < a) {
                            this.p = 2;
                            break;
                        }
                        break;
                    case 6:
                        if (this.l == 0) {
                            this.p = 1;
                        }
                        invalidate();
                        break;
                    default:
                        break;
                }
            }
            if (this.p == 5) {
                this.p = 6;
                if (this.q != null && this.w) {
                    this.q.b();
                }
            } else if (this.p == 6 && this.l == 0) {
                this.p = 1;
                if (this.q != null && this.w) {
                    this.q.c();
                }
            } else if (this.p == 3 && this.l == 0) {
                this.p = 1;
            } else if (this.p == 7 && this.l == 0) {
                this.p = 1;
            }
            invalidate();
        }
        return true;
    }

    private void f() {
        View childAt = getChildAt(0);
        View childAt2 = getChildAt(1);
        this.e.setText(ao.e(this.n));
        switch (this.p) {
            case 1:
                if (childAt.getVisibility() != 4) {
                    childAt.setVisibility(4);
                }
                this.b.setVisibility(8);
                childAt2.offsetTopAndBottom(-childAt2.getTop());
                this.y = false;
                break;
            case 2:
            case 3:
                this.b.clearAnimation();
                this.b.setVisibility(0);
                this.y = false;
                childAt2.offsetTopAndBottom((-this.l) - childAt2.getTop());
                if (this.w) {
                    if (childAt.getVisibility() != 0) {
                        childAt.setVisibility(0);
                    }
                } else if (childAt.getVisibility() != 4) {
                    childAt.setVisibility(4);
                }
                childAt.offsetTopAndBottom(((-a) - this.l) - childAt.getTop());
                this.d.setText(getResources().getString(R.string.drop_dowm));
                break;
            case 4:
            case 5:
                childAt2.offsetTopAndBottom((-this.l) - childAt2.getTop());
                if (this.w) {
                    if (childAt.getVisibility() != 0) {
                        childAt.setVisibility(0);
                    }
                } else if (childAt.getVisibility() != 4) {
                    childAt.setVisibility(4);
                }
                childAt.offsetTopAndBottom(((-a) - this.l) - childAt.getTop());
                this.d.setText(getResources().getString(R.string.release_update));
                break;
            case 6:
            case 7:
                childAt2.offsetTopAndBottom((-this.l) - childAt2.getTop());
                int top = childAt.getTop();
                if (this.y) {
                    this.b.setVisibility(8);
                } else {
                    this.b.startAnimation(this.i);
                    this.m = null;
                }
                if (TextUtils.isEmpty(this.m)) {
                    this.d.setText(getResources().getString(R.string.doing_update));
                } else {
                    this.d.setText(this.m);
                }
                childAt.offsetTopAndBottom(((-a) - this.l) - top);
                if (!this.w) {
                    if (childAt.getVisibility() != 4) {
                        childAt.setVisibility(4);
                        break;
                    }
                } else if (childAt.getVisibility() != 0) {
                    childAt.setVisibility(0);
                    break;
                }
                break;
        }
        invalidate();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        getChildAt(0).layout(0, (-a) - this.l, getMeasuredWidth(), -this.l);
        getChildAt(1).layout(0, -this.l, getMeasuredWidth(), getMeasuredHeight() - this.l);
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View a = a(getChildAt(i5));
            if (a != null) {
                int[] iArr = new int[2];
                this.x = new Rect();
                int[] iArr2 = new int[2];
                a.getLocationInWindow(iArr2);
                getLocationInWindow(iArr);
                this.x.left = iArr2[0];
                this.x.top = iArr2[1] - iArr[1];
                this.x.right = iArr2[0] + a.getMeasuredWidth();
                this.x.bottom = (a.getMeasuredHeight() + iArr2[1]) - iArr[1];
            } else {
                this.x = null;
            }
        }
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public void setUpdateDate(long j) {
        this.n = j;
        if (this.e != null) {
            this.e.setText(ao.e(this.n));
        }
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        if (this.p != 2 && this.p != 3) {
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void setEnable(boolean z) {
        this.s = z;
        invalidate();
    }
}

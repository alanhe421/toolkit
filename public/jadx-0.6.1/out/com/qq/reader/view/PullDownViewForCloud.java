package com.qq.reader.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.Scroller;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;

public class PullDownViewForCloud extends FrameLayout implements OnGestureListener, AnimationListener {
    public static int a;
    private ImageView b;
    private ProgressBar c;
    private FrameLayout d;
    private TextView e;
    private GestureDetector f = new GestureDetector(this);
    private Animation g;
    private Animation h;
    private boolean i;
    private a j = new a(this);
    private int k;
    private int l = 0;
    private int m = 1;
    private b n;
    private String o;
    private View p;
    private boolean q = true;
    private Drawable r;
    private Drawable s;

    private class a implements Runnable {
        final /* synthetic */ PullDownViewForCloud a;
        private Scroller b;
        private int c;

        public a(PullDownViewForCloud pullDownViewForCloud) {
            this.a = pullDownViewForCloud;
            this.b = new Scroller(pullDownViewForCloud.getContext());
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
            this.a.i = true;
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
            this.a.i = false;
            this.a.removeCallbacks(this);
        }
    }

    public interface b {
        void a();

        void b();
    }

    public PullDownViewForCloud(Context context) {
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
        this.r = getResources().getDrawable(R.drawable.z_arrow_up);
        this.s = getResources().getDrawable(R.drawable.z_arrow_down);
    }

    public PullDownViewForCloud(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
        b();
    }

    public void setListHeaderHeigh(int i) {
        this.l = i;
    }

    public void setUpdateHandle(b bVar) {
        this.n = bVar;
    }

    private void b() {
        this.g = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_up);
        this.g.setAnimationListener(this);
        this.h = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_down);
        this.h.setAnimationListener(this);
        this.p = LayoutInflater.from(getContext()).inflate(R.layout.vw_update_bar, null);
        this.p.setVisibility(4);
        addView(this.p);
        this.b = new ImageView(getContext());
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.b.setScaleType(ScaleType.FIT_CENTER);
        this.b.setLayoutParams(layoutParams);
        this.b.setImageResource(R.drawable.z_arrow_down);
        this.d = (FrameLayout) getChildAt(0).findViewById(R.id.iv_content);
        this.d.addView(this.b);
        layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.c = new ProgressBar(getContext(), null, 16842873);
        this.c.setIndeterminateDrawable(getResources().getDrawable(R.drawable.progress_40_blue_anim));
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.updatebar_padding);
        this.c.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        this.c.setLayoutParams(layoutParams);
        this.d.addView(this.c);
        this.e = (TextView) findViewById(R.id.tv_title);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.q) {
            return super.dispatchTouchEvent(motionEvent);
        }
        if (this.i) {
            return true;
        }
        boolean onTouchEvent = this.f.onTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        if (action == 1) {
            onTouchEvent = c();
        } else if (action == 3) {
            onTouchEvent = c();
        }
        if (this.m == 6 || this.m == 7) {
            f();
            return super.dispatchTouchEvent(motionEvent);
        } else if ((onTouchEvent || this.m == 2 || this.m == 4 || this.m == 5 || this.m == 3) && getChildAt(1).getTop() != 0) {
            motionEvent.setAction(3);
            super.dispatchTouchEvent(motionEvent);
            f();
            return true;
        } else {
            f();
            return super.dispatchTouchEvent(motionEvent);
        }
    }

    private boolean c() {
        if (this.k >= 0) {
            return false;
        }
        switch (this.m) {
            case 2:
            case 3:
                if (Math.abs(this.k) < a) {
                    this.m = 3;
                }
                e();
                break;
            case 4:
            case 5:
                this.m = 5;
                d();
                break;
        }
        return true;
    }

    private void d() {
        this.j.a((-this.k) - a, 300);
    }

    private void e() {
        this.j.a(-this.k, 400);
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
            if (this.n != null) {
                this.n.b();
            }
        } else {
            z2 = z;
        }
        if ((f3 >= 0.0f || !r0) && this.k >= 0) {
            return false;
        }
        return a(f3, true);
    }

    private boolean a(float f, boolean z) {
        if (this.m == 6) {
            if (f >= 0.0f) {
                if (z) {
                    this.m = 7;
                }
            }
            return true;
        }
        if (this.m != 7 || f >= 0.0f || (-this.k) < a) {
            this.k = (int) (((float) this.k) + f);
            if (this.k > 0) {
                this.k = 0;
            }
            if (z) {
                switch (this.m) {
                    case 1:
                        if (this.k < 0) {
                            this.m = 2;
                            this.c.setVisibility(4);
                            this.b.setVisibility(0);
                            break;
                        }
                        break;
                    case 2:
                        if (Math.abs(this.k) < a) {
                            if (this.k == 0) {
                                this.m = 1;
                                break;
                            }
                        }
                        this.m = 4;
                        this.c.setVisibility(4);
                        this.b.setVisibility(0);
                        this.b.startAnimation(this.g);
                        break;
                        break;
                    case 3:
                    case 5:
                        if (z) {
                            if (Math.abs(this.k) >= a) {
                                this.m = 4;
                                this.c.setVisibility(4);
                                this.b.setVisibility(0);
                                this.b.startAnimation(this.g);
                            } else if (Math.abs(this.k) < a) {
                                this.m = 2;
                                this.c.setVisibility(4);
                                this.b.setVisibility(0);
                                this.b.startAnimation(this.h);
                            } else if (this.k == 0) {
                                this.m = 1;
                            }
                        } else if (this.k == 0) {
                            this.m = 1;
                        }
                        invalidate();
                        break;
                    case 4:
                        if (Math.abs(this.k) < a) {
                            this.m = 2;
                            this.c.setVisibility(4);
                            this.b.setVisibility(0);
                            this.b.startAnimation(this.h);
                            break;
                        }
                        break;
                    case 6:
                        if (this.k == 0) {
                            this.m = 1;
                        }
                        invalidate();
                        break;
                    default:
                        break;
                }
            }
            if (this.m == 5) {
                this.m = 6;
                if (this.n != null) {
                    this.n.a();
                }
            } else if (this.m == 6 && this.k == 0) {
                this.m = 1;
            } else if (this.m == 3 && this.k == 0) {
                this.m = 1;
            } else if (this.m == 7 && this.k == 0) {
                this.m = 1;
            }
            invalidate();
        }
        return true;
    }

    private void f() {
        View childAt = getChildAt(0);
        View childAt2 = getChildAt(1);
        if (this.o == null) {
            this.o = "";
        }
        switch (this.m) {
            case 1:
                if (childAt.getVisibility() != 4) {
                    childAt.setVisibility(4);
                }
                childAt2.offsetTopAndBottom(-childAt2.getTop());
                break;
            case 2:
            case 3:
                childAt2.offsetTopAndBottom((-this.k) - childAt2.getTop());
                if (childAt.getVisibility() != 0) {
                    childAt.setVisibility(0);
                }
                childAt.offsetTopAndBottom(((-a) - this.k) - childAt.getTop());
                this.e.setText(getResources().getString(R.string.drop_dowm) + "\n" + this.o);
                break;
            case 4:
            case 5:
                childAt2.offsetTopAndBottom((-this.k) - childAt2.getTop());
                if (childAt.getVisibility() != 0) {
                    childAt.setVisibility(0);
                }
                childAt.offsetTopAndBottom(((-a) - this.k) - childAt.getTop());
                this.e.setText(getResources().getString(R.string.release_update) + "\n" + this.o);
                break;
            case 6:
            case 7:
                childAt2.offsetTopAndBottom((-this.k) - childAt2.getTop());
                int top = childAt.getTop();
                if (this.c.getVisibility() != 0) {
                    this.c.setVisibility(0);
                }
                if (this.b.getVisibility() != 4) {
                    this.b.setVisibility(4);
                }
                this.e.setText(getResources().getString(R.string.doing_update) + "\n" + this.o);
                childAt.offsetTopAndBottom(((-a) - this.k) - top);
                if (childAt.getVisibility() != 0) {
                    childAt.setVisibility(0);
                    break;
                }
                break;
        }
        invalidate();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        getChildAt(0).layout(0, (-a) - this.k, getMeasuredWidth(), -this.k);
        getChildAt(1).layout(0, -this.k, getMeasuredWidth(), getMeasuredHeight() - this.k);
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public void setUpdateDate(String str) {
        this.o = str;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        if (this.m == 2 || this.m == 3) {
            this.b.setImageDrawable(this.s);
        } else {
            this.b.setImageDrawable(this.r);
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void setEnable(boolean z) {
        this.q = z;
        invalidate();
    }
}

package com.qq.reader.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.Scroller;
import com.tencent.feedback.proguard.R;
import com.tencent.qalsdk.im_open.http;

public class BounceList extends ListView implements OnGestureListener {
    public static int a;
    private GestureDetector b = new GestureDetector(this);
    private boolean c;
    private a d = new a(this);
    private int e;
    private int f = 1;
    private int g = 0;

    private class a implements Runnable {
        final /* synthetic */ BounceList a;
        private Scroller b;
        private int c;

        public a(BounceList bounceList) {
            this.a = bounceList;
            this.b = new Scroller(bounceList.getContext());
        }

        private void a() {
            this.a.removeCallbacks(this);
        }

        public void a(int i, int i2) {
            if (i == 0) {
                if (this.a.g == 1) {
                    i--;
                } else if (this.a.g == 2) {
                    i++;
                }
            }
            a();
            this.c = 0;
            this.b.startScroll(0, 0, -i, 0, i2);
            this.a.c = true;
            this.a.post(this);
        }

        public void run() {
            Scroller scroller = this.b;
            boolean computeScrollOffset = scroller.computeScrollOffset();
            int currX = scroller.getCurrX();
            this.a.a((float) (this.c - currX), false);
            this.a.d();
            if (computeScrollOffset) {
                this.c = currX;
                this.a.post(this);
                return;
            }
            this.a.c = false;
            this.a.removeCallbacks(this);
        }
    }

    public BounceList(Context context) {
        super(context);
        a();
    }

    private void a() {
        a = getResources().getDimensionPixelSize(R.dimen.updatebar_height);
        setDrawingCacheEnabled(false);
        setBackgroundDrawable(null);
        setClipChildren(false);
        this.b.setIsLongpressEnabled(true);
    }

    public BounceList(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        float f3 = (float) (((double) f2) * 0.5d);
        if (getCount() == 0 || getChildCount() == 0) {
            return false;
        }
        boolean z;
        boolean z2 = getFirstVisiblePosition() == 0;
        if (z2) {
            z2 = getChildAt(0).getTop() == 0;
        }
        if (getFirstVisiblePosition() + getChildCount() == getCount()) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (getChildAt(getChildCount() - 1).getBottom() == getHeight()) {
                z = true;
            } else {
                z = false;
            }
        }
        if ((f3 < 0.0f && r0) || this.e < 0) {
            this.g = 1;
            return a(f3, true);
        } else if ((f3 <= 0.0f || !r3) && this.e <= 0) {
            return false;
        } else {
            this.g = 2;
            return a(f3, true);
        }
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.c) {
            return true;
        }
        boolean onTouchEvent = this.b.onTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        if (action == 1) {
            onTouchEvent = b();
        } else if (action == 3) {
            onTouchEvent = b();
        }
        if ((onTouchEvent || this.f == 2 || this.f == 4 || this.f == 5 || this.f == 3) && !(getTop() == 0 && getBottom() == getHeight())) {
            motionEvent.setAction(3);
            super.dispatchTouchEvent(motionEvent);
            d();
            return true;
        }
        d();
        return super.dispatchTouchEvent(motionEvent);
    }

    private boolean b() {
        if (this.g == 1 && this.e >= 0) {
            return false;
        }
        if (this.g == 2 && this.e <= 0) {
            return false;
        }
        switch (this.f) {
            case 2:
            case 3:
            case 4:
                this.f = 3;
                c();
                return true;
            default:
                return false;
        }
    }

    private void c() {
        this.d.a(-this.e, http.Internal_Server_Error);
    }

    private boolean a(float f, boolean z) {
        this.e = (int) (((float) this.e) + f);
        if (this.g == 1 && this.e > 0) {
            this.e = 0;
        } else if (this.g == 2 && this.e < 0) {
            this.e = 0;
        }
        if (z) {
            switch (this.f) {
                case 1:
                    if (Math.abs(this.e) > 0) {
                        this.f = 2;
                        break;
                    }
                    break;
                case 2:
                    if (Math.abs(this.e) < a) {
                        if (this.e == 0) {
                            this.f = 1;
                            break;
                        }
                    }
                    this.f = 4;
                    break;
                    break;
                case 3:
                    if (z) {
                        if (Math.abs(this.e) < a) {
                            this.f = 2;
                        } else if (this.e == 0) {
                            this.f = 1;
                        }
                    } else if (this.e == 0) {
                        this.f = 1;
                    }
                    invalidate();
                    break;
                case 4:
                    if (Math.abs(this.e) < a) {
                        this.f = 2;
                        break;
                    }
                    break;
                default:
                    break;
            }
        }
        if (this.f == 3 && this.e == 0) {
            this.f = 1;
        }
        invalidate();
        return true;
    }

    private void d() {
        switch (this.f) {
            case 1:
                scrollBy(0, -getScrollY());
                break;
            case 2:
            case 3:
                scrollBy(0, this.e - getScrollY());
                break;
            case 4:
            case 5:
                scrollBy(0, this.e - getScrollY());
                break;
        }
        invalidate();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            return super.onTouchEvent(motionEvent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

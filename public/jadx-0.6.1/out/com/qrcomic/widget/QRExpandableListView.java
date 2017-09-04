package com.qrcomic.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

public class QRExpandableListView extends XExpandableListView implements Callback {
    private boolean a = false;
    private byte b;
    private View c;
    private View d;
    private int e;
    private int f;
    private int g;
    private int h;
    private boolean i;
    private VelocityTracker j;
    private int k;
    private ViewConfiguration l;
    private int m;
    private Scroller n;
    private Handler o;
    private View p;
    private int q;
    private a r;

    public interface a {
        void a(View view);

        void b(View view);
    }

    public QRExpandableListView(Context context) {
        super(context);
        a(context);
    }

    public QRExpandableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public QRExpandableListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.n = new Scroller(context);
        this.l = ViewConfiguration.get(context);
        this.q = this.l.getScaledTouchSlop();
        this.k = this.l.getScaledMinimumFlingVelocity() * 4;
        this.e = -1;
        this.g = -1;
        this.f = -1;
        this.h = -1;
        this.o = new Handler(Looper.getMainLooper(), this);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r8) {
        /*
        r7 = this;
        r5 = -1;
        r6 = -3;
        r4 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r2 = 1;
        r1 = 0;
        r0 = r7.a;
        if (r0 != 0) goto L_0x000f;
    L_0x000a:
        r0 = super.onInterceptTouchEvent(r8);
    L_0x000e:
        return r0;
    L_0x000f:
        r0 = r8.getX();
        r0 = r0 + r4;
        r3 = (int) r0;
        r0 = r8.getY();
        r0 = r0 + r4;
        r0 = (int) r0;
        r4 = r8.getAction();
        switch(r4) {
            case 0: goto L_0x002a;
            case 1: goto L_0x00e0;
            case 2: goto L_0x00a9;
            case 3: goto L_0x00e0;
            default: goto L_0x0022;
        };
    L_0x0022:
        r0 = r1;
    L_0x0023:
        if (r0 != 0) goto L_0x000e;
    L_0x0025:
        r0 = super.onInterceptTouchEvent(r8);
        goto L_0x000e;
    L_0x002a:
        r7.b = r2;
        r7.e = r3;
        r7.g = r3;
        r7.f = r0;
        r7.h = r0;
        r0 = r7.d;
        r7.c = r0;
        r0 = r7.c;
        r0 = r7.b(r0);
        r7.i = r0;
        r0 = r7.i;
        if (r0 != 0) goto L_0x005b;
    L_0x0044:
        r0 = r1;
    L_0x0045:
        r4 = r7.getChildCount();
        if (r0 >= r4) goto L_0x005b;
    L_0x004b:
        r4 = r7.getChildAt(r0);
        r5 = r7.b(r4);
        r7.i = r5;
        r5 = r7.i;
        if (r5 == 0) goto L_0x00a6;
    L_0x0059:
        r7.c = r4;
    L_0x005b:
        r7.m = r1;
        r0 = 0;
        r7.d = r0;
        r0 = r7.f;
        r4 = r7.e;
        r0 = r7.pointToPosition(r4, r0);
        if (r0 < 0) goto L_0x0091;
    L_0x006a:
        r4 = r7.getFirstVisiblePosition();
        r0 = r0 - r4;
        r0 = r7.getChildAt(r0);
        r7.d = r0;
        r0 = r7.d;
        if (r0 == 0) goto L_0x0091;
    L_0x0079:
        r0 = r7.d;
        r0 = r0.getTag(r6);
        r0 = r0 instanceof java.lang.Integer;
        if (r0 == 0) goto L_0x0091;
    L_0x0083:
        r0 = r7.d;
        r0 = r0.getTag(r6);
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r7.m = r0;
    L_0x0091:
        r0 = r7.i;
        if (r0 == 0) goto L_0x00a3;
    L_0x0095:
        r0 = r7.d;
        r4 = r7.c;
        if (r0 != r4) goto L_0x00a2;
    L_0x009b:
        r0 = (float) r3;
        r0 = r7.a(r0);
        if (r0 == 0) goto L_0x00a3;
    L_0x00a2:
        r1 = r2;
    L_0x00a3:
        r0 = r1;
        goto L_0x0023;
    L_0x00a6:
        r0 = r0 + 1;
        goto L_0x0045;
    L_0x00a9:
        r4 = r7.m;
        if (r4 <= 0) goto L_0x0022;
    L_0x00ad:
        r4 = r7.i;
        if (r4 == 0) goto L_0x00c1;
    L_0x00b1:
        r4 = r7.c;
        r5 = r7.d;
        if (r4 != r5) goto L_0x00c1;
    L_0x00b7:
        r4 = (float) r3;
        r4 = r7.a(r4);
        if (r4 != 0) goto L_0x00c1;
    L_0x00be:
        r0 = r1;
        goto L_0x0023;
    L_0x00c1:
        r4 = r7.b;
        if (r4 != r2) goto L_0x00d8;
    L_0x00c5:
        r4 = r7.e;
        r3 = r3 - r4;
        r3 = java.lang.Math.abs(r3);
        r3 = (float) r3;
        r4 = r7.f;
        r0 = r0 - r4;
        r0 = java.lang.Math.abs(r0);
        r0 = (float) r0;
        r7.a(r3, r0);
    L_0x00d8:
        r0 = r7.b;
        r3 = 2;
        if (r0 != r3) goto L_0x0022;
    L_0x00dd:
        r0 = r2;
        goto L_0x0023;
    L_0x00e0:
        r7.e = r5;
        r7.g = r5;
        r7.f = r5;
        r7.h = r5;
        goto L_0x0022;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qrcomic.widget.QRExpandableListView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (!this.a) {
            return super.onTouchEvent(motionEvent);
        }
        a(motionEvent);
        int x = (int) (motionEvent.getX() + 0.5f);
        int y = (int) (motionEvent.getY() + 0.5f);
        switch (motionEvent.getAction()) {
            case 0:
                if (this.i && this.c != this.d) {
                    c(this.c);
                    break;
                }
            case 1:
            case 3:
                if (this.i) {
                    boolean z2 = this.d != this.c || a((float) x);
                    if (this.b == (byte) 2) {
                        b();
                        z = true;
                    } else if (Math.abs(getScrollY()) <= this.q) {
                        if (b(this.c)) {
                            c(this.d);
                            z = z2;
                        } else {
                            z = z2;
                        }
                    }
                } else if (this.m != 0 && this.b == (byte) 2) {
                    b();
                    z = true;
                }
                d();
                this.e = -1;
                this.g = -1;
                this.f = -1;
                this.h = -1;
                break;
            case 2:
                if (this.i && this.d == this.c) {
                    if (this.b == (byte) 1) {
                        a((float) Math.abs(x - this.e), (float) Math.abs(y - this.f));
                    }
                    if (this.b == (byte) 2) {
                        a(x, y, this.d, this.m);
                        z = true;
                    } else if (this.b == (byte) 3) {
                        c(this.d);
                    }
                } else if (this.m != 0) {
                    if (this.b == (byte) 1) {
                        a((float) Math.abs(x - this.e), (float) Math.abs(y - this.f));
                    }
                    if (this.b == (byte) 2) {
                        a(x, y, this.d, this.m);
                        z = true;
                    }
                }
                this.g = x;
                this.h = y;
                break;
        }
        if (!z) {
            return super.onTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() != 2 && motionEvent.getAction() != 1) {
            return z;
        }
        motionEvent.setAction(3);
        super.onTouchEvent(motionEvent);
        return z;
    }

    private boolean a(float f) {
        return f < ((float) (getWidth() - this.m));
    }

    private void b() {
        int scrollVelocity = getScrollVelocity();
        int scrollX = this.d.getScrollX();
        if (scrollVelocity > this.k) {
            c(this.d);
        } else if (scrollVelocity < (-this.k)) {
            a(this.d);
        } else if (scrollVelocity > 0 && ((float) scrollX) < ((float) this.m) * 0.7f) {
            c(this.d);
        } else if (scrollVelocity < 0 && ((float) scrollX) > ((float) this.m) * 0.3f) {
            a(this.d);
        } else if (this.i && ((float) scrollX) < ((float) this.m) * 0.7f) {
            c(this.d);
        } else if (this.i && ((float) scrollX) > ((float) this.m) * 0.3f) {
            a(this.d);
        } else if (((float) scrollX) > ((float) this.m) * 0.5f) {
            a(this.d);
        } else {
            c(this.d);
        }
    }

    private void a(View view) {
        if (view != null) {
            int intValue;
            int scrollX = view.getScrollX();
            int i = this.m;
            if (i == 0 && (view.getTag(-3) instanceof Integer)) {
                intValue = ((Integer) view.getTag(-3)).intValue();
            } else {
                intValue = i;
            }
            if (scrollX != intValue) {
                if (!(this.p == view || this.p == null)) {
                    this.p.scrollTo(0, 0);
                }
                c();
                this.p = view;
                int i2 = intValue - scrollX;
                this.n.startScroll(scrollX, 0, i2, 0, a(intValue, i2));
                this.o.sendEmptyMessage(4);
            } else if (this.r != null) {
                this.r.a(view);
            }
        }
    }

    private void a(int i, int i2, View view, int i3) {
        int scrollX = view.getScrollX() - (i - this.g);
        if (scrollX <= i3) {
            if (scrollX < 0) {
                i3 = 0;
            } else {
                i3 = scrollX;
            }
        }
        view.scrollTo(i3, 0);
    }

    public void setDragEnable(boolean z) {
        this.a = z;
        if (!this.a) {
            a();
        }
    }

    private boolean a(float f, float f2) {
        if (f <= ((float) this.q) && f2 <= ((float) this.q)) {
            return false;
        }
        if (f <= ((float) this.q) || f2 / f >= 0.6f) {
            this.b = (byte) 3;
            return true;
        }
        this.b = (byte) 2;
        return true;
    }

    private boolean b(View view) {
        if (view == null || view.getScrollX() < this.q) {
            return false;
        }
        return true;
    }

    public void a() {
        if (!b(this.d)) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (b(childAt)) {
                    this.d = childAt;
                    break;
                }
            }
        }
        c(this.d);
    }

    private void c(View view) {
        int scrollX;
        if (view != null) {
            scrollX = view.getScrollX();
        } else {
            scrollX = 0;
        }
        if (scrollX != 0) {
            int intValue;
            if (view.getTag(-3) instanceof Integer) {
                intValue = ((Integer) view.getTag(-3)).intValue();
            } else {
                intValue = 0;
            }
            if (!(this.p == view || this.p == null)) {
                this.p.scrollTo(0, 0);
            }
            c();
            this.p = view;
            int i = -scrollX;
            this.n.startScroll(scrollX, 0, i, 0, a(intValue, i));
            this.o.sendEmptyMessage(5);
        } else if (this.r != null) {
            this.r.b(this.p);
        }
    }

    private void c() {
        this.o.removeMessages(5);
        this.o.removeMessages(4);
        this.p = null;
    }

    private int a(int i, int i2) {
        if (i > 0) {
            return ((int) ((((float) Math.abs(i2)) / ((float) i)) * 300.0f)) + 50;
        }
        return 300;
    }

    private void a(MotionEvent motionEvent) {
        if (this.j == null) {
            this.j = VelocityTracker.obtain();
        }
        this.j.addMovement(motionEvent);
    }

    private void d() {
        if (this.j != null) {
            this.j.recycle();
            this.j = null;
        }
    }

    private int getScrollVelocity() {
        this.j.computeCurrentVelocity(1000);
        return (int) this.j.getXVelocity();
    }

    public boolean handleMessage(Message message) {
        boolean computeScrollOffset;
        float currX;
        switch (message.what) {
            case 4:
                computeScrollOffset = this.n.computeScrollOffset();
                currX = (float) this.n.getCurrX();
                if (this.p != null) {
                    this.p.scrollTo((int) currX, 0);
                }
                if (computeScrollOffset) {
                    this.o.sendEmptyMessageDelayed(4, 16);
                    return true;
                }
                if (this.r != null) {
                    this.r.a(this.p);
                }
                this.p = null;
                return true;
            case 5:
                computeScrollOffset = this.n.computeScrollOffset();
                currX = (float) this.n.getCurrX();
                if (this.p != null) {
                    this.p.scrollTo((int) currX, 0);
                }
                if (computeScrollOffset) {
                    this.o.sendEmptyMessageDelayed(5, 16);
                    return true;
                }
                if (this.r != null) {
                    this.r.b(this.p);
                }
                this.p = null;
                return true;
            default:
                return false;
        }
    }

    public void setRightIconMenuListener(a aVar) {
        this.r = aVar;
    }
}

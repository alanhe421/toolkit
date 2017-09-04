package com.qq.reader.module.bookstore.qweb.channel;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.monitor.j;
import com.tencent.feedback.proguard.R;
import com.tencent.qalsdk.im_open.http;

public class DragGrid extends GridView {
    public int a;
    public int b;
    public int c;
    public int d;
    int e;
    int f;
    public int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private View n = null;
    private WindowManager o = null;
    private LayoutParams p = null;
    private boolean q = false;
    private int r;
    private double s = 1.2d;
    private Vibrator t;
    private int u = 15;
    private String v;
    private int w = 4;

    public DragGrid(Context context) {
        super(context);
        a(context);
    }

    public DragGrid(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public DragGrid(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public void a(Context context) {
        this.t = (Vibrator) context.getSystemService("vibrator");
    }

    public void setAutoNumColumns(int i) {
        this.w = i;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.a = (int) motionEvent.getX();
            this.b = (int) motionEvent.getY();
            this.c = (int) motionEvent.getX();
            this.d = (int) motionEvent.getY();
            setOnItemClickListener(motionEvent);
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!(this.n == null || this.g == -1)) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            switch (motionEvent.getAction()) {
                case 0:
                    this.a = (int) motionEvent.getX();
                    this.c = (int) motionEvent.getX();
                    this.b = (int) motionEvent.getY();
                    this.d = (int) motionEvent.getY();
                    break;
                case 1:
                    a();
                    b(x, y);
                    requestDisallowInterceptTouchEvent(false);
                    break;
                case 2:
                    a(x, y, (int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                    if (!this.q) {
                        a(x, y);
                    }
                    if (pointToPosition(x, y) != -1) {
                        break;
                    }
                    break;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    private void a(int i, int i2, int i3, int i4) {
        if (this.n != null) {
            this.p.alpha = 0.6f;
            this.p.x = i3 - this.h;
            this.p.y = i4 - this.i;
            this.o.updateViewLayout(this.n, this.p);
        }
    }

    private void b(int i, int i2) {
        this.j = pointToPosition(i, i2);
        b bVar = (b) getAdapter();
        bVar.b(true);
        bVar.notifyDataSetChanged();
    }

    public void setOnItemClickListener(final MotionEvent motionEvent) {
        setOnItemLongClickListener(new OnItemLongClickListener(this) {
            final /* synthetic */ DragGrid b;

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                j.a(43, 2);
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                this.b.k = i;
                this.b.g = i;
                if (this.b.k < 2) {
                    return false;
                }
                ViewGroup viewGroup = (ViewGroup) this.b.getChildAt(this.b.g - this.b.getFirstVisiblePosition());
                TextView textView = (TextView) viewGroup.findViewById(R.id.column_tv_newstitle);
                textView.setTextColor(-15551619);
                textView.setSelected(true);
                textView.setEnabled(false);
                this.b.l = viewGroup.getHeight();
                this.b.m = viewGroup.getWidth();
                if (this.b.g == -1) {
                    return false;
                }
                this.b.h = this.b.c - viewGroup.getLeft();
                this.b.i = this.b.d - viewGroup.getTop();
                this.b.e = (int) (motionEvent.getRawX() - ((float) x));
                this.b.f = (int) (motionEvent.getRawY() - ((float) y));
                viewGroup.destroyDrawingCache();
                viewGroup.setDrawingCacheEnabled(true);
                Bitmap createBitmap = Bitmap.createBitmap(viewGroup.getDrawingCache());
                this.b.t.vibrate(50);
                this.b.a(createBitmap, (int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                this.b.b();
                viewGroup.setVisibility(4);
                this.b.q = false;
                this.b.requestDisallowInterceptTouchEvent(true);
                return true;
            }
        });
    }

    public void a(Bitmap bitmap, int i, int i2) {
        a();
        this.p = new LayoutParams();
        this.p.gravity = 51;
        this.p.x = i - this.h;
        this.p.y = i2 - this.i;
        this.p.width = (int) (this.s * ((double) bitmap.getWidth()));
        this.p.height = (int) (this.s * ((double) bitmap.getHeight()));
        this.p.flags = http.Request_Timeout;
        this.p.format = -3;
        this.p.windowAnimations = 0;
        View imageView = new ImageView(getContext());
        imageView.setImageBitmap(bitmap);
        this.o = (WindowManager) getContext().getSystemService("window");
        this.o.addView(imageView, this.p);
        this.n = imageView;
    }

    private void a() {
        if (this.n != null) {
            this.o.removeView(this.n);
            this.n = null;
        }
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }

    private void b() {
        ((b) getAdapter()).b(false);
    }

    public Animation a(float f, float f2) {
        Animation translateAnimation = new TranslateAnimation(1, 0.0f, 1, f, 1, 0.0f, 1, f2);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(300);
        return translateAnimation;
    }

    public void a(int i, int i2) {
        int pointToPosition = pointToPosition(i, i2);
        if (pointToPosition >= 2 && pointToPosition != -1 && pointToPosition != this.g) {
            int i3;
            this.j = pointToPosition;
            if (this.g != this.k) {
                this.g = this.k;
            }
            if (this.g == this.k || this.g != this.j) {
                i3 = this.j - this.g;
            } else {
                i3 = 0;
            }
            if (i3 != 0) {
                int abs = Math.abs(i3);
                if (pointToPosition != this.g) {
                    ((ViewGroup) getChildAt(this.g)).setVisibility(4);
                    float f = (0.0f / ((float) this.m)) + 1.0f;
                    float f2 = (((float) this.u) / ((float) this.l)) + 1.0f;
                    for (int i4 = 0; i4 < abs; i4++) {
                        float f3;
                        float f4;
                        if (i3 > 0) {
                            this.r = (this.g + i4) + 1;
                            if (this.g / this.w == this.r / this.w) {
                                f3 = 0.0f;
                                f4 = -f;
                            } else if (this.r % 4 == 0) {
                                f4 = 3.0f * f;
                                f3 = -f2;
                            } else {
                                f3 = 0.0f;
                                f4 = -f;
                            }
                        } else {
                            this.r = (this.g - i4) - 1;
                            if (this.g / this.w == this.r / this.w) {
                                f3 = 0.0f;
                                f4 = f;
                            } else if ((this.r + 1) % 4 == 0) {
                                f3 = f2;
                                f4 = -3.0f * f;
                            } else {
                                f3 = 0.0f;
                                f4 = f;
                            }
                        }
                        ViewGroup viewGroup = (ViewGroup) getChildAt(this.r);
                        Animation a = a(f4, f3);
                        viewGroup.startAnimation(a);
                        if (this.r == this.j) {
                            this.v = a.toString();
                        }
                        a.setAnimationListener(new AnimationListener(this) {
                            final /* synthetic */ DragGrid a;

                            {
                                this.a = r1;
                            }

                            public void onAnimationStart(Animation animation) {
                                this.a.q = true;
                            }

                            public void onAnimationRepeat(Animation animation) {
                            }

                            public void onAnimationEnd(Animation animation) {
                                if (animation.toString().equalsIgnoreCase(this.a.v)) {
                                    ((b) this.a.getAdapter()).a(this.a.k, this.a.j);
                                    this.a.k = this.a.j;
                                    this.a.g = this.a.j;
                                    this.a.q = false;
                                }
                            }
                        });
                    }
                }
            }
        }
    }
}

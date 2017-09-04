package com.qq.reader.view;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import com.tencent.qalsdk.im_open.http;

public class TagView extends View {
    private boolean A;
    private boolean B;
    private boolean C;
    private int D;
    private int E;
    private float F;
    private float G;
    private float H;
    private float I;
    private int J = 1000;
    private float K;
    private int L;
    private int M;
    private Path N;
    private Typeface O;
    private ValueAnimator P;
    private boolean Q;
    private float R;
    private float S;
    private int T;
    private float U;
    private Runnable V = new Runnable(this) {
        final /* synthetic */ TagView a;

        {
            this.a = r1;
        }

        public void run() {
            if (!this.a.B && !this.a.A && ((TagContainerLayout) this.a.getParent()).getTagViewState() == 0) {
                this.a.C = true;
                this.a.p.b(((Integer) this.a.getTag()).intValue(), this.a.getText());
            }
        }
    };
    private boolean a = false;
    private int b;
    private int c;
    private int d;
    private int e;
    private float f;
    private float g;
    private float h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private boolean n;
    private int o;
    private a p;
    private int q = 5;
    private int r = 4;
    private int s = http.Internal_Server_Error;
    private int t = 3;
    private float u;
    private Paint v;
    private Paint w;
    private RectF x;
    private String y;
    private String z;

    public interface a {
        void a(int i);

        void a(int i, String str);

        void b(int i, String str);
    }

    public boolean getRippleAnimationState() {
        return this.a;
    }

    public void setRippleAnimationState(boolean z) {
        this.a = z;
    }

    public int getPressBackgroundColor() {
        return this.b;
    }

    public void setPressBackgroundColor(int i) {
        this.b = i;
    }

    public TagView(Context context, String str) {
        super(context);
        a(context, str);
    }

    private void a(Context context, String str) {
        this.v = new Paint(1);
        this.w = new Paint(1);
        this.w.setStyle(Style.FILL);
        this.x = new RectF();
        this.N = new Path();
        if (str == null) {
            str = "";
        }
        this.z = str;
        this.q = (int) a(context, (float) this.q);
        this.r = (int) a(context, (float) this.r);
        setLayerType(1, null);
    }

    private void b() {
        if (TextUtils.isEmpty(this.z)) {
            this.y = "";
        } else {
            this.y = this.z.length() <= this.o ? this.z : this.z.substring(0, this.o - 3) + "...";
        }
        this.v.setTypeface(this.O);
        this.v.setTextSize(this.h);
        FontMetrics fontMetrics = this.v.getFontMetrics();
        this.F = fontMetrics.descent - fontMetrics.ascent;
        if (this.t == 4) {
            this.G = 0.0f;
            for (char valueOf : this.y.toCharArray()) {
                this.G = this.v.measureText(String.valueOf(valueOf)) + this.G;
            }
            return;
        }
        this.G = this.v.measureText(this.y);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int i3 = ((int) this.F) + (this.j * 2);
        int i4 = (a() ? i3 : 0) + (((int) this.G) + (this.i * 2));
        this.R = Math.min(Math.max(this.R, (float) i3), (float) i4);
        setMeasuredDimension(i4, i3);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.x.set(this.f, this.f, ((float) i) - this.f, ((float) i2) - this.f);
    }

    protected void onDraw(Canvas canvas) {
        this.v.setStyle(Style.FILL);
        this.v.setColor(this.l);
        canvas.drawRoundRect(this.x, this.g, this.g, this.v);
        this.v.setStyle(Style.STROKE);
        this.v.setStrokeWidth(this.f);
        this.v.setColor(this.k);
        canvas.drawRoundRect(this.x, this.g, this.g, this.v);
        b(canvas);
        this.v.setStyle(Style.FILL);
        this.v.setColor(this.m);
        if (this.t == 4) {
            float width = (this.G / 2.0f) + ((float) ((a() ? getWidth() + getHeight() : getWidth()) / 2));
            for (char valueOf : this.y.toCharArray()) {
                String valueOf2 = String.valueOf(valueOf);
                width -= this.v.measureText(valueOf2);
                canvas.drawText(valueOf2, width, (((float) (getHeight() / 2)) + (this.F / 2.0f)) - this.u, this.v);
            }
        } else {
            canvas.drawText(this.y, ((float) ((a() ? getWidth() - getHeight() : getWidth()) / 2)) - (this.G / 2.0f), (((float) (getHeight() / 2)) + (this.F / 2.0f)) - this.u, this.v);
        }
        a(canvas);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.n) {
            int y = (int) motionEvent.getY();
            int x = (int) motionEvent.getX();
            switch (motionEvent.getAction()) {
                case 0:
                    if (getParent() != null) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                    this.E = y;
                    this.D = x;
                    break;
                case 2:
                    if (Math.abs(this.E - y) > this.r || Math.abs(this.D - x) > this.r) {
                        if (getParent() != null) {
                            getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        this.B = true;
                        return false;
                    }
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.K = 0.0f;
            this.H = motionEvent.getX();
            this.I = motionEvent.getY();
            c();
            this.l = this.b;
            this.m = this.d;
            postInvalidate();
        } else if (action == 1 || action == 3) {
            this.l = this.c;
            this.m = this.e;
            postInvalidate();
        }
        if (a() && a(motionEvent) && this.p != null) {
            if (action == 0) {
                this.p.a(((Integer) getTag()).intValue());
            }
            return true;
        } else if (!this.n || this.p == null) {
            return super.onTouchEvent(motionEvent);
        } else {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            switch (action) {
                case 0:
                    this.E = y;
                    this.D = x;
                    this.B = false;
                    this.A = false;
                    this.C = false;
                    postDelayed(this.V, (long) this.s);
                    break;
                case 1:
                    this.A = true;
                    if (!(this.C || this.B)) {
                        this.p.a(((Integer) getTag()).intValue(), getText());
                        break;
                    }
                case 2:
                    if (!this.B && (Math.abs(this.D - x) > this.q || Math.abs(this.E - y) > this.q)) {
                        this.B = true;
                        break;
                    }
            }
            return true;
        }
    }

    private boolean a(MotionEvent motionEvent) {
        if (this.t == 4) {
            if (motionEvent.getX() <= this.R) {
                return true;
            }
            return false;
        } else if (motionEvent.getX() < ((float) getWidth()) - this.R) {
            return false;
        } else {
            return true;
        }
    }

    private void a(Canvas canvas) {
        if (a()) {
            this.S = this.S > ((float) (getHeight() / 2)) ? (float) (getHeight() / 2) : this.S;
            int width = this.t == 4 ? (int) this.S : (int) (((float) (getWidth() - getHeight())) + this.S);
            int i = this.t == 4 ? (int) this.S : (int) this.S;
            int width2 = this.t == 4 ? (int) this.S : (int) (((float) (getWidth() - getHeight())) + this.S);
            int height = this.t == 4 ? (int) (((float) getHeight()) - this.S) : (int) (((float) getHeight()) - this.S);
            int height2 = this.t == 4 ? (int) (((float) getHeight()) - this.S) : (int) (((float) getWidth()) - this.S);
            int i2 = this.t == 4 ? (int) this.S : (int) this.S;
            int height3 = this.t == 4 ? (int) (((float) getHeight()) - this.S) : (int) (((float) getWidth()) - this.S);
            int height4 = this.t == 4 ? (int) (((float) getHeight()) - this.S) : (int) (((float) getHeight()) - this.S);
            this.v.setStyle(Style.STROKE);
            this.v.setColor(this.T);
            this.v.setStrokeWidth(this.U);
            canvas.drawLine((float) width, (float) i, (float) height3, (float) height4, this.v);
            canvas.drawLine((float) width2, (float) height, (float) height2, (float) i2, this.v);
        }
    }

    @TargetApi(11)
    private void b(Canvas canvas) {
        if (VERSION.SDK_INT >= 11 && canvas != null) {
            canvas.save();
            this.N.reset();
            try {
                canvas.clipPath(this.N);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.N.addRoundRect(this.x, this.g, this.g, Direction.CCW);
            canvas.clipPath(this.N, Op.REPLACE);
            canvas.drawCircle(this.H, this.I, this.K, this.w);
            canvas.restore();
        }
    }

    @TargetApi(11)
    private void c() {
        if (getRippleAnimationState() && VERSION.SDK_INT >= 11 && this.H > 0.0f && this.I > 0.0f) {
            this.w.setColor(this.L);
            this.w.setAlpha(this.M);
            final float max = Math.max(Math.max(Math.max(this.H, this.I), Math.abs(((float) getMeasuredWidth()) - this.H)), Math.abs(((float) getMeasuredHeight()) - this.I));
            this.P = ValueAnimator.ofFloat(new float[]{0.0f, max}).setDuration((long) this.J);
            this.P.addUpdateListener(new AnimatorUpdateListener(this) {
                final /* synthetic */ TagView b;

                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    TagView tagView = this.b;
                    if (floatValue >= max) {
                        floatValue = 0.0f;
                    }
                    tagView.K = floatValue;
                    this.b.postInvalidate();
                }
            });
            this.P.start();
        }
    }

    public String getText() {
        return this.z;
    }

    public boolean getIsViewClickable() {
        return this.n;
    }

    public void setTagMaxLength(int i) {
        this.o = i;
        b();
    }

    public void setOnTagClickListener(a aVar) {
        this.p = aVar;
    }

    public void setTagBackgroundColor(int i) {
        this.l = i;
        this.c = i;
    }

    public void setTagBorderColor(int i) {
        this.k = i;
    }

    public void setTagTextColor(int i) {
        this.m = i;
        this.e = i;
    }

    public void setPressTextColor(int i) {
        this.d = i;
    }

    public void setBorderWidth(float f) {
        this.f = f;
    }

    public void setBorderRadius(float f) {
        this.g = f;
    }

    public void setTextSize(float f) {
        this.h = f;
        b();
    }

    public void setHorizontalPadding(int i) {
        this.i = i;
    }

    public void setVerticalPadding(int i) {
        this.j = i;
    }

    public void setIsViewClickable(boolean z) {
        this.n = z;
    }

    public int getTextDirection() {
        return this.t;
    }

    public void setTextDirection(int i) {
        this.t = i;
    }

    public void setTypeface(Typeface typeface) {
        this.O = typeface;
        b();
    }

    public void setRippleAlpha(int i) {
        this.M = i;
    }

    public void setRippleColor(int i) {
        this.L = i;
    }

    public void setRippleDuration(int i) {
        this.J = i;
    }

    public void setBdDistance(float f) {
        this.u = f;
    }

    public boolean a() {
        return this.Q;
    }

    public void setEnableCross(boolean z) {
        this.Q = z;
    }

    public float getCrossAreaWidth() {
        return this.R;
    }

    public void setCrossAreaWidth(float f) {
        this.R = f;
    }

    public float getCrossLineWidth() {
        return this.U;
    }

    public void setCrossLineWidth(float f) {
        this.U = f;
    }

    public float getCrossAreaPadding() {
        return this.S;
    }

    public void setCrossAreaPadding(float f) {
        this.S = f;
    }

    public int getCrossColor() {
        return this.T;
    }

    public void setCrossColor(int i) {
        this.T = i;
    }

    public float a(Context context, float f) {
        return (context.getResources().getDisplayMetrics().density * f) + 0.5f;
    }
}

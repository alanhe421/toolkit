package com.qq.reader.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.LinearInterpolator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.qq.reader.c.b;
import com.qq.reader.common.utils.ao;
import com.tencent.smtt.sdk.WebView;

public class GradientBottomAutoRaiseNumView extends View implements AnimatorUpdateListener, Runnable {
    private RectF A = new RectF();
    private float B = ((float) ao.a(5.0f));
    private float C = ((float) ao.a(6.0f));
    private Rect D = new Rect();
    protected Rect a = new Rect();
    Shader b;
    BlurMaskFilter c;
    private TextPaint d;
    private CharSequence e = "";
    private int f;
    private int g;
    private float h;
    private Bitmap i;
    private Canvas j;
    private Paint k;
    private int l;
    private int m;
    private int n = 60;
    private int o = 1000;
    private int p = 1;
    private ValueAnimator q;
    private TextPaint r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w = 0;
    private int x = 3;
    private int y = -1;
    private int z = Color.parseColor("#d3e7fb");

    public void setTextColor(int i) {
        if (i != this.g) {
            this.g = i;
            this.d.setColor(i);
            d();
        }
    }

    public void setTextSize(float f) {
        if (this.h != f) {
            this.h = f;
            this.d.setTextSize(f);
            this.r.setTextSize(f);
            d();
        }
    }

    public int getTextColor() {
        return this.g;
    }

    public GradientBottomAutoRaiseNumView(Context context) {
        super(context);
        a(context, null);
    }

    public void setText(CharSequence charSequence) {
        if (this.e == null || !this.e.equals(charSequence)) {
            if (TextUtils.isEmpty(charSequence)) {
                charSequence = "0";
            }
            if (a(charSequence)) {
                requestLayout();
            } else {
                invalidate();
            }
            this.e = charSequence;
        }
    }

    private boolean a(CharSequence charSequence) {
        return getPaint().measureText(charSequence.toString()) != getPaint().measureText(getText().toString());
    }

    public CharSequence getText() {
        if (TextUtils.isEmpty(this.e)) {
            return "0";
        }
        return this.e;
    }

    public TextPaint getPaint() {
        if (this.d == null) {
            this.d = new TextPaint();
        }
        return this.d;
    }

    public void setPaint(TextPaint textPaint) {
        if (textPaint != null && textPaint != this.d) {
            this.d = textPaint;
        }
    }

    public void setMannualSetShadowColor(int i) {
        this.w = i;
    }

    public void a(int i) {
        if (this.q != null && this.q.isRunning()) {
            this.q.end();
            setText(String.valueOf(this.l));
        }
        if (TextUtils.isDigitsOnly(getText())) {
            this.m = Integer.valueOf(getText().toString()).intValue();
            this.l = this.m + i;
            this.q = ValueAnimator.ofInt(new int[]{this.m, this.l});
            this.q.setDuration(1000);
            this.q.setInterpolator(new LinearInterpolator());
            this.q.setRepeatCount(0);
            this.q.addUpdateListener(this);
            this.q.start();
        }
    }

    public void run() {
        if (this.m <= this.l) {
            this.m += this.p;
            setText(String.valueOf(Math.min(this.m, this.l)));
            postDelayed(this, (long) this.n);
        }
    }

    public GradientBottomAutoRaiseNumView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.GradientBottomAutoRaiseNumView);
            if (obtainStyledAttributes != null) {
                this.y = obtainStyledAttributes.getColor(0, -1);
                this.z = obtainStyledAttributes.getColor(1, Color.parseColor("#d3e7fb"));
                this.f = obtainStyledAttributes.getColor(4, WebView.NIGHT_MODE_COLOR);
                this.g = obtainStyledAttributes.getColor(6, WebView.NIGHT_MODE_COLOR);
                this.s = obtainStyledAttributes.getResourceId(0, 0);
                this.t = obtainStyledAttributes.getResourceId(1, 0);
                this.u = obtainStyledAttributes.getResourceId(4, 0);
                this.v = obtainStyledAttributes.getResourceId(6, 0);
                this.B = obtainStyledAttributes.getDimension(2, this.B);
                this.C = obtainStyledAttributes.getDimension(3, this.C);
                this.h = obtainStyledAttributes.getDimension(5, 50.0f);
                obtainStyledAttributes.recycle();
            }
        }
        a();
        c();
        this.d = new TextPaint();
        this.d.setAntiAlias(true);
        this.d.setTextSize(this.h);
        this.d.setTextAlign(Align.LEFT);
        this.d.setColor(this.g);
        this.k = new Paint();
    }

    private void a() {
        this.c = new BlurMaskFilter((float) this.x, Blur.NORMAL);
    }

    private void a(boolean z) {
        getPaint().setColor(getTextColor());
        if (this.b == null || z) {
            float f = 0.0f;
            float f2 = 0.0f;
            this.b = new LinearGradient(0.0f, f, f2, (float) getHeight(), new int[]{this.y, this.z}, new float[]{0.0f, 0.6f}, TileMode.CLAMP);
        }
        getPaint().setShader(this.b);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        try {
            super.onSizeChanged(i, i2, i3, i4);
            if (i > 0 && i2 > 0) {
                this.i = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
                this.j = new Canvas(this.i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                this.i = Bitmap.createBitmap(1, 1, Config.ARGB_8888);
                this.j = new Canvas(this.i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private boolean b() {
        int color;
        boolean z = false;
        if (this.t != 0) {
            color = getResources().getColor(this.t);
            if (this.z != color) {
                this.z = color;
                z = true;
            }
        }
        if (this.s != 0) {
            color = getResources().getColor(this.s);
            if (this.y != color) {
                this.y = color;
                z = true;
            }
        }
        if (this.w != 0) {
            if (this.w != this.f) {
                this.f = this.w;
                this.r.setColor(this.f);
                z = true;
            }
        } else if (this.u != 0) {
            color = getResources().getColor(this.u);
            if (color != this.f) {
                this.f = color;
                this.r.setColor(this.f);
                z = true;
            }
        }
        if (this.v == 0) {
            return z;
        }
        color = getResources().getColor(this.v);
        if (color == this.g) {
            return z;
        }
        this.g = color;
        this.d.setColor(this.g);
        return true;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            boolean b = b();
            if (!(this.A.width() == ((float) getWidth()) && this.A.height() == ((float) getHeight()))) {
                this.A.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            }
            int height = (int) (((float) ((int) this.A.height())) - this.B);
            if (this.j != null) {
                this.j.drawColor(0, Mode.CLEAR);
                this.j.drawText(getText().toString(), 0.0f, (float) height, this.r);
                height = (int) (((float) height) - this.C);
                a(b);
                this.j.drawText(getText().toString(), 0.0f, (float) height, getPaint());
                canvas.drawBitmap(this.i, 0.0f, 0.0f, this.k);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void c() {
        this.r = new TextPaint();
        this.r.setShader(null);
        this.r.setTextSize(getTextSize());
        this.r.setTextAlign(Align.LEFT);
        this.r.setColor(this.f);
        this.r.setAntiAlias(true);
    }

    public float getTextSize() {
        return this.h;
    }

    private void d() {
        invalidate();
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (!TextUtils.isEmpty(getText())) {
            getPaint().getTextBounds(getText().toString(), 0, getText().toString().length(), this.D);
            super.onMeasure(MeasureSpec.makeMeasureSpec((int) Math.min(getPaint().measureText(getText().toString()), (float) size), 1073741824), MeasureSpec.makeMeasureSpec((int) Math.min(((((float) this.D.height()) + this.B) + this.C) + ((float) ao.a(2.0f)), (float) size2), 1073741824));
        }
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        Integer animatedValue = valueAnimator.getAnimatedValue();
        if ((animatedValue instanceof Integer) && TextUtils.isDigitsOnly(getText()) && Integer.valueOf(getText().toString()) != animatedValue) {
            setText(String.valueOf(animatedValue));
        }
    }
}

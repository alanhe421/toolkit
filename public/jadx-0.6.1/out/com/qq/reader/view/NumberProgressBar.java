package com.qq.reader.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.qq.reader.c.b;
import com.tencent.feedback.proguard.R;
import com.tencent.tinker.android.dx.instruction.Opcodes;

public class NumberProgressBar extends View {
    private float A;
    private boolean B;
    private boolean C;
    private boolean D;
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private float f;
    private float g;
    private float h;
    private String i;
    private String j;
    private final int k;
    private final int l;
    private final int m;
    private final float n;
    private final float o;
    private final float p;
    private final float q;
    private float r;
    private float s;
    private float t;
    private String u;
    private Paint v;
    private Paint w;
    private Paint x;
    private RectF y;
    private RectF z;

    public enum ProgressTextVisibility {
        Visible,
        Invisible
    }

    public NumberProgressBar(Context context) {
        this(context, null);
    }

    public NumberProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.numberProgressBarStyle);
    }

    public NumberProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 100;
        this.b = 0;
        this.i = "%";
        this.j = "";
        this.k = Color.rgb(66, Opcodes.SUB_INT, 241);
        this.l = Color.rgb(66, Opcodes.SUB_INT, 241);
        this.m = Color.rgb(204, 204, 204);
        this.y = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.z = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.B = true;
        this.C = true;
        this.D = true;
        this.p = a(1.5f);
        this.q = a(1.0f);
        this.o = b(10.0f);
        this.n = a(3.0f);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, b.NumberProgressBar, i, 0);
        this.c = obtainStyledAttributes.getColor(3, this.l);
        this.d = obtainStyledAttributes.getColor(2, this.m);
        this.e = obtainStyledAttributes.getColor(7, this.k);
        this.f = obtainStyledAttributes.getDimension(6, this.o);
        this.g = obtainStyledAttributes.getDimension(4, this.p);
        this.h = obtainStyledAttributes.getDimension(5, this.q);
        this.A = obtainStyledAttributes.getDimension(8, this.n);
        if (obtainStyledAttributes.getInt(9, 0) != 0) {
            this.D = false;
        }
        setProgress(obtainStyledAttributes.getInt(0, 0));
        setMax(obtainStyledAttributes.getInt(1, 100));
        obtainStyledAttributes.recycle();
        a();
    }

    protected int getSuggestedMinimumWidth() {
        return (int) this.f;
    }

    protected int getSuggestedMinimumHeight() {
        return Math.max((int) this.f, Math.max((int) this.g, (int) this.h));
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(a(i, true), a(i2, false));
    }

    private int a(int i, boolean z) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int paddingLeft = z ? getPaddingLeft() + getPaddingRight() : getPaddingTop() + getPaddingBottom();
        if (mode == 1073741824) {
            return size;
        }
        int suggestedMinimumWidth = (z ? getSuggestedMinimumWidth() : getSuggestedMinimumHeight()) + paddingLeft;
        if (mode != Integer.MIN_VALUE) {
            return suggestedMinimumWidth;
        }
        if (z) {
            return Math.max(suggestedMinimumWidth, size);
        }
        return Math.min(suggestedMinimumWidth, size);
    }

    protected void onDraw(Canvas canvas) {
        if (this.D) {
            c();
        } else {
            b();
        }
        if (this.C) {
            canvas.drawRect(this.z, this.v);
        }
        if (this.B) {
            canvas.drawRect(this.y, this.w);
        }
        if (this.D) {
            canvas.drawText(this.u, this.s, this.t, this.x);
        }
    }

    private void a() {
        this.v = new Paint(1);
        this.v.setColor(this.c);
        this.w = new Paint(1);
        this.w.setColor(this.d);
        this.x = new Paint(1);
        this.x.setColor(this.e);
        this.x.setTextSize(this.f);
    }

    private void b() {
        this.z.left = (float) getPaddingLeft();
        this.z.top = (((float) getHeight()) / 2.0f) - (this.g / 2.0f);
        this.z.right = ((((float) ((getWidth() - getPaddingLeft()) - getPaddingRight())) / (((float) getMax()) * 1.0f)) * ((float) getProgress())) + ((float) getPaddingLeft());
        this.z.bottom = (((float) getHeight()) / 2.0f) + (this.g / 2.0f);
        this.y.left = this.z.right;
        this.y.right = (float) (getWidth() - getPaddingRight());
        this.y.top = (((float) getHeight()) / 2.0f) + ((-this.h) / 2.0f);
        this.y.bottom = (((float) getHeight()) / 2.0f) + (this.h / 2.0f);
    }

    private void c() {
        this.u = String.format("%d", new Object[]{Integer.valueOf((getProgress() * 100) / getMax())});
        this.u = this.j + this.u + this.i;
        this.r = this.x.measureText(this.u);
        if (getProgress() == 0) {
            this.C = false;
            this.s = (float) getPaddingLeft();
        } else {
            this.C = true;
            this.z.left = (float) getPaddingLeft();
            this.z.top = (((float) getHeight()) / 2.0f) - (this.g / 2.0f);
            this.z.right = (((((float) ((getWidth() - getPaddingLeft()) - getPaddingRight())) / (((float) getMax()) * 1.0f)) * ((float) getProgress())) - this.A) + ((float) getPaddingLeft());
            this.z.bottom = (((float) getHeight()) / 2.0f) + (this.g / 2.0f);
            this.s = this.z.right + this.A;
        }
        this.t = (float) ((int) ((((float) getHeight()) / 2.0f) - ((this.x.descent() + this.x.ascent()) / 2.0f)));
        if (this.s + this.r >= ((float) (getWidth() - getPaddingRight()))) {
            this.s = ((float) (getWidth() - getPaddingRight())) - this.r;
            this.z.right = this.s - this.A;
        }
        float f = (this.s + this.r) + this.A;
        if (f >= ((float) (getWidth() - getPaddingRight()))) {
            this.B = false;
            return;
        }
        this.B = true;
        this.y.left = f;
        this.y.right = (float) (getWidth() - getPaddingRight());
        this.y.top = (((float) getHeight()) / 2.0f) + ((-this.h) / 2.0f);
        this.y.bottom = (((float) getHeight()) / 2.0f) + (this.h / 2.0f);
    }

    public int getTextColor() {
        return this.e;
    }

    public float getProgressTextSize() {
        return this.f;
    }

    public int getUnreachedBarColor() {
        return this.d;
    }

    public int getReachedBarColor() {
        return this.c;
    }

    public int getProgress() {
        return this.b;
    }

    public int getMax() {
        return this.a;
    }

    public float getReachedBarHeight() {
        return this.g;
    }

    public float getUnreachedBarHeight() {
        return this.h;
    }

    public void setProgressTextSize(float f) {
        this.f = f;
        this.x.setTextSize(this.f);
        invalidate();
    }

    public void setProgressTextColor(int i) {
        this.e = i;
        this.x.setColor(this.e);
        invalidate();
    }

    public void setUnreachedBarColor(int i) {
        this.d = i;
        this.w.setColor(this.c);
        invalidate();
    }

    public void setReachedBarColor(int i) {
        this.c = i;
        this.v.setColor(this.c);
        invalidate();
    }

    public void setReachedBarHeight(float f) {
        this.g = f;
    }

    public void setUnreachedBarHeight(float f) {
        this.h = f;
    }

    public void setMax(int i) {
        if (i > 0) {
            this.a = i;
            invalidate();
        }
    }

    public void setSuffix(String str) {
        if (str == null) {
            this.i = "";
        } else {
            this.i = str;
        }
    }

    public String getSuffix() {
        return this.i;
    }

    public void setPrefix(String str) {
        if (str == null) {
            this.j = "";
        } else {
            this.j = str;
        }
    }

    public String getPrefix() {
        return this.j;
    }

    public void setProgress(int i) {
        if (i <= getMax() && i >= 0) {
            this.b = i;
            invalidate();
        }
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable("saved_instance", super.onSaveInstanceState());
        bundle.putInt("text_color", getTextColor());
        bundle.putFloat("text_size", getProgressTextSize());
        bundle.putFloat("reached_bar_height", getReachedBarHeight());
        bundle.putFloat("unreached_bar_height", getUnreachedBarHeight());
        bundle.putInt("reached_bar_color", getReachedBarColor());
        bundle.putInt("unreached_bar_color", getUnreachedBarColor());
        bundle.putInt("max", getMax());
        bundle.putInt("progress", getProgress());
        bundle.putString("suffix", getSuffix());
        bundle.putString("prefix", getPrefix());
        bundle.putBoolean("text_visibility", getProgressTextVisibility());
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.e = bundle.getInt("text_color");
            this.f = bundle.getFloat("text_size");
            this.g = bundle.getFloat("reached_bar_height");
            this.h = bundle.getFloat("unreached_bar_height");
            this.c = bundle.getInt("reached_bar_color");
            this.d = bundle.getInt("unreached_bar_color");
            a();
            setMax(bundle.getInt("max"));
            setProgress(bundle.getInt("progress"));
            setPrefix(bundle.getString("prefix"));
            setSuffix(bundle.getString("suffix"));
            setProgressTextVisibility(bundle.getBoolean("text_visibility") ? ProgressTextVisibility.Visible : ProgressTextVisibility.Invisible);
            super.onRestoreInstanceState(bundle.getParcelable("saved_instance"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public float a(float f) {
        return (getResources().getDisplayMetrics().density * f) + 0.5f;
    }

    public float b(float f) {
        return getResources().getDisplayMetrics().scaledDensity * f;
    }

    public void setProgressTextVisibility(ProgressTextVisibility progressTextVisibility) {
        this.D = progressTextVisibility == ProgressTextVisibility.Visible;
        invalidate();
    }

    public boolean getProgressTextVisibility() {
        return this.D;
    }
}

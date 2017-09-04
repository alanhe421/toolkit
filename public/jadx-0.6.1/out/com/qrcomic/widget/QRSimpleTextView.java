package com.qrcomic.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;

public class QRSimpleTextView extends View {
    private final TextPaint a;
    private CharSequence b;
    private ColorStateList c;
    private int d;
    private Rect e;
    private int f;
    private float g;
    private float h;
    private boolean i;

    public QRSimpleTextView(Context context) {
        this(context, null);
    }

    public QRSimpleTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public QRSimpleTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = 17;
        this.b = "";
        Resources resources = getResources();
        this.a = new TextPaint(1);
        this.a.density = resources.getDisplayMetrics().density;
        this.e = new Rect();
        this.i = true;
    }

    public Paint getPaint() {
        return this.a;
    }

    public void setGravity(int i) {
        if (this.f != i) {
            this.f = i;
            this.i = true;
            invalidate();
        }
    }

    public final void setText(CharSequence charSequence) {
        if (this.b == charSequence) {
            return;
        }
        if (charSequence != null || !"".equals(this.b)) {
            this.b = charSequence;
            if (this.b == null) {
                this.b = "";
            }
            requestLayout();
            invalidate();
        }
    }

    public final CharSequence getText() {
        return this.b;
    }

    public void setTextSize(float f) {
        setTextSize(2, f);
    }

    public void setTextSize(int i, float f) {
        Resources system;
        Context context = getContext();
        if (context == null) {
            system = Resources.getSystem();
        } else {
            system = context.getResources();
        }
        setRawTextSize(TypedValue.applyDimension(i, f, system.getDisplayMetrics()));
    }

    private void setRawTextSize(float f) {
        if (f != this.a.getTextSize()) {
            this.a.setTextSize(f);
            this.i = true;
            invalidate();
        }
    }

    public void setTextColor(int i) {
        this.c = ColorStateList.valueOf(i);
        a();
    }

    private void a() {
        int colorForState = this.c.getColorForState(getDrawableState(), 0);
        if (colorForState != this.d) {
            this.d = colorForState;
            invalidate();
        }
    }

    protected void onMeasure(int i, int i2) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            mode = size;
        } else {
            mode = ((int) Math.ceil((double) Layout.getDesiredWidth(this.b, this.a))) + (getPaddingLeft() + getPaddingRight());
        }
        if (mode2 != 1073741824) {
            size = this.a.getFontMetricsInt(null) + (getPaddingTop() + getPaddingBottom());
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(size2, size) : size;
        }
        setMeasuredDimension(mode, size2);
        if (measuredHeight != size2 || measuredWidth != mode) {
            this.i = true;
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Object charSequence = this.b.toString();
        this.a.setColor(this.d);
        this.a.drawableState = getDrawableState();
        canvas.save();
        canvas.translate((float) getScrollX(), (float) getScrollY());
        if (this.i) {
            this.a.getTextBounds(charSequence, 0, charSequence.length(), this.e);
            float desiredWidth = Layout.getDesiredWidth(charSequence, this.a);
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            int width = getWidth();
            int height = getHeight();
            int i = this.f & 7;
            this.g = (float) paddingLeft;
            if (i == 1) {
                this.g = ((((float) ((width - paddingLeft) - paddingRight)) - desiredWidth) / 2.0f) + this.g;
            } else if (i == 5) {
                this.g = (((float) ((width - paddingLeft) - paddingRight)) - desiredWidth) + this.g;
            }
            int i2 = this.f & 112;
            FontMetricsInt fontMetricsInt = this.a.getFontMetricsInt();
            paddingRight = fontMetricsInt.descent - fontMetricsInt.ascent;
            this.h = (float) ((paddingRight - fontMetricsInt.descent) + paddingTop);
            if (i2 == 16) {
                this.h += (float) ((((height - paddingBottom) - paddingTop) - paddingRight) / 2);
            } else if (i2 == 80) {
                this.h += (float) (((height - paddingBottom) - paddingTop) - paddingRight);
            }
            this.i = false;
        }
        canvas.drawText(charSequence, this.g, this.h, this.a);
        canvas.restore();
    }
}

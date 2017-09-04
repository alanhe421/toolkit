package com.qq.reader.view.scrollcover;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.view.n;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import com.qq.reader.c.b;

public class FancyCoverFlow extends Gallery {
    private float a = 0.4f;
    private int b = 20;
    private boolean c = false;
    private float d;
    private Camera e;
    private int f = 75;
    private float g;
    private float h = 0.5f;
    private int i;
    private boolean j = false;
    private boolean k = false;
    private Handler l = new Handler(new Callback(this) {
        final /* synthetic */ FancyCoverFlow a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1001:
                    if (this.a.getSelectedItemPosition() == (this.a.getCount() - 2) - 1) {
                        this.a.setSelection(2);
                    } else {
                        this.a.setSelection(this.a.getSelectedItemPosition() + 1);
                    }
                    this.a.l.sendEmptyMessageDelayed(1001, 5000);
                    break;
            }
            return true;
        }
    });
    private float m;
    private float n = 1.0f;
    private a o;
    private int p = 0;
    private int q = 0;

    public static class LayoutParams extends android.widget.Gallery.LayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }
    }

    public interface a {
        void a(int i);
    }

    public FancyCoverFlow(Context context) {
        super(context);
        b();
    }

    public FancyCoverFlow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
        a(attributeSet);
    }

    public FancyCoverFlow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
        a(attributeSet);
    }

    private void b() {
        this.e = new Camera();
        setSpacing(0);
    }

    private void a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, b.FancyCoverFlow);
        this.i = obtainStyledAttributes.getInteger(7, Integer.MAX_VALUE);
        this.h = obtainStyledAttributes.getFloat(6, 1.0f);
        this.f = obtainStyledAttributes.getInteger(3, 45);
        this.d = obtainStyledAttributes.getFloat(0, 0.3f);
        this.m = obtainStyledAttributes.getFloat(1, 0.0f);
        this.g = obtainStyledAttributes.getFloat(2, 0.75f);
        this.j = obtainStyledAttributes.getBoolean(4, false);
        this.k = obtainStyledAttributes.getBoolean(5, false);
        obtainStyledAttributes.recycle();
    }

    public float getReflectionRatio() {
        return this.a;
    }

    public void setReflectionRatio(float f) {
        if (f <= 0.0f || f > 0.5f) {
            throw new IllegalArgumentException("reflectionRatio may only be in the interval (0, 0.5]");
        }
        this.a = f;
        if (getAdapter() != null) {
            ((a) getAdapter()).notifyDataSetChanged();
        }
    }

    public void setSelectedScale(float f) {
        this.n = f;
    }

    public int getReflectionGap() {
        return this.b;
    }

    public void setReflectionGap(int i) {
        this.b = i;
        if (getAdapter() != null) {
            ((a) getAdapter()).notifyDataSetChanged();
        }
    }

    public boolean a() {
        return this.c;
    }

    public void setReflectionEnabled(boolean z) {
        this.c = z;
        if (getAdapter() != null) {
            ((a) getAdapter()).notifyDataSetChanged();
        }
    }

    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (spinnerAdapter instanceof a) {
            super.setAdapter(spinnerAdapter);
            return;
        }
        throw new ClassCastException(FancyCoverFlow.class.getSimpleName() + " only works in conjunction with a " + a.class.getSimpleName());
    }

    public int getMaxRotation() {
        return this.f;
    }

    public void setMaxRotation(int i) {
        this.f = i;
    }

    public float getUnselectedAlpha() {
        return this.d;
    }

    public float getUnselectedScale() {
        return this.g;
    }

    public void setUnselectedScale(float f) {
        this.g = f;
    }

    public float getScaleDownGravity() {
        return this.h;
    }

    public void setScaleDownGravity(float f) {
        this.h = f;
    }

    public int getActionDistance() {
        return this.i;
    }

    public void setActionDistance(int i) {
        this.i = i;
    }

    public void setUnselectedAlpha(float f) {
        super.setUnselectedAlpha(f);
        this.d = f;
    }

    public float getUnselectedSaturation() {
        return this.m;
    }

    public void setUnselectedSaturation(float f) {
        this.m = f;
    }

    protected int getChildDrawingOrder(int i, int i2) {
        if (i2 == 0) {
            this.q = 0;
        }
        int selectedItemPosition = getSelectedItemPosition() - getFirstVisiblePosition();
        if (i2 == i - 1) {
            return selectedItemPosition;
        }
        if (i2 < selectedItemPosition) {
            return i2;
        }
        this.q++;
        return i - this.q;
    }

    protected boolean getChildStaticTransformation(View view, Transformation transformation) {
        FancyCoverFlowItemWrapper fancyCoverFlowItemWrapper = (FancyCoverFlowItemWrapper) view;
        if (VERSION.SDK_INT >= 16) {
            fancyCoverFlowItemWrapper.invalidate();
        }
        int width = (getWidth() / 2) + getLeft();
        int width2 = fancyCoverFlowItemWrapper.getWidth();
        int height = fancyCoverFlowItemWrapper.getHeight();
        float min = Math.min(1.0f, Math.max(-1.0f, ((float) ((fancyCoverFlowItemWrapper.getLeft() + (width2 / 2)) - width)) * (1.0f / ((float) (getWidth() / 2)))));
        transformation.clear();
        transformation.setTransformationType(3);
        float abs = Math.abs(min) * 2.0f;
        float f = abs > 1.0f ? 1.0f : abs;
        if (fancyCoverFlowItemWrapper != null && fancyCoverFlowItemWrapper.a() > 0) {
            ((ImageView) fancyCoverFlowItemWrapper.findViewById(fancyCoverFlowItemWrapper.a())).setAlpha(f);
        }
        if (this.m != 1.0f) {
            fancyCoverFlowItemWrapper.b(((this.m - 1.0f) * Math.abs(min)) + 1.0f);
        }
        Matrix matrix = transformation.getMatrix();
        if (this.j && this.f != 0) {
            int i = (int) ((-min) * ((float) this.f));
            this.e.save();
            this.e.rotateY((float) i);
            this.e.getMatrix(matrix);
            this.e.restore();
        }
        f = ((this.g - this.n) * Math.abs(min)) + this.n;
        float f2 = ((float) width2) / 2.0f;
        float f3 = ((float) height) * this.h;
        matrix.preTranslate(-f2, -f3);
        matrix.postScale(f, f);
        matrix.postTranslate(f2, f3);
        return true;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return super.onFling(motionEvent, motionEvent2, 5.0f, f2);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (n.a(motionEvent)) {
            case 0:
                if (this.k) {
                    this.l.removeMessages(1001);
                    break;
                }
                break;
            case 1:
                if (this.k) {
                    this.l.sendEmptyMessageDelayed(1001, 5000);
                    break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        boolean onScroll = super.onScroll(motionEvent, motionEvent2, f, f2);
        if (this.k) {
            if (getSelectedItemPosition() == getCount() - 2) {
                setSelection(2);
            } else if (getSelectedItemPosition() == 1) {
                setSelection(getCount() - 3);
            }
            int selectedItemPosition = getSelectedItemPosition();
            if (getSelectedItemPosition() != this.p) {
                this.p = selectedItemPosition;
                this.o.a(selectedItemPosition);
            }
        }
        return onScroll;
    }

    public void setSelection(int i) {
        super.setSelection(i);
        if (this.o != null && getSelectedItemPosition() != this.p) {
            this.p = i;
            this.o.a(i);
        }
    }

    public void setOnitemSelectListener(a aVar) {
        this.o = aVar;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.k) {
            this.l.removeMessages(1001);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.k) {
            this.l.sendEmptyMessageDelayed(1001, 3000);
        }
    }
}

package com.qq.reader.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.qq.reader.c.b;
import com.qq.reader.common.monitor.debug.c;
import com.tencent.theme.SkinEngine;
import com.tencent.upload.log.trace.TracerConfig;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class WaveView extends View {
    public static final ShapeType a = ShapeType.SQUARE;
    private boolean b;
    private BitmapShader c;
    private Matrix d;
    private Paint e;
    private Paint f;
    private float g;
    private float h;
    private float i;
    private double j;
    private float k = 0.05f;
    private float l = 1.0f;
    private float m = 0.5f;
    private float n = 0.0f;
    private int o = SkinEngine.TYPE_FILE;
    private int p = 0;
    private ShapeType q = a;
    private int r = 0;
    private int s = 0;
    private int t;
    private int u;

    public enum ShapeType {
        CIRCLE,
        SQUARE
    }

    public static class a {
        private WaveView a;
        private AnimatorSet b;

        public a(WaveView waveView) {
            this.a = waveView;
            d();
        }

        public void a() {
            this.a.setShowWave(true);
            if (this.b != null) {
                this.b.start();
            }
        }

        private void d() {
            Collection arrayList = new ArrayList();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.a, "waveShiftRatio", new float[]{0.0f, 1.0f});
            ofFloat.setRepeatCount(-1);
            ofFloat.setDuration(TracerConfig.LOG_FLUSH_DURATION);
            ofFloat.setInterpolator(new LinearInterpolator());
            arrayList.add(ofFloat);
            ofFloat = ObjectAnimator.ofFloat(this.a, "waterLevelRatio", new float[]{0.35f, 0.35f});
            ofFloat.setDuration(TracerConfig.LOG_FLUSH_DURATION);
            ofFloat.setRepeatMode(2);
            ofFloat.setRepeatCount(-1);
            ofFloat.setInterpolator(new DecelerateInterpolator());
            arrayList.add(ofFloat);
            ofFloat = ObjectAnimator.ofFloat(this.a, "amplitudeRatio", new float[]{0.08f, 0.09f});
            ofFloat.setRepeatCount(-1);
            ofFloat.setRepeatMode(2);
            ofFloat.setDuration(5000);
            ofFloat.setInterpolator(new LinearInterpolator());
            arrayList.add(ofFloat);
            this.b = new AnimatorSet();
            this.b.playTogether(arrayList);
        }

        public void b() {
            if (this.b != null) {
                this.b.end();
            }
        }

        public void c() {
            if (this.b != null) {
                ArrayList childAnimations = this.b.getChildAnimations();
                if (childAnimations != null) {
                    Iterator it = childAnimations.iterator();
                    while (it.hasNext()) {
                        Animator animator = (Animator) it.next();
                        animator.end();
                        animator.cancel();
                    }
                }
                this.b.end();
                this.b.cancel();
                ValueAnimator.clearAllAnimations();
                this.b = null;
            }
        }
    }

    public WaveView(Context context) {
        super(context);
        a(context, null);
    }

    public WaveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public WaveView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.d = new Matrix();
        this.e = new Paint();
        this.e.setAntiAlias(true);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.WaveView);
            if (obtainStyledAttributes != null) {
                try {
                    this.o = obtainStyledAttributes.getColor(1, SkinEngine.TYPE_FILE);
                    int resourceId = obtainStyledAttributes.getResourceId(1, 0);
                    if (resourceId != 0) {
                        this.t = getResources().getColor(resourceId);
                    }
                    this.p = obtainStyledAttributes.getColor(0, 0);
                    resourceId = obtainStyledAttributes.getResourceId(0, 0);
                    if (resourceId != 0) {
                        this.u = getResources().getColor(resourceId);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                obtainStyledAttributes.recycle();
            }
        }
    }

    public float getWaveShiftRatio() {
        return this.n;
    }

    public void setWaveShiftRatio(float f) {
        if (this.n != f) {
            this.n = f;
            invalidate();
        }
    }

    public float getWaterLevelRatio() {
        return this.m;
    }

    public void setWaterLevelRatio(float f) {
        if (this.m != f) {
            this.m = f;
            invalidate();
        }
    }

    public float getAmplitudeRatio() {
        return this.k;
    }

    public void setAmplitudeRatio(float f) {
        if (this.k != f) {
            this.k = f;
            invalidate();
        }
    }

    public float getWaveLengthRatio() {
        return this.l;
    }

    public void setWaveLengthRatio(float f) {
        this.l = f;
    }

    public void setShowWave(boolean z) {
        this.b = z;
    }

    public void setMannuallyWaveColor(int i, int i2) {
        this.s = i2;
        this.r = i;
    }

    public void setBorder(int i, int i2) {
        if (this.f == null) {
            this.f = new Paint();
            this.f.setAntiAlias(true);
            this.f.setStyle(Style.STROKE);
        }
        this.f.setColor(i2);
        this.f.setStrokeWidth((float) i);
        invalidate();
    }

    public void setShapeType(ShapeType shapeType) {
        this.q = shapeType;
        invalidate();
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i > 0 && i2 > 0) {
            a();
        }
    }

    private void a() {
        try {
            int i;
            this.j = 6.283185307179586d / ((double) getWidth());
            this.g = ((float) getHeight()) * 0.05f;
            this.h = ((float) getHeight()) * 0.5f;
            this.i = (float) getWidth();
            Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            paint.setStrokeWidth(2.0f);
            paint.setAntiAlias(true);
            int width = getWidth() + 1;
            int height = getHeight() + 1;
            float[] fArr = new float[width];
            paint.setColor(this.o);
            for (i = 0; i < width; i++) {
                double d = (double) this.h;
                double d2 = (double) this.g;
                float sin = (float) ((Math.sin(((double) i) * this.j) * d2) + d);
                canvas.drawLine((float) i, sin, (float) i, (float) height, paint);
                fArr[i] = sin;
            }
            paint.setColor(this.p);
            int i2 = (int) (this.i / 4.0f);
            for (i = 0; i < width; i++) {
                canvas.drawLine((float) i, fArr[(i + i2) % width], (float) i, (float) height, paint);
            }
            this.c = new BitmapShader(createBitmap, TileMode.REPEAT, TileMode.CLAMP);
            this.e.setShader(this.c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDraw(Canvas canvas) {
        int i = 0;
        try {
            int i2;
            if (this.r != 0) {
                i2 = this.r;
            } else if (this.u != 0) {
                i2 = this.u;
            } else {
                i2 = 0;
            }
            if (this.s != 0) {
                i = this.s;
            } else if (this.t != 0) {
                i = this.t;
            }
            if (!(i == this.o && i2 == this.p)) {
                if (i == 0) {
                    i = this.o;
                }
                this.o = i;
                if (i2 == 0) {
                    i2 = this.p;
                }
                this.p = i2;
                a();
                c.e("TAG", "create shader");
            }
            if (!this.b || this.c == null) {
                this.e.setShader(null);
                return;
            }
            if (this.e.getShader() == null) {
                this.e.setShader(this.c);
            }
            this.d.setScale(this.l / 1.0f, this.k / 0.05f, 0.0f, this.h);
            this.d.postTranslate(this.n * ((float) getWidth()), (0.5f - this.m) * ((float) getHeight()));
            this.c.setLocalMatrix(this.d);
            float strokeWidth = this.f == null ? 0.0f : this.f.getStrokeWidth();
            switch (this.q) {
                case CIRCLE:
                    if (strokeWidth > 0.0f) {
                        canvas.drawCircle(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f, ((((float) getWidth()) - strokeWidth) / 2.0f) - 1.0f, this.f);
                    }
                    canvas.drawCircle(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f, (((float) getWidth()) / 2.0f) - strokeWidth, this.e);
                    return;
                case SQUARE:
                    if (strokeWidth > 0.0f) {
                        canvas.drawRect(strokeWidth / 2.0f, strokeWidth / 2.0f, (((float) getWidth()) - (strokeWidth / 2.0f)) - 0.5f, (((float) getHeight()) - (strokeWidth / 2.0f)) - 0.5f, this.f);
                    }
                    canvas.drawRect(strokeWidth, strokeWidth, ((float) getWidth()) - strokeWidth, ((float) getHeight()) - strokeWidth, this.e);
                    return;
                default:
                    return;
            }
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

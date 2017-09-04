package com.qrcomic.f;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.Pair;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;
import java.lang.reflect.Field;

/* compiled from: ValueAnimation */
public class f<T> extends Animation {
    static final b a = new b<Integer>() {
        public Integer a(float f, Integer num, Integer num2) {
            int intValue = num.intValue();
            return Integer.valueOf((int) ((((float) (num2.intValue() - intValue)) * f) + ((float) intValue)));
        }
    };
    static final b b = new b<Number>() {
        public Float a(float f, Number number, Number number2) {
            float floatValue = number.floatValue();
            return Float.valueOf(floatValue + ((number2.floatValue() - floatValue) * f));
        }
    };
    static final b c = new b<Rect>() {
        public Rect a(float f, Rect rect, Rect rect2) {
            return new Rect(rect.left + ((int) (((float) (rect2.left - rect.left)) * f)), rect.top + ((int) (((float) (rect2.top - rect.top)) * f)), rect.right + ((int) (((float) (rect2.right - rect.right)) * f)), rect.bottom + ((int) (((float) (rect2.bottom - rect.bottom)) * f)));
        }
    };
    static final b d = new b<Point>() {
        public Point a(float f, Point point, Point point2) {
            float f2 = (float) point.x;
            float f3 = (float) point.y;
            return new Point((int) (f2 + ((((float) point2.x) - f2) * f)), (int) (f3 + ((((float) point2.y) - f3) * f)));
        }
    };
    static final b e = new b<PointF>() {
        public PointF a(float f, PointF pointF, PointF pointF2) {
            float f2 = pointF.x;
            float f3 = pointF.y;
            return new PointF(f2 + ((pointF2.x - f2) * f), f3 + ((pointF2.y - f3) * f));
        }
    };
    protected T f;
    protected T g;
    protected b<T> h;
    protected a<T> i;
    protected boolean j;
    protected boolean k;
    protected boolean l;
    protected boolean m;
    private long n;
    private Pair[] o;

    /* compiled from: ValueAnimation */
    public interface a<T> {
        void a(f<T> fVar, float f, T t, Transformation transformation);
    }

    /* compiled from: ValueAnimation */
    interface b<T> {
        T a(float f, T t, T t2);
    }

    public f(T t, T t2, a<T> aVar) {
        this(t, t2, aVar, false, false, null);
    }

    public f(T t, T t2, a<T> aVar, boolean z, boolean z2, b<T> bVar) {
        this.n = 0;
        Class cls = t.getClass();
        if (bVar != null) {
            this.h = bVar;
        } else if (cls == Integer.class) {
            this.h = a;
        } else if (Float.class == cls) {
            this.h = b;
        } else if (Rect.class == cls) {
            this.h = c;
        } else if (Point.class == cls) {
            this.h = d;
        } else if (PointF.class == cls) {
            this.h = e;
        } else {
            throw new IllegalArgumentException("Can't support type " + t.getClass().getSimpleName());
        }
        this.f = t;
        this.g = t2;
        a((a) aVar);
        this.j = z;
        this.k = z2;
    }

    public void a(a<T> aVar) {
        this.i = aVar;
    }

    public boolean willChangeTransformationMatrix() {
        return this.k;
    }

    public boolean willChangeBounds() {
        return this.j;
    }

    public void cancel() {
        int i = 0;
        this.l = true;
        if (VERSION.SDK_INT >= 8) {
            super.cancel();
            return;
        }
        if (this.o == null) {
            this.o = new Pair[]{new Pair("mEnded", Boolean.valueOf(true)), new Pair("mMore", Boolean.valueOf(false)), new Pair("mOneMoreTime", Boolean.valueOf(false))};
        }
        try {
            Field declaredField = getClass().getDeclaredField("mListener");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this);
            if (obj instanceof AnimationListener) {
                ((AnimationListener) obj).onAnimationEnd(this);
            }
            declaredField = getClass().getDeclaredField("mStartTime");
            declaredField.setAccessible(true);
            declaredField.setLong(this, Long.MIN_VALUE);
            Pair[] pairArr = this.o;
            int length = pairArr.length;
            while (i < length) {
                Pair pair = pairArr[i];
                Field declaredField2 = getClass().getDeclaredField((String) pair.first);
                declaredField2.setAccessible(true);
                declaredField2.setBoolean(this, ((Boolean) pair.second).booleanValue());
                i++;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }

    public void a(long j) {
        this.n = j;
    }

    public void reset() {
        this.l = false;
        super.reset();
    }

    public boolean getTransformation(long j, Transformation transformation) {
        if (this.m) {
            if (this.n == 0) {
                a(j - getStartTime());
            }
            setStartTime(j - this.n);
        }
        return super.getTransformation(j, transformation);
    }

    protected void applyTransformation(float f, Transformation transformation) {
        if (this.i != null) {
            this.i.a(this, f, this.h.a(f, this.f, this.g), transformation);
        }
    }
}

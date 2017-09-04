package com.qq.reader.liveshow.utils;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation.AnimationListener;
import com.qq.reader.liveshow.a.c;
import com.qq.reader.liveshow.a.f;
import com.qq.reader.liveshow.a.j;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: AbstractPathAnimator */
public abstract class a {
    protected final a a;
    private final Random b = new Random();

    /* compiled from: AbstractPathAnimator */
    public static class a {
        public int a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;
        public int i;
        public int j;

        public static a a(TypedArray typedArray, float f, float f2, int i, int i2, int i3) {
            a aVar = new a();
            Resources resources = typedArray.getResources();
            aVar.a = (int) typedArray.getDimension(j.HeartLayout_initX, f);
            aVar.b = (int) typedArray.getDimension(j.HeartLayout_initY, f2);
            aVar.c = (int) typedArray.getDimension(j.HeartLayout_xRand, (float) resources.getDimensionPixelOffset(c.heart_anim_bezier_x_rand));
            aVar.g = (int) typedArray.getDimension(j.HeartLayout_animLength, (float) resources.getDimensionPixelOffset(c.heart_anim_length));
            aVar.d = (int) typedArray.getDimension(j.HeartLayout_animLengthRand, (float) resources.getDimensionPixelOffset(c.heart_anim_length_rand));
            aVar.e = typedArray.getInteger(j.HeartLayout_bezierFactor, resources.getInteger(f.heart_anim_bezier_factor));
            aVar.f = i;
            aVar.h = (int) typedArray.getDimension(j.HeartLayout_heart_width, (float) resources.getDimensionPixelOffset(c.heart_size_width));
            aVar.i = (int) typedArray.getDimension(j.HeartLayout_heart_height, (float) resources.getDimensionPixelOffset(c.heart_size_height));
            aVar.j = typedArray.getInteger(j.HeartLayout_anim_duration, resources.getInteger(f.anim_duration));
            return aVar;
        }
    }

    public abstract void a(View view, ViewGroup viewGroup, AnimationListener animationListener);

    public a(a aVar) {
        this.a = aVar;
    }

    public float a() {
        return (this.b.nextFloat() * 28.6f) - 14.3f;
    }

    public Path a(AtomicInteger atomicInteger, View view, int i) {
        Random random = this.b;
        int nextInt = random.nextInt(this.a.c);
        int nextInt2 = random.nextInt(this.a.c);
        int height = view.getHeight() - this.a.b;
        int nextInt3 = random.nextInt(this.a.d) + ((atomicInteger.intValue() * 15) + (this.a.g * i));
        int i2 = nextInt3 / this.a.e;
        int i3 = this.a.f + nextInt;
        int i4 = this.a.f + nextInt2;
        int i5 = height - nextInt3;
        int i6 = height - (nextInt3 / 2);
        Path path = new Path();
        path.moveTo((float) this.a.a, (float) height);
        path.cubicTo((float) this.a.a, (float) (height - i2), (float) i3, (float) (i6 + i2), (float) i3, (float) i6);
        path.moveTo((float) i3, (float) i6);
        path.cubicTo((float) i3, (float) (i6 - i2), (float) i4, (float) (i5 + i2), (float) i4, (float) i5);
        return path;
    }
}

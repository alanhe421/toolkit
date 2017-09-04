package com.qq.reader.view.animation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.widget.Scroller;
import com.qq.reader.module.readpage.ab;
import com.qq.reader.readengine.kernel.PageIndex;
import com.qq.reader.readengine.kernel.b;
import com.qq.reader.view.animation.AnimationProvider.AnimState;
import com.qq.reader.view.animation.AnimationProvider.Mode;

public class DoublePageAnimationProvider extends AnimationProvider {
    boolean p;
    boolean q;
    private Object r;
    private Drawable s;
    private Drawable t;
    private Drawable u;
    private Drawable v;
    private Scroller w;
    private final Paint x;
    private final Paint y;
    private TouchArea z;

    public enum TouchArea {
        leftTop,
        leftCenter,
        leftButtom,
        rightTop,
        rightCenter,
        rightButtom,
        None
    }

    public void a(int i, int i2) {
        this.l = i - (this.s.getIntrinsicWidth() * 2);
        this.m = i2;
    }

    public boolean e() {
        return this.d == AnimState.DRAGING;
    }

    public boolean f() {
        return this.d == AnimState.ANIMATING;
    }

    public boolean g() {
        if (this.d == AnimState.ANIMATING) {
            if (this.w.computeScrollOffset()) {
                this.b = (float) this.w.getCurrX();
                this.c = (float) this.w.getCurrY();
            } else {
                i();
            }
        }
        return true;
    }

    private void b(Canvas canvas) {
        Bitmap a = this.k.a(PageIndex.current_left);
        Bitmap a2 = this.k.a(PageIndex.current_right);
        int width = a.getWidth();
        int intrinsicWidth = this.s.getIntrinsicWidth();
        int intrinsicWidth2 = this.t.getIntrinsicWidth();
        int i = (this.l / 2) + intrinsicWidth;
        int i2 = this.l + (intrinsicWidth * 2);
        canvas.drawBitmap(a, (float) (i - width), 0.0f, this.y);
        canvas.drawBitmap(a2, (float) i, 0.0f, this.y);
        this.s.setBounds(0, 0, intrinsicWidth, this.m);
        this.s.draw(canvas);
        this.t.setBounds(i - intrinsicWidth2, 0, i, this.m);
        this.t.draw(canvas);
        this.u.setBounds(i, 0, i + intrinsicWidth2, this.m);
        this.u.draw(canvas);
        this.v.setBounds(i2 - intrinsicWidth, 0, i2, this.m);
        this.v.draw(canvas);
    }

    private void a(int i, PageIndex pageIndex) {
        switch (i) {
            case 0:
            case 1:
                this.k.a(pageIndex);
                return;
            default:
                this.k.c(pageIndex);
                return;
        }
    }

    public void j() {
        boolean z = a() == PageIndex.next;
        switch (this.a) {
            case AutoScrollingForward:
            case NoScrolling:
                this.k.b(z);
                break;
            case ForceScrolling:
                this.k.f(PageIndex.previous_left);
                this.k.f(PageIndex.previous_right);
                this.k.f(PageIndex.next_left);
                this.k.f(PageIndex.next_right);
                break;
        }
        this.a = Mode.NoScrolling;
    }

    private Point a(int i, int i2, TouchArea touchArea, int i3, int i4) {
        int cos;
        float f = (float) i;
        float f2 = (float) i2;
        if (touchArea == TouchArea.leftTop) {
            f = (float) Math.abs((i3 / 2) - i);
            f2 = (float) i2;
        } else if (touchArea == TouchArea.rightTop) {
            f = (float) Math.abs((i3 / 2) - i);
            f2 = (float) i2;
        } else if (touchArea == TouchArea.rightButtom) {
            f = (float) Math.abs((i3 / 2) - i);
            f2 = (float) Math.abs(i4 - i2);
        } else if (touchArea == TouchArea.leftButtom) {
            f = (float) Math.abs((i3 / 2) - i);
            f2 = (float) Math.abs(i4 - i2);
        }
        double atan2 = Math.atan2((double) f2, (double) f);
        int sin;
        if (touchArea == TouchArea.rightTop || touchArea == TouchArea.leftTop) {
            cos = (int) (((double) (i3 / 2)) * Math.cos(atan2));
            sin = ((int) (((double) (i3 / 2)) * Math.sin(atan2))) - ((int) (((180.0d * atan2) / 3.141592653589793d) * 2.0d));
            if (i2 > sin) {
                i2 = sin;
            }
        } else if (touchArea == TouchArea.rightButtom || touchArea == TouchArea.leftButtom) {
            cos = (int) (((double) (i3 / 2)) * Math.cos(atan2));
            sin = (i4 - ((int) (((double) (i3 / 2)) * Math.sin(atan2)))) + ((int) (((180.0d * atan2) / 3.141592653589793d) * 2.0d));
            if (i2 < sin) {
                i2 = sin;
            }
        } else {
            cos = -1;
        }
        if (i < (i3 / 2) - cos) {
            i = (i3 / 2) - cos;
        } else if (i > (i3 / 2) + cos) {
            i = (i3 / 2) + cos;
        }
        if (touchArea == TouchArea.rightTop || touchArea == TouchArea.rightButtom) {
            i -= i3 / 2;
        }
        return new Point(i, i2);
    }

    private void a(Canvas canvas, PageIndex pageIndex, PageIndex pageIndex2) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        Object obj;
        float f;
        float f2;
        Shader linearGradient;
        double d;
        int i6;
        Bitmap a = this.k.a(pageIndex);
        if (n == -15584170) {
            int min = Math.min(a.getWidth(), 10);
            int min2 = Math.min(a.getHeight(), 10);
            long j = 0;
            long j2 = 0;
            long j3 = 0;
            for (i = 0; i < min; i++) {
                for (i2 = 0; i2 < min2; i2++) {
                    int pixel = a.getPixel((a.getWidth() / 2) + i, (a.getHeight() / 2) + i2);
                    j += (long) (16711680 & pixel);
                    j2 += (long) (65280 & pixel);
                    j3 += (long) (pixel & 255);
                }
            }
            n = Color.rgb((int) (((j / ((long) (min * min2))) >> 16) & 255), (int) (((j2 / ((long) (min * min2))) >> 8) & 255), (int) ((j3 / ((long) (min * min2))) & 255));
        }
        this.x.setColor(n);
        int intrinsicWidth = this.s.getIntrinsicWidth();
        int i7 = this.l / 2;
        int i8 = this.m;
        int i9 = (this.z == TouchArea.leftTop || this.z == TouchArea.leftButtom || this.z == TouchArea.leftCenter) ? 0 : i7;
        if (this.z == TouchArea.rightTop || this.z == TouchArea.leftTop) {
            i3 = 0;
        } else {
            i3 = i8;
        }
        int abs = Math.abs(i7 - i9);
        int abs2 = Math.abs(i8 - i3);
        int i10 = (int) this.b;
        int i11 = (int) this.c;
        i = Math.max(1, Math.abs(i10 - i9));
        int max = Math.max(1, Math.abs(i11 - i3));
        i2 = i9 == 0 ? (((max * max) / i) + i) / 2 : i9 - ((((max * max) / i) + i) / 2);
        if (i2 < 0) {
            i4 = 0;
        } else if (i2 > i7) {
            i4 = i7;
        } else {
            i4 = i2;
        }
        if (i3 == 0) {
            i5 = (((i * i) / max) + max) / 2;
        } else {
            i5 = i3 - ((((i * i) / max) + max) / 2);
        }
        float f3 = (float) (i10 - i4);
        float f4 = (float) (i11 - i3);
        f3 = ((float) Math.sqrt((double) ((f3 * f3) + (f4 * f4)))) / 2.0f;
        if (i9 == i7 && ((float) i4) - f3 < 0.0f) {
            f3 = (float) i4;
            obj = 1;
        } else if (i9 != 0 || ((float) i4) + f3 <= ((float) i7)) {
            obj = null;
        } else {
            f3 = (float) Math.abs(i7 - i4);
            int i12 = 1;
        }
        if (i9 == 0) {
            f = -f3;
        } else {
            f = f3;
        }
        f3 = (float) (i10 - i9);
        float f5 = (float) (i11 - i5);
        f3 = ((float) Math.sqrt((double) ((f3 * f3) + (f5 * f5)))) / 2.0f;
        if (i3 == 0) {
            f2 = -f3;
        } else {
            f2 = f3;
        }
        double cos = ((double) i7) / Math.cos(0.6981317007977318d);
        double sqrt = Math.sqrt((double) ((Math.abs(i9 - i10) * Math.abs(i9 - i10)) + (Math.abs(i3 - i11) * Math.abs(i3 - i11))));
        double atan2 = Math.atan2((double) Math.abs(i9 - i4), (double) Math.abs(i3 - i5));
        float abs3 = (float) (((double) Math.abs(f2)) * Math.sin(atan2));
        f3 = (float) i9;
        f4 = ((float) i5) - f2;
        float sqrt2 = (float) Math.sqrt((double) ((Math.abs((((float) i5) - f2) - ((float) i3)) * Math.abs((((float) i5) - f2) - ((float) i3))) + (Math.abs((((float) i4) - f) - ((float) i9)) * Math.abs((((float) i4) - f) - ((float) i9)))));
        float[] fArr = new float[10];
        canvas.getMatrix().getValues(fArr);
        float f6 = fArr[2];
        float f7 = f3 + f6;
        if (i9 == 0) {
            linearGradient = new LinearGradient(f7 - abs3, f4, f7, f4, 0, -1442840576, TileMode.CLAMP);
        } else {
            linearGradient = new LinearGradient(f7, f4, f7 + abs3, f4, -1442840576, 0, TileMode.CLAMP);
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(((float) ((i3 == 0 ? -1 : 1) * (i9 == 0 ? -1 : 1))) * ((float) ((180.0d * atan2) / 3.141592653589793d)), f7, f4);
        canvas.save();
        canvas.clipRect(0, 0, i7, i8);
        canvas.setMatrix(matrix);
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        if (i9 == 0) {
            f5 = f7 - abs3;
        } else {
            f5 = f7;
        }
        int i13 = (int) f5;
        if (i3 == 0) {
            f5 = f4 - sqrt2;
        } else {
            f5 = f4;
        }
        i = (int) f5;
        if (i9 != 0) {
            f7 += abs3;
        }
        int i14 = (int) f7;
        if (i3 != 0) {
            f4 += sqrt2;
        }
        shapeDrawable.setBounds(i13, i, i14, (int) f4);
        shapeDrawable.getPaint().setAntiAlias(true);
        shapeDrawable.getPaint().setShader(linearGradient);
        shapeDrawable.draw(canvas);
        canvas.restore();
        Path path = new Path();
        path.rewind();
        path.moveTo((float) i10, (float) i11);
        path.lineTo((float) ((i10 + i9) / 2), (float) ((i11 + i5) / 2));
        path.quadTo((float) i9, (float) i5, (float) i9, ((float) i5) - f2);
        if (Math.abs((((float) i5) - f2) - ((float) i3)) < ((float) i8)) {
            path.lineTo((float) i9, (float) abs2);
        }
        path.lineTo((float) abs, (float) abs2);
        path.lineTo((float) (((i9 == 0 ? 1 : -1) * i7) + abs), (float) abs2);
        path.lineTo((float) (((i9 == 0 ? 1 : -1) * i7) + abs), (float) i3);
        path.lineTo((float) abs, (float) i3);
        if (Math.abs((((float) i4) - f) - ((float) i9)) < ((float) i7)) {
            path.lineTo((float) abs, (float) i3);
        }
        path.lineTo(((float) i4) - f, (float) i3);
        if (obj != null) {
            f5 = (float) Math.atan2((double) Math.abs(i3 - i11), (double) Math.abs(i4 - i10));
            path.quadTo((float) i4, (float) i3, ((float) i4) - ((float) (((double) f) * Math.cos((double) f5))), (float) ((Math.sin((double) f5) * ((double) (((float) (i3 == 0 ? 1 : -1)) * Math.abs(f)))) + ((double) i3)));
            path.lineTo((float) ((i10 + i4) / 2), (float) ((i11 + i3) / 2));
        } else {
            path.quadTo((float) i4, (float) i3, (float) ((i10 + i4) / 2), (float) ((i11 + i3) / 2));
        }
        path.close();
        canvas.save();
        canvas.clipPath(path);
        if (this.h == PageIndex.previous) {
            canvas.drawBitmap(a, -f6, 0.0f, this.y);
        } else {
            canvas.drawBitmap(a, 0.0f, 0.0f, this.y);
        }
        i2 = this.t.getIntrinsicWidth();
        i = (this.l / 2) + intrinsicWidth;
        this.t.setBounds((i - i2) - ((int) f6), 0, i - ((int) f6), this.m);
        this.t.draw(canvas);
        this.u.setBounds(i - ((int) f6), 0, (i2 + i) - ((int) f6), this.m);
        this.u.draw(canvas);
        double min3 = (double) (Math.min(i7, i8) / 10);
        double atan22 = (double) ((float) Math.atan2((double) Math.abs(i3 - i11), (double) Math.abs(i9 - i10)));
        double d2 = (180.0d * atan22) / 3.141592653589793d;
        double abs4 = ((((double) ((d2 < 45.0d ? -1 : 1) * 2)) * Math.abs(45.0d - d2)) / 45.0d) + d2;
        if (abs4 < 0.0d) {
            abs4 = 0.0d;
        } else if (abs4 > 90.0d) {
            abs4 = 90.0d;
        }
        double d3 = (abs4 * 3.141592653589793d) / 180.0d;
        if (min3 > (min3 * sqrt) / cos) {
            d = (min3 * sqrt) / cos;
        } else {
            d = min3;
        }
        atan2 = ((90.0d - (((Math.atan2((double) Math.abs(i9 - i4), (double) Math.abs(i3 - i5)) * 2.0d) * 180.0d) / 3.141592653589793d)) * 3.141592653589793d) / 180.0d;
        min3 = sqrt + d;
        cos = i9 == 0 ? Math.cos(d3) * min3 : ((double) i9) - (Math.cos(d3) * min3);
        double sin = i3 == 0 ? Math.sin(d3) * min3 : ((double) i3) - (min3 * Math.sin(d3));
        min3 = ((double) i4) + (((double) (i9 == 0 ? 1 : -1)) * (((d / 2.0d) / Math.cos(atan22)) + (((2.0d * d) * ((double) Math.abs(45 - ((int) d2)))) / 45.0d)));
        if (d2 <= 0.2d) {
            d3 = (double) i5;
        } else {
            d3 = i3 == 0 ? ((double) i5) + ((d / 2.0d) / Math.sin(atan22)) : ((double) i5) - ((d / 2.0d) / Math.sin(atan22));
        }
        Path path2 = new Path();
        path2.moveTo((float) cos, (float) sin);
        path2.lineTo((float) i10, (float) i11);
        path2.lineTo((float) i4, (float) i3);
        path2.lineTo((float) min3, (float) i3);
        path2.close();
        f5 = (float) cos;
        f4 = (float) sin;
        f7 = (float) (((((double) (i9 == 0 ? -1 : 1)) * d) * Math.cos(atan2)) + cos);
        if (i3 == 0) {
            i6 = 1;
        } else {
            i6 = -1;
        }
        linearGradient = new LinearGradient(f5, f4, f7, (float) (((((double) i6) * d) * Math.sin(atan2)) + sin), 0, Integer.MIN_VALUE, TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(linearGradient);
        canvas.drawPath(path2, paint);
        path2 = new Path();
        path2.moveTo((float) cos, (float) sin);
        path2.lineTo((float) i10, (float) i11);
        path2.lineTo((float) i9, (float) i5);
        path2.lineTo((float) i9, (float) d3);
        path2.close();
        f5 = (float) cos;
        f4 = (float) sin;
        f7 = (float) (((((double) (i9 == 0 ? -1 : 1)) * d) * Math.sin(atan2)) + cos);
        if (i3 == 0) {
            i6 = -1;
        } else {
            i6 = 1;
        }
        paint.setShader(new LinearGradient(f5, f4, f7, (float) (((((double) i6) * d) * Math.cos(atan2)) + sin), 0, Integer.MIN_VALUE, TileMode.CLAMP));
        canvas.drawPath(path2, paint);
        canvas.restore();
        Path path3 = new Path();
        path.moveTo((float) i10, (float) i11);
        path.lineTo((float) ((i10 + i9) / 2), (float) ((i11 + i5) / 2));
        path.quadTo((float) i9, (float) i5, (float) i9, ((float) i5) - f2);
        if (Math.abs((((float) i5) - f2) - ((float) i3)) < ((float) i8)) {
            path.lineTo((float) i9, (float) abs2);
        }
        path.lineTo((float) abs, (float) abs2);
        if (Math.abs((((float) i4) - f) - ((float) i9)) < ((float) i7)) {
            path.lineTo((float) abs, (float) i3);
        }
        path.lineTo(((float) i4) - f, (float) i3);
        path.quadTo((float) i4, (float) i3, (float) ((i10 + i4) / 2), (float) ((i11 + i3) / 2));
        path.close();
        float f8 = (float) (((((i10 + i9) / 2) + (i9 * 2)) + i9) / 4);
        f4 = (((float) (((i5 * 2) + ((i11 + i5) / 2)) + i5)) - f2) / 4.0f;
        if (obj != null) {
            f5 = (float) Math.atan2((double) Math.abs(i3 - i11), (double) Math.abs(i4 - i10));
            f5 = (((((float) i4) - f) + ((float) (i4 * 2))) + (((float) i4) - ((float) (((double) f) * Math.cos((double) f5))))) / 4.0f;
            f3 = (((float) ((Math.sin((double) f5) * ((double) (((float) (i3 == 0 ? 1 : -1)) * Math.abs(f)))) + ((double) i3))) + ((float) ((i3 * 2) + i3))) / 4.0f;
        } else {
            f5 = (((((float) i4) - f) + ((float) (i4 * 2))) + ((float) ((i10 + i4) / 2))) / 4.0f;
            f3 = (float) ((((i3 * 2) + i3) + ((i11 + i3) / 2)) / 4);
        }
        d3 = (double) ((float) Math.atan2((double) Math.abs(f5 - f8), (double) Math.abs(f3 - f4)));
        path3.moveTo((float) i10, (float) i11);
        path3.lineTo((float) ((i10 + i9) / 2), (float) ((i11 + i5) / 2));
        path3.quadTo((float) (((i9 * 3) + i10) / 4), (float) (((i5 * 3) + i11) / 4), f8, f4);
        path3.lineTo(f5, f3);
        path3.quadTo((float) (((i4 * 3) + i10) / 4), (float) (((i3 * 3) + i11) / 4), (float) ((i10 + i4) / 2), (float) ((i11 + i3) / 2));
        path3.close();
        canvas.drawPath(path3, this.x);
        canvas.save();
        Bitmap a2 = this.k.a(pageIndex2);
        if (a2 != null) {
            canvas.save();
            canvas.clipPath(path3);
            matrix = new Matrix();
            if (i9 == 0) {
                matrix.postTranslate((float) (i10 - i7), (float) (i11 - i3));
            } else {
                matrix.postTranslate((float) i10, (float) (i11 - i3));
            }
            if (i9 == 0) {
                f3 = 57.295647f * ((float) Math.atan2((double) (i11 - i3), (double) (i10 - i4)));
            } else {
                f3 = -57.295647f * ((float) Math.atan2((double) (i11 - i3), (double) (i4 - i10)));
            }
            matrix.postRotate(f3, (float) i10, (float) i11);
            canvas.drawBitmap(a2, matrix, this.y);
            canvas.restore();
        }
        canvas.save();
        float abs5 = Math.abs(f) / 2.0f;
        float sqrt3 = (float) Math.sqrt((double) ((Math.abs((((float) i5) - f2) - ((float) i3)) * Math.abs((((float) i5) - f2) - ((float) i3))) + (Math.abs((((float) i4) - f) - ((float) i9)) * Math.abs((((float) i4) - f) - ((float) i9)))));
        float[] fArr2 = new float[10];
        canvas.getMatrix().getValues(fArr2);
        canvas.save();
        canvas.clipPath(path3);
        ShapeDrawable shapeDrawable2 = new ShapeDrawable();
        float f9 = f8 + fArr2[2];
        linearGradient = new LinearGradient(i9 == 0 ? f9 : f9 - abs5, f4, i9 == 0 ? f9 + abs5 : f9, f4, i9 == 0 ? new int[]{536870912, 855638016, 0} : new int[]{0, 855638016, 536870912}, i9 == 0 ? new float[]{0.0f, 0.3f, 1.0f} : new float[]{0.0f, 0.7f, 1.0f}, TileMode.CLAMP);
        Matrix matrix2 = new Matrix();
        i = i9 == 0 ? -1 : 1;
        matrix2.setTranslate(0.0f, 0.0f);
        matrix2.postRotate(((float) (i * (i3 == 0 ? -1 : 1))) * ((float) ((180.0d * d3) / 3.141592653589793d)), f9, f4);
        canvas.setMatrix(matrix2);
        i14 = (int) (i9 == 0 ? f9 : f9 - abs5);
        if (i3 == 0) {
            f5 = f4 - sqrt3;
        } else {
            f5 = f4;
        }
        i6 = (int) f5;
        if (i9 == 0) {
            f5 = f9 + abs5;
        } else {
            f5 = f9;
        }
        i = (int) f5;
        if (i3 != 0) {
            f4 += sqrt3;
        }
        shapeDrawable2.setBounds(i14, i6, i, (int) f4);
        shapeDrawable2.getPaint().setAntiAlias(true);
        shapeDrawable2.getPaint().setShader(linearGradient);
        shapeDrawable2.draw(canvas);
        canvas.restore();
    }

    public boolean a(Canvas canvas) {
        int intrinsicWidth = this.s.getIntrinsicWidth();
        this.t.getIntrinsicWidth();
        int i = (this.l / 2) + intrinsicWidth;
        int i2 = this.l + (intrinsicWidth * 2);
        if (h() || e()) {
            Matrix matrix = canvas.getMatrix();
            Matrix matrix2 = new Matrix();
            Bitmap a;
            if (this.h == PageIndex.previous) {
                canvas.drawBitmap(this.k.a(PageIndex.current_right), (float) i, 0.0f, this.y);
                a = this.k.a(PageIndex.previous_left);
                canvas.drawBitmap(a, (float) (i - a.getWidth()), 0.0f, this.y);
                matrix2.setTranslate((float) intrinsicWidth, 0.0f);
                canvas.setMatrix(matrix2);
                a(canvas, PageIndex.current_left, PageIndex.previous_right);
            } else if (this.h == PageIndex.next) {
                a = this.k.a(PageIndex.current_left);
                canvas.drawBitmap(a, (float) (i - a.getWidth()), 0.0f, this.y);
                canvas.drawBitmap(this.k.a(PageIndex.next_right), (float) i, 0.0f, this.y);
                matrix2.setTranslate((float) i, 0.0f);
                canvas.setMatrix(matrix2);
                a(canvas, PageIndex.current_right, PageIndex.next_left);
            }
            canvas.setMatrix(matrix);
            this.s.setBounds(0, 0, intrinsicWidth, this.m);
            this.s.draw(canvas);
            this.v.setBounds(i2 - intrinsicWidth, 0, i2, this.m);
            this.v.draw(canvas);
        } else {
            b(canvas);
        }
        return true;
    }

    public PageIndex a(float f, float f2) {
        if (((float) this.e.x) < f) {
            a(PageIndex.previous);
            int i = this.m;
            int i2 = this.e.y;
            this.c = -1.0f;
            this.b = -1.0f;
            this.q = true;
            if (i2 <= i / 2) {
                this.z = TouchArea.leftTop;
            } else {
                this.z = TouchArea.leftButtom;
            }
        } else if (((float) this.e.x) > f) {
            a(PageIndex.next);
            if (this.e.y <= this.m / 2) {
                this.z = TouchArea.rightTop;
            } else {
                this.z = TouchArea.rightButtom;
            }
            this.c = -1.0f;
            this.b = -1.0f;
            this.q = true;
        }
        return this.h;
    }

    public void b(float f, float f2) {
        this.e.set((int) f, (int) f2);
        this.c = -1.0f;
        this.b = -1.0f;
        this.p = false;
        this.q = false;
    }

    private void k() {
        int i = this.l;
        int i2 = this.m;
        int i3 = this.e.x;
        int i4 = this.e.y;
        switch (ab.a((float) i3, (float) i4, i, i2)) {
            case 0:
                a(PageIndex.next);
                if (i4 <= i2 / 2) {
                    this.z = TouchArea.rightTop;
                    return;
                } else {
                    this.z = TouchArea.rightButtom;
                    return;
                }
            case 1:
                a(PageIndex.previous);
                if (i4 <= i2 / 2) {
                    this.z = TouchArea.leftTop;
                    return;
                } else {
                    this.z = TouchArea.leftButtom;
                    return;
                }
            default:
                return;
        }
    }

    public void b(int i, int i2) {
        int i3 = this.l;
        int i4 = this.m;
        int intrinsicWidth = this.s.getIntrinsicWidth();
        if (i >= intrinsicWidth) {
            intrinsicWidth = i > this.l + intrinsicWidth ? intrinsicWidth + this.l : i;
        }
        int i5 = i2 < 0 ? 0 : i2 > this.m ? this.m : i2;
        Point a = a(intrinsicWidth, i5, this.z, i3, i4);
        this.b = (float) a.x;
        this.c = (float) a.y;
        this.d = AnimState.DRAGING;
        this.p = true;
    }

    public void a(int i, int i2, int i3, int i4, Mode mode, int i5) {
        int i6 = 0;
        if (this.d == AnimState.ANIMATING) {
            i();
        }
        this.d = AnimState.ANIMATING;
        if (!this.q) {
            k();
        }
        this.a = mode;
        int i7 = this.l / 2;
        int i8 = this.m;
        Point a = a(i, i2, this.z, this.l, i8);
        int i9 = a.x;
        int i10 = a.y;
        if (this.p) {
            i9 = (int) this.b;
        }
        if (this.p) {
            i10 = (int) this.c;
        }
        this.e = new Point(i9, i10);
        i9 = (mode == Mode.AutoScrollingForward || mode == Mode.ForceScrolling) ? 1 : 0;
        if (this.h == PageIndex.previous) {
            if (i9 != 0) {
                i9 = i7 * 2;
            } else {
                i9 = 0;
            }
            if (this.z != TouchArea.leftTop) {
                i6 = i8;
            }
            this.f = new Point(i9, i6);
        } else if (this.h == PageIndex.next) {
            i9 = i9 != 0 ? -i7 : i7;
            if (this.z != TouchArea.rightTop) {
                i6 = i8;
            }
            this.f = new Point(i9, i6);
        } else {
            this.f = new Point(i3, i4);
        }
        this.w.startScroll(this.e.x, this.e.y, this.f.x - this.e.x, this.f.y - this.e.y, (int) ((((float) Math.abs(this.f.x - this.e.x)) / ((float) (i7 * 2))) * ((float) i5)));
    }

    public void i() {
        super.i();
        this.z = TouchArea.None;
        this.d = AnimState.READY;
        this.c = -1.0f;
        this.b = -1.0f;
        this.w.abortAnimation();
        if (!this.w.isFinished()) {
            this.w.forceFinished(true);
        }
        this.p = false;
    }

    public int a(b bVar) {
        int f;
        synchronized (this.r) {
            if (this.d == AnimState.ANIMATING) {
                j();
                this.d = AnimState.READY;
                this.c = -1.0f;
                this.b = -1.0f;
                this.w.abortAnimation();
                if (!this.w.isFinished()) {
                    this.w.forceFinished(true);
                }
                this.p = false;
            }
            boolean d = this.k.d(PageIndex.previous_right);
            boolean d2 = this.k.d(PageIndex.previous_left);
            if (d && d2) {
                bVar.f();
                f = bVar.f();
            } else {
                f = bVar.f();
                a(f, PageIndex.previous_right);
                a(bVar.f(), PageIndex.previous_left);
            }
        }
        return f;
    }

    public int b(b bVar) {
        int e;
        synchronized (this.r) {
            if (this.d == AnimState.ANIMATING) {
                j();
                this.d = AnimState.READY;
                this.c = -1.0f;
                this.b = -1.0f;
                this.w.abortAnimation();
                if (!this.w.isFinished()) {
                    this.w.forceFinished(true);
                }
                this.p = false;
            }
            boolean d = this.k.d(PageIndex.next_left);
            if (this.k.d(PageIndex.next_right) && d) {
                bVar.e();
                e = bVar.e();
            } else {
                bVar.e();
                e = bVar.e();
                a(e, PageIndex.next_left);
                a(bVar.e(), PageIndex.next_right);
                bVar.f();
            }
        }
        return e;
    }
}

package android.support.v4.view;

import android.animation.ValueAnimator;
import android.graphics.Paint;
import android.view.View;

/* compiled from: ViewCompatHC */
class ad {
    static long a() {
        return ValueAnimator.getFrameDelay();
    }

    public static float a(View view) {
        return view.getAlpha();
    }

    public static void a(View view, int i, Paint paint) {
        view.setLayerType(i, paint);
    }

    public static int b(View view) {
        return view.getLayerType();
    }

    public static int a(int i, int i2, int i3) {
        return View.resolveSizeAndState(i, i2, i3);
    }

    public static int c(View view) {
        return view.getMeasuredState();
    }

    public static float d(View view) {
        return view.getTranslationX();
    }

    public static float e(View view) {
        return view.getTranslationY();
    }

    public static float f(View view) {
        return view.getScaleX();
    }

    public static void a(View view, float f) {
        view.setTranslationX(f);
    }

    public static void b(View view, float f) {
        view.setTranslationY(f);
    }

    public static void c(View view, float f) {
        view.setAlpha(f);
    }

    public static void d(View view, float f) {
        view.setX(f);
    }

    public static void e(View view, float f) {
        view.setY(f);
    }

    public static void f(View view, float f) {
        view.setScaleX(f);
    }

    public static void g(View view, float f) {
        view.setScaleY(f);
    }

    public static void a(View view, boolean z) {
        view.setSaveFromParentEnabled(z);
    }
}

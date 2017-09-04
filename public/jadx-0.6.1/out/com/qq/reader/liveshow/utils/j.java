package com.qq.reader.liveshow.utils;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PathAnimator */
public class j extends a {
    private final AtomicInteger b = new AtomicInteger(0);
    private Handler c = new Handler(Looper.getMainLooper());

    /* compiled from: PathAnimator */
    static class a extends Animation {
        private PathMeasure a;
        private View b;
        private float c = this.a.getLength();
        private float d;

        public a(Path path, float f, View view, View view2) {
            this.a = new PathMeasure(path, false);
            this.b = view2;
            this.d = f;
            view.setLayerType(2, null);
        }

        protected void applyTransformation(float f, Transformation transformation) {
            this.a.getMatrix(this.c * f, transformation.getMatrix(), 1);
            this.b.setRotation(this.d * f);
            float a = 3000.0f * f < 200.0f ? j.b((double) f, 0.0d, 0.06666667014360428d, 0.20000000298023224d, 1.100000023841858d) : 3000.0f * f < 300.0f ? j.b((double) f, 0.06666667014360428d, 0.10000000149011612d, 1.100000023841858d, 1.0d) : 1.0f;
            this.b.setScaleX(a);
            this.b.setScaleY(a);
            transformation.setAlpha(1.0f - f);
        }
    }

    public j(com.qq.reader.liveshow.utils.a.a aVar) {
        super(aVar);
    }

    public void a(View view, ViewGroup viewGroup, final AnimationListener animationListener) {
        Animation aVar = new a(a(this.b, (View) viewGroup, 2), a(), viewGroup, view);
        aVar.setDuration((long) this.a.j);
        aVar.setInterpolator(new AccelerateInterpolator());
        aVar.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ j b;

            public void onAnimationEnd(Animation animation) {
                animationListener.onAnimationEnd(animation);
                this.b.b.decrementAndGet();
            }

            public void onAnimationRepeat(Animation animation) {
                animationListener.onAnimationRepeat(animation);
            }

            public void onAnimationStart(Animation animation) {
                animationListener.onAnimationStart(animation);
                this.b.b.incrementAndGet();
            }
        });
        aVar.setInterpolator(new LinearInterpolator());
        view.startAnimation(aVar);
    }

    private static float b(double d, double d2, double d3, double d4, double d5) {
        return (float) ((((d - d2) / (d3 - d2)) * (d5 - d4)) + d4);
    }
}

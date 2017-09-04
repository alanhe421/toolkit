package com.qq.reader.view.animation;

import android.app.Activity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.f;

/* compiled from: AnimationHelper */
public class b {
    public static boolean b = false;
    c a;

    public b(Activity activity) {
        this.a = new c(activity);
    }

    public void a(View view) {
        b = true;
        c.a("ani", "applyFirstRotation");
        float height = ((float) view.getHeight()) / 2.0f;
        f.b("centerX =" + (((float) view.getWidth()) / 2.0f), "centerX");
        f.b("centerY =" + height, "centerY");
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        alphaAnimation.setAnimationListener(this.a);
        view.startAnimation(alphaAnimation);
    }

    public void b(View view) {
        c.a("ani", "applyLastRotation ");
        float height = ((float) view.getHeight()) / 2.0f;
        f.b("centerX =" + (((float) view.getWidth()) / 2.0f), "centerX");
        f.b("centerY =" + height, "centerY");
        Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        view.startAnimation(alphaAnimation);
        b = false;
    }
}

package com.qq.reader.view.animation;

import android.app.Activity;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/* compiled from: DisplayNextView */
public class c implements AnimationListener {
    Activity a;

    /* compiled from: DisplayNextView */
    private final class a implements Runnable {
        final /* synthetic */ c a;

        private a(c cVar) {
            this.a = cVar;
        }

        public void run() {
            ((a) this.a.a).jumpActivity();
        }
    }

    public c(Activity activity) {
        this.a = activity;
    }

    public void onAnimationStart(Animation animation) {
        com.qq.reader.common.monitor.debug.c.a("ani", "onAnimationStart  current " + this.a.getClass().getSimpleName());
        a();
    }

    public void onAnimationEnd(Animation animation) {
        com.qq.reader.common.monitor.debug.c.a("ani", "onAnimationEnd  current " + this.a.getClass().getSimpleName());
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void a() {
        ((a) this.a).getLayout().post(new a());
    }
}

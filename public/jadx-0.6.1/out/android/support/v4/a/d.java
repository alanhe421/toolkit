package android.support.v4.a;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;

/* compiled from: HoneycombMr1AnimatorCompatProvider */
class d implements b {
    private TimeInterpolator a;

    d() {
    }

    public void a(View view) {
        if (this.a == null) {
            this.a = new ValueAnimator().getInterpolator();
        }
        view.animate().setInterpolator(this.a);
    }
}

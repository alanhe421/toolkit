package android.support.v4.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* compiled from: ViewPropertyAnimatorCompatICS */
class ap {
    public static void a(View view, long j) {
        view.animate().setDuration(j);
    }

    public static void a(View view, float f) {
        view.animate().alpha(f);
    }

    public static void b(View view, float f) {
        view.animate().translationX(f);
    }

    public static void c(View view, float f) {
        view.animate().translationY(f);
    }

    public static void a(View view) {
        view.animate().cancel();
    }

    public static void b(View view) {
        view.animate().start();
    }

    public static void a(final View view, final ar arVar) {
        if (arVar != null) {
            view.animate().setListener(new AnimatorListenerAdapter() {
                public void onAnimationCancel(Animator animator) {
                    arVar.c(view);
                }

                public void onAnimationEnd(Animator animator) {
                    arVar.b(view);
                }

                public void onAnimationStart(Animator animator) {
                    arVar.a(view);
                }
            });
        } else {
            view.animate().setListener(null);
        }
    }
}

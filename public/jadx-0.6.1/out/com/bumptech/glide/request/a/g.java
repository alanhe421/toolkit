package com.bumptech.glide.request.a;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/* compiled from: ViewAnimationFactory */
public class g<R> implements d<R> {
    private final a a;
    private c<R> b;

    /* compiled from: ViewAnimationFactory */
    private static class a implements a {
        private final Context a;
        private final int b;

        public a(Context context, int i) {
            this.a = context.getApplicationContext();
            this.b = i;
        }

        public Animation a() {
            return AnimationUtils.loadAnimation(this.a, this.b);
        }
    }

    public g(Context context, int i) {
        this(new a(context, i));
    }

    g(a aVar) {
        this.a = aVar;
    }

    public c<R> a(boolean z, boolean z2) {
        if (z || !z2) {
            return e.b();
        }
        if (this.b == null) {
            this.b = new f(this.a);
        }
        return this.b;
    }
}

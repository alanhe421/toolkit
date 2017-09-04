package com.nhaarman.listviewanimations.a;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.util.SparseArray;
import android.view.View;
import android.widget.GridView;
import com.nhaarman.listviewanimations.b.e;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.view.ViewHelper;
import com.tencent.tinker.android.dx.instruction.Opcodes;

/* compiled from: ViewAnimator */
public class b {
    private final e a;
    private final SparseArray<Animator> b = new SparseArray();
    private int c = Opcodes.OR_INT;
    private int d = 100;
    private int e = 300;
    private long f;
    private int g;
    private int h;
    private boolean i = true;

    public b(e eVar) {
        this.a = eVar;
        this.f = -1;
        this.g = -1;
        this.h = -1;
    }

    void a(int i) {
        this.h = i;
    }

    void a(View view) {
        int hashCode = view.hashCode();
        Animator animator = (Animator) this.b.get(hashCode);
        if (animator != null) {
            animator.end();
            this.b.remove(hashCode);
        }
    }

    public void a(int i, View view, Animator[] animatorArr) {
        if (this.i && i > this.h) {
            if (this.g == -1) {
                this.g = i;
            }
            b(i, view, animatorArr);
            this.h = i;
        }
    }

    private void b(int i, View view, Animator[] animatorArr) {
        if (this.f == -1) {
            this.f = SystemClock.uptimeMillis();
        }
        ViewHelper.setAlpha(view, 0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorArr);
        animatorSet.setStartDelay((long) b(i));
        animatorSet.setDuration((long) this.e);
        animatorSet.start();
        this.b.put(view.hashCode(), animatorSet);
    }

    @SuppressLint({"NewApi"})
    private int b(int i) {
        if ((this.a.d_() - this.a.c_()) + 1 < (i - 1) - this.g) {
            int i2 = this.d;
            if (!(this.a.h() instanceof GridView) || VERSION.SDK_INT < 11) {
                return i2;
            }
            return ((i % ((GridView) this.a.h()).getNumColumns()) * this.d) + i2;
        }
        return Math.max(0, (int) ((((-SystemClock.uptimeMillis()) + this.f) + ((long) this.c)) + ((long) ((i - this.g) * this.d))));
    }
}

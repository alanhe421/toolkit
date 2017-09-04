package com.qq.reader.liveshow.views.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.RelativeLayout;
import com.qq.reader.liveshow.a;
import com.qq.reader.liveshow.a.c;
import com.qq.reader.liveshow.a.d;
import com.qq.reader.liveshow.utils.SxbLog;
import com.qq.reader.liveshow.utils.j;
import com.qq.reader.module.question.card.AudioQuestionQuizCard;
import com.tencent.smtt.utils.TbsLog;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HeartLayout extends RelativeLayout {
    static final int[] a = new int[]{9, 99, TbsLog.TBSLOG_CODE_SDK_INIT, 9999, 99999, AudioQuestionQuizCard.MAX_BOOKCOIN_COUNT, 9999999, 99999999, 999999999, Integer.MAX_VALUE};
    private static HeartHandler d;
    private static int e = 15;
    private static int[] m = new int[]{d.heart0, d.heart1, d.heart2, d.heart3, d.heart4, d.heart5, d.heart6, d.heart7, d.heart8};
    private AttributeSet b = null;
    private int c = 0;
    private List<View> f = Collections.synchronizedList(new ArrayList());
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private Random n = new Random();

    public class HeartHandler extends Handler {
        WeakReference<HeartLayout> a;
        final /* synthetic */ HeartLayout b;

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (((HeartLayout) this.a.get()) != null) {
                switch (message.what) {
                    case 1:
                        this.b.a();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public class HeartThread implements Runnable {
        final /* synthetic */ HeartLayout a;
        private long b;
        private int c;

        public void run() {
            if (HeartLayout.d != null) {
                if (this.c > 0) {
                    HeartLayout.d.sendEmptyMessage(1);
                    this.c--;
                }
                this.a.postDelayed(this, this.b);
            }
        }
    }

    public HeartLayout(Context context) {
        super(context);
        a(context);
    }

    public HeartLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = attributeSet;
        a(context);
    }

    public HeartLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = attributeSet;
        this.c = i;
        a(context);
    }

    private void a(Context context) {
        this.i = getResources().getDimensionPixelOffset(c.heart_size_height);
        this.j = getResources().getDimensionPixelOffset(c.heart_size_width);
        this.l = this.j;
        for (int i = 0; i < e; i++) {
            this.f.add(c());
        }
    }

    private j a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, a.j.HeartLayout, i, 0);
        if (this.l <= this.k && this.l >= 0) {
            this.l -= 20;
        } else if (this.l < (-this.k) || this.l > 0) {
            this.l = this.k;
        } else {
            this.l += 20;
        }
        j jVar = new j(com.qq.reader.liveshow.utils.a.a.a(obtainStyledAttributes, (float) this.k, (float) this.i, this.l, this.j, this.i));
        obtainStyledAttributes.recycle();
        return jVar;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.h = getMeasuredWidth();
        this.g = getMeasuredHeight();
        this.k = (this.h - getResources().getDimensionPixelOffset(c.heart_init_x_offset)) - (this.j / 2);
    }

    public void clearAnimation() {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).clearAnimation();
        }
    }

    private View c() {
        View heartView = new HeartView(getContext());
        heartView.setDrawable(m[this.n.nextInt(8)]);
        heartView.setVisibility(4);
        addView(heartView, new LayoutParams(this.j, this.i));
        SxbLog.b("HeartLayout", "add a heart view ,Child count = " + getChildCount());
        return heartView;
    }

    public void a() {
        if (this.f.size() > 0) {
            final View view = (View) this.f.remove(0);
            a(this.b, this.c).a(view, (ViewGroup) this, new AnimationListener(this) {
                final /* synthetic */ HeartLayout b;

                public void onAnimationStart(Animation animation) {
                    view.setVisibility(0);
                }

                public void onAnimationEnd(Animation animation) {
                    this.b.f.add(view);
                    view.setVisibility(4);
                    view.clearAnimation();
                }

                public void onAnimationRepeat(Animation animation) {
                }
            });
        }
    }
}

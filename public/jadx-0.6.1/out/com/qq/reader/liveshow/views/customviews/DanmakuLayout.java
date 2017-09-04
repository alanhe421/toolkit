package com.qq.reader.liveshow.views.customviews;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import com.qq.reader.liveshow.a.e;
import com.qq.reader.liveshow.a.g;
import com.qq.reader.liveshow.b.c;
import com.qq.reader.liveshow.model.im.message.a.b;
import java.util.ArrayList;
import java.util.List;

public class DanmakuLayout extends LinearLayout {
    private List<DanmakuItemView> a;
    private int b = 30;
    private int c = 15;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private c g;

    public DanmakuLayout(Context context) {
        super(context);
        a(context);
    }

    public DanmakuLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public DanmakuLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(g.ly_danmaku, this);
        this.a = new ArrayList();
        this.a.add((DanmakuItemView) findViewById(e.track1));
        this.a.add((DanmakuItemView) findViewById(e.track2));
    }

    public void a(b bVar) {
        for (DanmakuItemView danmakuItemView : this.a) {
            if (danmakuItemView.a()) {
                danmakuItemView.setData(bVar);
                a(danmakuItemView);
                return;
            }
        }
    }

    private void a(final DanmakuItemView danmakuItemView) {
        int right = (getRight() - getLeft()) - getPaddingLeft();
        danmakuItemView.b();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(danmakuItemView, "translationX", new float[]{(float) right, (float) (-right)});
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.addListener(new AnimatorListenerAdapter(this) {
            final /* synthetic */ DanmakuLayout b;

            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                danmakuItemView.clearAnimation();
                danmakuItemView.c();
                this.b.g.a(danmakuItemView.getData());
            }
        });
        ofFloat.setDuration((long) danmakuItemView.a);
        ofFloat.start();
    }

    public void setPositionCallback(c cVar) {
        this.g = cVar;
    }
}

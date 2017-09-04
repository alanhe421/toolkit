package com.qq.reader.liveshow.views.customviews;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import com.qq.reader.liveshow.a.e;
import com.qq.reader.liveshow.a.g;
import com.qq.reader.liveshow.b.c;
import com.qq.reader.liveshow.model.im.message.a.b;
import com.qq.reader.liveshow.utils.p;
import com.tencent.qalsdk.im_open.http;

public class SuperVipEnterLayout extends RelativeLayout {
    private Context a;
    private final int b = http.Internal_Server_Error;
    private final int c = 1000;
    private SuperVipEnterView d;
    private c e;

    public SuperVipEnterLayout(Context context) {
        super(context);
        a(context);
    }

    public SuperVipEnterLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public SuperVipEnterLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        this.a = context;
        LayoutInflater.from(context).inflate(g.ly_vipenter, this);
        this.d = (SuperVipEnterView) findViewById(e.vip_enter);
    }

    public void a(b bVar) {
        this.d.setData(bVar);
        this.d.setVisibility(0);
        a();
    }

    public void a() {
        int right = (getRight() - getLeft()) - getPaddingLeft();
        Animator ofFloat = ObjectAnimator.ofFloat(this.d, "translationX", new float[]{(float) right, (float) p.a(12.0f, this.a)});
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.setDuration(500);
        Animator ofFloat2 = ObjectAnimator.ofFloat(this.d, "translationX", new float[]{0.0f, (float) (-right)});
        ofFloat2.setInterpolator(new LinearInterpolator());
        ofFloat2.addListener(new AnimatorListenerAdapter(this) {
            final /* synthetic */ SuperVipEnterLayout a;

            {
                this.a = r1;
            }

            public void onAnimationEnd(Animator animator) {
                this.a.d.setVisibility(8);
                this.a.e.a(this.a.d.getData());
            }
        });
        ofFloat2.setDuration(500);
        ofFloat2.setStartDelay(1000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat).before(ofFloat2);
        animatorSet.start();
    }

    public void setPositionCallback(c cVar) {
        this.e = cVar;
    }
}

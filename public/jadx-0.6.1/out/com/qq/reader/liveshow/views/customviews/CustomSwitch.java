package com.qq.reader.liveshow.views.customviews;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.qq.reader.liveshow.a.d;

public class CustomSwitch extends ImageView {
    private boolean a = false;
    private AnimationDrawable b;
    private Handler c;
    private Runnable d;

    private void a() {
        this.c = new Handler();
        this.d = new Runnable(this) {
            final /* synthetic */ CustomSwitch a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.b();
            }
        };
    }

    public CustomSwitch(Context context) {
        super(context);
        a();
    }

    public CustomSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public CustomSwitch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public int a(AnimationDrawable animationDrawable) {
        int i = 0;
        int i2 = 0;
        while (i < animationDrawable.getNumberOfFrames()) {
            i2 += animationDrawable.getDuration(i);
            i++;
        }
        return i2;
    }

    private void b() {
        if (this.a) {
            setImageResource(d.btn_switch_on);
        } else {
            setImageResource(d.btn_switch_off);
        }
    }

    public void setChecked(boolean z, boolean z2) {
        if (z != this.a) {
            this.a = z;
            if (z2) {
                setImageResource(this.a ? d.switch_open : d.switch_close);
                this.b = (AnimationDrawable) getDrawable();
                this.b.start();
                this.c.postDelayed(this.d, (long) a(this.b));
                return;
            }
            b();
        }
    }

    public boolean getChecked() {
        return this.a;
    }
}

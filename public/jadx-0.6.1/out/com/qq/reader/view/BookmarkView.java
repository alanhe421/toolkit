package com.qq.reader.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import com.tencent.av.sdk.AVError;
import com.tencent.feedback.proguard.R;
import java.util.Random;

public class BookmarkView extends TextView {
    public static final int[] c = new int[]{R.drawable.btn_mark_1, R.drawable.btn_mark_2, R.drawable.btn_mark_3, R.drawable.btn_mark_4, R.drawable.btn_mark_5, R.drawable.btn_mark_6};
    Animation a;
    Animation b;
    private String d = "BookMarkView";
    private final long e = 800;
    private final long f = 800;
    private int g = 0;
    private int h = 0;
    private Handler i = new Handler(this) {
        final /* synthetic */ BookmarkView a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what == AVError.AV_ERR_IMSDK_TIMEOUT && this.a.getVisibility() == 0) {
                this.a.startAnimation(this.a.b);
            } else if (message.what == 7001) {
                this.a.b();
            }
        }
    };

    public BookmarkView(Context context, int i, int i2, int i3, int i4) {
        super(context);
        Drawable drawable = getResources().getDrawable(c[new Random().nextInt(6)]);
        this.g = drawable.getMinimumWidth();
        this.h = drawable.getMinimumHeight();
        setBackgroundDrawable(drawable);
        int i5 = i4 - this.h > 0 ? -this.h : -i4;
        this.a = new TranslateAnimation((float) null, (float) null, (float) i5, (float) null);
        this.a.setDuration(800);
        this.a.setFillAfter(true);
        this.a.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ BookmarkView a;

            {
                this.a = r1;
            }

            public void onAnimationEnd(Animation animation) {
                new Thread(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.i.sendEmptyMessageDelayed(AVError.AV_ERR_IMSDK_TIMEOUT, 800);
                    }
                }.start();
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        this.b = new TranslateAnimation((float) null, (float) null, (float) null, (float) i5);
        this.b.setDuration(800);
        this.b.setFillAfter(true);
        this.b.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ BookmarkView a;

            {
                this.a = r1;
            }

            public void onAnimationEnd(Animation animation) {
                this.a.i.sendEmptyMessage(7001);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        super.setVisibility(4);
    }

    public int getImgWidth() {
        return this.g;
    }

    public int getImgHeight() {
        return this.h;
    }

    public synchronized void a() {
        setBackgroundResource(c[new Random().nextInt(6)]);
        super.setVisibility(0);
        startAnimation(this.a);
    }

    public synchronized void b() {
        if (getVisibility() == 0) {
            super.setVisibility(8);
            if (this.a.hasStarted() || this.b.hasStarted()) {
                setAnimation(null);
                if (this.i.hasMessages(AVError.AV_ERR_IMSDK_TIMEOUT)) {
                    this.i.removeMessages(AVError.AV_ERR_IMSDK_TIMEOUT);
                }
                if (this.i.hasMessages(7001)) {
                    this.i.removeMessages(7001);
                }
            }
        }
    }

    public void setVisibility(int i) {
    }
}

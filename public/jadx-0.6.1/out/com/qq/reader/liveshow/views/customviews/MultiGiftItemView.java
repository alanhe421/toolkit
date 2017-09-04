package com.qq.reader.liveshow.views.customviews;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.liveshow.a.e;
import com.qq.reader.liveshow.a.g;
import com.qq.reader.liveshow.a.h;
import com.qq.reader.liveshow.model.im.message.a.c;
import com.qq.reader.liveshow.utils.p;
import com.qq.reader.liveshow.utils.q;
import com.tencent.qalsdk.im_open.http;
import java.util.Timer;
import java.util.TimerTask;

public class MultiGiftItemView extends LinearLayout {
    private ImageView a;
    private TextView b;
    private TextView c;
    private ImageView d;
    private MultiClickNumberWidget e;
    private final int f = http.Internal_Server_Error;
    private final int g = http.Internal_Server_Error;
    private final int h = 200;
    private boolean i = true;
    private Context j;
    private Timer k = null;
    private int l = 0;
    private int m = 0;
    private c n;

    public MultiGiftItemView(Context context) {
        super(context);
        a(context);
    }

    public MultiGiftItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        this.j = context;
        LayoutInflater.from(context).inflate(g.multigift_item_layout, this);
        this.a = (ImageView) findViewById(e.avatar_icon);
        this.b = (TextView) findViewById(e.user_name);
        this.c = (TextView) findViewById(e.content_text);
        this.d = (ImageView) findViewById(e.gift_img);
        this.e = (MultiClickNumberWidget) findViewById(e.num_widget);
    }

    public void setData(final c cVar) {
        boolean z = true;
        if (cVar != null) {
            this.n = cVar;
            this.b.setText(cVar.b().getNickName());
            this.c.setText(this.j.getResources().getString(h.send_text, new Object[]{cVar.e()}));
            this.l = cVar.g();
            Context context = this.j;
            ImageView imageView = this.a;
            String avatar = cVar.b().getAvatar();
            if (cVar.b().getAuthorId() <= 0) {
                z = false;
            }
            p.a(context, imageView, avatar, z);
            p.a(this.j, this.d, cVar.f());
            this.a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ MultiGiftItemView b;

                public void onClick(View view) {
                    q.a((Activity) view.getContext(), cVar.b().getAuthorId(), cVar.b().getId(), cVar.b().getNickName(), cVar.b().getAvatar(), null, false);
                }
            });
        }
    }

    public c getData() {
        return this.n;
    }

    public boolean a() {
        return this.i;
    }

    public void a(com.qq.reader.liveshow.b.c cVar) {
        if (getVisibility() != 0) {
            this.i = false;
            setVisibility(0);
            this.e.setVisibility(4);
            b(cVar);
        }
    }

    public void b() {
        if (getVisibility() == 0) {
            this.i = true;
            setVisibility(4);
        }
    }

    public void b(final com.qq.reader.liveshow.b.c cVar) {
        Animation a = p.a(http.Internal_Server_Error, -((getRight() - getLeft()) - getPaddingLeft()), p.a(12.0f, this.j), 0, 0, new LinearInterpolator());
        a.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ MultiGiftItemView b;

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.b.clearAnimation();
                this.b.c(cVar);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        startAnimation(a);
    }

    public void c(final com.qq.reader.liveshow.b.c cVar) {
        this.k = new Timer();
        this.k.schedule(new TimerTask(this) {
            final /* synthetic */ MultiGiftItemView b;

            public void run() {
                ((Activity) this.b.j).runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b.m = this.a.b.m + 1;
                        if (this.a.b.m > this.a.b.l) {
                            this.a.b.m = 0;
                            if (this.a.b.k != null) {
                                this.a.b.k.cancel();
                                this.a.b.k = null;
                            }
                            this.a.b.postDelayed(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.b.d(cVar);
                                }
                            }, 2000);
                            return;
                        }
                        if (this.a.b.e.getVisibility() != 0) {
                            this.a.b.e.setVisibility(0);
                        }
                        this.a.b.e.setCount(this.a.b.m);
                    }
                });
            }
        }, 100, 200);
    }

    public void d(final com.qq.reader.liveshow.b.c cVar) {
        Animation a = p.a(http.Internal_Server_Error, 0, 0, 0, (getTop() - getBottom()) - getPaddingTop(), new LinearInterpolator());
        Animation animationSet = new AnimationSet(true);
        a.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ MultiGiftItemView b;

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.b.clearAnimation();
                this.b.b();
                cVar.a(this.b.getData());
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        animationSet.addAnimation(a);
        a = new AlphaAnimation(1.0f, 0.0f);
        a.setDuration(500);
        animationSet.addAnimation(a);
        startAnimation(animationSet);
    }
}

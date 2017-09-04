package com.qq.reader.liveshow.views.customviews;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.pay.http.APPluginErrorCode;
import com.qq.reader.liveshow.a;
import com.qq.reader.liveshow.a.g;
import com.qq.reader.liveshow.b.b;
import com.qq.reader.liveshow.b.h;
import com.qq.reader.liveshow.b.m;
import com.qq.reader.liveshow.b.n;
import com.qq.reader.liveshow.c.e;
import com.qq.reader.liveshow.model.c;
import com.qq.reader.liveshow.model.im.viewdata.GiftItem;
import com.qq.reader.liveshow.utils.l;
import com.qq.reader.liveshow.utils.p;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.smtt.utils.TbsLog;
import java.util.HashMap;
import java.util.Map;

public class SendGiftView extends LinearLayout {
    public ImageView a;
    public TextView b;
    public TextView c;
    public View d;
    public View e;
    public CircleProgressDrawable f;
    public ObjectAnimator g;
    public View h;
    public TextView i;
    GiftItem j;
    private e k;
    private h l;
    private final int m = APPluginErrorCode.ERROR_APP_TENPAY;
    private final int n = 100;
    private final int o = 12;
    private final int p = TbsLog.TBSLOG_CODE_SDK_INIT;
    private boolean q = false;
    private int r = 0;
    private boolean s = false;
    private Context t;
    private boolean u = false;

    public SendGiftView(Context context) {
        super(context);
        a(context);
    }

    public SendGiftView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        this.t = context;
        LayoutInflater.from(context).inflate(g.send_gift_layout, this);
        this.e = findViewById(a.e.send_gift_layout);
        this.a = (ImageView) findViewById(a.e.send_gift_icon);
        this.b = (TextView) findViewById(a.e.send_gift_name);
        this.c = (TextView) findViewById(a.e.send_gift_price);
        this.i = (TextView) findViewById(a.e.count_down_second);
        this.d = findViewById(a.e.send_gift_bg);
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SendGiftView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (c.a().k()) {
                    b b = n.a().b();
                    if (b != null) {
                        b.a((Activity) this.a.t);
                    }
                } else if (!this.a.q) {
                    this.a.q = true;
                    this.a.k.a(this.a.j.mPrice, new com.qq.reader.liveshow.b.a<Integer>(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void a(Integer num) {
                            this.a.a.e.setVisibility(8);
                            switch (num.intValue()) {
                                case 0:
                                    this.a.a.r = this.a.a.r + 1;
                                    if (this.a.a.s) {
                                        this.a.a.h.setVisibility(0);
                                        this.a.a.l.a(this.a.a.r);
                                        this.a.a.d();
                                        return;
                                    }
                                    n.a().e().a(this.a.a.t, new DialogInterface.OnClickListener(this) {
                                        final /* synthetic */ AnonymousClass1 a;

                                        {
                                            this.a = r1;
                                        }

                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            this.a.a.a.c();
                                        }
                                    }, new DialogInterface.OnClickListener(this) {
                                        final /* synthetic */ AnonymousClass1 a;

                                        {
                                            this.a = r1;
                                        }

                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            this.a.a.a.b();
                                            dialogInterface.dismiss();
                                        }
                                    }, new OnCancelListener(this) {
                                        final /* synthetic */ AnonymousClass1 a;

                                        {
                                            this.a = r1;
                                        }

                                        public void onCancel(DialogInterface dialogInterface) {
                                            this.a.a.a.b();
                                            dialogInterface.dismiss();
                                        }
                                    }, this.a.a.j.mName).show();
                                    return;
                                case 1:
                                    this.a.a.b();
                                    n.a().e().a(this.a.a.t, new DialogInterface.OnClickListener(this) {
                                        final /* synthetic */ AnonymousClass1 a;

                                        {
                                            this.a = r1;
                                        }

                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            try {
                                                n.a().d().a((Activity) this.a.a.a.t, l.a.a(this.a.a.a.j.mPrice, "gift"));
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }, this.a.a.t.getResources().getString(a.h.live_show_gift_cost_count, new Object[]{this.a.a.j.mPrice + this.a.a.j.unit})).show();
                                    return;
                                case 2:
                                case 3:
                                    this.a.a.b();
                                    m.a(this.a.a.t, this.a.a.t.getResources().getString(a.h.network_unavailable), 0);
                                    return;
                                default:
                                    this.a.a.b();
                                    return;
                            }
                        }
                    });
                    Map hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, this.a.j.mId + "");
                    com.qq.reader.liveshow.b.l.a("event_Z6", hashMap, this.a.t.getApplicationContext());
                }
            }
        });
        ObjectAnimator.setFrameDelay(10);
        this.f = new CircleProgressDrawable();
        this.f.setColor(this.t.getResources().getColor(a.b.count_down_bg));
        this.f.setBackGroundColor(this.t.getResources().getColor(a.b.count_down_progress_bg));
        this.g = ObjectAnimator.ofFloat(this.f, "progress", new float[]{0.0f, 1.0f});
        this.g.setDuration(3000);
        this.g.setInterpolator(new LinearInterpolator());
        this.g.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ SendGiftView a;

            {
                this.a = r1;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.a.i.setText(String.valueOf((int) Math.ceil((double) (((float) (3000 - valueAnimator.getCurrentPlayTime())) / 1000.0f))));
            }
        });
        this.g.addListener(new AnimatorListenerAdapter(this) {
            final /* synthetic */ SendGiftView a;
            private boolean b = false;

            {
                this.a = r2;
            }

            public void onAnimationStart(Animator animator) {
                this.b = false;
                this.a.i.setText(String.valueOf(3));
            }

            public void onAnimationCancel(Animator animator) {
                this.b = true;
            }

            public void onAnimationEnd(Animator animator) {
                if (!this.b) {
                    this.a.c();
                }
            }
        });
        this.h = findViewById(a.e.count_down_layout);
        this.h.setBackgroundDrawable(this.f);
        this.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SendGiftView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.r = this.a.r + 1;
                this.a.l.a(this.a.r);
                if (this.a.r > TbsLog.TBSLOG_CODE_SDK_INIT) {
                    this.a.r = TbsLog.TBSLOG_CODE_SDK_INIT;
                    this.a.g.end();
                    return;
                }
                this.a.d();
            }
        });
    }

    public void setData(GiftItem giftItem) {
        if (this.q) {
            this.u = true;
            if (this.g != null) {
                this.g.end();
            }
        }
        this.j = giftItem;
        if (this.j.mType == 2) {
            this.s = false;
        } else {
            this.s = true;
        }
        this.h.setVisibility(4);
        this.e.setVisibility(0);
        p.a(this.t, this.a, this.j.mImgUrl);
        this.b.setText(this.j.mName);
        this.c.setText(this.j.mPrice + "");
    }

    public void setHelper(e eVar) {
        this.k = eVar;
    }

    public void setRefreshCallback(h hVar) {
        this.l = hVar;
    }

    private void c() {
        this.k.a(this.j.mId, this.r);
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, this.j.mId + "");
        hashMap.put(ComicStoreExclusiveItemCard.NET_AD_ATTR_COUNT, this.r + "");
        com.qq.reader.liveshow.b.l.a("event_Z8", hashMap, this.t.getApplicationContext());
        b();
    }

    public void a() {
        setVisibility(0);
        startAnimation(p.a(getContext(), 100));
    }

    public void b() {
        this.h.setVisibility(4);
        this.r = 0;
        if (this.u) {
            this.u = false;
        } else {
            this.l.a();
        }
        this.s = false;
        this.q = false;
    }

    private void d() {
        if (this.g != null) {
            this.g.cancel();
            this.g.start();
        }
    }
}

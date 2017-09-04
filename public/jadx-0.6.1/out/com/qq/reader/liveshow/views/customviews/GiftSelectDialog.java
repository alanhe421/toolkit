package com.qq.reader.liveshow.views.customviews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.qq.reader.liveshow.a;
import com.qq.reader.liveshow.a.b;
import com.qq.reader.liveshow.a.c;
import com.qq.reader.liveshow.a.g;
import com.qq.reader.liveshow.b.h;
import com.qq.reader.liveshow.b.n;
import com.qq.reader.liveshow.c.e;
import com.qq.reader.liveshow.model.im.viewdata.GiftItem;
import com.qq.reader.liveshow.utils.p;
import java.util.ArrayList;
import java.util.List;

public class GiftSelectDialog extends BaseDialog {
    private Context a;
    private SendGiftView b;
    private MultiClickNumberWidget c;
    private List<GiftItem> d = new ArrayList();
    private List<View> e = new ArrayList();
    private final int f = 6;
    private final int g = 3;
    private int h = -1;
    private float i = 0.0f;
    private float j = 0.0f;
    private RelativeLayout k;
    private View l;
    private ImageView m;
    private View n;

    public GiftSelectDialog(Context context, int i, e eVar, List<GiftItem> list) {
        super(context, i);
        this.a = context;
        setContentView(g.gift_select_dialog);
        this.d = list;
        setCanceledOnTouchOutside(true);
        a(context, eVar);
    }

    private void a(Context context, e eVar) {
        this.k = (RelativeLayout) findViewById(a.e.send_layout);
        this.l = findViewById(a.e.gift_multi_click);
        this.c = (MultiClickNumberWidget) findViewById(a.e.num_widget);
        this.m = (ImageView) findViewById(a.e.gift_img);
        if (this.d == null || this.d.size() < 6) {
            ((TextView) findViewById(a.e.gift_select_bg)).setText("获取礼物中...请稍后再试");
            n.a().f().sendBroadcast(new Intent("com.reader.live.room_reload"));
        } else {
            this.i = this.a.getResources().getDimension(c.send_gift_width);
            this.j = this.a.getResources().getDimension(c.send_gift_height);
            LayoutInflater from = LayoutInflater.from(context);
            for (int i = 0; i < 6; i++) {
                View inflate;
                if (i < 3) {
                    inflate = from.inflate(g.gift_select_item, null);
                } else {
                    inflate = from.inflate(g.gift_select_item2, null);
                }
                a(inflate, i);
                inflate.setLayoutParams(a(i));
                this.k.addView(inflate);
            }
            this.b = new SendGiftView(this.a);
            this.b.setVisibility(8);
            this.b.setHelper(eVar);
            this.b.setRefreshCallback(new h(this) {
                final /* synthetic */ GiftSelectDialog a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    if (i == 1) {
                        if (this.a.l.getVisibility() != 0) {
                            this.a.n.setBackgroundColor(this.a.a.getResources().getColor(b.gift_dialog_click_bg));
                            this.a.l.setVisibility(0);
                        }
                        p.a(this.a.a, this.a.m, ((GiftItem) this.a.d.get(this.a.h)).mImgUrl);
                    }
                    this.a.c.setCount(i);
                }

                public void a() {
                    ((View) this.a.e.get(this.a.h)).setVisibility(0);
                    this.a.l.setVisibility(8);
                    this.a.n.setBackgroundColor(0);
                    this.a.b.setVisibility(8);
                }
            });
            this.k.addView(this.b);
        }
        this.n = findViewById(a.e.click_layout);
        this.n.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ GiftSelectDialog a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.dismiss();
            }
        });
    }

    private void a(View view, final int i) {
        ImageView imageView = (ImageView) view.findViewById(a.e.gift_icon);
        TextView textView = (TextView) view.findViewById(a.e.gift_price);
        final View findViewById = view.findViewById(a.e.normal_gift_layout);
        this.e.add(findViewById);
        p.a(this.a, imageView, ((GiftItem) this.d.get(i)).mImgUrl);
        textView.setText(((GiftItem) this.d.get(i)).mPrice + "");
        findViewById.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ GiftSelectDialog c;

            public void onClick(View view) {
                if (this.c.h != i) {
                    if (this.c.h >= 0) {
                        ((View) this.c.e.get(this.c.h)).setVisibility(0);
                    }
                    this.c.h = i;
                }
                findViewById.setVisibility(4);
                if (this.c.l.getVisibility() == 0) {
                    this.c.l.setVisibility(8);
                    this.c.n.setBackgroundColor(0);
                }
                this.c.b.setLayoutParams(this.c.a(i));
                this.c.b.setData((GiftItem) this.c.d.get(i));
                this.c.b.a();
                this.c.k.invalidate();
            }
        });
    }

    private LayoutParams a(int i) {
        LayoutParams layoutParams = new LayoutParams((int) this.i, (int) this.j);
        switch (i % 3) {
            case 0:
                layoutParams.addRule(9);
                break;
            case 1:
                layoutParams.addRule(14);
                break;
            case 2:
                layoutParams.addRule(11);
                break;
        }
        if (i < 3) {
            layoutParams.addRule(10);
        } else {
            layoutParams.addRule(12);
        }
        return layoutParams;
    }

    public void dismiss() {
        super.dismiss();
    }

    public void cancel() {
        super.cancel();
    }

    public void show() {
        super.show();
    }
}

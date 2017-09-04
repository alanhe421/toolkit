package com.qq.reader.liveshow.views;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.liveshow.a.e;
import com.qq.reader.liveshow.c.a.d.a;
import com.qq.reader.liveshow.c.h;
import com.qq.reader.liveshow.c.i;
import com.qq.reader.liveshow.c.j;
import com.qq.reader.liveshow.model.RankItem;
import com.qq.reader.liveshow.model.c;
import com.qq.reader.liveshow.utils.SxbLog;
import com.qq.reader.liveshow.utils.g;
import com.qq.reader.liveshow.utils.l;
import com.qq.reader.liveshow.utils.p;
import com.qq.reader.liveshow.utils.q;
import com.qq.reader.module.bookstore.qnative.item.s;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: LiveHostProxy */
public class d implements a {
    LiveActivity a;
    h b;
    private ImageView c;
    private LinearLayout d;
    private TextView e;
    private TextView f;
    private TextView g;
    private View h;
    private View i;
    private TextView j;
    private ImageView k;
    private List<ImageView> l = new ArrayList();
    private List<View> m = new ArrayList();
    private j n;
    private View o;

    public d(LiveActivity liveActivity, j jVar) {
        this.a = liveActivity;
        this.b = (h) k();
        this.b.a(jVar);
        this.n = jVar;
        j();
    }

    public void j() {
        this.c = (ImageView) this.a.findViewById(e.head_icon);
        this.e = (TextView) this.a.findViewById(e.member_counts);
        this.f = (TextView) this.a.findViewById(e.heart_counts);
        this.e.setText(String.valueOf(com.qq.reader.liveshow.model.a.f()));
        this.f.setText(String.valueOf(com.qq.reader.liveshow.model.a.g()));
        this.j = (TextView) this.a.findViewById(e.broadcasting_time);
        this.i = this.a.findViewById(e.top3_layout);
        this.m.add(this.a.findViewById(e.top1_view));
        this.m.add(this.a.findViewById(e.top2_view));
        this.m.add(this.a.findViewById(e.top3_view));
        this.l.add((ImageView) this.a.findViewById(e.top1_icon));
        this.l.add((ImageView) this.a.findViewById(e.top2_icon));
        this.l.add((ImageView) this.a.findViewById(e.top3_icon));
        this.h = this.a.findViewById(e.cup_icon);
        ((TextView) this.a.findViewById(e.room_id)).setText(com.qq.reader.liveshow.model.a.j());
        this.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                WebActivity.a(this.a.a, l.b() + "?roomId=" + com.qq.reader.liveshow.model.a.i() + "&userId=" + c.a().b() + "&tf=1", view.getResources().getString(com.qq.reader.liveshow.a.h.rank_title));
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, c.a().h() == 1 ? "1" : "2");
                com.qq.reader.liveshow.b.l.a("event_Z2", hashMap, this.a.a.getApplicationContext());
            }
        });
        this.o = this.a.findViewById(e.header_layout);
    }

    private void m() {
        q.a(this.a, Long.valueOf(com.qq.reader.liveshow.model.a.b()).longValue(), com.qq.reader.liveshow.model.a.c(), com.qq.reader.liveshow.model.a.d(), com.qq.reader.liveshow.model.a.e(), this.n, true);
    }

    private void n() {
        if (this.k != null) {
            Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(500);
            alphaAnimation.setRepeatCount(-1);
            alphaAnimation.setRepeatMode(2);
            this.k.startAnimation(alphaAnimation);
        }
    }

    public i k() {
        return new h(this);
    }

    public void f() {
    }

    public void g() {
    }

    public void h() {
        o();
        this.b.a();
    }

    private void o() {
        if (this.k != null) {
            Animation animation = this.k.getAnimation();
            if (animation != null) {
                animation.cancel();
            }
            this.k.clearAnimation();
        }
    }

    public void i() {
        p.a(this.a, this.c, com.qq.reader.liveshow.model.a.e(), true);
        if (this.d != null && this.d.getVisibility() == 0) {
            this.d.setVisibility(8);
        }
        if (c.a().h() == 1) {
            this.d = (LinearLayout) this.a.findViewById(e.record_tip);
            this.k = (ImageView) this.a.findViewById(e.record_ball);
            l();
            n();
        } else {
            this.d = (LinearLayout) this.a.findViewById(e.host_info);
            ((TextView) this.a.findViewById(e.host_name)).setText(p.a(com.qq.reader.liveshow.model.a.d(), 10));
            ((ImageView) this.a.findViewById(e.host_level)).setImageResource(p.a(com.qq.reader.liveshow.model.a.p()));
        }
        this.d.setVisibility(0);
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.m();
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, c.a().h() == 1 ? "1" : "2");
                com.qq.reader.liveshow.b.l.a("event_Z1", hashMap, this.a.a.getApplicationContext());
            }
        });
    }

    public void a() {
        p.a(this.a, this.c, com.qq.reader.liveshow.model.a.e(), true);
        if (c.a().h() == 1) {
            l();
            return;
        }
        ((TextView) this.a.findViewById(e.host_name)).setText(p.a(com.qq.reader.liveshow.model.a.d(), 10));
        ((ImageView) this.a.findViewById(e.host_level)).setImageResource(p.a(com.qq.reader.liveshow.model.a.p()));
    }

    public void b() {
        if (this.f == null) {
            this.f = (TextView) this.a.findViewById(e.heart_counts);
        }
        this.f.setText(String.valueOf(com.qq.reader.liveshow.model.a.g()));
    }

    public void c() {
        if (this.e == null) {
            this.e = (TextView) this.a.findViewById(e.member_counts);
        }
        this.e.setText(com.qq.reader.liveshow.model.a.f() + "人");
    }

    public void l() {
        if (c.a().h() == 1) {
            if (this.g == null) {
                this.g = (TextView) this.a.findViewById(e.gift_counts);
                this.g.setVisibility(0);
            }
            this.g.setText("贡献 " + com.qq.reader.liveshow.model.a.l());
        }
    }

    public void d() {
        SxbLog.e("Proxy", " updateRankItem ");
        List n = com.qq.reader.liveshow.model.a.n();
        if (n == null || n.size() == 0) {
            this.i.setVisibility(8);
            return;
        }
        if (this.i.getVisibility() != 0) {
            this.i.setVisibility(0);
        }
        if (this.m.size() < 3) {
            this.m.add(this.a.findViewById(e.top1_view));
            this.m.add(this.a.findViewById(e.top2_view));
            this.m.add(this.a.findViewById(e.top3_view));
        }
        if (this.l.size() < 3) {
            this.l.add((ImageView) this.a.findViewById(e.top1_icon));
            this.l.add((ImageView) this.a.findViewById(e.top2_icon));
            this.l.add((ImageView) this.a.findViewById(e.top3_icon));
        }
        for (int i = 0; i < n.size(); i++) {
            boolean z;
            final RankItem rankItem = (RankItem) n.get(i);
            if (((View) this.m.get(i)).getVisibility() != 0) {
                ((View) this.m.get(i)).setVisibility(0);
            }
            Context context = this.a;
            ImageView imageView = (ImageView) this.l.get(i);
            String str = rankItem.mIcon;
            if (rankItem.mAuthorId > 0) {
                z = true;
            } else {
                z = false;
            }
            p.a(context, imageView, str, z);
            ((View) this.m.get(i)).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ d b;

                public void onClick(View view) {
                    q.a((Activity) view.getContext(), rankItem.mAuthorId, rankItem.mUid, rankItem.mNickname, rankItem.mIcon, null, false);
                }
            });
        }
    }

    public void a(String str) {
        if (1 == c.a().h() && this.j != null) {
            SxbLog.b("Proxy", " refresh time ");
            this.j.setText(str);
        }
    }

    public boolean e() {
        return g.a(this.a);
    }

    public void a(boolean z) {
        if (this.o != null) {
            this.o.setVisibility(z ? 0 : 8);
        }
    }
}

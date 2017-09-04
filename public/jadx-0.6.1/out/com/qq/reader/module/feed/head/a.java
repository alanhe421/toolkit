package com.qq.reader.module.feed.head;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager.e;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.feed.card.view.FeedColumnByPassEntranceHeadView;
import com.qq.reader.qurl.c;
import com.qq.reader.view.CirclePageIndicator;
import com.qq.reader.view.HeadViewPager;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: FeedHeadAdv */
public class a implements e, OnClickListener, com.qq.reader.module.bookstore.qnative.c.a {
    protected HeadViewPager a;
    protected a b;
    protected Activity c;
    private View d;
    private View e;
    private View f;
    private CirclePageIndicator g;
    private boolean h = false;
    private b i = null;
    private Map<String, String> j = new HashMap();
    private FeedColumnByPassEntranceHeadView k;

    public a(Activity activity) {
        this.c = activity;
        this.b = new a(this, activity.getApplicationContext());
        this.d = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.feedhead_adv_viewpage, null);
        this.e = this.d.findViewById(R.id.adv_root_frame);
        this.f = this.d.findViewById(R.id.adv_pager_container);
        this.a = (HeadViewPager) this.d.findViewById(R.id.adv_feed_adv);
        this.a.setAdapter(this.b);
        this.g = (CirclePageIndicator) this.d.findViewById(R.id.adv_feed_indicator);
        this.g.setViewPager(this.a);
        this.g.setOnPageChangeListener(this);
        this.a.j();
        this.k = (FeedColumnByPassEntranceHeadView) this.d.findViewById(R.id.feed_bypass_entrance_head);
    }

    public View b() {
        return this.d;
    }

    public void c() {
        if (!this.h) {
            synchronized (this) {
                if (!this.h) {
                    this.h = true;
                    this.a.j();
                }
            }
        }
    }

    public void d() {
        if (this.h) {
            synchronized (this) {
                if (this.h) {
                    this.h = false;
                    this.a.k();
                }
            }
        }
        l();
    }

    public boolean a(List<com.qq.reader.cservice.adv.a> list) {
        if (list == null || list.size() == 0) {
            this.f.setBackgroundResource(R.drawable.feed_adv_default_bg);
            return false;
        }
        this.f.setBackgroundDrawable(null);
        a();
        f();
        this.b.a();
        boolean a = this.b.a(list);
        if (!a) {
            return a;
        }
        this.b.e();
        int a2 = this.b.a();
        this.a.getMyPagerAdapter().c();
        if (a2 == 2 || a2 == 1) {
            this.a.setCurrentItem(0);
            return a;
        }
        this.a.setCurrentItem(0);
        this.a.a(2000);
        return a;
    }

    public void onClick(View view) {
        if (view.getTag() != null && (view.getTag() instanceof com.qq.reader.cservice.adv.a)) {
            com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) view.getTag();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(s.ORIGIN, aVar.f());
                aVar.w().a().putString(s.STATPARAM_KEY, jSONObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(aVar.d()));
            i.a("event_C111", hashMap, ReaderApplication.getApplicationImp());
            if (c.a(aVar.h())) {
                String h = aVar.h();
                if (this.a.a.get(h) != null) {
                    h = h + "&posId=" + this.a.a.get(h);
                }
                com.qq.reader.common.monitor.debug.c.e("adv", h);
                try {
                    c.a(this.c, h);
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            aVar.w().a((com.qq.reader.module.bookstore.qnative.c.a) this);
        }
    }

    protected void a() {
        i.a("event_C113", null, ReaderApplication.getApplicationImp());
    }

    public void e() {
        if (this.e != null) {
            this.e.setVisibility(8);
        }
    }

    public void f() {
        if (this.e.getVisibility() != 0) {
            this.e.setVisibility(0);
        }
    }

    public boolean g() {
        return this.e.getVisibility() == 0;
    }

    protected int h() {
        return R.drawable.feed_adv_bg_male_1;
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        this.a.j();
        a(i % this.b.d().size());
    }

    private void a(int i) {
        View view = (View) this.b.d().get(i);
        if (view != null) {
            RisingNumberView risingNumberView = (RisingNumberView) view.findViewById(R.id.feedheadadv_maintext);
            if (risingNumberView != null) {
                risingNumberView.b();
            }
            NewHistogramView newHistogramView = (NewHistogramView) view.findViewById(R.id.feedheadadv_graphlayout);
            if (newHistogramView != null) {
                newHistogramView.b();
            }
        }
    }

    private void l() {
        try {
            int size = this.b.d().size();
            for (int i = 0; i < size; i++) {
                View view = (View) this.b.d().get(i);
                if (view != null) {
                    RisingNumberView risingNumberView = (RisingNumberView) view.findViewById(R.id.feedheadadv_maintext);
                    if (risingNumberView != null) {
                        risingNumberView.c();
                    }
                    NewHistogramView newHistogramView = (NewHistogramView) view.findViewById(R.id.feedheadadv_graphlayout);
                    if (newHistogramView != null) {
                        newHistogramView.c();
                    }
                }
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("FeedHeadAdv", e.getMessage());
        }
    }

    public void doFunction(Bundle bundle) {
    }

    public Activity getFromActivity() {
        return this.c;
    }

    public boolean i() {
        return this.h;
    }

    public void a(JSONObject jSONObject, com.qq.reader.module.bookstore.qnative.c.a aVar, boolean z) {
        if (this.k != null) {
            this.k.setVisibility(0);
            try {
                this.k.a(jSONObject, aVar, z);
                this.k.a();
            } catch (Exception e) {
                j();
            }
        }
    }

    public void j() {
        if (this.k != null) {
            this.k.setVisibility(8);
        }
    }

    public void k() {
        if (this.k != null && this.k.getVisibility() == 0) {
            this.k.b();
        }
    }
}

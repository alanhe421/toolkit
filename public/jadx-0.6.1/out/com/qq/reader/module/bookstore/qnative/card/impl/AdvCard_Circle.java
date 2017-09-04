package com.qq.reader.module.bookstore.qnative.card.impl;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.e;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.h;
import com.qq.reader.module.bookstore.qnative.card.BaseAdvCard;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.view.AdvLoopVerticalViewPager;
import com.qq.reader.view.AdvViewPager;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdvCard_Circle extends BaseAdvCard {
    private boolean isAdText;
    private int showPicHeight = ((com.qq.reader.common.c.a.bU * 93) / 360);

    private class a extends PagerAdapter {
        int a = 0;
        final /* synthetic */ AdvCard_Circle b;
        private ArrayList<ImageView> c = new ArrayList();
        private List<s> d = new ArrayList();

        public a(AdvCard_Circle advCard_Circle) {
            this.b = advCard_Circle;
        }

        private void c(int i) {
            for (int i2 = 0; i2 < i; i2++) {
                ImageView imageView = new ImageView(ReaderApplication.getApplicationImp().getApplicationContext());
                imageView.setLayoutParams(new LayoutParams(-1, -1));
                imageView.setScaleType(ScaleType.FIT_XY);
                this.c.add(imageView);
            }
        }

        private void d(int i) {
            for (int i2 = 0; i2 < i; i2++) {
                this.c.remove(0);
            }
        }

        public void a(List<s> list) {
            this.d.clear();
            this.d.addAll(list);
            this.b.getViewPager().removeAllViews();
            e();
        }

        private void e() {
            int size = this.d.size();
            int size2 = this.c.size();
            if (size > size2) {
                c(size - size2);
            } else if (size < size2) {
                d(size2 - size);
            }
        }

        public void d() {
            int size = this.d.size();
            for (int i = 0; i < size; i++) {
                final com.qq.reader.module.bookstore.qnative.item.b bVar = (com.qq.reader.module.bookstore.qnative.item.b) this.d.get(i);
                c.a(this.b.getEvnetListener().getFromActivity()).a(bVar.f(), (ImageView) this.c.get(i), com.qq.reader.common.imageloader.a.a().j());
                ((ImageView) this.c.get(i)).setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ a b;

                    public void onClick(View view) {
                        this.b.b.doOnAdvViewClicked(bVar);
                        this.b.b.imageAdvClickStatics();
                    }
                });
            }
        }

        public int a() {
            return this.d.size();
        }

        public boolean a(View view, Object obj) {
            return view == obj;
        }

        public int a(Object obj) {
            this.a++;
            return -2;
        }

        public void a(ViewGroup viewGroup, int i, Object obj) {
            if (this.a > 0) {
                this.a--;
                viewGroup.removeView((View) obj);
            }
        }

        public Object a(ViewGroup viewGroup, int i) {
            View view = (View) this.c.get(i);
            if (view.getParent() == null) {
                viewGroup.addView(view);
            } else {
                ((ViewGroup) view.getParent()).removeView(view);
                viewGroup.addView(view);
            }
            return view;
        }
    }

    private class b extends PagerAdapter {
        int a;
        public boolean b;
        final /* synthetic */ AdvCard_Circle c;
        private ArrayList<View> d;
        private List<s> e;

        private b(AdvCard_Circle advCard_Circle) {
            this.c = advCard_Circle;
            this.a = 0;
            this.d = new ArrayList();
            this.e = new ArrayList();
            this.b = false;
        }

        private ViewGroup e() {
            return (ViewGroup) ap.a(this.c.getRootView(), R.id.vp_text_advs);
        }

        protected int d() {
            return R.layout.vertical_text_adv_item_layout;
        }

        private void c(int i) {
            for (int i2 = 0; i2 < i; i2++) {
                View inflate = View.inflate(ReaderApplication.getApplicationImp().getApplicationContext(), d(), null);
                inflate.setLayoutParams(new LayoutParams(-1, -1));
                this.d.add(inflate);
            }
        }

        private void d(int i) {
            for (int i2 = 0; i2 < i; i2++) {
                this.d.remove(0);
            }
        }

        private void a(View view, final com.qq.reader.module.bookstore.qnative.item.b bVar) {
            if (view != null) {
                TextView textView = (TextView) view.findViewById(R.id.localstore_adv_0_text_title);
                if (textView != null && bVar != null) {
                    textView.setText(bVar.c());
                    view.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ b b;

                        public void onClick(View view) {
                            this.b.c.doOnAdvViewClicked(bVar);
                            this.b.c.textAdvClickStatics();
                        }
                    });
                }
            }
        }

        public void a(List<s> list) {
            this.e.clear();
            this.e.addAll(list);
            e().removeAllViews();
            f();
            int i = 0;
            while (this.e != null && this.d != null && i < this.e.size() && i < this.d.size()) {
                a((View) this.d.get(i), (com.qq.reader.module.bookstore.qnative.item.b) this.e.get(i));
                i++;
            }
        }

        private void f() {
            int size = this.e.size();
            int size2 = this.d.size();
            if (size > size2) {
                c(size - size2);
            } else if (size < size2) {
                d(size2 - size);
            }
        }

        public int a() {
            if (!this.b || this.e == null || this.e.size() <= 1) {
                return this.e.size();
            }
            return 100000;
        }

        public boolean a(View view, Object obj) {
            return view == obj;
        }

        public int a(Object obj) {
            this.a++;
            return -2;
        }

        public void a(ViewGroup viewGroup, int i, Object obj) {
            if (this.a > 0) {
                this.a--;
                viewGroup.removeView((View) obj);
            }
        }

        public Object a(ViewGroup viewGroup, int i) {
            if (this.b) {
                i %= this.d.size();
            }
            View view = (View) this.d.get(i);
            if (view.getParent() == null) {
                viewGroup.addView(view);
            } else {
                ((ViewGroup) view.getParent()).removeView(view);
                viewGroup.addView(view);
            }
            return view;
        }
    }

    private void showImageAdvStatics() {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, getCardId());
        i.a("event_B228", hashMap, ReaderApplication.getApplicationImp());
    }

    private void showTextAdvStatics() {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, getCardId());
        i.a("event_B230", hashMap, ReaderApplication.getApplicationImp());
    }

    private void imageAdvClickStatics() {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, getCardId());
        i.a("event_B229", hashMap, ReaderApplication.getApplicationImp());
    }

    private void textAdvClickStatics() {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, getCardId());
        i.a("event_B231", hashMap, ReaderApplication.getApplicationImp());
    }

    public AdvCard_Circle(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_adv_0;
    }

    private View getAdvTextLayout() {
        return ap.a(getRootView(), R.id.localstore_adv_0_text_layout);
    }

    private ImageView getAdvImageLayout() {
        return (ImageView) ap.a(getRootView(), R.id.localstore_adv_0_img);
    }

    private AdvLoopVerticalViewPager getVerticalViewPager() {
        return (AdvLoopVerticalViewPager) ap.a(getRootView(), R.id.vp_text_advs);
    }

    private AdvViewPager getViewPager() {
        return (AdvViewPager) ap.a(getRootView(), R.id.localstore_adv_0_viewpager);
    }

    private LinearLayout getPagerIndicator() {
        return (LinearLayout) ap.a(getRootView(), R.id.localstore_adv_0_indicator);
    }

    public void build(JSONObject jSONObject) {
        super.build(jSONObject);
        this.isAdText = "text".equals(jSONObject.optString("showtype"));
    }

    public void attachView() {
        if (getViewPager() != null) {
            getViewPager().k();
        }
        if (getItemList().size() <= 0) {
            return;
        }
        if (this.isAdText) {
            loadTextOrLinkAds(getRootView(), getItemList());
            showTextAdvStatics();
        } else if (getItemList().size() == 1) {
            loadSingleImg((com.qq.reader.module.bookstore.qnative.item.b) getItemList().get(0));
            showImageAdvStatics();
        } else {
            loadImageAd(true);
            showImageAdvStatics();
        }
    }

    private void loadSingleImg(final com.qq.reader.module.bookstore.qnative.item.b bVar) {
        getAdvImageLayout().setVisibility(0);
        getViewPager().setVisibility(8);
        getPagerIndicator().setVisibility(8);
        getAdvTextLayout().setVisibility(8);
        getAdvImageLayout().getLayoutParams().height = this.showPicHeight;
        c.a(getEvnetListener().getFromActivity()).a(bVar.f(), getAdvImageLayout(), com.qq.reader.common.imageloader.a.a().j());
        getAdvImageLayout().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AdvCard_Circle b;

            public void onClick(View view) {
                this.b.doOnAdvViewClicked(bVar);
                this.b.imageAdvClickStatics();
            }
        });
    }

    private void loadImageAd(boolean z) {
        getViewPager().getLayoutParams().height = this.showPicHeight;
        getViewPager().setVisibility(0);
        getAdvTextLayout().setVisibility(8);
        getAdvImageLayout().setVisibility(8);
        initViewpager(getItemList());
        if (z) {
            getPagerIndicator().setVisibility(0);
            initViewPagerIndicator();
            return;
        }
        getPagerIndicator().setVisibility(8);
    }

    private void loadTextOrLinkAds(View view, List<s> list) {
        getVerticalViewPager().setOnPageChangeListener(new e(this) {
            final /* synthetic */ AdvCard_Circle a;

            {
                this.a = r1;
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                AdvLoopVerticalViewPager access$200 = this.a.getVerticalViewPager();
                if (access$200 != null) {
                    access$200.a();
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        getViewPager().setVisibility(8);
        getAdvTextLayout().setVisibility(0);
        getPagerIndicator().setVisibility(8);
        getAdvImageLayout().setVisibility(8);
        AdvLoopVerticalViewPager verticalViewPager = getVerticalViewPager();
        PagerAdapter pagerAdapter = (b) getVerticalViewPager().getAdapter();
        if (pagerAdapter == null) {
            pagerAdapter = new b();
        }
        pagerAdapter.b = true;
        pagerAdapter.a((List) list);
        verticalViewPager.setAdapter(pagerAdapter);
        pagerAdapter.c();
        verticalViewPager.setCurrentItem(50000);
        verticalViewPager.a();
    }

    private void doOnAdvViewClicked(com.qq.reader.module.bookstore.qnative.item.b bVar) {
        h.a(getClass().getSimpleName(), getPageCacheKey(), this.mFromBid, bVar.b(), bVar.a());
        if (bVar != null) {
            String e = bVar.e();
            if (com.qq.reader.qurl.c.a(e)) {
                try {
                    com.qq.reader.qurl.c.a(getEvnetListener().getFromActivity(), e);
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            bVar.a(getEvnetListener());
        }
    }

    private void initViewpager(List<s> list) {
        PagerAdapter pagerAdapter = (a) getViewPager().getAdapter();
        if (pagerAdapter == null) {
            pagerAdapter = new a(this);
        }
        pagerAdapter.a((List) list);
        getViewPager().setAdapter(pagerAdapter);
        pagerAdapter.d();
        pagerAdapter.c();
        if (list.size() > 1) {
            getViewPager().j();
        }
    }

    private void initViewPagerIndicator() {
        int a;
        int i;
        int childCount = getPagerIndicator().getChildCount();
        a aVar = (a) getViewPager().getAdapter();
        if (aVar != null) {
            a = aVar.a();
        } else {
            a = 0;
        }
        if (childCount > a) {
            for (i = 0; i < childCount - a; i++) {
                getPagerIndicator().removeViewAt(0);
            }
        } else {
            int dimensionPixelSize = ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.localstore_size_8);
            for (i = 0; i < a - childCount; i++) {
                View imageView = new ImageView(ReaderApplication.getApplicationImp());
                LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.leftMargin = dimensionPixelSize;
                imageView.setLayoutParams(layoutParams);
                imageView.setScaleType(ScaleType.FIT_XY);
                imageView.setBackgroundResource(R.drawable.localstore_adv_viewpagerindicator_selector);
                getPagerIndicator().addView(imageView);
            }
        }
        a = getPagerIndicator().getChildCount();
        for (i = 0; i < a; i++) {
            getPagerIndicator().getChildAt(i).setSelected(false);
        }
        getPagerIndicator().getChildAt(getViewPager().getCurrentItem()).setSelected(true);
        getViewPager().setOnPageChangeListener(new e(this) {
            final /* synthetic */ AdvCard_Circle a;

            {
                this.a = r1;
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                if (this.a.getPagerIndicator() != null) {
                    for (int i2 = 0; i2 < this.a.getPagerIndicator().getChildCount(); i2++) {
                        this.a.getPagerIndicator().getChildAt(i2).setSelected(false);
                    }
                    View childAt = this.a.getPagerIndicator().getChildAt(i);
                    if (childAt != null) {
                        childAt.setSelected(true);
                    }
                }
                AdvViewPager access$500 = this.a.getViewPager();
                if (access$500 != null) {
                    access$500.j();
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        getItemList().clear();
        boolean parseData = super.parseData(jSONObject);
        JSONArray optJSONArray = jSONObject.optJSONArray("adtext");
        if (!(optJSONArray == null || parseData)) {
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                this.isAdText = true;
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                s bVar = new com.qq.reader.module.bookstore.qnative.item.b();
                bVar.parseData(jSONObject2);
                addItem(bVar);
            }
            if (length > 0) {
                return true;
            }
        }
        return parseData;
    }
}

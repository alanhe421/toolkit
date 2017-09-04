package com.qq.reader.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.k;
import android.support.v4.view.ViewPager.e;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.widget.PagerSlidingTabStrip;
import com.qq.reader.common.widget.PagerSlidingTabStrip.c;
import com.qq.reader.module.bookstore.qweb.TabInfo;
import com.qq.reader.module.bookstore.qweb.WebAdViewPager;
import com.qq.reader.module.bookstore.qweb.fragment.BaseFragment;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public abstract class AbsBaseTabActivity extends ReaderBaseActivity {
    protected PagerSlidingTabStrip a;
    protected WebAdViewPager b;
    public a c;
    protected View d;
    protected View e;
    protected View f;
    protected ArrayList<TabInfo> g = new ArrayList();
    protected TextView h;
    protected ImageView i;
    protected ArrayList<View> j = new ArrayList();

    protected class a extends com.qq.reader.module.bookstore.qweb.a implements c {
        final /* synthetic */ AbsBaseTabActivity a;

        public a(AbsBaseTabActivity absBaseTabActivity, k kVar) {
            this.a = absBaseTabActivity;
            super(kVar);
        }

        public View c(int i) {
            return this.a.a(i);
        }

        public int a() {
            return this.a.g == null ? 0 : this.a.g.size();
        }

        public BaseFragment d(int i) {
            return f(i);
        }

        private BaseFragment f(int i) {
            TabInfo tabInfo = (TabInfo) this.a.g.get(i);
            if (tabInfo == null) {
                return null;
            }
            BaseFragment baseFragment;
            Class cls = tabInfo.cls;
            BaseFragment baseFragment2 = tabInfo.mFragment;
            if (baseFragment2 == null) {
                try {
                    baseFragment = (BaseFragment) cls.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                    baseFragment = baseFragment2;
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                }
                baseFragment.setHashArguments(tabInfo.args);
                baseFragment.setTitle(tabInfo.title);
                return baseFragment;
            }
            baseFragment = baseFragment2;
            baseFragment.setHashArguments(tabInfo.args);
            baseFragment.setTitle(tabInfo.title);
            return baseFragment;
        }
    }

    protected abstract void a(Bundle bundle);

    protected abstract String e();

    protected abstract int f();

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!"Meizu_M040".equals(com.qq.reader.common.c.a.E)) {
            getWindow().addFlags(SigType.WLOGIN_PF);
        }
        int f = f();
        if (f != 0) {
            setContentView(f);
            b(bundle);
        }
    }

    protected void a() {
        finish();
    }

    private void b(Bundle bundle) {
        this.h = (TextView) findViewById(R.id.profile_header_title);
        this.i = (ImageView) findViewById(R.id.profile_header_left_back);
        if (this.i != null) {
            this.i.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ AbsBaseTabActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.a();
                }
            });
        }
        this.d = findViewById(R.id.common_tab_tabs_layout);
        this.a = (PagerSlidingTabStrip) findViewById(R.id.common_tab_tabs);
        this.a.setShouldExpand(true);
        this.e = findViewById(R.id.common_tab__line);
        this.f = findViewById(R.id.common_tab_top_shadow);
        this.b = (WebAdViewPager) findViewById(R.id.common_tab_viewpager);
        a(bundle);
        this.h.setText(e());
        if (this.g != null && this.g.size() > 0) {
            this.d.setVisibility(0);
            int size = this.g.size();
            int i = com.qq.reader.common.c.a.bU / size;
            int i2 = i / 8;
            if (size == 2 || size == 3) {
                i2 = (i - getResources().getDimensionPixelOffset(R.dimen.common_dp_80)) / 2;
            }
            this.a.setLineRightAndLeftPadding(i2, i2);
        }
        this.c = new a(this, getSupportFragmentManager());
        this.b.setOffscreenPageLimit(2);
        this.b.setAdapter(this.c);
        if (this.g == null || this.g.size() <= 1) {
            this.e.setVisibility(8);
            this.f.setVisibility(8);
            if (this.d == null) {
                this.d = findViewById(R.id.common_titler);
            } else {
                this.d.setVisibility(8);
            }
        }
        this.a.setViewPager(this.b);
        this.a.setOnPageChaneListenerForTitle(new e(this) {
            final /* synthetic */ AbsBaseTabActivity a;

            {
                this.a = r1;
            }

            public void onPageSelected(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    public boolean b() {
        return false;
    }

    public View a(int i) {
        TabInfo tabInfo = (TabInfo) this.g.get(i);
        View inflate = getLayoutInflater().inflate(R.layout.profileaccount_tab_item, null);
        TextView textView = (TextView) inflate.findViewById(R.id.tab_text);
        textView.setText(tabInfo.title);
        if (this.j.size() > i) {
            this.j.set(i, inflate);
        } else {
            while (this.j.size() <= i) {
                this.j.add(null);
            }
            this.j.set(i, inflate);
        }
        if (b()) {
            if (this.b.getCurrentItem() == i) {
                textView.setTextColor(getResources().getColor(R.color.textcolor_blue));
            } else {
                textView.setTextColor(getResources().getColor(R.color.textcolor_black));
            }
        }
        return inflate;
    }

    public void c() {
        this.b.setAdapter(this.c);
        this.c.c();
        if (this.a != null) {
            this.a.a();
        }
        g();
    }

    private void g() {
        if (this.g == null || this.g.size() <= 1) {
            this.e.setVisibility(8);
            this.f.setVisibility(8);
        } else if (this.a.b()) {
            this.e.setVisibility(8);
            this.f.setVisibility(8);
        } else {
            this.e.setVisibility(0);
            this.f.setVisibility(0);
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable("adapter", this.c.b());
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        this.c.a(bundle.getParcelable("adapter"), null);
    }

    public Fragment d() {
        return this.c.e(this.b.getCurrentItem());
    }
}

package com.qq.reader.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.k;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.widget.PagerSlidingTabStrip;
import com.qq.reader.common.widget.PagerSlidingTabStrip.c;
import com.qq.reader.module.bookstore.qweb.TabInfo;
import com.qq.reader.module.bookstore.qweb.WebAdViewPager;
import com.qq.reader.module.bookstore.qweb.fragment.BaseFragment;
import com.qq.reader.module.bookstore.qweb.fragment.WebBrowserFragment;
import com.qq.reader.view.linearmenu.b;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;

public class BookClassifyActivity extends ReaderBaseActivity implements com.qq.reader.common.web.js.JsAdEvent.a, com.qq.reader.view.web.n.a {
    protected final int a = 304;
    private PagerSlidingTabStrip b;
    private WebAdViewPager c;
    private ImageView d;
    private TextView[] e;
    private a f;
    private ArrayList<TabInfo> g = new ArrayList();
    private ImageView h;
    private boolean i = true;
    private BroadcastReceiver j = new BroadcastReceiver(this) {
        final /* synthetic */ BookClassifyActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            this.a.a();
        }
    };
    private b k;

    private class a extends com.qq.reader.module.bookstore.qweb.a implements c {
        final /* synthetic */ BookClassifyActivity a;

        public a(BookClassifyActivity bookClassifyActivity, k kVar) {
            this.a = bookClassifyActivity;
            super(kVar);
        }

        public View c(int i) {
            TabInfo tabInfo = (TabInfo) this.a.g.get(i);
            View inflate = this.a.getLayoutInflater().inflate(R.layout.bookclassify_tab_item, null);
            TextView textView = (TextView) inflate.findViewById(R.id.tab_text);
            textView.setText(tabInfo.title);
            if (i == this.a.c.getCurrentItem()) {
                textView.setTextColor(this.a.getResources().getColor(R.color.bookstore_tab_select_text));
            } else {
                textView.setTextColor(this.a.getResources().getColor(R.color.bookstore_tab_unselect_text));
            }
            this.a.e[i] = textView;
            return inflate;
        }

        public int a() {
            return this.a.g == null ? 0 : this.a.g.size();
        }

        public BaseFragment d(int i) {
            return f(i);
        }

        private BaseFragment f(int i) {
            InstantiationException e;
            IllegalAccessException e2;
            TabInfo tabInfo = (TabInfo) this.a.g.get(i);
            if (tabInfo == null) {
                return null;
            }
            BaseFragment baseFragment;
            try {
                baseFragment = (BaseFragment) tabInfo.cls.newInstance();
                try {
                    baseFragment.setHashArguments(tabInfo.args);
                    return baseFragment;
                } catch (InstantiationException e3) {
                    e = e3;
                } catch (IllegalAccessException e4) {
                    e2 = e4;
                    e2.printStackTrace();
                    return baseFragment;
                }
            } catch (InstantiationException e5) {
                e = e5;
                baseFragment = null;
                e.printStackTrace();
                return baseFragment;
            } catch (IllegalAccessException e6) {
                e2 = e6;
                baseFragment = null;
                e2.printStackTrace();
                return baseFragment;
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.qq.reader.refreshclassify");
        registerReceiver(this.j, intentFilter);
        setContentView(R.layout.bookclassify_layout);
        this.b = (PagerSlidingTabStrip) findViewById(R.id.bookclassify_tabs);
        this.b.setShouldExpand(true);
        this.c = (WebAdViewPager) findViewById(R.id.bookclassify_page);
        this.d = (ImageView) findViewById(R.id.bookstore_more);
        this.h = (ImageView) findViewById(R.id.bookclassify_search);
        this.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ BookClassifyActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                o.c(this.a, "");
            }
        });
        HashMap hashMap = new HashMap();
        hashMap.put("book_url", "category.html?" + e.b((Context) this) + e.c((Context) this) + "&up=" + d.aS(this));
        this.g.add(new TabInfo(WebBrowserFragment.class, "", "出版图书", hashMap));
        this.e = new TextView[this.g.size()];
        this.f = new a(this, getSupportFragmentManager());
        this.c.setOffscreenPageLimit(2);
        this.c.setAdapter(this.f);
        this.b.setViewPager(this.c);
        this.b.setIndicatorColorResource(R.color.textcolor_white);
        int size = com.qq.reader.common.c.a.bU / this.g.size();
        this.b.setLineRightAndLeftPadding(size / 8, size / 8);
        this.b.setIndicatorHeight(getResources().getDimensionPixelOffset(R.dimen.common_dp_2));
        this.c.setShouldIntercept(new com.qq.reader.module.bookstore.qweb.WebAdViewPager.a(this) {
            final /* synthetic */ BookClassifyActivity a;

            {
                this.a = r1;
            }

            public boolean a() {
                return this.a.i;
            }

            public void b() {
                this.a.i = true;
            }
        });
        this.i = true;
        final ViewPager.e d = this.f.d();
        this.b.setOnPageChangeListener(new ViewPager.e(this) {
            final /* synthetic */ BookClassifyActivity b;

            public void onPageSelected(int i) {
                d.onPageSelected(i);
                this.b.a(this.b.c.getCurrentItem());
            }

            public void onPageScrolled(int i, float f, int i2) {
                d.onPageScrolled(i, f, i2);
            }

            public void onPageScrollStateChanged(int i) {
                if (i == 2) {
                    this.b.a(this.b.c.getCurrentItem());
                }
                d.onPageScrollStateChanged(i);
            }
        });
        d();
        setIsShowNightMask(false);
    }

    private void d() {
    }

    protected void onStop() {
        this.i = true;
        super.onStop();
    }

    protected void onDestroy() {
        unregisterReceiver(this.j);
        super.onDestroy();
    }

    public void a() {
        this.g.clear();
        HashMap hashMap = new HashMap();
        hashMap.put("book_url", "category.html?" + e.b((Context) this) + e.c((Context) this) + "&up=" + d.aS(this));
        this.g.add(new TabInfo(WebBrowserFragment.class, "", "出版图书", hashMap));
        this.e = new TextView[this.g.size()];
        a(this.c.getCurrentItem());
        this.c.setAdapter(this.f);
        this.f.c();
        this.b.a();
    }

    private void a(int i) {
    }

    public com.qq.reader.view.linearmenu.a b() {
        this.k = new b(this);
        this.k.a(0, "刷新", null);
        this.k.a(1, getString(R.string.online_history), null);
        this.k.a(new com.qq.reader.view.linearmenu.a.b(this) {
            final /* synthetic */ BookClassifyActivity a;

            {
                this.a = r1;
            }

            public boolean a(int i, Bundle bundle) {
                this.a.k.cancel();
                return this.a.a(i, bundle);
            }
        });
        this.k.a(new OnCancelListener(this) {
            final /* synthetic */ BookClassifyActivity a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.a.getWindow().closeAllPanels();
            }
        });
        return this.k;
    }

    protected boolean a(int i, Bundle bundle) {
        BaseFragment e = this.f.e(this.c.getCurrentItem());
        switch (i) {
            case 0:
                if (e instanceof WebBrowserFragment) {
                    ((WebBrowserFragment) e).refresh();
                }
                j.a(1, 2);
                return true;
            case 1:
                c();
                j.a(2, 2);
                return true;
            default:
                return false;
        }
    }

    public void c() {
        Intent intent = new Intent();
        intent.setClass(this, OnlineHistoryActivity.class);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivityForResult(intent, Constants.ERRORCODE_UNKNOWN);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        BaseFragment e = this.f.e(this.c.getCurrentItem());
        if (i == 4) {
            if ((e instanceof WebBrowserFragment) && !((WebBrowserFragment) e).stop()) {
                ((MainActivity) getParent()).a("bookstand_tab");
            }
            return true;
        }
        if (i == 82) {
            b().f_();
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean b(int i, Bundle bundle) {
        return false;
    }

    public void setTouched(boolean z) {
        this.i = !z;
    }
}

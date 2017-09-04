package com.qq.reader.module.feed.activity;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.ViewPager.e;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.BaseWebTabActivity;
import com.qq.reader.activity.HallOfFameFragment;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.c.c;
import com.qq.reader.module.bookstore.qweb.TabInfo;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;

public class FamousAuthorActivity extends BaseWebTabActivity implements e, a {
    private Bundle k;
    private String[] l = new String[]{"已关注", "名人堂"};
    private int m = 0;

    protected void onCreate(Bundle bundle) {
        this.k = getIntent().getExtras();
        this.m = this.k.getInt("LOCAL_STORE_IN_TAB_INDEX");
        super.onCreate(bundle);
        if (this.m == 0) {
            i.a("event_F66", null, ReaderApplication.getApplicationImp());
        }
    }

    protected void onResume() {
        super.onResume();
        this.b.setCurrentItem(this.m);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void onPageScrolled(final int i, float f, int i2) {
        this.mHandler.postDelayed(new Runnable(this) {
            final /* synthetic */ FamousAuthorActivity b;

            public void run() {
                try {
                    this.b.a.b(i);
                } catch (Exception e) {
                }
            }
        }, 1000);
    }

    public void onPageSelected(int i) {
        this.m = i;
        if (i == 0) {
            i.a("event_F66", null, ReaderApplication.getApplicationImp());
        } else {
            i.a("event_F67", null, ReaderApplication.getApplicationImp());
        }
    }

    public void onPageScrollStateChanged(int i) {
    }

    protected void a(Bundle bundle) {
        HashMap hashMap = new HashMap();
        hashMap.put("key_data", this.k);
        this.g.add(0, new TabInfo(NativePageFragmentForAuthorTimeLine.class, null, this.l[0], hashMap));
        this.g.add(1, new TabInfo(HallOfFameFragment.class, null, this.l[1], null));
        LayoutParams layoutParams = (LayoutParams) this.d.getLayoutParams();
        if (VERSION.SDK_INT >= 19) {
            layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.original_titlerbar_height) + com.qq.reader.common.c.a.ca;
            this.d.setPadding(0, com.qq.reader.common.c.a.ca, 0, 0);
        } else {
            layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.original_titlerbar_height);
            this.d.setPadding(0, 0, 0, 0);
        }
        this.d.setLayoutParams(layoutParams);
        findViewById(R.id.common_titler).setVisibility(8);
        this.e.setVisibility(8);
        this.f.setVisibility(8);
        this.d.setVisibility(0);
        this.d.setBackgroundResource(R.drawable.titler_bg);
        this.a.a(1, this.g);
        this.a.setOnPageChangeListener(this);
        com.qq.reader.common.widget.a.a((ImageView) this.d.findViewById(R.id.title_left), this);
        ImageView imageView = (ImageView) this.d.findViewById(R.id.title_right);
        imageView.setImageResource(R.drawable.titlebar_icon_search_selector);
        imageView.setBackgroundDrawable(null);
        imageView.setOnClickListener(new c(this) {
            final /* synthetic */ FamousAuthorActivity a;

            {
                this.a = r1;
            }

            public void a(View view) {
                StatisticsManager.a().a(7).c();
                j.a(16, 2);
                if (!this.a.isFinishing()) {
                    o.b(this.a, "", "5", ReaderApplication.getApplicationImp().getResources().getString(R.string.please_input_author));
                }
            }
        });
        imageView.setVisibility(0);
    }

    protected String e() {
        return null;
    }

    public void doFunction(Bundle bundle) {
    }

    public Activity getFromActivity() {
        return this;
    }

    public void b(int i) {
        this.b.setCurrentItem(i);
    }
}

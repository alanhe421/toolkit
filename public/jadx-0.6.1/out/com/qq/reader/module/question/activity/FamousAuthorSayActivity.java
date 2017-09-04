package com.qq.reader.module.question.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager.e;
import android.view.View;
import android.widget.ImageView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.BaseWebTabActivity;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.widget.a;
import com.qq.reader.module.bookstore.qnative.c.c;
import com.qq.reader.module.bookstore.qweb.TabInfo;
import com.qq.reader.module.question.fragment.FamousAuthorSayFragment;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;

public class FamousAuthorSayActivity extends BaseWebTabActivity implements e {
    private String[] k = new String[]{"大神说", "名人堂"};
    private int l = 0;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.l = getIntent().getExtras().getInt("LOCAL_STORE_IN_TAB_INDEX");
        if (this.l == 0) {
            i.a("event_D202", null, ReaderApplication.getApplicationImp());
        }
    }

    protected void onResume() {
        super.onResume();
        this.b.setCurrentItem(this.l);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected int f() {
        return R.layout.famous_author_layout;
    }

    public void onPageScrolled(final int i, float f, int i2) {
        this.mHandler.postDelayed(new Runnable(this) {
            final /* synthetic */ FamousAuthorSayActivity b;

            public void run() {
                try {
                    this.b.a.b(i);
                } catch (Exception e) {
                }
            }
        }, 1000);
    }

    public void onPageSelected(int i) {
        this.l = i;
        if (i == 0) {
            i.a("event_D202", null, ReaderApplication.getApplicationImp());
        } else {
            i.a("event_D203", null, ReaderApplication.getApplicationImp());
        }
    }

    public void onPageScrollStateChanged(int i) {
    }

    protected void a(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        HashMap hashMap = new HashMap();
        hashMap.put("key_data", bundle2);
        this.g.add(0, new TabInfo(FamousAuthorSayFragment.class, null, this.k[0], hashMap));
        this.a.a(2, this.g);
        this.a.setOnPageChangeListener(this);
        a.a((ImageView) this.d.findViewById(R.id.title_left), this);
        ImageView imageView = (ImageView) findViewById(R.id.profile_header_right_image);
        imageView.setImageResource(R.drawable.titlebar_icon_search_selector);
        imageView.setBackgroundDrawable(null);
        imageView.setOnClickListener(new c(this) {
            final /* synthetic */ FamousAuthorSayActivity a;

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
        return this.k[0];
    }
}

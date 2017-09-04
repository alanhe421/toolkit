package com.qq.reader.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qweb.TabInfo;
import com.qq.reader.module.bookstore.qweb.fragment.WebBrowserFragment;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;

public class WebBookRewardActivity extends BaseWebTabActivity {
    private int k;
    private String l = "作者互动";

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int size = this.g.size();
        if (this.k < 0 || this.k >= size) {
            this.k = 0;
        }
        this.b.setCurrentItem(this.k);
    }

    protected void a(Bundle bundle) {
        long longExtra = getIntent().getLongExtra("URL_BUILD_PERE_BOOK_ID", 0);
        if (longExtra <= 0) {
            longExtra = getIntent().getLongExtra("URL_BUILD_PERE_BOOK_ID", 0);
        }
        this.k = getIntent().getIntExtra("PARA_TYPE_REWARD_TAB_INDEX", 0);
        this.l = getIntent().getStringExtra("PARA_TYPE_BOOK_TITLE");
        if (TextUtils.isEmpty(this.l)) {
            this.l = "书籍互动";
        }
        String stringExtra = getIntent().getStringExtra("PARA_TYPE_REWARD_EXTRA_URL_PARAMS");
        HashMap hashMap = new HashMap();
        hashMap.put("book_url", e.b(this, longExtra) + stringExtra);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("book_url", e.c(this, longExtra) + stringExtra);
        HashMap hashMap3 = new HashMap();
        hashMap3.put("book_url", e.d(this, longExtra) + stringExtra);
        HashMap hashMap4 = new HashMap();
        hashMap4.put("book_url", e.e(this, longExtra) + stringExtra);
        this.g.add(new TabInfo(WebBrowserFragment.class, "", "打赏", hashMap));
        this.g.add(new TabInfo(WebBrowserFragment.class, "", "推荐票", hashMap2));
        this.g.add(new TabInfo(WebBrowserFragment.class, "", "月票", hashMap3));
        this.g.add(new TabInfo(WebBrowserFragment.class, "", "粉丝榜", hashMap4));
        LayoutParams layoutParams = (LayoutParams) this.d.getLayoutParams();
        layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.common_dp_40);
        this.d.setLayoutParams(layoutParams);
        this.a.setIndicatorBottomPadding(0);
    }

    protected String e() {
        return this.l;
    }

    public boolean b() {
        return true;
    }

    public View a(int i) {
        TabInfo tabInfo = (TabInfo) this.g.get(i);
        View inflate = getLayoutInflater().inflate(R.layout.profileaccount_tab_item, null);
        ((TextView) inflate.findViewById(R.id.tab_text)).setText(tabInfo.title);
        if (this.j.size() > i) {
            this.j.set(i, inflate);
        } else {
            while (this.j.size() <= i) {
                this.j.add(null);
            }
            this.j.set(i, inflate);
        }
        return inflate;
    }
}

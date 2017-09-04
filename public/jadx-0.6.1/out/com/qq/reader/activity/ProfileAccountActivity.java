package com.qq.reader.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qweb.TabInfo;
import com.qq.reader.module.bookstore.qweb.fragment.WebBrowserFragment;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;

public class ProfileAccountActivity extends BaseWebTabActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a.setIndicatorColorResource(R.color.textcolor_white);
        this.a.setIndicatorHeight(getResources().getDimensionPixelOffset(R.dimen.common_dp_2));
        this.a.setVisibility(8);
    }

    protected void a(Bundle bundle) {
        HashMap hashMap = new HashMap();
        hashMap.put("book_url", e.f(this));
        this.g.add(new TabInfo(WebBrowserFragment.class, "", "", hashMap));
        LayoutParams layoutParams = (LayoutParams) this.d.getLayoutParams();
        layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.common_dp_40);
        this.d.setLayoutParams(layoutParams);
        this.a.setIndicatorBottomPadding(0);
    }

    protected String e() {
        return "我的账户";
    }

    protected int f() {
        return R.layout.profile_account_layout;
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

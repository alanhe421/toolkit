package com.qq.reader.activity;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import com.qq.reader.common.c.a;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;

public class ProfileLevelActivity extends BaseWebTabActivity {
    private String k;
    private int l = 0;

    protected void onCreate(Bundle bundle) {
        Bundle bundleExtra = getIntent().getBundleExtra("info");
        this.k = bundleExtra.getString("title");
        this.l = bundleExtra.getInt("select");
        this.g = (ArrayList) bundleExtra.getSerializable("tablist");
        super.onCreate(bundle);
        this.b.setCurrentItem(this.l);
    }

    protected void a(Bundle bundle) {
        LayoutParams layoutParams = (LayoutParams) this.d.getLayoutParams();
        if (VERSION.SDK_INT >= 19) {
            layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.original_titlerbar_height) + a.ca;
            this.d.setPadding(0, a.ca, 0, 0);
        } else {
            layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.original_titlerbar_height);
            this.d.setPadding(0, 0, 0, 0);
        }
        this.d.setLayoutParams(layoutParams);
        findViewById(R.id.common_titler).setVisibility(8);
        this.d.setVisibility(0);
        this.d.setBackgroundResource(R.drawable.titler_bg);
        if (this.g == null || this.g.size() <= 2) {
            this.a.a(1, this.g);
        } else {
            this.a.a(2, this.g);
            findViewById(R.id.common_titler).setVisibility(0);
            this.d.setPadding(0, 0, 0, 0);
            layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.original_titlerbar_height);
        }
        com.qq.reader.common.widget.a.a((ImageView) this.d.findViewById(R.id.title_left), this);
    }

    protected String e() {
        return this.k;
    }

    protected int f() {
        return R.layout.profile_account_layout;
    }

    public boolean b() {
        return true;
    }

    public View a(int i) {
        return null;
    }
}

package com.qq.reader.module.qmessage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.ViewPager.e;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import com.qq.reader.activity.BaseWebTabActivity;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qweb.TabInfo;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;

public class MessageActivity extends BaseWebTabActivity implements e {
    private BroadcastReceiver k = new 1(this);

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(a.cF);
        intentFilter.addAction(a.cG);
        registerReceiver(this.k, intentFilter);
        Intent intent = getIntent();
        if (intent != null && intent.getBooleanExtra("fromNotification", false)) {
            Object stringExtra = intent.getStringExtra("msgid");
            if (TextUtils.isEmpty(stringExtra)) {
                stringExtra = "0";
            }
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, stringExtra);
            i.a("event_C161", hashMap, this);
            StatisticsManager.a().a("event_C161", hashMap);
        }
    }

    protected void onResume() {
        super.onResume();
        j();
        if (d.aF(this)) {
            sendBroadcast(new Intent(a.cG));
            d.A((Context) this, false);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.k);
    }

    private void j() {
        if (d.z((Context) this, 1)) {
            this.a.a(0);
            this.b.setCurrentItem(0);
        } else if (d.z((Context) this, 2)) {
            this.a.a(1);
            this.b.setCurrentItem(1);
        }
    }

    protected int f() {
        return R.layout.message_layout;
    }

    public void onPageScrolled(int i, float f, int i2) {
        this.mHandler.postDelayed(new 2(this, i), 1000);
    }

    public void onPageSelected(int i) {
        i.a("event_C154", null, this);
    }

    public void onPageScrollStateChanged(int i) {
    }

    private MessageFragment b(int i) {
        Bundle bundle = new Bundle();
        MessageFragment messageFragment = new MessageFragment();
        bundle.putInt("TYPE", i);
        messageFragment.setArguments(bundle);
        return messageFragment;
    }

    protected void a(Bundle bundle) {
        this.g.add(0, new TabInfo(b(1), null, "通知消息", null));
        this.g.add(1, new TabInfo(b(2), null, "互动消息", null));
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
        this.e.setVisibility(8);
        this.f.setVisibility(8);
        this.d.setVisibility(0);
        this.d.setBackgroundResource(R.drawable.titler_bg);
        this.a.a(1, this.g);
        this.a.setOnPageChangeListener(this);
        com.qq.reader.common.widget.a.a((ImageView) this.d.findViewById(R.id.title_left), this);
    }

    protected String e() {
        return null;
    }
}

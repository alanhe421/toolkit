package com.qq.reader.module.bookstore.qnative.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforOther;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.impl.NativeServerLimitTimeDiscountBuyPage;
import com.qq.reader.module.bookstore.qnative.page.impl.NativeServerLimitTimeDiscountBuyPage.EventTimeItem;
import com.qq.reader.module.bookstore.qweb.TabInfo;
import com.qq.reader.module.feed.a.a;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NativeLimitTimeDiscountBuyActivity extends NativeBookStoreTwoLevelActivity {
    private a A;
    private int B;
    private BroadcastReceiver C = new BroadcastReceiver(this) {
        final /* synthetic */ NativeLimitTimeDiscountBuyActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("BROADCAST_ACTION_FRAGMENT_NOTIFY".equals(action)) {
                ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("EXTRA_PARAM_KEY_EVENT_LIST");
                int i = 0;
                while (parcelableArrayListExtra != null && this.a.j != null && i < parcelableArrayListExtra.size() && i < this.a.j.size()) {
                    long currentTimeMillis = System.currentTimeMillis();
                    TextView textView = (TextView) ((View) this.a.j.get(i)).findViewById(R.id.tv_desc);
                    if (currentTimeMillis <= ((EventTimeItem) parcelableArrayListExtra.get(i)).a || currentTimeMillis >= ((EventTimeItem) parcelableArrayListExtra.get(i)).b) {
                        textView.setText("即将开始");
                    } else {
                        textView.setText("进行中");
                    }
                    i++;
                }
            } else if ("BROADCAST_ACTION_FORCE_TO_REFRESH".equals(action)) {
                ((NativePageFragmentforOther) this.a.d()).silentRefreshOnBackground();
            } else if ("BROADCAST_ACTION_SELECT_CUR_ITEM".equals(action)) {
                try {
                    int intExtra = intent.getIntExtra("BUNDLE_PARAM_LIMIT_TIME_DISCOUNT_BUY_SEQ", 0);
                    this.a.a.setCurrentItem(intExtra);
                    this.a.onPageSelected(intExtra);
                } catch (Exception e) {
                    c.e("Error", e.getMessage());
                }
            } else if ("BROADCAST_ACTION_RELOAD_DATA".equals(action)) {
                String stringExtra = intent.getStringExtra("BUNDLE_PARAM_LIMIT_TIME_DISCOUNT_BUY_START_TIME");
                if (this.a.r != null) {
                    this.a.r.putString("BUNDLE_PARAM_LIMIT_TIME_DISCOUNT_BUY_START_TIME", stringExtra);
                }
                this.a.c(this.a.r);
            }
        }
    };
    private int k = 0;
    private int w = 0;
    private int x = 0;
    private int y = 0;
    private final int z = ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.common_dp_50);

    public void onCreate(Bundle bundle) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.k = extras.getInt("NEW_TAB_TWO_LEVEL_TITLE_BAR_BG_RES_ID", 0);
            this.w = extras.getInt("NEW_TAB_TWO_LEVEL_TITLE_BAR_SELECTED_TEXTCOLOR", 0);
            this.x = extras.getInt("NEW_TAB_TWO_LEVEL_TITLE_BAR_UNSELECTED_TEXTCOLOR", 0);
            this.y = extras.getInt("NEW_TAB_TWO_LEVEL_LOADING_DRAWABLE_RES_ID", 0);
        }
        super.onCreate(bundle);
        if (this.k != 0) {
            findViewById(R.id.common_titler).setBackgroundResource(this.k);
        }
        if (this.y != 0) {
            ((ProgressBar) findViewById(R.id.default_progress)).setIndeterminateDrawable(getResources().getDrawable(this.y));
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("BROADCAST_ACTION_FRAGMENT_NOTIFY");
        intentFilter.addAction("BROADCAST_ACTION_FORCE_TO_REFRESH");
        intentFilter.addAction("BROADCAST_ACTION_SELECT_CUR_ITEM");
        intentFilter.addAction("BROADCAST_ACTION_RELOAD_DATA");
        registerReceiver(this.C, intentFilter);
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.C);
    }

    protected int f() {
        return R.layout.localbookstore_viewpager_limit_time_discount_buy;
    }

    public View a(int i) {
        TabInfo tabInfo = (TabInfo) this.g.get(i);
        if (!(this.w == 0 || this.x == 0)) {
            int[] iArr = new int[]{this.w, this.w, this.x};
            r2 = new int[3][];
            r2[0] = new int[]{16842908};
            r2[1] = new int[]{16842913};
            r2[2] = new int[0];
            ColorStateList colorStateList = new ColorStateList(r2, iArr);
        }
        View inflate = getLayoutInflater().inflate(R.layout.limit_time_discount_buy_activity_tab_item, null);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_desc);
        ((TextView) inflate.findViewById(R.id.tv_title)).setText(tabInfo.title);
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

    protected void m() {
        int i;
        super.m();
        LayoutParams layoutParams = (LayoutParams) this.d.getLayoutParams();
        layoutParams.height = this.z;
        this.d.setLayoutParams(layoutParams);
        this.a.setLineRightAndLeftPadding(0, 0);
        this.a.setIndicatorBottomPadding(0);
        this.a.setBackgroundColor(getResources().getColor(17170445));
        if (getResources().getDisplayMetrics().densityDpi == 480) {
            i = 1;
        } else {
            i = 0;
        }
        this.a.setIndicatorResource(R.drawable.limit_time_discount_buy_tab_item_selected, 0, i + ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.limit_time_discount_buy_pager_strip_padding_bottom));
        this.a.setTabPaddingLeftRight(0);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.a.getLayoutParams();
        layoutParams2.width = -1;
        layoutParams2.height = this.z;
        this.a.setLayoutParams(layoutParams2);
        ((RelativeLayout.LayoutParams) ((FrameLayout) findViewById(R.id.fl_content)).getLayoutParams()).setMargins(0, this.z, 0, 0);
        this.a.setOffscreenPageLimit(5);
        this.a.setCurrentItem(0);
        onPageSelected(0);
    }

    protected void a(Bundle bundle) {
        int i;
        super.a(bundle);
        this.d.setVisibility(0);
        this.d.setBackgroundResource(R.drawable.limit_time_discount_buy_tab_bg);
        this.b.setOffscreenPageLimit(5);
        this.a.setLineRightAndLeftPadding(0, 0);
        this.a.setIndicatorBottomPadding(0);
        this.a.setBackgroundColor(getResources().getColor(17170445));
        if (getResources().getDisplayMetrics().densityDpi == ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE) {
            i = 1;
        } else {
            i = 0;
        }
        this.a.setIndicatorResource(R.drawable.limit_time_discount_buy_tab_indicator, 0, i + ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.limit_time_discount_buy_pager_strip_padding_bottom));
        this.a.setTabPaddingLeftRight(0);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.a.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -2;
        this.a.setLayoutParams(layoutParams);
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onResume() {
        super.onResume();
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        this.B = i;
        if (i != 0) {
            Fragment d = d();
            if (d != null && (d instanceof NativePageFragmentforOther)) {
                NativePageFragmentforOther nativePageFragmentforOther = (NativePageFragmentforOther) d;
                if (nativePageFragmentforOther != null) {
                    b bVar = nativePageFragmentforOther.mHoldPage;
                    if (bVar != null && (bVar instanceof NativeServerLimitTimeDiscountBuyPage)) {
                        NativeServerLimitTimeDiscountBuyPage nativeServerLimitTimeDiscountBuyPage = (NativeServerLimitTimeDiscountBuyPage) bVar;
                        if (nativeServerLimitTimeDiscountBuyPage != null) {
                            nativeServerLimitTimeDiscountBuyPage.x();
                        }
                    }
                }
            }
        }
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, String.valueOf(i + 1));
        i.a("event_F121", hashMap, ReaderApplication.getApplicationImp());
        int i2 = 0;
        while (this.j != null && i2 < this.j.size()) {
            TextView textView = (TextView) ((View) this.j.get(i2)).findViewById(R.id.tv_title);
            if (i == i2) {
                textView.setTextSize(0, (float) ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.text_size_class_5));
            } else {
                textView.setTextSize(0, (float) ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.text_size_class_3));
            }
            i2++;
        }
    }

    public int x() {
        return this.B;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 99 && strArr.length > 0 && iArr.length > 0 && strArr[0].equals("android.permission.READ_CALENDAR")) {
            if (iArr[0] == 0) {
                if (this.A != null) {
                    this.A.b();
                }
            } else if (this.A != null) {
                this.A.a();
            }
        }
    }

    public void a(a aVar) {
        this.A = aVar;
    }
}

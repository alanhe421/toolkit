package com.qq.reader.module.audio.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.k;
import android.support.v4.view.ViewPager.e;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.activity.MainActivity;
import com.qq.reader.activity.ReaderBaseFragment;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.widget.PagerSlidingTabStrip;
import com.qq.reader.common.widget.PagerSlidingTabStrip.c;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforOther;
import com.qq.reader.module.bookstore.qweb.TabInfo;
import com.qq.reader.module.bookstore.qweb.WebAdViewPager;
import com.qq.reader.module.bookstore.qweb.fragment.BaseFragment;
import com.qq.reader.module.bookstore.qweb.fragment.WebBrowserFragment;
import com.qq.reader.view.linearmenu.b;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class NativeListenZoneTabFragment extends ReaderBaseFragment implements e, com.qq.reader.module.bookstore.qnative.c.a {
    public static final int MENU_ID_EXIT = 3;
    public static final int MENU_ID_REFRESH = 0;
    private String[] PAGE_CATEGORY = null;
    private String[] PAGE_LOCAL_NAME = null;
    private String[] PAGE_TITLES = new String[]{"热门推荐", "听书书库"};
    public a mAdapter;
    protected View mCommonTabShadow;
    protected View mCommon_tab_tabs_layout;
    private int mGendar = 1;
    private TextView mLefttab;
    protected PagerSlidingTabStrip mPagerSlidingTabStrip;
    private b mReaderMenu;
    private TextView mRighttab;
    private View mRootView;
    protected ArrayList<TabInfo> mTabList = new ArrayList();
    protected TextView mTitleView;
    protected ArrayList<View> mTitlelist = new ArrayList();
    protected ImageView mTopBarLeftView;
    protected WebAdViewPager mViewPager;
    private BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver(this) {
        final /* synthetic */ NativeListenZoneTabFragment a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (com.qq.reader.common.c.a.cs.equalsIgnoreCase(intent.getAction())) {
                this.a.mHandler.sendEmptyMessage(8012);
            }
        }
    };
    protected View pagerSlideTabLine;

    protected class a extends com.qq.reader.module.bookstore.qweb.a implements c {
        final /* synthetic */ NativeListenZoneTabFragment a;

        public a(NativeListenZoneTabFragment nativeListenZoneTabFragment, k kVar) {
            this.a = nativeListenZoneTabFragment;
            super(kVar);
        }

        public View c(int i) {
            return this.a.getLocalTitleView(i);
        }

        public int a() {
            return this.a.mTabList == null ? 0 : this.a.mTabList.size();
        }

        public BaseFragment d(int i) {
            return f(i);
        }

        private BaseFragment f(int i) {
            TabInfo tabInfo = (TabInfo) this.a.mTabList.get(i);
            if (tabInfo == null) {
                return null;
            }
            BaseFragment baseFragment;
            Class cls = tabInfo.cls;
            BaseFragment baseFragment2 = tabInfo.mFragment;
            if (baseFragment2 == null) {
                try {
                    baseFragment = (BaseFragment) cls.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
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

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.localstack_viewpager_layout, null);
        }
        return this.mRootView;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (!(VERSION.SDK_INT <= 10 || "Meizu_M040".equals(com.qq.reader.common.c.a.E) || getActivity() == null)) {
            getActivity().getWindow().addFlags(SigType.WLOGIN_PF);
        }
        initBaseTabUI();
        init();
        setIsShowNightMask(false);
        StatisticsManager.a().a(1).a("pagename", this.PAGE_LOCAL_NAME[0]).c();
    }

    private void initBaseTabUI() {
        if (this.mRootView != null) {
            this.mTitleView = (TextView) this.mRootView.findViewById(R.id.profile_header_title);
            this.mTopBarLeftView = (ImageView) this.mRootView.findViewById(R.id.profile_header_left_back);
            if (this.mTopBarLeftView != null) {
                this.mTopBarLeftView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ NativeListenZoneTabFragment a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                    }
                });
            }
            this.mCommon_tab_tabs_layout = this.mRootView.findViewById(R.id.common_tab_tabs_layout);
            this.mPagerSlidingTabStrip = (PagerSlidingTabStrip) this.mRootView.findViewById(R.id.common_tab_tabs);
            this.mPagerSlidingTabStrip.setShouldExpand(true);
            this.pagerSlideTabLine = this.mRootView.findViewById(R.id.common_tab__line);
            this.mCommonTabShadow = this.mRootView.findViewById(R.id.common_tab_top_shadow);
            this.mViewPager = (WebAdViewPager) this.mRootView.findViewById(R.id.common_tab_viewpager);
            this.mTitleView.setText(getTopBarTitle());
            if (this.mTabList != null && this.mTabList.size() > 0) {
                this.mCommon_tab_tabs_layout.setVisibility(0);
                int size = this.mTabList.size();
                int i = com.qq.reader.common.c.a.bU / size;
                int i2 = i / 8;
                if (size == 2 || size == 3) {
                    i2 = (i - getApplicationContext().getResources().getDimensionPixelOffset(R.dimen.common_dp_80)) / 2;
                }
                this.mPagerSlidingTabStrip.setLineRightAndLeftPadding(i2, i2);
            }
            this.mAdapter = new a(this, getActivity().getSupportFragmentManager());
            this.mViewPager.setOffscreenPageLimit(2);
            this.mViewPager.setAdapter(this.mAdapter);
            if (this.mTabList == null || this.mTabList.size() <= 1) {
                this.pagerSlideTabLine.setVisibility(8);
                this.mCommonTabShadow.setVisibility(8);
                if (this.mCommon_tab_tabs_layout == null) {
                    this.mCommon_tab_tabs_layout = this.mRootView.findViewById(R.id.common_titler);
                } else {
                    this.mCommon_tab_tabs_layout.setVisibility(8);
                }
            }
            this.mPagerSlidingTabStrip.setViewPager(this.mViewPager);
            this.mPagerSlidingTabStrip.setOnPageChaneListenerForTitle(new e(this) {
                final /* synthetic */ NativeListenZoneTabFragment a;

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
    }

    @Deprecated
    protected void setCurrentTitle(int i) {
        if (this.mTitlelist.size() > 0 && i < this.mTitlelist.size()) {
            for (int i2 = 0; i2 < this.mTitlelist.size(); i2++) {
                if (((View) this.mTitlelist.get(i2)) != null) {
                    setSelectTitle(i2, i);
                }
            }
        }
    }

    public void IOnResume() {
        super.IOnResume();
        if (isNeedStatistics()) {
            StatisticsManager.a().b();
            String statisticsPageName = getStatisticsPageName();
            if (!TextUtils.isEmpty(statisticsPageName)) {
                StatisticsManager.a().a(statisticsPageName);
            }
        }
        if (getActivity() != null) {
            getActivity().registerReceiver(this.myBroadcastReceiver, new IntentFilter(com.qq.reader.common.c.a.cs));
        }
        i.a("event_reader_bookstore", null, getApplicationContext());
        if (this.mGendar != d.aQ(getApplicationContext())) {
            initTabList();
        }
        StatisticsManager.a().b();
        StatisticsManager.a().a(this.PAGE_LOCAL_NAME[this.mViewPager.getCurrentItem()]);
        this.mHandler.sendEmptyMessageDelayed(1, 1500);
        showChannelAdv();
        forceRerefshTabTextColor();
    }

    public void IOnPause() {
        try {
            if (getActivity() != null) {
                getActivity().unregisterReceiver(this.myBroadcastReceiver);
            }
        } catch (Exception e) {
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    private void initTabList() {
        this.mTabList.clear();
        for (int i = 0; i < this.PAGE_LOCAL_NAME.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putString("KEY_JUMP_PAGENAME", this.PAGE_LOCAL_NAME[i]);
            bundle.putLong("KEY_PAGEINDEX", -1);
            bundle.putString("URL_BUILD_PERE_AUDIO_CATEGORY", this.PAGE_CATEGORY[i]);
            HashMap hashMap = new HashMap();
            hashMap.put("key_data", bundle);
            this.mTabList.add(i, new TabInfo(NativePageFragmentforOther.class, this.PAGE_TITLES[i], this.PAGE_TITLES[i], hashMap));
        }
        notifyAdapterChanged();
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 1:
                if (ao.o(getApplicationContext())) {
                    com.qq.reader.cservice.adv.b.a(getApplicationContext()).a();
                }
                return true;
            case 8012:
                showChannelAdv();
                return true;
            case 8000009:
                try {
                    ((NativePageFragmentforOther) getCurFragment()).refreshWithoutPulldown(true);
                } catch (Exception e) {
                }
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    private void init() {
        if (this.mRootView != null) {
            initTabList();
            int size = this.mTabList.size();
            if (size >= 1) {
                this.mCommon_tab_tabs_layout.setVisibility(0);
                this.mCommon_tab_tabs_layout.setBackgroundDrawable(null);
            }
            this.mViewPager.setOffscreenPageLimit(size);
            this.mPagerSlidingTabStrip.setVisibility(0);
            this.mPagerSlidingTabStrip.setIndicatorResource(R.drawable.stacktab_flip, getApplicationContext().getResources().getDimensionPixelOffset(R.dimen.common_dp_10), getApplicationContext().getResources().getDimensionPixelOffset(R.dimen.common_dp_10));
            this.mPagerSlidingTabStrip.setTextSize((int) getApplicationContext().getResources().getDimension(R.dimen.text_size_class_3));
            this.mPagerSlidingTabStrip.setIndicatorHeight(10);
            this.mPagerSlidingTabStrip.setOnPageChangeListener(this);
            this.mPagerSlidingTabStrip.setShouldExpand(false);
            this.mPagerSlidingTabStrip.setTabPaddingLeftRight(0);
            com.qq.reader.common.widget.a.a((ImageView) this.mRootView.findViewById(R.id.title_left), getActivity());
            ImageView imageView = (ImageView) this.mRootView.findViewById(R.id.title_right);
            imageView.setVisibility(0);
            imageView.setImageResource(R.drawable.titlebar_icon_search_selector);
            imageView.setBackgroundDrawable(null);
            imageView.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
                final /* synthetic */ NativeListenZoneTabFragment a;

                {
                    this.a = r1;
                }

                public void a(View view) {
                    if (this.a.getActivity() != null) {
                        o.a(this.a.getActivity(), "", "");
                    }
                }
            });
            notifyAdapterChanged();
            this.mPagerSlidingTabStrip.setCurrentItem(0);
        }
    }

    protected void initTabList(Bundle bundle) {
    }

    protected String getTopBarTitle() {
        return null;
    }

    public void notifyAdapterChanged() {
        this.mViewPager.setAdapter(this.mAdapter);
        this.mAdapter.c();
        if (this.mPagerSlidingTabStrip != null) {
            this.mPagerSlidingTabStrip.a();
        }
        setTabShadow();
    }

    private void setTabShadow() {
        if (this.mTabList == null || this.mTabList.size() <= 1) {
            this.pagerSlideTabLine.setVisibility(8);
            this.mCommonTabShadow.setVisibility(8);
        } else if (this.mPagerSlidingTabStrip.b()) {
            this.pagerSlideTabLine.setVisibility(8);
            this.mCommonTabShadow.setVisibility(8);
        } else {
            this.pagerSlideTabLine.setVisibility(0);
            this.mCommonTabShadow.setVisibility(0);
        }
    }

    public void onPageScrollStateChanged(int i) {
    }

    private void forceRerefshTabTextColor() {
        if (this.mPagerSlidingTabStrip != null && this.mLefttab != null && this.mRighttab != null) {
            int currentPagerViewItem = this.mPagerSlidingTabStrip.getCurrentPagerViewItem();
            if (currentPagerViewItem >= 0) {
                float f = (float) currentPagerViewItem;
                int color = getApplicationContext().getResources().getColor(R.color.skin_set_localstack_tab_textcolor_startcolor);
                int color2 = getApplicationContext().getResources().getColor(R.color.skin_set_localstack_tab_textcolor_endcolor);
                int i = (color >> 16) & 255;
                int i2 = (color2 >> 16) & 255;
                int i3 = (color >> 8) & 255;
                int i4 = (color2 >> 8) & 255;
                color = (color >> 0) & 255;
                color2 = (color2 >> 0) & 255;
                int i5 = ((int) (((float) (i2 - i)) * f)) + i;
                int i6 = ((int) (((float) (i4 - i3)) * f)) + i3;
                currentPagerViewItem = ((int) (f * ((float) (color2 - color)))) + color;
                i = (i + i2) - i5;
                i2 = (i4 + i3) - i6;
                color = (color + color2) - currentPagerViewItem;
                this.mLefttab.setTextColor(ColorStateList.valueOf(Color.rgb(i5, i6, currentPagerViewItem)));
                this.mRighttab.setTextColor(ColorStateList.valueOf(Color.rgb(i, i2, color)));
            }
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (f != 0.0f || f != 1.0f) {
            try {
                if (this.mLefttab != null && this.mRighttab != null) {
                    float f2 = ((float) i) + f;
                    int color = getApplicationContext().getResources().getColor(R.color.skin_set_localstack_tab_textcolor_startcolor);
                    int color2 = getApplicationContext().getResources().getColor(R.color.skin_set_localstack_tab_textcolor_endcolor);
                    int i3 = (color >> 16) & 255;
                    int i4 = (color2 >> 16) & 255;
                    int i5 = (color >> 8) & 255;
                    int i6 = (color2 >> 8) & 255;
                    color = (color >> 0) & 255;
                    color2 = (color2 >> 0) & 255;
                    int i7 = ((int) (((float) (i4 - i3)) * f2)) + i3;
                    int i8 = ((int) (((float) (i6 - i5)) * f2)) + i5;
                    int i9 = ((int) (f2 * ((float) (color2 - color)))) + color;
                    i3 = (i3 + i4) - i7;
                    i4 = (i6 + i5) - i8;
                    color = (color + color2) - i9;
                    if (i == 0) {
                        this.mLefttab.setTextColor(ColorStateList.valueOf(Color.rgb(i7, i8, i9)));
                        this.mRighttab.setTextColor(ColorStateList.valueOf(Color.rgb(i3, i4, color)));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onPageSelected(int i) {
        if (i == 1) {
            try {
                j.a(105, 2);
                i.a("event_B241", null, getApplicationContext());
                StatisticsManager.a().a("event_C241", null);
            } catch (Exception e) {
                return;
            }
        } else if (i == 0) {
            j.a(104, 2);
            i.a("event_B240", null, getApplicationContext());
            StatisticsManager.a().a("event_C240", null);
        }
        StatisticsManager.a().b();
        StatisticsManager.a().a("pagename", this.PAGE_LOCAL_NAME[i]).a(1).c();
        d.M(getApplicationContext(), i);
    }

    public void doFunction(Bundle bundle) {
    }

    public Activity getFromActivity() {
        return getActivity();
    }

    public void startActivity(Intent intent) {
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        super.startActivity(intent);
    }

    public boolean iSsetCurrentTitle() {
        return true;
    }

    public void setSelectTitle(int i, int i2) {
    }

    public View getLocalTitleView(int i) {
        com.qq.reader.common.monitor.debug.c.a("stack", "getLocalTitleView " + i);
        if (getActivity() == null) {
            return null;
        }
        View inflate;
        TextView textView;
        TabInfo tabInfo = (TabInfo) this.mTabList.get(i);
        if (i == 0) {
            inflate = getActivity().getLayoutInflater().inflate(R.layout.lbsstackactivtiy_tab_leftitem, null);
            textView = (TextView) inflate.findViewById(R.id.tab_text);
            this.mLefttab = textView;
        } else {
            inflate = getActivity().getLayoutInflater().inflate(R.layout.lbsstackactivtiy_tab_rightitem, null);
            textView = (TextView) inflate.findViewById(R.id.tab_text);
            this.mRighttab = textView;
        }
        textView.setText(tabInfo.title);
        if (this.mTitlelist.size() > i) {
            this.mTitlelist.set(i, inflate);
            return inflate;
        }
        while (this.mTitlelist.size() <= i) {
            this.mTitlelist.add(null);
        }
        this.mTitlelist.set(i, inflate);
        return inflate;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            j.a(21, 2);
            i.a("event_C22", null, getApplicationContext());
            if (getActivity() != null) {
                ((MainActivity) getActivity()).a("bookstand_tab");
            }
        } else if (i == 82) {
        }
        return true;
    }

    public void onTabSelected(int i) {
    }

    public com.qq.reader.view.linearmenu.a getMenu() {
        if (getActivity() == null) {
            return null;
        }
        this.mReaderMenu = new b(getActivity());
        this.mReaderMenu.a(0, "刷新", null);
        this.mReaderMenu.a(new com.qq.reader.view.linearmenu.a.b(this) {
            final /* synthetic */ NativeListenZoneTabFragment a;

            {
                this.a = r1;
            }

            public boolean a(int i, Bundle bundle) {
                this.a.mReaderMenu.cancel();
                return this.a.menuSelected(i, bundle);
            }
        });
        this.mReaderMenu.a(new OnCancelListener(this) {
            final /* synthetic */ NativeListenZoneTabFragment a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                if (this.a.getActivity() != null) {
                    this.a.getActivity().getWindow().closeAllPanels();
                }
            }
        });
        return this.mReaderMenu;
    }

    protected boolean menuSelected(int i, Bundle bundle) {
        Fragment curFragment = getCurFragment();
        switch (i) {
            case 0:
                if (curFragment instanceof WebBrowserFragment) {
                    ((WebBrowserFragment) curFragment).refresh();
                }
                j.a(1, 2);
                return true;
            default:
                return false;
        }
    }

    public boolean isNeedStatistics() {
        return false;
    }

    public String getStatisticsPageName() {
        return null;
    }

    protected Fragment getCurFragment() {
        return this.mAdapter.e(this.mViewPager.getCurrentItem());
    }

    protected void upToPreUI() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }
}

package com.qq.reader.module.bookstore.qnative.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Color;
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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.MainActivity;
import com.qq.reader.activity.ReaderBaseFragment;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.widget.PagerSlidingTabStrip.c;
import com.qq.reader.module.bookshelf.j;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforOther;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qweb.TabInfo;
import com.qq.reader.module.bookstore.qweb.WebAdViewPager;
import com.qq.reader.module.bookstore.qweb.fragment.BaseFragment;
import com.qq.reader.module.bookstore.qweb.fragment.WebBrowserFragment;
import com.qq.reader.module.comic.activity.NativeBookLibraryActivity;
import com.qq.reader.view.LinearListView;
import com.qq.reader.view.linearmenu.b;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class NativeBookStoreConfigStackTabFragment extends ReaderBaseFragment implements e, com.qq.reader.module.bookstore.qnative.c.a {
    public static final String CATEGORY_TYPE_AUDIO = "5";
    public static final String CATEGORY_TYPE_BOY = "1";
    public static final String CATEGORY_TYPE_COMIC = "4";
    public static final String CATEGORY_TYPE_GIRL = "2";
    public static final String CATEGORY_TYPE_PUBLIC = "3";
    public static final String INTETNT_CATEGORY_TYPE_KEY = "categoryType";
    public static final int MENU_ID_EXIT = 3;
    public static final int MENU_ID_REFRESH = 0;
    private static final String[] PAGE_CATEGORY = new String[]{"1", "3", "2", "4", "5"};
    private static final String[] PAGE_TITLES = new String[]{"男生", "出版", "女生", "漫画", "音频"};
    private String[] PAGE_LOCAL_NAME = new String[]{"BookLibCategory_boy", "BookLibCategory_publish", "BookLibCategory_girl", "BookLibCategory_comic", "BookLibCategory_audio"};
    public a mAdapter;
    protected View mCommonTabShadow;
    protected View mCommon_tab_tabs_layout;
    private int mGendar = 1;
    private BroadcastReceiver mGotAvatarBroadcastReceiver = new BroadcastReceiver(this) {
        final /* synthetic */ NativeBookStoreConfigStackTabFragment a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (com.qq.reader.common.c.a.ct.equals(intent.getAction())) {
                j.b(this.a.mTitleBarAvatarView);
            }
        }
    };
    private TextView mLefttab;
    private LinearListView mLinearListView;
    private b mReaderMenu;
    private TextView mRighttab;
    private View mRootView;
    private BroadcastReceiver mSwitchUserLikeReceiver = new BroadcastReceiver(this) {
        final /* synthetic */ NativeBookStoreConfigStackTabFragment a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (com.qq.reader.common.c.a.cK.equals(intent.getAction())) {
                this.a.updateUserReadingPreference();
            }
        }
    };
    private BaseAdapter mTabInfoAdapter = new BaseAdapter(this) {
        final /* synthetic */ NativeBookStoreConfigStackTabFragment a;

        {
            this.a = r1;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.a.getLayoutInflater(null).inflate(R.layout.localstore_card_author_left, viewGroup, false);
            }
            ((TextView) view.findViewById(R.id.author_tab_title)).setText(NativeBookStoreConfigStackTabFragment.PAGE_TITLES[i]);
            return view;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public Object getItem(int i) {
            return NativeBookStoreConfigStackTabFragment.PAGE_TITLES[i];
        }

        public int getCount() {
            return NativeBookStoreConfigStackTabFragment.PAGE_TITLES.length;
        }
    };
    protected ArrayList<TabInfo> mTabList = new ArrayList();
    private View mTitleBarAvatarView;
    protected ArrayList<View> mTitlelist = new ArrayList();
    protected WebAdViewPager mViewPager;
    private BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver(this) {
        final /* synthetic */ NativeBookStoreConfigStackTabFragment a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (com.qq.reader.common.c.a.cs.equalsIgnoreCase(intent.getAction())) {
                this.a.mHandler.sendEmptyMessage(8012);
            }
        }
    };

    protected class a extends com.qq.reader.module.bookstore.qweb.a implements c {
        final /* synthetic */ NativeBookStoreConfigStackTabFragment a;

        public a(NativeBookStoreConfigStackTabFragment nativeBookStoreConfigStackTabFragment, k kVar) {
            this.a = nativeBookStoreConfigStackTabFragment;
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

    public static int getCategoryShowIndex(String str) {
        for (int i = 0; i < PAGE_CATEGORY.length; i++) {
            if (PAGE_CATEGORY[i].equals(str)) {
                return i;
            }
        }
        return -1;
    }

    private void getStackWithPreference() {
        this.mGendar = d.aQ(getApplicationContext());
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.book_stack_layout, viewGroup, false);
        }
        return this.mRootView;
    }

    public void switchCategoryByType(String str) {
        if (PAGE_CATEGORY != null) {
            int categoryIndex = getCategoryIndex(str);
            if (categoryIndex >= 0 && this.mLinearListView != null) {
                this.mLinearListView.b(categoryIndex).performClick();
            }
        }
    }

    private int getCategoryIndex(String str) {
        for (int i = 0; i < PAGE_CATEGORY.length; i++) {
            if (PAGE_CATEGORY[i].equals(str)) {
                return i;
            }
        }
        return 0;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (!("Meizu_M040".equals(com.qq.reader.common.c.a.E) || getActivity() == null)) {
            getActivity().getWindow().addFlags(SigType.WLOGIN_PF);
        }
        initBaseTabUI();
        init();
        setIsShowNightMask(false);
        StatisticsManager.a().a(1).a("pagename", this.PAGE_LOCAL_NAME[0]).c();
        if (getActivity() != null) {
            getActivity().registerReceiver(this.mGotAvatarBroadcastReceiver, new IntentFilter(com.qq.reader.common.c.a.ct));
            getActivity().registerReceiver(this.mSwitchUserLikeReceiver, new IntentFilter(com.qq.reader.common.c.a.cK));
        }
    }

    private void initBaseTabUI() {
        if (this.mRootView != null) {
            this.mCommon_tab_tabs_layout = this.mRootView.findViewById(R.id.common_titler);
            this.mCommonTabShadow = this.mRootView.findViewById(R.id.common_tab_top_shadow);
            this.mViewPager = (WebAdViewPager) this.mRootView.findViewById(R.id.common_tab_viewpager);
            this.mAdapter = new a(this, getChildFragmentManager());
            this.mViewPager.setCanHorizontalScroll(false);
            this.mViewPager.setAdapter(this.mAdapter);
            this.mViewPager.setShouldIntercept(new com.qq.reader.module.bookstore.qweb.WebAdViewPager.a(this) {
                final /* synthetic */ NativeBookStoreConfigStackTabFragment a;

                {
                    this.a = r1;
                }

                public boolean a() {
                    return false;
                }

                public void b() {
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
        com.qq.reader.cservice.adv.b.a(2, true);
        this.mHandler.post(new Runnable(this) {
            final /* synthetic */ NativeBookStoreConfigStackTabFragment a;

            class AnonymousClass1 extends ReaderIOTask {
                final /* synthetic */ AnonymousClass14 this$1;
                final /* synthetic */ int val$position;
                final /* synthetic */ View val$view;

                AnonymousClass1(AnonymousClass14 anonymousClass14, View view, int i) {
                    this.this$1 = anonymousClass14;
                    this.val$view = view;
                    this.val$position = i;
                }

                public void run() {
                    super.run();
                    d.a(this.val$view.getContext(), this.val$position);
                    Map hashMap;
                    if (NativeBookStoreConfigStackTabFragment.PAGE_CATEGORY[this.val$position].equals("4")) {
                        hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, NativeBookStoreConfigStackTabFragment.PAGE_CATEGORY[this.val$position]);
                        i.a("event_F298", hashMap, this.val$view.getContext());
                    } else if (NativeBookStoreConfigStackTabFragment.PAGE_CATEGORY[this.val$position].equals("5")) {
                        i.a("event_B283", null, this.val$view.getContext());
                    } else {
                        hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, NativeBookStoreConfigStackTabFragment.PAGE_CATEGORY[this.val$position]);
                        i.a("event_F294", hashMap, this.val$view.getContext());
                    }
                }
            }

            {
                this.a = r1;
            }

            public void run() {
                j.b(this.a.mTitleBarAvatarView);
                j.a(this.a.mTitleBarAvatarView);
            }
        });
        if (this.mViewPager != null && this.mViewPager.getCurrentItem() == 4) {
            i.a("event_B283", null, getApplicationContext());
        }
    }

    public void IOnPause() {
        try {
            if (getActivity() != null) {
                getActivity().unregisterReceiver(this.myBroadcastReceiver);
            }
            com.qq.reader.cservice.adv.b.a(2, false);
        } catch (Exception e) {
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (mAdvDialog != null) {
            mAdvDialog = null;
        }
        if (getActivity() != null) {
            getActivity().unregisterReceiver(this.mGotAvatarBroadcastReceiver);
            getActivity().unregisterReceiver(this.mSwitchUserLikeReceiver);
        }
    }

    private void initTabList() {
        getStackWithPreference();
        this.mTabList.clear();
        for (int i = 0; i < this.PAGE_LOCAL_NAME.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putString("KEY_JUMP_PAGENAME", this.PAGE_LOCAL_NAME[i]);
            bundle.putLong("KEY_PAGEINDEX", -1);
            bundle.putString("URL_BUILD_PERE_CATEGORY", PAGE_CATEGORY[i]);
            HashMap hashMap = new HashMap();
            hashMap.put("key_data", bundle);
            this.mTabList.add(i, new TabInfo(NativePageFragmentforOther.class, PAGE_TITLES[i], PAGE_TITLES[i], hashMap));
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
                j.a(this.mTitleBarAvatarView);
                return true;
            case 8000009:
                try {
                    ((NativePageFragmentforOther) getCurFragment()).refreshWithoutPulldown(true);
                } catch (Exception e) {
                }
                return true;
            case 8009999:
                refreshStackWithUserLike();
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    private void refreshStackWithUserLike() {
        updateUserReadingPreference();
    }

    private void init() {
        if (this.mRootView != null) {
            initTabList();
            if (getActivity() != null && (getActivity() instanceof MainActivity)) {
                LayoutParams layoutParams = (LayoutParams) this.mViewPager.getLayoutParams();
                layoutParams.bottomMargin = getApplicationContext().getResources().getDimensionPixelSize(R.dimen.main_tab_height);
                this.mViewPager.setLayoutParams(layoutParams);
            }
            int size = this.mTabList.size();
            if (size >= 1) {
                this.mCommon_tab_tabs_layout.setVisibility(0);
            }
            this.mViewPager.setOffscreenPageLimit(size);
            ImageView imageView = (ImageView) this.mRootView.findViewById(R.id.profile_header_right_image);
            imageView.setVisibility(0);
            imageView.setImageResource(R.drawable.titlebar_icon_search_selector);
            imageView.setBackgroundDrawable(null);
            imageView.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
                final /* synthetic */ NativeBookStoreConfigStackTabFragment a;

                {
                    this.a = r1;
                }

                public void a(View view) {
                    if (this.a.getActivity() != null) {
                        StatisticsManager.a().a(7).c();
                        com.qq.reader.common.monitor.j.a(16, 2);
                        o.b(this.a.getActivity(), "", "3");
                    }
                }
            });
            notifyAdapterChanged();
            imageView = (ImageView) this.mRootView.findViewById(R.id.profile_header_left_back);
            this.mTitleBarAvatarView = this.mRootView.findViewById(R.id.title_bar_left_view);
            if (getActivity() == null || !(getActivity() instanceof NativeBookLibraryActivity)) {
                imageView.setVisibility(8);
                this.mTitleBarAvatarView.setVisibility(0);
                this.mTitleBarAvatarView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ NativeBookStoreConfigStackTabFragment a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        if (this.a.getActivity() != null) {
                            ((MainActivity) this.a.getActivity()).a().d(3);
                            d.d(this.a.getApplicationContext(), false);
                        }
                    }
                });
            } else {
                imageView.setVisibility(0);
                this.mTitleBarAvatarView.setVisibility(8);
                imageView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ NativeBookStoreConfigStackTabFragment a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.getActivity().finish();
                    }
                });
            }
            ((TextView) this.mRootView.findViewById(R.id.profile_header_title)).setText("书库");
            this.mLinearListView = (LinearListView) this.mRootView.findViewById(R.id.left_tab_list);
            this.mLinearListView.setAdapter(this.mTabInfoAdapter);
            this.mLinearListView.setOnItemClickListener(new LinearListView.b(this) {
                int a = -1;
                final /* synthetic */ NativeBookStoreConfigStackTabFragment b;

                {
                    this.b = r2;
                }

                public void a(LinearListView linearListView, View view, int i, long j) {
                    if (i != this.a) {
                        this.b.mViewPager.setCurrentItem(i, false);
                        a(view);
                        if (this.a >= 0) {
                            b(this.b.mLinearListView.b(this.a));
                        }
                        this.a = i;
                        g.a().a(new AnonymousClass1(this, view, i));
                        this.b.checkIsNeedRefresh(view);
                    }
                }

                private void a(View view) {
                    ap.a(view, R.id.author_tab_layout).setBackgroundResource(R.color.concept_card_bg);
                    ((TextView) ap.a(view, R.id.author_tab_title)).setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c301));
                    ap.a(view, R.id.author_tab_line).setVisibility(0);
                    ap.a(view, R.id.author_divider_line).setVisibility(8);
                }

                private void b(View view) {
                    ap.a(view, R.id.author_tab_layout).setBackgroundResource(R.color.concept_divider_bg);
                    ((TextView) ap.a(view, R.id.author_tab_title)).setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.common_textcolor_primary));
                    ap.a(view, R.id.author_tab_line).setVisibility(8);
                    ap.a(view, R.id.author_divider_line).setVisibility(0);
                }
            });
            this.mLinearListView.post(new Runnable(this) {
                final /* synthetic */ NativeBookStoreConfigStackTabFragment a;

                {
                    this.a = r1;
                }

                public void run() {
                    int i;
                    int a = d.a(this.a.mLinearListView.getContext());
                    if (this.a.getActivity() != null && (this.a.getActivity() instanceof NativeBookLibraryActivity)) {
                        a = this.a.getCategoryIndex(((NativeBookLibraryActivity) this.a.getActivity()).a());
                    }
                    if (a < 0) {
                        i = 0;
                        while (i < NativeBookStoreConfigStackTabFragment.PAGE_CATEGORY.length) {
                            if (NativeBookStoreConfigStackTabFragment.PAGE_CATEGORY[i].equals(String.valueOf(this.a.mGendar))) {
                                break;
                            }
                            i++;
                        }
                    }
                    i = a;
                    if (this.a.mLinearListView.b(i) != null) {
                        this.a.mLinearListView.b(i).performClick();
                    }
                }
            });
        }
    }

    private void checkIsNeedRefresh(View view) {
        long j = 0;
        if (view.getTag(R.string.key_id) != null) {
            j = ((Long) view.getTag(R.string.key_id)).longValue();
        }
        if ((System.currentTimeMillis() - j) / TimeUnit.MINUTES.toMillis(1) > 10) {
            if (getCurFragment() != null) {
                ((NativePageFragmentforOther) getCurFragment()).refreshWithoutPulldown(true);
            }
            view.setTag(R.string.key_id, Long.valueOf(System.currentTimeMillis()));
        }
    }

    private void updateUserReadingPreference() {
        this.mLinearListView.post(new Runnable(this) {
            final /* synthetic */ NativeBookStoreConfigStackTabFragment a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.mGendar = d.aQ(this.a.getApplicationContext());
                int i = 0;
                while (i < NativeBookStoreConfigStackTabFragment.PAGE_CATEGORY.length) {
                    if (NativeBookStoreConfigStackTabFragment.PAGE_CATEGORY[i].equals(String.valueOf(this.a.mGendar))) {
                        break;
                    }
                    i++;
                }
                i = 0;
                this.a.mLinearListView.b(i).performClick();
            }
        });
    }

    protected void initTabList(Bundle bundle) {
    }

    protected String getTopBarTitle() {
        return null;
    }

    public void notifyAdapterChanged() {
        this.mViewPager.setAdapter(this.mAdapter);
        this.mAdapter.c();
        setTabShadow();
    }

    private void setTabShadow() {
        if (this.mTabList == null || this.mTabList.size() <= 1) {
            this.mCommonTabShadow.setVisibility(8);
        } else {
            this.mCommonTabShadow.setVisibility(0);
        }
    }

    public void onPageScrollStateChanged(int i) {
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
                com.qq.reader.common.monitor.j.a(105, 2);
                i.a("event_C106", null, getApplicationContext());
                StatisticsManager.a().a("event_C106", null);
            } catch (Exception e) {
                return;
            }
        } else if (i == 0) {
            com.qq.reader.common.monitor.j.a(104, 2);
            i.a("event_C105", null, getApplicationContext());
            StatisticsManager.a().a("event_C105", null);
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
            if (getActivity() != null && (getActivity() instanceof MainActivity)) {
                com.qq.reader.common.monitor.j.a(21, 2);
                i.a("event_C22", null, getApplicationContext());
                ((MainActivity) getActivity()).a("bookstand_tab");
            } else if (getActivity() != null && (getActivity() instanceof NativeBookLibraryActivity)) {
                getActivity().finish();
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
            final /* synthetic */ NativeBookStoreConfigStackTabFragment a;

            {
                this.a = r1;
            }

            public boolean a(int i, Bundle bundle) {
                this.a.mReaderMenu.cancel();
                return this.a.menuSelected(i, bundle);
            }
        });
        this.mReaderMenu.a(new OnCancelListener(this) {
            final /* synthetic */ NativeBookStoreConfigStackTabFragment a;

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
                com.qq.reader.common.monitor.j.a(1, 2);
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

    public void onAudioFloatingStateChange(int i, long j, boolean z, String str) {
        if (getActivity() instanceof MainActivity) {
            super.onAudioFloatingStateChange(i, j, z, str);
        }
    }
}

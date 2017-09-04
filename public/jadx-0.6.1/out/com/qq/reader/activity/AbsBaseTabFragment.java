package com.qq.reader.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.k;
import android.support.v4.view.ViewPager.e;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.widget.PagerSlidingTabStrip;
import com.qq.reader.common.widget.PagerSlidingTabStrip.c;
import com.qq.reader.module.bookstore.qweb.TabInfo;
import com.qq.reader.module.bookstore.qweb.WebAdViewPager;
import com.qq.reader.module.bookstore.qweb.fragment.BaseFragment;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public abstract class AbsBaseTabFragment extends ReaderBaseFragment {
    public a mAdapter;
    protected View mCommonTabShadow;
    protected View mCommon_tab_tabs_layout;
    protected PagerSlidingTabStrip mPagerSlidingTabStrip;
    protected View mRootView;
    protected ArrayList<TabInfo> mTabList = new ArrayList();
    protected TextView mTitleView;
    protected ArrayList<View> mTitlelist = new ArrayList();
    protected ImageView mTopBarLeftView;
    protected WebAdViewPager mViewPager;
    protected View pagerSlideTabLine;

    protected class a extends com.qq.reader.module.bookstore.qweb.a implements c {
        final /* synthetic */ AbsBaseTabFragment a;

        public a(AbsBaseTabFragment absBaseTabFragment, k kVar) {
            this.a = absBaseTabFragment;
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

    protected abstract int getLayoutResourceId();

    protected abstract String getTopBarTitle();

    protected abstract void initTabList(Bundle bundle);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(getLayoutResourceId(), null);
        }
        return this.mRootView;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (!("Meizu_M040".equals(com.qq.reader.common.c.a.E) || getActivity() == null)) {
            getActivity().getWindow().addFlags(SigType.WLOGIN_PF);
        }
        initBaseTabUI(bundle);
    }

    protected void upToPreUI() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    private void initBaseTabUI(Bundle bundle) {
        if (this.mRootView != null) {
            this.mTitleView = (TextView) this.mRootView.findViewById(R.id.profile_header_title);
            this.mTopBarLeftView = (ImageView) this.mRootView.findViewById(R.id.profile_header_left_back);
            if (this.mTopBarLeftView != null) {
                this.mTopBarLeftView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ AbsBaseTabFragment a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.upToPreUI();
                    }
                });
            }
            this.mCommon_tab_tabs_layout = this.mRootView.findViewById(R.id.common_tab_tabs_layout);
            this.mPagerSlidingTabStrip = (PagerSlidingTabStrip) this.mRootView.findViewById(R.id.common_tab_tabs);
            this.mPagerSlidingTabStrip.setShouldExpand(true);
            this.pagerSlideTabLine = this.mRootView.findViewById(R.id.common_tab__line);
            this.mCommonTabShadow = this.mRootView.findViewById(R.id.common_tab_top_shadow);
            this.mViewPager = (WebAdViewPager) this.mRootView.findViewById(R.id.common_tab_viewpager);
            initTabList(bundle);
            this.mTitleView.setText(getTopBarTitle());
            if (this.mTabList != null && this.mTabList.size() > 0) {
                this.mCommon_tab_tabs_layout.setVisibility(0);
                int size = this.mTabList.size();
                int i = com.qq.reader.common.c.a.bU / size;
                int i2 = i / 8;
                if (size == 2 || size == 3) {
                    i2 = (i - getResources().getDimensionPixelOffset(R.dimen.common_dp_80)) / 2;
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
                final /* synthetic */ AbsBaseTabFragment a;

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

    @Deprecated
    public void setSelectTitle(int i, int i2) {
        if (iSsetCurrentTitle()) {
            View view = (View) this.mTitlelist.get(i);
            if (view == null) {
                return;
            }
            if (i == i2) {
                ((TextView) view.findViewById(R.id.tab_text)).setTextColor(getResources().getColor(R.color.textcolor_green));
            } else {
                ((TextView) view.findViewById(R.id.tab_text)).setTextColor(getResources().getColor(R.color.textcolor_black));
            }
        }
    }

    public boolean iSsetCurrentTitle() {
        return false;
    }

    public View getLocalTitleView(int i) {
        if (getActivity() == null) {
            return null;
        }
        TabInfo tabInfo = (TabInfo) this.mTabList.get(i);
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.profileaccount_tab_item, null);
        TextView textView = (TextView) inflate.findViewById(R.id.tab_text);
        textView.setText(tabInfo.title);
        if (this.mTitlelist.size() > i) {
            this.mTitlelist.set(i, inflate);
        } else {
            while (this.mTitlelist.size() <= i) {
                this.mTitlelist.add(null);
            }
            this.mTitlelist.set(i, inflate);
        }
        if (iSsetCurrentTitle()) {
            if (this.mViewPager.getCurrentItem() == i) {
                textView.setTextColor(getResources().getColor(R.color.textcolor_blue));
            } else {
                textView.setTextColor(getResources().getColor(R.color.textcolor_black));
            }
        }
        return inflate;
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

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable("adapter", this.mAdapter.b());
    }

    public void onRestoreInstanceState(Bundle bundle) {
        this.mAdapter.a(bundle.getParcelable("adapter"), null);
    }

    protected Fragment getCurFragment() {
        return this.mAdapter.e(this.mViewPager.getCurrentItem());
    }
}

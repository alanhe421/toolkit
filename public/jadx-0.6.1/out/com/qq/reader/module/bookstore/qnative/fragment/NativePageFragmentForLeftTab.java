package com.qq.reader.module.bookstore.qnative.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment.InstantiationException;
import android.support.v4.app.k;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreRankBActivity;
import com.qq.reader.module.bookstore.qnative.item.ae;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.d;
import com.qq.reader.module.bookstore.qnative.page.e;
import com.qq.reader.module.bookstore.qnative.page.impl.av;
import com.qq.reader.module.bookstore.qweb.WebAdViewPager;
import com.qq.reader.module.bookstore.qweb.fragment.BaseFragment;
import com.qq.reader.module.feed.card.view.HallOfFameTabItemView;
import com.qq.reader.view.LinearListView;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NativePageFragmentForLeftTab extends BaseFragment implements com.qq.reader.module.bookstore.qnative.c.a {
    private int currentItem = 0;
    private Bundle enterBundle = new Bundle();
    private String mActionId;
    private NativeBookStoreRankBActivity mActivity;
    protected View mFailedLayout = null;
    protected b mHoldPage = null;
    private int mLastPosition = 0;
    protected View mLoadingProgress = null;
    private List<d> mPageRankInfos = new ArrayList();
    private SparseArray<String> mPositionMaps = new SparseArray();
    private a mRankBoardDetailAdapter;
    protected View mRootView;
    LinearListView.b mTabCLickListener = new LinearListView.b(this) {
        final /* synthetic */ NativePageFragmentForLeftTab a;

        {
            this.a = r1;
        }

        public void a(LinearListView linearListView, View view, int i, long j) {
            String str;
            this.a.mViewPager.setCurrentItem(i, false);
            this.a.currentItem = i;
            ae aeVar = (ae) this.a.mTabInfoAdapter.getItem(i);
            ((HallOfFameTabItemView) this.a.mTabListView.b(this.a.mLastPosition).getTag()).b();
            this.a.mLastPosition = i;
            ((HallOfFameTabItemView) this.a.mTabListView.b(i).getTag()).a();
            String valueOf = String.valueOf(aeVar.b());
            if (TextUtils.isEmpty((CharSequence) this.a.mPositionMaps.get(i))) {
                str = valueOf;
            } else {
                str = (String) this.a.mPositionMaps.get(i);
            }
            d access$700 = this.a.getPageInfo(str);
            this.a.loadCurTab(i);
            Message obtainMessage = this.a.mActivity.getHandler().obtainMessage(1002);
            obtainMessage.obj = access$700;
            obtainMessage.arg1 = 1;
            this.a.mActivity.getHandler().sendMessage(obtainMessage);
        }
    };
    private int mTabCount;
    private BaseAdapter mTabInfoAdapter = new BaseAdapter(this) {
        final /* synthetic */ NativePageFragmentForLeftTab a;

        {
            this.a = r1;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate;
            if (view == null) {
                try {
                    inflate = LayoutInflater.from(this.a.getContext()).inflate(R.layout.localstore_card_author_left, viewGroup, false);
                } catch (Exception e) {
                    return null;
                }
            }
            inflate = view;
            HallOfFameTabItemView hallOfFameTabItemView = new HallOfFameTabItemView(this.a.getContext(), null, inflate);
            hallOfFameTabItemView.setTabTitle(((ae) ((av) this.a.mHoldPage).x().get(i)).a());
            inflate.setTag(hallOfFameTabItemView);
            return inflate;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public Object getItem(int i) {
            return ((av) this.a.mHoldPage).x().get(i);
        }

        public int getCount() {
            this.a.mTabCount = ((av) this.a.mHoldPage).x().size();
            return this.a.mTabCount;
        }
    };
    private View mTabListContainerView;
    private LinearListView mTabListView;
    private String mUserPre;
    private WebAdViewPager mViewPager;

    private class a extends com.qq.reader.module.bookstore.qweb.a {
        final /* synthetic */ NativePageFragmentForLeftTab a;

        public a(NativePageFragmentForLeftTab nativePageFragmentForLeftTab, k kVar) {
            this.a = nativePageFragmentForLeftTab;
            super(kVar);
        }

        public int a() {
            return this.a.mTabCount;
        }

        public BaseFragment d(int i) {
            return c(i);
        }

        public int a(Object obj) {
            return super.a(obj);
        }

        private BaseFragment c(final int i) {
            BaseFragment baseFragment;
            InstantiationException e;
            IllegalAccessException e2;
            InstantiationException e3;
            List x = ((av) this.a.mHoldPage).x();
            if (x != null && x.size() > 0) {
                ae aeVar = (ae) x.get(i);
                if (aeVar != null) {
                    try {
                        baseFragment = (NativePageFragmentforOther) NativePageFramentforTenYearsRank.class.newInstance();
                        try {
                            Bundle bundle = new Bundle();
                            bundle.putString("KEY_ACTIONID", String.valueOf(aeVar.b()));
                            bundle.putString("KEY_ACTIONTAG", aeVar.e());
                            if ("5".equalsIgnoreCase(this.a.mUserPre)) {
                                bundle.putString("KEY_JUMP_PAGENAME", "BookLibTopRank_audio");
                            } else {
                                bundle.putString("KEY_JUMP_PAGENAME", "rankboard_detail");
                            }
                            HashMap hashMap = new HashMap();
                            hashMap.put("key_data", bundle);
                            baseFragment.setHashArguments(hashMap);
                            baseFragment.setPageRankInfoListener(new e(this) {
                                final /* synthetic */ a b;

                                public void a(BaseFragment baseFragment, d dVar) {
                                    if (dVar != null) {
                                        if (this.b.a.getPageInfo(dVar.d()) == null) {
                                            this.b.a.mPageRankInfos.add(dVar);
                                        }
                                        if (this.b.a.currentItem == i) {
                                            Message obtainMessage = this.b.a.mActivity.getHandler().obtainMessage(1002);
                                            obtainMessage.arg1 = 1;
                                            obtainMessage.obj = dVar;
                                            this.b.a.mActivity.getHandler().sendMessage(obtainMessage);
                                        }
                                        this.b.a.mPositionMaps.put(i, dVar.d());
                                    }
                                }
                            });
                            return baseFragment;
                        } catch (InstantiationException e4) {
                            e = e4;
                            e.printStackTrace();
                            return baseFragment;
                        } catch (IllegalAccessException e5) {
                            e2 = e5;
                            e2.printStackTrace();
                            return baseFragment;
                        } catch (InstantiationException e6) {
                            e3 = e6;
                            e3.printStackTrace();
                            return baseFragment;
                        }
                    } catch (InstantiationException e7) {
                        e = e7;
                        baseFragment = null;
                        e.printStackTrace();
                        return baseFragment;
                    } catch (IllegalAccessException e8) {
                        e2 = e8;
                        baseFragment = null;
                        e2.printStackTrace();
                        return baseFragment;
                    } catch (InstantiationException e9) {
                        e3 = e9;
                        baseFragment = null;
                        e3.printStackTrace();
                        return baseFragment;
                    }
                }
            }
            return null;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.rankboard_tab_layout, null);
        }
        this.enterBundle.putString("KEY_JUMP_PAGENAME", (String) getHashArguments().get("KEY_JUMP_PAGENAME"));
        this.enterBundle.putString("KEY_ACTIONID", (String) getHashArguments().get("KEY_ACTIONID"));
        this.enterBundle.putString("URL_BUILD_PERE_RANK", (String) getHashArguments().get("URL_BUILD_PERE_RANK"));
        this.mUserPre = (String) getHashArguments().get("URL_BUILD_PERE_RANK");
        this.mActionId = (String) getHashArguments().get("KEY_ACTIONID");
        this.mActivity = (NativeBookStoreRankBActivity) getActivity();
        initView();
        loadPage();
        this.mRankBoardDetailAdapter = new a(this, getChildFragmentManager());
        this.mViewPager = (WebAdViewPager) this.mRootView.findViewById(R.id.haffoffame_author_list_author);
        this.mViewPager.setCanHorizontalScroll(false);
        this.mViewPager.setShouldIntercept(new com.qq.reader.module.bookstore.qweb.WebAdViewPager.a(this) {
            final /* synthetic */ NativePageFragmentForLeftTab a;

            {
                this.a = r1;
            }

            public boolean a() {
                return false;
            }

            public void b() {
            }
        });
        return this.mRootView;
    }

    public void onPreLoad() {
    }

    public void onLoading() {
    }

    public void onLoadFinished() {
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 500000:
            case 500001:
                if (message.obj != null) {
                    this.mHoldPage.a((b) message.obj);
                }
                if (this.mTabInfoAdapter.getCount() <= 0) {
                    getHandler().sendEmptyMessage(500004);
                    return true;
                }
                if (this.mTabListContainerView.getVisibility() != 0) {
                    this.mTabListContainerView.setVisibility(0);
                }
                this.mTabListView.setAdapter(this.mTabInfoAdapter);
                this.mViewPager.setAdapter(this.mRankBoardDetailAdapter);
                this.mViewPager.setOffscreenPageLimit(2);
                this.currentItem = getIndexOfActionId(((av) this.mHoldPage).x());
                this.mLastPosition = this.currentItem;
                if (this.mTabListView.b(this.currentItem) != null) {
                    ((HallOfFameTabItemView) this.mTabListView.b(this.currentItem).getTag()).a();
                    this.mViewPager.setCurrentItem(this.currentItem);
                    if (this.currentItem == 0) {
                        Map hashMap = new HashMap();
                        hashMap.put("pre", this.mUserPre);
                        i.a("event_B247", hashMap, this.mActivity);
                    }
                }
                hideLoadingPage();
                return true;
            case 500004:
                showFailedPage();
                return true;
            case 10000508:
                if (this.mTabInfoAdapter != null) {
                    this.mTabInfoAdapter.notifyDataSetChanged();
                }
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    private int getIndexOfActionId(List<ae> list) {
        if (list == null || TextUtils.isEmpty(this.mActionId)) {
            return 0;
        }
        for (int i = 0; i < list.size(); i++) {
            if (this.mActionId.equals(String.valueOf(((ae) list.get(i)).b()))) {
                return i;
            }
        }
        return 0;
    }

    public void onDestroy() {
        super.onDestroy();
        com.qq.reader.module.bookstore.qnative.d.b().a(this.mHoldPage);
        if (this.mHoldPage != null) {
            this.mHoldPage.w();
        }
    }

    @TargetApi(16)
    public void initView() {
        this.mLoadingProgress = this.mRootView.findViewById(R.id.loading_layout);
        this.mFailedLayout = this.mRootView.findViewById(R.id.loading_failed_layout);
        if (this.mFailedLayout != null) {
            this.mFailedLayout.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ NativePageFragmentForLeftTab a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.reLoadData();
                }
            });
            this.mTabListView = (LinearListView) this.mRootView.findViewById(R.id.leaderboard_list);
            this.mTabListView.setOnItemClickListener(this.mTabCLickListener);
            if (this.mFailedLayout != null) {
                this.mFailedLayout.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ NativePageFragmentForLeftTab a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.loadPage();
                    }
                });
            }
        }
        this.mTabListContainerView = this.mRootView.findViewById(R.id.haffoffame_tab_list_outer);
    }

    protected void showFailedPage() {
        this.mLoadingProgress.setVisibility(8);
        this.mFailedLayout.setVisibility(0);
    }

    public void reLoadData() {
        this.mHoldPage.a(1000);
        tryObtainDataWithNet(true, false);
    }

    protected void tryObtainDataWithNet(boolean z, boolean z2) {
        boolean a = com.qq.reader.module.bookstore.qnative.d.b().a(getContext(), this.mHoldPage, this.mHandler, z);
        if (!z2) {
            if (a) {
                hideLoadingPage();
            } else {
                showLoadingPage();
            }
        }
    }

    protected void hideLoadingPage() {
        this.mLoadingProgress.setVisibility(8);
    }

    private void loadCurTab(int i) {
        ae aeVar = (ae) ((av) this.mHoldPage).x().get(i);
        Bundle bundle = new Bundle();
        bundle.putString("KEY_ACTIONTAG", aeVar.e());
        bundle.putString("KEY_ACTIONID", String.valueOf(aeVar.b()));
        if ("5".equalsIgnoreCase(this.mUserPre)) {
            bundle.putString("KEY_JUMP_PAGENAME", "BookLibTopRank_audio");
        } else {
            bundle.putString("KEY_JUMP_PAGENAME", "rankboard_detail");
        }
        Map hashMap = new HashMap();
        hashMap.put("rankboard", "abtest_B");
        hashMap.put("actionId", String.valueOf(aeVar.b()));
        hashMap.put("pre", this.mUserPre);
        hashMap.put("defaultPre", this.mUserPre);
        i.a("event_B226", hashMap, getContext());
        if ("4".equalsIgnoreCase(this.mUserPre)) {
            hashMap = new HashMap();
            hashMap.put("actionId", String.valueOf(aeVar.b()));
            i.a("event_F307", hashMap, getContext());
        }
        Map hashMap2 = new HashMap();
        hashMap2.put("pre", this.mUserPre);
        i.a("event_B247", hashMap2, getContext());
    }

    private d getPageInfo(String str) {
        for (d dVar : this.mPageRankInfos) {
            if (dVar.d().equalsIgnoreCase(str)) {
                return dVar;
            }
        }
        return null;
    }

    private void loadPage() {
        this.mHoldPage = com.qq.reader.module.bookstore.qnative.e.a().a(this.enterBundle, this);
        tryObtainDataWithNet(false, false);
    }

    protected void showLoadingPage() {
        this.mFailedLayout.setVisibility(8);
        this.mLoadingProgress.setVisibility(0);
    }

    public void doFunction(Bundle bundle) {
    }

    public Activity getFromActivity() {
        return this.mActivity;
    }

    public void loadRankDetailPageData(String str, String str2) {
        if (((NativePageFramentforTenYearsRank) this.mRankBoardDetailAdapter.e(this.currentItem)) != null) {
            Bundle bundle = new Bundle();
            bundle.putString("KEY_ACTIONTAG", str2);
            bundle.putString("KEY_ACTIONID", str);
            if ("5".equalsIgnoreCase(this.mUserPre)) {
                bundle.putString("KEY_JUMP_PAGENAME", "BookLibTopRank_audio");
            } else {
                bundle.putString("KEY_JUMP_PAGENAME", "rankboard_detail");
            }
            ((NativePageFramentforTenYearsRank) this.mRankBoardDetailAdapter.e(this.currentItem)).loadPageWithFilter(bundle);
            Map hashMap = new HashMap();
            hashMap.put("pre", this.mUserPre);
            i.a("event_B247", hashMap, getContext());
        }
    }
}

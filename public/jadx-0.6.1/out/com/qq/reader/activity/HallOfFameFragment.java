package com.qq.reader.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment.InstantiationException;
import android.support.v4.app.k;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.c;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigBaseFragment;
import com.qq.reader.module.bookstore.qnative.e;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentAutoMoreforOther;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforOther;
import com.qq.reader.module.bookstore.qnative.item.r;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.impl.i;
import com.qq.reader.module.bookstore.qweb.WebAdViewPager;
import com.qq.reader.module.bookstore.qweb.fragment.BaseFragment;
import com.qq.reader.module.feed.card.view.HallOfFameTabItemView;
import com.qq.reader.view.LinearListView;
import com.qq.reader.view.LinearListView.b;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class HallOfFameFragment extends NativeBookStoreConfigBaseFragment implements com.qq.reader.module.bookstore.qnative.c.a {
    private int currentItem = 0;
    protected Bundle enterBundle = null;
    private BaseAdapter mAdapter = new BaseAdapter(this) {
        final /* synthetic */ HallOfFameFragment a;

        {
            this.a = r1;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(this.a.getApplicationContext()).inflate(R.layout.localstore_card_author_left, viewGroup, false);
            }
            HallOfFameTabItemView hallOfFameTabItemView = new HallOfFameTabItemView(this.a.mContext, null, view);
            hallOfFameTabItemView.setTabItemData((r) ((i) this.a.mHoldPage).x().get(i));
            view.setTag(hallOfFameTabItemView);
            return view;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public Object getItem(int i) {
            return ((i) this.a.mHoldPage).x().get(i);
        }

        public int getCount() {
            this.a.mTabCount = ((i) this.a.mHoldPage).x().size();
            return this.a.mTabCount;
        }
    };
    private a mAuthorPagerAdapter;
    private Context mContext;
    private int mLastPosition = 0;
    b mListener = new b(this) {
        final /* synthetic */ HallOfFameFragment a;

        {
            this.a = r1;
        }

        public void a(LinearListView linearListView, View view, int i, long j) {
            this.a.mViewPager.setCurrentItem(i, false);
            ((HallOfFameTabItemView) this.a.mTabListView.b(this.a.mLastPosition).getTag()).b();
            this.a.mLastPosition = i;
            ((HallOfFameTabItemView) this.a.mTabListView.b(i).getTag()).a();
        }
    };
    private View mRootView;
    private int mTabCount;
    private LinearListView mTabListView;
    private WebAdViewPager mViewPager;
    private TextView titleView = null;

    private class a extends com.qq.reader.module.bookstore.qweb.a {
        final /* synthetic */ HallOfFameFragment a;

        public a(HallOfFameFragment hallOfFameFragment, k kVar) {
            this.a = hallOfFameFragment;
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

        private BaseFragment c(int i) {
            BaseFragment baseFragment;
            InstantiationException e;
            IllegalAccessException e2;
            InstantiationException e3;
            r rVar = (r) ((i) this.a.mHoldPage).x().get(i);
            if (rVar == null) {
                return null;
            }
            Class cls;
            if (rVar.a().equalsIgnoreCase("more")) {
                cls = NativePageFragmentAutoMoreforOther.class;
            } else {
                cls = NativePageFragmentforOther.class;
            }
            try {
                baseFragment = (NativePageFragmentforOther) cls.newInstance();
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("KEY_ACTIONID", rVar.a());
                    bundle.putString("KEY_ACTIONTAG", this.a.getPref());
                    bundle.putString("KEY_JUMP_PAGENAME", "HallOfFamePage");
                    HashMap hashMap = new HashMap();
                    hashMap.put("key_data", bundle);
                    baseFragment.setHashArguments(hashMap);
                    return baseFragment;
                } catch (InstantiationException e4) {
                    e = e4;
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

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.localbookstore_halloffame_layout, null);
        }
        return this.mRootView;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.mRootView != null) {
            this.mContext = getApplicationContext();
            this.enterBundle = getActivity().getIntent().getExtras();
            this.currentItem = this.enterBundle.getInt("CURRENT_ITEM");
            this.mLastPosition = this.currentItem;
            if (this.mPullDownView != null) {
                this.mPullDownView.setOnRefreshListener(new SwipeRefreshLayout.b(this) {
                    final /* synthetic */ HallOfFameFragment a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        this.a.onUpdate();
                    }
                });
            }
            init();
            loadPage();
            this.mAuthorPagerAdapter = new a(this, getActivity().getSupportFragmentManager());
            this.mViewPager = (WebAdViewPager) this.mRootView.findViewById(R.id.haffoffame_author_list_author);
            this.mViewPager.setShouldIntercept(new com.qq.reader.module.bookstore.qweb.WebAdViewPager.a(this) {
                final /* synthetic */ HallOfFameFragment a;

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

    public void IOnResume() {
        super.IOnResume();
        checkIsNeedUpdate();
        StatisticsManager.a().b();
    }

    private void checkIsNeedUpdate() {
        if (this.mLastUpdateTime > 0) {
            long currentTimeMillis = System.currentTimeMillis() - this.mLastUpdateTime;
            if (currentTimeMillis >= 172800000) {
                forceReLoadData();
            } else if (currentTimeMillis >= 1800000) {
                reLoadData();
            }
        }
    }

    public void init() {
        this.mLoadingProgress = this.mRootView.findViewById(R.id.loading_layout);
        this.mFailedLayout = this.mRootView.findViewById(R.id.loading_failed_layout);
        if (this.mFailedLayout != null) {
            this.mFailedLayout.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ HallOfFameFragment a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.reLoadData();
                }
            });
        }
        if (this.mPullDownView != null) {
            this.mPullDownView.setOnRefreshListener(new SwipeRefreshLayout.b(this) {
                final /* synthetic */ HallOfFameFragment a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.onUpdate();
                }
            });
        }
        this.mTabListView = (LinearListView) this.mRootView.findViewById(R.id.haffoffame_tab_list);
        this.mTabListView.setOnItemClickListener(this.mListener);
        if (this.mFailedLayout != null) {
            this.mFailedLayout.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ HallOfFameFragment a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.loadPage();
                }
            });
        }
    }

    private void loadPage() {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("KEY_JUMP_PAGENAME", "HallOfFamePage");
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(s.ORIGIN, "102437");
                bundle.putString(s.STATPARAM_KEY, jSONObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.mHoldPage = e.a().a(bundle, this);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        tryObtainDataWithNet(false, false);
    }

    public void doFunction(Bundle bundle) {
    }

    public Activity getFromActivity() {
        return getActivity();
    }

    public void startActivity(Intent intent) {
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        super.startActivity(intent);
    }

    private String getPref() {
        int aS = d.aS(getActivity());
        if (aS == 0) {
            aS = 3;
        }
        return String.valueOf(aS);
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 500000:
            case 500001:
                super.handleMessageImp(message);
                this.mTabListView.setAdapter(this.mAdapter);
                this.mViewPager.setAdapter(this.mAuthorPagerAdapter);
                this.mViewPager.setOffscreenPageLimit(2);
                ((HallOfFameTabItemView) this.mTabListView.b(this.currentItem).getTag()).a();
                this.mViewPager.setCurrentItem(this.currentItem);
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    protected void showLoadingPage() {
        hideFailedPage();
        this.mTabListView.setVisibility(8);
        this.mLoadingProgress.setVisibility(0);
    }

    protected void hideLoadingPage() {
        hideFailedPage();
        this.mTabListView.setVisibility(0);
        this.mLoadingProgress.setVisibility(8);
    }

    protected void showFailedPage() {
        if (this.mTabListView.getVisibility() != 0 && this.mTabCount <= 0) {
            this.mLoadingProgress.setVisibility(8);
            this.mFailedLayout.setVisibility(0);
        } else if (this.mPullDownView != null) {
            this.mPullDownView.setRefreshing(false);
        }
    }
}

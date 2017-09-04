package com.qq.reader.module.bookstore.qnative.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.qq.reader.activity.MainActivity;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.cservice.adv.b;
import com.qq.reader.module.bookshelf.j;
import com.qq.reader.module.bookstore.qnative.a.f;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.e;
import com.qq.reader.module.findhome.base.FindHomeBaseCard;
import com.tencent.feedback.proguard.R;

public class NativeBookStoreConfigFindPageFragment extends NativeBookStoreConfigBaseFragment implements a {
    BroadcastReceiver loginOkReciver = new BroadcastReceiver(this) {
        final /* synthetic */ NativeBookStoreConfigFindPageFragment a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getBooleanExtra("loginSuccess", false)) {
                this.a.loadPage();
            }
        }
    };
    BroadcastReceiver loginOutReciver = new BroadcastReceiver(this) {
        final /* synthetic */ NativeBookStoreConfigFindPageFragment a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            this.a.loadPage();
        }
    };
    private Context mContext;
    private BroadcastReceiver mGotAvatarBroadcastReceiver = new BroadcastReceiver(this) {
        final /* synthetic */ NativeBookStoreConfigFindPageFragment a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (com.qq.reader.common.c.a.ct.equals(intent.getAction())) {
                j.b(this.a.mTitleBarAvatarView);
            }
        }
    };
    private long mLastOnResumeTime;
    private int mResumeTimes;
    private View mRootView;
    private View mTitleBarAvatarView;
    private BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver(this) {
        final /* synthetic */ NativeBookStoreConfigFindPageFragment a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (com.qq.reader.common.c.a.cs.equalsIgnoreCase(intent.getAction())) {
                this.a.mHandler.sendEmptyMessage(8012);
            }
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.localbookstore_config_find_layout, null);
        }
        return this.mRootView;
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            this.mHandler.sendEmptyMessage(10017);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.mRootView != null) {
            this.mContext = getApplicationContext();
            init();
            loadPage();
            setIsShowNightMask(false);
            setStatPageName("discoverypage");
            if (getActivity() != null) {
                getActivity().registerReceiver(this.loginOkReciver, new IntentFilter("com.qq.reader.loginok"));
                getActivity().registerReceiver(this.loginOutReciver, new IntentFilter("com.qq.reader.loginout"));
                getActivity().registerReceiver(this.mGotAvatarBroadcastReceiver, new IntentFilter(com.qq.reader.common.c.a.ct));
            }
        }
    }

    public void IOnResume() {
        super.IOnResume();
        this.mResumeTimes++;
        if (getActivity() != null) {
            getActivity().registerReceiver(this.myBroadcastReceiver, new IntentFilter(com.qq.reader.common.c.a.cs));
        }
        checkIsNeedUpdate();
        StatisticsManager.a().b();
        this.mHandler.sendEmptyMessageDelayed(1, 1500);
        this.mHandler.sendEmptyMessage(8000010);
        showChannelAdv();
        b.a(3, true);
        this.mHandler.post(new Runnable(this) {
            final /* synthetic */ NativeBookStoreConfigFindPageFragment a;

            {
                this.a = r1;
            }

            public void run() {
                j.b(this.a.mTitleBarAvatarView);
                j.a(this.a.mTitleBarAvatarView);
            }
        });
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

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 40000) {
            d.z = true;
            d.A = true;
        }
    }

    protected boolean handleMessageImp(Message message) {
        int size;
        int i;
        switch (message.what) {
            case 1:
                if (ao.o(getApplicationContext())) {
                    b.a(getApplicationContext()).a();
                }
                return true;
            case 8012:
                showChannelAdv();
                j.a(this.mTitleBarAvatarView);
                return true;
            case 10017:
                try {
                    if (this.mHoldPage != null && this.mHoldPage.m().size() > 0) {
                        size = this.mHoldPage.m().size();
                        for (i = 0; i < size; i++) {
                            ((FindHomeBaseCard) this.mHoldPage.m().get(i)).refreshData();
                        }
                    }
                } catch (Exception e) {
                    c.e("NativeBookStoreConfigFindPageFragment", e.getMessage());
                }
                return true;
            case 8000008:
                refreshWithoutPulldown(true);
                return true;
            case 8000010:
                if (this.mResumeTimes > 1) {
                    if (this.mHoldPage != null && this.mHoldPage.m().size() > 0) {
                        size = this.mHoldPage.m().size();
                        for (i = 0; i < size; i++) {
                            ((com.qq.reader.module.bookstore.qnative.card.a) this.mHoldPage.m().get(i)).refresh();
                        }
                    }
                    com.qq.reader.cservice.adv.c.c();
                    if (System.currentTimeMillis() - this.mLastOnResumeTime >= 3600000) {
                        onUpdate();
                    }
                }
                this.mLastOnResumeTime = System.currentTimeMillis();
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    public void refreshWithoutPulldown(boolean z) {
        if (!this.mPullDownView.b()) {
            if (z) {
                this.mPullDownView.setRefreshing(true);
            }
            long j = 0;
            if (z) {
                j = 1500;
            }
            this.mHandler.postDelayed(new Runnable(this) {
                final /* synthetic */ NativeBookStoreConfigFindPageFragment a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.onUpdate();
                }
            }, j);
        }
    }

    public void IOnPause() {
        try {
            if (getActivity() != null) {
                getActivity().unregisterReceiver(this.myBroadcastReceiver);
            }
            b.a(3, false);
        } catch (Exception e) {
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (getActivity() != null) {
            getActivity().unregisterReceiver(this.loginOkReciver);
            getActivity().unregisterReceiver(this.loginOutReciver);
            getActivity().unregisterReceiver(this.mGotAvatarBroadcastReceiver);
        }
        if (mAdvDialog != null) {
            mAdvDialog = null;
        }
    }

    public void init() {
        this.mCardListView = (ListView) this.mRootView.findViewById(R.id.card_list);
        this.mPullDownView = (SwipeRefreshLayout) this.mRootView.findViewById(R.id.pull_down_list);
        this.mCardListView.setOnScrollListener(new com.qq.reader.common.imageloader.core.a.a(com.qq.reader.common.imageloader.c.a((Fragment) this).a(), true, true));
        this.mLoadingProgress = this.mRootView.findViewById(R.id.loading_layout);
        this.mFailedLayout = this.mRootView.findViewById(R.id.loading_failed_layout);
        if (this.mFailedLayout != null) {
            this.mFailedLayout.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ NativeBookStoreConfigFindPageFragment a;

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
                final /* synthetic */ NativeBookStoreConfigFindPageFragment a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.onUpdate();
                }
            });
        }
        ((ImageView) this.mRootView.findViewById(R.id.profile_header_left_back)).setVisibility(8);
        ((TextView) this.mRootView.findViewById(R.id.profile_header_title)).setText(R.string.classify);
        ImageView imageView = (ImageView) this.mRootView.findViewById(R.id.profile_header_right_image);
        imageView.setImageResource(R.drawable.titlebar_icon_search_selector);
        imageView.setBackgroundDrawable(null);
        imageView.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ NativeBookStoreConfigFindPageFragment a;

            {
                this.a = r1;
            }

            public void a(View view) {
                StatisticsManager.a().a(7).c();
                com.qq.reader.common.monitor.j.a(16, 2);
                if (this.a.getActivity() != null) {
                    o.b(this.a.getActivity(), "", "4");
                }
            }
        });
        imageView.setVisibility(0);
        this.mTitleBarAvatarView = this.mRootView.findViewById(R.id.title_bar_left_view);
        this.mTitleBarAvatarView.setVisibility(0);
        this.mTitleBarAvatarView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreConfigFindPageFragment a;

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
        this.mCardListView.setOverScrollMode(2);
    }

    private void loadPage() {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("KEY_JUMP_PAGENAME", "Find_HomePage");
            this.mHoldPage = e.a().a(bundle, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.mAdapter == null) {
            this.mAdapter = new f(this.mContext);
        }
        this.mAdapter.a(this.mHoldPage);
        this.mCardListView.setAdapter(this.mAdapter);
        tryObtainDataWithNet(true, false);
    }

    public void doFunction(Bundle bundle) {
        if (bundle != null && bundle.getBoolean("goClassify") && getActivity() != null) {
            ((MainActivity) getActivity().getParent()).a("stacks_tab");
        }
    }

    public Activity getFromActivity() {
        return getActivity();
    }

    public void startActivity(Intent intent) {
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        super.startActivity(intent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            com.qq.reader.common.monitor.j.a(106, 2);
            i.a("event_C107", null, this.mContext);
            StatisticsManager.a().a("event_C107", null);
            if (getActivity() != null) {
                ((MainActivity) getActivity()).a("bookstand_tab");
            }
        }
        return true;
    }

    protected void hideLoadingPage() {
        super.hideLoadingPage();
    }

    protected void showLoadingPage() {
        super.showLoadingPage();
    }

    protected void showFailedPage() {
        super.showFailedPage();
    }

    public void notifyView() {
    }
}

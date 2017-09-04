package com.qq.reader.module.question.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.common.utils.CustomArrayList;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.common.widget.SwipeRefreshLayout.b;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qweb.fragment.BaseFragment;
import com.qq.reader.module.question.data.c;
import com.qq.reader.module.question.loader.FamousAuthorSayDataLoader;
import com.qq.reader.view.pullupdownlist.XListView;
import com.qq.reader.view.pullupdownlist.XListView$a;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;
import java.util.List;

public class FamousAuthorSayFragment extends BaseFragment implements a {
    public static final long DATA_EXPIREDTIME_WEEK = 604800000;
    private static final int DEL_MESSAGE = 1;
    public static final String DEL_MESSAGE_ID = "MessageID";
    private static final String TAG = "FamousAuthorSay";
    private com.qq.reader.module.question.loader.a mAdapter = null;
    private int mCurrentOptType = 2;
    protected View mFailedLayout = null;
    protected View mLoadingProgress = null;
    private long mMaxTime = 0;
    private long mMinTime = Long.MAX_VALUE;
    private c mPackage;
    private com.qq.reader.module.question.a.a mPage = null;
    protected SwipeRefreshLayout mPullDownView;
    protected XListView mXListView = null;
    protected View root;
    protected TextView toastView = null;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.root = layoutInflater.inflate(getLayoutResourceId(), null);
        init(this.root);
        this.mHandler = new WeakReferenceHandler(this);
        super.onCreateView(layoutInflater, viewGroup, bundle);
        loadData(2, System.currentTimeMillis());
        i.a("event_D174", null, ReaderApplication.getApplicationImp());
        return this.root;
    }

    public int getLayoutResourceId() {
        return R.layout.famousauthorsaylist_layout;
    }

    private void loadData(int i, long j) {
        if (i == 2) {
            showLoadingPage();
        }
        f.a(TAG, "opt time is " + j + " minTime:" + this.mMinTime + " maxTime: " + this.mMaxTime);
        this.mCurrentOptType = i;
        this.mPackage = new c(j, i, this);
        FamousAuthorSayDataLoader.b().a(this.mPackage, this.mHandler);
    }

    public void onResume() {
        super.onResume();
    }

    protected void init(View view) {
        this.mLoadingProgress = view.findViewById(R.id.loading_layout);
        this.mPullDownView = (SwipeRefreshLayout) view.findViewById(R.id.booklist_pull_down_list);
        this.mPullDownView.setOnRefreshListener(new b(this) {
            final /* synthetic */ FamousAuthorSayFragment a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.reRefresh();
            }
        });
        this.mFailedLayout = view.findViewById(R.id.loading_failed_layout);
        this.mFailedLayout.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FamousAuthorSayFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.reLoadData();
            }
        });
        this.toastView = (TextView) view.findViewById(R.id.main_toastbar);
        initCardListView(view);
    }

    public void onDestroyView() {
        d.f = "";
        d.g = "";
        g.a().a(new ReaderDBTask() {
            public void run() {
                com.qq.reader.module.question.data.b.b().a((FamousAuthorSayFragment.this.mMaxTime > 0 ? FamousAuthorSayFragment.this.mMaxTime : System.currentTimeMillis()) - FamousAuthorSayFragment.DATA_EXPIREDTIME_WEEK);
            }
        });
        super.onDestroyView();
    }

    protected boolean handleMessageImp(Message message) {
        try {
            switch (message.what) {
                case 500005:
                    loadMore();
                    break;
                case 11000501:
                    hideLoadingPage();
                    c cVar = (c) message.obj;
                    long f = (cVar.e().f() >= this.mMinTime || cVar.e().f() == 0) ? this.mMinTime : cVar.e().f();
                    this.mMinTime = f;
                    this.mMaxTime = cVar.e().e() > this.mMaxTime ? cVar.e().e() : this.mMaxTime;
                    if (cVar.c() != 1) {
                        if (cVar.c() != 0) {
                            this.mPage = cVar.e();
                            construct(this.mPage, cVar.a(), cVar.c());
                            break;
                        }
                        List g = cVar.e().g();
                        int size = g.size();
                        this.mPullDownView.a(size > 0 ? "推荐了" + size + "条新内容" : "暂无新内容", this.toastView);
                        this.mPage.a(g, true);
                        construct(this.mPage, true, cVar.c());
                        break;
                    }
                    this.mPage.a(cVar.e().g());
                    construct(this.mPage, cVar.a(), cVar.c());
                    break;
                case 11000502:
                    com.qq.reader.common.monitor.debug.c.e(TAG, "list obtain failed");
                    hideLoadingPage();
                    showFailedPage();
                    break;
                case 11000505:
                    showLoadingPullDown();
                    break;
            }
            return super.handleMessageImp(message);
        } catch (Exception e) {
            return super.handleMessageImp(message);
        }
    }

    public void construct(com.qq.reader.module.question.a.a aVar, boolean z, int i) {
        int i2 = 0;
        if (!isDetached() && aVar != null) {
            if (this.mAdapter == null) {
                this.mAdapter = new com.qq.reader.module.question.loader.a(getActivity());
            }
            try {
                int count = this.mAdapter.getCount() - 1;
                int childCount = this.mXListView.getChildCount() - 2;
                if (childCount >= 0 && childCount < this.mXListView.getChildCount()) {
                    View childAt = this.mXListView.getChildAt(childCount);
                    if (childAt != null) {
                        i2 = childAt.getTop();
                    }
                }
                this.mAdapter.a(aVar);
                if (this.mAdapter.b()) {
                    this.mXListView.setAdapter(this.mAdapter);
                    if (i == 1 && count >= 0 && count < this.mAdapter.getCount()) {
                        this.mXListView.setSelectionFromTop(count, i2);
                    }
                }
                if (i == 1) {
                    if (z) {
                        this.mXListView.c();
                    } else {
                        this.mXListView.e();
                    }
                } else if (aVar.g().size() == 0) {
                    this.mXListView.g();
                } else {
                    this.mXListView.b();
                }
                this.mAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.e("FamousAuthorSayFragment", e.getMessage());
            }
        }
    }

    public void initCardListView(View view) {
        if (this.mXListView == null) {
            this.mXListView = (XListView) view.findViewById(R.id.list_layout);
            this.mXListView.setCrashTag(CustomArrayList.Class_NativePageFragmentforOther);
            this.mXListView.setPullRefreshEnable(true);
            this.mXListView.setPullLoadEnable(true);
        }
        this.mXListView.setVisibility(0);
        this.mXListView.setXListFooter(new FamousAuthorSayXListFooter(getActivity()));
        this.mXListView.setXListViewListener(new XListView$a(this) {
            final /* synthetic */ FamousAuthorSayFragment a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.mHandler.sendEmptyMessage(500005);
            }
        });
        this.mXListView.setOnScrollListener(new com.qq.reader.common.imageloader.core.a.a(com.qq.reader.common.imageloader.c.a(this).a(), true, true));
        this.mXListView.b();
    }

    public void loadMore() {
        this.mCurrentOptType = 1;
        loadData(1, this.mMinTime);
    }

    public void reRefresh() {
        this.mCurrentOptType = 0;
        loadData(0, this.mMaxTime);
    }

    public void reRefreshFristTime() {
        showLoadingPage();
        this.mCurrentOptType = 0;
        loadData(0, this.mMaxTime);
    }

    private void reLoadData() {
        reRefreshFristTime();
    }

    protected void showLoadingPullDown() {
        hideLoadingPage();
        hideFailedPage();
        if (this.mPullDownView != null) {
            this.mPullDownView.setRefreshing(true);
        }
    }

    protected void hideLoadingPullDown() {
        hideLoadingPage();
        hideFailedPage();
        if (this.mPullDownView != null) {
            this.mPullDownView.setRefreshing(false);
        }
    }

    protected void showLoadingPage() {
        hideFailedPage();
        if (this.mXListView != null) {
            this.mXListView.setVisibility(8);
        }
        if (this.mLoadingProgress != null) {
            this.mLoadingProgress.setVisibility(0);
        }
    }

    protected void hideLoadingPage() {
        hideFailedPage();
        if (this.mXListView != null) {
            this.mXListView.setVisibility(0);
        }
        if (this.mLoadingProgress != null) {
            this.mLoadingProgress.setVisibility(8);
        }
    }

    protected void showFailedPage() {
        if (this.mXListView == null || this.mXListView.getCount() <= 0 || this.mXListView.getVisibility() != 0) {
            if (this.mLoadingProgress != null) {
                this.mLoadingProgress.setVisibility(8);
            }
            this.mFailedLayout.setVisibility(0);
        } else if (this.mPackage.c() == 1) {
            this.mXListView.d();
        } else {
            this.mPullDownView.a(getApplicationContext().getResources().getString(R.string.pulldownview_failed), this.toastView);
        }
    }

    protected void hideFailedPage() {
        this.mFailedLayout.setVisibility(8);
    }

    public void onPreLoad() {
    }

    public void onLoading() {
    }

    public void onLoadFinished() {
    }

    public void doFunction(Bundle bundle) {
    }

    public Activity getFromActivity() {
        return getActivity();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.mPage != null) {
            this.mPage.a(i, i2, intent, this.mHandler);
        }
    }
}

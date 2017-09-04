package com.qq.reader.module.bookstore.qnative.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.RecyclerListener;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.CustomArrayList;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.common.widget.SwipeRefreshLayout.a;
import com.qq.reader.module.bookstore.qnative.a.f;
import com.qq.reader.module.bookstore.qnative.card.BaseListCard;
import com.qq.reader.module.bookstore.qnative.d;
import com.qq.reader.module.bookstore.qnative.model.TitlerControlModel;
import com.qq.reader.module.bookstore.qnative.page.e;
import com.qq.reader.module.bookstore.qnative.page.impl.NativeServerLimitTimeDiscountBuyPage;
import com.qq.reader.module.bookstore.qnative.page.impl.aa;
import com.qq.reader.module.bookstore.qnative.page.impl.m;
import com.qq.reader.view.EmptyView;
import com.qq.reader.view.af;
import com.qq.reader.view.linearmenu.b;
import com.qq.reader.view.pullupdownlist.XListView;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class NativePageFragmentforOther extends NativePageFragment implements Callback, a {
    public static final int DIALOG_DELETE_REPLY = 609;
    protected static final int MENU_CANCEL_TOPREPLY = 2;
    public static final int MENU_DELETE = 0;
    protected static final int MENU_REPLY = 1;
    protected static final int MENU_SET_TOPREPLY = 3;
    protected static final int STATUS_BUSY = 1;
    protected static final int STATUS_FREE = 0;
    public boolean configEmpty;
    protected EmptyView emptyView;
    protected Bundle enterBundle = null;
    public boolean hideEmpty;
    public boolean isFromCharts = false;
    public f mAdapter;
    protected b mBottomContextMenu;
    private Context mContext;
    protected int mCurPageStatus = 0;
    protected View mFailedLayout = null;
    protected WeakReferenceHandler mHandler;
    protected View mLoadingProgress = null;
    protected Bundle mNextBundle = null;
    public com.qq.reader.module.bookstore.qnative.page.b mNextPage = null;
    protected e mPageRankInfoListener;
    protected SwipeRefreshLayout mPullDownView;
    private TitlerControlModel mTitlerControl;
    protected XListView mXListView = null;
    protected RelativeLayout rl_parentLayout;
    protected View root;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.root = layoutInflater.inflate(getLayoutResourceId(), null);
        init(this.root);
        super.onCreateView(layoutInflater, viewGroup, bundle);
        return this.root;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mHandler = new WeakReferenceHandler(this);
    }

    public boolean isLoading() {
        if (this.mCurPageStatus == 1) {
            return true;
        }
        return false;
    }

    protected void init(View view) {
        this.mLoadingProgress = view.findViewById(R.id.loading_layout);
        this.mPullDownView = (SwipeRefreshLayout) view.findViewById(R.id.booklist_pull_down_list);
        this.mPullDownView.setDispatchEventListener(this);
        this.mPullDownView.setOnRefreshListener(new SwipeRefreshLayout.b(this) {
            final /* synthetic */ NativePageFragmentforOther a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.reRefresh();
            }
        });
        this.mFailedLayout = view.findViewById(R.id.loading_failed_layout);
        this.rl_parentLayout = (RelativeLayout) view.findViewById(R.id.rl_parent);
        this.mFailedLayout.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativePageFragmentforOther a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.reLoadData();
            }
        });
        this.emptyView = (EmptyView) view.findViewById(R.id.noresult_layout);
        HashMap hashArguments = getHashArguments();
        if (hashArguments != null) {
            this.enterBundle = (Bundle) hashArguments.get("key_data");
            this.isFromCharts = this.enterBundle.getBoolean("PARA_TYPE_BOOLEAN");
            this.mPullDownView.setEnabled(this.enterBundle.getBoolean("PARA_TYPE_SWIPE_REFRESH_ENABLE", true));
            int i = this.enterBundle.getInt("NATIVE_FRAGMENT_LOADING_PROGRESSBAR_INDETEMINATE_DRAWABLE_RES_ID", 0);
            if (i != 0) {
                ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.default_progress);
                if (progressBar != null) {
                    progressBar.setIndeterminateDrawable(ReaderApplication.getApplicationImp().getResources().getDrawable(i));
                }
            }
            try {
                int i2 = this.enterBundle.getInt("NATIVE_BG_COLOR_Resource", -1);
                i = this.enterBundle.getInt("NATIVE_LISTVIEW_DIVIDOR_RES", -1);
                int i3 = this.enterBundle.getInt("NATIVE_LISTVIEW_DIVIDOR_HEIGHT", -1);
                int i4 = this.enterBundle.getInt("NATIVE_LISTVIEW_PULLDOWN_SCHEME_COLOR", -1);
                int i5 = this.enterBundle.getInt("NATIVE_LISTVIEW_FOOTVIEW_BG_COLOR", -1);
                if (i2 != -1) {
                    view.setBackgroundResource(i2);
                }
                if (i4 != -1) {
                    this.mPullDownView.setMannuallySetSchemeColor(i4);
                }
                if (i5 != -1) {
                    ((XListView) view.findViewById(R.id.list_layout)).setFootViewBgColor(i5);
                }
                if (i != -1) {
                    XListView xListView = (XListView) view.findViewById(R.id.list_layout);
                    xListView.setCrashTag(CustomArrayList.Class_NativePageFragmentforOther);
                    if (xListView != null) {
                        xListView.setDivider(getResources().getDrawable(i));
                        if (i3 != -1) {
                            xListView.setDividerHeight(i3);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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
            if (!isDetached()) {
                this.mHandler.postDelayed(new Runnable(this) {
                    final /* synthetic */ NativePageFragmentforOther a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.reRefresh();
                    }
                }, j);
            }
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void initCardListView(View view, boolean z) {
        if (this.mXListView == null) {
            this.mXListView = (XListView) view.findViewById(R.id.list_layout);
            if (this.enterBundle != null) {
                int i = this.enterBundle.getInt("NATIVE_LISTVIEW_FOOTER_LOADING_PROGRESSBAR_INDETEMINATE_DRAWABLE_RES_ID", 0);
                if (!(i == 0 || this.mXListView == null)) {
                    this.mXListView.setFooterProgressBarLoadingDrawable(i);
                }
            }
            if (this.mHoldPage instanceof NativeServerLimitTimeDiscountBuyPage) {
                View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.limit_count_xlistview_footer, null);
                if (this.mXListView != null) {
                    this.mXListView.addFooterView(inflate);
                }
            }
            this.mXListView.setCrashTag(CustomArrayList.Class_NativePageFragmentforOther);
            this.mXListView.setPullRefreshEnable(false);
            this.mXListView.setRecyclerListener(new RecyclerListener(this) {
                final /* synthetic */ NativePageFragmentforOther a;

                {
                    this.a = r1;
                }

                public void onMovedToScrapHeap(View view) {
                }
            });
        }
        if (this.mHoldPage != null) {
            boolean z2;
            this.mXListView.setEmptyView(this.emptyView);
            this.mXListView.setVisibility(0);
            if (z || this.mHoldPage.s()) {
                z2 = true;
            } else {
                z2 = false;
            }
            Boolean configCanPullLoadMore = configCanPullLoadMore();
            if (configCanPullLoadMore != null) {
                z2 = configCanPullLoadMore.booleanValue();
            }
            this.mXListView.setPullLoadEnable(z2);
            if (!this.mHoldPage.s() || (this.mHoldPage instanceof m)) {
                this.mXListView.c();
            } else {
                this.mXListView.setXListViewListener(new XListView.a(this) {
                    final /* synthetic */ NativePageFragmentforOther a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        this.a.mHandler.sendEmptyMessage(500005);
                    }
                });
                this.mXListView.setOnScrollListener(new com.qq.reader.common.imageloader.core.a.a(c.a((Fragment) this).a(), true, true));
                this.mXListView.e();
            }
            onListViewInitialized();
        }
    }

    protected void onListViewInitialized() {
    }

    protected void onDataInitialized() {
    }

    public void initListBookCardUI(View view, BaseListCard baseListCard) {
        initCardListView(view, true);
        baseListCard.attachView(this.mXListView);
        baseListCard.notifyDataSetChanged();
    }

    protected void initConfigBookCardUI(View view, List<com.qq.reader.module.bookstore.qnative.card.a> list) {
        initCardListView(view, false);
        if (this.mAdapter == null) {
            this.mAdapter = new f(getActivity());
        }
        this.mAdapter.a(this.mHoldPage);
        if (this.mAdapter.b() || this.mXListView.getAdapter() == null) {
            this.mXListView.setAdapter(this.mAdapter);
        } else {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void refresh() {
        if (this.mCurPageStatus != 1) {
            if (this.mHoldPage == null || this.mHoldPage.j() == null || !this.mHoldPage.j().getBoolean("need_reload")) {
                try {
                    if (this.mHoldPage != null) {
                        this.mHoldPage.q();
                        List m = this.mHoldPage.m();
                        if (m != null && m.size() > 0) {
                            BaseListCard listBookCard = getListBookCard(m);
                            if (listBookCard != null) {
                                initListBookCardUI(this.root, listBookCard);
                                listBookCard.notifyDataSetChanged();
                                return;
                            } else if (this.mAdapter != null) {
                                this.mAdapter.a(this.mHoldPage);
                                if (this.mAdapter.b() || this.mXListView.getAdapter() == null) {
                                    this.mXListView.setAdapter(this.mAdapter);
                                    return;
                                } else {
                                    this.mAdapter.notifyDataSetChanged();
                                    return;
                                }
                            } else {
                                return;
                            }
                        }
                        return;
                    }
                    return;
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.a("LOGGER_NATIVE", e.toString());
                    return;
                }
            }
            this.mHoldPage.j().remove("need_reload");
            this.mPullDownView.setRefreshing(true);
            reRefresh();
        }
    }

    public void onPreLoad() {
    }

    public void onLoading() {
        if (this.mHandler != null) {
            this.mHandler.sendEmptyMessage(500002);
        }
    }

    private void loadPage() {
        if (this.mHoldPage == null && this.enterBundle != null) {
            try {
                Object obj = getHashArguments().get("LOCAL_STORE_HOLD_PAGE");
                if (obj != null) {
                    this.mHoldPage = (com.qq.reader.module.bookstore.qnative.page.b) obj;
                }
            } catch (Exception e) {
                com.qq.reader.common.monitor.f.a("LBPageFragment", "LBPageFragment  loadPage exception : " + e.toString());
            }
            if (this.mHoldPage == null) {
                this.mHoldPage = com.qq.reader.module.bookstore.qnative.e.a().a(this.enterBundle, this);
                tryObtainDataWithNet(true, false);
                return;
            }
            notifyData();
            hideLoadingPage();
        }
    }

    protected void configEmptyView() {
    }

    public void loadNextPage() {
        if (this.mCurPageStatus != 0) {
            return;
        }
        if (this.mHoldPage.s()) {
            if (this.mNextBundle == null) {
                this.mNextBundle = new Bundle(this.enterBundle);
            }
            long r = this.mHoldPage.r();
            if (r != 0) {
                this.mNextBundle.putLong("KEY_PAGEINDEX", r);
                this.mNextBundle.putString("URL_BUILD_PERE_SIGNAL", "nextpage");
            }
            this.mNextPage = com.qq.reader.module.bookstore.qnative.e.a().a(this.mNextBundle, this);
            this.mCurPageStatus = 1;
            this.mNextPage.a(1001);
            d.b().a(getApplicationContext(), this.mNextPage, this.mHandler, true);
        } else if (this.mXListView != null) {
            this.mXListView.c();
        }
    }

    public void onLoadFinished() {
    }

    public b getMoreMenu(Bundle bundle) {
        this.mBottomContextMenu = new b(getActivity());
        this.mBottomContextMenu.a(0, "删除", bundle);
        this.mBottomContextMenu.a(new OnCancelListener(this) {
            final /* synthetic */ NativePageFragmentforOther a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.a.getActivity().getWindow().closeAllPanels();
            }
        });
        return this.mBottomContextMenu;
    }

    public Activity getFromActivity() {
        return getActivity();
    }

    public void notifyData() {
        if (!isDetached()) {
            if (this.mNextPage != null && this.mCurPageStatus == 1) {
                if (this.mNextPage.m().size() <= 0) {
                    this.mXListView.c();
                } else {
                    this.mHoldPage.addMore(this.mNextPage);
                    this.mXListView.e();
                    if (this.mAdapter != null) {
                        try {
                            int count = this.mAdapter.getCount() - 1;
                            int childCount = this.mXListView.getChildCount() - 2;
                            if (childCount < 0 || childCount >= this.mXListView.getChildCount()) {
                                childCount = 0;
                            } else {
                                View childAt = this.mXListView.getChildAt(childCount);
                                childCount = childAt == null ? 0 : childAt.getTop();
                            }
                            this.mAdapter.a(this.mHoldPage);
                            if (this.mAdapter.b() || this.mXListView.getAdapter() == null) {
                                this.mXListView.setAdapter(this.mAdapter);
                                if (count >= 0 && count < this.mAdapter.getCount()) {
                                    this.mXListView.setSelectionFromTop(count, childCount);
                                }
                            } else {
                                this.mAdapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            com.qq.reader.common.monitor.debug.c.e(CustomArrayList.Class_NativePageFragmentforOther, e.getMessage());
                        }
                    }
                    if (!this.mHoldPage.s()) {
                        this.mXListView.c();
                    }
                }
                this.mNextPage = null;
                this.mCurPageStatus = 0;
            } else if (this.mHoldPage != null) {
                if (this.mHoldPage instanceof NativeServerLimitTimeDiscountBuyPage) {
                    Intent intent = new Intent();
                    intent.setAction("BROADCAST_ACTION_FRAGMENT_NOTIFY");
                    intent.putExtra("EXTRA_PARAM_KEY_EVENT_LIST", ((NativeServerLimitTimeDiscountBuyPage) this.mHoldPage).d);
                    ReaderApplication.getApplicationImp().sendBroadcast(intent);
                }
                if (this.mHoldPage instanceof aa) {
                    com.qq.reader.appconfig.a.d.u(ReaderApplication.getApplicationImp(), "");
                }
                List m = this.mHoldPage.m();
                if (this.mHoldPage != null) {
                    configEmptyView();
                }
                if (m != null && m.size() > 0) {
                    BaseListCard listBookCard = getListBookCard(m);
                    if (listBookCard != null) {
                        listBookCard.setIsFromCharis(this.isFromCharts);
                        initListBookCardUI(this.root, listBookCard);
                        if (this.mHoldPage instanceof com.qq.reader.module.bookstore.qnative.page.c) {
                            checkShouldShowUpdateTime((com.qq.reader.module.bookstore.qnative.page.c) this.mHoldPage);
                        }
                    } else {
                        initConfigBookCardUI(this.root, m);
                    }
                    if (this.emptyView != null) {
                        this.emptyView.setVisibility(8);
                    }
                    if (this.mXListView != null) {
                        this.mXListView.setVisibility(0);
                    }
                    onDataInitialized();
                } else if (!this.hideEmpty) {
                    configCanPullDownRefresh(true);
                    if (this.emptyView != null) {
                        this.emptyView.setVisibility(0);
                    }
                    if (this.mXListView != null) {
                        this.mXListView.setVisibility(8);
                    }
                } else if (this.emptyView != null) {
                    this.emptyView.setVisibility(8);
                }
            }
        }
    }

    public BaseListCard getListBookCard(List<com.qq.reader.module.bookstore.qnative.card.a> list) {
        if (list != null && list.size() == 1) {
            com.qq.reader.module.bookstore.qnative.card.a aVar = (com.qq.reader.module.bookstore.qnative.card.a) list.get(0);
            if (aVar != null && (aVar instanceof BaseListCard)) {
                return (BaseListCard) aVar;
            }
        }
        return null;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 111:
                af.a(getApplicationContext(), (CharSequence) "登录态失效，请重新登录", 0).a();
                Bundle bundle = new Bundle();
                bundle.putInt("function_type", 3);
                doFunction(bundle);
                return true;
            case 500000:
            case 500001:
                this.mPullDownView.setRefreshing(false);
                try {
                    if (message.obj != null) {
                        Object obj = message.obj;
                        if (obj instanceof com.qq.reader.module.bookstore.qnative.page.c) {
                            com.qq.reader.module.bookstore.qnative.page.b bVar = (com.qq.reader.module.bookstore.qnative.page.c) obj;
                            if (bVar.g().indexOf("nextpage") == -1) {
                                checkShouldShowUpdateTime(bVar);
                                this.mHoldPage.a(bVar);
                                onLoadPageDataFirstSectionSucess(bVar);
                                if (this.mPageRankInfoListener != null) {
                                    this.mPageRankInfoListener.a(this, this.mHoldPage.k());
                                }
                            } else if (this.mNextPage == null || this.mCurPageStatus != 1) {
                                return true;
                            } else {
                                this.mNextPage.a(bVar);
                            }
                        } else if (obj instanceof com.qq.reader.module.bookstore.qnative.page.b) {
                            this.mHoldPage.a((com.qq.reader.module.bookstore.qnative.page.b) obj);
                        }
                        hideLoadingPage();
                        notifyData();
                    } else {
                        com.qq.reader.common.monitor.debug.c.a("LOGGER_NATIVE", "msg.obj == null");
                    }
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.a("LOGGER_NATIVE", e.toString());
                }
                return true;
            case 500002:
                loadPage();
                return true;
            case 500004:
                this.mPullDownView.setRefreshing(false);
                if (this.mCurPageStatus == 1) {
                    this.mNextPage = null;
                    this.mCurPageStatus = 0;
                    this.mXListView.d();
                } else {
                    showFailedPage();
                }
                return true;
            case 500005:
                loadNextPage();
                return true;
            case 7000002:
                refresh();
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    protected void checkShouldShowUpdateTime(com.qq.reader.module.bookstore.qnative.page.c cVar) {
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
        if (this.mXListView == null || this.mXListView.getVisibility() != 0) {
            if (this.mLoadingProgress != null) {
                this.mLoadingProgress.setVisibility(8);
            }
            this.mFailedLayout.setVisibility(0);
        }
    }

    protected void hideFailedPage() {
        this.mFailedLayout.setVisibility(8);
    }

    public void reLoadData() {
        this.mHoldPage.a(1000);
        tryObtainDataWithNet(true, false);
    }

    public void silentRefreshOnBackground() {
        if (this.mPullDownView != null && this.mHoldPage != null) {
            tryObtainDataWithNet(false, true);
            configCanPullDownRefresh(false);
        }
    }

    public void reRefresh() {
        if (this.mPullDownView != null && this.mHoldPage != null) {
            this.mHoldPage.a(1001);
            tryObtainDataWithNet(false, true);
            configCanPullDownRefresh(false);
        }
    }

    protected void tryObtainDataWithNet(boolean z, boolean z2) {
        boolean a = d.b().a(getApplicationContext(), this.mHoldPage, this.mHandler, z);
        if (!z2) {
            if (a) {
                notifyData();
                hideLoadingPage();
                return;
            }
            showLoadingPage();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.mHoldPage != null) {
            this.mHoldPage.a(i, i2, intent, this.mHandler);
        }
    }

    public XListView getXListView() {
        return this.mXListView;
    }

    public int getLayoutResourceId() {
        return R.layout.localbooklist_layout;
    }

    private String getFormatDate(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(j));
    }

    protected Boolean configCanPullLoadMore() {
        return null;
    }

    protected void configCanPullDownRefresh(boolean z) {
    }

    public void onDispatchTouchEvent(MotionEvent motionEvent) {
        if (this.emptyView != null && this.emptyView.isShown()) {
            this.emptyView.dispatchTouchEvent(motionEvent);
        }
    }

    public void setPageRankInfoListener(e eVar) {
        this.mPageRankInfoListener = eVar;
    }

    public void onLoadPageDataFirstSectionSucess(com.qq.reader.module.bookstore.qnative.page.b bVar) {
    }
}

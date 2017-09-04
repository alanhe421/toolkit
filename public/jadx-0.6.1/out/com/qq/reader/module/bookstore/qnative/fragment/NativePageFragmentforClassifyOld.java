package com.qq.reader.module.bookstore.qnative.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.imageloader.core.a.a;
import com.qq.reader.common.utils.CustomArrayList;
import com.qq.reader.module.bookstore.qnative.a.f;
import com.qq.reader.module.bookstore.qnative.card.BaseListCard;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCardCommon;
import com.qq.reader.module.bookstore.qnative.d;
import com.qq.reader.module.bookstore.qnative.e;
import com.qq.reader.module.bookstore.qnative.page.impl.ag;
import com.qq.reader.view.af;
import com.qq.reader.view.linearmenu.b;
import com.qq.reader.view.pullupdownlist.XListView;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NativePageFragmentforClassifyOld extends NativePageFragment implements Callback, OnScrollListener {
    public static final int DIALOG_DELETE_REPLY = 609;
    protected static final int MENU_CANCEL_TOPREPLY = 2;
    public static final int MENU_DELETE = 0;
    protected static final int MENU_REPLY = 1;
    protected static final int MENU_SET_TOPREPLY = 3;
    protected static final int STATUS_BUSY = 1;
    protected static final int STATUS_FREE = 0;
    private static final String tag = "classify";
    protected Bundle enterBundle = null;
    private boolean isFromCharts = false;
    private f mAdapter;
    private a mBackUpFragmentOfClassify;
    protected b mBottomContextMenu;
    private a mClassifySelectedFragment;
    protected int mCurPageStatus = 0;
    protected View mFailedLayout = null;
    protected WeakReferenceHandler mHandler;
    private View mHeaderView;
    private BaseListCard mLastListCard = null;
    private int mLastPosition = -1;
    private int mListViewTop = -1;
    protected View mLoadingProgress = null;
    protected Bundle mNextBundle = null;
    public com.qq.reader.module.bookstore.qnative.page.b mNextPage = null;
    private View mNoResultLayout;
    private int mPageType;
    a mPauseOnScrollListener = new a(c.a((Fragment) this).a(), true, true);
    private int mScrollState = 0;
    private JSONObject mTabJson;
    private StringBuffer mTextString = new StringBuffer();
    private LinearLayout mTopSelectedLayout;
    private LinearLayout mTopSelectedResultLayout;
    private TextView mTopSelectedTextView;
    private StringBuffer mUrlendString = new StringBuffer();
    protected XListView mXListView = null;
    protected RelativeLayout rl_parentLayout;
    protected View root;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.root = layoutInflater.inflate(R.layout.localbooklist_classify_layout, null);
        init(this.root);
        this.mHandler = new WeakReferenceHandler(this);
        super.onCreateView(layoutInflater, viewGroup, bundle);
        return this.root;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.mHoldPage.s()) {
            this.mPauseOnScrollListener.onScrollStateChanged(absListView, i);
        }
        this.mScrollState = i;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        int viewTop;
        com.qq.reader.common.monitor.f.d(tag, "onScroll firstVisibleItem : " + i + "  lastposition : " + this.mLastPosition + "  state " + this.mScrollState);
        switch (this.mScrollState) {
            case 0:
                this.mLastPosition = i;
                break;
            case 1:
            case 2:
                if (this.mLastPosition < 0) {
                    this.mLastPosition = i;
                }
                if (this.mLastPosition < i && this.mTopSelectedLayout.getVisibility() == 0) {
                    hideBackupSelectedView();
                    showResultLayout();
                }
                this.mLastPosition = i;
                break;
        }
        if (this.mHoldPage.s()) {
            this.mPauseOnScrollListener.onScroll(absListView, i, i2, i3);
        }
        if (i <= 1) {
            View view = this.mHeaderView;
            viewTop = getViewTop(view);
            int height = viewTop + view.getHeight();
            int dimensionPixelOffset = (height / 2) + (getActivity().getResources().getDimensionPixelOffset(R.dimen.bookstore_titlerbar_height) / 2);
            if (this.mListViewTop == -1) {
                this.mListViewTop = getViewTop(this.mXListView);
            }
            if (height <= this.mListViewTop || viewTop == this.mListViewTop) {
                if (viewTop == this.mListViewTop) {
                    if (this.mTopSelectedResultLayout.getVisibility() != 8) {
                        this.mTopSelectedResultLayout.setVisibility(8);
                    }
                    if (this.mTopSelectedLayout.getVisibility() != 4) {
                        hideBackupSelectedView();
                    }
                    if (this.mHeaderView.getVisibility() != 0) {
                        this.mHeaderView.setVisibility(0);
                        this.mClassifySelectedFragment.a(this.mBackUpFragmentOfClassify.a());
                        new Handler().post(new Runnable(this) {
                            final /* synthetic */ NativePageFragmentforClassifyOld a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.root.requestLayout();
                            }
                        });
                    }
                } else if (this.mTopSelectedResultLayout.getVisibility() != 0) {
                    showResultLayout();
                    if (this.mTextString.length() > 0) {
                        this.mTopSelectedTextView.setText(this.mTextString.toString().replace("全部,", "").replace(",", " - "));
                    }
                }
            } else if (this.mTopSelectedResultLayout.getVisibility() != 8) {
                hideResultLayout();
            }
            viewTop = dimensionPixelOffset;
        } else {
            if (this.mTopSelectedResultLayout.getVisibility() != 0) {
                showResultLayout();
                if (this.mTextString.length() > 0) {
                    this.mTopSelectedTextView.setText(this.mTextString.toString().replace("全部,", "").replace(",", " - "));
                }
            }
            viewTop = 0;
        }
        Activity activity = getActivity();
        if (activity != null) {
            LinearLayout linearLayout = (LinearLayout) activity.findViewById(R.id.loading_layout);
            if (linearLayout != null) {
                linearLayout.setPadding(0, viewTop, 0, 0);
            }
        }
    }

    private int getViewTop(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr[1];
    }

    private void init(View view) {
        this.mLoadingProgress = view.findViewById(R.id.loading_layout);
        this.mTopSelectedLayout = (LinearLayout) view.findViewById(R.id.topcontainer);
        this.mTopSelectedResultLayout = (LinearLayout) view.findViewById(R.id.topselectedlayout);
        this.mTopSelectedTextView = (TextView) view.findViewById(R.id.top_selectedtext);
        this.mNoResultLayout = view.findViewById(R.id.noresult_layout);
        this.mTopSelectedResultLayout.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativePageFragmentforClassifyOld a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.showBackupSelectedView();
                if (this.a.mHeaderView.getVisibility() != 8) {
                    this.a.mHeaderView.setVisibility(8);
                }
                if (this.a.mTopSelectedResultLayout.getVisibility() != 8) {
                    this.a.hideResultLayout();
                }
            }
        });
        this.mFailedLayout = view.findViewById(R.id.loading_failed_layout);
        this.rl_parentLayout = (RelativeLayout) view.findViewById(R.id.rl_parent);
        this.mFailedLayout.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativePageFragmentforClassifyOld a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.reLoadData();
            }
        });
        HashMap hashArguments = getHashArguments();
        if (hashArguments != null) {
            this.enterBundle = (Bundle) hashArguments.get("key_data");
            this.isFromCharts = this.enterBundle.getBoolean("PARA_TYPE_BOOLEAN");
            if ("bookclubchapter".equals(this.enterBundle.getString("KEY_JUMP_PAGENAME"))) {
                this.mPageType = 1;
            }
        }
    }

    private void showResultLayout() {
        this.mTopSelectedResultLayout.clearAnimation();
        Animation loadAnimation = AnimationUtils.loadAnimation(ReaderApplication.getApplicationImp(), R.anim.topbar_enter);
        loadAnimation.setDuration(500);
        this.mTopSelectedResultLayout.setVisibility(0);
        this.mTopSelectedResultLayout.startAnimation(loadAnimation);
    }

    private void hideResultLayout() {
        Animation loadAnimation = AnimationUtils.loadAnimation(ReaderApplication.getApplicationImp(), R.anim.topbar_out);
        loadAnimation.setDuration(500);
        this.mTopSelectedResultLayout.clearAnimation();
        this.mTopSelectedResultLayout.startAnimation(loadAnimation);
        loadAnimation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ NativePageFragmentforClassifyOld a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.mTopSelectedResultLayout.setVisibility(8);
            }
        });
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    private void showBackupSelectedView() {
        if (this.mBackUpFragmentOfClassify == null) {
            this.mBackUpFragmentOfClassify = new a(getActivity());
            Bundle bundle = new Bundle();
            bundle.putString("classify_list", ((ag) this.mHoldPage).x().toString());
            this.mBackUpFragmentOfClassify.a(this.mHandler);
            this.mBackUpFragmentOfClassify.a(bundle);
            this.mTopSelectedLayout.addView(this.mBackUpFragmentOfClassify.a(LayoutInflater.from(getActivity())));
        }
        this.mBackUpFragmentOfClassify.a(this.mClassifySelectedFragment.a());
        this.mTopSelectedResultLayout.setVisibility(8);
        this.mTopSelectedLayout.setVisibility(0);
    }

    private void hideBackupSelectedView() {
        this.mTopSelectedLayout.setVisibility(4);
        this.mTopSelectedResultLayout.setVisibility(0);
    }

    private void initCardListView(View view, boolean z) {
        if (getActivity() != null) {
            Bundle bundle;
            if (this.mXListView == null) {
                com.qq.reader.common.monitor.debug.c.a(tag, "initCardListView " + this);
                this.mXListView = (XListView) view.findViewById(R.id.list_layout);
                this.mXListView.setCrashTag(CustomArrayList.Class_NativePageFragmentforClassify);
                this.mXListView.setPullRefreshEnable(false);
                this.mXListView.setOnScrollListener(this);
                this.mClassifySelectedFragment = new a(getActivity());
                bundle = new Bundle();
                if (!(this.mHoldPage == null || ((ag) this.mHoldPage).x() == null)) {
                    bundle.putString("classify_list", ((ag) this.mHoldPage).x().toString());
                }
                this.mClassifySelectedFragment.a(this.mHandler);
                this.mClassifySelectedFragment.a(bundle);
                this.mHeaderView = this.mClassifySelectedFragment.a(LayoutInflater.from(getActivity()));
                this.mXListView.addHeaderView(this.mHeaderView);
            }
            if (this.mBackUpFragmentOfClassify == null) {
                this.mBackUpFragmentOfClassify = new a(getActivity());
                bundle = new Bundle();
                if (!(this.mHoldPage == null || ((ag) this.mHoldPage).x() == null)) {
                    bundle.putString("classify_list", ((ag) this.mHoldPage).x().toString());
                }
                this.mBackUpFragmentOfClassify.a(this.mHandler);
                this.mBackUpFragmentOfClassify.a(bundle);
                this.mTopSelectedLayout.addView(this.mBackUpFragmentOfClassify.a(LayoutInflater.from(getActivity())));
            }
            a.c[] c = this.mClassifySelectedFragment.c();
            StringBuffer stringBuffer = new StringBuffer();
            int i = 0;
            while (c != null && i < c.length) {
                a.c cVar = c[i];
                if (cVar != null) {
                    if (i == c.length - 1) {
                        stringBuffer.append(cVar.b);
                    } else {
                        stringBuffer.append(cVar.b).append(",");
                    }
                }
                i++;
            }
            if (stringBuffer != null && stringBuffer.length() > 0) {
                this.mTopSelectedTextView.setText(stringBuffer.toString().replace("全部,", "").replace(",", " - "));
            }
            if (this.mHoldPage != null) {
                this.mXListView.setVisibility(0);
                boolean z2 = true;
                if (!(z || this.mHoldPage.s())) {
                    z2 = false;
                }
                this.mXListView.setPullLoadEnable(z2);
                if (this.mHoldPage.s()) {
                    this.mXListView.setXListViewListener(new XListView.a(this) {
                        final /* synthetic */ NativePageFragmentforClassifyOld a;

                        {
                            this.a = r1;
                        }

                        public void a() {
                            this.a.mHandler.sendEmptyMessage(500005);
                        }
                    });
                    this.mXListView.setOnScrollListener(this);
                    this.mXListView.e();
                    return;
                }
                this.mXListView.c();
            }
        }
    }

    private void initListBookCardUI(View view, BaseListCard baseListCard) {
        com.qq.reader.common.monitor.debug.c.a(tag, "initListBookCardUi " + baseListCard.getClass().getSimpleName());
        initCardListView(view, true);
        baseListCard.attachView(this.mXListView);
        baseListCard.notifyDataSetChanged();
        hideNoResultLayout();
        if (baseListCard instanceof ListCardCommon) {
            List itemList = ((ListCardCommon) baseListCard).getItemList();
            if (itemList == null || itemList.size() == 0) {
                this.mXListView.g();
                showNoResultLayout();
            }
        }
    }

    private void showNoResultLayout() {
        if (this.mNoResultLayout.getVisibility() != 0) {
            this.mNoResultLayout.setVisibility(0);
        }
    }

    private void hideNoResultLayout() {
        if (this.mNoResultLayout.getVisibility() != 8) {
            this.mNoResultLayout.setVisibility(8);
        }
    }

    private void initConfigBookCardUI(View view, List<com.qq.reader.module.bookstore.qnative.card.a> list) {
        initCardListView(view, false);
        if (this.mAdapter == null) {
            this.mAdapter = new f(getApplicationContext());
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
            try {
                if (this.mHoldPage != null) {
                    this.mHoldPage.q();
                    List m = this.mHoldPage.m();
                    if (m != null && m.size() > 0) {
                        BaseListCard listBookCard = getListBookCard(m);
                        if (listBookCard != null) {
                            listBookCard.notifyDataSetChanged();
                        } else if (this.mAdapter != null) {
                            this.mAdapter.a(this.mHoldPage);
                            if (this.mAdapter.b() || this.mXListView.getAdapter() == null) {
                                this.mXListView.setAdapter(this.mAdapter);
                            } else {
                                this.mAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.a("LOGGER_NATIVE", e.toString());
            }
        }
    }

    public void onPreLoad() {
    }

    public void onLoading() {
        this.mHandler.sendEmptyMessage(500002);
    }

    public void setTitleInfo(JSONObject jSONObject) {
        this.mTabJson = jSONObject;
        com.qq.reader.common.monitor.debug.c.a(tag, "settitleInfo :  " + jSONObject.toString());
        if (this.mTabJson != null) {
            JSONArray optJSONArray = this.mTabJson.optJSONArray("actionIdList");
            this.mUrlendString = new StringBuffer();
            this.mTextString = new StringBuffer();
            int i = 0;
            int i2 = 0;
            while (optJSONArray != null && i < optJSONArray.length()) {
                int optInt;
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    optInt = optJSONObject.optInt("actionId");
                    String optString = optJSONObject.optString("title");
                    optJSONObject.optBoolean("isSelected");
                    if (i != 0) {
                        if (this.mUrlendString.length() == 0) {
                            this.mUrlendString.append(optInt);
                            optInt = i2;
                        } else {
                            this.mUrlendString.append(",").append(optInt);
                            optInt = i2;
                        }
                    }
                    if (this.mTextString.length() == 0) {
                        this.mTextString.append(optString);
                    } else {
                        this.mTextString.append(",").append(optString);
                    }
                } else {
                    optInt = i2;
                }
                i++;
                i2 = optInt;
            }
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable(this) {
                    final /* synthetic */ NativePageFragmentforClassifyOld a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.mTopSelectedTextView.setText(this.a.mTextString.toString().replace("全部,", "").replace(",", " - "));
                    }
                });
            }
            if (this.enterBundle != null) {
                if (this.mUrlendString == null || this.mUrlendString.toString().equalsIgnoreCase("")) {
                    this.mUrlendString.append("-1,-1,6");
                }
                this.enterBundle.putString("KEY_ACTIONTAG", this.mUrlendString.toString());
                this.enterBundle.putString("KEY_ACTIONID", String.valueOf(i2));
                this.enterBundle.putString("KEY_JUMP_PAGENAME", tag);
            }
            this.mHoldPage = e.a().a(this.enterBundle, this);
            com.qq.reader.common.monitor.debug.c.a(tag, " Url =============  " + ((ag) this.mHoldPage).g());
            tryObtainDataWithNet(true, false);
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
                com.qq.reader.common.monitor.debug.c.a(tag, "loadPage " + this.enterBundle.toString());
                this.mHoldPage = e.a().a(this.enterBundle, this);
                tryObtainDataWithNet(true, false);
                return;
            }
            notifyData();
            hideLoadingPage();
        }
    }

    private void loadNextPage() {
        if (this.mCurPageStatus != 0) {
            return;
        }
        if (this.mHoldPage.s()) {
            this.mNextBundle = new Bundle(this.enterBundle);
            long r = this.mHoldPage.r();
            if (r != 0) {
                this.mNextBundle.putLong("KEY_PAGEINDEX", r);
                this.mNextBundle.putString("URL_BUILD_PERE_SIGNAL", "nextpage");
            }
            this.mNextPage = e.a().a(this.mNextBundle, this);
            this.mCurPageStatus = 1;
            this.mNextPage.a(1001);
            d.b().a(getApplicationContext(), this.mNextPage, this.mHandler, true);
        } else if (this.mXListView != null) {
            this.mXListView.g();
        }
    }

    public void onLoadFinished() {
    }

    public void doFunction(Bundle bundle) {
    }

    public b getMoreMenu(Bundle bundle) {
        this.mBottomContextMenu = new b(getActivity());
        this.mBottomContextMenu.a(0, "删除", bundle);
        this.mBottomContextMenu.a(new OnCancelListener(this) {
            final /* synthetic */ NativePageFragmentforClassifyOld a;

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
                        this.mAdapter.a(this.mHoldPage);
                        if (this.mAdapter.b() || this.mXListView.getAdapter() == null) {
                            this.mXListView.setAdapter(this.mAdapter);
                        } else {
                            this.mAdapter.notifyDataSetChanged();
                        }
                    }
                    if (!this.mHoldPage.s()) {
                        this.mXListView.c();
                    }
                }
                this.mNextPage = null;
                this.mCurPageStatus = 0;
            } else if (this.mHoldPage != null) {
                List m = this.mHoldPage.m();
                com.qq.reader.common.monitor.debug.c.a(tag, "  =============================     " + m.getClass().getSimpleName() + " size " + m.size());
                if (m == null || m.size() <= 0) {
                    if (!(this.mLastListCard == null || this.mLastListCard.getItemList() == null)) {
                        this.mLastListCard.getItemList().clear();
                        this.mLastListCard.notifyDataSetChanged();
                        this.mXListView.g();
                    }
                    showNoResultLayout();
                    return;
                }
                BaseListCard listBookCard = getListBookCard(m);
                this.mLastListCard = listBookCard;
                if (listBookCard != null) {
                    listBookCard.setIsFromCharis(this.isFromCharts);
                    initListBookCardUI(this.root, listBookCard);
                    return;
                }
                initConfigBookCardUI(this.root, m);
            }
        }
    }

    private BaseListCard getListBookCard(List<com.qq.reader.module.bookstore.qnative.card.a> list) {
        if (list != null && list.size() == 1) {
            com.qq.reader.module.bookstore.qnative.card.a aVar = (com.qq.reader.module.bookstore.qnative.card.a) list.get(0);
            if (aVar != null && (aVar instanceof BaseListCard)) {
                return (BaseListCard) aVar;
            }
        }
        return null;
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
                try {
                    if (message.obj != null) {
                        Object obj = message.obj;
                        com.qq.reader.common.monitor.debug.c.a(tag, "handlerMessageImp " + obj.getClass().getSimpleName());
                        if (obj instanceof com.qq.reader.module.bookstore.qnative.page.c) {
                            com.qq.reader.module.bookstore.qnative.page.b bVar = (com.qq.reader.module.bookstore.qnative.page.c) obj;
                            if (bVar.g().indexOf("nextpage") == -1) {
                                this.mHoldPage.a(bVar);
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
            case 10000002:
                if (message.obj != null) {
                    try {
                        if (this.mAdapter != null) {
                            this.mAdapter.a();
                            this.mAdapter.notifyDataSetChanged();
                        } else {
                            com.qq.reader.common.monitor.debug.c.a(tag, "Classify_selected " + message.obj.toString());
                            setTitleInfo(new JSONObject(message.obj.toString()));
                        }
                        showLoadingPageWithOutgoneList();
                        break;
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        break;
                    }
                }
                break;
        }
        return super.handleMessageImp(message);
    }

    private void showLoadingPageWithOutgoneList() {
        hideFailedPage();
        if (this.mLoadingProgress != null) {
            this.mLoadingProgress.setVisibility(0);
        }
    }

    private void showLoadingPage() {
        hideFailedPage();
        if (this.mLoadingProgress != null) {
            this.mLoadingProgress.setVisibility(0);
        }
    }

    private void hideLoadingPage() {
        hideFailedPage();
        if (this.mXListView != null) {
            this.mXListView.setVisibility(0);
        }
        if (this.mLoadingProgress != null) {
            this.mLoadingProgress.setVisibility(8);
        }
    }

    protected void showFailedPage() {
        if (this.mLoadingProgress != null) {
            this.mLoadingProgress.setVisibility(8);
        }
        af.a(getApplicationContext(), (int) R.string.dialog_net_error, 0).a();
        if (this.mXListView == null || this.mXListView.getVisibility() != 0) {
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

    private void tryObtainDataWithNet(boolean z, boolean z2) {
        com.qq.reader.common.monitor.debug.c.a(tag, "tryObtainDataWithNet  in Fragment " + ((com.qq.reader.module.bookstore.qnative.page.impl.af) this.mHoldPage).g());
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
}

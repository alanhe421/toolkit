package com.qq.reader.module.bookstore.qnative.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.imageloader.core.a.a;
import com.qq.reader.common.utils.CustomArrayList;
import com.qq.reader.module.bookstore.qnative.a.f;
import com.qq.reader.module.bookstore.qnative.card.BaseListCard;
import com.qq.reader.module.bookstore.qnative.d;
import com.qq.reader.module.bookstore.qnative.e;
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

public class NativePageFragmentforClassify_1 extends NativePageFragment implements Callback, OnScrollListener {
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
    protected b mBottomContextMenu;
    protected int mCurPageStatus = 0;
    protected View mFailedLayout = null;
    protected WeakReferenceHandler mHandler;
    private View mHeaderView;
    protected View mLoadingProgress = null;
    protected Bundle mNextBundle = null;
    public com.qq.reader.module.bookstore.qnative.page.b mNextPage = null;
    private int mPageType;
    a mPauseOnScrollListener = new a(c.a((Fragment) this).a(), true, true);
    private JSONObject mTabJson;
    private LinearLayout mTopSelectedLayout;
    private LinearLayout mTopSelectedResultLayout;
    private TextView mTopSelectedTextView;
    private StringBuffer mUrlendString = new StringBuffer();
    protected XListView mXListView = null;
    protected RelativeLayout rl_parentLayout;
    protected View root;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.root = layoutInflater.inflate(R.layout.localbooklist_classify_layout_1, null);
        init(this.root);
        this.mHandler = new WeakReferenceHandler(this);
        super.onCreateView(layoutInflater, viewGroup, bundle);
        return this.root;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.mHoldPage.s()) {
            this.mPauseOnScrollListener.onScrollStateChanged(absListView, i);
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.mHoldPage.s()) {
            this.mPauseOnScrollListener.onScroll(absListView, i, i2, i3);
        }
    }

    private void init(View view) {
        this.mLoadingProgress = view.findViewById(R.id.loading_layout);
        this.mTopSelectedLayout = (LinearLayout) view.findViewById(R.id.topcontainer);
        this.mTopSelectedResultLayout = (LinearLayout) view.findViewById(R.id.topselectedlayout);
        this.mTopSelectedTextView = (TextView) view.findViewById(R.id.top_selectedtext);
        this.mTopSelectedResultLayout.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativePageFragmentforClassify_1 a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.mHeaderView.getVisibility() != 8) {
                    this.a.mHeaderView.setVisibility(8);
                }
                if (this.a.mTopSelectedResultLayout.getVisibility() != 8) {
                    this.a.mTopSelectedResultLayout.setVisibility(8);
                }
            }
        });
        this.mFailedLayout = view.findViewById(R.id.loading_failed_layout);
        this.rl_parentLayout = (RelativeLayout) view.findViewById(R.id.rl_parent);
        this.mFailedLayout.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativePageFragmentforClassify_1 a;

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

    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initCardListView(View view, boolean z) {
        if (this.mXListView == null) {
            com.qq.reader.common.monitor.debug.c.a(tag, "initCardListView " + this);
            this.mXListView = (XListView) view.findViewById(R.id.list_layout);
            this.mXListView.setCrashTag(CustomArrayList.Class_NativePageFragmentforClassify_1);
            this.mXListView.setPullRefreshEnable(false);
            this.mXListView.setOnScrollListener(this);
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
                    final /* synthetic */ NativePageFragmentforClassify_1 a;

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

    private void initListBookCardUI(View view, BaseListCard baseListCard) {
        com.qq.reader.common.monitor.debug.c.a(tag, "initListBookCardUi " + baseListCard.getClass().getSimpleName());
        initCardListView(view, true);
        baseListCard.attachView(this.mXListView);
        baseListCard.notifyDataSetChanged();
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
            final StringBuffer stringBuffer = new StringBuffer();
            int i = 0;
            int i2 = 0;
            while (optJSONArray != null && i < optJSONArray.length()) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    int optInt = optJSONObject.optInt("actionId");
                    String optString = optJSONObject.optString("title");
                    boolean optBoolean = optJSONObject.optBoolean("isSelected");
                    if (i == 0) {
                        i2 = optInt;
                    } else if (this.mUrlendString.length() == 0) {
                        this.mUrlendString.append(optInt);
                    } else {
                        this.mUrlendString.append(",").append(optInt);
                    }
                    if (optBoolean) {
                        if (stringBuffer.length() == 0) {
                            stringBuffer.append(optString);
                        } else {
                            stringBuffer.append(",").append(optString);
                        }
                    }
                }
                i++;
            }
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable(this) {
                    final /* synthetic */ NativePageFragmentforClassify_1 b;

                    public void run() {
                        this.b.mTopSelectedTextView.setText(stringBuffer.toString());
                    }
                });
            }
            Bundle bundle = new Bundle();
            if (this.mUrlendString == null || this.mUrlendString.toString().equalsIgnoreCase("")) {
                this.mUrlendString.append("-1,-1,6");
            }
            bundle.putString("KEY_ACTIONTAG", this.mUrlendString.toString());
            bundle.putString("KEY_ACTIONID", String.valueOf(i2));
            bundle.putString("KEY_JUMP_PAGENAME", tag);
            this.mHoldPage = e.a().a(bundle, this);
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
                com.qq.reader.common.monitor.f.a("LBPageFragment", "NativePageFragmentforClassify_1  loadPage exception : " + e.toString());
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
            if (this.mNextBundle == null) {
                this.mNextBundle = new Bundle(this.enterBundle);
            }
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
            this.mXListView.c();
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
            final /* synthetic */ NativePageFragmentforClassify_1 a;

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
                com.qq.reader.common.monitor.debug.c.a(tag, "  =============================     " + m.getClass().getSimpleName());
                if (m != null && m.size() > 0) {
                    BaseListCard listBookCard = getListBookCard(m);
                    if (listBookCard != null) {
                        listBookCard.setIsFromCharis(this.isFromCharts);
                        initListBookCardUI(this.root, listBookCard);
                        return;
                    }
                    initConfigBookCardUI(this.root, m);
                }
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
                            com.qq.reader.common.monitor.debug.c.a(tag, "adapter " + this.mXListView.getAdapter().getClass().getSimpleName());
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

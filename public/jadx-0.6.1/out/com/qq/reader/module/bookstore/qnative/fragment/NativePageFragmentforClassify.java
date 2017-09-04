package com.qq.reader.module.bookstore.qnative.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.CustomArrayList;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.module.bookstore.qnative.a.f;
import com.qq.reader.module.bookstore.qnative.card.BaseListCard;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCardCommon;
import com.qq.reader.module.bookstore.qnative.d;
import com.qq.reader.module.bookstore.qnative.e;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.impl.ag;
import com.qq.reader.module.bookstore.search.AbsSearchTabView;
import com.qq.reader.module.bookstore.search.SearchTabInfo.SearchActionTagLv3InitialDataModel;
import com.qq.reader.module.bookstore.search.a;
import com.qq.reader.module.bookstore.search.h;
import com.qq.reader.view.af;
import com.qq.reader.view.linearmenu.b;
import com.qq.reader.view.pullupdownlist.XListView;
import com.tencent.feedback.proguard.R;
import com.tencent.mm.performance.WxPerformanceHandle;
import com.tencent.util.WeakReferenceHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class NativePageFragmentforClassify extends NativePageFragment implements Callback {
    public static final int DIALOG_DELETE_REPLY = 609;
    protected static final int MENU_CANCEL_TOPREPLY = 2;
    public static final int MENU_DELETE = 0;
    protected static final int MENU_REPLY = 1;
    protected static final int MENU_SET_TOPREPLY = 3;
    protected static final int STATUS_BUSY = 1;
    protected static final int STATUS_FREE = 0;
    private static final String tag = "classify";
    protected Bundle enterBundle = null;
    private boolean isAfterSelect = false;
    private boolean isFromCharts = false;
    private String m2levelId = "";
    private f mAdapter;
    protected b mBottomContextMenu;
    protected int mCurPageStatus = 0;
    protected View mFailedLayout = null;
    protected WeakReferenceHandler mHandler;
    private BaseListCard mLastListCard = null;
    private int mLastPosition = -1;
    protected View mLoadingProgress = null;
    protected Bundle mNextBundle = null;
    public com.qq.reader.module.bookstore.qnative.page.b mNextPage = null;
    private View mNoResultLayout;
    private int mPageType;
    private int mScrollState = 0;
    private JSONObject mTabJson;
    private AbsSearchTabView mTopSelector = null;
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

    private void init(View view) {
        this.mLoadingProgress = view.findViewById(R.id.loading_layout);
        this.mFailedLayout = view.findViewById(R.id.loading_failed_layout);
        this.rl_parentLayout = (RelativeLayout) view.findViewById(R.id.rl_parent);
        this.mFailedLayout.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativePageFragmentforClassify a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.reLoadData();
            }
        });
        this.mNoResultLayout = view.findViewById(R.id.noresult_layout);
        this.mTopSelector = (AbsSearchTabView) view.findViewById(R.id.top_selector);
        this.mTopSelector.setUseLocation(getSudId());
        this.mTopSelector.setInitSelectedItems(getInitSeletedItem());
        this.mTopSelector.a(h.a(null, "search/default_tab_info.txt"));
        this.mTopSelector.setSearchTabListener(new a(this) {
            final /* synthetic */ NativePageFragmentforClassify a;

            {
                this.a = r1;
            }

            public void a_(String str) {
                Message obtainMessage = this.a.mHandler.obtainMessage();
                obtainMessage.obj = str;
                obtainMessage.what = 10000002;
                this.a.mHandler.sendMessage(obtainMessage);
            }

            public void a(int i, int i2) {
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, String.valueOf(i));
                i.a(this.a.getReportSelectId(), hashMap, ReaderApplication.getApplicationImp());
                StatisticsManager.a().a(this.a.getReportSelectId(), hashMap);
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
        if (this.mTopSelector != null) {
            this.mTopSelector.a(getFromActivity());
        }
    }

    private void initCardListView(View view, boolean z) {
        if (getActivity() != null) {
            if (this.mXListView == null) {
                c.a(tag, "initCardListView " + this);
                this.mXListView = (XListView) view.findViewById(R.id.list_layout);
                this.mXListView.setCrashTag(CustomArrayList.Class_NativePageFragmentforClassify);
                this.mXListView.setPullRefreshEnable(false);
                Bundle bundle = new Bundle();
                if (!(this.mHoldPage == null || ((ag) this.mHoldPage).x() == null)) {
                    bundle.putString("classify_list", ((ag) this.mHoldPage).x().toString());
                }
            }
            if (this.mHoldPage != null) {
                this.mXListView.setVisibility(0);
                int i = 1;
                if (!(z || this.mHoldPage.s())) {
                    i = 0;
                }
                this.mXListView.setPullLoadEnable(i | isShowLoadAllInFirstPage());
                if (this.mHoldPage.s()) {
                    this.mXListView.setXListViewListener(new XListView.a(this) {
                        final /* synthetic */ NativePageFragmentforClassify a;

                        {
                            this.a = r1;
                        }

                        public void a() {
                            this.a.mHandler.sendEmptyMessage(500005);
                        }
                    });
                    this.mXListView.e();
                    return;
                }
                this.mXListView.c();
            }
        }
    }

    private void initListBookCardUI(View view, BaseListCard baseListCard) {
        c.a(tag, "initListBookCardUi " + baseListCard.getClass().getSimpleName());
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
        hideNoResultLayout();
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
                c.a("LOGGER_NATIVE", e.toString());
            }
        }
    }

    public void onPreLoad() {
    }

    public void onLoading() {
        if (this.mHandler != null) {
            this.mHandler.sendEmptyMessage(500002);
        }
    }

    public void doSelect(JSONObject jSONObject) {
        if (jSONObject != null) {
            CharSequence optString = jSONObject.optString("actionId");
            CharSequence optString2 = jSONObject.optString("actionTag");
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, optString);
            hashMap.put(WxPerformanceHandle.MESSAGE_TAG, optString2);
            i.a(getReportSearchId(), hashMap, ReaderApplication.getApplicationImp());
            StatisticsManager.a().a(getReportSearchId(), hashMap);
            String str = null;
            if (this.enterBundle != null) {
                str = this.enterBundle.getString("KEY_ACTIONID");
            }
            String str2 = "0";
            if (!TextUtils.isEmpty(optString)) {
                str = optString;
            } else if (!TextUtils.isEmpty(this.m2levelId)) {
                str = this.m2levelId;
            } else if (TextUtils.isEmpty(str)) {
                str = str2;
            }
            if (this.enterBundle != null) {
                this.enterBundle.putString("KEY_ACTIONID", str);
                Bundle bundle = this.enterBundle;
                str2 = "KEY_ACTIONTAG";
                if (TextUtils.isEmpty(optString2)) {
                    str = ",-1,-1,-1,-1,6";
                } else {
                    CharSequence charSequence = optString2;
                }
                bundle.putString(str2, str);
            }
            this.mHoldPage = e.a().a(this.enterBundle, this);
            c.a(tag, " Url =============  " + ((ag) this.mHoldPage).g());
            this.isAfterSelect = true;
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
                c.a(tag, "loadPage " + this.enterBundle.toString());
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
            final /* synthetic */ NativePageFragmentforClassify a;

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

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void notifyData() {
        /*
        r4 = this;
        r2 = 0;
        r0 = r4.isDetached();
        if (r0 == 0) goto L_0x0008;
    L_0x0007:
        return;
    L_0x0008:
        r0 = r4.mNextPage;
        if (r0 == 0) goto L_0x006a;
    L_0x000c:
        r0 = r4.mCurPageStatus;
        r1 = 1;
        if (r0 != r1) goto L_0x006a;
    L_0x0011:
        r0 = r4.mNextPage;
        r0 = r0.m();
        r0 = r0.size();
        if (r0 > 0) goto L_0x0028;
    L_0x001d:
        r0 = r4.mXListView;
        r0.c();
    L_0x0022:
        r0 = 0;
        r4.mNextPage = r0;
        r4.mCurPageStatus = r2;
        goto L_0x0007;
    L_0x0028:
        r0 = r4.mHoldPage;
        r1 = r4.mNextPage;
        r0.addMore(r1);
        r0 = r4.mXListView;
        r0.e();
        r0 = r4.mAdapter;
        if (r0 == 0) goto L_0x0056;
    L_0x0038:
        r0 = r4.mAdapter;
        r1 = r4.mHoldPage;
        r0.a(r1);
        r0 = r4.mAdapter;
        r0 = r0.b();
        if (r0 != 0) goto L_0x004f;
    L_0x0047:
        r0 = r4.mXListView;
        r0 = r0.getAdapter();
        if (r0 != 0) goto L_0x0064;
    L_0x004f:
        r0 = r4.mXListView;
        r1 = r4.mAdapter;
        r0.setAdapter(r1);
    L_0x0056:
        r0 = r4.mHoldPage;
        r0 = r0.s();
        if (r0 != 0) goto L_0x0022;
    L_0x005e:
        r0 = r4.mXListView;
        r0.c();
        goto L_0x0022;
    L_0x0064:
        r0 = r4.mAdapter;
        r0.notifyDataSetChanged();
        goto L_0x0056;
    L_0x006a:
        r0 = r4.mHoldPage;
        if (r0 == 0) goto L_0x0007;
    L_0x006e:
        r0 = r4.isAfterSelect;
        if (r0 != 0) goto L_0x015b;
    L_0x0072:
        r0 = r4.mHoldPage;
        r0 = r0.k();
        if (r0 == 0) goto L_0x00be;
    L_0x007a:
        r1 = r0.e();
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 != 0) goto L_0x008a;
    L_0x0084:
        r1 = r0.e();
        r4.m2levelId = r1;
    L_0x008a:
        r1 = r0.a();
        r2 = android.text.TextUtils.isEmpty(r1);
        if (r2 != 0) goto L_0x00be;
    L_0x0094:
        r2 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x013e }
        r2.<init>(r1);	 Catch:{ JSONException -> 0x013e }
        r1 = r4.mTopSelector;	 Catch:{ JSONException -> 0x013e }
        r3 = r4.getSelectIdArray(r2);	 Catch:{ JSONException -> 0x013e }
        r1.setMustSelectedIds(r3);	 Catch:{ JSONException -> 0x013e }
        r1 = r4.mTopSelector;	 Catch:{ JSONException -> 0x013e }
        r3 = r4.getSelectId(r2);	 Catch:{ JSONException -> 0x013e }
        r1.a(r3);	 Catch:{ JSONException -> 0x013e }
        r1 = r0.k();	 Catch:{ JSONException -> 0x013e }
        switch(r1) {
            case 4: goto L_0x0128;
            case 5: goto L_0x0144;
            default: goto L_0x00b2;
        };	 Catch:{ JSONException -> 0x013e }
    L_0x00b2:
        r0 = r4.mTopSelector;	 Catch:{ JSONException -> 0x013e }
        r1 = "search/default_tab_info.txt";
        r1 = com.qq.reader.module.bookstore.search.h.a(r2, r1);	 Catch:{ JSONException -> 0x013e }
        r0.a(r1);	 Catch:{ JSONException -> 0x013e }
    L_0x00be:
        r0 = new java.util.HashMap;
        r0.<init>();
        r1 = "origin";
        r2 = r4.m2levelId;
        r0.put(r1, r2);
        r1 = "event_C248";
        r2 = com.qq.reader.ReaderApplication.getApplicationImp();
        com.qq.reader.common.monitor.i.a(r1, r0, r2);
        r0 = r4.mHoldPage;
        r0 = r0.m();
        r1 = "classify";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "  =============================     ";
        r2 = r2.append(r3);
        r3 = r0.getClass();
        r3 = r3.getSimpleName();
        r2 = r2.append(r3);
        r3 = " size ";
        r2 = r2.append(r3);
        r3 = r0.size();
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.qq.reader.common.monitor.debug.c.a(r1, r2);
        if (r0 == 0) goto L_0x0166;
    L_0x010e:
        r1 = r0.size();
        if (r1 <= 0) goto L_0x0166;
    L_0x0114:
        r1 = r4.getListBookCard(r0);
        r4.mLastListCard = r1;
        if (r1 == 0) goto L_0x015f;
    L_0x011c:
        r0 = r4.isFromCharts;
        r1.setIsFromCharis(r0);
        r0 = r4.root;
        r4.initListBookCardUI(r0, r1);
        goto L_0x0007;
    L_0x0128:
        r1 = r4.mTopSelector;	 Catch:{ JSONException -> 0x013e }
        r0 = r0.k();	 Catch:{ JSONException -> 0x013e }
        r1.setInfoType(r0);	 Catch:{ JSONException -> 0x013e }
        r0 = r4.mTopSelector;	 Catch:{ JSONException -> 0x013e }
        r1 = "search/comic_tab_info.txt";
        r1 = com.qq.reader.module.bookstore.search.h.a(r2, r1);	 Catch:{ JSONException -> 0x013e }
        r0.a(r1);	 Catch:{ JSONException -> 0x013e }
        goto L_0x00be;
    L_0x013e:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00be;
    L_0x0144:
        r1 = r4.mTopSelector;	 Catch:{ JSONException -> 0x013e }
        r0 = r0.k();	 Catch:{ JSONException -> 0x013e }
        r1.setInfoType(r0);	 Catch:{ JSONException -> 0x013e }
        r0 = r4.mTopSelector;	 Catch:{ JSONException -> 0x013e }
        r1 = "search/audio_tab_info.txt";
        r1 = com.qq.reader.module.bookstore.search.h.a(r2, r1);	 Catch:{ JSONException -> 0x013e }
        r0.a(r1);	 Catch:{ JSONException -> 0x013e }
        goto L_0x00be;
    L_0x015b:
        r4.isAfterSelect = r2;
        goto L_0x00be;
    L_0x015f:
        r1 = r4.root;
        r4.initConfigBookCardUI(r1, r0);
        goto L_0x0007;
    L_0x0166:
        r0 = r4.mLastListCard;
        if (r0 == 0) goto L_0x0172;
    L_0x016a:
        r0 = r4.mLastListCard;
        r0 = r0.getItemList();
        if (r0 != 0) goto L_0x0177;
    L_0x0172:
        r4.showNoResultLayout();
        goto L_0x0007;
    L_0x0177:
        r0 = r4.mLastListCard;
        r0 = r0.getItemList();
        r0.clear();
        r0 = r4.mLastListCard;
        r0.notifyDataSetChanged();
        r0 = r4.mXListView;
        r0.g();
        goto L_0x0172;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforClassify.notifyData():void");
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
                        c.a(tag, "handlerMessageImp " + obj.getClass().getSimpleName());
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
                        c.a("LOGGER_NATIVE", "msg.obj == null");
                    }
                } catch (Exception e) {
                    c.a("LOGGER_NATIVE", e.toString());
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
                        c.a(tag, "Classify_selected " + message.obj.toString());
                        doSelect(new JSONObject(message.obj.toString()));
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
        c.a(tag, "tryObtainDataWithNet  in Fragment " + ((com.qq.reader.module.bookstore.qnative.page.impl.af) this.mHoldPage).g());
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

    protected int getSudId() {
        return 6;
    }

    private ArrayList<SearchActionTagLv3InitialDataModel> getInitSeletedItem() {
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            ArrayList<SearchActionTagLv3InitialDataModel> arrayList = (ArrayList) extras.getSerializable("PARAM_CLASSIFY_MUST_SELECTED_DATA");
            if (arrayList != null) {
                return arrayList;
            }
        }
        return null;
    }

    protected int getSelectId(JSONObject jSONObject) {
        Object optString = jSONObject.optString("actionId");
        if (TextUtils.isEmpty(optString)) {
            return 0;
        }
        return Integer.parseInt(optString);
    }

    protected int[] getSelectIdArray(JSONObject jSONObject) {
        Object optString = jSONObject.optString("actionId");
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(optString)) {
            return new int[0];
        }
        String[] split = optString.split(",");
        int i = 0;
        while (split != null && i < split.length) {
            if (!TextUtils.isEmpty(split[i])) {
                try {
                    arrayList.add(Integer.valueOf(Integer.parseInt(split[i])));
                } catch (Exception e) {
                    c.e("Err", e.getMessage());
                }
            }
            i++;
        }
        if (arrayList == null || arrayList.size() <= 0) {
            return new int[0];
        }
        int[] iArr = new int[arrayList.size()];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            iArr[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        return iArr;
    }

    protected String getReportSelectId() {
        return "event_C249";
    }

    protected String getReportSearchId() {
        return "event_C82";
    }

    public boolean onBackPress() {
        super.onBackPress();
        c.e("TAG", getClass().getSimpleName() + " onBackPress");
        if (this.mTopSelector == null || !this.mTopSelector.a(getFromActivity())) {
            return false;
        }
        return true;
    }

    public boolean isShowLoadAllInFirstPage() {
        return true;
    }
}

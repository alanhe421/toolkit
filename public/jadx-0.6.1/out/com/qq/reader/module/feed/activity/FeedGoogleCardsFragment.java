package com.qq.reader.module.feed.activity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.nhaarman.listviewanimations.itemmanipulation.b.b;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.MainActivity;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.CustomArrayList;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.m;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.common.widget.swipelistview.SwipeMenuListView;
import com.qq.reader.module.bookshelf.j;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.feed.card.FeedNoMoreBottomCard;
import com.qq.reader.module.feed.card.FeedNoMoreTopCard;
import com.qq.reader.module.feed.card.FeedRookieEntranceCard;
import com.qq.reader.module.feed.card.FeedTodayFlashSaleCard;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.qq.reader.module.feed.data.impl.e;
import com.qq.reader.module.feed.data.impl.f;
import com.qq.reader.module.feed.mypreference.MyReadingGeneActivity;
import com.qq.reader.module.feed.swipe.FeedSwipeLayout;
import com.qq.reader.module.feed.widget.FeedListViewFooter;
import com.qq.reader.view.FloatingFadeAnimView;
import com.qq.reader.view.ap;
import com.qq.reader.view.k;
import com.qq.reader.view.l;
import com.qq.reader.view.pullupdownlist.XListViewFooter;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONObject;

public class FeedGoogleCardsFragment extends FeedGoogleCardBaseFragment implements b, a, FeedAdapter.a, com.qq.reader.view.animation.a, l {
    private static final int INITIAL_DELAY_MILLIS = 300;
    public static final int MAXFEEDREFRESHNOACTIONCOUNT = 3;
    private static final int MAX_Enter_Feed_Should_Tips_COUNT = 3;
    private static final String TAG = "GoogleCardsActivity";
    public static final float TITLE_BAR_ANIMATION_FACTOR = 0.6666667f;
    private final int DATA_FROM_CACHE = 101;
    private final int DATA_FROM_NET = 100;
    BroadcastReceiver actUpdateReceiver = new 9(this);
    private boolean hasShowRookieAnim;
    BroadcastReceiver loginOkReciver = new 10(this);
    BroadcastReceiver loginOutReceiver = new 23(this);
    private com.qq.reader.module.feed.a mBrandExpansionHelper;
    private int mCurSearchIndex = 0;
    private int mEnterFeedIndex = -1;
    protected FeedAdapter mFeedAdapter;
    private com.qq.reader.module.feed.head.a mFeedHead;
    private SwipeMenuListView mFeedListView;
    private int mFeedRefreshNoActionCount;
    private View mFloatEntranceBar;
    private BroadcastReceiver mGotAvatarBroadcastReceiver = new 12(this);
    private View mHeadTitleBar;
    private boolean mIsManualRefreshTip = false;
    private boolean mIsNeedGoToTop = false;
    private boolean mIsNetSucess = false;
    private boolean mIsShowClickTabToTopTip = false;
    private boolean mIsTitleBarInvisible = true;
    private View mOperatingActivityCloseView;
    private FloatingFadeAnimView mOperatingActivityEntrance;
    private View mOperatingActivityEntranceView;
    private Map<String, String> mPreferMap = new HashMap();
    private SwipeRefreshLayout mPullToRefreshView;
    private FloatingFadeAnimView mRookieZoneEntrance;
    private View mRootView;
    private ArrayList<String> mSearchWords = new ArrayList();
    private Map<String, String> mStatMap = new HashMap();
    private com.qq.reader.view.b.b mTip;
    private View mTitleBarAvatarView;
    private int mTitleBarEndColor;
    private int mTitleBarStartColor;
    private TextView mTitleView;
    private BroadcastReceiver myBroadcastReceiver = new 18(this);
    private boolean shownRookieGiftAnimation;
    private Map<String, String> statMap;
    private SwipeMenuListView.a swipeMenuUpdate = new 1(this);
    BroadcastReceiver switchGeneReceiver = new 22(this);
    protected TextView toastView = null;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        c.a("ani", "FeedGoogleCardsActivity oncreate ");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.activity_googlelayout, null);
        }
        return this.mRootView;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        com.qq.reader.common.c.a.a(false);
        setIsShowNightMask(false);
        initUI();
        addAdvHeader();
        initData();
        getFirstPageData(true);
        if (getActivity() != null) {
            getActivity().registerReceiver(this.loginOkReciver, new IntentFilter("com.qq.reader.loginok"));
            getActivity().registerReceiver(this.actUpdateReceiver, new IntentFilter(com.qq.reader.common.c.a.cH));
            getActivity().registerReceiver(this.mGotAvatarBroadcastReceiver, new IntentFilter(com.qq.reader.common.c.a.ct));
            getActivity().registerReceiver(this.switchGeneReceiver, new IntentFilter(com.qq.reader.common.c.a.cL));
            getActivity().registerReceiver(this.loginOutReceiver, new IntentFilter(com.qq.reader.common.c.a.cN));
        }
        d.h(ReaderApplication.getApplicationImp(), System.currentTimeMillis());
        showMyView();
        setStatPageName("feedbookstorepage");
        this.mIsShowClickTabToTopTip = d.bs(getApplicationContext());
        this.mIsManualRefreshTip = d.bu(getApplicationContext());
        this.mHandler.post(new 24(this));
    }

    private void initUI() {
        if (this.mRootView != null && getActivity() != null) {
            this.mRookieZoneEntrance = (FloatingFadeAnimView) this.mRootView.findViewById(R.id.rookie_zone_entrance);
            this.mRookieZoneEntrance.setOnClickListener(new 25(this));
            if (com.qq.reader.appconfig.b.a) {
                this.mRookieZoneEntrance.setOnLongClickListener(new 26(this));
            }
            this.mOperatingActivityEntrance = (FloatingFadeAnimView) this.mRootView.findViewById(R.id.operating_activity_entrance);
            this.mOperatingActivityCloseView = this.mRootView.findViewById(R.id.operating_activity_close_view);
            this.mOperatingActivityCloseView.setOnClickListener(new 27(this));
            this.mTitleView = (TextView) this.mRootView.findViewById(R.id.titlebar_title);
            this.mPullToRefreshView = (SwipeRefreshLayout) this.mRootView.findViewById(R.id.refreshview);
            this.mFeedListView = (SwipeMenuListView) this.mRootView.findViewById(R.id.activity_googlecards_listview);
            this.mFeedListView.setCrashTag(CustomArrayList.Class_FeedGoogleCardsActivity);
            this.toastView = (TextView) this.mRootView.findViewById(R.id.main_toastbar);
            XListViewFooter feedListViewFooter = new FeedListViewFooter(getActivity());
            feedListViewFooter.setOnClickListener(null);
            this.mFeedListView.setXListFooter(feedListViewFooter);
            this.mFeedListView.setUpdateMenuInterface(this.swipeMenuUpdate);
            this.mFeedListView.setPullLoadEnable(true);
            this.mHeadTitleBar = this.mRootView.findViewById(R.id.common_titler);
            this.mRootView.findViewById(R.id.action_btn).setOnClickListener(new 28(this));
            this.mTitleBarStartColor = ReaderApplication.getApplicationImp().getResources().getColor(R.color.skin_set_bookshelf_scroll_title_bar_start_color);
            this.mTitleBarEndColor = ReaderApplication.getApplicationImp().getResources().getColor(R.color.skin_set_bookshelf_scroll_title_bar_end_color);
            this.mTitleBarAvatarView = this.mRootView.findViewById(R.id.title_bar_left_view);
            this.mTitleBarAvatarView.setOnClickListener(new 2(this));
        }
    }

    private void initData() {
        if (getActivity() != null) {
            this.mPullToRefreshView.setOnRefreshListener(new 3(this));
            this.mFeedListView.setXListViewListener(new 4(this));
            this.mFeedAdapter = new FeedAdapter(getActivity());
            this.mFeedAdapter.a(this);
            ListAdapter aVar = new com.nhaarman.listviewanimations.a.a.a(new com.qq.reader.module.feed.swipe.a(this.mFeedAdapter, this, FeedSwipeLayout.a, R.id.ll_bottom_view, R.id.tv_bottom_textview));
            aVar.a(this.mFeedListView);
            this.mFeedListView.setAdapter(aVar);
            this.mFeedListView.setOnScrollListener(new 5(this, com.qq.reader.common.imageloader.c.a((Fragment) this).a(), true, true));
            this.mFeedListView.setOnItemClickListener(new 6(this));
            if (com.qq.reader.common.c.b.i == 1) {
                this.mFeedListView.setOnItemLongClickListener(new 7(this));
            }
        }
    }

    private void setSearchHint(String str) {
        if (str == null || "".equals(str)) {
            this.mTitleView.setText(getResources().getString(R.string.feed_titlebar_hint));
        } else {
            this.mTitleView.setText(str);
        }
    }

    private void doAnimateTitleBar(AbsListView absListView, int i) {
        if (i <= 0) {
            View childAt = absListView.getChildAt(0);
            if (childAt != null) {
                float abs = 1.0f - (Math.abs((float) childAt.getTop()) / ((float) childAt.getHeight()));
                ObjectAnimator ofObject;
                if (!this.mIsTitleBarInvisible && abs > 0.6666667f) {
                    this.mIsTitleBarInvisible = true;
                    ofObject = ObjectAnimator.ofObject(this.mHeadTitleBar, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(this.mTitleBarEndColor), Integer.valueOf(this.mTitleBarStartColor)});
                    ofObject.setDuration(200);
                    ofObject.start();
                    if (this.mBrandExpansionHelper != null) {
                        this.mBrandExpansionHelper.a(false);
                    }
                } else if (this.mIsTitleBarInvisible && abs < 0.6666667f) {
                    this.mIsTitleBarInvisible = false;
                    ofObject = ObjectAnimator.ofObject(this.mHeadTitleBar, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(this.mTitleBarStartColor), Integer.valueOf(this.mTitleBarEndColor)});
                    ofObject.setDuration(200);
                    ofObject.start();
                    if (this.mBrandExpansionHelper != null) {
                        this.mBrandExpansionHelper.a(true);
                    }
                }
            }
        } else if (this.mIsTitleBarInvisible) {
            this.mIsTitleBarInvisible = false;
            this.mHeadTitleBar.setBackgroundColor(this.mTitleBarEndColor);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 30001) {
            if (i2 == -1) {
                refreshWithoutPulldown(true);
            }
        } else if (i == 1006) {
            this.mFeedAdapter.a(i, i2, intent, this.mHandler);
        }
    }

    private void checkAndShowBrandExpansion() {
        if (isVisible()) {
            createBrandExpansion();
        }
    }

    private void createBrandExpansion() {
        if (mAdvDialog == null && com.qq.reader.module.feed.a.a() && getActivity() != null && this.mBrandExpansionHelper == null) {
            this.mBrandExpansionHelper = new com.qq.reader.module.feed.a((ViewGroup) getActivity().findViewById(16908290), getHandler());
            this.mBrandExpansionHelper.a(new 8(this));
        }
    }

    public void onDestroy() {
        if (this.mBrandExpansionHelper != null && this.mBrandExpansionHelper.e()) {
            this.mBrandExpansionHelper.d();
        }
        if (getActivity() != null) {
            getActivity().unregisterReceiver(this.loginOkReciver);
            getActivity().unregisterReceiver(this.actUpdateReceiver);
            getActivity().unregisterReceiver(this.mGotAvatarBroadcastReceiver);
            getActivity().unregisterReceiver(this.switchGeneReceiver);
            getActivity().unregisterReceiver(this.loginOutReceiver);
        }
        if (mAdvDialog != null) {
            mAdvDialog = null;
        }
        if (this.mFeedAdapter != null) {
            this.mFeedAdapter.j();
        }
        super.onDestroy();
        com.qq.reader.module.feed.loader.c.b().a();
    }

    private void addFloatEntranceToHeader(FeedBaseCard feedBaseCard) {
    }

    private void removeFloatEntranceBar() {
    }

    private void checkLoginState() {
    }

    protected boolean handleMessageImp(Message message) {
        e eVar;
        switch (message.what) {
            case 1:
                if (!ao.o(getApplicationContext())) {
                    return true;
                }
                com.qq.reader.cservice.adv.b.a(getApplicationContext()).a();
                return true;
            case 8012:
                if (this.mFeedHead != null) {
                    this.mFeedHead.a(com.qq.reader.cservice.adv.b.a(getApplicationContext()).b("103170"));
                    this.mFeedHead.k();
                }
                checkAndShowBrandExpansion();
                showChannelAdv();
                showOperatingAdv();
                j.a(this.mTitleBarAvatarView);
                return true;
            case 500000:
            case 500001:
                boolean z;
                this.mFeedAdapter.j();
                this.mFeedAdapter.e();
                f fVar = (f) message.obj;
                List<FeedBaseCard> x = fVar.x();
                this.mFeedAdapter.b(x);
                if (message.what == 500001) {
                    z = true;
                } else {
                    z = false;
                }
                for (FeedBaseCard feedBaseCard : x) {
                    if (z) {
                        feedBaseCard.mIsNeedStatAlg = true;
                    } else {
                        feedBaseCard.mIsNeedStatAlg = false;
                    }
                }
                JSONObject z2 = fVar.z();
                if (z2 != null) {
                    this.mFeedHead.a(z2, this, z);
                }
                this.mFeedAdapter.notifyDataSetChanged();
                Collection y = fVar.y();
                if (y != null && y.size() > 0) {
                    this.mCurSearchIndex = 0;
                    this.mSearchWords.clear();
                    this.mSearchWords.addAll(y);
                    i.a("event_F5", null, getContext());
                    setSearchHint((String) this.mSearchWords.get(this.mCurSearchIndex));
                }
                this.mPullToRefreshView.setRefreshing(false);
                return true;
            case 500004:
                this.mPullToRefreshView.setRefreshing(false);
                return true;
            case 8000001:
                c.e("FeedGoodsActivity", "handle MESSAGE_QUERY_TIME_LIST_SUCCESS----->");
                eVar = (e) message.obj;
                int i = message.arg1;
                if (eVar.j() == 1) {
                    if (eVar.h().size() > 0) {
                        this.mFeedAdapter.c(eVar.h());
                        if (this.mFeedRefreshNoActionCount < 3 && this.mFeedRefreshNoActionCount < 3) {
                            removeFloatEntranceBar();
                        }
                        this.mFeedAdapter.notifyDataSetChanged();
                    } else {
                        this.mFeedRefreshNoActionCount = 0;
                        this.mFeedAdapter.notifyDataSetChanged();
                    }
                    this.mIsNetSucess = true;
                } else if (eVar.j() != 0) {
                    if (eVar.h().size() > 0) {
                        this.mFeedAdapter.c(eVar.h());
                        this.mFeedAdapter.notifyDataSetChanged();
                    } else {
                        this.mFeedAdapter.notifyDataSetChanged();
                    }
                    if (eVar.n()) {
                        this.mPullToRefreshView.setRefreshing(true);
                    }
                    this.mIsNetSucess = true;
                    c.e("FeedGoodsActivity", "send  MESSAGE_QUERY_TIME_LIST_FORCE----->");
                } else if (eVar.h().size() > 0) {
                    this.mFeedAdapter.c(eVar.h());
                    this.mFeedAdapter.notifyDataSetChanged();
                } else {
                    this.mFeedAdapter.a(new FeedNoMoreBottomCard(null, null));
                    this.mFeedAdapter.notifyDataSetChanged();
                    this.mFeedListView.c();
                }
                checkLoginState();
                return true;
            case 8000002:
                if (this.mFeedAdapter.g()) {
                    this.mFeedListView.g();
                } else {
                    this.mFeedListView.d();
                }
                this.mIsNetSucess = false;
                if (message.arg1 == -1) {
                    this.mPullToRefreshView.a(getApplicationContext().getResources().getString(R.string.pulldownview_failed), this.toastView);
                    return true;
                } else if (message.arg1 == 0 || message.arg1 == -3) {
                    return true;
                } else {
                    this.mPullToRefreshView.a("系统繁忙", this.toastView);
                    return true;
                }
            case 8000004:
                c.e("FeedGoodsActivity", "handle  MESSAGE_QUERY_TIME_LIST_FORCE----->");
                refreshWithoutPulldown(true);
                return true;
            case 8000005:
                this.mFeedAdapter.e();
                removeFloatEntranceBar();
                this.mFeedAdapter.notifyDataSetChanged();
                return true;
            case 8000006:
                c.e("FeedGoodsActivity", "handle  MESSAGE_FEED_REFRESH_FROM_TAB----->");
                i.a("event_C104", null, ReaderApplication.getApplicationImp());
                com.qq.reader.common.monitor.j.a(103, 2);
                this.mFeedRefreshNoActionCount++;
                if (this.mFeedListView == null) {
                    return true;
                }
                this.mFeedListView.setSelection(0);
                return true;
            case 8000011:
                eVar = (e) message.obj;
                if (this.mFeedAdapter.g()) {
                    this.mFeedListView.g();
                    return true;
                } else if (eVar.j() == 1) {
                    return true;
                } else {
                    this.mFeedListView.c();
                    return true;
                }
            case 10000001:
                if (message.arg1 == 1) {
                    removeFloatEntranceBar();
                    this.mFeedListView.e();
                    this.mFeedListView.setSelection(0);
                    this.mPullToRefreshView.setRefreshing(true);
                    getFirstPageData(false);
                    if (com.qq.reader.module.feed.mypreference.c.b().c() != null) {
                        return true;
                    }
                    com.qq.reader.module.feed.mypreference.c.b().a(null);
                    return true;
                }
                this.mFeedAdapter.c();
                removeFloatEntranceBar();
                this.mFeedListView.setSelection(0);
                this.mPullToRefreshView.setRefreshing(true);
                getFirstPageData(false);
                if (com.qq.reader.module.feed.mypreference.c.b().c() != null) {
                    return true;
                }
                com.qq.reader.module.feed.mypreference.c.b().a(null);
                return true;
            case 10000003:
            case 10000004:
                return true;
            case 10000202:
                if (!com.qq.reader.common.login.c.b() || getActivity() == null) {
                    loginWithTask(10000202);
                    return true;
                }
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyReadingGeneActivity.class);
                startActivityForResult(intent, 30001);
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    public void refreshWithoutPulldown(boolean z) {
        if (!this.mPullToRefreshView.b()) {
            this.mFeedListView.setSelection(0);
            if (z) {
                this.mPullToRefreshView.setRefreshing(true);
                this.mPullToRefreshView.setIsInterptAnimation(false);
            }
            long j = 0;
            if (z) {
                j = 500;
            }
            this.mHandler.postDelayed(new 11(this), j);
        }
    }

    private void loadFeed(com.qq.reader.module.feed.loader.f fVar, int i) {
        String str = null;
        int i2 = 0;
        if (fVar != null) {
            str = fVar.a;
            i2 = fVar.b;
        }
        e eVar = new e(str, i);
        eVar.a(i2);
        g.a().a(new 20(this, eVar));
    }

    private void switchToOldBookStore() {
        if (getActivity() != null) {
            MainActivity mainActivity = (MainActivity) getActivity();
            if (mainActivity != null) {
                mainActivity.a(true);
                return;
            }
            Intent intent = new Intent();
            intent.setClass(getActivity(), MainActivity.class);
            intent.setFlags(SigType.WLOGIN_QRPUSH);
            intent.putExtra("IS_SWITCH_FROM_TIME_BOOKSTORE", true);
            startActivity(intent);
        }
    }

    private void deleteItem(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (iArr != null && i < iArr.length) {
            if (!arrayList.contains(Integer.valueOf(iArr[i]))) {
                arrayList.add(Integer.valueOf(iArr[i]));
                this.mFeedAdapter.a(iArr[i]);
            }
            i++;
        }
        this.mFeedAdapter.notifyDataSetChanged();
    }

    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    public void onDismiss(ViewGroup viewGroup, int[] iArr) {
        FeedBaseCard feedBaseCard = null;
        for (int i : iArr) {
            if (i >= 0 && i < this.mFeedAdapter.getCount()) {
                feedBaseCard = this.mFeedAdapter.b(i);
            }
            if (feedBaseCard != null) {
                Map hashMap = new HashMap();
                hashMap.put("event_C72", feedBaseCard.getStatString());
                StatisticsManager.a().a("event_C72", hashMap);
            }
        }
        deleteItem(iArr);
        i.a("event_C72", null, getApplicationContext());
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return false;
        }
        if (this.mBrandExpansionHelper == null || !this.mBrandExpansionHelper.e()) {
            i.a("event_C4", null, ReaderApplication.getApplicationImp());
            if (getActivity() != null) {
                ((MainActivity) getActivity()).a("bookstand_tab");
            }
            return true;
        }
        this.mBrandExpansionHelper.d();
        return true;
    }

    public void doFunction(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        if (bundle.getBoolean("goExplore")) {
            if (getActivity() != null) {
                ((MainActivity) getActivity()).a("bookweb_classify_tab");
            }
        } else if (bundle.getBoolean("goClassify")) {
            if (getActivity() != null) {
                ((MainActivity) getActivity()).a("stacks_tab");
            }
        } else if (bundle.getBoolean("goLgoin")) {
            i.a("event_C65", null, getApplicationContext());
            com.qq.reader.common.monitor.j.a(64, 2);
            this.mLoginNextTask = null;
            startLogin();
        } else if (!bundle.getBoolean("goRookieLogin")) {
            if (bundle.getBoolean("feedEntranceActivity")) {
                int i = bundle.getInt("feedEntranceActivityNeedlogin");
                String string = bundle.getString("feedQurl");
                if (!TextUtils.isEmpty(string)) {
                    if (i != 1) {
                        excuteQurl(string);
                    } else if (com.qq.reader.common.login.c.b()) {
                        excuteQurl(string);
                    } else {
                        setLoginNextTask(new 13(this, string));
                        startLogin();
                    }
                }
            } else if (bundle.getBoolean("feedNeedRefresh")) {
                if (this.mFeedListView != null) {
                    this.mFeedListView.setSelection(0);
                    getFirstPageData(false);
                }
            } else if (bundle.getBoolean("feedGotoPersonalityBooks")) {
                com.qq.reader.module.feed.c.c.a = true;
                if (!TextUtils.isEmpty(bundle.getString("feedQurl"))) {
                    setLoginNextTask(new 14(this));
                    startLogin();
                }
            }
        }
    }

    private void excuteQurl(String str) {
        if (getActivity() != null) {
            try {
                com.qq.reader.qurl.c.a(getActivity(), str, null);
            } catch (Exception e) {
            }
        }
    }

    public Activity getFromActivity() {
        return getActivity();
    }

    private void addAdvHeader() {
        if (getActivity() != null) {
            if (this.mFeedHead == null) {
                this.mFeedHead = new com.qq.reader.module.feed.head.a(getActivity());
            } else if (this.mFeedListView.getHeaderViewsCount() > 0) {
                this.mFeedListView.removeHeaderView(this.mFeedHead.b());
            }
            this.mFeedListView.addHeaderView(this.mFeedHead.b());
            this.mFeedHead.a(com.qq.reader.cservice.adv.b.a(getActivity().getApplicationContext()).b("103170"));
        }
    }

    public void IOnResume() {
        super.IOnResume();
        this.mTitleBarStartColor = ReaderApplication.getApplicationImp().getResources().getColor(R.color.skin_set_bookshelf_scroll_title_bar_start_color);
        this.mTitleBarEndColor = ReaderApplication.getApplicationImp().getResources().getColor(R.color.skin_set_bookshelf_scroll_title_bar_end_color);
        if (getActivity() != null) {
            getActivity().registerReceiver(this.myBroadcastReceiver, new IntentFilter(com.qq.reader.common.c.a.cs));
        }
        this.mFeedHead.c();
        this.mHandler.postDelayed(new 15(this), 1000);
        if (!(m.a(getActivity()) || !ao.s(getApplicationContext()) || this.mHandler.hasMessages(8000006))) {
            this.mHandler.sendEmptyMessage(8000004);
        }
        showChannelAdv();
        showOperatingAdv();
        this.mEnterFeedIndex++;
        this.mHandler.sendEmptyMessageDelayed(1, 1500);
        this.mHandler.post(new 16(this));
        if (com.qq.reader.module.rookie.presenter.a.a().b) {
            if (this.mRookieZoneEntrance != null) {
                this.mRookieZoneEntrance.setVisibility(0);
                this.mRookieZoneEntrance.clearAnimation();
                i.a("event_A261", null, ReaderApplication.getApplicationImp());
            }
        } else if (this.mRookieZoneEntrance != null) {
            this.mRookieZoneEntrance.setVisibility(8);
            this.mRookieZoneEntrance.clearAnimation();
        }
        optionRookieGiftAnima();
        com.qq.reader.cservice.adv.b.a(1, true);
        if (this.mIsNeedGoToTop) {
            refreshWithoutPulldown(true);
            this.mIsNeedGoToTop = false;
        }
        this.mHandler.post(new 17(this));
        if (this.mFeedAdapter != null) {
            ArrayList arrayList = this.mFeedAdapter.c;
            if (arrayList != null && arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    FeedBaseCard feedBaseCard = (FeedBaseCard) it.next();
                    if (feedBaseCard instanceof FeedTodayFlashSaleCard) {
                        ((FeedTodayFlashSaleCard) feedBaseCard).startCountDown();
                    }
                }
            }
        }
    }

    private void showBrandExpansionWhenResume() {
        if (!isNeedShowBrandEntrance()) {
            return;
        }
        if (this.mBrandExpansionHelper != null) {
            this.mBrandExpansionHelper.a(false);
            this.mBrandExpansionHelper.c();
            return;
        }
        checkAndShowBrandExpansion();
    }

    private boolean isNeedShowBrandEntrance() {
        if (this.mFeedListView.getFirstVisiblePosition() != 0) {
            return false;
        }
        View childAt = this.mFeedListView.getChildAt(0);
        if (childAt == null) {
            return false;
        }
        if (1.0f - (Math.abs((float) childAt.getTop()) / ((float) childAt.getHeight())) > 0.6666667f) {
            return true;
        }
        return false;
    }

    private void optionRookieGiftAnima() {
        if (!com.qq.reader.module.rookie.presenter.a.a().b) {
            return;
        }
        if (com.qq.reader.module.rookie.presenter.a.a().c()) {
            this.mRookieZoneEntrance.setImageResource(R.drawable.feed_rookie_gift_nonull);
            if (!this.hasShowRookieAnim) {
                this.hasShowRookieAnim = true;
                this.mRookieZoneEntrance.setAnimationController(new FloatingFadeAnimView.a(this.mRookieZoneEntrance, 1));
                this.mRookieZoneEntrance.a();
                return;
            }
            return;
        }
        this.mRookieZoneEntrance.setImageResource(R.drawable.feed_rookie_gift_none);
    }

    public void IOnPause() {
        try {
            if (getActivity() != null) {
                getActivity().unregisterReceiver(this.myBroadcastReceiver);
            }
        } catch (Exception e) {
        }
        if (this.mFeedAdapter != null) {
            String h = this.mFeedAdapter.h();
            if (h != null) {
                this.mStatMap.put("event_feed_exposure", h);
                StatisticsManager.a().a("event_feed_exposure", this.mStatMap);
                this.mStatMap.clear();
            }
            ArrayList arrayList = this.mFeedAdapter.c;
            if (arrayList != null && arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    FeedBaseCard feedBaseCard = (FeedBaseCard) it.next();
                    if (feedBaseCard instanceof FeedTodayFlashSaleCard) {
                        ((FeedTodayFlashSaleCard) feedBaseCard).removeHandler();
                    }
                }
            }
        }
        if (this.mFeedHead != null) {
            this.mFeedHead.d();
        }
        this.mPullToRefreshView.a();
        if (this.mTip != null && this.mTip.c()) {
            this.mTip.b();
        }
        com.qq.reader.cservice.adv.b.a(1, false);
        if (this.mBrandExpansionHelper != null) {
            this.mBrandExpansionHelper.a(true);
            this.mBrandExpansionHelper.b();
        }
    }

    public View getLayout() {
        if (getActivity() == null) {
            return null;
        }
        return getActivity().getWindow().getDecorView();
    }

    public void jumpActivity() {
        c.a("ani", "jumpActivity  FeedGoole");
        switchToOldBookStore();
    }

    public void showMyView() {
        if (com.qq.reader.view.animation.b.b && getActivity() != null) {
            new com.qq.reader.view.animation.b(getActivity()).b(getLayout());
        }
    }

    public void showItsView() {
        c.a("ani", "showItsView ");
        if (getActivity() != null) {
            new com.qq.reader.view.animation.b(getActivity()).a(getLayout());
        }
    }

    public void onAdapterNotifidataSetChange() {
        int count = this.mFeedAdapter.getCount();
        int headerViewsCount = (this.mFeedListView.getHeaderViewsCount() + count) + this.mFeedListView.getFooterViewsCount();
        if (count == 0 || (count == 1 && ((this.mFeedAdapter.b(0) instanceof FeedNoMoreTopCard) || (this.mFeedAdapter.b(0) instanceof FeedRookieEntranceCard)))) {
            this.mFeedListView.g();
        } else if (this.mFeedAdapter.d()) {
            this.mFeedListView.c();
        } else {
            this.mFeedListView.e();
            this.mFeedListView.a();
        }
    }

    private void showClickTabToTopTip() {
        if (!this.mIsShowClickTabToTopTip && getActivity() != null) {
            this.mIsShowClickTabToTopTip = true;
            ((MainActivity) getActivity()).c();
            d.br(getApplicationContext());
        }
    }

    @Deprecated
    private void showManualFreshFeedTip() {
        if (!this.mIsManualRefreshTip && getActivity() != null) {
            this.mIsManualRefreshTip = true;
            if (this.mTip != null && this.mTip.c()) {
                this.mTip.b();
            }
            this.mTip = ap.a(8, getActivity());
            this.mTip.a((l) this);
            this.mTip.a();
            d.bt(getApplicationContext());
        }
    }

    public void doGuid(int i) {
    }

    public int[] getArea(int i) {
        return null;
    }

    public void dismiss(int i) {
    }

    public k getHighLightArea(int i) {
        return null;
    }

    protected void notifyRookieGiftRefresh() {
        if (com.qq.reader.module.rookie.presenter.a.a().b) {
            if (this.mRookieZoneEntrance != null) {
                this.mRookieZoneEntrance.setVisibility(0);
                this.mRookieZoneEntrance.clearAnimation();
                i.a("event_A261", null, ReaderApplication.getApplicationImp());
            }
        } else if (this.mRookieZoneEntrance != null) {
            this.mRookieZoneEntrance.setVisibility(8);
            this.mRookieZoneEntrance.clearAnimation();
        }
    }

    private void showOperatingAdv() {
        if (!com.qq.reader.module.rookie.presenter.a.a().b) {
            Object obj = (checkRookieDialog() || isNeedShowBrandExpansion()) ? null : 1;
            if (obj != null) {
                loadOperatingAdv();
            }
        }
    }

    public void onAudioFloatingStateChange(int i, long j, boolean z, String str) {
        if (z) {
            hideOperatingEntranceView();
            if (this.mRookieZoneEntrance != null) {
                this.mRookieZoneEntrance.setVisibility(8);
                this.mRookieZoneEntrance.clearAnimation();
            }
        } else {
            if (com.qq.reader.module.rookie.presenter.a.a().b && this.mRookieZoneEntrance != null) {
                this.mRookieZoneEntrance.setVisibility(0);
                this.mRookieZoneEntrance.clearAnimation();
                i.a("event_A261", null, ReaderApplication.getApplicationImp());
            }
            showOperatingAdv();
        }
        super.onAudioFloatingStateChange(i, j, z, str);
    }

    private void loadOperatingAdv() {
        List b = com.qq.reader.cservice.adv.b.a(getApplicationContext()).b("103187");
        if (b == null || b.size() <= 0) {
            hideOperatingEntranceView();
            return;
        }
        com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) b.get(0);
        if (com.qq.reader.cservice.adv.a.b(getApplicationContext(), "FEED_ADV_OPERATING_ACTIVITY_DATE")) {
            hideOperatingEntranceView();
            return;
        }
        com.qq.reader.common.imageloader.c.a((Fragment) this).a(aVar.g(), this.mOperatingActivityEntrance, new 19(this, aVar));
        this.mOperatingActivityEntrance.setOnClickListener(new 21(this, aVar));
    }

    private void handleOpratingAdvViewOnClick(com.qq.reader.cservice.adv.a aVar) {
        if (getActivity() != null) {
            try {
                com.qq.reader.qurl.c.a(getActivity(), aVar.h());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void showOperatingEntranceView() {
        this.mOperatingActivityEntrance.setVisibility(0);
        this.mOperatingActivityEntrance.clearAnimation();
        this.mOperatingActivityCloseView.setVisibility(0);
    }

    private void hideOperatingEntranceView() {
        this.mOperatingActivityEntrance.setVisibility(4);
        this.mOperatingActivityEntrance.clearAnimation();
        this.mOperatingActivityCloseView.setVisibility(4);
    }

    private void getFirstPageData(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString("KEY_JUMP_PAGENAME", "Feed_FirstPage");
        com.qq.reader.module.bookstore.qnative.d.b().a(getApplicationContext(), com.qq.reader.module.bookstore.qnative.e.a().a(bundle, this), this.mHandler, z, 4);
    }
}

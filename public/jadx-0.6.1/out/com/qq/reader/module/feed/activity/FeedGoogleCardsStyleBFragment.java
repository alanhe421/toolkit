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
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nhaarman.listviewanimations.itemmanipulation.b.b;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.MainActivity;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.CustomArrayList;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.common.widget.swipelistview.SwipeMenuListView;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.feed.card.FeedNoMoreBottomCard;
import com.qq.reader.module.feed.card.FeedNoMoreTopCard;
import com.qq.reader.module.feed.card.FeedRookieEntranceCard;
import com.qq.reader.module.feed.card.FeedTodayBrowseCard;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.qq.reader.module.feed.loader.CardNavigationBarLoader;
import com.qq.reader.module.feed.loader.d;
import com.qq.reader.module.feed.loader.e;
import com.qq.reader.module.feed.loader.f;
import com.qq.reader.module.feed.mypreference.MyReadingGeneActivity;
import com.qq.reader.module.feed.swipe.FeedSwipeLayout;
import com.qq.reader.module.feed.widget.FeedListViewFooter;
import com.qq.reader.view.ap;
import com.qq.reader.view.k;
import com.qq.reader.view.l;
import com.qq.reader.view.pullupdownlist.XListViewFooter;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.utils.TbsLog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONArray;
import org.json.JSONObject;

public class FeedGoogleCardsStyleBFragment extends FeedGoogleCardBaseFragment implements b, a, FeedStyleBAdapter.a, com.qq.reader.view.animation.a, l {
    private static final int INITIAL_DELAY_MILLIS = 300;
    public static final int MAXFEEDREFRESHNOACTIONCOUNT = 3;
    private static final int MAX_Enter_Feed_Should_Tips_COUNT = 3;
    private static final String TAG = "GoogleCardsActivity";
    private final int DATA_FROM_CACHE = 101;
    private final int DATA_FROM_NET = 100;
    private final int MAX_FEED_NUM = 60;
    protected float[] XDeltaArray;
    BroadcastReceiver actUpdateReceiver = new 2(this);
    protected TranslateAnimation[] animSet;
    protected int curAnimSetCount;
    protected int curAnimSetIndex;
    private ArrayList<ArrayList<d.b>> feedColumnList = new ArrayList();
    BroadcastReceiver loginOkReciver = new 3(this);
    private View mColumnView;
    private int mEnterFeedIndex = -1;
    protected FeedStyleBAdapter mFeedAdapter;
    private com.qq.reader.module.feed.head.a mFeedHead;
    private SwipeMenuListView mFeedListView;
    private int mFeedRefreshNoActionCount;
    private com.bumptech.glide.load.resource.c.b mGif;
    private View mHeadTitleBar;
    private int mIndexOne = 0;
    private int mIndexThree = 0;
    private int mIndexTwo = 0;
    private boolean mIsFirstItemVisible;
    private boolean mIsManualRefreshTip = false;
    private boolean mIsNeedAddCard = true;
    private boolean mIsNetSucess = false;
    private boolean mIsShowClickTabToTopTip = false;
    private boolean mIsTodayBrowseCardShow = false;
    private ImageView mLeftColumnIcon;
    private TextView mLeftColumnIntro;
    private TextView mLeftColumnTitle;
    private ImageView mLeftIcon;
    private LinearLayout mLeftLayout;
    private Map<String, String> mPreferMap = new HashMap();
    private SwipeRefreshLayout mPullToRefreshView;
    private ImageView mRightBottomColumnIcon;
    private TextView mRightBottomColumnIntro;
    private TextView mRightBottomColumnTitle;
    private RelativeLayout mRightBottomLayout;
    private ImageView mRightTopColumnIcon;
    private TextView mRightTopColumnIntro;
    private TextView mRightTopColumnTitle;
    private RelativeLayout mRightTopLayout;
    private View mRootView;
    private Map<String, String> mStatMap = new HashMap();
    private com.qq.reader.view.b.b mTip;
    private int mTitleBarEndColor;
    private int mTitleBarStartColor;
    private TextView mTitleView;
    private BroadcastReceiver myBroadcastReceiver = new 10(this);
    String qurlForRookieLogin;
    private e rookieCardParser;
    private CardNavigationBarLoader rookieLoader;
    private Map<String, String> statMap;
    private SwipeMenuListView.a swipeMenuUpdate = new 1(this);
    BroadcastReceiver switchUserLikeReceiver = new 4(this);
    protected TextView toastView = null;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        c.a("ani", "FeedGoogleCardsActivity oncreate ");
        setIsShowNightMask(false);
        initUI();
        addAdvHeader();
        initData();
        if (getActivity() != null) {
            getActivity().registerReceiver(this.loginOkReciver, new IntentFilter("com.qq.reader.loginok"));
            getActivity().registerReceiver(this.actUpdateReceiver, new IntentFilter(com.qq.reader.common.c.a.cH));
            getActivity().registerReceiver(this.switchUserLikeReceiver, new IntentFilter(com.qq.reader.common.c.a.cK));
        }
        String currentDay = getCurrentDay();
        String bv = com.qq.reader.appconfig.a.d.bv(getApplicationContext());
        if (TextUtils.isEmpty(currentDay) || !currentDay.equals(bv)) {
            com.qq.reader.appconfig.a.d.t(getApplicationContext(), currentDay);
        }
        com.qq.reader.appconfig.a.d.K(getApplicationContext(), 0);
        loadFeed(null, 2);
        com.qq.reader.appconfig.a.d.h(ReaderApplication.getApplicationImp(), System.currentTimeMillis());
        showMyView();
        setStatPageName("feedbookstorepage");
        this.mIsShowClickTabToTopTip = com.qq.reader.appconfig.a.d.bs(getApplicationContext());
        this.mIsManualRefreshTip = com.qq.reader.appconfig.a.d.bu(getApplicationContext());
        this.rookieCardParser = new e();
        this.rookieLoader = new CardNavigationBarLoader();
        getFeedColumn();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.activity_googlelayout, null);
        }
        return this.mRootView;
    }

    private void initUI() {
        if (this.mRootView != null && getActivity() != null) {
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
            this.mRootView.findViewById(R.id.action_btn).setOnClickListener(new 11(this));
        }
    }

    protected void startHeadIconShake(String str) {
        if (getActivity() != null) {
            if (!str.toLowerCase().endsWith(".gif") || this.mGif == null) {
                this.XDeltaArray = new float[]{0.0f, 6.0f, 0.0f, -8.0f, 0.0f, 10.0f, 0.0f, -10.0f, 0.0f, 8.0f, 0.0f, -6.0f, 0.0f};
                if (this.animSet == null) {
                    AnimationListener 17 = new 17(this);
                    this.animSet = new TranslateAnimation[(this.XDeltaArray.length - 1)];
                    for (int i = 0; i < this.animSet.length; i++) {
                        this.animSet[i] = new TranslateAnimation(this.XDeltaArray[i], this.XDeltaArray[i + 1], 0.0f, 0.0f);
                        if (i % 2 == 0) {
                            this.animSet[i].setInterpolator(getActivity(), 17432582);
                        } else {
                            this.animSet[i].setInterpolator(getActivity(), 17432581);
                        }
                        if (i == this.animSet.length - 1) {
                            this.animSet[i].setDuration(80);
                        } else {
                            this.animSet[i].setDuration(50);
                        }
                        this.animSet[i].setAnimationListener(17);
                    }
                }
                this.curAnimSetIndex = 0;
                this.curAnimSetCount = 0;
                this.animSet[this.curAnimSetIndex].reset();
                this.mLeftIcon.startAnimation(this.animSet[this.curAnimSetIndex]);
            } else {
                this.mHandler.postDelayed(new 16(this), 2000);
            }
            i.a("event_A189", this.statMap, getApplicationContext());
        }
    }

    private void initData() {
        if (getActivity() != null) {
            this.mPullToRefreshView.setOnRefreshListener(new 18(this));
            this.mFeedListView.setXListViewListener(new 19(this));
            this.mFeedAdapter = new FeedStyleBAdapter(getActivity());
            this.mFeedAdapter.a(this);
            ListAdapter aVar = new com.nhaarman.listviewanimations.a.a.a(new com.qq.reader.module.feed.swipe.a(this.mFeedAdapter, this, FeedSwipeLayout.a, R.id.ll_bottom_view, R.id.tv_bottom_textview));
            aVar.a(this.mFeedListView);
            this.mFeedListView.setAdapter(aVar);
            this.mFeedListView.setOnScrollListener(new 20(this, com.qq.reader.common.imageloader.c.a((Fragment) this).a(), true, true));
            this.mFeedListView.setOnItemClickListener(new 21(this));
            if (com.qq.reader.common.c.b.i == 1) {
                this.mFeedListView.setOnItemLongClickListener(new 22(this));
            }
        }
    }

    private void doAnimateTitleBar(int i) {
        ObjectAnimator ofObject;
        if (i <= 0 && !this.mIsFirstItemVisible) {
            this.mIsFirstItemVisible = true;
            ofObject = ObjectAnimator.ofObject(this.mHeadTitleBar, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(this.mTitleBarEndColor), Integer.valueOf(this.mTitleBarStartColor)});
            ofObject.setDuration(200);
            ofObject.start();
        } else if (i > 0 && this.mIsFirstItemVisible) {
            this.mIsFirstItemVisible = false;
            ofObject = ObjectAnimator.ofObject(this.mHeadTitleBar, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(this.mTitleBarStartColor), Integer.valueOf(this.mTitleBarEndColor)});
            ofObject.setDuration(200);
            ofObject.start();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 30001 && i2 == -1) {
            refreshWithoutPulldown(true);
        }
    }

    public void onDestroy() {
        if (getActivity() != null) {
            getActivity().unregisterReceiver(this.loginOkReciver);
            getActivity().unregisterReceiver(this.actUpdateReceiver);
            getActivity().unregisterReceiver(this.switchUserLikeReceiver);
        }
        this.mIsNeedAddCard = true;
        super.onDestroy();
    }

    private boolean isActiveDayRookieUser() {
        int bF = com.qq.reader.appconfig.a.d.bF(ReaderApplication.getApplicationImp());
        return bF >= 0 && bF <= 30;
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 1:
                if (ao.o(getApplicationContext())) {
                    com.qq.reader.cservice.adv.b.a(getApplicationContext()).a();
                }
                return true;
            case TbsLog.TBSLOG_CODE_SDK_SELF_MODE /*996*/:
                this.mFeedAdapter.b(new FeedNoMoreBottomCard(null, null));
                this.mFeedAdapter.notifyDataSetChanged();
                this.mFeedListView.c();
                return true;
            case TbsLog.TBSLOG_CODE_SDK_LOAD_ERROR /*998*/:
                this.mFeedAdapter.b(new FeedTodayBrowseCard(null, FeedTodayBrowseCard.TYPE_TODAY_BROWSE));
                this.mFeedAdapter.notifyDataSetChanged();
                return true;
            case 8012:
                if (this.mFeedHead != null) {
                    this.mFeedHead.a(com.qq.reader.cservice.adv.b.a(getActivity().getApplicationContext()).b("103170"));
                }
                checkActUpdate();
                showChannelAdv();
                return true;
            case 8019:
                startHeadIconShake((String) message.obj);
                return true;
            case 8000001:
                c.e("FeedGoodsActivity", "handle MESSAGE_QUERY_TIME_LIST_SUCCESS----->");
                com.qq.reader.module.feed.data.impl.e eVar = (com.qq.reader.module.feed.data.impl.e) message.obj;
                int i = message.arg1;
                this.mPullToRefreshView.a(i > 0 ? "推荐了" + i + "条新内容" : "暂无更新内容", this.toastView);
                if (eVar.j() == 1) {
                    if (eVar.h().size() > 0) {
                        this.mFeedAdapter.b(eVar.h());
                        if (this.mFeedRefreshNoActionCount >= 3 && !((isActiveDayRookieUser() && com.qq.reader.appconfig.a.d.bU(ReaderApplication.getApplicationImp())) || com.qq.reader.appconfig.a.d.j(ReaderApplication.getApplicationImp()))) {
                            this.mFeedAdapter.a(new FeedNoMoreTopCard(null, FeedNoMoreTopCard.FEED_NOUSERACTION));
                        }
                        this.mFeedAdapter.notifyDataSetChanged();
                    } else {
                        this.mFeedRefreshNoActionCount = 0;
                        if (!(com.qq.reader.appconfig.a.d.j(ReaderApplication.getApplicationImp()) || (isActiveDayRookieUser() && com.qq.reader.appconfig.a.d.bU(ReaderApplication.getApplicationImp())))) {
                            this.mFeedAdapter.a(new FeedNoMoreTopCard(null, FeedNoMoreTopCard.FEED_NODATA));
                        }
                        this.mFeedAdapter.notifyDataSetChanged();
                    }
                    this.mIsNetSucess = true;
                } else if (eVar.j() != 0) {
                    if (eVar.h().size() > 0) {
                        this.mFeedAdapter.g();
                        this.mFeedAdapter.b(eVar.h());
                        this.mFeedAdapter.notifyDataSetChanged();
                    } else {
                        if (!(com.qq.reader.appconfig.a.d.j(ReaderApplication.getApplicationImp()) || (isActiveDayRookieUser() && com.qq.reader.appconfig.a.d.bU(ReaderApplication.getApplicationImp())))) {
                            this.mFeedAdapter.a(new FeedNoMoreTopCard(null, FeedNoMoreTopCard.FEED_NODATA));
                        }
                        this.mFeedAdapter.notifyDataSetChanged();
                    }
                    this.mPullToRefreshView.setRefreshing(false);
                    this.mIsNetSucess = true;
                    c.e("FeedGoodsActivity", "send  MESSAGE_QUERY_TIME_LIST_FORCE----->");
                } else if (eVar.h().size() > 0) {
                    this.mFeedAdapter.c(eVar.h());
                    this.mFeedAdapter.notifyDataSetChanged();
                } else {
                    this.mFeedAdapter.b(new FeedNoMoreBottomCard(null, null));
                    this.mFeedAdapter.notifyDataSetChanged();
                    this.mFeedListView.c();
                }
                return true;
            case 8000002:
                if (this.mFeedAdapter.j()) {
                    this.mFeedListView.g();
                } else {
                    this.mFeedListView.d();
                }
                this.mIsNetSucess = false;
                if (message.arg1 == -1) {
                    this.mPullToRefreshView.a(getApplicationContext().getResources().getString(R.string.pulldownview_failed), this.toastView);
                } else if (message.arg1 == 0 || message.arg1 == -3) {
                    this.mPullToRefreshView.a("暂无新内容", this.toastView);
                } else {
                    this.mPullToRefreshView.a("系统繁忙", this.toastView);
                }
                return true;
            case 8000004:
                c.e("FeedGoodsActivity", "handle  MESSAGE_QUERY_TIME_LIST_FORCE----->");
                return true;
            case 8000005:
                this.mFeedAdapter.g();
                this.mFeedAdapter.notifyDataSetChanged();
                return true;
            case 8000006:
                c.e("FeedGoodsActivity", "handle  MESSAGE_FEED_REFRESH_FROM_TAB----->");
                i.a("event_C104", null, ReaderApplication.getApplicationImp());
                j.a(103, 2);
                this.mFeedAdapter.l();
                this.mFeedListView.setSelection(0);
                return true;
            case 10000001:
                if (message.arg1 == 1) {
                    this.mFeedAdapter.g();
                    this.mFeedAdapter.notifyDataSetChanged();
                    this.mFeedListView.e();
                    loadFeed(this.mFeedAdapter.i(), 2);
                } else {
                    this.mFeedAdapter.e();
                    this.mFeedAdapter.notifyDataSetChanged();
                }
                return true;
            case 10000003:
                if (isActiveDayRookieUser()) {
                    this.rookieLoader.a(com.qq.reader.appconfig.e.cj + com.qq.reader.appconfig.a.d.aU(ReaderApplication.getApplicationImp()), 10000004, this.mHandler, this.rookieCardParser);
                }
                return true;
            case 10000004:
                if (message.arg1 == 1) {
                    if (!TextUtils.isEmpty(this.qurlForRookieLogin) && com.qq.reader.appconfig.a.d.bU(ReaderApplication.getApplicationImp()) && com.qq.reader.common.login.c.b() && getActivity() != null) {
                        try {
                            com.qq.reader.qurl.c.a(getActivity(), this.qurlForRookieLogin);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.qurlForRookieLogin = null;
                    }
                    if ((message.obj instanceof Boolean) && ((Boolean) message.obj).booleanValue()) {
                        this.mFeedAdapter.notifyDataSetChanged();
                    }
                }
                return true;
            case 10000006:
                this.mFeedAdapter.notifyDataSetChanged();
                return true;
            case 10000202:
                if (!com.qq.reader.common.login.c.b() || getActivity() == null) {
                    loginWithTask(10000202);
                } else {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), MyReadingGeneActivity.class);
                    startActivityForResult(intent, 30001);
                }
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
                j = 1500;
            }
            this.mHandler.postDelayed(new 5(this), j);
        }
    }

    private void loadFeed(f fVar, int i) {
        Message obtain;
        int i2 = 0;
        String str = null;
        if (com.qq.reader.appconfig.a.d.bw(getApplicationContext()) >= 60) {
            Object c = d.b().c();
            if (TextUtils.isEmpty(c)) {
                obtain = Message.obtain();
                obtain.what = TbsLog.TBSLOG_CODE_SDK_SELF_MODE;
                this.mHandler.sendMessage(obtain);
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(c);
                if (jSONObject != null) {
                    JSONArray optJSONArray = jSONObject.optJSONArray("infos");
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        List<com.qq.reader.module.bookstore.qnative.card.a> a = com.qq.reader.module.feed.data.impl.c.a(null, optJSONArray);
                        List arrayList = new ArrayList();
                        for (com.qq.reader.module.bookstore.qnative.card.a aVar : a) {
                            if (aVar instanceof FeedBaseCard) {
                                arrayList.add((FeedBaseCard) aVar);
                            }
                        }
                        if (!this.mIsTodayBrowseCardShow) {
                            arrayList.add(0, new FeedTodayBrowseCard(null, FeedTodayBrowseCard.TYPE_TODAY_BROWSE));
                            i.a("event_C245", null, ReaderApplication.getApplicationImp());
                            this.mIsTodayBrowseCardShow = true;
                        }
                        this.mFeedAdapter.c(arrayList);
                        this.mFeedAdapter.notifyDataSetChanged();
                        this.mPullToRefreshView.setRefreshing(false);
                        return;
                    }
                    return;
                }
                return;
            } catch (Exception e) {
                obtain = Message.obtain();
                obtain.what = TbsLog.TBSLOG_CODE_SDK_SELF_MODE;
                this.mHandler.sendMessage(obtain);
                return;
            }
        }
        if (fVar != null) {
            str = fVar.a;
            i2 = fVar.b;
        }
        com.qq.reader.module.feed.data.impl.e eVar = new com.qq.reader.module.feed.data.impl.e(str, i);
        eVar.a(i2);
        g.a().a(new 14(this, eVar));
    }

    private void switchToOldBookStore() {
        if (getActivity() != null) {
            MainActivity mainActivity = (MainActivity) getActivity().getParent();
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
        if (i == 4) {
            i.a("event_C4", null, ReaderApplication.getApplicationImp());
            ((MainActivity) getActivity()).a("bookstand_tab");
        }
        return true;
    }

    public void doFunction(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        if (bundle.getBoolean("goExplore") && getActivity() != null) {
            ((MainActivity) getActivity()).a("bookweb_classify_tab");
        } else if (bundle.getBoolean("goClassify")) {
            ((MainActivity) getActivity()).a("stacks_tab");
        } else if (bundle.getBoolean("goLgoin")) {
            i.a("event_C65", null, getApplicationContext());
            j.a(64, 2);
            this.mLoginNextTask = null;
            startLogin();
        } else if (bundle.getBoolean("goRookieLogin")) {
            this.qurlForRookieLogin = null;
            String string = bundle.getString("getReward");
            if (!TextUtils.isEmpty(string) && com.qq.reader.qurl.c.a(string)) {
                setLoginNextTask(new 6(this, string));
            }
            startLogin();
        }
    }

    public Activity getFromActivity() {
        return getActivity();
    }

    private void addAdvHeader() {
        if (getActivity() != null) {
            if (this.mFeedHead == null) {
                this.mFeedHead = new com.qq.reader.module.feed.head.a(getActivity());
            }
            this.mFeedListView.addHeaderView(this.mFeedHead.b());
            this.mFeedHead.a(com.qq.reader.cservice.adv.b.a(getActivity().getApplicationContext()).b("103170"));
        }
    }

    private void addColumnView() {
        if (this.mColumnView == null && getActivity() != null) {
            this.mColumnView = LayoutInflater.from(getActivity()).inflate(R.layout.concept_recommend_b_layout, null);
            if (this.mColumnView != null) {
                this.mLeftColumnIcon = (ImageView) this.mColumnView.findViewById(R.id.left_icon);
                this.mLeftColumnTitle = (TextView) this.mColumnView.findViewById(R.id.left_title);
                this.mLeftColumnIntro = (TextView) this.mColumnView.findViewById(R.id.left_intro);
                this.mRightTopColumnIcon = (ImageView) this.mColumnView.findViewById(R.id.right_top_icon);
                this.mRightTopColumnTitle = (TextView) this.mColumnView.findViewById(R.id.right_top_title);
                this.mRightTopColumnIntro = (TextView) this.mColumnView.findViewById(R.id.right_top_intro);
                this.mRightBottomColumnIcon = (ImageView) this.mColumnView.findViewById(R.id.right_bottom_icon);
                this.mRightBottomColumnTitle = (TextView) this.mColumnView.findViewById(R.id.right_bottom_title);
                this.mRightBottomColumnIntro = (TextView) this.mColumnView.findViewById(R.id.right_bottom_intro);
                this.mLeftLayout = (LinearLayout) this.mColumnView.findViewById(R.id.left_layout);
                this.mRightTopLayout = (RelativeLayout) this.mColumnView.findViewById(R.id.right_top_layout);
                this.mRightBottomLayout = (RelativeLayout) this.mColumnView.findViewById(R.id.right_bottom_layout);
                this.mLeftLayout.setOnClickListener(new 7(this));
                this.mRightTopLayout.setOnClickListener(new 8(this));
                this.mRightBottomLayout.setOnClickListener(new 9(this));
                this.mFeedListView.addHeaderView(this.mColumnView);
                this.mColumnView.setVisibility(8);
            }
        }
    }

    public void IOnResume() {
        super.IOnResume();
        if (getActivity() != null) {
            getActivity().registerReceiver(this.myBroadcastReceiver, new IntentFilter(com.qq.reader.common.c.a.cs));
        }
        this.mFeedHead.c();
        this.mPreferMap.clear();
        this.mPreferMap.put("prefer", com.qq.reader.appconfig.a.d.aS(getApplicationContext()) + "");
        i.a("event_C63", this.mPreferMap, getApplicationContext());
        j.a(62, 2);
        StatisticsManager.a().a("event_C63", this.mPreferMap);
        if (ao.s(getApplicationContext()) && !this.mHandler.hasMessages(8000006)) {
            this.mHandler.sendEmptyMessage(8000004);
        }
        showChannelAdv();
        this.mEnterFeedIndex++;
        this.mHandler.sendEmptyMessageDelayed(1, 1500);
        checkActUpdate();
        this.mHandler.sendEmptyMessageDelayed(10000003, 100);
        if (!com.qq.reader.common.login.c.b()) {
            this.mFeedAdapter.notifyDataSetChanged();
        }
    }

    public void IOnPause() {
        if (getActivity() != null) {
            getActivity().unregisterReceiver(this.myBroadcastReceiver);
        }
        if (this.mFeedAdapter != null) {
            String k = this.mFeedAdapter.k();
            if (k != null) {
                this.mStatMap.put("event_feed_exposure", k);
                StatisticsManager.a().a("event_feed_exposure", this.mStatMap);
                this.mStatMap.clear();
            }
        }
        this.mFeedHead.d();
        this.mPullToRefreshView.a();
        if (this.mTip != null && this.mTip.c()) {
            this.mTip.b();
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
        } else if (this.mFeedAdapter.f()) {
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
            com.qq.reader.appconfig.a.d.br(getApplicationContext());
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
            com.qq.reader.appconfig.a.d.bt(getApplicationContext());
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

    private void stopGif() {
        if (this.mGif != null) {
            this.mGif.stop();
            this.mLeftIcon.setImageBitmap(this.mGif.b());
            this.mGif.f();
            this.mGif = null;
        }
    }

    private void startShake(String str) {
        Message obtain = Message.obtain();
        obtain.what = 8019;
        obtain.obj = str;
        this.mHandler.sendMessage(obtain);
    }

    private void checkActUpdate() {
        List b = com.qq.reader.cservice.adv.b.a(getApplicationContext()).b("102879");
        if (b == null || b.size() <= 0) {
            stopGif();
            com.qq.reader.cservice.adv.a.a(getApplicationContext());
            this.mLeftIcon.setVisibility(4);
            return;
        }
        com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) b.get(0);
        com.qq.reader.common.imageloader.c.a((Fragment) this).a(aVar.g(), this.mLeftIcon, new 12(this, aVar));
        this.statMap = new HashMap();
        this.statMap.put(s.ORIGIN, String.valueOf(aVar.d()));
        i.a("event_A188", this.statMap, getApplicationContext());
        this.mLeftIcon.setOnClickListener(new 13(this, aVar));
    }

    private String getCurrentDay() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    private void getFeedColumn() {
        if (this.feedColumnList != null) {
            this.feedColumnList.clear();
        }
        g.a().a(new GetFeedColumnTask(this, new 15(this)));
    }

    private ArrayList<String> getColumnList() {
        ArrayList<String> arrayList = new ArrayList();
        int aS = com.qq.reader.appconfig.a.d.aS(ReaderApplication.getApplicationImp());
        if (aS == 1) {
            arrayList.add("2398");
            arrayList.add("2397");
            arrayList.add("2399");
        } else if (aS == 2) {
            arrayList.add("2401");
            arrayList.add("2400");
            arrayList.add("2402");
        } else if (aS == 3) {
            arrayList.add("2394");
            arrayList.add("2395");
            arrayList.add("2396");
        }
        return arrayList;
    }

    private void updateColumnUI(ArrayList<ArrayList<d.b>> arrayList) {
        try {
            addColumnView();
            if (this.mColumnView != null) {
                this.mColumnView.setVisibility(0);
            }
            int size = ((ArrayList) arrayList.get(0)).size();
            int size2 = ((ArrayList) arrayList.get(1)).size();
            int size3 = ((ArrayList) arrayList.get(2)).size();
            this.mLeftColumnTitle.setText(((d.b) ((ArrayList) arrayList.get(0)).get(this.mIndexOne)).c);
            this.mLeftColumnIntro.setText(((d.b) ((ArrayList) arrayList.get(0)).get(this.mIndexOne)).b);
            com.qq.reader.common.imageloader.c.a((Fragment) this).a(ao.g(((d.b) ((ArrayList) arrayList.get(0)).get(this.mIndexOne)).a), this.mLeftColumnIcon, com.qq.reader.common.imageloader.a.a().j());
            this.mRightTopColumnTitle.setText(((d.b) ((ArrayList) arrayList.get(1)).get(this.mIndexTwo)).c);
            this.mRightTopColumnIntro.setText(((d.b) ((ArrayList) arrayList.get(1)).get(this.mIndexTwo)).b);
            com.qq.reader.common.imageloader.c.a((Fragment) this).a(ao.g(((d.b) ((ArrayList) arrayList.get(1)).get(this.mIndexTwo)).a), this.mRightTopColumnIcon, com.qq.reader.common.imageloader.a.a().j());
            this.mRightBottomColumnTitle.setText(((d.b) ((ArrayList) arrayList.get(2)).get(this.mIndexThree)).c);
            this.mRightBottomColumnIntro.setText(((d.b) ((ArrayList) arrayList.get(2)).get(this.mIndexThree)).b);
            com.qq.reader.common.imageloader.c.a((Fragment) this).a(ao.g(((d.b) ((ArrayList) arrayList.get(2)).get(this.mIndexThree)).a), this.mRightBottomColumnIcon, com.qq.reader.common.imageloader.a.a().j());
            this.mIndexOne++;
            this.mIndexTwo++;
            this.mIndexThree++;
            if (this.mIndexOne == size) {
                this.mIndexOne = 0;
            }
            if (this.mIndexTwo == size2) {
                this.mIndexTwo = 0;
            }
            if (this.mIndexThree == size3) {
                this.mIndexThree = 0;
            }
        } catch (Exception e) {
        }
    }

    private String composePageUrl() {
        int aS = com.qq.reader.appconfig.a.d.aS(getApplicationContext());
        Bundle bundle = new Bundle();
        if (aS == 1) {
            bundle.putString("URL_BUILD_PERE_COLS", "2398,2397,2399");
        } else if (aS == 2) {
            bundle.putString("URL_BUILD_PERE_COLS", "2401,2400,2402");
        } else if (aS == 3) {
            bundle.putString("URL_BUILD_PERE_COLS", "2394,2395,2396");
        }
        com.qq.reader.module.bookstore.qnative.c cVar = new com.qq.reader.module.bookstore.qnative.c(bundle);
        String str = "queryOperation?";
        return cVar.b("queryOperation?");
    }
}

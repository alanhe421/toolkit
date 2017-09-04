package com.qq.reader.activity;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue.IdleHandler;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.nineoldandroids.animation.AnimatorSet;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.handle.f;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.db.handle.o;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.download.task.m;
import com.qq.reader.common.download.task.n;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.common.readertask.protocol.QueryReadTimeTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ab;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.r;
import com.qq.reader.common.utils.t;
import com.qq.reader.common.web.js.JSAddToBookShelf;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.cservice.bookfollow.b.a;
import com.qq.reader.cservice.cloud.h;
import com.qq.reader.cservice.download.book.DownloadBookTask;
import com.qq.reader.cservice.download.book.e;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.DownloadMark;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookshelf.k;
import com.qq.reader.module.bookshelf.l;
import com.qq.reader.module.bookshelf.signup.SignupManager;
import com.qq.reader.module.bookshelf.signup.SignupManager.DeductionExtInfo;
import com.qq.reader.module.bookshelf.signup.SignupManager.SignInfo;
import com.qq.reader.module.bookshelf.signup.SignupManager.SignItem;
import com.qq.reader.module.bookshelf.signup.SignupManager.b;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreTwoLevelActivity;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.plugin.audiobook.MusicBookGroup;
import com.qq.reader.plugin.audiobook.core.SongInfo;
import com.qq.reader.qurl.JumpActivityParameter;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.CustomDrawerLayout;
import com.qq.reader.view.LeakFixDialog;
import com.qq.reader.view.LuckyDrawDialog;
import com.qq.reader.view.af;
import com.qq.reader.view.aq;
import com.qq.reader.view.metro.MetroItem;
import com.qq.reader.view.web.g;
import com.qq.reader.widget.GradientBottomAutoRaiseNumView;
import com.qq.reader.widget.ReaderWidget;
import com.qq.reader.widget.WaveView;
import com.qq.reader.widget.progress.CircleProgressBar;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONException;
import org.json.JSONObject;

public class BookShelfFragment extends AbsBaseBookListFragment implements OnCreateContextMenuListener, a, b {
    public static final int BOOKSHELF_TAB_BOOKS = 1003;
    public static final int BOOKSHELF_TAB_ID_NEWS = 1000;
    public static final int BOOKSHELF_TOPBAR_ACTION_BATMANAGEMENT = 1003;
    public static final int BOOKSHELF_TOPBAR_ACTION_IMPORTBOOKS = 1001;
    @Deprecated
    public static final int BOOKSHELF_TOPBAR_ACTION_NIGHTMODE = 1004;
    public static final int BOOKSHELF_TOPBAR_ACTION_SERIALIZEDUPDATE = 1005;
    public static final int BOOKSHELF_TOPBAR_ACTION_SIGNIN = 1006;
    public static final int BOOKSHELF_TOPBAR_ACTION_TRACKBOOK = 1002;
    public static final String CATEGORY_ALL = "全部";
    public static final String CATEGORY_BOOKSTAND = "我的书架";
    private static final int FORGETSIGN_GRID_THREECOL_HORIZONTALSPACE = 8;
    private static final int FORGETSIGN_GRID_TWOCOL_HORIZONTALSPACE = 20;
    private static final int FORGETSIGN_GRID_VERTICALSPACE = 18;
    public static final int MENU_ID_GOTO_CLOUD = 4;
    public static final int MENU_ID_LOCAL_DISK = 0;
    public static final int MENU_ID_MANAGE = 2;
    public static final int MENU_ID_NET_DISK = 1;
    public static final int MENU_ID_NONE = -1;
    public static final int MENU_ID_SOFR_WITHTIME = 5;
    public static final int MENU_ID_UPDATE_ALARM = 3;
    public static final byte RESET_LAST_DOWNLOAD = (byte) 2;
    public static final byte RESET_LAST_READED = (byte) 1;
    public static final byte RESET_NONE = (byte) 0;
    public static final float TITLE_BAR_ANIMATION_FACTOR = 0.6666667f;
    public static boolean isFirstResume = true;
    public static boolean isIntercept = false;
    private static long lastClickTime = 0;
    public static ArrayList<Mark> mMarksInCurModel = new ArrayList();
    public static byte resetScrollType = (byte) 0;
    private static final int sCongrats = 3;
    private static final int sNextCome = 4;
    private static final int sSign_Week = 2;
    private static final int sToday = 0;
    private static final int sTomorrow = 1;
    private final int DIALOG_CLEAR_BOOKMARK = 303;
    private final int DIALOG_IMPORTBOOKS_FROM_READERZONE = 307;
    private final int DIALOG_LUCKY_DRAW_REDUNDANT_TIP = 504;
    private final int DIALOG_REMOVE_SELECT_BOOKMARK = 306;
    private final int DIALOG_SIGN_COMMIT_USERINFO = 505;
    private final int DIALOG_SIGN_UP_MISS_TIP = 501;
    private final int DIALOG_SIGN_UP_PAY_NEED_CHARGE = 503;
    private final int DIALOG_SIGN_UP_REDUNDANT_TIP = 502;
    private final int DIALOG_SINATURE = 400;
    private final int MENU_FIX_TOP = 12;
    private final int MENU_FIX_TOP_CANCEL = 13;
    private final int MENU_IMPORT_CLOADBOOK = 15;
    @Deprecated
    private final int MENU_IMPORT_FREECHANNEL = 17;
    private final int MENU_IMPORT_LOCALBOOK = 16;
    private final int MENU_IMPORT_WEIYUNNETDISK = 19;
    private final int MENU_REMOVE_ADV = 18;
    private final String TAG_DOWNLOADTASK = "tag_dt";
    private final String TAG_LOCALMARK = "tag_ml";
    protected float[] XDeltaArray = new float[]{0.0f, 6.0f, 0.0f, -8.0f, 0.0f, 10.0f, 0.0f, -10.0f, 0.0f, 8.0f, 0.0f, -6.0f, 0.0f};
    private BroadcastReceiver allBroadcastReceiver = new BroadcastReceiver(this) {
        final /* synthetic */ BookShelfFragment a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (com.qq.reader.common.c.a.cv.equalsIgnoreCase(action)) {
                this.a.mHandler.sendEmptyMessage(300011);
            } else if (com.qq.reader.common.c.a.cy.equalsIgnoreCase(action)) {
                this.a.mHandler.sendEmptyMessage(300012);
            }
        }
    };
    private List<Mark> allMarks = new ArrayList();
    protected TranslateAnimation[] animSet;
    private Mark[] bms;
    private FrameLayout bookshelfContainer;
    private TextView checkInBtn;
    private BroadcastReceiver classCategoryGotoAllReceiver = new BroadcastReceiver(this) {
        final /* synthetic */ BookShelfFragment a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            Message obtain = Message.obtain();
            obtain.arg1 = i.a;
            obtain.obj = BookShelfFragment.CATEGORY_ALL;
            obtain.what = 20004;
            this.a.mHandler.sendMessage(obtain);
        }
    };
    protected int curAnimSetCount;
    protected int curAnimSetIndex;
    private com.qq.reader.common.protocol.b curMsg;
    private BroadcastReceiver gotAvatarBroadcastReceiver = new BroadcastReceiver(this) {
        final /* synthetic */ BookShelfFragment a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (com.qq.reader.common.c.a.ct.equals(intent.getAction()) && this.a.mHandler != null) {
                this.a.mHandler.obtainMessage(3006, null).sendToTarget();
            }
        }
    };
    private boolean hasflip = false;
    private String headerBgImagUrl;
    private String headerGiftImageUrl;
    private AnimatorSet increaseNumAni;
    private boolean isExitMuiscMarkInStand = false;
    private long lastRefresh = 0;
    private m listener = new m(this) {
        final /* synthetic */ BookShelfFragment a;

        {
            this.a = r1;
        }

        public void a(n nVar) {
            TaskStateEnum c = nVar.c();
            TaskStateEnum a = nVar.a();
            if (c != TaskStateEnum.Installing || a == TaskStateEnum.Installing) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.a.lastRefresh > 500 || c != a) {
                    this.a.lastRefresh = currentTimeMillis;
                    this.a.mHandler.sendEmptyMessage(8001);
                    return;
                }
                return;
            }
            DownloadBookTask downloadBookTask = (DownloadBookTask) nVar.d();
            Serializable a2 = i.c().a(downloadBookTask);
            Message obtain = Message.obtain();
            obtain.what = 8003;
            Bundle bundle = new Bundle();
            bundle.putSerializable("tag_ml", a2);
            bundle.putSerializable("tag_dt", downloadBookTask);
            obtain.setData(bundle);
            this.a.mHandler.sendMessage(obtain);
        }
    };
    BroadcastReceiver loginOkReciver = new BroadcastReceiver(this) {
        final /* synthetic */ BookShelfFragment a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getBooleanExtra("loginSuccess", false)) {
                SignupManager.a().c();
                this.a.mHandler.sendEmptyMessage(300015);
                SignupManager.a().a(this.a.getRequestSignDay());
            }
        }
    };
    private boolean mAutoSign = false;
    private k mBookBooksTab;
    private com.qq.reader.view.linearmenu.b mBottomContextMenu;
    private boolean mCheckGift = false;
    private CircleProgressBar mCircleProgressBar;
    private ImageView mCircleProgressBarTopAdvLayer;
    private Context mContext;
    private int mCurrentReadTime;
    private o mDownloadHandle = o.c();
    private e mDownloadProxy = null;
    private ImageView mEdgeImageView;
    private ProgressDialog mImportReadzoneProgressDlg;
    private boolean mIsRunningAnimation = false;
    private boolean mIsTitleBarInvisible = true;
    private int mLastRead;
    private TextView mLoginTips;
    private aq mPopupMenu;
    private ProgressDialog mQueryProgressDlg;
    private View mRootView;
    private boolean mShowProfile = false;
    private ImageView mSignFrameBackground;
    private View mSignFrameV2;
    private int mTitleBarEndColor;
    private int mTitleBarStartColor;
    private View mTitleBarView;
    private ImageView mTitleBar_leftbtn_avatar;
    private ImageView mTitleBar_leftbtn_cover;
    private ImageView mTitleBar_leftbtn_tip;
    private View mTitleBar_leftbtn_view;
    private ImageView mTitleBar_rightbtn;
    private TextView mTitleView;
    private TextView mTvReadTimetip;
    private com.qq.reader.module.rookie.presenter.a.a mUpdateListener;
    private l mUserReadTimeData;
    private int mViewPagerScrollFrom = -1;
    private WaveView.a mWaveHelper;
    private WaveView mWavingView;
    private BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver(this) {
        final /* synthetic */ BookShelfFragment a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (com.qq.reader.common.c.a.cj.equalsIgnoreCase(action)) {
                this.a.mHandler.sendEmptyMessage(8006);
            } else if (com.qq.reader.common.c.a.ck.equalsIgnoreCase(action)) {
                this.a.mHandler.sendEmptyMessage(8010);
            } else if (com.qq.reader.common.c.a.cl.equalsIgnoreCase(action)) {
                this.a.mHandler.sendEmptyMessage(8014);
            } else if (com.qq.reader.common.c.a.cq.equalsIgnoreCase(action)) {
                this.a.mHandler.sendEmptyMessage(8017);
            } else if (com.qq.reader.common.c.a.cs.equalsIgnoreCase(action)) {
                this.a.mHandler.sendEmptyMessage(8012);
            } else if (com.qq.reader.common.c.a.cw.equalsIgnoreCase(action)) {
                long longExtra = intent.getLongExtra(com.qq.reader.common.c.a.cE, -1);
                if (longExtra > 0) {
                    Message obtainMessage = this.a.mHandler.obtainMessage();
                    obtainMessage.what = 8015;
                    obtainMessage.obj = String.valueOf(longExtra);
                    this.a.mHandler.sendMessage(obtainMessage);
                }
            }
        }
    };
    private g onlineAdapter;
    private af pageToast;
    private final int sBTN_NORMAL = 0;
    private final int sBTN_REWARD = 1;
    private final int sBTN_WAIT_REWARD = 2;
    BroadcastReceiver songPlayStateChangedReceiver = new BroadcastReceiver(this) {
        final /* synthetic */ BookShelfFragment a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null) {
                if (com.qq.reader.plugin.audiobook.core.l.a != null && com.qq.reader.plugin.audiobook.core.e.h.equals(action)) {
                    try {
                        SongInfo o = com.qq.reader.plugin.audiobook.core.l.a.o();
                        if (o != null) {
                            Mark e = i.c().e(String.valueOf(o.e()));
                            if (e != null) {
                                e.setPercentStr("第" + o.f() + "集");
                                e.setLastReadChapterName(o.h());
                                i.c().a(e, true);
                            }
                        }
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
                this.a.mAdapter.notifyDataSetChanged();
            }
        }
    };
    private TextView tvNumIncrease;
    private TextView tvRookieDouble;
    private TextView tvSignTipsA;
    private TextView tvSignTipsB;
    private GradientBottomAutoRaiseNumView tvTimeLong;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.mUpdateListener == null) {
            this.mUpdateListener = new com.qq.reader.module.rookie.presenter.a.a(this) {
                final /* synthetic */ BookShelfFragment a;

                {
                    this.a = r1;
                }

                public void a(boolean z) {
                    if (this.a.tvRookieDouble != null && ((Boolean) this.a.tvRookieDouble.getTag()).booleanValue() != com.qq.reader.module.rookie.presenter.a.a().b) {
                        if (com.qq.reader.module.rookie.presenter.a.a().b) {
                            this.a.tvRookieDouble.setVisibility(0);
                            if (TextUtils.isEmpty(this.a.headerBgImagUrl)) {
                                this.a.setDefaultSignFrameBackground(R.drawable.common_rookie_header_bg);
                            }
                        } else {
                            this.a.tvRookieDouble.setVisibility(4);
                        }
                        this.a.tvRookieDouble.setTag(Boolean.valueOf(com.qq.reader.module.rookie.presenter.a.a().b));
                    }
                }
            };
            com.qq.reader.module.rookie.presenter.a.a().a(this.mUpdateListener);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ReaderApplication.timeLog.addSplit("BookShelfFragement onCreateView");
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.bookshelf, null);
        }
        ReaderApplication.timeLog.addSplit("BookShelfFragement inflate");
        return this.mRootView;
    }

    public void onViewCreated(View view, Bundle bundle) {
        ReaderApplication.timeLog.addSplit("BookShelfFragement onViewCreated");
        super.onViewCreated(view, bundle);
        com.qq.reader.common.c.a.a(false);
        this.mContext = getApplicationContext();
        this.mDownloadProxy = (e) com.qq.reader.common.download.task.l.d(1001);
        this.isReady2Show = false;
        resetScrollType = (byte) 1;
        ReaderApplication.timeLog.addSplit("BookShelfFragement onViewCreated initUI 0");
        initUI();
        ReaderApplication.timeLog.addSplit("BookShelfFragement onViewCreated initUI end");
        this.mHandler.sendEmptyMessage(300013);
        setIsShowNightMask(false);
        SignupManager.a().a((b) this);
        setStatPageName("bookshelfpage");
        doRegisterReceiver();
        Looper.myQueue().addIdleHandler(new IdleHandler(this) {
            final /* synthetic */ BookShelfFragment a;

            {
                this.a = r1;
            }

            public boolean queueIdle() {
                com.qq.reader.common.readertask.g.a().a(new AnonymousClass1(this));
                if (!ReaderApplication.getInstance().isVerifySinatureOK) {
                    this.a.mHandler.sendEmptyMessageDelayed(211, 1000);
                }
                Map hashMap = new HashMap();
                hashMap.put("name", "BookShelfActivity");
                com.qq.reader.common.monitor.i.a("event_A73", hashMap, this.a.mContext);
                StatisticsManager.a().a("event_A73", hashMap);
                j.a(72, 0);
                try {
                    if (!r.a()) {
                        this.a.showPageToast("存储卡当前不可用,如果连接数据线，请拔掉数据线重新打开软件试试");
                    } else if (!r.c(0)) {
                        this.a.showPageToast("存储卡空间不足，可能会影响软件的正常使用，建议清理一下存储卡");
                    }
                } catch (Exception e) {
                }
                return false;
            }
        });
        Activity activity = getActivity();
        if (activity != null) {
            this.onlineAdapter = new g(activity);
        }
        ReaderApplication.timeLog.addSplit("BookShelfFragement onViewCreated end");
    }

    private void doRegisterReceiver() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.registerReceiver(this.allBroadcastReceiver, new IntentFilter(com.qq.reader.common.c.a.cv));
            activity.registerReceiver(this.allBroadcastReceiver, new IntentFilter(com.qq.reader.common.c.a.cy));
            activity.registerReceiver(this.gotAvatarBroadcastReceiver, new IntentFilter(com.qq.reader.common.c.a.ct));
            activity.registerReceiver(this.loginOkReciver, new IntentFilter("com.qq.reader.loginok"));
            IntentFilter intentFilter = new IntentFilter(com.qq.reader.plugin.audiobook.core.e.h);
            intentFilter.addAction(com.qq.reader.plugin.audiobook.core.e.j);
            intentFilter.addAction(com.qq.reader.plugin.audiobook.core.e.k);
            activity.registerReceiver(this.songPlayStateChangedReceiver, intentFilter);
        }
    }

    private void showAvatorAni() {
        if (c.b() && d.k(getApplicationContext())) {
            startHeadIconShake();
        }
        com.qq.reader.module.bookshelf.j.a(this.mTitleBar_leftbtn_view);
    }

    public void onStop() {
        super.onStop();
    }

    private CustomDrawerLayout getDrawLayout() {
        if (getActivity() != null) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                return ((MainActivity) activity).a();
            }
        }
        return null;
    }

    @SuppressLint({"NewApi"})
    private void initUI() {
        if (this.mRootView != null && getActivity() != null) {
            if (com.qq.reader.common.c.b.e == 0 || com.qq.reader.common.c.b.e == 2) {
                getActivity().getWindow().getDecorView().post(new Runnable(this) {
                    final /* synthetic */ BookShelfFragment a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.getHandler().post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass56 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                            }
                        });
                    }
                });
            }
            this.mTitleBarView = this.mRootView.findViewById(R.id.common_titler);
            this.mTitleView = (TextView) this.mRootView.findViewById(R.id.title);
            this.mTitleView.setText("");
            this.mTitleBarStartColor = ReaderApplication.getApplicationImp().getResources().getColor(R.color.skin_set_bookshelf_scroll_title_bar_start_color);
            this.mTitleBarEndColor = ReaderApplication.getApplicationImp().getResources().getColor(R.color.skin_set_bookshelf_scroll_title_bar_end_color);
            this.mTitleBar_leftbtn_avatar = (ImageView) this.mRootView.findViewById(R.id.title_bar_avatar);
            this.mTitleBar_leftbtn_cover = (ImageView) this.mRootView.findViewById(R.id.title_bar_avatar_cover);
            this.mTitleBar_leftbtn_view = this.mRootView.findViewById(R.id.title_bar_left_view);
            if (com.qq.reader.common.c.b.e == 0 || com.qq.reader.common.c.b.e == 2) {
                this.mTitleBar_leftbtn_view.setVisibility(0);
            } else {
                this.mTitleBar_leftbtn_view.setVisibility(4);
            }
            this.mTitleBar_leftbtn_avatar.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ BookShelfFragment a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.getActivity() != null) {
                        ((MainActivity) this.a.getActivity()).a().d(3);
                        com.qq.reader.common.monitor.i.a("event_A8", null, ReaderApplication.getApplicationImp());
                        d.d(this.a.getApplicationContext(), false);
                        StatisticsManager.a().a("event_A8", null);
                    }
                }
            });
            this.mTitleBar_leftbtn_tip = (ImageView) this.mRootView.findViewById(R.id.title_bar_avatar_reb_dot);
            this.mTitleBar_rightbtn = (ImageView) this.mRootView.findViewById(R.id.bookshelf_rightbtn);
            this.mTitleBar_rightbtn.setVisibility(0);
            this.mTitleBar_rightbtn.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ BookShelfFragment a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    com.qq.reader.common.monitor.i.a("event_A1", null, this.a.getApplicationContext());
                    if (!BookShelfFragment.isFastClick()) {
                        this.a.getTopBarPopupMenu().f_();
                        j.a(0, 0);
                    }
                }
            });
            ((ImageView) this.mRootView.findViewById(R.id.bookshelf_right_search_btn)).setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
                final /* synthetic */ BookShelfFragment a;

                {
                    this.a = r1;
                }

                public void a(View view) {
                    if (this.a.getActivity() != null) {
                        com.qq.reader.common.utils.o.b(this.a.getActivity(), "", "1");
                    }
                }
            });
            createHeaderView();
            this.mAdapter = new com.qq.reader.module.bookshelf.e(getApplicationContext(), false);
            this.mBookBooksTab = new k(1003, getActivity(), this.mHandler, R.layout.bookshelf_pulldown_list, R.id.bookshelf_pulldown_listview, this.mSignFrameV2, this.mAdapter);
            this.mBookBooksTab.a(new com.qq.reader.module.bookshelf.a.a(this) {
                final /* synthetic */ BookShelfFragment a;

                class AnonymousClass1 extends ReaderDBTask {
                    final /* synthetic */ AnonymousClass9 this$1;

                    AnonymousClass1(AnonymousClass9 anonymousClass9) {
                        this.this$1 = anonymousClass9;
                    }

                    public void run() {
                        super.run();
                        if (this.this$1.a.mDownloadProxy != null) {
                            this.this$1.a.mDownloadProxy.a(this.this$1.a.getApplicationContext());
                        }
                    }
                }

                {
                    this.a = r1;
                }

                public void a() {
                    View findViewById = this.a.mSignFrameV2.findViewById(R.id.rl_checkin_container);
                    MarginLayoutParams marginLayoutParams = (MarginLayoutParams) findViewById.getLayoutParams();
                    marginLayoutParams.topMargin = (int) findViewById.getResources().getDimension(R.dimen.book_shelf_check_in_container_margin_top_has_adv);
                    findViewById.setLayoutParams(marginLayoutParams);
                }

                public void b() {
                    View findViewById = this.a.mSignFrameV2.findViewById(R.id.rl_checkin_container);
                    MarginLayoutParams marginLayoutParams = (MarginLayoutParams) findViewById.getLayoutParams();
                    marginLayoutParams.topMargin = (int) findViewById.getResources().getDimension(R.dimen.book_shelf_check_in_container_margin_top_no_adv);
                    findViewById.setLayoutParams(marginLayoutParams);
                }
            });
            this.mBookBooksTab.a(new OnScrollListener(this) {
                final /* synthetic */ BookShelfFragment a;

                {
                    this.a = r1;
                }

                public void onScrollStateChanged(AbsListView absListView, int i) {
                }

                public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                    this.a.refreshTitleBarVisibility(absListView, i);
                }
            });
            this.mBookBooksTab.a((TextView) this.mRootView.findViewById(R.id.main_toastbar));
            this.bookshelfContainer = (FrameLayout) this.mRootView.findViewById(R.id.bookshelf_container);
            this.bookshelfContainer.addView(this.mBookBooksTab.i, -1, -1);
            this.mAdapter.a(1003);
            this.mBookBooksTab.a(false);
        }
    }

    private void refreshTitleBarVisibility(AbsListView absListView, int i) {
        if (i <= 0) {
            View childAt = absListView.getChildAt(0);
            if (childAt != null) {
                float abs = 1.0f - (Math.abs((float) childAt.getTop()) / ((float) childAt.getHeight()));
                ObjectAnimator ofObject;
                if (!this.mIsTitleBarInvisible && abs > 0.6666667f) {
                    this.mIsTitleBarInvisible = true;
                    ofObject = ObjectAnimator.ofObject(this.mTitleBarView, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(this.mTitleBarEndColor), Integer.valueOf(this.mTitleBarStartColor)});
                    ofObject.setDuration(200);
                    ofObject.start();
                } else if (this.mIsTitleBarInvisible && abs < 0.6666667f) {
                    this.mIsTitleBarInvisible = false;
                    ofObject = ObjectAnimator.ofObject(this.mTitleBarView, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(this.mTitleBarStartColor), Integer.valueOf(this.mTitleBarEndColor)});
                    ofObject.setDuration(200);
                    ofObject.start();
                }
            }
        } else if (this.mIsTitleBarInvisible) {
            this.mIsTitleBarInvisible = false;
            this.mTitleBarView.setBackgroundColor(this.mTitleBarEndColor);
        }
    }

    private aq getTopBarPopupMenu() {
        if (getActivity() == null) {
            return null;
        }
        if (this.mPopupMenu == null) {
            this.mPopupMenu = new aq(getActivity(), 13, (int) getActivity().getResources().getDimension(R.dimen.book_shelf_top_bar_width), 0);
            this.mPopupMenu.a(getApplicationContext().getString(R.string.bookshelf_menu_import), R.drawable.bookshelf_menu_import, 1001);
            this.mPopupMenu.a(getApplicationContext().getString(R.string.bookshelf_menu_trackbook), R.drawable.bookshelf_menu_trackbook, 1002);
            this.mPopupMenu.a(getApplicationContext().getString(R.string.bookshelf_menu_batmanagement), R.drawable.bookshelf_menu_batmanagement, 1003);
            this.mPopupMenu.a(getApplicationContext().getString(R.string.bookshelf_menu_serializedupdate), R.drawable.bookshelf_menu_serializedupdate, 1005, d.aC(getApplicationContext()));
            c.c();
            this.mPopupMenu.a(new com.qq.reader.view.a.a(this) {
                final /* synthetic */ BookShelfFragment a;

                {
                    this.a = r1;
                }

                public boolean a(int i) {
                    switch (i) {
                        case 1001:
                            this.a.mHandler.sendEmptyMessage(300016);
                            return true;
                        case 1002:
                            this.a.go2CategoryActivity();
                            j.a(13, 0);
                            return true;
                        case 1003:
                            this.a.go2ManageActivity();
                            j.a(1, 0);
                            return true;
                        case 1005:
                            if (d.aC(this.a.getApplicationContext())) {
                                d.y(this.a.getApplicationContext(), false);
                                j.a(106, 0);
                                return true;
                            }
                            d.y(this.a.getApplicationContext(), true);
                            j.a(105, 0);
                            return true;
                        case 1006:
                            if (this.a.isSignViewShown()) {
                                com.qq.reader.common.monitor.i.a("event_A147", null, ReaderApplication.getApplicationImp());
                                this.a.mBookBooksTab.d().setSelection(0);
                                d.d(this.a.getApplicationContext(), false, c.c().c());
                            } else {
                                com.qq.reader.common.monitor.i.a("event_A146", null, ReaderApplication.getApplicationImp());
                                StatisticsManager.a().a("event_A146", null);
                                j.a((int) Opcodes.SUB_INT, 0);
                                this.a.mBookBooksTab.d().setSelection(0);
                                d.d(this.a.getApplicationContext(), true, c.c().c());
                                this.a.refreshCheckInStates(SignupManager.a().g());
                            }
                            d.e(this.a.getApplicationContext(), true, c.c().c());
                            return true;
                        default:
                            return false;
                    }
                }
            });
        } else {
            this.mPopupMenu.b(getApplicationContext().getString(R.string.bookshelf_menu_serializedupdate), R.drawable.bookshelf_menu_serializedupdate, 1005, d.aC(getApplicationContext()));
            this.mPopupMenu.g();
        }
        return this.mPopupMenu;
    }

    @Deprecated
    private void initRightUI() {
    }

    public boolean handleMessageImp(Message message) {
        List list = null;
        Mark mark;
        switch (message.what) {
            case 1:
                if (ao.o(getApplicationContext())) {
                    com.qq.reader.cservice.adv.b.a(getApplicationContext()).a();
                }
                return true;
            case 211:
                Map hashMap = new HashMap();
                hashMap.put("param_FailCode", ao.h(this.mContext));
                com.qq.reader.common.monitor.i.a("event_signature", false, 0, 0, hashMap, ReaderApplication.getApplicationImp());
                showFragmentDialog(400);
                return true;
            case 1124:
                this.mHandler.sendEmptyMessage(Constants.CODE_SERVICE_DISABLED);
                Toast.makeText(ReaderApplication.getApplicationImp(), "请先登录", 0).show();
                return true;
            case 1240:
                updateReaderTimeWidget();
                return true;
            case 1241:
                com.qq.reader.common.readertask.g.a().a(new QueryReadTimeTask(new com.qq.reader.common.readertask.ordinal.c(this) {
                    final /* synthetic */ BookShelfFragment a;

                    {
                        this.a = r1;
                    }

                    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                        l lVar = new l();
                        if (lVar.a(str)) {
                            com.qq.reader.common.c.a.W = ao.n();
                            d.K(ReaderApplication.getApplicationImp(), str);
                            this.a.mUserReadTimeData = lVar;
                            this.a.mHandler.sendEmptyMessage(1240);
                        }
                    }

                    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    }
                }));
                if (com.qq.reader.module.rookie.presenter.a.a().b) {
                    com.qq.reader.common.monitor.i.a("event_F153", null, ReaderApplication.getApplicationContext());
                }
                return true;
            case 1242:
                if (this.mWaveHelper != null) {
                    this.mWaveHelper.b();
                }
                return true;
            case 1251:
                updateBookshelfHeaderAdv();
                return true;
            case 1252:
                if (message.obj != null) {
                    list = (List) message.obj;
                }
                updateBookshelfHeaderBgAdv(list);
                return true;
            case 1253:
                if (message.obj != null) {
                    list = (List) message.obj;
                }
                updateGiftTopAdv(list);
                return true;
            case 3006:
                com.qq.reader.module.bookshelf.j.b(this.mTitleBar_leftbtn_view);
                showAvatorAni();
                return true;
            case 8001:
                this.mAdapter.notifyDataSetChanged();
                return true;
            case 8003:
                try {
                    Bundle data = message.getData();
                    if (data != null) {
                        mark = (LocalMark) data.getSerializable("tag_ml");
                        DownloadBookTask downloadBookTask = (DownloadBookTask) data.getSerializable("tag_dt");
                        if (!(mark == null || downloadBookTask == null)) {
                            this.mAdapter.a(mark);
                            this.mDownloadProxy.b(downloadBookTask);
                        }
                    }
                    this.mAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                }
                return true;
            case 8006:
                if (this.curMsg != null) {
                    showMsg();
                }
                return true;
            case 8007:
                OnlineTag[] onlineTagArr = (OnlineTag[]) message.obj;
                message.arg1 = 1;
                if (!isInShelf) {
                    message.arg1 = 0;
                }
                this.mBookBooksTab.a(message);
                if (onlineTagArr == null) {
                    return true;
                }
                if (onlineTagArr.length > 0) {
                    refreshTab();
                }
                return true;
            case 8008:
                this.mBookBooksTab.a(message);
                return true;
            case 8009:
                cancelQueryDlg();
                initListView(i.a);
                this.mAdapter.notifyDataSetChanged();
                af.a(this.mContext, (CharSequence) "已经将书城历史添加到书架", 0).a();
                return true;
            case 8010:
                if (com.qq.reader.appconfig.a.c.b == null) {
                    transferOnline();
                }
                return true;
            case 8012:
                this.mBookBooksTab.h();
                this.mBookBooksTab.f();
                com.qq.reader.module.bookshelf.j.a(this.mTitleBar_leftbtn_view);
                showChannelAdv();
                return true;
            case 8014:
                return true;
            case 8015:
                this.mAdapter.a(i.c().e((String) message.obj));
                this.mAdapter.notifyDataSetChanged();
                return true;
            case 8016:
                Message obtain = Message.obtain();
                obtain.what = 300009;
                this.mBookBooksTab.a(obtain);
                return true;
            case 8017:
                refreshTab();
                return true;
            case 10006:
                refreshTab();
                this.mBookBooksTab.a(message);
                return true;
            case Constants.CODE_SERVICE_DISABLED /*10007*/:
                this.mBookBooksTab.a(message);
                return true;
            case 10009:
                Intent intent = new Intent();
                intent.setAction(ReaderWidget.d);
                if (getActivity() != null) {
                    getActivity().sendBroadcast(intent);
                }
                return true;
            case 10013:
                createDialog(307, (Bundle) message.obj).show();
                return true;
            case 10014:
                checkImportFromReaderZone();
                return true;
            case 10015:
                refreshTab();
                if (cancelImportReadZoneProgress()) {
                    showPageToast("导入书籍成功");
                }
                return true;
            case 10016:
                if (getActivity() != null) {
                    ((MainActivity) getActivity()).a("bookweb_recommend_tab");
                }
                return true;
            case 11001:
                this.mHandler.sendEmptyMessageDelayed(11002, 1000);
                return true;
            case 20002:
                return true;
            case qalsdk.n.f /*30000*/:
                com.qq.reader.cservice.bookfollow.b bVar = new com.qq.reader.cservice.bookfollow.b(this.mContext.getApplicationContext());
                bVar.a((a) this);
                bVar.a();
                return true;
            case 70001:
                try {
                    Object obj = message.obj;
                    if (obj instanceof Mark) {
                        this.mAdapter.c((Mark) obj);
                    } else {
                        for (Mark mark2 : (List) obj) {
                            this.mAdapter.c(mark2);
                        }
                    }
                    this.mAdapter.notifyDataSetChanged();
                    if (this.mAdapter.getCount() == 0) {
                        this.mBookBooksTab.c();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                return true;
            case 70002:
                af.a(this.mContext.getApplicationContext(), (String) message.obj, 1).a();
                return true;
            case 300004:
                d.f(this.mContext, System.currentTimeMillis());
                checkBookUpdate();
                this.mHandler.postDelayed(new Runnable(this) {
                    final /* synthetic */ BookShelfFragment a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.mBookBooksTab.f();
                    }
                }, 500);
                return true;
            case 300005:
                onClickBook(message.arg1);
                return true;
            case 300006:
                return onLongClickBook(message.arg1);
            case 300007:
                onLongClickAdv();
                return true;
            case 300008:
                return true;
            case 300011:
                return true;
            case 300012:
                refreshTab();
                return true;
            case 300013:
                if (ao.q(this.mContext)) {
                    this.mBookBooksTab.m();
                }
                return true;
            case 300014:
                if (ao.r(this.mContext) || (i.c().e() == 0 && h.a() != 0)) {
                    this.mBookBooksTab.m();
                }
                return true;
            case 300015:
                this.mBookBooksTab.m();
                return true;
            case 300016:
                com.qq.reader.common.monitor.i.a("event_A130", null, this.mContext);
                if (getActivity() == null) {
                    return false;
                }
                this.mBottomContextMenu = new com.qq.reader.view.linearmenu.b(getActivity());
                this.mBottomContextMenu.a(15, "云书架", null);
                this.mBottomContextMenu.a(16, "导入本地书", null);
                this.mBottomContextMenu.a(19, "微云网盘", null);
                this.mBottomContextMenu.a(new com.qq.reader.view.linearmenu.a.b(this) {
                    final /* synthetic */ BookShelfFragment a;

                    {
                        this.a = r1;
                    }

                    public boolean a(int i, Bundle bundle) {
                        switch (i) {
                            case 15:
                                this.a.gotoCloudActivity(Constants.CODE_LOGIC_REGISTER_IN_PROCESS);
                                com.qq.reader.common.monitor.i.a("event_A57", null, ReaderApplication.getApplicationImp());
                                break;
                            case 16:
                                this.a.gotoLocalImportActivity(10001);
                                j.a(17, 0);
                                break;
                            case 17:
                                if (this.a.getActivity() != null) {
                                    Intent intent = new Intent(this.a.getActivity(), NativeBookStoreTwoLevelActivity.class);
                                    Bundle bundle2 = new Bundle();
                                    String str = "FreePage_Boy";
                                    switch (d.aS(this.a.getApplicationContext())) {
                                        case 1:
                                            str = "FreePage_Boy";
                                            break;
                                        case 2:
                                            str = "FreePage_Girl";
                                            break;
                                        case 3:
                                            str = "FreePage_Publish";
                                            break;
                                    }
                                    bundle2.putString("KEY_JUMP_PAGENAME", str);
                                    bundle2.putString("LOCAL_STORE_IN_TITLE", "免费");
                                    intent.putExtras(bundle2);
                                    this.a.startActivity(intent);
                                    j.a((int) Opcodes.INVOKE_SUPER_RANGE, 0);
                                    break;
                                }
                                return false;
                            case 19:
                                this.a.gotoNetImportActivity();
                                j.a(84, 0);
                                break;
                        }
                        return true;
                    }
                });
                this.mBottomContextMenu.f_();
                return true;
            case 300018:
                if (ao.q(this.mContext) && this.mAdapter != null && this.mAdapter.getCount() > 0) {
                    checkBookUpdate();
                }
                return true;
            case 8000007:
                this.mBookBooksTab.n();
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    private void updateReaderTimeWidget() {
        if (this.mUserReadTimeData.c()) {
            this.mCircleProgressBar.setVisibility(0);
            int[] d = this.mUserReadTimeData.d();
            try {
                this.mCircleProgressBar.setMaxProgress(d[0]);
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.e("BookShelfActivity", e.getMessage());
                this.mCircleProgressBar.setMaxProgress(1800000);
            }
            this.mTvReadTimetip.setText("本周阅读时长/分钟");
            if (this.mUserReadTimeData.e()) {
                this.mCircleProgressBar.setCenterDrawable(getApplicationContext().getResources().getDrawable(R.drawable.liwu));
                this.mCircleProgressBar.setProgress(d[0]);
            } else {
                this.mCircleProgressBar.setCenterDrawable(getApplicationContext().getResources().getDrawable(R.drawable.liwu_nao));
                this.mCircleProgressBar.setProgress(d[1]);
            }
        } else {
            this.mCircleProgressBar.setVisibility(4);
            if (this.mUserReadTimeData.b() > 0) {
                this.mTvReadTimetip.setText("本周阅读时长超过了" + this.mUserReadTimeData.b() + "%书友");
            } else {
                this.mTvReadTimetip.setText("本周阅读时长/分钟");
            }
        }
        int a = this.mUserReadTimeData.a();
        if (a >= 1000) {
            this.tvTimeLong.setTextSize(this.tvTimeLong.getResources().getDimension(R.dimen.read_length_title_txt_size_for_4_num));
        } else {
            this.tvTimeLong.setTextSize(this.tvTimeLong.getResources().getDimension(R.dimen.read_length_title_txt_size));
        }
        if (a > this.mCurrentReadTime) {
            this.tvTimeLong.a(a - this.mCurrentReadTime);
            this.tvNumIncrease.setText("+" + (a - this.mCurrentReadTime));
            makeAddCountAnimation(this.tvNumIncrease);
        } else {
            this.tvTimeLong.setText(String.valueOf(a));
        }
        this.mCurrentReadTime = a;
    }

    private void checkImportFromReaderZone() {
        boolean z = true;
        if (c.c().d() == 1 && ao.i(this.mContext)) {
            boolean aZ = d.aZ(this.mContext);
            if (aZ || !new File(com.qq.reader.common.c.a.aG).exists()) {
                z = aZ;
            } else {
                d.C(this.mContext, true);
            }
            if (!z) {
                com.qq.reader.common.readertask.g.a().a(new ReaderDBTask() {
                    public void run() {
                        super.run();
                        String R = d.R(BookShelfFragment.this.getApplicationContext());
                        Serializable d = i.c().d(R);
                        if (d.size() > 0) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("booknum", d.size());
                            bundle.putString("qqnum", R);
                            bundle.putSerializable("marks", d);
                            Message obtainMessage = BookShelfFragment.this.mHandler.obtainMessage();
                            obtainMessage.obj = bundle;
                            obtainMessage.what = 10013;
                            BookShelfFragment.this.mHandler.sendMessage(obtainMessage);
                        }
                        File file = new File(com.qq.reader.common.c.a.aG);
                        try {
                            d.C(BookShelfFragment.this.mContext, true);
                            if (ab.a(file.getParentFile())) {
                                file.createNewFile();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    private void doImportFromReaderZone(final ArrayList<Mark> arrayList, final String str) {
        com.qq.reader.common.readertask.g.a().a(new ReaderDBTask() {
            public void run() {
                try {
                    int size = arrayList.size();
                    for (int i = 0; i < size; i++) {
                        i.c().a((Mark) arrayList.get(i), true);
                    }
                    v.b().e(str);
                    d.I(BookShelfFragment.this.mContext.getApplicationContext(), size);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                BookShelfFragment.this.mHandler.sendEmptyMessage(10015);
            }
        });
        showImportReadZoneProgress();
        j.a((int) Opcodes.NEG_FLOAT, 0);
    }

    private c getMainActivityProfileView() {
        if (getActivity() != null) {
            return ((MainActivity) getActivity()).c;
        }
        return null;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 10004 && i2 == -1 && getActivity() != null) {
            ((MainActivity) getActivity()).a("bookweb_recommend_tab");
        }
    }

    public void IOnResume() {
        super.IOnResume();
        try {
            this.mTitleBarStartColor = ReaderApplication.getApplicationImp().getResources().getColor(R.color.skin_set_bookshelf_scroll_title_bar_start_color);
            this.mTitleBarEndColor = ReaderApplication.getApplicationImp().getResources().getColor(R.color.skin_set_bookshelf_scroll_title_bar_end_color);
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.registerReceiver(this.myBroadcastReceiver, new IntentFilter(com.qq.reader.common.c.a.cs));
                activity.registerReceiver(this.myBroadcastReceiver, new IntentFilter(com.qq.reader.common.c.a.cq));
                activity.registerReceiver(this.myBroadcastReceiver, new IntentFilter(com.qq.reader.common.c.a.cj));
                activity.registerReceiver(this.myBroadcastReceiver, new IntentFilter(com.qq.reader.common.c.a.ck));
                activity.registerReceiver(this.myBroadcastReceiver, new IntentFilter(com.qq.reader.common.c.a.cl));
                activity.registerReceiver(this.classCategoryGotoAllReceiver, new IntentFilter(com.qq.reader.common.c.a.ci));
                activity.registerReceiver(this.myBroadcastReceiver, new IntentFilter(com.qq.reader.common.c.a.cw));
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("bookshelfActivity", e.getMessage());
        }
        this.mAdapter.a(c.b());
        this.mDownloadProxy.a(TaskStateEnum.values(), this.listener);
        isInShelf = true;
        com.qq.reader.module.bookshelf.a aVar = this.mBookBooksTab;
        aVar.f();
        if (com.qq.reader.common.c.a.de) {
            if (this.mAdapter.e() > 0) {
                this.mAdapter.d();
            }
            refreshTab();
            this.mBookBooksTab.f();
            com.qq.reader.common.c.a.de = false;
        } else {
            refreshTab();
            if (this.mAdapter.getCount() > 0) {
                View childAt = aVar.d().getChildAt(0);
                if (!(childAt == null || childAt.getTop() == 0)) {
                    aVar.d().setSelection(0);
                }
            }
        }
        if (!com.qq.reader.common.utils.m.a(getActivity())) {
            this.mHandler.sendEmptyMessage(300018);
            this.mHandler.sendEmptyMessageDelayed(300014, 1000);
        }
        this.mHandler.sendEmptyMessage(1240);
        this.mHandler.sendEmptyMessageDelayed(1241, 1000);
        this.mHandler.sendEmptyMessageDelayed(3006, 500);
        this.mHandler.sendEmptyMessageDelayed(1, 1500);
        com.qq.reader.common.c.a.dg = "";
        this.mHandler.removeMessages(1242);
        if (this.mWaveHelper != null) {
            this.mWaveHelper.a();
        }
        showChannelAdv();
        Looper.myQueue().addIdleHandler(new IdleHandler(this) {
            final /* synthetic */ BookShelfFragment a;

            {
                this.a = r1;
            }

            public boolean queueIdle() {
                this.a.playSignBtnAnimation();
                if (BookShelfFragment.isFirstResume) {
                    this.a.checkUpdate(false, true);
                    BookShelfFragment.isFirstResume = false;
                }
                com.qq.reader.common.monitor.i.a("event_A132", null, this.a.mContext);
                return false;
            }
        });
        ReaderApplication.timeLog.addSplit("BookShelfFragement onResume");
        ReaderApplication.timeLog.dumpToLog();
        this.mHandler.sendEmptyMessage(1251);
        com.qq.reader.cservice.adv.b.a(0, true);
        com.qq.reader.common.readertask.g.a().a(new ReaderShortTask() {
            public void run() {
                SignupManager.a().a(BookShelfFragment.this.getRequestSignDay());
            }
        });
    }

    public void onStart() {
        super.onStart();
    }

    public void refreshTab() {
        initListView(i.a);
        this.mAdapter.notifyDataSetChanged();
        com.qq.reader.module.bookshelf.a aVar = this.mBookBooksTab;
        if (this.mAdapter.getCount() == 0) {
            aVar.c();
        } else {
            aVar.b();
        }
        refreshTitleBarVisibility(this.mBookBooksTab.d(), this.mBookBooksTab.k());
    }

    public void IOnPause() {
        this.mDownloadProxy.b(TaskStateEnum.values(), this.listener);
        try {
            if (getActivity() != null) {
                getActivity().unregisterReceiver(this.myBroadcastReceiver);
                getActivity().unregisterReceiver(this.classCategoryGotoAllReceiver);
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("BookshelfActivity", e.getMessage());
        }
        com.qq.reader.cservice.cloud.b.a(getApplicationContext()).a(hashCode());
        isInShelf = false;
        this.mBookBooksTab.j();
        this.mHandler.sendEmptyMessageDelayed(1242, 1000);
        com.qq.reader.cservice.adv.b.a(0, false);
    }

    private void doComitWebBookIdsOnShelf(List<Mark> list) {
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (i < list.size() && i < 20) {
            long bookId = ((Mark) list.get(i)).getBookId();
            if (bookId > 0) {
                stringBuffer.append(bookId);
                stringBuffer.append(",");
            }
            i++;
        }
        String stringBuffer2 = stringBuffer.toString();
        if (stringBuffer2.length() > 0) {
            d.g(this.mContext.getApplicationContext(), stringBuffer2.substring(0, stringBuffer2.length() - 1));
        }
    }

    public List<Mark> composeBooks(List<Mark> list) {
        File[] a = com.qq.reader.common.utils.g.a();
        if (a != null && a.length > 0) {
            Collection arrayList = new ArrayList();
            for (File file : a) {
                boolean z;
                for (Mark mark : list) {
                    if (file != null && file.getAbsolutePath().equals(mark.getId())) {
                        z = true;
                        break;
                    }
                }
                z = false;
                if (!z) {
                    String absolutePath = file.getAbsolutePath();
                    Mark localMark = new LocalMark(absolutePath.substring(absolutePath.lastIndexOf("/") + 1, absolutePath.length()), absolutePath, 0, 1, false);
                    localMark.setStarPointStr(Mark.HEADPAGE_FLAG);
                    localMark.setPercentStr("0.0%").setAuthor("匿名");
                    arrayList.add(localMark);
                    i.c().a(localMark, true);
                }
            }
            list.addAll(arrayList);
        }
        return list;
    }

    private void initListView(int i) {
        this.isExitMuiscMarkInStand = false;
        if (this.mAdapter.e() > 0) {
            this.mAdapter.d();
            this.mAdapter.notifyDataSetChanged();
        }
        if (this.allMarks.size() > 0) {
            this.allMarks.clear();
        }
        this.allMarks = i.c().g();
        doComitWebBookIdsOnShelf(this.allMarks);
        composeBooks(this.allMarks);
        this.bms = new Mark[this.allMarks.size()];
        this.allMarks.toArray(this.bms);
        if (this.bms != null && this.bms.length > 0) {
            Mark[] markArr;
            if (i == i.a) {
                d.m(this.mContext.getApplicationContext(), CATEGORY_ALL);
                markArr = this.bms;
            } else {
                ArrayList arrayList = new ArrayList();
                for (Mark mark : this.bms) {
                    if (mark.getCategoryID() == i) {
                        arrayList.add(mark);
                    }
                }
                markArr = new Mark[arrayList.size()];
                arrayList.toArray(markArr);
                this.isExitMuiscMarkInStand = false;
            }
            initDownLoadTask(markArr);
            com.qq.reader.common.db.handle.a.a().b();
            this.mAdapter.a(markArr);
            j.a(((com.qq.reader.module.bookshelf.e) this.mAdapter).a());
            j.l = this.bms.length;
        }
    }

    private void initDownLoadTask(Mark[] markArr) {
        if (this.mDownloadProxy != null) {
            List a = this.mDownloadProxy.a();
            if (a != null && a.size() != 0) {
                for (int i = 0; i < a.size(); i++) {
                    DownloadBookTask downloadBookTask = (DownloadBookTask) a.get(i);
                    int i2 = 0;
                    while (i2 < markArr.length) {
                        if (markArr[i2].getType() == 3 && downloadBookTask.getFilePath().equals(markArr[i2].getId())) {
                            if (downloadBookTask.getState() == TaskStateEnum.Installing) {
                                Serializable a2 = i.c().a(downloadBookTask);
                                markArr[i2] = a2;
                                Message obtain = Message.obtain();
                                obtain.what = 8003;
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("tag_ml", a2);
                                bundle.putSerializable("tag_dt", downloadBookTask);
                                obtain.setData(bundle);
                                this.mHandler.sendMessage(obtain);
                            } else {
                                markArr[i2].setOperateTime(downloadBookTask.getCreateTime());
                                ((DownloadMark) markArr[i2]).setDownloadTask(downloadBookTask);
                            }
                        } else {
                            i2++;
                        }
                    }
                }
            }
        }
    }

    public void onWindowFocusChanged(boolean z) {
        this.isReady2Show = true;
    }

    public void showPageToast(String str) {
        if (this.pageToast == null) {
            this.pageToast = af.a(getApplicationContext(), (CharSequence) "", 0);
        }
        this.pageToast.a((CharSequence) str);
        this.pageToast.a();
    }

    public void transferOnline() {
        if (getActivity() != null) {
            final List c = v.b().c();
            if (c.size() > 0) {
                new AlertDialog.a(getActivity()).c(R.drawable.alert_dialog_icon).a((int) R.string.history_dialog_tip).b((int) R.string.history_transfer_shelf).a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ BookShelfFragment b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.b.beginTransferOnlineDB(c);
                    }
                }).b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ BookShelfFragment a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a().show();
            }
        }
    }

    private Mark getMarkById(String str) {
        Object obj = null;
        if (this.bms == null) {
            return null;
        }
        Mark mark;
        Mark mark2 = null;
        for (Mark mark22 : this.bms) {
            if (mark22 != null && mark22 != null && mark22.getId().trim().length() != 0 && mark22.getId().equals(str)) {
                obj = 1;
                mark = mark22;
                break;
            }
        }
        mark = mark22;
        if (obj != null) {
            return mark;
        }
        return null;
    }

    public void beginTransferOnlineDB(final List<OnlineTag> list) {
        if (getActivity() != null) {
            if (this.mQueryProgressDlg == null || !this.mQueryProgressDlg.isShowing()) {
                this.mQueryProgressDlg = ProgressDialog.show(getActivity(), null, "正在将书城历史导入到书架，请稍候...", true, false);
            }
            new Thread(new Runnable(this) {
                final /* synthetic */ BookShelfFragment b;

                public void run() {
                    List arrayList = new ArrayList();
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        OnlineTag onlineTag = (OnlineTag) list.get(i);
                        long parseLong = Long.parseLong(onlineTag.k());
                        if (this.b.mDownloadProxy.a(parseLong) == null && this.b.getMarkById(String.valueOf(parseLong)) == null) {
                            Mark localMark = new LocalMark(onlineTag.b(), onlineTag.k(), 0, 4, false);
                            localMark.setDescriptionStr("").setPercentStr("0.0%").setAuthor(onlineTag.o()).setEncoding(4);
                            localMark.setStartPoint(onlineTag.i());
                            localMark.setId(onlineTag.k());
                            localMark.setBookId(Long.valueOf(onlineTag.k()).longValue());
                            arrayList.add(localMark);
                        }
                    }
                    Mark[] markArr = new Mark[size];
                    arrayList.toArray(markArr);
                    i.c().a(markArr);
                    this.b.mHandler.sendEmptyMessage(8009);
                }
            }).start();
        }
    }

    private int initLastRead(Mark[] markArr) {
        int i;
        long j = 0;
        if (markArr != null) {
            int length = markArr.length;
            int i2 = 0;
            Mark mark = null;
            i = -1;
            while (i2 < length) {
                int i3;
                Mark mark2 = markArr[i2];
                mark2.setLastRead(false);
                if (mark2.getReadTime() > j) {
                    j = mark2.getReadTime();
                    if (mark != null) {
                        mark.setLastRead(false);
                    }
                    mark2.setLastRead(true);
                    i3 = i2;
                } else {
                    mark2 = mark;
                    i3 = i;
                }
                i2++;
                i = i3;
                mark = mark2;
            }
        } else {
            i = -1;
        }
        if (i != -1) {
            this.mAdapter.b(markArr[i]);
        } else {
            this.mAdapter.b(null);
        }
        return i;
    }

    public boolean cancelQueryDlg() {
        if (this.mQueryProgressDlg == null || !this.mQueryProgressDlg.isShowing()) {
            return false;
        }
        this.mQueryProgressDlg.cancel();
        return true;
    }

    public void onDestroy() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.unregisterReceiver(this.allBroadcastReceiver);
            activity.unregisterReceiver(this.gotAvatarBroadcastReceiver);
            activity.unregisterReceiver(this.loginOkReciver);
            activity.unregisterReceiver(this.songPlayStateChangedReceiver);
        }
        SignupManager.a().a(null);
        com.qq.reader.common.readertask.g.a().a(new ReaderIOTask() {
            public void run() {
                try {
                    j.g();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.mDownloadProxy = null;
        com.qq.reader.common.protocol.a.b();
        f.a().b();
        closeCloudService();
        super.onDestroy();
        if (this.mWaveHelper != null) {
            this.mWaveHelper.c();
        }
        if (mAdvDialog != null) {
            mAdvDialog = null;
        }
        com.qq.reader.module.rookie.presenter.a.a().b(this.mUpdateListener);
        this.mUpdateListener = null;
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        return onContextMenuSelected(menuItem.getItemId(), null);
    }

    public boolean onContextMenuSelected(int i, Bundle bundle) {
        if (this.currentSelectMark == null) {
            return false;
        }
        com.qq.reader.common.download.task.g downloadTask;
        switch (i) {
            case 0:
                showFragmentDialog(301);
                return true;
            case 1:
                showFragmentDialog(302);
                j.a(9, 0);
                if (this.currentSelectMark.getType() != 8) {
                    return true;
                }
                com.qq.reader.common.monitor.i.a("event_C212", null, getApplicationContext());
                return true;
            case 4:
                if (this.currentSelectMark != null && (this.currentSelectMark instanceof DownloadMark)) {
                    downloadTask = ((DownloadMark) this.currentSelectMark).getDownloadTask();
                    downloadTask.setIsOnlyDownLoadIcon(false);
                    this.mDownloadProxy.e(downloadTask);
                    break;
                }
            case 5:
                if (this.currentSelectMark != null && (this.currentSelectMark instanceof DownloadMark)) {
                    downloadTask = ((DownloadMark) this.currentSelectMark).getDownloadTask();
                    downloadTask.setIsOnlyDownLoadIcon(false);
                    this.mDownloadProxy.c(downloadTask);
                    break;
                }
            case 12:
                if (i.c().b(1) > 2) {
                    showPageToast("最多可置顶3本书");
                } else {
                    this.currentSelectMark.setSortIndex(1);
                    i.c().c(this.currentSelectMark.getId(), 1);
                    this.mAdapter.c();
                    this.mAdapter.notifyDataSetChanged();
                    showPageToast("已置顶");
                }
                j.a(16, 0);
                return true;
            case 13:
                this.currentSelectMark.setSortIndex(0);
                i.c().c(this.currentSelectMark.getId(), 0);
                this.mAdapter.c();
                this.mAdapter.notifyDataSetChanged();
                showPageToast("已取消置顶");
                return true;
        }
        return super.onContextMenuSelected(i, bundle);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case 4:
                CustomDrawerLayout drawLayout = getDrawLayout();
                if (drawLayout != null && drawLayout.e(3)) {
                    drawLayout.b();
                    return true;
                } else if (this.mQueryProgressDlg == null || !this.mQueryProgressDlg.isShowing()) {
                    AlertDialog backCancelDialog = getBackCancelDialog();
                    if (!(backCancelDialog == null || getActivity() == null || getActivity().isFinishing())) {
                        backCancelDialog.show();
                    }
                    com.qq.reader.common.monitor.i.a("event_D116", null, getApplicationContext());
                    j.a(20, 0);
                    return true;
                } else {
                    this.mQueryProgressDlg.cancel();
                    return false;
                }
            case Opcodes.IGET /*82*/:
                return true;
            default:
                return false;
        }
    }

    public void selectAllInCurModel() {
        mMarksInCurModel.clear();
        if (this.bms != null) {
            int h = i.c().h(d.ak(this.mContext.getApplicationContext()));
            for (Mark mark : this.bms) {
                if ((h == i.a || mark.getCategoryID() == h) && !(mark instanceof MusicBookGroup)) {
                    mMarksInCurModel.add(mark);
                }
            }
        }
    }

    private AlertDialog getBackCancelDialog() {
        if (getActivity() == null) {
            return null;
        }
        List b = com.qq.reader.cservice.adv.b.a(ReaderApplication.getApplicationImp()).b("102870");
        if (b == null || b.size() <= 0) {
            return new AlertDialog.a(getActivity()).c(R.drawable.alert_dialog_icon).a((int) R.string.exit).b(getApplicationContext().getString(R.string.dialog_exit, new Object[]{getApplicationContext().getString(R.string.app_name)})).a((int) R.string.dialog_exit_ok, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ BookShelfFragment a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    if (this.a.getActivity() != null) {
                        this.a.getActivity().finish();
                        this.a.quitAll();
                        d.e = false;
                        com.qq.reader.common.monitor.i.a("event_D119", null, this.a.getApplicationContext());
                    }
                }
            }).b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ BookShelfFragment a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    com.qq.reader.common.monitor.i.a("event_D120", null, this.a.getApplicationContext());
                }
            }).a(new OnCancelListener(this) {
                final /* synthetic */ BookShelfFragment a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    com.qq.reader.common.monitor.i.a("event_D121", null, this.a.getApplicationContext());
                }
            }).a();
        }
        final com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) b.get(0);
        View inflate = View.inflate(ReaderApplication.getApplicationImp(), R.layout.bookshelf_back_cancel_dialog_adv_ui, null);
        final ImageView imageView = (ImageView) inflate.findViewById(R.id.img_cancel_adv);
        com.qq.reader.common.imageloader.c.a(getContext()).a(aVar.g(), imageView, com.qq.reader.common.imageloader.a.a().b((int) R.drawable.back_dialog_adv_loading_failed), new com.bumptech.glide.request.e<String, com.bumptech.glide.load.resource.a.b>(this) {
            final /* synthetic */ BookShelfFragment b;

            public boolean a(Exception exception, String str, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                if (imageView != null) {
                    View rootView = imageView.getRootView();
                    Drawable drawable = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.back_dialog_adv_loading_failed);
                    if (imageView instanceof ImageView) {
                        imageView.setImageDrawable(drawable);
                    }
                    if (rootView != null) {
                        TextView textView = (TextView) rootView.findViewById(R.id.message_dialog_title);
                        if (textView != null) {
                            textView.setText(this.b.getApplicationContext().getString(R.string.exit));
                        }
                        FrameLayout frameLayout = (FrameLayout) rootView.findViewById(R.id.body);
                        if (frameLayout != null) {
                            frameLayout.setLayoutParams(new LayoutParams(-1, drawable.getIntrinsicHeight()));
                        }
                    }
                }
                return true;
            }

            public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                if (bVar == null || imageView == null || !(bVar instanceof com.bumptech.glide.load.resource.bitmap.j)) {
                    return false;
                }
                com.bumptech.glide.load.resource.bitmap.j jVar2 = (com.bumptech.glide.load.resource.bitmap.j) bVar;
                int intrinsicHeight = (jVar2.getIntrinsicHeight() * com.qq.reader.common.c.a.bU) / jVar2.getIntrinsicWidth();
                imageView.setLayoutParams(new LayoutParams(-1, intrinsicHeight));
                imageView.setImageBitmap(((com.bumptech.glide.load.resource.bitmap.j) bVar).b());
                View rootView = imageView.getRootView();
                if (rootView != null) {
                    TextView textView = (TextView) rootView.findViewById(R.id.message_dialog_title);
                    if (textView != null) {
                        textView.setText(this.b.getApplicationContext().getString(R.string.dialog_exit, new Object[]{this.b.getApplicationContext().getString(R.string.app_name)}));
                    }
                    FrameLayout frameLayout = (FrameLayout) rootView.findViewById(R.id.body);
                    if (frameLayout != null) {
                        frameLayout.setLayoutParams(new LayoutParams(-1, intrinsicHeight));
                    }
                }
                return true;
            }
        });
        if (aVar != null) {
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(aVar.d()));
            com.qq.reader.common.monitor.i.a("event_D117", hashMap, getApplicationContext());
        }
        imageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ BookShelfFragment b;

            public void onClick(View view) {
                if (this.b.getActivity() != null) {
                    try {
                        com.qq.reader.qurl.c.a(this.b.getActivity(), aVar.h());
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, String.valueOf(aVar.d()));
                        com.qq.reader.common.monitor.i.a("event_D118", hashMap, this.b.getApplicationContext());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        AlertDialog a = new AlertDialog.a(getActivity()).c(R.drawable.alert_dialog_icon).a(getApplicationContext().getString(R.string.dialog_exit, new Object[]{getApplicationContext().getString(R.string.app_name)})).a(inflate).a((int) R.string.dialog_exit_ok, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ BookShelfFragment a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                if (this.a.getActivity() != null) {
                    this.a.getActivity().finish();
                    this.a.quitAll();
                    d.e = false;
                    com.qq.reader.common.monitor.i.a("event_D119", null, this.a.getApplicationContext());
                }
            }
        }).b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ BookShelfFragment a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                com.qq.reader.common.monitor.i.a("event_D120", null, this.a.getApplicationContext());
            }
        }).a();
        a.a(getApplicationContext().getResources().getDimensionPixelOffset(R.dimen.main_back_dialog_height));
        return a;
    }

    protected Dialog createDialog(int i, Bundle bundle) {
        Dialog dialog = null;
        Context activity = getActivity();
        if (activity == null) {
            return null;
        }
        final CheckBox checkBox;
        switch (i) {
            case 303:
                dialog = new AlertDialog.a(activity).c(R.drawable.alert_dialog_icon).a((int) R.string.bookstand_menu_clear).b((int) R.string.bookstand_dialog_clear).a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ BookShelfFragment a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.clearRecordFile();
                    }
                }).b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ BookShelfFragment a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
            case 306:
                View inflate = LayoutInflater.from(activity).inflate(R.layout.delete_file, null);
                checkBox = (CheckBox) inflate.findViewById(R.id.confirm_check);
                ((TextView) inflate.findViewById(R.id.confirm_text)).setText(R.string.bookstand_dialog_select_del);
                checkBox.setChecked(false);
                dialog = new AlertDialog.a(activity).c(R.drawable.alert_dialog_icon).a((int) R.string.bookstand_menu_remove).a(inflate).a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ BookShelfFragment b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean isChecked = checkBox.isChecked();
                        Mark mark;
                        if (isChecked) {
                            Iterator it = BookShelfFragment.mMarksInCurModel.iterator();
                            while (it.hasNext()) {
                                mark = (Mark) it.next();
                                if (mark != null) {
                                    this.b.delRecordFile(mark, false);
                                }
                            }
                            final List arrayList = new ArrayList(BookShelfFragment.mMarksInCurModel.size());
                            arrayList.addAll(BookShelfFragment.mMarksInCurModel);
                            if (this.b.getActivity() != null) {
                                format.epub.common.utils.f.a(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass27 b;

                                    public void run() {
                                        for (Mark mark : arrayList) {
                                            if (mark != null) {
                                                this.b.b.delBookLocalFile(mark);
                                            }
                                        }
                                    }
                                }, this.b.getActivity(), "正在删除本地文件,请稍候..");
                                return;
                            }
                            return;
                        }
                        Iterator it2 = BookShelfFragment.mMarksInCurModel.iterator();
                        while (it2.hasNext()) {
                            mark = (Mark) it2.next();
                            if (mark != null) {
                                this.b.delRecordFile(mark, isChecked);
                            }
                        }
                    }
                }).b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ BookShelfFragment a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
            case 307:
                final ArrayList arrayList = (ArrayList) bundle.getSerializable("marks");
                final String string = bundle.getString("qqnum");
                dialog = new AlertDialog.a(activity).c(R.drawable.alert_dialog_icon).a((CharSequence) "提示").b("检测到您" + getApplicationContext().getString(R.string.app_name) + "中心上有" + bundle.getInt("booknum") + "本书不在本地书架上，是否导入？").a((CharSequence) "导入", new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ BookShelfFragment c;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.c.doImportFromReaderZone(arrayList, string);
                    }
                }).b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ BookShelfFragment a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
            case ErrorCode.ERROR_TBSCORE_DEXOPT_DIR /*311*/:
                View inflate2 = LayoutInflater.from(activity).inflate(R.layout.delete_nofile_mark, null);
                checkBox = (CheckBox) inflate2.findViewById(R.id.confirm_check);
                checkBox.setChecked(false);
                dialog = new AlertDialog.a(activity).c(R.drawable.alert_dialog_icon).a((int) R.string.dialog_shortcut_title).a(inflate2).a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ BookShelfFragment b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (checkBox.isChecked()) {
                            if (this.b.getActivity() != null) {
                                format.epub.common.utils.f.a(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass31 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        for (Mark mark : i.c().g()) {
                                            if (!(mark == null || new File(mark.getId()).exists() || 4 == mark.getType())) {
                                                this.a.b.delRecordFile(mark, false);
                                                try {
                                                    Thread.sleep(100);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                    }
                                }, this.b.getActivity(), "正在清理,请稍候..");
                            }
                        } else if (this.b.currentSelectMark != null) {
                            this.b.delRecordFile(this.b.currentSelectMark, false);
                        }
                    }
                }).b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ BookShelfFragment a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
            case 400:
                dialog = new AlertDialog.a(activity).c(R.drawable.alert_dialog_icon).a((int) R.string.app_sinature_note_title).b(getApplicationContext().getString(R.string.app_sinature_note, new Object[]{getApplicationContext().getString(R.string.app_name)})).a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ BookShelfFragment a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
            case 501:
                dialog = new AlertDialog.a(activity).c(R.drawable.alert_dialog_icon).a((CharSequence) "提示").b("检测到您" + getApplicationContext().getString(R.string.app_name) + "QQ阅读中心上有").a((CharSequence) "导入", new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ BookShelfFragment a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ BookShelfFragment a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
            case 502:
                dialog = new AlertDialog.a(activity).c(R.drawable.alert_dialog_icon).a((CharSequence) "签到成功").b((CharSequence) "今日签到奖励已经领过啦，同一设备不能重复领取").a((CharSequence) "我知道了", new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ BookShelfFragment a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
            case 503:
                dialog = new AlertDialog.a(activity).c(17301543).a((int) R.string.dialog_shortcut_title).b((int) R.string.resign_not_enough_balance).a((int) R.string.charge, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ BookShelfFragment a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (this.a.getActivity() != null) {
                            new JSPay(this.a.getActivity()).startCharge(this.a.getActivity(), 0);
                        }
                    }
                }).b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ BookShelfFragment a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
            case 504:
                dialog = new AlertDialog.a(activity).c(R.drawable.alert_dialog_icon).a((CharSequence) "签到成功").b((CharSequence) "已经抽过啦，下周再来吧").a((CharSequence) "确定", new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ BookShelfFragment a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
            case 505:
                if (getActivity() != null) {
                    Dialog a;
                    View inflate3 = LayoutInflater.from(getActivity()).inflate(R.layout.bookshelf_sign_commit_user_info_dialog, null);
                    final EditText editText = (EditText) inflate3.findViewById(R.id.input_qq_edittext);
                    final EditText editText2 = (EditText) inflate3.findViewById(R.id.input_phone_edittext);
                    if (getActivity() != null) {
                        a = new AlertDialog.a(getActivity()).a((CharSequence) "填写联系方式").a(inflate3).a((int) R.string.sign_lucky_draw_commit_info, new DialogInterface.OnClickListener(this) {
                            final /* synthetic */ BookShelfFragment c;

                            public void onClick(DialogInterface dialogInterface, int i) {
                                String trim = editText.getText().toString().trim();
                                String trim2 = editText2.getText().toString().trim();
                                if (TextUtils.isEmpty(trim)) {
                                    af.a(this.c.getApplicationContext(), (CharSequence) "QQ号不能为空", 0).a();
                                    return;
                                }
                                SignupManager.a().a(trim, trim2);
                                dialogInterface.dismiss();
                            }
                        }).a();
                        a.a(false);
                        a.getWindow().setSoftInputMode(16);
                    } else {
                        a = null;
                    }
                    dialog = a;
                    break;
                }
                break;
        }
        return dialog == null ? super.createDialog(i, bundle) : dialog;
    }

    protected void onFragmentDialgoCancel(DialogInterface dialogInterface) {
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    private void go2CategoryActivity() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), CategoryIndexActivity.class);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent);
    }

    private void go2SearchActivity() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), LocalSearchBooksActivity.class);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent);
    }

    private void go2ManageActivity() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), CategoryBooksActivity.class);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        intent.putExtra("category_books_mode", 10103);
        intent.putExtra("category_id", i.a);
        intent.putExtra("category_name", CATEGORY_ALL);
        startActivity(intent);
    }

    private void checkBookUpdate() {
        if (!this.mHandler.hasMessages(qalsdk.n.f)) {
            Message obtain = Message.obtain();
            obtain.what = qalsdk.n.f;
            this.mHandler.sendMessageDelayed(obtain, 500);
        }
        j.a(29, 0);
    }

    private void clearRecordFile() {
        ArrayList b = this.mAdapter.b();
        Mark[] markArr = new Mark[b.size()];
        for (int i = 0; i < b.size(); i++) {
            markArr[i] = (Mark) b.get(i);
        }
        for (Mark delRecordFile : markArr) {
            delRecordFile(delRecordFile, false);
        }
        format.epub.common.a.a.a();
    }

    public void onClickBook(int i) {
        if (getActivity() != null) {
            Object item = this.mAdapter.getItem(i);
            if (item == null) {
            }
            if (item instanceof Mark) {
                final Mark mark = (Mark) item;
                if (mark instanceof DownloadMark) {
                    if (!mark.isHardCoverBook()) {
                        final DownloadBookTask downloadTask = ((DownloadMark) mark).getDownloadTask();
                        if (downloadTask != null) {
                            switch (downloadTask.getState()) {
                                case Prepared:
                                case Started:
                                case DeactivePrepared:
                                case DeactiveStarted:
                                    this.mDownloadProxy.c(downloadTask);
                                    return;
                                case Paused:
                                case Failed:
                                    if (downloadTask.getIsOnlyDownLoadIcon()) {
                                        downloadTask.setIsOnlyDownLoadIcon(false);
                                    }
                                    if (!mark.isHardCoverBook() || c.b()) {
                                        this.mDownloadProxy.e(downloadTask);
                                        return;
                                    }
                                    setLoginNextTask(new com.qq.reader.common.login.a(this) {
                                        final /* synthetic */ BookShelfFragment b;

                                        public void a(int i) {
                                            switch (i) {
                                                case 1:
                                                    if (this.b.mDownloadProxy != null && downloadTask != null) {
                                                        this.b.mDownloadProxy.f(downloadTask);
                                                        return;
                                                    }
                                                    return;
                                                default:
                                                    return;
                                            }
                                        }
                                    });
                                    startLogin();
                                    return;
                                case InstallCompleted:
                                    if (!new File(downloadTask.getFilePath()).exists() || getActivity() == null) {
                                        downloadTask.reStart();
                                        return;
                                    }
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("filepath", downloadTask.getFilePath());
                                    bundle.putString("filename", downloadTask.getFullName());
                                    bundle.putString("fileauthor", downloadTask.getAuthor());
                                    intent.putExtras(bundle);
                                    intent.setClass(getActivity(), ReaderPageActivity.class);
                                    com.qq.reader.b.a(intent, getActivity());
                                    return;
                                default:
                                    return;
                            }
                        }
                        return;
                    } else if (c.b()) {
                        gotoDownloadMark((DownloadMark) mark);
                        return;
                    } else {
                        this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                            final /* synthetic */ BookShelfFragment b;

                            public void a(int i) {
                                if (i == 1) {
                                    this.b.gotoDownloadMark((DownloadMark) mark);
                                }
                            }
                        };
                        startLogin();
                        return;
                    }
                } else if (System.currentTimeMillis() < ao.h(mark.getLimitFreeEndTime()).longValue()) {
                    com.qq.reader.common.monitor.i.a("event_F314", null, getContext());
                }
            }
            super.onClickBook(i);
        }
    }

    private void gotoDownloadMark(DownloadMark downloadMark) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        if (downloadMark != null) {
            bundle.putString("filepath", downloadMark.getId());
            bundle.putString("filename", downloadMark.getBookName());
            bundle.putString("fileauthor", downloadMark.getAuthor());
            bundle.putBoolean("detailpage_trial_read", true);
            bundle.putString("fileid", String.valueOf(downloadMark.getBookId()));
            intent.putExtras(bundle);
            com.qq.reader.b.a(intent, getActivity());
        }
    }

    public void onLongClickAdv() {
        if (getActivity() != null) {
            this.mBottomContextMenu = new com.qq.reader.view.linearmenu.b(getActivity());
            this.mBottomContextMenu.a(18, "移除", null);
            this.mBottomContextMenu.a(new com.qq.reader.view.linearmenu.a.b(this) {
                final /* synthetic */ BookShelfFragment a;

                {
                    this.a = r1;
                }

                public boolean a(int i, Bundle bundle) {
                    if (18 == i) {
                    }
                    if (this.a.mBookBooksTab != null) {
                        this.a.mBookBooksTab.g();
                    }
                    return true;
                }
            });
            this.mBottomContextMenu.f_();
        }
    }

    public boolean onLongClickBook(int i) {
        if (getActivity() == null) {
            return false;
        }
        Mark mark = (Mark) this.mAdapter.getItem(i);
        if (mark != null) {
            this.contextMenu = new com.qq.reader.view.linearmenu.d(getActivity());
            if (mark instanceof LocalMark) {
                if (mark.getSortIndex() <= 0) {
                    this.contextMenu.a(12, "置顶", null);
                } else {
                    this.contextMenu.a(13, "取消置顶", null);
                }
            }
            this.contextMenu.a(new com.qq.reader.view.r(this) {
                final /* synthetic */ BookShelfFragment a;

                {
                    this.a = r1;
                }

                public t a() {
                    return this.a.contextMenu.c();
                }

                public void onDismiss(DialogInterface dialogInterface) {
                    super.onDismiss(dialogInterface);
                    j.a(12, 0);
                }
            });
        }
        return super.onLongClickBook(i);
    }

    public void delRecordFile(Mark mark, boolean z) {
        com.qq.reader.common.download.task.g a = this.mDownloadHandle.a(mark.getId());
        if (a != null) {
            this.mDownloadProxy.d(a);
        }
        super.delRecordFile(mark, z);
    }

    private void showMsg() {
        this.curMsg = com.qq.reader.common.protocol.a.a();
        if (this.curMsg == null) {
            this.curMsg = new com.qq.reader.common.protocol.b("访问QQ书城,海量图书任你选", "/book/index.c?");
            this.curMsg.a(5);
        }
    }

    public void onQueryNewResult(int i, Object obj) {
        Message message = new Message();
        message.what = i;
        message.obj = obj;
        this.mHandler.sendMessage(message);
    }

    protected void setListViewDataByCateId(int i) {
    }

    protected void categoryTo(Mark mark) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(mark);
        getCategoryOpDialog(arrayList).f_();
    }

    protected void doChooseCategory(MetroItem metroItem) {
    }

    private void showImportReadZoneProgress() {
        if (getActivity() != null) {
            if (this.mImportReadzoneProgressDlg == null || !this.mImportReadzoneProgressDlg.isShowing()) {
                this.mImportReadzoneProgressDlg = ProgressDialog.show(getActivity(), "", "正在导入" + getApplicationContext().getString(R.string.app_name) + "中心书籍到书架，请稍候...", true);
                this.mImportReadzoneProgressDlg.setCanceledOnTouchOutside(false);
            }
        }
    }

    private boolean cancelImportReadZoneProgress() {
        try {
            if (this.mImportReadzoneProgressDlg != null && this.mImportReadzoneProgressDlg.isShowing()) {
                this.mImportReadzoneProgressDlg.cancel();
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    @SuppressLint({"NewApi"})
    public void onSignUpReturned(int i, final Object obj) {
        switch (i) {
            case 0:
                if (obj != null) {
                    final SignInfo signInfo = (SignInfo) obj;
                    if (signInfo.getCurrentSignDay() != 7 && c.b()) {
                        d.b(getApplicationContext(), false, c.c().c());
                        d.b(ReaderApplication.getApplicationImp(), "", c.c().c());
                        d.c(ReaderApplication.getApplicationImp(), false, c.c().c());
                    }
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable(this) {
                            final /* synthetic */ BookShelfFragment b;

                            public void run() {
                                this.b.refreshCheckInStates(signInfo);
                                this.b.setRequestSignDay(Calendar.getInstance().get(5));
                                if (c.b() && this.b.mAutoSign && !signInfo.mAlreadySigned) {
                                    this.b.mAutoSign = false;
                                    this.b.checkInBtn.performClick();
                                }
                            }
                        });
                        return;
                    }
                    return;
                }
                return;
            case 1:
                if (obj != null && getActivity() != null) {
                    final SignupManager.c cVar = (SignupManager.c) obj;
                    final SignInfo g = SignupManager.a().g();
                    g.mAlreadySigned = true;
                    getActivity().runOnUiThread(new Runnable(this) {
                        final /* synthetic */ BookShelfFragment c;

                        public void run() {
                            int i = 0;
                            boolean z;
                            if (cVar == null || cVar.a.size() == 0) {
                                this.c.setUserSignInFlag(true);
                                if (!(g == null || g.mItems == null || g.mItems.size() <= 0)) {
                                    z = g.getCurrentSignDay() + -1 >= 0;
                                    if (g.getCurrentSignDay() - 1 < g.mItems.size()) {
                                        i = 1;
                                    }
                                    if (z && r2 != 0) {
                                        ((SignItem) g.mItems.get(g.getCurrentSignDay() - 1)).mSignedType = 1;
                                    }
                                }
                                this.c.refreshCheckInStates(g);
                                this.c.showFragmentDialog(502);
                                return;
                            }
                            if (((SignItem) cVar.a.get(0)).mItemType == 1) {
                                final Dialog access$2500 = this.c.initResignOkWindow(cVar);
                                access$2500.getWindow().setWindowAnimations(R.style.sign_pop_anim_style);
                                access$2500.show();
                                this.c.mHandler.postDelayed(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass53 b;

                                    public void run() {
                                        access$2500.dismiss();
                                    }
                                }, 3000);
                                int[] iArr = cVar.b;
                                int length = iArr.length;
                                while (i < length) {
                                    int i2 = iArr[i];
                                    if (i2 - 1 < g.mItems.size() && i2 - 1 >= 0) {
                                        ((SignItem) g.mItems.get(i2 - 1)).mSignedType = 2;
                                    }
                                    i++;
                                }
                                this.c.refreshCheckInStates(g);
                                com.qq.reader.common.monitor.i.a("event_A139", null, this.c.mContext);
                            } else {
                                com.qq.reader.cservice.adv.a aVar;
                                this.c.setUserSignInFlag(true);
                                if (!(g == null || g.mItems == null || g.mItems.size() <= 0)) {
                                    z = g.getCurrentSignDay() + -1 >= 0;
                                    boolean z2;
                                    if (g.getCurrentSignDay() - 1 < g.mItems.size()) {
                                        z2 = true;
                                    } else {
                                        z2 = false;
                                    }
                                    if (z && r4) {
                                        ((SignItem) g.mItems.get(g.getCurrentSignDay() - 1)).mSignedType = 1;
                                    }
                                }
                                this.c.refreshCheckInStates(g);
                                List b = com.qq.reader.cservice.adv.b.a(ReaderApplication.getApplicationImp()).b("103100");
                                if (b == null || b.size() <= 0 || b.get(0) == null) {
                                    aVar = null;
                                } else {
                                    aVar = (com.qq.reader.cservice.adv.a) b.get(0);
                                }
                                final Dialog access$2600 = this.c.initSignUpOkWindow((SignItem) cVar.a.get(0), aVar);
                                if (access$2600 != null) {
                                    access$2600.getWindow().setWindowAnimations(R.style.sign_pop_anim_style);
                                    access$2600.show();
                                    if (aVar != null) {
                                        Map hashMap = new HashMap();
                                        hashMap.put(s.ORIGIN, String.valueOf(aVar.d()));
                                        com.qq.reader.common.monitor.i.a("event_B213", hashMap, this.c.getContext());
                                    } else if (((SignItem) cVar.a.get(0)).mItemId != 105) {
                                        this.c.mHandler.postDelayed(new Runnable(this) {
                                            final /* synthetic */ AnonymousClass53 b;

                                            public void run() {
                                                try {
                                                    access$2600.dismiss();
                                                } catch (Exception e) {
                                                    com.qq.reader.common.monitor.debug.c.e("BookShelfActivity", e.getMessage());
                                                }
                                            }
                                        }, 3000);
                                    }
                                }
                            }
                            SignupManager.a().f();
                        }
                    });
                    return;
                }
                return;
            case 2:
                if (obj != null && getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable(this) {
                        final /* synthetic */ BookShelfFragment b;

                        public void run() {
                            if (this.b.getActivity() != null) {
                                final SignItem signItem = (SignItem) obj;
                                final LuckyDrawDialog luckyDrawDialog = new LuckyDrawDialog(this.b.getActivity(), R.style.fullscreen_dim_dialog);
                                String c = c.c().c();
                                if (signItem.mNeedAddress) {
                                    d.c(this.b.mContext, false, c);
                                    d.b(ReaderApplication.getApplicationImp(), (signItem.mCount != 0 ? Integer.valueOf(signItem.mCount) : "") + signItem.mPrize, c);
                                } else {
                                    d.c(this.b.mContext, true, c);
                                    d.b(ReaderApplication.getApplicationImp(), "", c);
                                }
                                if (c.b()) {
                                    d.b(this.b.getApplicationContext(), true, c.c().c());
                                }
                                ArrayList b = SignupManager.a().b();
                                if (b == null) {
                                    this.b.showFragmentDialog(504);
                                    return;
                                }
                                d.a(this.b.mContext, SignupManager.a().a(b), c.c().c());
                                luckyDrawDialog.a(false, signItem, SignupManager.a().b());
                                luckyDrawDialog.a(new OnClickListener(this) {
                                    final /* synthetic */ AnonymousClass52 b;

                                    public void onClick(View view) {
                                        this.b.b.showFragmentDialog(505);
                                        luckyDrawDialog.dismiss();
                                    }
                                });
                                luckyDrawDialog.setOnDismissListener(new OnDismissListener(this) {
                                    final /* synthetic */ AnonymousClass52 c;

                                    public void onDismiss(DialogInterface dialogInterface) {
                                        if (!signItem.mNeedAddress) {
                                            luckyDrawDialog.dismiss();
                                        }
                                        this.c.b.refreshCheckInStates(SignupManager.a().g());
                                    }
                                });
                                luckyDrawDialog.getWindow().setWindowAnimations(R.style.sign_pop_anim_style);
                                luckyDrawDialog.show();
                            }
                        }
                    });
                    return;
                }
                return;
            case 3:
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable(this) {
                        final /* synthetic */ BookShelfFragment b;

                        public void run() {
                            if (obj != null) {
                                ArrayList arrayList = (ArrayList) obj;
                                if (this.b.getActivity() != null) {
                                    LuckyDrawDialog luckyDrawDialog = new LuckyDrawDialog(this.b.getActivity(), R.style.fullscreen_dim_dialog);
                                    if (this.b.mCheckGift) {
                                        luckyDrawDialog.a(true, null, arrayList);
                                        if (!(this.b.getActivity() == null || this.b.getActivity().isFinishing())) {
                                            try {
                                                luckyDrawDialog.show();
                                            } catch (Throwable th) {
                                                com.qq.reader.common.monitor.debug.c.e("BookShelfFragment", th.getMessage());
                                            }
                                        }
                                        this.b.mCheckGift = false;
                                        return;
                                    }
                                    SignupManager.a().e();
                                }
                            }
                        }
                    });
                    return;
                }
                return;
            case 5:
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable(this) {
                        final /* synthetic */ BookShelfFragment a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            af.a(this.a.getApplicationContext(), (CharSequence) "提交成功", 0).a();
                            d.c(this.a.mContext, true, c.c().c());
                            this.a.refreshCheckInStates(SignupManager.a().g());
                        }
                    });
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onSignUpError(int i, final int i2) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            switch (i) {
                case 0:
                    activity.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ BookShelfFragment a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.refreshCheckInStates(SignupManager.a().g());
                        }
                    });
                    return;
                case 1:
                    activity.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ BookShelfFragment b;

                        public void run() {
                            if (i2 == -2) {
                                af.a(ReaderApplication.getApplicationImp(), (CharSequence) "已经签过了", 0).a();
                                this.b.setUserSignInFlag(true);
                                SignupManager.a().c();
                                SignupManager.a().a(this.b.getRequestSignDay());
                            } else if (i2 == 1004) {
                                this.b.showFragmentDialog(503);
                            } else {
                                af.a(ReaderApplication.getApplicationImp(), (CharSequence) "网络异常，请稍后重试", 0).a();
                            }
                        }
                    });
                    return;
                case 2:
                    activity.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ BookShelfFragment b;

                        public void run() {
                            if (i2 == -5) {
                                af.a(ReaderApplication.getApplicationImp(), (CharSequence) "已经抽过奖了", 0).a();
                                d.b(ReaderApplication.getApplicationImp(), true, c.c().c());
                            } else if (i2 == -6) {
                                af.a(ReaderApplication.getApplicationImp(), (CharSequence) "本周已经抽过奖了，同一设备不能重复抽取", 0).a();
                                d.b(ReaderApplication.getApplicationImp(), true, c.c().c());
                            } else if (i2 == -8) {
                                af.a(ReaderApplication.getApplicationImp(), (CharSequence) "出错啦，请稍后重试", 0).a();
                                d.b(ReaderApplication.getApplicationImp(), true, c.c().c());
                            } else if (i2 == -1) {
                                af.a(ReaderApplication.getApplicationImp(), (CharSequence) "抽奖失败", 0).a();
                                d.b(ReaderApplication.getApplicationImp(), true, c.c().c());
                            }
                            SignupManager.a().c();
                            SignupManager.a().a(this.b.getRequestSignDay());
                        }
                    });
                    return;
                case 3:
                    activity.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ BookShelfFragment b;

                        public void run() {
                            if (i2 == -1) {
                                af.a(ReaderApplication.getApplicationImp(), (CharSequence) "出错啦，请稍后重试", 0).a();
                            }
                        }
                    });
                    return;
                case 4:
                    activity.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ BookShelfFragment b;

                        public void run() {
                            if (i2 == -2) {
                                af.a(ReaderApplication.getApplicationImp(), (CharSequence) "已经签过了", 0).a();
                                this.b.setUserSignInFlag(true);
                                SignupManager.a().c();
                                SignupManager.a().a(this.b.getRequestSignDay());
                            } else if (i2 == 1004) {
                                this.b.showFragmentDialog(503);
                            } else {
                                af.a(ReaderApplication.getApplicationImp(), (CharSequence) "补签失败，请稍后重试", 0).a();
                            }
                        }
                    });
                    return;
                case 5:
                    activity.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ BookShelfFragment a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            af.a(ReaderApplication.getApplicationImp(), (CharSequence) "提交失败，请稍后再试", 0).a();
                            d.c(this.a.mContext, false, c.c().c());
                            this.a.refreshCheckInStates(SignupManager.a().g());
                        }
                    });
                    return;
                case 6:
                    activity.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ BookShelfFragment a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            af.a(ReaderApplication.getApplicationImp(), (CharSequence) "登录态失效，请重新登录！", 0).a();
                            c.a();
                        }
                    });
                    return;
                default:
                    return;
            }
        }
    }

    private boolean isSignViewShown() {
        return true;
    }

    private void releaseSignAni(RelativeLayout relativeLayout, ImageView imageView) {
        Object tag;
        for (int i = 1; i < relativeLayout.getChildCount(); i++) {
            ImageView imageView2 = (ImageView) relativeLayout.getChildAt(i);
            imageView2.setTag(Boolean.valueOf(true));
            tag = imageView2.getTag(R.string.obj_tag);
            if (tag instanceof ValueAnimator) {
                ((ValueAnimator) tag).cancel();
            }
        }
        tag = imageView.getTag();
        if (tag instanceof ValueAnimator) {
            ((ValueAnimator) tag).cancel();
        }
    }

    private Dialog initResignTipsWindow(SignInfo signInfo) {
        Context activity = getActivity();
        if (activity == null) {
            return null;
        }
        View inflate = activity.getLayoutInflater().inflate(R.layout.bookshelf_sign_make_up_tip_pop_window, null);
        final Dialog leakFixDialog = new LeakFixDialog(activity);
        leakFixDialog.requestWindowFeature(1);
        leakFixDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        leakFixDialog.setCanceledOnTouchOutside(true);
        TextView textView = (TextView) inflate.findViewById(R.id.open_vip_btn);
        View findViewById = inflate.findViewById(R.id.rmd_area);
        String str = "";
        final List<SignItem> missDaysTillNow = signInfo.getMissDaysTillNow(signInfo.getCurrentSignDay());
        List arrayList = new ArrayList();
        if (missDaysTillNow == null) {
            return null;
        }
        CharSequence string;
        OnClickListener anonymousClass65;
        for (SignItem signItem : missDaysTillNow) {
            String a = SignupManager.a().a(getApplicationContext(), signItem);
            com.qq.reader.module.bookshelf.signup.b.a aVar = new com.qq.reader.module.bookshelf.signup.b.a();
            aVar.a(a);
            aVar.b(SignupManager.a().b(signItem.mItemId));
            aVar.a(signItem.mItemId);
            if (!arrayList.contains(aVar)) {
                arrayList.add(aVar);
            }
        }
        if (arrayList != null && arrayList.size() > 0) {
            GridView gridView = (GridView) inflate.findViewById(R.id.sign_grid);
            if (arrayList.size() == 1) {
                gridView.setAdapter(new com.qq.reader.module.bookshelf.signup.a.a(getActivity(), arrayList, 1));
                gridView.setNumColumns(1);
            } else if (arrayList.size() == 2) {
                gridView.setAdapter(new com.qq.reader.module.bookshelf.signup.a.a(getActivity(), arrayList, 2));
                gridView.setNumColumns(2);
                gridView.setVerticalSpacing(ao.a(18.0f));
                gridView.setHorizontalSpacing(ao.a(20.0f));
            } else if (arrayList.size() >= 3) {
                gridView.setAdapter(new com.qq.reader.module.bookshelf.signup.a.a(getActivity(), arrayList, 3));
                gridView.setNumColumns(3);
                gridView.setVerticalSpacing(ao.a(18.0f));
                gridView.setHorizontalSpacing(ao.a(8.0f));
            }
            computeGridViewWidth(gridView, arrayList);
        }
        findViewById.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ BookShelfFragment b;

            public void onClick(View view) {
                if (!BookShelfFragment.isFastClick() && this.b.getActivity() != null) {
                    new JSPay(this.b.getActivity()).openVip();
                    com.qq.reader.common.monitor.i.a("event_A138", null, this.b.mContext);
                    if (leakFixDialog != null && leakFixDialog.isShowing()) {
                        leakFixDialog.dismiss();
                    }
                }
            }
        });
        if (c.c().j(getApplicationContext())) {
            findViewById.setClickable(false);
            textView.setTextColor(getResources().getColor(R.color.text_color_c103));
            textView.setText(R.string.sign_make_up_tip3);
            textView.setCompoundDrawables(null, null, null, null);
            if (missDaysTillNow.size() != 1 || signInfo.mTotalSupplyCount > 0) {
                String format;
                if (signInfo.mTotalSupplyCount > 0) {
                    format = String.format(getApplicationContext().getString(R.string.sign_make_up_btn), new Object[]{Integer.valueOf(signInfo.mTotalSupplyCount)});
                } else {
                    format = String.format(getApplicationContext().getString(R.string.sign_make_up_btn), new Object[]{Integer.valueOf((missDaysTillNow.size() - 1) * 10)});
                }
                AnonymousClass66 anonymousClass66 = new OnClickListener(this) {
                    final /* synthetic */ BookShelfFragment c;

                    public void onClick(View view) {
                        if (!BookShelfFragment.isFastClick()) {
                            com.qq.reader.common.monitor.i.a("event_A137", null, this.c.mContext);
                            if (c.c().g(this.c.mContext) >= (missDaysTillNow.size() - 1) * 10) {
                                int[] iArr = new int[missDaysTillNow.size()];
                                for (int i = 0; i < missDaysTillNow.size(); i++) {
                                    iArr[i] = ((SignItem) missDaysTillNow.get(i)).mDay;
                                }
                                SignupManager.a().a(iArr, 1);
                                leakFixDialog.dismiss();
                                return;
                            }
                            this.c.showFragmentDialog(503);
                        }
                    }
                };
                Object obj = format;
                Object obj2 = anonymousClass66;
            } else {
                string = getApplicationContext().getString(R.string.sign_free);
                anonymousClass65 = new OnClickListener(this) {
                    final /* synthetic */ BookShelfFragment c;

                    public void onClick(View view) {
                        if (!BookShelfFragment.isFastClick()) {
                            SignupManager.a().a(new int[]{((SignItem) missDaysTillNow.get(0)).mDay}, 1);
                            leakFixDialog.dismiss();
                        }
                    }
                };
            }
        } else {
            findViewById.setClickable(true);
            textView.setText(R.string.sign_make_up_tip2);
            string = String.format(getApplicationContext().getString(R.string.sign_make_up_btn), new Object[]{Integer.valueOf(missDaysTillNow.size() * 10)});
            anonymousClass65 = new OnClickListener(this) {
                final /* synthetic */ BookShelfFragment c;

                public void onClick(View view) {
                    if (!BookShelfFragment.isFastClick()) {
                        com.qq.reader.common.monitor.i.a("event_A137", null, this.c.mContext);
                        if (c.c().g(this.c.mContext) >= missDaysTillNow.size() * 10) {
                            int[] iArr = new int[missDaysTillNow.size()];
                            for (int i = 0; i < missDaysTillNow.size(); i++) {
                                iArr[i] = ((SignItem) missDaysTillNow.get(i)).mDay;
                            }
                            SignupManager.a().a(iArr, 1);
                            leakFixDialog.dismiss();
                            return;
                        }
                        this.c.showFragmentDialog(503);
                    }
                }
            };
        }
        return new AlertDialog.a(activity).a(String.format(getApplicationContext().getString(R.string.sign_miss), new Object[]{Integer.valueOf(missDaysTillNow.size())})).a(inflate).a(string, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ BookShelfFragment b;

            public void onClick(DialogInterface dialogInterface, int i) {
                anonymousClass65.onClick(null);
            }
        }).b(getApplicationContext().getString(R.string.alert_dialog_cancel), new DialogInterface.OnClickListener(this) {
            final /* synthetic */ BookShelfFragment a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).a();
    }

    @SuppressLint({"NewApi"})
    private Dialog initResignOkWindow(SignupManager.c cVar) {
        Context activity = getActivity();
        if (activity == null) {
            return null;
        }
        View inflate = activity.getLayoutInflater().inflate(R.layout.bookshelf_sign_make_up_pop_window, null);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.container);
        linearLayout.removeAllViews();
        if (cVar.a.size() == 1) {
            inflate.findViewById(R.id.resign_bottom_blank).setVisibility(0);
            linearLayout.setBackgroundResource(R.drawable.common_backgraound_white);
        } else {
            linearLayout.setBackgroundResource(R.drawable.resign_bottm_blank_bg);
        }
        int i = 0;
        while (i < cVar.a.size()) {
            int i2 = ((SignItem) cVar.a.get(i)).mItemId;
            View inflate2 = activity.getLayoutInflater().inflate(R.layout.bookshelf_resign_item_normal, null);
            if (cVar.a.size() == 1) {
                ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
                layoutParams.gravity = 17;
                inflate2.setLayoutParams(layoutParams);
                inflate2.requestLayout();
            }
            TextView textView = (TextView) inflate2.findViewById(R.id.text1);
            TextView textView2 = (TextView) inflate2.findViewById(R.id.text2);
            TextView textView3 = (TextView) inflate2.findViewById(R.id.image_week);
            final ImageView imageView = (ImageView) inflate2.findViewById(R.id.image1);
            if (i != cVar.a.size() - 1) {
                inflate2.findViewById(R.id.divider).setVisibility(0);
            } else {
                inflate2.findViewById(R.id.divider).setVisibility(8);
            }
            imageView.setVisibility(0);
            try {
                if (cVar.b != null && i < cVar.b.length) {
                    CharSequence week = getWeek(cVar.b[i]);
                    if (TextUtils.isEmpty(week)) {
                        textView3.setVisibility(8);
                    } else {
                        textView3.setVisibility(0);
                        textView3.setText(week);
                    }
                }
            } catch (Exception e) {
            }
            if (i2 == 102) {
                textView2.setVisibility(8);
                textView.setText(((SignItem) cVar.a.get(i)).mPrize + "+" + ((SignItem) cVar.a.get(i)).mCount);
                imageView.setBackgroundResource(R.drawable.sign_ticket_large);
            } else if (i2 == 5) {
                textView2.setVisibility(8);
                textView.setText(((SignItem) cVar.a.get(i)).mPrize + "+" + ((SignItem) cVar.a.get(i)).mCount);
                imageView.setBackgroundResource(R.drawable.sign_exp_large);
            } else if (i2 == 103) {
                textView.setVisibility(0);
                textView2.setVisibility(0);
                com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g((long) ((SignItem) cVar.a.get(i)).mBookid), imageView, com.qq.reader.common.imageloader.a.a().j());
                textView.setText(String.format(getApplicationContext().getString(R.string.sign_reward_unlock_detail), new Object[]{((SignItem) cVar.a.get(i)).mPrize}));
                textView2.setText(String.format(getApplicationContext().getString(R.string.sign_reward_unlock_chapter_count), new Object[]{Integer.valueOf(((SignItem) cVar.a.get(i)).mCount)}));
                new JSAddToBookShelf(activity).addBook(((SignItem) cVar.a.get(i)).mExtInfo);
                refreshTab();
            } else if (i2 == 104) {
                textView.setVisibility(0);
                textView2.setVisibility(0);
                imageView.setImageResource(R.drawable.default_sign_free_book_cover);
                com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g((long) ((SignItem) cVar.a.get(i)).mBookid), imageView, com.qq.reader.common.imageloader.a.a().j());
                textView2.setText(((SignItem) cVar.a.get(i)).mPrize);
                textView.setText(String.format(getApplicationContext().getString(R.string.checkin_free_limit), new Object[]{Integer.valueOf(((SignItem) cVar.a.get(i)).mCount)}));
                d.a(this.mContext, (long) ((SignItem) cVar.a.get(i)).mBookid, ((long) (((((SignItem) cVar.a.get(i)).mCount * 60) * 60) * 1000)) + System.currentTimeMillis(), c.c().c());
                refreshTab();
            } else if (i2 == 105) {
                textView.setVisibility(0);
                textView2.setVisibility(0);
                DeductionExtInfo displayResignBookCoverUrl = displayResignBookCoverUrl((SignItem) cVar.a.get(i));
                if (displayResignBookCoverUrl != null) {
                    if (TextUtils.isEmpty(displayResignBookCoverUrl.imageUrl)) {
                        imageView.setImageResource(R.drawable.sign_deduction_large);
                    } else {
                        if (imageView != null) {
                            ViewGroup.LayoutParams layoutParams2 = imageView.getLayoutParams();
                            layoutParams2.height = getApplicationContext().getResources().getDimensionPixelOffset(R.dimen.localstore_imgsize_smallcover_height);
                            layoutParams2.width = getApplicationContext().getResources().getDimensionPixelOffset(R.dimen.localstore_imgsize_smallcover_width);
                            imageView.setLayoutParams(layoutParams2);
                        }
                        com.qq.reader.common.imageloader.c.a(getContext()).a(displayResignBookCoverUrl.imageUrl, imageView, com.qq.reader.common.imageloader.a.a().v(), new com.bumptech.glide.request.e<String, com.bumptech.glide.load.resource.a.b>(this) {
                            final /* synthetic */ BookShelfFragment b;

                            public boolean a(Exception exception, String str, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                                if (imageView != null) {
                                    Drawable drawable = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.sign_deduction_large);
                                    if (imageView instanceof ImageView) {
                                        imageView.setImageDrawable(drawable);
                                    }
                                }
                                return true;
                            }

                            public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                                if (bVar == null || imageView == null || !(bVar instanceof com.bumptech.glide.load.resource.bitmap.j)) {
                                    return false;
                                }
                                imageView.setImageBitmap(((com.bumptech.glide.load.resource.bitmap.j) bVar).b());
                                return true;
                            }
                        });
                    }
                    textView2.setText(SignupManager.a().a(getApplicationContext(), displayResignBookCoverUrl.bookType, displayResignBookCoverUrl.deductionType, displayResignBookCoverUrl.intro));
                } else {
                    imageView.setImageResource(R.drawable.sign_deduction_large);
                    textView2.setText(getApplicationContext().getString(R.string.sign_deduction_default_intro));
                }
                if (((SignItem) cVar.a.get(i)).mCount > 0) {
                    textView.setText(String.format("抵扣券+%d", new Object[]{Integer.valueOf(((SignItem) cVar.a.get(i)).mCount)}));
                }
            }
            LayoutParams layoutParams3 = new LayoutParams(-2, getApplicationContext().getResources().getDimensionPixelOffset(R.dimen.bookshelf_resign_item_height));
            linearLayout.addView(inflate2);
            i++;
        }
        final Dialog leakFixDialog = new LeakFixDialog(activity, R.style.fullscreen_dim_dialog);
        leakFixDialog.requestWindowFeature(1);
        leakFixDialog.setContentView(inflate);
        WindowManager.LayoutParams attributes = leakFixDialog.getWindow().getAttributes();
        attributes.dimAmount = 0.7f;
        leakFixDialog.getWindow().setAttributes(attributes);
        leakFixDialog.getWindow().addFlags(2);
        leakFixDialog.setCanceledOnTouchOutside(true);
        inflate.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ BookShelfFragment b;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (leakFixDialog != null && leakFixDialog.isShowing()) {
                    leakFixDialog.dismiss();
                }
                return false;
            }
        });
        return leakFixDialog;
    }

    private DeductionExtInfo displayResignBookCoverUrl(SignItem signItem) {
        Object obj = 1;
        String str = "";
        DeductionExtInfo deductionExtInfo = new DeductionExtInfo();
        try {
            JSONObject jSONObject = new JSONObject(signItem.mExtInfo);
            deductionExtInfo.deductionType = jSONObject.optString("deductionType");
            deductionExtInfo.bookType = jSONObject.optString("bookType");
            deductionExtInfo.intro = jSONObject.optString("intro");
            deductionExtInfo.typeValue = jSONObject.optLong("typeValue");
            deductionExtInfo.qurl = jSONObject.optString("qurl");
            Object obj2 = ("1".equals(deductionExtInfo.bookType) && "1".equals(deductionExtInfo.deductionType)) ? 1 : null;
            if (!("2".equals(deductionExtInfo.bookType) && "1".equals(deductionExtInfo.deductionType))) {
                obj = null;
            }
            if (!((obj2 == null && obj == null) || deductionExtInfo.typeValue == 0)) {
                if (obj2 != null) {
                    str = ao.g(deductionExtInfo.typeValue);
                } else if (obj != null) {
                    str = ao.h(deductionExtInfo.typeValue);
                }
                deductionExtInfo.imageUrl = str;
            }
        } catch (Exception e) {
            deductionExtInfo.deductionType = "";
            deductionExtInfo.bookType = "";
            deductionExtInfo.intro = "";
            deductionExtInfo.typeValue = 0;
            deductionExtInfo.imageUrl = "";
            deductionExtInfo.qurl = "";
        }
        return deductionExtInfo;
    }

    private String getWeek(int i) {
        String str = "";
        switch (i) {
            case 1:
                return "周一补签";
            case 2:
                return "周二补签";
            case 3:
                return "周三补签";
            case 4:
                return "周四补签";
            case 5:
                return "周五补签";
            case 6:
                return "周六补签";
            default:
                return str;
        }
    }

    protected void quit() {
        if (com.qq.reader.common.c.a.a() && getActivity() != null) {
            getActivity().finish();
        }
    }

    private void playBubbleAnimation(final View view) {
        Random random = new Random();
        final int nextInt = random.nextInt(100) + 300;
        final int nextInt2 = random.nextInt(360) + 1;
        double nextDouble = random.nextDouble() + 2.0d;
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, nextInt});
        Object tag = view.getTag(R.string.obj_tag);
        if (tag instanceof ValueAnimator) {
            ((ValueAnimator) tag).cancel();
        }
        view.setTag(R.string.obj_tag, ofInt);
        ofInt.setTarget(view);
        ofInt.setDuration((long) ((int) (nextDouble * ((double) nextInt))));
        ofInt.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ BookShelfFragment d;

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float f = 0.5f;
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                int sin = (int) (Math.sin((double) nextInt2) * ((double) intValue));
                int cos = (int) (Math.cos((double) nextInt2) * ((double) intValue));
                view.setScaleX(((float) intValue) / ((float) nextInt) > 0.5f ? ((float) intValue) / ((float) nextInt) : 0.5f);
                View view = view;
                if (((float) intValue) / ((float) nextInt) > 0.5f) {
                    f = ((float) intValue) / ((float) nextInt);
                }
                view.setScaleY(f);
                view.setAlpha(1.0f - (((float) intValue) / ((float) nextInt)));
                view.setTranslationX((float) cos);
                view.setTranslationY((float) sin);
            }
        });
        ofInt.addListener(new AnimatorListener(this) {
            final /* synthetic */ BookShelfFragment b;

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                if (view.getTag() == null) {
                    this.b.playBubbleAnimation(view);
                }
            }

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }
        });
        ofInt.start();
    }

    @SuppressLint({"NewApi"})
    private Dialog initSignUpOkWindow(SignItem signItem, com.qq.reader.cservice.adv.a aVar) {
        if (getActivity() == null) {
            return null;
        }
        try {
            View inflate = getActivity().getLayoutInflater().inflate(R.layout.bookshelf_sign_ok_pop_window, null);
            TextView textView = (TextView) inflate.findViewById(R.id.prefix);
            TextView textView2 = (TextView) inflate.findViewById(R.id.count);
            TextView textView3 = (TextView) inflate.findViewById(R.id.unit);
            TextView textView4 = (TextView) inflate.findViewById(R.id.text2);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.immdiate_use_ll);
            Button button = (Button) inflate.findViewById(R.id.immdiate_use);
            final SignInfo g = SignupManager.a().g();
            final ImageView imageView = (ImageView) inflate.findViewById(R.id.image1);
            linearLayout.setVisibility(8);
            String str = "";
            if (signItem.mItemId == 5) {
                imageView.setVisibility(0);
                imageView.setImageResource(R.drawable.sign_bottom_img);
                textView.setVisibility(0);
                textView2.setVisibility(0);
                textView3.setVisibility(0);
                textView4.setVisibility(8);
                textView2.setText("" + signItem.mCount);
                textView3.setText(signItem.mPrize);
            } else if (signItem.mItemId == 102) {
                imageView.setVisibility(0);
                imageView.setImageResource(R.drawable.sign_ticket_large);
                textView.setVisibility(0);
                textView2.setVisibility(0);
                textView3.setVisibility(0);
                textView4.setVisibility(8);
                textView2.setText("" + signItem.mCount);
                textView3.setText(signItem.mPrize);
            } else if (signItem.mItemId == 103) {
                textView2.setVisibility(0);
                textView4.setVisibility(0);
                textView2.setTextSize(0, getResources().getDimension(R.dimen.text_size_class_4));
                textView2.setTextColor(getResources().getColor(R.color.text_color_c102));
                imageView.setImageResource(R.drawable.default_sign_book_cover);
                com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g((long) signItem.mBookid), imageView, com.qq.reader.common.imageloader.a.a().j());
                try {
                    r2 = new JSONObject(signItem.mExtInfo);
                    textView2.setText(TextUtils.isEmpty(r2.optString("title")) ? "" : "《" + r2.optString("title") + "》");
                    textView4.setText("解锁收费章节" + String.format("+%d", new Object[]{Integer.valueOf(signItem.mCount)}));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (getActivity() != null) {
                    new JSAddToBookShelf(getActivity()).addBook(signItem.mExtInfo);
                    refreshTab();
                }
            } else if (signItem.mItemId == 104) {
                textView2.setVisibility(0);
                textView4.setVisibility(0);
                textView2.setTextSize(0, getResources().getDimension(R.dimen.text_size_class_4));
                textView2.setTextColor(getResources().getColor(R.color.text_color_c102));
                imageView.setImageResource(R.drawable.default_sign_free_book_cover);
                com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g((long) signItem.mBookid), imageView, com.qq.reader.common.imageloader.a.a().j());
                try {
                    r2 = new JSONObject(signItem.mExtInfo);
                    textView2.setText(TextUtils.isEmpty(r2.optString("title")) ? "" : "《" + r2.optString("title") + "》");
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                textView4.setText(String.format("限免%d小时", new Object[]{Integer.valueOf(signItem.mCount)}));
                d.a(this.mContext, (long) signItem.mBookid, System.currentTimeMillis() + ((long) (((signItem.mCount * 60) * 60) * 1000)), c.c().c());
            } else if (signItem.mItemId == 105) {
                CharSequence charSequence;
                textView2.setVisibility(0);
                textView4.setVisibility(0);
                textView2.setTextSize(0, getResources().getDimension(R.dimen.text_size_class_4));
                textView2.setTextColor(getResources().getColor(R.color.text_color_c102));
                DeductionExtInfo displayResignBookCoverUrl = displayResignBookCoverUrl(signItem);
                if (displayResignBookCoverUrl != null) {
                    if (TextUtils.isEmpty(displayResignBookCoverUrl.imageUrl)) {
                        imageView.setImageResource(R.drawable.sign_deduction_large);
                    } else {
                        if (imageView != null) {
                            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                            layoutParams.height = getApplicationContext().getResources().getDimensionPixelOffset(R.dimen.localstore_imgsize_bigcover_height);
                            layoutParams.width = getApplicationContext().getResources().getDimensionPixelOffset(R.dimen.localstore_imgsize_bigcover_width);
                            imageView.setLayoutParams(layoutParams);
                        }
                        com.qq.reader.common.imageloader.c.a(getContext()).a(displayResignBookCoverUrl.imageUrl, imageView, com.qq.reader.common.imageloader.a.a().v(), new com.bumptech.glide.request.e<String, com.bumptech.glide.load.resource.a.b>(this) {
                            final /* synthetic */ BookShelfFragment b;

                            public boolean a(Exception exception, String str, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                                if (imageView != null) {
                                    Drawable drawable = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.default_sign_free_book_cover);
                                    if (imageView instanceof ImageView) {
                                        imageView.setImageDrawable(drawable);
                                    }
                                }
                                return true;
                            }

                            public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                                if (bVar == null || imageView == null || !(bVar instanceof com.bumptech.glide.load.resource.bitmap.j)) {
                                    return false;
                                }
                                imageView.setImageBitmap(((com.bumptech.glide.load.resource.bitmap.j) bVar).b());
                                return true;
                            }
                        });
                    }
                    charSequence = displayResignBookCoverUrl.qurl;
                    textView2.setText(SignupManager.a().a(getApplicationContext(), displayResignBookCoverUrl.bookType, displayResignBookCoverUrl.deductionType, displayResignBookCoverUrl.intro));
                } else {
                    imageView.setImageResource(R.drawable.sign_deduction_large);
                    textView2.setText(getApplicationContext().getString(R.string.sign_deduction_default_intro));
                    Object obj = str;
                }
                if (TextUtils.isEmpty(charSequence)) {
                    linearLayout.setVisibility(8);
                } else {
                    linearLayout.setVisibility(0);
                    com.qq.reader.common.monitor.i.a("event_A276", null, ReaderApplication.getApplicationImp());
                }
                if (signItem.mCount > 0) {
                    textView4.setText(String.format("抵扣券+%d", new Object[]{Integer.valueOf(signItem.mCount)}));
                }
                CharSequence charSequence2 = charSequence;
            }
            if (getActivity() == null) {
                return null;
            }
            final Dialog dialog = new Dialog(getActivity(), R.style.fullscreen_dim_dialog);
            dialog.setContentView(inflate);
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.dimAmount = 0.7f;
            dialog.getWindow().setAttributes(attributes);
            dialog.getWindow().addFlags(2);
            if (linearLayout.getVisibility() == 0) {
                button.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ BookShelfFragment c;

                    public void onClick(View view) {
                        try {
                            if (!TextUtils.isEmpty(str)) {
                                com.qq.reader.qurl.c.a(this.c.getActivity(), str);
                                com.qq.reader.common.monitor.i.a("event_A277", null, ReaderApplication.getApplicationImp());
                            }
                            if (dialog != null && dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        } catch (Exception e) {
                        }
                    }
                });
            }
            ImageView imageView2;
            if (aVar != null) {
                imageView2 = (ImageView) inflate.findViewById(R.id.close_btn);
                imageView2.setVisibility(0);
                imageView2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ BookShelfFragment b;

                    public void onClick(View view) {
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }
                });
                initSignOkAdvert(dialog, inflate, aVar);
                dialog.setCanceledOnTouchOutside(false);
            } else if (signItem.mItemId == 105) {
                dialog.setCanceledOnTouchOutside(false);
                imageView2 = (ImageView) inflate.findViewById(R.id.close_btn);
                imageView2.setVisibility(0);
                imageView2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ BookShelfFragment b;

                    public void onClick(View view) {
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }
                });
                try {
                    if (imageView2.getLayoutParams() != null && (imageView2.getLayoutParams() instanceof MarginLayoutParams)) {
                        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) imageView2.getLayoutParams();
                        marginLayoutParams.topMargin = ao.a(40.0f);
                        imageView2.setLayoutParams(marginLayoutParams);
                    }
                } catch (Exception e3) {
                }
            } else {
                dialog.setCanceledOnTouchOutside(true);
                inflate.setOnTouchListener(new OnTouchListener(this) {
                    final /* synthetic */ BookShelfFragment b;

                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        return false;
                    }
                });
            }
            final com.qq.reader.cservice.adv.a aVar2 = aVar;
            dialog.setOnDismissListener(new OnDismissListener(this) {
                final /* synthetic */ BookShelfFragment c;

                public void onDismiss(DialogInterface dialogInterface) {
                    if (g == null || g.getCurrentSignDay() == 7) {
                        this.c.refreshCheckInStates(g);
                    } else {
                        this.c.mHandler.postDelayed(new Runnable(this) {
                            final /* synthetic */ AnonymousClass81 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.c.refreshCheckInStates(g);
                            }
                        }, 800);
                    }
                    if (aVar2 != null) {
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, String.valueOf(aVar2.d()));
                        com.qq.reader.common.monitor.i.a("event_B220", hashMap, this.c.getContext());
                    }
                }
            });
            return dialog;
        } catch (OutOfMemoryError e4) {
            return null;
        }
    }

    private void initSignOkAdvert(final Dialog dialog, View view, final com.qq.reader.cservice.adv.a aVar) {
        final ImageView imageView = (ImageView) view.findViewById(R.id.advert);
        imageView.setVisibility(0);
        com.qq.reader.common.imageloader.c.a((Fragment) this).a(aVar.g(), imageView, com.qq.reader.common.imageloader.a.a().i(), new com.bumptech.glide.request.e<String, com.bumptech.glide.load.resource.a.b>(this) {
            final /* synthetic */ BookShelfFragment d;

            public boolean a(Exception exception, String str, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                return false;
            }

            public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                imageView.setVisibility(0);
                imageView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ AnonymousClass82 a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        if (aVar != null) {
                            if (!aVar.p() || c.b()) {
                                this.a.d.handleNativeAdvViewOnClick(dialog, aVar);
                            } else {
                                AnonymousClass1 anonymousClass1 = new com.qq.reader.common.login.a(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void a(int i) {
                                        switch (i) {
                                            case 1:
                                                if (aVar != null) {
                                                    this.a.a.d.handleNativeAdvViewOnClick(dialog, aVar);
                                                    return;
                                                }
                                                return;
                                            default:
                                                return;
                                        }
                                    }
                                };
                                Message obtainMessage = this.a.d.mHandler.obtainMessage();
                                obtainMessage.obj = anonymousClass1;
                                obtainMessage.what = 65542;
                                this.a.d.mHandler.sendMessage(obtainMessage);
                            }
                        }
                        if (aVar != null) {
                            Map hashMap = new HashMap();
                            hashMap.put(s.ORIGIN, String.valueOf(aVar.d()));
                            com.qq.reader.common.monitor.i.a("event_B214", hashMap, this.a.d.getContext());
                        }
                    }
                });
                return false;
            }
        });
    }

    private void handleNativeAdvViewOnClick(Dialog dialog, com.qq.reader.cservice.adv.a aVar) {
        String h = aVar.h();
        if (com.qq.reader.qurl.c.a(h)) {
            try {
                com.qq.reader.qurl.c.a(getActivity(), h);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), WebBrowserForContents.class);
            intent.setFlags(SigType.WLOGIN_QRPUSH);
            intent.putExtra("com.qq.reader.WebContent", h);
            getActivity().startActivity(intent);
        }
        if (dialog.isShowing() && !getActivity().isFinishing()) {
            dialog.dismiss();
        }
    }

    private void setRequestSignDay(int i) {
        if (c.b()) {
            d.a(getApplicationContext(), i, c.c().c());
        }
    }

    private int getRequestSignDay() {
        if (!c.b()) {
            return 0;
        }
        return d.y(getApplicationContext(), c.c().c());
    }

    private void setUserSignInFlag(boolean z) {
        if (c.b()) {
            d.a(getApplicationContext(), z, c.c().c());
        }
    }

    public static synchronized boolean isFastClick() {
        boolean z;
        synchronized (BookShelfFragment.class) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - lastClickTime < 500) {
                z = true;
            } else {
                lastClickTime = currentTimeMillis;
                z = false;
            }
        }
        return z;
    }

    protected void startHeadIconShake() {
        if (this.animSet == null) {
            AnimationListener anonymousClass83 = new AnimationListener(this) {
                final /* synthetic */ BookShelfFragment a;

                {
                    this.a = r1;
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    if (animation == this.a.animSet[this.a.curAnimSetIndex]) {
                        BookShelfFragment bookShelfFragment = this.a;
                        bookShelfFragment.curAnimSetIndex++;
                        if (this.a.curAnimSetIndex >= 0 && this.a.curAnimSetIndex < this.a.animSet.length) {
                            this.a.animSet[this.a.curAnimSetIndex].reset();
                            this.a.mTitleBar_leftbtn_view.startAnimation(this.a.animSet[this.a.curAnimSetIndex]);
                        }
                    }
                }
            };
            this.animSet = new TranslateAnimation[(this.XDeltaArray.length - 1)];
            for (int i = 0; i < this.animSet.length; i++) {
                this.animSet[i] = new TranslateAnimation(this.XDeltaArray[i], this.XDeltaArray[i + 1], 0.0f, 0.0f);
                if (i % 2 == 0) {
                    this.animSet[i].setInterpolator(this.mContext, 17432582);
                } else {
                    this.animSet[i].setInterpolator(this.mContext, 17432581);
                }
                if (i == this.animSet.length - 1) {
                    this.animSet[i].setDuration(80);
                } else {
                    this.animSet[i].setDuration(50);
                }
                this.animSet[i].setAnimationListener(anonymousClass83);
            }
        }
        this.curAnimSetIndex = 0;
        this.curAnimSetCount = 0;
        this.animSet[this.curAnimSetIndex].reset();
        this.mTitleBar_leftbtn_view.startAnimation(this.animSet[this.curAnimSetIndex]);
    }

    private void createHeaderView() {
        if (getActivity() != null) {
            this.mSignFrameV2 = LayoutInflater.from(getActivity()).inflate(R.layout.bookshelf_checkin_layout_v2, null);
            this.mSignFrameBackground = (ImageView) this.mSignFrameV2.findViewById(R.id.img_bookshelf_header_background);
            this.tvTimeLong = (GradientBottomAutoRaiseNumView) this.mSignFrameV2.findViewById(R.id.tv_time_long);
            this.mTvReadTimetip = (TextView) this.mSignFrameV2.findViewById(R.id.tv_this_week_read_during);
            this.tvRookieDouble = (TextView) this.mSignFrameV2.findViewById(R.id.rookie_reading_double);
            this.tvRookieDouble.setTag(Boolean.valueOf(com.qq.reader.module.rookie.presenter.a.a().b));
            this.tvRookieDouble.setVisibility(com.qq.reader.module.rookie.presenter.a.a().b ? 0 : 4);
            this.mUserReadTimeData = new l();
            Looper.myQueue().addIdleHandler(new IdleHandler(this) {
                final /* synthetic */ BookShelfFragment a;

                {
                    this.a = r1;
                }

                public boolean queueIdle() {
                    com.qq.reader.common.readertask.g.a().a(new AnonymousClass1(this));
                    return false;
                }
            });
            this.checkInBtn = (TextView) this.mSignFrameV2.findViewById(R.id.checkin_btn);
            this.tvSignTipsA = (TextView) this.mSignFrameV2.findViewById(R.id.checkin_tip_a);
            this.tvSignTipsB = (TextView) this.mSignFrameV2.findViewById(R.id.checkin_tip_b);
            this.mWavingView = (WaveView) this.mSignFrameV2.findViewById(R.id.wlv_checkin);
            this.mWaveHelper = new WaveView.a(this.mWavingView);
            this.tvNumIncrease = (TextView) this.mSignFrameV2.findViewById(R.id.tv_num_increase);
            this.mCircleProgressBar = (CircleProgressBar) this.mSignFrameV2.findViewById(R.id.cpb_book_shelf);
            this.mCircleProgressBarTopAdvLayer = (ImageView) this.mSignFrameV2.findViewById(R.id.img_bookshelf_adv_gift_icon);
            this.checkInBtn.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ BookShelfFragment a;

                class AnonymousClass1 extends ReaderIOTask {
                    final /* synthetic */ AnonymousClass84 this$1;

                    AnonymousClass1(AnonymousClass84 anonymousClass84) {
                        this.this$1 = anonymousClass84;
                    }

                    public void run() {
                        com.qq.reader.common.c.a.W = (long) ((int) ao.n());
                        this.this$1.a.mUserReadTimeData.a(d.bT(this.this$1.a.getApplicationContext()));
                        this.this$1.a.mCurrentReadTime = this.this$1.a.mUserReadTimeData.a();
                        this.this$1.a.mHandler.sendEmptyMessage(1240);
                    }
                }

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (!ao.d(ReaderApplication.getApplicationImp())) {
                        af.a(ReaderApplication.getApplicationImp(), this.a.getApplicationContext().getString(R.string.net_not_available), 0).a();
                    } else if (c.b()) {
                        SignInfo g = SignupManager.a().g();
                        if (g == null) {
                            return;
                        }
                        if (this.a.checkInBtn.getText().toString().contains("漏签")) {
                            Dialog access$3200 = this.a.initResignTipsWindow(g);
                            if (access$3200 != null) {
                                access$3200.show();
                                com.qq.reader.common.monitor.i.a("event_A135", null, this.a.mContext);
                            }
                        } else if (this.a.checkInBtn.getText().toString().equals(this.a.getApplicationContext().getString(R.string.sign_get_reward))) {
                            this.a.mCheckGift = false;
                            SignupManager.a().d();
                            com.qq.reader.common.monitor.i.a("event_A140", null, this.a.mContext);
                        } else if (this.a.checkInBtn.getText().toString().equals(this.a.getApplicationContext().getString(R.string.sign_write_contact))) {
                            this.a.showFragmentDialog(505);
                        } else {
                            SignupManager.a().a(new int[]{g.getCurrentSignDay()}, 0);
                            com.qq.reader.common.monitor.i.a("event_A133", null, this.a.mContext);
                        }
                    } else {
                        this.a.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                            final /* synthetic */ AnonymousClass85 a;

                            {
                                this.a = r1;
                            }

                            public void a(int i) {
                                com.qq.reader.common.monitor.i.a("event_A155", null, this.a.a.mContext);
                                this.a.a.mAutoSign = true;
                                SignupManager.a().a(this.a.a.getRequestSignDay());
                            }
                        };
                        this.a.setLoginNextTask(this.a.mLoginNextTask);
                        this.a.startLogin();
                    }
                }
            });
            initBookshelfHeaderAdv();
        }
    }

    private void updateBookshelfHeaderBgAdv(List<com.qq.reader.cservice.adv.a> list) {
        OnClickListener anonymousClass86 = new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ BookShelfFragment a;

            {
                this.a = r1;
            }

            public void a(View view) {
                com.qq.reader.common.monitor.i.a("event_A201", null, ReaderApplication.getApplicationImp());
                if (com.qq.reader.module.rookie.presenter.a.a().b) {
                    com.qq.reader.common.monitor.i.a("event_F154", null, ReaderApplication.getApplicationImp());
                }
                com.qq.reader.common.utils.o.h((Activity) view.getContext(), "readTime.html", new JumpActivityParameter());
            }
        };
        int i = R.drawable.common_header_bg;
        if (com.qq.reader.module.rookie.presenter.a.a().b) {
            i = R.drawable.common_rookie_header_bg;
        }
        if (list == null || list.size() <= 0) {
            setDefaultSignFrameBackground(i);
            this.mSignFrameBackground.setOnClickListener(anonymousClass86);
            return;
        }
        com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) list.get(0);
        if (aVar != null) {
            Object g = aVar.g();
            String h = aVar.h();
            String str = "";
            str = "";
            str = "";
            str = "";
            str = ao.a(aVar.i(), 0);
            final String a = ao.a(aVar.i(), 1);
            final String a2 = ao.a(aVar.i(), 2);
            Object a3 = ao.a(aVar.i(), 3);
            if (TextUtils.isEmpty(h)) {
                this.mSignFrameBackground.setOnClickListener(anonymousClass86);
            } else {
                if (!TextUtils.isEmpty(a3)) {
                    h = ao.a(h, "titlebarcolor", a3.replace("#", ""));
                }
                this.mSignFrameBackground.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
                    final /* synthetic */ BookShelfFragment b;

                    public void a(View view) {
                        try {
                            com.qq.reader.qurl.c.a(this.b.getActivity(), h, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            if (!TextUtils.isEmpty(g) && !g.equals(this.headerBgImagUrl)) {
                com.qq.reader.common.imageloader.c.a((Fragment) this).a(aVar.g(), this.mSignFrameBackground, com.qq.reader.common.imageloader.a.a().b(com.qq.reader.common.c.a.bU, ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.bookshelf_header_bg_height)), new com.bumptech.glide.request.e<String, com.bumptech.glide.load.resource.a.b>(this) {
                    final /* synthetic */ BookShelfFragment e;

                    public boolean a(Exception exception, String str, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                        this.e.setDefaultSignFrameBackground(i);
                        return true;
                    }

                    public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                        this.e.headerBgImagUrl = str;
                        if (this.e.mWavingView != null) {
                            this.e.mWavingView.setMannuallyWaveColor(ao.y(str), ao.y(a));
                        }
                        if (this.e.tvTimeLong != null) {
                            this.e.tvTimeLong.setMannualSetShadowColor(ao.y(a2));
                        }
                        return false;
                    }
                });
                return;
            }
            return;
        }
        setDefaultSignFrameBackground(i);
        this.mSignFrameBackground.setOnClickListener(anonymousClass86);
    }

    private void setDefaultSignFrameBackground(int i) {
        this.mSignFrameBackground.setImageResource(i);
        resetWavingColorAndNumberShadowColor();
    }

    private void resetWavingColorAndNumberShadowColor() {
        if (this.mWavingView != null) {
            this.mWavingView.setMannuallyWaveColor(0, 0);
        }
        if (this.tvTimeLong != null) {
            this.tvTimeLong.setMannualSetShadowColor(0);
        }
    }

    private void updateGiftTopAdv(List<com.qq.reader.cservice.adv.a> list) {
        if (list == null || list.size() <= 0) {
            this.mCircleProgressBarTopAdvLayer.setVisibility(8);
            this.mCircleProgressBar.setVisibility(0);
            return;
        }
        com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) list.get(0);
        if (aVar != null) {
            final String g = aVar.g();
            String h = aVar.h();
            this.mCircleProgressBarTopAdvLayer.setVisibility(0);
            this.mCircleProgressBar.setVisibility(8);
            if (TextUtils.isEmpty(h)) {
                this.mCircleProgressBarTopAdvLayer.setOnClickListener(null);
            } else {
                Object i = aVar.i();
                if (!TextUtils.isEmpty(i)) {
                    h = ao.a(h, "titlebarcolor", i.replace("#", ""));
                }
                this.mCircleProgressBarTopAdvLayer.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
                    final /* synthetic */ BookShelfFragment b;

                    public void a(View view) {
                        try {
                            com.qq.reader.qurl.c.a(this.b.getActivity(), h, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            if (!TextUtils.isEmpty(g) && !g.equals(this.headerGiftImageUrl)) {
                int dimensionPixelOffset = ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.bookshelf_header_gift_top_layer_height);
                com.qq.reader.common.imageloader.c.a((Fragment) this).a(aVar.g(), this.mCircleProgressBarTopAdvLayer, com.qq.reader.common.imageloader.a.a().b(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.bookshelf_header_gift_top_layer_width), dimensionPixelOffset), new com.bumptech.glide.request.e<String, com.bumptech.glide.load.resource.a.b>(this) {
                    final /* synthetic */ BookShelfFragment b;

                    public boolean a(Exception exception, String str, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                        this.b.mCircleProgressBarTopAdvLayer.setVisibility(8);
                        this.b.mCircleProgressBar.setVisibility(0);
                        return true;
                    }

                    public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                        if (!(bVar instanceof com.bumptech.glide.load.resource.bitmap.j)) {
                            return false;
                        }
                        this.b.mCircleProgressBarTopAdvLayer.setImageBitmap(((com.bumptech.glide.load.resource.bitmap.j) bVar).b());
                        this.b.headerGiftImageUrl = g;
                        this.b.mCircleProgressBarTopAdvLayer.setVisibility(0);
                        this.b.mCircleProgressBar.setVisibility(8);
                        return true;
                    }
                });
                return;
            }
            return;
        }
        this.mCircleProgressBarTopAdvLayer.setVisibility(8);
        this.mCircleProgressBar.setVisibility(0);
    }

    private void initBookshelfHeaderAdv() {
        final List b = com.qq.reader.cservice.adv.b.a(ReaderApplication.getApplicationImp()).b("103117");
        final List b2 = com.qq.reader.cservice.adv.b.a(ReaderApplication.getApplicationImp()).b("103118");
        if (b == null || b2 == null || b.size() <= 0 || b2.size() <= 0) {
            updateBookshelfHeaderBgAdv(null);
            updateGiftTopAdv(null);
            return;
        }
        com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) b.get(0);
        com.qq.reader.cservice.adv.a aVar2 = (com.qq.reader.cservice.adv.a) b2.get(0);
        com.bumptech.glide.request.e anonymousClass92 = new com.bumptech.glide.request.e<String, com.bumptech.glide.load.resource.a.b>(this) {
            int a = 0;
            boolean b = false;
            final /* synthetic */ BookShelfFragment e;

            public boolean a(Exception exception, String str, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                this.a++;
                this.b = true;
                if (this.a == 2) {
                    this.e.updateBookshelfHeaderBgAdv(null);
                    this.e.updateGiftTopAdv(null);
                }
                return true;
            }

            public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                this.a++;
                if (!this.b && this.a == 2) {
                    this.e.updateBookshelfHeaderBgAdv(b);
                    this.e.updateGiftTopAdv(b2);
                }
                return false;
            }
        };
        com.qq.reader.common.imageloader.c.a((Fragment) this).a(aVar.g(), anonymousClass92);
        com.qq.reader.common.imageloader.c.a((Fragment) this).a(aVar2.g(), anonymousClass92);
    }

    private void updateBookshelfHeaderAdv() {
        com.qq.reader.common.readertask.g.a().a(new ReaderIOTask() {
            public void run() {
                super.run();
                final List b = com.qq.reader.cservice.adv.b.a(ReaderApplication.getApplicationImp()).b("103117");
                final List b2 = com.qq.reader.cservice.adv.b.a(ReaderApplication.getApplicationImp()).b("103118");
                if (b != null && b2 != null && b.size() > 0 && b2.size() > 0) {
                    com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) b.get(0);
                    com.qq.reader.cservice.adv.a aVar2 = (com.qq.reader.cservice.adv.a) b2.get(0);
                    com.bumptech.glide.request.e anonymousClass1 = new com.bumptech.glide.request.e<String, com.bumptech.glide.load.resource.a.b>(this) {
                        int a = 0;
                        boolean b = false;
                        final /* synthetic */ AnonymousClass93 e;

                        public boolean a(Exception exception, String str, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                            this.a++;
                            this.b = true;
                            return true;
                        }

                        public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                            this.a++;
                            if (!this.b && this.a == 2) {
                                BookShelfFragment.this.updateBookshelfHeaderBgAdv(b);
                                BookShelfFragment.this.updateGiftTopAdv(b2);
                            }
                            return false;
                        }
                    };
                    com.qq.reader.common.imageloader.c.a(BookShelfFragment.this.getActivity()).a(aVar.g(), anonymousClass1);
                    com.qq.reader.common.imageloader.c.a(BookShelfFragment.this.getActivity()).a(aVar2.g(), anonymousClass1);
                }
            }
        });
    }

    private void changeCheckInBgState(int i) {
        switch (i) {
            case 0:
                this.checkInBtn.setBackgroundResource(R.drawable.checkin_btn_normal_selector);
                this.checkInBtn.setTextColor(getApplicationContext().getResources().getColorStateList(R.color.skin_set_bookshelf_checkin_btn_uncheckin_textcolor));
                this.checkInBtn.setTag(R.string.obj_tag, Boolean.valueOf(true));
                return;
            case 1:
                this.checkInBtn.setTag(R.string.obj_tag, Boolean.valueOf(true));
                this.checkInBtn.setBackgroundResource(R.drawable.checkin_btn_red_selector);
                this.checkInBtn.setTextColor(getApplicationContext().getResources().getColorStateList(R.color.skin_set_bookshelf_checkin_btn_uncheckin_textcolor));
                return;
            case 2:
                this.checkInBtn.setTag(R.string.obj_tag, Boolean.valueOf(false));
                this.checkInBtn.setBackgroundDrawable(null);
                this.checkInBtn.setPadding(0, 0, 0, 0);
                this.checkInBtn.setTextColor(getApplicationContext().getResources().getColorStateList(R.color.skin_set_bookshelf_checkin_btn_checkin_already_textcolor));
                return;
            default:
                return;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void changeTipsStatus(com.qq.reader.module.bookshelf.signup.SignupManager.SignItem r7, int r8) {
        /*
        r6 = this;
        r0 = 2130838403; // 0x7f020383 float:1.7281787E38 double:1.052774052E-314;
        r1 = 0;
        r3 = "";
        r2 = "";
        switch(r8) {
            case 0: goto L_0x0026;
            case 1: goto L_0x004d;
            case 2: goto L_0x0074;
            case 3: goto L_0x0084;
            case 4: goto L_0x00b4;
            default: goto L_0x000d;
        };
    L_0x000d:
        r0 = r1;
        r4 = r3;
        r3 = r2;
        r2 = r1;
    L_0x0011:
        r5 = r6.tvSignTipsA;
        r5.setText(r4);
        r4 = r6.tvSignTipsB;
        r4.setText(r3);
        r3 = r6.tvSignTipsA;
        r3.setCompoundDrawablesWithIntrinsicBounds(r2, r1, r1, r1);
        r2 = r6.tvSignTipsB;
        r2.setCompoundDrawablesWithIntrinsicBounds(r0, r1, r1, r1);
        return;
    L_0x0026:
        if (r7 == 0) goto L_0x000d;
    L_0x0028:
        r0 = r6.getApplicationContext();
        r2 = 2131296562; // 0x7f090132 float:1.8211044E38 double:1.0530004124E-314;
        r3 = r0.getString(r2);
        r0 = com.qq.reader.module.bookshelf.signup.SignupManager.a();
        r2 = r6.getApplicationContext();
        r2 = r0.a(r2, r7);
        r0 = com.qq.reader.module.bookshelf.signup.SignupManager.a();
        r4 = r7.mItemId;
        r0 = r0.b(r4);
        r4 = r3;
        r3 = r2;
        r2 = r1;
        goto L_0x0011;
    L_0x004d:
        if (r7 == 0) goto L_0x000d;
    L_0x004f:
        r0 = r6.getApplicationContext();
        r2 = 2131296563; // 0x7f090133 float:1.8211046E38 double:1.053000413E-314;
        r3 = r0.getString(r2);
        r0 = com.qq.reader.module.bookshelf.signup.SignupManager.a();
        r2 = r6.getApplicationContext();
        r2 = r0.a(r2, r7);
        r0 = com.qq.reader.module.bookshelf.signup.SignupManager.a();
        r4 = r7.mItemId;
        r0 = r0.b(r4);
        r4 = r3;
        r3 = r2;
        r2 = r1;
        goto L_0x0011;
    L_0x0074:
        r3 = r6.getApplicationContext();
        r4 = 2131297379; // 0x7f090463 float:1.8212701E38 double:1.053000816E-314;
        r3 = r3.getString(r4);
        r4 = r3;
        r3 = r2;
        r2 = r0;
        r0 = r1;
        goto L_0x0011;
    L_0x0084:
        r4 = com.qq.reader.common.login.c.b();
        if (r4 == 0) goto L_0x00c5;
    L_0x008a:
        r3 = com.qq.reader.ReaderApplication.getApplicationImp();
        r4 = com.qq.reader.common.login.c.c();
        r4 = r4.c();
        r3 = com.qq.reader.appconfig.a.d.w(r3, r4);
        r4 = r6.getApplicationContext();
        r5 = 2131297355; // 0x7f09044b float:1.8212653E38 double:1.053000804E-314;
        r4 = r4.getString(r5);
        r5 = 1;
        r5 = new java.lang.Object[r5];
        r5[r1] = r3;
        r3 = java.lang.String.format(r4, r5);
        r4 = r3;
        r3 = r2;
        r2 = r0;
        r0 = r1;
        goto L_0x0011;
    L_0x00b4:
        r3 = r6.getApplicationContext();
        r4 = 2131297365; // 0x7f090455 float:1.8212673E38 double:1.053000809E-314;
        r3 = r3.getString(r4);
        r4 = r3;
        r3 = r2;
        r2 = r0;
        r0 = r1;
        goto L_0x0011;
    L_0x00c5:
        r4 = r3;
        r3 = r2;
        r2 = r0;
        r0 = r1;
        goto L_0x0011;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.activity.BookShelfFragment.changeTipsStatus(com.qq.reader.module.bookshelf.signup.SignupManager$SignItem, int):void");
    }

    private void initNoUserCheckUi(SignInfo signInfo) {
        SignItem signItem = null;
        if (signInfo != null) {
            signItem = signInfo.getTodaySignItem();
        }
        changeTipsStatus(signItem, 0);
        this.checkInBtn.setClickable(true);
        changeCheckInBgState(0);
        this.checkInBtn.setText(getApplicationContext().getString(R.string.sign_btn));
    }

    private void changeCheckInBtnStates(SignInfo signInfo) {
        int i = 1;
        boolean z = false;
        if (signInfo != null && c.b()) {
            boolean isCheckInToday = signInfo.isCheckInToday();
            CharSequence string = getApplicationContext().getString(R.string.sign_btn);
            String c = c.c().c();
            if (isCheckInToday) {
                List missDaysTillNow = signInfo.getMissDaysTillNow(signInfo.getCurrentSignDay());
                isCheckInToday = missDaysTillNow == null ? false : missDaysTillNow.size() > 0;
                int size = missDaysTillNow == null ? 0 : missDaysTillNow.size();
                if (signInfo.getCurrentSignDay() == 7) {
                    if (isCheckInToday) {
                        string = String.format(getApplicationContext().getString(R.string.sign_miss_btn), new Object[]{Integer.valueOf(size)});
                        com.qq.reader.common.monitor.i.a("event_A134", null, ReaderApplication.getApplicationImp());
                        i = 0;
                        z = true;
                    } else if (!d.v(getApplicationContext(), c.c().c())) {
                        string = getApplicationContext().getString(R.string.sign_get_reward);
                        z = true;
                    } else if (TextUtils.isEmpty(d.w(ReaderApplication.getApplicationImp(), c)) || d.x(getApplicationContext(), c.c().c())) {
                        string = "";
                        i = 2;
                    } else {
                        string = getApplicationContext().getString(R.string.sign_write_contact);
                        i = 0;
                        z = true;
                    }
                } else if (isCheckInToday) {
                    com.qq.reader.common.monitor.i.a("event_A134", null, ReaderApplication.getApplicationImp());
                    string = String.format(getApplicationContext().getString(R.string.sign_miss_btn), new Object[]{Integer.valueOf(size)});
                    i = 0;
                    z = true;
                } else {
                    string = getApplicationContext().getString(R.string.sign_a_week_to_reward);
                    i = 2;
                }
            } else {
                i = 0;
                z = true;
            }
            this.checkInBtn.setText(string);
            this.checkInBtn.setClickable(z);
            changeCheckInBgState(i);
        }
    }

    private void refreshCheckInStates(SignInfo signInfo) {
        int i = 0;
        if (signInfo != null && signInfo.mItems != null && signInfo.mItems.size() > 0) {
            changeCheckInBtnStates(signInfo);
            if (c.b()) {
                boolean isCheckInToday = signInfo.isCheckInToday();
                List missDaysTillNow = signInfo.getMissDaysTillNow(signInfo.getCurrentSignDay());
                SignItem nextDaySignItem = signInfo.getNextDaySignItem();
                boolean v = d.v(getApplicationContext(), c.c().c());
                String c = c.c().c();
                if (!isCheckInToday) {
                    changeTipsStatus(signInfo.getTodaySignItem(), 0);
                    return;
                } else if (signInfo.getCurrentSignDay() == 7) {
                    if (missDaysTillNow != null && missDaysTillNow.size() > 0) {
                        i = 1;
                    }
                    if (i != 0 || !v) {
                        changeTipsStatus(null, 2);
                        return;
                    } else if (TextUtils.isEmpty(d.w(ReaderApplication.getApplicationImp(), c)) || d.x(ReaderApplication.getApplicationImp(), c)) {
                        changeTipsStatus(null, 4);
                        return;
                    } else {
                        changeTipsStatus(null, 3);
                        return;
                    }
                } else {
                    changeTipsStatus(nextDaySignItem, 1);
                    return;
                }
            }
            initNoUserCheckUi(signInfo);
        }
    }

    private boolean shouldPlayBtnAnimation() {
        if (this.checkInBtn.getTag(R.string.obj_tag) instanceof Boolean) {
            return ((Boolean) this.checkInBtn.getTag(R.string.obj_tag)).booleanValue();
        }
        return false;
    }

    private void playSignBtnAnimation() {
        if (shouldPlayBtnAnimation()) {
            com.facebook.rebound.e b = com.facebook.rebound.j.c().b();
            b.a(new com.facebook.rebound.f(72.0d, 4.0d));
            b.a(new com.facebook.rebound.d(this) {
                final /* synthetic */ BookShelfFragment a;

                {
                    this.a = r1;
                }

                public void a(com.facebook.rebound.e eVar) {
                    float b = (((float) eVar.b()) * 0.1f) + 0.9f;
                    this.a.checkInBtn.setScaleX(b);
                    this.a.checkInBtn.setScaleY(b);
                }
            });
            b.b(1.0d);
        }
    }

    private void makeAddCountAnimation(TextView textView) {
        if (this.increaseNumAni == null) {
            com.nineoldandroids.animation.ObjectAnimator ofFloat = com.nineoldandroids.animation.ObjectAnimator.ofFloat((Object) textView, "alpha", 0.0f, 1.0f);
            com.nineoldandroids.animation.ObjectAnimator ofFloat2 = com.nineoldandroids.animation.ObjectAnimator.ofFloat((Object) textView, "scaleX", 0.1f, 1.1f);
            com.nineoldandroids.animation.ObjectAnimator ofFloat3 = com.nineoldandroids.animation.ObjectAnimator.ofFloat((Object) textView, "scaleY", 0.1f, 1.1f);
            com.nineoldandroids.animation.ObjectAnimator ofFloat4 = com.nineoldandroids.animation.ObjectAnimator.ofFloat((Object) textView, "translationY", 0.0f, -5.0f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(ofFloat, ofFloat2, ofFloat3, ofFloat4);
            animatorSet.setDuration(300);
            this.increaseNumAni = new AnimatorSet();
            com.nineoldandroids.animation.ObjectAnimator.ofFloat((Object) textView, "alpha", 1.0f, 0.0f).setDuration(1000);
            this.increaseNumAni.playSequentially(animatorSet, ofFloat);
        }
        this.increaseNumAni.start();
    }

    private void computeGridViewWidth(GridView gridView, List<com.qq.reader.module.bookshelf.signup.b.a> list) {
        if (list != null) {
            com.qq.reader.module.bookshelf.signup.a.a aVar = (com.qq.reader.module.bookshelf.signup.a.a) gridView.getAdapter();
            if (aVar != null) {
                int a = aVar.a();
                int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
                int i = 0;
                for (int i2 = 0; i2 < list.size(); i2++) {
                    View view = aVar.getView(i2, null, gridView);
                    if (view != null) {
                        int measuredWidth;
                        if (view instanceof LinearLayout) {
                            view.measure(makeMeasureSpec, makeMeasureSpec);
                            measuredWidth = view.getMeasuredWidth();
                            if (measuredWidth <= i) {
                                measuredWidth = i;
                            }
                            i = measuredWidth;
                        } else {
                            try {
                                view.measure(makeMeasureSpec, makeMeasureSpec);
                                measuredWidth = view.getMeasuredWidth();
                                if (measuredWidth > i) {
                                    i = measuredWidth;
                                }
                            } catch (NullPointerException e) {
                                try {
                                    measuredWidth = (com.qq.reader.common.c.a.bU - 20) / 3;
                                    if (measuredWidth > i) {
                                        i = measuredWidth;
                                    }
                                } catch (Exception e2) {
                                }
                            }
                        }
                    }
                }
                if (i <= 0 || (com.qq.reader.common.c.a.bU > 0 && i >= com.qq.reader.common.c.a.bU)) {
                    i = (com.qq.reader.common.c.a.bU - 20) / 3;
                    com.qq.reader.common.monitor.f.a("itemWidth:", i + "");
                }
                int i3 = i * a;
                if (list.size() == 2) {
                    i3 += ((a - 1) * ao.a(20.0f)) + 10;
                } else if (list.size() >= 3) {
                    i3 += (a - 1) * ao.a(8.0f);
                }
                ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
                layoutParams.width = i3;
                gridView.setGravity(1);
                gridView.setLayoutParams(layoutParams);
                gridView.requestLayout();
            }
        }
    }
}

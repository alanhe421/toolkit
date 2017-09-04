package com.qq.reader.activity;

import android.app.Activity;
import android.app.ActivityManagerNative;
import android.app.Dialog;
import android.app.IActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.k;
import android.support.v4.app.m;
import android.support.v4.app.t;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.download.task.l;
import com.qq.reader.common.login.e;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.g;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.AppUpdateTask;
import com.qq.reader.common.readertask.protocol.LuckyMoneyTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.e.a;
import com.qq.reader.cservice.bookfollow.FollowBroadcastReceiver;
import com.qq.reader.cservice.cloud.big.CloudBookListActivity;
import com.qq.reader.cservice.cloud.c;
import com.qq.reader.cservice.download.app.b;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qweb.fragment.BaseFragment;
import com.qq.reader.plugin.PlugInDefaultActivity;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.AudioFloatingWindowView;
import com.qq.reader.view.af;
import com.qq.reader.view.aj;
import com.qq.reader.view.o;
import com.qq.reader.view.r;
import com.qq.reader.view.web.i;
import com.samsung.android.sdk.multiwindow.SMultiWindow;
import com.samsung.android.sdk.multiwindow.SMultiWindowActivity;
import com.samsung.android.sdk.multiwindow.SMultiWindowActivity.StateChangeListener;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.av.sdk.AVError;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.theme.SkinnableActivityProcesser;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import com.tencent.util.WeakReferenceHandler;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONException;
import org.json.JSONObject;

public class ReaderBaseFragment extends BaseFragment implements Callback, e, a, c, b.a, StateChangeListener {
    public static final int APP_ID = 537032487;
    private static final String BUNDLE_DIALOG_BUNDLE = "BUNDLE_DIALOG_BUNDLE";
    private static final String BUNDLE_DIALOG_TYPE = "BUNDLE_DIALOG_TYPE";
    public static boolean isInShelf = false;
    private static boolean isRookieWaiting = false;
    protected static i mAdvDialog = null;
    protected final int DIALOG_INSTALL_NOTE = 105;
    protected final int DIALOG_UPDATE_NOTE = 104;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver(this) {
        final /* synthetic */ ReaderBaseFragment a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (com.qq.reader.common.c.a.cI.equals(intent.getAction())) {
                this.a.excuteOnSwitchAccount(context);
            }
        }
    };
    private BroadcastReceiver categoryGotoAllReceiver = new BroadcastReceiver(this) {
        final /* synthetic */ ReaderBaseFragment a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            d.m(ReaderApplication.getApplicationImp(), BookShelfFragment.CATEGORY_ALL);
        }
    };
    private TextView debugTextView;
    protected boolean hasShowImmersive = false;
    protected boolean isAllowNet = true;
    private boolean isAutoUpdate = true;
    private boolean isCheckShowDialog = true;
    public boolean isReady2Show = false;
    BroadcastReceiver loginReciver = new BroadcastReceiver(this) {
        final /* synthetic */ ReaderBaseFragment a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getBooleanExtra("loginSuccess", false)) {
                if (this.a.mLoginNextTask != null) {
                    this.a.mLoginNextTask.a(1);
                }
                this.a.mLoginNextTask = null;
            }
        }
    };
    private long mActivityResumeTime = -1;
    private com.qq.reader.module.bookshelf.c mBookCouponHandler = null;
    t.d mBuilder;
    protected com.qq.reader.common.charge.a mChargeNextTask;
    private af mCloudDelToast;
    private Context mContext;
    private com.qq.reader.module.rookie.presenter.a.a mGiftUpdateListener;
    protected WeakReferenceHandler mHandler;
    private boolean mHidden = false;
    public com.qq.reader.common.login.a mLoginNextTask;
    private com.qq.reader.common.utils.t mNMC = null;
    private boolean mOnPause = false;
    String mProfileFile = (com.qq.reader.common.c.a.l + "user/activityinfo");
    public ProgressDialog mProgressDialog;
    private SMultiWindow mSMultiWindow;
    private SMultiWindowActivity mSMultiWindowActivity;
    private aj mShareDialog;
    protected String mStatPageName;
    b mUpdateHandler = null;
    protected boolean mUseAnimation = true;
    NotificationManager nm = null;
    Notification notification;
    private SkinnableActivityProcesser processer;

    public class MyAlertDialogFragment extends DialogFragment {
        public MyAlertDialogFragment(int i, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putInt(ReaderBaseFragment.BUNDLE_DIALOG_TYPE, i);
            if (bundle != null) {
                bundle2.putBundle(ReaderBaseFragment.BUNDLE_DIALOG_BUNDLE, bundle);
            }
            setArguments(bundle2);
        }

        public Dialog onCreateDialog(Bundle bundle) {
            if (getActivity() == null) {
                return null;
            }
            return ReaderBaseFragment.this.createDialog(getArguments().getInt(ReaderBaseFragment.BUNDLE_DIALOG_TYPE), getArguments().getBundle(ReaderBaseFragment.BUNDLE_DIALOG_BUNDLE));
        }

        public void onCancel(DialogInterface dialogInterface) {
            ReaderBaseFragment.this.onFragmentDialgoCancel(dialogInterface);
        }

        public void show(k kVar, String str) {
            setDialogFramentField();
            m a = kVar.a();
            a.a((Fragment) this, str);
            a.b();
        }

        private void setDialogFramentField() {
            try {
                Class cls = DialogFragment.class;
                Field declaredField = cls.getDeclaredField("mDismissed");
                declaredField.setAccessible(true);
                declaredField.setBoolean(this, false);
                Field declaredField2 = cls.getDeclaredField("mShownByMe");
                declaredField2.setAccessible(true);
                declaredField2.setBoolean(this, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onModeChanged(boolean z) {
        switchImmerseMode();
    }

    public void onZoneChanged(int i) {
    }

    public void onSizeChanged(Rect rect) {
    }

    public void onLoadFinished() {
    }

    public void onLoading() {
    }

    public void onPreLoad() {
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        com.qq.reader.common.utils.e.a().a(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Activity activity = getActivity();
        if (activity != null) {
            this.processer = new SkinnableActivityProcesser(activity, null);
            this.mContext = activity;
        }
        if (this.isAllowNet) {
            ReaderApplication.getInstance().appNetworkStart();
        }
        if (activity != null) {
            try {
                this.mSMultiWindow = new SMultiWindow();
                this.mSMultiWindow.initialize(activity);
                this.mSMultiWindowActivity = new SMultiWindowActivity(activity);
                if (this.mSMultiWindowActivity != null) {
                    this.mSMultiWindowActivity.setStateChangeListener(this);
                }
            } catch (Throwable th) {
                com.qq.reader.common.monitor.debug.c.e("SUNSUMG", "error is report form system");
            }
        }
        getDeviceWidth();
        initDefaultCover();
        this.mHandler = new WeakReferenceHandler(this);
        this.isReady2Show = false;
        if (activity != null) {
            this.mNMC = new com.qq.reader.common.utils.t(activity, true);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(com.qq.reader.common.c.a.cI);
            activity.registerReceiver(this.broadcastReceiver, intentFilter);
        }
        if (com.qq.reader.module.rookie.presenter.a.a().c && this.mGiftUpdateListener == null) {
            this.mGiftUpdateListener = new com.qq.reader.module.rookie.presenter.a.a(this) {
                final /* synthetic */ ReaderBaseFragment a;

                {
                    this.a = r1;
                }

                public void a(boolean z) {
                    try {
                        com.qq.reader.common.monitor.debug.c.d("adv", " rookie dialog onrefresh");
                        if (com.qq.reader.module.rookie.presenter.a.a().a("p1", 0, false) != null) {
                            ReaderBaseFragment.isRookieWaiting = true;
                            this.a.showChannelAdv();
                        }
                        this.a.notifyRookieGiftRefresh();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            com.qq.reader.module.rookie.presenter.a.a().a(this.mGiftUpdateListener);
        }
    }

    protected void notifyRookieGiftRefresh() {
    }

    public void excuteOnSwitchAccount(Context context) {
    }

    public boolean needSetImmerseMode() {
        if (this.mSMultiWindowActivity == null || !this.mSMultiWindowActivity.isMultiWindow()) {
            return true;
        }
        return false;
    }

    public void switchImmerseMode() {
        if (needSetImmerseMode() && !this.hasShowImmersive) {
            com.qq.reader.common.utils.aj.a((Activity) this.mContext);
            com.qq.reader.common.utils.aj.a((Fragment) this);
            this.hasShowImmersive = true;
        } else if (!needSetImmerseMode() && this.hasShowImmersive) {
            com.qq.reader.common.utils.aj.c((Activity) this.mContext);
            this.hasShowImmersive = false;
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void onDestroy() {
        super.onDestroy();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.unregisterReceiver(this.broadcastReceiver);
        }
        if (this.mSMultiWindowActivity != null) {
            this.mSMultiWindowActivity.setStateChangeListener(null);
        }
        if (this.processer != null) {
            this.processer.destory();
        }
        if (this.mGiftUpdateListener != null) {
            com.qq.reader.module.rookie.presenter.a.a().b(this.mGiftUpdateListener);
        }
        com.qq.reader.common.utils.e.a().b(this);
    }

    public final void onResume() {
        this.mOnPause = false;
        super.onResume();
        if (!this.mHidden) {
            selfOnResume();
            IOnResume();
        }
        onAudioFloatingStateChange(2, com.qq.reader.common.utils.e.a().c(), com.qq.reader.common.utils.e.a().b(), com.qq.reader.common.utils.e.a().d());
    }

    private void selfOnResume() {
        com.qq.reader.cservice.cloud.e.a().a((c) this);
        switchImmerseMode();
        quit();
        Context activity = getActivity();
        if (activity != null) {
            FollowBroadcastReceiver.a(activity);
        }
        if (ReaderApplication.isAllowNet) {
            statPageResume();
            g.b(getApplicationContext(), getMTAStatPageName());
        }
        this.mActivityResumeTime = System.currentTimeMillis();
        com.qq.reader.common.monitor.debug.c.a("alive", "onResume" + this.mActivityResumeTime);
    }

    public void IOnResume() {
    }

    public void setStatPageName(String str) {
        this.mStatPageName = str;
    }

    protected void statPageResume() {
        g.a(getApplicationContext(), getMTAStatPageName());
    }

    protected void statPagePause() {
        g.c(getApplicationContext(), getMTAStatPageName());
    }

    protected String getMTAStatPageName() {
        if (TextUtils.isEmpty(this.mStatPageName)) {
            return getClass().getName();
        }
        return this.mStatPageName;
    }

    public void setIsShowNightMask(boolean z) {
        if (this.mNMC != null) {
            this.mNMC.a(z);
        }
    }

    protected void backRootActivity() {
        if (getActivity() != null) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), MainActivity.class);
            intent.setFlags(SigType.WLOGIN_QRPUSH);
            startActivity(intent);
        }
    }

    public final void onPause() {
        super.onPause();
        if (!this.mOnPause) {
            selfOnPause();
            IOnPause();
            this.mOnPause = true;
        }
    }

    private void selfOnPause() {
        com.qq.reader.cservice.cloud.e.a().a(null);
        if (ReaderApplication.isAllowNet) {
            statPagePause();
            g.a(getApplicationContext());
        }
        if (this.mActivityResumeTime > 0) {
            long currentTimeMillis = (System.currentTimeMillis() - this.mActivityResumeTime) + d.bA(getApplicationContext());
            d.k(getApplicationContext(), currentTimeMillis);
            this.mActivityResumeTime = 0;
            com.qq.reader.common.monitor.debug.c.a("alive", "onPause    alive :" + currentTimeMillis);
        }
    }

    public void IOnPause() {
    }

    public void setHidden(boolean z) {
        this.mHidden = z;
    }

    public void onStart() {
        super.onStart();
        if (this.mNMC != null) {
            this.mNMC.b();
        }
        if (!d.e) {
            com.qq.reader.common.monitor.i.a("event_startup2", null, getApplicationContext());
            d.e = true;
        }
    }

    public void onStop() {
        super.onStop();
        if (this.mNMC != null) {
            this.mNMC.a();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 4098) {
            switch (i2) {
                case -1:
                    if (this.mLoginNextTask != null) {
                        this.mLoginNextTask.a(1);
                    }
                    this.mLoginNextTask = null;
                    break;
                case 0:
                    break;
                default:
                    return;
            }
            if (this.mLoginNextTask != null) {
                this.mLoginNextTask.a(3);
            }
            this.mLoginNextTask = null;
        } else if (i == 20001 || i == 20002) {
            if (i2 == 0) {
                Message obtainMessage = this.mHandler.obtainMessage();
                obtainMessage.what = 10000301;
                obtainMessage.obj = intent;
                this.mHandler.sendMessage(obtainMessage);
            }
            if (this.mChargeNextTask != null) {
                if (i2 == 0) {
                    this.mChargeNextTask.a();
                } else if (i2 == 2) {
                    this.mChargeNextTask.c();
                } else if (i2 == -1 || i2 == 100) {
                    this.mChargeNextTask.b();
                }
                this.mChargeNextTask = null;
            }
        }
    }

    private synchronized af getCloudDelToast() {
        if (this.mCloudDelToast == null) {
            this.mCloudDelToast = af.a(getApplicationContext(), (CharSequence) "", 0);
        }
        return this.mCloudDelToast;
    }

    protected void showChannelAdv() {
        Object obj = (checkRookieDialog() || isNeedShowBrandExpansion()) ? null : 1;
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, obj != null ? "1" : "0");
        com.qq.reader.common.monitor.i.a("event_A274", hashMap, ReaderApplication.getApplicationImp());
        if (obj != null) {
            loadAdvDialog();
        }
    }

    private void loadAdvDialog() {
        try {
            long currentTimeMillis = System.currentTimeMillis() - com.qq.reader.cservice.adv.a.d(this.mContext, "CHANNEL_ADV_CLOSE_TIME");
            com.qq.reader.common.monitor.debug.c.a("adv", "duration = " + currentTimeMillis);
            if (currentTimeMillis > 180000) {
                com.qq.reader.common.monitor.i.a("event_A275", null, ReaderApplication.getApplicationImp());
                List b = com.qq.reader.cservice.adv.b.a(getApplicationContext()).b("100126");
                com.qq.reader.common.monitor.debug.c.a("adv", "showChannelAdv " + b.size());
                if (b.size() > 0) {
                    final com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) b.get(0);
                    Map hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, String.valueOf(aVar.d()));
                    com.qq.reader.common.monitor.i.a("event_A182", hashMap, ReaderApplication.getApplicationImp());
                    switch (aVar.t()) {
                        case 0:
                            if (com.qq.reader.cservice.adv.a.b(ReaderApplication.getApplicationImp(), "ADV_SHOW_DATE")) {
                                return;
                            }
                            break;
                        case 2:
                            if (d.d) {
                                return;
                            }
                            break;
                    }
                    if (aVar.q() == 2 && !com.qq.reader.cservice.adv.b.b(aVar)) {
                        com.qq.reader.cservice.adv.b.c(aVar);
                    } else if (getActivity() != null) {
                        mAdvDialog = new i(getActivity(), aVar.q(), aVar.r());
                        mAdvDialog.b(aVar, this.mHandler);
                        mAdvDialog.a(new OnCancelListener(this) {
                            final /* synthetic */ ReaderBaseFragment b;

                            public void onCancel(DialogInterface dialogInterface) {
                                Map hashMap = new HashMap();
                                hashMap.put(s.ORIGIN, String.valueOf(aVar.d()));
                                com.qq.reader.common.monitor.i.a("event_A190", hashMap, this.b.getApplicationContext());
                            }
                        });
                        mAdvDialog.a(new r(this) {
                            final /* synthetic */ ReaderBaseFragment a;

                            {
                                this.a = r1;
                            }

                            public com.qq.reader.common.utils.t a() {
                                return null;
                            }

                            public void onDismiss(DialogInterface dialogInterface) {
                                super.onDismiss(dialogInterface);
                                com.qq.reader.cservice.adv.b.b = false;
                                com.qq.reader.cservice.adv.a.c(ReaderApplication.getApplicationImp(), "CHANNEL_ADV_CLOSE_TIME");
                                if (ReaderBaseFragment.isRookieWaiting) {
                                    this.a.checkRookieDialog();
                                    ReaderBaseFragment.isRookieWaiting = false;
                                }
                                ReaderBaseFragment.mAdvDialog = null;
                            }
                        });
                    }
                }
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.a("Exception", e.toString());
        }
    }

    protected boolean handleMessageImp(Message message) {
        com.qq.reader.cservice.cloud.d dVar;
        com.qq.reader.framework.a.b b;
        Map hashMap;
        switch (message.what) {
            case 10001:
                com.qq.reader.appconfig.a.c.i(getApplicationContext());
                if (this.isAutoUpdate) {
                    getReaderUpdateHandler().a(this.mContext, this.isAutoUpdate);
                    return true;
                }
                showFragmentDialog(104);
                return true;
            case Constants.CODE_LOGIC_REGISTER_IN_PROCESS /*10002*/:
                com.qq.reader.appconfig.a.c.i(getApplicationContext());
                if (this.isAutoUpdate || this.isCheckShowDialog) {
                    return true;
                }
                af.a(ReaderApplication.getApplicationImp(), (int) R.string.dialog_update_failed, 0).a();
                return true;
            case 10004:
                if (this.isAutoUpdate) {
                    return true;
                }
                af.a(ReaderApplication.getApplicationImp(), (int) R.string.dialog_net_error, 0).a();
                return true;
            case AVError.AV_ERR_SERVER_CONNECT_ROOM_FAIL /*10005*/:
                StringBuffer stringBuffer = new StringBuffer();
                if (!(message.obj == null || ((String) message.obj) == null)) {
                    stringBuffer.append((String) message.obj);
                    stringBuffer.append("，");
                }
                stringBuffer.append("删除失败");
                getCloudDelToast().a(stringBuffer.toString());
                getCloudDelToast().a();
                return true;
            case 10008:
                if (this instanceof BookShelfFragment) {
                    return true;
                }
                af.a(ReaderApplication.getApplicationImp(), "《" + message.obj + "》" + "下载完成", 0).a();
                return true;
            case 10010:
                dVar = (com.qq.reader.cservice.cloud.d) message.obj;
                if (dVar == null || dVar.b < 0) {
                    return true;
                }
                List<com.qq.reader.framework.a.b> list = dVar.c;
                if (list == null) {
                    return true;
                }
                for (com.qq.reader.framework.a.b bVar : list) {
                    b = com.qq.reader.common.db.handle.s.a().b(bVar.h(), bVar.a());
                    if (b == null) {
                        com.qq.reader.common.db.handle.s.a().e(bVar.h(), bVar.a());
                        if (bVar.a(com.qq.reader.common.db.handle.s.a())) {
                            com.qq.reader.common.db.handle.s.a().a(bVar.h(), bVar.a(), (long) bVar.d(), bVar.g(), true);
                            synCloudNoteDone(bVar);
                        }
                    } else if (b.d() != bVar.d() || b.g() == 0) {
                        if ((b.d() >= bVar.d() || b.b() <= bVar.b()) && (b.d() <= bVar.d() || b.b() >= bVar.b())) {
                            synCloudNoteDone(bVar);
                        } else {
                            showCloudNoteSynWarning(b, bVar);
                        }
                    }
                }
                return true;
            case 10011:
                dVar = (com.qq.reader.cservice.cloud.d) message.obj;
                if (dVar == null) {
                    return true;
                }
                if (dVar.b >= 0) {
                    for (com.qq.reader.framework.a.b b2 : dVar.c) {
                        if (b2.i() != 0 && b2.i() == 1000) {
                            com.qq.reader.framework.a.b b3 = com.qq.reader.common.db.handle.s.a().b(b2.h(), b2.a());
                            if (b3.b() > b2.b()) {
                                showCloudNoteSynWarning(b3, b2);
                            }
                        }
                    }
                    return true;
                } else if (dVar.b != -1000 || getActivity() == null) {
                    return true;
                } else {
                    com.qq.reader.common.login.c.a(getActivity(), Boolean.valueOf(false));
                    return true;
                }
            case 10012:
                if (getActivity() == null) {
                    return true;
                }
                com.qq.reader.common.login.c.a(getActivity(), Boolean.valueOf(false));
                return true;
            case 11003:
                gotoCloudActivity(-1);
                return true;
            case 65538:
                if (!(mAdvDialog == null || getActivity() == null || getActivity().isFinishing() || !com.qq.reader.cservice.adv.b.f())) {
                    com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) message.obj;
                    switch (aVar.t()) {
                        case 0:
                            com.qq.reader.cservice.adv.a.a(ReaderApplication.getApplicationImp(), "ADV_SHOW_DATE");
                            break;
                        case 1:
                            aVar.a(0);
                            com.qq.reader.cservice.adv.b.a(ReaderApplication.getApplicationImp()).d(aVar);
                            break;
                        case 2:
                            d.d = true;
                            break;
                    }
                    j.a((int) Opcodes.NOT_INT, 0);
                    hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, String.valueOf(aVar.d()));
                    com.qq.reader.common.monitor.i.a("event_A125", hashMap, ReaderApplication.getApplicationImp());
                    StatisticsManager.a().a("event_A125", hashMap);
                    mAdvDialog.f_();
                    com.qq.reader.cservice.adv.b.b = true;
                }
                com.qq.reader.cservice.adv.b.a = false;
                return true;
            case 65539:
                if (getActivity() == null || getActivity().isFinishing() || mAdvDialog == null || mAdvDialog.f()) {
                    return true;
                }
                mAdvDialog.dismiss();
                return true;
            case 65542:
                setLoginNextTask((com.qq.reader.common.login.a) message.obj);
                startLogin();
                return true;
            case 65544:
                loadAdvDialog();
                return true;
            case 65552:
                if (!(mAdvDialog == null || getActivity() == null || getActivity().isFinishing() || !com.qq.reader.cservice.adv.b.f())) {
                    com.qq.reader.module.rookie.a.b bVar2 = (com.qq.reader.module.rookie.a.b) message.obj;
                    com.qq.reader.module.rookie.presenter.a.a().a(bVar2.a, "p1", 0);
                    mAdvDialog.f_();
                    com.qq.reader.common.monitor.debug.c.d("ADV", " rookie dialog show");
                    hashMap = new HashMap();
                    hashMap.put("id", bVar2.a + "");
                    com.qq.reader.common.monitor.i.a("event_A272", hashMap, ReaderApplication.getApplicationImp());
                    break;
                }
            case 10000301:
                if (message.obj == null) {
                    return true;
                }
                Intent intent = (Intent) message.obj;
                int intExtra = intent.getIntExtra("result", -1);
                int intExtra2 = intent.getIntExtra("realSaveNum", 0);
                if (intExtra != 0) {
                    return true;
                }
                com.qq.reader.common.readertask.g.a().a(new LuckyMoneyTask(new com.qq.reader.common.readertask.ordinal.c(this) {
                    final /* synthetic */ ReaderBaseFragment a;

                    {
                        this.a = r1;
                    }

                    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            CharSequence optString = jSONObject.optString("rid");
                            if (jSONObject.optInt("code", -1) == 0 && !TextUtils.isEmpty(optString)) {
                                Message obtainMessage = this.a.mHandler.obtainMessage();
                                obtainMessage.what = 10000302;
                                obtainMessage.obj = jSONObject;
                                long currentTimeMillis = System.currentTimeMillis() - readerProtocolTask.getRunTime();
                                if (currentTimeMillis >= 3500) {
                                    this.a.mHandler.sendMessage(obtainMessage);
                                } else {
                                    this.a.mHandler.sendMessageDelayed(obtainMessage, 3500 - currentTimeMillis);
                                }
                                com.qq.reader.common.monitor.i.a("event_D107", null, this.a.mContext);
                            }
                        } catch (JSONException e) {
                            com.qq.reader.common.monitor.debug.c.e("luckymoney", e.getMessage());
                        }
                    }

                    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                        com.qq.reader.common.monitor.debug.c.e("luckymoney", exception.getMessage());
                    }
                }, intExtra2));
                return true;
            case 10000302:
                try {
                    if (getActivity() == null) {
                        return true;
                    }
                    new o(getActivity(), (JSONObject) message.obj).f_();
                    com.qq.reader.common.monitor.i.a("event_D108", null, this.mContext);
                    return true;
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e("ReaderBaseActivity_luckymoney", e.getMessage());
                    return true;
                }
        }
        return false;
    }

    public void synCloudNoteDone(com.qq.reader.framework.a.b bVar) {
    }

    public void showCloudNoteSynWarning(com.qq.reader.framework.a.b bVar, final com.qq.reader.framework.a.b bVar2) {
        if (getActivity() != null) {
            CharSequence f;
            String string = getString(R.string.alert_dialog_cloud_note_content);
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(bVar == null ? 0 : bVar.b());
            objArr[1] = Integer.valueOf(bVar2.b());
            CharSequence format = String.format(string, objArr);
            AlertDialog.a c = new AlertDialog.a(getActivity()).c(17301543);
            if (bVar2 != null) {
                f = bVar2.f();
            } else {
                f = bVar.f();
            }
            Dialog a = c.a(f).b(format).b((int) R.string.alert_dialog_cloud_note_local, new OnClickListener(this) {
                final /* synthetic */ ReaderBaseFragment b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    if (com.qq.reader.common.db.handle.s.a().a(bVar2.h(), bVar2.a(), (long) bVar2.d(), System.currentTimeMillis(), true)) {
                        com.qq.reader.framework.a.b b = com.qq.reader.common.db.handle.s.a().b(bVar2.h(), bVar2.a());
                        List arrayList = new ArrayList();
                        arrayList.add(b);
                        com.qq.reader.cservice.cloud.e.a().a(arrayList);
                    }
                }
            }).a((int) R.string.alert_dialog_cloud_note_cloud, new OnClickListener(this) {
                final /* synthetic */ ReaderBaseFragment b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    com.qq.reader.common.db.handle.s.a().e(bVar2.h(), bVar2.a());
                    if (bVar2.a(com.qq.reader.common.db.handle.s.a())) {
                        com.qq.reader.common.db.handle.s.a().a(bVar2.h(), bVar2.a(), (long) bVar2.d(), 0, true);
                    }
                    this.b.synCloudNoteDone(bVar2);
                }
            }).a();
            if (a != null) {
                a.setCanceledOnTouchOutside(false);
                a.show();
            }
        }
    }

    public void checkUpdate(boolean z, boolean z2) {
        if (!z || getReaderUpdateHandler().b()) {
            this.isAutoUpdate = z;
            this.isCheckShowDialog = z2;
            com.qq.reader.common.readertask.g.a().a(new AppUpdateTask(new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ ReaderBaseFragment a;

                {
                    this.a = r1;
                }

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    try {
                        com.qq.reader.common.protocol.c a = com.qq.reader.common.protocol.c.a(this.a.getContext(), str);
                        if (a != null && a.a() != null) {
                            String a2 = a.a();
                            com.qq.reader.appconfig.a.c.a = a.d();
                            com.qq.reader.appconfig.a.c.b = a2;
                            com.qq.reader.appconfig.a.c.c = a.c();
                            com.qq.reader.appconfig.a.c.d = a.b();
                            if (a2 != null && this.a.mHandler != null) {
                                if (!this.a.isCheckShowDialog || com.qq.reader.appconfig.a.c.a != 2 || !this.a.getReaderUpdateHandler().b(this.a.getContext())) {
                                    if (this.a.isAutoUpdate || !this.a.getReaderUpdateHandler().a()) {
                                        this.a.mHandler.sendMessage(this.a.mHandler.obtainMessage(10001, a2));
                                    } else {
                                        this.a.showFragmentDialog(105);
                                    }
                                }
                            }
                        } else if (this.a.mHandler != null) {
                            this.a.mHandler.obtainMessage(Constants.CODE_LOGIC_REGISTER_IN_PROCESS).sendToTarget();
                        }
                    } catch (Exception e) {
                        f.a("onConnectionRecieveData", "read xml error", e);
                        if (this.a.mHandler != null) {
                            this.a.mHandler.obtainMessage(Constants.CODE_LOGIC_REGISTER_IN_PROCESS, null).sendToTarget();
                        }
                    }
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    if (this.a.mHandler != null) {
                        this.a.mHandler.sendMessage(this.a.mHandler.obtainMessage(10004));
                    }
                }
            }));
        }
    }

    public Context getContext() {
        return ReaderApplication.getApplicationImp();
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    protected void quitAll() {
        ao.m(getApplicationContext());
        com.qq.reader.common.monitor.i.a("event_A21", null, ReaderApplication.getApplicationImp());
        com.qq.reader.common.c.a.a(true);
        l.a();
        com.qq.reader.plugin.m.c().d();
        format.epub.a.a().b();
        com.qq.reader.common.readertask.f.b().d();
        com.qq.reader.cservice.download.audio.a.a().d();
        com.qq.reader.plugin.audiobook.core.l.a(ReaderApplication.getApplicationImp());
        com.qq.reader.module.feed.loader.d.b().d();
        com.qq.reader.module.game.a.b().c();
    }

    protected int getDeviceWidth() {
        DisplayMetrics displayMetrics = ReaderApplication.getApplicationImp().getResources().getDisplayMetrics();
        com.qq.reader.common.c.a.bU = displayMetrics.widthPixels;
        com.qq.reader.common.c.a.bT = displayMetrics.heightPixels;
        com.qq.reader.common.c.a.bZ = displayMetrics.density;
        com.qq.reader.common.c.a.bY = (int) (160.0f * displayMetrics.density);
        if (getActivity() != null) {
            com.qq.reader.common.c.a.ca = ao.x(getActivity());
            com.qq.reader.common.c.a.cb = ao.f(getActivity());
        }
        com.qq.reader.common.c.a.bX = ao.a(18.0f);
        com.qq.reader.common.c.a.ce = com.qq.reader.common.c.a.bU / ao.a(14.0f);
        int max = Math.max(com.qq.reader.common.c.a.bU, com.qq.reader.common.c.a.bT);
        if (max >= 1180) {
            com.qq.reader.common.c.a.bW = 15;
        } else if (max >= 960) {
            com.qq.reader.common.c.a.bW = 15;
        } else if (max >= 800) {
            com.qq.reader.common.c.a.bW = 15;
        } else if (max <= ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE) {
        }
        return displayMetrics.widthPixels;
    }

    private void initDefaultCover() {
    }

    protected void quit() {
        if (com.qq.reader.common.c.a.a() && getActivity() != null) {
            getActivity().finish();
        }
    }

    public void gotoLocalImportActivity(int i) {
        Intent intent = new Intent();
        intent.setClass(ReaderApplication.getApplicationImp(), LocalBookActivity.class);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        if (i < 0) {
            startActivity(intent);
        } else {
            startActivityForResult(intent, i);
        }
    }

    public void gotoNetImportActivity() {
        com.qq.reader.plugin.d dVar;
        com.qq.reader.plugin.l b = com.qq.reader.plugin.k.b().b("30");
        if (b != null) {
            dVar = (com.qq.reader.plugin.d) com.qq.reader.plugin.m.c().b(this.mContext, b);
        } else {
            dVar = null;
        }
        Object obj = 1;
        if (dVar != null && dVar.l()) {
            try {
                Intent launchIntentForPackage = getActivity().getPackageManager().getLaunchIntentForPackage("com.qq.qcloud");
                if (launchIntentForPackage != null) {
                    launchIntentForPackage.addFlags(tencent.tls.platform.SigType.TLS);
                    com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                    startActivity(launchIntentForPackage);
                    obj = null;
                }
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (obj != null) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            intent.setClass(ReaderApplication.getApplicationImp(), PlugInDefaultActivity.class);
            intent.putExtra("PLUGIN_TYPE", "8");
            intent.putExtras(bundle);
            com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
            startActivity(intent);
        }
    }

    public void gotoCloudActivity(int i) {
        if (com.qq.reader.common.login.c.b()) {
            j.a(56, 0);
            Intent intent = new Intent();
            intent.setClass(ReaderApplication.getApplicationImp(), CloudBookListActivity.class);
            com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
            if (i < 0) {
                startActivity(intent);
                return;
            } else {
                startActivityForResult(intent, i);
                return;
            }
        }
        loginWithTask(11003);
    }

    protected void loginWithTask(final int i) {
        this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
            final /* synthetic */ ReaderBaseFragment b;

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.b.mHandler.sendEmptyMessage(i);
                        return;
                    default:
                        return;
                }
            }
        };
        startLogin();
    }

    public void startLoginQQOnly() {
        startLogin(1);
    }

    public void startLogin(int i) {
        if (getActivity() != null) {
            com.qq.reader.common.login.c.a(getActivity(), i);
        }
    }

    public void startLogin() {
        startLogin(7);
    }

    public void setLoginNextTask(com.qq.reader.common.login.a aVar) {
        this.mLoginNextTask = aVar;
    }

    public void setChargeNextTask(com.qq.reader.common.charge.a aVar) {
        this.mChargeNextTask = aVar;
    }

    public void disableUseAnimation() {
        this.mUseAnimation = false;
    }

    public void startActivityForResult(Intent intent, int i) {
        super.startActivityForResult(intent, i);
        if (this.mUseAnimation && getActivity() != null) {
            try {
                if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() >= 5) {
                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            } catch (Exception e) {
            }
        }
    }

    public void startActivity(Intent intent) {
        super.startActivity(intent);
        if (this.mUseAnimation && getActivity() != null) {
            try {
                if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() >= 5) {
                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            } catch (Exception e) {
            }
        }
    }

    public int startActivityWithFieldsForApi19(Intent intent) {
        try {
            IActivityManager iActivityManager = ActivityManagerNative.getDefault();
            String type = intent.getType();
            if (type == null && intent.getData() != null && MessageKey.MSG_CONTENT.equals(intent.getData().getScheme())) {
                type = iActivityManager.getProviderMimeType(intent.getData(), 0);
            }
            iActivityManager.startActivityAndWait(null, null, intent, type, null, null, 0, 1, null, null, null, -2);
        } catch (Exception e) {
            f.d("debug", "error " + e);
        }
        return 0;
    }

    private ParcelFileDescriptor getParcelFileDescriptor() {
        try {
            File file = new File(this.mProfileFile);
            if (!file.exists()) {
                file.createNewFile();
            }
            return ParcelFileDescriptor.open(file, 1006632960);
        } catch (Exception e) {
            f.d("debug", "Exception " + e);
            e.printStackTrace();
            return null;
        }
    }

    public void showPorgress(String str) {
        if (getActivity() != null && !getActivity().isFinishing()) {
            if (this.mProgressDialog == null) {
                if (str == null) {
                    str = "";
                }
                this.mProgressDialog = ProgressDialog.show(getActivity(), null, str, true);
                this.mProgressDialog.setCanceledOnTouchOutside(false);
                this.mProgressDialog.setCancelable(true);
                this.mProgressDialog.setOnKeyListener(new OnKeyListener(this) {
                    final /* synthetic */ ReaderBaseFragment a;

                    {
                        this.a = r1;
                    }

                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        switch (i) {
                            case 4:
                                this.a.progressCancel();
                                break;
                        }
                        return false;
                    }
                });
                return;
            }
            this.mProgressDialog.show();
        }
    }

    public void progressCancel() {
        if (getActivity() != null && !getActivity().isFinishing() && this.mProgressDialog != null && this.mProgressDialog.isShowing()) {
            try {
                this.mProgressDialog.cancel();
                this.mProgressDialog = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void getDone(com.qq.reader.cservice.cloud.d dVar) {
        this.mHandler.obtainMessage(10010, dVar).sendToTarget();
    }

    public void saveDone(com.qq.reader.cservice.cloud.d dVar) {
        if (dVar.b != -1000) {
            this.mHandler.obtainMessage(10011, dVar).sendToTarget();
        }
    }

    public void onAudioFloatingStateChange(int i, long j, boolean z, String str) {
        View view = getView();
        Activity activity = getActivity();
        if (view != null && activity != null) {
            AudioFloatingWindowView audioFloatingWindowView = (AudioFloatingWindowView) view.findViewById(R.id.img_audio_floating);
            if (audioFloatingWindowView != null) {
                ao.a(2, activity, audioFloatingWindowView, j, z, str);
            }
        }
    }

    protected Dialog createDialog(int i, Bundle bundle) {
        if (getActivity() == null) {
            return null;
        }
        AlertDialog.a b;
        Dialog a;
        switch (i) {
            case 104:
                if (!this.isAutoUpdate && this.isCheckShowDialog) {
                    com.qq.reader.common.monitor.i.a("event_D72", null, ReaderApplication.getApplicationImp());
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("\n");
                stringBuffer.append(getText(R.string.dialog_update_note));
                if (com.qq.reader.appconfig.a.c.d != null && com.qq.reader.appconfig.a.c.d.length() > 0) {
                    stringBuffer.append("\n");
                    stringBuffer.append(com.qq.reader.appconfig.a.c.d);
                }
                b = new AlertDialog.a(getActivity()).c(R.drawable.alert_dialog_icon).a((int) R.string.dialog_update_note_title).b(stringBuffer.toString());
                if (com.qq.reader.appconfig.a.c.a != 1) {
                    b.a((int) R.string.dialog_update_note_but1, new OnClickListener(this) {
                        final /* synthetic */ ReaderBaseFragment a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.getReaderUpdateHandler().a(this.a.mContext, this.a.isAutoUpdate);
                            if (!this.a.isAutoUpdate && this.a.isCheckShowDialog) {
                                com.qq.reader.common.monitor.i.a("event_D74", null, ReaderApplication.getApplicationImp());
                            }
                            com.qq.reader.appconfig.a.c.b = null;
                        }
                    }).b((int) R.string.alert_dialog_cancel, new OnClickListener(this) {
                        final /* synthetic */ ReaderBaseFragment a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (!this.a.isAutoUpdate && this.a.isCheckShowDialog) {
                                com.qq.reader.common.monitor.i.a("event_D73", null, ReaderApplication.getApplicationImp());
                            }
                            com.qq.reader.appconfig.a.c.b = null;
                        }
                    });
                } else {
                    b.b((int) R.string.dialog_update_note_but1, new OnClickListener(this) {
                        final /* synthetic */ ReaderBaseFragment a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.getReaderUpdateHandler().a(this.a.mContext, this.a.isAutoUpdate);
                            if (!this.a.isAutoUpdate && this.a.isCheckShowDialog) {
                                com.qq.reader.common.monitor.i.a("event_D74", null, ReaderApplication.getApplicationImp());
                            }
                            com.qq.reader.appconfig.a.c.b = null;
                        }
                    });
                }
                a = b.a();
                a.setCanceledOnTouchOutside(false);
                a.setOnKeyListener(new OnKeyListener(this) {
                    final /* synthetic */ ReaderBaseFragment a;

                    {
                        this.a = r1;
                    }

                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        if (i == 4 || i == 84 || i == 82) {
                            return true;
                        }
                        return false;
                    }
                });
                return a;
            case 105:
                if (!this.isAutoUpdate && this.isCheckShowDialog) {
                    com.qq.reader.common.monitor.i.a("event_D72", null, ReaderApplication.getApplicationImp());
                }
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("\n");
                stringBuffer2.append(getText(R.string.dialog_install_note));
                if (com.qq.reader.appconfig.a.c.d != null && com.qq.reader.appconfig.a.c.d.length() > 0) {
                    stringBuffer2.append("\n");
                    stringBuffer2.append("\n");
                    stringBuffer2.append(com.qq.reader.appconfig.a.c.d);
                }
                if (getActivity() == null) {
                    return null;
                }
                b = new AlertDialog.a(getActivity()).c(R.drawable.alert_dialog_icon).a((int) R.string.dialog_install_note_title).b(stringBuffer2.toString());
                if (com.qq.reader.appconfig.a.c.a != 1) {
                    b.a((int) R.string.dialog_install_note_but1, new OnClickListener(this) {
                        final /* synthetic */ ReaderBaseFragment a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.getReaderUpdateHandler().a(this.a.mContext);
                            if (!this.a.isAutoUpdate && this.a.isCheckShowDialog) {
                                com.qq.reader.common.monitor.i.a("event_D74", null, ReaderApplication.getApplicationImp());
                            }
                            com.qq.reader.appconfig.a.c.b = null;
                        }
                    }).b((int) R.string.dialog_install_note_but2, new OnClickListener(this) {
                        final /* synthetic */ ReaderBaseFragment a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (!this.a.isAutoUpdate && this.a.isCheckShowDialog) {
                                com.qq.reader.common.monitor.i.a("event_D73", null, ReaderApplication.getApplicationImp());
                            }
                            com.qq.reader.appconfig.a.c.b = null;
                        }
                    });
                } else {
                    b.b((int) R.string.dialog_install_note_but1, new OnClickListener(this) {
                        final /* synthetic */ ReaderBaseFragment a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.getReaderUpdateHandler().a(this.a.mContext);
                            if (!this.a.isAutoUpdate && this.a.isCheckShowDialog) {
                                com.qq.reader.common.monitor.i.a("event_D73", null, ReaderApplication.getApplicationImp());
                            }
                            com.qq.reader.appconfig.a.c.b = null;
                            if (com.qq.reader.appconfig.a.c.a == 1 && this.a.getActivity() != null) {
                                this.a.getActivity().finish();
                            }
                        }
                    });
                }
                a = b.a();
                a.setCanceledOnTouchOutside(false);
                a.setOnKeyListener(new OnKeyListener(this) {
                    final /* synthetic */ ReaderBaseFragment a;

                    {
                        this.a = r1;
                    }

                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        if (i == 4 || i == 84 || i == 82) {
                            return true;
                        }
                        return false;
                    }
                });
                return a;
            default:
                return null;
        }
    }

    protected void onFragmentDialgoCancel(DialogInterface dialogInterface) {
    }

    public void showFragmentDialog(int i) {
        showFragmentDialog(i, null);
    }

    public void showFragmentDialog(int i, Bundle bundle) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            try {
                new MyAlertDialogFragment(i, bundle).show(activity.getSupportFragmentManager(), "dialog");
            } catch (Exception e) {
                f.a("ReaderBaseActivity", e.getMessage());
            }
        }
    }

    public boolean isFragmentDialogShowing() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return false;
        }
        try {
            DialogFragment dialogFragment = (DialogFragment) activity.getSupportFragmentManager().a("dialog");
            if (!(dialogFragment == null || dialogFragment.getDialog() == null || !dialogFragment.getDialog().isShowing())) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void onLoginSuccess(int i) {
    }

    public void onLoginError(String str, int i, int i2) {
    }

    public void onNeedVerifyImage(String str, byte[] bArr) {
    }

    public aj getShareDialog() {
        if (this.mShareDialog == null && getActivity() != null) {
            this.mShareDialog = new aj(getActivity());
        }
        return this.mShareDialog;
    }

    public boolean handleMessage(Message message) {
        return handleMessageImp(message);
    }

    public void toWebBookDetail(long j) {
        Context activity = getActivity();
        if (activity != null) {
            ao.a(activity, j);
        }
    }

    public b getReaderUpdateHandler() {
        if (this.mUpdateHandler == null) {
            this.mUpdateHandler = new b();
        }
        return this.mUpdateHandler;
    }

    public void createNotification() {
        if (this.nm == null && getActivity() != null) {
            this.nm = (NotificationManager) this.mContext.getSystemService("notification");
            this.mBuilder = ao.y(getActivity());
            this.notification = this.mBuilder.a(getResources().getString(R.string.app_name) + "最新版").b((CharSequence) "下载中...0%").a(PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0)).a();
            this.nm.notify(1000, this.notification);
        }
    }

    public void updateNotificationProgress(int i) {
        if (this.nm == null) {
            return;
        }
        if (i != 100) {
            this.mBuilder.b("下载中..." + i + "%");
            this.notification = this.mBuilder.a();
            this.nm.notify(1000, this.notification);
            return;
        }
        this.nm.cancel(1000);
    }

    protected boolean checkRookieDialog() {
        if (com.qq.reader.cservice.adv.b.b || com.qq.reader.cservice.adv.b.a || com.qq.reader.module.rookie.presenter.a.a().d()) {
            com.qq.reader.common.monitor.debug.c.d("adv", "show channel adv busy return ");
            return true;
        } else if (!com.qq.reader.module.rookie.presenter.a.a().c) {
            return false;
        } else {
            if (isNeedShowBrandExpansion()) {
                return false;
            }
            if (getActivity() != null) {
                com.qq.reader.module.rookie.a.b a = com.qq.reader.module.rookie.presenter.a.a().a("p1", 0, false);
                if (a != null) {
                    mAdvDialog = com.qq.reader.view.web.l.a(getActivity(), a, new r(this) {
                        final /* synthetic */ ReaderBaseFragment a;

                        {
                            this.a = r1;
                        }

                        public com.qq.reader.common.utils.t a() {
                            if (ReaderBaseFragment.mAdvDialog == null) {
                                return null;
                            }
                            return ReaderBaseFragment.mAdvDialog.c();
                        }

                        public void onDismiss(DialogInterface dialogInterface) {
                            super.onDismiss(dialogInterface);
                            com.qq.reader.cservice.adv.a.c(ReaderApplication.getApplicationImp(), "CHANNEL_ADV_CLOSE_TIME");
                            ReaderBaseFragment.mAdvDialog = null;
                        }
                    }, 1);
                    if (mAdvDialog == null) {
                        return false;
                    }
                    if (this.mHandler != null) {
                        ((com.qq.reader.view.web.l) mAdvDialog).a(a, this.mHandler, false);
                        com.qq.reader.common.monitor.debug.c.d("adv", " rookie dialog loadRookieGift");
                        isRookieWaiting = false;
                        return true;
                    }
                }
            }
            return false;
        }
    }

    protected boolean isNeedShowBrandExpansion() {
        if (getActivity() == null || !(getActivity() instanceof MainActivity)) {
            return false;
        }
        if (((MainActivity) getActivity()).d() != 1) {
            return false;
        }
        List c = com.qq.reader.cservice.adv.b.a(getActivity()).c("103484");
        if (c != null && c.size() > 0) {
            com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) c.get(0);
            if (aVar != null && aVar.l() == 1) {
                return true;
            }
        }
        return false;
    }
}

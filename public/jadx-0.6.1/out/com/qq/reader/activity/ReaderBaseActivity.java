package com.qq.reader.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManagerNative;
import android.app.Dialog;
import android.app.IActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
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
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.k;
import android.support.v4.app.t;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.q.Qt;
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
import com.qq.reader.cservice.bookfollow.FollowBroadcastReceiver;
import com.qq.reader.cservice.cloud.big.CloudBookListActivity;
import com.qq.reader.cservice.cloud.c;
import com.qq.reader.cservice.download.app.b;
import com.qq.reader.cservice.download.app.b.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.plugin.PlugInDefaultActivity;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.af;
import com.qq.reader.view.aj;
import com.qq.reader.view.m;
import com.qq.reader.view.o;
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

public class ReaderBaseActivity extends SwipeBackActivity implements Callback, e, c, a, StateChangeListener {
    public static final int APP_ID = 537032487;
    private static final String BUNDLE_DIALOG_BUNDLE = "BUNDLE_DIALOG_BUNDLE";
    private static final String BUNDLE_DIALOG_TYPE = "BUNDLE_DIALOG_TYPE";
    public static boolean isInShelf = false;
    protected final int DIALOG_INSTALL_NOTE = 105;
    protected final int DIALOG_UPDATE_NOTE = 104;
    private BroadcastReceiver categoryGotoAllReceiver = new BroadcastReceiver(this) {
        final /* synthetic */ ReaderBaseActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            d.m(this.a.getApplicationContext(), BookShelfFragment.CATEGORY_ALL);
        }
    };
    private GestureDetector debugClearDetector;
    private TextView debugTextView;
    private IntentFilter filterRookieDebug;
    private boolean hasShowImmersive = false;
    protected boolean isAllowNet = true;
    private boolean isAutoUpdate = true;
    private boolean isCheckShowDialog = true;
    public boolean isOnResume = false;
    public boolean isReady2Show = false;
    private long mActivityResumeTime = -1;
    private i mAdvDialog = null;
    private com.qq.reader.module.bookshelf.c mBookCouponHandler = null;
    t.d mBuilder;
    protected com.qq.reader.common.charge.a mChargeNextTask;
    private af mCloudDelToast;
    private Context mContext;
    protected WeakReferenceHandler mHandler;
    public com.qq.reader.common.login.a mLoginNextTask;
    private com.qq.reader.common.utils.t mNMC = null;
    String mProfileFile = (com.qq.reader.common.c.a.l + "user/activityinfo");
    public m mProgressDialog;
    private SMultiWindow mSMultiWindow;
    private SMultiWindowActivity mSMultiWindowActivity;
    private aj mShareDialog;
    protected String mStatPageName;
    b mUpdateHandler = null;
    protected boolean mUseAnimation = true;
    NotificationManager nm = null;
    Notification notification;
    private SkinnableActivityProcesser processer;
    private BroadcastReceiver rookieDebugReceiver;
    private BroadcastReceiver switchAccountBroadcastReceiver = new BroadcastReceiver(this) {
        final /* synthetic */ ReaderBaseActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (com.qq.reader.common.c.a.cI.equals(intent.getAction())) {
                this.a.excuteOnSwitchAccount(context);
            }
        }
    };

    public static class MyAlertDialogFragment extends DialogFragment {
        public static MyAlertDialogFragment newInstance(int i, Bundle bundle) {
            MyAlertDialogFragment myAlertDialogFragment = new MyAlertDialogFragment();
            Bundle bundle2 = new Bundle();
            bundle2.putInt(ReaderBaseActivity.BUNDLE_DIALOG_TYPE, i);
            if (bundle != null) {
                bundle2.putBundle(ReaderBaseActivity.BUNDLE_DIALOG_BUNDLE, bundle);
            }
            myAlertDialogFragment.setArguments(bundle2);
            return myAlertDialogFragment;
        }

        public Dialog onCreateDialog(Bundle bundle) {
            return ((ReaderBaseActivity) getActivity()).createDialog(getArguments().getInt(ReaderBaseActivity.BUNDLE_DIALOG_TYPE), getArguments().getBundle(ReaderBaseActivity.BUNDLE_DIALOG_BUNDLE));
        }

        public void onCancel(DialogInterface dialogInterface) {
            if (getActivity() != null) {
                ((ReaderBaseActivity) getActivity()).onFragmentDialgoCancel(dialogInterface);
            }
        }

        public void show(k kVar, String str) {
            setDialogFramentField();
            android.support.v4.app.m a = kVar.a();
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

    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        this.processer = new SkinnableActivityProcesser(this, null);
        if (this.isAllowNet) {
            ReaderApplication.getInstance().appNetworkStart();
        }
        this.mContext = this;
        try {
            this.mSMultiWindow = new SMultiWindow();
            this.mSMultiWindow.initialize(this);
            this.mSMultiWindowActivity = new SMultiWindowActivity(this);
            if (this.mSMultiWindowActivity != null) {
                this.mSMultiWindowActivity.setStateChangeListener(this);
            }
        } catch (Throwable th) {
            com.qq.reader.common.monitor.debug.c.e("SUNSUMG", "error is report form system");
        }
        getDeviceWidth();
        initDefaultCover();
        this.mHandler = new WeakReferenceHandler(this);
        this.isReady2Show = false;
        this.mNMC = new com.qq.reader.common.utils.t((Activity) this, true);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(com.qq.reader.common.c.a.cI);
        registerReceiver(this.switchAccountBroadcastReceiver, intentFilter);
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
            com.qq.reader.common.utils.aj.b((Activity) this.mContext);
            this.hasShowImmersive = true;
        } else if (!needSetImmerseMode() && this.hasShowImmersive) {
            com.qq.reader.common.utils.aj.c((Activity) this.mContext);
            this.hasShowImmersive = false;
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.switchAccountBroadcastReceiver);
        if (this.mSMultiWindow != null) {
            this.mSMultiWindow = null;
        }
        if (this.mSMultiWindowActivity != null) {
            this.mSMultiWindowActivity.setStateChangeListener(null);
            this.mSMultiWindowActivity = null;
        }
        if (this.processer != null) {
            this.processer.destory();
        }
        if (this.rookieDebugReceiver != null) {
            unregisterReceiver(this.rookieDebugReceiver);
        }
    }

    protected void onResume() {
        super.onResume();
        com.qq.reader.cservice.cloud.e.a().a((c) this);
        switchImmerseMode();
        quit();
        FollowBroadcastReceiver.a(this);
        if (ReaderApplication.isAllowNet) {
            statPageResume();
            g.b(getApplicationContext(), getMTAStatPageName());
        }
        this.mActivityResumeTime = System.currentTimeMillis();
        com.qq.reader.common.monitor.debug.c.a("alive", "onResume" + this.mActivityResumeTime);
        if (com.qq.reader.appconfig.b.i) {
            makeRookieDebugFunction();
        }
        showDebugUI();
        this.isOnResume = true;
        Qt.start(this, ao.h(getApplicationContext()));
        Qt.setDeviceUniqID(d.h(getApplicationContext()));
    }

    private void makeRookieDebugFunction() {
        if (this.filterRookieDebug == null) {
            this.filterRookieDebug = new IntentFilter();
            this.filterRookieDebug.addAction("com.qq.reader._rookie_debug_update_info");
        }
        if (this.rookieDebugReceiver == null) {
            this.rookieDebugReceiver = new BroadcastReceiver(this) {
                final /* synthetic */ ReaderBaseActivity a;

                {
                    this.a = r1;
                }

                public void onReceive(Context context, Intent intent) {
                    if ("com.qq.reader._rookie_debug_update_info".equals(intent.getAction()) && this.a.debugTextView != null) {
                        this.a.debugTextView.append(intent.getStringExtra("info"));
                    }
                }
            };
            registerReceiver(this.rookieDebugReceiver, this.filterRookieDebug);
        }
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
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        intent.setFlags(SigType.WLOGIN_QRPUSH);
        startActivity(intent);
    }

    protected void onPause() {
        super.onPause();
        com.qq.reader.cservice.cloud.e.a().a(null);
        if (ReaderApplication.isAllowNet) {
            statPagePause();
            g.a(getApplicationContext());
        }
        if (this.mActivityResumeTime > 0) {
            long currentTimeMillis = (System.currentTimeMillis() - this.mActivityResumeTime) + d.bA(this);
            d.k((Context) this, currentTimeMillis);
            this.mActivityResumeTime = 0;
            com.qq.reader.common.monitor.debug.c.a("alive", "onPause    alive :" + currentTimeMillis);
        }
        this.isOnResume = false;
    }

    public void showNightMode(boolean z) {
        if (this.mNMC == null) {
            return;
        }
        if (z) {
            this.mNMC.b();
        } else {
            this.mNMC.a();
        }
    }

    protected void onStart() {
        super.onStart();
        if (this.mNMC != null) {
            this.mNMC.b();
        }
        if (!d.e) {
            com.qq.reader.common.monitor.i.a("event_startup2", null, getApplicationContext());
            d.e = true;
        }
    }

    protected void onStop() {
        super.onStop();
        if (this.mNMC != null) {
            this.mNMC.a();
        }
    }

    public void onBackPressed() {
        try {
            super.onBackPressed();
        } catch (Exception e) {
            f.a("handle crash", "onBackPressed", e);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        com.qq.reader.cservice.b.b.a(i, i2, intent);
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
        if (this.mAdvDialog == null || !this.mAdvDialog.f()) {
            loadAdvDialog();
        }
    }

    private void loadAdvDialog() {
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
            if (aVar.q() != 2 || com.qq.reader.cservice.adv.b.b(aVar)) {
                this.mAdvDialog = new i(this, aVar.q(), aVar.r());
                this.mAdvDialog.b(aVar, this.mHandler);
                this.mAdvDialog.a(new OnCancelListener(this) {
                    final /* synthetic */ ReaderBaseActivity b;

                    public void onCancel(DialogInterface dialogInterface) {
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, String.valueOf(aVar.d()));
                        com.qq.reader.common.monitor.i.a("event_A190", hashMap, this.b.getApplicationContext());
                    }
                });
                return;
            }
            com.qq.reader.cservice.adv.b.c(aVar);
        }
    }

    protected boolean handleMessageImp(Message message) {
        com.qq.reader.cservice.cloud.d dVar;
        com.qq.reader.framework.a.b b;
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
                af.a(getApplicationContext(), (int) R.string.dialog_update_failed, 0).a();
                return true;
            case 10004:
                if (this.isAutoUpdate) {
                    return true;
                }
                af.a(getApplicationContext(), (int) R.string.dialog_net_error, 0).a();
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
                } else if (dVar.b != -1000) {
                    return true;
                } else {
                    com.qq.reader.common.login.c.a((Activity) this, Boolean.valueOf(false));
                    return true;
                }
            case 10012:
                com.qq.reader.common.login.c.a((Activity) this, Boolean.valueOf(false));
                return true;
            case 11003:
                gotoCloudActivity(-1);
                return true;
            case 65538:
                if (this.mAdvDialog == null) {
                    return true;
                }
                com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) message.obj;
                switch (aVar.t()) {
                    case 0:
                        com.qq.reader.cservice.adv.a.a(ReaderApplication.getApplicationImp(), "ADV_SHOW_DATE");
                        break;
                    case 1:
                        aVar.a(0);
                        com.qq.reader.cservice.adv.b.a(getApplicationContext()).d(aVar);
                        break;
                    case 2:
                        d.d = true;
                        break;
                }
                j.a((int) Opcodes.NOT_INT, 0);
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, String.valueOf(aVar.d()));
                com.qq.reader.common.monitor.i.a("event_A125", hashMap, ReaderApplication.getApplicationImp());
                StatisticsManager.a().a("event_A125", hashMap);
                this.mAdvDialog.f_();
                return true;
            case 65539:
                if (this.mAdvDialog == null || this.mAdvDialog.f()) {
                    return true;
                }
                this.mAdvDialog.dismiss();
                return true;
            case 65542:
                setLoginNextTask((com.qq.reader.common.login.a) message.obj);
                startLogin();
                return true;
            case 65544:
                loadAdvDialog();
                return true;
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
                    final /* synthetic */ ReaderBaseActivity a;

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
                    new o(this, (JSONObject) message.obj).f_();
                    com.qq.reader.common.monitor.i.a("event_D108", null, this.mContext);
                    return true;
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e("ReaderBaseActivity_luckymoney", e.getMessage());
                    return true;
                }
            default:
                return false;
        }
    }

    public void synCloudNoteDone(com.qq.reader.framework.a.b bVar) {
    }

    public void showCloudNoteSynWarning(com.qq.reader.framework.a.b bVar, final com.qq.reader.framework.a.b bVar2) {
        CharSequence f;
        String string = getString(R.string.alert_dialog_cloud_note_content);
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(bVar == null ? 0 : bVar.b());
        objArr[1] = Integer.valueOf(bVar2.b());
        CharSequence format = String.format(string, objArr);
        AlertDialog.a c = new AlertDialog.a(this).c(17301543);
        if (bVar2 != null) {
            f = bVar2.f();
        } else {
            f = bVar.f();
        }
        Dialog a = c.a(f).b(format).b((int) R.string.alert_dialog_cloud_note_local, new OnClickListener(this) {
            final /* synthetic */ ReaderBaseActivity b;

            public void onClick(DialogInterface dialogInterface, int i) {
                if (com.qq.reader.common.db.handle.s.a().a(bVar2.h(), bVar2.a(), (long) bVar2.d(), System.currentTimeMillis(), true)) {
                    com.qq.reader.framework.a.b b = com.qq.reader.common.db.handle.s.a().b(bVar2.h(), bVar2.a());
                    List arrayList = new ArrayList();
                    arrayList.add(b);
                    com.qq.reader.cservice.cloud.e.a().a(arrayList);
                }
            }
        }).a((int) R.string.alert_dialog_cloud_note_cloud, new OnClickListener(this) {
            final /* synthetic */ ReaderBaseActivity b;

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

    public void checkUpdate(boolean z, boolean z2) {
        if (!z || getReaderUpdateHandler().b()) {
            this.isAutoUpdate = z;
            this.isCheckShowDialog = z2;
            com.qq.reader.common.readertask.g.a().a(new AppUpdateTask(new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ ReaderBaseActivity a;

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
        return getApplicationContext();
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
        com.qq.reader.plugin.audiobook.core.l.a(getApplicationContext());
        com.qq.reader.module.feed.loader.d.b().d();
        com.qq.reader.module.game.a.b().c();
    }

    protected int getDeviceWidth() {
        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        com.qq.reader.common.c.a.bU = displayMetrics.widthPixels;
        com.qq.reader.common.c.a.bT = displayMetrics.heightPixels;
        com.qq.reader.common.c.a.bZ = displayMetrics.density;
        com.qq.reader.common.c.a.bY = (int) (160.0f * displayMetrics.density);
        com.qq.reader.common.c.a.ca = ao.x((Context) this);
        com.qq.reader.common.c.a.cb = ao.f((Activity) this);
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
        try {
            if (com.qq.reader.common.c.a.cc <= 0 || com.qq.reader.common.c.a.cd <= 0) {
                com.qq.reader.common.c.a.cc = getResources().getDimensionPixelOffset(R.dimen.common_bookcover_width);
                com.qq.reader.common.c.a.cd = getResources().getDimensionPixelOffset(R.dimen.common_bookcover_height);
            }
        } catch (Exception e) {
        }
    }

    protected void quit() {
        if (com.qq.reader.common.c.a.a()) {
            finish();
        }
    }

    public void gotoLocalImportActivity(int i) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), LocalBookActivity.class);
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
                Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage("com.qq.qcloud");
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
            intent.setClass(getApplicationContext(), PlugInDefaultActivity.class);
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
            intent.setClass(getApplicationContext(), CloudBookListActivity.class);
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
            final /* synthetic */ ReaderBaseActivity b;

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

    public void startLogin() {
        startLogin(7);
    }

    @Deprecated
    public void startLoginQQOnly() {
        com.qq.reader.common.login.c.a((Activity) this, 1);
    }

    public void startLogin(int i) {
        com.qq.reader.common.login.c.a((Activity) this, i);
    }

    public void setLoginNextTask(com.qq.reader.common.login.a aVar) {
        this.mLoginNextTask = aVar;
    }

    public void setChargeNextTask(com.qq.reader.common.charge.a aVar) {
        this.mChargeNextTask = aVar;
    }

    public void finish() {
        super.finish();
        if (this.mUseAnimation) {
            try {
                if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() >= 5) {
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                }
            } catch (Exception e) {
            }
        }
    }

    public void disableUseAnimation() {
        this.mUseAnimation = false;
    }

    public void startActivityForResult(Intent intent, int i) {
        super.startActivityForResult(intent, i);
        if (this.mUseAnimation) {
            try {
                if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() >= 5) {
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            } catch (Exception e) {
            }
        }
    }

    public void startActivity(Intent intent) {
        super.startActivity(intent);
        if (this.mUseAnimation) {
            try {
                if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() >= 5) {
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
        if (!isFinishing()) {
            if (this.mProgressDialog == null) {
                if (str == null) {
                    str = "";
                }
                this.mProgressDialog = new m(this);
                this.mProgressDialog.a(str);
                this.mProgressDialog.a(new OnKeyListener(this) {
                    final /* synthetic */ ReaderBaseActivity a;

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
            }
            this.mProgressDialog.f_();
        }
    }

    public void progressCancel() {
        if (!isFinishing() && this.mProgressDialog != null && this.mProgressDialog.f()) {
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

    protected Dialog createDialog(int i, Bundle bundle) {
        StringBuffer stringBuffer;
        AlertDialog.a b;
        Dialog a;
        switch (i) {
            case 104:
                if (!this.isAutoUpdate && this.isCheckShowDialog) {
                    com.qq.reader.common.monitor.i.a("event_D72", null, ReaderApplication.getApplicationImp());
                }
                stringBuffer = new StringBuffer();
                stringBuffer.append("\n");
                stringBuffer.append(getText(R.string.dialog_update_note));
                if (com.qq.reader.appconfig.a.c.d != null && com.qq.reader.appconfig.a.c.d.length() > 0) {
                    stringBuffer.append("\n");
                    stringBuffer.append(com.qq.reader.appconfig.a.c.d);
                }
                b = new AlertDialog.a(this).c(R.drawable.alert_dialog_icon).a((int) R.string.dialog_update_note_title).b(stringBuffer.toString());
                if (com.qq.reader.appconfig.a.c.a != 1) {
                    b.a((int) R.string.dialog_update_note_but1, new OnClickListener(this) {
                        final /* synthetic */ ReaderBaseActivity a;

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
                        final /* synthetic */ ReaderBaseActivity a;

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
                        final /* synthetic */ ReaderBaseActivity a;

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
                    final /* synthetic */ ReaderBaseActivity a;

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
                stringBuffer = new StringBuffer();
                stringBuffer.append("\n");
                stringBuffer.append(getText(R.string.dialog_install_note));
                if (com.qq.reader.appconfig.a.c.d != null && com.qq.reader.appconfig.a.c.d.length() > 0) {
                    stringBuffer.append("\n");
                    stringBuffer.append("\n");
                    stringBuffer.append(com.qq.reader.appconfig.a.c.d);
                }
                b = new AlertDialog.a(this).c(R.drawable.alert_dialog_icon).a((int) R.string.dialog_install_note_title).b(stringBuffer.toString());
                if (com.qq.reader.appconfig.a.c.a != 1) {
                    b.a((int) R.string.dialog_install_note_but1, new OnClickListener(this) {
                        final /* synthetic */ ReaderBaseActivity a;

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
                        final /* synthetic */ ReaderBaseActivity a;

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
                        final /* synthetic */ ReaderBaseActivity a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.getReaderUpdateHandler().a(this.a.mContext);
                            if (!this.a.isAutoUpdate && this.a.isCheckShowDialog) {
                                com.qq.reader.common.monitor.i.a("event_D73", null, ReaderApplication.getApplicationImp());
                            }
                            com.qq.reader.appconfig.a.c.b = null;
                            if (com.qq.reader.appconfig.a.c.a == 1) {
                                this.a.finish();
                            }
                        }
                    });
                }
                a = b.a();
                a.setCanceledOnTouchOutside(false);
                a.setOnKeyListener(new OnKeyListener(this) {
                    final /* synthetic */ ReaderBaseActivity a;

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
        try {
            MyAlertDialogFragment.newInstance(i, bundle).show(getSupportFragmentManager(), "dialog");
        } catch (Exception e) {
            f.a("ReaderBaseActivity", e.getMessage());
        }
    }

    public boolean isFragmentDialogShowing() {
        try {
            DialogFragment dialogFragment = (DialogFragment) getSupportFragmentManager().a("dialog");
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
        if (this.mShareDialog == null) {
            this.mShareDialog = new aj(this);
        }
        return this.mShareDialog;
    }

    public boolean handleMessage(Message message) {
        return handleMessageImp(message);
    }

    public void toWebBookDetail(long j) {
        ao.a((Context) this, j);
    }

    public b getReaderUpdateHandler() {
        if (this.mUpdateHandler == null) {
            this.mUpdateHandler = new b();
        }
        return this.mUpdateHandler;
    }

    public void createNotification() {
        if (this.nm == null) {
            this.nm = (NotificationManager) this.mContext.getSystemService("notification");
            this.mBuilder = ao.y((Context) this);
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

    private void showDebugUI() {
        if (!com.qq.reader.appconfig.b.a) {
            return;
        }
        if (com.qq.reader.appconfig.b.h || com.qq.reader.appconfig.b.i) {
            if (this.debugClearDetector == null) {
                this.debugClearDetector = new GestureDetector(this, new SimpleOnGestureListener(this) {
                    final /* synthetic */ ReaderBaseActivity a;

                    {
                        this.a = r1;
                    }

                    public boolean onDoubleTap(MotionEvent motionEvent) {
                        this.a.debugTextView.setText("");
                        return true;
                    }
                });
            }
            if (this.debugTextView == null) {
                this.debugTextView = new EditText(this);
                this.debugTextView.setTextSize(15.0f);
                this.debugTextView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                this.debugTextView.setTextColor(Color.parseColor("#ff0000"));
                LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, com.qq.reader.common.c.a.bT / 3);
                layoutParams.topMargin = ao.a(20.0f);
                this.debugTextView.setFocusable(true);
                this.debugTextView.setVerticalScrollBarEnabled(true);
                this.debugTextView.setFocusableInTouchMode(true);
                this.debugTextView.setLongClickable(true);
                this.debugTextView.setOnTouchListener(new OnTouchListener(this) {
                    final /* synthetic */ ReaderBaseActivity a;

                    {
                        this.a = r1;
                    }

                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return this.a.debugClearDetector.onTouchEvent(motionEvent);
                    }
                });
                addContentView(this.debugTextView, layoutParams);
            }
            if (!com.qq.reader.appconfig.b.h) {
            }
        } else if (this.debugTextView != null) {
            this.debugTextView.setVisibility(8);
        }
    }

    private String getTotalMemory() {
        ActivityManager activityManager = (ActivityManager) ReaderApplication.getApplicationImp().getSystemService(Constants.FLAG_ACTIVITY_NAME);
        int[] iArr = new int[]{Process.myPid()};
        activityManager.getMemoryInfo(new MemoryInfo());
        return Formatter.formatFileSize(ReaderApplication.getApplicationImp(), (long) (activityManager.getProcessMemoryInfo(iArr)[0].getTotalPss() * 1024));
    }
}

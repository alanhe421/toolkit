package com.qq.reader;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue.IdleHandler;
import android.os.Process;
import android.util.TimingLogger;
import android.view.ViewConfiguration;
import com.bumptech.glide.g;
import com.liveshow.b.f;
import com.q.Qt;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.push.XGRegisterShortTask;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.common.utils.ab;
import com.qq.reader.common.utils.ao;
import com.qq.reader.plugin.w;
import com.qq.reader.tinker.BaseBuildInfo;
import com.qq.reader.tinker.j;
import com.sijla.callback.QtCallBack;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.b;
import com.tencent.android.tpush.common.Constants;
import com.tencent.beacon.event.UserAction;
import com.tencent.feedback.eup.CrashReport;
import com.tencent.mm.performance.WxPerformanceHandle;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;
import java.util.List;
import org.json.JSONObject;

public class ReaderApplication extends DefaultApplicationLike {
    public static boolean IS_SUPPORT_THEME = true;
    protected static ReaderApplication instance;
    public static boolean isAllowNet = false;
    public static int isAppInForegroundResumeCount = 0;
    public static boolean isDebugApplication = false;
    public static boolean isFirstInstall = false;
    public static String mCurActivityName = "";
    public static long startTime;
    public static TimingLogger timeLog = new TimingLogger("timing", "timelogger");
    private boolean isFirstStart = true;
    public boolean isVerifySinatureOK = true;
    public Handler mUiHandler = new Handler();

    public ReaderApplication(Application application, int i, boolean z, long j, long j2, Intent intent) {
        super(application, i, z, j, j2, intent);
        instance = this;
    }

    public void onCreate() {
        super.onCreate();
        c.a(c.a().a(a.bR).a());
        i.a(new com.qq.reader.common.a.a());
        if (!d.bJ(getApplicationImp())) {
            isAllowNet = true;
            appNetworkStart();
        }
        Looper.myQueue().addIdleHandler(new IdleHandler(this) {
            final /* synthetic */ ReaderApplication a;

            {
                this.a = r1;
            }

            public boolean queueIdle() {
                com.qq.reader.tinker.d.b(ReaderApplication.getApplicationImp());
                return false;
            }
        });
        if (!ShareTinkerInternals.isInMainProcess(getApplicationImp())) {
            f.a(getApplicationImp());
        }
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks(this) {
            final /* synthetic */ ReaderApplication a;

            {
                this.a = r1;
            }

            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            public void onActivityStarted(Activity activity) {
                ReaderApplication.isAppInForegroundResumeCount++;
            }

            public void onActivityResumed(Activity activity) {
            }

            public void onActivityPaused(Activity activity) {
            }

            public void onActivityStopped(Activity activity) {
                ReaderApplication.isAppInForegroundResumeCount--;
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            public void onActivityDestroyed(Activity activity) {
            }
        });
        try {
            ViewConfiguration.get(getApplicationImp());
        } catch (Throwable th) {
            c.e("ReaderApplication", th.getMessage());
        }
        b.a(getApplicationContext(), new AuthInfo(getApplicationContext(), "628782507", "https://api.weibo.com/oauth2/default.html", "email,direct_messages_read,direct_messages_write,friendships_groups_read,friendships_groups_write,statuses_to_me_read,follow_app_official_microblog,invitation_write"));
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        if (i >= 60) {
            releaseGlide(i);
        }
        if (i == 20) {
            onAppBackground();
        }
    }

    private void onAppBackground() {
        com.qq.reader.tinker.d.a(getApplication());
    }

    private void releaseGlide(int i) {
        try {
            g.a(getApplication()).a(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBaseContextAttached(Context context) {
        startTime = System.currentTimeMillis();
        try {
            android.support.multidex.a.a(getApplicationImp());
        } catch (Throwable th) {
        }
        super.onBaseContextAttached(context);
        j.a((ApplicationLike) this);
        j.b();
        j.a(true);
        TinkerInstaller.setLogIml(new com.qq.reader.tinker.c());
        j.b(this);
        if (Tinker.with(getApplicationImp()).isTinkerLoaded()) {
            com.qq.reader.appconfig.a.a.a = com.qq.reader.tinker.a.d;
        } else {
            com.qq.reader.appconfig.a.a.a = BaseBuildInfo.b;
        }
    }

    @TargetApi(14)
    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        getApplication().registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    public void appNetworkStart() {
        if (this.isFirstStart) {
            if (isRunInQQReadersAllProcesses()) {
                timeLog.addSplit("isRunInQQReadersProcesses");
                if (new File(a.l).exists()) {
                    isFirstInstall = false;
                } else {
                    isFirstInstall = true;
                }
                com.qq.reader.appconfig.a.a.a().b();
                timeLog.addSplit("switchAccount");
                initOfflinePath();
                timeLog.addSplit("initOfflinePath");
                format.epub.common.utils.i iVar = new format.epub.common.utils.i(getApplicationImp());
                format.epub.b.c cVar = new format.epub.b.c();
                timeLog.addSplit("ZLAndroidImageManager");
                com.qq.reader.common.monitor.b.a().a(getApplicationImp());
                CrashReport.initCrashReport(getApplicationImp());
                timeLog.addSplit("CrashReport");
                UserAction.onSplashEvent();
                UserAction.initUserAction(getApplicationImp());
                timeLog.addSplit("initUserAction");
                timeLog.addSplit("getDefaultAcc");
                d.n = d.P(getApplicationImp());
                d.o = d.Q(getApplicationImp());
                com.qq.reader.cservice.bookfollow.d.a(getApplicationImp());
                timeLog.addSplit("startOrderSerive");
                com.qq.reader.common.readertask.g.a().a(new ReaderShortTask() {
                    public void run() {
                        super.run();
                        String c = com.qq.reader.common.login.c.c().c();
                        if (c != null && c.length() > 0) {
                            UserAction.setQQ(c);
                            CrashReport.setUserId(ReaderApplication.getApplicationImp(), c);
                        }
                        UserAction.setChannelID(ao.h(ReaderApplication.getApplicationImp()));
                        if (com.qq.reader.common.login.c.b() && ReaderApplication.this.isRunInQQReadersProcesses()) {
                            ReaderApplication.this.mUiHandler.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass3 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    com.qq.reader.common.login.d.a(ReaderApplication.getApplicationImp(), com.qq.reader.common.login.c.c().d(), true);
                                }
                            });
                        }
                        w.b().a(ReaderApplication.getApplicationImp());
                        w.b().e(d.bS(ReaderApplication.getApplicationImp()));
                        com.qq.reader.common.push.b.a();
                        if (ao.f(ReaderApplication.getApplicationContext())) {
                            new f().a(ReaderApplication.getApplicationContext(), null);
                        }
                    }
                }, 20);
                timeLog.addSplit("XGRegisterShortTask beofore");
                if (!com.qq.reader.b.a.a.d()) {
                    com.qq.reader.common.readertask.g.a().a(new XGRegisterShortTask());
                }
                timeLog.addSplit("XGRegisterShortTask");
                timeLog.addSplit("XiaoMiPush");
                if (com.qq.reader.appconfig.b.j) {
                    WxPerformanceHandle.getInstance(getApplicationImp());
                }
                timeLog.addSplit("WxPerformanceHandle");
                try {
                    a.a(getApplicationImp());
                    timeLog.addSplit("IRMonitorUtils");
                } catch (Throwable th) {
                    c.e("ReaderApplication", th.getMessage());
                }
                timeLog.addSplit("SkinManager");
                i.a("event_startup1", null, getApplicationImp());
                Qt.setCallBack(new QtCallBack(this) {
                    final /* synthetic */ ReaderApplication a;

                    {
                        this.a = r1;
                    }

                    public void uploadCallBack(JSONObject jSONObject) {
                        i.a("QM", null, ReaderApplication.getApplicationImp());
                    }
                });
            }
            com.qq.reader.common.readertask.g.a().a(new ReaderIOTask() {
                public void run() {
                    super.run();
                    com.qq.reader.common.readertask.f.b();
                    ab.a();
                }
            });
            this.isFirstStart = false;
        }
    }

    private void initOfflinePath() {
        a.cV = getApplicationImp().getFilesDir().getAbsolutePath() + "/temp.zip";
        a.cW = getApplicationImp().getFilesDir().getAbsolutePath() + "/copylock/";
        a.cX = getApplicationImp().getFilesDir().getAbsolutePath() + "/updatelock/";
        a.cY = getApplicationImp().getFilesDir().getAbsolutePath() + "/WebContent/";
        a.cZ = getApplicationImp().getFilesDir().getAbsolutePath() + "/_tmp/";
        a.da = getApplicationImp().getFilesDir().getAbsolutePath() + "/_delete/";
    }

    public static synchronized ReaderApplication getInstance() {
        ReaderApplication readerApplication;
        synchronized (ReaderApplication.class) {
            readerApplication = instance;
        }
        return readerApplication;
    }

    public static synchronized Application getApplicationImp() {
        Application application;
        synchronized (ReaderApplication.class) {
            application = instance.getApplication();
        }
        return application;
    }

    public static Context getApplicationContext() {
        return getApplicationImp().getApplicationContext();
    }

    private boolean isRunInQQReadersProcesses() {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) getApplicationImp().getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses();
        String packageName = getApplicationImp().getPackageName();
        int myPid = Process.myPid();
        if (runningAppProcesses == null) {
            return false;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == myPid && packageName.equals(runningAppProcessInfo.processName)) {
                return true;
            }
        }
        return false;
    }

    private boolean isRunInQQReadersAllProcesses() {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) getApplicationImp().getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses();
        String packageName = getApplicationImp().getPackageName();
        String str = packageName + ":game_process";
        int myPid = Process.myPid();
        if (runningAppProcesses == null) {
            return false;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == myPid && (packageName.equals(runningAppProcessInfo.processName) || str.equals(runningAppProcessInfo.processName))) {
                return true;
            }
        }
        return false;
    }
}

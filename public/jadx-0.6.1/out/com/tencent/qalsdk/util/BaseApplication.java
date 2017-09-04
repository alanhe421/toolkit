package com.tencent.qalsdk.util;

import android.app.Application;
import java.util.ArrayList;

public abstract class BaseApplication extends Application {
    public static final String DATA_KEY_CHANNEL_ID = "channel_id";
    public static int appnewavmsgicon = 0;
    public static int appnewmsgicon = 0;
    static BaseApplication context;
    public static int defaultNotifSoundResourceId = 0;
    public static int devlockQuickloginIcon = 0;
    public static ArrayList<String> exclusiveStreamList = new ArrayList();
    public static int qqWifiConnecticon3 = 0;
    public static int qqWifiLayout = 0;
    public static int qqWifiMissions = 0;
    public static int qqWifiNoSignal = 0;
    public static int qqWifiOperation = 0;
    public static int qqWifiOperationTextViewId = 0;
    public static int qqWifiRedTouchViewId = 0;
    public static int qqWifiSettingViewId = 0;
    public static int qqWifiSettings = 0;
    public static int qqWifiStateIconViewId = 0;
    public static int qqWifiStateTextSingleLine = 0;
    public static int qqWifiStateTextViewId = 0;
    public static int qqWifiTextDoubleLine = 0;
    public static int qqWifiUserful = 0;
    public static int qqlaunchicon = 0;
    public static int qqwifinotifyconnectedicon = 0;
    public static int qqwifinotifydivide = 0;
    public static int qqwifinotifynoneicon = 0;
    public static int qqwifinotifyusefulicon = 0;
    public static int sAppLaunchSeq;
    public static long sAppLaunchTime;
    public static boolean sIsCleanInstall = false;

    public abstract Object getAppData(String str);

    public void onCreate() {
        super.onCreate();
        context = this;
    }
}

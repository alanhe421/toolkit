package com.q;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.amap.api.location.AMapLocation;
import com.baidu.location.BDLocation;
import com.sijla.HBee;
import com.sijla.c.c;
import com.sijla.callback.QtCallBack;
import com.sijla.common.b;
import com.sijla.d.a;
import com.sijla.j.g;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import java.util.Map;

public class Qt {
    public static void init(Context context, String str) {
        start(context, str, true);
    }

    public static void init(Context context, String str, boolean z) {
        start(context, str, z);
    }

    public static void start(Context context, String str) {
        start(context, str, true);
    }

    public static void start(Context context, String str, boolean z) {
        try {
            b.a(context, str, z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void notifyInject(final Activity activity) {
        if (activity != null) {
            Intent intent = activity.getIntent();
            if (intent != null) {
                String stringExtra = intent.getStringExtra("qt_notify_open");
                if (stringExtra != null && !com.sijla.j.b.a(stringExtra)) {
                    c.a(new Runnable() {
                        public void run() {
                            g.a(activity, com.sijla.j.b.p(activity), "clickNotify");
                        }
                    });
                }
            }
        }
    }

    public static void setCallBack(QtCallBack qtCallBack) {
        b.a(qtCallBack);
    }

    public static void setUserGid(Context context, String str) {
        b.b(context, str);
    }

    public static void setDeviceUniqID(String str) {
        b.a(str);
    }

    public static void onEvent(Context context, String str, String str2) {
        onEvent(context, str, str2, null);
    }

    public static void onEvent(Context context, String str, String str2, Map<String, String> map) {
        HBee.getInstance().onEvent(context, str, str2, map, WeiboAuthException.DEFAULT_AUTH_ERROR_CODE);
    }

    public static void onReceiveBDLocation(Context context, BDLocation bDLocation) {
        HBee.getInstance().onReceiveLocation(context, bDLocation);
    }

    public static void onReceiveGDLocation(Context context, AMapLocation aMapLocation) {
        HBee.getInstance().onLocationChanged(context, aMapLocation);
    }

    public static void upload(Context context) {
        HBee.getInstance().sendData(context);
    }

    public static void update(Context context) {
        a.a(context);
    }
}

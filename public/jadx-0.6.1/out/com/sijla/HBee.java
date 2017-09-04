package com.sijla;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.amap.api.location.AMapLocation;
import com.baidu.location.BDLocation;
import com.sijla.c.c;
import com.sijla.callback.QtCallBack;
import com.sijla.common.b;
import com.sijla.d.a;
import com.sijla.h.i;
import com.sijla.j.f;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class HBee {
    private static HBee bee;

    private HBee() {
    }

    public static HBee getInstance() {
        if (bee == null) {
            synchronized (HBee.class) {
                if (bee == null) {
                    bee = new HBee();
                }
            }
        }
        return bee;
    }

    public void setCallBack(QtCallBack qtCallBack) {
        b.a(qtCallBack);
    }

    public void setUserGid(Context context, String str) {
        b.b(context, str);
    }

    public void setDeviceUniqID(String str) {
        b.a(str);
    }

    public void startService(Context context, String str) {
        startService(context, str, true);
    }

    public void startService(Context context, String str, boolean z) {
        try {
            b.a(context, str, z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void stopService(Context context) {
    }

    public void sendData(Context context) {
        c.a(new i(context, null, true));
    }

    public void onLocationChanged(Context context, AMapLocation aMapLocation) {
        c.a(new 1(this, aMapLocation, context));
    }

    public void onReceiveLocation(Context context, BDLocation bDLocation) {
        c.a(new 2(this, bDLocation, context));
    }

    private boolean isLocationChange(Context context, BDLocation bDLocation) {
        double longitude = bDLocation.getLongitude();
        double latitude = bDLocation.getLatitude();
        f.a("bd lng=" + longitude + " lat=" + latitude);
        SharedPreferences sharedPreferences = context.getSharedPreferences("arch", 0);
        Editor edit = sharedPreferences.edit();
        double a = com.sijla.j.b.a((double) sharedPreferences.getFloat("lng", 0.0f), (double) sharedPreferences.getFloat("lat", 0.0f), longitude, latitude);
        boolean z = a > 1000.0d;
        if (z) {
            edit.putFloat("lng", (float) longitude);
            edit.putFloat("lat", (float) latitude);
            edit.commit();
            f.a("bd-distance=" + a + " move=" + 1000.0d);
        }
        return z;
    }

    public void page(Context context) {
    }

    public void pageStart(Context context) {
    }

    public void pageStop(Context context) {
    }

    public void onEvent(Context context, String str, String str2) {
        onEvent(context, str, str2, null, WeiboAuthException.DEFAULT_AUTH_ERROR_CODE);
    }

    public void onEvent(Context context, String str, String str2, Map<String, String> map, String str3) {
        c.a(new 3(this, context, str, str2, map, str3));
    }

    private String mapToJsonString(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            if (str != null) {
                try {
                    jSONObject.put(str, (String) entry.getValue());
                } catch (JSONException e) {
                }
            }
        }
        return jSONObject.toString();
    }

    public void updatehbc(Context context) {
        a.a(context);
    }
}

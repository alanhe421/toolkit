package com.sijla.h;

import android.content.Context;
import android.content.Intent;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.sijla.HBee;
import com.sijla.common.a;

public class e implements BDLocationListener, a {
    private LocationClient a;
    private Context b;

    public e(Context context) {
        this.b = context;
    }

    public void run() {
    }

    public void a() {
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
        try {
            if (this.a == null) {
                this.a = new LocationClient(this.b);
                this.a.setLocOption(f());
            }
            this.a.registerLocationListener(this);
            this.a.start();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void b() {
        try {
            if (this.a != null) {
                this.a.unRegisterLocationListener(this);
                this.a.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(Intent intent) {
        if (this.a != null) {
            this.a.requestLocation();
        }
    }

    public void onReceiveLocation(BDLocation bDLocation) {
        HBee.getInstance().onReceiveLocation(this.b, bDLocation);
    }

    public LocationClientOption f() {
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setLocationMode(LocationMode.Hight_Accuracy);
        locationClientOption.setScanSpan(0);
        locationClientOption.setIsNeedAddress(true);
        locationClientOption.setNeedDeviceDirect(false);
        locationClientOption.setLocationNotify(false);
        locationClientOption.setIgnoreKillProcess(false);
        locationClientOption.setIsNeedLocationDescribe(true);
        locationClientOption.setIsNeedLocationPoiList(true);
        locationClientOption.SetIgnoreCacheException(true);
        locationClientOption.setIsNeedAltitude(false);
        return locationClientOption;
    }
}

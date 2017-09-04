package com.sijla.h;

import android.content.Context;
import android.content.Intent;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.sijla.HBee;
import com.sijla.common.a;
import com.sijla.j.f;

public class g implements AMapLocationListener, a {
    public AMapLocationClient a = null;
    private Context b;
    private AMapLocationClientOption c = null;

    public g(Context context) {
        this.b = context;
    }

    public void run() {
        e();
    }

    private void f() {
        try {
            if (this.c == null) {
                this.c = new AMapLocationClientOption();
                this.c.setLocationMode(AMapLocationMode.Hight_Accuracy);
                this.c.setNeedAddress(true);
                this.c.setOnceLocation(true);
                this.c.setWifiActiveScan(true);
            }
            this.a.setLocationOption(this.c);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onLocationChanged(AMapLocation aMapLocation) {
        HBee.getInstance().onLocationChanged(this.b, aMapLocation);
    }

    public void a() {
    }

    public void b() {
        try {
            if (this.a != null) {
                this.a.stopLocation();
                this.a.onDestroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
        try {
            if (this.a == null) {
                this.a = new AMapLocationClient(this.b);
                this.a.setLocationListener(this);
                f.a("gaodeSDKVerion = " + this.a.getVersion());
                f();
            }
            this.a.startLocation();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(Intent intent) {
        e();
    }
}

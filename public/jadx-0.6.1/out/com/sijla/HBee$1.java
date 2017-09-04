package com.sijla;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.amap.api.location.AMapLocation;
import com.sijla.j.b;
import com.sijla.j.f;
import java.util.ArrayList;
import java.util.List;

class HBee$1 implements Runnable {
    final /* synthetic */ AMapLocation a;
    final /* synthetic */ Context b;
    final /* synthetic */ HBee c;

    HBee$1(HBee hBee, AMapLocation aMapLocation, Context context) {
        this.c = hBee;
        this.a = aMapLocation;
        this.b = context;
    }

    public void run() {
        try {
            int errorCode = this.a.getErrorCode();
            f.c("gdlocation change errorcode = " + errorCode);
            if (this.a != null && errorCode == 0) {
                double latitude = this.a.getLatitude();
                double longitude = this.a.getLongitude();
                String address = this.a.getAddress();
                f.c("gaode addr = " + address);
                String poiName = this.a.getPoiName();
                f.a("gaode poiname = " + poiName);
                f.a("gaode speed = " + this.a.getSpeed());
                f.a("locationDetail = " + this.a.getLocationDetail());
                SharedPreferences sharedPreferences = this.b.getSharedPreferences("arch", 0);
                Editor edit = sharedPreferences.edit();
                double a = b.a((double) sharedPreferences.getFloat("lng", 0.0f), (double) sharedPreferences.getFloat("lat", 0.0f), longitude, latitude);
                if (a > 1000.0d) {
                    Object obj;
                    edit.putFloat("lng", (float) longitude);
                    edit.putFloat("lat", (float) latitude);
                    edit.commit();
                    String city = this.a.getCity();
                    String district = this.a.getDistrict();
                    if (b.a(address)) {
                        obj = null;
                    } else {
                        edit.putString("sadr", address).commit();
                        obj = 1;
                    }
                    List arrayList = new ArrayList();
                    arrayList.add(b.p(this.b));
                    arrayList.add(obj != null ? address : "");
                    arrayList.add(city);
                    arrayList.add(district);
                    arrayList.add(b.j(this.b));
                    arrayList.add(latitude + "");
                    arrayList.add(longitude + "");
                    arrayList.add(b.g() + "");
                    arrayList.add(poiName);
                    arrayList.add("gd");
                    com.sijla.common.b.e().a(b.f("L"), arrayList);
                }
                f.a("gd-distance=" + a + " move=" + 1000.0d);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}

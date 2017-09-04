package com.sijla;

import android.content.Context;
import com.baidu.location.BDLocation;
import com.sijla.j.b;
import com.sijla.j.f;
import com.sijla.j.j;
import java.util.ArrayList;
import java.util.List;

class HBee$2 implements Runnable {
    final /* synthetic */ BDLocation a;
    final /* synthetic */ Context b;
    final /* synthetic */ HBee c;

    HBee$2(HBee hBee, BDLocation bDLocation, Context context) {
        this.c = hBee;
        this.a = bDLocation;
        this.b = context;
    }

    public void run() {
        try {
            if (this.a == null) {
                f.a("null == location");
            } else if (505 != this.a.getLocType()) {
                String locationDescribe = this.a.getLocationDescribe();
                f.c("bd poiname = " + locationDescribe);
                Object addrStr = this.a.getAddrStr();
                f.c("bd addr = " + addrStr);
                boolean access$000 = HBee.access$000(this.c, this.b, this.a);
                f.a("bd locationChange = " + access$000);
                if (access$000) {
                    double latitude = this.a.getLatitude();
                    double longitude = this.a.getLongitude();
                    String city = this.a.getCity();
                    String district = this.a.getDistrict();
                    Object obj = !b.a(addrStr) ? 1 : null;
                    if (obj != null) {
                        j.a(this.b, "sadr", addrStr);
                    }
                    List arrayList = new ArrayList();
                    arrayList.add(b.p(this.b));
                    if (obj == null) {
                        addrStr = "";
                    }
                    arrayList.add(addrStr);
                    arrayList.add(city);
                    arrayList.add(district);
                    arrayList.add(b.j(this.b));
                    arrayList.add(latitude + "");
                    arrayList.add(longitude + "");
                    arrayList.add(b.g() + "");
                    arrayList.add(locationDescribe);
                    arrayList.add("bd");
                    com.sijla.common.b.e().a(b.f("L"), arrayList);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}

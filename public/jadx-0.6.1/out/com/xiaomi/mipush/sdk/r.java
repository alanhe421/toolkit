package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.xiaomi.channel.commonutils.c.b;
import com.xiaomi.channel.commonutils.c.e;
import com.xiaomi.channel.commonutils.c.g.a;
import com.xiaomi.channel.commonutils.d.d;
import com.xiaomi.push.service.au;
import com.xiaomi.push.service.k;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.ar;
import com.xiaomi.xmpush.thrift.c;
import com.xiaomi.xmpush.thrift.f;
import com.xiaomi.xmpush.thrift.j;
import com.xiaomi.xmpush.thrift.m;
import com.xiaomi.xmpush.thrift.n;
import com.xiaomi.xmpush.thrift.p;
import com.xiaomi.xmpush.thrift.w;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class r extends a {
    private final int a = -1;
    private final int b = 3600;
    private Context c;
    private int d;

    public r(Context context, int i) {
        this.c = context;
        this.d = i;
    }

    private static Location a(Location location, Location location2) {
        return location == null ? location2 : (location2 == null || location.getTime() > location2.getTime()) ? location : location2;
    }

    public static void a(Context context, boolean z) {
        byte[] a = ar.a(b(context));
        org.apache.thrift.a afVar = new af(WeiboAuthException.DEFAULT_AUTH_ERROR_CODE, false);
        afVar.c(p.GeoUpdateLoc.T);
        afVar.a(a);
        afVar.a(new HashMap());
        afVar.i().put("initial_wifi_upload", String.valueOf(z));
        boolean b = au.b(context);
        if (b) {
            afVar.i().put("xmsf_geo_is_work", String.valueOf(b));
        }
        al.a(context).a(afVar, com.xiaomi.xmpush.thrift.a.Notification, true, null);
        g(context);
    }

    private boolean a(long j) {
        return ((float) Math.abs((System.currentTimeMillis() / 1000) - this.c.getSharedPreferences("mipush_extra", 0).getLong("last_upload_lbs_data_timestamp", -1))) > ((float) j) * 0.9f;
    }

    protected static boolean a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        return (packageManager.checkPermission("android.permission.ACCESS_COARSE_LOCATION", packageName) == 0) || (packageManager.checkPermission("android.permission.ACCESS_FINE_LOCATION", packageName) == 0);
    }

    private static n b(Context context) {
        n nVar = new n();
        nVar.a(c(context));
        nVar.b(d(context));
        nVar.a(e(context));
        return nVar;
    }

    private boolean b() {
        if (d.f(this.c)) {
            return true;
        }
        return d.g(this.c) && a((long) Math.max(60, k.a(this.c).a(f.UploadNOWIFIGeoLocFrequency.a(), 3600)));
    }

    private static List<w> c(Context context) {
        Comparator sVar = new s();
        try {
            List scanResults = ((WifiManager) context.getSystemService("wifi")).getScanResults();
            if (b.a(scanResults)) {
                return null;
            }
            Collections.sort(scanResults, sVar);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < Math.min(30, scanResults.size()); i++) {
                ScanResult scanResult = (ScanResult) scanResults.get(i);
                if (scanResult != null) {
                    w wVar = new w();
                    wVar.a(TextUtils.isEmpty(scanResult.BSSID) ? "" : scanResult.BSSID);
                    wVar.a(scanResult.level);
                    wVar.b(scanResult.SSID);
                    arrayList.add(wVar);
                }
            }
            return arrayList;
        } catch (Throwable th) {
            return null;
        }
    }

    private static List<c> d(Context context) {
        try {
            List neighboringCellInfo = ((TelephonyManager) context.getSystemService("phone")).getNeighboringCellInfo();
            int i = 0;
            List<c> list = null;
            while (i < neighboringCellInfo.size()) {
                NeighboringCellInfo neighboringCellInfo2 = (NeighboringCellInfo) neighboringCellInfo.get(i);
                ArrayList arrayList = new ArrayList();
                if (neighboringCellInfo2.getLac() > 0 || neighboringCellInfo2.getCid() > 0) {
                    c cVar = new c();
                    cVar.a(neighboringCellInfo2.getCid());
                    cVar.b((neighboringCellInfo2.getRssi() * 2) - 113);
                    arrayList.add(cVar);
                }
                i++;
                Object obj = arrayList;
            }
            return list;
        } catch (Throwable th) {
            return null;
        }
    }

    private static j e(Context context) {
        if (!a(context)) {
            return null;
        }
        Location f = f(context);
        if (f == null) {
            return null;
        }
        m mVar = new m();
        mVar.b(f.getLatitude());
        mVar.a(f.getLongitude());
        j jVar = new j();
        jVar.a((double) f.getAccuracy());
        jVar.a(mVar);
        jVar.a(f.getProvider());
        jVar.a(new Date().getTime() - f.getTime());
        return jVar;
    }

    private static Location f(Context context) {
        Location lastKnownLocation;
        Location lastKnownLocation2;
        Location lastKnownLocation3;
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        try {
            lastKnownLocation = locationManager.getLastKnownLocation("network");
        } catch (Exception e) {
            lastKnownLocation = null;
        }
        try {
            lastKnownLocation2 = locationManager.getLastKnownLocation("gps");
        } catch (Exception e2) {
            lastKnownLocation2 = null;
        }
        try {
            lastKnownLocation3 = locationManager.getLastKnownLocation("passive");
        } catch (Exception e3) {
            lastKnownLocation3 = null;
        }
        return a(lastKnownLocation3, a(lastKnownLocation, lastKnownLocation2));
    }

    private static void g(Context context) {
        Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.putLong("last_upload_lbs_data_timestamp", System.currentTimeMillis() / 1000);
        edit.commit();
    }

    public int a() {
        return 14;
    }

    public void run() {
        if (au.e(this.c) && k.a(this.c).a(f.UploadGeoAppLocSwitch.a(), true) && d.e(this.c) && b() && e.a(this.c, String.valueOf(14), (long) this.d)) {
            a(this.c, false);
        }
    }
}

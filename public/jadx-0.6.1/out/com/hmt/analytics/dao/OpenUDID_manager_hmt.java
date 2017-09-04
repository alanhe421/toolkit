package com.hmt.analytics.dao;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.provider.Settings.Secure;
import com.hmt.analytics.common.CommonUtil;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class OpenUDID_manager_hmt implements ServiceConnection {
    private static String f = null;
    private static boolean g = false;
    private final Context a;
    private List<ResolveInfo> b;
    private Map<String, Integer> c = new HashMap();
    private final SharedPreferences d;
    private final Random e = new Random();

    private class ValueComparator implements Comparator {
        final /* synthetic */ OpenUDID_manager_hmt a;

        private ValueComparator(OpenUDID_manager_hmt openUDID_manager_hmt) {
            this.a = openUDID_manager_hmt;
        }

        public int compare(Object obj, Object obj2) {
            if (((Integer) this.a.c.get(obj)).intValue() < ((Integer) this.a.c.get(obj2)).intValue()) {
                return 1;
            }
            if (this.a.c.get(obj) == this.a.c.get(obj2)) {
                return 0;
            }
            return -1;
        }
    }

    private OpenUDID_manager_hmt(Context context) {
        this.d = context.getSharedPreferences("openudid_prefs", 0);
        this.a = context;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            Parcel obtain = Parcel.obtain();
            obtain.writeInt(this.e.nextInt());
            Parcel obtain2 = Parcel.obtain();
            iBinder.transact(1, Parcel.obtain(), obtain2, 0);
            if (obtain.readInt() == obtain2.readInt()) {
                String readString = obtain2.readString();
                if (readString != null) {
                    if (this.c.containsKey(readString)) {
                        this.c.put(readString, Integer.valueOf(((Integer) this.c.get(readString)).intValue() + 1));
                    } else {
                        this.c.put(readString, Integer.valueOf(1));
                    }
                }
            }
        } catch (RemoteException e) {
        }
        this.a.unbindService(this);
        CommonUtil.printLog("service", "unbind");
        startService();
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }

    private void storeOpenUDID() {
        Editor edit = this.d.edit();
        edit.putString("openudid", f);
        edit.commit();
    }

    private void generateOpenUDID() {
        f = Secure.getString(this.a.getContentResolver(), "android_id");
        if (f == null || f.equals("9774d56d682e549c") || f.length() < 15) {
            f = new BigInteger(64, new SecureRandom()).toString(16);
        }
    }

    private void startService() {
        if (this.b.size() > 0) {
            ServiceInfo serviceInfo = ((ResolveInfo) this.b.get(0)).serviceInfo;
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(serviceInfo.applicationInfo.packageName, serviceInfo.name));
            this.b.remove(0);
            try {
                if (this.a.bindService(intent, this, 1)) {
                    CommonUtil.printLog("openUDID", "bind opendudid service success_hmt");
                    return;
                }
                CommonUtil.printLog("openUDID", "bind opendudid service faill_hmt");
                this.a.unbindService(this);
                startService();
                return;
            } catch (SecurityException e) {
                startService();
                return;
            }
        }
        getMostFrequentOpenUDID();
        if (f == null) {
            generateOpenUDID();
        }
        storeOpenUDID();
        g = true;
    }

    private void getMostFrequentOpenUDID() {
        if (!this.c.isEmpty()) {
            TreeMap treeMap = new TreeMap(new ValueComparator());
            treeMap.putAll(this.c);
            f = (String) treeMap.firstKey();
        }
    }

    public static String getOpenUDID() {
        if (!g) {
            CommonUtil.printLog("OpenUDID", "Initialisation isn't done");
        }
        return f;
    }

    public static boolean isInitialized() {
        return g;
    }

    public static void sync(Context context) {
        OpenUDID_manager_hmt openUDID_manager_hmt = new OpenUDID_manager_hmt(context);
        f = openUDID_manager_hmt.d.getString("openudid", null);
        if (f == null) {
            openUDID_manager_hmt.b = context.getPackageManager().queryIntentServices(new Intent("org.OpenUDID.GETUDID"), 0);
            if (openUDID_manager_hmt.b != null) {
                openUDID_manager_hmt.startService();
                return;
            }
            return;
        }
        g = true;
    }
}

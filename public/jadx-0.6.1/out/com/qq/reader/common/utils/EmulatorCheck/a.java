package com.qq.reader.common.utils.EmulatorCheck;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/* compiled from: Check */
public class a {
    public static boolean a(Context context) {
        for (Sensor type : ((SensorManager) context.getSystemService("sensor")).getSensorList(-1)) {
            if (type.getType() == 9) {
                return true;
            }
        }
        return false;
    }

    public static boolean b(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        if (locationManager == null) {
            return false;
        }
        List allProviders = locationManager.getAllProviders();
        if (allProviders == null) {
            return false;
        }
        return allProviders.contains("gps");
    }

    public static int a() {
        File file = new File("/sys/devices/system/cpu");
        if (!file.exists()) {
            return 0;
        }
        File[] listFiles = file.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return Pattern.matches("cpu[0-9]", file.getName());
            }
        });
        if (listFiles == null || listFiles.length <= 0) {
            return 0;
        }
        List arrayList = new ArrayList();
        for (File absolutePath : listFiles) {
            String absolutePath2 = absolutePath.getAbsolutePath();
            try {
                int max = Math.max(Math.max(Integer.parseInt(c.a(absolutePath2 + "/cpufreq/cpuinfo_max_freq")), Integer.parseInt(c.a(absolutePath2 + "/cpufreq/scaling_cur_freq"))), Integer.parseInt(c.a(absolutePath2 + "/cpufreq/cpuinfo_min_freq")));
                if (max > 0) {
                    arrayList.add(Integer.valueOf(max));
                }
            } catch (Throwable th) {
            }
        }
        if (arrayList.isEmpty()) {
            return 0;
        }
        Collections.sort(arrayList);
        return ((Integer) arrayList.get(arrayList.size() - 1)).intValue();
    }

    public static String c(Context context) {
        if (context == null) {
            return null;
        }
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver == null) {
            return null;
        }
        int intExtra = registerReceiver.getIntExtra("temperature", -1);
        if (intExtra > 0) {
            return c.a(((float) intExtra) / 10.0f, 1);
        }
        return null;
    }

    public static String d(Context context) {
        if (context == null) {
            return null;
        }
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver == null) {
            return null;
        }
        int intExtra = registerReceiver.getIntExtra("voltage", -1);
        if (intExtra > 0) {
            return String.valueOf(intExtra);
        }
        return null;
    }
}

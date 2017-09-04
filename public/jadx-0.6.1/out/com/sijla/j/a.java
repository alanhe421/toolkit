package com.sijla.j;

import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.tencent.android.tpush.common.Constants;
import java.util.ArrayList;
import java.util.List;

public class a {
    private static List<String> a = new ArrayList();
    private Context b;

    public a(Context context) {
        this.b = context;
    }

    public String a(String str, String str2) {
        String str3 = "";
        List<String> arrayList = new ArrayList();
        if (arrayList != null && arrayList.size() > 0 && a.size() == 0) {
            for (String replace : arrayList) {
                a.add(replace.replace("\n", "").trim());
            }
        }
        arrayList = a(this.b, 5);
        if (arrayList != null && arrayList.size() > 0) {
            for (String replace2 : arrayList) {
                if (a.contains(replace2)) {
                    return replace2;
                }
            }
        }
        return str3;
    }

    private List<String> a(Context context, int i) {
        List<String> arrayList = new ArrayList();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME);
        PackageManager packageManager = context.getPackageManager();
        try {
            List recentTasks = activityManager.getRecentTasks(i, 0);
            if (recentTasks != null && recentTasks.size() > 0) {
                for (int i2 = 0; i2 < recentTasks.size(); i2++) {
                    ResolveInfo resolveActivity = packageManager.resolveActivity(((RecentTaskInfo) recentTasks.get(i2)).baseIntent, 0);
                    if (resolveActivity != null) {
                        arrayList.add(resolveActivity.activityInfo.packageName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}

package com.sijla.h;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.FileObserver;
import android.text.TextUtils;
import android.util.Log;
import com.sijla.j.b;
import com.sijla.j.f;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class a extends FileObserver {
    private Context a;
    private String b;
    private String c = "";

    public a(Context context, String str, String str2) {
        super(str2, Opcodes.FLOAT_TO_LONG);
        this.a = context;
        this.c = str;
        this.b = str2;
    }

    public void startWatching() {
        super.startWatching();
    }

    public void onEvent(int i, String str) {
        String[] a = a(this.a, this.b + File.separator + str);
        if (a != null && !TextUtils.isEmpty(a[0])) {
            try {
                String str2 = a[0];
                String str3 = "AK.csv-" + str2 + "-" + this.c + "-";
                if (b.a(this.a, "AK", str2).b()) {
                    Log.i("WADM", "ak from:" + this.c + " " + str2 + " have saved");
                    return;
                }
                Object obj = com.sijla.j.a.a.e(this.a, str2) ? "2" : "1";
                if (com.sijla.j.a.a.u(this.a).contains(this.c)) {
                    obj = obj + "3";
                }
                str3 = str3 + System.currentTimeMillis();
                List arrayList = new ArrayList();
                arrayList.add(b.p(this.a));
                arrayList.add(this.c);
                arrayList.add(str2);
                arrayList.add(a[1]);
                arrayList.add(obj);
                arrayList.add(b.g() + "");
                com.sijla.common.b.e().a(str3, arrayList);
                f.c("save ak:" + str2 + " from:" + this.c);
            } catch (Exception e) {
                e.printStackTrace();
                f.c("save ak path error");
            }
        }
    }

    public String[] a(Context context, String str) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (str.contains(ShareConstants.PATCH_SUFFIX)) {
            str = str.substring(0, str.indexOf(ShareConstants.PATCH_SUFFIX) + 4);
        }
        File file = new File(str);
        if (!file.exists() || file.isDirectory()) {
            return null;
        }
        String[] strArr = new String[2];
        try {
            PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 1);
            if (packageArchiveInfo != null) {
                String str2 = packageArchiveInfo.packageName;
                String str3 = packageArchiveInfo.versionName;
                f.a("get WADM appid:" + str2 + " appver:" + str3);
                strArr[0] = str2;
                strArr[1] = str3;
                return strArr;
            }
            f.a("null == packageInfo");
            return strArr;
        } catch (Exception e2) {
            f.a("getakinfo exception:" + e2.getMessage());
            return strArr;
        }
    }

    public void stopWatching() {
        super.stopWatching();
        f.a(this.c + " stopWatching");
    }
}

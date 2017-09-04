package com.qq.reader.common.web.js;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.web.js.a.a.b;
import com.qq.reader.view.af;

public class JSAPP extends b {
    private Context a;

    public JSAPP(Context context) {
        this.a = context;
    }

    public void log(String str) {
        c.e("Reader Test JS Log", str);
    }

    public void open(String str, String str2) {
        PackageInfo packageInfo;
        try {
            packageInfo = this.a.getPackageManager().getPackageInfo(str2, 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo == null) {
            this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            return;
        }
        Intent launchIntentForPackage = this.a.getPackageManager().getLaunchIntentForPackage(str2);
        if (launchIntentForPackage != null) {
            this.a.startActivity(launchIntentForPackage);
        } else {
            af.a(this.a.getApplicationContext(), (CharSequence) "发生错误", 0).a();
        }
    }

    public boolean isAppExist(String str) {
        return ao.a(this.a, str);
    }
}

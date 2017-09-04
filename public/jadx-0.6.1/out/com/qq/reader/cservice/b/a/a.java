package com.qq.reader.cservice.b.a;

import android.app.Activity;
import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.cservice.b.b;
import com.tencent.feedback.proguard.R;
import com.tencent.tauth.Tencent;
import java.util.ArrayList;

/* compiled from: QQShareManager */
public class a {
    public static void a(Activity activity, Tencent tencent, Bundle bundle) {
        try {
            tencent.shareToQQ(activity, bundle, b.a(activity, bundle.getString("targetUrl")));
        } catch (Exception e) {
            if (e != null) {
                c.e("share", "share2QQ ERROR : " + e.toString());
            }
        }
    }

    public static void b(Activity activity, Tencent tencent, Bundle bundle) {
        try {
            tencent.shareToQzone(activity, bundle, b.b(activity, bundle.getString("targetUrl")));
        } catch (Exception e) {
            if (e != null) {
                c.e("share", "share2QZone ERROR : " + e.toString());
            }
        }
    }

    public static void c(Activity activity, Tencent tencent, Bundle bundle) {
        tencent.publishToQzone(activity, bundle, b.b(activity, bundle.getString("targetUrl")));
    }

    public static Bundle a(int i, int i2, String str, String str2, String str3, String str4) {
        Bundle bundle = new Bundle();
        switch (i2) {
            case 1:
                bundle.putString("site", "http://book.qq.com");
                bundle.putString("appName", ReaderApplication.getApplicationImp().getString(R.string.app_name));
                if (i != 14 && i != 17) {
                    bundle.putInt("req_type", 1);
                    bundle.putString("title", str);
                    bundle.putString("imageUrl", str4);
                    bundle.putString("targetUrl", str3);
                    bundle.putString("summary", str2);
                    break;
                }
                bundle.putString("imageLocalUrl", str4);
                bundle.putInt("req_type", 5);
                break;
                break;
            case 2:
                if (i == 14 || i == 17) {
                    bundle.putString("summary", "分享图片");
                    bundle.putInt("req_type", 3);
                } else {
                    bundle.putInt("req_type", 1);
                    bundle.putString("title", str);
                    bundle.putString("summary", str2);
                    bundle.putString("targetUrl", str3);
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(str4);
                bundle.putStringArrayList("imageUrl", arrayList);
                break;
        }
        return bundle;
    }
}

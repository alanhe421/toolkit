package com.qq.reader.common.utils.networkUtil;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;

/* compiled from: DeviceInfoUtil */
public class c {
    public static String a() {
        StringBuilder stringBuilder = new StringBuilder("Device info :");
        stringBuilder.append(Build.MODEL);
        stringBuilder.append("\\");
        stringBuilder.append(VERSION.RELEASE);
        stringBuilder.append("\\");
        stringBuilder.append(VERSION.SDK_INT);
        stringBuilder.append("\\");
        stringBuilder.append(b());
        return stringBuilder.toString();
    }

    private static String b() {
        String str;
        Exception e;
        String str2 = "";
        try {
            str = ReaderApplication.getApplicationImp().getPackageManager().getPackageInfo("com.qq.reader", 0).versionName;
            try {
                if (TextUtils.isEmpty(str)) {
                    return "";
                }
                return str;
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return str;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            str = str2;
            e = exception;
            e.printStackTrace();
            return str;
        }
    }
}

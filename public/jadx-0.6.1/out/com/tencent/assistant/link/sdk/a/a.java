package com.tencent.assistant.link.sdk.a;

import android.os.Environment;
import android.text.TextUtils;
import java.io.File;

/* compiled from: ProGuard */
public class a {
    public static String a() {
        String str = "";
        if (b()) {
            str = Environment.getExternalStorageDirectory().getPath() + "/tencent/assistant";
        }
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    public static boolean b() {
        try {
            return "mounted".equals(Environment.getExternalStorageState()) && Environment.getExternalStorageDirectory().canWrite();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        return new String(new c().a(b.a(str, 0), "ji*9^&43U0X-~./(".getBytes()));
    }
}

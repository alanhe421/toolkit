package com.tencent.av.config;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Common {
    public static final String AV_ENGINE_VERSION = "V2.9.0304.001";
    public static final String AV_SHARP_VERSION = "1.0";
    public static final String CONFIG_INFO = "ConfigInfo";
    public static final int EM_Device_Android = 101;
    public static final String FILE_NAME = "VideoConfigInfo";
    public static final String SHARP_CONFIG_PAYLOAD_FILE_NAME = "SharpConfigPayload";
    public static final String SHARP_CONFIG_TYPE_CLEAR = "0";
    public static final String SHARP_CONFIG_TYPE_PAYLOAD = "1";
    public static final String SHARP_CONFIG_TYPE_URL = "2";

    public static int intPow(int i, int i2) {
        if (i2 < 0) {
            return 0;
        }
        int i3 = 1;
        while (i2 > 0) {
            i3 *= i;
            i2--;
        }
        return i3;
    }

    public static void writeFile(Context context, String str, byte[] bArr) {
        try {
            FileOutputStream openFileOutput = context.openFileOutput(str, 0);
            openFileOutput.write(bArr);
            openFileOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] readFile(Context context, String str) {
        byte[] bArr = null;
        try {
            FileInputStream openFileInput = context.openFileInput(str);
            int available = openFileInput.available();
            if (available > 0) {
                bArr = new byte[available];
                openFileInput.read(bArr);
                openFileInput.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bArr;
    }

    public static String getVersion(Context context) {
        String str = "1.0";
        if (context != null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                if (!(packageInfo == null || TextUtils.isEmpty(packageInfo.versionName.trim()))) {
                    str = packageInfo.versionName.trim();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }
}

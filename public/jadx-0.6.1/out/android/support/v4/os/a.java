package android.support.v4.os;

import android.os.Build.VERSION;
import android.os.Environment;
import android.util.Log;
import com.tencent.av.config.ConfigBaseParser;
import java.io.File;
import java.io.IOException;

/* compiled from: EnvironmentCompat */
public class a {
    public static String a(File file) {
        if (VERSION.SDK_INT >= 19) {
            return b.a(file);
        }
        try {
            if (file.getCanonicalPath().startsWith(Environment.getExternalStorageDirectory().getCanonicalPath())) {
                return Environment.getExternalStorageState();
            }
        } catch (IOException e) {
            Log.w("EnvironmentCompat", "Failed to resolve canonical path: " + e);
        }
        return ConfigBaseParser.DEFAULT_VALUE;
    }
}

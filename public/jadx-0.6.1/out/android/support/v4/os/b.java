package android.support.v4.os;

import android.os.Environment;
import java.io.File;

/* compiled from: EnvironmentCompatKitKat */
class b {
    public static String a(File file) {
        return Environment.getStorageState(file);
    }
}

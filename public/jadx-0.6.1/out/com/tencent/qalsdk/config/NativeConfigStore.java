package com.tencent.qalsdk.config;

import android.content.Context;
import com.tencent.qalsdk.util.QLog;
import java.util.concurrent.atomic.AtomicBoolean;

public class NativeConfigStore {
    private static final String NATIVE_LIB_NAME = "msfbootV2";
    private static String tag = "MSF.C.NativeConfigStore";
    Context context;
    public AtomicBoolean loadSaveRootSucc = new AtomicBoolean(true);

    public native synchronized String getConfig(String str);

    public native synchronized String[] getConfigList(String str);

    public native synchronized void loadConfig(Context context, boolean z);

    public native synchronized void removeConfig(String str);

    public native synchronized void setConfig(String str, String str2);

    public native synchronized void setSaveRootPath(String str);

    public NativeConfigStore(Context context) {
        this.context = context;
    }

    public synchronized void n_setConfig(String str, String str2) {
        if (this.loadSaveRootSucc.get()) {
            setConfig(str, str2);
        } else {
            QLog.w(tag, 1, "loadSaveRootFailed,return");
        }
    }

    public synchronized void n_removeConfig(String str) {
        if (this.loadSaveRootSucc.get()) {
            removeConfig(str);
        } else {
            QLog.w(tag, 1, "loadSaveRootFailed,return");
        }
    }
}

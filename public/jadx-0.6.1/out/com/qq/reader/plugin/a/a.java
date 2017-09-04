package com.qq.reader.plugin.a;

import android.app.ActivityManager;
import android.app.Application;
import android.os.Build.VERSION;
import com.qq.reader.common.monitor.debug.c;
import com.tencent.android.tpush.common.Constants;
import com.tencent.theme.SkinEngineHandler;

/* compiled from: OOMHandler */
public class a implements SkinEngineHandler {
    private Application a;
    private int b = -1;

    public a(Application application) {
        this.a = application;
    }

    public boolean onDecodeOOM(OutOfMemoryError outOfMemoryError, String str, boolean z) {
        if (this.b == -1) {
            this.b = ((ActivityManager) this.a.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getMemoryClass();
        }
        long totalMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        StringBuffer stringBuffer = new StringBuffer("decode resources oom, fileName: ");
        stringBuffer.append(str);
        stringBuffer.append(", is skin file: ");
        stringBuffer.append(z);
        stringBuffer.append(", memory used:");
        stringBuffer.append(totalMemory);
        stringBuffer.append(" , heap size: ");
        stringBuffer.append(this.b);
        stringBuffer.append(", api level:");
        stringBuffer.append(VERSION.SDK_INT);
        c.c("res-OOM", stringBuffer.toString(), true);
        System.gc();
        Thread.yield();
        System.gc();
        return true;
    }

    public boolean onDecodeReturnNullBitmap(String str, boolean z) {
        if (this.b == -1) {
            this.b = ((ActivityManager) this.a.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getMemoryClass();
        }
        long totalMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        StringBuffer stringBuffer = new StringBuffer("decode resources return null, fileName: ");
        stringBuffer.append(str);
        stringBuffer.append(", is skin file: ");
        stringBuffer.append(z);
        stringBuffer.append(", memory used:");
        stringBuffer.append(totalMemory);
        stringBuffer.append(" , heap size: ");
        stringBuffer.append(this.b);
        stringBuffer.append(", api level:");
        stringBuffer.append(VERSION.SDK_INT);
        c.c("res-OOM", stringBuffer.toString(), true);
        System.gc();
        Thread.yield();
        System.gc();
        return true;
    }

    public boolean onSecondDecodeOOM(OutOfMemoryError outOfMemoryError, String str, boolean z) {
        if (this.b == -1) {
            this.b = ((ActivityManager) this.a.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getMemoryClass();
        }
        long totalMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        StringBuffer stringBuffer = new StringBuffer("decode resources second oom, fileName: ");
        stringBuffer.append(str);
        stringBuffer.append(", is skin file: ");
        stringBuffer.append(z);
        stringBuffer.append(", memory used:");
        stringBuffer.append(totalMemory);
        stringBuffer.append(" , heap size: ");
        stringBuffer.append(this.b);
        stringBuffer.append(", api level:");
        stringBuffer.append(VERSION.SDK_INT);
        c.c("res-OOM", stringBuffer.toString(), true);
        System.gc();
        Thread.yield();
        System.gc();
        return true;
    }
}

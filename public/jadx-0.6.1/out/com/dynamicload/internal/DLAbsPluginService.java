package com.dynamicload.internal;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.dynamicload.Lib.DLBasePluginService;
import com.dynamicload.Lib.DLConstants;
import com.dynamicload.Lib.DLPluginManager;
import com.dynamicload.c;

public class DLAbsPluginService extends Service {
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            c.a(intent);
            DLBasePluginService service = DLPluginManager.getInstance(this).getService(intent.getStringExtra(DLConstants.EXTRA_CLASS));
            if (service != null) {
                service.onStartCommand(intent, i, i2);
            }
        }
        return super.onStartCommand(intent, i, i2);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}

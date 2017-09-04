package com.tencent.qalsdk.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.tencent.qalsdk.util.QLog;

public class QalAssistService extends Service {
    private static final String TAG = "QalAssistService";

    public class a extends Binder {
        final /* synthetic */ QalAssistService a;

        public a(QalAssistService qalAssistService) {
            this.a = qalAssistService;
        }

        public QalAssistService a() {
            return this.a;
        }
    }

    public IBinder onBind(Intent intent) {
        QLog.i(TAG, "onBind");
        return new a(this);
    }

    public void onDestroy() {
        super.onDestroy();
        QLog.i(TAG, "onDestroy");
    }
}

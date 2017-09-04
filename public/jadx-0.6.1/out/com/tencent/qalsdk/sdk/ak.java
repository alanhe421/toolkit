package com.tencent.qalsdk.sdk;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.tencent.qalsdk.base.remote.IBaseService.Stub;
import com.tencent.qalsdk.util.QLog;

/* compiled from: RemoteServiceProxy */
class ak implements ServiceConnection {
    final /* synthetic */ aj a;

    ak(aj ajVar) {
        this.a = ajVar;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (QLog.isColorLevel()) {
            QLog.d("MSF.D.RemoteServiceProxy", 2, "threadID:" + Thread.currentThread().getId() + " onServiceConnected service:" + componentName);
        }
        this.a.d = Stub.asInterface(iBinder);
        this.a.d();
        this.a.l();
    }

    public void onServiceDisconnected(ComponentName componentName) {
        QLog.i("MSF.D.RemoteServiceProxy", 2, " onServiceDisconnected " + componentName);
        this.a.d = null;
        this.a.l();
    }
}

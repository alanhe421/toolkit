package com.tencent.imsdk;

import com.tencent.TIMNetworkStatus;
import com.tencent.qalsdk.QALConnListener;

final class at implements QALConnListener {
    private /* synthetic */ IMMsfCoreProxy a;

    at(IMMsfCoreProxy iMMsfCoreProxy) {
        this.a = iMMsfCoreProxy;
    }

    public final void onConnected() {
        this.a.networkStatus = TIMNetworkStatus.TIM_NETWORK_STATUS_CONNECTED;
        IMMsfCoreProxy.mainHandler.post(new au(this));
        QLog.i("imsdk.IMMsfCoreProxy", 1, "onConnected");
    }

    public final void onDisconnected(int i, String str) {
        this.a.networkStatus = TIMNetworkStatus.TIM_NETWORK_STATUS_DISCONNECTED;
        IMMsfCoreProxy.mainHandler.post(new aw(this));
        QLog.e("imsdk.IMMsfCoreProxy", 1, "onDisconnected, code:" + i + "|desc:" + str);
    }

    public final void onWifiNeedAuth(String str) {
        IMMsfCoreProxy.mainHandler.post(new ax(this, str));
        QLog.i("imsdk.IMMsfCoreProxy", 1, "onWifiNeedAuth, desc: " + str);
    }
}

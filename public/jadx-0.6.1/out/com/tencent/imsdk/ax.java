package com.tencent.imsdk;

import com.tencent.TIMManager;
import com.tencent.timint.TIMIntManager;

final class ax implements Runnable {
    private /* synthetic */ String a;

    ax(at atVar, String str) {
        this.a = str;
    }

    public final void run() {
        if (TIMManager.getInstance().getConnectionListener() != null) {
            TIMManager.getInstance().getConnectionListener().onWifiNeedAuth(this.a);
        }
        if (TIMIntManager.getInstance().getConnectionListener() != null) {
            TIMIntManager.getInstance().getConnectionListener().onWifiNeedAuth(this.a);
        }
    }
}

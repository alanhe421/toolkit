package com.tencent.imsdk;

import com.tencent.TIMManager;
import com.tencent.timint.TIMIntManager;

final class au implements Runnable {
    au(at atVar) {
    }

    public final void run() {
        if (TIMManager.getInstance().getConnectionListener() != null) {
            TIMManager.getInstance().getConnectionListener().onConnected();
        }
        if (TIMIntManager.getInstance().getConnectionListener() != null) {
            TIMIntManager.getInstance().getConnectionListener().onConnected();
        }
    }
}

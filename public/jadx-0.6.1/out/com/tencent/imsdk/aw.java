package com.tencent.imsdk;

import com.tencent.TIMManager;
import com.tencent.timint.TIMIntManager;

final class aw implements Runnable {
    aw(at atVar) {
    }

    public final void run() {
        if (TIMManager.getInstance().getConnectionListener() != null) {
            TIMManager.getInstance().getConnectionListener().onDisconnected(0, "");
        }
        if (TIMIntManager.getInstance().getConnectionListener() != null) {
            TIMIntManager.getInstance().getConnectionListener().onDisconnected(0, "");
        }
    }
}

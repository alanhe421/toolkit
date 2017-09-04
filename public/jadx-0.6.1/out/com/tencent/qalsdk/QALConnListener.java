package com.tencent.qalsdk;

public interface QALConnListener {
    void onConnected();

    void onDisconnected(int i, String str);

    void onWifiNeedAuth(String str);
}

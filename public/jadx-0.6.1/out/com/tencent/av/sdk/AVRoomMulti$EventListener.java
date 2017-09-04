package com.tencent.av.sdk;

public interface AVRoomMulti$EventListener {
    void onCameraSettingNotify(int i, int i2, int i3);

    void onDisableAudioIssue();

    void onEndpointsUpdateInfo(int i, String[] strArr);

    void onEnterRoomComplete(int i, String str);

    void onExitRoomComplete();

    void onHwStateChangeNotify(boolean z, boolean z2, boolean z3, String str);

    void onPrivilegeDiffNotify(int i);

    void onRoomDisconnect(int i, String str);

    void onRoomEvent(int i, int i2, Object obj);

    void onSemiAutoRecvCameraVideo(String[] strArr);

    void onSwitchRoomComplete(int i, String str);
}

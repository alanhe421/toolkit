package com.tencent.qalsdk;

public interface QALUserStatusListener {
    void onForceOffline(String str);

    void onRegisterFail(String str, int i, String str2);

    void onRegisterSucc(String str);
}

package com.dynamicload.bridge;

public interface QLoginCallback {
    void loginFailed();

    void loginSuccess(String str);
}

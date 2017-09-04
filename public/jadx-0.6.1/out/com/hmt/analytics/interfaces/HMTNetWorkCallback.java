package com.hmt.analytics.interfaces;

public interface HMTNetWorkCallback {
    void preSend(String str);

    void sendFail(String str, int i);

    void sendSuccess(String str);
}

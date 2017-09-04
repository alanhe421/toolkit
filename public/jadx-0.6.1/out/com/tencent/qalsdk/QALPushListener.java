package com.tencent.qalsdk;

public interface QALPushListener {
    void onError(String str, int i, String str2);

    void onSuccess(String str, byte[] bArr);
}

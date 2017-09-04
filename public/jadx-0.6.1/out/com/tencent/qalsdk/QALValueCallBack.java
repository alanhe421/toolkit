package com.tencent.qalsdk;

public interface QALValueCallBack {
    void onError(int i, String str);

    void onSuccess(byte[] bArr);
}

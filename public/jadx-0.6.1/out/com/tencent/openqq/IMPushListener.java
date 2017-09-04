package com.tencent.openqq;

public interface IMPushListener extends IMBaseListener {
    void onRecv(byte[] bArr);
}

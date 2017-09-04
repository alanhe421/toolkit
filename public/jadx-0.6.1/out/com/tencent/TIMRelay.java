package com.tencent;

import com.tencent.timint.TIMIntManager;

public class TIMRelay {
    private static TIMRelay instance = new TIMRelay();
    private static final String serviceCmd_empty = "imopen_passthrough.callback_emp";
    private static final String serviceCmd_encrypt = "imopen_passthrough.callback";
    private static final String tag = "imsdk.TIMRelay";

    private TIMRelay() {
    }

    public static TIMRelay getInstance() {
        return instance;
    }

    public void sendDataWithEncryption(byte[] bArr, TIMValueCallBack<byte[]> tIMValueCallBack) {
        TIMIntManager.getInstance().request(serviceCmd_encrypt, bArr, tIMValueCallBack);
    }

    public void sendDataWithEncryption(byte[] bArr, TIMValueCallBack<byte[]> tIMValueCallBack, long j) {
        TIMIntManager.getInstance().request(serviceCmd_encrypt, bArr, tIMValueCallBack, j);
    }

    public void sendDataWithoutEncryption(byte[] bArr, TIMValueCallBack<byte[]> tIMValueCallBack) {
        TIMIntManager.getInstance().request(serviceCmd_empty, bArr, tIMValueCallBack);
    }

    public void sendDataWithoutEncryption(byte[] bArr, TIMValueCallBack<byte[]> tIMValueCallBack, long j) {
        TIMIntManager.getInstance().request(serviceCmd_empty, bArr, tIMValueCallBack, j);
    }
}

package com.tencent.qalhttp;

public interface QALHttpValueCallBack {
    void onFailed(int i, String str);

    void onFinished(QALHttpResponse qALHttpResponse);
}

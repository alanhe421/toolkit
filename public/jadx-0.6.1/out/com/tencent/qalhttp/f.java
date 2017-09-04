package com.tencent.qalhttp;

import com.tencent.qalsdk.base.a;
import com.tencent.qalsdk.util.QLog;
import java.io.UnsupportedEncodingException;

/* compiled from: QALStringRequest */
class f implements QALHttpValueCallBack {
    final /* synthetic */ QALHttpValueCallBack a;
    final /* synthetic */ QALStringRequest b;

    f(QALStringRequest qALStringRequest, QALHttpValueCallBack qALHttpValueCallBack) {
        this.b = qALStringRequest;
        this.a = qALHttpValueCallBack;
    }

    public void onFailed(int i, String str) {
        QLog.e(QALStringRequest.TAG, this.b.uri + "failed:" + i + ":" + str);
        this.a.onFailed(i, str);
    }

    public void onFinished(QALHttpResponse qALHttpResponse) {
        String str = qALHttpResponse.responsePrivate.content_charset.get();
        if (str == null || str.length() == 0) {
            str = "utf-8";
        }
        try {
            String str2 = new String(qALHttpResponse.getBody(), str);
            QLog.d(QALStringRequest.TAG, "charset:" + str + ":" + qALHttpResponse.getBody().length);
            qALHttpResponse.setStringResp(str2);
            this.a.onFinished(qALHttpResponse);
        } catch (UnsupportedEncodingException e) {
            QLog.e(QALStringRequest.TAG, this.b.uri + " unsupport encodeing:" + str);
            this.a.onFailed(a.bF, "QAL_SDK_STRING_DECODE_ERR");
        }
    }
}

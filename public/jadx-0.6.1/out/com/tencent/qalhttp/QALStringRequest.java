package com.tencent.qalhttp;

import com.tencent.open.SocialConstants;
import com.tencent.qalsdk.util.QLog;

public class QALStringRequest extends QALHttpRequest {
    private static final String TAG = QALStringRequest.class.getSimpleName();
    private String uri = null;

    public QALStringRequest(String str) {
        super(str);
        this.uri = str;
    }

    public void request(QALHttpValueCallBack qALHttpValueCallBack) {
        QALHttpValueCallBack fVar = new f(this, qALHttpValueCallBack);
        QLog.d(TAG, SocialConstants.TYPE_REQUEST);
        super.request(fVar);
    }
}

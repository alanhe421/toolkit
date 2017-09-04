package com.tencent.qalhttp;

import com.tencent.qalsdk.base.a;
import com.tencent.qalsdk.util.QLog;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: QALJSONObjectRequest */
class e implements QALHttpValueCallBack {
    final /* synthetic */ QALHttpValueCallBack a;
    final /* synthetic */ QALJSONObjectRequest b;

    e(QALJSONObjectRequest qALJSONObjectRequest, QALHttpValueCallBack qALHttpValueCallBack) {
        this.b = qALJSONObjectRequest;
        this.a = qALHttpValueCallBack;
    }

    public void onFailed(int i, String str) {
        QLog.e(QALJSONObjectRequest.TAG, this.b.uri + " failed:" + i + ":" + str);
        this.a.onFailed(i, str);
    }

    public void onFinished(QALHttpResponse qALHttpResponse) {
        String str = qALHttpResponse.responsePrivate.content_charset.get();
        if (str == null || str.length() == 0) {
            str = "utf-8";
        }
        QLog.d(QALJSONObjectRequest.TAG, "strCharSet:" + str);
        try {
            qALHttpResponse.setJSONObjectResp(new JSONObject(new String(qALHttpResponse.getBody(), str)));
            this.a.onFinished(qALHttpResponse);
        } catch (UnsupportedEncodingException e) {
            QLog.e(QALJSONObjectRequest.TAG, this.b.uri + " unsupport encodeing:" + str);
            this.a.onFailed(a.bF, "QAL_SDK_STRING_DECODE_ERR");
        } catch (JSONException e2) {
            QLog.e(QALJSONObjectRequest.TAG, this.b.uri + " jason parse error:" + e2.getMessage());
            this.a.onFailed(a.bG, "jason parse error:" + e2.getMessage());
        }
    }
}

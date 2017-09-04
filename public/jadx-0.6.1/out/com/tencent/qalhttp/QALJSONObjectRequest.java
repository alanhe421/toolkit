package com.tencent.qalhttp;

import org.json.JSONObject;

public class QALJSONObjectRequest extends QALHttpRequest {
    private static final String TAG = QALJSONObjectRequest.class.getSimpleName();
    private JSONObject jsonRequest = null;
    private String uri;

    public QALJSONObjectRequest(String str, JSONObject jSONObject) {
        super(str);
        this.uri = str;
        this.jsonRequest = jSONObject;
    }

    public void request(QALHttpValueCallBack qALHttpValueCallBack) {
        QALHttpValueCallBack eVar = new e(this, qALHttpValueCallBack);
        setBody(this.jsonRequest == null ? null : this.jsonRequest.toString().getBytes());
        setContentType("application/json; charset=utf-8");
        super.request(eVar);
    }
}

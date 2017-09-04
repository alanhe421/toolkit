package com.pay.http;

import com.tencent.connect.common.Constants;

public abstract class APHttpReqPost extends APBaseHttpReq {
    public APHttpReqPost() {
        this.httpParam.setReqWithHttp();
        this.httpParam.setSendWithPost();
    }

    private void a() {
        try {
            this.httpURLConnection.setRequestMethod(Constants.HTTP_POST);
            this.httpURLConnection.setRequestProperty("Charset", "UTF-8");
            this.httpURLConnection.setDoInput(true);
            this.httpURLConnection.setDoOutput(true);
            this.httpURLConnection.setRequestProperty("Content-Length", String.valueOf(this.httpParam.urlParams.getBytes().length));
            this.httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        } catch (Exception e) {
        }
    }

    protected void setBody() {
        super.setBody();
    }

    protected void setHeader() {
        a();
    }
}

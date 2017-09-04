package com.tencent.qalhttp;

import com.tencent.qalsdk.QALSDKManager;
import java.util.List;
import java.util.Map;

public class QALHttpRequest {
    protected static final String PROTOCOL_CHARSET = "utf-8";
    static a cacheHelper = new a();
    private static final String tag = "QALHttpRequest";
    private QALHttpHelper helper;

    public static void init() {
        a.a(QALSDKManager.getInstance().getContext());
    }

    public static void setCacheMaxSize(long j) {
        a.a(j);
    }

    public QALHttpRequest(String str) {
        this.helper = new QALHttpHelper(str);
    }

    public void setRequestMethod(int i) {
        this.helper.setRequestMethod(i);
    }

    public void setContentType(String str) {
        this.helper.setContentType(str);
    }

    public void setAccept(String str) {
        this.helper.setAccept(str);
    }

    public void setAcceptLanguage(String str) {
        this.helper.setAcceptLanguage(str);
    }

    public void setAcceptCharset(String str) {
        this.helper.setAcceptCharset(str);
    }

    public void setUserAgent(String str) {
        this.helper.setUserAgent(str);
    }

    public void setCookie(String str) {
        this.helper.setCookie(str);
    }

    public void setReferer(String str) {
        this.helper.setReferer(str);
    }

    public void setOrigin(String str) {
        this.helper.setOrigin(str);
    }

    public void setXRequestedWith(String str) {
        this.helper.setXRequestedWith(str);
    }

    public void setIfModifiedSince(String str) {
        this.helper.setIfModifiedSince(str);
    }

    public void setIfUnmodifiedSince(String str) {
        this.helper.setIfUnmodifiedSince(str);
    }

    public void setIfNoneMatch(String str) {
        this.helper.setIfNoneMatch(str);
    }

    public void setIfMatch(String str) {
        this.helper.setIfMatch(str);
    }

    public void setCacheControl(List<String> list) {
        this.helper.setCacheControl(list);
    }

    public void setPragma(String str) {
        this.helper.setPragma(str);
    }

    public void setRequestOtherHeader(String str, String str2) {
        this.helper.setRequestOtherHeader(str, str2);
    }

    public void setFormData(String str, Map<String, String> map) {
        this.helper.setFormData(str, map);
    }

    public void setBody(byte[] bArr) {
        this.helper.setBody(bArr);
    }

    public void request(QALHttpValueCallBack qALHttpValueCallBack) {
        this.helper.request(qALHttpValueCallBack);
    }
}

package com.yw.game.sdk.login.util.network;

import android.os.Handler;
import android.os.Looper;
import com.google.zxing.common.StringUtils;
import com.tencent.connect.common.Constants;
import java.io.Serializable;

public abstract class BaseHttp implements Serializable, Comparable<BaseHttp>, Runnable {
    private static final String[] CHARSET = new String[]{"utf-8", StringUtils.GB2312};
    static final String GET = PERMITTED_USER_METHODS[1];
    private static final String[] PERMITTED_USER_METHODS = new String[]{"OPTIONS", Constants.HTTP_GET, "HEAD", Constants.HTTP_POST, "PUT", "DELETE", "TRACE"};
    static final String POST = PERMITTED_USER_METHODS[3];
    static final int PRIORITY_HIGH = 0;
    static final int PRIORITY_LOW = 2;
    static final int PRIORITY_MEDIUM = 1;
    static final String UTF_8 = CHARSET[0];
    private static final long serialVersionUID = 1;
    String charset = UTF_8;
    int connectTimeout = com.tencent.android.tpush.common.Constants.ERRORCODE_UNKNOWN;
    protected Exception exception;
    protected d handler = null;
    private Handler mHandler = null;
    int readTimeout = com.tencent.android.tpush.common.Constants.ERRORCODE_UNKNOWN;
    int requestId;
    String requestMothod = GET;
    String urlStr = null;

    protected abstract void interup(Http http);

    protected abstract void mainThread(String str);

    protected abstract String schedulersIo();

    String getUrlStr() {
        return this.urlStr;
    }

    public void setUrlStr(String str) {
        this.urlStr = str;
    }

    public Exception getException() {
        return this.exception;
    }

    public String getRequestMothod() {
        return this.requestMothod;
    }

    public String getCharset() {
        return this.charset;
    }

    public int getRequestId() {
        return this.requestId;
    }

    public BaseHttp(String str, String str2) {
        this.urlStr = str;
        this.requestMothod = str2;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.requestId = str.hashCode() + str2.hashCode();
    }

    public void run() {
        final String schedulersIo = schedulersIo();
        this.mHandler.post(new Runnable(this) {
            final /* synthetic */ BaseHttp b;

            public void run() {
                this.b.mainThread(schedulersIo);
            }
        });
    }

    protected void setConnectTimeout(int i) {
        this.connectTimeout = i;
    }

    protected void setReadTimeout(int i) {
        this.readTimeout = i;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BaseHttp)) {
            return false;
        }
        if (this.requestId != ((BaseHttp) obj).requestId) {
            z = false;
        }
        return z;
    }

    public int compareTo(BaseHttp baseHttp) {
        if (this.requestId == baseHttp.getRequestId()) {
            return 0;
        }
        return this.requestId < baseHttp.getRequestId() ? 1 : -1;
    }
}

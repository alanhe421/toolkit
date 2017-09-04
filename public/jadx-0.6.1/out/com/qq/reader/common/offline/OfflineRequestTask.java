package com.qq.reader.common.offline;

import android.os.Message;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.c.a;
import com.qq.reader.common.conn.http.b;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.tencent.connect.common.Constants;
import com.tencent.qalsdk.sdk.v;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public class OfflineRequestTask extends ReaderProtocolJSONTask {
    private String mCallBackMethod;
    private String mHostName;
    private String mPostContent;
    private long mRunTime;
    private boolean mShouldCache;
    private boolean mShouldCallback;
    private Message msg;

    public OfflineRequestTask(c cVar, String str, String str2) {
        super(cVar);
        this.mUrl = str;
        this.mCallBackMethod = str2;
    }

    public OfflineRequestTask(String str, String str2) {
        super(null);
        this.mUrl = str;
        this.mCallBackMethod = str2;
    }

    public OfflineRequestTask(String str, String str2, String str3) {
        this(str, str2);
        this.mPostContent = str3;
    }

    public String getContentType() {
        return "application/x-www-form-urlencoded";
    }

    public String getRequestContent() {
        if (this.mPostContent != null) {
            return this.mPostContent;
        }
        return null;
    }

    public void registerReaderNetTaskListener(c cVar) {
        this.mListener = cVar;
    }

    public String getHostName() {
        return this.mHostName;
    }

    public void setHostName(String str) {
        this.mHostName = str;
    }

    public void setShouldCallBack(boolean z) {
        this.mShouldCallback = z;
    }

    public boolean getShouldCallBack() {
        return this.mShouldCallback;
    }

    public void setShouldCache(boolean z) {
        this.mShouldCache = z;
    }

    public long getRunTime() {
        return this.mRunTime;
    }

    public boolean getShouldCache() {
        return this.mShouldCache;
    }

    public String getCallBackMethod() {
        return this.mCallBackMethod;
    }

    public void setMessage(Message message) {
        this.msg = message;
    }

    public Message getMessage() {
        return this.msg;
    }

    public void run() {
        try {
            String requestMethod = getRequestMethod();
            this.mContentType = getContentType();
            this.mHeaders = getBasicHeader();
            this.mRunTime = System.currentTimeMillis();
            try {
                onFinish(b.b(this.mUrl, getRequest(), requestMethod, this.mHeaders, this.mContentType, getContext()));
            } catch (Exception e) {
                onError(e);
            } catch (Exception e2) {
                onError(e2);
            } catch (Exception e22) {
                onError(e22);
            }
        } catch (Exception e222) {
            onError(e222);
        }
    }

    protected void onFinish(HttpResponse httpResponse) throws IOException {
        String value = httpResponse.getFirstHeader("Cache-Control").getValue();
        InputStream inputStream = null;
        HttpEntity entity = httpResponse.getEntity();
        try {
            inputStream = entity.getContent();
            String a = b.a(inputStream);
            if (this.mListener != null) {
                ((d) this.mListener).a(this, a, value);
            }
            if (entity != null) {
                entity.consumeContent();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            onError(e2);
            if (entity != null) {
                entity.consumeContent();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                }
            }
        } catch (Exception e22) {
            e22.printStackTrace();
            onError(e22);
            if (entity != null) {
                entity.consumeContent();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                }
            }
        } catch (Throwable th) {
            if (entity != null) {
                entity.consumeContent();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                }
            }
        }
    }

    public HashMap<String, String> getBasicHeader() {
        this.mHeaders.put("resolution", a.bU + v.n + a.bT);
        this.mHeaders.put("safekey", d.y(getContext()));
        return this.mHeaders;
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }
}

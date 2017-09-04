package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.utils.ao;
import com.tencent.connect.common.Constants;
import java.util.HashMap;

public class PostTopicTask extends ReaderProtocolJSONTask {
    private int mCtype;
    private String mErrormsg = null;
    public String mFakeCommentId = null;
    private String mOrginalUrl = null;

    public PostTopicTask(c cVar, String str, int i) {
        super(cVar);
        this.mUrl = e.bH + str + "&ctype=" + i;
        this.mOrginalUrl = this.mUrl;
        this.mCtype = i;
        setFailedType(2);
    }

    public HashMap<String, String> getBasicHeader() {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        this.mHeaders.put("qrsy", d.f(this.mContext, currentTimeMillis));
        this.mHeaders.put("qrtm", String.valueOf(currentTimeMillis));
        this.mHeaders.put("qrem", ao.p());
        return super.getBasicHeader();
    }

    public void setRequest(String str) {
        this.mRequest = str;
    }

    public void setFakeCommentId(String str) {
        f.d("fake", "setFakeCommentId " + str);
        this.mFakeCommentId = str;
        this.mOrginalUrl = this.mUrl;
        this.mUrl += "&signal=" + str;
    }

    public String getFakeCommentId() {
        f.d("fake", "getFakeCommentId " + this.mFakeCommentId);
        return this.mFakeCommentId;
    }

    public void setErrorMessage(String str) {
        this.mErrormsg = str;
    }

    public String getErrorMsg() {
        return this.mErrormsg;
    }

    public String getRequestContent() {
        f.d("post", "getRequestContent " + this.mRequest);
        return this.mRequest;
    }

    public String getOrginalUrl() {
        return this.mOrginalUrl;
    }

    public String generateTaskKey() {
        return this.mOrginalUrl + this.mRequest;
    }

    public boolean isSameKindofTask(ReaderTask readerTask) {
        if ((readerTask instanceof PostTopicTask) && this.mOrginalUrl != null && this.mOrginalUrl.equals(((PostTopicTask) readerTask).getOrginalUrl())) {
            return true;
        }
        return false;
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }

    public boolean isRequestGzip() {
        return true;
    }

    public int getCtype() {
        return this.mCtype;
    }
}

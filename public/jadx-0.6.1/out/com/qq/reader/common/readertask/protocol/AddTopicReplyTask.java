package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.utils.ao;
import com.tencent.connect.common.Constants;
import java.util.HashMap;

public class AddTopicReplyTask extends ReaderProtocolJSONTask {
    private static final long serialVersionUID = 1;

    public AddTopicReplyTask(String str, int i, String str2, String str3, String str4, String str5, long j, int i2) {
        if (i == 1) {
            this.mUrl = e.bT + "tid=" + str + "&replytype=" + i + "&commentid=" + str2 + "&replyid=" + str3 + "&replyuid=" + str4 + "&signal=" + j + "&ctype=" + i2;
            this.mRequest = wrapJsonData(str5);
        } else {
            this.mUrl = e.bT + "tid=" + str + "&replytype=" + i + "&commentid=" + str2 + "&signal=" + j + "&ctype=" + i2;
            this.mRequest = wrapJsonData(str5);
        }
        setFailedType(1);
    }

    public HashMap<String, String> getBasicHeader() {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        this.mHeaders.put("qrsy", d.f(this.mContext, currentTimeMillis));
        this.mHeaders.put("qrtm", String.valueOf(currentTimeMillis));
        this.mHeaders.put("qrem", ao.p());
        return super.getBasicHeader();
    }

    public boolean isNeedLogin() {
        return true;
    }

    public void setRequest(String str) {
        this.mRequest = str;
    }

    public String generateTaskKey() {
        return this.mUrl + this.mRequest;
    }

    public boolean isSameKindofTask(ReaderTask readerTask) {
        if ((readerTask instanceof PostTopicTask) && this.mUrl != null && this.mUrl.equals(((PostTopicTask) readerTask).getUrl())) {
            return true;
        }
        return false;
    }

    public boolean isRequestGzip() {
        return true;
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }
}

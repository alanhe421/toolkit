package com.qq.reader.common.readertask.protocol;

import android.text.TextUtils;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.readpage.voteview.net.GetVoteUserIconsTask;
import com.tencent.connect.common.Constants;
import java.util.HashMap;

public class AddAudioReplyTask extends ReaderProtocolJSONTask {
    public AddAudioReplyTask(String str, String str2, int i, String str3, String str4, String str5, long j, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(e.cy);
        stringBuilder.append("?qid=").append(str);
        stringBuilder.append("&ctype=").append(i2);
        if (!TextUtils.isEmpty(str2)) {
            stringBuilder.append(GetVoteUserIconsTask.BID).append(str2);
        }
        stringBuilder.append("&replytype=").append(i);
        stringBuilder.append("&signal=").append(j);
        if (i == 1) {
            stringBuilder.append("&replyid=").append(str3);
            stringBuilder.append("&replyuid=").append(str4);
        }
        this.mUrl = stringBuilder.toString();
        this.mRequest = wrapJsonData(str5);
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

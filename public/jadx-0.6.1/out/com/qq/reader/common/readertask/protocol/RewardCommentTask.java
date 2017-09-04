package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.readpage.voteview.net.GetVoteUserIconsTask;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.connect.common.Constants;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class RewardCommentTask extends ReaderProtocolJSONTask {
    public RewardCommentTask(String str, String str2, String str3, c cVar) {
        super(cVar);
        this.mUrl = e.bZ + "bid=" + str + GetVoteUserIconsTask.CID + str2;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(MessageKey.MSG_CONTENT, str3);
            setRequest(jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        com.qq.reader.common.monitor.debug.c.a("dialog", "url " + this.mUrl);
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

    protected String getRequestContent() {
        com.qq.reader.common.monitor.debug.c.a("dialog", "getRequestContent " + this.mRequest);
        return this.mRequest;
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

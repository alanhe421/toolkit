package com.qq.reader.common.readertask.protocol;

import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.tencent.connect.common.Constants;
import org.json.JSONObject;

public class AudioQuestionReportTask extends ReaderProtocolJSONTask {
    String qid;
    int reason;

    public AudioQuestionReportTask(int i, String str, c cVar) {
        super(cVar);
        this.reason = i;
        this.qid = str;
        this.mUrl = e.cB;
    }

    protected String getRequestContent() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("reportReason", String.valueOf(this.reason));
            jSONObject.put("authorId", String.valueOf(com.qq.reader.common.login.c.c().l(ReaderApplication.getApplicationImp())));
            jSONObject.put("qid", this.qid);
            jSONObject.put("status", "4");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }
}

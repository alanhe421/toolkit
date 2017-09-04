package com.qq.reader.common.readertask.protocol;

import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.tencent.connect.common.Constants;
import org.json.JSONObject;

public class AudioQuestionRefuseTask extends ReaderProtocolJSONTask {
    String content;
    String qid;

    public AudioQuestionRefuseTask(String str, String str2, c cVar) {
        super(cVar);
        this.content = str;
        this.qid = str2;
        this.mUrl = e.cA;
    }

    protected String getRequestContent() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", "3");
            jSONObject.put("qid", this.qid);
            jSONObject.put("authorId", String.valueOf(com.qq.reader.common.login.c.c().l(ReaderApplication.getApplicationImp())));
            if (!TextUtils.isEmpty(this.content)) {
                jSONObject.put("refusalReason", this.content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }
}

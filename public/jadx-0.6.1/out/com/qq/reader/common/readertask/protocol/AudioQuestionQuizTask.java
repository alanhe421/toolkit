package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.tencent.connect.common.Constants;
import java.net.URLEncoder;
import org.json.JSONObject;

public class AudioQuestionQuizTask extends ReaderProtocolJSONTask {
    String authorId;
    String content;
    String price;

    public AudioQuestionQuizTask(String str, int i, long j, c cVar) {
        super(cVar);
        this.content = str;
        this.authorId = String.valueOf(j);
        this.price = String.valueOf(i);
        this.mUrl = e.cw;
    }

    protected String getRequestContent() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("authorId", this.authorId);
            jSONObject.put("questionDescription", URLEncoder.encode(this.content));
            jSONObject.put("price", this.price);
            jSONObject.put("userType", "0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }
}

package com.qq.reader.module.question.loader;

import android.text.TextUtils;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.tencent.connect.common.Constants;
import org.json.JSONObject;

public class AuthorSayNativeDataProtocolTask extends ReaderProtocolJSONTask {
    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }

    protected String getRequestContent() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(d.f)) {
                jSONObject.put("adfilterid", d.f);
            }
            if (!TextUtils.isEmpty(d.g)) {
                jSONObject.put("recfilterid", d.g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}

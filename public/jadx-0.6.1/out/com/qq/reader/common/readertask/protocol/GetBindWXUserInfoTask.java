package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.utils.a.c;
import com.qq.reader.common.utils.a.f;
import com.qq.reader.module.bookstore.qnative.card.impl.AuthorWXInfoBindCard.WXBasicInfoItem;
import com.tencent.connect.common.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public class GetBindWXUserInfoTask extends ReaderProtocolJSONTask {
    WXBasicInfoItem authorInfo;

    public GetBindWXUserInfoTask(WXBasicInfoItem wXBasicInfoItem) {
        this.mUrl = e.g + "login";
        this.authorInfo = wXBasicInfoItem;
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }

    public String getRequestContent() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ACCESS_TOKEN", this.authorInfo.accessToken);
            jSONObject.put("OPENID", this.authorInfo.openId);
            jSONObject.put("UNIONID", this.authorInfo.unionId);
            jSONObject.put("REFRESH_TOKEN", this.authorInfo.refreshToken);
            jSONObject.put("SCOPE", this.authorInfo.scope);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        byte[] bArr = null;
        try {
            bArr = f.a(jSONObject.toString().getBytes());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return c.a(bArr);
    }
}

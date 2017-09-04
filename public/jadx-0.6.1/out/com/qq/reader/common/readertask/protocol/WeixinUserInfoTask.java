package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.login.define.a;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.utils.a.c;
import com.qq.reader.common.utils.a.f;
import com.tencent.connect.common.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public class WeixinUserInfoTask extends ReaderProtocolJSONTask {
    public WeixinUserInfoTask(String str, String str2) {
        this.mUrl = e.g + "login";
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }

    public String getRequestContent() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ACCESS_TOKEN", a.d(getContext()));
            jSONObject.put("OPENID", a.j(getContext()));
            jSONObject.put("UNIONID", a.m(getContext()));
            jSONObject.put("REFRESH_TOKEN", a.i(getContext()));
            jSONObject.put("SCOPE", a.n(getContext()));
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

    protected void doConnectionSuccessToRDM(boolean z) {
        i.a("event_login_wx_getuserinfo", true, 0, 0, null, false, false, this.mContext);
    }

    protected void doConnectionErrorToRDM(boolean z, Exception exception) {
        i.a("event_login_wx_getuserinfo", false, 0, 0, null, true, false, this.mContext);
    }
}

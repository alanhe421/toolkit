package com.qq.reader.common.readertask.protocol;

import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.wxapi.WXApiManager;
import com.tencent.connect.common.Constants;

public class WeixinLoginTask extends ReaderProtocolJSONTask {
    public WeixinLoginTask(String str) {
        this.mUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WXApiManager.a + "&secret=" + WXApiManager.b + "&code=" + str + "&grant_type=authorization_code";
    }

    public String getRequestMethod() {
        return Constants.HTTP_GET;
    }

    protected void doConnectionSuccessToRDM(boolean z) {
        i.a("event_login_wx_getaccesstoken", true, 0, 0, null, false, false, this.mContext);
    }

    protected void doConnectionErrorToRDM(boolean z, Exception exception) {
        i.a("event_login_wx_getaccesstoken", false, 0, 0, null, true, false, this.mContext);
        i.a("event_login_by_wx", false, 0, 0, null, true, false, this.mContext);
    }
}

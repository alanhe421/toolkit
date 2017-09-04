package com.qq.reader.common.readertask.protocol;

import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.wxapi.WXApiManager;
import com.tencent.connect.common.Constants;

public class WXRefreshTokenTask extends ReaderProtocolJSONTask {
    public WXRefreshTokenTask(String str) {
        setFailedType(1);
        this.mUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + WXApiManager.a + "&grant_type=refresh_token&refresh_token=" + str;
    }

    public String getRequestMethod() {
        return Constants.HTTP_GET;
    }

    protected void doConnectionSuccessToRDM(boolean z) {
        i.a("event_login_wx_refreshaccesstoken", true, 0, 0, null, false, false, this.mContext);
        i.a("event_login_by_wx", true, 0, 0, null, false, false, this.mContext);
    }

    protected void doConnectionErrorToRDM(boolean z, Exception exception) {
        i.a("event_login_wx_refreshaccesstoken", false, 0, 0, null, true, false, this.mContext);
        i.a("event_login_by_wx", false, 0, 0, null, true, false, this.mContext);
    }
}

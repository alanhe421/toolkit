package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class UserQASettingUploadTask extends ReaderProtocolJSONTask {
    Map<String, String> data = new HashMap();

    public UserQASettingUploadTask(String str, String str2, String str3, c cVar) {
        super(cVar);
        this.mUrl = e.a.concat("nativepage/aqa/edit").concat("?authorId=").concat(str3);
        this.data.put(SocialConstants.PARAM_APP_DESC, str);
        this.data.put("price", str2);
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }

    public String getContentType() {
        return "application/json";
    }

    public boolean isRequestGzip() {
        return true;
    }

    protected String getRequestContent() {
        return new JSONObject(this.data).toString();
    }
}

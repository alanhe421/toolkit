package com.qq.reader.module.question.loader;

import android.text.TextUtils;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.module.question.data.c;
import com.tencent.connect.common.Constants;
import org.json.JSONObject;

public class FamousAuthorSayObtainTask extends ReaderProtocolJSONTask {
    private static final String TAG = "FamousAuthorSay";
    private c mPackage;

    public FamousAuthorSayObtainTask(c cVar) {
        this.mPackage = cVar;
        this.mUrl = buildUrl(this.mPackage);
    }

    private String buildUrl(c cVar) {
        if (cVar == null) {
            return "";
        }
        String f = cVar.f();
        if (f != null && f.length() > 0) {
            return f;
        }
        StringBuilder stringBuilder = new StringBuilder(e.a);
        stringBuilder.append("nativepage/authortalk/list?");
        stringBuilder.append("move=");
        stringBuilder.append(cVar.c());
        stringBuilder.append("&lastDisplayTime=");
        stringBuilder.append(cVar.b() > 0 ? Long.valueOf(cVar.b()) : "");
        f.a(TAG, "url is " + stringBuilder.toString() + " adfilterid:" + d.f + " recfilterid: " + d.g);
        return stringBuilder.toString();
    }

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

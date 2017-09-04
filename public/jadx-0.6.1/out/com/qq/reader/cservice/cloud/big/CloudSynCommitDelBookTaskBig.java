package com.qq.reader.cservice.cloud.big;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.cservice.cloud.a.d;
import com.tencent.connect.common.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CloudSynCommitDelBookTaskBig extends ReaderProtocolJSONTask {
    private static final long serialVersionUID = 1;
    private transient String jsonString;

    public CloudSynCommitDelBookTaskBig(c cVar, d dVar, long j) {
        super(cVar);
        this.mUrl = e.aX + 1;
        this.jsonString = getUpListString(dVar, j);
        setFailedType(1);
    }

    protected boolean interuptNoConn() {
        return true;
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }

    public String getRequestContent() {
        return this.jsonString;
    }

    public String getContentType() {
        return "application/json";
    }

    private String getUpListString(d dVar, long j) {
        String str = null;
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("method", dVar.n());
            jSONObject2.put("bookid", dVar.b());
            jSONObject2.put("updatetime", dVar.f());
            jSONObject2.put("format", dVar.a());
            jSONObject2.put("bookType", dVar.m());
            jSONArray.put(jSONObject2);
            jSONObject.put("books", jSONArray);
            str = jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    protected boolean isNeedSaveFailedTaskToDisk() {
        return true;
    }
}

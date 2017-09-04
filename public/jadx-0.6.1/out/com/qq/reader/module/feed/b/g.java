package com.qq.reader.module.feed.b;

import com.tencent.android.tpush.common.MessageKey;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* compiled from: FeedOperationModelStyle12 */
public class g extends a {
    private String c;
    private String d;
    private String e;
    private int f;
    private long g;

    public a a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        this.a = jSONObject.optInt("uistyle");
        this.b = jSONObject.optString("positionId");
        if (this.a != 12) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(MessageKey.MSG_CONTENT);
        if (optJSONObject == null) {
            return null;
        }
        a gVar = new g();
        gVar.c = optJSONObject.optString("title");
        gVar.d = optJSONObject.optString(SocialConstants.PARAM_APP_DESC);
        gVar.e = optJSONObject.optString("qurl");
        gVar.f = optJSONObject.optInt("sex");
        gVar.g = optJSONObject.optLong("time");
        gVar.a = this.a;
        gVar.b = this.b;
        return gVar;
    }

    public a a(a aVar) {
        return aVar;
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.e;
    }

    public int d() {
        return this.f;
    }

    public long e() {
        return this.g;
    }
}

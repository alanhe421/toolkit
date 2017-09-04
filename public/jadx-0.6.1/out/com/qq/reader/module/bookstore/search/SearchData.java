package com.qq.reader.module.bookstore.search;

import android.text.TextUtils;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.connect.common.Constants;
import org.json.JSONObject;

public class SearchData extends AbsSearchWords {
    public String id = "";
    public a mExtInfo = new a();
    public b mStatePara = new b();

    public static class a {
        public String a = "";
    }

    public static class b {
        public String a = "";
        public String b = "";
        public String c = "";
        public String d = "";
    }

    public SearchData parseJson(JSONObject jSONObject) {
        this.id = jSONObject.optString("id");
        setKeyWord(jSONObject.optString("title"));
        this.mType = jSONObject.optInt("type");
        if (this.mType != 6 || TextUtils.isEmpty(jSONObject.optString("Newqurl"))) {
            setQurl(jSONObject.optString("qurl"));
        } else {
            setQurl(jSONObject.optString("Newqurl"));
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("ext_info");
        if (optJSONObject != null) {
            this.mExtInfo.a = optJSONObject.optString("author");
        }
        optJSONObject = jSONObject.optJSONObject(s.STATPARAM_KEY);
        if (optJSONObject != null) {
            this.mStatePara.a = optJSONObject.optString("algo_info");
            this.mStatePara.b = optJSONObject.optString(Constants.PARAM_PLATFORM);
            this.mStatePara.c = optJSONObject.optString(s.ORIGIN);
            this.mStatePara.d = optJSONObject.optString("qurl");
        }
        return this;
    }
}

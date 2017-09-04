package com.qq.reader.module.bookstore.qnative.item;

import android.text.Html;
import android.text.TextUtils;
import com.qq.reader.common.utils.ao;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* compiled from: CustomCommentAndReplyItem */
public class n extends l {
    public String u;
    public String v;
    public String w;
    public String x;
    public int y;

    public void parseData(JSONObject jSONObject) {
        super.parseData(jSONObject);
        if (jSONObject != null) {
            this.u = jSONObject.optString("bookName");
            this.v = jSONObject.optString("contextContent");
            if (!TextUtils.isEmpty(this.v)) {
                this.v = Html.fromHtml(this.v).toString();
            }
            this.v = ao.w(this.v);
            this.w = jSONObject.optString("qurl");
            this.x = jSONObject.optString(SocialConstants.PARAM_SOURCE);
            this.y = jSONObject.optInt("commentType", 0);
            this.r = (float) jSONObject.optDouble("score", 0.0d);
        }
    }
}

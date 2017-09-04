package com.qq.reader.module.game.data;

import com.tencent.open.SocialConstants;
import java.io.Serializable;
import org.json.JSONObject;

public class GameTopBannerData implements Serializable {
    public long beginTime;
    public long endTime;
    public long id;
    public String img;
    public String qurl;

    public GameTopBannerData(JSONObject jSONObject) {
        try {
            this.id = jSONObject.optLong("id");
            this.img = jSONObject.optString(SocialConstants.PARAM_IMG_URL);
            this.beginTime = jSONObject.optLong("beginTime");
            this.endTime = jSONObject.optLong("endTime");
            this.qurl = jSONObject.optString("qurl");
        } catch (Exception e) {
        }
    }
}

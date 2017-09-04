package com.qq.reader.module.findhome;

import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* compiled from: FindHomeItem */
public class d extends s {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private int g;
    private a h;

    public void parseData(JSONObject jSONObject) {
        try {
            this.a = jSONObject.optString("imageUrl");
            this.c = jSONObject.optString(SocialConstants.PARAM_URL);
            this.b = jSONObject.optString("title");
            JSONObject optJSONObject = jSONObject.optJSONObject(ComicStoreExclusiveItemCard.NET_AD_ATTR_EXTINFO);
            if (optJSONObject != null) {
                this.d = optJSONObject.optString(SocialConstants.PARAM_APP_DESC);
                this.e = optJSONObject.optString(MessageKey.MSG_ICON);
                this.f = optJSONObject.optString("adPosition");
            }
            this.g = jSONObject.optInt("type");
        } catch (Exception e) {
            c.e("FindHomeItem", e.getMessage());
        }
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public a g() {
        return this.h;
    }

    public void a(a aVar) {
        this.h = aVar;
    }

    public int h() {
        return this.g;
    }
}

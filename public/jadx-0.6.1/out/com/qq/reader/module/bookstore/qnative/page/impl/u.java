package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.AuthorWXInfoBindCard;
import org.json.JSONObject;

/* compiled from: NativeServerAuthorWXBindPage */
public class u extends af {
    public u(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        return e.ab + "?authorId=" + bundle.getString("AUTHORPAGE_KEY_AUTHORID");
    }

    public void b(JSONObject jSONObject) {
        this.p = jSONObject.toString();
        a(jSONObject.optLong("expireTime") * 1000);
        this.o = jSONObject.optLong("pagestamp");
        a authorWXInfoBindCard = new AuthorWXInfoBindCard(this, "AuthorWXInfoBindCard");
        authorWXInfoBindCard.setEventListener(l());
        authorWXInfoBindCard.fillData(jSONObject);
        this.k.add(authorWXInfoBindCard);
    }

    public boolean a() {
        return false;
    }
}

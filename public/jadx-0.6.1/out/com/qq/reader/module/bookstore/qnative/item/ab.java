package com.qq.reader.module.bookstore.qnative.item;

import android.os.Bundle;
import android.view.View;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.feed.card.view.ListenBooklistView;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* compiled from: ListenBookListItem */
public class ab extends z {
    private long a;
    private String b;
    private String c;
    private String d;
    private List<Object> e = new ArrayList();

    public void parseData(JSONObject jSONObject) {
        this.a = jSONObject.optLong("id");
        this.b = jSONObject.optString("title");
        this.c = jSONObject.optString("imageUrl");
        this.d = jSONObject.optString("intro");
        this.l = new c(null);
        Bundle a = this.l.a();
        a.putInt("function_type", 0);
        a.putString("KEY_JUMP_PAGENAME", "webpage");
        String optString = jSONObject.optString(SocialConstants.PARAM_URL);
        if (optString.toLowerCase().startsWith("http://") || optString.toLowerCase().startsWith("https://")) {
            a.putString("com.qq.reader.WebContent", optString);
        } else {
            a.putString("com.qq.reader.WebContent", "/topicV2.html?tid=" + this.a);
        }
        setStatisic(jSONObject, a);
    }

    public String a() {
        return this.b;
    }

    public void a(View view, int i, boolean z) {
        ((ListenBooklistView) view).setBookCollectListItemData(this);
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }
}

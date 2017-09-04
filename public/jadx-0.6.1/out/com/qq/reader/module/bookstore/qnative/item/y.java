package com.qq.reader.module.bookstore.qnative.item;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.JSONObject;

/* compiled from: ListHallOfFameAuthorNameItem */
public class y extends z {
    private String a;
    private String b;

    public void parseData(JSONObject jSONObject) {
        this.a = jSONObject.optString("name");
    }

    public String a() {
        return this.a;
    }

    public void a(a aVar) {
        if (TextUtils.isEmpty(this.b) || "0".equals(this.b)) {
            super.a(aVar);
            return;
        }
        try {
            c.a(aVar.getFromActivity(), String.format("uniteqqreader://nativepage/authors/mainpage?authorId=%s&realname=%s&iconUrl=%s", new Object[]{this.b, this.a, null}), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public void a(String str) {
        this.a = str;
        this.l = new com.qq.reader.module.bookstore.qnative.c(null);
        Bundle a = this.l.a();
        a.putString("LOCAL_STORE_IN_TITLE", a());
        a.putString("KEY_JUMP_PAGENAME", "webpage");
        a.putString("KEY_ACTIONID", a());
        a.putString("com.qq.reader.WebContent", "/search.html?key=" + URLEncoder.encode(a()) + "&from=hall");
    }

    public void a(String str, String str2) {
        this.b = str2;
        this.a = str;
        this.l = new com.qq.reader.module.bookstore.qnative.c(null);
        if (!TextUtils.isEmpty(this.b) && !"0".equals(this.b)) {
            Bundle a = this.l.a();
            a.putString("KEY_ACTION", "sameauthorallbooks");
            a.putString("KEY_ACTIONID", str2);
            a.putString("LOCAL_STORE_IN_TITLE", "全部作品");
        } else if (!TextUtils.isEmpty(this.a)) {
            Bundle a2 = this.l.a();
            a2.putString("KEY_ACTION", "authorbooks");
            try {
                a2.putString("KEY_ACTIONID", URLEncoder.encode(this.a, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            a2.putString("LOCAL_STORE_IN_TITLE", "全部作品");
        }
    }

    public void a(View view, int i, boolean z) {
        ((TextView) ap.a(view, R.id.author_name)).setText(a());
        ap.a(view, R.id.author_divider_line).setVisibility(0);
    }
}

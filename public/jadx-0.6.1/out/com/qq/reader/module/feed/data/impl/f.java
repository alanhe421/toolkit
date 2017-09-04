package com.qq.reader.module.feed.data.impl;

import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.page.impl.af;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: FeedFirstPage */
public class f extends af {
    private List<FeedBaseCard> a = new ArrayList();
    private List<String> b = new ArrayList();
    private JSONObject c = null;

    public f(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        return e.dd + "?sex=" + d.aU(ReaderApplication.getApplicationImp());
    }

    public boolean b() {
        return true;
    }

    public void a(JSONObject jSONObject) {
        int i = 0;
        if (jSONObject != null) {
            try {
                JSONArray optJSONArray = jSONObject.optJSONArray("searchwords");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    if (this.b.size() > 0) {
                        this.b.clear();
                    }
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        this.b.add(optJSONArray.getString(i2));
                    }
                }
                JSONArray optJSONArray2 = jSONObject.optJSONArray("list");
                if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                    while (i < optJSONArray2.length()) {
                        JSONObject optJSONObject = optJSONArray2.optJSONObject(i);
                        if (optJSONObject != null && optJSONObject.optInt("style") == 1) {
                            this.c = optJSONObject;
                            break;
                        }
                        i++;
                    }
                }
                d.F(ReaderApplication.getApplicationImp().getApplicationContext(), jSONObject.optInt("sex"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collection a = c.a(null, jSONObject, l());
        if (this.a.size() > 0) {
            this.a.clear();
        }
        this.a.addAll(a);
    }

    public List<FeedBaseCard> x() {
        return this.a;
    }

    public List<String> y() {
        return this.b;
    }

    public JSONObject z() {
        return this.c;
    }
}

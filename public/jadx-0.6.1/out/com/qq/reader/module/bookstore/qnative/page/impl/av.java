package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import android.text.TextUtils;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.item.ae;
import com.qq.reader.module.bookstore.qnative.page.b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeServerRankBoardPage */
public class av extends af {
    private List<ae> a = new ArrayList();

    public List<ae> x() {
        return this.a;
    }

    public av(Bundle bundle) {
        super(bundle);
    }

    public void a(b bVar) {
        super.a(bVar);
        try {
            if (!TextUtils.isEmpty(this.p)) {
                JSONArray optJSONArray = new JSONObject(this.p).optJSONArray("rank");
                if (optJSONArray != null) {
                    int length = optJSONArray.length();
                    x().clear();
                    if (length > 0) {
                        for (int i = 0; i < length; i++) {
                            if (optJSONArray.get(i) != null) {
                                JSONObject jSONObject = optJSONArray.getJSONObject(i);
                                ae aeVar = new ae();
                                aeVar.parseData(jSONObject);
                                a(aeVar);
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void a(ae aeVar) {
        this.a.add(aeVar);
    }

    public int n() {
        return this.i.hashCode();
    }

    public void b(JSONObject jSONObject) {
        super.b(jSONObject);
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("rank");
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                x().clear();
                if (length > 0) {
                    for (int i = 0; i < length; i++) {
                        if (optJSONArray.get(i) != null) {
                            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                            ae aeVar = new ae();
                            aeVar.parseData(jSONObject2);
                            a(aeVar);
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String a(Bundle bundle) {
        return new c(this.f).a(e.a, new StringBuffer("queryOperation?").toString());
    }
}

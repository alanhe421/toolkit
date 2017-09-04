package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import android.text.TextUtils;
import com.qq.reader.common.c.b;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforClassify;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforClassify_1;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfClassify */
public class ag extends af {
    private JSONObject a;
    private String b;

    public ag(Bundle bundle) {
        super(bundle);
    }

    public void b(JSONObject jSONObject) {
        this.a = jSONObject;
        super.b(jSONObject);
    }

    public String a(Bundle bundle) {
        this.b = bundle.getString("KEY_ACTION");
        if (TextUtils.isEmpty(this.b)) {
            bundle.putString("KEY_ACTION", "categoryV3");
        }
        return new c(bundle).b(new StringBuffer("listDispatch?").toString());
    }

    public Class c() {
        if (b.f == 0) {
            return NativePageFragmentforClassify.class;
        }
        return NativePageFragmentforClassify_1.class;
    }

    public JSONObject x() {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(this.p);
        } catch (JSONException e) {
            e.printStackTrace();
            jSONObject = null;
        }
        return jSONObject.optJSONObject("info");
    }

    public String g() {
        return super.g();
    }

    public JSONObject y() {
        return this.a;
    }

    public String z() {
        return this.b;
    }
}

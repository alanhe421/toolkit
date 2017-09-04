package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.impl.ObtainWelfareListCard;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentForObtainWelfare;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeLocalObtainWelfarePage */
public class j extends af {
    public j(Bundle bundle) {
        super(bundle);
    }

    public void b(JSONObject jSONObject) {
        super.b(jSONObject);
        JSONArray optJSONArray = jSONObject.optJSONArray("welfareList");
        if (optJSONArray != null) {
            int i = 0;
            while (i < optJSONArray.length()) {
                try {
                    JSONObject jSONObject2 = (JSONObject) optJSONArray.get(i);
                    if (jSONObject2 != null) {
                        ObtainWelfareListCard obtainWelfareListCard = new ObtainWelfareListCard(this, ObtainWelfareListCard.class.getSimpleName());
                        obtainWelfareListCard.fillData(jSONObject2);
                        this.k.add(obtainWelfareListCard);
                        this.l.put(i + "", obtainWelfareListCard);
                        obtainWelfareListCard.setEventListener(l());
                        obtainWelfareListCard.setIsShowBottomDivider(true);
                    }
                    i++;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        if (this.k.size() > 0) {
            ((ObtainWelfareListCard) this.k.get(this.k.size() - 1)).setIsShowBottomDivider(false);
        }
    }

    public String a(Bundle bundle) {
        return new c(bundle).a(e.di, new StringBuilder().toString());
    }

    public Class c() {
        return NativePageFragmentForObtainWelfare.class;
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return true;
    }
}

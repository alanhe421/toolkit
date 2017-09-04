package com.qq.reader.module.bookstore.charge;

import android.os.Bundle;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.charge.card.MonthlyAutoPayCard;
import com.qq.reader.module.bookstore.charge.card.MonthlyChargeAdvTextCard;
import com.qq.reader.module.bookstore.charge.card.MonthlyChargeItemCard;
import com.qq.reader.module.bookstore.charge.card.MonthlyChargeProfileCard;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.impl.af;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfChargeOpenVIP */
public class d extends af {
    public int a;
    public int b;
    public int c;
    public int d;
    @Deprecated
    public int e = 1;

    public d(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        return e.dl;
    }

    public void b(JSONObject jSONObject) {
        this.a = jSONObject.optInt("balance");
        this.d = jSONObject.optInt("switch");
        Object optJSONObject = jSONObject.optJSONObject("userMonthInfo");
        MonthlyChargeProfileCard monthlyChargeProfileCard = new MonthlyChargeProfileCard(this, "MonthlyChargeProfileCard");
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        monthlyChargeProfileCard.fillData(optJSONObject);
        monthlyChargeProfileCard.setEventListener(l());
        monthlyChargeProfileCard.setHelpCenter(jSONObject.optString("helpCenterUrl"));
        this.k.add(monthlyChargeProfileCard);
        this.l.put(monthlyChargeProfileCard.getCardId(), monthlyChargeProfileCard);
        this.e = monthlyChargeProfileCard.getGfrom();
        this.b = monthlyChargeProfileCard.getStatus();
        this.c = monthlyChargeProfileCard.getQQOpenType();
        JSONObject optJSONObject2 = jSONObject.optJSONObject("ad");
        if (optJSONObject2 != null) {
            MonthlyChargeAdvTextCard monthlyChargeAdvTextCard = new MonthlyChargeAdvTextCard(this, "MonthlyChargeAdvTextCard");
            monthlyChargeAdvTextCard.fillData(optJSONObject2);
            monthlyChargeAdvTextCard.setEventListener(l());
            this.k.add(monthlyChargeAdvTextCard);
            this.l.put(monthlyChargeAdvTextCard.getCardId(), monthlyChargeAdvTextCard);
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("config");
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                optJSONObject2 = (JSONObject) optJSONArray.opt(i);
                if (optJSONObject2 != null) {
                    MonthlyChargeItemCard monthlyChargeItemCard = new MonthlyChargeItemCard(this, "MonthlyChargeItemCard");
                    monthlyChargeItemCard.fillData(optJSONObject2);
                    monthlyChargeItemCard.setEventListener(l());
                    this.k.add(monthlyChargeItemCard);
                    this.l.put(monthlyChargeItemCard.getCardId(), monthlyChargeItemCard);
                }
            }
        }
        MonthlyAutoPayCard monthlyAutoPayCard = new MonthlyAutoPayCard(this, "MonthlyAutoPayCard");
        monthlyAutoPayCard.fillData(jSONObject);
        monthlyAutoPayCard.setEventListener(l());
        this.k.add(monthlyAutoPayCard);
        this.l.put(monthlyAutoPayCard.getCardId(), monthlyAutoPayCard);
    }

    public void a(b bVar) {
        super.a(bVar);
        this.a = ((d) bVar).a;
        this.b = ((d) bVar).b;
        this.c = ((d) bVar).c;
        this.d = ((d) bVar).d;
        this.e = ((d) bVar).e;
    }

    public boolean a() {
        return false;
    }
}

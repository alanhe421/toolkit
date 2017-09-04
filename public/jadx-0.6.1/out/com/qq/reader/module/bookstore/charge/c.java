package com.qq.reader.module.bookstore.charge;

import android.os.Bundle;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.charge.card.ChargeAdvCard;
import com.qq.reader.module.bookstore.charge.card.ChargeAdvTextCard;
import com.qq.reader.module.bookstore.charge.card.ChargeItemCard;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.impl.af;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfChargeBookCoin */
public class c extends af {
    public List<a> a;
    public int b = this.c;
    private int c = 100;

    public c(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        String str = e.dk;
        int i = bundle.getInt("charge_prevalue", 0);
        if (i > 0) {
            return str + "?prevalue=" + i;
        }
        return str;
    }

    public void b(JSONObject jSONObject) {
        boolean z = false;
        int optInt = jSONObject.optInt("code");
        if (optInt != 0) {
            throw new IllegalStateException("NativeServerPageOfChargeBookCoin fillData with code == " + optInt + " we will load the default grades");
        }
        JSONArray optJSONArray;
        ChargeAdvCard chargeAdvCard = new ChargeAdvCard(this, "ChargeAdvCard");
        chargeAdvCard.fillData(jSONObject);
        chargeAdvCard.setEventListener(l());
        this.k.add(chargeAdvCard);
        this.l.put(chargeAdvCard.getCardId(), chargeAdvCard);
        JSONObject optJSONObject = jSONObject.optJSONObject("txtad");
        if (optJSONObject != null) {
            ChargeAdvTextCard chargeAdvTextCard = new ChargeAdvTextCard(this, "ChargeAdvTextCard");
            chargeAdvTextCard.fillData(optJSONObject);
            chargeAdvTextCard.setEventListener(l());
            this.k.add(chargeAdvTextCard);
            this.l.put(chargeAdvTextCard.getCardId(), chargeAdvTextCard);
        }
        Collection arrayList = new ArrayList();
        JSONArray optJSONArray2 = jSONObject.optJSONArray("rechargeGradeList");
        if (optJSONArray2 == null || optJSONArray2.length() == 0) {
            optJSONArray = d().optJSONArray("rechargeGradeList");
        } else {
            optJSONArray = optJSONArray2;
        }
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            int i = 0;
            while (i < length) {
                boolean z2;
                optJSONObject = (JSONObject) optJSONArray.opt(i);
                if (optJSONObject != null) {
                    a aVar = new a();
                    aVar.a(optJSONObject);
                    if (aVar.a()) {
                        ChargeItemCard chargeItemCard = new ChargeItemCard(this, "ChargeItemCard");
                        chargeItemCard.setChargeItem(aVar);
                        chargeItemCard.setEventListener(l());
                        if (i == length - 1) {
                            chargeItemCard.setHideDivider(true);
                        }
                        this.k.add(chargeItemCard);
                        this.l.put(chargeItemCard.getCardId(), chargeItemCard);
                        z2 = true;
                    } else {
                        z2 = z;
                    }
                    arrayList.add(aVar);
                } else {
                    z2 = z;
                }
                i++;
                z = z2;
            }
        }
        if (!z) {
            x();
        }
        this.b = jSONObject.optInt("proportion", this.c);
        if (arrayList != null && arrayList.size() > 0) {
            this.a = new ArrayList();
            this.a.addAll(arrayList);
        }
    }

    private void x() {
        try {
            JSONArray optJSONArray = d().optJSONArray("rechargeGradeList");
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = (JSONObject) optJSONArray.opt(i);
                if (jSONObject != null) {
                    a aVar = new a();
                    aVar.a(jSONObject);
                    if (aVar.a()) {
                        ChargeItemCard chargeItemCard = new ChargeItemCard(this, "ChargeItemCard");
                        chargeItemCard.setChargeItem(aVar);
                        chargeItemCard.setEventListener(l());
                        if (i == length - 1) {
                            chargeItemCard.setHideDivider(true);
                        }
                        this.k.add(chargeItemCard);
                        this.l.put(chargeItemCard.getCardId(), chargeItemCard);
                    }
                }
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("NativeServerPageOfChargeBookCoin", e.getMessage());
        }
    }

    public boolean a() {
        return false;
    }

    public void a(b bVar) {
        super.a(bVar);
        this.a = ((c) bVar).a;
        this.b = ((c) bVar).b;
    }

    public static c a(a aVar) {
        c cVar = new c(new Bundle());
        cVar.b(aVar);
        cVar.b(d());
        return cVar;
    }

    public static JSONObject d() {
        try {
            return new JSONObject("{\"isLogin\":true,\"proportion\":100,\"sid\":\"\",\"guestcheckcode\":-1,\"guin\":null,\"code\":\"0\",\"prefer\":1,\"rechargeGradeList\":[{\"id\":1,\"isShow\":1,\"money\":10,\"number\":1000,\"privilegeInfo\":\"\",\"recommendInfo\":\"\"},{\"id\":2,\"isShow\":1,\"money\":20,\"number\":2000,\"privilegeInfo\":\"\",\"recommendInfo\":\"\"},{\"id\":3,\"isShow\":1,\"money\":50,\"number\":5000,\"privilegeInfo\":\"\",\"recommendInfo\":\"\"},{\"id\":4,\"isShow\":1,\"money\":100,\"number\":10000,\"privilegeInfo\":\"\",\"recommendInfo\":\"\"},{\"id\":5,\"isShow\":1,\"money\":500,\"number\":50000,\"privilegeInfo\":\"\",\"recommendInfo\":\"\"}],\"channel\":\"00000\",\"qq\":null,\"version\":null}");
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("", e.getMessage());
            return null;
        }
    }
}

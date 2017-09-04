package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import android.text.TextUtils;
import com.qq.reader.common.utils.an;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.a;
import com.qq.reader.module.bookstore.qnative.page.d;
import com.qq.reader.module.bookstore.qnative.page.d.b;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeLocalGeneralPage */
public class h extends a {
    private d a;
    private HashSet<String> b = new HashSet();

    public h(Bundle bundle, String str) {
        this.f = bundle;
        this.i = str;
    }

    public void b(JSONObject jSONObject) {
        int i = 0;
        for (String optJSONObject : new String[]{"ads", "cols", "rank", "limitFreeBooks", "vipcols", "jzqmcols", "rentcids", "realTimeRec"}) {
            com.qq.reader.module.bookstore.qnative.card.a aVar;
            JSONObject optJSONObject2 = jSONObject.optJSONObject(optJSONObject);
            if (optJSONObject2 != null) {
                Iterator keys = optJSONObject2.keys();
                while (keys.hasNext()) {
                    aVar = (com.qq.reader.module.bookstore.qnative.card.a) this.l.get((String) keys.next());
                    if (aVar != null) {
                        JSONObject optJSONObject3 = optJSONObject2.optJSONObject(aVar.getCardId());
                        if (optJSONObject3 != null) {
                            aVar.fillData(optJSONObject3);
                        } else {
                            JSONArray optJSONArray = optJSONObject2.optJSONArray(aVar.getCardId());
                            if (optJSONArray != null) {
                                aVar.fillData(optJSONArray);
                            }
                        }
                    }
                }
            }
        }
        for (String str : new String[]{ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, "rec", "strRec", "rank", "PayMonthGuide", "topicinfo", "baginfo", "recTag"}) {
            aVar = (com.qq.reader.module.bookstore.qnative.card.a) this.l.get(str);
            if (aVar != null) {
                JSONObject optJSONObject4 = jSONObject.optJSONObject(str);
                if (optJSONObject4 != null) {
                    aVar.fillData(optJSONObject4);
                }
                JSONArray optJSONArray2 = jSONObject.optJSONArray(str);
                if (optJSONArray2 != null) {
                    aVar.fillData(optJSONArray2);
                }
            }
        }
        String[] strArr = new String[]{"classifyEntrance", "finishCount"};
        int length = strArr.length;
        while (i < length) {
            aVar = (com.qq.reader.module.bookstore.qnative.card.a) this.l.get(strArr[i]);
            if (aVar != null) {
                aVar.fillData(jSONObject);
            }
            i++;
        }
    }

    public String a(List<com.qq.reader.module.bookstore.qnative.card.a> list) {
        c cVar = new c(null);
        Bundle a = cVar.a();
        ArrayList arrayList = new ArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        StringBuilder stringBuilder3 = new StringBuilder();
        StringBuilder stringBuilder4 = new StringBuilder();
        StringBuilder stringBuilder5 = new StringBuilder();
        StringBuilder stringBuilder6 = new StringBuilder();
        StringBuilder stringBuilder7 = new StringBuilder();
        StringBuilder stringBuilder8 = new StringBuilder();
        for (com.qq.reader.module.bookstore.qnative.card.a aVar : list) {
            if ("Adv".equals(aVar.getType())) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(",");
                }
                stringBuilder.append(aVar.getCardId());
            } else if ("Col".equals(aVar.getType())) {
                if (stringBuilder2.length() > 0) {
                    stringBuilder2.append(",");
                }
                stringBuilder2.append(aVar.getCardId());
            } else if ("strRec".equals(aVar.getType())) {
                a.putString("URL_BUILD_PERE_ISSTRRECFLAG", aVar.getValue());
            } else if ("rec".equals(aVar.getType())) {
                a.putString("URL_BUILD_PERE_ISRECFLAG", aVar.getValue());
            } else if ("Free".equals(aVar.getType())) {
                if (stringBuilder7.length() > 0) {
                    stringBuilder7.append(",");
                }
                stringBuilder7.append(aVar.getCardId());
            } else if ("Collect".equals(aVar.getType())) {
                a.putString("URL_BUILD_PERE_ISJZQMCIDS", a(a, "URL_BUILD_PERE_ISJZQMCIDS", aVar.getCardId()));
            } else if (ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE.equals(aVar.getType())) {
                a.putString("URL_BUILD_PERE_CATEGORY", String.valueOf(aVar.getSex()));
            } else if ("rank".equals(aVar.getType())) {
                a.putString("URL_BUILD_PERE_RANK", aVar.getValue());
            } else if ("topicinfo".equals(aVar.getType())) {
                a.putString("URL_BUILD_PERE_BOOK_COLLECT", aVar.getValue());
            } else if ("baginfo".equals(aVar.getType())) {
                a.putString("URL_BUILD_PERE_BOOK_PACK", aVar.getValue());
            } else if ("Lcol".equals(aVar.getType())) {
                if (stringBuilder3.length() > 0) {
                    stringBuilder3.append(",");
                }
                stringBuilder3.append(aVar.getCardId());
            } else if ("recTag".equalsIgnoreCase(aVar.getType())) {
                a.putString("URL_BUILD_PERE_TAG_REC", aVar.getValue());
            } else if ("finishCount".equalsIgnoreCase(aVar.getType())) {
                a.putString("URL_BUILD_PERE_FINISH_COUNT", aVar.getValue());
            } else if ("DiscountBuy".equalsIgnoreCase(aVar.getType())) {
                if (stringBuilder4.length() > 0) {
                    stringBuilder4.append(",");
                }
                stringBuilder4.append(aVar.getCardId());
                if (stringBuilder2.length() > 0) {
                    stringBuilder2.append(",");
                }
                stringBuilder2.append(aVar.getCardId());
            } else if ("Rent".equalsIgnoreCase(aVar.getType())) {
                if (stringBuilder5.length() > 0) {
                    stringBuilder5.append(",");
                }
                stringBuilder5.append(aVar.getCardId());
            } else if ("vipcols".equalsIgnoreCase(aVar.getType())) {
                if (stringBuilder6.length() > 0) {
                    stringBuilder6.append(",");
                }
                stringBuilder6.append(aVar.getCardId());
            } else if ("realTimeRec".equals(aVar.getType())) {
                if (stringBuilder8.length() > 0) {
                    stringBuilder8.append(",");
                }
                stringBuilder8.append(aVar.getCardId());
            }
        }
        a.putString("URL_BUILD_PERE_COLS", stringBuilder2.toString());
        a.putString("URL_BUILD_PERE_ADVS", stringBuilder.toString());
        a.putString("URL_BUILD_PERE_LCOLS", stringBuilder3.toString());
        a.putString("URL_BUILD_PERE_DISCOUNT_BUY", stringBuilder4.toString());
        a.putString("URL_BUILD_PERE_RENT", stringBuilder5.toString());
        a.putString("URL_BUILD_PERE_ISLMTCIDS", stringBuilder7.toString());
        a.putString("URL_BUILD_VIP_FREE", stringBuilder6.toString());
        a.putString("URL_BUILD_PERE_REALTIME_REC", stringBuilder8.toString());
        if (this.m != null) {
            a.putString(s.STATPARAM_KEY, this.m);
        }
        if (this.f != null) {
            String string = this.f.getString("bids");
            Object string2 = this.f.getString("bidsincid");
            Object string3 = this.f.getString("cidincate");
            this.f.putString("bids", "");
            this.f.putString("bidsincid", "");
            this.f.putString("cidincate", "");
            this.f.putBoolean("SECONDARY_PAGE_EXTRA_KEY_HAS_FIX_BIDS", false);
            if (!TextUtils.isEmpty(string)) {
                a.putString("bids", string);
                this.f.putBoolean("SECONDARY_PAGE_EXTRA_KEY_HAS_FIX_BIDS", true);
                this.f.putString("bids", string);
            }
            if (!TextUtils.isEmpty(string2)) {
                a.putString("bidsincid", string2);
            }
            if (!TextUtils.isEmpty(string3)) {
                a.putString("cidincate", string3);
            }
        }
        return cVar.b("queryOperation?");
    }

    private String a(Bundle bundle, String str, String str2) {
        StringBuilder stringBuilder;
        String string = bundle.getString(str);
        if (string == null || string.length() <= 0) {
            stringBuilder = new StringBuilder();
        } else {
            stringBuilder = new StringBuilder(string);
            stringBuilder.append(",");
        }
        stringBuilder.append(str2);
        return stringBuilder.toString();
    }

    public boolean a() {
        return false;
    }

    public void a(String str) {
        super.a(str);
        x();
    }

    private void x() {
        if (this.b.size() == 0) {
            this.b.add("End_Book_Boy");
            this.b.add("End_Book_Girl");
            this.b.add("Limit_Free_Boy");
            this.b.add("Limit_Free_Girl");
            this.b.add("Limit_Free_Publish");
            this.b.add("Boutique_Zone_Boy");
            this.b.add("Boutique_Zone_Girl");
            this.b.add("Boutique_Zone_Publish");
            this.b.add("PayMonth_Boy");
            this.b.add("PayMonth_Girl");
            this.b.add("PayMonth_Publish");
        }
        if (this.b.contains(this.i)) {
            JSONObject jSONObject = null;
            try {
                if (this.a == null) {
                    if (this.i.equals("End_Book_Boy") || this.i.equals("End_Book_Girl")) {
                        jSONObject = new JSONObject(an.a().a(6));
                    } else if (this.i.equals("Limit_Free_Boy") || this.i.equals("Limit_Free_Girl") || this.i.equals("Limit_Free_Publish")) {
                        jSONObject = new JSONObject(an.a().a(7));
                    } else if (this.i.equals("Boutique_Zone_Boy") || this.i.equals("Boutique_Zone_Girl") || this.i.equals("Boutique_Zone_Publish")) {
                        jSONObject = new JSONObject(an.a().a(8));
                    } else if (this.i.equals("PayMonth_Boy") || this.i.equals("PayMonth_Girl") || this.i.equals("PayMonth_Publish")) {
                        jSONObject = new JSONObject(an.a().a(9));
                    }
                    this.a = new d();
                    this.a.a(jSONObject);
                }
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
            }
            if (this.f != null) {
                String string = this.f.getString("KEY_ACTIONTAG");
                List g = this.a.g();
                for (int i = 0; i < g.size(); i++) {
                    b bVar = (b) g.get(i);
                    if (bVar != null) {
                        bVar.c = bVar.b.equals(string);
                    }
                }
            }
            this.n = this.a;
        }
    }
}

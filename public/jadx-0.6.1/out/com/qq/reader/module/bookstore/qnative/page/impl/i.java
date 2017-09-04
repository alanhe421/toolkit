package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.a;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCardCommon;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCardHallOfFameAuthorDetail;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCardHallOfFameAuthorName;
import com.qq.reader.module.bookstore.qnative.item.r;
import com.qq.reader.module.bookstore.qnative.item.x;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeLocalHallOfFamePage */
public class i extends af {
    private String a = null;
    private List<r> b = new ArrayList();

    public i(Bundle bundle) {
        super(bundle);
    }

    public List<r> x() {
        return this.b;
    }

    protected void a(r rVar) {
        this.b.add(rVar);
    }

    public void a(b bVar) {
        super.a(bVar);
        if (!this.p.equalsIgnoreCase("")) {
            try {
                d(new JSONObject(this.p));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public int n() {
        return this.i.hashCode();
    }

    public void d(JSONObject jSONObject) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE);
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                x().clear();
                if (length > 0) {
                    for (int i = 0; i < length; i++) {
                        if (optJSONArray.get(i) != null) {
                            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                            r rVar = new r();
                            rVar.parseData(jSONObject2);
                            a(rVar);
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void b(JSONObject jSONObject) {
        super.b(jSONObject);
        d(jSONObject);
        JSONObject optJSONObject = jSONObject.optJSONObject("hall");
        if (optJSONObject != null) {
            this.a = optJSONObject.optString("intro");
            this.o = jSONObject.optLong("pagestamp");
            if (optJSONObject.optJSONArray("flames") != null) {
                ListCardCommon listCardHallOfFameAuthorDetail = new ListCardHallOfFameAuthorDetail(this, "authorList");
                listCardHallOfFameAuthorDetail.setEventListener(l());
                listCardHallOfFameAuthorDetail.fillData(optJSONObject);
                this.k.add(listCardHallOfFameAuthorDetail);
                this.l.put(listCardHallOfFameAuthorDetail.getCardId(), listCardHallOfFameAuthorDetail);
            }
        }
        a(jSONObject.optLong("expireTime", 0) * 1000);
        if (jSONObject.optJSONArray("names") != null) {
            ListCardCommon listCardHallOfFameAuthorName = new ListCardHallOfFameAuthorName(this, "authorNameList");
            listCardHallOfFameAuthorName.setEventListener(l());
            listCardHallOfFameAuthorName.fillData(jSONObject);
            this.k.add(listCardHallOfFameAuthorName);
            this.l.put(listCardHallOfFameAuthorName.getCardId(), listCardHallOfFameAuthorName);
        }
    }

    public boolean addMore(a aVar) {
        if (aVar instanceof af) {
            af afVar = (af) aVar;
            List m = afVar.m();
            if (((com.qq.reader.module.bookstore.qnative.card.a) m.get(0)).getItemList().get(0) instanceof x) {
                if ("".equalsIgnoreCase(((x) ((com.qq.reader.module.bookstore.qnative.card.a) m.get(0)).getItemList().get(0)).b())) {
                    ((com.qq.reader.module.bookstore.qnative.card.a) afVar.m().get(0)).getItemList().remove(0);
                }
            }
        }
        return super.addMore(aVar);
    }

    public String a(Bundle bundle) {
        c cVar = new c(this.f);
        StringBuffer stringBuffer = new StringBuffer("listDispatch?");
        stringBuffer.append("action=hall");
        return cVar.b(stringBuffer.toString());
    }
}

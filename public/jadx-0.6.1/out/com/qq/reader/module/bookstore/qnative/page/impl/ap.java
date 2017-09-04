package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.search.ISearchParamCollection;
import com.qq.reader.module.bookstore.search.SearchTabInfo;
import com.qq.reader.module.bookstore.search.card.SearchBaseCard;
import com.qq.reader.module.bookstore.search.card.SearchResultCountersignCard;
import com.qq.reader.module.bookstore.search.h;
import com.tencent.mm.performance.WxPerformanceHandle;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfSearch */
public class ap extends af {
    public static String s = "selectList";
    public ArrayList<String> a = new ArrayList();
    public boolean b = false;
    public int c;
    public int d;
    public int e = -1;
    public SearchTabInfo t;
    public String u;
    public int v;
    public int w;

    public ap(Bundle bundle) {
        super(bundle);
        this.o = (long) bundle.getInt("searchpageNO", 0);
        this.c = bundle.getInt("searchstate", 0);
        this.u = URLDecoder.decode(bundle.getString("searchkey"));
    }

    public String a(Bundle bundle) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ao.a((ISearchParamCollection) bundle.getSerializable("searchParamSearchMode")).getSearchProtocolURL());
        stringBuilder.append("key=").append(bundle.getString("searchkey"));
        try {
            JSONObject jSONObject = new JSONObject(bundle.getString("searchParams"));
            stringBuilder.append("&actionTag=").append(jSONObject.optString("actionTag"));
            stringBuilder.append("&actionId=").append(jSONObject.optString("actionId"));
        } catch (Exception e) {
            c.e("NativeServerPageOfSearch", e.getMessage());
        }
        stringBuilder.append("&n=").append(10);
        stringBuilder.append("&start=").append(bundle.getInt("nextstart", 0));
        stringBuilder.append("&needNoice=").append(1);
        stringBuilder.append("&needDirect=").append(1);
        stringBuilder.append("&needTopic=").append(1);
        stringBuilder.append("&needToplist=").append(1);
        stringBuilder.append("&needArea=").append(1);
        stringBuilder.append("&needRelate=").append(1);
        return stringBuilder.toString();
    }

    public void a(b bVar) {
        super.a(bVar);
        this.a = ((ap) bVar).a;
        this.w = ((ap) bVar).w;
        this.b = ((ap) bVar).b;
        this.t = ((ap) bVar).t;
        this.d = ((ap) bVar).d;
        this.v = ((ap) bVar).v;
        this.e = ((ap) bVar).e;
        this.c = ((ap) bVar).c;
    }

    public void b(JSONObject jSONObject) {
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("alarm");
            if (optJSONObject != null) {
                SearchBaseCard searchResultCountersignCard = new SearchResultCountersignCard(this, "Countersign");
                searchResultCountersignCard.setSearchKey(this.u);
                searchResultCountersignCard.fillData(optJSONObject);
                searchResultCountersignCard.setEventListener(l());
                searchResultCountersignCard.setPageNo((int) this.o);
                this.k.add(searchResultCountersignCard);
                this.l.put(searchResultCountersignCard.getCardId(), searchResultCountersignCard);
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("cardlist");
            if (optJSONArray != null) {
                List a = com.qq.reader.module.bookstore.search.card.b.a(this, optJSONArray, this.u);
                int size = a.size();
                for (int i = 0; i < size; i++) {
                    SearchBaseCard searchBaseCard = (SearchBaseCard) a.get(i);
                    searchBaseCard.setEventListener(l());
                    searchBaseCard.setPageNo((int) this.o);
                    this.k.add(searchBaseCard);
                    this.l.put(searchBaseCard.getCardId(), searchBaseCard);
                }
                this.b = optJSONArray.length() >= 10;
            }
            if (this.k.size() > 0) {
                this.e = ((SearchBaseCard) this.k.get(0)).mHittype;
            }
            optJSONObject = jSONObject.optJSONObject("relateRetList");
            if (optJSONObject != null) {
                this.w = optJSONObject.optInt("dispType");
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("relateWords");
                if (optJSONArray2 != null) {
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        this.a.add(optJSONArray2.optString(i2));
                    }
                }
            }
            this.d = (int) Math.ceil(((double) jSONObject.optInt("estimation")) / 10.0d);
            optJSONObject = jSONObject.optJSONObject(s);
            if (this.c == 1 || optJSONObject == null || (optJSONObject.optJSONArray("catel3") == null && optJSONObject.optJSONArray(WxPerformanceHandle.MESSAGE_TAG) == null)) {
                this.t = null;
            } else {
                this.t = h.a(optJSONObject, "search/default_search_tab_info.txt");
            }
            this.v = jSONObject.optInt("nextstart");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean s() {
        return this.b;
    }

    public boolean addMore(a aVar) {
        ap apVar = (ap) aVar;
        this.k.addAll(apVar.k);
        this.l.putAll(apVar.l);
        this.b = apVar.b;
        this.o = apVar.o;
        this.v = apVar.v;
        return true;
    }

    public boolean a() {
        return false;
    }
}

package com.qq.reader.module.rookie.a;

import android.text.TextUtils;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.reader.module.rookie.presenter.a;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: RookieGiftCache */
public class c {
    private int a;
    private int b = 0;
    private int c = 0;
    private HashMap<String, a> d = new HashMap();

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public String a(String str) {
        return ((a) this.d.get(str)).a();
    }

    public String b(String str) {
        return ((a) this.d.get(str)).d();
    }

    public HashMap<String, a> d() {
        return this.d;
    }

    public void a(JSONObject jSONObject) throws Exception {
        this.a = jSONObject.optInt("giftid");
        if (jSONObject.optInt("isunlocked") == 1) {
            this.b |= 1;
        }
        if (jSONObject.optInt("isobtained") == 1) {
            this.b |= 2;
        }
        this.c = jSONObject.optInt("dialogtype");
        JSONArray optJSONArray = jSONObject.optJSONArray("displaydetail");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = (JSONObject) optJSONArray.get(i);
                String optString = jSONObject2.optString("pos");
                jSONObject2 = jSONObject2.optJSONObject("rule");
                if (jSONObject2 != null) {
                    a aVar = new a();
                    aVar.a(jSONObject2);
                    this.d.put(optString, aVar);
                }
            }
        }
    }

    public void a(c cVar) {
        this.b = cVar.b;
        this.c = cVar.c;
        for (Entry entry : cVar.d.entrySet()) {
            String str = (String) entry.getKey();
            a aVar = (a) entry.getValue();
            aVar.a = ((a) this.d.get(str)).a;
            aVar.b = ((a) this.d.get(str)).b;
        }
        this.d = cVar.d;
    }

    public String e() {
        JSONArray jSONArray = new JSONArray();
        for (Entry entry : this.d.entrySet()) {
            String str = (String) entry.getKey();
            a aVar = (a) entry.getValue();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("pos", str);
                jSONObject.put(ComicStoreExclusiveItemCard.NET_AD_ATTR_COUNT, aVar.a);
                jSONObject.put("bids", aVar.b);
                jSONArray.put(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONArray.toString();
    }

    public void a(int i) {
        this.b = i;
    }

    public void c(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                    String optString = jSONObject.optString("pos");
                    int optInt = jSONObject.optInt(ComicStoreExclusiveItemCard.NET_AD_ATTR_COUNT);
                    String optString2 = jSONObject.optString("bids");
                    ((a) this.d.get(optString)).a = optInt;
                    ((a) this.d.get(optString)).b = optString2;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean f() {
        boolean z = (this.b & 2) == 0 && (this.b & 1) != 0;
        a.a().a("该礼物是否有效 有效必须已解锁且未领取:state = " + this.b + " result = " + z);
        return z;
    }

    public void a(boolean z) {
        if (z) {
            this.b |= 2;
        }
    }

    public String toString() {
        return "RookieGiftCache{id=" + this.a + ", status=" + this.b + ", dialogType=" + this.c + ", ruleList=" + this.d + '}';
    }
}

package com.qq.reader.module.bookstore.search;

import com.qq.reader.common.utils.ao.d;
import com.qq.reader.module.bookstore.search.SearchTabInfo.a;
import com.qq.reader.module.bookstore.search.SearchTabInfo.b;
import com.qq.reader.module.bookstore.search.SearchTabInfo.c;
import com.tencent.mm.performance.WxPerformanceHandle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SearchTabDefDataCreater */
public class h {
    public static b a(JSONObject jSONObject) {
        b bVar = new b();
        bVar.a = jSONObject.optInt("id");
        bVar.c = jSONObject.optInt("subId");
        bVar.b = jSONObject.optString("tips");
        return bVar;
    }

    public static c b(JSONObject jSONObject) {
        c cVar = new c();
        try {
            cVar.g = jSONObject.optBoolean("equalWidth");
            cVar.a = jSONObject.optInt("defaultSelectedId");
            cVar.c = jSONObject.optBoolean("fold");
            cVar.d = jSONObject.optInt("maxChecked");
            cVar.f = jSONObject.optInt("minChecked");
            cVar.b = jSONObject.optInt("maxLines");
            cVar.e = jSONObject.optString("subTitle");
            cVar.h = jSONObject.optInt("subId");
            JSONArray optJSONArray = jSONObject.optJSONArray("mTipTagsLevel3");
            int i = 0;
            while (optJSONArray != null && i < optJSONArray.length()) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    b a = a(optJSONObject);
                    a.c = cVar.h;
                    cVar.i.add(a);
                }
                i++;
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e(h.class.getSimpleName(), "parse tag fail");
        }
        return cVar;
    }

    public static a c(JSONObject jSONObject) {
        a aVar = new a();
        aVar.a = jSONObject.optBoolean("isSelected");
        aVar.c = jSONObject.optInt("mListType");
        aVar.d = jSONObject.optString("title");
        aVar.e = jSONObject.optInt("actionId");
        aVar.f = jSONObject.optInt("subId");
        JSONArray optJSONArray = jSONObject.optJSONArray("tagsLevel2");
        int i = 0;
        while (optJSONArray != null && i < optJSONArray.length()) {
            aVar.b.add(b(optJSONArray.optJSONObject(i)));
            i++;
        }
        return aVar;
    }

    public static SearchTabInfo d(JSONObject jSONObject) {
        SearchTabInfo searchTabInfo = new SearchTabInfo();
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("searchActionIdsLevel1");
            int i = 0;
            while (optJSONArray != null && i < optJSONArray.length()) {
                searchTabInfo.a.add(c(optJSONArray.optJSONObject(i)));
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchTabInfo;
    }

    public static JSONObject a(String str) {
        try {
            return new JSONObject(d.a(str));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static JSONObject b(JSONObject jSONObject, String str) {
        int i = 0;
        if (jSONObject == null) {
            return a(str);
        }
        JSONObject a = a(str);
        try {
            JSONArray jSONArray = a.getJSONArray("searchActionIdsLevel1").getJSONObject(2).getJSONArray("tagsLevel2");
            JSONArray jSONArray2 = jSONArray.getJSONObject(0).getJSONArray("mTipTagsLevel3");
            JSONArray optJSONArray = jSONObject.optJSONArray("catel3");
            if (optJSONArray != null && optJSONArray.length() > 1) {
                int i2;
                for (i2 = 0; i2 < optJSONArray.length(); i2++) {
                    jSONArray2.put(a(optJSONArray.getJSONObject(i2), 6));
                }
                if (optJSONArray == null || optJSONArray.length() == 0) {
                    optJSONArray = jSONObject.getJSONArray("catel2");
                    if (optJSONArray != null) {
                        for (i2 = 0; i2 < optJSONArray.length(); i2++) {
                            jSONArray2.put(a(optJSONArray.getJSONObject(i2), 6));
                        }
                    }
                }
            }
            JSONArray jSONArray3 = jSONArray.getJSONObject(1).getJSONArray("mTipTagsLevel3");
            jSONArray = jSONObject.getJSONArray(WxPerformanceHandle.MESSAGE_TAG);
            while (i < jSONArray.length()) {
                jSONArray3.put(a(jSONArray.getJSONObject(i), 0));
                i++;
            }
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            return a(str);
        }
    }

    private static JSONObject a(JSONObject jSONObject, int i) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        int optInt = jSONObject.optInt("id");
        String optString = jSONObject.optString("keyword");
        jSONObject2.put("id", optInt);
        jSONObject2.put("subId", i);
        jSONObject2.put("tips", optString);
        return jSONObject2;
    }

    public static final SearchTabInfo a(JSONObject jSONObject, String str) {
        return d(b(jSONObject, str));
    }
}

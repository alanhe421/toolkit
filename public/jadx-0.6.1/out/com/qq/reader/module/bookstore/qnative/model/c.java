package com.qq.reader.module.bookstore.qnative.model;

import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: SearchOptionModel */
public class c {
    public ArrayList<c> a = new ArrayList();
    public d b = new d(this);
    public b c = new b(this);

    /* compiled from: SearchOptionModel */
    public class a {
        public String a;
        public int b;
        final /* synthetic */ c c;

        public a(c cVar) {
            this.c = cVar;
        }
    }

    /* compiled from: SearchOptionModel */
    public class b {
        public int a;
        public ArrayList<a> b = new ArrayList();
        final /* synthetic */ c c;

        public b(c cVar) {
            this.c = cVar;
        }
    }

    /* compiled from: SearchOptionModel */
    public class c {
        public String a;
        public ArrayList<String> b = new ArrayList();
        public int c;
        final /* synthetic */ c d;

        public c(c cVar) {
            this.d = cVar;
        }
    }

    /* compiled from: SearchOptionModel */
    public class d {
        public int a;
        public ArrayList<Integer> b = new ArrayList();
        public ArrayList<String> c = new ArrayList();
        final /* synthetic */ c d;

        public d(c cVar) {
            this.d = cVar;
        }
    }

    public void a(String str) {
        int i = 0;
        try {
            int i2;
            JSONObject jSONObject = new JSONObject(str);
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            JSONObject optJSONObject = jSONObject.optJSONObject("section_info");
            JSONObject optJSONObject2 = jSONObject.optJSONObject("header_info");
            if (optJSONArray != null) {
                for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                    jSONObject = optJSONArray.optJSONObject(i3);
                    c cVar = new c(this);
                    cVar.a = jSONObject.optString("name");
                    cVar.c = jSONObject.optInt("sec_id");
                    JSONArray optJSONArray2 = jSONObject.optJSONArray("values");
                    i2 = 0;
                    while (optJSONArray2 != null && i2 < optJSONArray2.length()) {
                        cVar.b.add(optJSONArray2.getJSONObject(i2).optString("val"));
                        i2++;
                    }
                    this.a.add(cVar);
                }
            }
            if (optJSONObject != null) {
                this.b.a = optJSONObject.optInt(ComicStoreExclusiveItemCard.NET_AD_ATTR_COUNT);
                JSONArray optJSONArray3 = optJSONObject.optJSONArray("max_selection");
                i2 = 0;
                while (optJSONArray3 != null && i2 < optJSONArray3.length()) {
                    this.b.b.add(Integer.valueOf(optJSONArray3.optJSONObject(i2).optInt("max")));
                    i2++;
                }
                optJSONArray3 = optJSONObject.optJSONArray("columns");
                i2 = 0;
                while (optJSONArray3 != null && i2 < optJSONArray3.length()) {
                    this.b.c.add(optJSONArray3.optJSONObject(i2).optString("col"));
                    i2++;
                }
            }
            if (optJSONObject2 != null) {
                this.c.a = optJSONObject2.optInt(ComicStoreExclusiveItemCard.NET_AD_ATTR_COUNT);
                JSONArray optJSONArray4 = optJSONObject2.optJSONArray("headers");
                while (optJSONArray4 != null && i < optJSONArray4.length()) {
                    a aVar = new a(this);
                    JSONObject jSONObject2 = optJSONArray4.getJSONObject(i);
                    aVar.b = jSONObject2.optInt("item_count");
                    aVar.a = jSONObject2.optString("name");
                    this.c.b.add(aVar);
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

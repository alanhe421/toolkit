package com.qq.reader.module.feed.mypreference;

import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.android.tpush.common.Constants;
import com.tencent.mm.performance.WxPerformanceHandle;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ReadingGeneInfo */
public class d {
    public ArrayList<d> a = new ArrayList();
    public ArrayList<a> b = new ArrayList();
    public b c;
    public c d;
    private final String e = getClass().getSimpleName();

    /* compiled from: ReadingGeneInfo */
    public static class a {
        public long a;
        public String b;
        public String c;
        public String d;
        public int e;
        public String f;
        public int g;
        private String h;

        public a(JSONObject jSONObject) {
            this.a = (long) jSONObject.optInt("bid");
            this.b = jSONObject.optString("bookName");
            this.c = jSONObject.optString("categoryName");
            this.d = jSONObject.optString("dataTime");
            this.e = jSONObject.optInt(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE);
            this.f = jSONObject.optString("actionType");
            this.g = jSONObject.optInt("trend");
            this.h = jSONObject.toString();
        }

        public String toString() {
            return this.h;
        }
    }

    /* compiled from: ReadingGeneInfo */
    public static class b {
        public String a;
        public String b;
        public String c;
        public String d;
        public String e;

        public b(JSONObject jSONObject) {
            this.a = jSONObject.optString("book_count");
            this.b = jSONObject.optString("day_count");
            this.c = jSONObject.optString("day_word_count");
            this.d = jSONObject.optString("total_word_count");
            this.e = jSONObject.toString();
        }

        public String toString() {
            return this.e;
        }
    }

    /* compiled from: ReadingGeneInfo */
    public static class c {
        public int a;
        public String b;
        public String c;
        public String d;
        public String e;

        public c(JSONObject jSONObject) {
            this.a = jSONObject.optInt("sex");
            this.b = jSONObject.optString("intro");
            this.c = jSONObject.optString("title");
            this.d = jSONObject.optString("imageUrl");
            this.e = jSONObject.optString("qqid");
        }

        public String toString() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("sex", this.a);
                jSONObject.put("imageUrl", this.d);
                jSONObject.put("title", this.c);
                jSONObject.put("intro", this.b);
                jSONObject.put("qqid", this.e);
                return jSONObject.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    /* compiled from: ReadingGeneInfo */
    public static class d {
        public int a;
        public String b;
        public int c;
        public int d;
        private String e;

        public d(JSONObject jSONObject) {
            this.a = jSONObject.optInt(WxPerformanceHandle.MESSAGE_TAG);
            this.b = jSONObject.optString(Constants.FLAG_TAG_NAME);
            this.d = jSONObject.optInt("type");
            JSONObject optJSONObject = jSONObject.optJSONObject(ComicStoreExclusiveItemCard.NET_AD_ATTR_EXTINFO);
            if (optJSONObject != null) {
                this.c = (int) (optJSONObject.optDouble("wpercent") * 100.0d);
            }
            this.e = jSONObject.toString();
        }

        public String toString() {
            return this.e;
        }
    }

    public d(JSONObject jSONObject) {
        a(jSONObject);
    }

    private void a(JSONObject jSONObject) {
        int i = 0;
        if (jSONObject != null) {
            this.d = new c(jSONObject);
            JSONObject optJSONObject = jSONObject.optJSONObject("property");
            if (optJSONObject != null) {
                this.c = new b(optJSONObject);
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("tags");
            int i2 = 0;
            while (optJSONArray != null && i2 < optJSONArray.length()) {
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                if (optJSONObject2 != null) {
                    this.a.add(new d(optJSONObject2));
                }
                i2++;
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("actions");
            while (optJSONArray2 != null && i < optJSONArray2.length()) {
                JSONObject optJSONObject3 = optJSONArray2.optJSONObject(i);
                if (optJSONObject3 != null) {
                    this.b.add(new a(optJSONObject3));
                }
                i++;
            }
        }
    }

    public String toString() {
        int i = 0;
        try {
            JSONObject jSONObject = new JSONObject(this.d.toString());
            jSONObject.put("property", new JSONObject(this.c.toString()));
            JSONArray jSONArray = new JSONArray();
            int i2 = 0;
            while (this.a != null && i2 < this.a.size()) {
                jSONArray.put(i2, new JSONObject(((d) this.a.get(i2)).toString()));
                i2++;
            }
            jSONObject.put("tags", jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            while (this.b != null && i < this.b.size()) {
                jSONArray2.put(i, new JSONObject(((a) this.b.get(i)).toString()));
                i++;
            }
            jSONObject.put("actions", jSONArray2);
            return jSONObject.toString();
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e(this.e, e.getMessage());
            return null;
        }
    }
}

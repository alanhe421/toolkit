package com.qq.reader.module.feed.data.impl;

import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.feed.loader.c;
import com.qq.reader.module.feed.loader.d;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: FeedPage */
public class g implements i {
    private ArrayList<FeedBaseCard> a;
    private String b;
    private String c = null;
    private int d = 0;
    private String e;

    public void a(String str) {
        this.e = str;
    }

    public void b(String str) {
        if (str != null) {
            this.c = str;
            if (this.a == null && str.length() > 0) {
                try {
                    a(new JSONObject(this.c));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<FeedBaseCard> a() {
        if (this.a == null) {
            this.a = new ArrayList();
        }
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        if (this.e != null) {
            return this.e;
        }
        return this.b;
    }

    public int d() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public String e() {
        return this.c;
    }

    private void a(JSONObject jSONObject) {
        this.b = jSONObject.optString("showtime");
        for (a aVar : c.a(null, jSONObject.optJSONArray("infos"))) {
            FeedBaseCard feedBaseCard = (FeedBaseCard) aVar;
            feedBaseCard.setShowTime(c());
            feedBaseCard.setSliceOrder(d());
            feedBaseCard.setIRemovedListener(this);
            a().add(feedBaseCard);
        }
    }

    public synchronized void a(a aVar) {
        if (aVar != null) {
            if (aVar instanceof FeedBaseCard) {
                FeedBaseCard feedBaseCard = (FeedBaseCard) aVar;
                try {
                    JSONObject jSONObject = new JSONObject(this.c);
                    JSONArray optJSONArray = jSONObject.optJSONArray("infos");
                    int index = feedBaseCard.getIndex();
                    if (index >= 0 && index < optJSONArray.length()) {
                        optJSONArray.put(index, null);
                        feedBaseCard.setIRemovedListener(null);
                        this.a.remove(feedBaseCard);
                        this.c = jSONObject.toString();
                        c.b().b(this);
                        d.b().b(this);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void b(a aVar) {
        if (aVar != null) {
            if (aVar instanceof FeedBaseCard) {
                FeedBaseCard feedBaseCard = (FeedBaseCard) aVar;
                try {
                    feedBaseCard.setToClickedStatus();
                    JSONObject jSONObject = new JSONObject(this.c);
                    JSONArray optJSONArray = jSONObject.optJSONArray("infos");
                    int index = feedBaseCard.getIndex();
                    if (index >= 0 && index < optJSONArray.length()) {
                        optJSONArray.put(index, feedBaseCard.getDataSourceObj());
                        this.c = jSONObject.toString();
                        c.b().b(this);
                        d.b().b(this);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void f() {
        this.a.clear();
    }

    public void g() {
        try {
            com.qq.reader.common.monitor.debug.c.e("FeedPackageDate", "=============ShowTime:" + this.b + "|| VisualTime:" + this.e + "-" + this.d + "==============");
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((FeedBaseCard) it.next()).ivanDebugPrint();
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("FeedPackageDate", e.toString());
        }
    }
}

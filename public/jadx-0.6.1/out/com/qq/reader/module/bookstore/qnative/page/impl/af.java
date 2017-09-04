package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.ColCard_Books;
import com.qq.reader.module.bookstore.qnative.card.impl.ColCard_Charts;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCard4BagVip;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCard4Book;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCard4BookCollectList;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCardCommon;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCardKinds;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforOther;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.c;
import com.qq.reader.module.bookstore.qnative.page.d;
import java.util.Collection;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeServerPage */
public class af extends c {
    protected int r = 1;

    public void a(b bVar) {
        super.a(bVar);
        this.r = ((af) bVar).r;
    }

    public af(Bundle bundle) {
        this.f = bundle;
        this.g = o();
        if (this.g == null || this.g.length() == 0) {
            this.g = a(this.f);
        }
        this.i = this.g;
        com.qq.reader.common.monitor.debug.c.a("pageurl", "pageurl " + this.g);
    }

    public int i_() {
        return this.i.hashCode();
    }

    public void b(JSONObject jSONObject) {
        super.b(jSONObject);
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("topicinfo");
            a(jSONObject.optLong("expireTime") * 1000);
            this.o = jSONObject.optLong("pagestamp");
            JSONArray jSONArray;
            if (optJSONObject != null) {
                jSONArray = optJSONObject.getJSONArray("elements");
                int length = jSONArray.length();
                if (jSONArray != null && length > 0) {
                    for (int i = 0; i < length; i++) {
                        a((JSONObject) jSONArray.get(i), jSONObject);
                    }
                    JSONObject optJSONObject2 = jSONObject.optJSONObject("cols");
                    if (optJSONObject2 != null) {
                        Iterator keys = optJSONObject2.keys();
                        while (keys.hasNext()) {
                            a aVar = (a) this.l.get((String) keys.next());
                            if (aVar != null) {
                                JSONObject optJSONObject3 = optJSONObject2.optJSONObject(aVar.getCardId());
                                if (optJSONObject3 != null) {
                                    aVar.fillData(optJSONObject3);
                                }
                            }
                        }
                    }
                    optJSONObject = jSONObject.optJSONObject("info");
                    if (optJSONObject != null) {
                        this.n = new d();
                        this.n.a(optJSONObject);
                        return;
                    }
                    return;
                }
                return;
            }
            ListCardCommon listCard4Book;
            this.r = jSONObject.optInt("nextPage");
            optJSONObject = jSONObject.optJSONObject("info");
            if (optJSONObject != null) {
                this.n = new d();
                this.n.a(optJSONObject);
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("books");
            if (optJSONArray != null) {
                listCard4Book = new ListCard4Book(this, "bookList");
                listCard4Book.setEventListener(l());
                listCard4Book.fillData(optJSONArray);
                this.k.add(listCard4Book);
                this.l.put(listCard4Book.getCardId(), listCard4Book);
            }
            optJSONArray = jSONObject.optJSONArray("bookList");
            JSONArray optJSONArray2 = jSONObject.optJSONArray("topicList");
            jSONArray = jSONObject.optJSONArray("packs");
            if (optJSONArray != null) {
                listCard4Book = new ListCard4Book(this, "bookList");
                listCard4Book.setEventListener(l());
                listCard4Book.fillData(optJSONArray);
                this.k.add(listCard4Book);
                this.l.put(listCard4Book.getCardId(), listCard4Book);
            } else if (optJSONArray2 != null) {
                r0 = new ListCard4BookCollectList(this, "topicList");
                r0.setEventListener(l());
                r0.fillData(optJSONArray2);
                this.k.add(r0);
                this.l.put(r0.getCardId(), r0);
            } else if (jSONArray != null) {
                r0 = new ListCard4BagVip(this, "packs");
                r0.setEventListener(l());
                r0.fillData(jSONArray);
                this.k.add(r0);
                this.l.put(r0.getCardId(), r0);
            } else {
                a(jSONObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String a(Bundle bundle) {
        return new com.qq.reader.module.bookstore.qnative.c(bundle).b("listDispatch?");
    }

    protected void a(JSONObject jSONObject, JSONObject jSONObject2) {
        a aVar = null;
        try {
            String toLowerCase = jSONObject.getString("type").toLowerCase();
            if ("normalcol".equals(toLowerCase)) {
                aVar = new ColCard_Books(this, "ColCard_Books");
            } else if ("rankcol".equals(toLowerCase) || "updatecol".equals(toLowerCase)) {
                aVar = new ColCard_Charts(this, "ColCard_Charts");
            }
            if (aVar != null) {
                aVar.build(jSONObject);
                aVar.setEventListener(l());
                this.k.add(aVar);
                this.l.put(aVar.getCardId(), aVar);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean addMore(com.qq.reader.module.bookstore.qnative.a aVar) {
        if (aVar instanceof af) {
            af afVar = (af) aVar;
            Collection<a> m = afVar.m();
            if (!(this.k == null || m == null || m.size() <= 0)) {
                boolean z = false;
                for (a aVar2 : this.k) {
                    boolean z2;
                    try {
                        if (aVar2.isAddAble()) {
                            for (a aVar3 : m) {
                                if (aVar2.equals(aVar3) && aVar2.addMore(aVar3)) {
                                    z = true;
                                    break;
                                }
                            }
                        }
                        z2 = z;
                    } catch (Exception e) {
                        com.qq.reader.common.monitor.debug.c.a("Native", aVar2.getClass().getName() + " : " + e.toString());
                        z2 = z;
                    }
                    z = z2;
                }
                if (z) {
                    this.o = afVar.o;
                    return true;
                } else if (s()) {
                    this.k.addAll(m);
                    this.l.putAll(afVar.l);
                    this.o = afVar.o;
                    return true;
                }
            }
        } else if (aVar instanceof a) {
            a aVar4 = (a) aVar;
            if (this.k != null && s()) {
                this.k.add(aVar4);
                this.l.put(aVar4.getCardId(), aVar4);
                return true;
            }
        }
        return false;
    }

    protected void a(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("ads");
        if (optJSONObject != null) {
            for (String str : this.f.getString("URL_BUILD_PERE_ADVS").split(",")) {
                if (str != null && str.length() > 0) {
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject(str);
                    if (optJSONObject2 != null) {
                        ListCardKinds listCardKinds = new ListCardKinds(this, str);
                        listCardKinds.setEventListener(l());
                        listCardKinds.fillData(optJSONObject2);
                        this.k.add(listCardKinds);
                        this.l.put(listCardKinds.getCardId(), listCardKinds);
                    }
                }
            }
        }
    }

    public Class c() {
        return NativePageFragmentforOther.class;
    }
}

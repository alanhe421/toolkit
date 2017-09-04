package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.impl.DetailBookInfoCard;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* compiled from: NativeLocalDetailPage */
public class f extends a {
    public boolean a = false;
    private long b = 0;

    public void a(b bVar) {
        super.a(bVar);
        this.b = ((f) bVar).b;
        this.a = ((f) bVar).a;
    }

    public f(Bundle bundle, String str) {
        this.f = bundle;
        this.i = str;
    }

    public void b(long j) {
        this.b = j;
    }

    public int n() {
        return (this.b + this.i).hashCode();
    }

    public JSONObject x() {
        for (com.qq.reader.module.bookstore.qnative.card.a aVar : m()) {
            if (aVar instanceof DetailBookInfoCard) {
                return ((DetailBookInfoCard) aVar).getBookInfo();
            }
        }
        return null;
    }

    public void b(JSONObject jSONObject) {
        boolean z = true;
        int i = 0;
        jSONObject.optString("code");
        String[] strArr = new String[]{"introinfo", "chapinfo", "dynamicInfos", "commentInfo", "adInfo", "columbooks", "authorRec", "expRec", "commentinfo", "copyright", "editorRec"};
        ArrayList arrayList = new ArrayList();
        arrayList.add("commentinfo");
        arrayList.add("columbooks");
        arrayList.add("expRec");
        arrayList.add("copyright");
        String optString = jSONObject.optJSONObject("introinfo").optJSONObject("book").optString("categoryInfoV4SlaveId");
        if (jSONObject.optJSONObject("introinfo").optInt("hasCoupon", 0) != 1) {
            z = false;
        }
        this.a = z;
        int length = strArr.length;
        while (i < length) {
            String str = strArr[i];
            if (!"19200".equals(optString) || !arrayList.contains(str)) {
                JSONObject optJSONObject = jSONObject.optJSONObject(str);
                if (optJSONObject == null || optJSONObject.length() <= 0) {
                    a(str, jSONObject);
                } else {
                    try {
                        if (str.equalsIgnoreCase("commentinfo")) {
                            String string = jSONObject.getJSONObject("introinfo").getJSONObject("book").getString("title");
                            if (string != null) {
                                optJSONObject.put("bookname", string);
                            }
                        } else if (str.equalsIgnoreCase("authorRec")) {
                            JSONObject jSONObject2 = jSONObject.getJSONObject("introinfo").getJSONObject("book");
                            optJSONObject.put("oribookid", jSONObject2.optInt("id"));
                            optJSONObject.put("authorid", jSONObject2.optString("centerAuthorId"));
                        }
                    } catch (Exception e) {
                    }
                    com.qq.reader.module.bookstore.qnative.card.a aVar = (com.qq.reader.module.bookstore.qnative.card.a) this.l.get(str);
                    if (aVar != null) {
                        aVar.mFromBid = this.b;
                        aVar.fillData(optJSONObject);
                    }
                }
            }
            i++;
        }
    }

    private void a(String str, JSONObject jSONObject) {
        if (str.equals("copyright")) {
            JSONObject optJSONObject = jSONObject.optJSONObject("introinfo");
            JSONObject optJSONObject2 = jSONObject.optJSONObject("commentinfo");
            if (optJSONObject2 == null || ao.m(optJSONObject2.optLong("bid"))) {
                com.qq.reader.module.bookstore.qnative.card.a aVar = (com.qq.reader.module.bookstore.qnative.card.a) this.l.get(str);
                if (aVar != null) {
                    aVar.mFromBid = this.b;
                    aVar.fillData(optJSONObject);
                    return;
                }
                return;
            }
            this.l.remove(str);
        }
    }

    public String a(List<com.qq.reader.module.bookstore.qnative.card.a> list) {
        c cVar = new c(null);
        Bundle a = cVar.a();
        a.putString("KEY_JUMP_PAGENAME", "DetailPage");
        a.putLong("URL_BUILD_PERE_BOOK_ID", this.b);
        a.putString(s.STATPARAM_KEY, this.m);
        return cVar.b("nativepage/book/detail?");
    }
}

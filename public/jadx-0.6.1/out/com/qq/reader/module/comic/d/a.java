package com.qq.reader.module.comic.d;

import android.os.Bundle;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.impl.PayMonthGuide;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.d;
import com.qq.reader.module.bookstore.qnative.page.impl.af;
import com.qq.reader.module.comic.card.ComicStoreEntranceCard;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.reader.module.comic.card.ComicStoreFourItemCard;
import com.qq.reader.module.comic.card.ComicStoreFreeColumnCard;
import com.qq.reader.module.comic.card.ComicStoreSixItemCard;
import com.qq.reader.module.comic.card.ComicStripItemCard;
import com.qq.reader.module.comic.card.ComicWeeklyRankCard;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfComicBookStore */
public class a extends af {
    public int a;
    private int b;
    private int c;

    public a(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        c cVar = new c(bundle);
        this.c = bundle.getInt("comic_main_type", 0);
        String a = cVar.a(e.dm, "?");
        if (a.contains("?")) {
            return a + "&pageType=" + this.c;
        }
        return a + "?pageType=" + this.c;
    }

    public void a(b bVar) {
        super.a(bVar);
    }

    public void b(JSONObject jSONObject) {
        try {
            super.b(jSONObject);
            this.a = jSONObject.optInt("code");
            this.b = jSONObject.optInt("pageVersion");
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            if (optJSONObject != null) {
                if (this.n == null) {
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("info");
                    if (optJSONObject2 != null) {
                        this.n = new d();
                        this.n.a(optJSONObject2);
                    }
                }
                this.o = (long) optJSONObject.optInt("pagestamp");
                com.qq.reader.common.monitor.debug.c.d("NEXT_PAGE", "fillData mPagestamp = " + this.o);
                if (this.c == 2) {
                    com.qq.reader.module.bookstore.qnative.card.a payMonthGuide = new PayMonthGuide(this, "OpenMonthCard", true);
                    payMonthGuide.setEventListener(l());
                    this.k.add(payMonthGuide);
                    this.l.put("OpenMonthCard", payMonthGuide);
                }
                JSONArray optJSONArray = optJSONObject.optJSONArray("dataList");
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        com.qq.reader.module.bookstore.qnative.card.a aVar = null;
                        JSONObject optJSONObject3 = optJSONArray.optJSONObject(i);
                        int optInt = optJSONObject3.optInt("templateType");
                        switch (optInt) {
                            case 0:
                                aVar = new ComicStoreEntranceCard(this, optInt + "");
                                break;
                            case 1:
                                aVar = new ComicStoreExclusiveItemCard(this, optInt + "");
                                break;
                            case 3:
                                aVar = new ComicStripItemCard(this, optInt + "");
                                break;
                            case 4:
                                aVar = new ComicStoreFourItemCard(this, optInt + "");
                                break;
                            case 6:
                                aVar = new ComicStoreSixItemCard(this, optInt + "");
                                break;
                            case 10:
                                aVar = new ComicWeeklyRankCard(this, optInt + "");
                                break;
                            case 13:
                                aVar = new ComicStoreFreeColumnCard(this, optInt + "");
                                break;
                        }
                        if (aVar != null && aVar.fillData(optJSONObject3)) {
                            aVar.setEventListener(l());
                            this.k.add(aVar);
                            this.l.put(optInt + "", aVar);
                        }
                    }
                    com.qq.reader.common.monitor.debug.c.e("comicMainPage", "mCardList size is " + this.k.size() + "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return true;
    }

    public boolean addMore(com.qq.reader.module.bookstore.qnative.a aVar) {
        boolean addMore = super.addMore(aVar);
        if (aVar instanceof af) {
            this.o = ((af) aVar).r();
            com.qq.reader.common.monitor.debug.c.d("NEXT_PAGE", "addMore mPagestamp = " + this.o);
        }
        return addMore;
    }

    public boolean s() {
        com.qq.reader.common.monitor.debug.c.d("NEXT_PAGE", "isAddAble mPagestamp = " + this.o);
        return super.s();
    }
}

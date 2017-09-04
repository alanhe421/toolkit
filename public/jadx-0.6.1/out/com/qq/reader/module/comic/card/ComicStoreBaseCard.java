package com.qq.reader.module.comic.card;

import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qrcomic.c.e;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public abstract class ComicStoreBaseCard extends a {
    private int sex;

    public ComicStoreBaseCard(b bVar, String str) {
        super(bVar, str);
    }

    protected void onClickMoreStat(int i) {
        Object a = e.a.a.a(i, 3);
        if (!TextUtils.isEmpty(a)) {
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(this.sex));
            i.a(a, hashMap, ReaderApplication.getApplicationImp());
        }
    }

    protected void onShowStat(int i) {
        Object a = e.a.a.a(i, 1);
        if (!TextUtils.isEmpty(a)) {
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(this.sex));
            i.a(a, hashMap, ReaderApplication.getApplicationImp());
        }
    }

    protected void onClickBookStat(int i) {
        Object a = e.a.a.a(i, 2);
        if (!TextUtils.isEmpty(a)) {
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(this.sex));
            i.a(a, hashMap, ReaderApplication.getApplicationImp());
        }
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.sex = jSONObject.optInt("userPrefer");
        return true;
    }
}

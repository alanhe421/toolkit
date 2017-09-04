package com.qq.reader.module.question.card;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import java.util.HashMap;
import org.json.JSONObject;

public class FamousAuthorSayNormalCard extends FamousAuthorSayBaseCard {
    static final String tag = "FamousAuthorSayNormalCard";

    public FamousAuthorSayNormalCard(b bVar, String str) {
        super(bVar, str);
    }

    public void attachView() {
        config(1);
        super.attachView();
    }

    protected void onToDetailPageUpLog(boolean z) {
        super.onToDetailPageUpLog(z);
        if (this.mType.equals("freeList")) {
            Object hashMap = new HashMap();
            if (this.data.b().e() == 1) {
                hashMap.put(s.ORIGIN, "1");
            } else {
                hashMap.put(s.ORIGIN, "2");
            }
            i.a("event_D167", hashMap, ReaderApplication.getApplicationImp());
        } else if (this.mType.equals("normalList")) {
            i.a("event_D173", null, ReaderApplication.getApplicationImp());
        }
    }

    protected void onCardShowUpLog() {
        if (this.mType.equals("freeList")) {
            Object hashMap = new HashMap();
            if (this.data.b().e() == 1) {
                hashMap.put(s.ORIGIN, "1");
            } else {
                hashMap.put(s.ORIGIN, "2");
            }
            i.a("event_D166", hashMap, ReaderApplication.getApplicationImp());
        } else if (this.mType.equals("normalList")) {
            i.a("event_D172", null, ReaderApplication.getApplicationImp());
        }
    }

    public void setId(JSONObject jSONObject) {
        this.mDataId = this.data.a().g();
    }
}

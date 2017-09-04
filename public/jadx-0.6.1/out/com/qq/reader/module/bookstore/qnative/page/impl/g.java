package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.impl.EndPageInteractionCard;
import com.qq.reader.module.bookstore.qnative.page.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import java.util.List;
import org.json.JSONObject;

/* compiled from: NativeLocalEndPage */
public class g extends a {
    private long a = 0;
    private boolean b;

    public void a(b bVar) {
        super.a(bVar);
        this.a = ((g) bVar).a;
        this.b = ((g) bVar).b;
    }

    public g(Bundle bundle, String str) {
        this.f = bundle;
        this.i = str;
    }

    public void b(long j) {
        this.a = j;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public int n() {
        return (this.a + this.i).hashCode();
    }

    public void b(JSONObject jSONObject) {
        for (String str : new String[]{"commentinfo", "authorRec", "expRec", "EndPageInteractionCard", "weekSortInfo"}) {
            JSONObject optJSONObject = jSONObject.optJSONObject(str);
            com.qq.reader.module.bookstore.qnative.card.a aVar;
            if (optJSONObject == null || optJSONObject.length() <= 0) {
                aVar = (com.qq.reader.module.bookstore.qnative.card.a) this.l.get(str);
                if (aVar != null && (aVar instanceof EndPageInteractionCard)) {
                    ((EndPageInteractionCard) aVar).isFinish = this.b;
                    aVar.mFromBid = this.a;
                    aVar.fillData(new JSONObject());
                }
            } else {
                aVar = (com.qq.reader.module.bookstore.qnative.card.a) this.l.get(str);
                if (aVar != null) {
                    aVar.mFromBid = this.a;
                    aVar.fillData(optJSONObject);
                }
            }
        }
    }

    public String a(List<com.qq.reader.module.bookstore.qnative.card.a> list) {
        c cVar = new c(null);
        cVar.a().putLong("URL_BUILD_PERE_BOOK_ID", this.a);
        return cVar.b("readover?");
    }
}

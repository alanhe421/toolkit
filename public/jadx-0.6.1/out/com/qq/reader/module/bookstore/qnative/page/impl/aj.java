package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.storage.task.BaseNativeDataTask;
import com.qq.reader.module.bookstore.qnative.storage.task.LoadNativeFindHomePageDataTask;
import com.qq.reader.module.findhome.FindHomePageCard;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfFindHome */
public class aj extends af {
    public aj(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        return e.bR;
    }

    public void b(JSONObject jSONObject) {
        synchronized (aj.class) {
            this.k.clear();
            this.l.clear();
            a(jSONObject.optLong("expireTime") * 1000);
            FindHomePageCard findHomePageCard = new FindHomePageCard(this, "FindHomePageCard");
            findHomePageCard.setEventListener(l());
            findHomePageCard.fillData(jSONObject);
            if (findHomePageCard.isDataReady()) {
                this.k.add(findHomePageCard);
                this.l.put(findHomePageCard.getCardId(), findHomePageCard);
            } else {
                throw new RuntimeException("findhome is empty");
            }
        }
    }

    public BaseNativeDataTask f() {
        return new LoadNativeFindHomePageDataTask(ReaderApplication.getApplicationImp().getApplicationContext(), this);
    }

    public void a(b bVar) {
        synchronized (aj.class) {
            super.a(bVar);
        }
    }

    public boolean a() {
        return false;
    }
}

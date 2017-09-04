package com.qq.reader.module.feed.loader;

import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.module.feed.card.FeedRookieEntranceCard;
import org.json.JSONObject;

/* compiled from: FeedRookieCardNavigationParser */
public class e implements a<Boolean> {
    public /* synthetic */ Object a(String str) {
        return b(str);
    }

    public Boolean b(String str) {
        try {
            c.e("FeedRookieCard", str + "");
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(FeedRookieEntranceCard.JSON_KEY_NAVIGATION_BAR)) {
                c.e("FeedRookieCard", "is rookie");
                d.P(ReaderApplication.getApplicationImp(), 1);
                FeedRookieEntranceCard feedRookieEntranceCard = new FeedRookieEntranceCard(null, "");
                feedRookieEntranceCard.parseData(jSONObject);
                if (feedRookieEntranceCard.isLoginCard()) {
                    if (!feedRookieEntranceCard.equals(d.bV(ReaderApplication.getApplicationImp()))) {
                        d.L(ReaderApplication.getApplicationImp(), str);
                    }
                } else if (!feedRookieEntranceCard.equals(d.bW(ReaderApplication.getApplicationImp()))) {
                    d.M(ReaderApplication.getApplicationImp(), str);
                }
                return Boolean.valueOf(true);
            }
            d.P(ReaderApplication.getApplicationImp(), 0);
            c.e("FeedRookieCard", "is not rookie");
            if (com.qq.reader.common.login.c.b()) {
                d.M(ReaderApplication.getApplicationImp(), "");
            } else {
                d.L(ReaderApplication.getApplicationImp(), "");
            }
            return Boolean.valueOf(true);
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.valueOf(false);
        }
    }
}

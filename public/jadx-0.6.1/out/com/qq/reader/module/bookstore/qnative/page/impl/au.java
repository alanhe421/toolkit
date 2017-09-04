package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCardCommon;
import com.qq.reader.module.bookstore.qnative.card.impl.PersonalityBooksListBookcard;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentForPersonality;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.open.SocialConstants;
import java.util.Calendar;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeServerPersonalityBooksPage */
public class au extends af {
    private static final String[] s = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private String a;
    private String b;
    private String c;
    private String d;
    private boolean e = false;

    public au(Bundle bundle) {
        super(bundle);
        this.f = bundle;
    }

    public void a(b bVar) {
        super.a(bVar);
        this.e = ((au) bVar).e;
        this.a = ((au) bVar).a;
        this.b = ((au) bVar).b;
        this.c = ((au) bVar).c;
        this.d = ((au) bVar).d;
    }

    public void b(JSONObject jSONObject) {
        boolean z = true;
        this.k.clear();
        this.l.clear();
        this.p = jSONObject.toString();
        this.q = jSONObject;
        a(this.q.optLong("expireTime") * 1000);
        this.o = this.q.optLong("pagestamp");
        JSONArray optJSONArray = jSONObject.optJSONArray("bookList");
        if (jSONObject.optInt("needGene") != 1) {
            z = false;
        }
        this.e = z;
        this.a = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
        this.b = jSONObject.optString("tips");
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(jSONObject.optLong("updateTime"));
        this.c = s[instance.get(2)];
        this.d = String.valueOf(instance.get(5));
        ListCardCommon personalityBooksListBookcard = new PersonalityBooksListBookcard(this, "bookList");
        personalityBooksListBookcard.setEventListener(l());
        personalityBooksListBookcard.fillData(optJSONArray);
        this.k.add(personalityBooksListBookcard);
        this.l.put(personalityBooksListBookcard.getCardId(), personalityBooksListBookcard);
        if (!jSONObject.optBoolean("hasSetExpiredTime") && !b()) {
            try {
                jSONObject.put("hasSetExpiredTime", true);
                long C = C();
                jSONObject.put("expireTime", C);
                a(C * 1000);
                v();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean b() {
        if (h() > System.currentTimeMillis()) {
            return false;
        }
        return true;
    }

    private long C() {
        Date date = new Date();
        return ((date.getTime() / 1000) - ((long) (date.getSeconds() + (((date.getHours() * 60) * 60) + (date.getMinutes() * 60))))) + 118800;
    }

    public String a(Bundle bundle) {
        c cVar = new c(bundle);
        String string = bundle.getString("needGeneInfo");
        String str = "common/recmybooklist?sex=" + d.aU(ReaderApplication.getApplicationImp());
        if ("1".equals(string)) {
            str = str + "&needGeneInfo=" + string;
        }
        return cVar.a(e.a, str);
    }

    public Class c() {
        return NativePageFragmentForPersonality.class;
    }

    public String x() {
        return this.a;
    }

    public String y() {
        return this.b;
    }

    public String z() {
        return this.c;
    }

    public String A() {
        return this.d;
    }

    public boolean B() {
        return this.e;
    }
}

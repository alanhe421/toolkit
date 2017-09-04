package com.qq.reader.module.bookstore.qnative.page.impl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.page.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.question.data.AudioData;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeLocalUserCenterMainPage */
public class n extends a {
    public int a = 0;
    public String b;
    public String c;
    public int d;
    private String e;

    public n(Bundle bundle, String str) {
        this.f = bundle;
        this.e = this.f.getString("userId");
        this.i = str;
    }

    public void a(b bVar) {
        super.a(bVar);
        this.a = ((n) bVar).a;
        this.b = ((n) bVar).b;
        this.c = ((n) bVar).c;
        this.d = ((n) bVar).d;
    }

    public int n() {
        return (this.e + this.i).hashCode();
    }

    public void b(JSONObject jSONObject) {
        this.a = jSONObject.optInt("gender");
        this.b = jSONObject.optString("nickName");
        this.c = jSONObject.optString("userIcon");
        this.d = jSONObject.optInt("isOwn");
        List arrayList = new ArrayList();
        int optInt = jSONObject.optInt("shelfCount");
        int optInt2 = jSONObject.optInt("totalCount");
        int optInt3 = jSONObject.optInt("contentCount");
        JSONArray optJSONArray = jSONObject.optJSONArray("followManitoList");
        int optInt4 = jSONObject.optInt("priStatus");
        arrayList.add("UserIntro");
        if (optInt > 0) {
            arrayList.add("UserBooks");
        }
        if (optJSONArray != null && optJSONArray.length() > 0) {
            arrayList.add("SubscribedAuthors");
        }
        if (this.d == 1) {
            jSONObject.optInt("mQuestionCount");
            jSONObject.optInt("mListenCount");
            arrayList.add("MyQuestion");
            arrayList.add("MyListen");
        } else if (jSONObject.optInt("hisQuestionCount") > 0) {
            arrayList.add("HisQuestion");
        }
        if (optInt2 > 0) {
            arrayList.add("UserComments");
        }
        if ((optInt4 == 0 || this.d == 1) && optInt3 > 0) {
            arrayList.add("UserInterActions");
        }
        if (arrayList.size() <= 1) {
            arrayList.add("UserEmpty");
        }
        for (Object obj : (String[]) arrayList.toArray(new String[arrayList.size()])) {
            com.qq.reader.module.bookstore.qnative.card.a aVar = (com.qq.reader.module.bookstore.qnative.card.a) this.l.get(obj);
            if (aVar != null) {
                aVar.fillData(jSONObject);
            }
        }
    }

    public String a(List<com.qq.reader.module.bookstore.qnative.card.a> list) {
        return e.bV + "&userId=" + this.e + "&uin=" + d.R(ReaderApplication.getApplicationImp());
    }

    public void a(int i, int i2, Intent intent, Handler handler) {
        super.a(i, i2, intent, handler);
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                AudioData audioData = (AudioData) extras.getParcelable("AUDIO_DATA");
                for (com.qq.reader.module.bookstore.qnative.card.a aVar : this.k) {
                    if (aVar instanceof com.qq.reader.module.question.card.a) {
                        try {
                            if (((com.qq.reader.module.question.card.a) aVar).isDataChanged(audioData)) {
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public boolean a() {
        return false;
    }
}

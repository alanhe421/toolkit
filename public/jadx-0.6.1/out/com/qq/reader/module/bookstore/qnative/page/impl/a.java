package com.qq.reader.module.bookstore.qnative.page.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.d;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.impl.AudioBookDetailHeaderCard;
import com.qq.reader.module.bookstore.qnative.card.impl.AudioDetailHeaderCard4Album;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import java.util.List;
import org.json.JSONObject;

/* compiled from: NativeLocalAudioBookDetailPage */
public class a extends com.qq.reader.module.bookstore.qnative.page.a {
    public boolean a = false;
    private String b;

    public a(Bundle bundle, String str) {
        this.f = bundle;
        this.i = str;
    }

    public void a(b bVar) {
        super.a(bVar);
        this.b = ((a) bVar).b;
        this.p = ((a) bVar).p;
    }

    public void c(String str) {
        this.b = str;
    }

    public int n() {
        return (this.b + this.i).hashCode();
    }

    public void b(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.p = jSONObject.toString();
            JSONObject optJSONObject = jSONObject.optJSONObject("audio");
            if (optJSONObject != null) {
                int optInt = optJSONObject.optInt("status", 1);
                int optInt2 = optJSONObject.optInt("checkLevel", 0);
                if (optInt == -1 || optInt2 == -1) {
                    d.a(ReaderApplication.getApplicationImp()).a(new Intent("BROADCAST_OFF_MARKET"));
                    return;
                }
            }
            if (this.k != null) {
                for (com.qq.reader.module.bookstore.qnative.card.a fillData : this.k) {
                    fillData.fillData(jSONObject);
                }
            }
        }
    }

    public String a(List<com.qq.reader.module.bookstore.qnative.card.a> list) {
        c cVar = new c(null);
        cVar.a().putString(s.STATPARAM_KEY, this.m);
        return cVar.a(e.a, "audio/intro?audiobid=" + this.b);
    }

    public JSONObject x() {
        try {
            for (com.qq.reader.module.bookstore.qnative.card.a aVar : m()) {
                if (aVar instanceof AudioBookDetailHeaderCard) {
                    return ((AudioBookDetailHeaderCard) aVar).getBookInfo();
                }
                if (aVar instanceof AudioDetailHeaderCard4Album) {
                    return ((AudioDetailHeaderCard4Album) aVar).getBookInfo();
                }
            }
            return null;
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
            return null;
        }
    }
}

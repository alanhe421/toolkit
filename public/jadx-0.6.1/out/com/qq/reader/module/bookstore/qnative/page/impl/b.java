package com.qq.reader.module.bookstore.qnative.page.impl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.card.impl.WriterQAPageCard;
import com.qq.reader.module.bookstore.qnative.page.a;
import com.tencent.android.tpush.common.MessageKey;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* compiled from: NativeLocalAuthorMainPage */
public class b extends a {
    public String a;
    public String b;
    public int c;
    public boolean d;
    public String e;
    public String r;
    public boolean s = true;
    public boolean t = false;
    public int u = 0;
    public boolean v;

    public b(Bundle bundle, String str) {
        this.f = bundle;
        this.a = this.f.getString("AUTHORPAGE_KEY_AUTHORID");
        this.i = str;
    }

    public void a(com.qq.reader.module.bookstore.qnative.page.b bVar) {
        super.a(bVar);
        this.a = ((b) bVar).a;
        this.b = ((b) bVar).b;
        this.c = ((b) bVar).c;
        this.d = ((b) bVar).d;
        this.e = ((b) bVar).e;
        this.r = ((b) bVar).r;
        this.s = ((b) bVar).s;
        this.v = ((b) bVar).v;
        this.u = ((b) bVar).u;
        this.t = ((b) bVar).t;
    }

    public int n() {
        return (this.a + this.i).hashCode();
    }

    public void b(JSONObject jSONObject) {
        int i = 0;
        this.t = jSONObject.optInt("owner") == 1;
        List arrayList = new ArrayList();
        arrayList.add("WriterIntro");
        JSONObject optJSONObject = jSONObject.optJSONObject("manitoInfo");
        if (optJSONObject != null) {
            boolean z;
            if (optJSONObject.optInt("roomNumber") > 0) {
                arrayList.add("WriterLiveShowEnter");
            }
            if (optJSONObject.optInt("active") == 1) {
                z = true;
            } else {
                z = false;
            }
            this.v = z;
            if (this.t) {
                arrayList.add("WriterHomePage");
                if (this.v) {
                    arrayList.add("WriterQAPage");
                }
            } else if (optJSONObject.optInt("qaCount") > 0) {
                arrayList.add("WriterAnswer");
            } else if (this.v) {
                arrayList.add("WriterInfo");
            }
            this.d = optJSONObject.optBoolean("isFocus", false);
            this.u = optJSONObject.optInt("toAnswer");
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("info");
            if (optJSONObject2 != null) {
                this.c = optJSONObject2.optInt("partiality");
                this.b = optJSONObject2.optString("authorName");
                this.e = optJSONObject2.optString(MessageKey.MSG_ICON);
                this.r = optJSONObject2.optString(MessageKey.MSG_CONTENT);
                this.a = optJSONObject2.optString("authorId");
                int optInt = optJSONObject2.optInt("dynamicListCount");
                int optInt2 = optJSONObject2.optInt("commentCount") + optJSONObject2.optInt("replyCount");
                int optInt3 = optJSONObject2.optInt("booksCount");
                if (optInt == 0 && optInt2 == 0 && optInt3 == 0 && !this.v) {
                    this.s = false;
                } else {
                    this.s = true;
                }
            }
        }
        arrayList.add("AllBooks");
        arrayList.add("AllComments");
        arrayList.add("AllNews");
        arrayList.add("AllEmpty");
        String[] strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
        int length = strArr.length;
        while (i < length) {
            com.qq.reader.module.bookstore.qnative.card.a aVar = (com.qq.reader.module.bookstore.qnative.card.a) this.l.get(strArr[i]);
            if (aVar != null) {
                aVar.fillData(jSONObject);
            }
            i++;
        }
    }

    public String a(List<com.qq.reader.module.bookstore.qnative.card.a> list) {
        return e.bU + "&authorId=" + this.a;
    }

    public boolean b() {
        return true;
    }

    public void a(int i, int i2, Intent intent, Handler handler) {
        super.a(i, i2, intent, handler);
        if (intent != null) {
            switch (i) {
                case 1007:
                    int intExtra = intent.getIntExtra("waitingQACount", -1);
                    if (intExtra >= 0) {
                        for (com.qq.reader.module.bookstore.qnative.card.a aVar : m()) {
                            if (aVar instanceof WriterQAPageCard) {
                                ((WriterQAPageCard) aVar).setWaitingCount(intExtra);
                                return;
                            }
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public boolean a() {
        return false;
    }
}

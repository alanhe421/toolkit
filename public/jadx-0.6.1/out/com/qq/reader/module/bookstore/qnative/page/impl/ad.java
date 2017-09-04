package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.d;
import com.qq.reader.module.bookstore.qnative.page.d.b;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeServerDiscoveryTopicPage */
public class ad extends af {
    private static d a;
    private int b;

    public ad(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        c cVar = new c(this.f);
        StringBuffer stringBuffer = new StringBuffer("listDispatch?");
        stringBuffer.append("action=topicstream");
        return cVar.b(stringBuffer.toString());
    }

    public void b(JSONObject jSONObject) {
        try {
            this.p = jSONObject.toString();
            a(jSONObject.optLong("expireTime") * 1000);
            JSONArray optJSONArray = jSONObject.optJSONArray("infos");
            this.o = jSONObject.optLong("pagestamp");
            this.b = jSONObject.optInt("actionId", 3);
            x();
            for (a aVar : com.qq.reader.module.feed.data.impl.c.a(this, optJSONArray)) {
                FeedBaseCard feedBaseCard = (FeedBaseCard) aVar;
                feedBaseCard.setEventListener(l());
                feedBaseCard.setShowDivider(true);
                feedBaseCard.isClickEnable = true;
                this.k.add(feedBaseCard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean a() {
        return false;
    }

    private void x() throws JSONException {
        if (a == null) {
            JSONObject jSONObject = new JSONObject(y());
            a = new d();
            a.a(jSONObject);
        }
        a.a(String.valueOf(this.b));
        switch (this.b) {
            case 0:
                a.a(2);
                a.b("出版专题");
                break;
            case 1:
                a.a(0);
                a.b("男生专题");
                break;
            case 2:
                a.a(1);
                a.b("女生专题");
                break;
        }
        if (this.f != null) {
            String string = this.f.getString("KEY_ACTIONTAG");
            List g = a.g();
            for (int i = 0; i < g.size(); i++) {
                b bVar = (b) g.get(i);
                bVar.c = false;
                if (bVar != null && bVar.b.equals(string)) {
                    bVar.c = true;
                }
            }
        }
        this.n = a;
    }

    private String y() {
        Exception e;
        Throwable th;
        InputStream open;
        try {
            open = ReaderApplication.getApplicationImp().getResources().getAssets().open("topic_action_tags.txt");
            try {
                byte[] bArr = new byte[open.available()];
                open.read(bArr);
                String str = new String(bArr, "UTF-8");
                if (open == null) {
                    return str;
                }
                try {
                    open.close();
                    return str;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return str;
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e5) {
            e = e5;
            open = null;
            e.printStackTrace();
            if (open != null) {
                open.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            open = null;
            if (open != null) {
                open.close();
            }
            throw th;
        }
    }
}

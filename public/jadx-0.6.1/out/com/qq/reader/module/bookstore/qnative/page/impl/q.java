package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.SingleCommentCard;
import com.qq.reader.module.bookstore.qnative.card.impl.SingleReplyCard;
import com.qq.reader.module.bookstore.qnative.page.d;
import com.qq.reader.module.bookstore.qnative.page.d.b;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeServerAllCommentPage */
public class q extends af {
    private static d a;

    public q(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        c cVar = new c(this.f);
        StringBuffer stringBuffer = new StringBuffer("");
        String string = this.f.getString("KEY_ACTIONTAG");
        String string2 = this.f.getString("AUTHORPAGE_KEY_AUTHORID");
        if ("1".equals(string)) {
            stringBuffer.append("manitoReplies?authorId=" + string2 + "&platform=1");
        } else if ("0".equals(string)) {
            stringBuffer.append("manitoComments?authorId=" + string2 + "&platform=1");
        }
        return cVar.a(e.a, stringBuffer.toString());
    }

    public void b(JSONObject jSONObject) {
        int i = 0;
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("replylist");
            JSONArray optJSONArray2 = jSONObject.optJSONArray("commentlist");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                    a singleReplyCard = new SingleReplyCard(this, "replyCard");
                    singleReplyCard.fillData(optJSONObject);
                    singleReplyCard.setEventListener(l());
                    this.k.add(singleReplyCard);
                }
            }
            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                while (i < optJSONArray2.length()) {
                    JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i);
                    a singleCommentCard = new SingleCommentCard(this, "commentCard");
                    singleCommentCard.fillData(optJSONObject2);
                    singleCommentCard.setEventListener(l());
                    this.k.add(singleCommentCard);
                    i++;
                }
            }
            this.p = jSONObject.toString();
            this.o = jSONObject.optLong("pagestamp");
            x();
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
        InputStream open;
        Exception e;
        Throwable th;
        try {
            open = ReaderApplication.getApplicationImp().getResources().getAssets().open("all_comment_action_tags.txt");
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

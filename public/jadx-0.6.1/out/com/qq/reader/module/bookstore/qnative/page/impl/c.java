package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.ArticleCardForAuthorTimeLine;
import com.qq.reader.module.bookstore.qnative.card.impl.AuthorSayCardForAuthorTimeLine;
import com.qq.reader.module.bookstore.qnative.card.impl.RecommendAuthorCardForAuthorTimeLine;
import com.qq.reader.module.bookstore.qnative.card.impl.SingleCommentCardForAuthorTimeLine;
import com.qq.reader.module.bookstore.qnative.card.impl.SingleReplyCardForAuthorTimeLine;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeLocalAuthorTimeLinePage */
public class c extends af {
    public c(Bundle bundle) {
        super(bundle);
    }

    public void b(JSONObject jSONObject) {
        super.b(jSONObject);
        this.f.putLong("lastDynamicTime", jSONObject.optLong("lastDynamicTime", System.currentTimeMillis()));
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("dynamicList");
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String string = jSONObject2.getString("cardType");
                    a singleCommentCardForAuthorTimeLine;
                    if (string.equals("comment")) {
                        singleCommentCardForAuthorTimeLine = new SingleCommentCardForAuthorTimeLine(this, "commentCard");
                        singleCommentCardForAuthorTimeLine.fillData(jSONObject2.getJSONObject("cardInfo"));
                        singleCommentCardForAuthorTimeLine.setEventListener(l());
                        this.k.add(singleCommentCardForAuthorTimeLine);
                    } else if (string.equals("reply")) {
                        singleCommentCardForAuthorTimeLine = new SingleReplyCardForAuthorTimeLine(this, "replyCard");
                        singleCommentCardForAuthorTimeLine.fillData(jSONObject2.getJSONObject("cardInfo"));
                        singleCommentCardForAuthorTimeLine.setEventListener(l());
                        this.k.add(singleCommentCardForAuthorTimeLine);
                    } else if (string.equals("authorTalk")) {
                        singleCommentCardForAuthorTimeLine = new AuthorSayCardForAuthorTimeLine(this, "WriterAnswerCard");
                        singleCommentCardForAuthorTimeLine.fillData(jSONObject2.getJSONObject("cardInfo"));
                        singleCommentCardForAuthorTimeLine.setEventListener(l());
                        this.k.add(singleCommentCardForAuthorTimeLine);
                    } else if (string.equals("article")) {
                        singleCommentCardForAuthorTimeLine = new ArticleCardForAuthorTimeLine(this, "AuthorNewsCard");
                        singleCommentCardForAuthorTimeLine.fillData(jSONObject2.getJSONObject("cardInfo"));
                        singleCommentCardForAuthorTimeLine.setEventListener(l());
                        this.k.add(singleCommentCardForAuthorTimeLine);
                    } else if (string.equals("recommendAuthor")) {
                        singleCommentCardForAuthorTimeLine = new RecommendAuthorCardForAuthorTimeLine(this, "RecommendAuthorCardForAuthorTimeLine");
                        singleCommentCardForAuthorTimeLine.fillData(jSONObject2.getJSONObject("cardInfo"));
                        singleCommentCardForAuthorTimeLine.setEventListener(l());
                        this.k.add(singleCommentCardForAuthorTimeLine);
                    }
                }
            }
            if (this.o > 0 && this.k.size() > 0) {
                ((com.qq.reader.module.bookstore.qnative.card.impl.a) this.k.get(this.k.size() - 1)).setShowDivider(Boolean.valueOf(false));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String a(Bundle bundle) {
        com.qq.reader.module.bookstore.qnative.c cVar = new com.qq.reader.module.bookstore.qnative.c(bundle);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("lastDynamicTime=").append(bundle.getLong("lastDynamicTime", System.currentTimeMillis()));
        return cVar.a(e.dj, stringBuilder.toString());
    }

    public boolean a() {
        return false;
    }
}

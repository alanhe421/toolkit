package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubReplyCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubTitleCard;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfTopicComment */
public class at extends ah {
    public at(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        String str = "topic/replylist?";
        return new c(bundle).b("topic/replylist?");
    }

    protected void a(a aVar) {
    }

    public synchronized boolean d(JSONObject jSONObject) {
        boolean z;
        int i;
        BookClubReplyCard bookClubReplyCard = new BookClubReplyCard(this, "BookClubReplyCard", this.w);
        bookClubReplyCard.fillData(jSONObject);
        bookClubReplyCard.setPageType(E());
        bookClubReplyCard.setEventListener(l());
        if (this.k.size() <= 0 || !(this.k.get(0) instanceof BookClubTitleCard)) {
            i = 0;
        } else {
            i = 1;
        }
        for (a aVar : this.k) {
            if ((aVar instanceof BookClubReplyCard) && aVar.getCardId().equals(bookClubReplyCard.getCardId())) {
                z = false;
                break;
            }
        }
        this.k.add(i, bookClubReplyCard);
        this.l.put(bookClubReplyCard.getCardId(), bookClubReplyCard);
        bookClubReplyCard.mCommentUid = jSONObject.optString("PARA_TYPE_COMMENT_UID");
        z();
        z = true;
        return z;
    }
}

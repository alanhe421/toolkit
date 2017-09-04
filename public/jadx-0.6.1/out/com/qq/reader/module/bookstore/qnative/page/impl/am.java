package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.module.rookie.view.RookieGeneCommitCard;
import com.qq.reader.module.rookie.view.RookieGeneRecBookCard;
import com.qq.reader.module.rookie.view.RookieUserGeneCard;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfNewUserGeneUpdate */
public class am extends af {
    public am(Bundle bundle) {
        super(bundle);
    }

    public void b(JSONObject jSONObject) {
        RookieGeneCommitCard rookieGeneCommitCard = new RookieGeneCommitCard(this, "RookieGeneCommitCard");
        rookieGeneCommitCard.fillData(jSONObject);
        rookieGeneCommitCard.setEventListener(l());
        this.k.add(rookieGeneCommitCard);
        this.l.put(rookieGeneCommitCard.getCardId(), rookieGeneCommitCard);
        RookieUserGeneCard rookieUserGeneCard = new RookieUserGeneCard(this, "RookieUserGeneCard");
        rookieUserGeneCard.fillData(jSONObject);
        rookieUserGeneCard.setEventListener(l());
        this.k.add(rookieUserGeneCard);
        this.l.put(rookieUserGeneCard.getCardId(), rookieUserGeneCard);
        JSONArray optJSONArray = jSONObject.optJSONArray("recommendBooks");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                RookieGeneRecBookCard rookieGeneRecBookCard = new RookieGeneRecBookCard(this, "RookieGeneRecBookCard");
                rookieGeneRecBookCard.fillData(optJSONArray.opt(i));
                rookieGeneRecBookCard.setEventListener(l());
                this.k.add(rookieGeneRecBookCard);
                this.l.put(rookieGeneRecBookCard.getCardId(), rookieGeneRecBookCard);
            }
        }
    }

    public boolean a() {
        return false;
    }
}

package com.qq.reader.module.bookstore.qnative.card.impl;

import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class AuthorPageEmptyCard extends a {
    public AuthorPageEmptyCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.user_center_empty_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("manitoInfo");
            if (optJSONObject.optInt("isOwn") > 0 || optJSONObject == null) {
                return false;
            }
            optJSONObject = optJSONObject.optJSONObject("info");
            if (optJSONObject == null) {
                return false;
            }
            int optInt = optJSONObject.optInt("dynamicListCount");
            int optInt2 = optJSONObject.optInt("commentCount") + optJSONObject.optInt("replyCount");
            int optInt3 = optJSONObject.optInt("booksCount");
            if (optInt == 0 && optInt2 == 0 && optInt3 == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            c.e(getClass().getSimpleName(), e.getMessage());
            return false;
        }
    }

    public void attachView() {
    }
}

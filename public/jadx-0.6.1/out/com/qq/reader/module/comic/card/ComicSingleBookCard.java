package com.qq.reader.module.comic.card;

import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.entity.p;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class ComicSingleBookCard extends a {
    p comicSingleBook;
    String curActionTag;

    public ComicSingleBookCard(b bVar, String str) {
        super(bVar, str);
    }

    public ComicSingleBookCard(b bVar, String str, String str2) {
        super(bVar, str);
        this.curActionTag = str2;
    }

    public int getResLayoutId() {
        return R.layout.comic_detail_item_originalbook_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return false;
    }

    public void attachView() {
    }
}

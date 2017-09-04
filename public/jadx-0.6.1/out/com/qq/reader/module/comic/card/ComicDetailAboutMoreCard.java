package com.qq.reader.module.comic.card;

import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.entity.e;
import com.qq.reader.module.comic.entity.j;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class ComicDetailAboutMoreCard extends ComicDetailPageBaseCard<j> {
    public ComicDetailAboutMoreCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.comic_detail_item_about_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.item = (e) new Gson().fromJson(jSONObject.toString(), new TypeToken<e<j>>(this) {
            final /* synthetic */ ComicDetailAboutMoreCard a;

            {
                this.a = r1;
            }
        }.getType());
        return true;
    }

    public void attachView() {
        ((TextView) ap.a(getRootView(), R.id.title)).setText(this.item.a);
        ((TextView) ap.a(getRootView(), R.id.copy_right_txt)).setText(((j) this.item.c).a);
        ((TextView) ap.a(getRootView(), R.id.provide_txt)).setText(((j) this.item.c).b);
        ((TextView) ap.a(getRootView(), R.id.desc_txt)).setText(((j) this.item.c).c);
    }
}

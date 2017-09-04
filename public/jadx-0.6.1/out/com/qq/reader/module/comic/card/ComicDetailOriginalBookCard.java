package com.qq.reader.module.comic.card;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.impl.SingleBookInfo;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.entity.e;
import com.qq.reader.module.comic.entity.k;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class ComicDetailOriginalBookCard extends ComicDetailPageBaseCard<k> {
    private g bookInfo;

    public ComicDetailOriginalBookCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.comic_detail_item_originalbook_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        try {
            this.item = (e) new Gson().fromJson(jSONObject.toString(), new TypeToken<e<k>>(this) {
                final /* synthetic */ ComicDetailOriginalBookCard a;

                {
                    this.a = r1;
                }
            }.getType());
            if (!(this.item == null || this.item.c == null)) {
                this.bookInfo = new g();
                this.bookInfo.a(Long.parseLong(((k) this.item.c).a));
                this.bookInfo.a(((k) this.item.c).b);
                this.bookInfo.b(((k) this.item.c).d);
                this.bookInfo.c(((k) this.item.c).c);
                this.bookInfo.d(((k) this.item.c).e);
                this.bookInfo.e(((k) this.item.c).f);
            }
            if (this.item != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void attachView() {
        View rootView = getRootView();
        ((TextView) rootView.findViewById(R.id.tv_comic_detail_card_title)).setText(this.item.a);
        ((TextView) rootView.findViewById(R.id.tv_comic_detail_card_face)).setText(this.item.b);
        SingleBookInfo singleBookInfo = (SingleBookInfo) rootView.findViewById(R.id.ll_single_book_info);
        singleBookInfo.setBookInfo(this.bookInfo);
        singleBookInfo.a(false);
        singleBookInfo.setBookInfoCategoryByCategoryType(this.bookInfo, 2);
        rootView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ComicDetailOriginalBookCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                o.a(this.a.getEvnetListener().getFromActivity(), String.valueOf(this.a.bookInfo.m()), null, null, null);
            }
        });
    }
}

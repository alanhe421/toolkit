package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.c.c;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VirtualRecommendOneBookCard extends VirtualRecommendCard {
    public VirtualRecommendOneBookCard(b bVar, int i, boolean z) {
        super(bVar, i, z);
    }

    VirtualRecommendOneBookCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_recommend_onebook;
    }

    public void attachView() {
        if (this.mBookItems.size() >= 1) {
            setCardTitle();
            fillSingleBookInfo(getRootView(), (g) this.mBookItems.get(0));
            ((TextView) ap.a(getRootView(), R.id.tv_publish_time)).setText(new SimpleDateFormat("yyyy年MM月dd日").format(new Date(this.mPublishTime)));
        }
    }

    private void fillSingleBookInfo(View view, final g gVar) {
        view.setVisibility(0);
        ap.a(view, R.id.rl_book_intro).setOnClickListener(new c(this) {
            final /* synthetic */ VirtualRecommendOneBookCard b;

            public void a(View view) {
                this.b.clickStatics();
                gVar.a(this.b.getEvnetListener());
            }
        });
        com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(ao.g(gVar.m()), (ImageView) ap.a(view, R.id.img_book_cover), a.a().j());
        ((TextView) ap.a(view, R.id.tv_title)).setText(gVar.n());
        ((TextView) ap.a(view, R.id.tv_author)).setText(gVar.q());
        ((TextView) ap.a(view, R.id.tv_content)).setText(gVar.s());
    }
}

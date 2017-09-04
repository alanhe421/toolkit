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

public class VirtualRecommendFourBookCard extends VirtualRecommendCard {
    private final int[] sectionResIds = new int[]{R.id.section_0, R.id.section_1, R.id.section_2, R.id.section_3};

    public VirtualRecommendFourBookCard(b bVar, int i, boolean z) {
        super(bVar, i, z);
    }

    VirtualRecommendFourBookCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_recommend_fourbook;
    }

    public void attachView() {
        if (this.mBookItems.size() >= 2) {
            setCardTitle();
            for (int i = 0; i < this.sectionResIds.length; i++) {
                View a = ap.a(getRootView(), this.sectionResIds[i]);
                if (i >= this.mBookItems.size()) {
                    a.setVisibility(4);
                } else {
                    a.setVisibility(0);
                    fillSingleBookInfo(a, (g) this.mBookItems.get(i));
                }
            }
            ((TextView) ap.a(getRootView(), R.id.tv_publish_time)).setText(new SimpleDateFormat("yyyy年MM月dd日").format(new Date(this.mPublishTime)));
        }
    }

    private void fillSingleBookInfo(View view, final g gVar) {
        view.setOnClickListener(new c(this) {
            final /* synthetic */ VirtualRecommendFourBookCard b;

            public void a(View view) {
                this.b.clickStatics();
                gVar.a(this.b.getEvnetListener());
            }
        });
        com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(ao.g(gVar.m()), (ImageView) ap.a(view, R.id.img_book_cover), a.a().j());
        TextView textView = (TextView) ap.a(view, R.id.tv_title);
        textView.setText(gVar.n());
        textView.setVisibility(0);
    }
}

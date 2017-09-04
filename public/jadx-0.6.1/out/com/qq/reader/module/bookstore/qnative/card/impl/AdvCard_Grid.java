package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.c.a;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.BaseAdvCard;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.card.view.CardMoreView;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class AdvCard_Grid extends BaseAdvCard {
    private int showPicHeight = ((((a.bU - ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.common_dp_42)) / 2) * 235) / 490);

    public AdvCard_Grid(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_adv_1;
    }

    public void attachView() {
        CardTitle cardTitle = (CardTitle) ap.a(getRootView(), R.id.localstore_adv_title);
        cardTitle.setCardTitle(this.mIconIndex, this.mShowTitle, this.mPromotionName, null);
        CardMoreView cardMoreView = (CardMoreView) ap.a(getRootView(), R.id.localstore_moreaction);
        if (this.mMoreAction == null) {
            cardTitle.setVisibility(8);
            cardMoreView.setVisibility(8);
        } else {
            cardTitle.setVisibility(0);
            cardMoreView.setVisibility(0);
            cardMoreView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ AdvCard_Grid a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.mMoreAction.a(this.a.getEvnetListener());
                }
            });
            cardMoreView.setText(this.mMoreAction.e);
        }
        AdvInfo4Pic advInfo4Pic = (AdvInfo4Pic) ap.a(getRootView(), R.id.localstore_adv_0);
        if (getItemList().size() >= 2) {
            advInfo4Pic.setAdv((com.qq.reader.module.bookstore.qnative.item.b) getItemList().get(0), (com.qq.reader.module.bookstore.qnative.item.b) getItemList().get(1), this.showPicHeight, getEvnetListener());
        }
        advInfo4Pic = (AdvInfo4Pic) ap.a(getRootView(), R.id.localstore_adv_1);
        if (getItemList().size() < 4 || this.mDispaly <= 2) {
            advInfo4Pic.setVisibility(8);
            return;
        }
        advInfo4Pic.setVisibility(0);
        advInfo4Pic.setAdv((com.qq.reader.module.bookstore.qnative.item.b) getItemList().get(2), (com.qq.reader.module.bookstore.qnative.item.b) getItemList().get(3), this.showPicHeight, getEvnetListener());
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        getItemList().clear();
        return super.parseData(jSONObject);
    }
}

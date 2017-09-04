package com.qq.reader.module.game.card;

import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.game.card.view.GameHorizontalView;
import com.qq.reader.module.game.data.c;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class MyGameListCard extends a {
    String json = "";
    c mData;
    com.qq.reader.module.game.card.view.c mPresenter;
    GameHorizontalView mView;
    private com.qq.reader.module.game.card.view.c presenter;

    public MyGameListCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.game_my_game_list_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mData = c.a(jSONObject);
        com.qq.reader.module.game.a.c(this.mData);
        return true;
    }

    public void attachView() {
        this.mView = (GameHorizontalView) ap.a(getRootView(), R.id.gameItem);
        if (this.mPresenter == null) {
            this.mPresenter = getPresenter();
        }
        this.mPresenter.a(this.mView, this.mData);
    }

    public void onCardShouldDestroy() {
        super.onCardShouldDestroy();
        if (this.mPresenter != null) {
            this.mPresenter.b();
        }
    }

    protected com.qq.reader.module.game.card.view.c getPresenter() {
        return new com.qq.reader.module.game.card.view.c(getEvnetListener());
    }
}

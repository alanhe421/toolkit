package com.qq.reader.module.game.card;

import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.impl.CardTitle;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.card.view.CardMoreView;
import com.qq.reader.module.game.card.forlog.a;
import com.qq.reader.module.game.card.view.d;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import org.json.JSONObject;

public class GameHorizontalListCard extends Game3ItemBaseCard {
    public GameHorizontalListCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.game_horizontal_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        super.parseData(jSONObject);
        return true;
    }

    public void attachView() {
        super.attachView();
    }

    public d newGamePresenter() {
        return new a(getEvnetListener(), this.columnId, 0);
    }

    public void installProvider() {
        if (this.providers == null) {
            this.providers = new ArrayList();
        } else {
            this.providers.clear();
        }
        this.providers.add((b) ap.a(getRootView(), R.id.game_a));
        this.providers.add((b) ap.a(getRootView(), R.id.game_b));
        this.providers.add((b) ap.a(getRootView(), R.id.game_c));
    }

    public CardTitle getTitleView() {
        return (CardTitle) ap.a(getRootView(), R.id.title_btn);
    }

    public CardMoreView getMoreView() {
        return (CardMoreView) ap.a(getRootView(), R.id.more_btn);
    }

    public void onCardShouldDestroy() {
        super.onCardShouldDestroy();
        if (this.presenters != null) {
            for (d b : this.presenters) {
                b.b();
            }
        }
    }
}

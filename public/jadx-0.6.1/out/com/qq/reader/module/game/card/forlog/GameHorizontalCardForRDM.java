package com.qq.reader.module.game.card.forlog;

import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.game.card.MyGameListCard;
import com.qq.reader.module.game.card.view.c;

public class GameHorizontalCardForRDM extends MyGameListCard {
    String categoryId;
    int userLocation;

    public GameHorizontalCardForRDM(b bVar, String str) {
        super(bVar, str);
    }

    public GameHorizontalCardForRDM(b bVar, String str, String str2, int i) {
        super(bVar, str);
        this.categoryId = str2;
        this.userLocation = i;
    }

    protected c getPresenter() {
        return new a(getEvnetListener(), this.categoryId, this.userLocation);
    }
}

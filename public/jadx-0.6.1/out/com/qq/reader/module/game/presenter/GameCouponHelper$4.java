package com.qq.reader.module.game.presenter;

import com.qq.reader.common.readertask.ordinal.ReaderDBTask;

class GameCouponHelper$4 extends ReaderDBTask {
    final /* synthetic */ a this$0;
    final /* synthetic */ String val$couponId;

    GameCouponHelper$4(a aVar, String str) {
        this.this$0 = aVar;
        this.val$couponId = str;
    }

    public void run() {
        super.run();
        this.this$0.a(a.b, "game_coupon_table", "couponid= '" + this.val$couponId + "'");
    }
}

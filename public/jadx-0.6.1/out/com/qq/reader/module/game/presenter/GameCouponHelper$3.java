package com.qq.reader.module.game.presenter;

import android.content.ContentValues;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.module.game.data.b;

class GameCouponHelper$3 extends ReaderDBTask {
    final /* synthetic */ a this$0;
    final /* synthetic */ b val$coupon;
    final /* synthetic */ String val$jsonStr;

    GameCouponHelper$3(a aVar, b bVar, String str) {
        this.this$0 = aVar;
        this.val$coupon = bVar;
        this.val$jsonStr = str;
    }

    public void run() {
        super.run();
        ContentValues contentValues = new ContentValues();
        contentValues.put("couponid", this.val$coupon.a());
        contentValues.put("json", this.val$jsonStr);
        this.this$0.a(a.b, "game_coupon_table", contentValues);
    }
}

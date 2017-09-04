package com.qq.reader.module.rookie.presenter;

import android.content.ContentValues;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;

class RookieGiftHelper$8 extends ReaderDBTask {
    final /* synthetic */ a this$0;
    final /* synthetic */ String val$column;
    final /* synthetic */ int val$giftId;
    final /* synthetic */ int val$value;

    RookieGiftHelper$8(a aVar, String str, int i, int i2) {
        this.this$0 = aVar;
        this.val$column = str;
        this.val$value = i;
        this.val$giftId = i2;
    }

    public void run() {
        super.run();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.val$column, Integer.valueOf(this.val$value));
        this.this$0.a(a.d, "rookie_gift_table", contentValues, "giftid= " + this.val$giftId);
    }
}

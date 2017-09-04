package com.qq.reader.module.rookie.presenter;

import android.content.ContentValues;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;

class RookieGiftHelper$9 extends ReaderDBTask {
    final /* synthetic */ a this$0;
    final /* synthetic */ String val$column;
    final /* synthetic */ int val$giftId;
    final /* synthetic */ String val$value;

    RookieGiftHelper$9(a aVar, String str, String str2, int i) {
        this.this$0 = aVar;
        this.val$column = str;
        this.val$value = str2;
        this.val$giftId = i;
    }

    public void run() {
        super.run();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.val$column, this.val$value);
        this.this$0.a(a.d, "rookie_gift_table", contentValues, "giftid= " + this.val$giftId);
    }
}

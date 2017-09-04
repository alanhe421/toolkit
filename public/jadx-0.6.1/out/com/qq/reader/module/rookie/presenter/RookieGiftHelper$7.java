package com.qq.reader.module.rookie.presenter;

import android.content.ContentValues;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.module.rookie.a.c;

class RookieGiftHelper$7 extends ReaderDBTask {
    final /* synthetic */ a this$0;
    final /* synthetic */ c val$gift;
    final /* synthetic */ String val$jsonStr;

    RookieGiftHelper$7(a aVar, c cVar, String str) {
        this.this$0 = aVar;
        this.val$gift = cVar;
        this.val$jsonStr = str;
    }

    public void run() {
        super.run();
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", Integer.valueOf(this.val$gift.b()));
        contentValues.put("json", this.val$jsonStr);
        contentValues.put("show_status", this.val$gift.e());
        this.this$0.a(a.d, "rookie_gift_table", contentValues, "giftid= " + this.val$gift.a());
    }
}

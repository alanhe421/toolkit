package com.qq.reader.module.rookie.presenter;

import android.content.ContentValues;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.module.rookie.a.c;

class RookieGiftHelper$6 extends ReaderDBTask {
    final /* synthetic */ a this$0;
    final /* synthetic */ c val$gift;
    final /* synthetic */ String val$jsonStr;

    RookieGiftHelper$6(a aVar, c cVar, String str) {
        this.this$0 = aVar;
        this.val$gift = cVar;
        this.val$jsonStr = str;
    }

    public void run() {
        super.run();
        ContentValues contentValues = new ContentValues();
        contentValues.put("giftid", Integer.valueOf(this.val$gift.a()));
        contentValues.put("status", Integer.valueOf(this.val$gift.b()));
        contentValues.put("json", this.val$jsonStr);
        contentValues.put("show_status", this.val$gift.e());
        contentValues.put("show_date", "");
        this.this$0.a(a.d, "rookie_gift_table", contentValues);
    }
}

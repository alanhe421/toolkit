package com.qq.reader.common.db.handle;

import android.os.Handler;
import android.text.TextUtils;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.cservice.adv.e;

class BookChapterMoreInfoHandler$1 extends ReaderIOTask {
    final /* synthetic */ String val$authorId;
    final /* synthetic */ String val$bid;
    final /* synthetic */ Handler val$callbackHandler;

    BookChapterMoreInfoHandler$1(String str, String str2, Handler handler) {
        this.val$authorId = str;
        this.val$bid = str2;
        this.val$callbackHandler = handler;
    }

    public void run() {
        super.run();
        long j = 0;
        if (TextUtils.isEmpty(this.val$authorId)) {
            j = g.a().a(this.val$bid);
        } else {
            try {
                j = Long.valueOf(this.val$authorId).longValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String a = g.a().a(j);
        c.c(this.val$bid, this.val$callbackHandler);
        e.a().b(this.val$bid);
        c.b(this.val$bid, a, g.a().b(), this.val$callbackHandler);
    }
}

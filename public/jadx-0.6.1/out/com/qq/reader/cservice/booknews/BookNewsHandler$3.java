package com.qq.reader.cservice.booknews;

import android.os.Message;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;

class BookNewsHandler$3 extends ReaderDBTask {
    final /* synthetic */ b this$0;

    BookNewsHandler$3(b bVar) {
        this.this$0 = bVar;
    }

    public void run() {
        this.this$0.a();
        Message obtainMessage = this.this$0.a.obtainMessage();
        obtainMessage.what = 100003;
        obtainMessage.obj = this.this$0.b();
        this.this$0.a.sendMessage(obtainMessage);
    }
}

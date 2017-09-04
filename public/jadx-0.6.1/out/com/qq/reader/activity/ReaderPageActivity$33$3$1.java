package com.qq.reader.activity;

import com.qq.reader.activity.ReaderPageActivity.64.AnonymousClass3;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;

class ReaderPageActivity$33$3$1 extends ReaderDBTask {
    final /* synthetic */ AnonymousClass3 this$2;

    ReaderPageActivity$33$3$1(AnonymousClass3 anonymousClass3) {
        this.this$2 = anonymousClass3;
    }

    public void run() {
        af.a(this.this$2.a.a, (int) R.string.read_public_opened, 1).a();
        i.c().b(this.this$2.a.a.X.getBookId() + "", 1);
    }
}

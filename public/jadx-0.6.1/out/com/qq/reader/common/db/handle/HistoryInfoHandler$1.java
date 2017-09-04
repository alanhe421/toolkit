package com.qq.reader.common.db.handle;

import com.qq.reader.common.readertask.ordinal.ReaderDBTask;

class HistoryInfoHandler$1 extends ReaderDBTask {
    final /* synthetic */ r this$0;
    final /* synthetic */ String val$hisId;
    final /* synthetic */ String val$name;
    final /* synthetic */ int val$type;

    HistoryInfoHandler$1(r rVar, int i, String str, String str2) {
        this.this$0 = rVar;
        this.val$type = i;
        this.val$hisId = str;
        this.val$name = str2;
    }

    public void run() {
        this.this$0.b(this.val$type, this.val$hisId, this.val$name);
    }
}

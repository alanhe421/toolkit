package com.qq.reader.activity;

import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import java.util.List;

class DelBookHelper$1 extends ReaderDBTask {
    final /* synthetic */ a this$0;
    final /* synthetic */ boolean val$isDeleteFile;
    final /* synthetic */ List val$localMarks;
    final /* synthetic */ List val$marks;
    final /* synthetic */ List val$netMarks;

    DelBookHelper$1(a aVar, List list, boolean z, List list2, List list3) {
        this.this$0 = aVar;
        this.val$localMarks = list;
        this.val$isDeleteFile = z;
        this.val$netMarks = list2;
        this.val$marks = list3;
    }

    public void run() {
        boolean a = this.this$0.c(this.val$localMarks, this.val$isDeleteFile);
        this.this$0.a(this.val$netMarks);
        boolean b = this.this$0.b(this.val$netMarks, this.val$isDeleteFile);
        if (this.this$0.a == null) {
            return;
        }
        if (a && b) {
            this.this$0.a.a(70001, this.val$marks);
        } else {
            this.this$0.a.a(70002, new String("删除失败"));
        }
    }
}

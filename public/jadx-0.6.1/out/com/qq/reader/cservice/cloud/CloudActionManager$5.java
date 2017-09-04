package com.qq.reader.cservice.cloud;

import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.comic.e.e;
import com.qq.reader.module.comic.mark.ComicBookMark;

class CloudActionManager$5 extends ReaderDBTask {
    final /* synthetic */ b this$0;
    final /* synthetic */ Mark val$mark;

    CloudActionManager$5(b bVar, Mark mark) {
        this.this$0 = bVar;
        this.val$mark = mark;
    }

    public void run() {
        e.a((ComicBookMark) this.val$mark);
    }
}

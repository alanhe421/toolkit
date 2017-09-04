package com.qq.reader.cservice.cloud;

import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.drm.a;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.cservice.onlineread.b;
import com.qq.reader.framework.mark.Mark;
import java.io.File;
import java.util.List;

class CloudActionManager$7 extends ReaderShortTask {
    final /* synthetic */ b this$0;
    final /* synthetic */ List val$marks;

    CloudActionManager$7(b bVar, List list) {
        this.this$0 = bVar;
        this.val$marks = list;
    }

    public void run() {
        i.c().c(this.val$marks);
        for (Mark mark : this.val$marks) {
            ao.a(new File(mark.getId()));
            ao.a(new File(a.a(mark.getId())));
            ao.a(new File(a.b(mark.getId())));
            format.epub.common.a.a.b(mark.getId());
            OnlineTag a = v.b().a(String.valueOf(mark.getBookId()));
            v.b().c(a);
            b.b(b.h, a);
        }
    }
}

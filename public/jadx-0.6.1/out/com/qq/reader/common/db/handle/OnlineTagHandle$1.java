package com.qq.reader.common.db.handle;

import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import java.io.File;
import java.util.List;

class OnlineTagHandle$1 extends ReaderIOTask {
    final /* synthetic */ v this$0;
    final /* synthetic */ List val$toDelChapterPaths;

    OnlineTagHandle$1(v vVar, List list) {
        this.this$0 = vVar;
        this.val$toDelChapterPaths = list;
    }

    public void run() {
        for (String str : this.val$toDelChapterPaths) {
            File file = new File(str);
            if (file.exists()) {
                File file2;
                File file3 = new File(str + "del");
                if (file.renameTo(file3)) {
                    file2 = file3;
                } else {
                    file2 = file;
                }
                file2.delete();
            }
        }
    }
}

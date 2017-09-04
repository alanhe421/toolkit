package com.qq.reader.cservice.download.game;

import com.qq.reader.common.download.task.n;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.cservice.download.game.a.AnonymousClass6;
import java.io.File;

class DownloadGameManagerDelegate$6$1 extends ReaderShortTask {
    final /* synthetic */ AnonymousClass6 this$1;
    final /* synthetic */ n val$context;

    DownloadGameManagerDelegate$6$1(AnonymousClass6 anonymousClass6, n nVar) {
        this.this$1 = anonymousClass6;
        this.val$context = nVar;
    }

    public void run() {
        new File(((d) this.val$context.d()).getTempFilePath()).delete();
    }
}

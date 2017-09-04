package com.qq.reader.plugin.audiobook;

import android.content.Context;
import com.qq.reader.common.download.task.f;
import com.qq.reader.common.download.task.g;
import com.qq.reader.common.download.task.k;
import com.qq.reader.common.download.task.state.TaskActionEnum;
import com.qq.reader.cservice.download.book.c;
import com.qq.reader.cservice.download.book.d;
import java.io.IOException;

/* compiled from: MusicDownloadWorker */
public class h extends c {
    public h(k kVar, g gVar, Object obj, Context context) {
        super(kVar, gVar, obj, context);
    }

    protected boolean c(f fVar) {
        return true;
    }

    protected void b(f fVar) {
        try {
            new d(this.f, fVar, this.e).a();
        } catch (IOException e) {
            com.qq.reader.common.monitor.f.a(h(), e.toString());
            this.f.a(fVar, TaskActionEnum.Err);
        }
    }
}

package com.qq.reader.cservice.download.book;

import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.db.handle.o;
import com.qq.reader.common.download.task.d;
import com.qq.reader.common.download.task.g;
import com.qq.reader.common.download.task.n;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.utils.ao;
import java.io.File;
import java.util.List;

/* compiled from: DownloadBookProvider */
public class b implements d {
    o a = o.c();

    public boolean a(g gVar) {
        if (!(gVar instanceof DownloadBookTask)) {
            return false;
        }
        this.a.a((DownloadBookTask) gVar);
        return true;
    }

    public boolean a(String str) {
        if (this.a.b(str) != null) {
            return true;
        }
        return false;
    }

    public List<g> a() {
        return this.a.a();
    }

    public void a(n nVar) {
        DownloadBookTask downloadBookTask = (DownloadBookTask) nVar.d();
        this.a.a(downloadBookTask.getId());
        if (nVar.a() != TaskStateEnum.InstallCompleted) {
            ao.a(new File(downloadBookTask.getTempFilePath()));
        }
    }

    public void b(g gVar) {
        if (gVar instanceof DownloadBookTask) {
            this.a.b((DownloadBookTask) gVar);
        }
    }

    public void c(g gVar) {
        DownloadBookTask downloadBookTask = (DownloadBookTask) gVar;
        String filePath = downloadBookTask.getFilePath();
        if (filePath != null) {
            i.c().c(filePath);
            i.c().c(downloadBookTask.getId(), Math.abs(filePath.hashCode()) + "");
            i.c().c(filePath, false);
        }
        this.a.a(downloadBookTask.getId());
        this.a.a(downloadBookTask);
    }
}

package com.qq.reader.cservice.download.game;

import com.qq.reader.common.db.handle.n;
import com.qq.reader.common.download.task.d;
import com.qq.reader.common.download.task.g;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import java.io.File;
import java.util.List;

/* compiled from: DownloadGameProvider */
public class c implements d {
    private n a = n.a();

    public boolean a(g gVar) {
        if (!(gVar instanceof d)) {
            return false;
        }
        this.a.a((d) gVar);
        return true;
    }

    public void b(g gVar) {
        this.a.b((d) gVar);
    }

    public void a(com.qq.reader.common.download.task.n nVar) {
        if (!nVar.a().equals(TaskStateEnum.Installing)) {
            d dVar = (d) nVar.d();
            this.a.b(dVar.getId());
            try {
                File file = new File(dVar.getTempFilePath());
                if (file.exists()) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void c(g gVar) {
        d dVar = (d) gVar;
        this.a.b(dVar.getId());
        this.a.a(dVar);
    }

    public List<g> a() {
        return this.a.b();
    }

    public boolean a(String str) {
        return false;
    }
}

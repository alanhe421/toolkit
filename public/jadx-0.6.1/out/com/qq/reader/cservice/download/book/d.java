package com.qq.reader.cservice.download.book;

import android.content.Context;
import com.qq.reader.common.download.task.f;
import com.qq.reader.common.download.task.k;
import com.qq.reader.common.download.task.state.TaskActionEnum;
import com.qq.reader.common.drm.a.a;
import com.qq.reader.common.utils.ao;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;

/* compiled from: DownloadFileParser */
public class d implements a {
    private f a;
    private Context b;
    private k c;

    public d(k kVar, f fVar, Context context) {
        this.a = fVar;
        this.b = context;
        this.c = kVar;
    }

    public void a() throws IOException {
        boolean z = true;
        if (this.a.getDrmflag() == 0) {
            b(this.a);
            this.c.a(this.a, TaskActionEnum.Finish);
        } else if (this.a.getDrmflag() == 1) {
            com.qq.reader.common.drm.a aVar = new com.qq.reader.common.drm.a(this.b, this.a.getFilePath());
            aVar.a((a) this);
            if (!this.a.getBookFormat().equalsIgnoreCase("trial")) {
                z = false;
            }
            aVar.a(String.valueOf(this.a.getId()), z);
        } else if (this.a.getDrmflag() == 2) {
            a(this.a);
            this.c.a(this.a, TaskActionEnum.Finish);
        }
    }

    private void a(f fVar) throws IOException {
        if (fVar.getDrmflag() == 2) {
            ao.a(fVar.getTempFilePath(), fVar.getFilePath(), false);
        }
    }

    private void b(f fVar) throws IOException {
        if (fVar.getDrmflag() == 0) {
            String tempFilePath = fVar.getTempFilePath();
            try {
                ao.a(tempFilePath, fVar.getFilePath(), true);
                ao.a(new File(tempFilePath));
            } catch (EOFException e) {
                ao.a(new File(tempFilePath));
                throw e;
            }
        }
    }

    private void c(f fVar) throws IOException {
        String tempFilePath = fVar.getTempFilePath();
        String filePath = fVar.getFilePath();
        if (fVar.getDrmflag() == 1) {
            new File(tempFilePath).renameTo(new File(filePath));
        }
    }

    public void f(int i) {
        x();
    }

    public void x() {
        try {
            c(this.a);
            this.c.a(this.a, TaskActionEnum.Finish);
        } catch (IOException e) {
            com.qq.reader.common.monitor.f.a("DownloadFileParser", e.toString());
            this.c.a(this.a, TaskActionEnum.Err);
        }
    }
}

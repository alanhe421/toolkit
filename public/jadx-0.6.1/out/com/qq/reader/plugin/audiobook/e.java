package com.qq.reader.plugin.audiobook;

import com.qq.reader.common.download.task.a;
import com.qq.reader.common.download.task.g;
import com.qq.reader.common.utils.ao;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: MusicBookTaskManagerDelegate */
public class e extends a {
    public e(int i) {
        super(i);
    }

    public void d(g gVar) {
        if (gVar != null) {
            this.b.d(gVar);
            this.a.e(gVar);
        }
    }

    public List<MusicDownloadTask> a(long j) {
        List<MusicDownloadTask> arrayList = new ArrayList();
        for (int i = 0; i < this.b.c(); i++) {
            MusicDownloadTask musicDownloadTask = (MusicDownloadTask) this.b.a(i);
            if (musicDownloadTask != null && musicDownloadTask.getBookId() == j) {
                arrayList.add(musicDownloadTask);
            }
        }
        return arrayList;
    }

    public void a(MusicDownloadTask musicDownloadTask) {
        if (musicDownloadTask != null) {
            List a = a(musicDownloadTask.getBookId());
            for (int i = 0; i < a.size(); i++) {
                d((MusicDownloadTask) a.get(i));
            }
            ao.c(new File(musicDownloadTask.getDownloadDirectory()));
        }
    }

    public synchronized void c() {
        this.a.b();
        this.b.b();
    }
}

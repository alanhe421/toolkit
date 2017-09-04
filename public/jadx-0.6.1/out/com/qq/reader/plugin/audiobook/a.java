package com.qq.reader.plugin.audiobook;

import com.qq.reader.common.download.task.d;
import com.qq.reader.common.download.task.g;
import com.qq.reader.common.download.task.n;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.utils.ao;
import java.io.File;
import java.util.List;

/* compiled from: DownloadMusicBookProvider */
public class a implements d {
    g a = g.a();

    public boolean a(g gVar) {
        if (!(gVar instanceof MusicDownloadTask)) {
            return false;
        }
        this.a.a((MusicDownloadTask) gVar);
        return true;
    }

    public void b(g gVar) {
        if (gVar instanceof MusicDownloadTask) {
            this.a.b((MusicDownloadTask) gVar);
        }
    }

    public void a(n nVar) {
        MusicDownloadTask musicDownloadTask = (MusicDownloadTask) nVar.d();
        this.a.a(musicDownloadTask.getBookId(), musicDownloadTask.getChapterId());
        if (nVar.a() != TaskStateEnum.InstallCompleted) {
            ao.a(new File(musicDownloadTask.getTempFilePath()));
        }
    }

    public void c(g gVar) {
        i a = i.a();
        MusicDownloadTask musicDownloadTask = (MusicDownloadTask) gVar;
        long bookId = musicDownloadTask.getBookId();
        long chapterId = musicDownloadTask.getChapterId();
        a.a(bookId, chapterId);
        this.a.a(bookId, chapterId);
        this.a.a(musicDownloadTask);
    }

    public List<g> a() {
        return this.a.b();
    }

    public boolean a(String str) {
        if (this.a.a(str)) {
            return true;
        }
        return false;
    }
}

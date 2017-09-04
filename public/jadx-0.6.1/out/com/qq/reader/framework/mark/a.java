package com.qq.reader.framework.mark;

import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.download.task.l;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.download.book.DownloadBookTask;
import com.qq.reader.cservice.download.book.e;
import com.qq.reader.module.bookstore.qnative.b.b;

/* compiled from: MarkBuilder */
public class a {
    public static Mark a(long j, String str, String str2, String str3) {
        Mark e = i.c().e(String.valueOf(j));
        if (e != null || str3 == null) {
            return e;
        }
        try {
            if (str3.length() <= 0) {
                return e;
            }
            b bVar = new b();
            bVar.a(str3);
            if (bVar.a()) {
                e = new DownloadMark(j);
            } else {
                e = new LocalMark(str, "", 0, 4, false);
            }
            e.setBookName(str);
            e.setBookId(j);
            e.setId(String.valueOf(j));
            e.setAuthor(str2);
            e.setDownloadInfo(str3);
            if (!(e instanceof DownloadMark) || !e.isHardCoverBook()) {
                return e;
            }
            int hashCode = e.getHardCoverChecker().g().hashCode();
            DownloadBookTask downloadBookTask = new DownloadBookTask(e.getBookId(), e.getBookShortName(), e.getAuthor(), "", ao.g(e.getBookId()), hashCode, e.getHardCoverChecker().e(), 1, -1);
            downloadBookTask.setIsOnlyDownLoadIcon(true);
            downloadBookTask.setNewVersion(hashCode);
            downloadBookTask.setState(TaskStateEnum.Paused);
            ((DownloadMark) e).setDownloadTask(downloadBookTask);
            e.setId(downloadBookTask.getFilePath());
            ((e) l.b(1001)).a(downloadBookTask);
            return e;
        } catch (Exception e2) {
            c.a("MarkBuilder", e2.toString());
            return null;
        }
    }
}

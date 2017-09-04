package com.qq.reader.common.db.handle;

import android.os.Handler;
import android.os.Message;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.GetReaderPageChapterMoreInfoTask;
import com.qq.reader.cservice.adv.e;
import com.qq.reader.module.bookchapter.online.OnlineChapter;

/* compiled from: BookChapterMoreInfoHandler */
public class c {
    static boolean a = true;

    public static void a(String str, String str2, Handler handler) {
        a = false;
        g.a().a(new BookChapterMoreInfoHandler$1(str2, str, handler));
    }

    private static void b(final String str, String str2, int i, final Handler handler) {
        g.a().a(new GetReaderPageChapterMoreInfoTask(new com.qq.reader.common.readertask.ordinal.c() {
            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                g.a().a(str, str, true);
                e.a().a(readerProtocolTask, str, str, true);
                c.c(str, handler);
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
            }
        }, str, str2, i));
    }

    private static void c(String str, Handler handler) {
        if (!a && g.a().d()) {
            OnlineChapter onlineChapter = new OnlineChapter();
            onlineChapter.setBookId(Long.parseLong(str));
            onlineChapter.setChapterId(g.a().e());
            onlineChapter.setCommentCount(0);
            onlineChapter.setReadCount(0);
            if (handler != null) {
                Message obtain = Message.obtain();
                obtain.what = 21016;
                obtain.obj = onlineChapter;
                handler.sendMessage(obtain);
            }
            a = true;
        }
    }

    public static void a(String str, Handler handler) {
        a = false;
        c(str, handler);
    }
}

package com.qq.reader.module.bookchapter.online;

import android.os.Handler;
import android.os.Message;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class OnlineChapterHandle$5 extends ReaderIOTask {
    final /* synthetic */ g this$0;
    final /* synthetic */ Handler val$handler;
    final /* synthetic */ List val$newChapterList;
    final /* synthetic */ List val$oldChapterList;

    OnlineChapterHandle$5(g gVar, List list, List list2, Handler handler) {
        this.this$0 = gVar;
        this.val$oldChapterList = list;
        this.val$newChapterList = list2;
        this.val$handler = handler;
    }

    public void run() {
        int size = this.val$oldChapterList.size();
        int size2 = this.val$newChapterList.size();
        List arrayList = new ArrayList();
        Set hashSet = new HashSet();
        String str = "";
        String g = this.this$0.b.g();
        for (int i = 0; i < size; i++) {
            OnlineChapter onlineChapter = (OnlineChapter) this.val$oldChapterList.get(i);
            String valueOf = String.valueOf(onlineChapter.getChapterId());
            if (i < size2) {
                OnlineChapter onlineChapter2 = (OnlineChapter) this.val$newChapterList.get(i);
                if (!(onlineChapter.getChapterMD5() == null || onlineChapter.getChapterMD5().equals(onlineChapter2.getChapterMD5()) || !new File(v.a(g, valueOf)).exists())) {
                    arrayList.add(valueOf);
                    hashSet.add(valueOf);
                }
            } else if (new File(v.a(g, valueOf)).exists()) {
                arrayList.add(valueOf);
                hashSet.add(valueOf);
            }
        }
        v.b().a(this.this$0.b.g(), arrayList);
        if (this.val$handler != null && hashSet.size() > 0) {
            Message obtain = Message.obtain();
            obtain.what = 21102;
            obtain.obj = hashSet;
            this.val$handler.sendMessage(obtain);
        }
    }
}

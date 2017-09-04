package com.qq.reader.common.readertask;

import com.tencent.android.tpush.common.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.PriorityBlockingQueue;

/* compiled from: ReaderFailedTaskMemManager */
public class c {
    private static c c = null;
    private PriorityBlockingQueue<ReaderTask> a = new PriorityBlockingQueue();
    private Map<String, ReaderTask> b = new HashMap();

    private c() {
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (c == null) {
                synchronized (c.class) {
                    c = new c();
                }
            }
            cVar = c;
        }
        return cVar;
    }

    protected synchronized boolean a(ReaderTask readerTask) {
        boolean z = false;
        synchronized (this) {
            com.qq.reader.common.monitor.debug.c.a("fail", "onTaskFail " + readerTask.getTaskKey() + "  type " + readerTask.getFailedType());
            switch (readerTask.getFailedType()) {
                case 1:
                    com.qq.reader.common.monitor.debug.c.a(Constants.LogTag, "addFailedTaskToRelivePool task (AUTO_RETRY) key : " + readerTask.getTaskKey());
                    if (!readerTask.isReachMaxAutoFailedTime()) {
                        Iterator it = this.a.iterator();
                        while (it.hasNext()) {
                            ReaderTask readerTask2 = (ReaderTask) it.next();
                            if (readerTask2.isSameofTask(readerTask)) {
                                this.a.remove(readerTask2);
                                com.qq.reader.common.monitor.debug.c.a(Constants.LogTag, "remove task (AUTO_RETRY) key : " + readerTask2.getTaskKey());
                            }
                        }
                        this.a.add(readerTask);
                        readerTask.mFaiedAutoTryedTime++;
                        z = true;
                        break;
                    }
                    com.qq.reader.common.monitor.debug.c.a(Constants.LogTag, "got max retry" + readerTask.getTaskKey());
                    break;
                case 2:
                    com.qq.reader.common.monitor.debug.c.a(Constants.LogTag, "addFailedTaskToRelivePool task (MENUAL_RETRY) key : " + readerTask.getTaskKey());
                    this.b.put(readerTask.getTaskKey(), readerTask);
                    readerTask.mFaiedAutoTryedTime++;
                    break;
            }
        }
        return z;
    }

    protected void b(ReaderTask readerTask) {
        if (readerTask.getFailedType() == 2) {
            this.b.remove(readerTask.getTaskKey());
        }
    }

    protected ReaderTask a(String str) {
        return (ReaderTask) this.b.get(str);
    }

    protected ArrayList<ReaderTask> c(ReaderTask readerTask) {
        ArrayList<ReaderTask> arrayList = new ArrayList();
        for (Entry value : this.b.entrySet()) {
            ReaderTask readerTask2 = (ReaderTask) value.getValue();
            if (readerTask2.isSameKindofTask(readerTask)) {
                arrayList.add(readerTask2);
            }
        }
        return arrayList;
    }

    protected ReaderTask b() throws InterruptedException {
        return (ReaderTask) this.a.take();
    }

    public void c() {
        synchronized (c.class) {
            c = null;
        }
    }
}

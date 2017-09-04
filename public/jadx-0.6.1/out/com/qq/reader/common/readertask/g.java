package com.qq.reader.common.readertask;

import com.qq.reader.common.imageloader.a.a.b.a;
import com.qq.reader.common.imageloader.core.assist.QueueProcessingType;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.common.readertask.ordinal.ReaderNetEmptyTask;
import com.qq.reader.common.readertask.ordinal.ReaderNetTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.tencent.android.tpush.common.Constants;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: ReaderTaskHandler */
public class g {
    private static g b;
    private static ThreadPoolExecutor c;
    private static ThreadPoolExecutor d;
    d a = null;
    private Executor e = null;
    private ExecutorService f = null;
    private final int g = 5;
    private Object h = new Object();
    private BlockingQueue<ReaderTask> i = new LinkedBlockingQueue();
    private Thread j = new Thread(new Runnable(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public void run() {
            Thread.currentThread().setName("readerTaskAddDispatch Thread");
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    ReaderNetTask readerNetTask = (ReaderNetTask) this.a.i.take();
                    if (!this.a.b(readerNetTask)) {
                        this.a.a(readerNetTask);
                    }
                } catch (Exception e) {
                    f.a("readerTaskAddDispatch", "readerTaskAddDispatch is interrupted for shutting down.", e);
                } finally {
                    f.a("readerTaskAddDispatch", "readerTaskAddDispatch thread is terminated.");
                }
            }
        }
    });

    private g() {
        c = new ThreadPoolExecutor(4, 10, 300, TimeUnit.SECONDS, new PriorityBlockingQueue());
        d = new ThreadPoolExecutor(3, 10, 300, TimeUnit.SECONDS, new PriorityBlockingQueue(100));
        this.e = a.a(3, 4, QueueProcessingType.FIFO);
        this.f = Executors.newFixedThreadPool(5);
        this.j.start();
    }

    public static synchronized g a() {
        g gVar;
        synchronized (g.class) {
            if (b == null) {
                b = new g();
            }
            gVar = b;
        }
        return gVar;
    }

    public void a(ReaderTask readerTask) {
        if (readerTask instanceof ReaderNetTask) {
            if (readerTask.getPriority() == 5) {
                this.f.submit(readerTask);
                return;
            }
            this.i.add(readerTask);
            this.i.add(new ReaderNetEmptyTask());
        } else if (readerTask instanceof ReaderIOTask) {
            this.e.execute(readerTask);
        } else if (readerTask != null) {
            this.a = e.a().a(readerTask);
            if (this.a.a(readerTask, readerTask.getDelayTime())) {
                c.a(Constants.LogTag, "add to readerQueue ok");
            } else {
                c.a(Constants.LogTag, "add to readerQueue error");
            }
        }
    }

    private void a(ReaderNetTask readerNetTask) {
        try {
            if (readerNetTask.getPriority() == 4) {
                synchronized (this.h) {
                    for (Runnable runnable : d.getQueue()) {
                        ReaderNetTask readerNetTask2 = (ReaderNetTask) runnable;
                        int priority = readerNetTask2.getPriority();
                        if (priority > 1) {
                            readerNetTask2.setPriority(priority - 1);
                        }
                    }
                    d.execute(readerNetTask);
                }
                return;
            }
            synchronized (this.h) {
                c.execute(readerNetTask);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(ReaderTask readerTask, long j) {
        this.a = e.a().a(readerTask);
        this.a.a(readerTask, j);
    }

    public void b(ReaderTask readerTask) {
        if (readerTask != null) {
            if ((readerTask instanceof ReaderShortTask) || (readerTask instanceof ReaderDBTask)) {
                this.a = e.a().a(readerTask);
                this.a.a(readerTask);
            }
            if (readerTask instanceof ReaderNetTask) {
                ReaderNetTask readerNetTask = (ReaderNetTask) readerTask;
                synchronized (this.h) {
                    Thread currentThread = readerNetTask.getCurrentThread();
                    if (currentThread != null) {
                        currentThread.interrupt();
                    }
                    c.remove(readerTask);
                    d.remove(readerTask);
                }
            }
        }
    }

    private boolean a(ReaderNetTask readerNetTask, ThreadPoolExecutor threadPoolExecutor) {
        synchronized (this.h) {
            for (Runnable runnable : threadPoolExecutor.getQueue()) {
                ReaderNetTask readerNetTask2 = (ReaderNetTask) runnable;
                if (readerNetTask2.equals(readerNetTask)) {
                    if ((readerNetTask2 instanceof ReaderProtocolTask) && (readerNetTask instanceof ReaderProtocolTask)) {
                        ((ReaderProtocolTask) readerNetTask2).registerNetTaskListener(((ReaderProtocolTask) readerNetTask).getRegisterNetTaskListener());
                    }
                    return true;
                }
            }
            return false;
        }
    }

    private boolean b(ReaderNetTask readerNetTask) {
        if (readerNetTask.getPriority() == 4) {
            if (d != null) {
                return a(readerNetTask, d);
            }
        } else if (c != null) {
            return a(readerNetTask, c);
        }
        return false;
    }
}

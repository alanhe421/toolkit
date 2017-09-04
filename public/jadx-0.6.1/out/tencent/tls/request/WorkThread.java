package tencent.tls.request;

import android.os.Handler;
import android.os.Looper;
import tencent.tls.report.QLog;

public class WorkThread extends Thread {
    private static Thread loopThread;
    private static Looper selfLooper;
    private Handler handler;
    private int ret;
    private When when;
    private Worker worker;

    public interface Worker {
        int work();
    }

    public interface When {
        void done(int i);
    }

    public WorkThread(Looper looper, Worker worker, When when) {
        this.worker = worker;
        this.when = when;
        if (looper != null) {
            this.handler = new Handler(looper);
        } else if (loopThread == null || !loopThread.isAlive()) {
            loopThread = new Thread(new Runnable() {
                public void run() {
                    Looper.prepare();
                    WorkThread.selfLooper = Looper.myLooper();
                    WorkThread.this.handler = new Handler();
                    Looper.loop();
                }
            });
            loopThread.setName("TLSLoopThread-" + loopThread.getId());
            loopThread.setDaemon(true);
            loopThread.start();
        } else {
            this.handler = new Handler(selfLooper);
        }
    }

    public void run() {
        try {
            QLog.i("run at " + Thread.currentThread().getName());
            this.ret = this.worker.work();
            this.handler.post(new Runnable() {
                public void run() {
                    QLog.i("receive at " + Thread.currentThread().getName());
                    try {
                        WorkThread.this.when.done(WorkThread.this.ret);
                    } catch (Throwable e) {
                        QLog.e(e);
                    }
                }
            });
        } catch (Throwable e) {
            QLog.e(e);
        }
    }
}

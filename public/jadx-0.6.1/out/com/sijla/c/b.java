package com.sijla.c;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class b<Params, Progress, Result> {
    public static final ThreadPoolExecutor a = new ThreadPoolExecutor(e, 30, 3, TimeUnit.SECONDS, g, f);
    public static final Executor b = new d();
    protected static final c c;
    private static int d = Runtime.getRuntime().availableProcessors();
    private static final int e = d;
    private static final ThreadFactory f = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "AsyncTask #" + this.a.getAndIncrement());
        }
    };
    private static final BlockingQueue<Runnable> g = new SynchronousQueue();
    private static volatile Executor h = a;
    private volatile e i;
    private final AtomicBoolean j;
    private b k;

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] b = new int[e.values().length];

        static {
            try {
                b[e.RUNNING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[e.FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            a = new int[a.values().length];
            try {
                a[a.LIFO.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[a.FIFO.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private static class a<Data> {
        final b a;
        final Data[] b;
    }

    public interface b {
        void a();

        void b();
    }

    private static class c extends Handler {
        public c(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            a aVar = (a) message.obj;
            switch (message.what) {
                case 1:
                    aVar.a.c(aVar.b[0]);
                    return;
                case 2:
                    aVar.a.a(aVar.b);
                    return;
                default:
                    return;
            }
        }
    }

    private static class d implements Executor {
        private static int c;
        private static int d;
        private a<Runnable> a = new a(d);
        private a b = a.LIFO;
        private int e = b.d;

        private enum a {
            LIFO,
            FIFO
        }

        private void a(int i) {
            this.e = i;
            c = i;
            d = (i + 3) * 16;
        }

        public d() {
            a(b.d);
        }

        public synchronized void execute(final Runnable runnable) {
            Runnable anonymousClass1 = new Runnable(this) {
                final /* synthetic */ d b;

                public void run() {
                    runnable.run();
                    this.b.a();
                }
            };
            if (b.a.getActiveCount() < c) {
                b.a.execute(anonymousClass1);
            } else {
                if (this.a.c() >= d) {
                    this.a.a();
                }
                this.a.b(anonymousClass1);
            }
        }

        public synchronized void a() {
            Runnable runnable;
            switch (this.b) {
                case LIFO:
                    runnable = (Runnable) this.a.b();
                    break;
                case FIFO:
                    runnable = (Runnable) this.a.a();
                    break;
                default:
                    runnable = (Runnable) this.a.b();
                    break;
            }
            if (runnable != null) {
                b.a.execute(runnable);
            }
        }
    }

    public enum e {
        PENDING,
        RUNNING,
        FINISHED
    }

    static {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            c = new c(Looper.getMainLooper());
        } else {
            c = new c();
        }
    }

    protected void a(Result result) {
    }

    protected void a(Progress... progressArr) {
    }

    protected void b(Result result) {
        a();
    }

    protected void a() {
    }

    public final boolean b() {
        return this.j.get();
    }

    public static void a(Runnable runnable) {
        try {
            h.execute(runnable);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void c(Result result) {
        if (b()) {
            b(result);
            if (this.k != null) {
                this.k.a();
            }
        } else {
            a((Object) result);
            if (this.k != null) {
                this.k.b();
            }
        }
        this.i = e.FINISHED;
    }
}

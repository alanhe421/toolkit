package rx.internal.schedulers;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.d.a;
import rx.d.d;
import rx.d.e;
import rx.f;
import rx.internal.util.c;
import rx.internal.util.g;

/* compiled from: NewThreadWorker */
public class b extends a implements f {
    public static final int b = Integer.getInteger("rx.scheduler.jdk6.purge-frequency-millis", 1000).intValue();
    private static final boolean e;
    private static final ConcurrentHashMap<ScheduledThreadPoolExecutor, ScheduledThreadPoolExecutor> f = new ConcurrentHashMap();
    private static final AtomicReference<ScheduledExecutorService> g = new AtomicReference();
    private static volatile Object h;
    private static final Object i = new Object();
    volatile boolean a;
    private final ScheduledExecutorService c;
    private final e d;

    static {
        boolean z = Boolean.getBoolean("rx.scheduler.jdk6.purge-force");
        int b = c.b();
        if (z || (b != 0 && b < 21)) {
            z = false;
        } else {
            z = true;
        }
        e = z;
    }

    public static void a(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        while (((ScheduledExecutorService) g.get()) == null) {
            ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, new rx.internal.util.e("RxSchedulerPurge-"));
            if (g.compareAndSet(null, newScheduledThreadPool)) {
                newScheduledThreadPool.scheduleAtFixedRate(new Runnable() {
                    public void run() {
                        b.b();
                    }
                }, (long) b, (long) b, TimeUnit.MILLISECONDS);
                break;
            }
        }
        f.putIfAbsent(scheduledThreadPoolExecutor, scheduledThreadPoolExecutor);
    }

    public static void a(ScheduledExecutorService scheduledExecutorService) {
        f.remove(scheduledExecutorService);
    }

    static void b() {
        try {
            Iterator it = f.keySet().iterator();
            while (it.hasNext()) {
                ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) it.next();
                if (scheduledThreadPoolExecutor.isShutdown()) {
                    it.remove();
                } else {
                    scheduledThreadPoolExecutor.purge();
                }
            }
        } catch (Throwable th) {
            rx.exceptions.a.a(th);
            d.a().b().a(th);
        }
    }

    public static boolean b(ScheduledExecutorService scheduledExecutorService) {
        if (e) {
            Method c;
            if (scheduledExecutorService instanceof ScheduledThreadPoolExecutor) {
                Object obj = h;
                if (obj == i) {
                    return false;
                }
                if (obj == null) {
                    c = c(scheduledExecutorService);
                    if (c != null) {
                        obj = c;
                    } else {
                        obj = i;
                    }
                    h = obj;
                } else {
                    c = (Method) obj;
                }
            } else {
                c = c(scheduledExecutorService);
            }
            if (c != null) {
                try {
                    c.invoke(scheduledExecutorService, new Object[]{Boolean.valueOf(true)});
                    return true;
                } catch (Throwable e) {
                    d.a().b().a(e);
                }
            }
        }
        return false;
    }

    static Method c(ScheduledExecutorService scheduledExecutorService) {
        for (Method method : scheduledExecutorService.getClass().getMethods()) {
            if (method.getName().equals("setRemoveOnCancelPolicy")) {
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1 && parameterTypes[0] == Boolean.TYPE) {
                    return method;
                }
            }
        }
        return null;
    }

    public b(ThreadFactory threadFactory) {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, threadFactory);
        if (!b(newScheduledThreadPool) && (newScheduledThreadPool instanceof ScheduledThreadPoolExecutor)) {
            a((ScheduledThreadPoolExecutor) newScheduledThreadPool);
        }
        this.d = d.a().d();
        this.c = newScheduledThreadPool;
    }

    public f a(rx.b.a aVar) {
        return a(aVar, 0, null);
    }

    public f a(rx.b.a aVar, long j, TimeUnit timeUnit) {
        if (this.a) {
            return rx.f.e.b();
        }
        return b(aVar, j, timeUnit);
    }

    public ScheduledAction b(rx.b.a aVar, long j, TimeUnit timeUnit) {
        Future submit;
        Runnable scheduledAction = new ScheduledAction(this.d.a(aVar));
        if (j <= 0) {
            submit = this.c.submit(scheduledAction);
        } else {
            submit = this.c.schedule(scheduledAction, j, timeUnit);
        }
        scheduledAction.add(submit);
        return scheduledAction;
    }

    public ScheduledAction a(rx.b.a aVar, long j, TimeUnit timeUnit, rx.f.b bVar) {
        Future submit;
        f scheduledAction = new ScheduledAction(this.d.a(aVar), bVar);
        bVar.a(scheduledAction);
        if (j <= 0) {
            submit = this.c.submit(scheduledAction);
        } else {
            submit = this.c.schedule(scheduledAction, j, timeUnit);
        }
        scheduledAction.add(submit);
        return scheduledAction;
    }

    public ScheduledAction a(rx.b.a aVar, long j, TimeUnit timeUnit, g gVar) {
        Future submit;
        f scheduledAction = new ScheduledAction(this.d.a(aVar), gVar);
        gVar.a(scheduledAction);
        if (j <= 0) {
            submit = this.c.submit(scheduledAction);
        } else {
            submit = this.c.schedule(scheduledAction, j, timeUnit);
        }
        scheduledAction.add(submit);
        return scheduledAction;
    }

    public void unsubscribe() {
        this.a = true;
        this.c.shutdownNow();
        a(this.c);
    }

    public boolean isUnsubscribed() {
        return this.a;
    }
}

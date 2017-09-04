package rx.internal.schedulers;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import rx.d.d;
import rx.exceptions.OnErrorNotImplementedException;
import rx.f;
import rx.f.b;
import rx.internal.util.g;

public final class ScheduledAction extends AtomicReference<Thread> implements Runnable, f {
    private static final long serialVersionUID = -3962399486978279857L;
    final rx.b.a action;
    final g cancel;

    private static final class Remover2 extends AtomicBoolean implements f {
        private static final long serialVersionUID = 247232374289553518L;
        final g parent;
        final ScheduledAction s;

        public Remover2(ScheduledAction scheduledAction, g gVar) {
            this.s = scheduledAction;
            this.parent = gVar;
        }

        public boolean isUnsubscribed() {
            return this.s.isUnsubscribed();
        }

        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.parent.b(this.s);
            }
        }
    }

    private static final class Remover extends AtomicBoolean implements f {
        private static final long serialVersionUID = 247232374289553518L;
        final b parent;
        final ScheduledAction s;

        public Remover(ScheduledAction scheduledAction, b bVar) {
            this.s = scheduledAction;
            this.parent = bVar;
        }

        public boolean isUnsubscribed() {
            return this.s.isUnsubscribed();
        }

        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.parent.b(this.s);
            }
        }
    }

    private final class a implements f {
        final /* synthetic */ ScheduledAction a;
        private final Future<?> b;

        private a(ScheduledAction scheduledAction, Future<?> future) {
            this.a = scheduledAction;
            this.b = future;
        }

        public void unsubscribe() {
            if (this.a.get() != Thread.currentThread()) {
                this.b.cancel(true);
            } else {
                this.b.cancel(false);
            }
        }

        public boolean isUnsubscribed() {
            return this.b.isCancelled();
        }
    }

    public ScheduledAction(rx.b.a aVar) {
        this.action = aVar;
        this.cancel = new g();
    }

    public ScheduledAction(rx.b.a aVar, b bVar) {
        this.action = aVar;
        this.cancel = new g(new Remover(this, bVar));
    }

    public ScheduledAction(rx.b.a aVar, g gVar) {
        this.action = aVar;
        this.cancel = new g(new Remover2(this, gVar));
    }

    public void run() {
        try {
            lazySet(Thread.currentThread());
            this.action.call();
        } catch (Throwable th) {
            Throwable th2;
            if (th2 instanceof OnErrorNotImplementedException) {
                th2 = new IllegalStateException("Exception thrown on Scheduler.Worker thread. Add `onError` handling.", th2);
            } else {
                th2 = new IllegalStateException("Fatal Exception thrown on Scheduler.Worker thread.", th2);
            }
            d.a().b().a(th2);
            Thread currentThread = Thread.currentThread();
            currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th2);
        } finally {
            unsubscribe();
        }
    }

    public boolean isUnsubscribed() {
        return this.cancel.isUnsubscribed();
    }

    public void unsubscribe() {
        if (!this.cancel.isUnsubscribed()) {
            this.cancel.unsubscribe();
        }
    }

    public void add(f fVar) {
        this.cancel.a(fVar);
    }

    public void add(Future<?> future) {
        this.cancel.a(new a(future));
    }

    public void addParent(b bVar) {
        this.cancel.a(new Remover(this, bVar));
    }

    public void addParent(g gVar) {
        this.cancel.a(new Remover2(this, gVar));
    }
}

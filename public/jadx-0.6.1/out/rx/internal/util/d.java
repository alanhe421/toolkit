package rx.internal.util;

import java.util.Queue;
import rx.exceptions.MissingBackpressureException;
import rx.f;
import rx.internal.operators.NotificationLite;
import rx.internal.util.a.ae;
import rx.internal.util.a.o;
import rx.internal.util.a.w;

/* compiled from: RxRingBuffer */
public class d implements f {
    static int b;
    public static final int c = b;
    private static final NotificationLite<Object> d = NotificationLite.a();
    private static b<Queue<Object>> h = new b<Queue<Object>>() {
        protected /* synthetic */ Object b() {
            return c();
        }

        protected w<Object> c() {
            return new w(d.c);
        }
    };
    private static b<Queue<Object>> i = new b<Queue<Object>>() {
        protected /* synthetic */ Object b() {
            return c();
        }

        protected o<Object> c() {
            return new o(d.c);
        }
    };
    public volatile Object a;
    private Queue<Object> e;
    private final int f;
    private final b<Queue<Object>> g;

    public static d a() {
        if (ae.a()) {
            return new d(h, c);
        }
        return new d();
    }

    public static d b() {
        if (ae.a()) {
            return new d(i, c);
        }
        return new d();
    }

    static {
        b = 128;
        if (c.a()) {
            b = 16;
        }
        String property = System.getProperty("rx.ring-buffer.size");
        if (property != null) {
            try {
                b = Integer.parseInt(property);
            } catch (Exception e) {
                System.err.println("Failed to set 'rx.buffer.size' with value " + property + " => " + e.getMessage());
            }
        }
    }

    private d(Queue<Object> queue, int i) {
        this.e = queue;
        this.g = null;
        this.f = i;
    }

    private d(b<Queue<Object>> bVar, int i) {
        this.g = bVar;
        this.e = (Queue) bVar.a();
        this.f = i;
    }

    public synchronized void c() {
        Object obj = this.e;
        b bVar = this.g;
        if (!(bVar == null || obj == null)) {
            obj.clear();
            this.e = null;
            bVar.a(obj);
        }
    }

    public void unsubscribe() {
        c();
    }

    d() {
        this(new h(c), c);
    }

    public void a(Object obj) throws MissingBackpressureException {
        Object obj2 = 1;
        Object obj3 = null;
        synchronized (this) {
            Queue queue = this.e;
            if (queue == null) {
                int i = 1;
                obj2 = null;
            } else if (queue.offer(d.a(obj))) {
                obj2 = null;
            }
        }
        if (obj3 != null) {
            throw new IllegalStateException("This instance has been unsubscribed and the queue is no longer usable.");
        } else if (obj2 != null) {
            throw new MissingBackpressureException();
        }
    }

    public void d() {
        if (this.a == null) {
            this.a = d.b();
        }
    }

    public boolean e() {
        Queue queue = this.e;
        if (queue == null) {
            return true;
        }
        return queue.isEmpty();
    }

    public Object f() {
        Object obj = null;
        synchronized (this) {
            Queue queue = this.e;
            if (queue == null) {
            } else {
                Object poll = queue.poll();
                obj = this.a;
                if (poll == null && obj != null && queue.peek() == null) {
                    this.a = null;
                } else {
                    obj = poll;
                }
            }
        }
        return obj;
    }

    public Object g() {
        Object obj;
        synchronized (this) {
            Queue queue = this.e;
            if (queue == null) {
                obj = null;
            } else {
                Object peek = queue.peek();
                obj = this.a;
                if (!(peek == null && obj != null && queue.peek() == null)) {
                    obj = peek;
                }
            }
        }
        return obj;
    }

    public boolean b(Object obj) {
        return d.b(obj);
    }

    public Object c(Object obj) {
        return d.d(obj);
    }

    public boolean isUnsubscribed() {
        return this.e == null;
    }
}

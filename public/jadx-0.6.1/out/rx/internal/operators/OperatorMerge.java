package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import rx.c;
import rx.e;
import rx.exceptions.CompositeException;
import rx.f;
import rx.internal.util.d;

public final class OperatorMerge<T> implements rx.a.b<T, rx.a<? extends T>> {
    final boolean a;
    final int b;

    static final class MergeProducer<T> extends AtomicLong implements c {
        private static final long serialVersionUID = -1214379189873595503L;
        final b<T> subscriber;

        public MergeProducer(b<T> bVar) {
            this.subscriber = bVar;
        }

        public void request(long j) {
            if (j > 0) {
                if (get() != Long.MAX_VALUE) {
                    a.a(this, j);
                    this.subscriber.d();
                }
            } else if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required");
            }
        }

        public long produced(int i) {
            return addAndGet((long) (-i));
        }
    }

    static final class a<T> extends e<T> {
        static final int f = (d.c / 4);
        final b<T> a;
        final long b;
        volatile boolean c;
        volatile d d;
        int e;

        public a(b<T> bVar, long j) {
            this.a = bVar;
            this.b = j;
        }

        public void a() {
            this.e = d.c;
            a((long) d.c);
        }

        public void onNext(T t) {
            this.a.a(this, (Object) t);
        }

        public void onError(Throwable th) {
            this.c = true;
            this.a.b().offer(th);
            this.a.d();
        }

        public void onCompleted() {
            this.c = true;
            this.a.d();
        }

        public void b(long j) {
            int i = this.e - ((int) j);
            if (i > f) {
                this.e = i;
                return;
            }
            this.e = d.c;
            i = d.c - i;
            if (i > 0) {
                a((long) i);
            }
        }
    }

    static final class b<T> extends e<rx.a<? extends T>> {
        static final a<?>[] q = new a[0];
        final e<? super T> a;
        final boolean b;
        final int c;
        MergeProducer<T> d;
        volatile d e;
        volatile rx.f.b f;
        volatile ConcurrentLinkedQueue<Throwable> g;
        final NotificationLite<T> h = NotificationLite.a();
        volatile boolean i;
        boolean j;
        boolean k;
        final Object l = new Object();
        volatile a<?>[] m = q;
        long n;
        long o;
        int p;

        public /* synthetic */ void onNext(Object obj) {
            a((rx.a) obj);
        }

        public b(e<? super T> eVar, boolean z, int i) {
            this.a = eVar;
            this.b = z;
            this.c = i;
            a((long) Math.min(i, d.c));
        }

        Queue<Throwable> b() {
            Queue<Throwable> queue = this.g;
            if (queue == null) {
                synchronized (this) {
                    queue = this.g;
                    if (queue == null) {
                        queue = new ConcurrentLinkedQueue();
                        this.g = queue;
                    }
                }
            }
            return queue;
        }

        rx.f.b c() {
            rx.f.b bVar = this.f;
            if (bVar == null) {
                Object obj;
                synchronized (this) {
                    rx.f.b bVar2 = this.f;
                    if (bVar2 == null) {
                        bVar2 = new rx.f.b();
                        this.f = bVar2;
                        bVar = bVar2;
                        obj = 1;
                    } else {
                        bVar = bVar2;
                        obj = null;
                    }
                }
                if (obj != null) {
                    a((f) bVar);
                }
            }
            return bVar;
        }

        public void a(rx.a<? extends T> aVar) {
            if (aVar != null) {
                if (aVar instanceof rx.internal.util.f) {
                    a(((rx.internal.util.f) aVar).c());
                    return;
                }
                long j = this.n;
                this.n = 1 + j;
                e aVar2 = new a(this, j);
                a((a) aVar2);
                aVar.a(aVar2);
                d();
            }
        }

        private void g() {
            Collection arrayList = new ArrayList(this.g);
            if (arrayList.size() == 1) {
                this.a.onError((Throwable) arrayList.get(0));
            } else {
                this.a.onError(new CompositeException(arrayList));
            }
        }

        public void onError(Throwable th) {
            b().offer(th);
            this.i = true;
            d();
        }

        public void onCompleted() {
            this.i = true;
            d();
        }

        void a(a<T> aVar) {
            c().a((f) aVar);
            synchronized (this.l) {
                Object obj = this.m;
                int length = obj.length;
                Object obj2 = new a[(length + 1)];
                System.arraycopy(obj, 0, obj2, 0, length);
                obj2[length] = aVar;
                this.m = obj2;
            }
        }

        void b(a<T> aVar) {
            int i = 0;
            d dVar = aVar.d;
            if (dVar != null) {
                dVar.c();
            }
            this.f.b(aVar);
            synchronized (this.l) {
                Object obj = this.m;
                int length = obj.length;
                while (i < length) {
                    if (aVar.equals(obj[i])) {
                        break;
                    }
                    i++;
                }
                i = -1;
                if (i < 0) {
                } else if (length == 1) {
                    this.m = q;
                } else {
                    Object obj2 = new a[(length - 1)];
                    System.arraycopy(obj, 0, obj2, 0, i);
                    System.arraycopy(obj, i + 1, obj2, i, (length - i) - 1);
                    this.m = obj2;
                }
            }
        }

        void a(a<T> aVar, T t) {
            Object obj = null;
            long j = this.d.get();
            if (j != 0) {
                synchronized (this) {
                    j = this.d.get();
                    if (!(this.j || j == 0)) {
                        this.j = true;
                        obj = 1;
                    }
                }
            }
            if (obj != null) {
                a(aVar, t, j);
            } else {
                b(aVar, t);
            }
        }

        protected void b(a<T> aVar, T t) {
            d dVar = aVar.d;
            if (dVar == null) {
                dVar = d.a();
                aVar.a((f) dVar);
                aVar.d = dVar;
            }
            try {
                dVar.a(this.h.a((Object) t));
                d();
            } catch (Throwable e) {
                aVar.unsubscribe();
                aVar.onError(e);
            } catch (Throwable e2) {
                if (!aVar.isUnsubscribed()) {
                    aVar.unsubscribe();
                    aVar.onError(e2);
                }
            }
        }

        protected void a(a<T> aVar, T t, long j) {
            Throwable th;
            Object obj = null;
            try {
                this.a.onNext(t);
            } catch (Throwable th2) {
                th = th2;
                obj = 1;
            }
            if (j != Long.MAX_VALUE) {
                this.d.produced(1);
            }
            aVar.b(1);
            synchronized (this) {
                if (this.k) {
                    this.k = false;
                    e();
                    return;
                }
                this.j = false;
                return;
            }
            if (obj == null) {
                synchronized (this) {
                    this.j = false;
                }
            }
            throw th;
        }

        public void b(long j) {
            a(j);
        }

        void a(T t) {
            Object obj = null;
            long j = this.d.get();
            if (j != 0) {
                synchronized (this) {
                    j = this.d.get();
                    if (!(this.j || j == 0)) {
                        this.j = true;
                        obj = 1;
                    }
                }
            }
            if (obj != null) {
                a((Object) t, j);
            } else {
                b((Object) t);
            }
        }

        protected void b(T t) {
            d dVar = this.e;
            if (dVar == null) {
                dVar = d.a();
                a((f) dVar);
                this.e = dVar;
            }
            try {
                dVar.a(this.h.a((Object) t));
                d();
            } catch (Throwable e) {
                unsubscribe();
                onError(e);
            } catch (Throwable e2) {
                if (!isUnsubscribed()) {
                    unsubscribe();
                    onError(e2);
                }
            }
        }

        protected void a(T t, long j) {
            Throwable th;
            Object obj = null;
            try {
                this.a.onNext(t);
            } catch (Throwable th2) {
                th = th2;
                obj = 1;
            }
            if (j != Long.MAX_VALUE) {
                this.d.produced(1);
            }
            b(1);
            synchronized (this) {
                if (this.k) {
                    this.k = false;
                    e();
                    return;
                }
                this.j = false;
                return;
            }
            if (obj == null) {
                synchronized (this) {
                    this.j = false;
                }
            }
            throw th;
        }

        void d() {
            synchronized (this) {
                if (this.j) {
                    this.k = true;
                    return;
                }
                this.j = true;
                e();
            }
        }

        void e() {
            Object obj = null;
            e eVar = this.a;
            while (!f()) {
                Object obj2;
                long j;
                int i;
                boolean z;
                d dVar;
                a[] aVarArr;
                int length;
                a aVar;
                int i2;
                d dVar2;
                d dVar3 = this.e;
                long j2 = this.d.get();
                if (j2 == Long.MAX_VALUE) {
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                int i3 = 0;
                Object obj3;
                int i4;
                int i5;
                int i6;
                int i7;
                Object obj4;
                int i8;
                if (dVar3 != null) {
                    do {
                        i4 = 0;
                        obj3 = null;
                        while (j2 > 0) {
                            obj3 = dVar3.f();
                            if (!f()) {
                                if (obj3 == null) {
                                    break;
                                }
                                try {
                                    eVar.onNext(this.h.d(obj3));
                                } catch (Throwable th) {
                                    try {
                                        if (this.b) {
                                            b().offer(th);
                                        } else {
                                            rx.exceptions.a.a(th);
                                            unsubscribe();
                                            eVar.onError(th);
                                            return;
                                        }
                                    } catch (Throwable th2) {
                                        if (obj == null) {
                                            synchronized (this) {
                                                this.j = false;
                                            }
                                        }
                                    }
                                }
                                j2--;
                                i4++;
                                i3++;
                            } else {
                                return;
                            }
                        }
                        if (i4 > 0) {
                            if (obj2 != null) {
                                j2 = Long.MAX_VALUE;
                            } else {
                                j2 = this.d.produced(i4);
                            }
                        }
                        if (j2 != 0) {
                        }
                    } while (obj3 != null);
                    j = j2;
                    i = i3;
                    z = this.i;
                    dVar = this.e;
                    aVarArr = this.m;
                    length = aVarArr.length;
                    if (z || !((dVar == null || dVar.e()) && length == 0)) {
                        if (length <= 0) {
                            long j3 = this.o;
                            i3 = this.p;
                            if (length <= i3 || aVarArr[i3].b != j3) {
                                if (length <= i3) {
                                    i3 = 0;
                                }
                                for (i5 = 0; i5 < length && aVarArr[i3].b != j3; i5++) {
                                    i3++;
                                    if (i3 == length) {
                                        i3 = 0;
                                    }
                                }
                                this.p = i3;
                                this.o = aVarArr[i3].b;
                            }
                            i6 = i3;
                            i4 = i;
                            j2 = j;
                            i7 = 0;
                            obj3 = null;
                            while (i7 < length) {
                                if (!f()) {
                                    aVar = aVarArr[i6];
                                    obj4 = null;
                                    do {
                                        i2 = 0;
                                        while (j2 > 0) {
                                            if (!f()) {
                                                dVar2 = aVar.d;
                                                if (dVar2 != null) {
                                                    obj4 = dVar2.f();
                                                    if (obj4 != null) {
                                                        break;
                                                    }
                                                    try {
                                                        eVar.onNext(this.h.d(obj4));
                                                        i2++;
                                                        j2--;
                                                    } catch (Throwable th3) {
                                                        obj = 1;
                                                        rx.exceptions.a.a(th3);
                                                        eVar.onError(th3);
                                                        return;
                                                    } finally {
                                                        unsubscribe();
                                                    }
                                                } else {
                                                    break;
                                                }
                                            }
                                            return;
                                        }
                                        if (i2 > 0) {
                                            if (obj2 != null) {
                                                j2 = this.d.produced(i2);
                                            } else {
                                                j2 = Long.MAX_VALUE;
                                            }
                                            aVar.b((long) i2);
                                        }
                                        if (j2 == 0) {
                                            break;
                                        }
                                    } while (obj4 != null);
                                    z = aVar.c;
                                    d dVar4 = aVar.d;
                                    if (z && (dVar4 == null || dVar4.e())) {
                                        b(aVar);
                                        if (!f()) {
                                            i4++;
                                            obj3 = 1;
                                        } else {
                                            return;
                                        }
                                    }
                                    if (j2 == 0) {
                                        obj4 = obj3;
                                        i8 = i4;
                                        break;
                                    }
                                    i3 = i6 + 1;
                                    if (i3 == length) {
                                        i3 = 0;
                                    }
                                    i7++;
                                    i6 = i3;
                                } else {
                                    return;
                                }
                            }
                            obj4 = obj3;
                            i8 = i4;
                            this.p = i6;
                            this.o = aVarArr[i6].b;
                        } else {
                            obj4 = null;
                            i8 = i;
                        }
                        if (i8 > 0) {
                            a((long) i8);
                        }
                        if (obj4 == null) {
                            synchronized (this) {
                                if (this.k) {
                                    this.j = false;
                                    return;
                                }
                                this.k = false;
                            }
                        }
                    } else {
                        Queue queue = this.g;
                        if (queue == null || queue.isEmpty()) {
                            eVar.onCompleted();
                        } else {
                            g();
                        }
                        if (dVar != null) {
                            dVar.c();
                            return;
                        }
                        return;
                    }
                }
                j = j2;
                i = i3;
                z = this.i;
                dVar = this.e;
                aVarArr = this.m;
                length = aVarArr.length;
                if (z) {
                }
                if (length <= 0) {
                    obj4 = null;
                    i8 = i;
                } else {
                    long j32 = this.o;
                    i3 = this.p;
                    if (length <= i3) {
                        i3 = 0;
                    }
                    for (i5 = 0; i5 < length; i5++) {
                        i3++;
                        if (i3 == length) {
                            i3 = 0;
                        }
                    }
                    this.p = i3;
                    this.o = aVarArr[i3].b;
                    i6 = i3;
                    i4 = i;
                    j2 = j;
                    i7 = 0;
                    obj3 = null;
                    while (i7 < length) {
                        if (!f()) {
                            aVar = aVarArr[i6];
                            obj4 = null;
                            do {
                                i2 = 0;
                                while (j2 > 0) {
                                    if (!f()) {
                                        dVar2 = aVar.d;
                                        if (dVar2 != null) {
                                            break;
                                        }
                                        obj4 = dVar2.f();
                                        if (obj4 != null) {
                                            break;
                                        }
                                        eVar.onNext(this.h.d(obj4));
                                        i2++;
                                        j2--;
                                    } else {
                                        return;
                                    }
                                }
                                if (i2 > 0) {
                                    if (obj2 != null) {
                                        j2 = Long.MAX_VALUE;
                                    } else {
                                        j2 = this.d.produced(i2);
                                    }
                                    aVar.b((long) i2);
                                }
                                if (j2 == 0) {
                                    break;
                                }
                                break;
                            } while (obj4 != null);
                            z = aVar.c;
                            d dVar42 = aVar.d;
                            b(aVar);
                            if (!f()) {
                                i4++;
                                obj3 = 1;
                                if (j2 == 0) {
                                    obj4 = obj3;
                                    i8 = i4;
                                    break;
                                }
                                i3 = i6 + 1;
                                if (i3 == length) {
                                    i3 = 0;
                                }
                                i7++;
                                i6 = i3;
                            } else {
                                return;
                            }
                        }
                        return;
                    }
                    obj4 = obj3;
                    i8 = i4;
                    this.p = i6;
                    this.o = aVarArr[i6].b;
                }
                if (i8 > 0) {
                    a((long) i8);
                }
                if (obj4 == null) {
                    synchronized (this) {
                        if (this.k) {
                            this.k = false;
                        } else {
                            this.j = false;
                            return;
                        }
                    }
                }
            }
        }

        boolean f() {
            if (this.a.isUnsubscribed()) {
                return true;
            }
            Queue queue = this.g;
            if (this.b || queue == null || queue.isEmpty()) {
                return false;
            }
            try {
                g();
                return true;
            } finally {
                unsubscribe();
            }
        }
    }

    public /* synthetic */ Object call(Object obj) {
        return a((e) obj);
    }

    public e<rx.a<? extends T>> a(e<? super T> eVar) {
        f bVar = new b(eVar, this.a, this.b);
        c mergeProducer = new MergeProducer(bVar);
        bVar.d = mergeProducer;
        eVar.a(bVar);
        eVar.a(mergeProducer);
        return bVar;
    }
}

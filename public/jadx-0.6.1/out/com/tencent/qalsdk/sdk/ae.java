package com.tencent.qalsdk.sdk;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: MyLinkedBlockingDeque */
public class ae<E> extends AbstractQueue<E> implements Serializable {
    private static final long d = -387911632671998426L;
    transient d<E> a;
    transient d<E> b;
    final ReentrantLock c;
    private transient int e;
    private final int f;
    private final Condition g;
    private final Condition h;

    /* compiled from: MyLinkedBlockingDeque */
    private abstract class a implements Iterator<E> {
        d<E> a;
        E b;
        final /* synthetic */ ae c;
        private d<E> d;

        abstract d<E> a();

        abstract d<E> a(d<E> dVar);

        a(ae aeVar) {
            this.c = aeVar;
            ReentrantLock reentrantLock = aeVar.c;
            reentrantLock.lock();
            try {
                this.a = a();
                this.b = this.a == null ? null : this.a.a;
            } finally {
                reentrantLock.unlock();
            }
        }

        private d<E> b(d<E> dVar) {
            while (true) {
                d<E> a = a(dVar);
                if (a == null) {
                    return null;
                }
                if (a.a != null) {
                    return a;
                }
                if (a == dVar) {
                    return a();
                }
                dVar = a;
            }
        }

        void b() {
            ReentrantLock reentrantLock = this.c.c;
            reentrantLock.lock();
            try {
                this.a = b(this.a);
                this.b = this.a == null ? null : this.a.a;
            } finally {
                reentrantLock.unlock();
            }
        }

        public boolean hasNext() {
            return this.a != null;
        }

        public E next() {
            if (this.a == null) {
                throw new NoSuchElementException();
            }
            this.d = this.a;
            E e = this.b;
            b();
            return e;
        }

        public void remove() {
            d dVar = this.d;
            if (dVar == null) {
                throw new IllegalStateException();
            }
            this.d = null;
            ReentrantLock reentrantLock = this.c.c;
            reentrantLock.lock();
            try {
                if (dVar.a != null) {
                    this.c.a(dVar);
                }
                reentrantLock.unlock();
            } catch (Throwable th) {
                reentrantLock.unlock();
            }
        }
    }

    /* compiled from: MyLinkedBlockingDeque */
    private class b extends a {
        final /* synthetic */ ae d;

        private b(ae aeVar) {
            this.d = aeVar;
            super(aeVar);
        }

        d<E> a() {
            return this.d.b;
        }

        d<E> a(d<E> dVar) {
            return dVar.b;
        }
    }

    /* compiled from: MyLinkedBlockingDeque */
    private class c extends a {
        final /* synthetic */ ae d;

        private c(ae aeVar) {
            this.d = aeVar;
            super(aeVar);
        }

        d<E> a() {
            return this.d.a;
        }

        d<E> a(d<E> dVar) {
            return dVar.c;
        }
    }

    /* compiled from: MyLinkedBlockingDeque */
    static final class d<E> {
        E a;
        d<E> b;
        d<E> c;

        d(E e) {
            this.a = e;
        }
    }

    public ae() {
        this(Integer.MAX_VALUE);
    }

    public ae(int i) {
        this.c = new ReentrantLock();
        this.g = this.c.newCondition();
        this.h = this.c.newCondition();
        if (i <= 0) {
            throw new IllegalArgumentException();
        }
        this.f = i;
    }

    public ae(Collection<? extends E> collection) {
        this(Integer.MAX_VALUE);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            for (Object next : collection) {
                if (next == null) {
                    throw new NullPointerException();
                } else if (!c(new d(next))) {
                    throw new IllegalStateException("Deque full");
                }
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    private boolean b(d<E> dVar) {
        if (this.e >= this.f) {
            return false;
        }
        d dVar2 = this.a;
        dVar.c = dVar2;
        this.a = dVar;
        if (this.b == null) {
            this.b = dVar;
        } else {
            dVar2.b = dVar;
        }
        this.e++;
        this.g.signal();
        return true;
    }

    private boolean c(d<E> dVar) {
        if (this.e >= this.f) {
            return false;
        }
        d dVar2 = this.b;
        dVar.b = dVar2;
        this.b = dVar;
        if (this.a == null) {
            this.a = dVar;
        } else {
            dVar2.c = dVar;
        }
        this.e++;
        this.g.signal();
        return true;
    }

    private E o() {
        d dVar = this.a;
        if (dVar == null) {
            return null;
        }
        d dVar2 = dVar.c;
        E e = dVar.a;
        dVar.a = null;
        dVar.c = dVar;
        this.a = dVar2;
        if (dVar2 == null) {
            this.b = null;
        } else {
            dVar2.b = null;
        }
        this.e--;
        this.h.signal();
        return e;
    }

    private E p() {
        d dVar = this.b;
        if (dVar == null) {
            return null;
        }
        d dVar2 = dVar.b;
        E e = dVar.a;
        dVar.a = null;
        dVar.b = dVar;
        this.b = dVar2;
        if (dVar2 == null) {
            this.a = null;
        } else {
            dVar2.c = null;
        }
        this.e--;
        this.h.signal();
        return e;
    }

    void a(d<E> dVar) {
        d dVar2 = dVar.b;
        d dVar3 = dVar.c;
        if (dVar2 == null) {
            o();
        } else if (dVar3 == null) {
            p();
        } else {
            dVar2.c = dVar3;
            dVar3.b = dVar2;
            dVar.a = null;
            this.e--;
            this.h.signal();
        }
    }

    public void a(E e) {
        if (!c((Object) e)) {
            throw new IllegalStateException("Deque full");
        }
    }

    public void b(E e) {
        if (!d(e)) {
            throw new IllegalStateException("Deque full");
        }
    }

    public boolean c(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        d dVar = new d(e);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            boolean b = b(dVar);
            return b;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean d(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        d dVar = new d(e);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            boolean c = c(dVar);
            return c;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void e(E e) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        d dVar = new d(e);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        while (!b(dVar)) {
            try {
                this.h.await();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public void f(E e) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        d dVar = new d(e);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        while (!c(dVar)) {
            try {
                this.h.await();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public boolean a(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        d dVar = new d(e);
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lockInterruptibly();
        while (!b(dVar)) {
            try {
                if (toNanos <= 0) {
                    return false;
                }
                toNanos = this.h.awaitNanos(toNanos);
            } finally {
                reentrantLock.unlock();
            }
        }
        reentrantLock.unlock();
        return true;
    }

    public boolean b(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        d dVar = new d(e);
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lockInterruptibly();
        while (!c(dVar)) {
            try {
                if (toNanos <= 0) {
                    return false;
                }
                toNanos = this.h.awaitNanos(toNanos);
            } finally {
                reentrantLock.unlock();
            }
        }
        reentrantLock.unlock();
        return true;
    }

    public E a() {
        E c = c();
        if (c != null) {
            return c;
        }
        throw new NoSuchElementException();
    }

    public E b() {
        E d = d();
        if (d != null) {
            return d;
        }
        throw new NoSuchElementException();
    }

    public E c() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            E o = o();
            return o;
        } finally {
            reentrantLock.unlock();
        }
    }

    public E d() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            E p = p();
            return p;
        } finally {
            reentrantLock.unlock();
        }
    }

    public E e() throws InterruptedException {
        E o;
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        while (true) {
            try {
                o = o();
                if (o != null) {
                    break;
                }
                this.g.await();
            } finally {
                reentrantLock.unlock();
            }
        }
        return o;
    }

    public E f() throws InterruptedException {
        E p;
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        while (true) {
            try {
                p = p();
                if (p != null) {
                    break;
                }
                this.g.await();
            } finally {
                reentrantLock.unlock();
            }
        }
        return p;
    }

    public E a(long j, TimeUnit timeUnit) throws InterruptedException {
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lockInterruptibly();
        long j2 = toNanos;
        while (true) {
            try {
                E o = o();
                if (o != null) {
                    reentrantLock.unlock();
                    return o;
                } else if (j2 <= 0) {
                    break;
                } else {
                    j2 = this.g.awaitNanos(j2);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        return null;
    }

    public E b(long j, TimeUnit timeUnit) throws InterruptedException {
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lockInterruptibly();
        long j2 = toNanos;
        while (true) {
            try {
                E p = p();
                if (p != null) {
                    reentrantLock.unlock();
                    return p;
                } else if (j2 <= 0) {
                    break;
                } else {
                    j2 = this.g.awaitNanos(j2);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        return null;
    }

    public E g() {
        E i = i();
        if (i != null) {
            return i;
        }
        throw new NoSuchElementException();
    }

    public E h() {
        E j = j();
        if (j != null) {
            return j;
        }
        throw new NoSuchElementException();
    }

    public E i() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            E e = this.a == null ? null : this.a.a;
            reentrantLock.unlock();
            return e;
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    public E j() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            E e = this.b == null ? null : this.b.a;
            reentrantLock.unlock();
            return e;
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    public boolean g(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            for (d dVar = this.a; dVar != null; dVar = dVar.c) {
                if (obj.equals(dVar.a)) {
                    a(dVar);
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean h(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            for (d dVar = this.b; dVar != null; dVar = dVar.b) {
                if (obj.equals(dVar.a)) {
                    a(dVar);
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean add(E e) {
        b((Object) e);
        return true;
    }

    public boolean offer(E e) {
        return d(e);
    }

    public void i(E e) throws InterruptedException {
        f(e);
    }

    public boolean c(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        return b(e, j, timeUnit);
    }

    public E remove() {
        return a();
    }

    public E poll() {
        return c();
    }

    public E k() throws InterruptedException {
        return e();
    }

    public E c(long j, TimeUnit timeUnit) throws InterruptedException {
        return a(j, timeUnit);
    }

    public E element() {
        return g();
    }

    public E peek() {
        return i();
    }

    public int l() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            int i = this.f - this.e;
            return i;
        } finally {
            reentrantLock.unlock();
        }
    }

    public int a(Collection<? super E> collection) {
        return a((Collection) collection, Integer.MAX_VALUE);
    }

    public int a(Collection<? super E> collection, int i) {
        int i2 = 0;
        if (collection == null) {
            throw new NullPointerException();
        } else if (collection == this) {
            throw new IllegalArgumentException();
        } else if (i <= 0) {
            return 0;
        } else {
            ReentrantLock reentrantLock = this.c;
            reentrantLock.lock();
            try {
                int min = Math.min(i, this.e);
                while (i2 < min) {
                    collection.add(this.a.a);
                    o();
                    i2++;
                }
                return min;
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public void j(E e) {
        a((Object) e);
    }

    public E m() {
        return a();
    }

    public boolean remove(Object obj) {
        return g(obj);
    }

    public int size() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            int i = this.e;
            return i;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            for (d dVar = this.a; dVar != null; dVar = dVar.c) {
                if (obj.equals(dVar.a)) {
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public Object[] toArray() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            Object[] objArr = new Object[this.e];
            int i = 0;
            d dVar = this.a;
            while (dVar != null) {
                int i2 = i + 1;
                objArr[i] = dVar.a;
                dVar = dVar.c;
                i = i2;
            }
            return objArr;
        } finally {
            reentrantLock.unlock();
        }
    }

    public <T> T[] toArray(T[] tArr) {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            if (tArr.length < this.e) {
                tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.e);
            }
            int i = 0;
            d dVar = this.a;
            while (dVar != null) {
                int i2 = i + 1;
                tArr[i] = dVar.a;
                dVar = dVar.c;
                i = i2;
            }
            if (tArr.length > i) {
                tArr[i] = null;
            }
            reentrantLock.unlock();
            return tArr;
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    public String toString() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            String str;
            d dVar = this.a;
            if (dVar == null) {
                str = "[]";
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append('[');
                d dVar2 = dVar;
                while (true) {
                    Object obj = dVar2.a;
                    if (obj == this) {
                        obj = "(this Collection)";
                    }
                    stringBuilder.append(obj);
                    dVar = dVar2.c;
                    if (dVar == null) {
                        break;
                    }
                    stringBuilder.append(',').append(' ');
                    dVar2 = dVar;
                }
                str = stringBuilder.append(']').toString();
                reentrantLock.unlock();
            }
            return str;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void clear() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            d dVar = this.a;
            while (dVar != null) {
                dVar.a = null;
                d dVar2 = dVar.c;
                dVar.b = null;
                dVar.c = null;
                dVar = dVar2;
            }
            this.b = null;
            this.a = null;
            this.e = 0;
            this.h.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    public Iterator<E> iterator() {
        return new c();
    }

    public Iterator<E> n() {
        return new b();
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            objectOutputStream.defaultWriteObject();
            for (d dVar = this.a; dVar != null; dVar = dVar.c) {
                objectOutputStream.writeObject(dVar.a);
            }
            objectOutputStream.writeObject(null);
        } finally {
            reentrantLock.unlock();
        }
    }

    private void a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.e = 0;
        this.a = null;
        this.b = null;
        while (true) {
            Object readObject = objectInputStream.readObject();
            if (readObject != null) {
                add(readObject);
            } else {
                return;
            }
        }
    }
}

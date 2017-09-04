package com.tencent.imsdk;

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

public class MyLinkedBlockingDeque<E> extends AbstractQueue<E> implements Serializable {
    private static final long serialVersionUID = -387911632671998426L;
    private final int capacity;
    private transient int count;
    transient Node<E> first;
    transient Node<E> last;
    final ReentrantLock lock;
    private final Condition notEmpty;
    private final Condition notFull;

    static final class Node<E> {
        E a;
        Node<E> b;
        Node<E> c;

        Node(E e) {
            this.a = e;
        }
    }

    private abstract class aa implements Iterator<E> {
        private Node<E> a;
        private E b;
        private Node<E> c;
        private /* synthetic */ MyLinkedBlockingDeque d;

        aa(MyLinkedBlockingDeque myLinkedBlockingDeque) {
            this.d = myLinkedBlockingDeque;
            ReentrantLock reentrantLock = myLinkedBlockingDeque.lock;
            reentrantLock.lock();
            try {
                this.a = a();
                this.b = this.a == null ? null : this.a.a;
            } finally {
                reentrantLock.unlock();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void b() {
            /*
            r5 = this;
            r0 = 0;
            r1 = r5.d;
            r3 = r1.lock;
            r3.lock();
            r1 = r5.a;	 Catch:{ all -> 0x0030 }
            r2 = r1;
        L_0x000b:
            r1 = r5.a(r2);	 Catch:{ all -> 0x0030 }
            if (r1 != 0) goto L_0x001e;
        L_0x0011:
            r1 = r0;
        L_0x0012:
            r5.a = r1;	 Catch:{ all -> 0x0030 }
            r1 = r5.a;	 Catch:{ all -> 0x0030 }
            if (r1 != 0) goto L_0x002b;
        L_0x0018:
            r5.b = r0;	 Catch:{ all -> 0x0030 }
            r3.unlock();
            return;
        L_0x001e:
            r4 = r1.a;	 Catch:{ all -> 0x0030 }
            if (r4 != 0) goto L_0x0012;
        L_0x0022:
            if (r1 != r2) goto L_0x0029;
        L_0x0024:
            r1 = r5.a();	 Catch:{ all -> 0x0030 }
            goto L_0x0012;
        L_0x0029:
            r2 = r1;
            goto L_0x000b;
        L_0x002b:
            r0 = r5.a;	 Catch:{ all -> 0x0030 }
            r0 = r0.a;	 Catch:{ all -> 0x0030 }
            goto L_0x0018;
        L_0x0030:
            r0 = move-exception;
            r3.unlock();
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.imsdk.MyLinkedBlockingDeque.aa.b():void");
        }

        abstract Node<E> a();

        abstract Node<E> a(Node<E> node);

        public boolean hasNext() {
            return this.a != null;
        }

        public E next() {
            if (this.a == null) {
                throw new NoSuchElementException();
            }
            this.c = this.a;
            E e = this.b;
            b();
            return e;
        }

        public void remove() {
            Node node = this.c;
            if (node == null) {
                throw new IllegalStateException();
            }
            this.c = null;
            ReentrantLock reentrantLock = this.d.lock;
            reentrantLock.lock();
            try {
                if (node.a != null) {
                    this.d.unlink(node);
                }
                reentrantLock.unlock();
            } catch (Throwable th) {
                reentrantLock.unlock();
            }
        }
    }

    private class ab extends aa {
        private /* synthetic */ MyLinkedBlockingDeque a;

        private ab(MyLinkedBlockingDeque myLinkedBlockingDeque) {
            this.a = myLinkedBlockingDeque;
            super(myLinkedBlockingDeque);
        }

        final Node<E> a() {
            return this.a.last;
        }

        final Node<E> a(Node<E> node) {
            return node.b;
        }
    }

    private class ac extends aa {
        private /* synthetic */ MyLinkedBlockingDeque a;

        private ac(MyLinkedBlockingDeque myLinkedBlockingDeque) {
            this.a = myLinkedBlockingDeque;
            super(myLinkedBlockingDeque);
        }

        final Node<E> a() {
            return this.a.first;
        }

        final Node<E> a(Node<E> node) {
            return node.c;
        }
    }

    public MyLinkedBlockingDeque() {
        this(Integer.MAX_VALUE);
    }

    public MyLinkedBlockingDeque(int i) {
        this.lock = new ReentrantLock();
        this.notEmpty = this.lock.newCondition();
        this.notFull = this.lock.newCondition();
        if (i <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = i;
    }

    public MyLinkedBlockingDeque(Collection<? extends E> collection) {
        this(Integer.MAX_VALUE);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            for (Object next : collection) {
                if (next == null) {
                    throw new NullPointerException();
                } else if (!linkLast(new Node(next))) {
                    throw new IllegalStateException("Deque full");
                }
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    private boolean linkFirst(Node<E> node) {
        if (this.count >= this.capacity) {
            return false;
        }
        Node node2 = this.first;
        node.c = node2;
        this.first = node;
        if (this.last == null) {
            this.last = node;
        } else {
            node2.b = node;
        }
        this.count++;
        this.notEmpty.signal();
        return true;
    }

    private boolean linkLast(Node<E> node) {
        if (this.count >= this.capacity) {
            return false;
        }
        Node node2 = this.last;
        node.b = node2;
        this.last = node;
        if (this.first == null) {
            this.first = node;
        } else {
            node2.c = node;
        }
        this.count++;
        this.notEmpty.signal();
        return true;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.count = 0;
        this.first = null;
        this.last = null;
        while (true) {
            Object readObject = objectInputStream.readObject();
            if (readObject != null) {
                add(readObject);
            } else {
                return;
            }
        }
    }

    private E unlinkFirst() {
        Node node = this.first;
        if (node == null) {
            return null;
        }
        Node node2 = node.c;
        E e = node.a;
        node.a = null;
        node.c = node;
        this.first = node2;
        if (node2 == null) {
            this.last = null;
        } else {
            node2.b = null;
        }
        this.count--;
        this.notFull.signal();
        return e;
    }

    private E unlinkLast() {
        Node node = this.last;
        if (node == null) {
            return null;
        }
        Node node2 = node.b;
        E e = node.a;
        node.a = null;
        node.b = node;
        this.last = node2;
        if (node2 == null) {
            this.first = null;
        } else {
            node2.c = null;
        }
        this.count--;
        this.notFull.signal();
        return e;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            objectOutputStream.defaultWriteObject();
            for (Node node = this.first; node != null; node = node.c) {
                objectOutputStream.writeObject(node.a);
            }
            objectOutputStream.writeObject(null);
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean add(E e) {
        addLast(e);
        return true;
    }

    public void addFirst(E e) {
        if (!offerFirst(e)) {
            throw new IllegalStateException("Deque full");
        }
    }

    public void addLast(E e) {
        if (!offerLast(e)) {
            throw new IllegalStateException("Deque full");
        }
    }

    public void clear() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Node node = this.first;
            while (node != null) {
                node.a = null;
                Node node2 = node.c;
                node.b = null;
                node.c = null;
                node = node2;
            }
            this.last = null;
            this.first = null;
            this.count = 0;
            this.notFull.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            for (Node node = this.first; node != null; node = node.c) {
                if (obj.equals(node.a)) {
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public Iterator<E> descendingIterator() {
        return new ab();
    }

    public int drainTo(Collection<? super E> collection) {
        return drainTo(collection, Integer.MAX_VALUE);
    }

    public int drainTo(Collection<? super E> collection, int i) {
        int i2 = 0;
        if (collection == null) {
            throw new NullPointerException();
        } else if (collection == this) {
            throw new IllegalArgumentException();
        } else if (i <= 0) {
            return 0;
        } else {
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            try {
                int min = Math.min(i, this.count);
                while (i2 < min) {
                    collection.add(this.first.a);
                    unlinkFirst();
                    i2++;
                }
                return min;
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public E element() {
        return getFirst();
    }

    public E getFirst() {
        E peekFirst = peekFirst();
        if (peekFirst != null) {
            return peekFirst;
        }
        throw new NoSuchElementException();
    }

    public E getLast() {
        E peekLast = peekLast();
        if (peekLast != null) {
            return peekLast;
        }
        throw new NoSuchElementException();
    }

    public Iterator<E> iterator() {
        return new ac();
    }

    public boolean offer(E e) {
        return offerLast(e);
    }

    public boolean offer(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        return offerLast(e, j, timeUnit);
    }

    public boolean offerFirst(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        Node node = new Node(e);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            boolean linkFirst = linkFirst(node);
            return linkFirst;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean offerFirst(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        Node node = new Node(e);
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lockInterruptibly();
        while (!linkFirst(node)) {
            try {
                if (toNanos <= 0) {
                    return false;
                }
                toNanos = this.notFull.awaitNanos(toNanos);
            } finally {
                reentrantLock.unlock();
            }
        }
        reentrantLock.unlock();
        return true;
    }

    public boolean offerLast(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        Node node = new Node(e);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            boolean linkLast = linkLast(node);
            return linkLast;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean offerLast(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        Node node = new Node(e);
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lockInterruptibly();
        while (!linkLast(node)) {
            try {
                if (toNanos <= 0) {
                    return false;
                }
                toNanos = this.notFull.awaitNanos(toNanos);
            } finally {
                reentrantLock.unlock();
            }
        }
        reentrantLock.unlock();
        return true;
    }

    public E peek() {
        return peekFirst();
    }

    public E peekFirst() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            E e = this.first == null ? null : this.first.a;
            reentrantLock.unlock();
            return e;
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    public E peekLast() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            E e = this.last == null ? null : this.last.a;
            reentrantLock.unlock();
            return e;
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    public E poll() {
        return pollFirst();
    }

    public E poll(long j, TimeUnit timeUnit) throws InterruptedException {
        return pollFirst(j, timeUnit);
    }

    public E pollFirst() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            E unlinkFirst = unlinkFirst();
            return unlinkFirst;
        } finally {
            reentrantLock.unlock();
        }
    }

    public E pollFirst(long j, TimeUnit timeUnit) throws InterruptedException {
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lockInterruptibly();
        long j2 = toNanos;
        while (true) {
            try {
                E unlinkFirst = unlinkFirst();
                if (unlinkFirst != null) {
                    reentrantLock.unlock();
                    return unlinkFirst;
                } else if (j2 <= 0) {
                    break;
                } else {
                    j2 = this.notEmpty.awaitNanos(j2);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        return null;
    }

    public E pollLast() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            E unlinkLast = unlinkLast();
            return unlinkLast;
        } finally {
            reentrantLock.unlock();
        }
    }

    public E pollLast(long j, TimeUnit timeUnit) throws InterruptedException {
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lockInterruptibly();
        long j2 = toNanos;
        while (true) {
            try {
                E unlinkLast = unlinkLast();
                if (unlinkLast != null) {
                    reentrantLock.unlock();
                    return unlinkLast;
                } else if (j2 <= 0) {
                    break;
                } else {
                    j2 = this.notEmpty.awaitNanos(j2);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        return null;
    }

    public E pop() {
        return removeFirst();
    }

    public void push(E e) {
        addFirst(e);
    }

    public void put(E e) throws InterruptedException {
        putLast(e);
    }

    public void putFirst(E e) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        Node node = new Node(e);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        while (!linkFirst(node)) {
            try {
                this.notFull.await();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public void putLast(E e) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        Node node = new Node(e);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        while (!linkLast(node)) {
            try {
                this.notFull.await();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public int remainingCapacity() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i = this.capacity - this.count;
            return i;
        } finally {
            reentrantLock.unlock();
        }
    }

    public E remove() {
        return removeFirst();
    }

    public boolean remove(Object obj) {
        return removeFirstOccurrence(obj);
    }

    public E removeFirst() {
        E pollFirst = pollFirst();
        if (pollFirst != null) {
            return pollFirst;
        }
        throw new NoSuchElementException();
    }

    public boolean removeFirstOccurrence(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            for (Node node = this.first; node != null; node = node.c) {
                if (obj.equals(node.a)) {
                    unlink(node);
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public E removeLast() {
        E pollLast = pollLast();
        if (pollLast != null) {
            return pollLast;
        }
        throw new NoSuchElementException();
    }

    public boolean removeLastOccurrence(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            for (Node node = this.last; node != null; node = node.b) {
                if (obj.equals(node.a)) {
                    unlink(node);
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public int size() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i = this.count;
            return i;
        } finally {
            reentrantLock.unlock();
        }
    }

    public E take() throws InterruptedException {
        return takeFirst();
    }

    public E takeFirst() throws InterruptedException {
        E unlinkFirst;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        while (true) {
            try {
                unlinkFirst = unlinkFirst();
                if (unlinkFirst != null) {
                    break;
                }
                this.notEmpty.await();
            } finally {
                reentrantLock.unlock();
            }
        }
        return unlinkFirst;
    }

    public E takeLast() throws InterruptedException {
        E unlinkLast;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        while (true) {
            try {
                unlinkLast = unlinkLast();
                if (unlinkLast != null) {
                    break;
                }
                this.notEmpty.await();
            } finally {
                reentrantLock.unlock();
            }
        }
        return unlinkLast;
    }

    public Object[] toArray() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Object[] objArr = new Object[this.count];
            int i = 0;
            Node node = this.first;
            while (node != null) {
                int i2 = i + 1;
                objArr[i] = node.a;
                node = node.c;
                i = i2;
            }
            return objArr;
        } finally {
            reentrantLock.unlock();
        }
    }

    public <T> T[] toArray(T[] tArr) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (tArr.length < this.count) {
                tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.count);
            }
            int i = 0;
            Node node = this.first;
            while (node != null) {
                int i2 = i + 1;
                tArr[i] = node.a;
                node = node.c;
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
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            String str;
            Node node = this.first;
            if (node == null) {
                str = "[]";
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append('[');
                Node node2 = node;
                while (true) {
                    Object obj = node2.a;
                    if (obj == this) {
                        obj = "(this Collection)";
                    }
                    stringBuilder.append(obj);
                    node = node2.c;
                    if (node == null) {
                        break;
                    }
                    stringBuilder.append(',').append(' ');
                    node2 = node;
                }
                str = stringBuilder.append(']').toString();
                reentrantLock.unlock();
            }
            return str;
        } finally {
            reentrantLock.unlock();
        }
    }

    void unlink(Node<E> node) {
        Node node2 = node.b;
        Node node3 = node.c;
        if (node2 == null) {
            unlinkFirst();
        } else if (node3 == null) {
            unlinkLast();
        } else {
            node2.c = node3;
            node3.b = node2;
            node.a = null;
            this.count--;
            this.notFull.signal();
        }
    }
}

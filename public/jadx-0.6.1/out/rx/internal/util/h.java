package rx.internal.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/* compiled from: SynchronizedQueue */
public class h<T> implements Queue<T> {
    private final LinkedList<T> a;
    private final int b;

    public h() {
        this.a = new LinkedList();
        this.b = -1;
    }

    public h(int i) {
        this.a = new LinkedList();
        this.b = i;
    }

    public synchronized boolean isEmpty() {
        return this.a.isEmpty();
    }

    public synchronized boolean contains(Object obj) {
        return this.a.contains(obj);
    }

    public synchronized Iterator<T> iterator() {
        return this.a.iterator();
    }

    public synchronized int size() {
        return this.a.size();
    }

    public synchronized boolean add(T t) {
        return this.a.add(t);
    }

    public synchronized boolean remove(Object obj) {
        return this.a.remove(obj);
    }

    public synchronized boolean containsAll(Collection<?> collection) {
        return this.a.containsAll(collection);
    }

    public synchronized boolean addAll(Collection<? extends T> collection) {
        return this.a.addAll(collection);
    }

    public synchronized boolean removeAll(Collection<?> collection) {
        return this.a.removeAll(collection);
    }

    public synchronized boolean retainAll(Collection<?> collection) {
        return this.a.retainAll(collection);
    }

    public synchronized void clear() {
        this.a.clear();
    }

    public synchronized String toString() {
        return this.a.toString();
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        h hVar = (h) obj;
        if (this.a == null) {
            if (hVar.a != null) {
                return false;
            }
            return true;
        } else if (this.a.equals(hVar.a)) {
            return true;
        } else {
            return false;
        }
    }

    public synchronized T peek() {
        return this.a.peek();
    }

    public synchronized T element() {
        return this.a.element();
    }

    public synchronized T poll() {
        return this.a.poll();
    }

    public synchronized T remove() {
        return this.a.remove();
    }

    public synchronized boolean offer(T t) {
        boolean offer;
        if (this.b <= -1 || this.a.size() + 1 <= this.b) {
            offer = this.a.offer(t);
        } else {
            offer = false;
        }
        return offer;
    }

    public synchronized Object clone() {
        h hVar;
        hVar = new h(this.b);
        hVar.addAll(this.a);
        return hVar;
    }

    public synchronized Object[] toArray() {
        return this.a.toArray();
    }

    public synchronized <R> R[] toArray(R[] rArr) {
        return this.a.toArray(rArr);
    }
}

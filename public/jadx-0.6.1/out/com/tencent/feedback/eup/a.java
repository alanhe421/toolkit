package com.tencent.feedback.eup;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/* compiled from: RQDSRC */
public final class a<E> extends LinkedList<E> {
    private int a = -1;

    public a(int i) {
        this.a = i;
    }

    public final boolean add(E e) {
        if (size() == this.a) {
            removeFirst();
        }
        return super.add(e);
    }

    public final void add(int i, E e) {
        if (size() == this.a) {
            removeFirst();
        }
        super.add(i, e);
    }

    public final boolean addAll(Collection<? extends E> collection) {
        int size = (size() + collection.size()) - this.a;
        if (size > 0) {
            removeRange(0, size);
        }
        return super.addAll(collection);
    }

    public final boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    public final void addFirst(E e) {
        throw new UnsupportedOperationException();
    }

    public final void addLast(E e) {
        add(e);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = iterator();
        while (it.hasNext()) {
            stringBuilder.append(it.next().toString());
        }
        return stringBuilder.toString();
    }
}

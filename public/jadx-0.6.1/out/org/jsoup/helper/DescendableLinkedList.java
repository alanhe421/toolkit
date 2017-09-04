package org.jsoup.helper;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class DescendableLinkedList<E> extends LinkedList<E> {

    private class a<E> implements Iterator<E> {
        final /* synthetic */ DescendableLinkedList a;
        private final ListIterator<E> b;

        private a(DescendableLinkedList descendableLinkedList, int i) {
            this.a = descendableLinkedList;
            this.b = descendableLinkedList.listIterator(i);
        }

        public boolean hasNext() {
            return this.b.hasPrevious();
        }

        public E next() {
            return this.b.previous();
        }

        public void remove() {
            this.b.remove();
        }
    }

    public void push(E e) {
        addFirst(e);
    }

    public E peekLast() {
        return size() == 0 ? null : getLast();
    }

    public E pollLast() {
        return size() == 0 ? null : removeLast();
    }

    public Iterator<E> descendingIterator() {
        return new a(size());
    }
}

package rx.internal.util.atomic;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: BaseLinkedAtomicQueue */
abstract class a<E> extends AbstractQueue<E> {
    private final AtomicReference<LinkedQueueNode<E>> a = new AtomicReference();
    private final AtomicReference<LinkedQueueNode<E>> b = new AtomicReference();

    protected final LinkedQueueNode<E> a() {
        return (LinkedQueueNode) this.a.get();
    }

    protected final LinkedQueueNode<E> b() {
        return (LinkedQueueNode) this.a.get();
    }

    protected final void a(LinkedQueueNode<E> linkedQueueNode) {
        this.a.lazySet(linkedQueueNode);
    }

    protected final LinkedQueueNode<E> c() {
        return (LinkedQueueNode) this.b.get();
    }

    protected final LinkedQueueNode<E> d() {
        return (LinkedQueueNode) this.b.get();
    }

    protected final void b(LinkedQueueNode<E> linkedQueueNode) {
        this.b.lazySet(linkedQueueNode);
    }

    public final Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    public final int size() {
        LinkedQueueNode c = c();
        LinkedQueueNode a = a();
        int i = 0;
        while (c != a && i < Integer.MAX_VALUE) {
            LinkedQueueNode lvNext;
            do {
                lvNext = c.lvNext();
            } while (lvNext == null);
            i++;
            c = lvNext;
        }
        return i;
    }

    public final boolean isEmpty() {
        return c() == a();
    }
}

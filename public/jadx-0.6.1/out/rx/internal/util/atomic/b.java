package rx.internal.util.atomic;

/* compiled from: SpscLinkedAtomicQueue */
public final class b<E> extends a<E> {
    public b() {
        LinkedQueueNode linkedQueueNode = new LinkedQueueNode();
        a(linkedQueueNode);
        b(linkedQueueNode);
        linkedQueueNode.soNext(null);
    }

    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("null elements not allowed");
        }
        LinkedQueueNode linkedQueueNode = new LinkedQueueNode(e);
        b().soNext(linkedQueueNode);
        a(linkedQueueNode);
        return true;
    }

    public E poll() {
        LinkedQueueNode lvNext = d().lvNext();
        if (lvNext == null) {
            return null;
        }
        E andNullValue = lvNext.getAndNullValue();
        b(lvNext);
        return andNullValue;
    }

    public E peek() {
        LinkedQueueNode lvNext = d().lvNext();
        if (lvNext != null) {
            return lvNext.lpValue();
        }
        return null;
    }
}

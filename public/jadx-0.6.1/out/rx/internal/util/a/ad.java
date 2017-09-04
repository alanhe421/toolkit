package rx.internal.util.a;

import rx.internal.util.atomic.LinkedQueueNode;

/* compiled from: SpscLinkedQueue */
public final class ad<E> extends a<E> {
    public ad() {
        b(new LinkedQueueNode());
        a(this.producerNode);
        this.consumerNode.soNext(null);
    }

    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("null elements not allowed");
        }
        LinkedQueueNode linkedQueueNode = new LinkedQueueNode(e);
        this.producerNode.soNext(linkedQueueNode);
        this.producerNode = linkedQueueNode;
        return true;
    }

    public E poll() {
        LinkedQueueNode lvNext = this.consumerNode.lvNext();
        if (lvNext == null) {
            return null;
        }
        E andNullValue = lvNext.getAndNullValue();
        this.consumerNode = lvNext;
        return andNullValue;
    }

    public E peek() {
        LinkedQueueNode lvNext = this.consumerNode.lvNext();
        if (lvNext != null) {
            return lvNext.lpValue();
        }
        return null;
    }
}

package rx.internal.util.a;

import rx.internal.util.atomic.LinkedQueueNode;

/* compiled from: BaseLinkedQueue */
abstract class e<E> extends c<E> {
    protected static final long b = ae.a(e.class, "producerNode");
    protected LinkedQueueNode<E> producerNode;

    e() {
    }

    protected final void b(LinkedQueueNode<E> linkedQueueNode) {
        this.producerNode = linkedQueueNode;
    }

    protected final LinkedQueueNode<E> b() {
        return (LinkedQueueNode) ae.a.getObjectVolatile(this, b);
    }
}

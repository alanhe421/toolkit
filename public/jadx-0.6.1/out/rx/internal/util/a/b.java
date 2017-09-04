package rx.internal.util.a;

import rx.internal.util.atomic.LinkedQueueNode;

/* compiled from: BaseLinkedQueue */
abstract class b<E> extends d<E> {
    protected static final long a = ae.a(b.class, "consumerNode");
    protected LinkedQueueNode<E> consumerNode;

    b() {
    }

    protected final void a(LinkedQueueNode<E> linkedQueueNode) {
        this.consumerNode = linkedQueueNode;
    }

    protected final LinkedQueueNode<E> a() {
        return (LinkedQueueNode) ae.a.getObjectVolatile(this, a);
    }
}

package rx.internal.util.a;

import java.util.Iterator;
import rx.internal.util.atomic.LinkedQueueNode;

/* compiled from: BaseLinkedQueue */
abstract class a<E> extends b<E> {
    a() {
    }

    public final Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    public final int size() {
        LinkedQueueNode a = a();
        LinkedQueueNode b = b();
        int i = 0;
        while (a != b && i < Integer.MAX_VALUE) {
            LinkedQueueNode lvNext;
            do {
                lvNext = a.lvNext();
            } while (lvNext == null);
            i++;
            a = lvNext;
        }
        return i;
    }

    public final boolean isEmpty() {
        return a() == b();
    }
}

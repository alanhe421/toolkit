package rx.internal.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import rx.exceptions.a;
import rx.f;

/* compiled from: SubscriptionList */
public final class g implements f {
    private LinkedList<f> a;
    private volatile boolean b;

    public g(f... fVarArr) {
        this.a = new LinkedList(Arrays.asList(fVarArr));
    }

    public g(f fVar) {
        this.a = new LinkedList();
        this.a.add(fVar);
    }

    public boolean isUnsubscribed() {
        return this.b;
    }

    public void a(f fVar) {
        if (!fVar.isUnsubscribed()) {
            if (!this.b) {
                synchronized (this) {
                    if (!this.b) {
                        LinkedList linkedList = this.a;
                        if (linkedList == null) {
                            linkedList = new LinkedList();
                            this.a = linkedList;
                        }
                        linkedList.add(fVar);
                        return;
                    }
                }
            }
            fVar.unsubscribe();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(rx.f r3) {
        /*
        r2 = this;
        r0 = r2.b;
        if (r0 != 0) goto L_0x000e;
    L_0x0004:
        monitor-enter(r2);
        r0 = r2.a;	 Catch:{ all -> 0x001a }
        r1 = r2.b;	 Catch:{ all -> 0x001a }
        if (r1 != 0) goto L_0x000d;
    L_0x000b:
        if (r0 != 0) goto L_0x000f;
    L_0x000d:
        monitor-exit(r2);	 Catch:{ all -> 0x001a }
    L_0x000e:
        return;
    L_0x000f:
        r0 = r0.remove(r3);	 Catch:{ all -> 0x001a }
        monitor-exit(r2);	 Catch:{ all -> 0x001a }
        if (r0 == 0) goto L_0x000e;
    L_0x0016:
        r3.unsubscribe();
        goto L_0x000e;
    L_0x001a:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x001a }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.util.g.b(rx.f):void");
    }

    public void unsubscribe() {
        if (!this.b) {
            synchronized (this) {
                if (this.b) {
                    return;
                }
                this.b = true;
                Collection collection = this.a;
                this.a = null;
                a(collection);
            }
        }
    }

    private static void a(Collection<f> collection) {
        if (collection != null) {
            List list = null;
            for (f unsubscribe : collection) {
                try {
                    unsubscribe.unsubscribe();
                } catch (Throwable th) {
                    List arrayList;
                    if (list == null) {
                        arrayList = new ArrayList();
                    } else {
                        arrayList = list;
                    }
                    arrayList.add(th);
                    list = arrayList;
                }
            }
            a.a(list);
        }
    }
}

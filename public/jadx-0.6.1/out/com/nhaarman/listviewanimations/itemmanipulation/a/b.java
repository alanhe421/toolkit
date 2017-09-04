package com.nhaarman.listviewanimations.itemmanipulation.a;

import android.util.Pair;
import com.nhaarman.listviewanimations.b.d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: InsertQueue */
public class b<T> {
    private final d<T> a;
    private final Collection<AtomicInteger> b = new HashSet();
    private final List<Pair<Integer, T>> c = new ArrayList();

    public b(d<T> dVar) {
        this.a = dVar;
    }

    public void a(int i) {
        Iterator it = this.b.iterator();
        Object obj = null;
        while (it.hasNext() && r1 == null) {
            if (((AtomicInteger) it.next()).get() == i) {
                it.remove();
                obj = 1;
            }
        }
        if (this.b.isEmpty()) {
            b();
        }
    }

    private void b() {
        for (Pair pair : this.c) {
            for (AtomicInteger atomicInteger : this.b) {
                if (atomicInteger.intValue() >= ((Integer) pair.first).intValue()) {
                    atomicInteger.incrementAndGet();
                }
            }
            this.b.add(new AtomicInteger(((Integer) pair.first).intValue()));
            this.a.a(((Integer) pair.first).intValue(), pair.second);
        }
        this.c.clear();
    }

    public Collection<Integer> a() {
        Collection<Integer> hashSet = new HashSet();
        for (AtomicInteger atomicInteger : this.b) {
            hashSet.add(Integer.valueOf(atomicInteger.get()));
        }
        return hashSet;
    }
}

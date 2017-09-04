package com.qq.reader.common.download.task;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* compiled from: TaskList */
public class j {
    private final List<g> a = Collections.synchronizedList(new LinkedList());

    public synchronized boolean a(g gVar) {
        return this.a.contains(gVar);
    }

    public synchronized void b(g gVar) {
        this.a.add(gVar);
    }

    public synchronized void c(g gVar) {
        this.a.add(0, gVar);
    }

    public synchronized boolean d(g gVar) {
        return this.a.remove(gVar);
    }

    public synchronized g a(int i) {
        g gVar;
        if (i >= 0) {
            if (i < this.a.size()) {
                gVar = (g) this.a.get(i);
            }
        }
        gVar = null;
        return gVar;
    }

    public synchronized g e(g gVar) {
        g gVar2;
        int indexOf = this.a.indexOf(gVar);
        if (indexOf >= 0) {
            gVar2 = (g) this.a.get(indexOf);
        } else {
            gVar2 = null;
        }
        return gVar2;
    }

    public synchronized List<g> a() {
        return this.a;
    }

    public synchronized void b() {
        this.a.clear();
    }

    public synchronized int c() {
        return this.a.size();
    }

    public synchronized void f(g gVar) {
        int indexOf = this.a.indexOf(gVar);
        if (indexOf >= 0) {
            this.a.remove(indexOf);
            this.a.add(indexOf, gVar);
        }
    }
}

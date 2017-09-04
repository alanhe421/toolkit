package com.qq.reader.module.readpage;

import java.util.ArrayList;
import java.util.List;

/* compiled from: ReaderPageAnimationObservers */
public class p implements com.qq.reader.view.animation.AnimationProvider.a {
    List<a> a = new ArrayList();

    /* compiled from: ReaderPageAnimationObservers */
    public interface a {
        void a();

        void b();
    }

    public boolean a(a aVar) {
        return this.a.add(aVar);
    }

    public void a() {
        for (a a : this.a) {
            a.a();
        }
    }

    public void b() {
        for (a b : this.a) {
            b.b();
        }
    }

    public void c() {
        for (a a : this.a) {
            a.a();
        }
    }
}

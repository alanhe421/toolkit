package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* compiled from: ViewPropertyAnimatorCompat */
public class ao {
    static final g a;
    private WeakReference<View> b;
    private Runnable c = null;
    private Runnable d = null;
    private int e = -1;

    /* compiled from: ViewPropertyAnimatorCompat */
    interface g {
        void a(ao aoVar, View view);

        void a(ao aoVar, View view, float f);

        void a(ao aoVar, View view, long j);

        void a(ao aoVar, View view, ar arVar);

        void b(ao aoVar, View view);

        void b(ao aoVar, View view, float f);

        void c(ao aoVar, View view, float f);
    }

    /* compiled from: ViewPropertyAnimatorCompat */
    static class a implements g {
        WeakHashMap<View, Runnable> a = null;

        /* compiled from: ViewPropertyAnimatorCompat */
        class a implements Runnable {
            WeakReference<View> a;
            ao b;
            final /* synthetic */ a c;

            private a(a aVar, ao aoVar, View view) {
                this.c = aVar;
                this.a = new WeakReference(view);
                this.b = aoVar;
            }

            public void run() {
                View view = (View) this.a.get();
                if (view != null) {
                    this.c.c(this.b, view);
                }
            }
        }

        a() {
        }

        public void a(ao aoVar, View view, long j) {
        }

        public void a(ao aoVar, View view, float f) {
            d(aoVar, view);
        }

        public void b(ao aoVar, View view, float f) {
            d(aoVar, view);
        }

        public void c(ao aoVar, View view, float f) {
            d(aoVar, view);
        }

        public void a(ao aoVar, View view) {
            d(aoVar, view);
        }

        public void b(ao aoVar, View view) {
            a(view);
            c(aoVar, view);
        }

        public void a(ao aoVar, View view, ar arVar) {
            view.setTag(2113929216, arVar);
        }

        private void c(ao aoVar, View view) {
            ar arVar;
            Object tag = view.getTag(2113929216);
            if (tag instanceof ar) {
                arVar = (ar) tag;
            } else {
                arVar = null;
            }
            Runnable a = aoVar.c;
            Runnable b = aoVar.d;
            if (a != null) {
                a.run();
            }
            if (arVar != null) {
                arVar.a(view);
                arVar.b(view);
            }
            if (b != null) {
                b.run();
            }
            if (this.a != null) {
                this.a.remove(view);
            }
        }

        private void a(View view) {
            if (this.a != null) {
                Runnable runnable = (Runnable) this.a.get(view);
                if (runnable != null) {
                    view.removeCallbacks(runnable);
                }
            }
        }

        private void d(ao aoVar, View view) {
            Runnable runnable;
            if (this.a != null) {
                runnable = (Runnable) this.a.get(view);
            } else {
                runnable = null;
            }
            if (runnable == null) {
                runnable = new a(aoVar, view);
                if (this.a == null) {
                    this.a = new WeakHashMap();
                }
                this.a.put(view, runnable);
            }
            view.removeCallbacks(runnable);
            view.post(runnable);
        }
    }

    /* compiled from: ViewPropertyAnimatorCompat */
    static class b extends a {
        WeakHashMap<View, Integer> b = null;

        /* compiled from: ViewPropertyAnimatorCompat */
        static class a implements ar {
            ao a;

            a(ao aoVar) {
                this.a = aoVar;
            }

            public void a(View view) {
                ar arVar;
                if (this.a.e >= 0) {
                    z.a(view, 2, null);
                }
                if (this.a.c != null) {
                    this.a.c.run();
                }
                Object tag = view.getTag(2113929216);
                if (tag instanceof ar) {
                    arVar = (ar) tag;
                } else {
                    arVar = null;
                }
                if (arVar != null) {
                    arVar.a(view);
                }
            }

            public void b(View view) {
                ar arVar;
                if (this.a.e >= 0) {
                    z.a(view, this.a.e, null);
                    this.a.e = -1;
                }
                if (this.a.d != null) {
                    this.a.d.run();
                }
                Object tag = view.getTag(2113929216);
                if (tag instanceof ar) {
                    arVar = (ar) tag;
                } else {
                    arVar = null;
                }
                if (arVar != null) {
                    arVar.b(view);
                }
            }

            public void c(View view) {
                ar arVar;
                Object tag = view.getTag(2113929216);
                if (tag instanceof ar) {
                    arVar = (ar) tag;
                } else {
                    arVar = null;
                }
                if (arVar != null) {
                    arVar.c(view);
                }
            }
        }

        b() {
        }

        public void a(ao aoVar, View view, long j) {
            ap.a(view, j);
        }

        public void a(ao aoVar, View view, float f) {
            ap.a(view, f);
        }

        public void b(ao aoVar, View view, float f) {
            ap.b(view, f);
        }

        public void c(ao aoVar, View view, float f) {
            ap.c(view, f);
        }

        public void a(ao aoVar, View view) {
            ap.a(view);
        }

        public void b(ao aoVar, View view) {
            ap.b(view);
        }

        public void a(ao aoVar, View view, ar arVar) {
            view.setTag(2113929216, arVar);
            ap.a(view, new a(aoVar));
        }
    }

    /* compiled from: ViewPropertyAnimatorCompat */
    static class d extends b {
        d() {
        }

        public void a(ao aoVar, View view, ar arVar) {
            aq.a(view, arVar);
        }
    }

    /* compiled from: ViewPropertyAnimatorCompat */
    static class c extends d {
        c() {
        }
    }

    /* compiled from: ViewPropertyAnimatorCompat */
    static class e extends c {
        e() {
        }
    }

    /* compiled from: ViewPropertyAnimatorCompat */
    static class f extends e {
        f() {
        }
    }

    ao(View view) {
        this.b = new WeakReference(view);
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 21) {
            a = new f();
        } else if (i >= 19) {
            a = new e();
        } else if (i >= 18) {
            a = new c();
        } else if (i >= 16) {
            a = new d();
        } else if (i >= 14) {
            a = new b();
        } else {
            a = new a();
        }
    }

    public ao a(long j) {
        View view = (View) this.b.get();
        if (view != null) {
            a.a(this, view, j);
        }
        return this;
    }

    public ao a(float f) {
        View view = (View) this.b.get();
        if (view != null) {
            a.a(this, view, f);
        }
        return this;
    }

    public ao b(float f) {
        View view = (View) this.b.get();
        if (view != null) {
            a.b(this, view, f);
        }
        return this;
    }

    public ao c(float f) {
        View view = (View) this.b.get();
        if (view != null) {
            a.c(this, view, f);
        }
        return this;
    }

    public void a() {
        View view = (View) this.b.get();
        if (view != null) {
            a.a(this, view);
        }
    }

    public void b() {
        View view = (View) this.b.get();
        if (view != null) {
            a.b(this, view);
        }
    }

    public ao a(ar arVar) {
        View view = (View) this.b.get();
        if (view != null) {
            a.a(this, view, arVar);
        }
        return this;
    }
}

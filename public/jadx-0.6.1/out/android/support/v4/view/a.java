package android.support.v4.view;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.a.i;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

/* compiled from: AccessibilityDelegateCompat */
public class a {
    private static final b b;
    private static final Object c = b.a();
    final Object a = b.a(this);

    /* compiled from: AccessibilityDelegateCompat */
    interface b {
        i a(Object obj, View view);

        Object a();

        Object a(a aVar);

        void a(Object obj, View view, int i);

        void a(Object obj, View view, android.support.v4.view.a.c cVar);

        boolean a(Object obj, View view, int i, Bundle bundle);

        boolean a(Object obj, View view, AccessibilityEvent accessibilityEvent);

        boolean a(Object obj, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent);

        void b(Object obj, View view, AccessibilityEvent accessibilityEvent);

        void c(Object obj, View view, AccessibilityEvent accessibilityEvent);

        void d(Object obj, View view, AccessibilityEvent accessibilityEvent);
    }

    /* compiled from: AccessibilityDelegateCompat */
    static class d implements b {
        d() {
        }

        public Object a() {
            return null;
        }

        public Object a(a aVar) {
            return null;
        }

        public boolean a(Object obj, View view, AccessibilityEvent accessibilityEvent) {
            return false;
        }

        public void b(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        }

        public void a(Object obj, View view, android.support.v4.view.a.c cVar) {
        }

        public void c(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        }

        public boolean a(Object obj, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return true;
        }

        public void a(Object obj, View view, int i) {
        }

        public void d(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        }

        public i a(Object obj, View view) {
            return null;
        }

        public boolean a(Object obj, View view, int i, Bundle bundle) {
            return false;
        }
    }

    /* compiled from: AccessibilityDelegateCompat */
    static class a extends d {
        a() {
        }

        public Object a() {
            return b.a();
        }

        public Object a(final a aVar) {
            return b.a(new android.support.v4.view.b.a(this) {
                final /* synthetic */ a b;

                public boolean a(View view, AccessibilityEvent accessibilityEvent) {
                    return aVar.b(view, accessibilityEvent);
                }

                public void b(View view, AccessibilityEvent accessibilityEvent) {
                    aVar.d(view, accessibilityEvent);
                }

                public void a(View view, Object obj) {
                    aVar.a(view, new android.support.v4.view.a.c(obj));
                }

                public void c(View view, AccessibilityEvent accessibilityEvent) {
                    aVar.c(view, accessibilityEvent);
                }

                public boolean a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
                    return aVar.a(viewGroup, view, accessibilityEvent);
                }

                public void a(View view, int i) {
                    aVar.a(view, i);
                }

                public void d(View view, AccessibilityEvent accessibilityEvent) {
                    aVar.a(view, accessibilityEvent);
                }
            });
        }

        public boolean a(Object obj, View view, AccessibilityEvent accessibilityEvent) {
            return b.a(obj, view, accessibilityEvent);
        }

        public void b(Object obj, View view, AccessibilityEvent accessibilityEvent) {
            b.b(obj, view, accessibilityEvent);
        }

        public void a(Object obj, View view, android.support.v4.view.a.c cVar) {
            b.a(obj, view, cVar.a());
        }

        public void c(Object obj, View view, AccessibilityEvent accessibilityEvent) {
            b.c(obj, view, accessibilityEvent);
        }

        public boolean a(Object obj, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return b.a(obj, viewGroup, view, accessibilityEvent);
        }

        public void a(Object obj, View view, int i) {
            b.a(obj, view, i);
        }

        public void d(Object obj, View view, AccessibilityEvent accessibilityEvent) {
            b.d(obj, view, accessibilityEvent);
        }
    }

    /* compiled from: AccessibilityDelegateCompat */
    static class c extends a {
        c() {
        }

        public Object a(final a aVar) {
            return c.a(new android.support.v4.view.c.a(this) {
                final /* synthetic */ c b;

                public boolean a(View view, AccessibilityEvent accessibilityEvent) {
                    return aVar.b(view, accessibilityEvent);
                }

                public void b(View view, AccessibilityEvent accessibilityEvent) {
                    aVar.d(view, accessibilityEvent);
                }

                public void a(View view, Object obj) {
                    aVar.a(view, new android.support.v4.view.a.c(obj));
                }

                public void c(View view, AccessibilityEvent accessibilityEvent) {
                    aVar.c(view, accessibilityEvent);
                }

                public boolean a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
                    return aVar.a(viewGroup, view, accessibilityEvent);
                }

                public void a(View view, int i) {
                    aVar.a(view, i);
                }

                public void d(View view, AccessibilityEvent accessibilityEvent) {
                    aVar.a(view, accessibilityEvent);
                }

                public Object a(View view) {
                    i a = aVar.a(view);
                    return a != null ? a.a() : null;
                }

                public boolean a(View view, int i, Bundle bundle) {
                    return aVar.a(view, i, bundle);
                }
            });
        }

        public i a(Object obj, View view) {
            Object a = c.a(obj, view);
            if (a != null) {
                return new i(a);
            }
            return null;
        }

        public boolean a(Object obj, View view, int i, Bundle bundle) {
            return c.a(obj, view, i, bundle);
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            b = new c();
        } else if (VERSION.SDK_INT >= 14) {
            b = new a();
        } else {
            b = new d();
        }
    }

    Object a() {
        return this.a;
    }

    public void a(View view, int i) {
        b.a(c, view, i);
    }

    public void a(View view, AccessibilityEvent accessibilityEvent) {
        b.d(c, view, accessibilityEvent);
    }

    public boolean b(View view, AccessibilityEvent accessibilityEvent) {
        return b.a(c, view, accessibilityEvent);
    }

    public void c(View view, AccessibilityEvent accessibilityEvent) {
        b.c(c, view, accessibilityEvent);
    }

    public void d(View view, AccessibilityEvent accessibilityEvent) {
        b.b(c, view, accessibilityEvent);
    }

    public void a(View view, android.support.v4.view.a.c cVar) {
        b.a(c, view, cVar);
    }

    public boolean a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return b.a(c, viewGroup, view, accessibilityEvent);
    }

    public i a(View view) {
        return b.a(c, view);
    }

    public boolean a(View view, int i, Bundle bundle) {
        return b.a(c, view, i, bundle);
    }
}

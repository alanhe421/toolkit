package com.qrcomic.a;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.util.Observable;
import java.util.Observer;

/* compiled from: QRComicObservable */
public class g extends Observable {
    private static String a = "QRComicObservable";
    private h b;

    /* compiled from: QRComicObservable */
    public static class a implements Observer {
        protected void a() {
        }

        protected void e() {
        }

        protected void f() {
        }

        protected void g() {
        }

        protected void h() {
        }

        protected void b() {
        }

        protected void c() {
        }

        protected void i() {
        }

        protected void j() {
        }

        protected void k() {
        }

        protected void d() {
        }

        public void update(Observable observable, final Object obj) {
            if (obj != null) {
                if (com.qrcomic.util.g.a()) {
                    com.qrcomic.util.g.b(g.a, com.qrcomic.util.g.d, "QRComicObservable update data is null");
                }
                new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                    final /* synthetic */ a b;

                    public void run() {
                        this.b.a(obj);
                    }
                });
            }
        }

        private final void a(Object obj) {
            if (obj instanceof Object[]) {
                Object[] objArr = (Object[]) obj;
                Object[] objArr2 = (Object[]) objArr[1];
                switch (((Integer) objArr[0]).intValue()) {
                    case 1:
                        a();
                        return;
                    case 2:
                        e();
                        return;
                    case 3:
                        f();
                        return;
                    case 4:
                        g();
                        return;
                    case 5:
                        h();
                        return;
                    case 7:
                        b();
                        return;
                    case 8:
                        c();
                        return;
                    case 9:
                        i();
                        return;
                    case 10:
                        j();
                        return;
                    case 12:
                        k();
                        return;
                    case 13:
                        d();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public g(h hVar) {
        this.b = hVar;
    }

    public void a() {
        setChanged();
        notifyObservers(new Object[]{Integer.valueOf(1), new Object[0]});
    }

    public void b() {
        setChanged();
        notifyObservers(new Object[]{Integer.valueOf(2), new Object[0]});
    }

    public void c() {
        setChanged();
        notifyObservers(new Object[]{Integer.valueOf(4), new Object[0]});
    }

    public void d() {
        setChanged();
        notifyObservers(new Object[]{Integer.valueOf(3), new Object[0]});
    }

    public void a(Bundle bundle) {
        setChanged();
        Object obj = new Object[2];
        obj[0] = Integer.valueOf(7);
        obj[1] = new Object[]{bundle};
        notifyObservers(obj);
    }

    public void e() {
        setChanged();
        notifyObservers(new Object[]{Integer.valueOf(8), new Object[0]});
    }

    public void f() {
        setChanged();
        notifyObservers(new Object[]{Integer.valueOf(13), new Object[0]});
    }

    public void g() {
        setChanged();
        notifyObservers(new Object[]{Integer.valueOf(12), new Object[0]});
    }
}

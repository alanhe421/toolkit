package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.util.h;
import android.view.LayoutInflater;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* compiled from: FragmentHostCallback */
public abstract class j<E> extends h {
    private final Activity a;
    final Context b;
    final int c;
    final l d;
    private final Handler e;
    private h<String, o> f;
    private p g;
    private boolean h;
    private boolean i;

    public abstract E g();

    j(FragmentActivity fragmentActivity) {
        this(fragmentActivity, fragmentActivity, fragmentActivity.mHandler, 0);
    }

    j(Activity activity, Context context, Handler handler, int i) {
        this.d = new l();
        this.a = activity;
        this.b = context;
        this.e = handler;
        this.c = i;
    }

    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public boolean a(Fragment fragment) {
        return true;
    }

    public LayoutInflater b() {
        return (LayoutInflater) this.b.getSystemService("layout_inflater");
    }

    public void d() {
    }

    public void a(Fragment fragment, Intent intent, int i) {
        if (i != -1) {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        }
        this.b.startActivity(intent);
    }

    public void a(Fragment fragment, String[] strArr, int i) {
    }

    public boolean a(String str) {
        return false;
    }

    public boolean e() {
        return true;
    }

    public int f() {
        return this.c;
    }

    public View a(int i) {
        return null;
    }

    public boolean a() {
        return true;
    }

    Activity h() {
        return this.a;
    }

    Context i() {
        return this.b;
    }

    Handler j() {
        return this.e;
    }

    l k() {
        return this.d;
    }

    p l() {
        if (this.g != null) {
            return this.g;
        }
        this.h = true;
        this.g = a("(root)", this.i, true);
        return this.g;
    }

    void b(String str) {
        if (this.f != null) {
            p pVar = (p) this.f.get(str);
            if (pVar != null && !pVar.f) {
                pVar.h();
                this.f.remove(str);
            }
        }
    }

    void b(Fragment fragment) {
    }

    void m() {
        if (!this.i) {
            this.i = true;
            if (this.g != null) {
                this.g.b();
            } else if (!this.h) {
                this.g = a("(root)", this.i, false);
                if (!(this.g == null || this.g.e)) {
                    this.g.b();
                }
            }
            this.h = true;
        }
    }

    void a(boolean z) {
        if (this.g != null && this.i) {
            this.i = false;
            if (z) {
                this.g.d();
            } else {
                this.g.c();
            }
        }
    }

    void n() {
        if (this.g != null) {
            this.g.h();
        }
    }

    void o() {
        if (this.f != null) {
            int size = this.f.size();
            p[] pVarArr = new p[size];
            for (int i = size - 1; i >= 0; i--) {
                pVarArr[i] = (p) this.f.c(i);
            }
            for (int i2 = 0; i2 < size; i2++) {
                p pVar = pVarArr[i2];
                pVar.e();
                pVar.g();
            }
        }
    }

    p a(String str, boolean z, boolean z2) {
        if (this.f == null) {
            this.f = new h();
        }
        p pVar = (p) this.f.get(str);
        if (pVar != null) {
            pVar.a(this);
            return pVar;
        } else if (!z2) {
            return pVar;
        } else {
            pVar = new p(str, this, z);
            this.f.put(str, pVar);
            return pVar;
        }
    }

    h<String, o> p() {
        int i;
        int i2 = 0;
        if (this.f != null) {
            int size = this.f.size();
            p[] pVarArr = new p[size];
            for (int i3 = size - 1; i3 >= 0; i3--) {
                pVarArr[i3] = (p) this.f.c(i3);
            }
            i = 0;
            while (i2 < size) {
                p pVar = pVarArr[i2];
                if (pVar.f) {
                    i = 1;
                } else {
                    pVar.h();
                    this.f.remove(pVar.d);
                }
                i2++;
            }
        } else {
            i = 0;
        }
        if (i != 0) {
            return this.f;
        }
        return null;
    }

    void a(h<String, o> hVar) {
        this.f = hVar;
    }

    void b(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mLoadersStarted=");
        printWriter.println(this.i);
        if (this.g != null) {
            printWriter.print(str);
            printWriter.print("Loader Manager ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this.g)));
            printWriter.println(":");
            this.g.a(str + "  ", fileDescriptor, printWriter, strArr);
        }
    }
}

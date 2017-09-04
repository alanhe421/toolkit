package android.support.v4.content;

import java.io.FileDescriptor;
import java.io.PrintWriter;

/* compiled from: Loader */
public class c<D> {
    int a;
    b<D> b;
    a<D> c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;

    /* compiled from: Loader */
    public interface a<D> {
    }

    /* compiled from: Loader */
    public interface b<D> {
    }

    public void a(int i, b<D> bVar) {
        if (this.b != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.b = bVar;
        this.a = i;
    }

    public void a(b<D> bVar) {
        if (this.b == null) {
            throw new IllegalStateException("No listener register");
        } else if (this.b != bVar) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        } else {
            this.b = null;
        }
    }

    public void a(a<D> aVar) {
        if (this.c != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.c = aVar;
    }

    public void b(a<D> aVar) {
        if (this.c == null) {
            throw new IllegalStateException("No listener register");
        } else if (this.c != aVar) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        } else {
            this.c = null;
        }
    }

    public final void a() {
        this.d = true;
        this.f = false;
        this.e = false;
        b();
    }

    protected void b() {
    }

    public void c() {
        this.d = false;
        d();
    }

    protected void d() {
    }

    public void e() {
        f();
        this.f = true;
        this.d = false;
        this.e = false;
        this.g = false;
        this.h = false;
    }

    protected void f() {
    }

    public String a(D d) {
        StringBuilder stringBuilder = new StringBuilder(64);
        android.support.v4.util.c.a(d, stringBuilder);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(64);
        android.support.v4.util.c.a(this, stringBuilder);
        stringBuilder.append(" id=");
        stringBuilder.append(this.a);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mId=");
        printWriter.print(this.a);
        printWriter.print(" mListener=");
        printWriter.println(this.b);
        if (this.d || this.g || this.h) {
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.print(this.d);
            printWriter.print(" mContentChanged=");
            printWriter.print(this.g);
            printWriter.print(" mProcessingChange=");
            printWriter.println(this.h);
        }
        if (this.e || this.f) {
            printWriter.print(str);
            printWriter.print("mAbandoned=");
            printWriter.print(this.e);
            printWriter.print(" mReset=");
            printWriter.println(this.f);
        }
    }
}

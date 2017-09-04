package qalsdk;

/* compiled from: EchoTask */
public abstract class w implements Runnable {
    public static final int a = 0;
    public static final int b = -1;
    public static final int c = -2;
    public static final int d = -3;
    protected int e;
    protected a f;

    /* compiled from: EchoTask */
    public interface a {
        void a(int i, int i2, String str, Object obj);
    }

    protected abstract int a(String str);

    protected abstract boolean a();

    protected abstract void b();

    protected abstract String c();

    protected abstract Object d();

    public w(int i, a aVar) {
        this.e = i;
        this.f = aVar;
    }

    public void run() {
        if (a()) {
            String c = c();
            if (c == null) {
                a(-2, null);
                return;
            } else {
                a(a(c), c);
                return;
            }
        }
        a(-1, null);
    }

    protected void a(int i, String str) {
        b();
        if (this.f != null) {
            this.f.a(this.e, i, str, d());
        }
    }
}

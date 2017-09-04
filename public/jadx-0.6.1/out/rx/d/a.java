package rx.d;

/* compiled from: RxJavaErrorHandler */
public abstract class a {
    public void a(Throwable th) {
    }

    public final String a(Object obj) {
        try {
            return b(obj);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return obj.getClass().getName() + ".errorRendering";
        } catch (Throwable th) {
            rx.exceptions.a.a(th);
            return obj.getClass().getName() + ".errorRendering";
        }
    }

    protected String b(Object obj) throws InterruptedException {
        return null;
    }
}

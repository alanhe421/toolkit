package rx.c;

import java.util.Arrays;
import rx.d.d;
import rx.e;
import rx.exceptions.CompositeException;
import rx.exceptions.OnErrorFailedException;

/* compiled from: SafeSubscriber */
public class a<T> extends e<T> {
    boolean a = false;
    private final e<? super T> b;

    public a(e<? super T> eVar) {
        super(eVar);
        this.b = eVar;
    }

    public void onCompleted() {
        if (!this.a) {
            this.a = true;
            try {
                this.b.onCompleted();
            } catch (Throwable th) {
                rx.exceptions.a.a(th);
                a(th);
            } finally {
                unsubscribe();
            }
        }
    }

    public void onError(Throwable th) {
        rx.exceptions.a.a(th);
        if (!this.a) {
            this.a = true;
            a(th);
        }
    }

    public void onNext(T t) {
        try {
            if (!this.a) {
                this.b.onNext(t);
            }
        } catch (Throwable th) {
            rx.exceptions.a.a(th);
            onError(th);
        }
    }

    protected void a(Throwable th) {
        try {
            d.a().b().a(th);
        } catch (Throwable th2) {
            b(th2);
        }
        OnErrorFailedException onErrorFailedException;
        try {
            this.b.onError(th);
            try {
                unsubscribe();
                return;
            } catch (Throwable e) {
                d.a().b().a(e);
            } catch (Throwable th22) {
                b(th22);
            }
            throw new OnErrorFailedException(e);
            unsubscribe();
            onErrorFailedException = new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError", new CompositeException(Arrays.asList(new Throwable[]{th, th22})));
        } catch (Throwable th3) {
            try {
                d.a().b().a(th3);
            } catch (Throwable e2) {
                b(e2);
            }
            onErrorFailedException = new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError and during unsubscription.", new CompositeException(Arrays.asList(new Throwable[]{th, th22, th3})));
        }
    }

    private void b(Throwable th) {
        System.err.println("RxJavaErrorHandler threw an Exception. It shouldn't. => " + th.getMessage());
        th.printStackTrace();
    }
}

package rx.internal.operators;

import java.io.Serializable;
import rx.b;

public final class NotificationLite<T> {
    private static final NotificationLite a = new NotificationLite();
    private static final Object b = new Serializable() {
        private static final long serialVersionUID = 1;

        public String toString() {
            return "Notification=>Completed";
        }
    };
    private static final Object c = new Serializable() {
        private static final long serialVersionUID = 2;

        public String toString() {
            return "Notification=>NULL";
        }
    };

    private static class OnErrorSentinel implements Serializable {
        private static final long serialVersionUID = 3;
        private final Throwable e;

        public OnErrorSentinel(Throwable th) {
            this.e = th;
        }

        public String toString() {
            return "Notification=>Error:" + this.e;
        }
    }

    private NotificationLite() {
    }

    public static <T> NotificationLite<T> a() {
        return a;
    }

    public Object a(T t) {
        if (t == null) {
            return c;
        }
        return t;
    }

    public Object b() {
        return b;
    }

    public Object a(Throwable th) {
        return new OnErrorSentinel(th);
    }

    public boolean a(b<? super T> bVar, Object obj) {
        if (obj == b) {
            bVar.onCompleted();
            return true;
        } else if (obj == c) {
            bVar.onNext(null);
            return false;
        } else if (obj == null) {
            throw new IllegalArgumentException("The lite notification can not be null");
        } else if (obj.getClass() == OnErrorSentinel.class) {
            bVar.onError(((OnErrorSentinel) obj).e);
            return true;
        } else {
            bVar.onNext(obj);
            return false;
        }
    }

    public boolean b(Object obj) {
        return obj == b;
    }

    public boolean c(Object obj) {
        return obj instanceof OnErrorSentinel;
    }

    public T d(Object obj) {
        return obj == c ? null : obj;
    }

    public Throwable e(Object obj) {
        return ((OnErrorSentinel) obj).e;
    }
}

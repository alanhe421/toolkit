package rx.exceptions;

import java.util.HashSet;
import java.util.Set;
import rx.d.d;

public final class OnErrorThrowable extends RuntimeException {
    private static final long serialVersionUID = -569558213262703934L;
    private final boolean hasValue;
    private final Object value;

    public static class OnNextValue extends RuntimeException {
        private static final long serialVersionUID = -3454462756050397899L;
        private final Object value;

        private static final class a {
            static final Set<Class<?>> a = a();

            private static Set<Class<?>> a() {
                Set<Class<?>> hashSet = new HashSet();
                hashSet.add(Boolean.class);
                hashSet.add(Character.class);
                hashSet.add(Byte.class);
                hashSet.add(Short.class);
                hashSet.add(Integer.class);
                hashSet.add(Long.class);
                hashSet.add(Float.class);
                hashSet.add(Double.class);
                return hashSet;
            }
        }

        public OnNextValue(Object obj) {
            super("OnError while emitting onNext value: " + renderValue(obj));
            this.value = obj;
        }

        public Object getValue() {
            return this.value;
        }

        static String renderValue(Object obj) {
            if (obj == null) {
                return "null";
            }
            if (a.a.contains(obj.getClass())) {
                return obj.toString();
            }
            if (obj instanceof String) {
                return (String) obj;
            }
            if (obj instanceof Enum) {
                return ((Enum) obj).name();
            }
            String a = d.a().b().a(obj);
            if (a != null) {
                return a;
            }
            return obj.getClass().getName() + ".class";
        }
    }

    private OnErrorThrowable(Throwable th) {
        super(th);
        this.hasValue = false;
        this.value = null;
    }

    private OnErrorThrowable(Throwable th, Object obj) {
        super(th);
        this.hasValue = true;
        this.value = obj;
    }

    public Object getValue() {
        return this.value;
    }

    public boolean isValueNull() {
        return this.hasValue;
    }

    public static OnErrorThrowable from(Throwable th) {
        Throwable b = a.b(th);
        if (b instanceof OnNextValue) {
            return new OnErrorThrowable(th, ((OnNextValue) b).getValue());
        }
        return new OnErrorThrowable(th);
    }

    public static Throwable addValueAsLastCause(Throwable th, Object obj) {
        Throwable b = a.b(th);
        if (!(b != null && (b instanceof OnNextValue) && ((OnNextValue) b).getValue() == obj)) {
            a.a(th, new OnNextValue(obj));
        }
        return th;
    }
}

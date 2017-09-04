package rx.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class CompositeException extends RuntimeException {
    private static final long serialVersionUID = 3026362227162912146L;
    private Throwable cause;
    private final List<Throwable> exceptions;
    private final String message;

    static final class CompositeExceptionCausalChain extends RuntimeException {
        static String MESSAGE = "Chain of Causes for CompositeException In Order Received =>";
        private static final long serialVersionUID = 3875212506787802066L;

        CompositeExceptionCausalChain() {
        }

        public String getMessage() {
            return MESSAGE;
        }
    }

    private static abstract class a {
        abstract Object a();

        abstract void a(Object obj);

        private a() {
        }
    }

    private static class b extends a {
        private final PrintStream a;

        b(PrintStream printStream) {
            super();
            this.a = printStream;
        }

        Object a() {
            return this.a;
        }

        void a(Object obj) {
            this.a.println(obj);
        }
    }

    private static class c extends a {
        private final PrintWriter a;

        c(PrintWriter printWriter) {
            super();
            this.a = printWriter;
        }

        Object a() {
            return this.a;
        }

        void a(Object obj) {
            this.a.println(obj);
        }
    }

    public CompositeException(String str, Collection<? extends Throwable> collection) {
        this.cause = null;
        Collection linkedHashSet = new LinkedHashSet();
        List arrayList = new ArrayList();
        if (collection != null) {
            for (Throwable th : collection) {
                if (th instanceof CompositeException) {
                    linkedHashSet.addAll(((CompositeException) th).getExceptions());
                } else if (th != null) {
                    linkedHashSet.add(th);
                } else {
                    linkedHashSet.add(new NullPointerException());
                }
            }
        } else {
            linkedHashSet.add(new NullPointerException());
        }
        arrayList.addAll(linkedHashSet);
        this.exceptions = Collections.unmodifiableList(arrayList);
        this.message = this.exceptions.size() + " exceptions occurred. ";
    }

    public CompositeException(Collection<? extends Throwable> collection) {
        this(null, collection);
    }

    public List<Throwable> getExceptions() {
        return this.exceptions;
    }

    public String getMessage() {
        return this.message;
    }

    public synchronized Throwable getCause() {
        if (this.cause == null) {
            Throwable compositeExceptionCausalChain = new CompositeExceptionCausalChain();
            Set hashSet = new HashSet();
            Throwable th = compositeExceptionCausalChain;
            for (Throwable th2 : this.exceptions) {
                if (!hashSet.contains(th2)) {
                    hashSet.add(th2);
                    Throwable th3 = th2;
                    for (Throwable th22 : getListOfCauses(th22)) {
                        if (hashSet.contains(th22)) {
                            th3 = new RuntimeException("Duplicate found in causal chain so cropping to prevent loop ...");
                        } else {
                            hashSet.add(th22);
                        }
                    }
                    try {
                        th.initCause(th3);
                    } catch (Throwable th4) {
                    }
                    th = th.getCause();
                }
            }
            this.cause = compositeExceptionCausalChain;
        }
        return this.cause;
    }

    public void printStackTrace() {
        printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream printStream) {
        printStackTrace(new b(printStream));
    }

    public void printStackTrace(PrintWriter printWriter) {
        printStackTrace(new c(printWriter));
    }

    private void printStackTrace(a aVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this).append("\n");
        for (Object append : getStackTrace()) {
            stringBuilder.append("\tat ").append(append).append("\n");
        }
        int i = 1;
        for (Throwable th : this.exceptions) {
            stringBuilder.append("  ComposedException ").append(i).append(" :").append("\n");
            appendStackTrace(stringBuilder, th, "\t");
            i++;
        }
        synchronized (aVar.a()) {
            aVar.a(stringBuilder.toString());
        }
    }

    private void appendStackTrace(StringBuilder stringBuilder, Throwable th, String str) {
        stringBuilder.append(str).append(th).append("\n");
        for (Object append : th.getStackTrace()) {
            stringBuilder.append("\t\tat ").append(append).append("\n");
        }
        if (th.getCause() != null) {
            stringBuilder.append("\tCaused by: ");
            appendStackTrace(stringBuilder, th.getCause(), "");
        }
    }

    private final List<Throwable> getListOfCauses(Throwable th) {
        List<Throwable> arrayList = new ArrayList();
        Throwable cause = th.getCause();
        if (cause == null) {
            return arrayList;
        }
        while (true) {
            arrayList.add(cause);
            if (cause.getCause() == null) {
                return arrayList;
            }
            cause = cause.getCause();
        }
    }
}

package okhttp3.internal.connection;

import java.util.LinkedHashSet;
import java.util.Set;
import okhttp3.aa;

/* compiled from: RouteDatabase */
public final class d {
    private final Set<aa> a = new LinkedHashSet();

    public synchronized void a(aa aaVar) {
        this.a.add(aaVar);
    }

    public synchronized void b(aa aaVar) {
        this.a.remove(aaVar);
    }

    public synchronized boolean c(aa aaVar) {
        return this.a.contains(aaVar);
    }
}

package format.epub;

import android.os.HandlerThread;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: EPubCoverReader */
public class a {
    private static a a = null;
    private List<String> b = Collections.synchronizedList(new ArrayList());
    private HandlerThread c = null;

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
    }

    private a() {
    }

    public synchronized void b() {
        if (this.c != null) {
            this.c.quit();
            this.c = null;
        }
        this.b.clear();
    }
}

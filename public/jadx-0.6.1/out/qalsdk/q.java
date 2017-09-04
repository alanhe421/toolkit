package qalsdk;

import java.util.Iterator;
import qalsdk.o.a;

/* compiled from: SocketEngineFactory */
class q extends Thread {
    final /* synthetic */ o a;

    q(o oVar) {
        this.a = oVar;
    }

    public void run() {
        Iterator it = this.a.r.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a();
        }
        this.a.s.addAll(this.a.r);
        this.a.r.clear();
        this.a.t = null;
    }
}

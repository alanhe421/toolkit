package qalsdk;

import android.os.SystemClock;
import com.tencent.qalsdk.base.CloseConnReason;
import com.tencent.qalsdk.util.QLog;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: SocketEngine */
class n$a extends Thread {
    AtomicBoolean a = new AtomicBoolean(true);
    long b = SystemClock.elapsedRealtime();
    final /* synthetic */ n c;

    n$a(n nVar) {
        this.c = nVar;
    }

    public void run() {
        while (this.a.get()) {
            while (!this.c.k.isDataAvailable(n.f)) {
                try {
                    if (!this.a.get()) {
                        return;
                    }
                } catch (Throwable th) {
                    this.a.set(false);
                    QLog.d("MSF.C.NetConnTag", 1, "read DataError " + th);
                    this.c.a(CloseConnReason.readError);
                }
            }
            if (this.a.get()) {
                this.c.i.a(this.c.k);
                this.c.o.addAndGet((long) this.c.k.getBufferlen());
                this.c.k.reset();
            } else {
                return;
            }
        }
    }
}

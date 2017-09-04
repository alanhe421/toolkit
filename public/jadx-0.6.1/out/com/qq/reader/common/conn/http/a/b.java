package com.qq.reader.common.conn.http.a;

import android.os.Message;
import com.qq.reader.common.monitor.debug.c;
import java.io.IOException;
import okhttp3.e;
import okhttp3.y;

/* compiled from: ImplRequestListenerString */
public abstract class b extends a {
    public abstract void b(int i, String str);

    void a(Message message) {
        switch (message.what) {
            case 0:
                a((Exception) message.obj);
                return;
            case 1:
                a(message.arg1, (String) message.obj);
                return;
            case 2:
                b(message.arg1, (String) message.obj);
                return;
            default:
                return;
        }
    }

    public final void a(e eVar, y yVar) throws IOException {
        try {
            if (yVar.c()) {
                String e = yVar.g().e();
                c.a("OKHTTP", "success : " + yVar.toString() + "   ---->   result = " + e);
                if (this.a) {
                    this.b.obtainMessage(2, yVar.b(), 1, e).sendToTarget();
                } else {
                    b(yVar.b(), e);
                }
            } else {
                c.a("OKHTTP", "error : " + yVar.toString() + "   ---->   result = " + null);
                if (this.a) {
                    this.b.obtainMessage(1, yVar.b(), 0, null).sendToTarget();
                } else {
                    a(yVar.b(), null);
                }
            }
            yVar.g().close();
        } catch (Exception e2) {
            c.a("OKHTTP", "failure : " + e2.toString());
            if (this.a) {
                this.b.obtainMessage(0, e2).sendToTarget();
            } else {
                a(e2);
            }
            yVar.g().close();
        } catch (Throwable th) {
            yVar.g().close();
        }
    }
}

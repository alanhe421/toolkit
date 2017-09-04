package okhttp3.internal.connection;

import com.tencent.connect.common.Constants;
import java.io.IOException;
import okhttp3.internal.b.g;
import okhttp3.s;
import okhttp3.u;
import okhttp3.w;
import okhttp3.y;

/* compiled from: ConnectInterceptor */
public final class a implements s {
    public final u a;

    public a(u uVar) {
        this.a = uVar;
    }

    public y a(okhttp3.s.a aVar) throws IOException {
        g gVar = (g) aVar;
        w a = gVar.a();
        f b = gVar.b();
        return gVar.a(a, b, b.a(this.a, !a.b().equals(Constants.HTTP_GET)), b.b());
    }
}

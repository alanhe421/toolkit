package qalsdk;

import com.tencent.android.tpush.common.Constants;
import qalsdk.w.a;

/* compiled from: WifiDetector */
public abstract class ag {
    protected z[] g = new z[]{new aa("http://3gimg.qq.com/qq_product_operations/nettest/index.html", "MobileQQ1"), new aa("http://3gimg.qq.com/qq_product_operations/nettest/index2.html", "MobileQQ2")};

    public abstract void a();

    public abstract void a(String str);

    public abstract void b();

    public abstract void b(String str);

    public abstract void c();

    protected w a(z zVar, int i, a aVar) {
        switch (zVar.d) {
            case 0:
                ab abVar = (ab) zVar;
                return new y(i, abVar.f, abVar.g, abVar.e, Constants.ERRORCODE_UNKNOWN, aVar);
            case 2:
                return new x(i, ((aa) zVar).f, zVar.e, Constants.ERRORCODE_UNKNOWN, aVar);
            default:
                return null;
        }
    }
}

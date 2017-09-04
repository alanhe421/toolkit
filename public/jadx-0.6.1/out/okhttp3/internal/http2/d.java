package okhttp3.internal.http2;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.Protocol;
import okhttp3.internal.b.c;
import okhttp3.internal.b.h;
import okhttp3.internal.b.i;
import okhttp3.internal.b.k;
import okhttp3.internal.connection.f;
import okhttp3.r;
import okhttp3.u;
import okhttp3.w;
import okhttp3.y;
import okhttp3.z;
import okio.ByteString;
import okio.j;
import okio.o;
import okio.p;

/* compiled from: Http2Codec */
public final class d implements c {
    private static final ByteString b = ByteString.encodeUtf8("connection");
    private static final ByteString c = ByteString.encodeUtf8("host");
    private static final ByteString d = ByteString.encodeUtf8("keep-alive");
    private static final ByteString e = ByteString.encodeUtf8("proxy-connection");
    private static final ByteString f = ByteString.encodeUtf8("transfer-encoding");
    private static final ByteString g = ByteString.encodeUtf8("te");
    private static final ByteString h = ByteString.encodeUtf8("encoding");
    private static final ByteString i = ByteString.encodeUtf8("upgrade");
    private static final List<ByteString> j = okhttp3.internal.c.a(b, c, d, e, g, f, h, i, a.c, a.d, a.e, a.f);
    private static final List<ByteString> k = okhttp3.internal.c.a(b, c, d, e, g, f, h, i);
    final f a;
    private final u l;
    private final e m;
    private g n;

    /* compiled from: Http2Codec */
    class a extends okio.f {
        final /* synthetic */ d a;

        public a(d dVar, p pVar) {
            this.a = dVar;
            super(pVar);
        }

        public void close() throws IOException {
            this.a.a.a(false, this.a);
            super.close();
        }
    }

    public d(u uVar, f fVar, e eVar) {
        this.l = uVar;
        this.a = fVar;
        this.m = eVar;
    }

    public o a(w wVar, long j) {
        return this.n.h();
    }

    public void a(w wVar) throws IOException {
        if (this.n == null) {
            this.n = this.m.a(b(wVar), wVar.d() != null);
            this.n.e().a((long) this.l.b(), TimeUnit.MILLISECONDS);
            this.n.f().a((long) this.l.c(), TimeUnit.MILLISECONDS);
        }
    }

    public void a() throws IOException {
        this.m.b();
    }

    public void b() throws IOException {
        this.n.h().close();
    }

    public okhttp3.y.a a(boolean z) throws IOException {
        okhttp3.y.a a = a(this.n.d());
        if (z && okhttp3.internal.a.a.a(a) == 100) {
            return null;
        }
        return a;
    }

    public static List<a> b(w wVar) {
        r c = wVar.c();
        List<a> arrayList = new ArrayList(c.a() + 4);
        arrayList.add(new a(a.c, wVar.b()));
        arrayList.add(new a(a.d, i.a(wVar.a())));
        String a = wVar.a("Host");
        if (a != null) {
            arrayList.add(new a(a.f, a));
        }
        arrayList.add(new a(a.e, wVar.a().b()));
        int a2 = c.a();
        for (int i = 0; i < a2; i++) {
            ByteString encodeUtf8 = ByteString.encodeUtf8(c.a(i).toLowerCase(Locale.US));
            if (!j.contains(encodeUtf8)) {
                arrayList.add(new a(encodeUtf8, c.b(i)));
            }
        }
        return arrayList;
    }

    public static okhttp3.y.a a(List<a> list) throws IOException {
        okhttp3.r.a aVar = new okhttp3.r.a();
        int size = list.size();
        int i = 0;
        k kVar = null;
        while (i < size) {
            okhttp3.r.a aVar2;
            k kVar2;
            a aVar3 = (a) list.get(i);
            if (aVar3 == null) {
                if (kVar != null && kVar.b == 100) {
                    aVar2 = new okhttp3.r.a();
                    kVar2 = null;
                }
                aVar2 = aVar;
                kVar2 = kVar;
            } else {
                ByteString byteString = aVar3.g;
                String utf8 = aVar3.h.utf8();
                if (byteString.equals(a.b)) {
                    okhttp3.r.a aVar4 = aVar;
                    kVar2 = k.a("HTTP/1.1 " + utf8);
                    aVar2 = aVar4;
                } else {
                    if (!k.contains(byteString)) {
                        okhttp3.internal.a.a.a(aVar, byteString.utf8(), utf8);
                    }
                    aVar2 = aVar;
                    kVar2 = kVar;
                }
            }
            i++;
            kVar = kVar2;
            aVar = aVar2;
        }
        if (kVar != null) {
            return new okhttp3.y.a().a(Protocol.HTTP_2).a(kVar.b).a(kVar.c).a(aVar.a());
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    public z a(y yVar) throws IOException {
        return new h(yVar.f(), j.a(new a(this, this.n.g())));
    }

    public void c() {
        if (this.n != null) {
            this.n.b(ErrorCode.CANCEL);
        }
    }
}

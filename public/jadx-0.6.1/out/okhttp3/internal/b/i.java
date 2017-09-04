package okhttp3.internal.b;

import java.net.Proxy.Type;
import okhttp3.HttpUrl;
import okhttp3.w;

/* compiled from: RequestLine */
public final class i {
    public static String a(w wVar, Type type) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(wVar.b());
        stringBuilder.append(' ');
        if (b(wVar, type)) {
            stringBuilder.append(wVar.a());
        } else {
            stringBuilder.append(a(wVar.a()));
        }
        stringBuilder.append(" HTTP/1.1");
        return stringBuilder.toString();
    }

    private static boolean b(w wVar, Type type) {
        return !wVar.g() && type == Type.HTTP;
    }

    public static String a(HttpUrl httpUrl) {
        String h = httpUrl.h();
        String j = httpUrl.j();
        return j != null ? h + '?' + j : h;
    }
}

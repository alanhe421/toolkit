package okhttp3.internal.b;

import java.util.List;
import java.util.regex.Pattern;
import okhttp3.HttpUrl;
import okhttp3.l;
import okhttp3.m;
import okhttp3.r;
import okhttp3.y;

/* compiled from: HttpHeaders */
public final class e {
    private static final Pattern a = Pattern.compile(" +([^ \"=]*)=(:?\"([^\"]*)\"|([^ \"=]*)) *(:?,|$)");

    public static long a(y yVar) {
        return a(yVar.f());
    }

    public static long a(r rVar) {
        return a(rVar.a("Content-Length"));
    }

    private static long a(String str) {
        long j = -1;
        if (str != null) {
            try {
                j = Long.parseLong(str);
            } catch (NumberFormatException e) {
            }
        }
        return j;
    }

    public static void a(m mVar, HttpUrl httpUrl, r rVar) {
        if (mVar != m.a) {
            List a = l.a(httpUrl, rVar);
            if (!a.isEmpty()) {
                mVar.a(httpUrl, a);
            }
        }
    }

    public static boolean b(y yVar) {
        if (yVar.a().b().equals("HEAD")) {
            return false;
        }
        int b = yVar.b();
        if ((b < 100 || b >= 200) && b != 204 && b != 304) {
            return true;
        }
        if (a(yVar) != -1 || "chunked".equalsIgnoreCase(yVar.a("Transfer-Encoding"))) {
            return true;
        }
        return false;
    }

    public static int a(String str, int i, String str2) {
        while (i < str.length() && str2.indexOf(str.charAt(i)) == -1) {
            i++;
        }
        return i;
    }

    public static int a(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt != ' ' && charAt != '\t') {
                break;
            }
            i++;
        }
        return i;
    }

    public static int b(String str, int i) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            if (parseLong < 0) {
                return 0;
            }
            return (int) parseLong;
        } catch (NumberFormatException e) {
            return i;
        }
    }
}

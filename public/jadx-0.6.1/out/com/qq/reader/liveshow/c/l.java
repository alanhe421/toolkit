package com.qq.reader.liveshow.c;

import android.text.TextUtils;
import com.qq.reader.liveshow.utils.SxbLog;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPOutputStream;
import okhttp3.e;
import okhttp3.f;
import okhttp3.s;
import okhttp3.t;
import okhttp3.u;
import okhttp3.u.a;
import okhttp3.w;
import okhttp3.x;
import okhttp3.y;

/* compiled from: OKhttpHelper */
public class l {
    public static final t a = t.a("application/json; charset=utf-8");
    private static final String b = l.class.getSimpleName();
    private static l c = null;
    private u d = new a().a(5, TimeUnit.SECONDS).b(5, TimeUnit.SECONDS).a(new s(this) {
        final /* synthetic */ l a;

        {
            this.a = r1;
        }

        public y a(s.a aVar) throws IOException {
            w a = aVar.a();
            y a2 = aVar.a(a);
            int i = 0;
            while (!a2.c() && i < 3) {
                SxbLog.c("OKHTTP", "Request is not successful - " + i);
                i++;
                a2 = aVar.a(a);
            }
            return a2;
        }
    }).a();

    public static synchronized l a() {
        l lVar;
        synchronized (l.class) {
            if (c == null) {
                c = new l();
            }
            lVar = c;
        }
        return lVar;
    }

    private void a(w.a aVar, Map<String, String> map) {
        if (map != null && aVar != null) {
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                String str2 = (String) entry.getValue();
                if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
                    aVar.b(a(str), a(str2));
                }
            }
        }
    }

    private String a(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt <= '\u001f' || charAt >= '') {
                return URLEncoder.encode(str);
            }
        }
        return str;
    }

    public e a(String str, f fVar, Map<String, String> map) throws IOException {
        w.a a = new w.a().a(str);
        a(a, map);
        e a2 = this.d.a(a.b());
        a2.a(fVar);
        return a2;
    }

    public e a(String str, String str2, boolean z, f fVar, Map<String, String> map) throws IOException {
        x a;
        if (z) {
            byte[] bytes = str2.getBytes("UTF-8");
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            OutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bytes);
            gZIPOutputStream.close();
            a = x.a(a, byteArrayOutputStream.toByteArray());
        } else {
            a = x.a(a, str2);
        }
        w.a a2 = new w.a().a(str).a(a);
        a(a2, map);
        e a3 = this.d.a(a2.b());
        a3.a(fVar);
        return a3;
    }
}

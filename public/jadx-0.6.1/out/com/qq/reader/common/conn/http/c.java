package com.qq.reader.common.conn.http;

import com.qq.reader.common.utils.ao;
import com.tencent.connect.common.Constants;
import java.io.InputStream;
import java.util.HashMap;
import okhttp3.r;
import okhttp3.s;
import okhttp3.t;
import okhttp3.u;
import okhttp3.w;
import okhttp3.w.a;
import okhttp3.x;
import okhttp3.y;

/* compiled from: QRHttpUtil */
public class c {
    public static InputStream a(String str, byte[] bArr, String str2, HashMap<String, String> hashMap, String str3, s sVar, s sVar2) throws Throwable {
        return a(a.a(sVar, sVar2), str, bArr, str2, hashMap, str3).g().d();
    }

    private static y a(u uVar, String str, byte[] bArr, String str2, HashMap<String, String> hashMap, String str3) throws Throwable {
        return a(uVar, a(str, bArr, str2, hashMap, str3));
    }

    private static w a(String str, byte[] bArr, String str2, HashMap<String, String> hashMap, String str3) throws Throwable {
        a aVar = new a();
        if (!Constants.HTTP_GET.equals(str2) || bArr == null || bArr.length <= 0) {
            aVar.a(str);
        } else {
            aVar.a(a(str, bArr));
        }
        if (hashMap != null && hashMap.size() > 0) {
            aVar.a(r.a(hashMap));
        }
        if (Constants.HTTP_POST.equals(str2)) {
            aVar.a(a(str3, bArr, "gzip".equals(hashMap.get("Accept-Encoding"))));
        }
        return aVar.b();
    }

    private static y a(u uVar, w wVar) throws Throwable {
        y a = uVar.a(wVar).a();
        int b = a.b();
        if (b == 200) {
            return a;
        }
        throw new HttpResponseException(b);
    }

    private static String a(String str, byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(str);
        if (bArr != null) {
            stringBuffer.append("?");
            stringBuffer.append(new String(bArr));
        }
        return stringBuffer.toString();
    }

    private static x a(String str, byte[] bArr, boolean z) throws Throwable {
        t a = t.a(str);
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        if (z) {
            return x.a(a, ao.b(bArr));
        }
        return x.a(a, bArr);
    }

    public static void a(com.qq.reader.common.conn.http.a.a aVar, String str, byte[] bArr, String str2, HashMap<String, String> hashMap, String str3, s sVar, s sVar2) throws Throwable {
        if (aVar == null || str == null || str2 == null) {
            throw new NullPointerException();
        }
        a.a(sVar, sVar2).a(a(str, bArr, str2, hashMap, str3)).a(aVar);
    }
}

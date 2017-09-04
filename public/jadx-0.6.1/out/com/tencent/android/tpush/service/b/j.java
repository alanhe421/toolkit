package com.tencent.android.tpush.service.b;

import android.content.Context;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.encrypt.a;
import com.tencent.android.tpush.service.d.f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class j {
    private static j a = new j();
    private static JSONObject b = new JSONObject();
    private static String c = a.a("com.tencent.tpush.multpkgs");

    private j() {
    }

    public static j a() {
        return a;
    }

    private JSONObject b(Context context) {
        String a = f.a(context, c, true);
        JSONObject jSONObject = new JSONObject();
        if (!(a == null || a.length() == 0)) {
            try {
                return new JSONObject(Rijndael.decrypt(a));
            } catch (Throwable e) {
                com.tencent.android.tpush.a.a.c(Constants.ServiceLogTag, "loadRegisterPkgs", e);
            }
        }
        return jSONObject;
    }

    private void c(Context context) {
        if (b != null && b.length() != 0) {
            f.a(context, c, Rijndael.encrypt(b.toString()), true);
        }
    }

    public List a(Context context, long j) {
        Collection arrayList = new ArrayList();
        if (context != null) {
            try {
                b = b(context);
                Collections.addAll(arrayList, b.getString(j + "").split(","));
            } catch (Throwable e) {
                com.tencent.android.tpush.a.a.b(Constants.ServiceLogTag, "getPkgs", e);
            }
        }
        return arrayList;
    }

    public void a(Context context, long j, String str) {
        if (context != null && !f.a(str)) {
            try {
                List arrayList;
                List<String> a = a(context, j);
                if (a == null) {
                    arrayList = new ArrayList();
                } else {
                    for (String equals : a) {
                        if (str.equals(equals)) {
                            return;
                        }
                    }
                    arrayList = a;
                }
                arrayList.add(str);
                String equals2 = arrayList.toString().replace(" ", "").replace("\t", "");
                b.put(j + "", equals2.substring(1, equals2.length() - 1));
                c(context);
            } catch (Throwable e) {
                com.tencent.android.tpush.a.a.c(Constants.ServiceLogTag, "putPkg", e);
            }
        }
    }

    public void a(Context context, String str) {
        if (context != null && str != null && str.length() != 0) {
            try {
                Iterator keys = b.keys();
                while (keys.hasNext()) {
                    String str2 = (String) keys.next();
                    List a = a(context, Long.valueOf(str2).longValue());
                    Collection arrayList = new ArrayList();
                    for (int i = 0; i < a.size(); i++) {
                        if (str.equals(a.get(i))) {
                            arrayList.add(a.get(i));
                        }
                    }
                    a.removeAll(arrayList);
                    String replace = a.toString().replace(" ", "").replace("\t", "");
                    b.put(str2, replace.substring(1, replace.length() - 1));
                    c(context);
                }
            } catch (Throwable e) {
                com.tencent.android.tpush.a.a.c(Constants.LogTag, "delPkg", e);
            }
        }
    }

    public void a(Context context) {
        f.a(context, c, "", true);
    }
}

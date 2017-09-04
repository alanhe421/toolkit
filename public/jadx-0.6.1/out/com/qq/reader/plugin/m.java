package com.qq.reader.plugin;

import android.content.Context;
import com.qq.reader.appconfig.a.b;
import com.tencent.connect.common.Constants;
import java.util.HashMap;
import java.util.Map.Entry;

/* compiled from: PluginHandlerManager */
public class m extends b {
    private static volatile m a;
    private static HashMap<String, a> b;

    private m() {
    }

    public static HashMap<String, a> b() {
        return b;
    }

    public static m c() {
        if (a == null) {
            synchronized (m.class) {
                if (a == null) {
                    a = new m();
                    b = new HashMap();
                }
            }
        }
        return a;
    }

    protected a a(Context context, l lVar) {
        a aVar;
        String k = lVar.k();
        String j = lVar.j();
        if (b.containsKey(j)) {
            aVar = (a) b.get(j);
            if (aVar != null) {
                l b = aVar.b();
                if (b != null) {
                    String m = b.m();
                    String b2 = b.b();
                    String c = b.c();
                    String m2 = lVar.m();
                    String b3 = lVar.b();
                    String c2 = lVar.c();
                    if (m.equals(m2) && b2.equals(b3) && c.equals(c2)) {
                        return (a) b.get(j);
                    }
                }
            }
        }
        h b4 = k.b();
        if ("2".equals(k)) {
            aVar = new f(context, lVar, b4);
        } else if ("4".equals(k) || "5".equals(k)) {
            aVar = new i(context, lVar, b4);
        } else if (Constants.VIA_SHARE_TYPE_INFO.equals(k)) {
            aVar = new d(context, lVar, b4);
        } else if ("7".equals(k)) {
            aVar = new d(context, lVar, b4);
        } else if ("8".equals(k)) {
            aVar = new d(context, lVar, b4);
        } else if (Constants.DEFAULT_UIN.equals(k)) {
            aVar = new y(context, lVar, v.b());
        } else {
            aVar = new b(context, lVar, b4);
        }
        b.put(j, aVar);
        return aVar;
    }

    public a b(Context context, l lVar) {
        String k = lVar.k();
        h b = k.b();
        if ("2".equals(k)) {
            return new f(context, lVar, b);
        }
        if ("5".equals(k) || "4".equals(k)) {
            return new i(context, lVar, b);
        }
        if (Constants.VIA_SHARE_TYPE_INFO.equals(k)) {
            return new d(context, lVar, b);
        }
        if ("7".equals(k)) {
            return new d(context, lVar, b);
        }
        if ("8".equals(k)) {
            return new d(context, lVar, b);
        }
        if (Constants.DEFAULT_UIN.equals(k)) {
            return new y(context, lVar, v.b());
        }
        return new b(context, lVar, b);
    }

    public a a(String str) {
        if (str == null || !b.containsKey(str)) {
            return null;
        }
        return (a) b.get(str);
    }

    public void d() {
        for (Entry value : b.entrySet()) {
            a aVar = (a) value.getValue();
            int e = aVar.e();
            if (e == 2 || e == 3) {
                aVar.u();
            }
        }
        b.clear();
    }

    public void a() {
        synchronized (m.class) {
            d();
            a = null;
        }
    }
}

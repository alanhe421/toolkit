package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.xiaomi.channel.commonutils.android.b;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.push.service.at;
import com.xiaomi.push.service.au;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.ar;
import com.xiaomi.xmpush.thrift.k;
import com.xiaomi.xmpush.thrift.p;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.thrift.a;
import org.apache.thrift.f;

public class t {
    private static volatile t a;
    private final String b = "GeoFenceRegMessageProcessor.";
    private Context c;

    private t(Context context) {
        this.c = context;
    }

    public static t a(Context context) {
        if (a == null) {
            synchronized (t.class) {
                if (a == null) {
                    a = new t(context);
                }
            }
        }
        return a;
    }

    private k a(af afVar, boolean z) {
        if (z && !au.a(this.c)) {
            return null;
        }
        if (z && !au.c(this.c)) {
            return null;
        }
        try {
            a kVar = new k();
            ar.a(kVar, afVar.m());
            return kVar;
        } catch (f e) {
            e.printStackTrace();
            return null;
        }
    }

    private com.xiaomi.xmpush.thrift.t a(boolean z) {
        com.xiaomi.xmpush.thrift.t tVar = new com.xiaomi.xmpush.thrift.t();
        Set treeSet = new TreeSet();
        if (z) {
            Iterator it = com.xiaomi.push.service.ar.a(this.c).a().iterator();
            while (it.hasNext()) {
                treeSet.add((k) it.next());
            }
        }
        tVar.a(treeSet);
        return tVar;
    }

    private void a(k kVar) {
        byte[] a = ar.a(kVar);
        a afVar = new af(WeiboAuthException.DEFAULT_AUTH_ERROR_CODE, false);
        afVar.c(p.J.T);
        afVar.a(a);
        al.a(this.c).a(afVar, com.xiaomi.xmpush.thrift.a.i, true, null);
        c.a("GeoFenceRegMessageProcessor.report package not exist geo_fencing id:" + kVar.a());
    }

    private void a(k kVar, boolean z) {
        byte[] a = ar.a(kVar);
        a afVar = new af(WeiboAuthException.DEFAULT_AUTH_ERROR_CODE, false);
        afVar.c(z ? p.E.T : p.G.T);
        afVar.a(a);
        al.a(this.c).a(afVar, com.xiaomi.xmpush.thrift.a.i, true, null);
        c.a("GeoFenceRegMessageProcessor.report geo_fencing id:" + kVar.a() + " " + (z ? "geo_reg" : "geo_unreg"));
    }

    public static boolean a(Map<String, String> map) {
        return map == null ? false : TextUtils.equals("1", (CharSequence) map.get("__geo_local_cache"));
    }

    private boolean d(af afVar) {
        return a(afVar.i()) && au.d(this.c);
    }

    public void a(af afVar) {
        if (au.e(this.c)) {
            boolean d = d(afVar);
            k a = a(afVar, d);
            if (a == null) {
                c.d("registration convert geofence object failed notification_id:" + afVar.c());
            } else if (b.g(this.c, a.g())) {
                if (d) {
                    if (com.xiaomi.push.service.ar.a(this.c).a(a) == -1) {
                        c.a("GeoFenceRegMessageProcessor. insert a new geofence failed about geo_id:" + a.a());
                    }
                    new u(this.c).a(a);
                    a(a, true);
                    c.a("receive geo reg notification");
                    return;
                }
                a(a, true);
            } else if (d) {
                a(a);
            }
        }
    }

    public void b(af afVar) {
        if (au.e(this.c)) {
            boolean d = d(afVar);
            k a = a(afVar, d);
            if (a == null) {
                c.d("unregistration convert geofence object failed notification_id:" + afVar.c());
            } else if (b.g(this.c, a.g())) {
                if (d) {
                    if (com.xiaomi.push.service.ar.a(this.c).d(a.a()) == 0) {
                        c.a("GeoFenceRegMessageProcessor. delete a geofence about geo_id:" + a.a() + " falied");
                    }
                    if (at.a(this.c).b(a.a()) == 0) {
                        c.a("GeoFenceRegMessageProcessor. delete all geofence messages about geo_id:" + a.a() + " failed");
                    }
                    new u(this.c).a(a.a());
                    a(a, false);
                    c.a("receive geo unreg notification");
                    return;
                }
                a(a, false);
            } else if (d) {
                a(a);
            }
        }
    }

    public void c(af afVar) {
        if (au.e(this.c)) {
            boolean d = d(afVar);
            if (d && !au.a(this.c)) {
                return;
            }
            if ((!d || au.c(this.c)) && b.g(this.c, afVar.i)) {
                Object a = a(d);
                byte[] a2 = ar.a(a);
                a afVar2 = new af(WeiboAuthException.DEFAULT_AUTH_ERROR_CODE, false);
                afVar2.c(p.I.T);
                afVar2.a(a2);
                al.a(this.c).a(afVar2, com.xiaomi.xmpush.thrift.a.i, true, null);
                c.c("GeoFenceRegMessageProcessor.sync_geo_data. geos size:" + a.a().size());
            }
        }
    }
}

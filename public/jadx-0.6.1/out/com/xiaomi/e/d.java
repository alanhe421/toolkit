package com.xiaomi.e;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.channel.commonutils.c.g;
import com.xiaomi.push.service.y;
import com.xiaomi.xmpush.thrift.e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class d {
    private static d a;
    private final Context b;
    private Map<String, e> c = new HashMap();
    private final HashMap<String, ArrayList<e>> d = new HashMap();
    private final HashMap<String, ArrayList<e>> e = new HashMap();

    private d(Context context) {
        this.b = context;
    }

    public static d a(Context context) {
        if (context == null) {
            c.d("[TinyDataManager]:mContext is null, TinyDataManager.getInstance(Context) failed.");
            return null;
        }
        if (a == null) {
            synchronized (d.class) {
                if (a == null) {
                    a = new d(context);
                }
            }
        }
        return a;
    }

    Context a() {
        return this.b;
    }

    public void a(e eVar, String str) {
        if (eVar == null) {
            c.d("[TinyDataManager]: please do not add null mUploader to TinyDataManager.");
        } else if (TextUtils.isEmpty(str)) {
            c.d("[TinyDataManager]: can not add a provider from unkown resource.");
        } else {
            Runnable aVar = new a(this);
            aVar.b = str;
            aVar.a = eVar;
            g.a(this.b).a(aVar);
        }
    }

    void a(e eVar, String str) {
        ArrayList arrayList;
        if ("com.xiaomi.xmsf".equals(str)) {
            CharSequence j = eVar.j();
            if (!TextUtils.isEmpty(j)) {
                CharSequence charSequence = j;
            }
            arrayList = (ArrayList) this.e.get(r5);
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.e.put(r5, arrayList);
            }
        } else {
            arrayList = (ArrayList) this.d.get(str);
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.d.put(str, arrayList);
            }
        }
        arrayList.add(eVar);
        if (arrayList.size() > 80) {
            arrayList.remove(0);
        }
    }

    public void a(String str) {
        Runnable bVar = new b(this);
        bVar.b = str;
        g.a(this.b).a(bVar);
    }

    e b() {
        e eVar = (e) this.c.get("UPLOADER_PUSH_CHANNEL");
        if (eVar != null) {
            return eVar;
        }
        eVar = (e) this.c.get("UPLOADER_HTTP");
        return eVar == null ? null : eVar;
    }

    public boolean b(e eVar, String str) {
        if (TextUtils.isEmpty(str)) {
            c.a("pkgName is null or empty, upload ClientUploadDataItem failed.");
            return false;
        } else if (y.a(eVar, false)) {
            return false;
        } else {
            Runnable cVar = new c(this);
            cVar.b = eVar;
            cVar.c = str;
            g.a(this.b).a(cVar);
            return true;
        }
    }

    Map<String, e> c() {
        return this.c;
    }

    Map<String, ArrayList<e>> d() {
        return this.d;
    }

    Map<String, ArrayList<e>> e() {
        return this.e;
    }
}

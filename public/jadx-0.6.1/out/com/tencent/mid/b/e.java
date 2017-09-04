package com.tencent.mid.b;

import android.content.Context;
import com.tencent.mid.util.Util;
import com.tencent.mid.util.f;
import com.tencent.mid.util.i;

public class e extends f {
    protected static f a = Util.getLogger();

    public e(Context context, int i) {
        super(context, i);
    }

    public int a() {
        return 1;
    }

    protected void a(a aVar) {
        synchronized (this) {
            a.b("write CheckEntity to Settings.System:" + aVar.toString());
            i.a(this.c).a(g(), aVar.toString());
        }
    }

    protected void a(String str) {
        synchronized (this) {
            a.b((Object) "write mid to Settings.System");
            i.a(this.c).a(h(), str);
        }
    }

    protected boolean b() {
        return Util.checkPermission(this.c, "android.permission.WRITE_SETTINGS");
    }

    protected String c() {
        String a;
        synchronized (this) {
            a.b((Object) "read mid from Settings.System");
            a = i.a(this.c).a(h());
        }
        return a;
    }

    protected a d() {
        a aVar;
        synchronized (this) {
            aVar = new a(i.a(this.c).a(g()));
            a.b("read readCheckEntity from Settings.System:" + aVar.toString());
        }
        return aVar;
    }
}

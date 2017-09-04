package com.sijla.g.a;

import android.content.Context;
import android.os.Build.VERSION;
import com.sijla.j.a.c;
import com.sijla.j.b;
import com.sijla.j.f;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class a implements b {
    public void a(Context context, String str) {
        if (!b.a(str) && str.contains(".") && !str.contains(" ")) {
            try {
                if (!a(str)) {
                    Object obj = VERSION.SDK_INT >= 20 ? 1 : null;
                    List arrayList = new ArrayList();
                    arrayList.add(b.p(context));
                    arrayList.add(str);
                    arrayList.add(com.sijla.j.a.a.d(context, str));
                    arrayList.add(com.sijla.j.a.a.a(str, context));
                    arrayList.add(b.g() + "");
                    arrayList.add(com.sijla.j.a.a.e(context));
                    arrayList.add(b.h(context));
                    arrayList.add(obj != null ? "1" : "0");
                    com.sijla.common.b.e().a(b.f("mdau") + "", arrayList);
                    b(str);
                    f.a("DauEvent.handle appid = [" + str + "] success");
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private boolean a(String str) {
        File[] listFiles = new File(b.l()).listFiles();
        if (listFiles == null) {
            return false;
        }
        for (File name : listFiles) {
            if (name.getName().equals(c.b(str))) {
                return true;
            }
        }
        return false;
    }

    private void b(String str) {
        com.sijla.j.a.b.a("", b.l() + c.b(str), false);
    }
}

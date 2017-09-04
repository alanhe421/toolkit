package com.sijla.h;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import com.sijla.common.a;
import com.sijla.d.c;
import com.sijla.j.f;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class b implements a {
    private static final String b = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator);
    private List<a> a = new ArrayList();
    private Context c;
    private List<String[]> d;

    public b(Context context) {
        this.c = context;
        this.d = c.g;
    }

    public void run() {
        f();
    }

    public void f() {
        f.a("APKFunnerManager.startCollector");
        if (com.sijla.j.a.a.a(this.c)) {
            try {
                if (this.d == null || this.d.size() <= 0) {
                    f.a("KV EMPTY CANCEL WDOG");
                    return;
                }
                this.a.clear();
                f.a("aksize=" + this.d.size());
                for (String[] strArr : this.d) {
                    String str = strArr[0];
                    String str2 = b + strArr[1];
                    if (a(this.c, str)) {
                        if (g().equals(str)) {
                            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator);
                            if (file.exists() && file.isDirectory()) {
                                File[] listFiles = file.listFiles();
                                if (listFiles != null) {
                                    for (File file2 : listFiles) {
                                        if (file2.getName().toLowerCase().startsWith("baiduas")) {
                                            str2 = file2.getAbsolutePath() + "/";
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        a aVar = new a(this.c, str, str2);
                        this.a.add(aVar);
                        aVar.startWatching();
                    }
                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        f.a("APKFunnerManager.startCollector cancel");
    }

    private String g() {
        try {
            return com.sijla.f.b.b("34fasmcs", "L3tJlNpCAfZRaIB7ha4mFl6LmRXzMtmk");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private boolean a(Context context, String str) {
        return com.sijla.j.a.a.e(context, str);
    }

    public void a() {
        f();
    }

    public void b() {
        f.a("APKFunnerManager.onScreenOff");
        try {
            if (this.a != null && this.a.size() > 0) {
                for (a stopWatching : this.a) {
                    stopWatching.stopWatching();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
    }

    public void a(Intent intent) {
    }
}

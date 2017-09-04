package com.tencent.feedback.eup.jni;

import android.content.Context;
import com.tencent.feedback.common.d;
import com.tencent.feedback.common.e;
import com.tencent.feedback.proguard.l;
import com.tencent.feedback.proguard.o;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: RQDSRC */
public final class a implements Runnable {
    private Context a;
    private String b;
    private List<File> c;

    public a(Context context, String str, List<File> list) {
        this.a = context;
        this.b = str;
        this.c = list;
    }

    public final void run() {
        List<File> arrayList = new ArrayList();
        if (this.c != null && this.c.size() > 0) {
            arrayList.addAll(this.c);
        }
        File file = new File(this.b);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles(new 1(this));
            if (listFiles != null) {
                for (Object obj : listFiles) {
                    if (!arrayList.contains(obj)) {
                        arrayList.add(obj);
                    }
                }
            }
        }
        List a = l.a(this.a, null, 1, -1);
        List arrayList2 = new ArrayList();
        for (File file2 : arrayList) {
            int i;
            if (a != null) {
                Iterator it = a.iterator();
                while (it.hasNext()) {
                    o oVar = (o) it.next();
                    if (file2.getAbsolutePath().equals(oVar.a()) && file2.lastModified() == oVar.b() && file2.length() == oVar.c() && oVar.d() != null) {
                        it.remove();
                        arrayList2.add(oVar);
                        e.b("rqdp{  BufFB existed n:}%s ,ar:%s, md:%s ,ut:%d", oVar.a(), oVar.f(), oVar.d(), Long.valueOf(file2.lastModified()));
                        i = 1;
                        break;
                    }
                }
            }
            i = 0;
            if (i == 0) {
                long currentTimeMillis = System.currentTimeMillis();
                String b = com.tencent.feedback.proguard.a.b(file2.getAbsolutePath());
                currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
                if (b != null) {
                    o oVar2 = new o();
                    oVar2.a(1);
                    oVar2.a(file2.getAbsolutePath());
                    StringBuilder stringBuilder = new StringBuilder();
                    d.a(this.a);
                    oVar2.c(stringBuilder.append(d.d()).toString());
                    oVar2.c(file2.length());
                    oVar2.b(file2.lastModified());
                    oVar2.b(b);
                    e.b("rqdp{  BufFB new }n:%s , ar:%s , md:%s , cs:%d", oVar2.a(), oVar2.f(), oVar2.d(), Long.valueOf(currentTimeMillis));
                    arrayList2.add(oVar2);
                } else {
                    e.b("rqdp{  Error BufFB md fail! pth:}%s , rqdp{  cs:}%d", file2.getAbsolutePath(), Long.valueOf(currentTimeMillis));
                }
            }
        }
        e.b("rqdp{  LBFTask del n: }%d", Integer.valueOf(l.a(this.a, 1)));
        if (arrayList2.size() > 0) {
            e.b("rqdp{  LBFTask ins n: }%d", Integer.valueOf(l.c(this.a, arrayList2)));
        }
    }
}

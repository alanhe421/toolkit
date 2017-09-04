package com.tencent.feedback.eup.jni;

import android.content.Context;
import com.tencent.feedback.common.e;
import com.tencent.feedback.eup.b;
import com.tencent.feedback.proguard.s;
import com.tencent.feedback.proguard.t;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* compiled from: RQDSRC */
public final class d implements s {
    public final String a;
    private String b;
    private File c;
    private long d;
    private int e;
    private int f;
    private Context g;

    public d(Context context, String str, long j, int i, String str2, String str3) {
        this.c = new File(str);
        this.d = j;
        this.e = i;
        this.g = context;
        this.a = str2;
        this.b = str3;
    }

    private static void a(String str, String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            e.c("rqdp{  fileNameList == null || fileNameList.length <= 0}", new Object[0]);
            return;
        }
        for (String file : strArr) {
            File file2 = new File(str, file);
            if (file2.exists() && file2.canWrite()) {
                e.b("rqdp{  file delete} %s", file2.getPath());
                file2.delete();
            }
        }
    }

    public final void d() {
    }

    public final void e() {
        e.a("on query end clear", new Object[0]);
        this.f = 0;
        if (this.c != null && this.c.exists() && this.c.isDirectory()) {
            e.a("rqdp{ start to find native record}", new Object[0]);
            com.tencent.feedback.eup.e a = c.a(this.g, this.c.getAbsolutePath());
            if (a != null) {
                e.a("found a record insert %s", a.e());
                a.c(true);
                if (a.i() > this.d) {
                    e.a("avail add", new Object[0]);
                    b.a(this.g, a);
                } else {
                    e.a("unavail drop", new Object[0]);
                }
            }
            c.a(this.c.getAbsolutePath());
            e.a("rqdp{  start to clean} %s.* rqdp{  in dir} %s rqdp{  which time <} %s rqdp{  and max file nums should <} %s", this.a, this.c.getAbsolutePath(), Long.valueOf(this.d), Integer.valueOf(this.e));
            List linkedList = new LinkedList();
            String[] list = this.c.list(new 1(this, this.a.length(), this.b.length(), linkedList));
            int length = list != null ? list.length : 0;
            if (length > 0) {
                e.b("rqdp{  delete old num} %d", Integer.valueOf(length));
                a(this.c.getAbsolutePath(), list);
            }
            length = (this.f - length) - this.e;
            int size = linkedList.size();
            if (length > 0 && size > 0) {
                e.a("rqdp{  should delete not too old file num} %d", Integer.valueOf(length));
                Collections.sort(linkedList);
                if (size <= length) {
                    length = size;
                }
                list = new String[length];
                StringBuffer stringBuffer = new StringBuffer();
                length = 0;
                while (length < linkedList.size() && length < list.length) {
                    stringBuffer.append(this.a);
                    stringBuffer.append(linkedList.get(length));
                    stringBuffer.append(".txt");
                    list[length] = stringBuffer.toString();
                    stringBuffer.delete(0, stringBuffer.length());
                    length++;
                }
                e.b("rqdp{  delete not too old files} %d", Integer.valueOf(list.length));
                a(this.c.getAbsolutePath(), list);
            }
            t.a(this.g).b(this);
            e.a("rqdp{  clean end!}", new Object[0]);
            return;
        }
        String str = "rqdp{  TombFilesCleanTask end for dir not avaliable %s}";
        Object[] objArr = new Object[1];
        objArr[0] = this.c == null ? "null" : this.c.getAbsolutePath();
        e.c(str, objArr);
    }

    public final void f() {
        e.a("on app first run delete record file", new Object[0]);
        c.a(this.c.getAbsolutePath());
    }
}

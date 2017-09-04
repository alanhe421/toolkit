package com.tencent.feedback.eup.jni;

import com.tencent.feedback.common.e;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

/* compiled from: RQDSRC */
class d$1 implements FilenameFilter {
    private /* synthetic */ int a;
    private /* synthetic */ int b;
    private /* synthetic */ List c;
    private /* synthetic */ d d;

    d$1(d dVar, int i, int i2, List list) {
        this.d = dVar;
        this.a = i;
        this.b = i2;
        this.c = list;
    }

    public final boolean accept(File file, String str) {
        e.b("rqdp{  check dir} %s rqdp{  , filename} %s", new Object[]{file, str});
        if (str.startsWith(this.d.a)) {
            d.a(this.d);
            e.b("rqdp{  accept }%s", new Object[]{str});
            try {
                long parseLong = Long.parseLong(str.substring(this.a, str.length() - this.b));
                e.b("rqdp{  mRemoveBeforeDate }%d", new Object[]{Long.valueOf(d.b(this.d))});
                if (parseLong <= d.b(this.d)) {
                    e.b("rqdp{  recordTime} %d rqdp{  is old}", new Object[]{Long.valueOf(parseLong)});
                    return true;
                }
                e.b("rqdp{  newFileTimeList add} %d", new Object[]{Long.valueOf(parseLong)});
                this.c.add(Long.valueOf(parseLong));
            } catch (Throwable th) {
                e.c("rqdp{  filename is not formatted ,shoud do delete! \n path:}%s", new Object[]{str});
                if (e.a(th)) {
                    return true;
                }
                th.printStackTrace();
                return true;
            }
        }
        return false;
    }
}

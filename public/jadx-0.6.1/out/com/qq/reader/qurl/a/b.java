package com.qq.reader.qurl.a;

import android.app.Activity;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.o;
import com.qq.reader.qurl.d;

/* compiled from: URLServerOfAudioQuestion */
public class b extends d {
    private final String a = "detail";
    private final String b = "list";

    public b(Activity activity, String str, String str2) {
        super(activity, str, str2);
    }

    public void f() throws Exception {
        String d = d();
        if ("detail".equalsIgnoreCase(d)) {
            g();
        } else if ("list".equalsIgnoreCase(d)) {
            h();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void g() {
        /*
        r5 = this;
        r0 = r5.e();
        if (r0 == 0) goto L_0x0062;
    L_0x0006:
        r0 = r5.e();	 Catch:{ Exception -> 0x0063 }
        r1 = "qid";
        r0 = r0.get(r1);	 Catch:{ Exception -> 0x0063 }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0063 }
        r3 = 2;
        r2 = 20;
        r1 = r5.e();	 Catch:{ Exception -> 0x0073 }
        r4 = "index";
        r1 = r1.get(r4);	 Catch:{ Exception -> 0x0073 }
        r1 = (java.lang.String) r1;	 Catch:{ Exception -> 0x0073 }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ Exception -> 0x0073 }
        r1 = r1.intValue();	 Catch:{ Exception -> 0x0073 }
        r3 = r1;
    L_0x002c:
        r1 = r5.e();	 Catch:{ Exception -> 0x0071 }
        r4 = "next";
        r1 = r1.get(r4);	 Catch:{ Exception -> 0x0071 }
        r1 = (java.lang.String) r1;	 Catch:{ Exception -> 0x0071 }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ Exception -> 0x0071 }
        r1 = r1.intValue();	 Catch:{ Exception -> 0x0071 }
        r2 = r1;
    L_0x0042:
        r1 = r5.e();	 Catch:{ Exception -> 0x006f }
        r4 = "locate";
        r1 = r1.get(r4);	 Catch:{ Exception -> 0x006f }
        r1 = (java.lang.String) r1;	 Catch:{ Exception -> 0x006f }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ Exception -> 0x006f }
        r1 = r1.intValue();	 Catch:{ Exception -> 0x006f }
        r4 = 1;
        if (r1 != r4) goto L_0x005a;
    L_0x005a:
        r1 = r5.b();	 Catch:{ Exception -> 0x0063 }
        r4 = 0;
        com.qq.reader.common.utils.o.a(r1, r0, r4, r3, r2);	 Catch:{ Exception -> 0x0063 }
    L_0x0062:
        return;
    L_0x0063:
        r0 = move-exception;
        r1 = "error";
        r0 = r0.getMessage();
        com.qq.reader.common.monitor.debug.c.e(r1, r0);
        goto L_0x0062;
    L_0x006f:
        r1 = move-exception;
        goto L_0x005a;
    L_0x0071:
        r1 = move-exception;
        goto L_0x0042;
    L_0x0073:
        r1 = move-exception;
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.qurl.a.b.g():void");
    }

    public void h() {
        if (e() != null) {
            try {
                String str = (String) e().get("aname");
                o.a(b(), Long.valueOf((String) e().get("aid")).longValue(), str);
            } catch (Exception e) {
                c.e("error", e.getMessage());
            }
        }
    }
}

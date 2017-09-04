package com.qq.reader.qurl.a;

import android.app.Activity;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.qurl.d;
import java.util.HashMap;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

/* compiled from: URLServerOfCommont */
public class i extends d {
    private final String a = "detail";
    private final String b = "index";
    private final String c = "addcomment";
    private final String d = "indexforcommonzone";
    private final String e = "zonelist";
    private final String f = "chapter";
    private final String g = "chosenCommunityContent";

    public i(Activity activity, String str, String str2) {
        super(activity, str, str2);
    }

    public void f() throws Exception {
        String d = d();
        if ("detail".equalsIgnoreCase(d)) {
            l();
        } else if ("index".equalsIgnoreCase(d)) {
            k();
        } else if ("indexforcommonzone".equalsIgnoreCase(d)) {
            j();
        } else if ("addcomment".equalsIgnoreCase(d)) {
            i();
        } else if ("zonelist".equalsIgnoreCase(d)) {
            h();
        } else if ("chapter".equalsIgnoreCase(d)) {
            m();
        } else if ("chosenCommunityContent".equalsIgnoreCase(d)) {
            g();
        }
    }

    public void g() {
        String str;
        if (e() != null) {
            str = (String) e().get("pushTime");
        } else {
            str = null;
        }
        o.a(b(), null, a(), str);
    }

    public void h() {
        o.a(b(), null, a().b(SigType.WLOGIN_QRPUSH).a(c()));
    }

    public void i() {
        if (e() != null) {
            o.a(b(), Long.valueOf((String) e().get("bid")), a().a(1002).b(SigType.WLOGIN_QRPUSH).a(c()));
        }
    }

    public void j() {
        if (e() != null) {
            String str = (String) e().get("bid");
            String str2 = (String) e().get("ctype");
            str2 = null;
            if (str.equals("1")) {
                str2 = "书荒互助";
            } else if (str.equals("2")) {
                str2 = "原创空间";
            } else if (str.equals("3")) {
                str2 = "大神沙龙";
            }
            o.a(b(), Long.valueOf(str).longValue(), str2, a().b(SigType.WLOGIN_QRPUSH).a(c()));
        }
    }

    public void k() {
        if (e() != null) {
            o.a(b(), Long.valueOf((String) e().get("bid")), null, Integer.valueOf((String) e().get("ctype")).intValue(), a().a(c()));
        }
    }

    public void l() {
        boolean z = false;
        if (e() != null) {
            int intValue;
            int intValue2;
            int intValue3;
            boolean z2;
            String str = (String) e().get("commentid");
            String str2 = (String) e().get("ctype");
            String str3 = (String) e().get("bid");
            String str4 = (String) e().get("authorid");
            String str5 = (String) e().get("itemid");
            String str6 = (String) e().get(s.ALG);
            try {
                intValue = Integer.valueOf((String) e().get("from")).intValue();
            } catch (Exception e) {
                c.e("URLServerOfCommont", e.getMessage());
                intValue = 0;
            }
            try {
                intValue2 = Integer.valueOf((String) e().get("index")).intValue();
            } catch (Exception e2) {
                c.e("URLServerOfCommont", e2.getMessage());
                intValue2 = 2;
            }
            try {
                intValue3 = Integer.valueOf((String) e().get("next")).intValue();
            } catch (Exception e3) {
                c.e("URLServerOfCommont", e3.getMessage());
                intValue3 = 20;
            }
            try {
                if (Integer.valueOf((String) e().get("lcoate")).intValue() == 1) {
                    z = true;
                }
                z2 = z;
            } catch (Exception e4) {
                c.e("URLServerOfCommont", e4.getMessage());
                z2 = false;
            }
            Object hashMap = new HashMap();
            hashMap.put("itemid", str5);
            hashMap.put(s.ALG, str6);
            a().a(hashMap);
            o.a(b(), Long.valueOf(str3), str, Integer.valueOf(str2).intValue(), str4, intValue2, intValue3, z2, intValue, a());
        }
    }

    public void m() {
        if (e() != null) {
            String str = (String) e().get("chapterid");
            String str2 = (String) e().get("bid");
            int i = 2;
            try {
                i = Integer.valueOf((String) e().get("index")).intValue();
            } catch (Exception e) {
                c.e("URLServerOfCommont", e.getMessage());
            }
            int i2 = 20;
            try {
                i2 = Integer.valueOf((String) e().get("next")).intValue();
            } catch (Exception e2) {
                c.e("URLServerOfCommont", e2.getMessage());
            }
            o.a(b(), str2, str, i, i2, a());
        }
    }
}

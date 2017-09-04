package format.epub.common.core.xhtml;

import android.text.TextUtils;
import com.dynamicload.Lib.DLConstants;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.connect.common.Constants;
import com.tencent.mm.performance.WxPerformanceHandle;
import format.epub.common.b.b;
import format.epub.common.core.a.c;
import format.epub.common.image.a;
import format.epub.common.text.model.n;
import format.epub.common.utils.d;
import java.util.ArrayList;
import java.util.List;

/* compiled from: XHTMLTagImageAction */
class h extends t {
    private final String a;
    private final String b;
    private boolean c;
    private String d;

    h(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    protected void a(c cVar, c cVar2) {
        this.c = false;
        this.d = null;
        String a = cVar2.a(WxPerformanceHandle.MESSAGE_CLASS);
        List arrayList = new ArrayList();
        if (a != null) {
            String[] split = a.split(" ");
            for (Object add : split) {
                arrayList.add(add);
            }
        }
        if (!cVar.a("", arrayList)) {
            if (!cVar.g && !cVar.b().h()) {
                cVar.a(false);
                cVar.g = true;
                this.c = true;
            } else if (cVar.g) {
                a = cVar.u;
                if (a != null && !a(a)) {
                    this.c = true;
                }
            }
        }
    }

    private boolean a(String str) {
        if ("p".equals(str) || "h1".equals(str) || "h2".equals(str) || "h3".equals(str) || "h4".equals(str) || "h5".equals(str) || "h6".equals(str) || "span".equals(str)) {
            return true;
        }
        return false;
    }

    protected void b(c cVar, c cVar2) {
        String a = cVar.a(cVar2, this.a, this.b);
        if (a != null) {
            b b = b.b(cVar.b + d.b(a));
            if (b != null) {
                format.epub.common.a.b b2 = cVar.b();
                String d = b.d();
                String a2 = cVar2.a("alt");
                a = cVar2.a(WxPerformanceHandle.MESSAGE_CLASS);
                String a3 = cVar2.a("active");
                String a4 = cVar2.a("align");
                String a5 = cVar2.a("style");
                List<String> arrayList = new ArrayList();
                if (a != null) {
                    String[] split = a.split(" ");
                    for (Object add : split) {
                        arrayList.add(add);
                    }
                }
                String str = d;
                for (String str2 : arrayList) {
                    if (a2 == null || str2 == null || !str2.toLowerCase().equals("qqreader-footnote")) {
                        a = str;
                    } else {
                        a = d + DLConstants.DEPENDENCY_PACKAGE_DIV + "0" + a2;
                    }
                    str = a;
                }
                if (a3 == null || !a3.equals("true")) {
                    a = str;
                } else {
                    a = str + DLConstants.DEPENDENCY_PACKAGE_DIV + "1";
                }
                if (a4 != null) {
                    if (a4.equals("right")) {
                        a = a + DLConstants.DEPENDENCY_PACKAGE_DIV + "2" + 2;
                    } else if (a4.equals("center")) {
                        a = a + DLConstants.DEPENDENCY_PACKAGE_DIV + "2" + 3;
                    } else if (a4.equals("left")) {
                        a = a + DLConstants.DEPENDENCY_PACKAGE_DIV + "2" + 1;
                    }
                    a2 = a;
                } else {
                    n d2 = cVar.d("", arrayList);
                    if (d2 == null || !d2.a(12)) {
                        a2 = a;
                    } else {
                        a2 = a + DLConstants.DEPENDENCY_PACKAGE_DIV + "2" + d2.g();
                    }
                }
                CharSequence charSequence = null;
                CharSequence charSequence2 = null;
                if (a5 != null) {
                    for (String str3 : a5.split(VoiceWakeuperAidl.PARAMS_SEPARATE)) {
                        if (str3.indexOf("width:") != -1) {
                            charSequence = str3.substring(6);
                            a2 = a2 + DLConstants.DEPENDENCY_PACKAGE_DIV + "3" + charSequence;
                        } else if (str3.indexOf("height:") != -1) {
                            charSequence2 = str3.substring(7);
                            a2 = a2 + DLConstants.DEPENDENCY_PACKAGE_DIV + Constants.VIA_SHARE_TYPE_INFO + charSequence2;
                        }
                    }
                }
                if (cVar.a("", (List) arrayList)) {
                    Object obj = (!b2.g() || b2.h()) ? null : 1;
                    if (obj != null) {
                        cVar.c();
                    }
                    b2.b(a2, (short) 0);
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(a2).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append("4").append(cVar.b("", arrayList)).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append("5").append(cVar.c("", arrayList));
                    if (TextUtils.isEmpty(charSequence)) {
                        Object e = cVar.e("", arrayList);
                        if (!TextUtils.isEmpty(e)) {
                            stringBuilder.append(DLConstants.DEPENDENCY_PACKAGE_DIV).append("3").append(e);
                        }
                    }
                    if (TextUtils.isEmpty(charSequence2)) {
                        n d3 = cVar.d("", arrayList);
                        if (!(d3 == null || TextUtils.isEmpty(d3.d()))) {
                            stringBuilder.append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(Constants.VIA_SHARE_TYPE_INFO).append(d3.d());
                        }
                    }
                    this.d = stringBuilder.toString();
                    b2.a(this.d, (short) 0);
                }
                b2.a(d, new a("image/auto", b));
            }
        }
    }

    protected void a(c cVar) {
        if (this.c) {
            cVar.c();
            this.c = false;
        }
    }
}

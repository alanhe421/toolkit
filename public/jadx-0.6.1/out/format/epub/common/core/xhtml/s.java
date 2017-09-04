package format.epub.common.core.xhtml;

import android.text.TextUtils;
import com.dynamicload.Lib.DLConstants;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.connect.common.Constants;
import com.tencent.mm.performance.WxPerformanceHandle;
import format.epub.common.b.b;
import format.epub.common.core.a.c;
import format.epub.common.image.a;
import format.epub.common.text.model.a.d;
import format.epub.common.text.model.n;
import java.util.ArrayList;
import java.util.List;

/* compiled from: XHTMLTagVideoAction */
public class s extends d {
    private final String a;
    private final String b;
    private boolean c;
    private String d;

    s(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    protected void a(c cVar, c cVar2) {
        if (cVar.t == 2) {
            cVar.t = 3;
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
                    this.c = true;
                }
            }
            cVar.r = new d();
        }
    }

    protected void b(c cVar, c cVar2) {
        String a = cVar.a(cVar2, this.a, this.b);
        if (a != null) {
            b b = b.b(cVar.b + format.epub.common.utils.d.b(a));
            if (b != null) {
                n d;
                CharSequence charSequence;
                CharSequence charSequence2;
                int i;
                StringBuilder stringBuilder;
                Object e;
                n d2;
                Object obj;
                format.epub.common.a.b b2 = cVar.b();
                String d3 = b.d();
                a = cVar2.a(WxPerformanceHandle.MESSAGE_CLASS);
                String a2 = cVar2.a("active");
                String a3 = cVar2.a("align");
                String a4 = cVar2.a("style");
                List arrayList = new ArrayList();
                if (a != null) {
                    String[] split = a.split(" ");
                    for (Object add : split) {
                        arrayList.add(add);
                    }
                }
                a = cVar2.a("src");
                if (a != null) {
                    if (!a.startsWith("http://")) {
                        a = b.b(cVar.b + format.epub.common.utils.d.b(a)).c();
                    }
                    if (a != null) {
                        a = d3 + DLConstants.DEPENDENCY_PACKAGE_DIV + "7" + a;
                        if (a2 != null && a2.equals("true")) {
                            a = a + DLConstants.DEPENDENCY_PACKAGE_DIV + "1";
                        }
                        if (a3 == null) {
                            if (a3.equals("right")) {
                                a = a + DLConstants.DEPENDENCY_PACKAGE_DIV + "2" + 2;
                            } else if (a3.equals("center")) {
                                a = a + DLConstants.DEPENDENCY_PACKAGE_DIV + "2" + 3;
                            } else if (a3.equals("left")) {
                                a = a + DLConstants.DEPENDENCY_PACKAGE_DIV + "2" + 1;
                            }
                            a3 = a;
                        } else {
                            d = cVar.d("", arrayList);
                            if (d == null && d.a(12)) {
                                a3 = a + DLConstants.DEPENDENCY_PACKAGE_DIV + "2" + d.g();
                            } else {
                                a3 = a;
                            }
                        }
                        charSequence = null;
                        charSequence2 = null;
                        if (a4 != null) {
                            for (String str : a4.split(VoiceWakeuperAidl.PARAMS_SEPARATE)) {
                                if (str.indexOf("width:") != -1) {
                                    charSequence = str.substring(6);
                                    a3 = a3 + DLConstants.DEPENDENCY_PACKAGE_DIV + "3" + charSequence;
                                } else if (str.indexOf("height:") != -1) {
                                    charSequence2 = str.substring(7);
                                    a3 = a3 + DLConstants.DEPENDENCY_PACKAGE_DIV + Constants.VIA_SHARE_TYPE_INFO + charSequence2;
                                }
                            }
                        }
                        if (cVar.a("", arrayList)) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(a3).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append("4").append(cVar.b("", arrayList)).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append("5").append(cVar.c("", arrayList));
                            if (TextUtils.isEmpty(charSequence)) {
                                e = cVar.e("", arrayList);
                                if (!TextUtils.isEmpty(e)) {
                                    stringBuilder.append(DLConstants.DEPENDENCY_PACKAGE_DIV).append("3").append(e);
                                }
                            }
                            if (TextUtils.isEmpty(charSequence2)) {
                                d2 = cVar.d("", arrayList);
                                if (!(d2 == null || TextUtils.isEmpty(d2.d()))) {
                                    stringBuilder.append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(Constants.VIA_SHARE_TYPE_INFO).append(d2.d());
                                }
                            }
                            this.d = stringBuilder.toString();
                            b2.a(this.d, (short) 0);
                        } else {
                            obj = (b2.g() || b2.h()) ? null : 1;
                            if (obj != null) {
                                cVar.c();
                            }
                            b2.b(a3, (short) 0);
                        }
                        b2.a(d3, new a("image/auto", b));
                    }
                }
                a = d3;
                a = a + DLConstants.DEPENDENCY_PACKAGE_DIV + "1";
                if (a3 == null) {
                    d = cVar.d("", arrayList);
                    if (d == null) {
                    }
                    a3 = a;
                } else {
                    if (a3.equals("right")) {
                        a = a + DLConstants.DEPENDENCY_PACKAGE_DIV + "2" + 2;
                    } else if (a3.equals("center")) {
                        a = a + DLConstants.DEPENDENCY_PACKAGE_DIV + "2" + 3;
                    } else if (a3.equals("left")) {
                        a = a + DLConstants.DEPENDENCY_PACKAGE_DIV + "2" + 1;
                    }
                    a3 = a;
                }
                charSequence = null;
                charSequence2 = null;
                if (a4 != null) {
                    for (i = 0; i < r9; i++) {
                        if (str.indexOf("width:") != -1) {
                            charSequence = str.substring(6);
                            a3 = a3 + DLConstants.DEPENDENCY_PACKAGE_DIV + "3" + charSequence;
                        } else if (str.indexOf("height:") != -1) {
                            charSequence2 = str.substring(7);
                            a3 = a3 + DLConstants.DEPENDENCY_PACKAGE_DIV + Constants.VIA_SHARE_TYPE_INFO + charSequence2;
                        }
                    }
                }
                if (cVar.a("", arrayList)) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(a3).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append("4").append(cVar.b("", arrayList)).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append("5").append(cVar.c("", arrayList));
                    if (TextUtils.isEmpty(charSequence)) {
                        e = cVar.e("", arrayList);
                        if (TextUtils.isEmpty(e)) {
                            stringBuilder.append(DLConstants.DEPENDENCY_PACKAGE_DIV).append("3").append(e);
                        }
                    }
                    if (TextUtils.isEmpty(charSequence2)) {
                        d2 = cVar.d("", arrayList);
                        stringBuilder.append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(Constants.VIA_SHARE_TYPE_INFO).append(d2.d());
                    }
                    this.d = stringBuilder.toString();
                    b2.a(this.d, (short) 0);
                } else {
                    if (b2.g()) {
                    }
                    if (obj != null) {
                        cVar.c();
                    }
                    b2.b(a3, (short) 0);
                }
                b2.a(d3, new a("image/auto", b));
            }
        }
    }

    protected void a(c cVar) {
        if (cVar.t == 3) {
            cVar.b().a(cVar.r);
            cVar.t = 2;
            if (this.c) {
                cVar.c();
                this.c = false;
            }
        }
    }

    protected boolean a(int i) {
        return i == 2 || i == 3;
    }
}

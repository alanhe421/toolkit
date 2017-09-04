package format.epub.common.core.xhtml;

import com.qq.reader.common.download.task.f;
import com.tencent.imsdk.QLogImpl;
import com.tencent.qalsdk.im_open.http;
import format.epub.common.core.a.c;

/* compiled from: XHTMLTagItemAction */
class j extends t {
    public static final String[] a = new String[]{"M", "CM", QLogImpl.TAG_REPORTLEVEL_DEVELOPER, "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public static final int[] b = new int[]{1000, f.SUCCESS, http.Internal_Server_Error, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private final char[] c = new char[]{'•'};
    private final char[] d = new char[]{'　'};
    private final char[] e = new char[]{'￼'};
    private final char[] f = new char[]{'▪'};
    private final char[] g = new char[]{'◦'};

    j() {
    }

    protected void a(c cVar, c cVar2) {
        boolean z = cVar.l.size() >= 2 ? ((a) cVar.l.get(cVar.l.size() + -2)).a().size() > 1 : true;
        if (z) {
            cVar.c();
            cVar.a(false);
        }
        if (!cVar.o.empty()) {
            cVar.b().a((short) (cVar.o.size() * 3));
            cVar.h();
            int intValue = ((Integer) cVar.o.peek()).intValue();
            if (intValue == 0) {
                int i = cVar.i();
                char[] cArr = null;
                switch (cVar.k()) {
                    case 1:
                        cArr = this.d;
                        break;
                    case 2:
                        cArr = this.f;
                        break;
                    case 4:
                        cArr = this.c;
                        break;
                    case 8:
                        cArr = this.g;
                        break;
                    case 16:
                        cArr = (String.valueOf(i) + "." + " ").toCharArray();
                        break;
                    case 32:
                        cArr = ("0" + String.valueOf(i) + "." + " ").toCharArray();
                        break;
                    case 64:
                        cArr = (b(i) + "." + " ").toCharArray();
                        break;
                    default:
                        String l = cVar.l();
                        if (l == null) {
                            cArr = this.c;
                            break;
                        } else {
                            cVar.b().a(l, (short) 0);
                            break;
                        }
                }
                if (cArr != null) {
                    cVar.b().a(cArr);
                }
            } else {
                int i2 = intValue + 1;
                cVar.b().a((String.valueOf(intValue) + ".").toCharArray());
            }
            cVar.b().a((short) 1);
        }
        cVar.g = true;
    }

    protected void a(c cVar) {
    }

    private String b(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        int i2 = i;
        for (int i3 = 0; i3 < 13; i3++) {
            while (i2 >= b[i3]) {
                i2 -= b[i3];
                stringBuilder.append(a[i3]);
            }
        }
        return stringBuilder.toString().toLowerCase();
    }
}

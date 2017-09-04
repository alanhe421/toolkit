package format.epub.common.core.xhtml;

import com.tencent.smtt.sdk.WebView;
import format.epub.common.a.b;
import format.epub.common.core.a.c;
import format.epub.common.utils.j;

/* compiled from: XHTMLTagHyperlinkAction */
class g extends t {
    private byte[] a = new byte[10];
    private int b;

    g() {
    }

    private static boolean a(String str) {
        switch (str.charAt(0)) {
            case 'f':
                if (str.startsWith("fbreader-action:") || str.startsWith("ftp://")) {
                    return true;
                }
                return false;
            case 'h':
                if (str.startsWith("http://") || str.startsWith("https://")) {
                    return true;
                }
                return false;
            case 'm':
                return str.startsWith(WebView.SCHEME_MAILTO);
            case 'q':
                return str.startsWith("qqreader:");
            default:
                return false;
        }
    }

    protected void a(c cVar, c cVar2) {
        b b = cVar.b();
        String a = cVar2.a("href");
        if (this.b == this.a.length) {
            this.a = j.a(this.a, this.b, this.b * 2);
        }
        if (a == null || a.length() <= 0) {
            byte[] bArr = this.a;
            int i = this.b;
            this.b = i + 1;
            bArr[i] = (byte) 0;
        } else {
            byte b2;
            if (a(a)) {
                b2 = (byte) 37;
            } else {
                b2 = (byte) 15;
                int indexOf = a.indexOf(35);
                if (indexOf == 0) {
                    a = new StringBuilder(cVar.d).append(a, 1, a.length()).toString();
                } else if (indexOf > 0) {
                    a = new StringBuilder(cVar.b(a.substring(0, indexOf))).append(a, indexOf, a.length()).toString();
                } else {
                    a = cVar.b(a);
                }
            }
            byte[] bArr2 = this.a;
            int i2 = this.b;
            this.b = i2 + 1;
            bArr2[i2] = b2;
            b.a(b2, a);
        }
        String a2 = cVar2.a("name");
        if (a2 != null) {
            b.a(cVar.d + a2);
        }
    }

    protected void a(c cVar) {
        byte[] bArr = this.a;
        int i = this.b - 1;
        this.b = i;
        byte b = bArr[i];
        if (b != (byte) 0) {
            cVar.b().a(b, false);
        }
    }
}

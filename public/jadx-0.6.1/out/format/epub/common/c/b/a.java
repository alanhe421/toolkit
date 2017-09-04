package format.epub.common.c.b;

import format.epub.common.core.a.c;
import format.epub.common.core.a.g;

/* compiled from: ContainerFileReader */
class a extends g {
    private String a;

    a() {
    }

    public String a() {
        return this.a;
    }

    public boolean a(String str, c cVar) {
        if ("rootfile".equalsIgnoreCase(str)) {
            this.a = cVar.a("full-path");
            if (this.a != null) {
                return true;
            }
        }
        return false;
    }
}

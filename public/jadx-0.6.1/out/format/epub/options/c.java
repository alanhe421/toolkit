package format.epub.options;

import format.epub.common.utils.k;

/* compiled from: ZLColorOption */
public final class c extends f {
    private final k a;
    private k b;

    public c(String str, String str2, k kVar) {
        super(str, str2);
        if (kVar == null) {
            kVar = new k(0);
        }
        this.a = kVar;
        this.b = this.a;
    }

    public k a() {
        if (!this.c) {
            String a = a(null);
            if (a != null) {
                try {
                    int parseInt = Integer.parseInt(a);
                    if (this.b.a() != parseInt) {
                        this.b = new k(parseInt);
                    }
                } catch (NumberFormatException e) {
                }
            }
            this.c = true;
        }
        return this.b;
    }

    public void a(k kVar) {
        if (kVar != null) {
            boolean equals = this.b.equals(kVar);
            if (!this.c || !equals) {
                if (!equals) {
                    this.b = kVar;
                }
                this.c = true;
                if (kVar.equals(this.a)) {
                    b();
                } else {
                    b("" + kVar.a());
                }
            }
        }
    }
}

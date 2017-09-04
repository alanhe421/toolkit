package format.epub.options;

/* compiled from: ZLBooleanOption */
public final class b extends f {
    private final boolean a;
    private boolean b;

    public b(String str, String str2, boolean z) {
        super(str, str2);
        this.a = z;
        this.b = z;
    }

    public boolean a() {
        if (!this.c) {
            String a = a(null);
            if (a != null) {
                if ("true".equals(a)) {
                    this.b = true;
                } else if ("false".equals(a)) {
                    this.b = false;
                }
            }
            this.c = true;
        }
        return this.b;
    }
}

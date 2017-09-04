package format.epub.options;

/* compiled from: ZLStringOption */
public final class g extends f {
    private final String a;
    private String b;

    public g(String str, String str2, String str3) {
        super(str, str2);
        this.a = str3 != null ? str3.intern() : "";
        this.b = this.a;
    }

    public String a() {
        if (!this.c) {
            String a = a(this.a);
            if (a != null) {
                this.b = a;
            }
            this.c = true;
        }
        return this.b;
    }

    public String c() {
        return this.a;
    }

    public void c(String str) {
        if (str != null) {
            String intern = str.intern();
            if (!this.c || this.b != intern) {
                this.b = intern;
                if (intern == this.a) {
                    b();
                } else {
                    b(intern);
                }
                this.c = true;
            }
        }
    }
}

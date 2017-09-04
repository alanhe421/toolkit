package format.epub.options;

/* compiled from: ZLIntegerRangeOption */
public final class e extends f {
    public final int a;
    public final int b;
    private final int d;
    private int e;

    public e(String str, String str2, int i, int i2, int i3) {
        super(str, str2);
        this.a = i;
        this.b = i2;
        if (i3 < this.a) {
            i3 = this.a;
        } else if (i3 > this.b) {
            i3 = this.b;
        }
        this.d = i3;
        this.e = i3;
    }

    public int a() {
        if (!this.c) {
            String a = a(null);
            if (a != null) {
                try {
                    int parseInt = Integer.parseInt(a);
                    if (parseInt < this.a) {
                        parseInt = this.a;
                    } else if (parseInt > this.b) {
                        parseInt = this.b;
                    }
                    this.e = parseInt;
                } catch (NumberFormatException e) {
                }
            }
            this.c = true;
        }
        return this.e;
    }

    public void a(int i) {
        if (i < this.a) {
            i = this.a;
        } else if (i > this.b) {
            i = this.b;
        }
        if (!this.c || this.e != i) {
            this.e = i;
            this.c = true;
            if (i == this.d) {
                b();
            } else {
                b("" + i);
            }
        }
    }
}

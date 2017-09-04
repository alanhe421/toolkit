package com.qrcomic.activity.reader;

/* compiled from: QRRequestConfig */
public class c {
    private final boolean a;
    private final boolean b;
    private final boolean c;
    private final boolean d;

    /* compiled from: QRRequestConfig */
    public static class a {
        private boolean a;
        private boolean b;
        private boolean c;
        private boolean d;

        private a() {
        }

        public a a() {
            this.a = true;
            return this;
        }

        public a b() {
            this.b = true;
            return this;
        }

        public a c() {
            this.c = true;
            return this;
        }

        public a d() {
            this.d = true;
            return this;
        }

        public c e() {
            return new c();
        }
    }

    public String toString() {
        return "QRRequestConfig{needUpdateToolBar=" + this.a + ", needUpdateScrollPager=" + this.b + ", needUpdateCurrentSection=" + this.c + ", needAutoShowBuyView=" + this.d + '}';
    }

    private c(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
    }

    public boolean a() {
        return this.a;
    }

    public boolean b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }

    public boolean d() {
        return this.d;
    }

    public static a e() {
        return new a();
    }
}

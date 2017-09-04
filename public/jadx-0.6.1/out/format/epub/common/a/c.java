package format.epub.common.a;

import format.epub.common.text.model.i;
import format.epub.common.utils.p;

/* compiled from: TOCTree */
public class c extends p<c> {
    int a = -1;
    private String f;
    private a g;

    /* compiled from: TOCTree */
    public static class a {
        public final int a;
        public final i b;

        public a(int i, i iVar) {
            this.a = i;
            this.b = iVar;
        }
    }

    protected c() {
    }

    public c(c cVar) {
        super(cVar);
    }

    public final String a() {
        return this.f;
    }

    public final void a(String str) {
        this.f = str;
    }

    public a b() {
        return this.g;
    }

    public void a(i iVar, int i) {
        this.g = new a(i, iVar);
    }

    public int c() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }
}

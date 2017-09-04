package format.epub.view;

import format.epub.common.text.model.i;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* compiled from: ZLTextParagraphCursorCache */
public class t {
    private static final HashMap<a, WeakReference<s>> a = new HashMap();

    /* compiled from: ZLTextParagraphCursorCache */
    private static final class a {
        private final i a;
        private final int b;

        public a(i iVar, int i) {
            this.a = iVar;
            this.b = i;
        }

        public boolean equals(Object obj) {
            a aVar = (a) obj;
            return this.a == aVar.a && this.b == aVar.b;
        }

        public int hashCode() {
            return this.a.hashCode() + this.b;
        }
    }

    public static void a(i iVar, int i, s sVar) {
        a.put(new a(iVar, i), new WeakReference(sVar));
    }

    public static s a(i iVar, int i) {
        WeakReference weakReference = (WeakReference) a.get(new a(iVar, i));
        return weakReference != null ? (s) weakReference.get() : null;
    }

    public static void a() {
        a.clear();
    }
}

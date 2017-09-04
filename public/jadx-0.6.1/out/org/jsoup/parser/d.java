package org.jsoup.parser;

import java.util.Iterator;
import org.jsoup.a.a;
import org.jsoup.nodes.b;

/* compiled from: ParseSettings */
public class d {
    public static final d a = new d(false, false);
    public static final d b = new d(true, true);
    private final boolean c;
    private final boolean d;

    public d(boolean z, boolean z2) {
        this.c = z;
        this.d = z2;
    }

    String a(String str) {
        String trim = str.trim();
        if (this.c) {
            return trim;
        }
        return a.a(trim);
    }

    b a(b bVar) {
        if (!this.d) {
            Iterator it = bVar.iterator();
            while (it.hasNext()) {
                org.jsoup.nodes.a aVar = (org.jsoup.nodes.a) it.next();
                aVar.a(a.a(aVar.a()));
            }
        }
        return bVar;
    }
}

package org.jsoup.nodes;

import org.jsoup.parser.f;
import org.jsoup.select.Elements;

/* compiled from: FormElement */
public class h extends g {
    private final Elements f = new Elements();

    public h(f fVar, String str, b bVar) {
        super(fVar, str, bVar);
    }

    public h b(g gVar) {
        this.f.add(gVar);
        return this;
    }
}

package format.epub.common.core.xhtml;

import format.epub.common.c.a.b;
import java.util.ArrayList;

public class XHTMLTagInfoList extends ArrayList<i> {
    public int find(b bVar, int i, int i2) {
        if (i < 0) {
            i = Math.max(size() + i, 0);
        }
        if (i2 <= 0) {
            i2 += size();
        }
        for (int min = Math.min(i2, size()) - 1; min >= i; min--) {
            if (((i) get(min)).a(bVar)) {
                return min;
            }
        }
        return -1;
    }

    public boolean matches(b bVar, int i) {
        return find(bVar, i, i + 1) != -1;
    }
}

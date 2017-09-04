package format.epub.view;

import java.util.List;

/* compiled from: ZLTextImageRegion */
public class q extends i {
    public final p d;

    q(p pVar, List<h> list, int i) {
        super(list, i);
        this.d = pVar;
    }

    public boolean equals(Object obj) {
        if ((obj instanceof q) && this.d == ((q) obj).d) {
            return true;
        }
        return false;
    }
}

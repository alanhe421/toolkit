package format.epub.view;

import java.util.List;

/* compiled from: ZLTextHyperlinkRegion */
public class o extends i {
    public final m d;

    o(m mVar, List<h> list, int i) {
        super(list, i);
        this.d = mVar;
    }

    public boolean equals(Object obj) {
        if ((obj instanceof o) && this.d == ((o) obj).d) {
            return true;
        }
        return false;
    }
}

package format.epub.common.core.xhtml;

import android.text.TextUtils;
import com.tencent.qalsdk.sdk.v;
import format.epub.common.c.a.b;
import java.util.ArrayList;
import java.util.List;

/* compiled from: XHTMLTagInfo */
public class i {
    private String a;
    private List<String> b = new ArrayList();

    public i(String str, List<String> list) {
        this.a = str;
        this.b.addAll(list);
    }

    public boolean a(b bVar) {
        Object a = bVar.a();
        CharSequence b = bVar.b();
        if (v.n.equals(a)) {
            return TextUtils.isEmpty(b);
        }
        if (!TextUtils.isEmpty(a) && !a.equals(this.a)) {
            return false;
        }
        if (TextUtils.isEmpty(b)) {
            return true;
        }
        return this.b.contains(b);
    }
}

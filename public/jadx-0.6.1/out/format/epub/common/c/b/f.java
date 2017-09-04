package format.epub.common.c.b;

import com.tencent.android.tpush.common.MessageKey;
import format.epub.common.b.b;
import format.epub.common.book.BookEPub;
import format.epub.common.core.a.c;
import format.epub.common.core.a.e;
import format.epub.common.core.a.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: OEBMetaInfoReader */
class f extends g {
    private final BookEPub a;
    private String b = "dc-metadata";
    private String c = "metadata";
    private String d = "metadata";
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j = "meta";
    private String k = "";
    private float l = 0.0f;
    private final ArrayList<String> m = new ArrayList();
    private final ArrayList<String> n = new ArrayList();
    private int o;
    private boolean p;
    private final StringBuilder q = new StringBuilder();

    f(BookEPub bookEPub) {
        this.a = bookEPub;
        this.a.setTitle(null);
        this.a.setLanguage(null);
    }

    boolean b(b bVar) {
        this.p = false;
        this.o = 0;
        if (!e.a((format.epub.common.core.a.f) this, bVar, 512)) {
            return false;
        }
        Iterator it = (this.m.isEmpty() ? this.n : this.m).iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            int indexOf = str.indexOf(44);
            if (indexOf >= 0) {
                str = str.substring(indexOf + 1).trim() + ' ' + str.substring(0, indexOf).trim();
            } else {
                str = str.trim();
            }
            this.a.addAuthor(str);
        }
        return true;
    }

    public boolean g() {
        return true;
    }

    public void a(Map<String, String> map) {
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.d = "metadata";
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getValue();
            if (str.startsWith("http://purl.org/dc/elements") || str.startsWith("http://purl.org/metadata/dublin_core")) {
                String str2 = (String) entry.getKey();
                this.f = (str2 + ":title").intern();
                this.g = (str2 + ":creator").intern();
                this.h = (str2 + ":subject").intern();
                this.i = (str2 + ":language").intern();
            } else if (str.equals("http://www.idpf.org/2007/opf")) {
                this.d = (((String) entry.getKey()) + ":metadata").intern();
            }
        }
    }

    public boolean a(String str, c cVar) {
        String intern = str.toLowerCase().intern();
        if (intern == this.c || intern == this.b || intern == this.d) {
            this.e = intern;
            this.p = true;
        } else if (this.p) {
            if (intern == this.f) {
                this.o = 3;
            } else if (intern == this.g) {
                intern = cVar.a("role");
                if (intern == null) {
                    this.o = 2;
                } else if (intern.equals("aut")) {
                    this.o = 1;
                }
            } else if (intern == this.h) {
                this.o = 4;
            } else if (intern == this.i) {
                this.o = 5;
            } else if (intern == this.j) {
                if ("calibre:series".equals(cVar.a("name"))) {
                    this.k = cVar.a(MessageKey.MSG_CONTENT);
                } else if ("calibre:series_index".equals(cVar.a("name"))) {
                    try {
                        this.l = Float.parseFloat(cVar.a(MessageKey.MSG_CONTENT));
                    } catch (NumberFormatException e) {
                    }
                }
            }
        }
        return false;
    }

    public void a(char[] cArr, int i, int i2) {
        switch (this.o) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                this.q.append(cArr, i, i2);
                return;
            default:
                return;
        }
    }

    public boolean c(String str) {
        String toLowerCase = str.toLowerCase();
        if (toLowerCase.equals(this.e)) {
            return true;
        }
        String trim = this.q.toString().trim();
        if (trim.length() != 0) {
            switch (this.o) {
                case 1:
                    this.m.add(trim);
                    break;
                case 2:
                    this.n.add(trim);
                    break;
                case 3:
                    this.a.setTitle(trim);
                    break;
                case 4:
                    this.a.addTag(trim);
                    break;
                case 5:
                    int indexOf = trim.indexOf(95);
                    if (indexOf >= 0) {
                        trim = trim.substring(0, indexOf);
                    }
                    indexOf = trim.indexOf(45);
                    if (indexOf >= 0) {
                        trim = trim.substring(0, indexOf);
                    }
                    BookEPub bookEPub = this.a;
                    if ("cz".equals(trim)) {
                        trim = "cs";
                    }
                    bookEPub.setLanguage(trim);
                    break;
            }
        } else if (toLowerCase.equals(this.j) && !"".equals(this.k) && this.l > 0.0f) {
        }
        this.q.delete(0, this.q.length());
        this.o = 0;
        return false;
    }
}

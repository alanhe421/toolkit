package format.epub.common.c.b;

import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import format.epub.common.a.a;
import format.epub.common.a.b;
import format.epub.common.book.BookEPub;
import format.epub.common.core.a.g;
import format.epub.common.utils.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/* compiled from: OEBBookReader */
class c extends g {
    private static final char[] a = new char[]{'.', '.', '.'};
    private final b b;
    private final HashMap<String, String> c = new HashMap();
    private final ArrayList<String> d = new ArrayList();
    private final ArrayList<h> e = new ArrayList();
    private final ArrayList<h> f = new ArrayList();
    private String g;
    private String h;
    private String i;
    private TreeMap<String, Integer> j = new TreeMap();
    private TreeMap<String, Integer> k = new TreeMap();
    private int l;

    c(a aVar) {
        this.b = new b(aVar);
    }

    boolean b(format.epub.common.b.b bVar) {
        this.h = d.a(bVar);
        this.c.clear();
        this.d.clear();
        this.i = null;
        this.e.clear();
        this.f.clear();
        this.l = 0;
        if (!a(bVar)) {
            return false;
        }
        this.b.e();
        this.b.a((byte) 0);
        format.epub.b d = this.b.a.d();
        format.epub.common.core.xhtml.c cVar = new format.epub.common.core.xhtml.c(this.b, this.j);
        Iterator it = this.d.iterator();
        int i = 1;
        while (it.hasNext()) {
            format.epub.common.b.b b = format.epub.common.b.b.b(this.h + ((String) it.next()));
            String a = cVar.a(d.a(b.c()));
            this.b.a(a);
            this.k.put(a, Integer.valueOf(this.b.a.b.b()));
            cVar.a(b, a + '#');
            this.b.c();
            if (d != null) {
                d.d((int) ((((double) i) / ((double) this.d.size())) * 100.0d));
            }
            i++;
        }
        if (!i.c().a(this.b.a.a.getBookPath(), false)) {
            a();
            format.epub.common.a.c cVar2 = this.b.a.c;
            BookEPub bookEPub = this.b.a.a;
            Mark[] markArr = new Mark[(cVar2.d() - 1)];
            if (markArr.length > 0) {
                Iterator g = cVar2.g();
                int i2 = 0;
                while (g.hasNext()) {
                    cVar2 = (format.epub.common.a.c) g.next();
                    if (cVar2.c != null) {
                        markArr[i2] = new LocalMark(bookEPub.getBookName(), bookEPub.getBookPath(), bookEPub.getLength(), 2, false);
                        markArr[i2].setStartPoint(((long) cVar2.b().a) << 32).setEncoding(bookEPub.getEncoding()).setDescriptionStr(ao.a(cVar2.a(), false));
                        markArr[i2].setPercentStr(String.valueOf(cVar2.c()));
                        markArr[i2].setChapterMarkLevel(cVar2.d);
                        i2++;
                    }
                }
                i.c().a(bookEPub.getBookPath(), markArr, true);
            }
        }
        return true;
    }

    private a.a a(String str) {
        Object substring;
        int indexOf = str.indexOf(35);
        if (indexOf >= 0) {
            substring = str.substring(0, indexOf);
        } else {
            String str2 = str;
        }
        Integer num = (Integer) this.j.get(substring);
        if (num == null) {
            return null;
        }
        if (indexOf != -1) {
            return this.b.a.a(num + str.substring(indexOf));
        }
        num = (Integer) this.k.get(num.toString());
        if (num == null) {
            return null;
        }
        return new a.a(null, num.intValue());
    }

    private void a() {
        a.a a;
        int i;
        if (this.i != null) {
            b bVar = new b(this.b);
            if (bVar.a(this.h + this.i)) {
                Map a2 = bVar.a();
                if (!a2.isEmpty()) {
                    int i2 = 0;
                    for (a aVar : a2.values()) {
                        a = a(aVar.d);
                        if (a != null) {
                            i = a.b;
                        } else {
                            i = -1;
                        }
                        while (i2 > aVar.b) {
                            this.b.f();
                            i2--;
                        }
                        while (true) {
                            i2++;
                            if (i2 > aVar.b) {
                                break;
                            }
                            this.b.a(-2);
                            this.b.b(a);
                        }
                        this.b.a(i);
                        this.b.b(aVar.e);
                        this.b.b(aVar.c.toCharArray());
                    }
                    while (i2 > 0) {
                        this.b.f();
                        i2--;
                    }
                    return;
                }
            }
        }
        Iterator it = (this.e.isEmpty() ? this.f : this.e).iterator();
        while (it.hasNext()) {
            h hVar = (h) it.next();
            a = a(hVar.b);
            if (a != null) {
                i = a.b;
                if (i != -1) {
                    this.b.a(i);
                    this.b.b(hVar.a.toCharArray());
                    this.b.f();
                }
            }
        }
    }

    public boolean a(String str, format.epub.common.core.a.c cVar) {
        String toLowerCase = str.toLowerCase();
        if (this.g != null && toLowerCase.startsWith(this.g)) {
            toLowerCase = toLowerCase.substring(this.g.length());
        }
        toLowerCase = toLowerCase.intern();
        if ("manifest" == toLowerCase) {
            this.l = 1;
        } else if ("spine" == toLowerCase) {
            this.i = (String) this.c.get(cVar.a("toc"));
            this.l = 2;
        } else if ("guide" == toLowerCase) {
            this.l = 3;
        } else if ("tour" == toLowerCase) {
            this.l = 4;
        } else if (this.l == 1 && "item" == toLowerCase) {
            toLowerCase = cVar.a("id");
            r1 = cVar.a("href");
            if (!(toLowerCase == null || r1 == null)) {
                this.c.put(toLowerCase, d.b(r1));
            }
        } else if (this.l == 2 && "itemref" == toLowerCase) {
            toLowerCase = cVar.a("idref");
            if (toLowerCase != null) {
                toLowerCase = (String) this.c.get(toLowerCase);
                if (toLowerCase != null) {
                    this.d.add(toLowerCase);
                }
            }
        } else if (this.l == 3 && "reference" == toLowerCase) {
            toLowerCase = cVar.a("type");
            r1 = cVar.a("title");
            String a = cVar.a("href");
            if (a != null) {
                a = d.b(a);
                if (r1 != null) {
                    this.f.add(new h(r1, a));
                }
                if (toLowerCase != null && "other.ms-coverimage-standard".equals(toLowerCase)) {
                    this.b.e();
                    format.epub.common.b.b b = format.epub.common.b.b.b(this.h + a);
                    r1 = b.d();
                    this.b.a(r1, (short) 0);
                    this.b.a(r1, new format.epub.common.image.a("image/auto", b));
                }
            }
        } else if (this.l == 4 && "site" == toLowerCase) {
            toLowerCase = cVar.a("title");
            r1 = cVar.a("href");
            if (!(toLowerCase == null || r1 == null)) {
                this.e.add(new h(toLowerCase, d.b(r1)));
            }
        }
        return false;
    }

    public boolean c(String str) {
        String toLowerCase = str.toLowerCase();
        if (this.g != null && toLowerCase.startsWith(this.g)) {
            toLowerCase = toLowerCase.substring(this.g.length());
        }
        toLowerCase = toLowerCase.intern();
        if ("manifest" == toLowerCase || "spine" == toLowerCase || "guide" == toLowerCase || "tour" == toLowerCase) {
            this.l = 0;
        }
        return false;
    }

    public boolean g() {
        return true;
    }

    public void a(Map<String, String> map) {
        this.g = null;
        for (Entry entry : map.entrySet()) {
            if ("http://www.idpf.org/2007/opf".equals(entry.getValue())) {
                this.g = ((String) entry.getKey()) + ":";
                return;
            }
        }
    }

    public boolean f() {
        return true;
    }
}

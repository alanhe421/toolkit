package format.epub.common.c.b;

import com.tencent.android.tpush.common.MessageKey;
import format.epub.common.core.a.c;
import format.epub.common.core.a.g;
import format.epub.common.utils.d;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: NCXReader */
class b extends g {
    int a = 0;
    int b = -65535;
    private final TreeMap<Integer, a> c = new TreeMap();
    private final ArrayList<a> d = new ArrayList();
    private String e;

    /* compiled from: NCXReader */
    static class a {
        final int a;
        final int b;
        String c = "";
        String d = "";
        int e = -1;

        a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    b(format.epub.common.a.b bVar) {
    }

    boolean a(String str) {
        format.epub.common.b.b b = format.epub.common.b.b.b(str);
        this.e = d.a(d.a(b));
        return a(b);
    }

    Map<Integer, a> a() {
        return this.c;
    }

    private int b(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public boolean a(String str, c cVar) {
        String intern = str.toLowerCase().intern();
        int b;
        switch (this.a) {
            case 0:
                if (intern == "navmap" || intern == "ncx:navmap") {
                    this.a = 1;
                    break;
                }
            case 1:
                if (intern == "navpoint" || intern == "ncx:navpoint") {
                    intern = cVar.a("playOrder");
                    if (intern != null) {
                        b = b(intern);
                    } else {
                        b = this.b;
                        this.b = b + 1;
                    }
                    this.d.add(new a(b, this.d.size()));
                    this.a = 2;
                    break;
                }
            case 2:
                if (intern != "navpoint" && intern != "ncx:navpoint") {
                    if (intern != "navlabel" && intern != "ncx:navlabel") {
                        if (intern == MessageKey.MSG_CONTENT || intern == "ncx:content") {
                            int size = this.d.size();
                            if (size > 0) {
                                ((a) this.d.get(size - 1)).d = format.epub.common.b.a.a(this.e + d.b(cVar.a("src")));
                                intern = cVar.a("price");
                                if (intern != null && intern.equalsIgnoreCase("0")) {
                                    ((a) this.d.get(size - 1)).e = 1;
                                    break;
                                }
                            }
                        }
                    }
                    this.a = 3;
                    break;
                }
                intern = cVar.a("playOrder");
                if (intern != null) {
                    b = b(intern);
                } else {
                    b = this.b;
                    this.b = b + 1;
                }
                this.d.add(new a(b, this.d.size()));
                break;
                break;
            case 3:
                if ("text" == intern || "ncx:text" == intern) {
                    this.a = 4;
                    break;
                }
        }
        return false;
    }

    public boolean c(String str) {
        String intern = str.toLowerCase().intern();
        switch (this.a) {
            case 1:
                if ("navmap" == intern || "ncx:navmap" == intern) {
                    this.a = 0;
                    break;
                }
            case 2:
                if ("navpoint" == intern || "ncx:navpoint" == intern) {
                    int i;
                    a aVar = (a) this.d.get(this.d.size() - 1);
                    if (aVar.c.length() == 0) {
                        aVar.c = "...";
                    }
                    this.c.put(Integer.valueOf(aVar.a), aVar);
                    this.d.remove(this.d.size() - 1);
                    if (this.d.isEmpty()) {
                        i = 1;
                    } else {
                        i = 2;
                    }
                    this.a = i;
                    break;
                }
            case 3:
                break;
            case 4:
                if ("text" == intern || "ncx:text" == intern) {
                    this.a = 3;
                    break;
                }
        }
        if ("navlabel" == intern || "ncx:navlabel" == intern) {
            this.a = 2;
        }
        return false;
    }

    public void a(char[] cArr, int i, int i2) {
        if (this.a == 4) {
            ArrayList arrayList = this.d;
            a aVar = (a) arrayList.get(arrayList.size() - 1);
            aVar.c += new String(cArr, i, i2);
        }
    }

    public boolean f() {
        return true;
    }
}

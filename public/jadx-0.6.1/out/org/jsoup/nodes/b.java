package org.jsoup.nodes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import org.jsoup.SerializationException;
import org.jsoup.helper.c;
import org.jsoup.nodes.Document.OutputSettings;

/* compiled from: Attributes */
public class b implements Cloneable, Iterable<a> {
    private LinkedHashMap<String, a> a = null;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return d();
    }

    public String a(String str) {
        c.a(str);
        if (this.a == null) {
            return "";
        }
        a aVar = (a) this.a.get(str);
        return aVar != null ? aVar.b() : "";
    }

    public String b(String str) {
        c.a(str);
        if (this.a == null) {
            return "";
        }
        a aVar = (a) this.a.get(str);
        if (aVar != null) {
            return aVar.b();
        }
        for (String str2 : this.a.keySet()) {
            if (str2.equalsIgnoreCase(str)) {
                return ((a) this.a.get(str2)).b();
            }
        }
        return "";
    }

    public void a(String str, String str2) {
        a(new a(str, str2));
    }

    public void a(a aVar) {
        c.a((Object) aVar);
        if (this.a == null) {
            this.a = new LinkedHashMap(2);
        }
        this.a.put(aVar.a(), aVar);
    }

    public void c(String str) {
        c.a(str);
        if (this.a != null) {
            Iterator it = this.a.keySet().iterator();
            while (it.hasNext()) {
                if (((String) it.next()).equalsIgnoreCase(str)) {
                    it.remove();
                }
            }
        }
    }

    public boolean d(String str) {
        return this.a != null && this.a.containsKey(str);
    }

    public boolean e(String str) {
        if (this.a == null) {
            return false;
        }
        for (String equalsIgnoreCase : this.a.keySet()) {
            if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public int a() {
        if (this.a == null) {
            return 0;
        }
        return this.a.size();
    }

    public void a(b bVar) {
        if (bVar.a() != 0) {
            if (this.a == null) {
                this.a = new LinkedHashMap(bVar.a());
            }
            this.a.putAll(bVar.a);
        }
    }

    public Iterator<a> iterator() {
        if (this.a == null || this.a.isEmpty()) {
            return Collections.emptyList().iterator();
        }
        return this.a.values().iterator();
    }

    public List<a> b() {
        if (this.a == null) {
            return Collections.emptyList();
        }
        List arrayList = new ArrayList(this.a.size());
        for (Entry value : this.a.entrySet()) {
            arrayList.add(value.getValue());
        }
        return Collections.unmodifiableList(arrayList);
    }

    public String c() {
        Appendable stringBuilder = new StringBuilder();
        try {
            a(stringBuilder, new Document("").e());
            return stringBuilder.toString();
        } catch (Throwable e) {
            throw new SerializationException(e);
        }
    }

    void a(Appendable appendable, OutputSettings outputSettings) throws IOException {
        if (this.a != null) {
            for (Entry value : this.a.entrySet()) {
                a aVar = (a) value.getValue();
                appendable.append(" ");
                aVar.a(appendable, outputSettings);
            }
        }
    }

    public String toString() {
        return c();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (this.a != null) {
            if (this.a.equals(bVar.a)) {
                return true;
            }
        } else if (bVar.a == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.a != null ? this.a.hashCode() : 0;
    }

    public b d() {
        if (this.a == null) {
            return new b();
        }
        try {
            b bVar = (b) super.clone();
            bVar.a = new LinkedHashMap(this.a.size());
            Iterator it = iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                bVar.a.put(aVar.a(), aVar.e());
            }
            return bVar;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

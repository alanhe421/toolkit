package org.jsoup.nodes;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map.Entry;
import org.jsoup.SerializationException;
import org.jsoup.helper.c;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Document.OutputSettings.Syntax;

/* compiled from: Attribute */
public class a implements Cloneable, Entry<String, String> {
    private static final String[] a = new String[]{"allowfullscreen", "async", "autofocus", "checked", "compact", "declare", "default", "defer", "disabled", "formnovalidate", "hidden", "inert", "ismap", "itemscope", "multiple", "muted", "nohref", "noresize", "noshade", "novalidate", "nowrap", "open", "readonly", "required", "reversed", "seamless", "selected", "sortable", "truespeed", "typemustmatch"};
    private String b;
    private String c;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return e();
    }

    public /* synthetic */ Object getKey() {
        return a();
    }

    public /* synthetic */ Object getValue() {
        return b();
    }

    public /* synthetic */ Object setValue(Object obj) {
        return b((String) obj);
    }

    public a(String str, String str2) {
        c.a((Object) str);
        c.a((Object) str2);
        this.b = str.trim();
        c.a(str);
        this.c = str2;
    }

    public String a() {
        return this.b;
    }

    public void a(String str) {
        c.a(str);
        this.b = str.trim();
    }

    public String b() {
        return this.c;
    }

    public String b(String str) {
        c.a((Object) str);
        String str2 = this.c;
        this.c = str;
        return str2;
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

    protected void a(Appendable appendable, OutputSettings outputSettings) throws IOException {
        appendable.append(this.b);
        if (!a(outputSettings)) {
            appendable.append("=\"");
            Entities.a(appendable, this.c, outputSettings, true, false, false);
            appendable.append('\"');
        }
    }

    public String toString() {
        return c();
    }

    protected final boolean a(OutputSettings outputSettings) {
        return ("".equals(this.c) || this.c.equalsIgnoreCase(this.b)) && outputSettings.c() == Syntax.html && d();
    }

    protected boolean d() {
        return Arrays.binarySearch(a, this.b) >= 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (this.b == null ? aVar.b != null : !this.b.equals(aVar.b)) {
            return false;
        }
        if (this.c != null) {
            if (this.c.equals(aVar.c)) {
                return true;
            }
        } else if (aVar.c == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.b != null) {
            hashCode = this.b.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode *= 31;
        if (this.c != null) {
            i = this.c.hashCode();
        }
        return hashCode + i;
    }

    public a e() {
        try {
            return (a) super.clone();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.helper.b;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Document.OutputSettings.Syntax;

/* compiled from: DocumentType */
public class f extends Node {
    public f(String str, String str2, String str3, String str4, String str5) {
        super(str5);
        b("name", str);
        if (str2 != null) {
            b("pubSysKey", str2);
        }
        b("publicId", str3);
        b("systemId", str4);
    }

    public String a() {
        return "#doctype";
    }

    void a(Appendable appendable, int i, OutputSettings outputSettings) throws IOException {
        if (outputSettings.c() != Syntax.html || a("publicId") || a("systemId")) {
            appendable.append("<!DOCTYPE");
        } else {
            appendable.append("<!doctype");
        }
        if (a("name")) {
            appendable.append(" ").append(q("name"));
        }
        if (a("pubSysKey")) {
            appendable.append(" ").append(q("pubSysKey"));
        }
        if (a("publicId")) {
            appendable.append(" \"").append(q("publicId")).append('\"');
        }
        if (a("systemId")) {
            appendable.append(" \"").append(q("systemId")).append('\"');
        }
        appendable.append('>');
    }

    void b(Appendable appendable, int i, OutputSettings outputSettings) {
    }

    private boolean a(String str) {
        return !b.a(q(str));
    }
}

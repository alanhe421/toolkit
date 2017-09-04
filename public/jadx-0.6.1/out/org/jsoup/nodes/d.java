package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.nodes.Document.OutputSettings;

/* compiled from: Comment */
public class d extends Node {
    public d(String str, String str2) {
        super(str2);
        this.c.a("comment", str);
    }

    public String a() {
        return "#comment";
    }

    public String b() {
        return this.c.a("comment");
    }

    void a(Appendable appendable, int i, OutputSettings outputSettings) throws IOException {
        if (outputSettings.d()) {
            c(appendable, i, outputSettings);
        }
        appendable.append("<!--").append(b()).append("-->");
    }

    void b(Appendable appendable, int i, OutputSettings outputSettings) {
    }

    public String toString() {
        return c();
    }
}

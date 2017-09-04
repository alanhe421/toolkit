package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.nodes.Document.OutputSettings;

/* compiled from: XmlDeclaration */
public class j extends Node {
    private final String f;
    private final boolean g;

    public String a() {
        return "#declaration";
    }

    void a(Appendable appendable, int i, OutputSettings outputSettings) throws IOException {
        appendable.append("<").append(this.g ? "!" : "?").append(this.f);
        this.c.a(appendable, outputSettings);
        appendable.append(this.g ? "!" : "?").append(">");
    }

    void b(Appendable appendable, int i, OutputSettings outputSettings) {
    }

    public String toString() {
        return c();
    }
}

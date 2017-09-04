package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.nodes.Document.OutputSettings;

/* compiled from: DataNode */
public class e extends Node {
    public e(String str, String str2) {
        super(str2);
        this.c.a("data", str);
    }

    public String a() {
        return "#data";
    }

    public String b() {
        return this.c.a("data");
    }

    void a(Appendable appendable, int i, OutputSettings outputSettings) throws IOException {
        appendable.append(b());
    }

    void b(Appendable appendable, int i, OutputSettings outputSettings) {
    }

    public String toString() {
        return c();
    }
}

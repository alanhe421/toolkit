package org.jsoup.parser;

import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.g;

/* compiled from: Parser */
public class e {
    public static Document a(String str, String str2) {
        i bVar = new b();
        return bVar.a(str, str2, ParseErrorList.noTracking(), bVar.a());
    }

    public static List<Node> a(String str, g gVar, String str2) {
        b bVar = new b();
        return bVar.a(str, gVar, str2, ParseErrorList.noTracking(), bVar.a());
    }
}

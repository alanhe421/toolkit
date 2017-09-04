package format.epub.common.utils;

import android.util.Xml;
import android.util.Xml.Encoding;
import format.epub.common.b.b;
import org.xml.sax.helpers.DefaultHandler;

/* compiled from: XmlUtil */
public abstract class g {
    public static boolean a(b bVar, DefaultHandler defaultHandler) {
        try {
            Xml.parse(bVar.i(), Encoding.UTF_8, defaultHandler);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

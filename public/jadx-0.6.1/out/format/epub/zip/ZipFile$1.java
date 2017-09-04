package format.epub.zip;

import java.util.LinkedHashMap;

class ZipFile$1 extends LinkedHashMap<String, c> {
    final /* synthetic */ f this$0;

    ZipFile$1(f fVar) {
        this.this$0 = fVar;
    }

    public c get(Object obj) {
        return (c) super.get(((String) obj).toLowerCase());
    }

    public c put(String str, c cVar) {
        return (c) super.put(str.toLowerCase(), cVar);
    }
}

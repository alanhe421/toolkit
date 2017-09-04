package org.java_websocket.b;

import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;

/* compiled from: HandshakedataImpl1 */
public class g implements c {
    private byte[] a;
    private TreeMap<String, String> b = new TreeMap(String.CASE_INSENSITIVE_ORDER);

    public Iterator<String> b() {
        return Collections.unmodifiableSet(this.b.keySet()).iterator();
    }

    public String b(String str) {
        String str2 = (String) this.b.get(str);
        if (str2 == null) {
            return "";
        }
        return str2;
    }

    public byte[] c() {
        return this.a;
    }

    public void a(byte[] bArr) {
        this.a = bArr;
    }

    public void a(String str, String str2) {
        this.b.put(str, str2);
    }

    public boolean c(String str) {
        return this.b.containsKey(str);
    }
}

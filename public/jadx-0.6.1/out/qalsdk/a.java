package qalsdk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: Cache */
public interface a {

    /* compiled from: Cache */
    public static class a implements Serializable {
        private static final long v = 7570494979244869097L;
        public int a;
        public String b;
        public String c;
        public List<String> d = new ArrayList();
        public String e;
        public String f;
        public String g;
        public List<String> h = new ArrayList();
        public List<String> i = new ArrayList();
        public String j;
        public String k;
        public List<String> l = new ArrayList();
        public String m;
        public String n;
        public long o;
        public long p;
        public long q;
        public Map<String, String> r = new HashMap();
        public byte[] s;
        long t;
        long u;

        public boolean a() {
            return this.t < System.currentTimeMillis() / 1000;
        }

        public boolean b() {
            return this.u > System.currentTimeMillis() / 1000;
        }
    }

    a a(String str);

    void a();

    void a(String str, a aVar);

    void b(String str);

    void c(String str);
}

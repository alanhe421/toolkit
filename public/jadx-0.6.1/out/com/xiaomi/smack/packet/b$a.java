package com.xiaomi.smack.packet;

public class b$a {
    public static final b$a a = new b$a("get");
    public static final b$a b = new b$a("set");
    public static final b$a c = new b$a("result");
    public static final b$a d = new b$a("error");
    public static final b$a e = new b$a("command");
    private String f;

    private b$a(String str) {
        this.f = str;
    }

    public static b$a a(String str) {
        if (str == null) {
            return null;
        }
        String toLowerCase = str.toLowerCase();
        return a.toString().equals(toLowerCase) ? a : b.toString().equals(toLowerCase) ? b : d.toString().equals(toLowerCase) ? d : c.toString().equals(toLowerCase) ? c : e.toString().equals(toLowerCase) ? e : null;
    }

    public String toString() {
        return this.f;
    }
}

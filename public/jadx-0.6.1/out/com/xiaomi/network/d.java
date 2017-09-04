package com.xiaomi.network;

import java.net.InetSocketAddress;

public final class d {
    private String a;
    private int b;

    public d(String str, int i) {
        this.a = str;
        this.b = i;
    }

    public static d a(String str, int i) {
        int lastIndexOf = str.lastIndexOf(":");
        if (lastIndexOf != -1) {
            String substring = str.substring(0, lastIndexOf);
            try {
                lastIndexOf = Integer.parseInt(str.substring(lastIndexOf + 1));
                if (lastIndexOf > 0) {
                    i = lastIndexOf;
                }
                str = substring;
            } catch (NumberFormatException e) {
                str = substring;
            }
        }
        return new d(str, i);
    }

    public static InetSocketAddress b(String str, int i) {
        d a = a(str, i);
        return new InetSocketAddress(a.b(), a.a());
    }

    public int a() {
        return this.b;
    }

    public String b() {
        return this.a;
    }

    public String toString() {
        return this.b > 0 ? this.a + ":" + this.b : this.a;
    }
}

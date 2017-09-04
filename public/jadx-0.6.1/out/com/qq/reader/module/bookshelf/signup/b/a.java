package com.qq.reader.module.bookshelf.signup.b;

/* compiled from: SignPrizeBaseInfo */
public class a {
    private String a;
    private int b;
    private int c;

    public void a(int i) {
        this.b = i;
    }

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public int b() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((a) obj).a);
    }
}

package com.qq.reader.module.bookchapter.online;

import java.util.ArrayList;
import java.util.List;

/* compiled from: OnlineNoPayBook */
public class i {
    private List<Integer> a = new ArrayList();
    private String b;
    private long c;
    private int d;

    public i(String str) {
        this.b = str;
    }

    public List<Integer> a() {
        return this.a;
    }

    public void a(int i) {
        this.a.add(Integer.valueOf(i));
    }

    public String b() {
        return this.b;
    }

    public long c() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public int d() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }
}

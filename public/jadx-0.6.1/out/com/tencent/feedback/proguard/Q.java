package com.tencent.feedback.proguard;

import java.io.Serializable;

/* compiled from: RQDSRC */
public class q implements Serializable, Comparable<q> {
    private long a;
    private long b;
    private int c;
    private String d;
    private String e;

    public /* synthetic */ int compareTo(Object obj) {
        return a((q) obj);
    }

    private synchronized int a(q qVar) {
        return (int) (this.b - qVar.b);
    }

    public final synchronized long a() {
        return this.b;
    }

    public final synchronized void a(long j) {
        this.b = j;
    }

    public final synchronized int b() {
        return this.c;
    }

    public final synchronized void a(int i) {
        this.c = i;
    }

    public final synchronized String c() {
        return this.d;
    }

    public final synchronized void a(String str) {
        this.d = str;
    }

    public final synchronized long d() {
        return this.a;
    }

    public final synchronized void b(long j) {
        this.a = j;
    }

    public final synchronized String e() {
        return this.e;
    }

    public final synchronized void b(String str) {
        this.e = str;
    }
}

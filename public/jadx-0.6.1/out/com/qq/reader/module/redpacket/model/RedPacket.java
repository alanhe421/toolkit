package com.qq.reader.module.redpacket.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class RedPacket implements Parcelable {
    public static final Creator<RedPacket> CREATOR = new Creator<RedPacket>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public RedPacket a(Parcel parcel) {
            return new RedPacket(parcel);
        }

        public RedPacket[] a(int i) {
            return new RedPacket[i];
        }
    };
    private int a = -1;
    private long b;
    private long c;
    private long d;
    private long e;
    private long f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private int l;
    private int m;
    private int n;
    private String o;
    private String p;
    private int q;
    private int r = 0;
    private int s;
    private int t;
    private int u = 1;
    private long v;
    private String w;
    private boolean x;

    protected RedPacket(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readLong();
        this.d = parcel.readLong();
        this.e = parcel.readLong();
        this.f = parcel.readLong();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readString();
        this.l = parcel.readInt();
        this.m = parcel.readInt();
        this.n = parcel.readInt();
        this.o = parcel.readString();
        this.p = parcel.readString();
        this.q = parcel.readInt();
        this.r = parcel.readInt();
        this.s = parcel.readInt();
        this.t = parcel.readInt();
        this.u = parcel.readInt();
        this.v = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeLong(this.b);
        parcel.writeLong(this.d);
        parcel.writeLong(this.e);
        parcel.writeLong(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeInt(this.l);
        parcel.writeInt(this.m);
        parcel.writeInt(this.n);
        parcel.writeString(this.o);
        parcel.writeString(this.p);
        parcel.writeInt(this.q);
        parcel.writeInt(this.r);
        parcel.writeInt(this.s);
        parcel.writeInt(this.t);
        parcel.writeInt(this.u);
        parcel.writeLong(this.v);
    }

    public int describeContents() {
        return 0;
    }

    public long a() {
        return this.v;
    }

    public void a(long j) {
        this.v = j;
    }

    public boolean b() {
        return this.x;
    }

    public void a(boolean z) {
        this.x = z;
    }

    public int c() {
        return this.t;
    }

    public void a(int i) {
        this.t = i;
    }

    public long d() {
        return this.b;
    }

    public void b(long j) {
        this.b = j;
    }

    public long e() {
        return this.d;
    }

    public void c(long j) {
        this.d = j;
    }

    public long f() {
        return this.e;
    }

    public void d(long j) {
        this.e = j;
    }

    public long g() {
        return this.f;
    }

    public void e(long j) {
        this.f = j;
    }

    public String h() {
        return this.h;
    }

    public void a(String str) {
        this.h = str;
    }

    public String i() {
        return this.i;
    }

    public void b(String str) {
        this.i = str;
    }

    public int j() {
        return this.l;
    }

    public void b(int i) {
        this.l = i;
    }

    public int k() {
        return this.m;
    }

    public void c(int i) {
        this.m = i;
    }

    public String l() {
        return this.o;
    }

    public void c(String str) {
        this.o = str;
    }

    public String m() {
        return this.p;
    }

    public void d(String str) {
        this.p = str;
    }

    public int n() {
        return this.s;
    }

    public void d(int i) {
        this.s = i;
    }

    public int o() {
        return this.n;
    }

    public void e(int i) {
        this.n = i;
    }

    public void e(String str) {
        this.k = str;
    }

    public int p() {
        return this.a;
    }

    public void f(int i) {
        this.a = i;
    }

    public void f(String str) {
        this.j = str;
    }

    public String q() {
        return this.j;
    }

    public void g(String str) {
        this.w = str;
    }

    public String r() {
        return this.w;
    }

    public void g(int i) {
        this.q = i;
    }

    public int s() {
        return this.q;
    }

    public void h(int i) {
        this.r = i;
    }

    public int t() {
        return this.r;
    }

    public long u() {
        return this.c;
    }

    public void f(long j) {
        this.c = j;
    }

    public int v() {
        return this.u;
    }

    public void i(int i) {
        this.u = i;
    }
}

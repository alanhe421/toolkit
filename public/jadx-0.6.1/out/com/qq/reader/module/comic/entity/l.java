package com.qq.reader.module.comic.entity;

import com.google.gson.annotations.SerializedName;
import com.qq.reader.common.charge.voucher.a.d;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ComicDirectoryInfo */
public class l {
    @SerializedName("id")
    private String a;
    @SerializedName("title")
    private String b;
    @SerializedName("fullChapterList")
    private List<o> c;
    @SerializedName("packageSize")
    private String d;
    @SerializedName("discount")
    private int e;
    @SerializedName("disDesc")
    private String f;
    @SerializedName("disStart")
    private long g;
    @SerializedName("disEnd")
    private long h;
    @SerializedName("totalPrice")
    private int i;
    @SerializedName("retCode")
    private String j;
    @SerializedName("version")
    private long k;
    @SerializedName("buyType")
    private int l;
    @SerializedName("payType")
    private int m;
    private boolean n;
    private List<d> o = new ArrayList();

    public boolean a() {
        long currentTimeMillis = System.currentTimeMillis();
        return this.e == 0 && currentTimeMillis > this.g && currentTimeMillis < this.h;
    }

    public boolean b() {
        return this.m == 2;
    }

    public boolean c() {
        return this.l == 2;
    }

    public boolean d() {
        return this.n;
    }

    public void a(boolean z) {
        this.n = z;
    }

    public String e() {
        return this.a;
    }

    public int f() {
        return Integer.parseInt(this.d);
    }

    public String g() {
        return this.b;
    }

    public List<o> h() {
        return this.c;
    }

    public int i() {
        if (h() == null || h().isEmpty()) {
            return 0;
        }
        return h().size();
    }

    public int j() {
        if (this.g == 0 || this.h == 0) {
            return this.e;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis <= this.g || currentTimeMillis >= this.h) {
            return 100;
        }
        return this.e;
    }

    public int k() {
        return this.i;
    }

    public String l() {
        return this.f;
    }

    public String m() {
        return this.j;
    }

    public long n() {
        return this.k;
    }

    public List<d> o() {
        return this.o;
    }

    public List<n> p() {
        List<n> arrayList = new ArrayList();
        if (this.c == null || this.c.isEmpty()) {
            return arrayList;
        }
        n nVar;
        int i;
        if (this.c.size() <= Integer.parseInt(this.d)) {
            nVar = new n();
            for (i = 0; i < this.c.size(); i++) {
                nVar.a((o) this.c.get(i));
            }
            arrayList.add(nVar);
        } else {
            nVar = new n();
            int i2 = 0;
            for (i = 0; i < this.c.size(); i++) {
                if (i2 < Integer.parseInt(this.d)) {
                    nVar.a((o) this.c.get(i));
                    i2++;
                } else {
                    if (nVar.a() != 0) {
                        arrayList.add(nVar);
                        nVar = new n();
                    }
                    nVar.a((o) this.c.get(i));
                    i2 = 1;
                }
            }
            if (nVar.a() != 0) {
                arrayList.add(nVar);
            }
        }
        return arrayList;
    }

    public int q() {
        return this.l;
    }
}

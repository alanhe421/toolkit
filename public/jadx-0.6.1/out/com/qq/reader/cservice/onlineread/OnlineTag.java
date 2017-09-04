package com.qq.reader.cservice.onlineread;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.monitor.a.b;
import com.qq.reader.common.utils.ao;
import java.io.File;

public class OnlineTag implements Parcelable, Cloneable, Comparable<OnlineTag> {
    public static final Creator<OnlineTag> CREATOR = new Creator<OnlineTag>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public OnlineTag[] a(int i) {
            return new OnlineTag[i];
        }

        public OnlineTag a(Parcel parcel) {
            return new OnlineTag(parcel);
        }
    };
    private long A;
    private String B = "";
    private boolean C;
    private String D = "";
    private int E = 1;
    private boolean F;
    private String G;
    boolean a = true;
    private String b = "";
    private long c = -1;
    private String d = "";
    private String e = "";
    private String f = "";
    private String g = "";
    private int h = 0;
    private volatile int i = 0;
    private String j = "";
    private long k = 0;
    private String l = "";
    private String m = "";
    private int n = 0;
    private String o = "";
    private String p = "";
    private int q = 0;
    private boolean r = true;
    private int s = this.h;
    private int t;
    private String u = "txt";
    private String v = "";
    private int w = 0;
    private boolean x = false;
    private long y;
    private String z = "";

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return z();
    }

    public /* synthetic */ int compareTo(Object obj) {
        return a((OnlineTag) obj);
    }

    public boolean a() {
        return this.a;
    }

    public void a(boolean z) {
        this.a = z;
    }

    public String b() {
        return this.f;
    }

    public OnlineTag a(String str) {
        this.f = str;
        return this;
    }

    public String c() {
        String c = v.c(this.b);
        if (c != null) {
            return c + File.separator;
        }
        return c;
    }

    public String d() {
        String c = c();
        if (c != null) {
            return c + "chapter.q";
        }
        return null;
    }

    public String e() {
        String c = c();
        if (c != null) {
            return c + "book.q";
        }
        return null;
    }

    public String f() {
        return v.a(this.b, g());
    }

    public String a(int i) {
        return v.a(this.b, i);
    }

    public String b(int i) {
        return a(i) + ".zip";
    }

    public int g() {
        return this.h;
    }

    public OnlineTag c(int i) {
        this.h = i;
        return this;
    }

    public String h() {
        return this.j;
    }

    public OnlineTag b(String str) {
        this.j = str;
        return this;
    }

    public long i() {
        return this.k;
    }

    public OnlineTag a(long j) {
        this.k = j;
        return this;
    }

    public String j() {
        return this.l;
    }

    public OnlineTag c(String str) {
        this.l = str;
        return this;
    }

    public OnlineTag d(String str) {
        this.m = str;
        return this;
    }

    public String k() {
        return this.b;
    }

    public String l() {
        return this.d;
    }

    public long m() {
        return this.c;
    }

    public OnlineTag b(long j) {
        this.c = j;
        return this;
    }

    public int n() {
        return this.i;
    }

    public OnlineTag d(int i) {
        this.i = i;
        return this;
    }

    public String o() {
        return this.g;
    }

    public OnlineTag e(String str) {
        this.g = str;
        return this;
    }

    public String p() {
        return this.o;
    }

    public OnlineTag f(String str) {
        this.o = str;
        return this;
    }

    public int q() {
        return this.n;
    }

    public OnlineTag e(int i) {
        this.n = i;
        return this;
    }

    public boolean r() {
        return this.r;
    }

    public void b(boolean z) {
        this.r = z;
    }

    public int s() {
        return this.s;
    }

    public int t() {
        return this.q;
    }

    public OnlineTag f(int i) {
        this.q = i;
        return this;
    }

    public void g(int i) {
        this.s = i;
    }

    public String u() {
        this.p = ao.q(this.p);
        return this.p;
    }

    public String v() {
        return this.v;
    }

    public OnlineTag g(String str) {
        this.v = str;
        return this;
    }

    public int w() {
        return this.w;
    }

    public OnlineTag h(int i) {
        this.w = i;
        return this;
    }

    public OnlineTag h(String str) {
        if (str == null || str.trim().length() <= 0) {
            this.p = ao.g(Long.parseLong(this.b));
        } else {
            this.p = str;
        }
        return this;
    }

    public OnlineTag c(boolean z) {
        this.x = z;
        return this;
    }

    public boolean x() {
        return this.x;
    }

    public String y() {
        return this.B;
    }

    public OnlineTag i(String str) {
        if (str != null) {
            this.B = str;
        } else {
            this.B = "";
        }
        return this;
    }

    public OnlineTag(String str, String str2, long j) {
        this.b = str;
        if (str2 != null) {
            this.d = str2;
        }
        if (j > 0) {
            this.c = j;
        } else {
            this.c = System.currentTimeMillis();
        }
    }

    public String a(String str, String str2) {
        i(b.a(this.b));
        StringBuffer stringBuffer = new StringBuffer();
        switch (this.E) {
            case 1:
                stringBuffer.append(e.a(str));
                stringBuffer.append("bookId=");
                stringBuffer.append(k());
                stringBuffer.append("&");
                stringBuffer.append("usepreview=1");
                stringBuffer.append("&");
                stringBuffer.append("type=");
                stringBuffer.append(q());
                stringBuffer.append("&");
                stringBuffer.append("tafauth=1");
                stringBuffer.append("&");
                stringBuffer.append("scids=");
                break;
            case 2:
                stringBuffer.append(e.N);
                stringBuffer.append("adid=");
                stringBuffer.append(k());
                stringBuffer.append("&");
                stringBuffer.append("acid=");
                break;
        }
        return stringBuffer.toString();
    }

    public String j(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        switch (this.E) {
            case 1:
                stringBuilder.append(e.a(str));
                break;
            case 2:
                stringBuilder.append(e.b(str));
                break;
        }
        stringBuilder.append("bookId=");
        stringBuilder.append(k());
        stringBuilder.append("&");
        stringBuilder.append("type=");
        stringBuilder.append(q());
        stringBuilder.append("&");
        stringBuilder.append("tafauth=1");
        stringBuilder.append("&");
        stringBuilder.append("useindex=1");
        stringBuilder.append("&");
        stringBuilder.append("scids=");
        stringBuilder.append(0);
        return stringBuilder.toString();
    }

    public OnlineTag z() {
        try {
            return (OnlineTag) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public int a(OnlineTag onlineTag) {
        return (int) (onlineTag.m() - m());
    }

    public int A() {
        return this.t;
    }

    public OnlineTag i(int i) {
        this.t = i;
        return this;
    }

    public String B() {
        return this.u;
    }

    public OnlineTag k(String str) {
        this.u = str;
        return this;
    }

    public boolean equals(Object obj) {
        try {
            return k().equals(((OnlineTag) obj).k());
        } catch (Exception e) {
            return false;
        }
    }

    public OnlineTag(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        this.b = parcel.readString();
        this.c = parcel.readLong();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readInt();
        this.i = parcel.readInt();
        this.j = parcel.readString();
        this.k = parcel.readLong();
        this.l = parcel.readString();
        this.m = parcel.readString();
        this.n = parcel.readInt();
        this.o = parcel.readString();
        this.p = parcel.readString();
        this.q = parcel.readInt();
        this.r = parcel.readInt() == 1;
        this.s = parcel.readInt();
        this.t = parcel.readInt();
        this.u = parcel.readString();
        this.v = parcel.readString();
        this.w = parcel.readInt();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.x = z;
        this.y = parcel.readLong();
        this.z = parcel.readString();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.C = z;
        this.E = parcel.readInt();
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        this.F = z2;
        this.A = parcel.readLong();
        this.G = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeString(this.b);
        parcel.writeLong(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
        parcel.writeString(this.j);
        parcel.writeLong(this.k);
        parcel.writeString(this.l);
        parcel.writeString(this.m);
        parcel.writeInt(this.n);
        parcel.writeString(this.o);
        parcel.writeString(this.p);
        parcel.writeInt(this.q);
        parcel.writeInt(this.r ? 1 : 0);
        parcel.writeInt(this.s);
        parcel.writeInt(this.t);
        parcel.writeString(this.u);
        parcel.writeString(this.v);
        parcel.writeInt(this.w);
        if (this.x) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeLong(this.y);
        parcel.writeString(this.z);
        if (this.C) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeInt(this.E);
        if (!this.F) {
            i3 = 0;
        }
        parcel.writeInt(i3);
        parcel.writeLong(this.A);
        parcel.writeString(this.G);
    }

    public long C() {
        return this.y;
    }

    public OnlineTag c(long j) {
        this.y = j;
        return this;
    }

    public String D() {
        return this.z;
    }

    public OnlineTag l(String str) {
        this.z = str;
        return this;
    }

    public boolean E() {
        return this.C;
    }

    public void d(boolean z) {
        this.C = z;
    }

    public int F() {
        return this.E;
    }

    public OnlineTag j(int i) {
        this.E = i;
        return this;
    }

    public boolean G() {
        return this.F;
    }

    public void e(boolean z) {
        this.F = z;
    }

    public void m(String str) {
        this.G = str;
    }

    public long H() {
        return this.A;
    }

    public void d(long j) {
        this.A = j;
    }
}
